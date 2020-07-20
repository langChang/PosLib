package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.service.LoginService;
import com.nhsoft.poslib.service.PosItemService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Iverson on 2019/4/9 10:08 AM
 * 此类用于：
 */
public class WeightOutBarUtil {

    public static boolean isWeightOutBarGoods(String barcode) {
        if (!TextUtils.isEmpty(barcode) && !TextUtils.isEmpty(LibConfig.saleParamsBean.getPrefixOfWeightItem()) && barcode.startsWith(LibConfig.saleParamsBean.getPrefixOfWeightItem()) && barcode.length() > 9)  return true;
        return false;
    }


    public static PosOrderDetail getWeightOutBarGoods(String barcode, LinkedList<PosOrderDetail> posOrderDetails){
        String itembarCode = "";
        try {
            switch (LibConfig.saleParamsBean.getChooseBarType()) {
                case 1:
                    //标识号 + PLU号 + 金额 + xuwei
                    itembarCode = barcode.substring(0, barcode.length() - Integer.parseInt(LibConfig.saleParamsBean.getLengthOfMoney())-1);
                    break;
                case 2:
                    //标识号 + PLU号 + 重量
                    itembarCode = barcode.substring(0, barcode.length() - Integer.parseInt(LibConfig.saleParamsBean.getLengthOfWeight())-1);
                    break;
                case 3:
                    //标识号 + PLU号 + 金额 + 重量
                    itembarCode = barcode.substring(0, barcode.length() - Integer.parseInt(LibConfig.saleParamsBean.getLengthOfMoney()) - Integer.parseInt(LibConfig.saleParamsBean.getLengthOfWeight())-1);
                    break;
            }
        }catch (Exception e){
            return null;
        }

        List<PosItem> posItems = PosItemService.getInstance().getPosItemByItemBarCode(LibConfig.activeShiftTable.getBranchNum(), itembarCode);
        if(posItems != null && posItems.size() > 0){
            PosItem posItem = posItems.get(0);
            float stdPrice = 1;
            if (LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived() && posItem.getBranch_regular_price() != 0) {
                stdPrice = posItem.getBranch_regular_price();
            } else {
                stdPrice = posItem.getItem_regular_price();
            }


            float goodsWeight = 0;
            float goodsPaymentMoney = 0;

            PosOrderDetail posOrderDetail = null;
            switch (LibConfig.saleParamsBean.getChooseBarType()) {
                case 1:
                    //标识号 + PLU号 + 重量
                    goodsPaymentMoney = Float.parseFloat(barcode.substring(barcode.length() - Integer.parseInt(LibConfig.saleParamsBean.getLengthOfMoney())-1,barcode.length() -1));
                    float bitMoney = (float) Math.pow(10, Double.parseDouble(LibConfig.saleParamsBean.getPrecisionOfMoney()));
                    float suttle = goodsPaymentMoney/bitMoney/stdPrice;
                    if(LoginService.getInstance().isNongMao()){
                        posOrderDetail = ConversionUtil.getNongMaoPosOrderDetail(null, posItem, null, suttle,posOrderDetails,"",false);
                    }else {
                        posOrderDetail = ConversionUtil.getPosOrderDetail(LibConfig.activeVipMember,null, posItem, null, suttle,posOrderDetails,"",false,false);
                    }
                    break;
                case 2:
                    //标识号 + PLU号 + 重量
                    goodsWeight = Float.parseFloat(barcode.substring(barcode.length() - Integer.parseInt(LibConfig.saleParamsBean.getLengthOfWeight())-1,barcode.length() -1));
                    float bitWeight = (float) Math.pow(10, Double.parseDouble(LibConfig.saleParamsBean.getPrecisionOfWeight()));
                    if(LoginService.getInstance().isNongMao()){
                        posOrderDetail = ConversionUtil.getNongMaoPosOrderDetail(null, posItem, null, goodsWeight/bitWeight,posOrderDetails,"",false);
                    }else {
                        posOrderDetail = ConversionUtil.getPosOrderDetail(LibConfig.activeVipMember,null, posItem, null, goodsWeight/bitWeight,posOrderDetails,"",false,false);
                    }
                    break;
                case 3:
                    goodsWeight = Float.parseFloat(barcode.substring(barcode.length() - Integer.parseInt(LibConfig.saleParamsBean.getLengthOfWeight())-1,barcode.length() -1));
                    float weightBit = (float) Math.pow(10, Double.parseDouble(LibConfig.saleParamsBean.getPrecisionOfWeight()));
                    float moneyBit = (float) Math.pow(10, Double.parseDouble(LibConfig.saleParamsBean.getPrecisionOfMoney()));

                    int weightLength = Integer.parseInt(LibConfig.saleParamsBean.getLengthOfWeight());
                    int montyLength = Integer.parseInt(LibConfig.saleParamsBean.getLengthOfMoney());

                    float detailMoney = Float.parseFloat(barcode.substring(barcode.length() - weightLength - montyLength -1,barcode.length() - weightLength -1));
                    float chazhi = stdPrice * goodsWeight / weightBit - detailMoney / moneyBit;
                    if(Math.abs(chazhi) > 0.1){
                        posOrderDetail = ConversionUtil.getPosOrderDetail(LibConfig.activeVipMember,null, posItem, null, goodsWeight/weightBit,posOrderDetails,"",false,false);
                        TagUtils.removeVipTag(posOrderDetail);
                        posOrderDetail.setOrderDetailPolicyFid(null);

                        posOrderDetail.setOrderDetailPolicyPromotionFlag(false);  //特价促销标记
                        posOrderDetail.setOrderDetailPolicyPromotionQuantityFlag(false); //超量特价标记
                        posOrderDetail.setOrderDetailPolicyPromotionMoneyFlag(false); //超额奖励标记
                        posOrderDetail.setOrderDetailPolicyPresentFlag(false);//赠送促销标记
                        posOrderDetail.setOrderDetailPolicyDiscountFlag(false); //超额折扣标记

                        posOrderDetail.setOrderDetailPrice(NumberUtil.getNewFloat(detailMoney*weightBit/moneyBit/goodsWeight));
                        TagUtils.addChangeTag(posOrderDetail);
                        if(chazhi < 0){
                            posOrderDetail.setOrderDetailStdPrice(posOrderDetail.getOrderDetailPrice());
                        }
                    }else {
                        posOrderDetail = ConversionUtil.getPosOrderDetail(LibConfig.activeVipMember,null, posItem, null, goodsWeight/weightBit,posOrderDetails,"",false,false);
                    }

                    PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail);
                    
                    break;
            }
            return posOrderDetail;
        }else {
            return null;
        }

    }


}
