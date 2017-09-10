/* ========================================
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
package com.shcem.annotation;

import com.shcem.enums.LoggerLevel;
import com.shcem.enums.LoggerName;

import java.lang.annotation.*;

/**
 * 日志处理
 * @author lizhihua
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface LogHandler {
    /**
     * 日志级别,默认Debug级别
     * @return
     */
    LoggerLevel level() default LoggerLevel.Debug;

    /**
     * LoggerName，默认是Controller
     * @return
     */
    LoggerName loggerName() default LoggerName.Controller;
}
