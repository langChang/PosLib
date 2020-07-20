package com.nhsoft.poslib.model;

import com.nhsoft.poslib.entity.new_nong_mao.PosItemNewNongMao;

import java.util.List;

/**
 * Created by Iverson on 2019-09-23 16:07
 * 此类用于：
 */
public class NmGoodsGradeBean {

    private String                  itemCodeName;
    private List<PosItemNewNongMao> posItems;
    private String                  itemCode;

    public List<PosItemNewNongMao> getPosItems() {
        return posItems;
    }

    public void setPosItems(List<PosItemNewNongMao> posItems) {
        this.posItems = posItems;
    }

    public String getItemCodeName() {
        return itemCodeName;
    }

    public void setItemCodeName(String itemCodeName) {
        this.itemCodeName = itemCodeName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
