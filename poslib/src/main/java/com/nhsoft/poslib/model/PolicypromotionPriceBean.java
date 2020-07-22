package com.nhsoft.poslib.model;

import com.nhsoft.poslib.entity.PolicyPromotionDetail;

/**
 * Created by Iverson on 2019/2/22 1:39 PM
 * 此类用于：
 */
public class PolicypromotionPriceBean {
    private float price;
    private String policypromotionId;
    private PolicyPromotionDetail policyPromotionDetail;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPolicypromotionId() {
        return policypromotionId;
    }

    public void setPolicypromotionId(String policypromotionId) {
        this.policypromotionId = policypromotionId;
    }

    public PolicyPromotionDetail getPolicyPromotionDetail() {
        return policyPromotionDetail;
    }

    public void setPolicyPromotionDetail(PolicyPromotionDetail policyPromotionDetail) {
        this.policyPromotionDetail = policyPromotionDetail;
    }

}
