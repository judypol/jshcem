package com.example.demo.configuration;

import com.example.demo.constans.StaticResourceFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by judysen on 2017/9/9.
 */
@Configuration
public class Config {
    @Bean
    public StaticResourceFile staticResourceFile(){
        return new StaticResourceFile();
    }
}
