package com.gujun.eurekaclient.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/myController01")
@RestController
public class MyController01 {

    @RequestMapping("/test01")
    public Map<String,String> test01(){
        Map<String,String> map=new HashMap<>();
        map.put("name","gujun");
        return map;
    }

}
