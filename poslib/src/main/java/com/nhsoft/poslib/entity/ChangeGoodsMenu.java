package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by Iverson on 2019-08-14 14:09
 * 此类用于：
 */
@Entity
public class ChangeGoodsMenu {

    @Id
    private Long menu_id;
    private String menu_text;
    @Transient
    private int position;//排序顺序

    @Generated(hash = 1475010378)
    public ChangeGoodsMenu(Long menu_id, String menu_text) {
        this.menu_id = menu_id;
        this.menu_text = menu_text;
    }

    @Generated(hash = 439529021)
    public ChangeGoodsMenu() {
    }

    public Long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Long menu_id) {
        this.menu_id = menu_id;
    }


    public String getMenu_text() {
        return menu_text;
    }

    public void setMenu_text(String menu_text) {
        this.menu_text = menu_text;
    }


    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
