package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Iverson on 2020/7/8 10:40 AM
 * 此类用于：
 */

@Entity
public class Inventory {

    /**
     * storehouse_num : 402000002
     * item_num : 402000010
     * inventory_amount : 172
     * inventory_money : 1032
     * inventory_assist_amount : 0
     */

    @Id
    private Long id;
    private Long storehouse_num;
    private Long item_num;
    private float inventory_amount;
    private float inventory_money;
    private float inventory_assist_amount;
    @Generated(hash = 1578474364)
    public Inventory(Long id, Long storehouse_num, Long item_num,
            float inventory_amount, float inventory_money,
            float inventory_assist_amount) {
        this.id = id;
        this.storehouse_num = storehouse_num;
        this.item_num = item_num;
        this.inventory_amount = inventory_amount;
        this.inventory_money = inventory_money;
        this.inventory_assist_amount = inventory_assist_amount;
    }
    @Generated(hash = 375708430)
    public Inventory() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getStorehouse_num() {
        return this.storehouse_num;
    }
    public void setStorehouse_num(Long storehouse_num) {
        this.storehouse_num = storehouse_num;
    }
    public Long getItem_num() {
        return this.item_num;
    }
    public void setItem_num(Long item_num) {
        this.item_num = item_num;
    }
    public float getInventory_amount() {
        return this.inventory_amount;
    }
    public void setInventory_amount(float inventory_amount) {
        this.inventory_amount = inventory_amount;
    }
    public float getInventory_money() {
        return this.inventory_money;
    }
    public void setInventory_money(float inventory_money) {
        this.inventory_money = inventory_money;
    }
    public float getInventory_assist_amount() {
        return this.inventory_assist_amount;
    }
    public void setInventory_assist_amount(float inventory_assist_amount) {
        this.inventory_assist_amount = inventory_assist_amount;
    }
    
    
}
