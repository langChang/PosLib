package com.nhsoft.poslib;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.nhsoft.poslib.dao.UserDao;
import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.AmountPay;
import com.nhsoft.poslib.entity.AppUser;
import com.nhsoft.poslib.entity.BookResource;
import com.nhsoft.poslib.entity.CardTypeParam;
import com.nhsoft.poslib.entity.Inventory;
import com.nhsoft.poslib.entity.ItemCategory;
import com.nhsoft.poslib.entity.KeyGeneratorBizday;
import com.nhsoft.poslib.entity.Login;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.PosItemGrade;
import com.nhsoft.poslib.entity.VipCrmAmaLevel;
import com.nhsoft.poslib.entity.VipUserInfo;
import com.nhsoft.poslib.entity.order.Payment;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.entity.shift.ShiftTable;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.model.AdjustPriceOrder;
import com.nhsoft.poslib.model.AdjustTradePriceOrder;
import com.nhsoft.poslib.model.BasePolicyBean;
import com.nhsoft.poslib.model.ClientParamsBean;
import com.nhsoft.poslib.model.CouponsBean;
import com.nhsoft.poslib.model.GoodsGradeBean;
import com.nhsoft.poslib.model.PosScaleStyleTypeBean;
import com.nhsoft.poslib.model.RedisBean;
import com.nhsoft.poslib.model.ShiftTableTotal;
import com.nhsoft.poslib.model.VipCardConfig;
import com.nhsoft.poslib.model.VipCardTypeBean;
import com.nhsoft.poslib.normal.callback.SerachGoodsCallback;
import com.nhsoft.poslib.normal.callback.SystemConnectCallback;
import com.nhsoft.poslib.normal.impl.AdjustPriceImpl;
import com.nhsoft.poslib.normal.impl.CheckPermissionImpl;
import com.nhsoft.poslib.normal.impl.ClearDataImpl;
import com.nhsoft.poslib.normal.impl.GlobalDataImpl;
import com.nhsoft.poslib.normal.impl.GoodsDataImpl;
import com.nhsoft.poslib.normal.impl.InventoryImpl;
import com.nhsoft.poslib.normal.impl.OrderOperationImpl;
import com.nhsoft.poslib.normal.impl.PromotionOperationImpl;
import com.nhsoft.poslib.service.AppUserService;
import com.nhsoft.poslib.service.BookResourceService;
import com.nhsoft.poslib.service.BottomMenuService;
import com.nhsoft.poslib.service.CardTypeParamService;
import com.nhsoft.poslib.service.CustomerRegisterSerivce;
import com.nhsoft.poslib.service.ItemCategoryService;
import com.nhsoft.poslib.service.LoginService;
import com.nhsoft.poslib.service.OrderService;
import com.nhsoft.poslib.service.OtherRevenueService;
import com.nhsoft.poslib.service.PosItemService;
import com.nhsoft.poslib.service.ShiftTableService;
import com.nhsoft.poslib.service.VipCrmAmaLevelService;
import com.nhsoft.poslib.utils.EvtLog;
import com.nhsoft.poslib.utils.NumberUtil;
import com.nhsoft.poslib.utils.TimeUtil;
import com.nhsoft.poslib.utils.WeightOutBarUtil;
import com.nhsoft.poslib.utils.XmlParser;
import com.nhsoft.poslib.utils.XmlUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Iverson on 2019-11-25 11:16
 * 此类用于：
 * 零售管理类
 */
public class RetailPosManager {


    public static Context               sContext;
    public SystemConnectCallback mConnectCallBack;

    public static PosTypeEnum sPosType = PosTypeEnum.AMA_POS; //pos类型
    private static RetailPosManager instance;

    private RetailPosManager() {
    }

    /**
     * 获取管理类的实例对象
     * @return
     */
    public static RetailPosManager getInstance() {
        if (instance == null) {
            instance = new RetailPosManager();
        }
        return instance;
    }

    /**
     * 初始化整个sdk
     * @param context
     * @return
     */
    public static boolean initLib(Context context, PosTypeEnum posTypeEnum) {
        sPosType = posTypeEnum;
        boolean flag = true;
        sContext = context;
        DaoManager.init(context);
        return flag;
    }

    /*********************sdk交互的方式**************************/
    public void setSystemConnect(SystemConnectCallback connectCallBack) {
        mConnectCallBack = connectCallBack;
    }

    /**
     * 提示错误信息
     *
     * @param info
     */
    public void showErrorInfo(String info) {
        if (mConnectCallBack != null) {
            mConnectCallBack.showErrorInfo(info);
        }
    }

