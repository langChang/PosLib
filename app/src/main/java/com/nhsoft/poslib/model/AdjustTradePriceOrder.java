package com.nhsoft.poslib.model;

import com.nhsoft.poslib.entity.PosItem;

import java.util.List;

/**
 * Created by Iverson on 2019-08-01 15:04
 * 此类用于：
 */
public class AdjustTradePriceOrder {

    private int    branch_num;
    private int    merchant_num;
    private int    stall_num;
    private String stall_adjustment_memo;
    private String stall_adjustment_operator;

    private List<AdjustTradeDetailBean> details;


    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public int getMerchant_num() {
        return merchant_num;
    }

    public void setMerchant_num(int merchant_num) {
        this.merchant_num = merchant_num;
    }

    public int getStall_num() {
        return stall_num;
    }

    public void setStall_num(int stall_num) {
        this.stall_num = stall_num;
    }

    public String getStall_adjustment_memo() {
        return stall_adjustment_memo;
    }

    public void setStall_adjustment_memo(String stall_adjustment_memo) {
        this.stall_adjustment_memo = stall_adjustment_memo;
    }

    public String getStall_adjustment_operator() {
        return stall_adjustment_operator;
    }

    public void setStall_adjustment_operator(String stall_adjustment_operator) {
        this.stall_adjustment_operator = stall_adjustment_operator;
    }

    public List<AdjustTradeDetailBean> getDetails() {
        return details;
    }

    public void setDetails(List<AdjustTradeDetailBean> details) {
        this.details = details;
    }

    public static class AdjustTradeDetailBean {

        private long   adjustment_detail_num;
        private float  adjustment_detail_level2_price;
        private float  adjustment_detail_ori_level2_price;
        private float  adjustment_detail_ori_regular_price;
        private float  adjustment_detail_regular_price;
        private int    item_grade_num;
        private long    item_num;
        private String stall_adjustment_fid;
        private PosItem posItem;
        private String item_name;
        private String item_unit_str;
        private float old_real_price;

        public String getItem_unit_str() {
            return item_unit_str;
        }

        public void setItem_unit_str(String item_unit_str) {
            this.item_unit_str = item_unit_str;
        }


        public long getAdjustment_detail_num() {
            return adjustment_detail_num;
        }

        public void setAdjustment_detail_num(long adjustment_detail_num) {
            this.adjustment_detail_num = adjustment_detail_num;
        }

        public float getAdjustment_detail_level2_price() {
            return adjustment_detail_level2_price;
        }

        public float getOld_real_price() {
            return old_real_price;
        }

        public void setOld_real_price(float old_real_price) {
            this.old_real_price = old_real_price;
        }

        public void setAdjustment_detail_level2_price(float adjustment_detail_level2_price) {
            this.adjustment_detail_level2_price = adjustment_detail_level2_price;
        }

        public float getAdjustment_detail_ori_level2_price() {
            return adjustment_detail_ori_level2_price;
        }

        public void setAdjustment_detail_ori_level2_price(float adjustment_detail_ori_level2_price) {
            this.adjustment_detail_ori_level2_price = adjustment_detail_ori_level2_price;
        }

        public float getAdjustment_detail_ori_regular_price() {
            return adjustment_detail_ori_regular_price;
        }

        public void setAdjustment_detail_ori_regular_price(float adjustment_detail_ori_regular_price) {
            this.adjustment_detail_ori_regular_price = adjustment_detail_ori_regular_price;
        }

        public float getAdjustment_detail_regular_price() {
            return adjustment_detail_regular_price;
        }

        public void setAdjustment_detail_regular_price(float adjustment_detail_regular_price) {
            this.adjustment_detail_regular_price = adjustment_detail_regular_price;
        }

        public int getItem_grade_num() {
            return item_grade_num;
        }

        public void setItem_grade_num(int item_grade_num) {
            this.item_grade_num = item_grade_num;
        }

        public long getItem_num() {
            return item_num;
        }

        public void setItem_num(long item_num) {
            this.item_num = item_num;
        }

        public String getStall_adjustment_fid() {
            return stall_adjustment_fid;
        }

        public void setStall_adjustment_fid(String stall_adjustment_fid) {
            this.stall_adjustment_fid = stall_adjustment_fid;
        }

        public PosItem getPosItem() {
            return posItem;
        }

        public void setPosItem(PosItem posItem) {
            this.posItem = posItem;
        }


        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

    }
}
