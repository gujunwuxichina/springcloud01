package com.gujun.ribbonclient01.service;

import com.gujun.ribbonclient01.dao.UserMapper;
import com.gujun.ribbonclient01.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User findById(Integer uId) {
        return userMapper.findById(uId);
    }

    @Override
    public User login(String username, String password) {
        return userMapper.login(username,password);
    }
}
