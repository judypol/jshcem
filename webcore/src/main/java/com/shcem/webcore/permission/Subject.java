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

import com.shcem.utils.CookieUtils;
import com.shcem.utils.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lizhihua
 * @version 1.0
 */
public class Subject {
    protected Subject(){
//        realm= SpringContextHolder.GetBean(IRealm.class);
//        if(SpringContextHolder.getApplicationContext() instanceof WebApplicationContext){
//            ((WebApplicationContext)SpringContextHolder.getApplicationContext()).getServletContext()
//        }
//        request=(HttpServletRequest) SpringContextHolder.GetBean(ServletRequest.class);
    }
    @Autowired
    IRealm realm;
    @Autowired
    HttpServletRequest request;

    String tokenKey= WebCoreConstant.PrefixCookieKey+WebCoreConstant.SplitCode+"token";

    public void login(String loginName,String password) throws Exception{
        LoginInfo info=realm.signIn(loginName,password);
    }

    /**
     * 获取权限
     * @return
     */
    public List<String> getPermissions() throws Exception{
        try{
            LoginInfo info=this.getLoginInfo();
            return info.getPermissions();
        }catch (Exception ex){
            throw new Exception("获取用户权限出错！",ex);
        }
    }

    /**
     * 获取角色
     * @return
     */
    public List<String> getRoles() throws Exception{
        try{
            LoginInfo info=this.getLoginInfo();
            return info.getRoles();
        }catch (Exception ex){
            throw new Exception("获取用户角色出错！",ex);
        }
    }

    /**
     *判断是否有权限
     * @param permission
     * @return
     */
    public boolean checkPermission(String permission) throws Exception{
        List<String> permissions=getPermissions();

        return permissions.contains(permission);
    }

    /**
     *
     * @param permission
     * @return
     */
    public boolean checkPermissions(List<String> permission)throws Exception{
        List<String> permissions=getPermissions();

        return permissions.containsAll(permission);
    }

    /**
     *判断是否拥有角色
     * @param role
     * @return
     */
    public boolean checkRole(String role) throws Exception{
        List<String> roles=getRoles();

        return roles.contains(role);
    }

    /**
     * 判断是否拥有角色
     * @param roles
     * @return
     */
    public boolean checkRoles(List<String> roles) throws Exception{
        List<String> allRoles=getRoles();

        return allRoles.containsAll(roles);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public LoginInfo getLoginInfo() throws Exception{
        String token= CookieUtils.getCookie(this.request,tokenKey);
        LoginInfo info=realm.getLoginInfo(token);
        return info;
    }
}