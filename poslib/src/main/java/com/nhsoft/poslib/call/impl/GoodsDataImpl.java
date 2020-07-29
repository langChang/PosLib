package com.nhsoft.poslib.call.impl;

import android.database.Cursor;
import android.text.TextUtils;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.ItemCategory;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.model.CouponsBean;
import com.nhsoft.poslib.model.CouponsXmlModel;
import com.nhsoft.poslib.model.GoodsGradeBean;
import com.nhsoft.poslib.call.callback.GoodsDataCallback;
import com.nhsoft.poslib.service.greendao.DaoSession;
import com.nhsoft.poslib.utils.EvtLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Iverson on 2019-11-28 09:36
 * 此类用于：
 */
public class GoodsDataImpl implements GoodsDataCallback {


    @Override
    public List<GoodsGradeBean> getAllShowGoods() {
        try {
            DaoSession session = DaoManager.getInstance().getDaoSession();
            List<ItemCategory> itemCategoryList = new ArrayList<>();
            ItemCategory itemCategory;
            String category_code = "";
            String category_name = "";
            String category_parent_code = "";
//                    String strSql = "select max(item_last_edit_time) as ITEM_LAST_EDIT_TIME from pos_item ";
            String strSql = "select *  from item_category  order by parent_category_code asc";
            Cursor c = session.getDatabase().rawQuery(strSql, null);
            while (c.moveToNext()) {
                itemCategory = new ItemCategory();
                itemCategory.setCategory_code(c.getString(c.getColumnIndex("CATEGORY_CODE")));
                itemCategory.setCategory_name(c.getString(c.getColumnIndex("CATEGORY_NAME")));
                itemCategory.setParent_category_code(c.getString(c.getColumnIndex("PARENT_CATEGORY_CODE")));
                EvtLog.e("itemCategoryId", category_code + "----" + category_name + "----" + category_parent_code);
                itemCategoryList.add(itemCategory);
            }
            c.close();
            if (itemCategoryList.size() > 0) {
                List<GoodsGradeBean> goodsGradeBeanList = new ArrayList<>();
                GoodsGradeBean goodsGradeBean;
                for (ItemCategory category : itemCategoryList) {
                    if (TextUtils.isEmpty(category.getParent_category_code())) {
                        goodsGradeBean = new GoodsGradeBean();
                        goodsGradeBean.setItemCategory(category);
                        goodsGradeBean.setGrade(1);
                        goodsGradeBean.setCategory_name(category.getCategory_name());
                        goodsGradeBean.setCategory_code(category.getCategory_code());
                        goodsGradeBeanList.add(goodsGradeBean);
                    } else {
                        for (GoodsGradeBean saveGoodsBean : goodsGradeBeanList) {
                            if (category.getParent_category_code().equals(saveGoodsBean.getCategory_code())) {
                                saveGoodsBean.getItemCategories().add(category);
                            }
                        }
                    }
                }

                List<GoodsGradeBean> newGradeList = new ArrayList<>();
                List<PosItem> posItemByCode = null;
                List<ItemCategory> newItemCategorys = null;

                for (GoodsGradeBean gradeBean : goodsGradeBeanList) {


                    newItemCategorys = new ArrayList<>();
                    if (gradeBean.getItemCategories() == null || gradeBean.getItemCategories().size() == 0) {
                        posItemByCode = PosItemImpl.getAllShoWPosItemByCode(gradeBean.getCategory_code(), 0);//只有一级类
                        if (posItemByCode != null && posItemByCode.size() > 0) {
                            newGradeList.add(gradeBean);
                            if (null != gradeBean.getItemCategory()) {
                                gradeBean.getItemCategory().setPosItemList(posItemByCode);
                            }
                        }

                    } else {
                        for (ItemCategory sonItemCategory : gradeBean.getItemCategories()) {
                            posItemByCode = PosItemImpl.getAllShoWPosItemAndSonPosItemByCode(sonItemCategory.getCategory_code(), 0);//一级类以下
                            if (posItemByCode != null && posItemByCode.size() > 0) {
                                sonItemCategory.setPosItemList(posItemByCode);
                                newItemCategorys.add(sonItemCategory);
                            }
                        }
                        
                        // TODO: 2020/2/24 修改8几类支持
                        posItemByCode = PosItemImpl.getAllShoWPosItemByCode(gradeBean.getCategory_code(), 0);
                        if (newItemCategorys.size() > 0) {
                            newGradeList.add(gradeBean);
                            gradeBean.setItemCategories(newItemCategorys);
                            if (posItemByCode != null && posItemByCode.size() > 0) {
                                if (null != gradeBean.getItemCategory()) {
                                    gradeBean.getItemCategory().setPosItemList(posItemByCode);
                                }
                            }
                        }else {
                            if (posItemByCode != null && posItemByCode.size() > 0) {
                                newGradeList.add(gradeBean);
                                if (null != gradeBean.getItemCategory()) {
                                    gradeBean.getItemCategory().setPosItemList(posItemByCode);
                                }
                            }
                        }
                        // TODO: 2020/2/24 修改8几类支持前 
//                        if (newItemCategorys.size() > 0) {
//                            newGradeList.add(gradeBean);
//                            gradeBean.setItemCategories(newItemCategorys);
//                        }

                    }
                }
                return newGradeList;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<GoodsGradeBean> getAllShowGoods(int stallNum, int merchantNum) {
        LibConfig.activityDisplayShowGoods.clear();
        try {
            DaoSession session = DaoManager.getInstance().getDaoSession();
            List<ItemCategory> itemCategoryList = new ArrayList<>();
            ItemCategory itemCategory;
            String category_code = "";
            String category_name = "";
            String category_parent_code = "";
//                    String strSql = "select max(item_last_edit_time) as ITEM_LAST_EDIT_TIME from pos_item ";
            String strSql = "select *  from category_find where stall_num = " + stallNum + " and merchant_num = " + merchantNum + " order by STALL_ITEM_CATEGORY_SEQUENCE asc";
            Cursor c = session.getDatabase().rawQuery(strSql, null);
            while (c.moveToNext()) {
                itemCategory = new ItemCategory();
                itemCategory.setCategory_code(c.getString(c.getColumnIndex("STALL_ITEM_CATEGORY_CODE")));
                itemCategory.setCategory_name(c.getString(c.getColumnIndex("STALL_ITEM_CATEGORY_NAME")));
                itemCategory.setParent_category_code(null);
                EvtLog.e("itemCategoryId", itemCategory.getCategory_code() + "----" + itemCategory.getCategory_name() + "----" + itemCategory.getParent_category_code()+"___"+itemCategory.getParent_category_name());
                itemCategoryList.add(itemCategory);
            }
            c.close();
            if (itemCategoryList.size() > 0) {
                List<GoodsGradeBean> goodsGradeBeanList = new ArrayList<>();
                GoodsGradeBean goodsGradeBean;
                for (ItemCategory category : itemCategoryList) {
                    if (TextUtils.isEmpty(category.getParent_category_code())) {
                        goodsGradeBean = new GoodsGradeBean();
                        goodsGradeBean.setItemCategory(category);
                        goodsGradeBean.setGrade(1);
                        goodsGradeBean.setCategory_name(category.getCategory_name());
                        goodsGradeBean.setCategory_code(category.getCategory_code());
                        goodsGradeBeanList.add(goodsGradeBean);
                    } else {
                        for (GoodsGradeBean saveGoodsBean : goodsGradeBeanList) {
                            if (category.getParent_category_code().equals(saveGoodsBean.getCategory_code())) {
                                saveGoodsBean.getItemCategories().add(category);
                            }
                        }
                    }
                }

                List<GoodsGradeBean> newGradeList = new ArrayList<>();
                List<PosItem> posItemByCode = null;
                List<ItemCategory> newItemCategorys = null;

                for (GoodsGradeBean gradeBean : goodsGradeBeanList) {
                    EvtLog.e("itemCategoryId_gradeBean", gradeBean.getCategory_code() + "----" + gradeBean.getCategory_name());


                    newItemCategorys = new ArrayList<>();
                    if (gradeBean.getItemCategories() == null || gradeBean.getItemCategories().size() == 0) {
                        posItemByCode = PosItemImpl.getAllShoWPosItemByCode(gradeBean.getCategory_code(), 0);
                        if (posItemByCode != null && posItemByCode.size() > 0) {
                            LibConfig.activityDisplayShowGoods.addAll(posItemByCode);
                            newGradeList.add(gradeBean);
                            if (null != gradeBean.getItemCategory()) {
                                gradeBean.getItemCategory().setPosItemList(posItemByCode);
                            }
                        }

                    } else {
                        for (ItemCategory sonItemCategory : gradeBean.getItemCategories()) {
                            posItemByCode = PosItemImpl.getAllShoWPosItemByCode(sonItemCategory.getCategory_code(), 0);
                            if (posItemByCode != null && posItemByCode.size() > 0) {
                                LibConfig.activityDisplayShowGoods.addAll(posItemByCode);
                                sonItemCategory.setPosItemList(posItemByCode);
                                newItemCategorys.add(sonItemCategory);
                            }
                        }

                        if (newItemCategorys.size() > 0) {
                            newGradeList.add(gradeBean);
                            gradeBean.setItemCategories(newItemCategorys);
                        }
                    }
                }
                return newGradeList;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public int isInGoodsList(CouponsBean couponsBean, PosOrderDetail posOrderDetail) {
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