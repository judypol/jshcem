package com.shcem.annotation;/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/6/6 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */

import java.lang.annotation.*;

/**
 * 设置缓存
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface CacheAnnotation {
    /**
     * key值
     * @return
     */
    String key() default "";

    /**
     * 过期时间
     * @return
     */
    int expire() default 31536000;
}
