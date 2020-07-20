package com.nhsoft.poslib.entity;

public class PrintDetail {

    /**
     * image : url
     * name : 详细销售单
     * file_name : first_order_style.txt
     */

    private String image;
    private String name;
    private String file_name;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
}
