package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * IC卡初始化
 */
@Entity
public class VipIcInit {

    @Id
    @Property(nameInDb = "CARDUSERNUM")
    private String cardUserNum;
    private String shiftTableBizDay;
    private String printNum;//表面卡号
    private String operateTime;//操作时间
    private String operator;//
    private String status;//开卡状态  1 开卡成功  2 开卡失败
    private String mome;//备注
    private String systemBookCode;
    private int branchNum;
    private String shiftTableNum;



    @Generated(hash = 1367308100)
    public VipIcInit(String cardUserNum, String shiftTableBizDay, String printNum,
            String operateTime, String operator, String status, String mome,
            String systemBookCode, int branchNum, String shiftTableNum) {
        this.cardUserNum = cardUserNum;
        this.shiftTableBizDay = shiftTableBizDay;
        this.printNum = printNum;
        this.operateTime = operateTime;
        this.operator = operator;
        this.status = status;
        this.mome = mome;
        this.systemBookCode = systemBookCode;
        this.branchNum = branchNum;
        this.shiftTableNum = shiftTableNum;
    }
    @Generated(hash = 379356002)
    public VipIcInit() {
    }



    public String getShiftTableBizDay() {
        return this.shiftTableBizDay;
    }
    public void setShiftTableBizDay(String shiftTableBizDay) {
        this.shiftTableBizDay = shiftTableBizDay;
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
    public String getCardUserNum() {
        return this.cardUserNum;
    }
    public void setCardUserNum(String cardUserNum) {
        this.cardUserNum = cardUserNum;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMome() {
        return this.mome;
    }
    public void setMome(String mome) {
        this.mome = mome;
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
}
