package com.gujun.eurekaclientconsumer01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix  //开启Hystrix
//@EnableCircuitBreaker
public class Eurekaclientconsumer01Application {

    public static void main(String[] args) {
        SpringApplication.run(Eurekaclientconsumer01Application.class, args);
    }

}
