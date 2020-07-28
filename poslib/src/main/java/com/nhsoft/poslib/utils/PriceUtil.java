package com.nhsoft.poslib.utils;

import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.PosItemGrade;
import com.nhsoft.poslib.libconfig.LibConfig ;

/**
 * Created by Iverson on 2019-11-15 10:04
 * 此类用于：
 */
public class PriceUtil {


    /**
     * 取分级商品标准价格
     * @param posItemGrade
     * @return
     */
    public static float getItemGradeRegularPrice(PosItemGrade posItemGrade){
        float truePrice;
        if (LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived()) {
            float branch_grade_regular_price = posItemGrade.getBranch_grade_regular_price();
            if(branch_grade_regular_price == 0){
                branch_grade_regular_price = posItemGrade.getItem_grade_regular_price();
            }
            truePrice = branch_grade_regular_price;
        }else {
            float item_grade_regular_price = posItemGrade.getItem_grade_regular_price() == null ? 0 : posItemGrade.getItem_grade_regular_price().floatValue();
            truePrice = item_grade_regular_price;
        }
        return truePrice;
    }


    public static float getItemRegularPrice(PosItem posItem,PosItemGrade posItemGrade){
        float truePrice;
        if(posItemGrade != null){
            float gradeRegularPrice = PriceUtil.getItemGradeRegularPrice(posItemGrade);
            truePrice = gradeRegularPrice;
        }else {
            if (LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived() && posItem.getBranch_regular_price() != 0) {
                truePrice = posItem.getBranch_regular_price() ;//门店标准单价
            } else {
                truePrice =  posItem.getItem_regular_price();//中心标准单价
            }
        }
        return truePrice;
    }
}
