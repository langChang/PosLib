package com.nhsoft.poslib.entity;

import android.widget.Button;


import com.nhsoft.poslib.model.PosScaleStyleTypeBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by Iverson on 2019-08-14 14:09
 * 此类用于：
 */
@Entity
public class BottomMenu {

    @Id
    private Long                  menu_id;
    private String                menu_type;
    private boolean               isPayStyle;
    private String                menu_text;
    @Transient
    private Button                mBtView;
    @Transient
    private PosScaleStyleTypeBean payStyleBean;
    @Transient
    private int                   position;//排序顺序


    public PosScaleStyleTypeBean getPayStyleBean() {
        return payStyleBean;
    }

    public void setPayStyleBean(PosScaleStyleTypeBean payStyleBean) {
        this.payStyleBean = payStyleBean;
    }



    public Button getmBtView() {
        return mBtView;
    }

    public void setmBtView(Button mBtView) {
        this.mBtView = mBtView;
    }



    @Generated(hash = 1482896269)
    public BottomMenu(Long menu_id, String menu_type, boolean isPayStyle,
            String menu_text) {
        this.menu_id = menu_id;
        this.menu_type = menu_type;
        this.isPayStyle = isPayStyle;
        this.menu_text = menu_text;
    }

    @Generated(hash = 572720821)
    public BottomMenu() {
    }

    public Long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Long menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_type() {
        return menu_type;
    }

    public void setMenu_type(String menu_type) {
        this.menu_type = menu_type;
    }

    public boolean isPayStyle() {
        return isPayStyle;
    }

    public void setPayStyle(boolean payStyle) {
        isPayStyle = payStyle;
    }

    public String getMenu_text() {
        return menu_text;
    }

    public void setMenu_text(String menu_text) {
        this.menu_text = menu_text;
    }

    public boolean getIsPayStyle() {
        return this.isPayStyle;
    }

    public void setIsPayStyle(boolean isPayStyle) {
        this.isPayStyle = isPayStyle;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
