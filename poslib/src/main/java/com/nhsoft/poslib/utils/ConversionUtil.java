package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.entity.PolicyPromotion;
import com.nhsoft.poslib.entity.PolicyPromotionDetail;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.PosItemGrade;
import com.nhsoft.poslib.model.VipUserInfo;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.model.CouponsBean;
import com.nhsoft.poslib.model.PolicypromotionPriceBean;
import com.nhsoft.poslib.model.PosOrderDetailState;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Iverson on 2018/11/27 11:47 AM
 * 此类用于：将选中的商品转换成订单商品
 */
public class ConversionUtil {

    public static PosOrderDetail getPosOrderDetail(VipUserInfo vipUserInfo, PosOrderDetail posOrderDetail, PosItem posItem, PosItemGrade posItemGrade, float suttle, LinkedList<PosOrderDetail> posOrderDetails, String memo, boolean isChangePrice, boolean isSettle) {
        if (posOrderDetail == null) {
            posOrderDetail = new PosOrderDetail();
            posOrderDetail.setOrderDetailPolicyPromotionFlag(false);  //特价促销标记
            posOrderDetail.setOrderDetailPolicyPromotionQuantityFlag(false); //超量特价标记
            posOrderDetail.setOrderDetailPolicyPromotionMoneyFlag(false); //超额奖励标记
            posOrderDetail.setOrderDetailPolicyPresentFlag(false);//赠送促销标记
            posOrderDetail.setOrderDetailPolicyDiscountFlag(false); //超额折扣标记
        }
        posOrderDetail.setPosItem(posItem);
        posOrderDetail.setPosItemGrade(posItemGrade);
        if (TextUtils.isEmpty(posOrderDetail.getOrderNo())) {
            posOrderDetail.setOrderNo("");
        }
        posOrderDetail.setItemNum(Integer.parseInt(String.valueOf(posItem.getItem_num()))); //项目编号
        posOrderDetail.setItemGradeNum(posItemGrade == null ? 0 : posItemGrade.getItem_grade_num()); //分级编号
        posOrderDetail.setOrderDetailItem(posItemGrade == null ? posItem.getItem_name() : posItemGrade.getItem_grade_name()); //项目名称
        posOrderDetail.setOrderDetailItemDepartment(posItem.getItem_department()); //项目部门

        posOrderDetail.setOrderDetailNum(0);//明细编号
        posOrderDetail.setOrderDetailType(LibConfig.C_ORDER_DETAIL_TYPE_ITEM); //业务类型

//        if (LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived() && posItem.getBranch_regular_price() != 0) {
//            posOrderDetail.setOrderDetailStdPrice(posItemGrade == null ? posItem.getBranch_regular_price() : posItemGrade.getBranch_grade_regular_price() == 0 ? posItem.getBranch_regular_price() : posItemGrade.getBranch_grade_regular_price());//标准单价
//        } else {
//            posOrderDetail.setOrderDetailStdPrice(posItemGrade == null ? posItem.getItem_regular_price() : posItemGrade.getBranch_grade_regular_price() == 0 ? posItem.getItem_regular_price() : posItemGrade.getBranch_grade_regular_price());//标准单价
//        }

        float finalRegularPrice = RetailPosManager.getInstance().getItemRegularPrice(posItem, posItemGrade);

        if (TextUtils.isEmpty(memo) || !memo.contains(LibConfig.GOODS_CHANGE_TAG)) {
//            if (LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived() && posItem.getBranch_regular_price() != 0) {
//                posOrderDetail.setOrderDetailPrice(posItemGrade == null ? posItem.getBranch_regular_price() : posItemGrade.getBranch_grade_regular_price() == 0 ? posItem.getBranch_regular_price() : posItemGrade.getBranch_grade_regular_price());//标准单价
//            } else {
//                posOrderDetail.setOrderDetailPrice(posItemGrade == null ? posItem.getItem_regular_price() : posItemGrade.getBranch_grade_regular_price() == 0 ? posItem.getItem_regular_price() : posItemGrade.getBranch_grade_regular_price());//标准单价
//            }
            posOrderDetail.setOrderDetailStdPrice(finalRegularPrice);
            posOrderDetail.setOrderDetailPrice(finalRegularPrice);
        }


//        if (posItem.getItem_weight_flag()) {
        posOrderDetail.setOrderDetailAmount(NumberUtil.getNewLongFloat(suttle)); //数量
//        } else {
//            posOrderDetail.setOrderDetailAmount(NumberUtil.getNewFloat(suttle)); //数量
//        }

        posOrderDetail.setOrderDetailPaymentMoney(posOrderDetail.getOrderDetailMoney()); //实际应收

        PosOrderDetailState orderState;
        if (LibConfig.S_HOME_SHOW_BY_CURRENT_MODE == LibConfig.S_HOME_SHOW_BY_RETURN) {
            orderState = PosOrderDetailStateUtil.getOrderState(LibConfig.S_ORDER_DETAIL_RETURN);
        } else {
            if (posOrderDetail != null && LibConfig.S_ORDER_DETAIL_PRESENT_NAME.equals(posOrderDetail.getOrderDetailStateName())) {
                orderState = PosOrderDetailStateUtil.getOrderState(LibConfig.S_ORDER_DETAIL_PRESENT);
            } else {
                orderState = PosOrderDetailStateUtil.getOrderState(LibConfig.S_ORDER_DETAIL_SALE);
            }

        }
        posOrderDetail.setOrderDetailStateCode(orderState.POS_ORDER_DETAIL_STATE_CODE); //状态编码  应在全局定义
        posOrderDetail.setOrderDetailStateName(orderState.POS_ORDER_DETAIL_STATE_NAME); //状态名称

        posOrderDetail.setOrderDetailAuditor(""); //审核人
        posOrderDetail.setOrderDetailItemMatrixNum(0);//矩阵明细编号
        posOrderDetail.setOrderDetailItemSerialNumber(""); //序列号
        posOrderDetail.setOrderDetailCommission(0); //销售提成
        posOrderDetail.setOrderDetailPolicyDetailNum(0); //促销政策明细编号
        posOrderDetail.setOrderDetailCost(posItem.getItem_cost_price()); //销售成本

        posOrderDetail.setOrderDetailGrossProfit(0);//毛利
        posOrderDetail.setOrderDetailProducingDate(null);//生产日期
        posOrderDetail.setOrderDetailLotNumber(""); //批号
        posOrderDetail.setOrderDetailTicketUuid(""); //消费劵标识
        posOrderDetail.setOrderDetailAssistAmount(0); //辅助数量
        posOrderDetail.setOrderDetailTicketActionId(""); //消费劵营销活动
        posOrderDetail.setOrderDetailOnlineUnit(posItem.getItem_unit()); //线上销售单位
        posOrderDetail.setOrderDetailOnlineQty(0); //线上销售数量
        posOrderDetail.setOrderDetailOnlinePrice(0); //线上销售单价
        posOrderDetail.setOrderDetailHasKit(false);//是否有组合明细
        posOrderDetail.setOrderDetailPolicyDetailNum(0); //流水号


//        posOrderDetail.setOrderSource(""); //单据来源
//        posOrderDetail.setSupplierNum(0); //供应商
//        posOrderDetail.setOrderDetailMerchantRate(0); //商户结算扣率
//        posOrderDetail.setOrderDetailBizday("");//营业日
//        posOrderDetail.setOrderDetailBookCode(""); //代码
//        posOrderDetail.setOrderDetailBranchNum(0); // 编号
//        posOrderDetail.setOrderDetailOrderState(0); //状态编号
//        posOrderDetail.setStallNum(0); //编号
//        posOrderDetail.setShiftTableNum(0); //编号

        //未记录  37  39 40

        //OK
        posOrderDetail.setOrderDetailAppendMoney(0); //附加金额
        posOrderDetail.setOrderDetailDiscount(0); //优惠折扣
        posOrderDetail.setOrderDetailTax(0); //税金
        PosOrderDetail orderDetail = null;
        orderDetail = getRealPrice(vipUserInfo, posOrderDetail, posItem, posOrderDetails, isChangePrice, isSettle);//取得最后真实的价格
        PriceQuantityCalUtils.getPolicyQuantityPrice(posOrderDetails, LibConfig.allPolicyQuantityList);

        return orderDetail;
    }


