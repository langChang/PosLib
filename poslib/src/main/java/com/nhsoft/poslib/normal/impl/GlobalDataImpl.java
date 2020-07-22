package com.nhsoft.poslib.normal.impl;

import android.text.TextUtils;
import android.util.Log;

import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.entity.BookResource;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.model.ItemSequenceBean;
import com.nhsoft.poslib.model.SaleParamsBean;
import com.nhsoft.poslib.normal.callback.GlobalDataCallback;
import com.nhsoft.poslib.service.AppUserService;
import com.nhsoft.poslib.service.BookResourceService;
import com.nhsoft.poslib.service.BranchGroupService;
import com.nhsoft.poslib.service.BranchResourceService;
import com.nhsoft.poslib.service.BranchService;
import com.nhsoft.poslib.service.IcCardMessageService;
import com.nhsoft.poslib.service.ItemCategoryService;
import com.nhsoft.poslib.service.MarketActionService;
import com.nhsoft.poslib.service.PointPolicyService;
import com.nhsoft.poslib.service.PolicyDiscountService;
import com.nhsoft.poslib.service.PolicyMoneyService;
import com.nhsoft.poslib.service.PolicyPresentService;
import com.nhsoft.poslib.service.PolicyPromotionService;
import com.nhsoft.poslib.service.PolicyQuantityService;
import com.nhsoft.poslib.service.PosItemService;
import com.nhsoft.poslib.service.PrivilegeResourceNewService;
import com.nhsoft.poslib.service.SystemBookService;
import com.nhsoft.poslib.service.VipSendCardService;
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
    public boolean initSaleParams(){
        //初始化前台参数
        SaleParamsBean saleParam;
        BookResource bookPosSale = BookResourceService.getInstance().getBookPosSale(LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.S_LOCAL_SCALE_STYLE);
        if (bookPosSale != null) {
            saleParam = XmlUtil.getPosSaleParams(bookPosSale.getBookResourceParam());
        } else {
            saleParam = new SaleParamsBean();
        }
        LibConfig.saleParamsBean = saleParam;
        //前台结算参数
        BookResource bookPosSaleType = BookResourceService.getInstance().getBookPosSale(LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.S_PAY_SCALE_STYLE);
        if (bookPosSaleType != null) {
            LibConfig.allPosScaleTypeList = XmlUtil.getPosScaleStyle(bookPosSaleType.getBookResourceParam());
        }
        LibConfig.BOOK_SCOPE_ID = SystemBookService.getBean(LibConfig.activeLoginBean.getSystem_book_code()).getBook_scope_id();
        //当前收银员
        LibConfig.activeAppUser = AppUserService.getInstance().login
                (LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.activeLoginBean.getBranch_num(), LibConfig.activeAppUser.getApp_user_code());

        //系统角色列表
        LibConfig.systemRoleList = PrivilegeResourceNewService.getInstance().getSystemRoleList();
        //当前角色
        LibConfig.systemRole = PrivilegeResourceNewService.getInstance().getRole(LibConfig.activeAppUser, LibConfig.systemRoleList);
        //会员卡参数
        LibConfig.sVipCardParams = VipSendCardService.getInstance().getVipCardTypeBean(LibConfig.activeLoginBean.getSystem_book_code());//获取消费卡类型
        //积分政策
        LibConfig.allPointPolicyList = PointPolicyService.loadAllPointPolicy(LibConfig.activeLoginBean.getSystem_book_code());
        //营销活动
        LibConfig.allMarketAction = MarketActionService.getAllMarketAction(LibConfig.activeLoginBean.getBranch_num());
        //积分规则
        BookResource pointRuleXml = BookResourceService.getInstance().getBookPosSale(LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.S_LOCAL_POINT_RULE);
        if (pointRuleXml != null) {
            LibConfig.allPointRuleList = PointRuleParseUtil.getPointRuleList(pointRuleXml);
        }
        //IC的卡信息初始化
        LibConfig.activeIcCardMessage = IcCardMessageService.getInstance().getBean();

        //优惠劵规则
        BookResource couponsXml = BookResourceService.getInstance().getBookPosSale(LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.S_LOCAL_COUPONS_STYLE);
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
            LibConfig.allPolicyPromotionList = PolicyPromotionService.getNewestPolciyPromotion();
            LibConfig.allPolicyDiscountList = PolicyDiscountService.getNewestPolciyDiscount();
            LibConfig.allPolicyQuantityList = PolicyQuantityService.getNewestPolciyQuantity();
            LibConfig.allPolicyMoneyList = PolicyMoneyService.getNewestPolciyMoney();
            LibConfig.allPolicyPresentList = PolicyPresentService.getNewestPolciyPresent();
            LibConfig.allPointPolicyList = PointPolicyService.loadAllPointPolicy(LibConfig.activeLoginBean.getSystem_book_code());

        LibConfig.activeBranch = BranchService.getInstance().getBranch(LibConfig.activeLoginBean.getSystem_book_code(),LibConfig.activeLoginBean.getBranch_num());
        BranchGroupService.initMyBranchGroup(LibConfig.activeLoginBean.getBranch_num());
        return LibConfig.saleParamsBean == null ? false : true;
    }

    /**
     * 展示显示商品
     */
    public void initShowGoods() {
        //重置商品排列顺序
        String itemSequenceString = BranchResourceService.getInstance().getItemSequenceString(LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.activeLoginBean.getBranch_num());
        if (!TextUtils.isEmpty(itemSequenceString)) {
            ArrayList<ItemSequenceBean> itemSequence = XmlUtil.getItemSequence(itemSequenceString);
            if (itemSequence != null && itemSequence.size() > 0) {
                PosItemService.resetPosItemSequence(itemSequence);
            }
        }
        ItemCategoryService.getInstance().setHierarchyCategory();
        LibConfig.activityShowGoods = RetailPosManager.getInstance().getRetailGoodsList();
    }

    public boolean initSaleBean(){
        SaleParamsBean saleParam;
        BookResource bookPosSale = BookResourceService.getInstance().getBookPosSale(LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.S_LOCAL_SCALE_STYLE);
        if (bookPosSale != null) {
            saleParam = XmlUtil.getPosSaleParams(bookPosSale.getBookResourceParam());
        } else {
            saleParam = new SaleParamsBean();
        }

        LibConfig.saleParamsBean = saleParam;

        return saleParam != null;
    }
}
