/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/9/11 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.hessian;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author lizhihua
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({HessianServiceScannerRegister.class,HessianServiceAdvice.class})
public @interface EnableHessianServiceScanner {
}
