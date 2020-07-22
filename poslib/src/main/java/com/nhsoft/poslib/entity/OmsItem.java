package com.nhsoft.poslib.entity;

public class OmsItem {
    private int item_id;
    private double payment;
    private boolean more_refund_less_pay;
    private float remain_weight;
    private float total_ama_num;

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public boolean isMore_refund_less_pay() {
        return more_refund_less_pay;
    }

    public void setMore_refund_less_pay(boolean more_refund_less_pay) {
        this.more_refund_less_pay = more_refund_less_pay;
    }

    public float getRemain_weight() {
        return remain_weight;
    }

    public void setRemain_weight(float remain_weight) {
        this.remain_weight = remain_weight;
    }

    public float getTotal_ama_num() {
        return total_ama_num;
    }

    public void setTotal_ama_num(float total_ama_num) {
        this.total_ama_num = total_ama_num;
    }
}
