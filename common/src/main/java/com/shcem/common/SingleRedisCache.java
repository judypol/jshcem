package com.shcem.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 非集群Redis操作
 */
public class SingleRedisCache implements IRedisCache {

    private static final BlockingQueue<Jedis> jedisPools = new LinkedBlockingQueue<Jedis>();
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

    private int MAX_TOTAL = 100;
    private int MAX_IDLE = 50;

    //JedisPool jedisPool;
    private static JedisPool jedisPool;
    //Jedis jedis;

    public SingleRedisCache(String host, int port, int database, String password) {
        if (jedisPool == null) {
            synchronized (this) {
                if (jedisPool == null) {
                    GenericObjectPoolConfig redisConfig = new GenericObjectPoolConfig();
                    redisConfig.setMaxTotal(MAX_TOTAL);
                    redisConfig.setMaxIdle(MAX_IDLE);
                    redisConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(redisConfig, host, port, 2000, null, 0);
                    //jedis = jedisPool.getResource();
                }
            }
        }
//        else {
//            jedis = jedisPool.getResource();
//        }

    }

    private SingleRedisCache(String host, int port, int database) {
        this(host, port, database, null);
    }

    public SingleRedisCache(String host, int port) {
        this(host, port, 0);
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
        try {
            String jsonString = serialiseObject(value);
            if (this.HasKey(key)) {
                this.DeleteKey(key);
            }
            jedis.set(key, jsonString, "NX", "EX", expire);
        } catch (Exception e) {
            //System.out.println(e);
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
//        Class<?> cls=value.getClass();
//        if(cls.equals(String.class)){
//            return value.toString();
//        }else if(cls.equals(ArrayList.class)) {
//            return JSONArray.toJSONString(value);
//        }else {
//            return JSON.toJSONString(value);
//        }
    }
    /**
     * redis设置一个值，
     *
     * @param key
     * @param value
     */
    public void SetValue(String key, Object value) throws Exception {
        Jedis jedis=jedisPool.getResource();
        Long expireTime=1800L;
        try {
            if(jedis.exists(key)){
                expireTime=jedis.ttl(key);
            }
            String jsonString = serialiseObject(value);
            jedis.set(key, jsonString);
            jedis.expire(key,expireTime.intValue());
        } catch (Exception e) {
            throw new Exception("SetValue--Exception", e);
        }finally {
            jedis.close();
        }
//        long expire = 2592000L;
//        this.SetValue(key, value, expire);
    }

    /**
     * 如果不存在则设置值，否则返回
     *
     * @param key
     * @param value
     */
    public long SetNX(String key, String value) throws Exception {
        Jedis jedis=jedisPool.getResource();
        try{
            long index=jedis.setnx(key,value);
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
        Jedis jedis=jedisPool.getResource();
        try {
            boolean flag = jedis.exists(key);
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
            long ret = jedis.exists(keys);
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
        Jedis jedis=jedisPool.getResource();
        try {
            return jedis.del(key);
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
            return jedis.del(keys);
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
        Jedis jedis=jedisPool.getResource();
        try {
            String val = jedis.get(key);
            Object obj = JSON.parse(val);
            return obj;
        } catch (Exception e) {
            return null;
        }finally {
            jedis.close();
        }
    }

    public JSONObject GetJSONObject(String key) {
        Jedis jedis=jedisPool.getResource();
        try {
            String val = jedis.get(key);
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
        try {
            String val = jedis.get(key);
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
            String val = jedis.get(key);
            T obj = JSON.parseObject(val,type);
            return obj;
        } catch (Exception e) {
            return null;
        }finally {
            jedis.close();
        }
    }

    /**
     * 查找redis中的符合条件的Keys，通过key直接查找
     */
    public Set<String> FindKeys(String pattern) {
        Jedis jedis=jedisPool.getResource();
        try{
            return jedis.keys(pattern);
        }catch (Exception ex){
            return null;
        }finally {
            jedis.close();
        }

    }
    /**正则表达式从RedisValues中查找
     * */
    public Set<String> FindKeysByStringContent(String pattern){
        Set<String> keys=new HashSet<String>();
        Set<String> tKeys=FindKeys("*");//得到所有的key值
        for (String key : tKeys){
            try{
                Object val=this.Get(key);
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
     * 设置key的过期时间
     *
     * @param key
     * @param expire (以秒为单位)
     */
    public void SetExpire(String key, int expire) {
        Jedis jedis=jedisPool.getResource();
        try{
            jedis.expire(key, expire);
        }catch (Exception ex){

        }finally {
            jedis.close();
        }

    }

    public void Close() {
        //jedis.close();
    }
}