    /**
     * 提示错误信息
     *
     * @param info
     */
    public void showWarnInfo(String info) {
        if (mConnectCallBack != null) {
            mConnectCallBack.showWarnInfo(info);
        }
    }


    /**
     * 打开钱箱
     */
    public void openDraw() {
        if (mConnectCallBack != null) {
            mConnectCallBack.openDraw();
        }
    }

    //设置当前登录信息
    public void setLoginBean(Login loginBean) {
        LibConfig.activeLoginBean = loginBean;
    }


    /**
     * 初始化前台参数
     *
     * @return true 为初始化前台参数成功 false 为初始化前台参数失败
     */
    public boolean initSaleParams() {
        GlobalDataImpl globalData = new GlobalDataImpl();
        return globalData.initSaleParams();
    }


    /**
     * 初始化前台展示商品
     *
     * @retur
     */
    public void initShowGoods() {
        GlobalDataImpl globalData = new GlobalDataImpl();
        globalData.initShowGoods();
    }


    /**
     * 初始化前台销售参数
     *
     * @return true 为初始化前台参数成功 false 为初始化前台参数失败
     */
    public boolean initSaleBean() {
        GlobalDataImpl globalData = new GlobalDataImpl();
        return globalData.initSaleBean();
    }


    /**
     * 清除数据
     *
     * @param days
     */
    public void clearData(int days) {
        ClearDataImpl clearData = new ClearDataImpl();
        clearData.clearData(days);
    }

    /**
     * 为了农贸版本修改
     */
    public void resetBottomMenuData() {
        if (!UserDao.getDeteleBottomMenu()) {
            BottomMenuService.getInstance().removeAllBottomMenu();
            UserDao.setDeteleBottomMenu(true);
        }
    }

    /**
     * 获取当前登录信息
     *
     * @return
     */
    public Login getCurrentLogin() {
        Login mCurrentLogin = LoginService.getInstance().queryAll().get(0);
        LibConfig.activeLoginBean = mCurrentLogin;
        return mCurrentLogin;
    }

    /**
     * 获取当前班次
     *
     * @return
     */
    public ShiftTable getCurrentShiftTable() {
        ShiftTable mShiftTable = ShiftTableService.getInstance().getCurrentClosedShiftTable(LibConfig.activeLoginBean.getSystem_book_code(),
                LibConfig.activeLoginBean.getBranch_num(), ShiftTableService.getInstance().getCurrentBizday(LibConfig.activeLoginBean.getSystem_book_code(),
                        LibConfig.activeLoginBean.getBranch_num()), LibConfig.activeAppUser.getApp_user_num());
        LibConfig.activeShiftTable = mShiftTable;
        return mShiftTable;
    }

    /**
     * 获取当前用户
     *
     * @param userCode
     * @return
     */
    public AppUser getCurrentAppUser(String userCode) {
        LibConfig.activeAppUser = AppUserService.getInstance().login
                (LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.activeLoginBean.getBranch_num(), userCode);
        return LibConfig.activeAppUser;

    }


    /********************商品档案相关***********************/

    /**
     * 获取零售展示商品
     *
     * @return
     */
    public List<GoodsGradeBean> getRetailGoodsList() {
        GoodsDataImpl goodsData = new GoodsDataImpl();
        return goodsData.getAllShowGoods();
    }

    /**
     * 检查商品是否在消费劵支持的范围内
     *
     * @param couponsBean
     * @param posOrderDetail
     * @return 0：支持所有的商品 1部分支持商品内  2：不在部分支持商品内
     */
    public int isInGoodsList(CouponsBean couponsBean, PosOrderDetail posOrderDetail) {
        GoodsDataImpl goodsData = new GoodsDataImpl();
        return goodsData.isInGoodsList(couponsBean, posOrderDetail);
    }

    /**
     * 检查商品是否在消费劵支持的范围内
     *
     * @param couponsBean
     * @param posOrderDetail
     * @return true 是包含  false 不包含
     */
    public boolean isContainGoods(CouponsBean couponsBean, PosOrderDetail posOrderDetail) {
        GoodsDataImpl goodsData = new GoodsDataImpl();
        int result = goodsData.isInGoodsList(couponsBean, posOrderDetail);
        return result == 1 ? true : false;
    }


    /**
     * 获取农贸展示的商品
     * stallNum : 档口编号
     * merchant_num： 商户编号
     *
     * @return
     */
    public List<GoodsGradeBean> getTradeGoodsList(int stallNum, int merchantNum) {
        GoodsDataImpl goodsData = new GoodsDataImpl();
        return goodsData.getAllShowGoods(stallNum, merchantNum);
    }


