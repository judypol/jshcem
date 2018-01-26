package com.example.demo.controller;

import com.shcem.common.MidTierRequest;
import com.shcem.common.RequestData;
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
@RestController(value="home")
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
    @Permission(role="123")
    @Permission(role="456")
    //@LogHandler(level= LoggerLevel.Warn,loggerName = LoggerName.Service)
    public ModelAndView index(String name){
        ModelAndView modelAndView=new ModelAndView("/home/index");
        return modelAndView;
    }
    @RequestMapping("login")
    //@Anonymous
    public ModelAndView login() throws Exception{
        //SecurityUtils.buildSubject().login("lisi","ss");
//        RequestData requestData=new RequestData();
//        requestData.setMethodName("");
//        requestData.setParams("");
//        requestData.setServiceName("");
//        MidTierRequest.Post(requestData);
        ModelAndView view= new ModelAndView("/home/login");
        view.addObject("p","{dds}");
        return view;
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

//    public ModelAndView parialView(String p1){
//        ModelAndView view =new ModelAndView("/home/partialView");
//        view.addObject("s1","s1");
//        view.addObject("s2","s2");
//        view.addObject("p1",p1);
//        return view;
//    }
}
