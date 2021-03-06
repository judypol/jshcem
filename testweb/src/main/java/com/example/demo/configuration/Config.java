package com.example.demo.configuration;

import com.example.demo.constans.StaticResourceFile;
import com.example.demo.constans.TestResourceFile;
import com.shcem.configCenter.spring.AutoConfigCenter;
import com.shcem.utils.SpringContextHolder;
import com.shcem.webcore.interceptor.AuthorizationInterceptor;
import com.shcem.webcore.permission.RestExceptionHandler;
import com.shcem.webcore.permission.EnableAuthorization;
import com.shcem.webcore.permission.IRealm;
import com.shcem.webcore.permission.PermissionCheck;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.utils.*;

/**
 * Created by judysen on 2017/9/9.
 */
//@EnableAuthorization
@Configuration
//@AutoConfigCenter
public class Config {
    @Value("${user.name:4512}")
    String userName;
    @Bean
    public StaticResourceFile staticResourceFile(){
        return new TestResourceFile();
    }
    @Bean
    public IRealm getDatabaseRealm(){
        DatabaseRealm realm=new DatabaseRealm();
        realm.setStyle("rest");
        return realm;
    }
    @Bean
    public PermissionCheck permissionCheck(){
        return new MyPermissionCheck();
    }
    @Bean
    public RestExceptionHandler defaultExceptionHandler(){
        return  new RestExceptionHandler();
    }
}
