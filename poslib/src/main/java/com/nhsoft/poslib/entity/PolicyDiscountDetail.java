package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Iverson on 2019/4/16 2:30 PM
 * 此类用于：
 */
@Entity
public class PolicyDiscountDetail {

    @Id(autoincrement = true)
    private Long id;

    private String policy_discount_no;
    private int policy_discount_detail_num;
    private int item_num;
    private String system_book_code;
    private String policy_discount_detail_memo;
    private int branch_num;

    @Generated(hash = 1624446323)
    public PolicyDiscountDetail(Long id, String policy_discount_no,
            int policy_discount_detail_num, int item_num, String system_book_code,
            String policy_discount_detail_memo, int branch_num) {
        this.id = id;
        this.policy_discount_no = policy_discount_no;
        this.policy_discount_detail_num = policy_discount_detail_num;
        this.item_num = item_num;
        this.system_book_code = system_book_code;
        this.policy_discount_detail_memo = policy_discount_detail_memo;
        this.branch_num = branch_num;
    }
    @Generated(hash = 1713091558)
    public PolicyDiscountDetail() {
    }

    public String getPolicy_discount_no() {
        return this.policy_discount_no;
    }
    public void setPolicy_discount_no(String policy_discount_no) {
        this.policy_discount_no = policy_discount_no;
    }
    public int getPolicy_discount_detail_num() {
        return this.policy_discount_detail_num;
    }
    public void setPolicy_discount_detail_num(int policy_discount_detail_num) {
        this.policy_discount_detail_num = policy_discount_detail_num;
    }
    public int getItem_num() {
        return this.item_num;
    }
    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }
    public String getSystem_book_code() {
        return this.system_book_code;
    }
    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }
    public String getPolicy_discount_detail_memo() {
        return this.policy_discount_detail_memo;
    }
    public void setPolicy_discount_detail_memo(String policy_discount_detail_memo) {
        this.policy_discount_detail_memo = policy_discount_detail_memo;
    }
    public int getBranch_num() {
        return this.branch_num;
    }
    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
