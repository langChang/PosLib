package com.nhsoft.poslib.call.impl;

import android.text.TextUtils;

import com.nhsoft.poslib.entity.PolicyDiscount;
import com.nhsoft.poslib.entity.PolicyMoney;
import com.nhsoft.poslib.entity.PolicyPresent;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.model.BasePolicyBean;
import com.nhsoft.poslib.call.callback.PromotionOperationCallback;
import com.nhsoft.poslib.utils.ItemPriceCheck;
import com.nhsoft.poslib.utils.NumberUtil;
import com.nhsoft.poslib.utils.PosOrderCalcUtil;
import com.nhsoft.poslib.utils.PriceDiscountUtils;
import com.nhsoft.poslib.utils.PricePolicyMoneyUseUtils;
import com.nhsoft.poslib.utils.PricePolicyPresentUseUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Iverson on 2019-11-28 16:02
 * 此类用于：
 */
public class PromotionOperationImpl implements PromotionOperationCallback {


    @Override
    public List<BasePolicyBean> getAllUsePolicys(PosOrder posOrder){

        if(posOrder == null)return new ArrayList<>();

        LibConfig.allPolicyMoneyList = PolicyMoneyImpl.getNewestPolciyMoney();
        LibConfig.allPolicyPresentList = PolicyPresentImpl.getNewestPolciyPresent();
        List<BasePolicyBean> basePolicyBeans = new ArrayList<>();
        List<PolicyMoney> policyMonies = new ArrayList<>();
        for (PolicyMoney policyMoney : LibConfig.allPolicyMoneyList) {
            PolicyMoney policyMoneyUse = PricePolicyMoneyUseUtils.getPolicyMoneyUse(posOrder.getPosOrderDetails(), policyMoney);
            if (policyMoneyUse != null) {
                policyMonies.add(policyMoneyUse);
            }
        }

        List<PolicyPresent> policyPresents = new ArrayList<>();
        for (PolicyPresent policyPresent : LibConfig.allPolicyPresentList) {
            PolicyPresent policyPresentUse = PricePolicyPresentUseUtils.getPolicyPresentUse(posOrder.getPosOrderDetails(), policyPresent);
            if (policyPresentUse != null) {
                policyPresents.add(policyPresentUse);
            }
        }
        basePolicyBeans.addAll(policyMonies);
        basePolicyBeans.addAll(policyPresents);
        return basePolicyBeans;
    }

