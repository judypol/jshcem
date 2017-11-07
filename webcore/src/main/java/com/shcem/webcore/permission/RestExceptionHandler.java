/* ========================================
 * System Name　　：化交线上平台
 * SubSystem Name ：化交站点核心工具集
 * File Name: Constants
 * ----------------------------------------
 * Create Date/Change History
 * ----------------------------------------
 * 2017/10/30 　lizhihua   Create
 *
 *
 * ----------------------------------------
 * Copyright (c) SCEM . All rights reserved.
 */
package com.shcem.webcore.permission;

import com.alibaba.fastjson.JSON;
import com.shcem.common.ResponseData;
import com.shcem.webcore.permission.exceptions.NoPermissionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author lizhihua
 * @version 1.0
 */
public class RestExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {
        httpServletResponse.setCharacterEncoding("UTF-8");
        if(e instanceof NoPermissionException){
            httpServletResponse.setStatus(HttpStatus.OK.value()); //设置状态码
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
            ResponseData responseData=new ResponseData();
            responseData.setDATA(e.getMessage());
            responseData.setCODE("11000");
            responseData.setINFO(e.getMessage());
            try{
                PrintWriter writer =httpServletResponse.getWriter();
                writer.write(JSON.toJSONString(responseData));
            }catch (Exception ex) {

            }finally {
                return  new ModelAndView();
            }
        }else{
            return new ModelAndView("/error");
        }
    }
}
