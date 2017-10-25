package com.shcem.webcore.permission;/* ========================================
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

import com.shcem.webcore.permission.exceptions.AuthenticationException;

import java.util.List;

/**
 *认证，授权的基本控制类
 */
public interface IRealm {

    /**
     * 登录接口,登录获取用户对于的权限，角色等
     * @return
     */
    LoginInfo signIn(String loginName,String password) throws AuthenticationException;

    /**
     * 获取用户信息（用户名，权限，角色等）
     * @param token
     * @return
     */
    LoginInfo getLoginInfo(String token);
}
