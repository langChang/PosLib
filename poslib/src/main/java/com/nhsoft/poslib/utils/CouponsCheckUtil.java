package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.call.impl.ItemCategoryImpl;
import com.nhsoft.poslib.entity.ItemCategory;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.model.BranchXmlModel;
import com.nhsoft.poslib.model.CheckCouponsStatus;
import com.nhsoft.poslib.model.CouponsBean;
import com.nhsoft.poslib.model.CouponsXmlModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Iverson on 2019-12-27 17:30
 * 此类用于：
 */
public class CouponsCheckUtil {

    //判断消费是否可用
    public static CheckCouponsStatus isCouponsAlable(PosOrder mPosOrder, CouponsBean couponsBean, boolean isNet) {

        CheckCouponsStatus checkCouponsStatus = new CheckCouponsStatus();


        List<PosOrderDetail> posOrderDetails = mPosOrder.getPosOrderDetails();
        float insertMoney = 0;
        boolean isUseable = false;

        if (couponsBean.getTicket_send_state_code() == 3) {
            if (isNet) {
//                mContext.toastShort(mContext.getString(R.string.no_coupons_alreay_user));

                checkCouponsStatus.setMsg("该消费已使用，请尝试其他消费券!");
            }
            checkCouponsStatus.setUse(false);
            return checkCouponsStatus;
        }


        List<PosOrderDetail> goodsCouponsContainList = new ArrayList<>();

        if(!"购物抵用券".equals(couponsBean.getTicket_category()) && !"消费折扣券".equals(couponsBean.getTicket_category())&& !"商品券".equals(couponsBean.getTicket_category())) {
            if(isNet){
                checkCouponsStatus.setMsg("此劵不是购物抵用劵或者是消费折扣劵！");
            }
            return checkCouponsStatus;
        }
        for (PosOrderDetail posOrderDetail : posOrderDetails) {
            if (LibConfig.activeVipMember != null && LibConfig.activeVipMember.isDiscount_without_coupon() && posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_VIP_TAG))
                continue;
//            int inGoodsList = RetailPosManager.getInstance().isContainGoods(couponsBean, posOrderDetail);
            if (RetailPosManager.getInstance().isContainGoods(couponsBean, posOrderDetail)) {
                isUseable = true;


                if ("消费折扣券".equals(couponsBean.getTicket_category())) {
                    if (!TextUtils.isEmpty(posOrderDetail.getOrderDetailMemo()) && posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_CHANGE_TAG)) {
                        continue;
                    }
                    float couponsPrice = posOrderDetail.getOrderDetailStdPrice() * couponsBean.getTicket_send_discount();
                    if (couponsPrice < posOrderDetail.getOrderDetailPrice()) {
                        float itemMinPrice = ItemPriceCheck.getItemMinPrice(posOrderDetail.getPosItem(), couponsPrice);
                        if (couponsPrice < itemMinPrice) {
                            insertMoney += (posOrderDetail.getOrderDetailPrice() - itemMinPrice) * posOrderDetail.getOrderDetailAmount();
                        } else {
                            insertMoney += (posOrderDetail.getOrderDetailPrice() - couponsPrice) * posOrderDetail.getOrderDetailAmount();
                        }
                    }

                } else if ("购物抵用券".equals(couponsBean.getTicket_category())) {
                    insertMoney = insertMoney + (posOrderDetail.getOrderDetailPaymentMoney() - posOrderDetail.getOrderDetailDiscount());
                }else if ("商品券".equals(couponsBean.getTicket_category())) {
                    if (couponsBean.isExcept_promotion_items()) {
                        if (TextUtils.isEmpty(posOrderDetail.getOrderDetailPolicyFid())){
                            goodsCouponsContainList.add(posOrderDetail);
                        }
                    }else {
                        goodsCouponsContainList.add(posOrderDetail);
                    }

                }

            }

//            } else if (inGoodsList == 2) {
////                return false;
//            }

//
//                insertMoney = insertMoney + PriceUtil.getItemRegularPrice(posOrderDetail.getPosItem(),posOrderDetail.getPosItemGrade()) * posOrderDetail.getOrderDetailAmount();
//            } else if (inGoodsList == 2) {
////                return false;
//            }
        }

        if("商品券".equals(couponsBean.getTicket_category())){
            insertMoney = 0;
            float coupons_discount_amount = couponsBean.getCoupons_discount_amount();
            if(coupons_discount_amount != 0){
                if(!goodsCouponsContainList.isEmpty()){
                    for (PosOrderDetail posOrderDetail : goodsCouponsContainList){
                        if(coupons_discount_amount < 0.0001){
                            break;
                        }
                        if(posOrderDetail.getOrderDetailAmount() >= coupons_discount_amount){
                            insertMoney+= posOrderDetail.getOrderDetailPrice()*coupons_discount_amount;
                            coupons_discount_amount = 0;
                            break;
                        }else {
                            insertMoney+= posOrderDetail.getOrderDetailPaymentMoney();
                            coupons_discount_amount = coupons_discount_amount - posOrderDetail.getOrderDetailAmount();
                        }
                    }

                    if(insertMoney < 0.0001){
                        isUseable = false;
                        checkCouponsStatus.setUse(false);
                        if (isNet) {
                            checkCouponsStatus.setMsg("当前没有商品可抵扣！");
                        }
                        return checkCouponsStatus;
                    }
                }else {
                    isUseable = false;
                    checkCouponsStatus.setUse(false);
                    if (isNet) {
                        checkCouponsStatus.setMsg("当前没有商品可抵扣！");
                    }
                    return checkCouponsStatus;
                }
            }
            if(insertMoney != 0){
                couponsBean.setResidueMoney(NumberUtil.getNewFloat(insertMoney));
                couponsBean.setTicket_send_detail_value(NumberUtil.getNewFloat(insertMoney));
            }
        }

        if(!isUseable && couponsBean.getTicket_limit_money() != 0){
            if (isNet) {
                checkCouponsStatus.setMsg("当前劵支持商品消费金额低于消费劵最低使用金额!");
//                mContext.toastShort(mContext.getString(R.string.no_coupons_limit_money_by_goods));
            }
            return checkCouponsStatus;
        }
        if("消费折扣券".equals(couponsBean.getTicket_category())) {
//            float maxdiscountMoney = insertMoney * (1 - couponsBean.getTicket_send_discount());
//            if(maxdiscountMoney > couponsBean.getTicket_max_use_money() && couponsBean.getTicket_max_use_money() >0 ){
//                maxdiscountMoney = couponsBean.getTicket_max_use_money();
//            }
//            maxdiscountMoney = NumberUtil.getNewFloat(maxdiscountMoney);
//            couponsBean.setResidueMoney(maxdiscountMoney);
//            couponsBean.setTicket_send_detail_value(maxdiscountMoney);

            if(insertMoney == 0)return checkCouponsStatus;
            if (insertMoney > couponsBean.getTicket_max_use_money() && couponsBean.getTicket_max_use_money() > 0) {
                insertMoney = couponsBean.getTicket_max_use_money();
            }
            insertMoney = NumberUtil.getNewFloat(insertMoney);
            couponsBean.setResidueMoney(insertMoney);
            couponsBean.setTicket_send_detail_value(insertMoney);


        }else if("购物抵用券".equals(couponsBean.getTicket_category())) {
            if ( couponsBean.getTicket_limit_money() > 0 && couponsBean.getTicket_limit_money() > insertMoney) {
                if (isNet) {
                    checkCouponsStatus.setMsg("当前劵支持商品消费金额低于消费劵最低使用金额!");
//                    mContext.toastShort(mContext.getString(R.string.no_coupons_limit_money_by_goods));
                }
                return checkCouponsStatus;
            }

            if (couponsBean.isTicket_limit_amount_loop()) {
                int bit = 1;
                if (couponsBean.getTicket_limit_money() != 0) {
                    bit = (int) (insertMoney / couponsBean.getTicket_limit_money());
                }
                couponsBean.setTicket_limit_amount(couponsBean.getTicket_limit_amount() * bit);
            }
        }


        Date zero = TimeUtil.getTodayZeroDate();
        boolean actionEffectiveDate = TimeUtil.isActionEffectiveDate(zero, couponsBean.getTicket_send_detail_valid_start(), couponsBean.getTicket_send_detail_valid_date());
        if (!actionEffectiveDate) {
            if (isNet) {

                checkCouponsStatus.setMsg("当前不在该劵有效使用时间内，请尝试其他消费券!");
//                mContext.toastShort(mContext.getString(R.string.no_coupons_date_out));
            }
            return checkCouponsStatus;
        }


        try {
            String xml2json = XmlParser.xml2json(couponsBean.getTicket_send_detail_branch());
            JSONObject jsonObjectRoot = new JSONObject(xml2json);
            JSONObject brachListObject = new JSONObject(jsonObjectRoot.getString("AppliedBranchArray"));
            if (CouponsParseUtil.isHaveJsonKey("AppliedBranch", brachListObject, 4)) {
                String appliedBranch = brachListObject.getString("AppliedBranch");
                List<BranchXmlModel> branchXmlModelmore = new Gson().fromJson(appliedBranch, new TypeToken<List<BranchXmlModel>>() {
                }.getType());
                couponsBean.setBranchXmlModels(branchXmlModelmore);
            } else if (CouponsParseUtil.isHaveJsonKey("AppliedBranch", brachListObject, 3)) {
                BranchXmlModel branchXmlModel = new Gson().fromJson(brachListObject.getString("AppliedBranch"), BranchXmlModel.class);
                List<BranchXmlModel> branchXmlModels = new ArrayList<>();
                branchXmlModels.add(branchXmlModel);
                couponsBean.setBranchXmlModels(branchXmlModels);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (couponsBean.getBranchXmlModels() == null || couponsBean.getBranchXmlModels().size() == 0) {
            if (isNet) {
                checkCouponsStatus.setMsg("消费券暂不能使用，请尝试其他消费券!");
//                mContext.toastShort(mContext.getString(R.string.no_coupons_branchs_null));
            }
            return checkCouponsStatus;
        } else {
            boolean isContain = false;
            for (BranchXmlModel branchXmlModel : couponsBean.getBranchXmlModels()) {
                if (branchXmlModel.getAppliedBranchNum() == 0) {
                    isContain = true;
                    break;
                } else if (branchXmlModel.getAppliedBranchNum() == LibConfig.activeShiftTable.getBranchNum()) {
                    isContain = true;
                    break;
                }
            }
            if (isNet && !isContain) {

                checkCouponsStatus.setMsg("该消费券不支持此门店使用，请尝试其他消费券!");
//                mContext.toastShort(mContext.getString(R.string.no_coupons_other_branch));
            }

            if (!isContain){
                checkCouponsStatus.setUse(isContain);
                return checkCouponsStatus;
            }
        }
        checkCouponsStatus.setUse(true);
        return checkCouponsStatus;
    }


    /**
     * 检查商品是否在消费劵支持的范围内
     *
     * @param couponsBean
     * @param posOrderDetail
     * @return 0：支持所有的商品 1部分支持商品内  2：不在部分支持商品内
     */
    public static int isInGoodsList(CouponsBean couponsBean, PosOrderDetail posOrderDetail) {
        if (LibConfig.sCouponsXmlModels != null) {
            if (!posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_SALE_NAME))
                return 2;
            boolean isHave = false;
            for (CouponsXmlModel xmlCouponsBean : LibConfig.sCouponsXmlModels) {
                if (couponsBean.getTicket_send_detail_type().equals(xmlCouponsBean.getCouponsName())) {

                    if(xmlCouponsBean.isMoneyExceptPromotionItems() == 1){
                        if(!TextUtils.isEmpty(posOrderDetail.getOrderDetailPolicyFid() )){
                            return 2;
                        }
                    }

                    if (!TextUtils.isEmpty(xmlCouponsBean.getCouponsSupportPayStyle())) {
                        String[] split = xmlCouponsBean.getCouponsSupportPayStyle().split(",");
                        couponsBean.setSupportPaystyleList(new ArrayList<>(Arrays.asList(split)));
                    }



                    List<CouponsXmlModel.CatetoryData> catetoryDataList = xmlCouponsBean.getmCatetoryDataList();
                    if (catetoryDataList != null && catetoryDataList.size() > 0) {
                        ItemCategory loadItemCategory = ItemCategoryImpl.findTopCode(posOrderDetail.getPosItem().getItem_category_code());
                        if (loadItemCategory != null) {
                            for (CouponsXmlModel.CatetoryData catetoryData : catetoryDataList) {
                                if (catetoryData.getCatetoryName().equals(loadItemCategory.getCategory_name())) {
                                    if (catetoryData.getGoodsList() == null || catetoryData.getGoodsList().size() == 0) {
                                        return 1;
                                    } else {
                                        int isInGoodsList = 0;
                                        for (CouponsXmlModel.GoodsData goodsData : catetoryData.getGoodsList()) {
                                            if (goodsData.getGoodsItemNum() == posOrderDetail.getItemNum()) {
                                                isInGoodsList = 1;
                                                break;
                                            }
                                        }
                                        return isInGoodsList;
                                    }
                                }
                            }
                        }
                        return 0;
                    } else {
                        if(xmlCouponsBean.isAll()){
                            if(xmlCouponsBean.getGoodsCodeList() != null && xmlCouponsBean.getGoodsCodeList().contains(""+posOrderDetail.getItemNum())){
                                return 2;
                            }
                        }else if(xmlCouponsBean.getCategoryCodeList() != null){
                            String[] split = xmlCouponsBean.getCategoryCodeList().split(",");
                            List<String> categoryList = Arrays.asList(split);
                            if(posOrderDetail.getPosItem() != null){
                                PosItem posItem = posOrderDetail.getPosItem();
                                ItemCategory topCode = ItemCategoryImpl.findTopCode(posItem.getItem_category_code());
                                if(categoryList.size() > 0 &&  !categoryList.contains(topCode.getCategory_code())){
                                    return 2;
                                }else {
                                    if(xmlCouponsBean.getGoodsCodeList() != null && xmlCouponsBean.getGoodsCodeList().contains(""+posOrderDetail.getItemNum())){
                                        return 2;
                                    }
                                }

                            }
                        }else if(xmlCouponsBean.getGoodsCodeList() != null && !xmlCouponsBean.getGoodsCodeList().contains(""+posOrderDetail.getItemNum())){
                            return 2;
                        }

                        return 1;
                    }
                }
            }
            if (!isHave) return 2;
        } else {
            return 0;
        }

        return 0;
    }



}
