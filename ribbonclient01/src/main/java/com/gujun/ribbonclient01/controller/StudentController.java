package com.gujun.ribbonclient01.controller;

import com.alibaba.fastjson.JSONObject;
import com.gujun.ribbonclient01.entity.Student;
import com.gujun.ribbonclient01.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    //DiscoveryClient对象是由SpringBoot自动创建的，可以获取该服务的id,服务主机，服务端口；H
    private DiscoveryClient discoveryClient;

    @GetMapping("/getAll")
    public JSONObject getAll(){
        ServiceInstance serviceInstance=discoveryClient.getInstances("RIBBONCLIENT").get(0);
        System.out.println("服务id:"+serviceInstance.getServiceId());
        System.out.println("服务所在主机:"+serviceInstance.getHost());
        System.out.println("服务端口:"+serviceInstance.getPort());
        JSONObject jsonObject=new JSONObject();
        List<Student> students=studentService.getAll();
        jsonObject.put("students",students);
        jsonObject.put("result","success");
        return jsonObject;
    }


}
