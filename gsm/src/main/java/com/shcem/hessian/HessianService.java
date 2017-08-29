package com.shcem.hessian;

import org.springframework.stereotype.Service;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by judysen on 2017/8/20.
 */
@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface HessianService {
    String value() default "";
}
