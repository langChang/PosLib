package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SystemImageQrcode {
    /**
     * 企业logo和二维码下载
     *
     * system_book_code : string
     * system_image_create_time : string
     * system_image_file_size : 0
     * system_image_height : 0
     * system_image_id : string
     * system_image_kind : string
     * system_image_name : string
     * system_image_order : 0
     * system_image_type : string
     * system_image_url : string
     * system_image_width : 0
     */

    @Id(autoincrement = true)
    private Long id;
    private String system_book_code;
    private String system_image_create_time;
    private int system_image_file_size;
    private int system_image_height;
    private String system_image_id;
    private String system_image_kind;
    private String system_image_name;
    private int system_image_order;
    private String system_image_type;
    private String system_image_url;
    private int system_image_width;

    @Generated(hash = 1665725838)
    public SystemImageQrcode(Long id, String system_book_code,
            String system_image_create_time, int system_image_file_size,
            int system_image_height, String system_image_id,
            String system_image_kind, String system_image_name,
            int system_image_order, String system_image_type,
            String system_image_url, int system_image_width) {
        this.id = id;
        this.system_book_code = system_book_code;
        this.system_image_create_time = system_image_create_time;
        this.system_image_file_size = system_image_file_size;
        this.system_image_height = system_image_height;
        this.system_image_id = system_image_id;
        this.system_image_kind = system_image_kind;
        this.system_image_name = system_image_name;
        this.system_image_order = system_image_order;
        this.system_image_type = system_image_type;
        this.system_image_url = system_image_url;
        this.system_image_width = system_image_width;
    }

    @Generated(hash = 2084855579)
    public SystemImageQrcode() {
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public String getSystem_image_create_time() {
        return system_image_create_time;
    }

    public void setSystem_image_create_time(String system_image_create_time) {
        this.system_image_create_time = system_image_create_time;
    }

    public int getSystem_image_file_size() {
        return system_image_file_size;
    }

    public void setSystem_image_file_size(int system_image_file_size) {
        this.system_image_file_size = system_image_file_size;
    }

    public int getSystem_image_height() {
        return system_image_height;
    }

    public void setSystem_image_height(int system_image_height) {
        this.system_image_height = system_image_height;
    }

    public String getSystem_image_id() {
        return system_image_id;
    }

    public void setSystem_image_id(String system_image_id) {
        this.system_image_id = system_image_id;
    }

    public String getSystem_image_kind() {
        return system_image_kind;
    }

    public void setSystem_image_kind(String system_image_kind) {
        this.system_image_kind = system_image_kind;
    }

    public String getSystem_image_name() {
        return system_image_name;
    }

    public void setSystem_image_name(String system_image_name) {
        this.system_image_name = system_image_name;
    }

    public int getSystem_image_order() {
        return system_image_order;
    }

    public void setSystem_image_order(int system_image_order) {
        this.system_image_order = system_image_order;
    }

    public String getSystem_image_type() {
        return system_image_type;
    }

    public void setSystem_image_type(String system_image_type) {
        this.system_image_type = system_image_type;
    }

    public String getSystem_image_url() {
        return system_image_url;
    }

    public void setSystem_image_url(String system_image_url) {
        this.system_image_url = system_image_url;
    }

    public int getSystem_image_width() {
        return system_image_width;
    }

    public void setSystem_image_width(int system_image_width) {
        this.system_image_width = system_image_width;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }





}
