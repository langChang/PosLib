package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class MeasureUnitItem {

    /**
     * item_unit_code : 01
     * item_unit_name : 两
     * item_unit_rate : 1
     * item_unit_type : 固定换算
     * item_unit_default : false
     */
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;
    private String group_name;
    private String item_unit_code;
    private String item_unit_name;
    private double item_unit_rate;
    private String item_unit_type;
    private boolean item_unit_default;
    @Generated(hash = 556497844)
    public MeasureUnitItem(Long id, String group_name, String item_unit_code,
            String item_unit_name, double item_unit_rate, String item_unit_type,
            boolean item_unit_default) {
        this.id = id;
        this.group_name = group_name;
        this.item_unit_code = item_unit_code;
        this.item_unit_name = item_unit_name;
        this.item_unit_rate = item_unit_rate;
        this.item_unit_type = item_unit_type;
        this.item_unit_default = item_unit_default;
    }
    @Generated(hash = 1524548211)
    public MeasureUnitItem() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getGroup_name() {
        return this.group_name;
    }
    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
    public String getItem_unit_code() {
        return this.item_unit_code;
    }
    public void setItem_unit_code(String item_unit_code) {
        this.item_unit_code = item_unit_code;
    }
    public String getItem_unit_name() {
        return this.item_unit_name;
    }
    public void setItem_unit_name(String item_unit_name) {
        this.item_unit_name = item_unit_name;
    }
    public double getItem_unit_rate() {
        return this.item_unit_rate;
    }
    public void setItem_unit_rate(double item_unit_rate) {
        this.item_unit_rate = item_unit_rate;
    }
    public String getItem_unit_type() {
        return this.item_unit_type;
    }
    public void setItem_unit_type(String item_unit_type) {
        this.item_unit_type = item_unit_type;
    }
    public boolean getItem_unit_default() {
        return this.item_unit_default;
    }
    public void setItem_unit_default(boolean item_unit_default) {
        this.item_unit_default = item_unit_default;
    }
   

}
