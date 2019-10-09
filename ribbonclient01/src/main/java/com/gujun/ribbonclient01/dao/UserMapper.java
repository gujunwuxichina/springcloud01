package com.gujun.ribbonclient01.dao;

import com.gujun.ribbonclient01.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User findByUsername(String username);

    User findById(Integer uId);

    User login(@Param("username") String username,@Param("password") String password);

}
