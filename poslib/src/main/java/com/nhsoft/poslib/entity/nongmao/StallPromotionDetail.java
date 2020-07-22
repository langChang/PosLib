package com.nhsoft.poslib.entity.nongmao;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 农贸
 * 档口促销明细
 */
@Entity
public class StallPromotionDetail {
    /**
     * item_grade_num : 0
     * item_num : 0
     * policy_promotion_detail_bill_limit : 0
     * policy_promotion_detail_memo : string
     * policy_promotion_detail_num : 0
     * policy_promotion_detail_rate : 0
     * policy_promotion_detail_special_price : 0
     * policy_promotion_detail_std_price : 0
     * policy_promotion_no : string
     */
    @Id(autoincrement = true)
    private Long id;
    private String policy_promotion_no;
    private int item_grade_num;
    private int    item_num;
    private float    policy_promotion_detail_bill_limit;
    private String policy_promotion_detail_memo;
    private int    policy_promotion_detail_num;
    private float   policy_promotion_detail_rate;
    private float    policy_promotion_detail_special_price;
    private float    policy_promotion_detail_std_price;


    @Generated(hash = 466080581)
    public StallPromotionDetail(Long id, String policy_promotion_no, int item_grade_num, int item_num,
            float policy_promotion_detail_bill_limit, String policy_promotion_detail_memo,
            int policy_promotion_detail_num, float policy_promotion_detail_rate,
            float policy_promotion_detail_special_price, float policy_promotion_detail_std_price) {
        this.id = id;
        this.policy_promotion_no = policy_promotion_no;
        this.item_grade_num = item_grade_num;
        this.item_num = item_num;
        this.policy_promotion_detail_bill_limit = policy_promotion_detail_bill_limit;
        this.policy_promotion_detail_memo = policy_promotion_detail_memo;
        this.policy_promotion_detail_num = policy_promotion_detail_num;
        this.policy_promotion_detail_rate = policy_promotion_detail_rate;
        this.policy_promotion_detail_special_price = policy_promotion_detail_special_price;
        this.policy_promotion_detail_std_price = policy_promotion_detail_std_price;
    }

    @Generated(hash = 894427821)
    public StallPromotionDetail() {
    }

    public int getItem_grade_num() {
        return item_grade_num;
    }

    public void setItem_grade_num(int item_grade_num) {
        this.item_grade_num = item_grade_num;
    }

    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }

    public float getPolicy_promotion_detail_bill_limit() {
        return policy_promotion_detail_bill_limit;
    }

    public void setPolicy_promotion_detail_bill_limit(float policy_promotion_detail_bill_limit) {
        this.policy_promotion_detail_bill_limit = policy_promotion_detail_bill_limit;
    }

    public String getPolicy_promotion_detail_memo() {
        return policy_promotion_detail_memo;
    }

    public void setPolicy_promotion_detail_memo(String policy_promotion_detail_memo) {
        this.policy_promotion_detail_memo = policy_promotion_detail_memo;
    }

    public int getPolicy_promotion_detail_num() {
        return policy_promotion_detail_num;
    }

    public void setPolicy_promotion_detail_num(int policy_promotion_detail_num) {
        this.policy_promotion_detail_num = policy_promotion_detail_num;
    }

    public float getPolicy_promotion_detail_rate() {
        return policy_promotion_detail_rate;
    }

    public void setPolicy_promotion_detail_rate(float policy_promotion_detail_rate) {
        this.policy_promotion_detail_rate = policy_promotion_detail_rate;
    }

    public float getPolicy_promotion_detail_special_price() {
        return policy_promotion_detail_special_price;
    }

    public void setPolicy_promotion_detail_special_price(float policy_promotion_detail_special_price) {
        this.policy_promotion_detail_special_price = policy_promotion_detail_special_price;
    }

    public float getPolicy_promotion_detail_std_price() {
        return policy_promotion_detail_std_price;
    }

    public void setPolicy_promotion_detail_std_price(float policy_promotion_detail_std_price) {
        this.policy_promotion_detail_std_price = policy_promotion_detail_std_price;
    }

    public String getPolicy_promotion_no() {
        return policy_promotion_no;
    }

    public void setPolicy_promotion_no(String policy_promotion_no) {
        this.policy_promotion_no = policy_promotion_no;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
