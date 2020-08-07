package com.nhsoft.poslib.model;

/**
 * Created by Iverson on 2020-01-11 15:57
 * 此类用于：
 */ 

public class LoginRequest{
    private String user_code;
    private String user_psw;

    public LoginRequest(String user_code,String userPsw){
        this.user_code = user_code;
        this.user_psw = userPsw;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String code) {
        this.user_code = code;
    }

    public String getUser_psw() {
        return user_psw;
    }

    public void setUser_psw(String user_psw) {
        this.user_psw = user_psw;
    }
}

