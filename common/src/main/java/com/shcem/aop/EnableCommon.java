package com.shcem.aop;

import com.shcem.async.AsyncConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * common组件中的通用注入
 * Created by judysen on 2017/9/10.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({AsyncConfiguration.class,AopDefaultConfiguration.class})
public @interface EnableCommon {
}
