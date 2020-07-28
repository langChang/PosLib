package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 会员等级对应的积分规则
 * @Author ADMIN
 * @time 2020-04-07 10:10 
 */

@Entity
public class VipLevelPointRule {


    /**
     * rule_id : 208501000010
     * rule_name : 卡存款积分
     * rule_creator : 管理员
     * rule_create_time : 2019-11-26 14:12:40
     * rule_type : 卡储值积分
     * rule_money : 50
     * rule_value : 1
     * discount_item_no_point : false
     */

    @Id
    private Long id;//对应会员等级id
    private long rule_id;
    private String rule_name;
    private String rule_creator;
    private String rule_create_time;
    private String rule_type;
    private float rule_money;
    private int rule_value;
    private boolean discount_item_no_point;


    @Generated(hash = 707938530)
    public VipLevelPointRule(Long id, long rule_id, String rule_name,
            String rule_creator, String rule_create_time, String rule_type,
            float rule_money, int rule_value, boolean discount_item_no_point) {
        this.id = id;
        this.rule_id = rule_id;
        this.rule_name = rule_name;
        this.rule_creator = rule_creator;
        this.rule_create_time = rule_create_time;
        this.rule_type = rule_type;
        this.rule_money = rule_money;
        this.rule_value = rule_value;
        this.discount_item_no_point = discount_item_no_point;
    }

    @Generated(hash = 1932268623)
    public VipLevelPointRule() {
    }

    

    public long getRule_id() {
        return rule_id;
    }

    public void setRule_id(long rule_id) {
        this.rule_id = rule_id;
    }

    public float getRule_money() {
        return rule_money;
    }

    public void setRule_money(float rule_money) {
        this.rule_money = rule_money;
    }

    public String getRule_name() {
        return rule_name;
    }

    public void setRule_name(String rule_name) {
        this.rule_name = rule_name;
    }

    public String getRule_creator() {
        return rule_creator;
    }

    public void setRule_creator(String rule_creator) {
        this.rule_creator = rule_creator;
    }

    public String getRule_create_time() {
        return rule_create_time;
    }

    public void setRule_create_time(String rule_create_time) {
        this.rule_create_time = rule_create_time;
    }

    public String getRule_type() {
        return rule_type;
    }

    public void setRule_type(String rule_type) {
        this.rule_type = rule_type;
    }


    public int getRule_value() {
        return rule_value;
    }

    public void setRule_value(int rule_value) {
        this.rule_value = rule_value;
    }

    public boolean isDiscount_item_no_point() {
        return discount_item_no_point;
    }

    public void setDiscount_item_no_point(boolean discount_item_no_point) {
        this.discount_item_no_point = discount_item_no_point;
    }

    public boolean getDiscount_item_no_point() {
        return this.discount_item_no_point;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}
