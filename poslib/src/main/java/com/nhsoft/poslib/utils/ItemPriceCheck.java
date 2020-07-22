package com.nhsoft.poslib.utils;

import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.libconfig.LibConfig;

/**
 * Created by Iverson on 2020-01-16 09:32
 * 此类用于：
 */
public class ItemPriceCheck {


    /**
     * 是否超过最高价
     * @param posItem 该商品
     * @param price 要修改的价格
     * @return
     */
    public static float getItemMaxPrice(PosItem posItem,float price){
        float maxPrice = 0;
        if (LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived()){
            maxPrice = posItem.getBranch_max_price();
            if(maxPrice == 0){
                maxPrice = posItem.getItem_max_price() == null? 0 : posItem.getItem_max_price();
            }
        }else {
            maxPrice = posItem.getItem_max_price() == null? 0 : posItem.getItem_max_price();
        }

        if(maxPrice != 0 && maxPrice < price){
           return maxPrice;
        }
        return price;
    }


    /**
     *
     * @param posItem 该商品
     * @param price 要修改的价格
     * @return
     */
    public static float getItemMinPrice(PosItem posItem,float price){

        float minPrice = 0;

        if (LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived()){
            minPrice = posItem.getBranch_min_price() == null ? 0 : posItem.getBranch_min_price();
            if(minPrice == 0){
                minPrice = posItem.getItem_min_price();
            }
        }else {
            minPrice = posItem.getItem_min_price();
        }

        if(minPrice != 0 && minPrice > price){
            return minPrice;
        }

        return price;
    }


}
