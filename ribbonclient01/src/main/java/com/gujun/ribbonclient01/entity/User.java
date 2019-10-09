package com.gujun.ribbonclient01.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias(value = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -5283005898981336466L;

    private Integer uId;

    private String username;

    private String password;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
