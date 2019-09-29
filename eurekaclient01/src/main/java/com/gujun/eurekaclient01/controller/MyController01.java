package com.gujun.eurekaclient01.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/myController01")
@RestController
public class MyController01 {

    @Value("${server.port}")
    private String port;

    @GetMapping("/hi")
    public String hi(){
        return "port:"+port;
    }

    @GetMapping("/test01")
    public Map<String,String> test01(){
        Map<String,String> map=new HashMap<>();
        map.put("name","gujun");
        map.put("port",port);
        return map;
    }



}
