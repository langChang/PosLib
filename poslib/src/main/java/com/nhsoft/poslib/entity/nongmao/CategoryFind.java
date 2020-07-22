package com.nhsoft.poslib.entity.nongmao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 农贸
 * 商户商品类别
 */
@Entity
public class CategoryFind {


    /**
     * branch_num : 0
     * merchant_num : 0
     * stall_item_category_code : string
     * stall_item_category_name : string
     * stall_item_category_num : 0
     * stall_item_category_rate : 0
     * stall_item_category_sequence : 0
     * stall_num : 0
     * system_book_code : string
     */
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;
    private int branch_num;
    private int    merchant_num;
    private String stall_item_category_code;
    private String stall_item_category_name;
    private String    stall_item_category_num;
    private float    stall_item_category_rate;
    private int    stall_item_category_sequence;
    private int    stall_num;
    private String system_book_code;




    @Generated(hash = 754561796)
    public CategoryFind(Long id, int branch_num, int merchant_num,
            String stall_item_category_code, String stall_item_category_name,
            String stall_item_category_num, float stall_item_category_rate,
            int stall_item_category_sequence, int stall_num,
            String system_book_code) {
        this.id = id;
        this.branch_num = branch_num;
        this.merchant_num = merchant_num;
        this.stall_item_category_code = stall_item_category_code;
        this.stall_item_category_name = stall_item_category_name;
        this.stall_item_category_num = stall_item_category_num;
        this.stall_item_category_rate = stall_item_category_rate;
        this.stall_item_category_sequence = stall_item_category_sequence;
        this.stall_num = stall_num;
        this.system_book_code = system_book_code;
    }

    @Generated(hash = 1623697863)
    public CategoryFind() {
    }




    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public int getMerchant_num() {
        return merchant_num;
    }

    public void setMerchant_num(int merchant_num) {
        this.merchant_num = merchant_num;
    }

    public String getStall_item_category_code() {
        return stall_item_category_code;
    }

    public void setStall_item_category_code(String stall_item_category_code) {
        this.stall_item_category_code = stall_item_category_code;
    }

    public String getStall_item_category_name() {
        return stall_item_category_name;
    }

    public void setStall_item_category_name(String stall_item_category_name) {
        this.stall_item_category_name = stall_item_category_name;
    }

    
    public int getStall_item_category_sequence() {
        return stall_item_category_sequence;
    }

    public void setStall_item_category_sequence(int stall_item_category_sequence) {
        this.stall_item_category_sequence = stall_item_category_sequence;
    }

    public int getStall_num() {
        return stall_num;
    }

    public void setStall_num(int stall_num) {
        this.stall_num = stall_num;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getStall_item_category_rate() {
        return this.stall_item_category_rate;
    }

    public void setStall_item_category_rate(float stall_item_category_rate) {
        this.stall_item_category_rate = stall_item_category_rate;
    }

    public String getStall_item_category_num() {
        return this.stall_item_category_num;
    }

    public void setStall_item_category_num(String stall_item_category_num) {
        this.stall_item_category_num = stall_item_category_num;
    }
}
