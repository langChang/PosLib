package com.nhsoft.poslib.normal.impl;

import android.text.TextUtils;

import com.nhsoft.poslib.entity.AppUser;
import com.nhsoft.poslib.entity.ItemCategory;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.SystemRole;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.model.CouponsBean;
import com.nhsoft.poslib.model.CouponsXmlModel;
import com.nhsoft.poslib.normal.callback.CheckPermissionCallback;
import com.nhsoft.poslib.service.ItemCategoryService;
import com.nhsoft.poslib.service.PrivilegeResourceNewService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Iverson on 2019-11-28 14:13
 * 此类用于：
 */
public class CheckPermissionImpl implements CheckPermissionCallback {

    @Override
    public int checkPermission(AppUser appUser, String privilageName, String operatorName, List<SystemRole> roleList) {
        boolean privilage = PrivilegeResourceNewService.getInstance().getPrivilage(appUser,
                privilageName, operatorName, roleList);
        if (privilage) return 1;
        if (!LibConfig.saleParamsBean.isEnableUserAuthorization()) {
            return 3;
        }
        return 2;
    }


    /**
     * 检查商品是否在消费劵支持的范围内
     *
     * @param couponsBean
     * @param posOrderDetail
     * @return 0：支持所有的商品 1部分支持商品内  2：不在部分支持商品内
     */
    private int isInGoodsList(CouponsBean couponsBean, PosOrderDetail posOrderDetail) {
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
                        ItemCategory loadItemCategory = ItemCategoryService.findTopCode(posOrderDetail.getPosItem().getItem_category_code());
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
                                ItemCategory topCode = ItemCategoryService.findTopCode(posItem.getItem_category_code());
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
