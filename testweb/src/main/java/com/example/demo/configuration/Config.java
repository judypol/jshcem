package com.example.demo.configuration;

import com.example.demo.constans.StaticResourceFile;
import com.shcem.utils.SpringContextHolder;
import com.shcem.webcore.permission.RestExceptionHandler;
import com.shcem.webcore.permission.EnableAuthorization;
import com.shcem.webcore.permission.IRealm;
import com.shcem.webcore.permission.PermissionCheck;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.utils.*;

/**
 * Created by judysen on 2017/9/9.
 */
//@EnableAuthorization
@Configuration
public class Config {
    @Bean
    public StaticResourceFile staticResourceFile(){
        return new StaticResourceFile();
    }
    @Bean
    public SpringContextHolder springContextHolder(){
        return new SpringContextHolder();
    }
    @Bean
    public IRealm getDatabaseRealm(){
        DatabaseRealm realm=new DatabaseRealm();
        realm.setStyle("rest");
        return realm;
    }
    @Bean
    public PermissionCheck permissionCheck(){
        return new PermissionCheck();
    }
    @Bean
    public RestExceptionHandler defaultExceptionHandler(){
        return  new RestExceptionHandler();
    }
}
