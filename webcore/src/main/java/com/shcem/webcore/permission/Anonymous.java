/* ========================================				
 * System Name　　：化交线上平台				
 * SubSystem Name ：化交站点核心工具集
 * File Name: Anonymous
 * ----------------------------------------
 * Create Date/Change History 
 * ----------------------------------------
 * 04/29/16 　池 永   Create
 * 
 * 
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.webcore.permission;

import java.lang.annotation.*;

/**
 * Anonymous
 *
 * @author lizhihua
 * @version 1.0
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Anonymous {
    boolean validate() default true;
}