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

import com.shcem.webcore.permission.authentication.AuthenticationToken;
import com.shcem.webcore.permission.exceptions.AuthenticationException;
import com.shcem.webcore.permission.exceptions.NoPermissionException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * PermissionCheck
 * 
 * @author lizhihua
 * @version 1.0
 */
public interface PermissionCheck {

    /**
     * check是否有权限，必须在子类中实现
     * @param authenticationToken
     * @exception AuthenticationException 没有认证（在系统中没有注册过），
     * @exception NoPermissionException 没有权限
     * @return
     */
    boolean process(AuthenticationToken authenticationToken) throws AuthenticationException,NoPermissionException;

//    protected void sendRedirect(HttpServletRequest request,HttpServletResponse response, String redirectURI) {
//        // TODO: 2017/2/20 授权失败跳转的页面--
//        if(isAjaxRequest(request)){
//            try{
//                ServletOutputStream outputStream= response.getOutputStream();
//                OutputStreamWriter ow = new OutputStreamWriter(outputStream,"UTF-8");
//                ow.write(redirectURI);
//                ow.flush();
//                ow.close();
//            }catch (Exception ex){
//
//            }
//        }else {
//            String url=redirectURI;
//            try {
//                response.sendRedirect(url);
//            } catch (IOException e) {
//                //logger.error("转向页面:" + url + "跳转出错:", e);
//            }
//        }
//    }
//    protected boolean isAjaxRequest(HttpServletRequest request) {
//        String requestType = request.getHeader("X-Requested-With");
//        if ("XMLHttpRequest".equals(requestType)) {
//            return true;
//        }else {
//            return false;
//        }
//    }
}
 
