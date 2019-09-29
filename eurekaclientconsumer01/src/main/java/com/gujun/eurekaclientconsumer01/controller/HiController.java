package com.gujun.eurekaclientconsumer01.controller;

import com.alibaba.fastjson.JSONObject;
import com.gujun.eurekaclientconsumer01.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hi")
    public String hi(){
        return helloService.hiService();
    }

    @GetMapping("/getAllStudents")
    public JSONObject getAllStudents(){
        return helloService.getAllStudents();
    }

}
