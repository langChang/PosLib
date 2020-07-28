package com.nhsoft.poslib.model;

/**
 * Created by Iverson on 2020/6/18 10:58 AM
 * 此类用于：
 */
public class KitGoodsAmount {

    private Long item_num;
    private float amount;
    private float host_amount;


    public float getHost_amount() {
        return host_amount;
    }

    public void setHost_amount(float host_amount) {
        this.host_amount = host_amount;
    }

    public Long getItem_num() {
        return item_num;
    }

    public void setItem_num(Long item_num) {
        this.item_num = item_num;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
