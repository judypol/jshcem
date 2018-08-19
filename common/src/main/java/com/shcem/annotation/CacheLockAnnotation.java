package com.shcem.annotation;

import java.lang.annotation.*;

/**
 * Created by judysen on 2017/4/4.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLockAnnotation {
    //String lockedPrefix() default "lock";   //redis 锁key的前缀
    String lockedKey() default "";      //锁key值
    long timeout() default 2000;        //锁时间
    int expireTime() default 10000;    //key在redis里存在的时间，10S
}
