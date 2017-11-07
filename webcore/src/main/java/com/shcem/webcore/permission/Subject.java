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
import com.shcem.utils.StringUtils;
import com.shcem.webcore.permission.exceptions.NoPermissionException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String getTokenKey() {
        return tokenKey;
    }

    public void login(String loginName, String password) throws Exception{
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
     *判断是否有权限,没有权限会抛出异常
     * @param permission
     * @return
     */
    public boolean checkPermission(String permission) throws Exception{
        List<String> permissions=getPermissions();

        boolean flag=permissions.contains(permission);
        if(flag){
            return flag;
        }else{
            throw new NoPermissionException("您没有访问权限");
        }
    }

    /**
     *
     * @param permission
     * @return
     */
    public boolean checkPermissions(List<String> permission)throws Exception{
        List<String> permissions=getPermissions();

        boolean flag= permissions.containsAll(permission);
        if(flag){
            return flag;
        }else{
            throw new NoPermissionException("您没有访问权限");
        }
    }

    /**
     *判断是否拥有角色
     * @param role
     * @return
     */
    public boolean checkRole(String role) throws Exception{
        List<String> roles=getRoles();

        boolean flag= roles.contains(role);
        if(flag){
            return flag;
        }else{
            throw new NoPermissionException("您没有访问权限");
        }
    }

    /**
     * 判断是否拥有角色
     * @param roles
     * @return
     */
    public boolean checkRoles(List<String> roles) throws Exception{
        List<String> allRoles=getRoles();

        boolean flag= allRoles.containsAll(roles);
        if(flag){
            return flag;
        }else{
            throw new NoPermissionException("您没有访问权限");
        }
    }

    /**
     *获取用户信息
     * @return
     * @throws Exception
     */
    public LoginInfo getLoginInfo() throws Exception{
        String token="";
        token=request.getHeader(tokenKey);          //---从请求头中获取token值，如果没有则从cookie中获取
        if(StringUtils.isEmpty(token)){
            token= CookieUtils.getCookie(this.request,tokenKey);
        }

        if(StringUtils.isEmpty(token)){
            return null;
        }

        LoginInfo info=realm.getLoginInfo(token);
        return info;
    }
}