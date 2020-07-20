package com.nhsoft.poslib.entity.nongmao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Iverson on 2019-07-30 10:35
 * 此类用于：
 */
@Entity
public class StallDiscountDetail {
    /**
     * branch_num : 0
     * item_num : 0
     * policy_discount_detail_memo : string
     * policy_discount_detail_num : 0
     * policy_discount_no : string
     * system_book_code : string
     */
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;
    private int branch_num;
    private int    item_num;
    private String policy_discount_detail_memo;
    private int    policy_discount_detail_num;
    private String policy_discount_no;
    private String system_book_code;

    @Generated(hash = 345427704)
    public StallDiscountDetail(Long id, int branch_num, int item_num,
            String policy_discount_detail_memo, int policy_discount_detail_num,
            String policy_discount_no, String system_book_code) {
        this.id = id;
        this.branch_num = branch_num;
        this.item_num = item_num;
        this.policy_discount_detail_memo = policy_discount_detail_memo;
        this.policy_discount_detail_num = policy_discount_detail_num;
        this.policy_discount_no = policy_discount_no;
        this.system_book_code = system_book_code;
    }

    @Generated(hash = 397704524)
    public StallDiscountDetail() {
    }

    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }

    public String getPolicy_discount_detail_memo() {
        return policy_discount_detail_memo;
    }

    public void setPolicy_discount_detail_memo(String policy_discount_detail_memo) {
        this.policy_discount_detail_memo = policy_discount_detail_memo;
    }

    public int getPolicy_discount_detail_num() {
        return policy_discount_detail_num;
    }

    public void setPolicy_discount_detail_num(int policy_discount_detail_num) {
        this.policy_discount_detail_num = policy_discount_detail_num;
    }

    public String getPolicy_discount_no() {
        return policy_discount_no;
    }

    public void setPolicy_discount_no(String policy_discount_no) {
        this.policy_discount_no = policy_discount_no;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
