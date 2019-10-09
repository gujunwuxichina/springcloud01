package com.gujun.ribbonclient01.controller;

import com.alibaba.fastjson.JSONObject;
import com.gujun.ribbonclient01.entity.Student;
import com.gujun.ribbonclient01.entity.User;
import com.gujun.ribbonclient01.service.StudentService;
import com.gujun.ribbonclient01.service.TokenService;
import com.gujun.ribbonclient01.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RequestMapping("student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

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

    @GetMapping("/login")
    public JSONObject login(@RequestParam(value = "username",required = true) String username,@RequestParam(value = "password",required = true) String password,HttpServletResponse response){
        JSONObject jsonObject=new JSONObject();
        User user=userService.login(username,password);
        if(user==null){
            jsonObject.put("result","login failure");
        }else{
            String token=tokenService.getToken(user);
            jsonObject.put("result","success");
            jsonObject.put("token",token);
            Cookie cookie=new Cookie("token",token);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return jsonObject;
    }


}