    /********************订单相关***********************/

    /**
     * 挂单
     *
     * @return
     */
    public boolean collectPosOrder(PosOrder posOrder) {
        OrderOperationImpl orderOperation = new OrderOperationImpl();
        return orderOperation.collectOrder(posOrder);
    }

    /**
     * 拷贝一个订单
     *
     * @param posOrder
     * @return
     */
    public PosOrder copyPosOrder(PosOrder posOrder) {
        OrderOperationImpl orderOperation = new OrderOperationImpl();
        return orderOperation.copayPosOrder(posOrder);
    }


    /**
     * 拷贝一个订单详细
     *
     * @param posOrderdetail
     * @return
     */
    public PosOrderDetail copyPosOrderDetail(PosOrderDetail posOrderdetail) {
        OrderOperationImpl orderOperation = new OrderOperationImpl();
        return orderOperation.copayPosOrderDetail(posOrderdetail);
    }


    /**
     * 创建一个payment
     */
    public Payment createPayment(String orderNo, PosScaleStyleTypeBean styleTypeBean) {
        OrderOperationImpl orderOperation = new OrderOperationImpl();
        return orderOperation.createPayment(orderNo, styleTypeBean);
    }


    public Payment updatePayment(Payment payment, float receiveMoney) {
        return null;
    }
    /**
     * 开启消费券分摊
     * @param posOrder
     */
    public PosOrder createQuitPosOrder2ByAll(PosOrder posOrder, PosOrder oldPosOrder, KeyGeneratorBizday mCurrentPosOrderKGB ){
        return new OrderOperationImpl().createQuitPosOrder2ByAll(posOrder, oldPosOrder, mCurrentPosOrderKGB);
    }

    /**
     * 获取可用的支付方式
     *
     * @param paymentName 当如果需要是会员支付折扣的支付时
     *                    paymentName传入 LibConfig.C_PAYMENT_TYPE_PETCARD_NAME
     *                    其他传个空就行
     * @return
     */
    public List<PosScaleStyleTypeBean> getEnablePayment(String paymentName) {
        OrderOperationImpl orderOperation = new OrderOperationImpl();
        return orderOperation.getEnablePayment(paymentName);
    }

    /**
     * 查看是否可用的支付方式包含现金支付
     *
     * @param posSaleParam
     * @return
     */
    public PosScaleStyleTypeBean containCashPayment(List<PosScaleStyleTypeBean> posSaleParam) {
        OrderOperationImpl orderOperation = new OrderOperationImpl();
        return orderOperation.containCashPayment(posSaleParam);
    }

    /**
     * 查看是否可用的支付方式包含储值卡支付
     *
     * @param posSaleParam
     * @return
     */
    public PosScaleStyleTypeBean containCardPayment(List<PosScaleStyleTypeBean> posSaleParam) {
        OrderOperationImpl orderOperation = new OrderOperationImpl();
        return orderOperation.containCardPayment(posSaleParam);
    }

    /**
     * 根据商品paymenMoney由大到小来进行排序
     *
     * @param posOrderDetails
     */
    public void sortPosOrderDetail(List<PosOrderDetail> posOrderDetails) {
        OrderOperationImpl orderOperation = new OrderOperationImpl();
        orderOperation.sortPosOrderDetail(posOrderDetails);
    }

    /**
     * 根据优惠券ticket_send_detail_value（面值）由小到大来进行排序
     *
     * @param couponsBeans
     */
    public void sortCoupons(List<CouponsBean> couponsBeans) {
        OrderOperationImpl orderOperation = new OrderOperationImpl();
        orderOperation.sortCoupons(couponsBeans);
    }

    /**
     * 创建退卡的json
     *
     * @param vipUserInfo
     * @return
     */
    public String createQuitCardJson(VipUserInfo vipUserInfo) {
        OrderOperationImpl orderOperation = new OrderOperationImpl();
        return orderOperation.createQuitCardJson(vipUserInfo);
    }


    /***********************促销相关*****************************/

    /**
     * 获取所有的可用的超额奖励和赠品促销
     *
     * @return
     */
    public List<BasePolicyBean> getAllUsePolicys(PosOrder posOrder) {
        PromotionOperationImpl promotionOperation = new PromotionOperationImpl();
        return promotionOperation.getAllUsePolicys(posOrder);
    }

