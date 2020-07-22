package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 上传数据库时的 信息
 */
@Entity
public class CurrentUser {

    @Id(autoincrement = true)
    private Long id;
    private String systemBookCode;
    private String shiftTableBizday;
    private Integer shiftTableNum;
    private Integer branchNum;
    private String appUserName;
    private String appUserCode;
    private String createTime;
    private String mac;


    @Generated(hash = 23247398)
    public CurrentUser(Long id, String systemBookCode, String shiftTableBizday,
            Integer shiftTableNum, Integer branchNum, String appUserName,
            String appUserCode, String createTime, String mac) {
        this.id = id;
        this.systemBookCode = systemBookCode;
        this.shiftTableBizday = shiftTableBizday;
        this.shiftTableNum = shiftTableNum;
        this.branchNum = branchNum;
        this.appUserName = appUserName;
        this.appUserCode = appUserCode;
        this.createTime = createTime;
        this.mac = mac;
    }

    @Generated(hash = 1481753967)
    public CurrentUser() {
    }


    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
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
    public String getShiftTableBizday() {
        return this.shiftTableBizday;
    }
    public void setShiftTableBizday(String shiftTableBizday) {
        this.shiftTableBizday = shiftTableBizday;
    }
    public Integer getShiftTableNum() {
        return this.shiftTableNum;
    }
    public void setShiftTableNum(Integer shiftTableNum) {
        this.shiftTableNum = shiftTableNum;
    }
    public Integer getBranchNum() {
        return this.branchNum;
    }
    public void setBranchNum(Integer branchNum) {
        this.branchNum = branchNum;
    }
    public String getAppUserName() {
        return this.appUserName;
    }
    public void setAppUserName(String appUserName) {
        this.appUserName = appUserName;
    }
    public String getAppUserCode() {
        return this.appUserCode;
    }
    public void setAppUserCode(String appUserCode) {
        this.appUserCode = appUserCode;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


}
