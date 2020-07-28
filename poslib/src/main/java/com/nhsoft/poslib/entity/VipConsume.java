package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class VipConsume {

    /**
     * VIP 卡存款消费、换卡消费
     */
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;
    private String type;//存款  或者 换卡
    private String consumeMoney;//消费金额
    private String payType;//支付类型（现金、支付宝。。。）
    private String payTypeCode;//支付类型编号（001）
    private String branchNum;//门店号
    private String shiftTableNum;//班次号
    private String operator;//操作者（营业员）
    private String systemBookCode;//账套号

    @Generated(hash = 660017012)
    public VipConsume(Long id, String type, String consumeMoney, String payType,
            String payTypeCode, String branchNum, String shiftTableNum,
            String operator, String systemBookCode) {
        this.id = id;
        this.type = type;
        this.consumeMoney = consumeMoney;
        this.payType = payType;
        this.payTypeCode = payTypeCode;
        this.branchNum = branchNum;
        this.shiftTableNum = shiftTableNum;
        this.operator = operator;
        this.systemBookCode = systemBookCode;
    }

    @Generated(hash = 778781807)
    public VipConsume() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(String consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    public String getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(String branchNum) {
        this.branchNum = branchNum;
    }

    public String getShiftTableNum() {
        return shiftTableNum;
    }

    public void setShiftTableNum(String shiftTableNum) {
        this.shiftTableNum = shiftTableNum;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSystemBookCode() {
        return systemBookCode;
    }

    public void setSystemBookCode(String systemBookCode) {
        this.systemBookCode = systemBookCode;
    }

    public String getPayType() {
        return this.payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayTypeCode() {
        return this.payTypeCode;
    }

    public void setPayTypeCode(String payTypeCode) {
        this.payTypeCode = payTypeCode;
    }
}