    /**
     * 看看零售订单能否享受超额减免和超额折扣
     *
     * @param posOrder
     */
    public void getPolicyDiscount(PosOrder posOrder) {
        PromotionOperationImpl promotionOperation = new PromotionOperationImpl();
        promotionOperation.getPolicyDiscount(posOrder);
    }

    /**
     * 看看农贸订单能否享受超额减免和超额折扣
     *
     * @param posOrder
     */
    public void getTradePolicyDiscount(PosOrder posOrder) {
        PromotionOperationImpl promotionOperation = new PromotionOperationImpl();
        promotionOperation.getPolicyDiscount(posOrder);
    }


    /***********************权限相关*****************************/

    /**
     * 查询本班次的收银员是否拥有某个权限某种操作的操作
     *
     * @param permissionName 权限名称
     * @param operatorName   操作名称
     * @return result : 1 => 拥有该权限  2 => 可以通过其他人授权 3 => 无法操作
     */
    public int checkPermission(String permissionName, String operatorName) {
        return checkPermission(LibConfig.activeAppUser, permissionName, operatorName);
    }

    /**
     * 查询本班次的收银员是否拥有某个权限某种操作的操作
     *
     * @param permissionName 权限名称
     * @param operatorName   操作名称
     * @return result : 1 => 拥有该权限  2 => 可以通过其他人授权 3 => 无法操作
     */
    public int checkPermission(AppUser appUser, String permissionName, String operatorName) {
        CheckPermissionImpl checkPermission = new CheckPermissionImpl();
        return checkPermission.checkPermission(appUser, permissionName, operatorName, LibConfig.systemRoleList);
    }


    /***********************调价单相关*****************************/

    /**
     * 创建一个可以请求的零售调价单
     *
     * @param adjustDetailsBeans：调价的商品列表
     * @return
     */
    public AdjustPriceOrder createAdjustOrder(List<AdjustPriceOrder.AdjustDetailsBean> adjustDetailsBeans) {
        AdjustPriceImpl adjustPrice = new AdjustPriceImpl();
        return adjustPrice.createAdjustOrder(adjustDetailsBeans);
    }

    /**
     * 创建一个可以请求的农贸调价单
     *
     * @param adjustTradeDetailBeans：调价的商品列表
     * @return
     */
    public AdjustTradePriceOrder createAdjustTradeOrder(List<AdjustTradePriceOrder.AdjustTradeDetailBean> adjustTradeDetailBeans) {
        AdjustPriceImpl adjustPrice = new AdjustPriceImpl();
        return adjustPrice.createAdjustTradeOrder(adjustTradeDetailBeans);
    }

    /**
     * 创建一个调价零售商品的对象
     *
     * @param posItem
     * @return
     */
    public AdjustPriceOrder.AdjustDetailsBean createAdjustDetail(PosItem posItem, PosItemGrade posItemGrade) {
        AdjustPriceImpl adjustPrice = new AdjustPriceImpl();
        return adjustPrice.createAdjustDetail(posItem, posItemGrade);
    }

    /**
     * 创建一个调价农贸商品的对象
     *
     * @param posItem
     * @return
     */
    public AdjustTradePriceOrder.AdjustTradeDetailBean createAdjustTradeDetail(PosItem posItem) {
        AdjustPriceImpl adjustPrice = new AdjustPriceImpl();
        return adjustPrice.createAdjustTradeDetail(posItem);
    }


    /***********************搜索商品相关*****************************/