    @Override
    public void getPolicyDiscount(PosOrder mPosOrder) {
        Map<PolicyDiscount, List<PosOrderDetail>> policyDiscountListMap = new LinkedHashMap<>();

        if (LibConfig.allPolicyDiscountList != null && LibConfig.allPolicyDiscountList.size() > 0) {
            List<PosOrderDetail> posOrderDetails = mPosOrder.getPosOrderDetails();
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                List<PolicyDiscount> policyDiscounts = PriceDiscountUtils.getPolicyDiscountPrice(posOrderDetail, LibConfig.allPolicyDiscountList);
                if (policyDiscounts == null || policyDiscounts.isEmpty()) continue;

                for (PolicyDiscount policyDiscount : policyDiscounts){
                    List<PosOrderDetail> mapPosOrdelList = policyDiscountListMap.get(policyDiscount);
                    if (mapPosOrdelList == null) {
                        mapPosOrdelList = new ArrayList<>();
                    }
                    mapPosOrdelList.add(posOrderDetail);
                    policyDiscountListMap.put(policyDiscount, mapPosOrdelList);
                }
            }

            for (Map.Entry<PolicyDiscount, List<PosOrderDetail>> entry : policyDiscountListMap.entrySet()) {
                PolicyDiscount policyDiscount = entry.getKey();
                List<PosOrderDetail>  valuePosOrdedList = entry.getValue();
                List<PosOrderDetail> mapPosOrdelList = new ArrayList<>();

                for (PosOrderDetail posOrderDetail : valuePosOrdedList){
                    if(!posOrderDetail.getOrderDetailPolicyDiscountFlag() && TextUtils.isEmpty(posOrderDetail.getOrderDetailPolicyFid())){
                        mapPosOrdelList.add(posOrderDetail);
                    }
                }



                if ("超额减免".equals(policyDiscount.getPolicy_discount_type())) {
                    float totalPaymentMoney = 0;
                    for (PosOrderDetail posOrderDetail : mapPosOrdelList) {
                        totalPaymentMoney += posOrderDetail.getOrderDetailPaymentMoney();
                    }

                    if (totalPaymentMoney < policyDiscount.getPolicy_discount_bill_money())
                        continue;
                    float totalDiscountMoney = NumberUtil.getNewFloat(policyDiscount.getPolicy_discount_discount_money());
                    //自动累加
                    if (policyDiscount.getPolicy_discount_discount_money() != 0) {
                        int discountNum = (int) (totalPaymentMoney / policyDiscount.getPolicy_discount_bill_money());
                        if (discountNum > 0) {
                            totalDiscountMoney *= discountNum;
                        }
                    }
                    //最高打多少钱
                    if (totalDiscountMoney > policyDiscount.getPolicy_discount_total_discount() &&  policyDiscount.getPolicy_discount_total_discount() != 0) {
                        totalDiscountMoney = policyDiscount.getPolicy_discount_total_discount();
                    }

                    float globalTotalDiscountMoney = totalDiscountMoney;


                    for (int i = 0; i < mapPosOrdelList.size(); i++) {
                        PosOrderDetail posOrderDetail = mapPosOrdelList.get(i);
                        float orderDetailPaymentMoney = posOrderDetail.getOrderDetailPaymentMoney();
                        if (i == mapPosOrdelList.size() - 1) {
                            float realDiscount = orderDetailPaymentMoney - totalDiscountMoney;
                            if (realDiscount < 0) realDiscount = 0;
                            posOrderDetail.setOrderDetailPrice((realDiscount) / posOrderDetail.getOrderDetailAmount());
                            posOrderDetail.setOrderDetailPolicyFid(policyDiscount.getPolicy_discount_no());
                            posOrderDetail.setOrderDetailPolicyDiscountFlag(true);
                            PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail);
                            break;
                        }
                        float discountPaymentMoney = (posOrderDetail.getOrderDetailPaymentMoney() / totalPaymentMoney) * globalTotalDiscountMoney;
                        float realDiscount = orderDetailPaymentMoney - discountPaymentMoney;
                        if (realDiscount < 0) realDiscount = 0;
                        posOrderDetail.setOrderDetailPrice((realDiscount) / posOrderDetail.getOrderDetailAmount());
                        posOrderDetail.setOrderDetailPolicyFid(policyDiscount.getPolicy_discount_no());
                        posOrderDetail.setOrderDetailPolicyDiscountFlag(true);
                        PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail);
                        totalDiscountMoney -= (orderDetailPaymentMoney - posOrderDetail.getOrderDetailPaymentMoney());
                    }

                } else if ("超额折扣".equals(policyDiscount.getPolicy_discount_type())) {
                    float totalPaymentMoney = 0;
                    for (PosOrderDetail posOrderDetail : mapPosOrdelList) {
                        totalPaymentMoney += posOrderDetail.getOrderDetailPaymentMoney();
                    }
                    if (totalPaymentMoney < policyDiscount.getPolicy_discount_bill_money())
                        continue;

                    for (PosOrderDetail posOrderDetail : mapPosOrdelList) {
                        if (posOrderDetail.getOrderDetailMemo() != null && posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_VIP_TAG)) {
                            if (posOrderDetail.getOrderDetailPrice() / posOrderDetail.getOrderDetailStdPrice() >= policyDiscount.getPolicy_discount_discount()) {
                                posOrderDetail.setOrderDetailPrice(posOrderDetail.getOrderDetailStdPrice() * policyDiscount.getPolicy_discount_discount());
                                posOrderDetail.setOrderDetailPolicyFid(policyDiscount.getPolicy_discount_no());
                                posOrderDetail.setOrderDetailPolicyDiscountFlag(true);
                                PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail);
                            }
                        } else {
                            posOrderDetail.setOrderDetailPrice(posOrderDetail.getOrderDetailStdPrice() * policyDiscount.getPolicy_discount_discount());
                            posOrderDetail.setOrderDetailPolicyFid(policyDiscount.getPolicy_discount_no());
                            posOrderDetail.setOrderDetailPolicyDiscountFlag(true);
                            PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail);
                        }
                        if(posOrderDetail.getPosItem() != null){
                            float itemMinPrice = ItemPriceCheck.getItemMinPrice(posOrderDetail.getPosItem(), posOrderDetail.getOrderDetailPrice());
                            if(posOrderDetail.getPosItem() != null && itemMinPrice > posOrderDetail.getOrderDetailPrice()){
                                posOrderDetail.setOrderDetailPrice(itemMinPrice);
                                PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail);
                            }
                        }
                    }
                }
            }
            PosOrderCalcUtil.calcPosOrder(mPosOrder);

        }
    }
}
