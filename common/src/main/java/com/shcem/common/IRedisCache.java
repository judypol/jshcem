package com.shcem.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.Set;

/**
 * Created by lizhihua on 2017/2/24.
 */
public interface IRedisCache {
    /**redis设置一个值，
     * @param key
     * @param value
     * @param expire 过期时间（秒为单位）
     * **/
    void SetValue(String key, Object value, long expire) throws Exception;
    /**redis设置一个值，
     * @param key
     * @param value
     * 默认为30天过期
     * */
    void SetValue(String key, Object value) throws Exception;
    /**如果不存在则设置值，否则返回
     * */
    long SetNX(String key, String value) throws Exception;
    /**判断redis中是否存在此key
     * @param key
     * */
    boolean HasKey(String key);
    /**判断redis中是否存在keys
     * @param keys
     * */
    long HasKeys(String... keys);
    /**删除一个key
     * @param key
     * */
    long DeleteKey(String key);
    /**同时删除多个keys
     * @param keys
     * */
    long DeleteKeys(String... keys);
    /**获取一个指定的值
     * @param key
     * */
    Object Get(String key);
    /**获取一个指定的值
     * @param key
     * */
    JSONObject GetJSONObject(String key);
    /**获取一个指定的值
     * @param key
     * */
    <T> T Get(String key, Class<T> cls);

    /**
     * 泛型转换
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    <T> T Get(String key,TypeReference<T> type);
    /**获取redis中的所有key
     * @param pattern 正则表达式
     * */
    Set<String> FindKeys(String pattern);
    /**设置key的过期时间
     * @param expire (以秒为单位)
     * @param key
     * */
    void SetExpire(String key, int expire);
    /**关闭redis连接
     * */
    void Close();
    /**正则表达式从RedisValues中查找
     * */
    Set<String> FindKeysByStringContent(String pattern);
}
