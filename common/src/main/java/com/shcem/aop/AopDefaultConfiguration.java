package com.shcem.aop;

import org.springframework.context.annotation.Bean;

/**
 * 系统默认Aop配置
 * DistributionLockAop,LoggerAop,RedisCacheAop
 *
 * Created by judysen on 2017/9/10.
 */
public class AopDefaultConfiguration {
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
}
