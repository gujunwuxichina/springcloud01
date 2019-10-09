package com.gujun.ribbonclient01.service;

import com.gujun.ribbonclient01.entity.User;

public interface UserService {

    User findByUsername(String username);

    User findById(Integer uId);

    User login(String username,String password);

}
