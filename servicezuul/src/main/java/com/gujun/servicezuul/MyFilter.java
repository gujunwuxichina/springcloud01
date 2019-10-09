package com.gujun.servicezuul;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

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
        boolean forward=true;
        String token="";
        Cookie[] cookies=ctx.getRequest().getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if("token".equals(cookie.getName())){
                    token=cookie.getValue();
                    break;
                }
            }
        }
        if("".equals(token)){
            forward=false;
        }else{
            String uId= JWT.decode(token).getAudience().get(0);
            System.out.println("uId:"+uId);
            String password="123";  //此处写死，不去数据库取了
            JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(password)).build();   //验证token
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                e.printStackTrace();
                forward=false;
            }
        }
        if(!forward){
            ctx.setSendZuulResponse(false); //不转发请求
            ctx.setResponseStatusCode(401); //设置HTTP响应码为401(未授权)
            ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8.getType());    //设置响应类型为JSON
            ctx.setResponseBody("{'result':false,'message':'no token'}");
        }
        return null;    //一致放过
    }

}
