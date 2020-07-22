package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 积分设置 例如 积分转储值 xx积分 转 xx 元
 * @Author ADMIN
 * @time 2020-04-21 18:02 
 */


@Entity
public class VipCrmPointRate {


    /**
     * check_psw : false
     * point_value : 2
     * money_value : 1
     */

    @Id
    private Long point_rule_id;
    private boolean check_psw;
    private int point_value;
    private float money_value;

    @Generated(hash = 1584598387)
    public VipCrmPointRate(Long point_rule_id, boolean check_psw, int point_value,
            float money_value) {
        this.point_rule_id = point_rule_id;
        this.check_psw = check_psw;
        this.point_value = point_value;
        this.money_value = money_value;
    }

    @Generated(hash = 1992634477)
    public VipCrmPointRate() {
    }

    public boolean isCheck_psw() {
        return check_psw;
    }

    public void setCheck_psw(boolean check_psw) {
        this.check_psw = check_psw;
    }

    public int getPoint_value() {
        return point_value;
    }

    public void setPoint_value(int point_value) {
        this.point_value = point_value;
    }

    public float getMoney_value() {
        return money_value;
    }

    public void setMoney_value(float money_value) {
        this.money_value = money_value;
    }

    public Long getPoint_rule_id() {
        return this.point_rule_id;
    }

    public void setPoint_rule_id(Long point_rule_id) {
        this.point_rule_id = point_rule_id;
    }

    public boolean getCheck_psw() {
        return this.check_psw;
    }
}
