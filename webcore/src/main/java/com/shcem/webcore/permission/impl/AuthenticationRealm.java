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
package com.shcem.webcore.permission.impl;

import com.shcem.utils.Encodes;
import com.shcem.webcore.permission.IRealm;
import com.shcem.webcore.permission.LoginInfo;
import com.shcem.webcore.permission.exceptions.AuthenticationException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * 需要自己实现的
 * @author lizhihua
 * @version 1.0
 */
public abstract class AuthenticationRealm implements IRealm {

    /**
     *
     * @param loginName
     * @param password
     * @return
     */
    protected abstract LoginInfo doSignIn(String loginName,String password) throws AuthenticationException;

    /**
     * 将用户信息写入缓存
     * @param info
     */
    protected abstract void setCache(LoginInfo info);

    /***
     * 从缓存中获取用户信息
     * @return
     */
    protected abstract LoginInfo getCache(String key);
    /**
     * 登录接口
     *
     * @param loginName
     * @param password
     * @return
     */
    @Override
    public LoginInfo signIn(String loginName, String password) throws AuthenticationException{
        try{
            LoginInfo loginInfo=doSignIn(loginName,password);
            //--将用户信息保存到缓存中
            setCache(loginInfo);
            return loginInfo;
        }catch (AuthenticationException ex){
            throw ex;
        }
    }

    /**
     * 获取用户信息（用户名，权限，角色等）
     *
     * @param token
     * @return
     */
    @Override
    public LoginInfo getLoginInfo(String token) {
        return getCache(token);
    }
}
