package com.nhsoft.poslib;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.nhsoft.poslib.call.callback.SerachGoodsCallback;
import com.nhsoft.poslib.call.callback.SystemConnectCallback;
import com.nhsoft.poslib.call.impl.AccountBankImpl;
import com.nhsoft.poslib.call.impl.AdjustPriceImpl;
import com.nhsoft.poslib.call.impl.AggregationImpl;
import com.nhsoft.poslib.call.impl.AppUserImpl;
import com.nhsoft.poslib.call.impl.AttachedScreenImpl;
import com.nhsoft.poslib.call.impl.BookResourceImpl;
import com.nhsoft.poslib.call.impl.BranchGroupImpl;
import com.nhsoft.poslib.call.impl.BranchImpl;
import com.nhsoft.poslib.call.impl.BranchRegionImpl;
import com.nhsoft.poslib.call.impl.BranchResourceImpl;
import com.nhsoft.poslib.call.impl.CardTypeParamImpl;
import com.nhsoft.poslib.call.impl.CheckPermissionImpl;
import com.nhsoft.poslib.call.impl.ClearDataImpl;
import com.nhsoft.poslib.call.impl.ClientPointImpl;
import com.nhsoft.poslib.call.impl.CustomerRegisterImpl;
import com.nhsoft.poslib.call.impl.EmployeeImpl;
import com.nhsoft.poslib.call.impl.GlobalDataImpl;
import com.nhsoft.poslib.call.impl.InventoryImpl;
import com.nhsoft.poslib.call.impl.ItemCategoryImpl;
import com.nhsoft.poslib.call.impl.KeyGeneratorBizdayImpl;
import com.nhsoft.poslib.call.impl.LoginImpl;
import com.nhsoft.poslib.call.impl.ManagementTemplateImpl;
import com.nhsoft.poslib.call.impl.MarketActionImpl;
import com.nhsoft.poslib.call.impl.MeasureUnitImpl;
import com.nhsoft.poslib.call.impl.OrderImpl;
import com.nhsoft.poslib.call.impl.OrderOperationImpl;
import com.nhsoft.poslib.call.impl.OtherRevenueImpl;
import com.nhsoft.poslib.call.impl.PayStyleImpl;
import com.nhsoft.poslib.call.impl.PointOrderImpl;
import com.nhsoft.poslib.call.impl.PointPolicyImpl;
import com.nhsoft.poslib.call.impl.PolicyDiscountImpl;
import com.nhsoft.poslib.call.impl.PolicyMoneyImpl;
import com.nhsoft.poslib.call.impl.PolicyPresentImpl;
import com.nhsoft.poslib.call.impl.PolicyPromotionImpl;
import com.nhsoft.poslib.call.impl.PolicyQuantityImpl;
import com.nhsoft.poslib.call.impl.PosCarryLogImpl;
import com.nhsoft.poslib.call.impl.PosItemImpl;
import com.nhsoft.poslib.call.impl.PosMachineImpl;
import com.nhsoft.poslib.call.impl.PrivilegeResourceNewImpl;
import com.nhsoft.poslib.call.impl.PromotionOperationImpl;
import com.nhsoft.poslib.call.impl.RoleImpl;
import com.nhsoft.poslib.call.impl.ShiftTableImpl;
import com.nhsoft.poslib.call.impl.ShiftTablePaymentImpl;
import com.nhsoft.poslib.call.impl.StoreHouseImpl;
import com.nhsoft.poslib.call.impl.SystemBookImpl;
import com.nhsoft.poslib.call.impl.TicketSendImpl;
import com.nhsoft.poslib.call.impl.VipCrmAmaLevelImpl;
import com.nhsoft.poslib.call.impl.VipOperationImpl;
import com.nhsoft.poslib.dao.UserDao;
import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.AccountBank;
import com.nhsoft.poslib.entity.Aggregation;
import com.nhsoft.poslib.entity.AmountPay;
import com.nhsoft.poslib.entity.AppUser;
import com.nhsoft.poslib.entity.AttachedScreen;
import com.nhsoft.poslib.entity.BookResource;
import com.nhsoft.poslib.entity.Branch;
import com.nhsoft.poslib.entity.BranchGroup;
import com.nhsoft.poslib.entity.BranchRegion;
import com.nhsoft.poslib.entity.BranchResource;
import com.nhsoft.poslib.entity.CardTypeParam;
import com.nhsoft.poslib.entity.ClientPoint;
import com.nhsoft.poslib.entity.Employee;
import com.nhsoft.poslib.entity.Inventory;
import com.nhsoft.poslib.entity.ItemCategory;
import com.nhsoft.poslib.entity.KeyGeneratorBizday;
import com.nhsoft.poslib.entity.Login;
import com.nhsoft.poslib.entity.ManagementTemplate;
import com.nhsoft.poslib.entity.MarketAction;
import com.nhsoft.poslib.entity.MeasureUnit;
import com.nhsoft.poslib.entity.PointOrder;
import com.nhsoft.poslib.entity.PointPolicy;
import com.nhsoft.poslib.entity.PolicyDiscount;
import com.nhsoft.poslib.entity.PolicyMoney;
import com.nhsoft.poslib.entity.PolicyPresent;
import com.nhsoft.poslib.entity.PolicyPromotion;
import com.nhsoft.poslib.entity.PolicyQuantity;
import com.nhsoft.poslib.entity.PosCarryLog;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.PosItemGrade;
import com.nhsoft.poslib.entity.PosMachine;
import com.nhsoft.poslib.entity.PrivilegeResourceNew;
import com.nhsoft.poslib.entity.ShiftTablePayment;
import com.nhsoft.poslib.entity.StoreHouse;
import com.nhsoft.poslib.entity.SystemBook;
import com.nhsoft.poslib.entity.SystemRole;
import com.nhsoft.poslib.entity.TicketSendDetail;
import com.nhsoft.poslib.entity.VipCrmAmaLevel;
import com.nhsoft.poslib.entity.order.Payment;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.entity.shift.ShiftTable;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.model.AdjustPriceOrder;
import com.nhsoft.poslib.model.AdjustTradePriceOrder;
import com.nhsoft.poslib.model.BasePolicyBean;
import com.nhsoft.poslib.model.CardBalaceConfig;
import com.nhsoft.poslib.model.ClientParamsBean;
import com.nhsoft.poslib.model.CouponsBean;
import com.nhsoft.poslib.model.GoodsGradeBean;
import com.nhsoft.poslib.model.PosScaleStyleTypeBean;
import com.nhsoft.poslib.model.RedisBean;
import com.nhsoft.poslib.model.ShiftTableTotal;
import com.nhsoft.poslib.model.VipCardConfig;
import com.nhsoft.poslib.model.VipCardTypeBean;
import com.nhsoft.poslib.model.VipUserInfo;
import com.nhsoft.poslib.utils.EvtLog;
import com.nhsoft.poslib.utils.NumberUtil;
import com.nhsoft.poslib.utils.PointCalUtils;
import com.nhsoft.poslib.utils.TimeUtil;
import com.nhsoft.poslib.utils.WeightOutBarUtil;
import com.nhsoft.poslib.utils.XmlUtil;

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
    public        SystemConnectCallback mConnectCallBack;

    public static  PosTypeEnum      sPosType = PosTypeEnum.AMA_POS; //pos类型
    private static RetailPosManager instance;

    private RetailPosManager() {
    }

    /**
     * 获取管理类的实例对象
     *
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
     *
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
     * 初始化本地一些基本参数
     * @return 1：代表初始化成功 2: 终端初始化失败。
     */
    public int initLocalData(Context context){
        //初始化POSMachine
        //初始化Branch
        //初始化ShifteTable
        LibConfig.activeLoginBean = getLoginData();
        LibConfig.activePosMachine = getLocalPosMachine(LibConfig.activeLoginBean.getBranch_num(), getMachineMac(context));
        if(LibConfig.activePosMachine == null){
            return 2;
        }

        LibConfig.SYSTEM_BOOK = LibConfig.activeLoginBean.getSystem_book_code();
        LibConfig.SYSTEM_BOOK_NAME = LibConfig.activeLoginBean.getSystem_book_name();
        LibConfig.BRANCH_NUM = LibConfig.activeLoginBean.getBranch_num();
        LibConfig.BRANCH_NAME = LibConfig.activeLoginBean.getBranch_name();
        if(LibConfig.BRANCH_NAME == null){
            LibConfig.BRANCH_NAME = LibConfig.SYSTEM_BOOK_NAME;
        }
        LibConfig.SHIFT_TABLE_BIZDAY = getCurrentByzday(LibConfig.SYSTEM_BOOK,LibConfig.BRANCH_NUM);
        LibConfig.POS_MACHINE_TERMINAL_ID = LibConfig.activePosMachine.getPos_machine_terminal_id();
        LibConfig.POS_MACHINE_SEQUENCE = LibConfig.activePosMachine.getPos_machine_sequence();
        LibConfig.activeBranch = getBranchByNum(LibConfig.SYSTEM_BOOK, LibConfig.BRANCH_NUM);
        LibConfig.MATRIX_PRICE_ACTIVED = LibConfig.activeBranch.getBranch_matrix_price_actived();
        initSaleBean();
        return 1;
    }


    /**
     * 初始化前台参数
     *
     * @return true 为初始化前台参数成功 false 为初始化前台参数失败
     */
    public boolean initHomeData() {
        GlobalDataImpl globalData = new GlobalDataImpl();
        return globalData.initHomeData();
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
     * 获取当前登录信息
     *
     * @return
     */
    public Login getCurrentLogin() {
        Login mCurrentLogin = LoginImpl.getInstance().queryAll().get(0);
        LibConfig.activeLoginBean = mCurrentLogin;
        return mCurrentLogin;
    }

    /**
     * 获取当前班次
     *
     * @return
     */
    public ShiftTable getCurrentShiftTable() {
        ShiftTable mShiftTable = ShiftTableImpl.getInstance().getCurrentClosedShiftTable(LibConfig.activeLoginBean.getSystem_book_code(),
                LibConfig.activeLoginBean.getBranch_num(), ShiftTableImpl.getInstance().getCurrentBizday(LibConfig.activeLoginBean.getSystem_book_code(),
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
        LibConfig.activeAppUser = AppUserImpl.getInstance().login
                (LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.activeLoginBean.getBranch_num(), userCode);
        return LibConfig.activeAppUser;

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


    /**
     * 更新某个支付
     *
     * @param payment
     */
    public void updatePaymentBalanceStatus(Payment payment) {
        OrderImpl.getInstance().updatePaymentBalanceStatus(payment);
    }

    /**
     * 开启消费券分摊
     *
     * @param posOrder
     */
    public PosOrder createQuitPosOrder2ByAll(PosOrder posOrder, PosOrder oldPosOrder, KeyGeneratorBizday mCurrentPosOrderKGB) {
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
    public int checkUserPermission(String permissionName, String operatorName) {
        return checkUserPermission(LibConfig.activeAppUser, permissionName, operatorName);
    }

    /**
     * 查询本班次的收银员是否拥有某个权限某种操作的操作
     *
     * @param permissionName 权限名称
     * @param operatorName   操作名称
     * @return result : 1 => 拥有该权限  2 => 可以通过其他人授权 3 => 无法操作
     */
    public int checkUserPermission(AppUser appUser, String permissionName, String operatorName) {
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
            posItems = PosItemImpl.getInstance().getPosItemByPinyin(
                    LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.activeLoginBean.getBranch_num(), searchText.toUpperCase());

            if(RetailPosManager.sPosType == PosTypeEnum.MOBILE_POS){
                List<PosItem> newPosItems = new ArrayList<>();
                for (PosItem posItem : posItems) {
                    if (posItem.getItem_type() == 10) {
                        List<PosItemGrade> allItemGrade = getAllItemGrade(posItems.get(0).getItem_num());
                        if (allItemGrade == null || allItemGrade.size() == 0) {
                            continue;
                        }
                        for (PosItemGrade posItemGrade : allItemGrade) {
                            PosItem copyPosItem = RetailPosManager.getInstance().copyPosItem(posItem);
                            copyPosItem.setShowPosItemGrade(posItemGrade);
                            newPosItems.add(copyPosItem);
                        }
                        continue;
                    }
                    newPosItems.add(posItem);
                }
            }
        } else {
            if (posOrderDetails != null && WeightOutBarUtil.isWeightOutBarGoods(searchText)) {
                PosOrderDetail posOrderDetail = WeightOutBarUtil.getWeightOutBarGoods(searchText, posOrderDetails);
                if (posOrderDetail == null) {
                    callBack.searchNoUseGoods("当前没有搜索到结果!");
                    return false;
                } else {
                    if(posOrderDetail.getPosItem() == null){
                        callBack.resetText();
                        callBack.hideSoftInput();
                        return false;
                    }
                    callBack.searchOutBarGoods(posOrderDetail);
                    callBack.resetText();
                    callBack.hideSoftInput();
                    return false;
                }
            } else {
                posItems = PosItemImpl.getInstance().getPosItemByItemBarCode(LibConfig.activeLoginBean.getBranch_num(), searchText.toUpperCase());
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
                    List<PosItem> posItemByKitList = PosItemImpl.getInstance().getPosItemByKitNum(posItems.get(0).getItem_num());
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
                    List<PosItemGrade> allItemGrade = PosItemImpl.getInstance().getAllItemGrade(posItems.get(0).getItem_num());
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
                        List<PosItemGrade> allItemGrade = PosItemImpl.getInstance().getAllItemGrade(posItem.getItem_num());
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

        List<PosItemGrade> posItemGrades = PosItemImpl.getInstance().getAllGradeByItemNum(posItem.getItem_num());
        if (posItemGrades == null || posItemGrades.size() == 0) return serachList;

        for (PosItemGrade posItemGrade : posItemGrades) {
            PosItem clonePosItem = RetailPosManager.getInstance().copyPosItem(posItem);
            clonePosItem.setShowPosItemGrade(posItemGrade);
            serachList.add(clonePosItem);
        }
        return serachList;
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
        return CardTypeParamImpl.getInstance().getCardType(typeCode);
    }

//    //老式
//    public VipCardTypeBean getVipCardTypeBean(String type_name) {
//        return BookResourceImpl.getInstance().getVipCardTypeBeanList(LibConfig.activeShiftTable.getSystemBookCode(), LibConfig.S_LOCAL_VIP_TYPE, type_name);
//    }



    /**
     * 是否开启全渠道
     *
     * @return
     */
    public static boolean isOpenCrm() {
        return BookResourceImpl.getInstance().isOpenCrm();
    }

    public static VipCrmAmaLevel getVipLevel(String id) {
        if (id == null) return null;
        return VipCrmAmaLevelImpl.getInstance().getVipCrmAmaLevelById(id);
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
        BookResource bookPosSale = BookResourceImpl.getInstance().getBookPosSale(LibConfig.activeLoginBean.getSystem_book_code(), LibConfig.S_LOCAL_CLIENT_STYLE);
        return XmlUtil.getClientParams(bookPosSale.getBookResourceParam());
    }


    //计算分摊券
    public void calculateShareCoupons(List<PosOrderDetail> posOrderDetails, List<CouponsBean> couponsBeans) {
        if (couponsBeans == null || couponsBeans.isEmpty()) {
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
                    if ("消费折扣券".equals(couponsBean.getTicket_category())) {
                        if (posOrderDetail.getOrderDetailPrice() < posOrderDetail.getOrderDetailStdPrice() * couponsBean.getTicket_send_discount()) {
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
     *
     * @param vipUserInfo
     * @param redisNo
     * @return
     */
    public RedisBean createPolicyRedisBean(VipUserInfo vipUserInfo, String redisNo) {
        if (vipUserInfo == null) return null;
        RedisBean redisBean = new RedisBean();
        if (!TextUtils.isEmpty(vipUserInfo.getCustomer_id())) {

            VipCardConfig vipConfig = RetailPosManager.getInstance().getVipConfig(LibConfig.SYSTEM_BOOK);
            if (RetailPosManager.isOpenCrm() && vipConfig != null) {
                boolean isEnablePayDiscount = vipConfig.isEnableCardPayDiscount();//是否开启卡支付折扣参数
                boolean isCustomerDiscountType = vipConfig.isCustomerDiscountType();//是否身份等级
                if(!isEnablePayDiscount && !isCustomerDiscountType && !TextUtils.isEmpty(vipUserInfo.getCard_user_num())){
                    redisBean.setRedis_key("PolicyPromotion_" + redisNo + "_" + vipUserInfo.getCard_user_num());
                    redisBean.setRedis_time(TimeUtil.getSubTime(TimeUtil.getInstance().getNowDateString(), ""));
                    redisBean.setRedis_value("YES");
                    redisBean.setVip_id(vipUserInfo.getCard_user_num());
                    return redisBean;
                }
            }
            redisBean.setRedis_key("PolicyPromotion_" + redisNo + "_" + vipUserInfo.getCustomer_id());
            redisBean.setRedis_time(TimeUtil.getSubTime(TimeUtil.getInstance().getNowDateString(), ""));
            redisBean.setRedis_value("YES");
            redisBean.setVip_id(vipUserInfo.getCustomer_id());

        } else {
            redisBean.setRedis_key("PolicyPromotion_" + redisNo + "_" + vipUserInfo.getCard_user_num());
            redisBean.setRedis_time(TimeUtil.getSubTime(TimeUtil.getInstance().getNowDateString(), ""));
            redisBean.setRedis_value("YES");
            redisBean.setVip_id(vipUserInfo.getCard_user_num());
        }
        return redisBean;
    }


    /**
     * 获取班次的资金信息
     *
     * @param systemBookCode
     * @param shiftTableNum
     * @param branchNum
     * @return
     */
    public ShiftTableTotal getShiftTableInfo(String systemBookCode, String shiftTableNum, String branchNum, ShiftTable shiftTable) {
        ShiftTableTotal printShiftTable = new ShiftTableTotal();
        List<AmountPay> paymentList = new ArrayList<>();

        printShiftTable.real_cash_receive = shiftTable.getShift_input_cash() == null ? 0 : shiftTable.getShift_input_cash();

        String strBizDay = shiftTable.getShiftTableBizday();

        int strStartCardNum = OrderImpl.getInstance().getStartCardNum(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");

        String strStrangeSuccessSendNum = OrderImpl.getInstance().getStrangeSuccessSendNum(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "") + "";
        float strStrangeSuccessSendMoney = OrderImpl.getInstance().getStrangeSuccessSendMoney(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");

        int strCouponsNum = OrderImpl.getInstance().getTicketSendDetailNum(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");
        float strCouponsMoney = OrderImpl.getInstance().getTicketSendMoney(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "", 5);
        Map map = OrderImpl.getInstance().getOrderPromotionDiscountMoney(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday(), 5);
        float strCouponsMoney01 = (float) map.get(LibConfig.TICKET_TAG);
//                OrderService.getInstance().getTicketSendMoney01(shiftTable.getSystemBookCode(),
//                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "", 5);
        float exitMoney = OrderImpl.getInstance().getExitOrderMoney(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday(), 5);
        int exitNum = OrderImpl.getInstance().getExitOrderNum(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday(), 5);

        int strSingle = OrderImpl.getInstance().PosOrderNum(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday(), 5);

        float strDiscountAmount = (float) map.get(LibConfig.MGR_TAG);
//                OrderService.getInstance().getPosOrderDiscountAmount(shiftTable.getSystemBookCode(),
//                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
//                shiftTable.getShiftTableBizday(), 5);

        float strRoundAmount = OrderImpl.getInstance().getPosOrderRoundMoney(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday(), 5);
        int numShiftTableNum = CustomerRegisterImpl.getInstance().getNumShiftTableNum(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");
        float moneyShiftTableNum = CustomerRegisterImpl.getInstance().getMoneyShiftTableNum(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");
        float strAmount = OrderImpl.getInstance().getPosOrderAmount(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday(), 5);

        float strAllAmount = OrderImpl.getInstance().getPosOrderAllAmount(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday(), 5);
//        strAllAmount=


        int StrangeCardNum = OrderImpl.getInstance().getDepositNum
                (shiftTable.getSystemBookCode(), shiftTable.getShiftTableNum() + "",
                        shiftTable.getBranchNum() + "");
        int ReplaceCardNum = OrderImpl.getInstance().getReplaceCardNum(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", String.valueOf(shiftTable.getBranchNum()));
        int RenewCardNum = OrderImpl.getInstance().getStrangeCardNum(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");
        double StrangeCardMoney = OrderImpl.getInstance().getDepositMoney(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");
        double ReplaceCardMoney = OrderImpl.getInstance().getReplaceCardMoney(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");
        double RenewCardMoney = OrderImpl.getInstance().getRenewCardMoney(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");

        int changeNum = OrderImpl.getInstance().getChangeNum(shiftTable.getSystemBookCode(),
                shiftTable.getShiftTableNum() + "", shiftTable.getBranchNum() + "");
        double changeMoney = OrderImpl.getInstance().getChangeMoney(shiftTable.getSystemBookCode(),
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
        float noDiscountMoney = OrderImpl.getInstance().getOrderNoDiscountMoney(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday(), 5);

        float feeNum = (float) map.get(LibConfig.S_ORDER_DETAIL_PRESENT_NAME);
//                OrderService.getInstance().getFeeMoney(shiftTable.getSystemBookCode(),
//                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
//                shiftTable.getShiftTableBizday(), 5);

        float sellAllMoney = noDiscountMoney - promotionDiscountMoney - modifyPriceDiscountMoney - strRoundAmount -
                vipDiscountMoney - strCouponsMoney - strDiscountAmount - feeNum;

        List<Payment> shfitOtherPayment = OtherRevenueImpl.getInstance()
                .getShfitOtherPayment(shiftTable.getSystemBookCode(),
                        shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                        shiftTable.getShiftTableBizday());

        int othersNum = shfitOtherPayment.size();
        float othersMoney = 0;

        for (Payment payment : shfitOtherPayment){
            othersMoney += payment.getPaymentMoney();
        }

        float collectionMoney = OrderImpl.getInstance().getAmountOfCollection(shiftTable.getSystemBookCode(),
                shiftTable.getBranchNum(), shiftTable.getShiftTableNum(),
                shiftTable.getShiftTableBizday());
        float floatAllReceiveMoney = 0;

        try {
            paymentList = OrderImpl.getInstance().getListPaymentByPayType(shiftTable.getSystemBookCode(),
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
        printShiftTable.cardChangeNum = changeNum;
        printShiftTable.cardChangeMoney = (float) changeMoney;
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
     *
     * @param inventories
     */
    public void saveGoodsInventory(List<Inventory> inventories) {
        LibConfig.sInventoryList = new InventoryImpl().saveGoodsInventoryList(inventories);
    }


    public ItemCategory getItemCategoryByCode(String itemCategoryCode) {
        return ItemCategoryImpl.findCategoryCode(itemCategoryCode);
    }

    /**
     * 合并相同商品相同价格
     *
     * @param posOrder
     */
    public void mergeAllGoods(PosOrder posOrder) {
        OrderOperationImpl.getInstance().mergeAllGoods(posOrder);
    }


    public boolean checkTodayLoading(){
        String strDate = TimeUtil.getInstance().getCurrDateSelfType(TimeUtil.LONG_DATE_FORMAT);
        return strDate.equals(UserDao.getLoginDate());
    }

    /********************************新添加Api**************************************/

    /**
     * 是否是IC卡帐套号
     * @return
     */
    public boolean isIcCard(){
        if (LibConfig.activeIcCardMessage != null && LibConfig.activeIcCardMessage.getBranch_iC()) {
            return true;
        }
        return false;
    }

    /*********************************AccountBankImpl*********************************************/
    /**
     * 保存银行账户
     *
     * @param accountBankList 银行账户集合
     * @return 是否保存成功
     */
    public boolean saveAccountBankList(final List<AccountBank> accountBankList) {
        return AccountBankImpl.getInstance().saveAccountBankList(accountBankList);
    }

    public AccountBank getAccountBank(){
        return AccountBankImpl.getInstance().getAccountBankNum();
    }
    /*********************************AddVipDialogImpl*********************************************/

    /*********************************AdjustPriceImpl*********************************************/

    /*********************************AggregationImpl*********************************************/
    /**
     * 保存聚合支付信息
     *
     * @param aggregation 聚合支付信息
     * @return 是否保存成功
     */
    public boolean saveAggregation(Aggregation aggregation) {
        return AggregationImpl.getInstance().saveAggregation(aggregation);
    }


    /**
     * 获取线上支付配置
     * @return
     */
    public Aggregation getOnlinePaySetting(){
        return AggregationImpl.getInstance().getAggreation();
    }

    /*********************************AmountPayImpl*********************************************/

    /*********************************AppUserImpl*********************************************/

    /**
     * 保存用户列表
     *
     * @param appUserList 用户集合
     * @return 是否保存成功
     */
    public boolean saveAppUserList(final List<AppUser> appUserList) {
        return AppUserImpl.getInstance().saveAppUserList(appUserList);
    }


    /*********************************AttachedScreenImpl*********************************************/
    /**
     * 保存副屏图片
     *
     * @param attachedScreenList 副屏图片集合
     * @return 是否保存成功
     */
    public boolean saveAttachedScreenList(final List<AttachedScreen> attachedScreenList) {
        return AttachedScreenImpl.getInstance().saveAttachedScreenList(attachedScreenList);
    }
    /*********************************BookResourceImpl*********************************************/

    /**
     * 获取全局储值卡配置
     *
     * @param book_code 帐套号
     * @return 储值卡配置
     */
    public VipCardConfig getVipConfig(String book_code) {
        return BookResourceImpl.getInstance().getVipCardConfig(book_code);
    }

    /**
     * 保存帐套资源
     *
     * @param bookResourceList 帐套资源集合
     * @return 是否保存成功
     */
    public boolean saveBookResourceList(final List<BookResource> bookResourceList) {
        return BookResourceImpl.getInstance().saveBookResourceList(bookResourceList);
    }


    /**
     *
     * @param systemCode
     * @param payScale
     * @return
     */
    public BookResource getBookPosSale(String systemCode, String payScale) {
        return BookResourceImpl.getInstance().getBookPosSale(systemCode, payScale);
    }


    /*********************************BottomMenuImpl*********************************************/

    /*********************************BranchGroupImpl*********************************************/

    /**
     * 保存门店分组信息
     *
     * @param branchGroupList 门店分组信息集合
     * @return 是否保存成功
     */
    public boolean saveBranchGroupList(final List<BranchGroup> branchGroupList) {
        return BranchGroupImpl.getInstance().saveBranchGroupList(branchGroupList);
    }
    /*********************************BranchImpl*********************************************/

    /**
     * 保存所有分店的信息
     *
     * @param branchList
     * @return 是否保存成功
     */
    public boolean saveBranchList(final List<Branch> branchList) {
        return BranchImpl.getInstance().saveBranchList(branchList);
    }

    /**
     * 获取门店经营范围的编号
     *
     * @param branch_num
     * @return
     */
    public Long getBranchTempleteNum(long branch_num) {
        return BranchImpl.getInstance().getBranchTempleteNum(branch_num);
    }


    /**
     * 获取对应门店对象
     * @param book_code 帐套号
     * @param branch_num 门店编号
     * @return
     */
    public Branch getBranchByNum(String book_code, int branch_num){
        return BranchImpl.getInstance().getBranchByNum(book_code, branch_num);
    }

    public String checkBranch(){
        BookResource bookPosCardType = BookResourceImpl.getInstance().getBookPosSale(LibConfig.SYSTEM_BOOK, "POS终端登录验证");
        if (bookPosCardType != null) {
            String s = bookPosCardType.getBookResourceParam();
            if (!"FALSE".equals(s)) {
                if (!LibConfig.activePosMachine.getPos_machine_enabled()) {
                    return "当前终端未授权，请联系总部管理员。";
                }
            }
        }

        if (LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_actived() != null && !LibConfig.activeBranch.getBranch_actived()) {
          return "门店目前为未启用状态，启用门店请联系管理员。";
        }

        if (LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_status() == 1) {
            return "该门店已闭店，请联系管理员。";
        }
        return "";
    }

    /**
     *  检查用户输入信息
     * @param user_name  用户名
     * @param user_psw  用户密码
     * @return 0 用户名为空 1 密码为空  2 校验通过
     */
    public int checkUserPsw(String user_name,String user_psw){
        if (TextUtils.isEmpty(user_name)) {
            return 0;
        }
        if (TextUtils.isEmpty(user_psw)) {
            return 1;
        }
        return 2;
    }



    /*********************************BranchMessageImpl*********************************************/

    /*********************************BranchRegionImpl*********************************************/

    /**
     * 保存所有分店区域
     *
     * @param branchRegionList 分店区域集合
     * @return 是否保存成功
     */
    public boolean saveBranchRegionList(final List<BranchRegion> branchRegionList) {
        return BranchRegionImpl.getInstance().saveBranchRegionList(branchRegionList);
    }

    /*********************************BranchResourceImpl*********************************************/
    /**
     * 保存门店资源
     *
     * @param branchResourceList 门店资源集合
     * @return 是否保存成功
     */
    public boolean saveBranchResourceList(final List<BranchResource> branchResourceList) {
        return BranchResourceImpl.getInstance().saveBranchResourceList(branchResourceList);
    }

    /**
     *获取卡类型信息没有去除没启用的卡类型
     * @param type_name  卡类型名称
     * @return
     */
    public VipCardTypeBean  getVipCardTypeBean(String type_name){
        return BookResourceImpl.getInstance().getVipCardTypeBeanList(LibConfig.SYSTEM_BOOK, LibConfig.S_LOCAL_VIP_TYPE,type_name);
    }

    /**
     * 获取所有的卡类型
     * @return
     */
    public List<VipCardTypeBean>  getAllVipCardTypeList(String book_code,String pay_scale,int branch_num){
        return BookResourceImpl.getInstance().getVipCardTypeBeanList(book_code, pay_scale,branch_num);
    }

    /**
     * 获取所有的卡类型名称
     * @return
     */
    public List<String>  getAllVipCardTypeNameList(String book_code,String pay_scale,int branch_num){
        return BookResourceImpl.getInstance().getVipCardTypeBeanNameList(book_code, pay_scale,branch_num);
    }


    /**
     * 获取门店卡存款配置
     * @param branch_num
     * @return
     */
    public CardBalaceConfig getCardBalanceConfig(int branch_num){
        return BranchResourceImpl.getInstance().getCardBalaceConfig(branch_num);
    }

    /*********************************CardChangeImpl*********************************************/

    /*********************************CardTypeParamImpl*********************************************/

    /**
     * 保存卡类型参数
     *
     * @param cardTypeParamList 卡类型参数集合
     * @return 是否保存成功
     */
    public boolean saveCardTypeParamList(final List<CardTypeParam> cardTypeParamList) {
        return CardTypeParamImpl.getInstance().saveCardTypeParamList(cardTypeParamList);
    }

    /*********************************ChangeGoodsMenuImpl*********************************************/

    /*********************************CheckPermissionImpl*********************************************/

    /*********************************ClearDataImpl*********************************************/

    /*********************************ClientPointImpl*********************************************/


    /**
     * 更改积分上传的状态
     *
     * @param clientPoint 积分对象
     */
    public void updateClientPointStatus(final ClientPoint clientPoint) {
        ClientPointImpl.getInstance().updateClientPointStatus(clientPoint);
    }

    /**
     * 批量更改积分上传的状态
     *
     * @param clientPointList 积分对象集合
     */
    public void updateClientPointStatus(final List<ClientPoint> clientPointList) {
        ClientPointImpl.getInstance().updateClientPointStatus(clientPointList);
    }


    /**
     * 获取该标记赠送积分对象
     *
     * @param book_code  帐套号
     * @param branch_num 门店号
     * @param sycnFlag   同步标记
     * @return 该标记赠送积分对象
     */
    public List<ClientPoint> getClientPointList(String book_code, int branch_num, boolean sycnFlag) {
        return ClientPointImpl.getInstance().getClientPointList(book_code, branch_num, sycnFlag);
    }


    /**
     * 计算积分
     * @param posOrder 订单信息
     * @param vipUserInfo 会员信息
     * @param storeMoney 储值金额
     * @param uploadType  积分上传类型
     * @return
     */
    public ClientPoint calcuatePoint(PosOrder posOrder, VipUserInfo vipUserInfo, float storeMoney, String uploadType) {
        return PointCalUtils.getInstance().calcuatePoint(posOrder, vipUserInfo, storeMoney, uploadType);
    }


    /*********************************CurrentUserImpl*********************************************/

    /*********************************CustomerRegisterImpl*********************************************/

    /*********************************DataSynchronousImpl*********************************************/

    /*********************************DeskOperatingParametersImpl*********************************************/

    /*********************************EmployeeImpl*********************************************/


    /**
     * 保存所有的员工
     *
     * @param employeeList 员工集合
     * @return 是否保存成功
     */
    public boolean saveEmployeeList(final List<Employee> employeeList) {
        return EmployeeImpl.getInstance().saveEmployeeList(employeeList);
    }
    /*********************************GlobalDataImpl*********************************************/

    /*********************************GoodsDataImpl*********************************************/

    /*********************************IcCardMessageImpl*********************************************/

    /*********************************InventoryImpl*********************************************/

    /*********************************ItemBarImpl*********************************************/

    /*********************************ItemCategoryImpl*********************************************/

    /**
     * 保存所有的商品类别
     *
     * @param itemCategoryList 商品类别集合
     * @return 是否保存成功
     */
    public boolean saveItemCategoryList(final List<ItemCategory> itemCategoryList) {
        return ItemCategoryImpl.getInstance().saveItemCategoryList(itemCategoryList);
    }


    /**
     * 给每个类别设置等级，便于后面展示
     */
    public void setHierarchyCategory(){
        ItemCategoryImpl.getInstance().setHierarchyCategory();
    }

    /*********************************KeyGeneratorBizdayImpl*********************************************/

    /**
     * 保存赠送消费券最大主键
     *
     * @param system_book        帐套号
     * @param branch_num         门店编号
     * @param shift_table_bizday 流水日
     * @param max_print_num      最大券的流水号
     * @return 是否保存成功
     */
    public boolean saveMaxCouponsPrintNum(String system_book, int branch_num, String shift_table_bizday, final int max_print_num) {
        return KeyGeneratorBizdayImpl.getInstance().saveMaxCouponsPrintNum(system_book, branch_num, shift_table_bizday, max_print_num);
    }


    /**
     * 获取今天的营业日
     *
     * @return
     */
    public String getTodayShiftBizday() {
        return KeyGeneratorBizdayImpl.getInstance().getTodayShiftBizday();
    }

    /**
     * 保存posorder的最大主键
     *
     * @param system_book        帐套号
     * @param branch_num         门店编号
     * @param shift_table_bizday 流水日
     * @param pos_key_value      订单的最大主键
     * @param payment_key_value  支付的最大主键
     * @return 是否保存成功
     */
    public boolean setMaxPosOrderAndPaymentValue(String system_book, int branch_num, String shift_table_bizday,
                                                 int pos_key_value, final int payment_key_value) {
        return KeyGeneratorBizdayImpl.getInstance().setMaxPosOrderAndPaymentValue(system_book, branch_num, shift_table_bizday, pos_key_value, payment_key_value);
    }

    /**
     * 保存储值的最大主键
     *
     * @param system_book        帐套号
     * @param branch_num         门店编号
     * @param shift_table_bizday 流水日
     * @param deposit_key_value  储值的最大主键
     * @return
     */
    public boolean setMaxDepositValue(String system_book, int branch_num,
                                      String shift_table_bizday, int deposit_key_value) {
        return KeyGeneratorBizdayImpl.getInstance().setMaxDepositValue(system_book, branch_num, shift_table_bizday, deposit_key_value);
    }

    /**
     * 获取当天的流水日是今年的第几天
     *
     * @param shift_table_bizday 流水日
     * @return
     */
    public String getYearCurrentDate(String shift_table_bizday) {
        return KeyGeneratorBizdayImpl.getInstance().getYearCurrentDate(shift_table_bizday);
    }


    /**
     * 获取消费券前缀
     *
     * @param system_book          帐套号
     * @param branch_num           门店号
     * @param shift_table_bizday   营业日
     * @param pos_machine_sequence pos_machine的终端顺序
     * @return
     */
    public String getCouponsPre(String system_book, int branch_num, String shift_table_bizday, int pos_machine_sequence) {
        return KeyGeneratorBizdayImpl.getInstance().getCouponsPre(system_book, branch_num, shift_table_bizday, pos_machine_sequence);
    }


    /**
     * 创建一个销售订单号
     * @param book_code 帐套号
     * @param branch_num 门店编号
     * @param shift_table_bizday 营业日
     * @param key_item 订单类型
     * @param pos_machine_seq 终端的序列号
     * @return
     */
    public KeyGeneratorBizday createPosOrderKG(String book_code,int branch_num,String shift_table_bizday,String key_item,int pos_machine_seq){
        return KeyGeneratorBizdayImpl.getInstance().createPosOrderKG(book_code, branch_num, shift_table_bizday, key_item,pos_machine_seq);
    }


    /**
     * 创建一个支付方式订单号
     * @param book_code 帐套号
     * @param branch_num 门店编号
     * @param shift_table_bizday 营业日
     * @param key_item 订单类型
     * @param pos_machine_seq 终端的序列号
     * @return
     */
    public KeyGeneratorBizday createPaymentKG(String book_code,int branch_num,String shift_table_bizday,String key_item,int pos_machine_seq){
        return KeyGeneratorBizdayImpl.getInstance().createPaymentKG(book_code, branch_num, shift_table_bizday, key_item,pos_machine_seq);
    }




    /**
     * 创建一个储值订单号
     * @param book_code 帐套号
     * @param branch_num 门店编号
     * @param shift_table_bizday 营业日
     * @param key_item 订单类型
     * @param pos_machine_seq 终端的序列号
     * @return
     */
    public KeyGeneratorBizday createDepositKG(String book_code, int branch_num, String shift_table_bizday, String keyItem, int pos_machine_seq) {
        return KeyGeneratorBizdayImpl.getInstance().createDepositKG(book_code, branch_num, shift_table_bizday, keyItem, pos_machine_seq);
    }
    /**
     * 保存订单的流水号
     * @param keyGeneratorBizday
     */
    public void saveKeyGeneratorBizday(KeyGeneratorBizday keyGeneratorBizday){
        KeyGeneratorBizdayImpl.getInstance().saveKeyGeneratorBizday(keyGeneratorBizday);
    }


    /**
     * 获取在线支付的子商务号
     * @param systemCode
     * @param branchNum
     * @return
     */
    public String getPayOutId(String systemCode, int branchNum) {
        return KeyGeneratorBizdayImpl.getInstance().getPayOutId(systemCode, branchNum);
    }
    /*********************************LoginImpl*********************************************/

    /**
     * 获取登录信息
     *
     * @return 返回登录信息
     */
    public Login getLoginData() {
        return LoginImpl.getInstance().getLogin();
    }


    /**
     * 保存登录信息
     *
     * @return 返回登录信息
     */
    public boolean saveLoginData(Login login) {
        return LoginImpl.getInstance().saveLogin(login);
    }


    /*********************************ManagementTemplateImpl*********************************************/


    /**
     * 保存经营范围
     *
     * @param management_template 经营范围
     * @return 是否保存成功
     */
    public boolean saveManagementTemplate(final ManagementTemplate management_template) {
        return ManagementTemplateImpl.getInstance().saveManagementTemplate(management_template);
    }

    /**
     * 获取经营范围上一次编辑时间
     *
     * @return
     */
    public String geTemplateLastEditTime() {
        return ManagementTemplateImpl.getInstance().geTemplateLastEditTime();
    }

    /*********************************MarketActionImpl*********************************************/

    /**
     * 保存营销活动
     *
     * @param marketActionList 营销集合
     * @return 是否保存成功
     */
    public boolean saveMarketAction(final List<MarketAction> marketActionList) {
        return MarketActionImpl.getInstance().saveMarketAction(marketActionList);
    }


    /**
     *  获取消费赠送的消费券
     * @param posOrder
     * @param shiftTable
     * @param marketActions
     * @param vipUserInfo
     * @return
     */
    public List<TicketSendDetail> sendTicketByPosOrder(PosOrder posOrder, ShiftTable
            shiftTable, List<MarketAction> marketActions, VipUserInfo vipUserInfo) {
        return MarketActionImpl.getInstance().sendCouponsByPosOrder(posOrder, shiftTable, marketActions, vipUserInfo);
    }

    /**
     * 获取储值赠送的消费券
     * @param orderNo
     * @param shiftTable
     * @param marketActions
     * @param vipUserInfo
     * @param paymentBy
     * @param paymentMoney
     * @return
     */
    public List<TicketSendDetail> sendCouponsByDeposit(String orderNo, ShiftTable shiftTable, List<MarketAction> marketActions, VipUserInfo vipUserInfo, String paymentBy, float paymentMoney) {
        return MarketActionImpl.getInstance().sendCouponsByDeposit(orderNo, shiftTable, marketActions, vipUserInfo, paymentBy, paymentMoney);
    }

    /**
     * 获取开卡赠送的消费券
     * @param orderNo
     * @param shiftTable
     * @param marketActions
     * @param vipUserInfo
     * @return
     */
    public List<TicketSendDetail> sendCouponsByOpenCard(String orderNo, ShiftTable shiftTable, List<MarketAction> marketActions, VipUserInfo vipUserInfo) {
        return  MarketActionImpl.getInstance().sendCouponsByOpenCard(orderNo, shiftTable, marketActions, vipUserInfo);
    }

    /**
     * 储值兑换券
     * @param shiftTable
     * @param vipUserInfo
     * @param orderNo
     * @param paymentmoney
     * @return
     */
    public TicketSendDetail createStrange2TicketModel(ShiftTable shiftTable, VipUserInfo vipUserInfo, String orderNo,
                                                             float paymentmoney) {
        return MarketActionImpl.createStrange2TicketModel(shiftTable, vipUserInfo, orderNo, paymentmoney);
    }
    /*********************************MD5Impl*********************************************/

    /*********************************MeasureUnitImpl*********************************************/

    /**
     * 保存商品单位集合
     *
     * @param measureUnitList 商品单位集合
     * @return 是否保存成功
     */
    public boolean saveMeasureUnit(final List<MeasureUnit> measureUnitList) {
        return MeasureUnitImpl.getInstance().saveMeasureUnitList(measureUnitList);
    }

    /*********************************OrderImpl*********************************************/

    /**
     * 订单上传成功后将posorder订单上传状态修改掉
     *
     * @param order_num 订单的编号
     */
    public void changeOrderUploadStatus(String order_num) {
        OrderImpl.getInstance().changeOrderUploadStatus(order_num);
    }

    /**
     * 获取订单的JSON
     *
     * @param posOrder 订单
     * @return 订单JSON
     */
    public String getPosOrderToJson(PosOrder posOrder, String branch_name, String system_book_name) {
        VipCardConfig vipCardConfig = getVipConfig(LibConfig.SYSTEM_BOOK);
        return OrderImpl.getInstance().getPosOrderToJson(posOrder, branch_name, system_book_name, vipCardConfig);
    }

    /**
     * 获取一组订单的JSON
     *
     * @param posOrderList 订单集合
     * @return 返回一组订单的JSON
     */
    public String getPosOrderListToJson(List<PosOrder> posOrderList) {
        return OrderImpl.getInstance().getPosOrderListToJson(posOrderList);
    }


    /**
     * 获取该班次号下所有该标记posOrder
     *
     * @param book_code          帐套号
     * @param branch_num         门店
     * @param shift_table_num    班次
     * @param shift_table_bizday 营业日
     * @param sycn_flag          同步标记
     * @return 班次号下所有该标记posOrder
     */
    public List<PosOrder> getPosOrderList(String book_code, int branch_num, int shift_table_num, String shift_table_bizday, boolean sycn_flag) {
        return OrderImpl.getInstance().getAllCompleteOrderList(book_code, branch_num, shift_table_num, shift_table_bizday, sycn_flag);
    }


    /**
     * 检查订单数据是否正常
     * @param posOrder 订单信息
     * @return true 正常
     */
    public boolean checkPosOrder(PosOrder posOrder){
        return OrderImpl.getInstance().checkPosOrder(posOrder);
    }


    /**
     * 更新订单商品状态
     * @param posOrderDetail
     */
    public void updatePosOrderDetailStatus(PosOrderDetail posOrderDetail) {
        OrderImpl.getInstance().updatePosOrderDetailStatus(posOrderDetail);
    }

    /**
     * 本地数据库保存订单
     * @param posOrder
     * @return
     */
    public boolean savePosOrder(final PosOrder posOrder) {
        return OrderImpl.getInstance().savePosOrder(posOrder);
    }


    /**
     * 获取所有完成的订单
     * @param systemBookCode
     * @param branchNum
     * @return
     */
    public List<PosOrder> getAllCompleteOrderList(String systemBookCode, int branchNum) {
        return OrderImpl.getInstance().getAllCompleteOrderList(systemBookCode, branchNum);
    }

    /**
     * 根据订单号来查询订单
     * @param orderNo
     * @return
     */
    public PosOrder getOrderByNum(String orderNo) {
        return OrderImpl.getInstance().getOrderByNum(orderNo);
    }

    /**
     * 检查订单是否能退货
     * @param order_no
     * @return
     */
    public boolean checkOrderCanQuit(String  order_no) {
        return OrderImpl.getInstance().checkOrderCanQuit(order_no);
    }

    public List<PosOrderDetail> getPosOrderDetailList(String order_no){
        return OrderImpl.getInstance().getPosOrderDetailList(order_no);
    }

    public List<PosOrderDetail> getPosOrderDetailList(String book_code ,int branch_num,String order_no){
        return OrderImpl.getInstance().getPosOrderDetailList(book_code, branch_num, order_no);
    }
    /*********************************OrderOperationImpl*********************************************/

    /*********************************OtherRevenueImpl*********************************************/

    /*********************************PaymentImpl*********************************************/

    /*********************************PayStyleImpl*********************************************/

    /**
     *  储值赠送金额
     * @param systemBookCode
     * @param payMoney
     * @param branchNum
     * @param cardType
     * @param payType
     * @return
     */
    public double strangeGivingMoney(String systemBookCode, String payMoney, String branchNum, String cardType, String payType) {
        return PayStyleImpl.getInstance().strangeGivingMoney(systemBookCode, payMoney, branchNum, cardType, payType);
    }

    /**
     *
     * @param list
     * @return
     */
    public List<String> getPayTypeNameList(List<PosScaleStyleTypeBean> list) {
        return PayStyleImpl.getInstance().getPayTypeNameList(list);
    }

    /**
     *
     * @param list
     * @return
     */
    public List<PosScaleStyleTypeBean> getPayTypeList(List<PosScaleStyleTypeBean> list) {
        return PayStyleImpl.getInstance().getPayTypeList(list);
    }




    /*********************************PayStyleToCashBankImpl*********************************************/

    /*********************************PointOrderImpl*********************************************/
    /**
     * 保存积分活动
     *
     * @param pointOrderList 积分活动集合
     * @return 是否保存成功
     */

    public boolean savePointOrderList(final List<PointOrder> pointOrderList) {
        return PointOrderImpl.getInstance().savePointOrderList(pointOrderList);
    }

    /*********************************PointPolicyImpl*********************************************/
    /**
     * 保存积分政策
     *
     * @param pointPolicyList 积分政策集合
     * @return 是否保存成功
     */
    public boolean savePointPolicyList(final List<PointPolicy> pointPolicyList) {
        return PointPolicyImpl.getInstance().savePointPolicyList(pointPolicyList);
    }

    /**
     * 获取所有积分政策
     *
     * @param book_code 帐套号
     * @return 返回全部的积分政策
     */
    public List<PointPolicy> getAllPointPolicy(String book_code) {
        return PointPolicyImpl.getInstance().loadAllPointPolicy(book_code);
    }


    /*********************************PointRuleImpl*********************************************/

    /*********************************PolicyDiscountImpl*********************************************/

    /**
     * 保存所有的超额折扣
     *
     * @param policyDiscountList 超额折扣集合
     * @return 是否保存成功
     */
    public boolean savePolicyDiscountList(final List<PolicyDiscount> policyDiscountList) {
        return PolicyDiscountImpl.getInstance().savePolicyDiscountList(policyDiscountList);
    }
    /*********************************PolicyMoneyImpl*********************************************/

    /**
     * 保存所有的超额奖励
     *
     * @param policyMoneyList 超额奖励集合
     * @return 是否保存成功
     */
    public boolean savePolicyMoneyList(final List<PolicyMoney> policyMoneyList) {
        return PolicyMoneyImpl.getInstance().savePolicyMoneyList(policyMoneyList);
    }

    /*********************************PolicyPresentImpl*********************************************/

    /**
     * 保存所有的赠品促销
     *
     * @param policyPresentList 赠品促销集合
     * @return 是否保存成功
     */
    public boolean savePolicyPresentList(final List<PolicyPresent> policyPresentList) {
        return PolicyPresentImpl.getInstance().savePolicyPresentList(policyPresentList);
    }

    /*********************************PolicyPromotionImpl*********************************************/

    /**
     * 保存所有的促销特价
     *
     * @param policyPromotionList 促销特价集合
     * @return 是否保存成功
     */
    public boolean savePolicyPromotionList(final List<PolicyPromotion> policyPromotionList) {
        return PolicyPromotionImpl.getInstance().savePolicyPromotionList(policyPromotionList);
    }

    /**
     * 是否能用会员支付折扣
     * @return
     */
    public boolean isEnablePayDiscount() {
        return PolicyPromotionImpl.getInstance().isEnablePayDiscount();
    }

    /*********************************PolicyQuantityImpl*********************************************/

    /**
     * 保存所有的超量特价
     *
     * @param policyDiscountList 超量特价集合
     * @return 是否保存成功
     */
    public boolean savePolicyQuantityList(final List<PolicyQuantity> policyDiscountList) {
        return PolicyQuantityImpl.getInstance().savePolicyQuantityList(policyDiscountList);
    }

    /*********************************PosCarryLogImpl*********************************************/

    /**
     * 批量更新异常操作日志
     *
     * @param posCarryLogList 异常操作日志集合
     */
    public void updateCarryLogStatus(List<PosCarryLog> posCarryLogList) {
        PosCarryLogImpl.getInstance().updateCarryLogStatus(posCarryLogList);
    }

    /**
     * 打开钱箱异常操作
     */
    public void tryOpenDraw() {
        PosCarryLogImpl.getInstance().tryOpenDraw();
    }

    /**
     * 挂单异常操作
     *
     * @param posOrder 订单信息
     */
    public void tryCollectionOrder(PosOrder posOrder) {
        PosCarryLogImpl.getInstance().tryCollectionOrder(posOrder);
    }

    /**
     * 撤单异常操作
     *
     * @param posOrder 订单信息
     */
    public void tryDeleteOrder(PosOrder posOrder) {
        PosCarryLogImpl.getInstance().tryDeleteOrder(posOrder);
    }

    /**
     * 退货异常操作
     *
     * @param posOrder 订单信息
     */
    public void tryQuitOrder(PosOrder posOrder) {
        PosCarryLogImpl.getInstance().tryQuitOrder(posOrder);
    }

    /**
     * 经理折扣异常操作
     *
     * @param posOrder 订单信息
     */
    public void tryOrderMgr(PosOrder posOrder) {
        PosCarryLogImpl.getInstance().tryOrderMgr(posOrder);
    }

    /**
     * @param posOrderDetail 商品信息
     */
    public void tryDeleteGoods(PosOrderDetail posOrderDetail) {
        PosCarryLogImpl.getInstance().tryDeleteGoods(posOrderDetail);
    }

    /**
     * 修改商品单价
     *
     * @param posOrderDetail 商品信息
     */
    public void tryChangeGoodsPrice(PosOrderDetail posOrderDetail,float detailPrice) {
        PosCarryLogImpl.getInstance().tryChangeGoodsPrice(posOrderDetail,detailPrice);
    }

    /**
     * 修改商品的数量
     *
     * @param posOrderDetail 商品信息
     */
    public void tryChangeGoodsAmount(PosOrderDetail posOrderDetail) {
        PosCarryLogImpl.getInstance().tryChangeGoodsAmount(posOrderDetail);
    }

    /**
     * 赠送商品
     *
     * @param posOrderDetail 商品信息
     */
    public void tryPresentGoods(PosOrderDetail posOrderDetail) {
        PosCarryLogImpl.getInstance().tryPresentGoods(posOrderDetail);
    }


    /**
     * 获取该标记的异常操作日志
     *
     * @param book_code       帐套号
     * @param branch_num      门店号
     * @param shift_table_num 班次
     * @param sycnFlag        同步标记
     * @return 该标记的异常操作日志
     */
    public List<PosCarryLog> getPosCarryLogList(String book_code, int branch_num, int shift_table_num, boolean sycnFlag) {
        return PosCarryLogImpl.getInstance().getPosCarryLogList(book_code, branch_num, shift_table_num, sycnFlag);
    }

    /**
     * 获取该标记的异常操作日志
     *
     * @param book_code  帐套号
     * @param branch_num 门店号
     * @param sycnFlag   同步标记
     * @return 该标记的异常操作日志
     */
    public List<PosCarryLog> getPosCarryLogList(String book_code, int branch_num, boolean sycnFlag) {
        return PosCarryLogImpl.getInstance().getPosCarryLogList(book_code, branch_num, sycnFlag);
    }

    /**
     * 删除多少基准日期之前的异常操作数据
     *
     * @param date 基准日期
     */
    public void deleteLogData(String date) {
        PosCarryLogImpl.getInstance().deleteLogData(date);
    }




    /*********************************PosItemGradeTerminalImpl*********************************************/

    /*********************************PosItemImpl*********************************************/

    /**
     * 保存PosItem数据
     *
     * @param posItemList PosItem 集合
     * @return 是否保存成功
     */
    public boolean savePosItemList(final List<PosItem> posItemList) {
        return PosItemImpl.getInstance().savePosItemList(posItemList);
    }

    /**
     * 获取商品档案上一次编辑的时间
     *
     * @return
     */
    public String getPosItemLastEditTime() {
        return PosItemImpl.getInstance().getPosItemLastEditTime();
    }

    public List<GoodsGradeBean> getAllShowPosItem(){
        return PosItemImpl.getInstance().getAllShowPosItem();
    }


    /**
     *  获取商品的标准价
     * @param posItem  主商品
     * @param posItemGrade  分级商品
     * @return
     */
    public float getItemRegularPrice(PosItem posItem,PosItemGrade posItemGrade){
        return PosItemImpl.getInstance().getItemRegularPrice(posItem, posItemGrade);
    }


    /**
     * 检查商品是否在消费劵支持的范围内
     *
     * @param couponsBean
     * @param posOrderDetail
     * @return true 是包含  false 不包含
     */
    public boolean isContainGoods(CouponsBean couponsBean, PosOrderDetail posOrderDetail) {
        int result = PosItemImpl.getInstance().isInGoodsList(couponsBean, posOrderDetail);
        return result == 1 ? true : false;
    }


    /**
     * 获取零售展示商品
     *
     * @return
     */
    public List<GoodsGradeBean> getRetailGoodsList() {
        return PosItemImpl.getInstance().getAllShowPosItem();
    }


    /**
     * 根据 store_item_pinyin 模糊查询
     * @param storeItemPinyin
     * @param systemBookCode
     * @param branchNum
     * @return
     */
    public List<PosItem> getPosItemByPinyin(String systemBookCode, int branchNum, String storeItemPinyin) {
        return PosItemImpl.getInstance().getPosItemByPinyin(systemBookCode, branchNum, storeItemPinyin);
    }

    /**
     * 根据 store_item_pinyin 模糊查询
     * @param item_num
     * @return
     */
    public PosItem getPosItemByItemNum(long item_num) {
        return PosItemImpl.getInstance().getPosItemByItemNum(item_num);
    }

    /**
     * 根据 store_item_pinyin 模糊查询
     * @param item_grade_num
     * @param item_num
     * @return
     */
    public PosItemGrade getPosItemGradeByItemGradeNum(int item_grade_num,long item_num) {
        return PosItemImpl.getInstance().getPosItemGradeByItemGradeNum(item_grade_num, item_num);
    }

    /**
     * 根据item_num来查找pos_item_grade数据
     *
     * @param item_num
     * @return
     */
    public List<PosItemGrade> getAllItemGrade(final long item_num) {
        return PosItemImpl.getInstance().getAllItemGrade(item_num);
    }


    public PosItem copyPosItem(PosItem posItem){
        return new Gson().fromJson(new Gson().toJson(posItem),PosItem.class);
    }

        /*********************************PosItemTerminalImpl*********************************************/

    /*********************************PosMachineImpl*********************************************/

    /**
     * 保存所有终端机器信息
     *
     * @param posMachineList 有终端机器信息 集合
     * @return 是否保存成功
     */
    public boolean savePosMachineList(final List<PosMachine> posMachineList) {
        return PosMachineImpl.getInstance().savePosMachineList(posMachineList);
    }

    /**
     * 获取mac地址
     *
     * @return
     */
    public String getMachineMac(Context context) {
        return PosMachineImpl.getInstance().getMacAddress(context);
    }

    /**
     * 获取mac地址
     *
     * @return
     */
    public String getLocalPosMachine(Context context) {
        return PosMachineImpl.getInstance().getMacAddress(context);
    }

    /**
     * 保存终端
     * @param posMachine  终端对象
     * @return 是否保存成功
     */
    public boolean savePosMachine(PosMachine posMachine){
        return PosMachineImpl.getInstance().savePosMachine(posMachine);
    }

    /**
     * @param branch_num  门店号
     * @param terminal_id 终端Mac地址
     * @return
     */
    public PosMachine getLocalPosMachine(int branch_num, String terminal_id) {
        return PosMachineImpl.getInstance().getPosMachine(branch_num, terminal_id);
    }


    /**
     * 获取最大注册的终端序列号
     * @return
     */
    public int getMaxPosMachineNum(){
        return PosMachineImpl.getInstance().getMaxPosMachineNum();
    }

    /*********************************PriceImpl*********************************************/

    /*********************************PrintOrderUsingImpl*********************************************/

    /*********************************PrivilegeResourceNewImpl*********************************************/

    /**
     * 保存权限信息
     *
     * @param privilegeResourceNewList 权限信息集合
     * @return 是否保存成功
     */
    public boolean savePrivilegeResourceList(List<PrivilegeResourceNew> privilegeResourceNewList) {
        return PrivilegeResourceNewImpl.getInstance().savePrivilegeList(privilegeResourceNewList);
    }

    /*********************************PromotionOperationImpl*********************************************/

    /*********************************RegisterPosMachineImpl*********************************************/

    /*********************************RoleImpl*********************************************/

    /**
     * 保存角色信息
     *
     * @param roleList 角色信息集合
     * @return 是否保存成功
     */
    public boolean saveSystemRoleList(List<SystemRole> roleList) {
        return RoleImpl.getInstance().saveSystemRoleList(roleList);
    }


    /*********************************ShiftTableImpl*********************************************/

    /**
     * 获取班次信息JSON
     *
     * @param shiftTable 班次
     * @return 班次信息JSON
     */
    public String getShiftTableToString(ShiftTable shiftTable) {
        return ShiftTableImpl.getInstance().getShiftTableToString(shiftTable);
    }

    /**
     * 班次是否有流水标记状态更改
     *
     * @param shiftTable 班次
     */
    public void changeStateShiftTable(ShiftTable shiftTable) {
        ShiftTableImpl.getInstance().changeStateShiftTable(shiftTable);
    }


    /**
     * 保存班次信息
     *
     * @param shiftTable 班次
     */
    public void saveShiftTable(ShiftTable shiftTable) {
        ShiftTableImpl.getInstance().saveShiftTable(shiftTable);
    }


    /**
     * 关闭班次
     *
     * @param shiftTable 班次信息
     * @return 返回关闭的后班次
     */
    public ShiftTable closeShiftTable(ShiftTable shiftTable) {
        return ShiftTableImpl.getInstance().closeShiftTable(shiftTable);
    }


    /**
     * 获取本地没有上传的所有的班次(有流水，未上传，)
     * @param book_code
     * @param branch_num
     * @return
     */
    public List<ShiftTable> getNotUploadList(String book_code, int branch_num){
        return ShiftTableImpl.getInstance().getNotUploadList(book_code, branch_num);
    }


    /**
     * 获取本地没有关闭的所有的班次(有流水，未交班)
     * @param book_code
     * @param branch_num
     * @return
     */
    public List<ShiftTable> getUnClosedShiftTable(String book_code, int branch_num){
        return ShiftTableImpl.getInstance().getUnClosedShiftTable(book_code, branch_num);
    }


    /**
     * 初始化班次
     */
    public void initShiftTable() {
        ShiftTableImpl.getInstance().initShiftTable();
    }


    /*********************************ShiftTablePaymentImpl*********************************************/


    public ShiftTablePayment getShiftPaymentByName(String shiftTableNum,String typeName,String shiftTableBizday){
        return ShiftTablePaymentImpl.getInstance().getShiftPaymentByName(shiftTableNum,typeName,shiftTableBizday);
    }
    /*********************************StoreHouseImpl*********************************************/

    /**
     * 保存仓库信息
     *
     * @param storeHouseList 仓库信息集合
     * @return 是否保存成功
     */
    public boolean saveStoreHouseList(final List<StoreHouse> storeHouseList) {
        return StoreHouseImpl.getInstance().saveStoreHouseList(storeHouseList);
    }


    /**
     * 获取所有的仓库信息
     * @return
     */
    public List<StoreHouse> getAllStoreHouse(){
        return StoreHouseImpl.getInstance().getAllStoreHouse();
    }

    /**
     * 获取今天的营业日
     * @param book_code 帐套号
     * @param branch_num 门店编号
     * @return
     */
    public String getCurrentByzday(String book_code,int branch_num){
        return ShiftTableImpl.getInstance().getCurrentBizday(book_code, branch_num);
    }



    /*********************************SystemBookImpl*********************************************/

    /**
     * 保存帐套号信息
     *
     * @param system_book 帐套号信息
     * @return 是否保存成功
     */
    public boolean saveSystemBook(final SystemBook system_book) {
        return SystemBookImpl.getInstance().saveSystemBook(system_book);
    }
    /*********************************SystemImageQrcodeImpl*********************************************/

    /*********************************SystemPrintImpl*********************************************/

    /*********************************TicketSendImpl*********************************************/

    /**
     * 更改券上传标记
     *
     * @param ticketSendDetails 一组券
     */
    public void updateTicketStatus(List<TicketSendDetail> ticketSendDetails) {
        TicketSendImpl.getInstance().updateTicketsStatus(ticketSendDetails);
    }


    /**
     * 获取同步标记的赠送的券对象
     *
     * @param book_code  帐套号
     * @param branch_num 门店号
     * @param sycnFlag   同步标记
     * @return 对应的券对象
     */
    public List<TicketSendDetail> getTicketSendDetailList(String book_code, int branch_num, boolean sycnFlag) {
        return TicketSendImpl.getInstance().getTicketSendDetailList(book_code, branch_num, sycnFlag);
    }

    /**
     *
     * @param ticketSendList
     * @return
     */
    public boolean saveTicketSendList(final List<TicketSendDetail> ticketSendList) {
        return TicketSendImpl.getInstance().saveTicketSendList(ticketSendList);
    }

    /*********************************VipCrmAmaLevelImpl*********************************************/

    /*********************************VipCrmFeeImpl*********************************************/

    /*********************************VipCrmLevelImpl*********************************************/

    /*********************************VipCrmPointRateImpl*********************************************/

    /*********************************VipIcInitImpl*********************************************/


    /*********************************VipOperationImpl*********************************************/

    /**
     * 获取挂失卡的json
     * @param card_user_num 卡主键
     * @param card_loss_operator 操作人
     * @return
     */
    public String getLossCardJson(String card_user_num,String card_loss_operator){
        return VipOperationImpl.getInstance().getLossCardJson(card_user_num,card_loss_operator,LibConfig.BRANCH_NAME);
    }


    /*********************************VipSendCardImpl*********************************************/

    /*********************************VipSendCardSuccessImpl*********************************************/

    /*********************************VipStrangeSuccessSendMoneyImpl*********************************************/

    /*********************************YunServiceDaysImpl*********************************************/


}
