package com.nhsoft.poslib.entity;

import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.model.BasePolicyDetail;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by Iverson on 2019-05-15 15:40
 * 此类用于：
 */
@Entity
public class PolicyPresentDetail extends BasePolicyDetail {

    /**
     * policy_present_no : 4344990000003
     * policy_present_detail_num : 1
     * item_num : 434400011
     * policy_present_detail_present_amount : 2
     * policy_present_detail_price : 17
     * system_book_code : 4344
     * branch_num : 99
     */

    @Id
    private Long id;
    private String policy_present_no;
    private int    policy_present_detail_num;
    private int    item_num;
    private float  policy_present_detail_present_amount;
    private float  policy_present_detail_price;
    private String system_book_code;
    private int    branch_num;
    private float policy_present_detail_present_limit;

    public PosOrderDetail getPosOrderDetail() {
        return posOrderDetail;
    }

    public void setPosOrderDetail(PosOrderDetail posOrderDetail) {
        this.posOrderDetail = posOrderDetail;
    }

    @Transient
    private PosOrderDetail posOrderDetail;

    @Generated(hash = 1107015409)
    public PolicyPresentDetail(Long id, String policy_present_no, int policy_present_detail_num,
            int item_num, float policy_present_detail_present_amount, float policy_present_detail_price,
            String system_book_code, int branch_num, float policy_present_detail_present_limit) {
        this.id = id;
        this.policy_present_no = policy_present_no;
        this.policy_present_detail_num = policy_present_detail_num;
        this.item_num = item_num;
        this.policy_present_detail_present_amount = policy_present_detail_present_amount;
        this.policy_present_detail_price = policy_present_detail_price;
        this.system_book_code = system_book_code;
        this.branch_num = branch_num;
        this.policy_present_detail_present_limit = policy_present_detail_present_limit;
    }

    @Generated(hash = 285564305)
    public PolicyPresentDetail() {
    }

   
    public String getPolicy_present_no() {
        return policy_present_no;
    }

    public void setPolicy_present_no(String policy_present_no) {
        this.policy_present_no = policy_present_no;
    }

    public int getPolicy_present_detail_num() {
        return policy_present_detail_num;
    }

    public void setPolicy_present_detail_num(int policy_present_detail_num) {
        this.policy_present_detail_num = policy_present_detail_num;
    }

    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }

    public float getPolicy_present_detail_present_amount() {
        return policy_present_detail_present_amount;
    }

    public void setPolicy_present_detail_present_amount(float policy_present_detail_present_amount) {
        this.policy_present_detail_present_amount = policy_present_detail_present_amount;
    }

    public float getPolicy_present_detail_price() {
        return policy_present_detail_price;
    }

    public void setPolicy_present_detail_price(float policy_present_detail_price) {
        this.policy_present_detail_price = policy_present_detail_price;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public int getBranch_num() {
        return branch_num;
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

    public float getPolicy_present_detail_present_limit() {
        return this.policy_present_detail_present_limit;
    }

    public void setPolicy_present_detail_present_limit(float policy_present_detail_present_limit) {
        this.policy_present_detail_present_limit = policy_present_detail_present_limit;
    }

}
