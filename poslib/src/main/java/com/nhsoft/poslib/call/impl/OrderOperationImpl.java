package com.nhsoft.poslib.call.impl;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.call.callback.OrderOperationCallback;
import com.nhsoft.poslib.entity.BookResource;
import com.nhsoft.poslib.entity.ClientPoint;
import com.nhsoft.poslib.entity.ItemBar;
import com.nhsoft.poslib.entity.KeyGeneratorBizday;
import com.nhsoft.poslib.entity.PolicyDiscount;
import com.nhsoft.poslib.entity.PolicyDiscountDetail;
import com.nhsoft.poslib.entity.PolicyPromotionDetail;
import com.nhsoft.poslib.entity.PolicyQuantityDetail;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.PosItemGrade;
import com.nhsoft.poslib.entity.PosItemKit;
import com.nhsoft.poslib.entity.PosMachine;
import com.nhsoft.poslib.entity.TicketSendDetail;
import com.nhsoft.poslib.entity.order.Payment;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.entity.order.PosOrderKitDetail;
import com.nhsoft.poslib.entity.shift.ShiftTable;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.model.CouponsBean;
import com.nhsoft.poslib.model.PosOrderDetailState;
import com.nhsoft.poslib.model.PosOrderState;
import com.nhsoft.poslib.model.PosScaleStyleTypeBean;
import com.nhsoft.poslib.model.RedisBean;
import com.nhsoft.poslib.model.VipUserInfo;
import com.nhsoft.poslib.utils.EvtLog;
import com.nhsoft.poslib.utils.LogToFileUtils;
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
            PosOrder clonePosOrder = new PosOrder();
            clonePosOrder.setOrderNo(posOrder.getOrderNo());
            clonePosOrder.setLayawayOrderNo(posOrder.getLayawayOrderNo());
            clonePosOrder.setClientFid(posOrder.getClientFid());
            clonePosOrder.setStorehouseNum(posOrder.getStorehouseNum());
            clonePosOrder.setSystemBookCode(posOrder.getSystemBookCode());
            clonePosOrder.setBranchNum(posOrder.getBranchNum());
            clonePosOrder.setShiftTableNum(posOrder.getShiftTableNum());
            clonePosOrder.setShiftTableBizday(posOrder.getShiftTableBizday());
            clonePosOrder.setOrderDate(posOrder.getOrderDate());
            clonePosOrder.setOrderSoldBy(posOrder.getOrderSoldBy());
            clonePosOrder.setOrderOperator(posOrder.getOrderOperator());
            clonePosOrder.setOrderOperateTime(posOrder.getOrderOperateTime());
            clonePosOrder.setOrderFlag(posOrder.getOrderFlag());
            clonePosOrder.setOrderPrintedNum(posOrder.getOrderPrintedNum());
            clonePosOrder.setOrderCardUser(posOrder.getOrderCardUser());
            clonePosOrder.setOrderCardTypeDesc(posOrder.getOrderCardTypeDesc());
            clonePosOrder.setOrderDiscountMoney(posOrder.getOrderDiscountMoney());
            clonePosOrder.setOrderCommission(posOrder.getOrderCommission());
            clonePosOrder.setOrderTotalMoney(posOrder.getOrderTotalMoney());
            clonePosOrder.setOrderPaymentMoney(posOrder.getOrderPaymentMoney());
            clonePosOrder.setOrderRound(posOrder.getOrderRound());
            clonePosOrder.setOrderBalance(posOrder.getOrderBalance());
            clonePosOrder.setOrderTotalInvoice(posOrder.getOrderTotalInvoice());
            clonePosOrder.setOrderChange(posOrder.getOrderChange());
            clonePosOrder.setOrderTime(posOrder.getOrderTime());
            clonePosOrder.setOrderMachine(posOrder.getOrderMachine());
            clonePosOrder.setOrderChangeAuditor(posOrder.getOrderChangeAuditor());
            clonePosOrder.setOrderChangeTime(posOrder.getOrderChangeTime());
            clonePosOrder.setOrderPayee(posOrder.getOrderPayee());
            clonePosOrder.setOrderStateCode(posOrder.getOrderStateCode());
            clonePosOrder.setOrderStateName(posOrder.getOrderStateName());
            clonePosOrder.setOrderMemo(posOrder.getOrderMemo());
            clonePosOrder.setOrderRefBillno(posOrder.getOrderRefBillno());
            clonePosOrder.setOrderPoint(posOrder.getOrderPoint());
            clonePosOrder.setOrderGrossProfit(posOrder.getOrderGrossProfit());
            clonePosOrder.setOrderMgrDiscountMoney(posOrder.getOrderMgrDiscountMoney());
            clonePosOrder.setOrderCouponTotalMoney(posOrder.getOrderCouponTotalMoney());
            clonePosOrder.setOrderCouponPaymentMoney(posOrder.getOrderCouponPaymentMoney());
            clonePosOrder.setOrderCardUserNum(posOrder.getOrderCardUserNum());
            clonePosOrder.setOrderCardType(posOrder.getOrderCardType());
            clonePosOrder.setOrderSource(posOrder.getOrderSource());
            clonePosOrder.setOrderPostFee(posOrder.getOrderPostFee());
            clonePosOrder.setOrderPromotionDiscountMoney(posOrder.getOrderPromotionDiscountMoney());
            clonePosOrder.setOrderExternalNo(posOrder.getOrderExternalNo());
            clonePosOrder.setOrderDetailItemCount(posOrder.getOrderDetailItemCount());
            clonePosOrder.setOrderTimeChar(posOrder.getOrderTimeChar());
            clonePosOrder.setOrderStockFlag(posOrder.getOrderStockFlag());
            clonePosOrder.setOrderCardPhone(posOrder.getOrderCardPhone());
            clonePosOrder.setOrderCardChange(posOrder.getOrderCardChange());
            clonePosOrder.setOrderTaxMoney(posOrder.getOrderTaxMoney());
            clonePosOrder.setOrderTmallMemo(posOrder.getOrderTmallMemo());
            clonePosOrder.setOrderUserGroup(posOrder.getOrderUserGroup());
            clonePosOrder.setOrderOnlineDiscount(posOrder.getOrderOnlineDiscount());
            clonePosOrder.setMerchantNum(posOrder.getMerchantNum());
            clonePosOrder.setStallNum(posOrder.getStallNum());
            clonePosOrder.setOrderCostMoney(posOrder.getOrderCostMoney());
            clonePosOrder.setOrderPayNo(posOrder.getOrderPayNo());
            clonePosOrder.setOrderUploadState(posOrder.getOrderUploadState());
            clonePosOrder.setOrderRemortInsertBean(posOrder.getOrderRemortInsertBean());
            clonePosOrder.setOpenId(posOrder.getOpenId());
            clonePosOrder.setAlipayUserId(posOrder.getAlipayUserId());
            clonePosOrder.setCustomerId(posOrder.getCustomerId());
            clonePosOrder.setPayBarCode(posOrder.getPayBarCode());
            clonePosOrder.setRoundPayment(posOrder.isRoundPayment());
            clonePosOrder.setQuickZeroMoney(posOrder.getQuickZeroMoney());

            if(posOrder.getRedisBean() != null){
                LogToFileUtils.write("clonePosOrder: getRedisBean");
                String json = new Gson().toJson(posOrder.getRedisBean());
                final RedisBean redisBean = new Gson().fromJson(json, RedisBean.class);
                clonePosOrder.setRedisBean(redisBean);
            }

            if(posOrder.getCouponsRedisBean() != null){
                LogToFileUtils.write("clonePosOrder: getCouponsRedisBean");
                String json = new Gson().toJson(posOrder.getCouponsRedisBean());
                final RedisBean couponsRedisBean = new Gson().fromJson(json, RedisBean.class);
                clonePosOrder.setCouponsRedisBean(couponsRedisBean);
            }

            if(posOrder.getVipUserInfo() != null){
                LogToFileUtils.write("clonePosOrder: getVipUserInfo");
                String json = new Gson().toJson(posOrder.getVipUserInfo());
                final VipUserInfo vipUserInfo = new Gson().fromJson(json, VipUserInfo.class);
                clonePosOrder.setVipUserInfo(vipUserInfo);
            }


            if(posOrder.getClientPoint() != null){
                LogToFileUtils.write("clonePosOrder: getClientPoint");
                String json = new Gson().toJson(posOrder.getClientPoint());
                final ClientPoint clientPoint = new Gson().fromJson(json, ClientPoint.class);
                clonePosOrder.setClientPoint(clientPoint);
            }

            if(posOrder.getTicketSendDetails() != null && posOrder.getTicketSendDetails().size() > 0){
                LogToFileUtils.write("clonePosOrder: getTicketSendDetails");
                List<TicketSendDetail> ticketSendDetails = new ArrayList<>();
                for (TicketSendDetail ticketSendDetail : posOrder.getTicketSendDetails()){
                    String json = new Gson().toJson(ticketSendDetail);
                    final TicketSendDetail sendDetail = new Gson().fromJson(json, TicketSendDetail.class);
                    ticketSendDetails.add(sendDetail);
                }
                clonePosOrder.setTicketSendDetails(ticketSendDetails);
            }


            if(posOrder.getUseConponsList() != null && posOrder.getUseConponsList().size() > 0){
                LogToFileUtils.write("clonePosOrder: getUseConponsList");
                List<CouponsBean> couponsBeans = new ArrayList<>();
                for (CouponsBean couponsBean : posOrder.getUseConponsList()){
                    String json = new Gson().toJson(couponsBean);
                    final CouponsBean couponsBean1 = new Gson().fromJson(json, CouponsBean.class);
                    couponsBeans.add(couponsBean1);
                }
                clonePosOrder.setUseConponsList(couponsBeans);
            }

            if(posOrder.getMercuryConponsList() != null && posOrder.getMercuryConponsList().size() > 0){
                LogToFileUtils.write("clonePosOrder: getMercuryConponsList");
                List<CouponsBean> couponsBeans = new ArrayList<>();
                for (CouponsBean couponsBean : posOrder.getMercuryConponsList()){
                    String json = new Gson().toJson(couponsBean);
                    final CouponsBean couponsBean1 = new Gson().fromJson(json, CouponsBean.class);
                    couponsBeans.add(couponsBean1);
                }
                clonePosOrder.setMercuryConponsList(couponsBeans);
            }


            if(posOrder.getPosOrderDetails() != null && posOrder.getPosOrderDetails().size() > 0){
                LogToFileUtils.write("clonePosOrder: getPosOrderDetails");
                List<PosOrderDetail> posOrderDetails = new ArrayList<>();
                for (PosOrderDetail posOrderDetail : posOrder.getPosOrderDetails()){
                    final PosOrderDetail posOrderDetail1 = copayPosOrderDetail(posOrderDetail);
                    posOrderDetails.add(posOrderDetail1);
                }
                clonePosOrder.setPosOrderDetails(posOrderDetails);
            }


            if(posOrder.getPayments() != null && posOrder.getPayments().size() > 0){
                LogToFileUtils.write("clonePosOrder: getPayments");
                List<Payment> payments = new ArrayList<>();
                for (Payment payment : posOrder.getPayments()){
                    final Payment payment1 = copyPayment(payment);
                    payments.add(payment1);
                }
                clonePosOrder.setPayments(payments);
            }


            LogToFileUtils.write("clonePosOrder: ok");
