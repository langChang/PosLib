package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.CardTypeParam;
import com.nhsoft.poslib.entity.KeyGeneratorBizday;
import com.nhsoft.poslib.entity.PolicyPromotion;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.PosItemGrade;
import com.nhsoft.poslib.entity.PosItemKit;
import com.nhsoft.poslib.entity.PosMachine;
import com.nhsoft.poslib.entity.order.Payment;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.entity.order.PosOrderKitDetail;
import com.nhsoft.poslib.entity.shift.ShiftTable;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.model.CouponsBean;
import com.nhsoft.poslib.model.PosOrderState;
import com.nhsoft.poslib.model.PosScaleStyleTypeBean;
import com.nhsoft.poslib.model.RedisBean;
import com.nhsoft.poslib.call.impl.KeyGeneratorBizdayImpl;
import com.nhsoft.poslib.call.impl.OrderImpl;
import com.nhsoft.poslib.call.impl.PayStyleImpl;
import com.nhsoft.poslib.call.impl.PosCarryLogImpl;
import com.nhsoft.poslib.call.impl.PosItemImpl;
import com.nhsoft.poslib.service.greendao.PosItemDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2018/12/4 3:06 PM
 * 此类用于：关于订单存储删除的操作
 */
public class PosOrderOperationUtil {

