package com.shcem.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.shcem.common.IRedisCache;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 集群模式下调用
 */
public class ClusterRedisCache implements IRedisCache {
    private static LinkedBlockingQueue<JedisCluster> queue=new LinkedBlockingQueue<JedisCluster>();
    private JedisCluster jedisCluster;
    /**初始化一个集群RedisCache
     * */
    public ClusterRedisCache(Set<HostAndPort> hostAndPortSet){
        if(queue.isEmpty())
        {
            jedisCluster=new JedisCluster(hostAndPortSet);
        }else{
            jedisCluster=queue.poll();
        }
    }
    /**设置一个值
     * @param key
     * @param value
     * @param expire
     * */
    public void SetValue(String key, Object value, long expire) throws Exception {
        try{
            String val= JSON.toJSONString(value);
            jedisCluster.setex(key,(int)expire,val);
        }catch (Exception e){
            throw e;
        }finally {
            OfferJedisPool();
        }

    }
    /**设置一个值
     * @param key
     * @param value
     * 默认时间为一个月
     * */
    public void SetValue(String key, Object value) throws Exception {
        this.SetValue(key,value,2592000L);
    }

    /**
     * 如果不存在则设置值，否则返回
     *
     * @param key
     * @param value
     */
    public long SetNX(String key, String value) {
        return 0;
    }

    /**查找一个key
     * @param key
     * */
    public boolean HasKey(String key) {
        try {
            return jedisCluster.exists(key);
        }finally {
            OfferJedisPool();
        }

    }
    /**查找keys
     * @param keys
     * */
    public long HasKeys(String... keys) {
        try {
            return jedisCluster.exists(keys);
        }finally {
            OfferJedisPool();
        }

    }
    /**删除一个key
     * @param key
     * */
    public long DeleteKey(String key) {
        try {
            return jedisCluster.del(key);
        }finally {
            OfferJedisPool();
        }

    }
    /**删除多个key
     * */
    public long DeleteKeys(String... keys) {
        try{
            return jedisCluster.del(keys);
        }finally {
            OfferJedisPool();
        }

    }
    /**获取值
     * */
    public Object Get(String key) {
        try{
            String val=jedisCluster.get(key);
            return JSON.parse(val);
        }finally {
            this.OfferJedisPool();
        }

    }

    public JSONObject GetJSONObject(String key) {
        try{
            String val=jedisCluster.get(key);
            return JSON.parseObject(val);
        }finally {
            OfferJedisPool();
        }
    }

    public <T> T Get(String key, Class<T> cls) {
        try{
            String val=jedisCluster.get(key);
            return JSON.parseObject(val,cls);
        }finally {
            OfferJedisPool();
        }
    }

    /**
     * 泛型转换
     *
     * @param key
     * @param type
     * @return
     */
    @Override
    public <T> T Get(String key, TypeReference<T> type) {
        try{
            String val=jedisCluster.get(key);
            return JSON.parseObject(val,type);
        }finally {
            OfferJedisPool();
        }
    }

    /**查找key
     * */
    public Set<String> FindKeys(String pattern) {
        try{
            Set<String> keys=new HashSet<String>();
            Map<String,JedisPool> jedisPoolMap=jedisCluster.getClusterNodes();
            for(String k :jedisPoolMap.keySet()){
                JedisPool pool=jedisPoolMap.get(k);
                Jedis connection=pool.getResource();
                try{
                    keys.addAll(connection.keys(pattern));
                }finally {
                    connection.close();
                }
            }
            return keys;
        }finally {
            OfferJedisPool();
        }
    }
    /**设置key的过期时间
     * */
    public void SetExpire(String key, int expire) {
        try {
            jedisCluster.expire(key,expire);
        }finally {
            OfferJedisPool();
        }
    }

    /**
     * 关闭redis连接
     */
    public void Close() {
        try{
            jedisCluster.close();
        }catch (Exception e){

        }
    }

    /**
     * 正则表达式从RedisValues中查找
     *
     * @param pattern
     */
    public Set<String> FindKeysByStringContent(String pattern) {
        Set<String> tkeys=this.FindKeys("*");
        Set<String> keys=new HashSet<String>();
        for (String k :tkeys){
            try{
                Object obj=this.Get(k);
                if(obj==null){
                    continue;
                }
                String val=JSON.toJSONString(obj);

                Pattern p=Pattern.compile(pattern);
                Matcher matcher=p.matcher(val);
                if(matcher.find()){
                    keys.add(k);
                }
            }catch (Exception e){

            }
        }
        return keys;
    }


    private void OfferJedisPool(){
        if(jedisCluster!=null){
            if(queue.size()>100)
            {
                try{
                    jedisCluster.close();
                }catch (Exception e){
                    System.out.println(e);
                }
            }else {
                queue.offer(jedisCluster);
            }
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
        return null;
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
        return null;
    }
}
