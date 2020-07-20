package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Iverson on 2018/11/16 2:30 PM
 * 此类用于：
 */
@Entity
public class PolicyPromotionDetail {

    /**
     * policy_promotion_no : 4444990000062
     * policy_promotion_detail_num : 1
     * item_num : 444400983
     * policy_promotion_detail_std_price : 9.2
     * policy_promotion_detail_special_price : 9.2
     * policy_promotion_detail_bill_limit : 0.0
     * policy_promotion_detail_policy_limit : 0.0
     * policy_promotion_detail_cost : 10.0
     * system_book_code : 4444
     * branch_num : 99
     * policy_promotion_detail_gross : 0.0
     * policy_promotion_detail_policy_cost : 10.0
     */

    @Id(autoincrement = true)
    private Long id;
    private String policy_promotion_no;
    private int    policy_promotion_detail_num;
    private int    item_num;
    private int item_grade_num;
    private double policy_promotion_detail_std_price;
    private double policy_promotion_detail_special_price;
    private double policy_promotion_detail_bill_limit;
    private double policy_promotion_detail_policy_limit;
    private double policy_promotion_detail_cost;
    private String policy_promotion_detail_lot_num;
    private String system_book_code;
    private int    branch_num;
    private double policy_promotion_detail_gross;
    private double policy_promotion_detail_policy_cost;

    @Generated(hash = 844052044)
    public PolicyPromotionDetail(Long id, String policy_promotion_no,
            int policy_promotion_detail_num, int item_num, int item_grade_num,
            double policy_promotion_detail_std_price,
            double policy_promotion_detail_special_price,
            double policy_promotion_detail_bill_limit,
            double policy_promotion_detail_policy_limit, double policy_promotion_detail_cost,
            String policy_promotion_detail_lot_num, String system_book_code, int branch_num,
            double policy_promotion_detail_gross,
            double policy_promotion_detail_policy_cost) {
        this.id = id;
        this.policy_promotion_no = policy_promotion_no;
        this.policy_promotion_detail_num = policy_promotion_detail_num;
        this.item_num = item_num;
        this.item_grade_num = item_grade_num;
        this.policy_promotion_detail_std_price = policy_promotion_detail_std_price;
        this.policy_promotion_detail_special_price = policy_promotion_detail_special_price;
        this.policy_promotion_detail_bill_limit = policy_promotion_detail_bill_limit;
        this.policy_promotion_detail_policy_limit = policy_promotion_detail_policy_limit;
        this.policy_promotion_detail_cost = policy_promotion_detail_cost;
        this.policy_promotion_detail_lot_num = policy_promotion_detail_lot_num;
        this.system_book_code = system_book_code;
        this.branch_num = branch_num;
        this.policy_promotion_detail_gross = policy_promotion_detail_gross;
        this.policy_promotion_detail_policy_cost = policy_promotion_detail_policy_cost;
    }

    @Generated(hash = 1120480607)
    public PolicyPromotionDetail() {
    }

    public int getItem_grade_num() {
        return item_grade_num;
    }

    public void setItem_grade_num(int item_grade_num) {
        this.item_grade_num = item_grade_num;
    }


   
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPolicy_promotion_no() {
        return this.policy_promotion_no;
    }
    public void setPolicy_promotion_no(String policy_promotion_no) {
        this.policy_promotion_no = policy_promotion_no;
    }
    public int getPolicy_promotion_detail_num() {
        return this.policy_promotion_detail_num;
    }
    public void setPolicy_promotion_detail_num(int policy_promotion_detail_num) {
        this.policy_promotion_detail_num = policy_promotion_detail_num;
    }
    public int getItem_num() {
        return this.item_num;
    }
    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }
    public double getPolicy_promotion_detail_std_price() {
        return this.policy_promotion_detail_std_price;
    }
    public void setPolicy_promotion_detail_std_price(
            double policy_promotion_detail_std_price) {
        this.policy_promotion_detail_std_price = policy_promotion_detail_std_price;
    }
    public double getPolicy_promotion_detail_special_price() {
        return this.policy_promotion_detail_special_price;
    }
    public void setPolicy_promotion_detail_special_price(
            double policy_promotion_detail_special_price) {
        this.policy_promotion_detail_special_price = policy_promotion_detail_special_price;
    }
    public double getPolicy_promotion_detail_bill_limit() {
        return this.policy_promotion_detail_bill_limit;
    }
    public void setPolicy_promotion_detail_bill_limit(
            double policy_promotion_detail_bill_limit) {
        this.policy_promotion_detail_bill_limit = policy_promotion_detail_bill_limit;
    }
    public double getPolicy_promotion_detail_policy_limit() {
        return this.policy_promotion_detail_policy_limit;
    }
    public void setPolicy_promotion_detail_policy_limit(
            double policy_promotion_detail_policy_limit) {
        this.policy_promotion_detail_policy_limit = policy_promotion_detail_policy_limit;
    }
    public double getPolicy_promotion_detail_cost() {
        return this.policy_promotion_detail_cost;
    }
    public void setPolicy_promotion_detail_cost(
            double policy_promotion_detail_cost) {
        this.policy_promotion_detail_cost = policy_promotion_detail_cost;
    }
    public String getPolicy_promotion_detail_lot_num() {
        return this.policy_promotion_detail_lot_num;
    }
    public void setPolicy_promotion_detail_lot_num(
            String policy_promotion_detail_lot_num) {
        this.policy_promotion_detail_lot_num = policy_promotion_detail_lot_num;
    }
    public String getSystem_book_code() {
        return this.system_book_code;
    }
    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }
    public int getBranch_num() {
        return this.branch_num;
    }
    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }
    public double getPolicy_promotion_detail_gross() {
        return this.policy_promotion_detail_gross;
    }
    public void setPolicy_promotion_detail_gross(
            double policy_promotion_detail_gross) {
        this.policy_promotion_detail_gross = policy_promotion_detail_gross;
    }
    public double getPolicy_promotion_detail_policy_cost() {
        return this.policy_promotion_detail_policy_cost;
    }
    public void setPolicy_promotion_detail_policy_cost(
            double policy_promotion_detail_policy_cost) {
        this.policy_promotion_detail_policy_cost = policy_promotion_detail_policy_cost;
    }
    
    
}
