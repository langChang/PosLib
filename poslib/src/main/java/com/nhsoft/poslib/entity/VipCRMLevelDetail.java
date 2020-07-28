package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class VipCRMLevelDetail implements Cloneable{


    /**
     * days : 0
     * id : 0
     * kind : string
     * real_price : 0
     */
    private int days;
    @Id
    private Long id;
    private String kind;
    private float real_price;
    private Long vipCRMLevelId;


    @Generated(hash = 425425667)
    public VipCRMLevelDetail(int days, Long id, String kind, float real_price,
            Long vipCRMLevelId) {
        this.days = days;
        this.id = id;
        this.kind = kind;
        this.real_price = real_price;
        this.vipCRMLevelId = vipCRMLevelId;
    }

    @Generated(hash = 1975732281)
    public VipCRMLevelDetail() {
    }


    public Long getVipCRMLevelId() {
        return vipCRMLevelId;
    }

    public void setVipCRMLevelId(Long vipCRMLevelId) {
        this.vipCRMLevelId = vipCRMLevelId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getReal_price() {
        return this.real_price;
    }

    public void setReal_price(float real_price) {
        this.real_price = real_price;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
