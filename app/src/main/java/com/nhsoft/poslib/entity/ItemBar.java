package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by Iverson on 2018/11/14 2:14 PM
 * 此类用于：
 */
@Entity
public  class ItemBar {

    /**
     * item_num : 444400587
     * item_bar_num : 1
     * item_bar_code : 22000004  商品条码
     * item_bar_rate : 1.0
     */

    @Id(autoincrement = true)
    private Long id;
    private Long item_num;
    private int    item_bar_num;
    private String item_bar_code;
    private Float item_bar_rate;
    @Transient int item_valid_period;


    public int getItem_valid_period() {
        return item_valid_period;
    }

    public void setItem_valid_period(int item_valid_period) {
        this.item_valid_period = item_valid_period;
    }



    public Float getItem_bar_rate() {
        return item_bar_rate;
    }

    public void setItem_bar_rate(Float item_bar_rate) {
        this.item_bar_rate = item_bar_rate;
    }


    @Transient
    private PosItem posItem;


    public PosItem getPosItem() {
        return posItem;
    }

    public void setPosItem(PosItem posItem) {
        this.posItem = posItem;
    }

    @Generated(hash = 1611157336)
    public ItemBar(Long id, Long item_num, int item_bar_num, String item_bar_code,
            Float item_bar_rate) {
        this.id = id;
        this.item_num = item_num;
        this.item_bar_num = item_bar_num;
        this.item_bar_code = item_bar_code;
        this.item_bar_rate = item_bar_rate;
    }

    @Generated(hash = 1246670899)
    public ItemBar() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getItem_num() {
        return this.item_num;
    }
    public void setItem_num(Long item_num) {
        this.item_num = item_num;
    }
    public int getItem_bar_num() {
        return this.item_bar_num;
    }
    public void setItem_bar_num(int item_bar_num) {
        this.item_bar_num = item_bar_num;
    }
    public String getItem_bar_code() {
        return this.item_bar_code;
    }
    public void setItem_bar_code(String item_bar_code) {
        this.item_bar_code = item_bar_code;
    }

}