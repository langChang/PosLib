package com.nhsoft.poslib.model;

/**
 * Created by Iverson on 2020/3/13 7:55 PM
 * 此类用于：
 */
public class PayQuitBean {

    private long refund_id;
    private long pay_id;
    private String app_id;
    private String refund_no;
    private int refund_state;
    private String transaction_no;
    private String operator;
    private String source;
    private String refund_start;
    private String refund_end;
    private int result_code;
    private String result;
    private int refund_amount;
    private String memo;
    private String extra;

    public long getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(long refund_id) {
        this.refund_id = refund_id;
    }

    public long getPay_id() {
        return pay_id;
    }

    public void setPay_id(long pay_id) {
        this.pay_id = pay_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getRefund_no() {
        return refund_no;
    }

    public void setRefund_no(String refund_no) {
        this.refund_no = refund_no;
    }

    public int getRefund_state() {
        return refund_state;
    }

    public void setRefund_state(int refund_state) {
        this.refund_state = refund_state;
    }

    public String getTransaction_no() {
        return transaction_no;
    }

    public void setTransaction_no(String transaction_no) {
        this.transaction_no = transaction_no;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRefund_start() {
        return refund_start;
    }

    public void setRefund_start(String refund_start) {
        this.refund_start = refund_start;
    }

    public String getRefund_end() {
        return refund_end;
    }

    public void setRefund_end(String refund_end) {
        this.refund_end = refund_end;
    }

    public int getResult_code() {
        return result_code;
    }

    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getRefund_amount() {
        return refund_amount;
    }

    public void setRefund_amount(int refund_amount) {
        this.refund_amount = refund_amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
