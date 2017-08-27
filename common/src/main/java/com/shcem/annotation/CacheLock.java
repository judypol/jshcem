package com.shcem.annotation;

import java.lang.annotation.*;

/**
 * Created by judysen on 2017/4/4.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {
    String lockedPrefix() default "lock";   //redis 锁key的前缀
    long timeout() default 2000;        //锁时间
    int expireTime() default 100000;    //key在redis里存在的时间，10S
}
