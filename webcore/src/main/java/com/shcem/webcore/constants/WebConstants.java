/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/8/22 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.webcore.constants;

/**
 * @author lizhihua
 * @version 1.0
 */
public class WebConstants {
    public final static String SSOUrl="ssoUrl";                 //统一登录验证
    public final static String SSOExpire="ssoCookie.expire";    //sso cookie 过期时间
    public final static String SSOModel="ssoMode";              //验证模式      1，允许多点登录，0-单点登录
    public final static String ssoScope="ssoScope";             //统一验证范围
}