    public static PosOrderDetail getPosOrderDetailByPolicy(PosItem posItem, PosItemGrade posItemGrade, float suttle, float price) {
        PosOrderDetail posOrderDetail = new PosOrderDetail();
        posOrderDetail.setOrderDetailPolicyPromotionFlag(false);  //特价促销标记
        posOrderDetail.setOrderDetailPolicyPromotionQuantityFlag(false); //超量特价标记
        posOrderDetail.setOrderDetailPolicyPromotionMoneyFlag(false); //超额奖励标记
        posOrderDetail.setOrderDetailPolicyPresentFlag(false);//赠送促销标记
        posOrderDetail.setOrderDetailPolicyDiscountFlag(false); //超额折扣标记
        posOrderDetail.setPosItem(posItem);
        posOrderDetail.setPosItemGrade(posItemGrade);
        posOrderDetail.setOrderNo(""); //单据号
        posOrderDetail.setItemNum(Integer.parseInt(String.valueOf(posItem.getItem_num()))); //项目编号
        posOrderDetail.setItemGradeNum(posItemGrade == null ? 0 : posItemGrade.getItem_grade_num()); //分级编号
        posOrderDetail.setOrderDetailItem(posItemGrade == null ? posItem.getItem_name() : posItemGrade.getItem_grade_name()); //项目名称
        posOrderDetail.setOrderDetailItemDepartment(posItem.getItem_department()); //项目部门

        posOrderDetail.setOrderDetailNum(0);//明细编号
        posOrderDetail.setOrderDetailType(LibConfig.C_ORDER_DETAIL_TYPE_ITEM); //业务类型

        float finalRegularPrice = RetailPosManager.getInstance().getItemRegularPrice(posItem, posItemGrade);
        posOrderDetail.setOrderDetailStdPrice(finalRegularPrice);

//        if (LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived() && posItem.getBranch_regular_price() != 0) {
//            posOrderDetail.setOrderDetailStdPrice(posItemGrade == null ? posItem.getBranch_regular_price() : posItemGrade.getBranch_grade_regular_price() == 0 ? posItem.getBranch_regular_price() : posItemGrade.getBranch_grade_regular_price());//标准单价
//        } else {
//            posOrderDetail.setOrderDetailStdPrice(posItemGrade == null ? posItem.getItem_regular_price() : posItemGrade.getBranch_grade_regular_price() == 0 ? posItem.getItem_regular_price() : posItemGrade.getBranch_grade_regular_price());//标准单价
//        }
//        if (posItem.getItem_weight_flag()) {
        posOrderDetail.setOrderDetailAmount(NumberUtil.getNewLongFloat(suttle)); //数量
        posOrderDetail.setOrderDetailPaymentMoney(posOrderDetail.getOrderDetailMoney()); //实际应收

        PosOrderDetailState orderState;
        if (LibConfig.S_HOME_SHOW_BY_CURRENT_MODE == LibConfig.S_HOME_SHOW_BY_RETURN) {
            orderState = PosOrderDetailStateUtil.getOrderState(LibConfig.S_ORDER_DETAIL_RETURN);
        } else {
            if (posOrderDetail != null && LibConfig.S_ORDER_DETAIL_PRESENT_NAME.equals(posOrderDetail.getOrderDetailStateName())) {
                orderState = PosOrderDetailStateUtil.getOrderState(LibConfig.S_ORDER_DETAIL_PRESENT);
            } else {
                orderState = PosOrderDetailStateUtil.getOrderState(LibConfig.S_ORDER_DETAIL_SALE);
            }

        }
        posOrderDetail.setOrderDetailStateCode(orderState.POS_ORDER_DETAIL_STATE_CODE); //状态编码  应在全局定义
        posOrderDetail.setOrderDetailStateName(orderState.POS_ORDER_DETAIL_STATE_NAME); //状态名称

        posOrderDetail.setOrderDetailAuditor(""); //审核人
        posOrderDetail.setOrderDetailItemMatrixNum(0);//矩阵明细编号
        posOrderDetail.setOrderDetailItemSerialNumber(""); //序列号
        posOrderDetail.setOrderDetailCommission(0); //销售提成
        posOrderDetail.setOrderDetailPolicyDetailNum(0); //促销政策明细编号
        posOrderDetail.setOrderDetailCost(posItem.getItem_cost_price()); //销售成本

        posOrderDetail.setOrderDetailGrossProfit(0);//毛利
        posOrderDetail.setOrderDetailProducingDate(null);//生产日期
        posOrderDetail.setOrderDetailLotNumber(""); //批号
        posOrderDetail.setOrderDetailTicketUuid(""); //消费劵标识
        posOrderDetail.setOrderDetailAssistAmount(0); //辅助数量
        posOrderDetail.setOrderDetailTicketActionId(""); //消费劵营销活动
        posOrderDetail.setOrderDetailOnlineUnit(posItem.getItem_unit()); //线上销售单位
        posOrderDetail.setOrderDetailOnlineQty(0); //线上销售数量
        posOrderDetail.setOrderDetailOnlinePrice(0); //线上销售单价
        posOrderDetail.setOrderDetailHasKit(false);//是否有组合明细
        posOrderDetail.setOrderDetailPolicyDetailNum(0); //流水号
        //OK
        posOrderDetail.setOrderDetailAppendMoney(0); //附加金额
        posOrderDetail.setOrderDetailDiscount(0); //优惠折扣
        posOrderDetail.setOrderDetailTax(0); //税金
        posOrderDetail.setOrderDetailPrice(price);
        return posOrderDetail;
    }

