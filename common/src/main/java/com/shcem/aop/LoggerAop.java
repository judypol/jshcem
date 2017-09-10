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

import com.shcem.annotation.LogHandler;
import com.shcem.enums.LoggerLevel;
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
 * 日志拦截器
 * @author lizhihua
 * @version 1.0
 */
@Aspect
public class LoggerAop {
    /**
     * 定义拦截规则：拦截LogHandler注解的方法。
     */
    @Pointcut("@annotation(com.shcem.annotation.LogHandler)")
    public void logMethodPointcut(){

    }
    /**
     * 拦截器具体实现
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("logMethodPointcut()")                      //指定拦截器规则；
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable{
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();

        Method method = signature.getMethod();          //获取被拦截的方法
        LogHandler logHandler=method.getAnnotation(LogHandler.class);
        if(logHandler==null){
            return null;
        }

        String clsName=signature.getDeclaringTypeName();
        Object[] pars=pjp.getArgs();
        Logger logger= LoggerFactory.getLogger(logHandler.loggerName().getStringValue());

        //logger.debug();
        Object result=null;
        try{
            setLog(logHandler.level(),logger,"{}-{} Start",clsName,method);
            if(pars==null){
                result=pjp.proceed();
            }else {
                result=pjp.proceed(pars);
            }
            setLog(logHandler.level(),logger,"{}-{} End",clsName,method);
        }catch (Exception ex){
            logger.error("调用"+clsName+"-"+method+"方法出现了异常.",ex);
        }

        return result;
    }
    private void debug(Logger logger,String format,Object args1,Object args2){
        logger.debug(format,args1,args2);
    }
    private void info(Logger logger,String format,Object args1,Object args2){
        logger.info(format,args1,args2);
    }
    private void warn(Logger logger,String format,Object args1,Object args2){
        logger.warn(format,args1,args2);
    }
    private void setLog(LoggerLevel level,Logger logger,String format,Object args1,Object args2){
        if(level.equals(LoggerLevel.Debug)){
            debug(logger,format,args1,args2);
        }else if(level.equals(LoggerLevel.Info)){
            info(logger,format,args1,args2);
        }else if(level.equals(LoggerLevel.Warn)){
            warn(logger,format,args1,args2);
        }else{
            debug(logger,format,args1,args2);
        }
    }
}
