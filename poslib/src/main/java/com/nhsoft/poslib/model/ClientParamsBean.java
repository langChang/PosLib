package com.nhsoft.poslib.model;

/**
 * Created by Iverson on 2020/5/21 2:00 PM
 * 此类用于：
 */
public class ClientParamsBean {

    private String creditControlType;
    private String creditControlPW;

    public String getCreditControlType() {
        return creditControlType;
    }

    public void setCreditControlType(String creditControlType) {
        this.creditControlType = creditControlType;
    }

    public String getCreditControlPW() {
        return creditControlPW;
    }

    public void setCreditControlPW(String creditControlPW) {
        this.creditControlPW = creditControlPW;
    }
}
