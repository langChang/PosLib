package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Iverson on 2018/11/14 2:16 PM
 * 此类用于：
 */
@Entity
public class PosItemGrade {


    /**
     * item_grade_num : 650
     * item_num : 444401158
     * item_grade_code : 00197
     * item_grade_barcode :
     * item_grade_name : 1
     * item_grade_discounted : true
     * item_grade_pinyin : 1
     * branch_grade_regular_price : 0.0
     * branch_grade_level2_price : 0.0
     * branch_grade_level3_price : 0.0
     * branch_grade_level4_price : 0.0
     */
    @Id(autoincrement = true)
    private Long id;
    private Long     item_num;
    private int item_grade_num;
    private String  item_grade_code;
    private String  item_grade_barcode;
    private String  item_grade_name;
    private boolean item_grade_discounted;
    private String  item_grade_pinyin;
    private float  branch_grade_regular_price;
    private float  branch_grade_level2_price;
    private float  branch_grade_level3_price;
    private float  branch_grade_level4_price;
    private Float item_grade_regular_price;
    private Float item_grade_level2_price;
    private Float item_grade_level3_price;
    private Float item_grade_level4_price;
    private Float item_grade_max_price;
    private Float item_grade_min_price;


    public Float getItem_grade_regular_price() {
        return item_grade_regular_price == null ? 0 : item_grade_regular_price;
    }

    public void setItem_grade_regular_price(Float item_grade_regular_price) {
        this.item_grade_regular_price = item_grade_regular_price;
    }

    public Float getItem_grade_level2_price() {
        return item_grade_level2_price == null ? 0 : item_grade_level2_price;
    }

    public void setItem_grade_level2_price(Float item_grade_level2_price) {
        this.item_grade_level2_price = item_grade_level2_price;
    }

    public Float getItem_grade_level3_price() {
        return item_grade_level3_price == null ? 0 : item_grade_level3_price;
    }

    public void setItem_grade_level3_price(Float item_grade_level3_price) {
        this.item_grade_level3_price = item_grade_level3_price;
    }

    public Float getItem_grade_level4_price() {
        return item_grade_level4_price == null ? 0 : item_grade_level4_price ;
    }

    public void setItem_grade_level4_price(Float item_grade_level4_price) {
        this.item_grade_level4_price = item_grade_level4_price;
    }

    private boolean item_grade_sale_cease_flag;
    private int item_grade_type;
    private Boolean pos_item_selected;//终端商品设置的时候判断该分级item是否选中默认false

    public int getItem_grade_type() {
        return item_grade_type;
    }

    public void setItem_grade_type(int item_grade_type) {
        this.item_grade_type = item_grade_type;
    }



    @Generated(hash = 938542853)
    public PosItemGrade(Long id, Long item_num, int item_grade_num,
            String item_grade_code, String item_grade_barcode,
            String item_grade_name, boolean item_grade_discounted,
            String item_grade_pinyin, float branch_grade_regular_price,
            float branch_grade_level2_price, float branch_grade_level3_price,
            float branch_grade_level4_price, Float item_grade_regular_price,
            Float item_grade_level2_price, Float item_grade_level3_price,
            Float item_grade_level4_price, Float item_grade_max_price,
            Float item_grade_min_price, boolean item_grade_sale_cease_flag,
            int item_grade_type, Boolean pos_item_selected) {
        this.id = id;
        this.item_num = item_num;
        this.item_grade_num = item_grade_num;
        this.item_grade_code = item_grade_code;
        this.item_grade_barcode = item_grade_barcode;
        this.item_grade_name = item_grade_name;
        this.item_grade_discounted = item_grade_discounted;
        this.item_grade_pinyin = item_grade_pinyin;
        this.branch_grade_regular_price = branch_grade_regular_price;
        this.branch_grade_level2_price = branch_grade_level2_price;
        this.branch_grade_level3_price = branch_grade_level3_price;
        this.branch_grade_level4_price = branch_grade_level4_price;
        this.item_grade_regular_price = item_grade_regular_price;
        this.item_grade_level2_price = item_grade_level2_price;
        this.item_grade_level3_price = item_grade_level3_price;
        this.item_grade_level4_price = item_grade_level4_price;
        this.item_grade_max_price = item_grade_max_price;
        this.item_grade_min_price = item_grade_min_price;
        this.item_grade_sale_cease_flag = item_grade_sale_cease_flag;
        this.item_grade_type = item_grade_type;
        this.pos_item_selected = pos_item_selected;
    }

