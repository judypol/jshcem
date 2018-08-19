package com.shcem.async;

import com.shcem.common.MidTierRequest;
import com.shcem.utils.SpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by judysen on 2017/9/10.
 */
@EnableAsync
public class AsyncConfiguration {
    /** Set the ThreadPoolExecutor's maximum pool size. */
    private int maxPoolSize = 10;
    /** Set the capacity for the ThreadPoolExecutor's BlockingQueue. */
    private int queueCapacity = 10;

    @Bean
    public Executor myAsync() {
        ThreadPoolExecutor cachedExcutor= (ThreadPoolExecutor) Executors.newCachedThreadPool();
        cachedExcutor.setMaximumPoolSize(10);

        return cachedExcutor;
    }
    @Bean
    public SpringContextHolder springContextHolder(){
        return  new SpringContextHolder();
    }
    @Bean
    public MidTierRequest midTierRequest(){
        return new MidTierRequest();
    }
}
