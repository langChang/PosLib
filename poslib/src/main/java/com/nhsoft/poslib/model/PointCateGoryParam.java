package com.nhsoft.poslib.model;

/**
 * Created by Iverson on 2020/8/26 4:16 PM
 * 此类用于：
 */
public class PointCateGoryParam {

    /**
     * category_code : string
     * category_name : string
     * consume_money : 0
     * point_value : 0
     */

    private String category_code;
    private String category_name;
    private float    consume_money;
    private float    point_value;

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public float getConsume_money() {
        return consume_money;
    }

    public void setConsume_money(float consume_money) {
        this.consume_money = consume_money;
    }

    public float getPoint_value() {
        return point_value;
    }

    public void setPoint_value(float point_value) {
        this.point_value = point_value;
    }
}
