package com.nhsoft.poslib.model;

/**
 * Created by Iverson on 2018/12/3 4:17 PM
 * 此类用于：
 */
public class PayResultBean {

    /**
     * pay_id : 4466
     * app_id : 14e306cfa4c94950b4edc1d707848df2
     * sub_app_id : 125
     * pay_type : 微信条码支付
     * channel_id : 72
     * channel_name : 银联商务
     * order_no : CC444499183370010
     * order_type : null
     * pay_state : 0
     * reverse_state : -1
     * refund_state : -1
     * result_code : null
     * result : null
     * transaction_no : 20181203161700008043831576
     * buyer_user_id : null
     * operator : null
     * source : null
     * pay_start : 2018-12-03 16:16:40
     * pay_end : null
     * amount : 1
     * refund_amount : 0
     * extra : null
     * append_info : null
     * memo : null
     * notify_url : null
     * notify_attach : null
     * notify_time : null
     * refunded_state : null
     * refund : null
     * real_pay_id : null
     */

    private int    pay_id;
    private String app_id;
    private int    sub_app_id;
    private String pay_type;
    private int    channel_id;
    private String channel_name;
    private String order_no;
    private Object order_type;
    private int    pay_state;
    private int    reverse_state;
    private int    refund_state;
    private Object result_code;
    private Object result;
    private String transaction_no;
    private String buyer_user_id;
    private Object operator;
    private Object source;
    private String pay_start;
    private Object pay_end;
    private int    amount;
    private int    refund_amount;
    private Object extra;
    private Object append_info;
    private Object memo;
    private Object notify_url;
    private Object notify_attach;
    private Object notify_time;
    private Object refunded_state;
    private Object refund;
    private Object real_pay_id;
    private float  buyerPayAmount;
    private float  receiptAmount;
    private String zim_id;
    private String zim_init_client_data;

    public String getZim_id() {
        return zim_id;
    }

    public void setZim_id(String zim_id) {
        this.zim_id = zim_id;
    }

    public String getZim_init_client_data() {
        return zim_init_client_data;
    }

    public void setZim_init_client_data(String zim_init_client_data) {
        this.zim_init_client_data = zim_init_client_data;
    }

    public float getBuyerPayAmount() {
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(float buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }

    public float getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(float receiptAmount) {
        this.receiptAmount = receiptAmount;
    }



    public int getPay_id() {
        return pay_id;
    }

    public void setPay_id(int pay_id) {
        this.pay_id = pay_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public int getSub_app_id() {
        return sub_app_id;
    }

    public void setSub_app_id(int sub_app_id) {
        this.sub_app_id = sub_app_id;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public int getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(int channel_id) {
        this.channel_id = channel_id;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public Object getOrder_type() {
        return order_type;
    }

    public void setOrder_type(Object order_type) {
        this.order_type = order_type;
    }

    public int getPay_state() {
        return pay_state;
    }

    public void setPay_state(int pay_state) {
        this.pay_state = pay_state;
    }

    public int getReverse_state() {
        return reverse_state;
    }

    public void setReverse_state(int reverse_state) {
        this.reverse_state = reverse_state;
    }

    public int getRefund_state() {
        return refund_state;
    }

    public void setRefund_state(int refund_state) {
        this.refund_state = refund_state;
    }

    public Object getResult_code() {
        return result_code;
    }

    public void setResult_code(Object result_code) {
        this.result_code = result_code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getTransaction_no() {
        return transaction_no;
    }

    public void setTransaction_no(String transaction_no) {
        this.transaction_no = transaction_no;
    }

    public String getBuyer_user_id() {
        return buyer_user_id;
    }

    public void setBuyer_user_id(String buyer_user_id) {
        this.buyer_user_id = buyer_user_id;
    }

    public Object getOperator() {
        return operator;
    }

    public void setOperator(Object operator) {
        this.operator = operator;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public String getPay_start() {
        return pay_start;
    }

    public void setPay_start(String pay_start) {
        this.pay_start = pay_start;
    }

    public Object getPay_end() {
        return pay_end;
    }

    public void setPay_end(Object pay_end) {
        this.pay_end = pay_end;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRefund_amount() {
        return refund_amount;
    }

    public void setRefund_amount(int refund_amount) {
        this.refund_amount = refund_amount;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public Object getAppend_info() {
        return append_info;
    }

    public void setAppend_info(Object append_info) {
        this.append_info = append_info;
    }

    public Object getMemo() {
        return memo;
    }

    public void setMemo(Object memo) {
        this.memo = memo;
    }

    public Object getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(Object notify_url) {
        this.notify_url = notify_url;
    }

    public Object getNotify_attach() {
        return notify_attach;
    }

    public void setNotify_attach(Object notify_attach) {
        this.notify_attach = notify_attach;
    }

    public Object getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(Object notify_time) {
        this.notify_time = notify_time;
    }

    public Object getRefunded_state() {
        return refunded_state;
    }

    public void setRefunded_state(Object refunded_state) {
        this.refunded_state = refunded_state;
    }

    public Object getRefund() {
        return refund;
    }

    public void setRefund(Object refund) {
        this.refund = refund;
    }

    public Object getReal_pay_id() {
        return real_pay_id;
    }

    public void setReal_pay_id(Object real_pay_id) {
        this.real_pay_id = real_pay_id;
    }
}
