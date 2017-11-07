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
package com.example.demo.utils;

import com.shcem.webcore.permission.LoginInfo;
import com.shcem.webcore.permission.exceptions.AuthenticationException;
import com.shcem.webcore.permission.impl.RedisRealm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhihua
 * @version 1.0
 */
public class DatabaseRealm extends RedisRealm {
    /**
     * @param loginName
     * @param password
     * @return
     */
    @Override
    protected LoginInfo doSignIn(String loginName, String password) throws AuthenticationException {
        LoginInfo info=new LoginInfo();
        info.setLoginName(loginName);
        List<String> permissions=new ArrayList<>();
        permissions.add("com.example.demo.controller.HomeController.index");
        info.setPermissions(permissions);
        //info.setPermissions();
        return info;
    }
}
