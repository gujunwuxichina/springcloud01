package com.gujun.ribbonclient01.advice;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

//该注解也标注了@Controller，会被SpringIoC扫描和装配；
@ControllerAdvice(basePackages = {"com.gujun.ribbonclient01.controller.*"},annotations = RestController.class)
public class MyControllerAdvice {

    //异常处理
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONObject exceptionHandler1(Exception ex){
        ex.printStackTrace();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("result","error");
        return jsonObject;
    }


}
