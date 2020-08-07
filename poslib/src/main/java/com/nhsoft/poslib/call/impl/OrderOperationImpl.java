package com.nhsoft.poslib.call.impl;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.entity.BookResource;
import com.nhsoft.poslib.entity.KeyGeneratorBizday;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.PosItemGrade;
import com.nhsoft.poslib.entity.PosMachine;
import com.nhsoft.poslib.model.VipUserInfo;
import com.nhsoft.poslib.entity.order.Payment;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.entity.shift.ShiftTable;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.model.CouponsBean;
import com.nhsoft.poslib.model.PosOrderDetailState;
import com.nhsoft.poslib.model.PosOrderState;
import com.nhsoft.poslib.model.PosScaleStyleTypeBean;
import com.nhsoft.poslib.call.callback.OrderOperationCallback;
import com.nhsoft.poslib.utils.EvtLog;
import com.nhsoft.poslib.utils.NumberUtil;
import com.nhsoft.poslib.utils.PosOrderDetailStateUtil;
import com.nhsoft.poslib.utils.PosOrderOperationUtil;
import com.nhsoft.poslib.utils.PosOrderStateUtil;
import com.nhsoft.poslib.utils.TagUtils;
import com.nhsoft.poslib.utils.TimeUtil;
import com.nhsoft.poslib.utils.UUIDUtils;
import com.nhsoft.poslib.utils.XmlUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Iverson on 2019-11-28 14:32
 * 此类用于：
 */
public class OrderOperationImpl implements OrderOperationCallback {

    private static OrderOperationImpl instance;

    public static OrderOperationImpl getInstance() {
        if (instance == null) {
            instance = new OrderOperationImpl();
        }
        return instance;
    }



    @Override
    public boolean collectOrder(PosOrder posOrder) {

        ShiftTable shiftTable = LibConfig.activeShiftTable;
        PosMachine posMachine = LibConfig.activePosMachine;

        KeyGeneratorBizday keyGeneratorBizday;
        if (TextUtils.isEmpty(posOrder.getOrderNo())) {
            keyGeneratorBizday = KeyGeneratorBizdayImpl.getInstance().createPosOrderKG(shiftTable.getSystemBookCode(), shiftTable.getBranchNum(), shiftTable.getShiftTableBizday(),
                    LibConfig.S_POS_ORDER_KEY_ITEM, posMachine.getPos_machine_sequence());
        } else {
            keyGeneratorBizday = KeyGeneratorBizdayImpl.getInstance().createPosOrderKG(shiftTable.getSystemBookCode(), shiftTable.getBranchNum(), shiftTable.getShiftTableBizday(),
                    LibConfig.S_POS_ORDER_KEY_ITEM, posMachine.getPos_machine_sequence());
            keyGeneratorBizday.setKeyGBString(posOrder.getOrderNo());
        }
        boolean result = PosOrderOperationUtil.startInsertOrder(posOrder, keyGeneratorBizday, LibConfig.S_ORDER_INIT);
        RetailPosManager.getInstance().savePosOrder(posOrder);
         RetailPosManager.getInstance().tryCollectionOrder(posOrder);
        return result;
    }

