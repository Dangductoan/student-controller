package com.cybersoft.demoSpring.controller;

import com.cybersoft.demoSpring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller -> dùng để viết web đươi dạng FE,BE cùng một project
//@RestController => dung de viet duoi dang BE
//@ResponseBody => chi tra chuoi khong tra ra giao dien
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    User user;
    @GetMapping("/hello")
    public String hello() {
        return "hello spring demo" + user.getFullname();
    }
    @GetMapping("/hello1")
    public String hello1() {
        return "hello spring demo1";
    }

}
