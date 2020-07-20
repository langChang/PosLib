package com.nhsoft.poslib.model;

import com.nhsoft.poslib.entity.PolicyQuantityDetail;

/**
 * Created by Iverson on 2019-05-13 16:33
 * 此类用于：
 */
public class PolicyQuantityPriceBean {

    private float                price;
    private String               policyQuantityId;
    private PolicyQuantityDetail policyQuantityDetail;


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPolicyQuantityId() {
        return policyQuantityId;
    }

    public void setPolicyQuantityId(String policyQuantityId) {
        this.policyQuantityId = policyQuantityId;
    }

    public PolicyQuantityDetail getPolicyQuantityDetail() {
        return policyQuantityDetail;
    }

    public void setPolicyQuantityDetail(PolicyQuantityDetail policyQuantityDetail) {
        this.policyQuantityDetail = policyQuantityDetail;
    }
}
