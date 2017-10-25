package com.shcem.webcore.permission;

import com.shcem.webcore.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by judysen on 2017/9/9.
 */
public class AuthorizationConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    AuthorizationInterceptor authorizationInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(authorizationInterceptor);
    }
    @Bean
    public Subject getSubject(){
        return new Subject();
    }
}
