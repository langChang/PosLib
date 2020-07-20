package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Iverson on 2019-05-13 15:14
 * 此类用于：
 */
@Entity
public class PolicyQuantityDetail {


    /**
     * promotion_quantity_no : 4344990000014
     * promotion_quantity_detail_num : 1
     * item_num : 434403033
     * promotion_quantity_detail_std_price : 40
     * promotion_quantity_detail_special_price : 40
     * promotion_quantity_detail_min_amount : 0
     * promotion_quantity_detail_cost : 10
     */

    @Id
    private Long id;
    private String promotion_quantity_no;
    private int promotion_quantity_detail_num;
    private long item_num;
    private float promotion_quantity_detail_std_price;
    private float promotion_quantity_detail_special_price;
    private float promotion_quantity_detail_min_amount;
    private float promotion_quantity_detail_cost;
    @Generated(hash = 1795055190)
    public PolicyQuantityDetail(Long id, String promotion_quantity_no,
            int promotion_quantity_detail_num, long item_num,
            float promotion_quantity_detail_std_price,
            float promotion_quantity_detail_special_price,
            float promotion_quantity_detail_min_amount,
            float promotion_quantity_detail_cost) {
        this.id = id;
        this.promotion_quantity_no = promotion_quantity_no;
        this.promotion_quantity_detail_num = promotion_quantity_detail_num;
        this.item_num = item_num;
        this.promotion_quantity_detail_std_price = promotion_quantity_detail_std_price;
        this.promotion_quantity_detail_special_price = promotion_quantity_detail_special_price;
        this.promotion_quantity_detail_min_amount = promotion_quantity_detail_min_amount;
        this.promotion_quantity_detail_cost = promotion_quantity_detail_cost;
    }
    @Generated(hash = 891500694)
    public PolicyQuantityDetail() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPromotion_quantity_no() {
        return this.promotion_quantity_no;
    }
    public void setPromotion_quantity_no(String promotion_quantity_no) {
        this.promotion_quantity_no = promotion_quantity_no;
    }
    public int getPromotion_quantity_detail_num() {
        return this.promotion_quantity_detail_num;
    }
    public void setPromotion_quantity_detail_num(
            int promotion_quantity_detail_num) {
        this.promotion_quantity_detail_num = promotion_quantity_detail_num;
    }
    public long getItem_num() {
        return this.item_num;
    }
    public void setItem_num(long item_num) {
        this.item_num = item_num;
    }
    public float getPromotion_quantity_detail_std_price() {
        return this.promotion_quantity_detail_std_price;
    }
    public void setPromotion_quantity_detail_std_price(
            float promotion_quantity_detail_std_price) {
        this.promotion_quantity_detail_std_price = promotion_quantity_detail_std_price;
    }
    public float getPromotion_quantity_detail_special_price() {
        return this.promotion_quantity_detail_special_price;
    }
    public void setPromotion_quantity_detail_special_price(
            float promotion_quantity_detail_special_price) {
        this.promotion_quantity_detail_special_price = promotion_quantity_detail_special_price;
    }
    public float getPromotion_quantity_detail_min_amount() {
        return this.promotion_quantity_detail_min_amount;
    }
    public void setPromotion_quantity_detail_min_amount(
            float promotion_quantity_detail_min_amount) {
        this.promotion_quantity_detail_min_amount = promotion_quantity_detail_min_amount;
    }
    public float getPromotion_quantity_detail_cost() {
        return this.promotion_quantity_detail_cost;
    }
    public void setPromotion_quantity_detail_cost(
            float promotion_quantity_detail_cost) {
        this.promotion_quantity_detail_cost = promotion_quantity_detail_cost;
    }
    
}
