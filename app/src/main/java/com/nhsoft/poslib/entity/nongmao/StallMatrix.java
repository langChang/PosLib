package com.nhsoft.poslib.entity.nongmao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 农贸
 * 档口价格下载
 */
@Entity
public class StallMatrix {

    /**
     * branch_num : 0
     * item_grade_num : 0
     * item_num : 0
     * merchant_num : 0
     * stall_matrix_level2_price : 0
     * stall_matrix_num : 0
     * stall_matrix_regular_price : 0
     * stall_num : 0
     * system_book_code : string
     */
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;
    private int branch_num;
    private int    item_grade_num;
    private int    item_num;
    private int    merchant_num;
    private float    stall_matrix_level2_price;
    private int    stall_matrix_num;
    private float    stall_matrix_regular_price;
    private int    stall_num;
    private String system_book_code;


    @Generated(hash = 658114307)
    public StallMatrix(Long id, int branch_num, int item_grade_num, int item_num,
            int merchant_num, float stall_matrix_level2_price, int stall_matrix_num,
            float stall_matrix_regular_price, int stall_num,
            String system_book_code) {
        this.id = id;
        this.branch_num = branch_num;
        this.item_grade_num = item_grade_num;
        this.item_num = item_num;
        this.merchant_num = merchant_num;
        this.stall_matrix_level2_price = stall_matrix_level2_price;
        this.stall_matrix_num = stall_matrix_num;
        this.stall_matrix_regular_price = stall_matrix_regular_price;
        this.stall_num = stall_num;
        this.system_book_code = system_book_code;
    }

    @Generated(hash = 112511316)
    public StallMatrix() {
    }


    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public int getItem_grade_num() {
        return item_grade_num;
    }

    public void setItem_grade_num(int item_grade_num) {
        this.item_grade_num = item_grade_num;
    }

    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }

    public int getMerchant_num() {
        return merchant_num;
    }

    public void setMerchant_num(int merchant_num) {
        this.merchant_num = merchant_num;
    }

    public int getStall_matrix_num() {
        return stall_matrix_num;
    }

    public void setStall_matrix_num(int stall_matrix_num) {
        this.stall_matrix_num = stall_matrix_num;
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

    public float getStall_matrix_level2_price() {
        return this.stall_matrix_level2_price;
    }

    public void setStall_matrix_level2_price(float stall_matrix_level2_price) {
        this.stall_matrix_level2_price = stall_matrix_level2_price;
    }

    public float getStall_matrix_regular_price() {
        return this.stall_matrix_regular_price;
    }

    public void setStall_matrix_regular_price(float stall_matrix_regular_price) {
        this.stall_matrix_regular_price = stall_matrix_regular_price;
    }
}


