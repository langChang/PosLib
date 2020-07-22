package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Iverson on 2018/11/16 10:54 AM
 * 此类用于：
 */
@Entity
public class PointPolicyDetail {

    /**
     * point_policy_id : 44440000002
     * item_num : 444400358
     */
    @Id(autoincrement = true)
    private Long id;
    private String point_policy_id;
    private int item_num;
    private float policy_promotion_detail_std_price;
    private float policy_promotion_detail_special_price;
    @Generated(hash = 588728664)
    public PointPolicyDetail(Long id, String point_policy_id, int item_num,
            float policy_promotion_detail_std_price,
            float policy_promotion_detail_special_price) {
        this.id = id;
        this.point_policy_id = point_policy_id;
        this.item_num = item_num;
        this.policy_promotion_detail_std_price = policy_promotion_detail_std_price;
        this.policy_promotion_detail_special_price = policy_promotion_detail_special_price;
    }
    @Generated(hash = 1390752576)
    public PointPolicyDetail() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPoint_policy_id() {
        return this.point_policy_id;
    }
    public void setPoint_policy_id(String point_policy_id) {
        this.point_policy_id = point_policy_id;
    }
    public int getItem_num() {
        return this.item_num;
    }
    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }
    public float getPolicy_promotion_detail_std_price() {
        return this.policy_promotion_detail_std_price;
    }
    public void setPolicy_promotion_detail_std_price(
            float policy_promotion_detail_std_price) {
        this.policy_promotion_detail_std_price = policy_promotion_detail_std_price;
    }
    public float getPolicy_promotion_detail_special_price() {
        return this.policy_promotion_detail_special_price;
    }
    public void setPolicy_promotion_detail_special_price(
            float policy_promotion_detail_special_price) {
        this.policy_promotion_detail_special_price = policy_promotion_detail_special_price;
    }
}