    @Override
    public PosOrder copayPosOrder(PosOrder posOrder) {
        try {
            String json = new Gson().toJson(posOrder);

            final PosOrder nowPosOrder = new Gson().fromJson(json,PosOrder.class);
//            List<PosOrderDetail> localposOrderDetails = new ArrayList<>();
//            List<PosOrderDetail> posOrderDetails = posOrder.getPosOrderDetails();
//            for (PosOrderDetail posOrderDetail : posOrderDetails) {
//                localposOrderDetails.add((PosOrderDetail) posOrderDetail.clone());
//            }
//            nowPosOrder.setPosOrderDetails(localposOrderDetails);
            return nowPosOrder;

        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public PosOrderDetail copayPosOrderDetail(PosOrderDetail posOrderDetail) {
        try {
            String json = new Gson().toJson(posOrderDetail);

            final PosOrderDetail nowPosOrderdetail = new Gson().fromJson(json,PosOrderDetail.class);
//            List<PosOrderDetail> localposOrderDetails = new ArrayList<>();
//            List<PosOrderDetail> posOrderDetails = posOrder.getPosOrderDetails();
//            for (PosOrderDetail posOrderDetail : posOrderDetails) {
//                localposOrderDetails.add((PosOrderDetail) posOrderDetail.clone());
//            }
//            nowPosOrder.setPosOrderDetails(localposOrderDetails);
            return nowPosOrderdetail;

        } catch (Exception e) {
            return null;
        }
    }



    @Override
    public Payment createPayment(String orderNo, PosScaleStyleTypeBean styleTypeBean) {
        Payment payment = new Payment();
        payment.setOrderNo(orderNo);
        payment.setPaymentPayBy(styleTypeBean.getPaymentTypeName());
        payment.setPaymentNo(styleTypeBean.getPaymentTypeCode());
        return payment;
    }

    @Override
    public Payment updatePayment(Payment payment,float receiveMoney) {
        return null;
    }

    @Override
    public List<PosScaleStyleTypeBean> getEnablePayment(String paymentName) {
        BookResource bookPosSale = BookResourceImpl.getInstance().getBookPosSale(LibConfig.activeShiftTable.getSystemBookCode(), LibConfig.S_PAY_SCALE_STYLE);
        List<PosScaleStyleTypeBean> posSaleParam = new ArrayList<>();
        if (bookPosSale != null) {
            posSaleParam = XmlUtil.getPosScaleStyle(bookPosSale.getBookResourceParam());
            if(LibConfig.C_PAYMENT_TYPE_PETCARD_NAME.equals(paymentName)){
                posSaleParam = PayStyleImpl.getInstance().getSettleCardPayList(posSaleParam);
            }else {
                posSaleParam = PayStyleImpl.getInstance().getSettlePaySaleList(posSaleParam);
            }
        }
        return posSaleParam;
    }

    @Override
    public PosScaleStyleTypeBean containCashPayment(List<PosScaleStyleTypeBean> posSaleParam) {
        for (final PosScaleStyleTypeBean styleTypeBean : posSaleParam) {
            if (LibConfig.C_PAYMENT_TYPE_CASH_NAME.equals(styleTypeBean.getPaymentTypeName())) {
               return styleTypeBean;
            }
        }
        return null;
    }

    @Override
    public PosScaleStyleTypeBean containCardPayment(List<PosScaleStyleTypeBean> posSaleParam) {
        for (final PosScaleStyleTypeBean styleTypeBean : posSaleParam) {
            if (LibConfig.C_PAYMENT_TYPE_PETCARD_NAME.equals(styleTypeBean.getPaymentTypeName())) {
                return styleTypeBean;
            }
        }
        return null;
    }

    @Override
    public String createQuitCardJson(VipUserInfo vipUserInfo) {
        if (vipUserInfo.isQuitCard()) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("card_user_num", vipUserInfo.getCard_user_num());
                jsonObject.put("card_user_revoke_shop", LibConfig.activeLoginBean.getBranch_num());
                jsonObject.put("card_user_revoker", LibConfig.activeAppUser.getApp_user_name());
                jsonObject.put("shift_table_bizday", LibConfig.activeShiftTable.getShiftTableBizday());
                jsonObject.put("shift_table_num", LibConfig.activeShiftTable.getShiftTableNum());
                return jsonObject.toString();
            } catch (JSONException e) {
                e.printStackTrace();
                return "";
            }
        } else {
           return "";
        }
    }

    @Override
    public void sortPosOrderDetail(List<PosOrderDetail> posOrderDetails) {
        Collections.sort(posOrderDetails, new Comparator<PosOrderDetail>() {
            @Override
            public int compare(PosOrderDetail posOrderDetail, PosOrderDetail t1) {
                if (posOrderDetail.getOrderDetailPaymentMoney() > t1.getOrderDetailPaymentMoney()) {
                    return 1;
                } else if (posOrderDetail.getOrderDetailPaymentMoney() < t1.getOrderDetailPaymentMoney()) {
                    return -1;
                }
                return 0;
            }
        });
    }

    @Override
    public void sortCoupons(List<CouponsBean> couponsBeans) {
        Collections.sort(couponsBeans, new Comparator<CouponsBean>() {
            @Override
            public int compare(CouponsBean posOrderDetail, CouponsBean t1) {
                if (posOrderDetail.getTicket_send_detail_value() < t1.getTicket_send_detail_value()) {
                    return 1;
                } else if (posOrderDetail.getTicket_send_detail_value() > t1.getTicket_send_detail_value()) {
                    return -1;
                }
                return 0;
            }
        });
    }


    /**
     *
     * @param posOrder
     */
    public void createQuitPosOrderByAll(PosOrder posOrder){

    }


