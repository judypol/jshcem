package com.shcem.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.shcem.common.IRedisCache;
import com.shcem.utils.StringUtils;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 非集群Redis操作
 */
public class SingleRedisCache implements IRedisCache {

    private static int cnt = 0;
    private String host;
    private int database;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    private int MAX_TOTAL = 1000;
    private int MAX_IDLE = 5;
    private String DBIndex="0";             //默认的DBIndex

    private static JedisPool jedisPool;
    //Jedis jedis;

    /**
     * 构造函数
     * @param host
     * @param port
     * @param database
     * @param password
     */
    public SingleRedisCache(String host, int port, String database, String password) {
        this.DBIndex=database;
        if (jedisPool == null) {
            synchronized (this) {
                if (jedisPool == null) {
                    GenericObjectPoolConfig redisConfig = new GenericObjectPoolConfig();
                    redisConfig.setMaxTotal(MAX_TOTAL);
                    redisConfig.setMaxIdle(MAX_IDLE);
                    redisConfig.setTestOnBorrow(true);
                    if(StringUtils.isNotEmpty(password)){
                        jedisPool = new JedisPool(redisConfig, host, port, 2000, password, 0);
                    }else{
                        jedisPool = new JedisPool(redisConfig, host, port, 2000, null, 0);
                    }

                    //jedis = jedisPool.getResource();
                }
            }
        }
    }

    /**
     * 构造函数
     * @param host
     * @param port
     * @param database
     */
    public SingleRedisCache(String host, int port, String database) {
        this(host, port, database, null);
    }

    /**
     *
     * @param host
     * @param port
     */
    public SingleRedisCache(String host, int port) {
        this(host, port, "0");
    }

    /**
     * 生成新的key
     * @param key
     * @return
     */
    private String getKey(String key){
        if(key.startsWith(this.DBIndex+":")){
            return key;
        }
        return this.DBIndex+":"+key;
    }

    /**
     * 是否覆盖旧值
     * @param key
     * @param value
     * @param expire
     * @param replace
     */
    public void setValue(String key,Object value,long expire,boolean replace) throws Exception{
        Jedis jedis=jedisPool.getResource();
        String newKey=getKey(key);
        try{
            String jsonString=serialiseObject(value);
            jedis.set(newKey,jsonString,"NX","EX",expire);
        }catch (Exception ex){
            throw new Exception("setValue--Exception",ex);
        }finally {
            jedis.close();
        }
    }
    /**
     * redis设置一个值，
     *
     * @param key
     * @param value
     * @param expire 过期时间（秒为单位）
     **/
    public void SetValue(String key, Object value, long expire) throws Exception {
        Jedis jedis=jedisPool.getResource();
        String newKey=getKey(key);
        try {
            String jsonString = serialiseObject(value);
            if (this.HasKey(newKey)) {
                this.DeleteKey(newKey);
            }
            jedis.set(newKey, jsonString, "NX", "EX", expire);
        } catch (Exception e) {
            throw new Exception("SetValue--Exception", e);
        }finally {
            jedis.close();
        }
    }

    /**
     *
     * @param value
     * @return
     */
    private String serialiseObject(Object value){
        return JSON.toJSONString(value);
    }
    /**
     * redis设置一个值，
     *
     * @param key
     * @param value
     */
    public void SetValue(String key, Object value) throws Exception {
        String newKey=getKey(key);
        Jedis jedis=jedisPool.getResource();
        Long expireTime=1800L;
        try {
            if(jedis.exists(newKey)){
                expireTime=jedis.ttl(newKey);
            }
            String jsonString = serialiseObject(value);
            jedis.set(newKey, jsonString);
            jedis.expire(newKey,expireTime.intValue());
        } catch (Exception e) {
            throw new Exception("SetValue--Exception", e);
        }finally {
            jedis.close();
        }
    }

    /**
     * 如果不存在则设置值，否则返回
     *
     * @param key
     * @param value
     */
    public long SetNX(String key, String value) throws Exception {
        String newKey=getKey(key);
        Jedis jedis=jedisPool.getResource();
        try{
            long index=jedis.setnx(newKey,value);
            return index;
        }catch (Exception ex){
            throw new Exception(ex);
        }finally {
            jedis.close();
        }
    }

    /**
     * 判断redis中是否存在此key
     *
     * @param key
     */
    public boolean HasKey(String key) {
        String newKey=getKey(key);
        Jedis jedis=jedisPool.getResource();
        try {
            boolean flag = jedis.exists(newKey);
            return flag;
        } catch (Exception e) {
            return false;
        }finally {
            jedis.close();
        }
    }

    /**
     * 判断redis中是否存在keys
     *
     * @param keys
     */
    public long HasKeys(String... keys) {
        Jedis jedis=jedisPool.getResource();
        try {
            int size=keys.length;
            String[] newKeys= new String[keys.length];
            for(int i=0;i<size;i++){
                String newKey=getKey(keys[i]);
                newKeys[i]=newKey;
            }
            long ret = jedis.exists(newKeys);
            return ret;
        } catch (Exception e) {
            return 0L;
        }finally {
            jedis.close();
        }

    }

    /**
     * 删除一个key
     *
     * @param key
     */
    public long DeleteKey(String key) {
        String newKey=getKey(key);
        Jedis jedis=jedisPool.getResource();
        try {
            return jedis.del(newKey);
        } catch (Exception e) {
            return 0L;
        }finally {
            jedis.close();
        }

    }