    public static PosOrderDetail getPosOrderDetailByOutBar(PosItem posItem, PosItemGrade posItemGrade, float suttle, float paymentMoney) {
        PosOrderDetail posOrderDetail = new PosOrderDetail();
        posOrderDetail.setPosItem(posItem);
        posOrderDetail.setPosItemGrade(posItemGrade);
        posOrderDetail.setOrderNo(""); //单据号
        posOrderDetail.setItemNum(Integer.parseInt(String.valueOf(posItem.getItem_num()))); //项目编号
        posOrderDetail.setItemGradeNum(posItemGrade == null ? 0 : posItemGrade.getItem_grade_num()); //分级编号
        posOrderDetail.setOrderDetailItem(posItemGrade == null ? posItem.getItem_name() : posItemGrade.getItem_grade_name()); //项目名称
        posOrderDetail.setOrderDetailItemDepartment(posItem.getItem_department()); //项目部门

        posOrderDetail.setOrderDetailNum(0);//明细编号
        posOrderDetail.setOrderDetailType(LibConfig.C_ORDER_DETAIL_TYPE_ITEM); //业务类型
//        if (LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived() && posItem.getBranch_regular_price() != 0) {
//            posOrderDetail.setOrderDetailStdPrice(posItemGrade == null ? posItem.getBranch_regular_price() : posItemGrade.getBranch_grade_regular_price() == 0 ? posItem.getBranch_regular_price() : posItemGrade.getBranch_grade_regular_price());//标准单价
//        } else {
//            posOrderDetail.setOrderDetailStdPrice(posItemGrade == null ? posItem.getItem_regular_price() : posItemGrade.getBranch_grade_regular_price() == 0 ? posItem.getItem_regular_price() : posItemGrade.getBranch_grade_regular_price());//标准单价
//        }

        float finalRegularPrice = RetailPosManager.getInstance().getItemRegularPrice(posItem, posItemGrade);
        posOrderDetail.setOrderDetailStdPrice(finalRegularPrice);

        if (paymentMoney != 0 && suttle != 0) {
            posOrderDetail.setOrderDetailPrice(NumberUtil.getNewFloat(paymentMoney / suttle));
            posOrderDetail.setOrderDetailAmount(NumberUtil.getNewLongFloat(suttle));
        } else if (paymentMoney == 0) {
            posOrderDetail.setOrderDetailPrice(NumberUtil.getNewFloat(posOrderDetail.getOrderDetailStdPrice()));
            posOrderDetail.setOrderDetailAmount(NumberUtil.getNewLongFloat(suttle));
        } else if (suttle == 0) {
            posOrderDetail.setOrderDetailPrice(NumberUtil.getNewFloat(posOrderDetail.getOrderDetailStdPrice()));
            posOrderDetail.setOrderDetailAmount(NumberUtil.getNewLongFloat(paymentMoney / posOrderDetail.getOrderDetailPrice()));
        }

        PosOrderDetailState orderState;
        if (LibConfig.S_HOME_SHOW_BY_CURRENT_MODE == LibConfig.S_HOME_SHOW_BY_RETURN) {
            orderState = PosOrderDetailStateUtil.getOrderState(LibConfig.S_ORDER_DETAIL_RETURN);
        } else {
            orderState = PosOrderDetailStateUtil.getOrderState(LibConfig.S_ORDER_DETAIL_SALE);
        }
        posOrderDetail.setOrderDetailStateCode(orderState.POS_ORDER_DETAIL_STATE_CODE); //状态编码  应在全局定义
        posOrderDetail.setOrderDetailStateName(orderState.POS_ORDER_DETAIL_STATE_NAME); //状态名称

        posOrderDetail.setOrderDetailAuditor(""); //审核人
        posOrderDetail.setOrderDetailItemMatrixNum(0);//矩阵明细编号
        posOrderDetail.setOrderDetailItemSerialNumber(""); //序列号
        posOrderDetail.setOrderDetailCommission(0); //销售提成
        posOrderDetail.setOrderDetailPolicyDetailNum(0); //促销政策明细编号
        posOrderDetail.setOrderDetailCost(posItem.getItem_cost_price()); //销售成本

        posOrderDetail.setOrderDetailGrossProfit(0);//毛利
        posOrderDetail.setOrderDetailProducingDate(null);//生产日期
        posOrderDetail.setOrderDetailLotNumber(""); //批号
        posOrderDetail.setOrderDetailTicketUuid(""); //消费劵标识
        posOrderDetail.setOrderDetailAssistAmount(0); //辅助数量
        posOrderDetail.setOrderDetailTicketActionId(""); //消费劵营销活动
        posOrderDetail.setOrderDetailOnlineUnit(posItem.getItem_unit()); //线上销售单位
        posOrderDetail.setOrderDetailOnlineQty(0); //线上销售数量
        posOrderDetail.setOrderDetailOnlinePrice(0); //线上销售单价
        posOrderDetail.setOrderDetailHasKit(false);//是否有组合明细
        posOrderDetail.setOrderDetailPolicyDetailNum(0); //流水号
        //OK
        posOrderDetail.setOrderDetailAppendMoney(0); //附加金额
        posOrderDetail.setOrderDetailDiscount(0); //优惠折扣
        posOrderDetail.setOrderDetailTax(0); //税金
        PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail);
        return posOrderDetail;
    }


    public static PosOrderDetail getRealPrice(VipUserInfo vipUserInfo, PosOrderDetail newPosOrderDetail, PosItem posItem, LinkedList<PosOrderDetail> posOrderDetails, boolean isChangePrice, boolean isSettle) {
//        posOrderDetail.setOrderDetailMoney(posOrderDetail.getOrderDetailPrice() * posOrderDetail.getOrderDetailAmount());
        if (!isChangePrice) {
            newPosOrderDetail.setOrderDetailPolicyPromotionFlag(false);  //特价促销标记
            newPosOrderDetail.setOrderDetailPolicyPromotionQuantityFlag(false); //超量特价标记
            newPosOrderDetail.setOrderDetailPolicyPromotionMoneyFlag(false); //超额奖励标记
            newPosOrderDetail.setOrderDetailPolicyPresentFlag(false);//赠送促销标记
            newPosOrderDetail.setOrderDetailPolicyDiscountFlag(false); //超额折扣标记

            newPosOrderDetail.setOrderDetailPolicyFid("");
            TagUtils.removeVipTag(newPosOrderDetail);
        }

        //有手改标记不再取其他标签
        if (!TextUtils.isEmpty(newPosOrderDetail.getOrderDetailMemo()) && newPosOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_CHANGE_TAG)) {
            posOrderDetails.addLast(newPosOrderDetail);
            PosOrderCalcUtil.calcPosOrderDetail(newPosOrderDetail);
            return newPosOrderDetail;
        }

