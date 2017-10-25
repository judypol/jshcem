package com.shcem.webcore.permission;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by judysen on 2017/9/9.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(AuthorizationConfiguration.class)
@ComponentScan("com.shcem.webcore")
public @interface EnableAuthorization  {

}
