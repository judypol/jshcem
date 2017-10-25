/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/10/19 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.webcore.permission.exceptions;

/**
 * 无权限异常
 * @author lizhihua
 * @version 1.0
 */
public class NoPermissionException extends Exception {
    String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public NoPermissionException(String message){
        super(message);
    }
    public NoPermissionException(String message,String code){
        super(message);
        this.code=code;
    }
}
