package com.gujun.ribbonclient01.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gujun.ribbonclient01.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String getToken(User user) {
        return JWT.create().withAudience(user.getuId().toString()).
                withIssuedAt(new Date()).withExpiresAt(new Date(System.currentTimeMillis()+60*60*1000)).    //有效时间1小时
                sign(Algorithm.HMAC256(user.getPassword()));
    }

}
