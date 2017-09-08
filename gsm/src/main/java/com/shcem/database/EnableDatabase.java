/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/9/8 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.database;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author lizhihua
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({DatabaseRegistrar.class})
public @interface EnableDatabase {
    /**
     *
     * @return
     */
    String url() default "";

    /**
     *
     * @return
     */
    String user() default "";
    String password() default "";

    /**
     * 数据源注册类
     * @return
     */
    Class<?>[] clazzes() default {};
}