    /**
     * 同时删除多个keys
     *
     * @param keys
     */
    public long DeleteKeys(String... keys) {
        Jedis jedis=jedisPool.getResource();
        try {
            String[] newKeys=new String[keys.length];
            for(int i=0;i<keys.length;i++){
                newKeys[i]=getKey(keys[i]);
            }
            return jedis.del(newKeys);
        } catch (Exception e) {
            return 0L;
        }finally {
            jedis.close();
        }
    }

    /**
     * 获取一个指定的值
     *
     * @param key
     */
    public Object Get(String key) {
        String newKey=getKey(key);
        Jedis jedis=jedisPool.getResource();
        try {
            String val = jedis.get(newKey);
            Object obj = JSON.parse(val);
            return obj;
        } catch (Exception e) {
            return null;
        }finally {
            jedis.close();
        }
    }

    public JSONObject GetJSONObject(String key) {
        String newKey=getKey(key);
        Jedis jedis=jedisPool.getResource();
        try {
            String val = jedis.get(newKey);
            JSONObject jobj = JSON.parseObject(val);
            return jobj;
        } catch (Exception e) {
            return null;
        }finally {
            jedis.close();
        }
    }

    /**
     * 获取一个指定的值
     *
     * @param key
     */
    public <T> T Get(String key, Class<T> cls) {
        Jedis jedis=jedisPool.getResource();
        String newKey=getKey(key);
        try {
            String val = jedis.get(newKey);
            T obj = JSON.parseObject(val, cls);
            return obj;
        } catch (Exception e) {
            return null;
        }finally {
            jedis.close();
        }
    }

    /**
     * 泛型转换
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public <T> T Get(String key,TypeReference<T> type){
        Jedis jedis=jedisPool.getResource();
        try {
            String newKey=getKey(key);
            String val = jedis.get(newKey);
            T obj = JSON.parseObject(val,type);
            return obj;
        } catch (Exception e) {
            return null;
        }finally {
            jedis.close();
        }
    }

    /**默认的dbindex中
     * 查找redis中的符合条件的Keys，通过key直接查找
     */
    public Set<String> FindKeys(String pattern) {
        return this.FindKeys(pattern,false);
    }

    /**
     *
     * @param pattern
     * @return
     */
    private Set<String> findKeysAll(String pattern){
        Jedis jedis=jedisPool.getResource();
        String keysPattern=this.DBIndex+":*"+pattern+"*";
        try{
            return jedis.keys(keysPattern);
        }catch (Exception ex){
            return null;
        }finally {
            jedis.close();
        }
    }
    /**
     * 获取redis中的所有的key
     *
     * @param pattern
     * @param isAll
     * @return
     */
    @Override
    public Set<String> FindKeys(String pattern, boolean isAll) {
        Set<String> allKeys=findKeysAll(pattern);
        if(isAll){
            return allKeys;
        }else{
            Set<String> keysInDbIndex=new HashSet<>();
            for(String key:allKeys){
                if(key.startsWith(this.DBIndex+":")){
                    keysInDbIndex.add(key);
                }
            }
            return keysInDbIndex;
        }
    }

    /**
     * 正则表达式从RedisValues中查找
     * */
    public Set<String> FindKeysByStringContent(String pattern){
        return this.FindKeyByStringContent(pattern,false);
    }
    private String getString(String key){
        Jedis jedis=jedisPool.getResource();
        try{
            return jedis.get(key);
        }catch (Exception ex){
            return null;
        }finally {
            jedis.close();
        }
    }
    /**
     *
     * @param pattern
     * @return
     */
    private Set<String> findKeyByStringContent(String pattern){
        Set<String> keys=new HashSet<String>();
        Set<String> tKeys=this.findKeysAll(this.DBIndex+":*");//得到所有的key值
        for (String key : tKeys){
            try{
                Object val=this.getString(key);
                if(val==null){
                    continue;
                }
                String stringVal=JSON.toJSONString(val);
                Pattern pat=Pattern.compile(pattern);
                Matcher matcher=pat.matcher(stringVal);
                if(matcher.find()){
                    keys.add(key);
                }else {
                    continue;
                }
            }catch (Exception e){

                continue;
            }
        }

        return keys;
    }
    /**
     * 正则表达式从RedisValues中查找
     *
     * @param pattern
     * @param isAll
     * @return
     */
    @Override
    public Set<String> FindKeyByStringContent(String pattern, boolean isAll) {
        Set<String> allKeys=this.findKeyByStringContent(pattern);
        if(isAll){
            return allKeys;
        }else{
            Set<String> newAllKeys=new HashSet<>();
            for(String key:allKeys){
                if(key.startsWith(this.DBIndex+":")){
                    newAllKeys.add(key);
                }
            }
            return newAllKeys;
        }
    }

    /**
     * 设置key的过期时间
     *
     * @param key
     * @param expire (以秒为单位)
     */
    public void SetExpire(String key, int expire) {
        Jedis jedis=jedisPool.getResource();
        try{
            String newKey=getKey(key);
            jedis.expire(newKey, expire);
        }catch (Exception ex){

        }finally {
            jedis.close();
        }

    }

    public void Close() {
        //jedis.close();
    }
}
