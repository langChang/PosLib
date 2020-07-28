package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 积分设置 例如 积分转储值 xx积分 转 xx 元
 * @Author ADMIN
 * @time 2020-04-21 18:02 
 */


public class VipCrmPoint {

    private VipCrmPointRate point_rule;

    public VipCrmPointRate getPoint_rule() {
        return point_rule;
    }

    public void setPoint_rule(VipCrmPointRate point_rule) {
        this.point_rule = point_rule;
    }
}
