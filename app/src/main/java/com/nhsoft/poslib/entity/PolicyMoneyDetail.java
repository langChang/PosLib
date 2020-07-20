package com.nhsoft.poslib.entity;

import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.model.BasePolicyDetail;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by Iverson on 2019-05-15 15:32
 * 此类用于：
 */
@Entity
public class PolicyMoneyDetail extends BasePolicyDetail {
    /**
     * promotion_money_no : 4344990000005
     * promotion_money_detail_num : 4
     * item_num : 434400014
     * promotion_money_detail_special_price : 17
     * promotion_money_detail_amount_limit : 1
     * system_book_code : 4344
     * branch_num : 99
     */

    @Id(autoincrement = true)
    private Long id;
    private String promotion_money_no;
    private int    promotion_money_detail_num;
    private int    item_num;
    private float  promotion_money_detail_special_price;
    private float  promotion_money_detail_amount_limit;
    private String system_book_code;
    private int    branch_num;

    @Transient
    private PosOrderDetail posOrderDetail;

    public PosOrderDetail getPosOrderDetail() {
        return posOrderDetail;
    }

    public void setPosOrderDetail(PosOrderDetail posOrderDetail) {
        this.posOrderDetail = posOrderDetail;
    }

    @Generated(hash = 1670565322)
    public PolicyMoneyDetail(Long id, String promotion_money_no, int promotion_money_detail_num,
            int item_num, float promotion_money_detail_special_price,
            float promotion_money_detail_amount_limit, String system_book_code, int branch_num) {
        this.id = id;
        this.promotion_money_no = promotion_money_no;
        this.promotion_money_detail_num = promotion_money_detail_num;
        this.item_num = item_num;
        this.promotion_money_detail_special_price = promotion_money_detail_special_price;
        this.promotion_money_detail_amount_limit = promotion_money_detail_amount_limit;
        this.system_book_code = system_book_code;
        this.branch_num = branch_num;
    }

    @Generated(hash = 1593282097)
    public PolicyMoneyDetail() {
    }

    public String getPromotion_money_no() {
        return promotion_money_no;
    }

    public void setPromotion_money_no(String promotion_money_no) {
        this.promotion_money_no = promotion_money_no;
    }

    public int getPromotion_money_detail_num() {
        return promotion_money_detail_num;
    }

    public void setPromotion_money_detail_num(int promotion_money_detail_num) {
        this.promotion_money_detail_num = promotion_money_detail_num;
    }

    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }

    public float getPromotion_money_detail_special_price() {
        return promotion_money_detail_special_price;
    }

    public void setPromotion_money_detail_special_price(float promotion_money_detail_special_price) {
        this.promotion_money_detail_special_price = promotion_money_detail_special_price;
    }

    public float getPromotion_money_detail_amount_limit() {
        return promotion_money_detail_amount_limit;
    }

    public void setPromotion_money_detail_amount_limit(float promotion_money_detail_amount_limit) {
        this.promotion_money_detail_amount_limit = promotion_money_detail_amount_limit;
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
}