    @Generated(hash = 1455349954)
    public PosItemGrade() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getItem_num() {
        return this.item_num;
    }
    public void setItem_num(Long item_num) {
        this.item_num = item_num;
    }
    public int getItem_grade_num() {
        return this.item_grade_num;
    }
    public void setItem_grade_num(int item_grade_num) {
        this.item_grade_num = item_grade_num;
    }
    public String getItem_grade_code() {
        return this.item_grade_code;
    }
    public void setItem_grade_code(String item_grade_code) {
        this.item_grade_code = item_grade_code;
    }
    public String getItem_grade_barcode() {
        return this.item_grade_barcode;
    }
    public void setItem_grade_barcode(String item_grade_barcode) {
        this.item_grade_barcode = item_grade_barcode;
    }
    public String getItem_grade_name() {
        return this.item_grade_name;
    }
    public void setItem_grade_name(String item_grade_name) {
        this.item_grade_name = item_grade_name;
    }
    public boolean getItem_grade_discounted() {
        return this.item_grade_discounted;
    }
    public void setItem_grade_discounted(boolean item_grade_discounted) {
        this.item_grade_discounted = item_grade_discounted;
    }
    public String getItem_grade_pinyin() {
        return this.item_grade_pinyin;
    }
    public void setItem_grade_pinyin(String item_grade_pinyin) {
        this.item_grade_pinyin = item_grade_pinyin;
    }
    public float getBranch_grade_regular_price() {
        return this.branch_grade_regular_price;
    }
    public void setBranch_grade_regular_price(float branch_grade_regular_price) {
        this.branch_grade_regular_price = branch_grade_regular_price;
    }
    public float getBranch_grade_level2_price() {
        return this.branch_grade_level2_price;
    }
    public void setBranch_grade_level2_price(float branch_grade_level2_price) {
        this.branch_grade_level2_price = branch_grade_level2_price;
    }
    public float getBranch_grade_level3_price() {
        return this.branch_grade_level3_price;
    }
    public void setBranch_grade_level3_price(float branch_grade_level3_price) {
        this.branch_grade_level3_price = branch_grade_level3_price;
    }
    public float getBranch_grade_level4_price() {
        return this.branch_grade_level4_price;
    }
    public void setBranch_grade_level4_price(float branch_grade_level4_price) {
        this.branch_grade_level4_price = branch_grade_level4_price;
    }

    public boolean isItem_grade_sale_cease_flag() {
        return item_grade_sale_cease_flag;
    }

    public void setItem_grade_sale_cease_flag(boolean item_grade_sale_cease_flag) {
        this.item_grade_sale_cease_flag = item_grade_sale_cease_flag;
    }
    public boolean getItem_grade_sale_cease_flag() {
        return this.item_grade_sale_cease_flag;
    }

    public Boolean getPos_item_selected() {
        return this.pos_item_selected==null?false:this.pos_item_selected;
    }

    public void setPos_item_selected(Boolean pos_item_selected) {
        this.pos_item_selected = pos_item_selected;
    }

    public Float getItem_grade_max_price() {
        return this.item_grade_max_price;
    }

    public void setItem_grade_max_price(Float item_grade_max_price) {
        this.item_grade_max_price = item_grade_max_price;
    }

    public Float getItem_grade_min_price() {
        return this.item_grade_min_price;
    }

    public void setItem_grade_min_price(Float item_grade_min_price) {
        this.item_grade_min_price = item_grade_min_price;
    }
   
}
