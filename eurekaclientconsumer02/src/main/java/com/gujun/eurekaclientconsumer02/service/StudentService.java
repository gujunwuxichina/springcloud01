package com.gujun.eurekaclientconsumer02.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/*
    @FeignClient代表这是一个Feign客户端，value值是一个微服务ID，这样Feign就会知道向哪个微服务发送请求，并会实现负载均衡；
    与Ribbon相比，Feign屏蔽了RestTemplate的使用，提供了接口声明式调用，可读性高；
 */
@FeignClient(qualifier = "studentService",value = "ribbonclient",fallback = StudentServiceHystrix.class)    //指定调用哪个服务
public interface StudentService {

    @GetMapping(value = "/student/getAll")    //指定调用服务中的/student/getAll接口；
    JSONObject getAllStudents();

    /*
        Feign中使用断路器：
        Feign是自带断路器的，没有默认打开，需要在配置文件中配置打开；
     */

}
