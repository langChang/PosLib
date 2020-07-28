package com.nhsoft.poslib.model;

/**
 * Created by Iverson on 2020-01-11 15:57
 * 此类用于：
 */ 

public class LoginRequest{
    private String code;
    private String user_psw;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUser_psw() {
        return user_psw;
    }

    public void setUser_psw(String user_psw) {
        this.user_psw = user_psw;
    }
}

