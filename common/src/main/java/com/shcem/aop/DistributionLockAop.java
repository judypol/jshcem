/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/6/27 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.aop;

import com.shcem.DistributionLock.CacheLockException;
import com.shcem.DistributionLock.DistributionRedisLock;
import com.shcem.annotation.CacheLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author lizhihua
 * @version 1.0
 */
@Aspect
public class DistributionLockAop {
    public static int ERROR_COUNT  = 0;
    /**
     * 定义拦截规则：CacheLock。
     */
    @Pointcut("@annotation(com.shcem.annotation.CacheLock)")
    public void distributionLockPointcut(){

    }
    /**
     * 拦截器具体实现
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("distributionLockPointcut()")                      //指定拦截器规则；
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable{
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();          //获取被拦截的方法
        String clsName=signature.getDeclaringTypeName();
        Object[] pars=pjp.getArgs();

        CacheLock cacheLock = method.getAnnotation(CacheLock.class);
        //RedisCacheKeyGenerator keyGenerator=new RedisCacheKeyGenerator();
        String key="";          //(String)keyGenerator.generate(signature.getDeclaringType(),method,pars);

        DistributionRedisLock lock=new DistributionRedisLock(cacheLock.lockedPrefix(),key);
        //logger.debug();

        boolean flag = lock.lock(cacheLock.timeout(), cacheLock.expireTime());
        if(!flag){//取锁失败
            ERROR_COUNT += 1;
            throw new CacheLockException("get lock fail");
        }
        try{
            //执行方法
            Object result=null;
            if(pars==null){
                result=pjp.proceed();
            }else {
                result=pjp.proceed(pars);
            }
            return result;
        }finally{
            System.out.println("intecepor 释放锁");
            lock.unlock();//释放锁
        }
    }
}
