package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Iverson on 2018/11/17 11:17 AM
 * 此类用于：
 */
@Entity
public class ManagementTemplateDetail {
    /**
     * item_num : 210100009
     * management_template_num : 417300009
     */
    @Id(autoincrement = true)
    private Long id;
    private int item_num;
    private Long management_template_num;
    @Generated(hash = 107791301)
    public ManagementTemplateDetail(Long id, int item_num,
            Long management_template_num) {
        this.id = id;
        this.item_num = item_num;
        this.management_template_num = management_template_num;
    }
    @Generated(hash = 510666301)
    public ManagementTemplateDetail() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getItem_num() {
        return this.item_num;
    }
    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }
    public Long getManagement_template_num() {
        return this.management_template_num;
    }
    public void setManagement_template_num(Long management_template_num) {
        this.management_template_num = management_template_num;
    }

}
