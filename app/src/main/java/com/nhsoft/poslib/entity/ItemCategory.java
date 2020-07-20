package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

@Entity
public class ItemCategory {
    /**
     * 获取所有商品类别
     *
     * category_code : 991
     * category_name : 散称零食
     * parent_category_code : 99
     */
    @Id
    private String category_code;
    private String category_name;
    private String parent_category_code;
    private Boolean can_not_sale_no_store_item;

    private Integer hierarchy;//层级
    private String second_category_code;//二级分类的 category_code

    @Transient
    private String parent_category_name;
    @Transient
    private List<PosItem> posItemList;


    @Generated(hash = 1415552065)
    public ItemCategory(String category_code, String category_name,
            String parent_category_code, Boolean can_not_sale_no_store_item,
            Integer hierarchy, String second_category_code) {
        this.category_code = category_code;
        this.category_name = category_name;
        this.parent_category_code = parent_category_code;
        this.can_not_sale_no_store_item = can_not_sale_no_store_item;
        this.hierarchy = hierarchy;
        this.second_category_code = second_category_code;
    }

    @Generated(hash = 1455990123)
    public ItemCategory() {
    }


    public List<PosItem> getPosItemList() {
        return posItemList;
    }

    public void setPosItemList(List<PosItem> posItemList) {
        this.posItemList = posItemList;
    }

    public String getCategory_code() {
        return this.category_code;
    }
    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }
    public String getCategory_name() {
        return this.category_name;
    }
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    public String getParent_category_code() {
        return this.parent_category_code;
    }
    public void setParent_category_code(String parent_category_code) {
        this.parent_category_code = parent_category_code;
    }

    public String getParent_category_name() {
        return parent_category_name;
    }

    public void setParent_category_name(String parent_category_name) {
        this.parent_category_name = parent_category_name;
    }

    public Integer getHierarchy() {
        return this.hierarchy == null ? 0 : this.hierarchy.intValue();
    }

    public void setHierarchy(Integer hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getSecond_category_code() {
        return this.second_category_code;
    }

    public void setSecond_category_code(String second_category_code) {
        this.second_category_code = second_category_code;
    }

    public Boolean getCan_not_sale_no_store_item() {
        return this.can_not_sale_no_store_item == null ? false : can_not_sale_no_store_item;
    }

    public void setCan_not_sale_no_store_item(Boolean can_not_sale_no_store_item) {
        this.can_not_sale_no_store_item = can_not_sale_no_store_item;
    }


}
