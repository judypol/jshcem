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

import com.alibaba.fastjson.JSON;
import com.shcem.annotation.CacheClearHandler;
import com.shcem.annotation.CachedHandler;
import com.shcem.common.IRedisCache;
import com.shcem.common.RedisCacheManager;
import com.shcem.utils.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Redis缓存拦截器
 * @author lizhihua
 * @version 1.0
 */
@Aspect
public class RedisCacheAop {
    Logger logger= LoggerFactory.getLogger(RedisCacheAop.class);
    /**
     * 定义拦截规则：拦截CachedHandler注解的方法
     */
    @Pointcut("@annotation(com.shcem.annotation.CachedHandler)")
    public void cacheMethodPointcut(){}

    /**
     * 定义拦截规则：拦截CacheClearHandler注解的方法
     */
    @Pointcut("@annotation(com.shcem.annotation.CacheClearHandler)")
    public void cacheClearMethodPointcut(){}

    /**
     *设置Redis值
     * @param pjp
     * @return
     */
    @Around("cacheMethodPointcut()")
    public Object CachedRedis(ProceedingJoinPoint pjp) throws Throwable{
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Object[] pars=pjp.getArgs();
        Object result=null;
        CachedHandler cachedHandler =signature.getMethod().getAnnotation(CachedHandler.class);
        String cacheKey="";
        int cacheExpire=0;
        if(cachedHandler!=null){
            cacheKey=cachedHandler.key();
            cacheExpire=cachedHandler.expire();
        }
        logger.debug("cacheKey:{},expire:{}",cacheKey,cacheExpire);
        if(!StringUtils.isEmpty(cacheKey)){
            IRedisCache cache=RedisCacheManager.GetRedisCache();
            RedisCacheModel model= cache.Get(cacheKey,RedisCacheModel.class);
            if(model==null){
                if(pars==null||pars.length==0){
                    result=pjp.proceed();
                }else{
                    result=pjp.proceed(pars);
                }

                RedisCacheModel redisCacheModel=new RedisCacheModel();
                redisCacheModel.setCls(result.getClass().getName());
                redisCacheModel.setVal(JSON.toJSONString(result));
                try{
                    synchronized (this.getClass()){
                        cache.SetValue(cacheKey,redisCacheModel,cacheExpire);
                    }
                }catch (Exception ex){
                    logger.error("RedisCacheAop set Redis value error！",ex);
                }

            }else{
                Class<?> cls=Class.forName(model.getCls());
                result= JSON.parseObject(model.getVal(),cls);
                return result;
            }
        }else{
            if(pars==null||pars.length==0){
                result=pjp.proceed();
            }else{
                result=pjp.proceed(pars);
            }
        }

        return result;
    }

    /**
     *清空redis值
     * @param pjp
     */
    @Around("cacheClearMethodPointcut()")
    public Object CachedClear(ProceedingJoinPoint pjp) throws Throwable{
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        Object[] pars=pjp.getArgs();

        CacheClearHandler cacheClearHandler=signature.getMethod().getAnnotation(CacheClearHandler.class);
        Object result=null;
        //---先执行方法---先从redis中删除key，再执行方法
        try{
            if(cacheClearHandler!=null){
                String key=cacheClearHandler.key();
                RedisCacheManager.GetRedisCache().DeleteKey(key);
            }

            if(pars==null||pars.length==0){
                result=pjp.proceed();
            }else{
                result=pjp.proceed(pars);
            }
        }catch (Exception ex){
            logger.error("RedisCacheAop 删除redis--"+cacheClearHandler.key()+"失败！",ex);
        }

        return result;
    }
}
