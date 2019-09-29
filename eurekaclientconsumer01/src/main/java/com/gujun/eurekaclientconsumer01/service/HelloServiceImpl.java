package com.gujun.eurekaclientconsumer01.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    private RestTemplate restTemplate;

    /*
        服务与服务之间的通讯是基于http restful的；
        SpringCloud有两种服务调用方式：
        1.ribbon+restTemplate；
        2.feign;
        ribbon是一个负载均衡客户端，可以很好控制http和tcp的一些行为；feign默认集成了ribbon;

        此处是ribbon+restTemplate；
        当通过restTemplate来访问指定服务的接口时，会访问具体的实例，多个实例会负责均衡分配，此时会替换成具体的url地址；

     */
    @Override
    public String hiService() {
        return restTemplate.getForObject("http://SERVICE-CLIENT01/myController01/hi",String.class);
    }

    @Override
    public JSONObject getAllStudents() {
        return restTemplate.getForObject("http://RIBBONCLIENT/student/getAll",JSONObject.class);
    }
}
