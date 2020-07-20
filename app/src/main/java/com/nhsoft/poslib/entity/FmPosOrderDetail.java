package com.nhsoft.poslib.entity;

import com.nhsoft.poslib.entity.new_nong_mao.PosItemNewNongMao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by Iverson on 2019-09-25 11:22
 * 此类用于：
 */
@Entity
public class FmPosOrderDetail implements Cloneable{

    @Id
    private Long id;
    private int orderDetailNum;
    private String orderNo;
    private String systemBookCode;
    private int branchNum;
    private int merchantNum;
    private long branchId;
    private String shiftTableBizday;
    private int shiftTableNum;
    private int orderStateCode;
    private long itemId;
    private int itemNum;
    private String orderDetailItem;
    private float orderDetailStdPrice;
    private float orderDetailPrice;
    private float orderDetailAmount;
    private float orderDetailMoney;
    private float orderDetailDiscount;
    private float orderDetailPaymentMoney;
    private int orderDetailStateCode;
    private String orderDetailStateName;
    private String orderDetailMemo;
    private String orderDetailPolicyFid;
    private int orderDetailPolicyDetailNum;
    private int orderDetailPromotionType;
    private float orderDetailMerchantRate;
    private float orderDetailShareDiscount;
    private String itemUnit;
    @Transient
    private PosItemNewNongMao posItem;

    public PosItemNewNongMao getPosItem() {
        return posItem;
    }

