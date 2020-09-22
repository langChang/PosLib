package com.nhsoft.poslib.call.impl;

import android.text.TextUtils;
import android.util.Log;

import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.entity.BookResource;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.model.ItemSequenceBean;
import com.nhsoft.poslib.model.SaleParamsBean;
import com.nhsoft.poslib.call.callback.GlobalDataCallback;
import com.nhsoft.poslib.utils.CouponsParseUtil;
import com.nhsoft.poslib.utils.PointRuleParseUtil;
import com.nhsoft.poslib.utils.XmlUtil;

import java.util.ArrayList;

/**
 * Created by Iverson on 2019-11-28 10:08
 * 此类用于：处理全局变量的类
 */
public class GlobalDataImpl implements GlobalDataCallback {

    @Override
    public boolean initHomeData(){

        initSaleBean();
        //初始化前台参数
//        SaleParamsBean saleParam;
////        initSaleBean();
//        BookResource bookPosSale = BookResourceImpl.getInstance().getBookPosSale(LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.S_LOCAL_SCALE_STYLE);
//        if (bookPosSale != null) {
//            saleParam = XmlUtil.getPosSaleParams(bookPosSale.getBookResourceParam());
//        } else {
//            saleParam = new SaleParamsBean();
//        }
//        LibConfig.saleParamsBean = saleParam;
        //前台结算参数
        BookResource bookPosSaleType = BookResourceImpl.getInstance().getBookPosSale(LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.S_PAY_SCALE_STYLE);
        if (bookPosSaleType != null) {
            LibConfig.allPosScaleTypeList = XmlUtil.getPosScaleStyle(bookPosSaleType.getBookResourceParam());
        }
//        initShowGoods();
        LibConfig.BOOK_SCOPE_ID = SystemBookImpl.getBean(LibConfig.activeLoginBean.getSystem_book_code()).getBook_scope_id();
        //当前收银员
//        LibConfig.activeAppUser = AppUserImpl.getInstance().login
//                (LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.activeLoginBean.getBranch_num(), LibConfig.activeAppUser.getApp_user_code());

        //系统角色列表
        LibConfig.systemRoleList = PrivilegeResourceNewImpl.getInstance().getSystemRoleList();
        //当前角色
        LibConfig.systemRole = PrivilegeResourceNewImpl.getInstance().getRole(LibConfig.activeAppUser, LibConfig.systemRoleList);
        //会员卡参数
        LibConfig.sVipCardParams = VipSendCardImpl.getInstance().getVipCardTypeBean(LibConfig.activeLoginBean.getSystem_book_code());//获取消费卡类型
        //积分政策
        LibConfig.allPointPolicyList = RetailPosManager.getInstance().getAllPointPolicy(LibConfig.activeLoginBean.getSystem_book_code());
        //营销活动
        LibConfig.allMarketAction = MarketActionImpl.getAllMarketAction(LibConfig.activeLoginBean.getBranch_num());
        //积分规则
        BookResource pointRuleXml = BookResourceImpl.getInstance().getBookPosSale(LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.S_LOCAL_POINT_RULE);
        if (pointRuleXml != null) {
            LibConfig.allPointRuleList = PointRuleParseUtil.getPointRuleList(pointRuleXml);
        }
        //IC的卡信息初始化
        LibConfig.activeIcCardMessage = IcCardMessageImpl.getInstance().getBean();

        //优惠劵规则
        BookResource couponsXml = BookResourceImpl.getInstance().getBookPosSale(LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.S_LOCAL_COUPONS_STYLE);
        if (couponsXml != null) {
            LibConfig.sCouponsXmlModels = CouponsParseUtil.getCouponsList(couponsXml);
            Log.e("sCouponsXmlModels", LibConfig.sCouponsXmlModels.size() + "个");
        } else {
            Log.e("sCouponsXmlModels", "0");
        }

        if(LibConfig.sCouponsXmlModels == null){
            LibConfig.sCouponsXmlModels = new ArrayList<>();
        }


        //促销
            LibConfig.allPolicyPromotionList = PolicyPromotionImpl.getNewestPolciyPromotion();
            LibConfig.allPolicyDiscountList = PolicyDiscountImpl.getNewestPolciyDiscount();
            LibConfig.allPolicyQuantityList = PolicyQuantityImpl.getNewestPolciyQuantity();
            LibConfig.allPolicyMoneyList = PolicyMoneyImpl.getNewestPolciyMoney();
            LibConfig.allPolicyPresentList = PolicyPresentImpl.getNewestPolciyPresent();
            LibConfig.allPointPolicyList = RetailPosManager.getInstance().getAllPointPolicy(LibConfig.activeLoginBean.getSystem_book_code());

        LibConfig.activeBranch = BranchImpl.getInstance().getBranchByNum(LibConfig.activeLoginBean.getSystem_book_code(),LibConfig.activeLoginBean.getBranch_num());
        BranchGroupImpl.initMyBranchGroup(LibConfig.activeLoginBean.getBranch_num());

        //重置商品排列顺序
        String itemSequenceString = BranchResourceImpl.getInstance().getItemSequenceString(LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.activeLoginBean.getBranch_num());
        if (!TextUtils.isEmpty(itemSequenceString)) {
            ArrayList<ItemSequenceBean> itemSequence = XmlUtil.getItemSequence(itemSequenceString);
            if (itemSequence != null && itemSequence.size() > 0) {
                PosItemImpl.resetPosItemSequence(itemSequence);
            }
        }
        ItemCategoryImpl.getInstance().setHierarchyCategory();
        LibConfig.activityShowGoods = RetailPosManager.getInstance().getAllShowPosItem();


        return LibConfig.saleParamsBean == null ? false : true;
    }

//    /**
//     * 展示显示商品
//     */
//    public void initShowGoods() {
//        //重置商品排列顺序
//        String itemSequenceString = BranchResourceImpl.getInstance().getItemSequenceString(LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.activeLoginBean.getBranch_num());
//        if (!TextUtils.isEmpty(itemSequenceString)) {
//            ArrayList<ItemSequenceBean> itemSequence = XmlUtil.getItemSequence(itemSequenceString);
//            if (itemSequence != null && itemSequence.size() > 0) {
//                PosItemImpl.resetPosItemSequence(itemSequence);
//            }
//        }
//        ItemCategoryImpl.getInstance().setHierarchyCategory();
//        LibConfig.activityShowGoods = RetailPosManager.getInstance().getAllShowPosItem();
//    }

    public boolean initSaleBean(){
        SaleParamsBean saleParam;
        BookResource bookPosSale = BookResourceImpl.getInstance().getBookPosSale(LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.S_LOCAL_SCALE_STYLE);
        if (bookPosSale != null) {
            saleParam = XmlUtil.getPosSaleParams(bookPosSale.getBookResourceParam());
        } else {
            saleParam = new SaleParamsBean();
        }

        LibConfig.saleParamsBean = saleParam;

        return saleParam != null;
    }
}
