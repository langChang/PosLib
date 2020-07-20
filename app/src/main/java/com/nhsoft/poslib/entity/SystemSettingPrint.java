package com.nhsoft.poslib.entity;

/**
 * 小票中心库 中间行 bean
 */
public class SystemSettingPrint {


    /**
     * image : url
     * name : 详细卡续卡单
     * file_name : second_replace_card_style.txt
     */

    private String image;
    private String name;
    private String file_name;
    private int image_height;
    private int version_code;

    public int getVersion_code() {
        return version_code;
    }

    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }

    public int getImage_height() {
        return image_height;
    }

    public void setImage_height(int image_height) {
        this.image_height = image_height;
    }

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
