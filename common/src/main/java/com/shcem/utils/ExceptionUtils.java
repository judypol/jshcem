/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/5/9 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author lizhihua
 * @version 1.0
 */
public class ExceptionUtils {
    public static String toExceptionString(Exception ex){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString(); // stack trace as a string
    }
}