    /**
     * 开启消费券分摊
     * @param posOrder
     */
    public PosOrder createQuitPosOrder2ByAll(PosOrder posOrder,PosOrder oldPosOrder,KeyGeneratorBizday mCurrentPosOrderKGB ){
        PosOrder mCurrentPosorder = RetailPosManager.getInstance().copyPosOrder(posOrder);
        mCurrentPosorder.setOrderExternalNo("");
        mCurrentPosorder.setPayments(new ArrayList<Payment>());
        String mCurrentPaymentKGBKeyGBString = mCurrentPosOrderKGB.getKeyGBString();
        EvtLog.d("http:="+mCurrentPaymentKGBKeyGBString);
        mCurrentPosorder.setOrderNo(mCurrentPaymentKGBKeyGBString);
        List<PosOrderDetail> posOrderDetails1 = new ArrayList<>();
        List<PosOrderDetail> dataList = oldPosOrder.getPosOrderDetails();
        posOrderDetails1.addAll(dataList);
        List<PosOrderDetail> posOrderDetails = new ArrayList<>();
        for (PosOrderDetail posOrderDetailold : posOrderDetails1) {

            TagUtils.removeVipTag(posOrderDetailold);

            posOrderDetailold.setOrderDetailPolicyDiscountFlag(false);
            posOrderDetailold.setOrderDetailPolicyPresentFlag(false);
            posOrderDetailold.setOrderDetailPolicyPromotionFlag(false);
            posOrderDetailold.setOrderDetailPolicyPromotionQuantityFlag(false);
            posOrderDetailold.setOrderDetailPolicyPromotionMoneyFlag(false);

            PosOrderDetail posOrderDetail =  RetailPosManager.getInstance().copyPosOrderDetail(posOrderDetailold);
            posOrderDetail.setId(null);
            posOrderDetail.setOrderNo(mCurrentPosorder.getOrderNo());
            if (posOrderDetail.getItemNum() != 0) {
                if (!LibConfig.S_ORDER_DETAIL_PRESENT_NAME.equals(posOrderDetail.getOrderDetailStateName())) {
                    PosOrderDetailState orderState = PosOrderDetailStateUtil.getOrderState(LibConfig.S_ORDER_DETAIL_RETURN);
                    posOrderDetail.setOrderDetailStateCode(orderState.POS_ORDER_DETAIL_STATE_CODE); //状态编码  应在全局定义
                    posOrderDetail.setOrderDetailStateName(orderState.POS_ORDER_DETAIL_STATE_NAME); //状态名称
                } else {
                    PosOrderDetailState orderState = PosOrderDetailStateUtil.getOrderState(LibConfig.S_ORDER_DETAIL_PRESENT);
                    posOrderDetail.setOrderDetailStateCode(orderState.POS_ORDER_DETAIL_STATE_CODE); //状态编码  应在全局定义
                    posOrderDetail.setOrderDetailStateName(orderState.POS_ORDER_DETAIL_STATE_NAME); //状态名称
                }

                PosItem posItem = PosItemImpl.getInstance().getPosItemByItemNum(posOrderDetail.getItemNum());
                if (posOrderDetail.getItemGradeNum() != 0) {
                    PosItemGrade posItemGrade = PosItemImpl.getInstance().getPosItemGradeByItemGradeNum(posOrderDetail.getItemGradeNum(),posItem.getItem_num());
                    if (posItemGrade != null) {
                        posOrderDetail.setItemGradeNum(posItemGrade.getItem_grade_num());
                        posOrderDetail.setPosItemGrade(posItemGrade);
                    }
                }
                posOrderDetail.setPosItem(posItem);
                float orderDetailAmount = posOrderDetail.getOrderDetailAmount();
                posOrderDetail.setOrderChangeAmount(NumberUtil.getNewLongFloat(orderDetailAmount));
                posOrderDetail.setOrderDetailPrice(NumberUtil.getNewFloat((posOrderDetailold.getOrderDetailPaymentMoney() - posOrderDetailold.getOrderDetailShareDiscount())/orderDetailAmount));
                posOrderDetail.setOrderDetailStdPrice(NumberUtil.getNewFloat((posOrderDetailold.getOrderDetailPaymentMoney() - posOrderDetailold.getOrderDetailShareDiscount())/orderDetailAmount));
                posOrderDetail.setOrderDetailPaymentMoney(NumberUtil.getNewFloat(posOrderDetailold.getOrderDetailPaymentMoney() - posOrderDetailold.getOrderDetailShareDiscount()));
                posOrderDetail.setResidueMoney(posOrderDetail.getOrderDetailPaymentMoney());
                posOrderDetail.setOrderDetailMoney(posOrderDetail.getOrderDetailPaymentMoney());
                posOrderDetails.add(posOrderDetail);
            } else {
                posOrderDetail.setQuitCoupons(true);
//                mCouponsDetails.add(posOrderDetail);
            }
            posOrderDetail.setOrderDetailShareDiscount(0f);
            posOrderDetail.setOrderDetailDiscount(0f);
//            PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail);
        }
        mCurrentPosorder.setPosOrderDetails(posOrderDetails);
        mCurrentPosorder.setOrderRefBillno(oldPosOrder.getOrderNo());


        PosOrderState orderStateBean = PosOrderStateUtil.getOrderState(LibConfig.S_ORDER_INIT);
        mCurrentPosorder.setOrderStateCode(orderStateBean.ORDER_STATE_CODE);
        mCurrentPosorder.setOrderMgrDiscountMoney(0);
        mCurrentPosorder.setOrderCouponPaymentMoney(0);
        mCurrentPosorder.setOrderCouponTotalMoney(0);
        mCurrentPosorder.setOrderDiscountMoney(0);
        mCurrentPosorder.setOrderStateName(orderStateBean.ORDER_STATE_NAME);
        mCurrentPosorder.setOrderOperator("" + LibConfig.activeAppUser.getApp_user_name());
        mCurrentPosorder.setOrderPayee("" + LibConfig.activeAppUser.getApp_user_name());
        mCurrentPosorder.setOrderDate(TimeUtil.getInstance().stampToDate(System.currentTimeMillis()));
        Payment newPayment;
        List<Payment> payments = OrderImpl.getInstance().getPaymentList(LibConfig.SYSTEM_BOOK,LibConfig.BRANCH_NUM,oldPosOrder.getOrderNo());

        List<Payment> mReturnPayment = new ArrayList<>();
        for (Payment payment : payments) {
            newPayment = createPaymentByOld(payment.getPaymentMoney(), payment.getPaymentPayBy(), payment,mCurrentPosorder);
            mReturnPayment.add(newPayment);
        }
        mCurrentPosorder.setOrderRound(0);
        mCurrentPosorder.setOrderDiscountMoney(0);
//        mCurrentPosorder= PosOrderCalcUtil.getInstance().calcPosOrderFan(mCurrentPosorder);
//        PosOrderCalcUtil.getInstance().calcPosOrderFan(mCurrentPosorder);
        mCurrentPosorder.setOrderPaymentMoney(-(oldPosOrder.getOrderPaymentMoney() - oldPosOrder.getOrderMgrDiscountMoney()));
        mCurrentPosorder.setOrderTotalMoney(mCurrentPosorder.getOrderPaymentMoney());
        mCurrentPosorder.setPayments(mReturnPayment);

        return mCurrentPosorder;
    }


