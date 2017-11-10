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
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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
    @Pointcut("execution(* *(..)) && @annotation(com.shcem.annotation.LogHandler)")
    public void logMethodPointcut(){

    }
    /**
     * 拦截器具体实现
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("logMethodPointcut()")                      //指定拦截器规则；
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable{

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
            setLog(logHandler.level(),logger,clsName+"-"+method.getName()+" Start",clsName,method);
            long beginTime = System.currentTimeMillis();
            if(pars==null){
                result=pjp.proceed();
            }else {
                result=pjp.proceed(pars);
            }
            long endTime=System.currentTimeMillis();
            setLog(logHandler.level(),logger,clsName+"-"+method.getName()+" End and cost "+(endTime-beginTime)+"ms",clsName,method.getName());
        }catch (Exception ex){
            logger.error("调用"+clsName+"-"+method+"方法出现了异常.",ex);
        }

        return result;
    }
    private void debug(Logger logger,String format){
        logger.debug(format);
    }
    private void info(Logger logger,String format){
        logger.info(format);
    }
    private void warn(Logger logger,String format){
        logger.warn(format);
    }
    private void setLog(LoggerLevel level,Logger logger,String format,Object args1,Object args2){
        if(level.equals(LoggerLevel.Debug)){
            debug(logger,format);
        }else if(level.equals(LoggerLevel.Info)){
            info(logger,format);
        }else if(level.equals(LoggerLevel.Warn)){
            warn(logger,format);
        }else{
            debug(logger,format);
        }
    }
}
