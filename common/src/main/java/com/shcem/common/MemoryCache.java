package com.shcem.common;

import java.util.concurrent.ConcurrentHashMap;

/**提供内存缓存
 *
 */
public class MemoryCache {
    private static MemoryCache instance=new MemoryCache();
    public static MemoryCache Instance()
    {
        return instance;
    }
    private static ConcurrentHashMap<String,Object> map=new ConcurrentHashMap<String, Object>();
    private MemoryCache(){

    }
    /**设置一个key-value，如果此key已经存在则更新值
     * @param key
     * @param obj
     * */
    public void Set(String key,Object obj)
    {
        //String val= JSON.toJSONString(obj);
        if(map.containsKey(key)){
            map.replace(key,obj);
        }else {
            map.put(key,obj);
        }
    }
    /**获取一个值
     * @param key
     * */
    public Object Get(String key)
    {
        return map.get(key);
    }
    /**移除一个key
     * @param key
     * */
    public void Remove(String key)
    {
        map.remove(key);
    }
    /**清空缓存
     * */
    public void Clear()
    {
        map.clear();
    }
}
