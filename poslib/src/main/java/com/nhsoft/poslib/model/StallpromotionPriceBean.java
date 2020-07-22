package com.nhsoft.poslib.model;

import com.nhsoft.poslib.entity.nongmao.StallPromotionDetail;

/**
 * Created by Iverson on 2019/2/22 1:39 PM
 * 此类用于：
 */
public class StallpromotionPriceBean {
    private float                price;
    private String               policypromotionId;
    private StallPromotionDetail policyPromotionDetail;
    private float policy_promotion_detail_rate;

    public float getPolicy_promotion_detail_rate() {
        return policy_promotion_detail_rate;
    }

    public void setPolicy_promotion_detail_rate(float policy_promotion_detail_rate) {
        this.policy_promotion_detail_rate = policy_promotion_detail_rate;
    }



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

    public StallPromotionDetail getPolicyPromotionDetail() {
        return policyPromotionDetail;
    }

    public void setPolicyPromotionDetail(StallPromotionDetail policyPromotionDetail) {
        this.policyPromotionDetail = policyPromotionDetail;
    }

}