    private Payment createPaymentByOld(float Money, String payBy, Payment oldpayment,PosOrder mCurrentPosorder) {
        if (Money > 0) Money = Money * -1;
        Payment payment = new Payment();
        payment.setOrderNo(mCurrentPosorder.getOrderNo());
        ShiftTable shiftTable = LibConfig.activeShiftTable;
        PosMachine posMachine = LibConfig.activePosMachine;
        KeyGeneratorBizday paymentKG = KeyGeneratorBizdayImpl.getInstance().createPaymentKG(shiftTable.getSystemBookCode(), shiftTable.getBranchNum(), shiftTable.getShiftTableBizday(), LibConfig.S_PAYMENT_KEY_ITEM, posMachine.getPos_machine_sequence());
        payment.setPaymentNo(paymentKG.getKeyGBString());
        payment.setPaymentPayBy(payBy);
        payment.setPaymentReceive(Money);
        payment.setPaymentMoney(Money);
        payment.setPaymentPaid(payment.getPaymentMoney());
        payment.setPaymentCustNum(oldpayment.getPaymentCustNum());
        payment.setPaymentChange(payment.getPaymentReceive() - payment.getPaymentMoney());
        payment.setPaymentCardPrintNum(oldpayment.getPaymentCardPrintNum());
        payment.setClientFid(oldpayment.getClientFid());
        payment.setWechatOpenId(oldpayment.getWechatOpenId());
        payment.setPaymentCardUserName(oldpayment.getPaymentCardUserName());
        payment.setUploadOk(false);
        payment.setMerchantNum(oldpayment.getMerchantNum());
        payment.setStallNum(oldpayment.getStallNum());
        payment.setVipInfoJson(oldpayment.getVipInfoJson());
        payment.setPaymentMemo(oldpayment.getPaymentMemo());
        return payment;
    }




