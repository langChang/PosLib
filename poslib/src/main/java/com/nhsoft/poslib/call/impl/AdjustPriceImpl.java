package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.PosItemGrade;
import com.nhsoft.poslib.entity.shift.ShiftTable;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.model.AdjustPriceOrder;
import com.nhsoft.poslib.model.AdjustTradePriceOrder;
import com.nhsoft.poslib.call.callback.AdjustPriceCallback;
import com.nhsoft.poslib.utils.TimeUtil;

import java.util.List;

/**
 * Created by Iverson on 2019-11-29 15:39
 * 此类用于：
 */
public class AdjustPriceImpl implements AdjustPriceCallback {

    @Override
    public AdjustPriceOrder createAdjustOrder(List<AdjustPriceOrder.AdjustDetailsBean> adjustDetailsBeans) {
        AdjustPriceOrder adjustPriceOrder = new AdjustPriceOrder();
        ShiftTable shiftTable = LibConfig.activeShiftTable;
        adjustPriceOrder.setBranch_num(shiftTable.getBranch_num());
        adjustPriceOrder.setPrice_adjustment_memo(TimeUtil.getInstance().getNowDateString());
        adjustPriceOrder.setPrice_adjustment_creator(shiftTable.getShift_table_userName());
        adjustPriceOrder.setPrice_adjustment_details(adjustDetailsBeans);
        return adjustPriceOrder;
    }

    @Override
    public AdjustTradePriceOrder createAdjustTradeOrder(List<AdjustTradePriceOrder.AdjustTradeDetailBean> adjustTradeDetailBeans) {
        AdjustTradePriceOrder adjustPriceOrder = new AdjustTradePriceOrder();
        ShiftTable shiftTable = LibConfig.activeShiftTable;
        adjustPriceOrder.setBranch_num(shiftTable.getBranch_num());
        adjustPriceOrder.setStall_adjustment_memo(TimeUtil.getInstance().getNowDateString());
        adjustPriceOrder.setStall_adjustment_operator(shiftTable.getShift_table_userName());
        adjustPriceOrder.setMerchant_num(shiftTable.getMerchant_num());
        adjustPriceOrder.setStall_num(shiftTable.getStall_num());
        adjustPriceOrder.setDetails(adjustTradeDetailBeans);
        return adjustPriceOrder;
    }


    @Override
    public AdjustTradePriceOrder.AdjustTradeDetailBean createAdjustTradeDetail(PosItem posItem) {
        AdjustTradePriceOrder.AdjustTradeDetailBean priceAdjustmentDetailsBean = new AdjustTradePriceOrder.AdjustTradeDetailBean();
        priceAdjustmentDetailsBean.setPosItem(posItem);
        if(posItem.getStall_regular_price() != 0){
            priceAdjustmentDetailsBean.setAdjustment_detail_ori_regular_price(posItem.getStall_regular_price());
            priceAdjustmentDetailsBean.setOld_real_price(posItem.getStall_regular_price());
            priceAdjustmentDetailsBean.setAdjustment_detail_ori_level2_price(posItem.getStall_vip_price());
            priceAdjustmentDetailsBean.setAdjustment_detail_regular_price(posItem.getStall_regular_price());
            priceAdjustmentDetailsBean.setAdjustment_detail_level2_price(posItem.getStall_vip_price());
        }else {
            priceAdjustmentDetailsBean.setAdjustment_detail_ori_regular_price(posItem.getBranch_regular_price());
            priceAdjustmentDetailsBean.setAdjustment_detail_ori_level2_price(posItem.getBranch_level2_price());
            priceAdjustmentDetailsBean.setAdjustment_detail_regular_price(posItem.getBranch_regular_price());
            priceAdjustmentDetailsBean.setAdjustment_detail_level2_price(posItem.getBranch_level2_price());
            priceAdjustmentDetailsBean.setOld_real_price(posItem.getBranch_regular_price());


        }
        priceAdjustmentDetailsBean.setItem_num(posItem.getItem_num());
        priceAdjustmentDetailsBean.setItem_name(posItem.getItem_name());
        priceAdjustmentDetailsBean.setItem_unit_str(posItem.getItem_unit());

        return priceAdjustmentDetailsBean;
    }

    @Override
    public AdjustPriceOrder.AdjustDetailsBean createAdjustDetail(PosItem posItem, PosItemGrade posItemGrade) {
        AdjustPriceOrder.AdjustDetailsBean adjustDetailsBean = new AdjustPriceOrder.AdjustDetailsBean();
        adjustDetailsBean.setPosItem(posItem);
        adjustDetailsBean.setPosItemGrade(posItemGrade);

        adjustDetailsBean.setAdjustment_detail_ori_regular_price(posItemGrade == null ? posItem.getBranch_regular_price(): posItemGrade.getBranch_grade_regular_price());
        adjustDetailsBean.setAdjustment_detail_ori_level2_price(posItemGrade == null ? posItem.getBranch_level2_price(): posItemGrade.getBranch_grade_level2_price());
        adjustDetailsBean.setAdjustment_detail_ori_level3_price(posItemGrade == null ? posItem.getBranch_level3_price(): posItemGrade.getBranch_grade_level3_price());
        adjustDetailsBean.setAdjustment_detail_ori_level4_price(posItemGrade == null ?  posItem.getBranch_level4_price(): posItemGrade.getBranch_grade_level4_price());


        adjustDetailsBean.setAdjustment_detail_regular_price(posItemGrade == null ?
                (posItem.getBranch_regular_price()==0?posItem.getItem_regular_price():posItem.getBranch_regular_price()):
                (posItemGrade.getBranch_grade_regular_price()==0?posItemGrade.getItem_grade_regular_price():posItemGrade.getBranch_grade_regular_price()));
        adjustDetailsBean.setAdjustment_detail_level2_price(posItemGrade == null ?
                posItem.getBranch_level2_price(): posItemGrade.getBranch_grade_level2_price());
        adjustDetailsBean.setAdjustment_detail_level3_price(posItemGrade == null ?
                posItem.getBranch_level3_price(): posItemGrade.getBranch_grade_level3_price());
        adjustDetailsBean.setAdjustment_detail_level4_price(posItemGrade == null ?
                posItem.getBranch_level4_price(): posItemGrade.getBranch_grade_level4_price());


        adjustDetailsBean.setAdjustment_detail_max_price(posItem.getBranch_max_price());
        adjustDetailsBean.setAdjustment_detail_min_price(posItem.getItem_min_price());
        adjustDetailsBean.setBranch_sale_cease_flag(posItem.getBranch_sale_cease_flag());
        if(posItemGrade != null){
            adjustDetailsBean.setBranch_grade_sale_cease_flag(posItemGrade.getItem_grade_sale_cease_flag());
        }
        adjustDetailsBean.setItem_del_flag(posItem.getItem_del_tag());
        adjustDetailsBean.setItem_num(posItem.getItem_num());
        adjustDetailsBean.setItem_grade_num(posItemGrade != null ? Long.parseLong(String.valueOf(posItemGrade.getItem_grade_num())): null);
        adjustDetailsBean.setItem_name(posItemGrade == null ? posItem.getItem_name(): posItemGrade.getItem_grade_name());
        adjustDetailsBean.setOld_real_price(posItemGrade == null ?  posItem.getBranch_regular_price(): posItemGrade.getBranch_grade_regular_price());
        adjustDetailsBean.setItem_unit_str(posItem.getItem_unit());
        return adjustDetailsBean;
    }
}
