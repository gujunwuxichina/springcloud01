package com.gujun.ribbonclient01.service;

import com.gujun.ribbonclient01.entity.User;

public interface TokenService {

    String getToken(User user);

}