    /**
     * 开始插入订单
     *
     * @param keyGenerator
     */
    public static boolean startInsertOrder(PosOrder posOrder, KeyGeneratorBizday keyGenerator, int orderState) {
        if (posOrder != null) {
            ShiftTable shiftTable = LibConfig.activeShiftTable;
            PosMachine posMachine = LibConfig.activePosMachine;
            posOrder.setOrderNo(keyGenerator.getKeyGBString());
            if (posOrder.getMerchantNum() == 0 && posOrder.getStallNum() == 0) {
                posOrder.setMerchantNum(shiftTable.getMerchantNum());
                posOrder.setStallNum(shiftTable.getStallNum());
            }
            long currentTimeMillis = System.currentTimeMillis();
            String stampToDate = TimeUtil.getInstance().stampToDate(currentTimeMillis);

            List<Payment> payments = posOrder.getPayments();
            for (Payment payment : payments) {
                payment.setMerchantNum(posOrder.getMerchantNum());
                payment.setStallNum(posOrder.getStallNum());

                if (LibConfig.C_PAYMENT_TYPE_CASH_NAME.equals(payment.getPaymentPayBy()) || LibConfig.openDrawPayment.contains(payment.getPaymentPayBy())) {
                    RetailPosManager.getInstance().openDraw();
                }

                if (LibConfig.C_PAYMENT_TYPE_SIGNBILL_NAME.equals(payment.getPaymentPayBy())) {
                    posOrder.setClientFid(payment.getClientFid());
                }
                payment.setOrderNo(posOrder.getOrderNo());
                payment.setSystemBookCode(shiftTable.getSystemBookCode());
                payment.setBranchNum(shiftTable.getBranchNum());
                payment.setShiftTableBizday(shiftTable.getShiftTableBizday());
                payment.setShiftTableNum(shiftTable.getShiftTableNum());
                payment.setPaymentMachine(posMachine.getPos_machine_terminal_id());
                payment.setPaymentTime(stampToDate);
                payment.setPaymentDate(stampToDate);
                payment.setMerchantNum(posOrder.getMerchantNum());
                KeyGeneratorBizday paymentKG = KeyGeneratorBizdayImpl.getInstance().createPaymentKG(shiftTable.getSystemBookCode(), shiftTable.getBranchNum(), shiftTable.getShiftTableBizday(), LibConfig.S_PAYMENT_KEY_ITEM, posMachine.getPos_machine_sequence());
                payment.setPaymentNo(paymentKG.getKeyGBString());
                for (PosScaleStyleTypeBean scaleStyleTypeBean : LibConfig.allPosScaleTypeList) {
                    if (payment.getPaymentPayBy().equals(scaleStyleTypeBean.getPaymentTypeName())) {
                        if (scaleStyleTypeBean.getPosPaymentEqualSource()) {
                            posOrder.setOrderSource(payment.getPaymentPayBy());
                        }
                    }
                }

                String payStyleBankNum = PayStyleImpl.getInstance().getPayStyleBankNum(payment.getPaymentPayBy());
                try {
                    int payCode = Integer.parseInt(payStyleBankNum);
                    payment.setAccountBankNum(payCode);
                } catch (Exception e) {
                    payment.setAccountBankNum(0);
                }

            }
            List<PosOrderDetail> posOrderDetails = posOrder.getPosOrderDetails();
            if (!OrderImpl.getInstance().isHaveCoupons(posOrder)) {

                List<CouponsBean> totalCouponsList = new ArrayList<>();
                if (posOrder.getMercuryConponsList() != null) {
                    totalCouponsList.addAll(posOrder.getMercuryConponsList());
                }


                if (posOrder.getUseConponsList() != null) {
                    if (LibConfig.saleParamsBean.isEnableDiscountShare() && !posOrder.getUseConponsList().isEmpty()) {
                        RetailPosManager.getInstance().calculateShareCoupons(posOrder.getPosOrderDetails(), posOrder.getUseConponsList());
                    }
                    totalCouponsList.addAll(posOrder.getUseConponsList());

                }

                for (CouponsBean couponsBean : totalCouponsList) {
                    PosOrderDetail couponsDetail = ConversionUtil.createCouponsDetail(couponsBean);
                    couponsDetail.setPaymentBalance(couponsBean.getTicket_send_detail_value());
                    posOrderDetails.add(couponsDetail);
                }
            }


            posOrder.setOrderDetailItemCount(posOrderDetails.size());

            float policyPromotionMoney = 0;
            for (int i = 1; i <= posOrderDetails.size(); i++) {

                PosOrderDetail posOrderDetail = posOrderDetails.get(i - 1);
                if (LibConfig.S_ORDER_INIT == orderState && posOrderDetail.getPosItem().getPosItemKits() != null && !posOrderDetail.getPosItem().getPosItemKits().isEmpty()) {
                    posOrderDetail.setKitAmountStr(new Gson().toJson(posOrderDetail.getPosItem().getPosItemKits()));
                }
                if (posOrder.getMerchantNum() > 0) {
                    posOrderDetail.setStall_num(posOrder.getStallNum());
                }

//                做分摊时候用的
//                if(posOrderDetail.getPosItem() != null && LibConfig.saleParamsBean.isEnableDiscountShare()){
//                    posOrderDetail.setCouponShareMoney(NumberUtil.getNewFloat(posOrderDetail.getOrderDetailPaymentMoney() - posOrderDetail.getResidueMoney()));
//                }
//                posOrderDetail.setCouponShareMoney(0f);
                posOrderDetail.setId(null);
                posOrderDetail.setOrderNo(keyGenerator.getKeyGBString());
                posOrderDetail.setBranchNum(shiftTable.getBranchNum());
                posOrderDetail.setOrderDetailNum(i);
                posOrderDetail.setSystemBookCode(shiftTable.getSystemBookCode());
                if (!TextUtils.isEmpty(posOrderDetail.getOrderDetailPolicyFid())) {
                    policyPromotionMoney += posOrderDetail.getOrderDetailDiscount();
                    if (LibConfig.S_ORDER_COMPLETE == orderState) {
                        for (PolicyPromotion policyPromotion : LibConfig.allVipOncePolicyPromotionList) {
                            if (policyPromotion.getPolicy_promotion_no().equals(posOrderDetail.getOrderDetailPolicyFid())) {

                                if (LibConfig.activeVipMember != null) {
                                    RedisBean redisBean = RetailPosManager.getInstance().createPolicyRedisBean(LibConfig.activeVipMember, posOrderDetail.getOrderDetailPolicyFid());
                                    posOrder.setRedisBean(redisBean);
                                    LibConfig.sVipEnjoyPromotion.put(redisBean.getVip_id(), policyPromotion.getPolicy_promotion_no());
                                }

                                if (LibConfig.discountVipMember != null) {
                                    RedisBean redisBean = RetailPosManager.getInstance().createPolicyRedisBean(LibConfig.discountVipMember, posOrderDetail.getOrderDetailPolicyFid());
                                    posOrder.setRedisBean(redisBean);
                                    LibConfig.sVipEnjoyPromotion.put(redisBean.getVip_id(), policyPromotion.getPolicy_promotion_no());
                                }


//                                    if (LibConfig.activeVipMember != null) {
//                                        RedisBean redisBean = new RedisBean();
//                                        if (!TextUtils.isEmpty(LibConfig.activeVipMember.getCustomer_id())) {
//                                            LibConfig.sVipEnjoyPromotion.put(LibConfig.activeVipMember.getCustomer_id(), policyPromotion.getPolicy_promotion_no());
//                                            redisBean.setRedis_key("PolicyPromotion_" + policyPromotion.getPolicy_promotion_no() + "_" + LibConfig.activeVipMember.getCustomer_id());
//                                            redisBean.setRedis_time(TimeUtil.getSubTime(TimeUtil.getInstance().getNowDateString(), policyPromotion.getPolicy_promotion_time_to()));
//                                            redisBean.setRedis_value("YES");
//                                            posOrder.setRedisBean(redisBean);
//                                        } else {
//                                            LibConfig.sVipEnjoyPromotion.put(LibConfig.activeVipMember.getCard_user_num(), policyPromotion.getPolicy_promotion_no());
//                                            redisBean.setRedis_key("PolicyPromotion_" + policyPromotion.getPolicy_promotion_no() + "_" + LibConfig.activeVipMember.getCard_user_num());
//                                            redisBean.setRedis_time(TimeUtil.getSubTime(TimeUtil.getInstance().getNowDateString(), policyPromotion.getPolicy_promotion_time_to()));
//                                            redisBean.setRedis_value("YES");
//                                            posOrder.setRedisBean(redisBean);
//                                        }
//                                    }

//                                    if (LibConfig.discountVipMember != null) {
//                                        RedisBean redisBean = new RedisBean();
//                                        if (!TextUtils.isEmpty(LibConfig.discountVipMember.getCustomer_id())) {
//                                            LibConfig.sVipEnjoyPromotion.put(LibConfig.discountVipMember.getCustomer_id(), policyPromotion.getPolicy_promotion_no());
//                                            redisBean.setRedis_key("PolicyPromotion_" + policyPromotion.getPolicy_promotion_no() + "_" + LibConfig.discountVipMember.getCustomer_id());
//                                            redisBean.setRedis_time(TimeUtil.getSubTime(TimeUtil.getInstance().getNowDateString(), policyPromotion.getPolicy_promotion_time_to()));
//                                            redisBean.setRedis_value("YES");
//                                            posOrder.setRedisBean(redisBean);
//                                        } else {
//                                            LibConfig.sVipEnjoyPromotion.put(LibConfig.discountVipMember.getCard_user_num(), policyPromotion.getPolicy_promotion_no());
//                                            redisBean.setRedis_key("PolicyPromotion_" + policyPromotion.getPolicy_promotion_no() + "_" + LibConfig.discountVipMember.getCard_user_num());
//                                            redisBean.setRedis_time(TimeUtil.getSubTime(TimeUtil.getInstance().getNowDateString(), policyPromotion.getPolicy_promotion_time_to()));
//                                            redisBean.setRedis_value("YES");
//                                            posOrder.setRedisBean(redisBean);
//                                        }
//                                    }

                            }
                        }
                    }

                } else {
                    posOrderDetail.setOrder_detail_merchat_rate(null);
                }
                if ((LibConfig.S_ORDER_COMPLETE == orderState || LibConfig.S_ORDER_CANCEL == orderState) && posOrderDetail.getPosItem() != null) {
                    if (posOrderDetail.getPosItem().getItem_type() == 4 || posOrderDetail.getPosItem().getItem_type() == 8) {
                        List<PosOrderKitDetail> posOrderDetailKitGoods = createPosOrderDetailKitGoods(posOrderDetail);
                        if (posOrderDetailKitGoods.size() > 0) {
                            posOrderDetail.setPosOrderKitDetails(posOrderDetailKitGoods);
                        }


                    }
                }
                if ((LibConfig.S_ORDER_COMPLETE == orderState || LibConfig.S_ORDER_CANCEL == orderState) && LibConfig.S_ORDER_DETAIL_PRESENT_NAME.equals(posOrderDetail.getOrderDetailStateName())) {
                    PosCarryLogImpl.tryPresentGoods(posOrderDetail);
                }


            }

            if ((LibConfig.S_ORDER_COMPLETE == orderState || LibConfig.S_ORDER_CANCEL == orderState) && posOrder.getOrderMgrDiscountMoney() != 0) {
                PosCarryLogImpl.tryOrderMgr(posOrder);
            }

            posOrder.setOrderPromotionDiscountMoney(policyPromotionMoney);
            posOrder.setShiftTableBizday(shiftTable.getShiftTableBizday());
            posOrder.setShiftTableNum(shiftTable.getShiftTableNum());
            posOrder.setSystemBookCode(shiftTable.getSystemBookCode());
            posOrder.setBranchNum(shiftTable.getBranchNum());


            PosOrderState orderStateBean = PosOrderStateUtil.getOrderState(orderState);
            if (TextUtils.isEmpty(posOrder.getOrderStateName())) {
                posOrder.setOrderStateCode(orderStateBean.ORDER_STATE_CODE);
                posOrder.setOrderStateName(orderStateBean.ORDER_STATE_NAME);
            } else {
                String orderStateName = posOrder.getOrderStateName();
                if (!orderStateName.contains(orderStateBean.ORDER_STATE_NAME)) {
                    posOrder.setOrderStateCode(posOrder.getOrderStateCode() + orderStateBean.ORDER_STATE_CODE);
                    posOrder.setOrderStateName(posOrder.getOrderStateName() + "|" + orderStateBean.ORDER_STATE_NAME);
                }
            }

            posOrder.setStorehouseNum(posMachine.getStorehouse_num());
            posOrder.setOrderMachine(posMachine.getPos_machine_terminal_id());
            posOrder.setOrderPayee(shiftTable.getShiftTableUserName());
            posOrder.setOrderOperator(shiftTable.getShiftTableUserName());
            posOrder.setOrderOperateTime(TimeUtil.getInstance().stampToDate(System.currentTimeMillis()));
            posOrder.setOrderTime(TimeUtil.getInstance().stampToDate(System.currentTimeMillis()));

            posOrder.setOrderMgrDiscountMoney(posOrder.getOrderMgrDiscountMoney() + posOrder.getQuickZeroMoney());
            if (LibConfig.activeVipMember != null) {
                posOrder.setCustomerId(LibConfig.activeVipMember.getCustomer_id());
                if (!TextUtils.isEmpty(LibConfig.activeVipMember.getCard_user_num())) {
                    posOrder.setOrderCardUserNum(Integer.parseInt(LibConfig.activeVipMember.getCard_user_num()));
                }

                posOrder.setOrderCardUser(LibConfig.activeVipMember.getCard_user_cust_name());
                if (!TextUtils.isEmpty(LibConfig.activeVipMember.getCard_user_type_code())) {
                    posOrder.setOrderCardType((int) Long.parseLong(LibConfig.activeVipMember.getCard_user_type_code()));
                }
                posOrder.setOrderPrintedNum(LibConfig.activeVipMember.getCard_print_num());

                if (!TextUtils.isEmpty(LibConfig.activeVipMember.getCard_user_type_code())) {
                    CardTypeParam cardType = RetailPosManager.getCardType(LibConfig.activeVipMember.getCard_user_type_code());
                    if (cardType != null) {
                        posOrder.setOrderCardTypeDesc(cardType.getType_name());
                    }
                }

            }


            if (keyGenerator.getSystemBookCode() != null) {
                KeyGeneratorBizday newKgb = KeyGeneratorBizdayImpl.getInstance().createPosOrderKG(shiftTable.getSystemBookCode(), shiftTable.getBranchNum(), shiftTable.getShiftTableBizday(), LibConfig.S_POS_ORDER_KEY_ITEM, posMachine.getPos_machine_sequence());
                if (newKgb.getKeyGBString().equals(keyGenerator.getKeyGBString())) {
                    KeyGeneratorBizdayImpl.getInstance().saveKeyGeneratorBizday(keyGenerator);
                }
            }

            if (orderState == LibConfig.S_ORDER_CANCEL) {
                PosOrderStateUtil.setPosOrderByCancel(posOrder);
                boolean b = OrderImpl.getInstance().doPayment(posOrder);

            }
            return true;
        }

        return false;
    }


