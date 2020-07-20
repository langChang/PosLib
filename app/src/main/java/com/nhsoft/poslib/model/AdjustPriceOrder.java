package com.nhsoft.poslib.model;

import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.PosItemGrade;

import java.util.List;

/**
 * Created by Iverson on 2019-06-11 15:17
 * 此类用于：
 */
public class AdjustPriceOrder {

    /**
     * branch_num : 0
     * price_adjustment_creator : string
     * price_adjustment_department : string
     * price_adjustment_details : [{"adjustment_detail_level2_price":0,"adjustment_detail_level3_price":0,"adjustment_detail_level4_price":0,"adjustment_detail_max_price":0,"adjustment_detail_memo":"string","adjustment_detail_min_price":0,"adjustment_detail_regular_price":0,"branch_sale_cease_flag":true,"item_del_flag":true,"item_num":0}]
     * price_adjustment_memo : string
     */

    private int                     branch_num;
    private String                  price_adjustment_creator;
    private String                  price_adjustment_department;
    private String                  price_adjustment_memo;
    private List<AdjustDetailsBean> price_adjustment_details;

    public String getApp_user_num() {
        return app_user_num;
    }

    public void setApp_user_num(String app_user_num) {
        this.app_user_num = app_user_num;
    }

    private String app_user_num;

    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public String getPrice_adjustment_creator() {
        return price_adjustment_creator;
    }

    public void setPrice_adjustment_creator(String price_adjustment_creator) {
        this.price_adjustment_creator = price_adjustment_creator;
    }

    public String getPrice_adjustment_department() {
        return price_adjustment_department;
    }

    public void setPrice_adjustment_department(String price_adjustment_department) {
        this.price_adjustment_department = price_adjustment_department;
    }

    public String getPrice_adjustment_memo() {
        return price_adjustment_memo;
    }

    public void setPrice_adjustment_memo(String price_adjustment_memo) {
        this.price_adjustment_memo = price_adjustment_memo;
    }

    public List<AdjustDetailsBean> getPrice_adjustment_details() {
        return price_adjustment_details;
    }

    public void setPrice_adjustment_details(List<AdjustDetailsBean> price_adjustment_details) {
        this.price_adjustment_details = price_adjustment_details;
    }

    public static class AdjustDetailsBean {
        /**
         * adjustment_detail_level2_price : 0
         * adjustment_detail_level3_price : 0
         * adjustment_detail_level4_price : 0
         * adjustment_detail_max_price : 0
         * adjustment_detail_memo : string
         * adjustment_detail_min_price : 0
         * adjustment_detail_regular_price : 0
         * branch_sale_cease_flag : true
         * item_del_flag : true
         * item_num : 0
         */

        private float adjustment_detail_level2_price;
        private float     adjustment_detail_level3_price;
        private float     adjustment_detail_level4_price;
        private float     adjustment_detail_max_price;
        private String  adjustment_detail_memo;
        private float     adjustment_detail_min_price;
        private float     adjustment_detail_regular_price;
        private boolean branch_sale_cease_flag;
        private boolean item_del_flag;
        private long     item_num;



        private Long         item_grade_num;
        private String       item_name;
        private float        old_real_price;
        private String       item_unit_str;
        private float        adjustment_detail_ori_level2_price;
        private float        adjustment_detail_ori_level3_price;
        private float        adjustment_detail_ori_level4_price;
        private float        adjustment_detail_ori_regular_price;



        private boolean branch_grade_sale_cease_flag;

        private PosItem      posItem;
        private PosItemGrade posItemGrade;

        public PosItemGrade getPosItemGrade() {
            return posItemGrade;
        }

        public void setPosItemGrade(PosItemGrade posItemGrade) {
            this.posItemGrade = posItemGrade;
        }



        public PosItem getPosItem() {
            return posItem;
        }

        public void setPosItem(PosItem posItem) {
            this.posItem = posItem;
        }

        public float getAdjustment_detail_ori_regular_price() {
            return adjustment_detail_ori_regular_price;
        }

        public void setAdjustment_detail_ori_regular_price(float adjustment_detail_ori_regular_price) {
            this.adjustment_detail_ori_regular_price = adjustment_detail_ori_regular_price;
        }

