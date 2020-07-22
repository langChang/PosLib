package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class TableMd5 {
    @Id
    private Long strKey;
    private String strValue;
    @Generated(hash = 1289832765)
    public TableMd5(Long strKey, String strValue) {
        this.strKey = strKey;
        this.strValue = strValue;
    }
    @Generated(hash = 1254696679)
    public TableMd5() {
    }
    public Long getStrKey() {
        return this.strKey;
    }
    public void setStrKey(Long strKey) {
        this.strKey = strKey;
    }
    public String getStrValue() {
        return this.strValue;
    }
    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

}
