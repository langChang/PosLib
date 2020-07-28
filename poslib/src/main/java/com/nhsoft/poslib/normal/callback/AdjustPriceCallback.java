package com.nhsoft.poslib.normal.callback;

import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.PosItemGrade;
import com.nhsoft.poslib.model.AdjustPriceOrder;
import com.nhsoft.poslib.model.AdjustTradePriceOrder;

import java.util.List;

/**
 * Created by Iverson on 2019-11-29 15:38
 * 此类用于：
 */
public interface AdjustPriceCallback {

    AdjustPriceOrder createAdjustOrder(List<AdjustPriceOrder.AdjustDetailsBean> adjustDetailsBeans);

    AdjustTradePriceOrder createAdjustTradeOrder(List<AdjustTradePriceOrder.AdjustTradeDetailBean> adjustTradeDetailBeans);

    AdjustPriceOrder.AdjustDetailsBean createAdjustDetail(PosItem posItem, PosItemGrade posItemGrade);

    AdjustTradePriceOrder.AdjustTradeDetailBean createAdjustTradeDetail(PosItem posItem);

}