    /**
     * @param callBack
     * @param searchText      搜索的内容
     * @param posOrderDetails 当前的已经录入的商品列表，如果是调价单搜索传null
     * @return
     */
    public boolean serachResult(SerachGoodsCallback callBack, String searchText, LinkedList<PosOrderDetail> posOrderDetails) {
        List<PosItem> posItems = new ArrayList<>();
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        if (pattern.matcher(searchText).find()) {
            posItems = PosItemService.getInstance().getPosItemByItemNum(
                    LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.activeLoginBean.getBranch_num(), searchText.toUpperCase());
        } else {
            if (posOrderDetails != null && WeightOutBarUtil.isWeightOutBarGoods(searchText)) {
                PosOrderDetail posOrderDetail = WeightOutBarUtil.getWeightOutBarGoods(searchText, posOrderDetails);
                if (posOrderDetail == null) {
                    callBack.searchNoUseGoods("当前没有搜索到结果!");
                    return false;
                } else {
                    callBack.searchOutBarGoods(posOrderDetail);
                    callBack.resetText();
                    callBack.hideSoftInput();
                    return false;
                }
            } else {
                posItems = PosItemService.getInstance().getPosItemByItemBarCode(LibConfig.activeLoginBean.getBranch_num(), searchText.toUpperCase());
            }
        }

        if (posItems == null || posItems.size() == 0) {
            callBack.searchNoUseGoods("当前没有搜索到结果!");
            return false;
        } else {
            if (posItems.size() == 1) {
                if (posItems.get(0).getItem_type() == 11) {
                    callBack.searchNoUseGoods("原料商品不允许销售!");
                    return false;
                }

                if (posItems.get(0).getItem_type() == 9) {
                    List<PosItem> posItemByKitList = PosItemService.getInstance().getPosItemByKitNum(posItems.get(0).getItem_num());
                    if (posItemByKitList.size() > 0) {
                        posItems.remove(0);
                        posItems.addAll(posItemByKitList);
                        if (posItems.size() > 1) {
                            callBack.serachGoodsList(posItems);
                            callBack.hideSoftInput();
                            return false;
                        }
                    } else {
                        callBack.searchNoUseGoods("成分商品不允许销售!");
                        return false;
                    }
                }

                if (posItems.get(0).getItem_type() == 10) {
                    List<PosItemGrade> allItemGrade = PosItemService.getInstance().getAllItemGrade(posItems.get(0).getItem_num());
                    if (allItemGrade == null || allItemGrade.size() == 0) {
                        callBack.searchNoUseGoods("该商品没有分级商品可销售!");
                        return false;
                    }
                    List<PosItem> newPosItemList = new ArrayList<>();
                    for (PosItemGrade posItemGrade : allItemGrade) {
                        try {
                            PosItem posItem = (PosItem) posItems.get(0).clone();
                            posItem.setPosItemGrade(posItemGrade);
                            newPosItemList.add(posItem);
                        } catch (Exception e) {

                        }
                    }
                    posItems.clear();
                    posItems.addAll(newPosItemList);
                    callBack.serachGoodsList(posItems);
                } else {
                    callBack.serachGoodsOnly(posItems.get(0), null);
                }
                callBack.resetText();
            } else {

                List<PosItem> newPosItemList = new ArrayList<>();
                for (PosItem posItem : posItems) {
                    if (posItem.getItem_type() == 10) {
                        List<PosItemGrade> allItemGrade = PosItemService.getInstance().getAllItemGrade(posItem.getItem_num());
                        for (PosItemGrade posItemGrade : allItemGrade) {
                            try {
                                PosItem clonePosItem = (PosItem) posItem.clone();
                                clonePosItem.setPosItemGrade(posItemGrade);
                                newPosItemList.add(clonePosItem);
                            } catch (Exception e) {

                            }
                        }
                    } else {
                        newPosItemList.add(posItem);
                    }
                }
                posItems.clear();
                posItems.addAll(newPosItemList);
                if (posItems.size() == 0) {
                    callBack.searchNoUseGoods("当前没有搜索到结果!");
                    return false;
                }
                callBack.serachGoodsList(posItems);
            }
        }
        return false;
    }


    public List<PosItem> getAllGradeList(PosItem posItem) {
        List<PosItem> serachList = new ArrayList<>();

        List<PosItemGrade> posItemGrades = PosItemService.getInstance().getAllGradeByItemNum(posItem.getItem_num());
        if (posItemGrades == null || posItemGrades.size() == 0) return serachList;

        for (PosItemGrade posItemGrade : posItemGrades) {
            PosItem clonePosItem = RetailPosManager.getInstance().copyPosItem(posItem);
            clonePosItem.setShowPosItemGrade(posItemGrade);
            serachList.add(clonePosItem);
        }
        return serachList;
    }

    public PosItem copyPosItem(PosItem posItem) {
        PosItem copyPosItem = new Gson().fromJson(new Gson().toJson(posItem), PosItem.class);
        return copyPosItem;
    }

    /**
     * 检查PosItem是否停售
     *
     * @param posItem
     * @return
     */
    public static boolean checkPosItemScaleFlag(PosItem posItem) {
        if (LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived()) {
            return posItem.getBranch_sale_cease_flag();
        } else {
            return posItem.getItem_sale_cease_flag() == null ? false : posItem.getItem_sale_cease_flag();
        }
    }

    /**
     * 检查PosItem是否停售
     *
     * @param
     * @return
     */
    public static boolean checkPosItemGradeScaleFlag(PosItemGrade posItemGrade) {
        return posItemGrade.getItem_grade_sale_cease_flag();
    }

    public static CardTypeParam getCardType(String typeCode) {
        return CardTypeParamService.getInstance().getCardType(typeCode);
    }

