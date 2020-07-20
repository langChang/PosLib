package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class VipStrangeSuccessSendMoney {

    @Property(nameInDb = "ID")
    @Id(autoincrement = true)
    private Long id;
    private String systemBookCode;
    private int branchNum;
    private String shiftTableNum;
    private String bizday;
    private float money;

    @Generated(hash = 402657542)
    public VipStrangeSuccessSendMoney(Long id, String systemBookCode, int branchNum,
            String shiftTableNum, String bizday, float money) {
        this.id = id;
        this.systemBookCode = systemBookCode;
        this.branchNum = branchNum;
        this.shiftTableNum = shiftTableNum;
        this.bizday = bizday;
        this.money = money;
    }
    @Generated(hash = 2068355437)
    public VipStrangeSuccessSendMoney() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSystemBookCode() {
        return this.systemBookCode;
    }
    public void setSystemBookCode(String systemBookCode) {
        this.systemBookCode = systemBookCode;
    }
    public int getBranchNum() {
        return this.branchNum;
    }
    public void setBranchNum(int branchNum) {
        this.branchNum = branchNum;
    }
    public String getShiftTableNum() {
        return this.shiftTableNum;
    }
    public void setShiftTableNum(String shiftTableNum) {
        this.shiftTableNum = shiftTableNum;
    }
    public float getMoney() {
        return this.money;
    }
    public void setMoney(float money) {
        this.money = money;
    }
    public String getBizday() {
        return this.bizday;
    }
    public void setBizday(String bizday) {
        this.bizday = bizday;
    }
    
}
