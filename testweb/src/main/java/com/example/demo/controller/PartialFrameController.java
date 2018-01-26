package com.example.demo.controller;

import com.shcem.webcore.controller.BaseController;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController(value = "partialFrame")
public class PartialFrameController extends BaseController {
    public ModelAndView parialView(String p1){
        ModelAndView view =new ModelAndView("/home/partialView");
        view.addObject("s1","s1");
        view.addObject("s2","s2");
        view.addObject("p1",p1);
        return view;
    }
}
