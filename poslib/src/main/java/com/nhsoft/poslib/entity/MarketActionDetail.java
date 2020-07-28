package com.nhsoft.poslib.entity;


import com.nhsoft.poslib.model.BranchXmlModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2018/11/16 10:45 AM
 * 此类用于：
 */
@Entity
public class MarketActionDetail {
    /**
     * action_id : MA4444990000166
     * market_action_detail_num : 1
     * market_action_detail_amount : 1
     * market_action_detail_type_name : 5yuan
     * market_action_detail_branch : <?xml version="1.0" encoding="GBK"?>
     <AppliedBranchArray><AppliedBranch><AppliedBranchNum>0</AppliedBranchNum><AppliedBranchName>所有门店</AppliedBranchName></AppliedBranch></AppliedBranchArray>
     * market_action_detail_value : 5.0
     * market_action_detail_day : 2
     * market_action_detail_grade : 50.0
     */
    @Id(autoincrement = true)
    private Long id;
    private String action_id;
    private int    market_action_detail_num;
    private int    market_action_detail_amount;
    private String market_action_detail_type_name;
    private String market_action_detail_branch;
    private double market_action_detail_value;
    private int    market_action_detail_day;
    private float market_action_detail_grade;
    private Float market_action_detail_grade_to;
    private String market_action_detail_effective_date;
    private String market_action_detail_date;
    private boolean market_action_detail_auto_value;
    private float market_action_detail_value_min;
    private float market_action_detail_value_max;
    private boolean market_action_detail_self_branch;
    private String market_action_detail_valid_type;
    private Integer market_action_detail_start;

    private String coupon_action_detail_id;

    public String getMarket_action_detail_valid_type() {
        return market_action_detail_valid_type;
    }

    public void setMarket_action_detail_valid_type(String market_action_detail_valid_type) {
        this.market_action_detail_valid_type = market_action_detail_valid_type;
    }

    public Integer getMarket_action_detail_start() {
        return market_action_detail_start;
    }

    public void setMarket_action_detail_start(Integer market_action_detail_start) {
        this.market_action_detail_start = market_action_detail_start;
    }

    @Transient
    private int maket_action_send_count;

    public int getMaket_action_send_count() {
        return maket_action_send_count;
    }

    public void setMaket_action_send_count(int maket_action_send_count) {
        this.maket_action_send_count = maket_action_send_count;
    }



    @Transient
    private List<BranchXmlModel> branchXmlModels = new ArrayList<>();

    @Generated(hash = 1618964450)
    public MarketActionDetail(Long id, String action_id, int market_action_detail_num, int market_action_detail_amount, String market_action_detail_type_name,
            String market_action_detail_branch, double market_action_detail_value, int market_action_detail_day, float market_action_detail_grade,
            Float market_action_detail_grade_to, String market_action_detail_effective_date, String market_action_detail_date,
            boolean market_action_detail_auto_value, float market_action_detail_value_min, float market_action_detail_value_max,
            boolean market_action_detail_self_branch, String market_action_detail_valid_type, Integer market_action_detail_start, String coupon_action_detail_id) {
        this.id = id;
        this.action_id = action_id;
        this.market_action_detail_num = market_action_detail_num;
        this.market_action_detail_amount = market_action_detail_amount;
        this.market_action_detail_type_name = market_action_detail_type_name;
        this.market_action_detail_branch = market_action_detail_branch;
        this.market_action_detail_value = market_action_detail_value;
        this.market_action_detail_day = market_action_detail_day;
        this.market_action_detail_grade = market_action_detail_grade;
        this.market_action_detail_grade_to = market_action_detail_grade_to;
        this.market_action_detail_effective_date = market_action_detail_effective_date;
        this.market_action_detail_date = market_action_detail_date;
        this.market_action_detail_auto_value = market_action_detail_auto_value;
        this.market_action_detail_value_min = market_action_detail_value_min;
        this.market_action_detail_value_max = market_action_detail_value_max;
        this.market_action_detail_self_branch = market_action_detail_self_branch;
        this.market_action_detail_valid_type = market_action_detail_valid_type;
        this.market_action_detail_start = market_action_detail_start;
        this.coupon_action_detail_id = coupon_action_detail_id;
    }

