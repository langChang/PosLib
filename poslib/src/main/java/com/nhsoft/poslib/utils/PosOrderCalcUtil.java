package com.nhsoft.poslib.utils;

import com.nhsoft.poslib.entity.FmPayment;
import com.nhsoft.poslib.entity.FmPosOrder;
import com.nhsoft.poslib.entity.FmPosOrderDetail;
import com.nhsoft.poslib.entity.order.Payment;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.libconfig.LibConfig;

import java.util.List;

public class PosOrderCalcUtil {

    private static PosOrderCalcUtil instance;

    public static PosOrderCalcUtil getInstance() {
        if (instance == null) {
            instance = new PosOrderCalcUtil();
        }
        return instance;
    }

    public static void calcPosOrderDetail(PosOrderDetail posOrderDetail) {
        posOrderDetail.setOrderDetailTax(0);
        posOrderDetail.setOrderDetailRound(0f);

        if (posOrderDetail.getPosItem() != null && posOrderDetail.getPosItem().getItem_weight_flag() && LibConfig.saleParamsBean.isEnableWeighItemRound()) {
            float oldDetailMoney = posOrderDetail.getOrderDetailPrice() * posOrderDetail.getOrderDetailAmount();
            posOrderDetail.setOrderDetailMoney(NumberUtil.getNewFloat(NumberUtil.roundMoney(posOrderDetail.getOrderDetailPrice() * posOrderDetail.getOrderDetailAmount(), LibConfig.S_ROUND_HALF, LibConfig.S_PRECISION_JIAO)));
            posOrderDetail.setOrderDetailPaymentMoney(NumberUtil.getNewFloat(NumberUtil.roundMoney(posOrderDetail.getOrderDetailMoney() + posOrderDetail.getOrderDetailAppendMoney(), LibConfig.S_ROUND_HALF, LibConfig.S_PRECISION_JIAO)));
            posOrderDetail.setOrderDetailRound(oldDetailMoney - posOrderDetail.getOrderDetailMoney());
            if(posOrderDetail.getOrderDetailStdPrice() * posOrderDetail.getOrderDetailAmount() - posOrderDetail.getOrderDetailPaymentMoney() - posOrderDetail.getOrderDetailRound() < 0.001){
                posOrderDetail.setOrderDetailDiscount(0);
            }else {
                posOrderDetail.setOrderDetailDiscount(NumberUtil.getNewFloat(NumberUtil.roundMoney(posOrderDetail.getOrderDetailStdPrice() * posOrderDetail.getOrderDetailAmount(), LibConfig.S_ROUND_HALF, LibConfig.S_PRECISION_JIAO) - posOrderDetail.getOrderDetailPaymentMoney()));
            }

        } else {
            posOrderDetail.setOrderDetailMoney(NumberUtil.getNewFloat(posOrderDetail.getOrderDetailPrice() * posOrderDetail.getOrderDetailAmount()));
            posOrderDetail.setOrderDetailPaymentMoney(NumberUtil.getNewFloat(posOrderDetail.getOrderDetailMoney() + posOrderDetail.getOrderDetailAppendMoney()));
            posOrderDetail.setOrderDetailDiscount(NumberUtil.getNewFloat(posOrderDetail.getOrderDetailStdPrice() * posOrderDetail.getOrderDetailAmount() - posOrderDetail.getOrderDetailPaymentMoney()));
        }
        if (posOrderDetail.getOrderDetailDiscount() < -0.001) {
            posOrderDetail.setOrderDetailDiscount(0);
        }

        if (posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM)) {
            if (posOrderDetail.getOrderDetailStateCode().equals(LibConfig.S_ORDER_DETAIL_PRESENT)) {
                posOrderDetail.setOrderDetailGrossProfit(-posOrderDetail.getOrderDetailCost() * posOrderDetail.getOrderDetailAmount());
                posOrderDetail.setOrderDetailDiscount(NumberUtil.getNewFloat(posOrderDetail.getOrderDetailPrice() * posOrderDetail.getOrderDetailAmount()));
                posOrderDetail.setOrderDetailPaymentMoney(posOrderDetail.getOrderDetailDiscount());
            } else {
                posOrderDetail.setOrderDetailGrossProfit(posOrderDetail.getOrderDetailPaymentMoney() - posOrderDetail.getOrderDetailCost() * posOrderDetail.getOrderDetailAmount());
            }
        } else {
            posOrderDetail.setOrderDetailGrossProfit(0);
        }
        //TODO 销售明细提成待实现
        posOrderDetail.setOrderDetailCommission(0);
        posOrderDetail.setResidueMoney(posOrderDetail.getOrderDetailPaymentMoney());
    }

    public static void calcFmPosOrderDetail(FmPosOrderDetail posOrderDetail) {
        posOrderDetail.setOrderDetailMoney(NumberUtil.getShortNewFloat(posOrderDetail.getOrderDetailPrice() * posOrderDetail.getOrderDetailAmount()));
        posOrderDetail.setOrderDetailPaymentMoney(posOrderDetail.getOrderDetailMoney());
        posOrderDetail.setOrderDetailDiscount(0);
        if (posOrderDetail.getOrderDetailDiscount() < -0.001) {
            posOrderDetail.setOrderDetailDiscount(0);
        }

        if (posOrderDetail.getOrderDetailStateCode() == LibConfig.S_ORDER_DETAIL_PRESENT) {
            posOrderDetail.setOrderDetailDiscount(NumberUtil.getNewFloat(posOrderDetail.getOrderDetailPrice() * posOrderDetail.getOrderDetailAmount()));
            posOrderDetail.setOrderDetailPaymentMoney(posOrderDetail.getOrderDetailDiscount());
        }
    }


    public static void calcRoundPosOrder(PosOrder posOrder) {
        float orderCommission = 0;
        float totalMoney = 0;
        float paymentMoney = 0;
        float discountMoney = 0;
        float grossPorfit = 0;
        float orderDetailTax = 0;

        List<PosOrderDetail> posOrderDetails = posOrder.getPosOrderDetails();
        for (PosOrderDetail posOrderDetail : posOrderDetails) {
            if (!posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM)) {
                continue;
            }

            if (posOrderDetail.getOrderDetailStateCode().equals(LibConfig.S_ORDER_DETAIL_PRESENT)) {
                discountMoney = discountMoney + posOrderDetail.getOrderDetailDiscount();
            } else if (posOrderDetail.getOrderDetailStateCode().equals(LibConfig.S_ORDER_DETAIL_RETURN)) {
                grossPorfit = grossPorfit - posOrderDetail.getOrderDetailGrossProfit();
                discountMoney = discountMoney - posOrderDetail.getOrderDetailDiscount();
                totalMoney = totalMoney - posOrderDetail.getOrderDetailPaymentMoney();
                paymentMoney = paymentMoney - posOrderDetail.getOrderDetailPaymentMoney();
                orderCommission = orderCommission - posOrderDetail.getOrderDetailCommission();
            } else {
                grossPorfit = grossPorfit + posOrderDetail.getOrderDetailGrossProfit();
                discountMoney = discountMoney + posOrderDetail.getOrderDetailDiscount();
                totalMoney = totalMoney + posOrderDetail.getOrderDetailPaymentMoney();
                paymentMoney = paymentMoney + posOrderDetail.getOrderDetailPaymentMoney();
                orderCommission = orderCommission + posOrderDetail.getOrderDetailCommission();
            }
        }


        paymentMoney = paymentMoney - posOrder.getOrderCouponTotalMoney();
        //TODO 读取营业参数应用取整规则
        posOrder.setOrderPaymentMoney(NumberUtil.getNewFloat(paymentMoney));
        posOrder.setOrderCommission(orderCommission);
        posOrder.setOrderDiscountMoney(discountMoney);
        posOrder.setOrderGrossProfit(grossPorfit);
        posOrder.setOrderChange(0);
        posOrder.setOrderTaxMoney(orderDetailTax);
        posOrder.setOrderCardChange(0); //零钱包余额


        float totalPaidMoney = 0;
        float changeMoney = 0;
        float orderRound = 0;
        float orderCardChange = 0;


        List<Payment> payments = posOrder.getPayments();
        for (Payment payment : payments) {
            totalPaidMoney = totalPaidMoney + payment.getPaymentMoney();
            if (payment.getPaymentPayBy().equals(LibConfig.C_PAYMENT_TYPE_CASH_NAME)) {
                changeMoney = changeMoney + payment.getPaymentChange();
                orderRound = orderRound + payment.getPaymentRound();
                payment.setPaymentRound(posOrder.getOrderRound());
//                if (changeMoney > 0) {
//                    if (LibConfig.activeVipMember != null && LibConfig.activeVipMember.isCard_user_change_enabled()) {
//                        if (LibConfig.sVipCardParams != null) {
//                            if ("小于1元".equals(LibConfig.sVipCardParams.getCardChangeType())) {
//                                orderCardChange = changeMoney - ((int) changeMoney);
//                            } else if ("小于5元".equals(LibConfig.sVipCardParams.getCardChangeType())) {
//                                orderCardChange = changeMoney - (5 * ((int) changeMoney / 5));
//                            } else if ("小于10元".equals(LibConfig.sVipCardParams.getCardChangeType())) {
//                                orderCardChange = changeMoney - (10 * ((int) changeMoney / 10));
//                            }
//
//                            if (orderCardChange != 0) {
//                                payment.setPaymentChange(changeMoney - orderCardChange);
//                            }
//                        }
//                    }
//                }

            } else {
                payment.setPaymentRound(0.00f);
            }
        }

        posOrder.setOrderChange(NumberUtil.getNewFloat(changeMoney));
        posOrder.setOrderCardChange(orderCardChange);

        float orderBalancePre = NumberUtil.getNewFloat(posOrder.getOrderPaymentMoney() - totalPaidMoney - posOrder.getOrderMgrDiscountMoney());

        float orderPaymentMoneyPre = posOrder.getOrderPaymentMoney();
        float receiveMoney = (NumberUtil.roundMoney(orderBalancePre, LibConfig.saleParamsBean.getRoundType(), LibConfig.saleParamsBean.getRoundTo()));
        posOrder.setOrderRound(orderBalancePre - receiveMoney);
        posOrder.setOrderPaymentMoney(orderPaymentMoneyPre - posOrder.getOrderRound());
        posOrder.setOrderTotalMoney(paymentMoney + posOrder.getOrderCouponTotalMoney());

        posOrder.setOrderBalance(NumberUtil.getNewFloat(posOrder.getOrderPaymentMoney() - totalPaidMoney - posOrder.getOrderMgrDiscountMoney() - posOrder.getQuickZeroMoney()));
        //TODO 单据积分计算
        posOrder.setOrderPoint(0);

        return;
    }


    public static void calcPosOrder(PosOrder posOrder) {
        float orderCommission = 0;
        float totalMoney = 0;
        float paymentMoney = 0;
        float discountMoney = 0;
        float grossPorfit = 0;
        float orderDetailTax = 0;

        List<PosOrderDetail> posOrderDetails = posOrder.getPosOrderDetails();
        for (PosOrderDetail posOrderDetail : posOrderDetails) {
            if (!posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM)) {
                continue;
            }

            if (posOrderDetail.getOrderDetailStateCode().equals(LibConfig.S_ORDER_DETAIL_PRESENT)) {
                discountMoney = discountMoney + posOrderDetail.getOrderDetailDiscount();
            } else if (posOrderDetail.getOrderDetailStateCode().equals(LibConfig.S_ORDER_DETAIL_RETURN)) {
                grossPorfit = grossPorfit - posOrderDetail.getOrderDetailGrossProfit();
                discountMoney = discountMoney - posOrderDetail.getOrderDetailDiscount();
                totalMoney = totalMoney - posOrderDetail.getOrderDetailPaymentMoney();
                paymentMoney = paymentMoney - posOrderDetail.getOrderDetailPaymentMoney();
                orderCommission = orderCommission - posOrderDetail.getOrderDetailCommission();
            } else {
                grossPorfit = grossPorfit + posOrderDetail.getOrderDetailGrossProfit();
                discountMoney = discountMoney + posOrderDetail.getOrderDetailDiscount();
                totalMoney = totalMoney + posOrderDetail.getOrderDetailPaymentMoney();
                paymentMoney = paymentMoney + posOrderDetail.getOrderDetailPaymentMoney();
                orderCommission = orderCommission + posOrderDetail.getOrderDetailCommission();
            }
        }
        float totalPaidMoney = 0;
        float changeMoney = 0;
        float orderRound = 0;
        float orderCardChange = 0;


        paymentMoney = paymentMoney - posOrder.getOrderCouponTotalMoney();

        if (posOrder.isRoundPayment()) {
            orderRound = posOrder.getOrderRound();
        }

        if (LibConfig.saleParamsBean.isEnableCashRound()) {
            posOrder.setOrderPaymentMoney(paymentMoney - orderRound);
        } else {
            //TODO 读取营业参数应用取整规则
//            float receiveMoney = (NumberUtil.roundMoney(paymentMoney - posOrder.getOrderMgrDiscountMoney(), LibConfig.saleParamsBean.getRoundType(), LibConfig.saleParamsBean.getRoundTo()));
//            posOrder.setOrderRound(paymentMoney - posOrder.getOrderMgrDiscountMoney() - receiveMoney);
//            posOrder.setOrderPaymentMoney(NumberUtil.roundMoney(paymentMoney, LibConfig.saleParamsBean.getRoundType(), LibConfig.saleParamsBean.getRoundTo()));


            posOrder.setOrderPaymentMoney(NumberUtil.getNewFloat(paymentMoney));
            float orderBalancePre = NumberUtil.getNewFloat(posOrder.getOrderPaymentMoney() - totalPaidMoney - posOrder.getOrderMgrDiscountMoney());
            float orderPaymentMoneyPre = posOrder.getOrderPaymentMoney();
            float receiveMoney = (NumberUtil.roundMoney(orderBalancePre, LibConfig.saleParamsBean.getRoundType(), LibConfig.saleParamsBean.getRoundTo()));
            posOrder.setOrderRound(orderBalancePre - receiveMoney);
            posOrder.setOrderPaymentMoney(orderPaymentMoneyPre - posOrder.getOrderRound());
        }

        //TODO 读取营业参数应用取整规则
        posOrder.setOrderCommission(orderCommission);
        posOrder.setOrderDiscountMoney(discountMoney);
        posOrder.setOrderGrossProfit(grossPorfit);
        posOrder.setOrderChange(0);
        posOrder.setOrderTaxMoney(orderDetailTax);
        posOrder.setOrderCardChange(0);

        posOrder.setOrderTotalMoney(paymentMoney + posOrder.getOrderCouponTotalMoney());


        List<Payment> payments = posOrder.getPayments();
        for (Payment payment : payments) {
            totalPaidMoney = totalPaidMoney + payment.getPaymentMoney();
            if (payment.getPaymentPayBy().equals(LibConfig.C_PAYMENT_TYPE_CASH_NAME)) {
                changeMoney = changeMoney + payment.getPaymentChange();
                orderRound = orderRound + payment.getPaymentRound();
                payment.setPaymentRound(posOrder.getOrderRound());

//                if (changeMoney > 0) {
//
//                    if (LibConfig.activeVipMember != null && LibConfig.activeVipMember.isCard_user_change_enabled()) {
//                        if (LibConfig.sVipCardParams != null) {
//                            if ("小于1元".equals(LibConfig.sVipCardParams.getCardChangeType())) {
//                                orderCardChange = changeMoney - ((int) changeMoney);
//                            } else if ("小于5元".equals(LibConfig.sVipCardParams.getCardChangeType())) {
//                                orderCardChange = changeMoney - (5 * ((int) changeMoney / 5));
//                            } else if ("小于10元".equals(LibConfig.sVipCardParams.getCardChangeType())) {
//                                orderCardChange = changeMoney - (10 * ((int) changeMoney / 10));
//                            }
//
//                            if (orderCardChange != 0) {
//                                payment.setPaymentChange(changeMoney - orderCardChange);
//                            }
//                        }
//                    }
//                }


            } else {
                payment.setPaymentRound(0.00f);
            }
        }
        posOrder.setOrderChange(NumberUtil.getNewFloat(changeMoney - orderCardChange));
        posOrder.setOrderCardChange(orderCardChange);
        posOrder.setOrderBalance(NumberUtil.getNewFloat(posOrder.getOrderPaymentMoney() - totalPaidMoney - posOrder.getOrderMgrDiscountMoney() - posOrder.getQuickZeroMoney()));
        //TODO 单据积分计算
        posOrder.setOrderPoint(0);

        return;
    }



    public static void calcReturnPosOrder(PosOrder posOrder) {
        float orderCommission = 0;
        float totalMoney = 0;
        float paymentMoney = 0;
        float discountMoney = 0;
        float grossPorfit = 0;
        float orderDetailTax = 0;

        List<PosOrderDetail> posOrderDetails = posOrder.getPosOrderDetails();
        for (PosOrderDetail posOrderDetail : posOrderDetails) {
            if (!posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM)) {
                continue;
            }

            if (posOrderDetail.getOrderDetailStateCode().equals(LibConfig.S_ORDER_DETAIL_PRESENT)) {
                discountMoney = discountMoney + posOrderDetail.getOrderDetailDiscount();
            } else if (posOrderDetail.getOrderDetailStateCode().equals(LibConfig.S_ORDER_DETAIL_RETURN)) {
                grossPorfit = grossPorfit - posOrderDetail.getOrderDetailGrossProfit();
                discountMoney = discountMoney - posOrderDetail.getOrderDetailDiscount();
                totalMoney = totalMoney - posOrderDetail.getOrderDetailPaymentMoney();
                paymentMoney = paymentMoney - posOrderDetail.getOrderDetailPaymentMoney();
                orderCommission = orderCommission - posOrderDetail.getOrderDetailCommission();
            } else {
                grossPorfit = grossPorfit + posOrderDetail.getOrderDetailGrossProfit();
                discountMoney = discountMoney + posOrderDetail.getOrderDetailDiscount();
                totalMoney = totalMoney + posOrderDetail.getOrderDetailPaymentMoney();
                paymentMoney = paymentMoney + posOrderDetail.getOrderDetailPaymentMoney();
                orderCommission = orderCommission + posOrderDetail.getOrderDetailCommission();
            }
        }
        float totalPaidMoney = 0;
        float changeMoney = 0;
        float orderRound = 0;
        float orderCardChange = 0;


        paymentMoney = paymentMoney - posOrder.getOrderCouponTotalMoney();

        if (posOrder.isRoundPayment()) {
            orderRound = posOrder.getOrderRound();
        }

        if (LibConfig.saleParamsBean.isEnableCashRound()) {
            posOrder.setOrderPaymentMoney(paymentMoney - orderRound);
        } else {
            //TODO 读取营业参数应用取整规则
//            float receiveMoney = (NumberUtil.roundMoney(paymentMoney - posOrder.getOrderMgrDiscountMoney(), LibConfig.saleParamsBean.getRoundType(), LibConfig.saleParamsBean.getRoundTo()));
//            posOrder.setOrderRound(paymentMoney - posOrder.getOrderMgrDiscountMoney() - receiveMoney);
//            posOrder.setOrderPaymentMoney(NumberUtil.roundMoney(paymentMoney, LibConfig.saleParamsBean.getRoundType(), LibConfig.saleParamsBean.getRoundTo()));


            posOrder.setOrderPaymentMoney(NumberUtil.getNewFloat(paymentMoney));
            float orderBalancePre = NumberUtil.getNewFloat(posOrder.getOrderPaymentMoney() - totalPaidMoney - posOrder.getOrderMgrDiscountMoney());
            float orderPaymentMoneyPre = posOrder.getOrderPaymentMoney();
            float receiveMoney = (NumberUtil.roundMoney(orderBalancePre, LibConfig.saleParamsBean.getRoundType(), LibConfig.saleParamsBean.getRoundTo()));
            posOrder.setOrderRound(orderBalancePre - receiveMoney);
            posOrder.setOrderPaymentMoney(orderPaymentMoneyPre - posOrder.getOrderRound());
        }

        //TODO 读取营业参数应用取整规则
        posOrder.setOrderCommission(orderCommission);
        posOrder.setOrderDiscountMoney(discountMoney);
        posOrder.setOrderGrossProfit(grossPorfit);
        posOrder.setOrderChange(0);
        posOrder.setOrderTaxMoney(orderDetailTax);
        posOrder.setOrderCardChange(0);

        posOrder.setOrderTotalMoney(paymentMoney + posOrder.getOrderCouponTotalMoney());


        List<Payment> payments = posOrder.getPayments();
        for (Payment payment : payments) {
            totalPaidMoney = totalPaidMoney + payment.getPaymentMoney();
            if (payment.getPaymentPayBy().equals(LibConfig.C_PAYMENT_TYPE_CASH_NAME)) {
                changeMoney = changeMoney + payment.getPaymentChange();
                orderRound = orderRound + payment.getPaymentRound();
                payment.setPaymentRound(posOrder.getOrderRound());

//                if (changeMoney > 0) {
//
//                    if (LibConfig.activeVipMember != null && LibConfig.activeVipMember.isCard_user_change_enabled()) {
//                        if (LibConfig.sVipCardParams != null) {
//                            if ("小于1元".equals(LibConfig.sVipCardParams.getCardChangeType())) {
//                                orderCardChange = changeMoney - ((int) changeMoney);
//                            } else if ("小于5元".equals(LibConfig.sVipCardParams.getCardChangeType())) {
//                                orderCardChange = changeMoney - (5 * ((int) changeMoney / 5));
//                            } else if ("小于10元".equals(LibConfig.sVipCardParams.getCardChangeType())) {
//                                orderCardChange = changeMoney - (10 * ((int) changeMoney / 10));
//                            }
//
//                            if (orderCardChange != 0) {
//                                payment.setPaymentChange(changeMoney - orderCardChange);
//                            }
//                        }
//                    }
//                }


            } else {
                payment.setPaymentRound(0.00f);
            }
        }
        posOrder.setOrderChange(NumberUtil.getNewFloat(changeMoney - orderCardChange));
        posOrder.setOrderCardChange(orderCardChange);
        posOrder.setOrderBalance(NumberUtil.getNewFloat(posOrder.getOrderPaymentMoney() - totalPaidMoney - posOrder.getOrderMgrDiscountMoney() - posOrder.getQuickZeroMoney()));
        //TODO 单据积分计算
        posOrder.setOrderPoint(0);

        return;
    }


    public static void calcFmPosOrder(FmPosOrder posOrder) {
        float totalMoney = 0;
        float paymentMoney = 0;
        float discountMoney = 0;
        List<FmPosOrderDetail> posOrderDetails = posOrder.getPosOrderDetails();
        for (FmPosOrderDetail posOrderDetail : posOrderDetails) {
            if (posOrderDetail.getOrderDetailStateCode() == LibConfig.S_ORDER_DETAIL_PRESENT) {
                discountMoney = discountMoney + posOrderDetail.getOrderDetailDiscount();
            } else if (posOrderDetail.getOrderDetailStateCode() == LibConfig.S_ORDER_DETAIL_RETURN) {
                discountMoney = discountMoney - posOrderDetail.getOrderDetailDiscount();
                totalMoney = totalMoney - posOrderDetail.getOrderDetailPaymentMoney();
                paymentMoney = paymentMoney - posOrderDetail.getOrderDetailPaymentMoney();
            } else {
                discountMoney = discountMoney + posOrderDetail.getOrderDetailDiscount();
                totalMoney = totalMoney + posOrderDetail.getOrderDetailPaymentMoney();
                paymentMoney = paymentMoney + posOrderDetail.getOrderDetailPaymentMoney();
            }
        }
        float totalPaidMoney = 0;
        float changeMoney = 0;
        float orderRound = 0;
        float orderCardChange = 0;

        paymentMoney = paymentMoney - posOrder.getOrderCouponTotalMoney();

        //TODO 读取营业参数应用取整规则
        float receiveMoney = (NumberUtil.roundMoney(paymentMoney - posOrder.getOrderMgrDiscountMoney(), LibConfig.S_ROUND_HALF, LibConfig.S_PRECISION_FEN));
        posOrder.setOrderRound(paymentMoney - posOrder.getOrderMgrDiscountMoney() - receiveMoney);
        posOrder.setOrderPaymentMoney(NumberUtil.roundMoney(paymentMoney, LibConfig.S_ROUND_HALF, LibConfig.S_PRECISION_FEN));


        //TODO 读取营业参数应用取整规则
        posOrder.setOrderDiscountMoney(discountMoney);
        posOrder.setOrderChange(0);
        posOrder.setOrderTotalMoney(paymentMoney + posOrder.getOrderCouponTotalMoney());


        List<FmPayment> payments = posOrder.getPayments();
        for (FmPayment payment : payments) {
            totalPaidMoney = totalPaidMoney + payment.getPaymentMoney();
            if (payment.getPaymentPayBy().equals(LibConfig.C_PAYMENT_TYPE_CASH_NAME)) {
                changeMoney = changeMoney + payment.getPaymentChange();
                orderRound = orderRound + payment.getPaymentRound();
                payment.setPaymentRound(posOrder.getOrderRound());
            } else {
                payment.setPaymentRound(0.00f);
            }
        }
        posOrder.setOrderChange(NumberUtil.getNewFloat(changeMoney - orderCardChange));
        posOrder.setOrderBalance(NumberUtil.getNewFloat(posOrder.getOrderPaymentMoney() - totalPaidMoney - posOrder.getOrderMgrDiscountMoney()));
        return;
    }

    /**
     * 反结账
     *
     * @param posOrder
     */
    public static PosOrder calcPosOrderFan(PosOrder posOrder) {
        float orderCommission = 0;
        float totalMoney = 0;
        float paymentMoney = 0;
        float discountMoney = 0;
        float grossPorfit = 0;
        float orderDetailTax = 0;

        List<PosOrderDetail> posOrderDetails = posOrder.getPosOrderDetails();
        for (PosOrderDetail posOrderDetail : posOrderDetails) {
            if (!posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM)) {
                continue;
            }

            if (posOrderDetail.getOrderDetailStateCode().equals(LibConfig.S_ORDER_DETAIL_PRESENT)) {
                discountMoney = discountMoney + posOrderDetail.getOrderDetailDiscount();
            } else if (posOrderDetail.getOrderDetailStateCode().equals(LibConfig.S_ORDER_DETAIL_RETURN)) {
                grossPorfit = grossPorfit - posOrderDetail.getOrderDetailGrossProfit();
                discountMoney = discountMoney - posOrderDetail.getOrderDetailDiscount();
                totalMoney = totalMoney - posOrderDetail.getOrderDetailPaymentMoney();
                paymentMoney = paymentMoney - posOrderDetail.getOrderDetailPaymentMoney();
                orderCommission = orderCommission - posOrderDetail.getOrderDetailCommission();
            } else {
                grossPorfit = grossPorfit + posOrderDetail.getOrderDetailGrossProfit();
                discountMoney = discountMoney + posOrderDetail.getOrderDetailDiscount();
                totalMoney = totalMoney + posOrderDetail.getOrderDetailPaymentMoney();
                paymentMoney = paymentMoney + posOrderDetail.getOrderDetailPaymentMoney();
                orderCommission = orderCommission + posOrderDetail.getOrderDetailCommission();
            }
        }
        float totalPaidMoney = 0;
        float changeMoney = 0;
        float orderRound = 0;
        float orderCardChange = 0;


        paymentMoney = paymentMoney - posOrder.getOrderCouponTotalMoney();

        if (posOrder.isRoundPayment()) {
            orderRound = posOrder.getOrderRound();
        }

        if (LibConfig.saleParamsBean.isEnableCashRound() && !posOrder.isRoundPayment()) {
            posOrder.setOrderPaymentMoney(paymentMoney - orderRound);
        } else {
            //TODO 读取营业参数应用取整规则
            float receiveMoney = (NumberUtil.roundMoney(paymentMoney - posOrder.getOrderMgrDiscountMoney(), LibConfig.saleParamsBean.getRoundType(), LibConfig.saleParamsBean.getRoundTo()));
            posOrder.setOrderRound(paymentMoney - posOrder.getOrderMgrDiscountMoney() - receiveMoney);
            posOrder.setOrderPaymentMoney(NumberUtil.roundMoney(paymentMoney, LibConfig.saleParamsBean.getRoundType(), LibConfig.saleParamsBean.getRoundTo()));
        }

        //TODO 读取营业参数应用取整规则
        posOrder.setOrderCommission(orderCommission);
        posOrder.setOrderDiscountMoney(discountMoney);
        posOrder.setOrderGrossProfit(grossPorfit);
        posOrder.setOrderChange(0);
        posOrder.setOrderTaxMoney(orderDetailTax);
        posOrder.setOrderCardChange(0);

        posOrder.setOrderTotalMoney(paymentMoney + posOrder.getOrderCouponTotalMoney());


        List<Payment> payments = posOrder.getPayments();
        for (Payment payment : payments) {
            totalPaidMoney = totalPaidMoney + payment.getPaymentMoney();
            if (payment.getPaymentPayBy().equals(LibConfig.C_PAYMENT_TYPE_CASH_NAME)) {
                changeMoney = changeMoney + payment.getPaymentChange();
                orderRound = orderRound + payment.getPaymentRound();
                payment.setPaymentRound(posOrder.getOrderRound());
            } else {
                payment.setPaymentRound(0.00f);
            }
        }
        posOrder.setOrderChange(NumberUtil.getNewFloat(changeMoney - orderCardChange));
        posOrder.setOrderCardChange(orderCardChange);
        posOrder.setOrderBalance(NumberUtil.getNewFloat(posOrder.getOrderPaymentMoney() - totalPaidMoney - posOrder.getOrderMgrDiscountMoney() - posOrder.getQuickZeroMoney()));
        //TODO 单据积分计算
        posOrder.setOrderPoint(0);

        return posOrder;
    }


}