    //老式
    public static VipCardTypeBean getVipCardTypeBean(String type_name) {
        return BookResourceService.getInstance().getVipCardTypeBeanList(LibConfig.activeShiftTable.getSystemBookCode(), LibConfig.S_LOCAL_VIP_TYPE, type_name);
    }


    public static VipCardConfig getVipConfig(String systemBookCode) {
        VipCardConfig vipCardConfigBean = null;
        BookResource bookPosCardType = BookResourceService.getInstance().getBookPosSale(systemBookCode, LibConfig.S_LOCAL_VIP_STYPE);
        if (bookPosCardType != null) {
            Gson gson = new Gson();
            String s = XmlParser.xml2json(bookPosCardType.getBookResourceParam());
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONObject object = jsonObject.optJSONObject("消费卡参数");
                vipCardConfigBean = gson.fromJson(object.toString(), VipCardConfig.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return vipCardConfigBean;
    }


    /**
     * 是否开启全渠道
     *
     * @return
     */
    public static boolean isOpenCrm() {
        return BookResourceService.getInstance().isOpenCrm();
    }

    public static VipCrmAmaLevel getVipLevel(String id) {
        if (id == null) return null;
        return VipCrmAmaLevelService.getInstance().getVipCrmAmaLevelById(id);
    }

    /**
     * 检测当前的CRM会员是否满足促销条件
     *
     * @param vipUserInfo
     * @param ids
     * @return
     */
    public static boolean checkCrmLevelInPolicy(VipUserInfo vipUserInfo, String ids) {
        if (vipUserInfo == null) return false;
        if (!TextUtils.isEmpty(ids)) {
            if (TextUtils.isEmpty(vipUserInfo.getLevel())) {
                return false;
            }
            if (!ids.contains(vipUserInfo.getLevel())) {
                return false;
            }
        }
        return true;
    }


    /**
     * 获取客户签单配置
     *
     * @return
     */
    public ClientParamsBean getClientParams() {
        BookResource bookPosSale = BookResourceService.getInstance().getBookPosSale(LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.S_LOCAL_CLIENT_STYLE);
        return XmlUtil.getClientParams(bookPosSale.getBookResourceParam());
    }


    //计算分摊券
    public void calculateShareCoupons(List<PosOrderDetail> posOrderDetails, List<CouponsBean> couponsBeans) {
        if(couponsBeans == null || couponsBeans.isEmpty()){
            return;
        }

        for (CouponsBean couponsBean : couponsBeans) {
            float totalShareMoney = couponsBean.getTicket_send_detail_value() - couponsBean.getResidueMoney();
            float remainShareMoney = couponsBean.getTicket_send_detail_value() - couponsBean.getResidueMoney();
            List<PosOrderDetail> containDetails = new ArrayList<>();
            float totalGoodsMoney = 0;
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (!posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM)) {
                    continue;
                }

                if (!posOrderDetail.getOrderDetailStateCode().equals(LibConfig.S_ORDER_DETAIL_SALE)) {
                    continue;
                }

                if (posOrderDetail.getPosItem() != null && !posOrderDetail.getPosItem().getItem_discounted()) { //不打折
                    continue;
                }



                boolean isContain = isContainGoods(couponsBean, posOrderDetail);
                if (isContain) {
                    if("消费折扣券".equals(couponsBean.getTicket_category())){
                        if(posOrderDetail.getOrderDetailPrice() < posOrderDetail.getOrderDetailStdPrice() * couponsBean.getTicket_send_discount()){
                            continue;
                        }
                    }
                    totalGoodsMoney += posOrderDetail.getOrderDetailPaymentMoney();
                    containDetails.add(posOrderDetail);
                }
            }

            for (int i = 0; i < containDetails.size(); i++) {
                PosOrderDetail posOrderDetail = containDetails.get(i);
                Float couponShareMoney = posOrderDetail.getOrderDetailShareDiscount();
                if (i == containDetails.size() - 1) {
                    posOrderDetail.setOrderDetailShareDiscount(couponShareMoney + remainShareMoney);
                    break;
                }
                float shareMoney = NumberUtil.getNewFloat(posOrderDetail.getOrderDetailPaymentMoney() * totalShareMoney / totalGoodsMoney);
                posOrderDetail.setOrderDetailShareDiscount(shareMoney + couponShareMoney);
                remainShareMoney -= shareMoney;
            }

        }
    }


