package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Iverson on 2020/4/8 10:03 AM
 * 此类用于：
 */

@Entity
public class PointRule {

    /**
     * birthday_point_value : 0
     * discount_item_no_point : true
     * enable_birthday_point : true
     * rule_create_time : string
     * rule_creator : string
     * rule_id : 0
     * rule_money : 0
     * rule_name : string
     * rule_type : string
     * rule_value : 0
     */

    @Id(autoincrement = true)
    private Long    id;
    private float   birthday_point_value;
    private boolean discount_item_no_point;
    private boolean enable_birthday_point;
    private String  rule_create_time;
    private String  rule_creator;
    private Long    rule_id;
    private float   rule_money;
    private String  rule_name;
    private String  rule_type;
    private float   rule_value;

    @Generated(hash = 1010394649)
    public PointRule(Long id, float birthday_point_value,
                     boolean discount_item_no_point, boolean enable_birthday_point,
                     String rule_create_time, String rule_creator, Long rule_id,
                     float rule_money, String rule_name, String rule_type,
                     float rule_value) {
        this.id = id;
        this.birthday_point_value = birthday_point_value;
        this.discount_item_no_point = discount_item_no_point;
        this.enable_birthday_point = enable_birthday_point;
        this.rule_create_time = rule_create_time;
        this.rule_creator = rule_creator;
        this.rule_id = rule_id;
        this.rule_money = rule_money;
        this.rule_name = rule_name;
        this.rule_type = rule_type;
        this.rule_value = rule_value;
    }

    @Generated(hash = 408859977)
    public PointRule() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getBirthday_point_value() {
        return this.birthday_point_value;
    }

    public void setBirthday_point_value(float birthday_point_value) {
        this.birthday_point_value = birthday_point_value;
    }

    public boolean getDiscount_item_no_point() {
        return this.discount_item_no_point;
    }

    public void setDiscount_item_no_point(boolean discount_item_no_point) {
        this.discount_item_no_point = discount_item_no_point;
    }

    public boolean getEnable_birthday_point() {
        return this.enable_birthday_point;
    }

    public void setEnable_birthday_point(boolean enable_birthday_point) {
        this.enable_birthday_point = enable_birthday_point;
    }

    public String getRule_create_time() {
        return this.rule_create_time;
    }

    public void setRule_create_time(String rule_create_time) {
        this.rule_create_time = rule_create_time;
    }

    public String getRule_creator() {
        return this.rule_creator;
    }

    public void setRule_creator(String rule_creator) {
        this.rule_creator = rule_creator;
    }

    public Long getRule_id() {
        return this.rule_id;
    }

    public void setRule_id(Long rule_id) {
        this.rule_id = rule_id;
    }

    public float getRule_money() {
        return this.rule_money;
    }

    public void setRule_money(float rule_money) {
        this.rule_money = rule_money;
    }

    public String getRule_name() {
        return this.rule_name;
    }

    public void setRule_name(String rule_name) {
        this.rule_name = rule_name;
    }

    public String getRule_type() {
        return this.rule_type;
    }

    public void setRule_type(String rule_type) {
        this.rule_type = rule_type;
    }

    public float getRule_value() {
        return this.rule_value;
    }

    public void setRule_value(float rule_value) {
        this.rule_value = rule_value;
    }


}
