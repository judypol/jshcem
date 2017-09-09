package com.shcem.webcore.permission;

import com.shcem.webcore.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.lang.annotation.*;

/**
 * Created by judysen on 2017/9/9.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(AuthorizationConfiguration.class)
public @interface EnableAuthorization  {

}
