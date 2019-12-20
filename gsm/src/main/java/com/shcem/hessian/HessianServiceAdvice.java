package com.shcem.hessian;


import com.alibaba.fastjson.JSON;
import com.shcem.Exception.FriendlyException;
import com.shcem.common.ResponseData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

@Aspect
public class HessianServiceAdvice {
    private Logger logger= LoggerFactory.getLogger("service");
    /**
     * 定义拦截规则：。
     */
    @Pointcut("@annotation(com.shcem.hessian.HessianService)")
    public void distributionLockPointcut(){

    }
    /**
     * 拦截器具体实现
     * @param pjp
     * @return
     */
    @Around("distributionLockPointcut()")                      //指定拦截器规则；
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable{
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();          //获取被拦截的方法
        String clsName=signature.getDeclaringTypeName();
//        Object[] pars=pjp.getArgs();
        logger.debug(clsName+"."+method+" Start");

        Object result;
        try{
            result=pjp.proceed();
        }catch (FriendlyException fe){
            ResponseData responseData=new ResponseData();
            responseData.setCODE(fe.ErrorCode);
            responseData.setINFO(fe.getMessage());
            responseData.setDATA(fe.getMessage());
            result= JSON.toJSONString(responseData);
        }catch (Exception ex){
            ResponseData responseData=new ResponseData();
            responseData.setCODE("44444");
            responseData.setINFO(ex.getMessage());
            responseData.setDATA(ex.getMessage());
            result= JSON.toJSONString(responseData);

            logger.error(ex.getMessage(),ex);
        }
        logger.debug(clsName+"."+method+" End");
        return result;
    }
}
