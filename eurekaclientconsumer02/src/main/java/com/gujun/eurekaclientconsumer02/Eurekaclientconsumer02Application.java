package com.gujun.eurekaclientconsumer02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//开启Feign的功能(启动Feign客户端),并定义了扫描包；
@EnableFeignClients(basePackages = "com.gujun.eurekaclientconsumer02.service")
public class Eurekaclientconsumer02Application {

    public static void main(String[] args) {
        SpringApplication.run(Eurekaclientconsumer02Application.class, args);
    }

}
