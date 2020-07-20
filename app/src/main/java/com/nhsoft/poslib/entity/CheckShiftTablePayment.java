package com.nhsoft.poslib.entity;

public class CheckShiftTablePayment {
    /**
     * 此bean用于审核缴款单
     */
    private float jiaoban;//交班金额
    private float jiaokuan;//缴款金额
    private float jiaoSystem;//系统金额
    private float chayi;//差异
    private String name;//支付方式

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getJiaoban() {
        return jiaoban;
    }

    public void setJiaoban(float jiaoban) {
        this.jiaoban = jiaoban;
    }

    public float getJiaokuan() {
        return jiaokuan;
    }

    public void setJiaokuan(float jiaokuan) {
        this.jiaokuan = jiaokuan;
    }

    public float getJiaoSystem() {
        return jiaoSystem;
    }

    public void setJiaoSystem(float jiaoSystem) {
        this.jiaoSystem = jiaoSystem;
    }

    public float getChayi() {
        return chayi;
    }

    public void setChayi(float chayi) {
        this.chayi = chayi;
    }
}