    /**
     * 创建只应用一次的情况
     * @param vipUserInfo
     * @param redisNo
     * @return
     */
    public RedisBean createPolicyRedisBean(VipUserInfo vipUserInfo, String redisNo){
        if(vipUserInfo == null)return null;
        RedisBean redisBean = new RedisBean();
        if (!TextUtils.isEmpty(vipUserInfo.getCustomer_id())) {
            redisBean.setRedis_key("PolicyPromotion_"+redisNo+"_"+vipUserInfo.getCustomer_id());
            redisBean.setRedis_time(TimeUtil.getSubTime(TimeUtil.getInstance().getNowDateString(),""));
            redisBean.setRedis_value("YES");
            redisBean.setVip_id(vipUserInfo.getCustomer_id());
        }else {
            redisBean.setRedis_key("PolicyPromotion_"+redisNo+"_"+vipUserInfo.getCard_user_num());
            redisBean.setRedis_time(TimeUtil.getSubTime(TimeUtil.getInstance().getNowDateString(),""));
            redisBean.setRedis_value("YES");
            redisBean.setVip_id(vipUserInfo.getCard_user_num());
        }
        return redisBean;
    }


    /**
     * 获取班次的资金信息
     * @param systemBookCode
     * @param shiftTableNum
     * @param branchNum
     * @return
     */
    public ShiftTableTotal getShiftTableInfo(String systemBookCode, String shiftTableNum, String branchNum,ShiftTable shiftTable){
        ShiftTableTotal  printShiftTable = new ShiftTableTotal();
        List<AmountPay> paymentList = new ArrayList<>();

        printShiftTable.real_cash_receive = shiftTable.getShift_input_cash() == null ? 0 : shiftTable.getShift_input_cash();

        String strBizDay = shiftTable.getShiftTableBizday();

        int strStartCardNum = OrderService.getInstance().getStartCardNum(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");

        String strStrangeSuccessSendNum = OrderService.getInstance().getStrangeSuccessSendNum(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "") + "";
        float strStrangeSuccessSendMoney = OrderService.getInstance().getStrangeSuccessSendMoney(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");

        int strCouponsNum = OrderService.getInstance().getTicketSendDetailNum(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");
        float strCouponsMoney = OrderService.getInstance().getTicketSendMoney(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "", 5);
        Map map = OrderService.getInstance().getOrderPromotionDiscountMoney(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday(), 5);
        float strCouponsMoney01 = (float) map.get(LibConfig.TICKET_TAG);
//                OrderService.getInstance().getTicketSendMoney01(shiftTable.getSystemBookCode(),
//                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "", 5);
        float exitMoney = OrderService.getInstance().getExitOrderMoney(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday(), 5);
        int exitNum = OrderService.getInstance().getExitOrderNum(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday(), 5);

        int strSingle = OrderService.getInstance().PosOrderNum(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday(), 5);

        float strDiscountAmount = (float) map.get(LibConfig.MGR_TAG);
//                OrderService.getInstance().getPosOrderDiscountAmount(shiftTable.getSystemBookCode(),
//                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
//                shiftTable.getShiftTableBizday(), 5);

        float strRoundAmount = OrderService.getInstance().getPosOrderRoundMoney(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday(), 5);
        int numShiftTableNum = CustomerRegisterSerivce.getInstance().getNumShiftTableNum(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");
        float moneyShiftTableNum = CustomerRegisterSerivce.getInstance().getMoneyShiftTableNum(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");
        float strAmount = OrderService.getInstance().getPosOrderAmount(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday(), 5);

        float strAllAmount = OrderService.getInstance().getPosOrderAllAmount(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday(), 5);
//        strAllAmount=


        int StrangeCardNum = OrderService.getInstance().getDepositNum
                (shiftTable.getSystemBookCode(), shiftTable.getShiftTableNum() + "",
                        shiftTable.getBranchNum() + "");
        int ReplaceCardNum = OrderService.getInstance().getReplaceCardNum(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", String.valueOf(shiftTable.getBranchNum()));
        int RenewCardNum = OrderService.getInstance().getStrangeCardNum(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");
        double StrangeCardMoney = OrderService.getInstance().getDepositMoney(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");
        double ReplaceCardMoney = OrderService.getInstance().getReplaceCardMoney(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");
        double RenewCardMoney = OrderService.getInstance().getRenewCardMoney(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");

        int changeNum = OrderService.getInstance().getChangeNum(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");
        double changeMoney = OrderService.getInstance().getChangeMoney(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");

        float vipDiscountMoney = (float) map.get(LibConfig.GOODS_VIP_TAG);
//                OrderService.getInstance().getOrderVipDiscountMoney(shiftTable.getSystemBookCode(),
//                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
//                shiftTable.getShiftTableBizday(), 5);
        float modifyPriceDiscountMoney = (float) map.get(LibConfig.GOODS_CHANGE_TAG);
//                OrderService.getInstance().getOrderModifyPriceDiscountMoney(shiftTable.getSystemBookCode(),
//                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
//                shiftTable.getShiftTableBizday(), 5);
        float promotionDiscountMoney = (float) map.get(LibConfig.GOODS_PROMO_TAG);
//        OrderService.getInstance().getOrderPromotionDiscountMoney(shiftTable.getSystemBookCode(),
//                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
//                shiftTable.getShiftTableBizday(), 5);
        float noDiscountMoney = OrderService.getInstance().getOrderNoDiscountMoney(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday(), 5);

        float feeNum = (float) map.get(LibConfig.S_ORDER_DETAIL_PRESENT_NAME);
//                OrderService.getInstance().getFeeMoney(shiftTable.getSystemBookCode(),
//                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
//                shiftTable.getShiftTableBizday(), 5);

        float sellAllMoney = noDiscountMoney - promotionDiscountMoney - modifyPriceDiscountMoney - strRoundAmount -
                vipDiscountMoney - strCouponsMoney - strDiscountAmount - feeNum ;

        int othersNum = OtherRevenueService.getInstance().getNum(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday());
        float othersMoney = OtherRevenueService.getInstance().getMoney(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday());

        float collectionMoney = OrderService.getInstance().getAmountOfCollection(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday());
        float floatAllReceiveMoney = 0;

        try {
            paymentList = OrderService.getInstance().getListPaymentByPayType(shiftTable.getSystemBookCode(),
                    shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                    shiftTable.getShiftTableBizday());
            if (paymentList != null) {
                for (AmountPay amountPay : paymentList) {
                    floatAllReceiveMoney = floatAllReceiveMoney + amountPay.getAmountMoney();
                }
            }
        } catch (Exception e) {
            EvtLog.d("eeeeee:=" + e.toString());
        }
        printShiftTable.othersNum = othersNum;
        printShiftTable.othersMoney = othersMoney;

        printShiftTable.feeNum = feeNum;
        printShiftTable.roundAmount = strRoundAmount;
        printShiftTable.saleNum = strSingle;
        printShiftTable.saleMoney = strAmount;
        printShiftTable.exitNum = exitNum;
        printShiftTable.exitMoney = exitMoney;
        printShiftTable.couponNum = strCouponsNum;
        printShiftTable.couponMoney = strCouponsMoney;

        printShiftTable.openCardNum = strStartCardNum;
        printShiftTable.strangeNum = StrangeCardNum;
        printShiftTable.strangeMoney = (float) StrangeCardMoney;
        printShiftTable.replaceCardNum = ReplaceCardNum;
        printShiftTable.replaceCardMuney = (float) ReplaceCardMoney;
        printShiftTable.renewCardNum = RenewCardNum;
        printShiftTable.renewCardMoney = (float) RenewCardMoney;
        printShiftTable.cardChangeNum=changeNum;
        printShiftTable.cardChangeMoney=(float)changeMoney;
        printShiftTable.cardRrmRegisterNum = numShiftTableNum;
        printShiftTable.cardRrmRegisterMoney = moneyShiftTableNum;

        printShiftTable.receiveAndPay = strAmount + strAllAmount;
        printShiftTable.goodAmount = noDiscountMoney;
        printShiftTable.vipDiscountMoney = vipDiscountMoney;
        printShiftTable.modifyDiscountMoney = modifyPriceDiscountMoney;
        printShiftTable.promotionDiscountMoney = promotionDiscountMoney;
        printShiftTable.couponDiscountMoney = strCouponsMoney;
        printShiftTable.managerDiscountMoney = strDiscountAmount;

        printShiftTable.saleAmount = sellAllMoney;
        printShiftTable.receiveAmountMoney = strAmount + strAllAmount + exitMoney + moneyShiftTableNum;
        printShiftTable.paymentList = paymentList;

        return printShiftTable;
    }

    /**
     * 保存库存
     * @param inventories
     */
    public void saveGoodsInventory(List<Inventory> inventories) {
      LibConfig.sInventoryList =  new InventoryImpl().saveGoodsInventoryList(inventories);
    }

    public ItemCategory getItemCategoryByCode(String itemCategoryCode){
        return ItemCategoryService.findCategoryCode(itemCategoryCode);
    }
}
