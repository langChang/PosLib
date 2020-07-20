package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by Iverson on 2018/11/30 9:20 AM
 * 此类用于：
 */

@Entity
public class KeyGeneratorBizday {

    @Id
    private Long id;
    private String systemBookCode;
    private int branchNum;
    private String shiftTableBizday;
    private String keyItem;
    private int keyValue;
    @Transient
    private String KeyGBString;

    public String getKeyGBString() {
        return KeyGBString;
    }

    public void setKeyGBString(String keyGBString) {
        KeyGBString = keyGBString;
    }

    @Generated(hash = 1199882664)
    public KeyGeneratorBizday(Long id, String systemBookCode, int branchNum,
            String shiftTableBizday, String keyItem, int keyValue) {
        this.id = id;
        this.systemBookCode = systemBookCode;
        this.branchNum = branchNum;
        this.shiftTableBizday = shiftTableBizday;
        this.keyItem = keyItem;
        this.keyValue = keyValue;
    }
    @Generated(hash = 843599568)
    public KeyGeneratorBizday() {
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
    public int getBranchNum() {
        return this.branchNum;
    }
    public void setBranchNum(int branchNum) {
        this.branchNum = branchNum;
    }
    public String getShiftTableBizday() {
        return this.shiftTableBizday;
    }
    public void setShiftTableBizday(String shiftTableBizday) {
        this.shiftTableBizday = shiftTableBizday;
    }
    public String getKeyItem() {
        return this.keyItem;
    }
    public void setKeyItem(String keyItem) {
        this.keyItem = keyItem;
    }
    public int getKeyValue() {
        return this.keyValue;
    }
    public void setKeyValue(int keyValue) {
        this.keyValue = keyValue;
    }

}
