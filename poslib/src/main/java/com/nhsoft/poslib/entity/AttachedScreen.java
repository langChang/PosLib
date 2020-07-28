package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class AttachedScreen {

    /**
     * 前台辅屏图片下载
     *
     * system_image_id : fdaa065b4bb44fe5a78e8832f900eaa9
     * system_book_code : 4444
     * system_image_type : .png
     * system_image_order : 1
     * system_image_url : http://image.nhsoft.cn/4444/systemImage/SCREEN_IMAGE/fdaa065b4bb44fe5a78e8832f900eaa9.png
     * system_image_width : 1024
     * system_image_height : 768
     */

    @Id
    @Property(nameInDb = "SYSTEM_IMAGE_ID")
    private String system_image_id;
    private String system_book_code;
    private String system_image_type;
    private int system_image_order;
    private String system_image_url;
    private int system_image_width;
    private int system_image_height;

    @Generated(hash = 1048744866)
    public AttachedScreen(String system_image_id, String system_book_code, String system_image_type, int system_image_order,
            String system_image_url, int system_image_width, int system_image_height) {
        this.system_image_id = system_image_id;
        this.system_book_code = system_book_code;
        this.system_image_type = system_image_type;
        this.system_image_order = system_image_order;
        this.system_image_url = system_image_url;
        this.system_image_width = system_image_width;
        this.system_image_height = system_image_height;
    }

    @Generated(hash = 1491454048)
    public AttachedScreen() {
    }

    public String getSystem_image_id() {
        return system_image_id;
    }

    public void setSystem_image_id(String system_image_id) {
        this.system_image_id = system_image_id;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public String getSystem_image_type() {
        return system_image_type;
    }

    public void setSystem_image_type(String system_image_type) {
        this.system_image_type = system_image_type;
    }

    public int getSystem_image_order() {
        return system_image_order;
    }

    public void setSystem_image_order(int system_image_order) {
        this.system_image_order = system_image_order;
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

    public int getSystem_image_height() {
        return system_image_height;
    }

    public void setSystem_image_height(int system_image_height) {
        this.system_image_height = system_image_height;
    }
}
