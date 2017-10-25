/* ========================================				
 * System Name　　：化交线上平台				
 * SubSystem Name ：化交站点核心工具集
 * File Name: PermissionCheck
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

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * PermissionCheck
 * 
 * @author lizhihua
 * @version 1.0
 */
public class PermissionCheck {

    public boolean process(Permission permission, HttpServletRequest request, HttpServletResponse response) {
        // TODO: 2017/2/20 权限控制--用户记录--redis记录 
//    	LoginUser user= WebContext.GetCurrentContext().getCurrentUser();    //如果获取不到用户信息
//    	if(user==null||user.getLoginName()==null){
//            sendRedirect(request,response, request.getContextPath()+"/member/login");
//        }
        throw new NotImplementedException();
    }

    /**
     *对权限进行check，权限名为（class.method）
     * @param className
     * @param methodName
     * @return
     */
    public boolean process(String className,String methodName) throws Exception{
        Subject subject=SecurityUtils.buildSubject();
        try{
            return subject.checkPermission(className+"."+methodName);
        }catch (Exception ex){
            throw new Exception("认证权限时出错了。",ex);
        }
    }

    protected void sendRedirect(HttpServletRequest request,HttpServletResponse response, String redirectURI) {
        // TODO: 2017/2/20 授权失败跳转的页面--
        if(isAjaxRequest(request)){
            try{
                ServletOutputStream outputStream= response.getOutputStream();
                OutputStreamWriter ow = new OutputStreamWriter(outputStream,"UTF-8");
                ow.write(redirectURI);
                ow.flush();
                ow.close();
            }catch (Exception ex){

            }
        }else {
            String url=redirectURI;
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                //logger.error("转向页面:" + url + "跳转出错:", e);
            }
        }
    }
    protected boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestType)) {
            return true;
        }else {
            return false;
        }
    }
}
 
