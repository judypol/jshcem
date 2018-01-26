package com.shcem.aop;

import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * 系统默认Aop配置
 * DistributionLockAop,LoggerAop,RedisCacheAop
 *
 * Created by judysen on 2017/9/10.
 */
public class AopDefaultConfiguration {
    @Value("${loggerAop.package:execution(* *.controllers.*.*(..))}")
    private String pointcut;
    @Autowired
    private LoggerAdvice loggerAdvice;
    @Bean
    public AspectJExpressionPointcutAdvisor loggerAopPointcutAdvisor() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(pointcut);
        advisor.setAdvice(loggerAdvice);
        return advisor;
    }

    @Bean
    public DistributionLockAop distributionLockAop(){
        return new DistributionLockAop();
    }
    @Bean
    public LoggerAop loggerAop(){
        return new LoggerAop();
    }
    @Bean
    public RedisCacheAop redisCacheAop(){
        return new RedisCacheAop();
    }
    @Bean
    public LoggerAdvice getLoggerAdvice(){
        return new LoggerAdvice();
    }
}
