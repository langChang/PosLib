package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Iverson on 2019-09-25 11:23
 * 此类用于：
 */
@Entity
public class FmPayment {

    @Id
    private Long id;
    private String orderNo;
    private int orderPaymentNum;
    private String systemBookCode;
    private int branchNum;
    private int shiftTableNum;
    private String shiftTableBizday;
    private int merchantNum;
    private long branchId;
    private String paymentTime;
    private String paymentPayBy;
    private float paymentRound;
    private float paymentReceive;
    private float paymentMoney;
    private float paymentChange;
    private float paymentPaid;
    private String paymentBillNo;
    private String paymentMemo;
    private int paymentCustNum;
    @Generated(hash = 611105284)
    public FmPayment(Long id, String orderNo, int orderPaymentNum,
            String systemBookCode, int branchNum, int shiftTableNum,
            String shiftTableBizday, int merchantNum, long branchId,
            String paymentTime, String paymentPayBy, float paymentRound,
            float paymentReceive, float paymentMoney, float paymentChange,
            float paymentPaid, String paymentBillNo, String paymentMemo,
            int paymentCustNum) {
        this.id = id;
        this.orderNo = orderNo;
        this.orderPaymentNum = orderPaymentNum;
        this.systemBookCode = systemBookCode;
        this.branchNum = branchNum;
        this.shiftTableNum = shiftTableNum;
        this.shiftTableBizday = shiftTableBizday;
        this.merchantNum = merchantNum;
        this.branchId = branchId;
        this.paymentTime = paymentTime;
        this.paymentPayBy = paymentPayBy;
        this.paymentRound = paymentRound;
        this.paymentReceive = paymentReceive;
        this.paymentMoney = paymentMoney;
        this.paymentChange = paymentChange;
        this.paymentPaid = paymentPaid;
        this.paymentBillNo = paymentBillNo;
        this.paymentMemo = paymentMemo;
        this.paymentCustNum = paymentCustNum;
    }
    @Generated(hash = 471740883)
    public FmPayment() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOrderNo() {
        return this.orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public int getOrderPaymentNum() {
        return this.orderPaymentNum;
    }
    public void setOrderPaymentNum(int orderPaymentNum) {
        this.orderPaymentNum = orderPaymentNum;
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
    public int getShiftTableNum() {
        return this.shiftTableNum;
    }
    public void setShiftTableNum(int shiftTableNum) {
        this.shiftTableNum = shiftTableNum;
    }
    public String getShiftTableBizday() {
        return this.shiftTableBizday;
    }
    public void setShiftTableBizday(String shiftTableBizday) {
        this.shiftTableBizday = shiftTableBizday;
    }
    public int getMerchantNum() {
        return this.merchantNum;
    }
    public void setMerchantNum(int merchantNum) {
        this.merchantNum = merchantNum;
    }
    public long getBranchId() {
        return this.branchId;
    }
    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }
    public String getPaymentTime() {
        return this.paymentTime;
    }
    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }
    public String getPaymentPayBy() {
        return this.paymentPayBy;
    }
    public void setPaymentPayBy(String paymentPayBy) {
        this.paymentPayBy = paymentPayBy;
    }
    public float getPaymentRound() {
        return this.paymentRound;
    }
    public void setPaymentRound(float paymentRound) {
        this.paymentRound = paymentRound;
    }
    public float getPaymentReceive() {
        return this.paymentReceive;
    }
    public void setPaymentReceive(float paymentReceive) {
        this.paymentReceive = paymentReceive;
    }
    public float getPaymentMoney() {
        return this.paymentMoney;
    }
    public void setPaymentMoney(float paymentMoney) {
        this.paymentMoney = paymentMoney;
    }
    public float getPaymentChange() {
        return this.paymentChange;
    }
    public void setPaymentChange(float paymentChange) {
        this.paymentChange = paymentChange;
    }
    public float getPaymentPaid() {
        return this.paymentPaid;
    }
    public void setPaymentPaid(float paymentPaid) {
        this.paymentPaid = paymentPaid;
    }
    public String getPaymentBillNo() {
        return this.paymentBillNo;
    }
    public void setPaymentBillNo(String paymentBillNo) {
        this.paymentBillNo = paymentBillNo;
    }
    public String getPaymentMemo() {
        return this.paymentMemo;
    }
    public void setPaymentMemo(String paymentMemo) {
        this.paymentMemo = paymentMemo;
    }
    public int getPaymentCustNum() {
        return this.paymentCustNum;
    }
    public void setPaymentCustNum(int paymentCustNum) {
        this.paymentCustNum = paymentCustNum;
    }

}
