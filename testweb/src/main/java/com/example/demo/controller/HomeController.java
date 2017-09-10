package com.example.demo.controller;

import com.shcem.annotation.LogHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by judysen on 2017/9/9.
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    //@Autowired
    //testBean testBean;
    @RequestMapping("index")
    @LogHandler
    public ModelAndView index(String name){
        ModelAndView modelAndView=new ModelAndView("/home/index");
        return modelAndView;
    }
}
