package com.nhsoft.poslib.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VipCardTypeBean implements Serializable {
    /**
     * 消费卡类型
     */

    @SerializedName("编号")
    public String num;
    @SerializedName("消费折扣价格级别")
    public String discountPriceLevel;
    @SerializedName("消费折扣率")
    public String discountRate;
    @SerializedName("图片ID")
    public String photoId;
    @SerializedName("图片路径")
    public String photoPath;
    @SerializedName("存款兑分比例")
    public String strangeToFenRate;
    @SerializedName("消费兑分比例")
    public String consumeToFenRate;
    @SerializedName("是否允许线上挂失会员卡")
    public String allowOnlineVipLoseCard;
    @SerializedName("会员卡结算折扣")
    public String vipDiscount;
    @SerializedName("会员优惠不与消费券同享")
    public String vipConsumeWithOthers;
    @SerializedName("会员卡表面卡号颜色")
    public String vipCardColor;
    @SerializedName("是否启用")
    public String isEnabled;

    @SerializedName("名称")
    public String name;
    @SerializedName("默认有效日期")
    public String defaultPeriodOfValidity;
    @SerializedName("永久有效期")
    public String alwaysPeriodOfValidity;
    @SerializedName("价格级别")
    public String price_level;
    @SerializedName("折扣")
    public String discount;
    @SerializedName("支持储值")
    public String supportStrange;
    @SerializedName("支持积分")
    public String supportIntegral;
    @SerializedName("仅发卡门店使用")
    public String onlyBranchUse;
    @SerializedName("存款金额基数")
    public String strangeNum;
    @SerializedName("生日折扣")
    public String birthdayDiscount;
    @SerializedName("最低控制金额")
    public String lowestCtrlNum;
    @SerializedName("使用会员卡支付不享受折扣")
    public String useVipCardNoDiscount;
    @SerializedName("默认换卡费用")
    public  String defaultChangeCardFee;
    @SerializedName("默认续卡费用")
    private String defaultContinueCardFee;
    @SerializedName("单笔存款限额")
    private String signalStrangeMoneyLimit;
    @SerializedName("卡内余额限额")
    private String cardReaminMoneyLimit;


    public String getCardReaminMoneyLimit() {
        return cardReaminMoneyLimit;
    }

    public void setCardReaminMoneyLimit(String cardReaminMoneyLimit) {
        this.cardReaminMoneyLimit = cardReaminMoneyLimit;
    }

    public String getSignalStrangeMoneyLimit() {
        return signalStrangeMoneyLimit;
    }

    public void setSignalStrangeMoneyLimit(String signalStrangeMoneyLimit) {
        this.signalStrangeMoneyLimit = signalStrangeMoneyLimit;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlwaysPeriodOfValidity() {
        return alwaysPeriodOfValidity;
    }

    public void setAlwaysPeriodOfValidity(String alwaysPeriodOfValidity) {
        this.alwaysPeriodOfValidity = alwaysPeriodOfValidity;
    }

    public String getSupportStrange() {
        return supportStrange;
    }

    public void setSupportStrange(String supportStrange) {
        this.supportStrange = supportStrange;
    }

    public String getSupportIntegral() {
        return supportIntegral;
    }

    public void setSupportIntegral(String supportIntegral) {
        this.supportIntegral = supportIntegral;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDefaultPeriodOfValidity() {
        return defaultPeriodOfValidity;
    }

    public void setDefaultPeriodOfValidity(String defaultPeriodOfValidity) {
        this.defaultPeriodOfValidity = defaultPeriodOfValidity;
    }

    public String getOnlyBranchUse() {
        return onlyBranchUse;
    }

    public void setOnlyBranchUse(String onlyBranchUse) {
        this.onlyBranchUse = onlyBranchUse;
    }

    public String getUseVipCardNoDiscount() {
        return useVipCardNoDiscount;
    }

    public void setUseVipCardNoDiscount(String useVipCardNoDiscount) {
        this.useVipCardNoDiscount = useVipCardNoDiscount;
    }

    public String getStrangeToFenRate() {
        return strangeToFenRate;
    }

    public void setStrangeToFenRate(String strangeToFenRate) {
        this.strangeToFenRate = strangeToFenRate;
    }

    public String getConsumeToFenRate() {
        return consumeToFenRate;
    }

    public void setConsumeToFenRate(String consumeToFenRate) {
        this.consumeToFenRate = consumeToFenRate;
    }

    public String getAllowOnlineVipLoseCard() {
        return allowOnlineVipLoseCard;
    }

    public void setAllowOnlineVipLoseCard(String allowOnlineVipLoseCard) {
        this.allowOnlineVipLoseCard = allowOnlineVipLoseCard;
    }

    public String getVipDiscount() {
        return vipDiscount;
    }

    public void setVipDiscount(String vipDiscount) {
        this.vipDiscount = vipDiscount;
    }

    public String getVipConsumeWithOthers() {
        return vipConsumeWithOthers;
    }

    public void setVipConsumeWithOthers(String vipConsumeWithOthers) {
        this.vipConsumeWithOthers = vipConsumeWithOthers;
    }

    public String getVipCardColor() {
        return vipCardColor;
    }

    public void setVipCardColor(String vipCardColor) {
        this.vipCardColor = vipCardColor;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getPrice_level() {
        return price_level;
    }

    public void setPrice_level(String price_level) {
        this.price_level = price_level;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getStrangeNum() {
        return strangeNum;
    }

    public void setStrangeNum(String strangeNum) {
        this.strangeNum = strangeNum;
    }

    public String getBirthdayDiscount() {
        return birthdayDiscount;
    }

    public void setBirthdayDiscount(String birthdayDiscount) {
        this.birthdayDiscount = birthdayDiscount;
    }

    public String getLowestCtrlNum() {
        return lowestCtrlNum;
    }

    public void setLowestCtrlNum(String lowestCtrlNum) {
        this.lowestCtrlNum = lowestCtrlNum;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getDiscountPriceLevel() {
        return discountPriceLevel;
    }

    public void setDiscountPriceLevel(String discountPriceLevel) {
        this.discountPriceLevel = discountPriceLevel;
    }

    public String getDefaultChangeCardFee() {
        return defaultChangeCardFee;
    }

    public void setDefaultChangeCardFee(String defaultChangeCardFee) {
        this.defaultChangeCardFee = defaultChangeCardFee;
    }

    public String getDefaultContinueCardFee() {
        return defaultContinueCardFee;
    }

    public void setDefaultContinueCardFee(String defaultContinueCardFee) {
        this.defaultContinueCardFee = defaultContinueCardFee;
    }
}