        public float getAdjustment_detail_ori_level2_price() {
            return adjustment_detail_ori_level2_price;
        }

        public void setAdjustment_detail_ori_level2_price(float adjustment_detail_ori_level2_price) {
            this.adjustment_detail_ori_level2_price = adjustment_detail_ori_level2_price;
        }

        public float getAdjustment_detail_ori_level3_price() {
            return adjustment_detail_ori_level3_price;
        }

        public void setAdjustment_detail_ori_level3_price(float adjustment_detail_ori_level3_price) {
            this.adjustment_detail_ori_level3_price = adjustment_detail_ori_level3_price;
        }

        public float getAdjustment_detail_ori_level4_price() {
            return adjustment_detail_ori_level4_price;
        }

        public void setAdjustment_detail_ori_level4_price(float adjustment_detail_ori_level4_price) {
            this.adjustment_detail_ori_level4_price = adjustment_detail_ori_level4_price;
        }

        public String getItem_unit_str() {
            return item_unit_str;
        }

        public void setItem_unit_str(String item_unit_str) {
            this.item_unit_str = item_unit_str;
        }

        public float getOld_real_price() {
            return old_real_price;
        }

        public void setOld_real_price(float old_real_price) {
            this.old_real_price = old_real_price;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }



        public float getAdjustment_detail_level2_price() {
            return adjustment_detail_level2_price;
        }

        public void setAdjustment_detail_level2_price(float adjustment_detail_level2_price) {
            this.adjustment_detail_level2_price = adjustment_detail_level2_price;
        }

        public float getAdjustment_detail_level3_price() {
            return adjustment_detail_level3_price;
        }

        public void setAdjustment_detail_level3_price(float adjustment_detail_level3_price) {
            this.adjustment_detail_level3_price = adjustment_detail_level3_price;
        }

        public float getAdjustment_detail_level4_price() {
            return adjustment_detail_level4_price;
        }

        public void setAdjustment_detail_level4_price(float adjustment_detail_level4_price) {
            this.adjustment_detail_level4_price = adjustment_detail_level4_price;
        }

        public float getAdjustment_detail_max_price() {
            return adjustment_detail_max_price;
        }

        public void setAdjustment_detail_max_price(float adjustment_detail_max_price) {
            this.adjustment_detail_max_price = adjustment_detail_max_price;
        }

        public String getAdjustment_detail_memo() {
            return adjustment_detail_memo;
        }

        public void setAdjustment_detail_memo(String adjustment_detail_memo) {
            this.adjustment_detail_memo = adjustment_detail_memo;
        }

        public float getAdjustment_detail_min_price() {
            return adjustment_detail_min_price;
        }

        public void setAdjustment_detail_min_price(float adjustment_detail_min_price) {
            this.adjustment_detail_min_price = adjustment_detail_min_price;
        }

        public float getAdjustment_detail_regular_price() {
            return adjustment_detail_regular_price;
        }

        public void setAdjustment_detail_regular_price(float adjustment_detail_regular_price) {
            this.adjustment_detail_regular_price = adjustment_detail_regular_price;
        }

        public boolean isBranch_sale_cease_flag() {
            return branch_sale_cease_flag;
        }

        public void setBranch_sale_cease_flag(boolean branch_sale_cease_flag) {
            this.branch_sale_cease_flag = branch_sale_cease_flag;
        }

        public boolean isItem_del_flag() {
            return item_del_flag;
        }

        public void setItem_del_flag(boolean item_del_flag) {
            this.item_del_flag = item_del_flag;
        }

        public long getItem_num() {
            return item_num;
        }

        public void setItem_num(long item_num) {
            this.item_num = item_num;
        }

        public Long getItem_grade_num() {
            return item_grade_num;
        }

        public void setItem_grade_num(Long item_grade_num) {
            this.item_grade_num = item_grade_num;
        }

        public boolean isBranch_grade_sale_cease_flag() {
            return branch_grade_sale_cease_flag;
        }

        public void setBranch_grade_sale_cease_flag(boolean branch_grade_sale_cease_flag) {
            this.branch_grade_sale_cease_flag = branch_grade_sale_cease_flag;
        }
    }
}
