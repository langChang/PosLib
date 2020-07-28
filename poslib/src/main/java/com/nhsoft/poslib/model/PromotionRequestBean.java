package com.nhsoft.poslib.model;

/**
 * Created by Iverson on 2019-09-17 09:01
 * 此类用于：
 */
public class PromotionRequestBean {
    /**
     * policy_fid : string
     * policy_type : string
     */

    private String policy_fid;
    private String policy_type;

    public String getPolicy_fid() {
        return policy_fid;
    }

    public void setPolicy_fid(String policy_fid) {
        this.policy_fid = policy_fid;
    }

    public String getPolicy_type() {
        return policy_type;
    }

    public void setPolicy_type(String policy_type) {
        this.policy_type = policy_type;
    }
}
