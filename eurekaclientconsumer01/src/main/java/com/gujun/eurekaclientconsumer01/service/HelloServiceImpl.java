package com.gujun.eurekaclientconsumer01.service;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    /*
        断路器Hystrix：
        在互联网中，可能存在某一个微服务在某个时刻压力变大导致服务缓慢，甚至出现故障，导致服务不能响应；
        如：两个服务，一个用户服务，一个产品服务，产品服务请求用户服务，此时用户服务进入瘫痪状态，
        此时产品服务还是能正常响应的，如果出现产品服务大量调用用户服务，就会出现大量等待，如果仍旧出现大量产品服务调用用户服务，
        就会造成大量请求的积压，导致产品服务最终也不可用；
        即在微服务中，如果一个服务不可用，其它服务还大量调用该不可用的服务，会导致其自己也不可用，其自身不可用后还会蔓延到其它服务
        不可用，最终导致分布式瘫痪；
        对此微服务提出了断路器的概念；在微服务系统之间大量调用可能导致服务消费者自身出现瘫痪的情况下，断路器就会将这些积压的大量请求熔断，
        来保证自身服务可用；✳

        处理限制请求的方式：限流、缓存等；
        此处是降级服务，即当请求其它微服务出现超时或发送故障时，就会使用自身服务的其它方法响应；
        在Spring Cloud中断路器是由NetFlix的Hystrix实现的，它默认监控微服务之间的调用超时时间为2000ms,若超时就会根据你配置的方法响应；

        1.ribbon中使用断路器：
    */

    @Override
    @HystrixCommand(fallbackMethod = "fallback")    //该注解对该方法创建了断路器功能，并指定了fallbackMethod熔断方法；
    public JSONObject getAllStudents() {
        return restTemplate.getForObject("http://RIBBONCLIENT/student/getAll",JSONObject.class);
    }

    public JSONObject fallback(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("result","error");
        return jsonObject;
    }

    /*
        Hystrix仪表盘：
        Spring Cloud还提供了一个仪表盘进行监控短路的情况，从而让开发者监控可能出现的问题；
        HHH
     */

}