//            String json = new Gson().toJson(posOrder);
//
//            final PosOrder nowPosOrder = new Gson().fromJson(json,PosOrder.class);
//            List<PosOrderDetail> localposOrderDetails = new ArrayList<>();
//            List<PosOrderDetail> posOrderDetails = posOrder.getPosOrderDetails();
//            for (PosOrderDetail posOrderDetail : posOrderDetails) {
//                localposOrderDetails.add((PosOrderDetail) posOrderDetail.clone());
//            }
//            nowPosOrder.setPosOrderDetails(localposOrderDetails);
            return clonePosOrder;

        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public PosOrderDetail copayPosOrderDetail(PosOrderDetail posOrderDetail) {
        try {
            PosOrderDetail clonePosOrderDetail = new PosOrderDetail();
            clonePosOrderDetail.setId(posOrderDetail.getId());
            clonePosOrderDetail.setOrderNo(posOrderDetail.getOrderNo());
            clonePosOrderDetail.setOrderDetailNum(posOrderDetail.getOrderDetailNum());
            clonePosOrderDetail.setItemNum(posOrderDetail.getItemNum());
            clonePosOrderDetail.setOrderDetailType(posOrderDetail.getOrderDetailType());
            clonePosOrderDetail.setOrderDetailItem(posOrderDetail.getOrderDetailItem());
            clonePosOrderDetail.setOrderDetailItemDepartment(posOrderDetail.getOrderDetailItemDepartment());
            clonePosOrderDetail.setOrderDetailStdPrice(posOrderDetail.getOrderDetailStdPrice());
            clonePosOrderDetail.setOrderDetailPrice(posOrderDetail.getOrderDetailPrice());
            clonePosOrderDetail.setOrderDetailAmount(posOrderDetail.getOrderDetailAmount());
            clonePosOrderDetail.setOrderDetailMoney(posOrderDetail.getOrderDetailMoney());
            clonePosOrderDetail.setOrderDetailAppendMoney(posOrderDetail.getOrderDetailAppendMoney());
            clonePosOrderDetail.setOrderDetailDiscount(posOrderDetail.getOrderDetailDiscount());
            clonePosOrderDetail.setOrderDetailTax(posOrderDetail.getOrderDetailTax());
            clonePosOrderDetail.setOrderDetailPaymentMoney(posOrderDetail.getOrderDetailPaymentMoney());
            clonePosOrderDetail.setOrderDetailStateCode(posOrderDetail.getOrderDetailStateCode());
            clonePosOrderDetail.setOrderDetailStateName(posOrderDetail.getOrderDetailStateName());
            clonePosOrderDetail.setOrderDetailAuditor(posOrderDetail.getOrderDetailAuditor());
            clonePosOrderDetail.setOrderDetailMemo(posOrderDetail.getOrderDetailMemo());
            clonePosOrderDetail.setOrderDetailItemMatrixNum(posOrderDetail.getOrderDetailItemMatrixNum());
            clonePosOrderDetail.setOrderDetailItemSerialNumber(posOrderDetail.getOrderDetailItemSerialNumber());
            clonePosOrderDetail.setOrderDetailCommission(posOrderDetail.getOrderDetailCommission());
            clonePosOrderDetail.setOrderDetailPolicyPromotionFlag(posOrderDetail.getOrderDetailPolicyPromotionFlag());
            clonePosOrderDetail.setOrderDetailPolicyPromotionQuantityFlag(posOrderDetail.getOrderDetailPolicyPromotionQuantityFlag());
            clonePosOrderDetail.setOrderDetailPolicyPromotionMoneyFlag(posOrderDetail.getOrderDetailPolicyPromotionMoneyFlag());
            clonePosOrderDetail.setOrderDetailPolicyPresentFlag(posOrderDetail.getOrderDetailPolicyPresentFlag());
            clonePosOrderDetail.setOrderDetailPolicyDiscountFlag(posOrderDetail.getOrderDetailPolicyDiscountFlag());
            clonePosOrderDetail.setOrderDetailPolicyFid(posOrderDetail.getOrderDetailPolicyFid());
            clonePosOrderDetail.setOrderDetailPolicyDetailNum(posOrderDetail.getOrderDetailPolicyDetailNum());
            clonePosOrderDetail.setOrderDetailCost(posOrderDetail.getOrderDetailCost());
            clonePosOrderDetail.setOrderDetailGrossProfit(posOrderDetail.getOrderDetailGrossProfit());
            clonePosOrderDetail.setOrderDetailProducingDate(posOrderDetail.getOrderDetailProducingDate());
            clonePosOrderDetail.setOrderDetailLotNumber(posOrderDetail.getOrderDetailLotNumber());
            clonePosOrderDetail.setOrderDetailTicketUuid(posOrderDetail.getOrderDetailTicketUuid());
            clonePosOrderDetail.setOrderDetailAssistAmount(posOrderDetail.getOrderDetailAssistAmount());
            clonePosOrderDetail.setOrderDetailPromotionType(posOrderDetail.getOrderDetailPromotionType());
            clonePosOrderDetail.setOrderDetailTicketActionId(posOrderDetail.getOrderDetailTicketActionId());
            clonePosOrderDetail.setItemGradeNum(posOrderDetail.getItemGradeNum());
            clonePosOrderDetail.setOrderDetailOnlineUnit(posOrderDetail.getOrderDetailOnlineUnit());
            clonePosOrderDetail.setOrderDetailOnlineQty(posOrderDetail.getOrderDetailOnlineQty());
            clonePosOrderDetail.setOrderDetailOnlinePrice(posOrderDetail.getOrderDetailOnlinePrice());
            clonePosOrderDetail.setOrderDetailHasKit(posOrderDetail.getOrderDetailHasKit());
            clonePosOrderDetail.setSystemBookCode(posOrderDetail.getSystemBookCode());
            clonePosOrderDetail.setBranchNum(posOrderDetail.getBranchNum());
            clonePosOrderDetail.setPaymentBalance(posOrderDetail.getPaymentBalance());
            clonePosOrderDetail.setOrderDetailRound(posOrderDetail.getOrderDetailRound());
            clonePosOrderDetail.setStall_num(posOrderDetail.getStall_num());
            clonePosOrderDetail.setOrderDetailShareDiscount(posOrderDetail.getOrderDetailShareDiscount());
            clonePosOrderDetail.setKitAmountStr(posOrderDetail.getKitAmountStr());
            clonePosOrderDetail.setOrderDetailPolicyPromotionQuantityFlagTwo(posOrderDetail.getOrderDetailPolicyPromotionQuantityFlagTwo());
            clonePosOrderDetail.setOrder_detail_merchat_rate(posOrderDetail.getOrder_detail_merchat_rate());
            clonePosOrderDetail.setResidueMoney(posOrderDetail.getResidueMoney());
            clonePosOrderDetail.setOrderChangeAmount(posOrderDetail.getOrderChangeAmount());
            clonePosOrderDetail.setQuitCoupons(posOrderDetail.isQuitCoupons());

            if(posOrderDetail.getPosOrderKitDetails() != null && posOrderDetail.getPosOrderKitDetails().size() > 0){
                List<PosOrderKitDetail> orderKitDetails = new ArrayList<>();
                for (PosOrderKitDetail posOrderKitDetail : posOrderDetail.getPosOrderKitDetails()){
                    PosOrderKitDetail copyPosOrderKitDetail = copyPosOrderKitDetail(posOrderKitDetail);
                    orderKitDetails.add(copyPosOrderKitDetail);
                }
                clonePosOrderDetail.setPosOrderKitDetails(orderKitDetails);
            }
            if(posOrderDetail.getPolicyDiscounts() != null && posOrderDetail.getPolicyDiscounts().size() > 0){
                List<PolicyDiscount> policyDiscounts = new ArrayList<>();
                for (PolicyDiscount policyDiscount : posOrderDetail.getPolicyDiscounts()){
                    PolicyDiscount copyPolicyDiscount = copyPolicyDiscount(policyDiscount);
                    policyDiscounts.add(copyPolicyDiscount);
                }
                clonePosOrderDetail.setPolicyDiscounts(policyDiscounts);
            }

            if(posOrderDetail.getPolicyPromotionDetail() != null){
                PolicyPromotionDetail policyPromotionDetail = copyPolicyPromotionDetail(posOrderDetail.getPolicyPromotionDetail());
                clonePosOrderDetail.setPolicyPromotionDetail(policyPromotionDetail);
            }

            if(posOrderDetail.getPolicyQuantityDetail() != null){
                PolicyQuantityDetail policyQuantityDetail = copyPolicyQuantityDetail(posOrderDetail.getPolicyQuantityDetail());
                clonePosOrderDetail.setPolicyQuantityDetail(policyQuantityDetail);
            }

            if(posOrderDetail.getPosItem() != null){
                PosItem copyPosItem = copyPosItem(posOrderDetail.getPosItem());
                clonePosOrderDetail.setPosItem(copyPosItem);
            }

            if(posOrderDetail.getPosItemGrade() != null){
                PosItemGrade copyPosItemGrade = copyPosItemGrade(posOrderDetail.getPosItemGrade());
                clonePosOrderDetail.setPosItemGrade(copyPosItemGrade);
            }


            return clonePosOrderDetail;




//            String json = new Gson().toJson(posOrderDetail);
//
//            final PosOrderDetail nowPosOrderdetail = new Gson().fromJson(json,PosOrderDetail.class);
//            List<PosOrderDetail> localposOrderDetails = new ArrayList<>();
//            List<PosOrderDetail> posOrderDetails = posOrder.getPosOrderDetails();
//            for (PosOrderDetail posOrderDetail : posOrderDetails) {
//                localposOrderDetails.add((PosOrderDetail) posOrderDetail.clone());
//            }
//            nowPosOrder.setPosOrderDetails(localposOrderDetails);
//            return nowPosOrderdetail;

        } catch (Exception e) {
            return null;
        }
    }



    private PosItem copyPosItem(PosItem posItem){
        PosItem copyPosItem = new PosItem();

        copyPosItem.setItem_num(posItem.getItem_num());
        copyPosItem.setItem_name(posItem.getItem_name());
        copyPosItem.setStore_item_pinyin(posItem.getStore_item_pinyin());
        copyPosItem.setItem_type(posItem.getItem_type());
        copyPosItem.setItem_unit(posItem.getItem_unit());
        copyPosItem.setItem_category(posItem.getItem_category());
        copyPosItem.setItem_min_price(posItem.getItem_min_price());
        copyPosItem.setItem_category_code(posItem.getItem_category_code());
        copyPosItem.setItem_department(posItem.getItem_department());
        copyPosItem.setItem_sale_message(posItem.getItem_sale_message());
        copyPosItem.setItem_pos_change_price_flag(posItem.getItem_pos_change_price_flag());
        copyPosItem.setItem_del_tag(posItem.getItem_del_tag());
        copyPosItem.setItem_point_actived(posItem.getItem_point_actived());
        copyPosItem.setItem_point_value(posItem.getItem_point_value());
        copyPosItem.setItem_brand(posItem.getItem_brand());
        copyPosItem.setItem_cost_mode(posItem.getItem_cost_mode());
        copyPosItem.setItem_valid_period(posItem.getItem_valid_period());
        copyPosItem.setItem_cost_price(posItem.getItem_cost_price());
        copyPosItem.setItem_transfer_price(posItem.getItem_transfer_price());
        copyPosItem.setItem_assist_unit(posItem.getItem_assist_unit());
        copyPosItem.setItem_sequence(posItem.getItem_sequence());
        copyPosItem.setItem_eliminative_flag(posItem.getItem_eliminative_flag());
        copyPosItem.setItem_create_time(posItem.getItem_create_time());
        copyPosItem.setItem_last_edit_time(posItem.getItem_last_edit_time());
        copyPosItem.setItem_assist_rate(posItem.getItem_assist_rate());
        copyPosItem.setItem_status(posItem.getItem_status());
        copyPosItem.setItem_discounted(posItem.getItem_discounted());
        copyPosItem.setItem_weight_flag(posItem.getItem_weight_flag());
        copyPosItem.setBranch_sale_cease_flag(posItem.getBranch_sale_cease_flag());
        copyPosItem.setItem_sale_cease_flag(posItem.getItem_sale_cease_flag());
        copyPosItem.setBranch_max_price(posItem.getBranch_max_price());
        copyPosItem.setBranch_min_price(posItem.getBranch_min_price());
        copyPosItem.setItem_max_price(posItem.getItem_max_price());
        copyPosItem.setBranch_regular_price(posItem.getBranch_regular_price());
        copyPosItem.setBranch_level2_price(posItem.getBranch_level2_price());
        copyPosItem.setBranch_level3_price(posItem.getBranch_level3_price());
        copyPosItem.setBranch_level4_price(posItem.getBranch_level4_price());
        copyPosItem.setBranch_num(posItem.getBranch_num());
        copyPosItem.setItem_regular_price(posItem.getItem_regular_price());
        copyPosItem.setItem_level2_price(posItem.getItem_level2_price());
        copyPosItem.setItem_level3_price(posItem.getItem_level3_price());
        copyPosItem.setItem_level4_price(posItem.getItem_level4_price());
        copyPosItem.setPos_item_selected(posItem.getPos_item_selected());
        copyPosItem.setPos_images_json(posItem.getPos_images_json());
        copyPosItem.setSelectCount(posItem.getSelectCount());
        copyPosItem.setItem_barcode(posItem.getItem_barcode());

        if(posItem.getShowPosItemGrade() != null){
            PosItemGrade copyPosItemGrade = copyPosItemGrade(posItem.getShowPosItemGrade());
            copyPosItem.setShowPosItemGrade(copyPosItemGrade);
        }

        if(posItem.getPosItemGrade() != null){
            copyPosItem.setPosItemGrade(copyPosItemGrade(posItem.getPosItemGrade()));
        }

        List<PosItemKit> allPosItemKit = PosItemImpl.getInstance().getAllPosItemKit(posItem.getItem_num());
        if(allPosItemKit != null && allPosItemKit.size() > 0){
            List<PosItemKit> posItemKits = new ArrayList<>();

            for (PosItemKit posItemKit : allPosItemKit){
                posItemKits.add(copyPosItemKit(posItemKit));
            }

            posItem.setPosItemKits(posItemKits);
        }

        if(posItem.getItemBar() != null){
            copyPosItem.setItemBar(copyItemBar(posItem.getItemBar(),copyPosItem));
        }

        return copyPosItem;
    }


    private PosItemGrade copyPosItemGrade(PosItemGrade posItemGrade){
        PosItemGrade copyPosItemGrade = new PosItemGrade();

        copyPosItemGrade.setId(posItemGrade.getId());
        copyPosItemGrade.setItem_num(posItemGrade.getItem_num());
        copyPosItemGrade.setItem_grade_num(posItemGrade.getItem_grade_num());
        copyPosItemGrade.setItem_grade_code(posItemGrade.getItem_grade_code());
        copyPosItemGrade.setItem_grade_barcode(posItemGrade.getItem_grade_barcode());
        copyPosItemGrade.setItem_grade_name(posItemGrade.getItem_grade_name());
        copyPosItemGrade.setItem_grade_discounted(posItemGrade.getItem_grade_discounted());
        copyPosItemGrade.setItem_grade_pinyin(posItemGrade.getItem_grade_pinyin());
        copyPosItemGrade.setBranch_grade_regular_price(posItemGrade.getBranch_grade_regular_price());
        copyPosItemGrade.setBranch_grade_level2_price(posItemGrade.getBranch_grade_level2_price());
        copyPosItemGrade.setBranch_grade_level3_price(posItemGrade.getBranch_grade_level3_price());
        copyPosItemGrade.setBranch_grade_level4_price(posItemGrade.getBranch_grade_level4_price());
        copyPosItemGrade.setItem_grade_regular_price(posItemGrade.getItem_grade_regular_price());
        copyPosItemGrade.setItem_grade_level2_price(posItemGrade.getItem_grade_level2_price());
        copyPosItemGrade.setItem_grade_level3_price(posItemGrade.getItem_grade_level3_price());
        copyPosItemGrade.setItem_grade_level4_price(posItemGrade.getItem_grade_level4_price());
        copyPosItemGrade.setItem_grade_max_price(posItemGrade.getItem_grade_max_price());
        copyPosItemGrade.setItem_grade_min_price(posItemGrade.getItem_grade_min_price());


        return copyPosItemGrade;
    }


    private PosItemKit copyPosItemKit(PosItemKit posItemKit){
        PosItemKit copyPosItemKit = new PosItemKit();

        copyPosItemKit.setId(posItemKit.getId());
        copyPosItemKit.setItem_num(posItemKit.getItem_num());
        copyPosItemKit.setKit_item_num(posItemKit.getKit_item_num());
        copyPosItemKit.setPos_item_amount_un_fixed(posItemKit.getPos_item_amount_un_fixed());
        copyPosItemKit.setPos_item_kit_amount(posItemKit.getPos_item_kit_amount());
        copyPosItemKit.setInput_kit_amount(posItemKit.getInput_kit_amount());
        copyPosItemKit.setInput_host_amount(posItemKit.getPos_item_kit_amount());

        return copyPosItemKit;
    }

    private ItemBar copyItemBar(ItemBar itemBar,PosItem posItem){
        ItemBar copyItemBar = new ItemBar();
        copyItemBar.setId(itemBar.getId());
        copyItemBar.setItem_num(itemBar.getItem_num());
        copyItemBar.setItem_bar_num(itemBar.getItem_bar_num());
        copyItemBar.setItem_bar_code(itemBar.getItem_bar_code());
        copyItemBar.setItem_bar_rate(itemBar.getItem_bar_rate());
        copyItemBar.setItem_valid_period(itemBar.getItem_valid_period());
        if(itemBar.getPosItem() != null){
            copyItemBar.setPosItem(posItem);
        }
        return copyItemBar;
    }

    private PolicyQuantityDetail copyPolicyQuantityDetail(PolicyQuantityDetail policyQuantityDetail){
        PolicyQuantityDetail copyPolicyQuantityDetail = new PolicyQuantityDetail();

        copyPolicyQuantityDetail.setId(policyQuantityDetail.getId());
        copyPolicyQuantityDetail.setPromotion_quantity_no(policyQuantityDetail.getPromotion_quantity_no());
        copyPolicyQuantityDetail.setPromotion_quantity_detail_num(policyQuantityDetail.getPromotion_quantity_detail_num());
        copyPolicyQuantityDetail.setItem_num(policyQuantityDetail.getItem_num());
        copyPolicyQuantityDetail.setPromotion_quantity_detail_std_price(policyQuantityDetail.getPromotion_quantity_detail_std_price());
        copyPolicyQuantityDetail.setPromotion_quantity_detail_special_price(policyQuantityDetail.getPromotion_quantity_detail_special_price());
        copyPolicyQuantityDetail.setPromotion_quantity_detail_min_amount(policyQuantityDetail.getPromotion_quantity_detail_min_amount());
        copyPolicyQuantityDetail.setPromotion_quantity_detail_cost(policyQuantityDetail.getPromotion_quantity_detail_cost());
        return copyPolicyQuantityDetail;
    }

    private PolicyPromotionDetail copyPolicyPromotionDetail(PolicyPromotionDetail policyPromotionDetail){

        PolicyPromotionDetail copyPolicyPromotionDetail = new PolicyPromotionDetail();

        copyPolicyPromotionDetail.setId(policyPromotionDetail.getId());
        copyPolicyPromotionDetail.setPolicy_promotion_no(policyPromotionDetail.getPolicy_promotion_no());
        copyPolicyPromotionDetail.setPolicy_promotion_detail_num(policyPromotionDetail.getPolicy_promotion_detail_num());
        copyPolicyPromotionDetail.setItem_num(policyPromotionDetail.getItem_num());
        copyPolicyPromotionDetail.setItem_grade_num(policyPromotionDetail.getItem_grade_num());
        copyPolicyPromotionDetail.setPolicy_promotion_detail_std_price(policyPromotionDetail.getPolicy_promotion_detail_std_price());
        copyPolicyPromotionDetail.setPolicy_promotion_detail_special_price(policyPromotionDetail.getPolicy_promotion_detail_special_price());
        copyPolicyPromotionDetail.setPolicy_promotion_detail_bill_limit(policyPromotionDetail.getPolicy_promotion_detail_bill_limit());
        copyPolicyPromotionDetail.setPolicy_promotion_detail_policy_limit(policyPromotionDetail.getPolicy_promotion_detail_policy_limit());
        copyPolicyPromotionDetail.setPolicy_promotion_detail_cost(policyPromotionDetail.getPolicy_promotion_detail_cost());
        copyPolicyPromotionDetail.setPolicy_promotion_detail_lot_num(policyPromotionDetail.getPolicy_promotion_detail_lot_num());
        copyPolicyPromotionDetail.setSystem_book_code(policyPromotionDetail.getSystem_book_code());
        copyPolicyPromotionDetail.setBranch_num(policyPromotionDetail.getBranch_num());
        copyPolicyPromotionDetail.setPolicy_promotion_detail_gross(policyPromotionDetail.getPolicy_promotion_detail_gross());
        copyPolicyPromotionDetail.setPolicy_promotion_detail_policy_cost(policyPromotionDetail.getPolicy_promotion_detail_policy_cost());



        return copyPolicyPromotionDetail;
    }


    private PolicyDiscount copyPolicyDiscount(PolicyDiscount policyDiscount){
        PolicyDiscount clonePolicyDiscount = new PolicyDiscount();

        clonePolicyDiscount.setPolicy_discount_no(policyDiscount.getPolicy_discount_no());
        clonePolicyDiscount.setSystem_book_code(policyDiscount.getSystem_book_code());
        clonePolicyDiscount.setPolicy_discount_date_from(policyDiscount.getPolicy_discount_date_from());
        clonePolicyDiscount.setPolicy_discount_date_to(policyDiscount.getPolicy_discount_date_to());
        clonePolicyDiscount.setPolicy_discount_time_from(policyDiscount.getPolicy_discount_time_from());
        clonePolicyDiscount.setPolicy_discount_time_to(policyDiscount.getPolicy_discount_time_to());
        clonePolicyDiscount.setPolicy_discount_applied_branch(policyDiscount.getPolicy_discount_applied_branch());
        clonePolicyDiscount.setPolicy_discount_mon_actived(policyDiscount.getPolicy_discount_mon_actived());
        clonePolicyDiscount.setPolicy_discount_tues_actived(policyDiscount.getPolicy_discount_tues_actived());
        clonePolicyDiscount.setPolicy_discount_wed_actived(policyDiscount.getPolicy_discount_wed_actived());
        clonePolicyDiscount.setPolicy_discount_thurs_actived(policyDiscount.getPolicy_discount_thurs_actived());
        clonePolicyDiscount.setPolicy_discount_friday_actived(policyDiscount.getPolicy_discount_friday_actived());
        clonePolicyDiscount.setPolicy_discount_sat_actived(policyDiscount.getPolicy_discount_sat_actived());
        clonePolicyDiscount.setPolicy_discount_sun_actived(policyDiscount.getPolicy_discount_sun_actived());
        clonePolicyDiscount.setPolicy_discount_state_code(policyDiscount.getPolicy_discount_state_code());
        clonePolicyDiscount.setPolicy_discount_state_name(policyDiscount.getPolicy_discount_state_name());
        clonePolicyDiscount.setPolicy_discount_create_time(policyDiscount.getPolicy_discount_create_time());
        clonePolicyDiscount.setPolicy_discount_creator(policyDiscount.getPolicy_discount_creator());
        clonePolicyDiscount.setPolicy_discount_audit_time(policyDiscount.getPolicy_discount_audit_time());
        clonePolicyDiscount.setPolicy_discount_auditor(policyDiscount.getPolicy_discount_auditor());
        clonePolicyDiscount.setPolicy_discount_memo(policyDiscount.getPolicy_discount_memo());
        clonePolicyDiscount.setPolicy_discount_cancel_time(policyDiscount.getPolicy_discount_cancel_time());
        clonePolicyDiscount.setPolicy_discount_cancel_operator(policyDiscount.getPolicy_discount_cancel_operator());
        clonePolicyDiscount.setBranch_num(policyDiscount.getBranch_num());
        clonePolicyDiscount.setPolicy_discount_bill_money(policyDiscount.getPolicy_discount_bill_money());
        clonePolicyDiscount.setPolicy_discount_discount(policyDiscount.getPolicy_discount_discount());
        clonePolicyDiscount.setPolicy_discount_card_only(policyDiscount.getPolicy_discount_card_only());
        clonePolicyDiscount.setPolicy_discount_card_type(policyDiscount.getPolicy_discount_card_type());
        clonePolicyDiscount.setPolicy_discount_repeat_type(policyDiscount.getPolicy_discount_repeat_type());
        clonePolicyDiscount.setPolicy_discount_repeat_end(policyDiscount.getPolicy_discount_repeat_end());
        clonePolicyDiscount.setPolicy_discount_discount_money(policyDiscount.getPolicy_discount_discount_money());
        clonePolicyDiscount.setPolicy_discount_total_discount(policyDiscount.getPolicy_discount_total_discount());
        clonePolicyDiscount.setPolicy_discount_assigned_item(policyDiscount.getPolicy_discount_assigned_item());
        clonePolicyDiscount.setPolicy_discount_assigned_category(policyDiscount.getPolicy_discount_assigned_category());
        clonePolicyDiscount.setPolicy_discount_assigned_type(policyDiscount.getPolicy_discount_assigned_type());
        clonePolicyDiscount.setPolicy_discount_department(policyDiscount.getPolicy_discount_department());
        clonePolicyDiscount.setPolicy_discount_last_edit_time(policyDiscount.getPolicy_discount_last_edit_time());
        clonePolicyDiscount.setPolicy_discount_last_editor(policyDiscount.getPolicy_discount_last_editor());
        clonePolicyDiscount.setPolicy_discount_type(policyDiscount.getPolicy_discount_type());
        clonePolicyDiscount.setPolicy_discount_level_ids(policyDiscount.getPolicy_discount_level_ids());

        if(policyDiscount.getPolicy_discount_details() != null && policyDiscount.getPolicy_discount_details().size() > 0){
            List<PolicyDiscountDetail> policyDiscountDetails = new ArrayList<>();
            for (PolicyDiscountDetail policyDiscountDetail : policyDiscount.getPolicy_discount_details()){
                PolicyDiscountDetail copyPolicyDiscountDetail = copyPolicyDiscountDetail(policyDiscountDetail);
                policyDiscountDetails.add(copyPolicyDiscountDetail);
            }
            clonePolicyDiscount.setPolicyDiscountDetalils(policyDiscountDetails);
        }

        return clonePolicyDiscount;
    }


    private PolicyDiscountDetail copyPolicyDiscountDetail(PolicyDiscountDetail policyDiscountDetail){
        PolicyDiscountDetail clonePolicyDiscountDetail = new PolicyDiscountDetail();
        clonePolicyDiscountDetail.setId(policyDiscountDetail.getId());
        clonePolicyDiscountDetail.setPolicy_discount_no(policyDiscountDetail.getPolicy_discount_no());
        clonePolicyDiscountDetail.setPolicy_discount_detail_num(policyDiscountDetail.getPolicy_discount_detail_num());
        clonePolicyDiscountDetail.setItem_num(policyDiscountDetail.getItem_num());
        clonePolicyDiscountDetail.setSystem_book_code(policyDiscountDetail.getSystem_book_code());
        clonePolicyDiscountDetail.setPolicy_discount_detail_memo(policyDiscountDetail.getPolicy_discount_detail_memo());
        clonePolicyDiscountDetail.setBranch_num(policyDiscountDetail.getBranch_num());
        return clonePolicyDiscountDetail;
    }


    private PosOrderKitDetail copyPosOrderKitDetail(PosOrderKitDetail posOrderKitDetail){
        PosOrderKitDetail copyPosOrderKitDetail = new PosOrderKitDetail();

        copyPosOrderKitDetail.setId(posOrderKitDetail.getId());
        copyPosOrderKitDetail.setOrderNo(posOrderKitDetail.getOrderNo());
        copyPosOrderKitDetail.setOrderDetailNum(posOrderKitDetail.getOrderDetailNum());
        copyPosOrderKitDetail.setOrderKitDetailNum(posOrderKitDetail.getOrderKitDetailNum());
        copyPosOrderKitDetail.setItemNum(posOrderKitDetail.getItemNum());
        copyPosOrderKitDetail.setOrderKitDetailItemName(posOrderKitDetail.getOrderKitDetailItemName());
        copyPosOrderKitDetail.setOrderKitDetailDepartment(posOrderKitDetail.getOrderKitDetailDepartment());
        copyPosOrderKitDetail.setOrderKitDetailStdPrice(posOrderKitDetail.getOrderKitDetailStdPrice());
        copyPosOrderKitDetail.setOrderKitDetailPrice(posOrderKitDetail.getOrderKitDetailPrice());
        copyPosOrderKitDetail.setOrderKitDetailAmount(posOrderKitDetail.getOrderKitDetailAmount());
        copyPosOrderKitDetail.setOrderKitDetailMoney(posOrderKitDetail.getOrderKitDetailMoney());
        copyPosOrderKitDetail.setOrderKitDetailAppendMoney(posOrderKitDetail.getOrderKitDetailAppendMoney());
        copyPosOrderKitDetail.setOrderKitDetailDiscount(posOrderKitDetail.getOrderKitDetailDiscount());
        copyPosOrderKitDetail.setOrderKitDetailTax(posOrderKitDetail.getOrderKitDetailTax());
        copyPosOrderKitDetail.setOrderKitDetailPaymentMoney(posOrderKitDetail.getOrderKitDetailPaymentMoney());
        copyPosOrderKitDetail.setOrderKitDetailItemMatrixNum(posOrderKitDetail.getOrderKitDetailItemMatrixNum());
        copyPosOrderKitDetail.setOrderKitDetailItemSerialNumber(posOrderKitDetail.getOrderKitDetailItemSerialNumber());
        copyPosOrderKitDetail.setOrderKitDetailMemo(posOrderKitDetail.getOrderKitDetailMemo());
        copyPosOrderKitDetail.setOrderKitDetailCost(posOrderKitDetail.getOrderKitDetailCost());
        copyPosOrderKitDetail.setOrderKitDetailGrossProfit(posOrderKitDetail.getOrderKitDetailGrossProfit());
        copyPosOrderKitDetail.setOrderKitDetailProducingDate(posOrderKitDetail.getOrderKitDetailProducingDate());
        copyPosOrderKitDetail.setOrderKitDetailLotNumber(posOrderKitDetail.getOrderKitDetailLotNumber());
        copyPosOrderKitDetail.setOrderKitDetailBookCode(posOrderKitDetail.getOrderKitDetailBookCode());
        copyPosOrderKitDetail.setOrderKitDetailBranchNum(posOrderKitDetail.getOrderKitDetailBranchNum());
        copyPosOrderKitDetail.setOrderKitDetailBizday(posOrderKitDetail.getOrderKitDetailBizday());
        copyPosOrderKitDetail.setOrderKitDetailOrderState(posOrderKitDetail.getOrderKitDetailOrderState());
        copyPosOrderKitDetail.setOrderKitDetailStateCode(posOrderKitDetail.getOrderKitDetailStateCode());
        copyPosOrderKitDetail.setOrderSource(posOrderKitDetail.getOrderSource());
        copyPosOrderKitDetail.setSupplierNum(posOrderKitDetail.getSupplierNum());
        copyPosOrderKitDetail.setSystemBookCode(posOrderKitDetail.getSystemBookCode());
        copyPosOrderKitDetail.setBranchNum(posOrderKitDetail.getBranchNum());
        copyPosOrderKitDetail.setShiftTableNum(posOrderKitDetail.getShiftTableNum());
        copyPosOrderKitDetail.setOrderKitDetailShareDiscount(posOrderKitDetail.getOrderKitDetailShareDiscount());


        return copyPosOrderKitDetail;
    }


    private Payment copyPayment(Payment payment){
        Payment clonePayment = new Payment();

        clonePayment.setPaymentNo(payment.getPaymentNo());
        clonePayment.setOrderNo(payment.getOrderNo());
        clonePayment.setClientFid(payment.getClientFid());
        clonePayment.setSystemBookCode(payment.getSystemBookCode());
        clonePayment.setBranchNum(payment.getBranchNum());
        clonePayment.setShiftTableNum(payment.getShiftTableNum());
        clonePayment.setShiftTableBizday(payment.getShiftTableBizday());
        clonePayment.setPaymentTime(payment.getPaymentTime());
        clonePayment.setPaymentPayBy(payment.getPaymentPayBy());
        clonePayment.setPaymentRound(payment.getPaymentRound());
        clonePayment.setPaymentReceive(payment.getPaymentReceive());
        clonePayment.setPaymentMoney(payment.getPaymentMoney());
        clonePayment.setPaymentChange(payment.getPaymentChange());
        clonePayment.setPaymentPaid(payment.getPaymentPaid());
        clonePayment.setPaymentBalance(payment.getPaymentBalance());
        clonePayment.setPaymentBillNo(payment.getPaymentBillNo());
        clonePayment.setPaymentMemo(payment.getPaymentMemo());
        clonePayment.setPaymentAcctNo(payment.getPaymentAcctNo());
        clonePayment.setPaymentAuditor(payment.getPaymentAuditor());
        clonePayment.setPaymentMachine(payment.getPaymentMachine());
        clonePayment.setPaymentCustNum(payment.getPaymentCustNum());
        clonePayment.setPaymentCardBalance(payment.getPaymentCardBalance());
        clonePayment.setPaymentConsumeCount(payment.getPaymentConsumeCount());
        clonePayment.setPaymentFlag(payment.getPaymentFlag());
        clonePayment.setPaymentDate(payment.getPaymentDate());
        clonePayment.setPaymentLastestDate(payment.getPaymentLastestDate());
        clonePayment.setAccountBankNum(payment.getAccountBankNum());
        clonePayment.setPaymentPoint(payment.getPaymentPoint());
        clonePayment.setConsumeRevokeFlag(payment.getConsumeRevokeFlag());
        clonePayment.setWechatOpenId(payment.getWechatOpenId());
        clonePayment.setPaymentCardPrintNum(payment.getPaymentCardPrintNum());
        clonePayment.setPaymentOnlineUnDiscount(payment.getPaymentOnlineUnDiscount());
        clonePayment.setPaymentBuyerMoney(payment.getPaymentBuyerMoney());
        clonePayment.setPaymentReceiptMoney(payment.getPaymentReceiptMoney());
        clonePayment.setMerchantNum(payment.getMerchantNum());
        clonePayment.setStallNum(payment.getStallNum());
        clonePayment.setVipInfoJson(payment.getVipInfoJson());
        clonePayment.setUploadOk(payment.isUploadOk());
        clonePayment.setCardShowInfo(payment.getCardShowInfo());
        clonePayment.setIcCard(payment.isIcCard());
        clonePayment.setPaymentCardUserName(payment.getPaymentCardUserName());
        clonePayment.setPaymentCardRedius(payment.getPaymentCardRedius());
        clonePayment.setPaymentCardPhone(payment.getPaymentCardPhone());
        clonePayment.setQuitCardJson(payment.getQuitCardJson());


        return clonePayment;
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

            posOrderDetailold.setOrderDetailPolicyFid("");
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
        payment.setPaymentBillNo(oldpayment.getPaymentBillNo());
        payment.setPaymentMoney(Money);
        payment.setPaymentPaid(payment.getPaymentMoney());
        payment.setPaymentCustNum(oldpayment.getPaymentCustNum());
        payment.setPaymentChange(payment.getPaymentReceive() - payment.getPaymentMoney());
        payment.setPaymentCardPrintNum(oldpayment.getPaymentCardPrintNum());
        payment.setClientFid(oldpayment.getClientFid());
        if(LibConfig.C_PAYMENT_TYPE_SIGNBILL_NAME.equals(payBy)){
            payment.setPaymentBalance(Money);
        }
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
                    posOrderDetailMap.put(UUIDUtils.getUUID32(), posOrderDetail);
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
