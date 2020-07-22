package com.nhsoft.poslib.entity.new_nong_mao;


import com.nhsoft.poslib.model.PosItemImg;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * 新农贸商品档案
 */
@Entity
public class PosItemNewNongMao implements Cloneable{


    /**
     * branch_num : 0
     * item_barcode : string
     * item_category : string
     * item_code : string
     * item_create_time : string
     * item_creator : string
     * item_del_tag : true
     * item_id : 0
     * item_last_edit_time : string
     * item_member_price : 0
     * item_name : string
     * item_num : 0
     * item_pinyin : string
     * item_regular_price : 0
     * item_unit : string
     * item_weight_flag : true
     * merchant_num : 0
     */

    @Id
    @Property(nameInDb = "ITEM_NUM")
    private Long item_num;
    private int branch_num;
    private String item_barcode;
    private String item_category;
    private String item_code;
    private String item_create_time;
    private String item_creator;
    private boolean item_del_tag;
    private long item_id;
    private String item_last_edit_time;
    private float item_member_price;
    private String item_name;

    private String item_pinyin;
    private float item_regular_price;
    private String item_unit;
    private boolean item_weight_flag;
    private int merchant_num;

    private String           pos_images_json;
    @Transient
    private List<PosItemImg> pos_images;

    public String getPos_images_json() {
        return pos_images_json;
    }

    public void setPos_images_json(String pos_images_json) {
        this.pos_images_json = pos_images_json;
    }

    public List<PosItemImg> getPos_Images() {
        return pos_images;
    }

    public void setPos_Images(List<PosItemImg> stalls) {
        this.pos_images = stalls;
    }



    @Generated(hash = 657233678)
    public PosItemNewNongMao(Long item_num, int branch_num, String item_barcode,
            String item_category, String item_code, String item_create_time,
            String item_creator, boolean item_del_tag, long item_id,
            String item_last_edit_time, float item_member_price, String item_name,
            String item_pinyin, float item_regular_price, String item_unit,
            boolean item_weight_flag, int merchant_num, String pos_images_json) {
        this.item_num = item_num;
        this.branch_num = branch_num;
        this.item_barcode = item_barcode;
        this.item_category = item_category;
        this.item_code = item_code;
        this.item_create_time = item_create_time;
        this.item_creator = item_creator;
        this.item_del_tag = item_del_tag;
        this.item_id = item_id;
        this.item_last_edit_time = item_last_edit_time;
        this.item_member_price = item_member_price;
        this.item_name = item_name;
        this.item_pinyin = item_pinyin;
        this.item_regular_price = item_regular_price;
        this.item_unit = item_unit;
        this.item_weight_flag = item_weight_flag;
        this.merchant_num = merchant_num;
        this.pos_images_json = pos_images_json;
    }

    @Generated(hash = 77066223)
    public PosItemNewNongMao() {
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public String getItem_barcode() {
        return item_barcode;
    }

    public void setItem_barcode(String item_barcode) {
        this.item_barcode = item_barcode;
    }

    public String getItem_category() {
        return item_category;
    }

    public void setItem_category(String item_category) {
        this.item_category = item_category;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_create_time() {
        return item_create_time;
    }

    public void setItem_create_time(String item_create_time) {
        this.item_create_time = item_create_time;
    }

    public String getItem_creator() {
        return item_creator;
    }

    public void setItem_creator(String item_creator) {
        this.item_creator = item_creator;
    }

    public boolean isItem_del_tag() {
        return item_del_tag;
    }

    public void setItem_del_tag(boolean item_del_tag) {
        this.item_del_tag = item_del_tag;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public String getItem_last_edit_time() {
        return item_last_edit_time;
    }

    public void setItem_last_edit_time(String item_last_edit_time) {
        this.item_last_edit_time = item_last_edit_time;
    }

    public float getItem_member_price() {
        return item_member_price;
    }

    public void setItem_member_price(float item_member_price) {
        this.item_member_price = item_member_price;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Long getItem_num() {
        return item_num;
    }

    public void setItem_num(Long item_num) {
        this.item_num = item_num;
    }

    public String getItem_pinyin() {
        return item_pinyin;
    }

    public void setItem_pinyin(String item_pinyin) {
        this.item_pinyin = item_pinyin;
    }

    public float getItem_regular_price() {
        return item_regular_price;
    }

    public void setItem_regular_price(float item_regular_price) {
        this.item_regular_price = item_regular_price;
    }

    public String getItem_unit() {
        return item_unit;
    }

    public void setItem_unit(String item_unit) {
        this.item_unit = item_unit;
    }

    public boolean isItem_weight_flag() {
        return item_weight_flag;
    }

    public void setItem_weight_flag(boolean item_weight_flag) {
        this.item_weight_flag = item_weight_flag;
    }

    public int getMerchant_num() {
        return merchant_num;
    }

    public void setMerchant_num(int merchant_num) {
        this.merchant_num = merchant_num;
    }

    public boolean getItem_del_tag() {
        return this.item_del_tag;
    }

    public boolean getItem_weight_flag() {
        return this.item_weight_flag;
    }
}
