package com.gujun.eurekaclientconsumer01.controller;

import com.alibaba.fastjson.JSONObject;
import com.gujun.eurekaclientconsumer01.service.HelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    @GetMapping("/timeoutTest")
    @HystrixCommand(fallbackMethod = "timeout",  //也可以修饰控制器方法
        commandProperties = {
            @HystrixProperty(
                    name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "10000"     //超时限制设为10秒
            )
        }
    )
    public String timeoutTest(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "熔断测试";
    }

    public String timeout(){
        return "超时";
    }

}
