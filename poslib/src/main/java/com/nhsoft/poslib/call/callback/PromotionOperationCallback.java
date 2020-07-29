package com.nhsoft.poslib.call.callback;

import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.model.BasePolicyBean;

import java.util.List;

/**
 * Created by Iverson on 2019-11-28 16:00
 * 此类用于：
 */
public interface PromotionOperationCallback {

    List<BasePolicyBean> getAllUsePolicys(PosOrder posOrder);

    void getPolicyDiscount(PosOrder mPosOrder);

}