    public void setPosItem(PosItemNewNongMao posItem) {
        this.posItem = posItem;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Generated(hash = 1084444468)
    public FmPosOrderDetail(Long id, int orderDetailNum, String orderNo,
            String systemBookCode, int branchNum, int merchantNum, long branchId,
            String shiftTableBizday, int shiftTableNum, int orderStateCode,
            long itemId, int itemNum, String orderDetailItem,
            float orderDetailStdPrice, float orderDetailPrice,
            float orderDetailAmount, float orderDetailMoney,
            float orderDetailDiscount, float orderDetailPaymentMoney,
            int orderDetailStateCode, String orderDetailStateName,
            String orderDetailMemo, String orderDetailPolicyFid,
            int orderDetailPolicyDetailNum, int orderDetailPromotionType,
            float orderDetailMerchantRate, float orderDetailShareDiscount,
            String itemUnit) {
        this.id = id;
        this.orderDetailNum = orderDetailNum;
        this.orderNo = orderNo;
        this.systemBookCode = systemBookCode;
        this.branchNum = branchNum;
        this.merchantNum = merchantNum;
        this.branchId = branchId;
        this.shiftTableBizday = shiftTableBizday;
        this.shiftTableNum = shiftTableNum;
        this.orderStateCode = orderStateCode;
        this.itemId = itemId;
        this.itemNum = itemNum;
        this.orderDetailItem = orderDetailItem;
        this.orderDetailStdPrice = orderDetailStdPrice;
        this.orderDetailPrice = orderDetailPrice;
        this.orderDetailAmount = orderDetailAmount;
        this.orderDetailMoney = orderDetailMoney;
        this.orderDetailDiscount = orderDetailDiscount;
        this.orderDetailPaymentMoney = orderDetailPaymentMoney;
        this.orderDetailStateCode = orderDetailStateCode;
        this.orderDetailStateName = orderDetailStateName;
        this.orderDetailMemo = orderDetailMemo;
        this.orderDetailPolicyFid = orderDetailPolicyFid;
        this.orderDetailPolicyDetailNum = orderDetailPolicyDetailNum;
        this.orderDetailPromotionType = orderDetailPromotionType;
        this.orderDetailMerchantRate = orderDetailMerchantRate;
        this.orderDetailShareDiscount = orderDetailShareDiscount;
        this.itemUnit = itemUnit;
    }

    @Generated(hash = 920165728)
    public FmPosOrderDetail() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getOrderDetailNum() {
        return this.orderDetailNum;
    }
    public void setOrderDetailNum(int orderDetailNum) {
        this.orderDetailNum = orderDetailNum;
    }
    public String getOrderNo() {
        return this.orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
    public String getShiftTableBizday() {
        return this.shiftTableBizday;
    }
    public void setShiftTableBizday(String shiftTableBizday) {
        this.shiftTableBizday = shiftTableBizday;
    }
    public int getShiftTableNum() {
        return this.shiftTableNum;
    }
    public void setShiftTableNum(int shiftTableNum) {
        this.shiftTableNum = shiftTableNum;
    }
    public int getOrderStateCode() {
        return this.orderStateCode;
    }
    public void setOrderStateCode(int orderStateCode) {
        this.orderStateCode = orderStateCode;
    }
    public long getItemId() {
        return this.itemId;
    }
    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
    public int getItemNum() {
        return this.itemNum;
    }
    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }
    public String getOrderDetailItem() {
        return this.orderDetailItem;
    }
    public void setOrderDetailItem(String orderDetailItem) {
        this.orderDetailItem = orderDetailItem;
    }
    public float getOrderDetailStdPrice() {
        return this.orderDetailStdPrice;
    }
    public void setOrderDetailStdPrice(float orderDetailStdPrice) {
        this.orderDetailStdPrice = orderDetailStdPrice;
    }
    public float getOrderDetailPrice() {
        return this.orderDetailPrice;
    }
    public void setOrderDetailPrice(float orderDetailPrice) {
        this.orderDetailPrice = orderDetailPrice;
    }
    public float getOrderDetailAmount() {
        return this.orderDetailAmount;
    }
    public void setOrderDetailAmount(float orderDetailAmount) {
        this.orderDetailAmount = orderDetailAmount;
    }
    public float getOrderDetailMoney() {
        return this.orderDetailMoney;
    }
    public void setOrderDetailMoney(float orderDetailMoney) {
        this.orderDetailMoney = orderDetailMoney;
    }
    public float getOrderDetailDiscount() {
        return this.orderDetailDiscount;
    }
    public void setOrderDetailDiscount(float orderDetailDiscount) {
        this.orderDetailDiscount = orderDetailDiscount;
    }
    public float getOrderDetailPaymentMoney() {
        return this.orderDetailPaymentMoney;
    }
    public void setOrderDetailPaymentMoney(float orderDetailPaymentMoney) {
        this.orderDetailPaymentMoney = orderDetailPaymentMoney;
    }
    public int getOrderDetailStateCode() {
        return this.orderDetailStateCode;
    }
    public void setOrderDetailStateCode(int orderDetailStateCode) {
        this.orderDetailStateCode = orderDetailStateCode;
    }
    public String getOrderDetailStateName() {
        return this.orderDetailStateName;
    }
    public void setOrderDetailStateName(String orderDetailStateName) {
        this.orderDetailStateName = orderDetailStateName;
    }
    public String getOrderDetailMemo() {
        return this.orderDetailMemo;
    }
    public void setOrderDetailMemo(String orderDetailMemo) {
        this.orderDetailMemo = orderDetailMemo;
    }
    public String getOrderDetailPolicyFid() {
        return this.orderDetailPolicyFid;
    }
    public void setOrderDetailPolicyFid(String orderDetailPolicyFid) {
        this.orderDetailPolicyFid = orderDetailPolicyFid;
    }
    public int getOrderDetailPolicyDetailNum() {
        return this.orderDetailPolicyDetailNum;
    }
    public void setOrderDetailPolicyDetailNum(int orderDetailPolicyDetailNum) {
        this.orderDetailPolicyDetailNum = orderDetailPolicyDetailNum;
    }
    public int getOrderDetailPromotionType() {
        return this.orderDetailPromotionType;
    }
    public void setOrderDetailPromotionType(int orderDetailPromotionType) {
        this.orderDetailPromotionType = orderDetailPromotionType;
    }
    public float getOrderDetailMerchantRate() {
        return this.orderDetailMerchantRate;
    }
    public void setOrderDetailMerchantRate(float orderDetailMerchantRate) {
        this.orderDetailMerchantRate = orderDetailMerchantRate;
    }
    public float getOrderDetailShareDiscount() {
        return this.orderDetailShareDiscount;
    }
    public void setOrderDetailShareDiscount(float orderDetailShareDiscount) {
        this.orderDetailShareDiscount = orderDetailShareDiscount;
    }

    public String getItemUnit() {
        return this.itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }
}
