package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class BookResource {
    /*
    获取所有账套资源*

    * "systemBookCode":"4444",
            "bookResourceName":"积分规则",
            "bookResourceParam":"<?xml version="1.0" encoding="GBK"?>
<积分规则表><积分规则 规则名称="按照单品积分计算" 消费金额="0" 积分值="0" 启用="0" 附加设置=""/><积分规则 规则名称="按照消费金额计算" 消费金额="1" 积分值="1" 启用="-1" 附加设置=""><积分类别明细><类别明细 类别代码="67" 类别名称="zms测试" 消费金额="1" 积分值="1"/></积分类别明细></积分规则><积分规则 规则名称="按照消费次数计算" 消费金额="0" 积分值="0" 启用="0" 附加设置=""/><积分规则 规则名称="按照会员卡储值消费计算" 消费金额="0" 积分值="0" 启用="0" 附加设置=""/><积分规则 规则名称="按照卡类型消费金额计算" 消费金额="0" 积分值="0" 启用="0" 附加设置=""/></积分规则表>"*/
    @Id(autoincrement = true)
    private Long id;
    private String systemBookCode;
    private String  bookResourceName;
    private String bookResourceParam;
    @Generated(hash = 1130884913)
    public BookResource(Long id, String systemBookCode, String bookResourceName, String bookResourceParam) {
        this.id = id;
        this.systemBookCode = systemBookCode;
        this.bookResourceName = bookResourceName;
        this.bookResourceParam = bookResourceParam;
    }
    @Generated(hash = 404871528)
    public BookResource() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSystemBookCode() {
        return this.systemBookCode;
    }
    public void setSystemBookCode(String systemBookCode) {
        this.systemBookCode = systemBookCode;
    }
    public String getBookResourceName() {
        return this.bookResourceName;
    }
    public void setBookResourceName(String bookResourceName) {
        this.bookResourceName = bookResourceName;
    }
    public String getBookResourceParam() {
        return this.bookResourceParam;
    }
    public void setBookResourceParam(String bookResourceParam) {
        this.bookResourceParam = bookResourceParam;
    }


}