    /**
     * 开始插入订单
     *
     * @param keyGenerator
     */
    public static boolean startInsertOnlineOrder(PosOrder posOrder, KeyGeneratorBizday keyGenerator, int orderState) {
        if (posOrder != null) {
            ShiftTable shiftTable = LibConfig.activeShiftTable;
            PosMachine posMachine = LibConfig.activePosMachine;
            posOrder.setOrderNo(keyGenerator.getKeyGBString());
            if (posOrder.getMerchantNum() == 0 && posOrder.getStallNum() == 0) {
                posOrder.setMerchantNum(shiftTable.getMerchantNum());
                posOrder.setStallNum(shiftTable.getStallNum());
            }
            long currentTimeMillis = System.currentTimeMillis();
            String stampToDate = TimeUtil.getInstance().stampToDate(currentTimeMillis);

            List<Payment> payments = posOrder.getPayments();
            for (Payment payment : payments) {
                payment.setMerchantNum(posOrder.getMerchantNum());
                payment.setStallNum(posOrder.getStallNum());

                if (LibConfig.C_PAYMENT_TYPE_CASH_NAME.equals(payment.getPaymentPayBy()) || LibConfig.openDrawPayment.contains(payment.getPaymentPayBy())) {
                    RetailPosManager.getInstance().openDraw();
                }

                if (LibConfig.C_PAYMENT_TYPE_SIGNBILL_NAME.equals(payment.getPaymentPayBy())) {
                    posOrder.setClientFid(payment.getClientFid());
                }
                payment.setOrderNo(posOrder.getOrderNo());
                payment.setSystemBookCode(shiftTable.getSystemBookCode());
                payment.setBranchNum(shiftTable.getBranchNum());
                payment.setShiftTableBizday(shiftTable.getShiftTableBizday());
                payment.setShiftTableNum(shiftTable.getShiftTableNum());
                payment.setPaymentMachine(posMachine.getPos_machine_terminal_id());
                payment.setPaymentTime(stampToDate);
                payment.setPaymentDate(stampToDate);
                payment.setMerchantNum(posOrder.getMerchantNum());
                KeyGeneratorBizday paymentKG = KeyGeneratorBizdayImpl.getInstance().createPaymentKG(shiftTable.getSystemBookCode(), shiftTable.getBranchNum(), shiftTable.getShiftTableBizday(), LibConfig.S_PAYMENT_KEY_ITEM, posMachine.getPos_machine_sequence());
                payment.setPaymentNo(paymentKG.getKeyGBString());
                for (PosScaleStyleTypeBean scaleStyleTypeBean : LibConfig.allPosScaleTypeList) {
                    if (payment.getPaymentPayBy().equals(scaleStyleTypeBean.getPaymentTypeName())) {
                        if (scaleStyleTypeBean.getPosPaymentEqualSource()) {
                            posOrder.setOrderSource(payment.getPaymentPayBy());
                        }
                    }
                }

                String payStyleBankNum = PayStyleImpl.getInstance().getPayStyleBankNum(payment.getPaymentPayBy());
                try {
                    int payCode = Integer.parseInt(payStyleBankNum);
                    payment.setAccountBankNum(payCode);
                } catch (Exception e) {
                    payment.setAccountBankNum(0);
                }

            }
            List<PosOrderDetail> posOrderDetails = posOrder.getPosOrderDetails();
            if (!OrderImpl.getInstance().isHaveCoupons(posOrder)) {

                List<CouponsBean> totalCouponsList = new ArrayList<>();
                if (posOrder.getMercuryConponsList() != null) {
                    totalCouponsList.addAll(posOrder.getMercuryConponsList());
                }


                if (posOrder.getUseConponsList() != null) {
                    totalCouponsList.addAll(posOrder.getUseConponsList());
                }

                for (CouponsBean couponsBean : totalCouponsList) {
                    PosOrderDetail couponsDetail = ConversionUtil.createCouponsDetail(couponsBean);
                    couponsDetail.setPaymentBalance(couponsBean.getTicket_send_detail_value());
                    posOrderDetails.add(couponsDetail);
                }
            }


            posOrder.setOrderDetailItemCount(posOrderDetails.size());

            float policyPromotionMoney = 0;
            for (int i = 1; i <= posOrderDetails.size(); i++) {

                PosOrderDetail posOrderDetail = posOrderDetails.get(i - 1);
                if (LibConfig.S_ORDER_INIT == orderState && posOrderDetail.getPosItem().getPosItemKits() != null && !posOrderDetail.getPosItem().getPosItemKits().isEmpty()) {
                    posOrderDetail.setKitAmountStr(new Gson().toJson(posOrderDetail.getPosItem().getPosItemKits()));
                }
                if (posOrder.getMerchantNum() > 0) {
                    posOrderDetail.setStall_num(posOrder.getStallNum());
                }

//                做分摊时候用的
//                if(posOrderDetail.getPosItem() != null && LibConfig.saleParamsBean.isEnableDiscountShare()){
//                    posOrderDetail.setCouponShareMoney(NumberUtil.getNewFloat(posOrderDetail.getOrderDetailPaymentMoney() - posOrderDetail.getResidueMoney()));
//                }
//                posOrderDetail.setCouponShareMoney(0f);
                posOrderDetail.setId(null);
                posOrderDetail.setOrderNo(keyGenerator.getKeyGBString());
                posOrderDetail.setBranchNum(shiftTable.getBranchNum());
                posOrderDetail.setOrderDetailNum(i);
                posOrderDetail.setSystemBookCode(shiftTable.getSystemBookCode());
                if (!TextUtils.isEmpty(posOrderDetail.getOrderDetailPolicyFid())) {
                    policyPromotionMoney += posOrderDetail.getOrderDetailDiscount();
                    if (LibConfig.S_ORDER_COMPLETE == orderState) {
                        for (PolicyPromotion policyPromotion : LibConfig.allVipOncePolicyPromotionList) {
                            if (policyPromotion.getPolicy_promotion_no().equals(posOrderDetail.getOrderDetailPolicyFid())) {
                                if (LibConfig.activeVipMember != null) {
                                    RedisBean redisBean = new RedisBean();
                                    if (!TextUtils.isEmpty(LibConfig.activeVipMember.getCustomer_id())) {
                                        LibConfig.sVipEnjoyPromotion.put(LibConfig.activeVipMember.getCustomer_id(), policyPromotion.getPolicy_promotion_no());
                                        redisBean.setRedis_key("PolicyPromotion_" + policyPromotion.getPolicy_promotion_no() + "_" + LibConfig.activeVipMember.getCustomer_id());
                                        redisBean.setRedis_time(TimeUtil.getSubTime(TimeUtil.getInstance().getNowDateString(), policyPromotion.getPolicy_promotion_time_to()));
                                        redisBean.setRedis_value("YES");
                                        posOrder.setRedisBean(redisBean);
                                    } else {
                                        LibConfig.sVipEnjoyPromotion.put(LibConfig.activeVipMember.getCard_user_num(), policyPromotion.getPolicy_promotion_no());
                                        redisBean.setRedis_key("PolicyPromotion_" + policyPromotion.getPolicy_promotion_no() + "_" + LibConfig.activeVipMember.getCard_user_num());
                                        redisBean.setRedis_time(TimeUtil.getSubTime(TimeUtil.getInstance().getNowDateString(), policyPromotion.getPolicy_promotion_time_to()));
                                        redisBean.setRedis_value("YES");
                                        posOrder.setRedisBean(redisBean);
                                    }
                                }

                                if (LibConfig.discountVipMember != null) {
                                    RedisBean redisBean = new RedisBean();
                                    if (!TextUtils.isEmpty(LibConfig.discountVipMember.getCustomer_id())) {
                                        LibConfig.sVipEnjoyPromotion.put(LibConfig.discountVipMember.getCustomer_id(), policyPromotion.getPolicy_promotion_no());
                                        redisBean.setRedis_key("PolicyPromotion_" + policyPromotion.getPolicy_promotion_no() + "_" + LibConfig.discountVipMember.getCustomer_id());
                                        redisBean.setRedis_time(TimeUtil.getSubTime(TimeUtil.getInstance().getNowDateString(), policyPromotion.getPolicy_promotion_time_to()));
                                        redisBean.setRedis_value("YES");
                                        posOrder.setRedisBean(redisBean);
                                    } else {
                                        LibConfig.sVipEnjoyPromotion.put(LibConfig.discountVipMember.getCard_user_num(), policyPromotion.getPolicy_promotion_no());
                                        redisBean.setRedis_key("PolicyPromotion_" + policyPromotion.getPolicy_promotion_no() + "_" + LibConfig.discountVipMember.getCard_user_num());
                                        redisBean.setRedis_time(TimeUtil.getSubTime(TimeUtil.getInstance().getNowDateString(), policyPromotion.getPolicy_promotion_time_to()));
                                        redisBean.setRedis_value("YES");
                                        posOrder.setRedisBean(redisBean);
                                    }
                                }

                            }
                        }
                    }

                } else {
                    posOrderDetail.setOrder_detail_merchat_rate(null);
                }
                if ((LibConfig.S_ORDER_COMPLETE == orderState || LibConfig.S_ORDER_CANCEL == orderState) && posOrderDetail.getPosItem() != null) {
                    if (posOrderDetail.getPosItem().getItem_type() == 4 || posOrderDetail.getPosItem().getItem_type() == 8) {
                        List<PosOrderKitDetail> posOrderDetailKitGoods = createPosOrderDetailKitGoods(posOrderDetail);
                        if (posOrderDetailKitGoods.size() > 0) {
                            posOrderDetail.setPosOrderKitDetails(posOrderDetailKitGoods);
                        }


                    }
                }
                if ((LibConfig.S_ORDER_COMPLETE == orderState || LibConfig.S_ORDER_CANCEL == orderState) && LibConfig.S_ORDER_DETAIL_PRESENT_NAME.equals(posOrderDetail.getOrderDetailStateName())) {
                    PosCarryLogImpl.tryPresentGoods(posOrderDetail);
                }


            }

            if ((LibConfig.S_ORDER_COMPLETE == orderState || LibConfig.S_ORDER_CANCEL == orderState) && posOrder.getOrderMgrDiscountMoney() != 0) {
                PosCarryLogImpl.tryOrderMgr(posOrder);
            }

            posOrder.setOrderPromotionDiscountMoney(policyPromotionMoney);
            posOrder.setShiftTableBizday(shiftTable.getShiftTableBizday());
            posOrder.setShiftTableNum(shiftTable.getShiftTableNum());
            posOrder.setSystemBookCode(shiftTable.getSystemBookCode());
            posOrder.setBranchNum(shiftTable.getBranchNum());


            PosOrderState orderStateBean = PosOrderStateUtil.getOrderState(orderState);
            if (TextUtils.isEmpty(posOrder.getOrderStateName())) {
                posOrder.setOrderStateCode(orderStateBean.ORDER_STATE_CODE);
                posOrder.setOrderStateName(orderStateBean.ORDER_STATE_NAME);
            } else {
                String orderStateName = posOrder.getOrderStateName();
                if (!orderStateName.contains(orderStateBean.ORDER_STATE_NAME)) {
                    posOrder.setOrderStateCode(posOrder.getOrderStateCode() + orderStateBean.ORDER_STATE_CODE);
                    posOrder.setOrderStateName(posOrder.getOrderStateName() + "|" + orderStateBean.ORDER_STATE_NAME);
                }
            }

            posOrder.setStorehouseNum(posMachine.getStorehouse_num());
            posOrder.setOrderMachine(posMachine.getPos_machine_terminal_id());
            posOrder.setOrderPayee(shiftTable.getShiftTableUserName());
            posOrder.setOrderOperator(shiftTable.getShiftTableUserName());
            posOrder.setOrderOperateTime(TimeUtil.getInstance().stampToDate(System.currentTimeMillis()));
            posOrder.setOrderTime(TimeUtil.getInstance().stampToDate(System.currentTimeMillis()));

            posOrder.setOrderMgrDiscountMoney(posOrder.getOrderMgrDiscountMoney() + posOrder.getQuickZeroMoney());
            if (LibConfig.activeVipMember != null) {
                posOrder.setCustomerId(LibConfig.activeVipMember.getCustomer_id());
                if (!TextUtils.isEmpty(LibConfig.activeVipMember.getCard_user_num())) {
                    posOrder.setOrderCardUserNum(Integer.parseInt(LibConfig.activeVipMember.getCard_user_num()));
                }

                posOrder.setOrderCardUser(LibConfig.activeVipMember.getCard_user_cust_name());
                if (!TextUtils.isEmpty(LibConfig.activeVipMember.getCard_user_type_code())) {
                    posOrder.setOrderCardType((int) Long.parseLong(LibConfig.activeVipMember.getCard_user_type_code()));
                }
                posOrder.setOrderPrintedNum(LibConfig.activeVipMember.getCard_print_num());

                if (!TextUtils.isEmpty(LibConfig.activeVipMember.getCard_user_type_code())) {
                    CardTypeParam cardType = RetailPosManager.getCardType(LibConfig.activeVipMember.getCard_user_type_code());
                    if (cardType != null) {
                        posOrder.setOrderCardTypeDesc(cardType.getType_name());
                    }
                }

            }


            if (keyGenerator.getSystemBookCode() != null) {
                KeyGeneratorBizday newKgb = KeyGeneratorBizdayImpl.getInstance().createPosOrderKG(shiftTable.getSystemBookCode(), shiftTable.getBranchNum(), shiftTable.getShiftTableBizday(), LibConfig.S_POS_ORDER_KEY_ITEM, posMachine.getPos_machine_sequence());
                if (newKgb.getKeyGBString().equals(keyGenerator.getKeyGBString())) {
                    KeyGeneratorBizdayImpl.getInstance().saveKeyGeneratorBizday(keyGenerator);
                }
            }

            if (orderState == LibConfig.S_ORDER_CANCEL) {
                PosOrderStateUtil.setPosOrderByCancel(posOrder);
                boolean b = OrderImpl.getInstance().doPayment(posOrder);

            }
            return true;
        }

        return false;
    }

