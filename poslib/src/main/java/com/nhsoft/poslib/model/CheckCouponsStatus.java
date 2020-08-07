package com.nhsoft.poslib.model;

/**
 * Created by Iverson on 2020/8/6 8:44 PM
 * 此类用于：
 */
public class CheckCouponsStatus {

    private boolean isUse;//是否可用
    private String msg;

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
