/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/10/24 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.webcore.permission;

import com.shcem.common.YamlConfiguration;

/**
 * @author lizhihua
 * @version 1.0
 */
public class WebCoreConstant {
    public static String SplitCode="-";
    public static String PrefixCookieKey= YamlConfiguration.instance().getString("prefixCookie");
}
