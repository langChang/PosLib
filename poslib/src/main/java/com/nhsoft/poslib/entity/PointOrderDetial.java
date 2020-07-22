package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

@Entity
public class PointOrderDetial {

    /***
     *
     "branch_num": 0,
     "item_num": 0,
     "point_order_detail_amount": 0,
     "point_order_detail_item_code": "string",
     "point_order_detail_item_name": "string",
     "point_order_detail_item_spec": "string",
     "point_order_detail_item_unit": "string",
     "point_order_detail_num": 0,
     "point_order_detail_point": 0,
     "point_order_fid": "string",
     "system_book_code": "string"
     */
    @Id(autoincrement = true)
    private Long id;
    private String point_order_fid;
    private int point_order_detail_num;
    private int item_num;
    private String point_order_detail_item_code;
    private String point_order_detail_item_name;
    private String point_order_detail_item_spec;
    private String point_order_detail_item_unit;
    private float point_order_detail_point;
    private float point_order_detail_amount;
    private String system_book_code;
    private int branch_num;
    private Float consume_point_price;
    @Transient
    private int amount=1 ;


    @Generated(hash = 980005968)
    public PointOrderDetial(Long id, String point_order_fid,
            int point_order_detail_num, int item_num,
            String point_order_detail_item_code,
            String point_order_detail_item_name,
            String point_order_detail_item_spec,
            String point_order_detail_item_unit, float point_order_detail_point,
            float point_order_detail_amount, String system_book_code,
            int branch_num, Float consume_point_price) {
        this.id = id;
        this.point_order_fid = point_order_fid;
        this.point_order_detail_num = point_order_detail_num;
        this.item_num = item_num;
        this.point_order_detail_item_code = point_order_detail_item_code;
        this.point_order_detail_item_name = point_order_detail_item_name;
        this.point_order_detail_item_spec = point_order_detail_item_spec;
        this.point_order_detail_item_unit = point_order_detail_item_unit;
        this.point_order_detail_point = point_order_detail_point;
        this.point_order_detail_amount = point_order_detail_amount;
        this.system_book_code = system_book_code;
        this.branch_num = branch_num;
        this.consume_point_price = consume_point_price;
    }
    @Generated(hash = 2082434089)
    public PointOrderDetial() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPoint_order_fid() {
        return this.point_order_fid;
    }
    public void setPoint_order_fid(String point_order_fid) {
        this.point_order_fid = point_order_fid;
    }
    public int getPoint_order_detail_num() {
        return this.point_order_detail_num;
    }
    public void setPoint_order_detail_num(int point_order_detail_num) {
        this.point_order_detail_num = point_order_detail_num;
    }
    public int getItem_num() {
        return this.item_num;
    }
    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }
    public String getPoint_order_detail_item_code() {
        return this.point_order_detail_item_code;
    }
    public void setPoint_order_detail_item_code(
            String point_order_detail_item_code) {
        this.point_order_detail_item_code = point_order_detail_item_code;
    }
    public String getPoint_order_detail_item_name() {
        return this.point_order_detail_item_name;
    }
    public void setPoint_order_detail_item_name(
            String point_order_detail_item_name) {
        this.point_order_detail_item_name = point_order_detail_item_name;
    }
    public String getPoint_order_detail_item_spec() {
        return this.point_order_detail_item_spec;
    }
    public void setPoint_order_detail_item_spec(
            String point_order_detail_item_spec) {
        this.point_order_detail_item_spec = point_order_detail_item_spec;
    }
    public String getPoint_order_detail_item_unit() {
        return this.point_order_detail_item_unit;
    }
    public void setPoint_order_detail_item_unit(
            String point_order_detail_item_unit) {
        this.point_order_detail_item_unit = point_order_detail_item_unit;
    }
    public float getPoint_order_detail_point() {
        return this.point_order_detail_point;
    }
    public void setPoint_order_detail_point(float point_order_detail_point) {
        this.point_order_detail_point = point_order_detail_point;
    }
    public float getPoint_order_detail_amount() {
        return this.point_order_detail_amount;
    }
    public void setPoint_order_detail_amount(float point_order_detail_amount) {
        this.point_order_detail_amount = point_order_detail_amount;
    }
    public String getSystem_book_code() {
        return this.system_book_code;
    }
    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }
    public int getBranch_num() {
        return this.branch_num;
    }
    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }
    public Float getConsume_point_price() {
        return this.consume_point_price;
    }
    public void setConsume_point_price(Float consume_point_price) {
        this.consume_point_price = consume_point_price;
    }
    
}
