package com.shcem.DistributionLock;

import com.shcem.common.IRedisCache;
import com.shcem.common.RedisCacheManager;

import java.util.Random;

/**
 * Created by judysen on 2017/4/4.
 */
public class DistributionRedisLock {
    //纳秒和毫秒之间的转换率
    public static final long MILLI_NANO_TIME = 1000 * 1000L;

    public static final String LOCKED = "TRUE";

    public static final Random RANDOM = new Random();
    private String key;
    //封装的操作redis的工具
    //private IRedisCache redisClient;

    private boolean lock = true;

    /**
    *
    * @param purpose 锁前缀
     * @param key 锁定的ID等东西
     */
    public DistributionRedisLock(String purpose, String key){
        this.key = purpose + "_" + key + "_lock";
        //this.redisClient = RedisCacheManager.GetRedisCache();
    }

    /**
     * 加锁
     * 使用方式为：
     * lock();
     * try{
     * 	  executeMethod();
     * }finally{
     * 	 unlock();
     * }
     * @param timeout timeout的时间范围内轮询锁
     * @param expire 设置锁超时时间
     * @return 成功 or 失败
     */
    public boolean lock(long timeout, int expire) throws Exception{
        long nanoTime = System.nanoTime();
        timeout *= MILLI_NANO_TIME;
        IRedisCache cache=RedisCacheManager.GetRedisCache();
        try {
            //在timeout的时间范围内不断轮询锁

            while (System.nanoTime() - nanoTime < timeout) {
                //锁不存在的话，设置锁并设置锁过期时间，即加锁
                if (cache.SetNX(this.key, LOCKED) == 1) {
                    cache.SetExpire(key, expire);//设置锁过期时间是为了在没有释放
                    //锁的情况下锁过期后消失，不会造成永久阻塞
                    this.lock = true;
                    return this.lock;
                }
                System.out.println("出现锁等待");
                //短暂休眠，避免可能的活锁
                Thread.sleep(30, RANDOM.nextInt(30));
            }
        } catch (Exception e) {
            throw new RuntimeException("locking error",e);
        }finally {
            cache.Close();
        }
        return false;
    }

    /**
     * 释放锁
     * @throws Exception
     */
    public  void unlock() throws Exception{
        IRedisCache cache=RedisCacheManager.GetRedisCache();
        try {
            if(this.lock){
                cache.DeleteKey(key);//直接删除
            }
        } catch (Throwable e) {

        }finally {
            cache.Close();
        }
    }
}
