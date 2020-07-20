package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TableMd5Entity {
    private String strKey;
    private String strValue;
    @Generated(hash = 2004072270)
    public TableMd5Entity(String strKey, String strValue) {
        this.strKey = strKey;
        this.strValue = strValue;
    }
    @Generated(hash = 1796145613)
    public TableMd5Entity() {
    }
    public String getStrKey() {
        return this.strKey;
    }
    public void setStrKey(String strKey) {
        this.strKey = strKey;
    }
    public String getStrValue() {
        return this.strValue;
    }
    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

}
