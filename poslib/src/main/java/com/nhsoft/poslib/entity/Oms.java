package com.nhsoft.poslib.entity;

import java.util.List;

public class Oms {

    /**
     *
     * 订单核销
     *
     * deliverable : true
     * id : 2626
     * title : 菠萝
     * display_order_id : E20180320161911005300012
     * platform : 1
     * shipping_type : 2
     * buyer_message :
     * status : 18
     * refund_status : 4
     * receiver_name : 寄
     * receiver_phone : 13736186256
     * post_fee : 0
     * payment : 0
     * total_fee : 0.01
     * package_fee : 0
     * created_at : 2018-03-20 16:19:11
     * ama_store_id : 1
     * receiver_address : 酒香韵道
     */

    private boolean deliverable;
    private int id;
    private String title;
    private String display_order_id;
    private int platform;
    private int shipping_type;//0 、"未知": UNKNOWN_SHIPPING_TYPE,   1、 "快递": EXPRESS, 2、 "到店自提": FETCH 3、"门店配送": LOCAL,
    private String buyer_message;
    private int status;
    private int refund_status;
    private String receiver_name;
    private String receiver_phone;
    private float post_fee;
    private float payment;
    private double total_fee;
    private float package_fee;
    private String created_at;
    private String ama_store_id;
    private String receiver_address;
    private boolean is_overtimed;
    private List<OmsDetail>order_items;
    private String allowed_branch_nums[];
    private String fetch_goods_time;
    private String seq;
    private float point_value;
    private String point_money;
    private boolean more_refund_less_pay;//是否需要多退少补
    private String more_refund_less_pay_state;//in_progress 已经在多退少补状态下
    private String platform_str;//商城名
    private String expected_delivery_time;//期望到达时间


    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getFetch_goods_time() {
        return fetch_goods_time;
    }

    public void setFetch_goods_time(String fetch_goods_time) {
        this.fetch_goods_time = fetch_goods_time;
    }

    public String[] getAllowed_branch_nums() {
        return allowed_branch_nums;
    }

    public void setAllowed_branch_nums(String[] allowed_branch_nums) {
        this.allowed_branch_nums = allowed_branch_nums;
    }

    public boolean isDeliverable() {
        return deliverable;
    }

    public void setDeliverable(boolean deliverable) {
        this.deliverable = deliverable;
    }

    public boolean isIs_overtimed() {
        return is_overtimed;
    }

    public void setIs_overtimed(boolean is_overtimed) {
        this.is_overtimed = is_overtimed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplay_order_id() {
        return display_order_id;
    }

    public void setDisplay_order_id(String display_order_id) {
        this.display_order_id = display_order_id;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public int getShipping_type() {
        return shipping_type;
    }

    public void setShipping_type(int shipping_type) {
        this.shipping_type = shipping_type;
    }

    public String getBuyer_message() {
        return buyer_message;
    }

    public void setBuyer_message(String buyer_message) {
        this.buyer_message = buyer_message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(int refund_status) {
        this.refund_status = refund_status;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public float getPost_fee() {
        return post_fee;
    }

    public void setPost_fee(float post_fee) {
        this.post_fee = post_fee;
    }

    public float getPayment() {
        return payment;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    public double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(double total_fee) {
        this.total_fee = total_fee;
    }

    public float getPackage_fee() {
        return package_fee;
    }

    public void setPackage_fee(float package_fee) {
        this.package_fee = package_fee;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getAma_store_id() {
        return ama_store_id;
    }

    public void setAma_store_id(String ama_store_id) {
        this.ama_store_id = ama_store_id;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    public List<OmsDetail> getOrder_items() {
        return order_items;
    }

    public void setOrder_items(List<OmsDetail> order_items) {
        this.order_items = order_items;
    }

    public float getPoint_value() {
        return point_value;
    }

    public void setPoint_value(float point_value) {
        this.point_value = point_value;
    }

    public String getPoint_money() {
        return point_money;
    }

    public void setPoint_money(String point_money) {
        this.point_money = point_money;
    }

    public boolean isMore_refund_less_pay() {
        return more_refund_less_pay;
    }

    public void setMore_refund_less_pay(boolean more_refund_less_pay) {
        this.more_refund_less_pay = more_refund_less_pay;
    }

    public String getMore_refund_less_pay_state() {
        return more_refund_less_pay_state==null?"":more_refund_less_pay_state;
    }

    public void setMore_refund_less_pay_state(String more_refund_less_pay_state) {
        this.more_refund_less_pay_state = more_refund_less_pay_state;
    }

    public String getExpected_delivery_time() {
        return expected_delivery_time;
    }

    public void setExpected_delivery_time(String expected_delivery_time) {
        this.expected_delivery_time = expected_delivery_time;
    }

    public String getPlatform_str() {
        return platform_str;
    }

    public void setPlatform_str(String platform_str) {
        this.platform_str = platform_str;
    }
}
