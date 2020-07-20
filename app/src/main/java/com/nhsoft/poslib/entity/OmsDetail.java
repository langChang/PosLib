package com.nhsoft.poslib.entity;

public class OmsDetail {


    /**
     * 订单核销 详细
     *
     * id : 783
     * total_ama_num : 1
     * num : 1
     * image_url : https://img.yzcdn.cn/upload_files/2018/03/19/FjFPcg68T_GjHrdhEBhbP3kfz0Xn.jpg
     * refunded_num : 0
     * spec_name : 蓝色
     * title : 菠萝
     * price : 0.01
     * payment : 0.01
     * total_fee : 0.01
     * ama_item_id : 444401157
     * refund_status : 4
     */

    private int id;
    private float total_ama_num;
    private float num;
    private String image_url;
    private float refunded_num;
    private String spec_name;
    private String title;
    private double price;
    private double payment;
    private double total_fee;
    private String ama_item_id;
    private int refund_status;
    private boolean more_refund_less_pay;
    private double real_payment;
    private float print_total_num;


    public float getPrint_total_num() {
        return print_total_num;
    }

    public void setPrint_total_num(float print_total_num) {
        this.print_total_num = print_total_num;
    }



    public boolean isMore_refund_less_pay() {
        return more_refund_less_pay;
    }

    public void setMore_refund_less_pay(boolean more_refund_less_pay) {
        this.more_refund_less_pay = more_refund_less_pay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotal_ama_num() {
        return total_ama_num;
    }

    public void setTotal_ama_num(float total_ama_num) {
        this.total_ama_num = total_ama_num;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public float getRefunded_num() {
        return refunded_num;
    }

    public void setRefunded_num(float refunded_num) {
        this.refunded_num = refunded_num;
    }

    public String getSpec_name() {
        return spec_name;
    }

    public void setSpec_name(String spec_name) {
        this.spec_name = spec_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(double total_fee) {
        this.total_fee = total_fee;
    }

    public String getAma_item_id() {
        return ama_item_id;
    }

    public void setAma_item_id(String ama_item_id) {
        this.ama_item_id = ama_item_id;
    }

    public int getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(int refund_status) {
        this.refund_status = refund_status;
    }

    public double getReal_payment() {
        return real_payment;
    }

    public void setReal_payment(double real_payment) {
        this.real_payment = real_payment;
    }
}
