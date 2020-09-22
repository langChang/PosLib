package com.nhsoft.poslib.model;

import com.nhsoft.poslib.entity.ItemCategory;
import com.nhsoft.poslib.entity.PosItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2018/11/21 4:49 PM
 * 此类用于：
 */
public class GoodsGradeBean {

    private int                grade;  //分一级  二级
    private String             category_code;
    private String             category_name;
    private boolean            isEmpty;
    public  List<PosItem>      posItems = new ArrayList<>();
    public  List<ItemCategory> itemCategories = new ArrayList<>();
    public  ItemCategory       itemCategory;
    private int selectPosition = -1;


    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }




    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

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


    public List<PosItem> getPosItems() {
        return posItems;
    }

    public void setPosItems(List<PosItem> posItems) {
        this.posItems = posItems;
    }

    public List<ItemCategory> getItemCategories() {
        return itemCategories;
    }

    public void setItemCategories(List<ItemCategory> itemCategories) {
        this.itemCategories = itemCategories;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
