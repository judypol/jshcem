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
 * @author lizhihua
 * @version 1.0
 */
@Aspect
@Component
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
        String clsName=signature.getDeclaringTypeName();
        Object[] pars=pjp.getArgs();
        Logger logger= LoggerFactory.getLogger(signature.getDeclaringType());

        //logger.debug();
        Object result=null;
        try{
            logger.debug("{}-{} Start",clsName,method);
            if(pars==null){
                result=pjp.proceed();
            }else {
                result=pjp.proceed(pars);
            }
            logger.debug("{}-{} End",clsName,method);
        }catch (Exception ex){
            logger.error("调用"+clsName+"-"+method+"方法出现了异常.",ex);
        }

        return result;
    }


}
