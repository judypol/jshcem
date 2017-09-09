package com.shcem.webcore.freemarker;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by judysen on 2017/9/9.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(FreemarkerConfiguration.class)
public @interface EnableFreemarker {
}
