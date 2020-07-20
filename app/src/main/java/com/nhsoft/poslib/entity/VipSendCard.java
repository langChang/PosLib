package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class VipSendCard {

    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;
    private String systemBookCode;
    private int branchNum;
    private String shiftTableNum;
    private float money;
    private String shiftTableBizDay;
    private String printNum;//表面卡号
    private String operateTime;//操作时间
    private String operator;//
    private String status;//开卡状态  1 开卡成功  2 开卡失败

    public String getShiftTableBizDay() {
        return shiftTableBizDay;
    }

    public void setShiftTableBizDay(String shiftTableBizDay) {
        this.shiftTableBizDay = shiftTableBizDay;
    }

    @Generated(hash = 74313156)
    public VipSendCard(Long id, String systemBookCode, int branchNum,
            String shiftTableNum, float money, String shiftTableBizDay,
            String printNum, String operateTime, String operator, String status) {
        this.id = id;
        this.systemBookCode = systemBookCode;
        this.branchNum = branchNum;
        this.shiftTableNum = shiftTableNum;
        this.money = money;
        this.shiftTableBizDay = shiftTableBizDay;
        this.printNum = printNum;
        this.operateTime = operateTime;
        this.operator = operator;
        this.status = status;
    }

    @Generated(hash = 355330340)
    public VipSendCard() {
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

    public String getPrintNum() {
        return this.printNum;
    }

    public void setPrintNum(String printNum) {
        this.printNum = printNum;
    }

    public String getOperateTime() {
        return this.operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
