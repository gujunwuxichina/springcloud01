package com.gujun.servicezuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Calendar;

@Component
public class MyFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";   //过滤类型为请求前
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;    //是否过滤，是
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx= RequestContext.getCurrentContext();
        if(Calendar.getInstance().get(Calendar.MINUTE)%2!=0){   //当前分钟不是偶数则不转发请求
            ctx.setSendZuulResponse(false); //不转发请求
            ctx.setResponseStatusCode(401); //设置HTTP响应码为401(未授权)
            ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8.getType());    //设置响应类型为JSON
            ctx.setResponseBody("{'result':false,'message':'not forward'}");
        }
        return null;    //一致放过
    }

}
