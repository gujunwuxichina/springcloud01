package com.gujun.eurekaclientconsumer02.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "ribbonclient")    //指定调用哪个服务
public interface SchedualStudent {

    @RequestMapping(value = "/student/getAll",method = RequestMethod.GET)    //指定调用服务中的getAll接口；
    JSONObject getAllStudents();

}