    @Generated(hash = 1958696575)
    public MarketActionDetail() {
    }

    public List<BranchXmlModel> getBranchXmlModels() {
        return branchXmlModels;
    }

    public void setBranchXmlModels(List<BranchXmlModel> branchXmlModels) {
        this.branchXmlModels = branchXmlModels;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction_id() {
        return this.action_id;
    }

    public void setAction_id(String action_id) {
        this.action_id = action_id;
    }

    public int getMarket_action_detail_num() {
        return this.market_action_detail_num;
    }

    public void setMarket_action_detail_num(int market_action_detail_num) {
        this.market_action_detail_num = market_action_detail_num;
    }

    public int getMarket_action_detail_amount() {
        return this.market_action_detail_amount;
    }

    public void setMarket_action_detail_amount(int market_action_detail_amount) {
        this.market_action_detail_amount = market_action_detail_amount;
    }

    public String getMarket_action_detail_type_name() {
        return this.market_action_detail_type_name;
    }

    public void setMarket_action_detail_type_name(String market_action_detail_type_name) {
        this.market_action_detail_type_name = market_action_detail_type_name;
    }

    public String getMarket_action_detail_branch() {
        return this.market_action_detail_branch;
    }

    public void setMarket_action_detail_branch(String market_action_detail_branch) {
        this.market_action_detail_branch = market_action_detail_branch;
    }

    public double getMarket_action_detail_value() {
        return this.market_action_detail_value;
    }

    public void setMarket_action_detail_value(double market_action_detail_value) {
        this.market_action_detail_value = market_action_detail_value;
    }

    public int getMarket_action_detail_day() {
        return this.market_action_detail_day;
    }

    public void setMarket_action_detail_day(int market_action_detail_day) {
        this.market_action_detail_day = market_action_detail_day;
    }

    public float getMarket_action_detail_grade() {
        return this.market_action_detail_grade;
    }

    public void setMarket_action_detail_grade(float market_action_detail_grade) {
        this.market_action_detail_grade = market_action_detail_grade;
    }

    public String getMarket_action_detail_effective_date() {
        return this.market_action_detail_effective_date;
    }

    public void setMarket_action_detail_effective_date(String market_action_detail_effective_date) {
        this.market_action_detail_effective_date = market_action_detail_effective_date;
    }

    public String getMarket_action_detail_date() {
        return this.market_action_detail_date;
    }

    public void setMarket_action_detail_date(String market_action_detail_date) {
        this.market_action_detail_date = market_action_detail_date;
    }

    public boolean getMarket_action_detail_auto_value() {
        return this.market_action_detail_auto_value;
    }

    public void setMarket_action_detail_auto_value(boolean market_action_detail_auto_value) {
        this.market_action_detail_auto_value = market_action_detail_auto_value;
    }

    public float getMarket_action_detail_value_min() {
        return this.market_action_detail_value_min;
    }

    public void setMarket_action_detail_value_min(float market_action_detail_value_min) {
        this.market_action_detail_value_min = market_action_detail_value_min;
    }

    public float getMarket_action_detail_value_max() {
        return this.market_action_detail_value_max;
    }

    public void setMarket_action_detail_value_max(float market_action_detail_value_max) {
        this.market_action_detail_value_max = market_action_detail_value_max;
    }

    public boolean isMarket_action_detail_self_branch() {
        return market_action_detail_self_branch;
    }

    public void setMarket_action_detail_self_branch(boolean market_action_detail_self_branch) {
        this.market_action_detail_self_branch = market_action_detail_self_branch;
    }

    public boolean getMarket_action_detail_self_branch() {
        return this.market_action_detail_self_branch;
    }


    public Float getMarket_action_detail_grade_to() {
        return market_action_detail_grade_to;
    }

    public void setMarket_action_detail_grade_to(Float market_action_detail_grade_to) {
        this.market_action_detail_grade_to = market_action_detail_grade_to;
    }

    public String getCoupon_action_detail_id() {
        return this.coupon_action_detail_id;
    }

    public void setCoupon_action_detail_id(String coupon_action_detail_id) {
        this.coupon_action_detail_id = coupon_action_detail_id;
    }
}
