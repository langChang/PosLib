package com.nhsoft.poslib.normal.callback;

import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.model.CouponsBean;
import com.nhsoft.poslib.model.GoodsGradeBean;

import java.util.List;

/**
 * Created by Iverson on 2019-11-28 09:35
 * 此类用于：
 */
public interface GoodsDataCallback {

    List<GoodsGradeBean> getAllShowGoods();

    List<GoodsGradeBean> getAllShowGoods(int stallNum, int merchantNum);

    int isInGoodsList(CouponsBean couponsBean, PosOrderDetail posOrderDetail);

}