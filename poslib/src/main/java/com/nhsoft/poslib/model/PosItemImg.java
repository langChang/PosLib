package com.nhsoft.poslib.model;

/**
 * Created by Iverson on 2019-08-23 10:59
 * 此类用于：
 */
public class PosItemImg {

    /**
     * item_num : 0
     * pos_image_default : true
     * pos_image_file_size : 0
     * pos_image_height : 0
     * pos_image_type : string
     * pos_image_url : string
     * pos_image_width : 0
     */

    private int item_num;
    private boolean pos_image_default;
    private int     pos_image_file_size;
    private int     pos_image_height;
    private String  pos_image_type;
    private String  pos_image_url;
    private int     pos_image_width;

    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }

    public boolean isPos_image_default() {
        return pos_image_default;
    }

    public void setPos_image_default(boolean pos_image_default) {
        this.pos_image_default = pos_image_default;
    }

    public int getPos_image_file_size() {
        return pos_image_file_size;
    }

    public void setPos_image_file_size(int pos_image_file_size) {
        this.pos_image_file_size = pos_image_file_size;
    }

    public int getPos_image_height() {
        return pos_image_height;
    }

    public void setPos_image_height(int pos_image_height) {
        this.pos_image_height = pos_image_height;
    }

    public String getPos_image_type() {
        return pos_image_type;
    }

    public void setPos_image_type(String pos_image_type) {
        this.pos_image_type = pos_image_type;
    }

    public String getPos_image_url() {
        return pos_image_url;
    }

    public void setPos_image_url(String pos_image_url) {
        this.pos_image_url = pos_image_url;
    }

    public int getPos_image_width() {
        return pos_image_width;
    }

    public void setPos_image_width(int pos_image_width) {
        this.pos_image_width = pos_image_width;
    }
}