    /**
     * 创建posorderKit
     *
     * @param posOrderDetail
     * @return
     */
    private static List<PosOrderKitDetail> createPosOrderDetailKitGoods(PosOrderDetail posOrderDetail) {

        PosItem detailPosItem = posOrderDetail.getPosItem();
        List<PosItemKit> posItemKits = new ArrayList<>();
        if (detailPosItem != null && detailPosItem.getPosItemKits() != null && !detailPosItem.getPosItemKits().isEmpty()) {
            posItemKits = detailPosItem.getPosItemKits();
//            Log.e("kit_json",new Gson().toJson(posItemKits));
        }

        List<PosOrderKitDetail> posOrderKitDetails = new ArrayList<>();
        PosItemDao posItemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
        List<PosItemKit> groupPosKits = PosItemImpl.getInstance().getGroupPosItemByItemNum(posOrderDetail.getItemNum());
        int order_num = 1;
        if (groupPosKits != null && groupPosKits.size() > 0) {
            for (PosItemKit posItemKit : groupPosKits) {
                PosItem posItem = posItemDao.load(posItemKit.getKit_item_num());
                if (posItem == null) continue;

                if (posItem != null && posItem.getItem_type() != 11 && !posItem.getItem_eliminative_flag()) {
                    PosItemGrade posItemGrade = null;
                    if (posItem.getItem_type() == 10) {
                        List<PosItemGrade> allItemGrade = PosItemImpl.getInstance().getAllItemGrade(posItem.getItem_num());
                        for (PosItemGrade itemPosItemGrade : allItemGrade) {
                            if (posItemGrade == null) {
                                posItemGrade = itemPosItemGrade;
                            } else {
                                if (itemPosItemGrade.getItem_grade_type() < posItemGrade.getItem_grade_type()) {
                                    posItemGrade = itemPosItemGrade;
                                }
                            }
                        }
                    }

                    PosOrderKitDetail posOrderKitDetail = new PosOrderKitDetail();
                    posOrderKitDetail.setOrderNo(posOrderDetail.getOrderNo());
                    posOrderKitDetail.setOrderDetailNum(posOrderDetail.getOrderDetailNum());
                    posOrderKitDetail.setOrderKitDetailNum(order_num);
                    order_num++;
                    posOrderKitDetail.setOrderKitDetailStateCode(posOrderDetail.getOrderDetailStateCode()); //状态编码  应在全局定义
                    posOrderKitDetail.setOrderKitDetailOrderState(5); //状态名称
                    posOrderKitDetail.setItemNum(posItem.getItem_num());
                    posOrderKitDetail.setOrderKitDetailItemName(posItemGrade == null ? posItem.getItem_name() : posItemGrade.getItem_grade_name());


                    float kitDetailAmount = posItemKit.getPos_item_kit_amount() * posOrderDetail.getOrderDetailAmount();
                    if (!posItemKits.isEmpty()) {
                        for (PosItemKit posItemKit1 : posItemKits) {
                            if (posItemKit1.getKit_item_num() == posItem.getItem_num()) {
                                kitDetailAmount = NumberUtil.getNewLongFloat((float) (posOrderDetail.getOrderDetailAmount() * posItemKit1.getInput_kit_amount() / posItemKit1.getInput_host_amount()));
                            }
                        }
                    }

                    posOrderKitDetail.setOrderKitDetailAmount(kitDetailAmount);

                    posOrderKitDetail.setOrderKitDetailDepartment(posOrderDetail.getOrderDetailItemDepartment());
                    posOrderKitDetail.setSystemBookCode(posOrderDetail.getSystemBookCode());
                    posOrderKitDetail.setOrderKitDetailBookCode(posOrderDetail.getSystemBookCode());
                    posOrderKitDetail.setBranchNum(posOrderDetail.getBranchNum());
                    posOrderKitDetail.setOrderKitDetailBizday(LibConfig.activeShiftTable.getShiftTableBizday());
                    posOrderKitDetail.setShiftTableNum(LibConfig.activeShiftTable.getShiftTableNum());
                    posOrderKitDetail.setBranchNum(LibConfig.activeShiftTable.getBranchNum());
                    posOrderKitDetail.setOrderKitDetailBranchNum(LibConfig.activeShiftTable.getBranchNum());
                    posOrderKitDetail.setOrderKitDetailCost(0);
//                    if (LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived() && posItem.getBranch_regular_price() !=0) {
//                        posOrderKitDetail.setOrderKitDetailStdPrice(posItemGrade == null ? posItem.getBranch_regular_price() : posItemGrade.getBranch_grade_regular_price() == 0 ? posItem.getBranch_regular_price() : posItemGrade.getBranch_grade_regular_price());//标准单价
//                    } else {
//                        posOrderKitDetail.setOrderKitDetailStdPrice(posItemGrade == null ? posItem.getItem_regular_price() : posItemGrade.getBranch_grade_regular_price() == 0 ? posItem.getItem_regular_price() : posItemGrade.getBranch_grade_regular_price());//标准单价
//                    }
                    float finalRegularPrice = PriceUtil.getItemRegularPrice(posItem, posItemGrade);
                    posOrderKitDetail.setOrderKitDetailStdPrice(finalRegularPrice);

                    posOrderDetail.setOrderDetailHasKit(true);
                    posOrderKitDetails.add(posOrderKitDetail);
                }

            }
        }
        if (posOrderKitDetails.size() > 0) {
            float orderKitDetailtotalPaymentMoney = 0;
            for (PosOrderKitDetail posOrderKitDetail : posOrderKitDetails) {
                orderKitDetailtotalPaymentMoney += posOrderKitDetail.getOrderKitDetailAmount() * posOrderKitDetail.getOrderKitDetailStdPrice();
            }

            PosOrderKitDetail posOrderKitDetail;
            float orderKitUseTotalMoney = posOrderDetail.getOrderDetailPaymentMoney();//用掉多少钱
            float orderKitDiscount = posOrderDetail.getOrderDetailDiscount();//多少折扣
            float orderShareDiscount = posOrderDetail.getOrderDetailShareDiscount() == null ? 0 : posOrderDetail.getOrderDetailShareDiscount();//多少折扣

            if (orderKitDetailtotalPaymentMoney > 0) {
                for (int i = 0; i < posOrderKitDetails.size(); i++) {
                    posOrderKitDetail = posOrderKitDetails.get(i);
                    if (i == posOrderKitDetails.size() - 1) {
                        posOrderKitDetail.setOrderKitDetailMoney(orderKitUseTotalMoney);
                        posOrderKitDetail.setOrderKitDetailPrice(posOrderKitDetail.getOrderKitDetailAmount() == 0 ? 0 : orderKitUseTotalMoney / posOrderKitDetail.getOrderKitDetailAmount());
                        posOrderKitDetail.setOrderKitDetailPaymentMoney(orderKitUseTotalMoney);
                        posOrderKitDetail.setOrderKitDetailDiscount(orderKitDiscount);
                        posOrderKitDetail.setOrderKitDetailShareDiscount(orderShareDiscount);

                    } else {
                        posOrderKitDetail.setOrderKitDetailMoney(NumberUtil.getNewFloat((posOrderKitDetail.getOrderKitDetailStdPrice() * posOrderKitDetail.getOrderKitDetailAmount() / orderKitDetailtotalPaymentMoney) * posOrderDetail.getOrderDetailPaymentMoney()));
                        posOrderKitDetail.setOrderKitDetailDiscount(NumberUtil.getNewFloat((posOrderKitDetail.getOrderKitDetailStdPrice() * posOrderKitDetail.getOrderKitDetailAmount() / orderKitDetailtotalPaymentMoney) * posOrderDetail.getOrderDetailDiscount()));
                        posOrderKitDetail.setOrderKitDetailShareDiscount(NumberUtil.getNewFloat((posOrderKitDetail.getOrderKitDetailStdPrice() * posOrderKitDetail.getOrderKitDetailAmount() / orderKitDetailtotalPaymentMoney) * posOrderDetail.getOrderDetailShareDiscount()));
                        posOrderKitDetail.setOrderKitDetailPrice((posOrderKitDetail.getOrderKitDetailStdPrice() / orderKitDetailtotalPaymentMoney) * posOrderDetail.getOrderDetailPaymentMoney());
                        posOrderKitDetail.setOrderKitDetailPaymentMoney(posOrderKitDetail.getOrderKitDetailMoney());
                        orderKitUseTotalMoney -= posOrderKitDetail.getOrderKitDetailPaymentMoney();
                        orderKitDiscount -= posOrderKitDetail.getOrderKitDetailDiscount();
                        orderShareDiscount -= posOrderKitDetail.getOrderKitDetailShareDiscount();
                    }
                }

            } else {

                float kitGoodsPaymentMoney = posOrderDetail.getOrderDetailPaymentMoney() / posOrderKitDetails.size();


                for (int i = 0; i < posOrderKitDetails.size(); i++) {
                    posOrderKitDetail = posOrderKitDetails.get(i);
                    if (i == posOrderKitDetails.size() - 1) {
                        posOrderKitDetail.setOrderKitDetailPrice(orderKitUseTotalMoney / posOrderKitDetail.getOrderKitDetailAmount());
                        posOrderKitDetail.setOrderKitDetailMoney(orderKitUseTotalMoney);
                        posOrderKitDetail.setOrderKitDetailPaymentMoney(orderKitUseTotalMoney);
                        posOrderKitDetail.setOrderKitDetailDiscount(orderKitDiscount);
                        posOrderKitDetail.setOrderKitDetailShareDiscount(orderShareDiscount);
                    } else {
                        posOrderKitDetail.setOrderKitDetailPrice(NumberUtil.getNewFloat(kitGoodsPaymentMoney / posOrderKitDetail.getOrderKitDetailAmount()));
                        posOrderKitDetail.setOrderKitDetailMoney(NumberUtil.getNewFloat(posOrderKitDetail.getOrderKitDetailPrice() * posOrderKitDetail.getOrderKitDetailAmount()));
                        posOrderKitDetail.setOrderKitDetailPaymentMoney(posOrderKitDetail.getOrderKitDetailMoney());
                        posOrderKitDetail.setOrderKitDetailDiscount(NumberUtil.getNewFloat(posOrderKitDetail.getOrderKitDetailPaymentMoney() / posOrderDetail.getOrderDetailPaymentMoney() * posOrderDetail.getOrderDetailDiscount()));
                        posOrderKitDetail.setOrderKitDetailShareDiscount(NumberUtil.getNewFloat(posOrderKitDetail.getOrderKitDetailPaymentMoney() / posOrderDetail.getOrderDetailPaymentMoney() * posOrderDetail.getOrderDetailShareDiscount()));
                        orderKitUseTotalMoney -= posOrderKitDetail.getOrderKitDetailPaymentMoney();
                        orderKitDiscount -= posOrderKitDetail.getOrderKitDetailDiscount();
                        orderShareDiscount -= posOrderKitDetail.getOrderKitDetailShareDiscount();
                    }
                }
            }
        }

        if (posOrderKitDetails != null && !posOrderKitDetails.isEmpty()) {
            for (PosOrderKitDetail posOrderKitDetail : posOrderKitDetails) {
                if (posOrderKitDetail.getOrderKitDetailAmount() > 0.001) {
                    posOrderKitDetail.setOrderKitDetailMoney(posOrderKitDetail.getOrderKitDetailPaymentMoney() + posOrderKitDetail.getOrderKitDetailDiscount());
                    posOrderKitDetail.setOrderKitDetailStdPrice(NumberUtil.getNewFloat(posOrderKitDetail.getOrderKitDetailMoney() / posOrderKitDetail.getOrderKitDetailAmount()));
                }
            }
        }
        return posOrderKitDetails;
    }

}