    /**
     * 订单相同商品相同价格的商品合并
     *
     * @param posOrder
     */
    public PosOrder mergeAllGoods(PosOrder posOrder) {
        Map<String, PosOrderDetail> posOrderDetailMap = new LinkedHashMap<>();
        List<PosOrderDetail> posOrderDetails = posOrder.getPosOrderDetails();
        for (PosOrderDetail posOrderDetail : posOrderDetails) {
            if (LibConfig.C_ORDER_DETAIL_TYPE_COUPON.equals(posOrderDetail.getOrderDetailType()))
                continue;
            if (LibConfig.S_ORDER_DETAIL_PRESENT_NAME.equals(posOrderDetail.getOrderDetailStateName())) {
                posOrderDetailMap.put(UUIDUtils.getUUID32(), posOrderDetail);
                continue;
            }
            //判断有没有手改标记
            if (posOrderDetail.getOrderDetailMemo() != null && posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_CHANGE_TAG)) {
                //看看之前有没有一样商品手改过
                if (posOrderDetailMap.get(posOrderDetail.getItemNum() + "_" + posOrderDetail.getItemGradeNum() + "_" + LibConfig.GOODS_CHANGE_TAG) == null) {
                    //没有就直接设置进去
                    posOrderDetailMap.put(posOrderDetail.getItemNum() + "_" + posOrderDetail.getItemGradeNum() + "_" + LibConfig.GOODS_CHANGE_TAG, posOrderDetail);
                } else {
                    //有先取出
                    PosOrderDetail oldPosOrderDetail = posOrderDetailMap.get(posOrderDetail.getItemNum() + "_" + posOrderDetail.getItemGradeNum() + "_" + LibConfig.GOODS_CHANGE_TAG);
                    //如果手改价格一致 放在一起叠加起来
                    if (oldPosOrderDetail != null && oldPosOrderDetail.getOrderDetailPrice() == posOrderDetail.getOrderDetailPrice()) {
                        oldPosOrderDetail.setOrderDetailAmount(oldPosOrderDetail.getOrderDetailAmount() + posOrderDetail.getOrderDetailAmount());
                        oldPosOrderDetail.setOrderDetailPaymentMoney(oldPosOrderDetail.getOrderDetailPaymentMoney() + posOrderDetail.getOrderDetailMoney());
                        oldPosOrderDetail.setOrderDetailMoney(oldPosOrderDetail.getOrderDetailMoney() + posOrderDetail.getOrderDetailMoney());
                        oldPosOrderDetail.setOrderDetailDiscount(oldPosOrderDetail.getOrderDetailDiscount() + posOrderDetail.getOrderDetailDiscount());
                    } else {
//                        没有直接设置进去
                        posOrderDetailMap.put(posOrderDetail.getItemNum() + "_" + posOrderDetail.getItemGradeNum() + "_" + LibConfig.GOODS_CHANGE_TAG, posOrderDetail);
                    }
                }
            } else {
                //判断是否营销活动一致
                if (posOrderDetailMap.get(posOrderDetail.getItemNum() + "_" + posOrderDetail.getItemGradeNum() + "_" + posOrderDetail.getOrderDetailPolicyFid()) == null) {
                    //不一致直接加进去
                    posOrderDetailMap.put(posOrderDetail.getItemNum() + "_" + posOrderDetail.getItemGradeNum() + "_" + posOrderDetail.getOrderDetailPolicyFid(), posOrderDetail);
                } else {
                    //一致直接叠加
                    PosOrderDetail oldPosOrderDetail = posOrderDetailMap.get(posOrderDetail.getItemNum() + "_" + posOrderDetail.getItemGradeNum() + "_" + posOrderDetail.getOrderDetailPolicyFid());
                    oldPosOrderDetail.setOrderDetailAmount(oldPosOrderDetail.getOrderDetailAmount() + posOrderDetail.getOrderDetailAmount());
                    oldPosOrderDetail.setOrderDetailPaymentMoney(oldPosOrderDetail.getOrderDetailPaymentMoney() + posOrderDetail.getOrderDetailMoney());
                    oldPosOrderDetail.setOrderDetailMoney(oldPosOrderDetail.getOrderDetailMoney() + posOrderDetail.getOrderDetailMoney());
                    oldPosOrderDetail.setOrderDetailDiscount(oldPosOrderDetail.getOrderDetailDiscount() + posOrderDetail.getOrderDetailDiscount());
                }
            }
        }
        LinkedList<PosOrderDetail> newPosOrderDetails = new LinkedList<>();
        for (Map.Entry<String, PosOrderDetail> entry : posOrderDetailMap.entrySet()) {
            newPosOrderDetails.addFirst(entry.getValue());
        }
        posOrder.setPosOrderDetails(newPosOrderDetails);
        return posOrder;
    }



}
