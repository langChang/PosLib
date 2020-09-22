package com.nhsoft.poslib.entity;

import com.nhsoft.poslib.entity.order.Payment;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

@Entity
public class AmountPay {

    private Long id;
    private String name;
    private float amountMoney;
    private String shiftTableBizday;
    private int shiftTableNum;



    @Generated(hash = 1552358736)
    public AmountPay(Long id, String name, float amountMoney,
            String shiftTableBizday, int shiftTableNum) {
        this.id = id;
        this.name = name;
        this.amountMoney = amountMoney;
        this.shiftTableBizday = shiftTableBizday;
        this.shiftTableNum = shiftTableNum;
    }
    @Generated(hash = 1692659385)
    public AmountPay() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getAmountMoney() {
        return this.amountMoney;
    }
    public void setAmountMoney(float amountMoney) {
        this.amountMoney = amountMoney;
    }
    public String getShiftTableBizday() {
        return this.shiftTableBizday;
    }
    public void setShiftTableBizday(String shiftTableBizday) {
        this.shiftTableBizday = shiftTableBizday;
    }
    public void setShiftTableNum(int shiftTableNum) {
        this.shiftTableNum = shiftTableNum;
    }
    public int getShiftTableNum() {
        return this.shiftTableNum;
    }

}
