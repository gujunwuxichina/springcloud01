package com.gujun.eurekaclientconsumer02.controller;

import com.alibaba.fastjson.JSONObject;
import com.gujun.eurekaclientconsumer02.service.SchedualStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("student")
@RestController
public class StudentController {

    @Autowired
    private SchedualStudent schedualStudent;

    @RequestMapping("/getAllStudents")
    public JSONObject getAllStudents(){
        return schedualStudent.getAllStudents();
    }

}
