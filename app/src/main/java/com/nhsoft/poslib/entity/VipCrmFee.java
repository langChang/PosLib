package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * crm 产生费用
 *
 * @Author ADMIN
 * @time 2020-04-09 14:04
 */

@Entity
public class VipCrmFee {

    @Id(autoincrement = true)
    private long id;
    private String date;
    private String shiftTableNum;
    private String systemBookCode;

    private int branchNum;
    private String type;
    private String payTypeName;
    private float payMoney;

    private String memo;
    private String macAddress;
    private String vipPhone;
    private String operator;

    @Generated(hash = 965388437)
    public VipCrmFee(long id, String date, String shiftTableNum,
            String systemBookCode, int branchNum, String type, String payTypeName,
            float payMoney, String memo, String macAddress, String vipPhone,
            String operator) {
        this.id = id;
        this.date = date;
        this.shiftTableNum = shiftTableNum;
        this.systemBookCode = systemBookCode;
        this.branchNum = branchNum;
        this.type = type;
        this.payTypeName = payTypeName;
        this.payMoney = payMoney;
        this.memo = memo;
        this.macAddress = macAddress;
        this.vipPhone = vipPhone;
        this.operator = operator;
    }

    @Generated(hash = 2076407402)
    public VipCrmFee() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShiftTableNum() {
        return shiftTableNum;
    }

    public void setShiftTableNum(String shiftTableNum) {
        this.shiftTableNum = shiftTableNum;
    }

    public String getSystemBookCode() {
        return systemBookCode;
    }

    public void setSystemBookCode(String systemBookCode) {
        this.systemBookCode = systemBookCode;
    }

    public int getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(int branchNum) {
        this.branchNum = branchNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public float getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(float payMoney) {
        this.payMoney = payMoney;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getVipPhone() {
        return vipPhone;
    }

    public void setVipPhone(String vipPhone) {
        this.vipPhone = vipPhone;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
