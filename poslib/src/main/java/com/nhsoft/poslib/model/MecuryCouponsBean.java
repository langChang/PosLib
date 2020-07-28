package com.nhsoft.poslib.model;

/**
 * Created by Iverson on 2020/3/4 3:43 PM
 * 此类用于：
 */
public class MecuryCouponsBean {


    /**
     * id : GIFT_CARD
     * book_code : 2072
     * number : 90666251372017812898
     * coupon_lower_limit_amount : 0.0
     * coupon_discount_amount : 1.0
     * coupon_start_time : 2019-06-11T00:00:00.000+08:00
     * coupon_end_time : 2019-06-13T00:00:00.000+08:00
     * logo_url : null
     * brand_name : 蜂巢7号
     * title : 小面额
     */

    private String id;
    private String book_code;
    private String number;
    private String coupon_lower_limit_amount;
    private String coupon_discount_amount;
    private String coupon_start_time;
    private String coupon_end_time;
    private String logo_url;
    private String brand_name;
    private String title;
    private boolean active;
    private boolean used;
    private double coupon_discount_percent_value;
    private double coupon_discount_percent_maximum;
    private boolean coupon_discount_percent_limit_boolean;

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getLogo_url(){
        return logo_url;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBook_code() {
        return book_code;
    }

    public void setBook_code(String book_code) {
        this.book_code = book_code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCoupon_lower_limit_amount() {
        return coupon_lower_limit_amount;
    }

    public void setCoupon_lower_limit_amount(String coupon_lower_limit_amount) {
        this.coupon_lower_limit_amount = coupon_lower_limit_amount;
    }

    public String getCoupon_discount_amount() {
        return coupon_discount_amount;
    }

    public void setCoupon_discount_amount(String coupon_discount_amount) {
        this.coupon_discount_amount = coupon_discount_amount;
    }

    public String getCoupon_start_time() {
        return coupon_start_time;
    }

    public void setCoupon_start_time(String coupon_start_time) {
        this.coupon_start_time = coupon_start_time;
    }

    public String getCoupon_end_time() {
        return coupon_end_time;
    }

    public void setCoupon_end_time(String coupon_end_time) {
        this.coupon_end_time = coupon_end_time;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCoupon_discount_percent_value() {
        return coupon_discount_percent_value;
    }

    public void setCoupon_discount_percent_value(double coupon_discount_percent_value) {
        this.coupon_discount_percent_value = coupon_discount_percent_value;
    }

    public double getCoupon_discount_percent_maximum() {
        return coupon_discount_percent_maximum;
    }

    public void setCoupon_discount_percent_maximum(double coupon_discount_percent_maximum) {
        this.coupon_discount_percent_maximum = coupon_discount_percent_maximum;
    }

    public boolean isCoupon_discount_percent_limit_boolean() {
        return coupon_discount_percent_limit_boolean;
    }

    public void setCoupon_discount_percent_limit_boolean(boolean coupon_discount_percent_limit_boolean) {
        this.coupon_discount_percent_limit_boolean = coupon_discount_percent_limit_boolean;
    }
}
