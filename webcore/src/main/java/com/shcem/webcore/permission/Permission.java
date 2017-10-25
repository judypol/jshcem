/* ========================================				
 * System Name　　：化交线上平台				
 * SubSystem Name ：化交站点核心工具集
 * File Name: Permission
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
 * Permission
 * 
 * @author lizhihua
 * @version 1.0
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
//    /**权限角色
//     * */
//    String[] roles() default {""};
//    /**权限之间的关系 And/Or
//     * */
//    RelationEnum relation() default RelationEnum.Or;
}
