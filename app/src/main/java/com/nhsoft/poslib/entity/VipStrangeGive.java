package com.nhsoft.poslib.entity;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VipStrangeGive {


    @SerializedName("编号")
    public String num;
    @SerializedName("金额上限")
    private String upperLimit;
    @SerializedName("金额下限")
    private String lowerLimit;
    @SerializedName("赠送金额")
    private String givingRate;
    @SerializedName("周期")
    private String cycle;
    @SerializedName("重复活动")
    private String repeatActivity;
    @SerializedName("开始日期")
    private String stratDate;
    @SerializedName("结束日期")
    private String endDate;
    @SerializedName("卡状态")
    private String state;

    @SerializedName("适用支付方式")
    private  List<PayType> applyPayTypeList;

    @SerializedName("应用门店范围")
    private  List<StoreHouse> applyStoreHouseList;

    @SerializedName("适用卡类型")
    private List<CardType> applyCardTypeList;


    public static class CardType{
        private String CardTypeName;
        private String CardTypeCode;

        public String getCardTypeName() {
            return CardTypeName;
        }

        public void setCardTypeName(String cardTypeName) {
            CardTypeName = cardTypeName;
        }

        public String getCardTypeCode() {
            return CardTypeCode;
        }

        public void setCardTypeCode(String cardTypeCode) {
            CardTypeCode = cardTypeCode;
        }
//        @SerializedName("会员卡类型")
//        private List<CardTypeLis> cardTypeLisList;
//
//
//        public static class CardTypeLis{
//
//
//        }
//
//        public List<CardTypeLis> getCardTypeLisList() {
//            return cardTypeLisList;
//        }
//
//        public void setCardTypeLisList(List<CardTypeLis> cardTypeLisList) {
//            this.cardTypeLisList = cardTypeLisList;
//        }
    }

    public static class PayType{
        private String PaymentTypeName;

        public String getPaymentTypeName() {
            return PaymentTypeName;
        }

        public void setPaymentTypeName(String paymentTypeName) {
            PaymentTypeName = paymentTypeName;
        }
//        @SerializedName("支付方式")
//        private List<PayTypeList>  payTypeListList;
//
//        public static class PayTypeList{
//
//
//        }
//
//        public List<PayTypeList> getPayTypeListList() {
//            return payTypeListList;
//        }
//
//        public void setPayTypeListList(List<PayTypeList> payTypeListList) {
//            this.payTypeListList = payTypeListList;
//        }
    }

    public static class StoreHouse{
        private String AppliedBranchNum;
        private String AppliedBranchName;

        public String getAppliedBranchNum() {
            return AppliedBranchNum;
        }

        public void setAppliedBranchNum(String appliedBranchNum) {
            AppliedBranchNum = appliedBranchNum;
        }

        public String getAppliedBranchName() {
            return AppliedBranchName;
        }

        public void setAppliedBranchName(String appliedBranchName) {
            AppliedBranchName = appliedBranchName;
        }
//
//        @SerializedName("应用门店")
//        private List<storeHoustList> storeHoustListList;
//
//        public static class storeHoustList{
//
//        }
//
//        public List<storeHoustList> getStoreHoustListList() {
//            return storeHoustListList;
//        }
//
//        public void setStoreHoustListList(List<storeHoustList> storeHoustListList) {
//            this.storeHoustListList = storeHoustListList;
//        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(String upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(String lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public String getGivingRate() {
        return givingRate;
    }

    public void setGivingRate(String givingRate) {
        this.givingRate = givingRate;
    }

    public String getRepeatActivity() {
        return repeatActivity;
    }

    public void setRepeatActivity(String repeatActivity) {
        this.repeatActivity = repeatActivity;
    }

    public String getStratDate() {
        return stratDate;
    }

    public void setStratDate(String stratDate) {
        this.stratDate = stratDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<PayType> getApplyPayTypeList() {
        return applyPayTypeList;
    }

    public void setApplyPayTypeList(List<PayType> applyPayTypeList) {
        this.applyPayTypeList = applyPayTypeList;
    }

    public List<StoreHouse> getApplyStoreHouseList() {
        return applyStoreHouseList;
    }

    public void setApplyStoreHouseList(List<StoreHouse> applyStoreHouseList) {
        this.applyStoreHouseList = applyStoreHouseList;
    }

    public List<CardType> getApplyCardTypeList() {
        return applyCardTypeList;
    }

    public void setApplyCardTypeList(List<CardType> applyCardTypeList) {
        this.applyCardTypeList = applyCardTypeList;
    }
}
