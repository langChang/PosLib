package com.nhsoft.poslib.normal.callback;

import com.nhsoft.poslib.model.VipUserInfo;
import com.nhsoft.poslib.entity.order.Payment;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.model.CouponsBean;
import com.nhsoft.poslib.model.PosScaleStyleTypeBean;

import java.util.List;

/**
 * Created by Iverson on 2019-11-28 14:32
 * 此类用于：
 */
public interface OrderOperationCallback {

    boolean collectOrder(PosOrder posOrder);

    PosOrder copayPosOrder(PosOrder posOrder);

    PosOrderDetail copayPosOrderDetail(PosOrderDetail posOrderDetail);

    void sortPosOrderDetail(List<PosOrderDetail> posOrderDetails);

    void sortCoupons(List<CouponsBean> couponsBeans);

    Payment createPayment(String orderNo, PosScaleStyleTypeBean styleTypeBean);

    Payment updatePayment(Payment payment,float receiveMoney);

    String createQuitCardJson(VipUserInfo vipUserInfo);

    List<PosScaleStyleTypeBean> getEnablePayment(String paymentName);

    PosScaleStyleTypeBean containCashPayment(List<PosScaleStyleTypeBean> posScaleStyleTypeBeans);

    PosScaleStyleTypeBean containCardPayment(List<PosScaleStyleTypeBean> posScaleStyleTypeBeans);

    PosOrder mergeAllGoods(PosOrder posOrder);


}
