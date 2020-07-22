package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class StoreHouse {

    /**
     *
     * 获取所有门店仓库
     *
     * storehouse_num : 444400017
     * branch_num : 1
     * storehouse_code : 0021
     * storehouse_name : dd
     * storehouse_actived : false
     * storehouse_center_tag : false
     */

    @Id
    @Property(nameInDb = "STOREHOUSE_NUM")
    private Long storehouse_num;
    private int branch_num;
    private String storehouse_code;
    private String storehouse_name;
    private boolean storehouse_actived;
    private boolean storehouse_center_tag;
    @Generated(hash = 759232881)
    public StoreHouse(Long storehouse_num, int branch_num, String storehouse_code,
            String storehouse_name, boolean storehouse_actived,
            boolean storehouse_center_tag) {
        this.storehouse_num = storehouse_num;
        this.branch_num = branch_num;
        this.storehouse_code = storehouse_code;
        this.storehouse_name = storehouse_name;
        this.storehouse_actived = storehouse_actived;
        this.storehouse_center_tag = storehouse_center_tag;
    }
    @Generated(hash = 2046432707)
    public StoreHouse() {
    }
    public Long getStorehouse_num() {
        return this.storehouse_num;
    }
    public void setStorehouse_num(Long storehouse_num) {
        this.storehouse_num = storehouse_num;
    }
    public int getBranch_num() {
        return this.branch_num;
    }
    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }
    public String getStorehouse_code() {
        return this.storehouse_code;
    }
    public void setStorehouse_code(String storehouse_code) {
        this.storehouse_code = storehouse_code;
    }
    public String getStorehouse_name() {
        return this.storehouse_name;
    }
    public void setStorehouse_name(String storehouse_name) {
        this.storehouse_name = storehouse_name;
    }
    public boolean getStorehouse_actived() {
        return this.storehouse_actived;
    }
    public void setStorehouse_actived(boolean storehouse_actived) {
        this.storehouse_actived = storehouse_actived;
    }
    public boolean getStorehouse_center_tag() {
        return this.storehouse_center_tag;
    }
    public void setStorehouse_center_tag(boolean storehouse_center_tag) {
        this.storehouse_center_tag = storehouse_center_tag;
    }

}
