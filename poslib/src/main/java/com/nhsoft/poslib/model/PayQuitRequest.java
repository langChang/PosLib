package com.nhsoft.poslib.model;

/**
 * Created by Iverson on 2020/3/13 8:03 PM
 * 此类用于：
 */
public class PayQuitRequest {


    private String app_id;
    private String out_sub_id;
    private String order_no;
    private String refund_no;
    private int refund_money;
    private String source;
    private String operator;
    private String memo;
    private String random;
    private String date;
    private String sign;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getOutsubid() {
        return out_sub_id;
    }

    public void setOutsubid(String outsubid) {
        this.out_sub_id = outsubid;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getRefund_no() {
        return refund_no;
    }

    public void setRefund_no(String refund_no) {
        this.refund_no = refund_no;
    }

    public int getRefund_money() {
        return refund_money;
    }

    public void setRefund_money(int refund_money) {
        this.refund_money = refund_money;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
