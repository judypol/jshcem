package com.shcem.aop;


import com.shcem.annotation.LogHandler;
import com.shcem.utils.StringUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/11/26 0026.
 */
public class LoggerAdvice implements MethodInterceptor {
    @Value("${logger.loggerName:kafka}")
    private String loggerName;
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method =methodInvocation.getMethod();          //获取被拦截的方法

        String clsName=method.getDeclaringClass().getName();
        Logger logger= LoggerFactory.getLogger(loggerName);

        //logger.debug();
        Object result=null;
        try{
            logger.info(clsName+"-"+method.getName()+" Start",clsName,method);

            long beginTime = System.currentTimeMillis();
            result=methodInvocation.proceed();
            long endTime=System.currentTimeMillis();
            logger.info(clsName+"-"+method.getName()+" End and cost "+(endTime-beginTime)+"ms");
        }catch (Exception ex){
            logger.error("调用"+clsName+"-"+method+"方法出现了异常.",ex);
        }

        return result;
    }
}