//        退货模式不享用会员价和超量特价
//        if (newPosOrderDetail.getOrderDetailStateCode() == LibConfig.S_ORDER_DETAIL_RETURN) {
//            posOrderDetails.addLast(newPosOrderDetail);
//            PosOrderCalcUtil.calcPosOrderDetail(newPosOrderDetail);
//            return newPosOrderDetail;
//        }


        float vipMemberPrice = Float.MAX_VALUE;
        float policypromotionPrice;


        //取会员价
        if (vipUserInfo != null) {
            if (isSettle) {
                vipMemberPrice = PriceVipCalUtils.getVipMemberPriceBySettle(newPosOrderDetail, vipUserInfo);
            } else {
                vipMemberPrice = PriceVipCalUtils.getVipMemberPrice(newPosOrderDetail, vipUserInfo);
            }
        }

        //取促销价
        PolicypromotionPriceBean policypromotionPriceBean = getPolicypromotionPrice(newPosOrderDetail, isSettle);
        policypromotionPrice = policypromotionPriceBean.getPrice();


        if (newPosOrderDetail.getOrderDetailPrice() > vipMemberPrice || newPosOrderDetail.getOrderDetailPrice() > policypromotionPrice) {
            if (vipMemberPrice < policypromotionPrice) {

                float itemMinPrice = ItemPriceCheck.getItemMinPrice(posItem, vipMemberPrice);
                if (itemMinPrice > vipMemberPrice) {
                    newPosOrderDetail.setOrderDetailPrice(itemMinPrice);//销售单价
                } else {
                    newPosOrderDetail.setOrderDetailPrice(vipMemberPrice);
                }

                TagUtils.addVipTag(newPosOrderDetail);
                posOrderDetails.addLast(newPosOrderDetail);
                PosOrderCalcUtil.calcPosOrderDetail(newPosOrderDetail);
                return newPosOrderDetail;

            } else {

                Map<String, Float> posOrderDetailMap = new LinkedHashMap<>();
                if (posOrderDetails != null && posOrderDetails.size() > 0) {
                    for (PosOrderDetail posOrderDetail : posOrderDetails) {
                        if (posOrderDetail.getOrderDetailPolicyPromotionFlag() || posOrderDetail.getOrderDetailPolicyDiscountFlag()) {
                            String orderDetailKey = posOrderDetail.getItemNum() + "_" + posOrderDetail.getItemGradeNum() + "_" + posOrderDetail.getOrderDetailPolicyFid();
                            Float detailAmount = posOrderDetailMap.get(orderDetailKey);
                            if (detailAmount == null) detailAmount = 0f;
                            detailAmount += posOrderDetail.getOrderDetailAmount();
                            posOrderDetailMap.put(orderDetailKey, detailAmount);
                        }
                    }
                }

                PolicyPromotionDetail policyPromotionDetail = policypromotionPriceBean.getPolicyPromotionDetail();
                float policyPromotionDetailNum = Float.MAX_VALUE;
                if (policyPromotionDetail != null) {
                    policyPromotionDetailNum = (float) policyPromotionDetail.getPolicy_promotion_detail_bill_limit();
                }
                String orderDetailKey = newPosOrderDetail.getItemNum() + "_" + newPosOrderDetail.getItemGradeNum() + "_" + policypromotionPriceBean.getPolicypromotionId();
                Float detailAmount = posOrderDetailMap.get(orderDetailKey);
                if (detailAmount == null) detailAmount = 0f;

                if (policyPromotionDetailNum == 0 || policyPromotionDetailNum - detailAmount >= newPosOrderDetail.getOrderDetailAmount()) {

                    float itemMinPrice = ItemPriceCheck.getItemMinPrice(posItem, policypromotionPrice);
                    if (itemMinPrice > policypromotionPrice) {
                        newPosOrderDetail.setOrderDetailPrice(itemMinPrice);//销售单价
                    } else {
                        newPosOrderDetail.setOrderDetailPrice(policypromotionPrice);
                    }
                    newPosOrderDetail.setOrderDetailPolicyPromotionFlag(true);  //特价促销标记
                    newPosOrderDetail.setOrderDetailPolicyFid(policypromotionPriceBean.getPolicypromotionId()); //促销政策流水号
                    posOrderDetails.addLast(newPosOrderDetail);
                    PosOrderCalcUtil.calcPosOrderDetail(newPosOrderDetail);
                    return newPosOrderDetail;
                } else {

                    try {
                        float rediusAmount = policyPromotionDetailNum - detailAmount;
                        if (newPosOrderDetail.getOrderDetailAmount() > rediusAmount && rediusAmount > 0) {
                            PosOrderDetail secondNewPosDetail = RetailPosManager.getInstance().copyPosOrderDetail(newPosOrderDetail);
//                            PosOrderDetail secondNewPosDetail = (PosOrderDetail) newPosOrderDetail.clone();
                            TagUtils.removeVipTag(secondNewPosDetail);
                            secondNewPosDetail.setOrderDetailPolicyPromotionFlag(false);
                            secondNewPosDetail.setOrderDetailPolicyFid("");
                            secondNewPosDetail.setOrderDetailAmount(newPosOrderDetail.getOrderDetailAmount() - rediusAmount);
                            //添加优惠的
                            newPosOrderDetail.setOrderDetailAmount(rediusAmount);

                            float itemMinPrice = ItemPriceCheck.getItemMinPrice(posItem, policypromotionPrice);
                            if (itemMinPrice > policypromotionPrice) {
                                newPosOrderDetail.setOrderDetailPrice(itemMinPrice);//销售单价
                            } else {
                                newPosOrderDetail.setOrderDetailPrice(policypromotionPrice);
                            }

                            newPosOrderDetail.setOrderDetailPolicyPromotionFlag(true);  //特价促销标记
                            newPosOrderDetail.setOrderDetailPolicyFid(policypromotionPriceBean.getPolicypromotionId()); //促销政策流水号
                            posOrderDetails.addLast(newPosOrderDetail);
                            PosOrderCalcUtil.calcPosOrderDetail(newPosOrderDetail);

                            if (vipMemberPrice < secondNewPosDetail.getOrderDetailStdPrice()) {
                                float itemMinPrice1 = ItemPriceCheck.getItemMinPrice(posItem, vipMemberPrice);
                                if (itemMinPrice1 > vipMemberPrice) {
                                    secondNewPosDetail.setOrderDetailPrice(itemMinPrice1);//销售单价
                                } else {
                                    secondNewPosDetail.setOrderDetailPrice(vipMemberPrice);
                                }
                                TagUtils.addVipTag(secondNewPosDetail);
                            }
//
                            RetailPosManager.getInstance().showErrorInfo("超过促销限额，超出部分不享受活动");
                            posOrderDetails.addLast(secondNewPosDetail);
                            PosOrderCalcUtil.calcPosOrderDetail(secondNewPosDetail);
                            return secondNewPosDetail;
                        } else if (newPosOrderDetail.getOrderDetailAmount() <= rediusAmount) {
                            //添加优惠的
                            float itemMinPrice = ItemPriceCheck.getItemMinPrice(posItem, policypromotionPrice);
                            if (itemMinPrice > policypromotionPrice) {
                                newPosOrderDetail.setOrderDetailPrice(itemMinPrice);//销售单价
                            } else {
                                newPosOrderDetail.setOrderDetailPrice(policypromotionPrice);
                            }


                            newPosOrderDetail.setOrderDetailPolicyPromotionFlag(true);  //特价促销标记
                            newPosOrderDetail.setOrderDetailPolicyFid(policypromotionPriceBean.getPolicypromotionId()); //促销政策流水号
                            posOrderDetails.addLast(newPosOrderDetail);
                            PosOrderCalcUtil.calcPosOrderDetail(newPosOrderDetail);
                            return newPosOrderDetail;
                        } else {
                            //添加优惠的
                            if (vipMemberPrice < newPosOrderDetail.getOrderDetailStdPrice()) {
                                float itemMinPrice = ItemPriceCheck.getItemMinPrice(posItem, vipMemberPrice);
                                if (itemMinPrice > vipMemberPrice) {
                                    newPosOrderDetail.setOrderDetailPrice(itemMinPrice);//销售单价
                                } else {
                                    newPosOrderDetail.setOrderDetailPrice(vipMemberPrice);
                                }
                                TagUtils.addVipTag(newPosOrderDetail);
                            }
                            posOrderDetails.addLast(newPosOrderDetail);
                            PosOrderCalcUtil.calcPosOrderDetail(newPosOrderDetail);
                            return newPosOrderDetail;
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        return newPosOrderDetail;
                    }
                }
            }

        } else {
            posOrderDetails.addLast(newPosOrderDetail);
            PosOrderCalcUtil.calcPosOrderDetail(newPosOrderDetail);
            return newPosOrderDetail;
        }
    }

    /**
     * 重新刷新价格
     *
     * @param posOrder
     * @return
     */
    public static PosOrder afrechGoodsPrice(VipUserInfo vipUserInfo, PosOrder posOrder, LinkedList<PosOrderDetail> selectPosOrderDetails, boolean isChangeDetailPrice) {
        if (posOrder == null) return null;
        LinkedList<PosOrderDetail> posOrderDetails = new LinkedList<>();
        for (PosOrderDetail posOrderDetail : selectPosOrderDetails) {
            ConversionUtil.getPosOrderDetail(vipUserInfo, posOrderDetail, posOrderDetail.getPosItem(), posOrderDetail.getPosItemGrade(),
                    posOrderDetail.getOrderDetailAmount(), posOrderDetails, posOrderDetail.getOrderDetailMemo(), isChangeDetailPrice, false);

        }
        selectPosOrderDetails.clear();
        selectPosOrderDetails.addAll(posOrderDetails);
        posOrder.setPosOrderDetails(selectPosOrderDetails);
        PosOrderCalcUtil.calcPosOrder(posOrder);
        return posOrder;
    }


    /**
     * 重新刷新价格
     *
     * @param posOrder
     * @return
     */
    public static PosOrder afrechGoodsPriceBySettle(VipUserInfo vipUserInfo, PosOrder posOrder, LinkedList<PosOrderDetail> selectPosOrderDetails, boolean isChangeDetailPrice) {
        if (posOrder == null) return null;
        LinkedList<PosOrderDetail> posOrderDetails = new LinkedList<>();
        for (PosOrderDetail posOrderDetail : selectPosOrderDetails) {
            ConversionUtil.getPosOrderDetail(vipUserInfo, posOrderDetail, posOrderDetail.getPosItem(), posOrderDetail.getPosItemGrade(), posOrderDetail.getOrderDetailAmount(), posOrderDetails, posOrderDetail.getOrderDetailMemo(), isChangeDetailPrice, true);
        }
        selectPosOrderDetails.clear();
        selectPosOrderDetails.addAll(posOrderDetails);
        posOrder.setPosOrderDetails(selectPosOrderDetails);
        PosOrderCalcUtil.calcPosOrder(posOrder);
        return posOrder;
    }


    /**
     * 获取促销价
     *
     * @param posOrderDetail
     * @return
     */
    public static PolicypromotionPriceBean getPolicypromotionPrice(PosOrderDetail posOrderDetail, boolean isSettle) {
        PolicypromotionPriceBean policypromotionPriceBean = new PolicypromotionPriceBean();
        Float policypromotionPrice = Float.MAX_VALUE;
        if (LibConfig.allPolicyPromotionList != null) {
            PolicyPromotion promotion = PricePromotionCalUtils.getPolicypromotionPrice(posOrderDetail, LibConfig.allPolicyPromotionList, isSettle);
            if (promotion != null) {
                policypromotionPriceBean.setPolicypromotionId(promotion.getPolicy_promotion_no());
                if (promotion.getPolicyPromotionDetail() == null) {
                    policypromotionPrice = (float) (promotion.getPolicy_promotion_discount() * posOrderDetail.getOrderDetailStdPrice());

                } else {
                    policypromotionPrice = (float) promotion.getPolicyPromotionDetail().getPolicy_promotion_detail_special_price();
                    policypromotionPriceBean.setPolicyPromotionDetail(promotion.getPolicyPromotionDetail());
                }
            }
        }

        policypromotionPriceBean.setPrice(policypromotionPrice);
        return policypromotionPriceBean;
    }


    public static PosOrderDetail createCouponsDetail(CouponsBean couponsBean) {
        PosOrderDetail posOrderDetail = new PosOrderDetail();
        posOrderDetail.setOrderDetailType(LibConfig.C_ORDER_DETAIL_TYPE_COUPON);
        posOrderDetail.setOrderDetailAmount(1);
//        posOrderDetail.setItemNum(0);
        posOrderDetail.setOrderDetailItem(couponsBean.getTicket_send_detail_type());
        posOrderDetail.setOrderDetailPrice(couponsBean.getTicket_send_detail_value());
        posOrderDetail.setOrderDetailMoney(couponsBean.getTicket_send_detail_value() - couponsBean.getResidueMoney());
        posOrderDetail.setOrderDetailAppendMoney(0);
        posOrderDetail.setOrderDetailDiscount(0);
        posOrderDetail.setOrderDetailTax(0);
        posOrderDetail.setOrderDetailPaymentMoney(couponsBean.getTicket_send_detail_value());
        posOrderDetail.setOrderDetailStdPrice(couponsBean.getTicket_send_detail_value());

        PosOrderDetailState orderState = PosOrderDetailStateUtil.getOrderState(LibConfig.S_ORDER_DETAIL_SALE);
        posOrderDetail.setOrderDetailStateCode(orderState.POS_ORDER_DETAIL_STATE_CODE);
        posOrderDetail.setOrderDetailStateName(orderState.POS_ORDER_DETAIL_STATE_NAME);
        posOrderDetail.setOrderDetailTicketUuid(couponsBean.getTicket_send_detail_uuid());
        posOrderDetail.setOrderDetailMemo(couponsBean.getTicket_send_detail_print_num());
        posOrderDetail.setOrderDetailItemDepartment(couponsBean.getTicket_send_detail_send_branch_name());
        posOrderDetail.setOrderDetailTicketActionId(couponsBean.getAction_id());
        if (couponsBean.getTicket_send_detail_print_num().length() != 20) {
            posOrderDetail.setOrderDetailLotNumber(couponsBean.getTicket_category());
        } else {
            posOrderDetail.setOrderDetailLotNumber("");
        }


        return posOrderDetail;
    }


}
