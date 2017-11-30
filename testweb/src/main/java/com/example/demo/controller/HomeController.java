package com.example.demo.controller;

import com.shcem.annotation.LogHandler;
import com.shcem.enums.LoggerLevel;
import com.shcem.enums.LoggerName;
import com.shcem.webcore.permission.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by judysen on 2017/9/9.
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    //@Autowired
    //testBean testBean;
    @Autowired
    PermissionCheck permissionCheck;
    @Autowired
    IRealm realm;
    @Autowired
    HttpServletRequest request;
    @RequestMapping("index")
    //@Permission
    //@LogHandler(level= LoggerLevel.Warn,loggerName = LoggerName.Service)
    public ModelAndView index(String name){
        ModelAndView modelAndView=new ModelAndView("/home/index");
        return modelAndView;
    }
    @RequestMapping("login")
    //@Anonymous
    public ModelAndView login() throws Exception{
        SecurityUtils.buildSubject().login("lisi","ss");
        return new ModelAndView("/home/login");
    }
    @RequestMapping("signIn")
    //@Anonymous
    public String restLogin() throws Exception{
        SecurityUtils.buildSubject().login("lisi","ss");
        String token=request.getAttribute(SecurityUtils.buildSubject().getTokenKey()).toString();
        System.out.println(token);
        return "ss";
    }
    @RequestMapping("info")
    //@Anonymous
    public @ResponseBody LoginInfo getRestLogin() throws Exception{
        LoginInfo info=SecurityUtils.buildSubject().getLoginInfo();
        return info;
    }
}
