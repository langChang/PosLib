package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by Iverson on 2018/11/14 2:18 PM
 * 此类用于：
 */
@Entity
public class PosItemKit {
    /**
     * item_num : 444400786
     * kit_item_num : 444400584
     * pos_item_amount_un_fixed : false
     */
    @Id(autoincrement = true)
    private Long id;
    private Long item_num;
    private long kit_item_num;
    private boolean pos_item_amount_un_fixed;
    private float pos_item_kit_amount;
    @Transient
    private double input_kit_amount;
    @Transient
    private double input_host_amount;


    public double getInput_host_amount() {
        return input_host_amount;
    }

    public void setInput_host_amount(double input_host_amount) {
        this.input_host_amount = input_host_amount;
    }




    public double getInput_kit_amount() {
        return input_kit_amount;
    }

    public void setInput_kit_amount(double input_kit_amount) {
        this.input_kit_amount = input_kit_amount;
    }

    @Generated(hash = 505632094)
    public PosItemKit(Long id, Long item_num, long kit_item_num,
            boolean pos_item_amount_un_fixed, float pos_item_kit_amount) {
        this.id = id;
        this.item_num = item_num;
        this.kit_item_num = kit_item_num;
        this.pos_item_amount_un_fixed = pos_item_amount_un_fixed;
        this.pos_item_kit_amount = pos_item_kit_amount;
    }

    @Generated(hash = 978532718)
    public PosItemKit() {
    }


    public float getPos_item_kit_amount() {
        return pos_item_kit_amount;
    }

    public void setPos_item_kit_amount(float pos_item_kit_amount) {
        this.pos_item_kit_amount = pos_item_kit_amount;
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
    public long getKit_item_num() {
        return this.kit_item_num;
    }
    public void setKit_item_num(long kit_item_num) {
        this.kit_item_num = kit_item_num;
    }
    public boolean getPos_item_amount_un_fixed() {
        return this.pos_item_amount_un_fixed;
    }
    public void setPos_item_amount_un_fixed(boolean pos_item_amount_un_fixed) {
        this.pos_item_amount_un_fixed = pos_item_amount_un_fixed;
    }


}
