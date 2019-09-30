package com.gujun.eurekaclientconsumer02.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class StudentServiceHystrix implements StudentService {

    @Override
    public JSONObject getAllStudents() {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("result","error");
        return jsonObject;
    }

}
