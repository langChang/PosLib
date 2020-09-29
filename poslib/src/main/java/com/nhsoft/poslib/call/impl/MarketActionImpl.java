package com.nhsoft.poslib.call.impl;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.KeyGeneratorBizday;
import com.nhsoft.poslib.entity.MarketAction;
import com.nhsoft.poslib.entity.MarketActionDetail;
import com.nhsoft.poslib.entity.TicketSendDetail;
import com.nhsoft.poslib.entity.order.Payment;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.entity.shift.ShiftTable;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.model.BranchXmlModel;
import com.nhsoft.poslib.model.CouponsXmlModel;
import com.nhsoft.poslib.model.MarketActionScopeBean;
import com.nhsoft.poslib.model.RedisBean;
import com.nhsoft.poslib.model.VipLevelOffline;
import com.nhsoft.poslib.model.VipUserInfo;
import com.nhsoft.poslib.service.greendao.MarketActionDao;
import com.nhsoft.poslib.service.greendao.MarketActionDetailDao;
import com.nhsoft.poslib.utils.MatterUtils;
import com.nhsoft.poslib.utils.TimeUtil;
import com.nhsoft.poslib.utils.UUIDUtils;
import com.nhsoft.poslib.utils.XmlParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Iverson on 2018/11/15 11:29 AM
 * 此类用于：用于操作商品的服务操作
 */
public class MarketActionImpl {

    private static MarketActionImpl instance;
    public static MarketActionImpl getInstance(){
        if (instance==null){
            instance=new MarketActionImpl();
        }
        return instance;
    }

    /**
     * 保存营销活动
     * @return 是否保存成功
     */
    public boolean saveMarketAction(final List<MarketAction> result) {
        final MarketActionDao marketActionDao = DaoManager.getInstance().getDaoSession().getMarketActionDao();
        final MarketActionDetailDao marketActionDetailDao = DaoManager.getInstance().getDaoSession().getMarketActionDetailDao();

        marketActionDetailDao.deleteAll();
        marketActionDao.deleteAll();

        if (result.size() == 0) return true;

        return MatterUtils.doMatter(marketActionDao, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < result.size(); i++) {
                    MarketAction marketAction = result.get(i);
                    if(marketAction.getPos_action_param() != null){
                        marketAction.setAction_param(new Gson().toJson(marketAction.getPos_action_param()));
                    }

                    marketActionDao.insertOrReplaceInTx(marketAction);
                    List<MarketActionDetail> market_action_details = marketAction.getMarket_action_details();
                    if (market_action_details != null && market_action_details.size() > 0) {
                        marketActionDetailDao.insertOrReplaceInTx(market_action_details);
                    }
                }
            }
        });
    }


    /**
     * 根据action_id来获取促销
     * @param actionId
     * @return
     */
    public MarketAction getOnceMarketActionByActionId(String actionId){
        if(actionId == null || TextUtils.isEmpty(actionId))return null;
        final MarketActionDao marketActionDao = DaoManager.getInstance().getDaoSession().getMarketActionDao();
        List<MarketAction> list = marketActionDao.queryBuilder().where(MarketActionDao.Properties.Action_id.eq(actionId),MarketActionDao.Properties.Only_use_once.eq(true)).build().list();
        if(list == null || list.isEmpty()){
            return null;
        }
        return list.get(0);
    }


    /**
     * 获取所有的活动
     *
     * @return
     */
    public static List<MarketAction> getAllMarketAction(int branch) {
        List<MarketAction> marketActions = new ArrayList<>();
        final MarketActionDao marketActionDao = DaoManager.getInstance().getDaoSession().getMarketActionDao();
        final MarketActionDetailDao marketActionDetailDao = DaoManager.getInstance().getDaoSession().getMarketActionDetailDao();

        List<MarketAction> loadMarketActions = marketActionDao.queryBuilder().orderDesc(MarketActionDao.Properties.Action_audit_time).build().list();
//        List<MarketAction> loadMarketActions = marketActionDao.loadAll();
        if (loadMarketActions != null && loadMarketActions.size() > 0) {
            for (MarketAction marketAction : loadMarketActions) {
                LibConfig.allOnceMarketAction.clear();
                if(marketAction.getOnly_use_once()!= null && marketAction.getOnly_use_once()){

                    LibConfig.allOnceMarketAction.add(marketAction);
                }
                if(!TextUtils.isEmpty(marketAction.getAction_param())){
                    MarketActionScopeBean marketActionScopeBean = new Gson().fromJson(marketAction.getAction_param(), MarketActionScopeBean.class);
                    marketAction.setActionMoney(marketActionScopeBean.getAction_money());
                }
                List<MarketActionDetail> marketActionDetails = marketActionDetailDao.queryBuilder().where(MarketActionDetailDao.Properties.Action_id.eq(marketAction.getAction_id())).build().list();
                for (MarketActionDetail marketActionDetail : marketActionDetails) {
                    List<BranchXmlModel> branchXmlModels = new ArrayList<>();
                    String xml2json = XmlParser.xml2json(marketActionDetail.getMarket_action_detail_branch());
                    try {
                        JSONObject jsonObjectRoot = new JSONObject(xml2json);
                        JSONObject brachListObject = new JSONObject(jsonObjectRoot.getString("AppliedBranchArray"));
                        if (isHaveJsonKey("AppliedBranch", brachListObject, 4)) {
                            String appliedBranch = brachListObject.getString("AppliedBranch");
                            List<BranchXmlModel> branchXmlModelmore = new Gson().fromJson(appliedBranch, new TypeToken<List<BranchXmlModel>>() {
                            }.getType());
                            branchXmlModels.addAll(branchXmlModelmore);
                        } else if (isHaveJsonKey("AppliedBranch", brachListObject, 3)) {
                            BranchXmlModel branchXmlModel = new Gson().fromJson(brachListObject.getString("AppliedBranch"), BranchXmlModel.class);
                            branchXmlModels.add(branchXmlModel);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    marketActionDetail.setBranchXmlModels(branchXmlModels);
                }
                marketAction.setMarket_action_details(marketActionDetails);
            }
            marketActions.addAll(loadMarketActions);
        }
        return marketActions;
    }

    private static boolean isHaveJsonKey(String key, JSONObject jsonObject, int type) {
        try {
            if (type == 1) {
                jsonObject.getInt(key);
            } else if (type == 2) {
                jsonObject.getDouble(key);
            } else if (type == 3) {
                jsonObject.getString(key);
            } else if (type == 4) {
                jsonObject.getJSONArray(key);
            }
        } catch (JSONException e) {
            return false;
        }
        return true;
    }


    //开卡赠劵
    public List<TicketSendDetail> sendCouponsByOpenCard(String orderNo, ShiftTable shiftTable, List<MarketAction> marketActions, VipUserInfo vipUserInfo) {
        List<TicketSendDetail> ticketSendModels = new ArrayList<>();
        try {
            //营销活动为空
            if (marketActions == null || marketActions.size() == 0) return new ArrayList<>();
            //营销活动活动金额来排序
            Collections.sort(marketActions, new Comparator<MarketAction>() {
                @Override
                public int compare(MarketAction marketAction, MarketAction marketAction1) {
                    if (marketAction.getActionMoney() > marketAction1.getActionMoney()) {
                        return -1;
                    } else if (marketAction.getActionMoney() < marketAction1.getActionMoney()) {
                        return -0;
                    }
                    return 0;
                }
            });
            //遍历营销活动
            for (MarketAction marketAction : marketActions) {
                String action_type = marketAction.getAction_type();
                if (!LibConfig.MARKET_ACTION_TYPE_OPEN_CARD.equals(action_type))
                    continue;  //活动类型不匹配
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                Date zero = calendar.getTime();
                //活动时间范围不匹配
                if (!TimeUtil.isActionEffectiveDate(zero, marketAction.getAction_date_from(), marketAction.getAction_date_to()))
                    continue;


                MarketActionScopeBean marketActionScopeBean = marketAction.getPos_action_param();
                if (marketActionScopeBean == null) continue; //活动条件不能为空
                //判断卡类型
                if (marketActionScopeBean.getCard_user_type_codes() != null && marketActionScopeBean.getCard_user_type_codes().size() > 0) {
                    if (vipUserInfo == null || !marketActionScopeBean.getCard_user_type_codes().contains(vipUserInfo.getCard_user_type_code())) {
                        continue;
                    }
                }

                if (marketActionScopeBean.getVip_level_offlines() != null && marketActionScopeBean.getVip_level_offlines().size() > 0) {
                    if (vipUserInfo == null) continue;
                    boolean isContain = false;
                    for (VipLevelOffline vipLevelOffline : marketActionScopeBean.getVip_level_offlines()) {
                        if (("" + vipLevelOffline.getId()).equals(vipUserInfo.getLevel())) {
                            isContain = true;
                            break;
                        }
                    }
                    if (!isContain) {
                        continue;
                    }
                }

                List<MarketActionDetail> market_action_details = marketAction.getMarket_action_details();
                MarketActionDetail marketActionDetail;
                for (int i = 1; i <= market_action_details.size(); i++) {
                    marketActionDetail = market_action_details.get(i - 1);
                    marketActionDetail.setMaket_action_send_count(marketActionDetail.getMarket_action_detail_amount());
                    List<TicketSendDetail> ticketBeans = createTicketModel(marketActionDetail, shiftTable, vipUserInfo, orderNo, 0);
                    if (ticketBeans != null && ticketBeans.size() > 0) {
                        ticketSendModels.addAll(ticketBeans);
                    }
                }
                if (ticketSendModels.size() > 0) return ticketSendModels;
            }
            return ticketSendModels;
        } catch (Exception e) {
            return ticketSendModels;
        }
    }

    //储值赠劵
    //卡存款赠券关联的流水号后缀需加上一位字符A
    public static List<TicketSendDetail> sendCouponsByDeposit(String orderNo, ShiftTable shiftTable, List<MarketAction> marketActions, VipUserInfo vipUserInfo, String paymentBy, float paymentMoney) {
        List<TicketSendDetail> ticketSendModels = new ArrayList<>();
        try {
            //营销活动为空
            if (marketActions == null || marketActions.size() == 0) return new ArrayList<>();
            //营销活动活动金额来排序
            Collections.sort(marketActions, new Comparator<MarketAction>() {
                @Override
                public int compare(MarketAction marketAction, MarketAction marketAction1) {
                    if (marketAction.getActionMoney() > marketAction1.getActionMoney()) {
                        return -1;
                    } else if (marketAction.getActionMoney() < marketAction1.getActionMoney()) {
                        return -0;
                    }
                    return 0;
                }
            });
            //遍历营销活动
            for (MarketAction marketAction : marketActions) {
                String action_type = marketAction.getAction_type();
                if (!LibConfig.MARKET_ACTION_TYPE_DEPOSIT.equals(action_type))
                    continue;  //活动类型不匹配
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                Date zero = calendar.getTime();
                //活动时间范围不匹配
                if (!TimeUtil.isActionEffectiveDate(zero, marketAction.getAction_date_from(), marketAction.getAction_date_to()))
                    continue;


                MarketActionScopeBean marketActionScopeBean = marketAction.getPos_action_param();
                if (marketActionScopeBean == null) continue; //活动条件不能为空
                //判断卡类型
                if (marketActionScopeBean.getCard_user_type_codes() != null && marketActionScopeBean.getCard_user_type_codes().size() > 0) {
                    if (vipUserInfo == null || !marketActionScopeBean.getCard_user_type_codes().contains(vipUserInfo.getCard_user_type_code())) {
                        continue;
                    }
                }

                if (marketActionScopeBean.getVip_level_offlines() != null && marketActionScopeBean.getVip_level_offlines().size() > 0) {
                    if (vipUserInfo == null) continue;
                    boolean isContain = false;
                    for (VipLevelOffline vipLevelOffline : marketActionScopeBean.getVip_level_offlines()) {
                        if (("" + vipLevelOffline.getId()).equals(vipUserInfo.getLevel())) {
                            isContain = true;
                            break;
                        }
                    }
                    if (!isContain) {
                        continue;
                    }
                }

                //判断例外日期
                if (!TextUtils.isEmpty(marketActionScopeBean.getExcept_date()) && TimeUtil.isContainTodayDate(marketActionScopeBean.getExcept_date())) {
                    continue;
                }

                int stangeCount = 0;
                if (TextUtils.isEmpty(vipUserInfo.getCard_user_deposit_count()) || "0".equals(vipUserInfo.getCard_user_deposit_count())) {
                    stangeCount = 1;
                }
                //首次活动
                if (marketActionScopeBean.isFirst_action() && stangeCount != 1) {
                    continue;
                }

                float limitmoney = paymentMoney;
//                //支付范围
                if (marketActionScopeBean.getPayment_types() != null && marketActionScopeBean.getPayment_types().size() > 0) {
                    if (!marketActionScopeBean.getPayment_types().contains(paymentBy)) {
                        limitmoney = 0;
                    }

                    if (marketActionScopeBean.getPayment_types().contains(LibConfig.C_PAYMENT_TYPE_THIRD_PARTY_ONLINE)) {
                        if (paymentBy.equals(LibConfig.C_PAYMENT_TYPE_ONLINE) || paymentBy.equals(LibConfig.C_PAYMENT_TYPE_ONLINE) ||
                                paymentBy.equals(LibConfig.C_PAYMENT_TYPE_ALIPAY_NAME) || paymentBy.equals(LibConfig.C_PAYMENT_TYPE_WECHATPAY_NAME) ||
                                paymentBy.equals(LibConfig.C_PAYMENT_TYPE_CLOUDFLASHPAY_NAME) || paymentBy.equals(LibConfig.C_PAYMENT_TYPE_MOBILEPET_NAME) ||
                                paymentBy.equals(LibConfig.C_PAYMENT_TYPE_YIPAY_NAME)) {
                            limitmoney = paymentMoney;
                        }
                    }
                }

                List<MarketActionDetail> market_action_details = marketAction.getMarket_action_details();
                Collections.sort(market_action_details, new Comparator<MarketActionDetail>() {
                    @Override
                    public int compare(MarketActionDetail marketActionDetail, MarketActionDetail marketActionDetail1) {
                        if (marketActionDetail.getMarket_action_detail_grade() > marketActionDetail1.getMarket_action_detail_grade()) {
                            return -1;
                        } else if (marketActionDetail.getMarket_action_detail_grade() < marketActionDetail1.getMarket_action_detail_grade()) {
                            return 1;
                        }
                        return 0;
                    }
                });

                boolean isAction_increase = marketActionScopeBean.isAction_increase();

                double isGradeDetail = 0;
                MarketActionDetail marketActionDetail;
                for (int i = 1; i <= market_action_details.size(); i++) {
                    marketActionDetail = market_action_details.get(i - 1);
                    int market_action_detail_amount = marketActionDetail.getMarket_action_detail_amount();
                    marketActionDetail.setMaket_action_send_count(market_action_detail_amount);


                    if (marketActionDetail.getMarket_action_detail_grade() < isGradeDetail)
                        continue;//阶梯价不为零赠送过了就不送了

                    if (marketActionDetail.getMarket_action_detail_grade_to() != null) {
                        float marketactionGradeFrom = marketActionDetail.getMarket_action_detail_grade();
                        float marketactionGradeTo = marketActionDetail.getMarket_action_detail_grade_to().floatValue();
                        if (limitmoney < marketactionGradeFrom || limitmoney >= marketactionGradeTo) {
                            continue;
                        }
                    }

                    if (marketActionDetail.getMarket_action_detail_grade() > limitmoney)
                        continue;

//                        List<TicketSendDetail> ticketBeans = createTicketModel(marketActionDetail, shiftTable, vipUserInfo, orderNo + "A", paymentMoney);
//                        if (ticketBeans != null && ticketBeans.size() > 0) {
//                            ticketSendModels.addAll(ticketBeans);
//                        }
//                    } else {
//                    if (marketActionDetail.getMarket_action_detail_grade() != 0 && marketActionDetail.getMarket_action_detail_grade() < isGradeDetail)
//                        continue;//阶梯价不为零赠送过了就不送了
                    if (marketActionDetail.getMarket_action_detail_grade() <= limitmoney) {
                        if (isAction_increase) {
//                            int totalCount = (int) (limitmoney / marketActionDetail.getMarket_action_detail_grade());

                            int totalCount = 1;
                            if (marketActionDetail.getMarket_action_detail_grade() != 0) {
                                totalCount = (int) (limitmoney / marketActionDetail.getMarket_action_detail_grade());
                            } else {
                                totalCount = 1;
                            }

                            if (marketActionScopeBean.getMost_count() != 0 && marketActionScopeBean.getMost_count() <= totalCount) {
                                totalCount = marketActionScopeBean.getMost_count();
                            }

                            marketActionDetail.setMaket_action_send_count(totalCount * marketActionDetail.getMarket_action_detail_amount());
                            List<TicketSendDetail> ticketBeans = createTicketModel(marketActionDetail, shiftTable, vipUserInfo, orderNo + "A", paymentMoney);
                            if (ticketBeans != null && ticketBeans.size() > 0) {
                                isGradeDetail = marketActionDetail.getMarket_action_detail_grade();
                                ticketSendModels.addAll(ticketBeans);
                            }
                        } else {
                            List<TicketSendDetail> ticketBeans = createTicketModel(marketActionDetail, shiftTable, vipUserInfo, orderNo + "A", paymentMoney);
                            if (ticketBeans != null && ticketBeans.size() > 0) {
                                isGradeDetail = marketActionDetail.getMarket_action_detail_grade();
                                ticketSendModels.addAll(ticketBeans);
                            }
                        }
                    }
//                    }
                }
                if (ticketSendModels.size() > 0) return ticketSendModels;
            }
            return ticketSendModels;
        } catch (Exception e) {
            return ticketSendModels;
        }
    }

    //消费赠劵
    public List<TicketSendDetail> sendCouponsByPosOrder(PosOrder posOrder, ShiftTable
            shiftTable, List<MarketAction> marketActions, VipUserInfo vipUserInfo) {
        List<TicketSendDetail> ticketSendModels = new ArrayList<>();
        try {
            //营销活动为空
            if (marketActions == null || marketActions.size() == 0) return new ArrayList<>();
            //营销活动活动金额来排序
            Collections.sort(marketActions, new Comparator<MarketAction>() {
                @Override
                public int compare(MarketAction marketAction, MarketAction marketAction1) {
                    if (marketAction.getActionMoney() > marketAction1.getActionMoney()) {
                        return -1;
                    } else if (marketAction.getActionMoney() < marketAction1.getActionMoney()) {
                        return -0;
                    }
                    return 0;
                }
            });
            PosOrder clonePosOrder = RetailPosManager.getInstance().copyPosOrder(posOrder);
//            PosOrder clonePosOrder = (PosOrder) posOrder.clone();
            List<PosOrderDetail> posOrderDetails = clonePosOrder.getPosOrderDetails();
            List<Payment> payments = clonePosOrder.getPayments();
            //遍历营销活动
            for (MarketAction marketAction : marketActions) {
                String action_type = marketAction.getAction_type();
                if (!LibConfig.MARKET_ACTION_TYPE_CONSUME.equals(action_type))
                    continue;  //活动类型不匹配
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                Date zero = calendar.getTime();
                if (marketAction.getOnly_card_use() != null && marketAction.getOnly_card_use() && vipUserInfo == null)
                    continue;

                if (marketAction.getOnly_use_once() != null && marketAction.getOnly_use_once() && vipUserInfo == null) {
                    continue;
                }
                if (marketAction.getOnly_use_once() && vipUserInfo != null) {
                    if (LibConfig.sVipEnjoyMarketAction.size() > 0) {
//                        String actionId = LibConfig.sVipEnjoyMarketAction.get(vipUserInfo.getCard_user_num());
//                        if (actionId != null && actionId.equals(marketAction.getAction_id())) {
//                            continue;
//                        }
                        String promotionId = "";
                        if (!TextUtils.isEmpty(vipUserInfo.getCustomer_id())) {
                            promotionId = LibConfig.sVipEnjoyMarketAction.get(vipUserInfo.getCustomer_id());
                        } else {
                            promotionId = LibConfig.sVipEnjoyMarketAction.get(vipUserInfo.getCard_user_num());
                        }
                        if (promotionId != null && promotionId.equals(marketAction.getAction_id())) {
                            continue;
                        }
                    }

                }

                //活动时间范围不匹配
                if (!TimeUtil.isActionEffectiveDate(zero, marketAction.getAction_date_from(), marketAction.getAction_date_to()))
                    continue;

                if (!currentTimeContain(marketAction))
                    continue;

                MarketActionScopeBean marketActionScopeBean = marketAction.getPos_action_param();
                if (marketActionScopeBean == null) continue; //活动条件不能为空
                //判断卡类型
                if (marketActionScopeBean.getCard_user_type_codes() != null && marketActionScopeBean.getCard_user_type_codes().size() > 0) {
                    if (vipUserInfo == null || !marketActionScopeBean.getCard_user_type_codes().contains(vipUserInfo.getCard_user_type_code())) {
                        continue;
                    }
                }

                if (marketActionScopeBean.getVip_level_offlines() != null && marketActionScopeBean.getVip_level_offlines().size() > 0) {
                    if (vipUserInfo == null) continue;
                    boolean isContain = false;
                    for (VipLevelOffline vipLevelOffline : marketActionScopeBean.getVip_level_offlines()) {
                        if (("" + vipLevelOffline.getId()).equals(vipUserInfo.getLevel())) {
                            isContain = true;
                            break;
                        }
                    }
                    if (!isContain) {
                        continue;
                    }
                }

                //判断例外日期
                if (!TextUtils.isEmpty(marketActionScopeBean.getExcept_date()) && TimeUtil.isContainTodayDate(marketActionScopeBean.getExcept_date())) {
                    continue;
                }
                float limitmoney = 0;

                //商品范围支付钱
                float rangDetailPayMoney = clonePosOrder.getOrderPaymentMoney() + clonePosOrder.getOrderDiscountMoney() + clonePosOrder.getOrderRound();
                for (PosOrderDetail posOrderDetail : posOrderDetails) {
                    if (posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_COUPON))
                        continue;

                    if ("指定商品".equals(marketActionScopeBean.getAction_type())) {
                        if (marketActionScopeBean.getItem_nums() != null && marketActionScopeBean.getItem_nums().size() > 0) {
                            if (!marketActionScopeBean.getItem_nums().contains(posOrderDetail.getItemNum())) {
                                //指定商品
                                rangDetailPayMoney -= posOrderDetail.getOrderDetailPaymentMoney();
                            }
                        }
                    } else if ("例外商品".equals(marketActionScopeBean.getAction_type())) {
                        if (marketActionScopeBean.getItem_type_names() != null && marketActionScopeBean.getItem_type_names().contains(posOrderDetail.getPosItem().getItem_category())) {
                            if (marketActionScopeBean.getOther_item_nums() != null && marketActionScopeBean.getOther_item_nums().contains(posOrderDetail.getItemNum())) {
                                rangDetailPayMoney -= posOrderDetail.getOrderDetailPaymentMoney();
                            }
                        } else {
                            rangDetailPayMoney -= posOrderDetail.getOrderDetailPaymentMoney();
                        }
                    } else if ("全部商品".equals(marketActionScopeBean.getAction_type())) {

                    }
                }
                //支付范围
                float rangPaymentPayMoney = 0;
                for (Payment payment : payments) {
                    if (marketActionScopeBean.getPayment_types() != null && marketActionScopeBean.getPayment_types().size() > 0) {
                        if (marketActionScopeBean.getPayment_types().contains(payment.getPaymentPayBy())) {
                            String paymentBy = payment.getPaymentPayBy();
                            if (marketActionScopeBean.getPayment_types().contains(LibConfig.C_PAYMENT_TYPE_THIRD_PARTY_ONLINE)) {
                                if (paymentBy.equals(LibConfig.C_PAYMENT_TYPE_ALIPAY_NAME) || paymentBy.equals(LibConfig.C_PAYMENT_TYPE_WECHATPAY_NAME) ||
                                        paymentBy.equals(LibConfig.C_PAYMENT_TYPE_CLOUDFLASHPAY_NAME) || paymentBy.equals(LibConfig.C_PAYMENT_TYPE_MOBILEPET_NAME) ||
                                        paymentBy.equals(LibConfig.C_PAYMENT_TYPE_YIPAY_NAME)) {
                                } else {
                                    rangPaymentPayMoney += payment.getPaymentMoney();
                                }
                            } else {
                                rangPaymentPayMoney += payment.getPaymentMoney();
                            }
                        }
                    } else {
                        rangPaymentPayMoney += payment.getPaymentMoney();
                    }
                }
                if (rangPaymentPayMoney > rangDetailPayMoney) {
                    //用商品总和
                    limitmoney = rangDetailPayMoney;
                } else {
                    limitmoney = rangPaymentPayMoney;
                }

                List<MarketActionDetail> market_action_details = marketAction.getMarket_action_details();
                Collections.sort(market_action_details, new Comparator<MarketActionDetail>() {
                    @Override
                    public int compare(MarketActionDetail marketActionDetail, MarketActionDetail marketActionDetail1) {
                        if (marketActionDetail.getMarket_action_detail_grade() > marketActionDetail1.getMarket_action_detail_grade()) {
                            return -1;
                        } else if (marketActionDetail.getMarket_action_detail_grade() < marketActionDetail1.getMarket_action_detail_grade()) {
                            return 1;
                        }
                        return 0;
                    }
                });

                boolean isAction_increase = marketActionScopeBean.isAction_increase();

                double isGradeDetail = 0;
                MarketActionDetail marketActionDetail;
                for (int i = 1; i <= market_action_details.size(); i++) {
                    marketActionDetail = market_action_details.get(i - 1);
                    int market_action_detail_amount = marketActionDetail.getMarket_action_detail_amount();
                    marketActionDetail.setMaket_action_send_count(market_action_detail_amount);

                    if (marketActionDetail.getMarket_action_detail_grade() < isGradeDetail)
                        continue;//阶梯价不为零赠送过了就不送了

                    if (marketActionDetail.getMarket_action_detail_grade_to() != null) {
                        float marketactionGradeFrom = marketActionDetail.getMarket_action_detail_grade();
                        float marketactionGradeTo = marketActionDetail.getMarket_action_detail_grade_to().floatValue();
                        if (limitmoney < marketactionGradeFrom || limitmoney >= marketactionGradeTo) {
                            continue;
                        }
                    }

                    if (marketActionDetail.getMarket_action_detail_grade() > limitmoney)
                        continue;


                    if (isAction_increase) {
                        int totalCount = 1;
                        if (marketActionDetail.getMarket_action_detail_grade() != 0) {
                            totalCount = (int) (limitmoney / marketActionDetail.getMarket_action_detail_grade());
                        } else {
                            totalCount = 1;
                        }

                        if (marketActionScopeBean.getMost_count() != 0 && marketActionScopeBean.getMost_count() <= totalCount) {
                            totalCount = marketActionScopeBean.getMost_count();
                        }
                        marketActionDetail.setMaket_action_send_count(totalCount * marketActionDetail.getMarket_action_detail_amount());
                        List<TicketSendDetail> ticketBeans = createTicketModel(marketActionDetail, shiftTable, vipUserInfo, clonePosOrder.getOrderNo(), clonePosOrder.getOrderPaymentMoney());
                        if (ticketBeans != null && ticketBeans.size() > 0) {
                            isGradeDetail = marketActionDetail.getMarket_action_detail_grade();
                            ticketSendModels.addAll(ticketBeans);
                        }
                    } else {
                        List<TicketSendDetail> ticketBeans = createTicketModel(marketActionDetail, shiftTable, vipUserInfo, clonePosOrder.getOrderNo(), clonePosOrder.getOrderPaymentMoney());
                        if (ticketBeans != null && ticketBeans.size() > 0) {
                            isGradeDetail = marketActionDetail.getMarket_action_detail_grade();
                            ticketSendModels.addAll(ticketBeans);
                        }
                    }


                }
                if (ticketSendModels.size() > 0) {
                    if (marketAction.getOnly_use_once() != null && marketAction.getOnly_use_once() && vipUserInfo != null) {

                        if (LibConfig.activeVipMember != null) {
                            RedisBean redisBean = RetailPosManager.getInstance().createPolicyRedisBean(vipUserInfo, marketAction.getAction_id());
                            posOrder.setRedisBean(redisBean);
                            posOrder.setCouponsRedisBean(redisBean);
                            LibConfig.sVipEnjoyMarketAction.put(redisBean.getVip_id(), marketAction.getAction_id());
                        }


                    }
                    return ticketSendModels;
                }
            }
            return ticketSendModels;
        } catch (Exception e) {
            return ticketSendModels;
        }
    }


    /**
     * 创建劵
     *
     * @param marketActionDetail
     */
    private static List<TicketSendDetail> createTicketModel(MarketActionDetail
                                                                    marketActionDetail, ShiftTable shiftTable, VipUserInfo vipUserInfo, String orderNo,
                                                            float paymentmoney) {
        String nowDateString = TimeUtil.getInstance().getNowDateString();
        List<TicketSendDetail> ticketSendDetailList = new ArrayList<>();
        for (int i = 0; i < marketActionDetail.getMaket_action_send_count(); i++) {
            TicketSendDetail ticketSendDetail = new TicketSendDetail();


            KeyGeneratorBizday couponsKG = KeyGeneratorBizdayImpl.getInstance().createCouponsKG(shiftTable.getSystemBookCode(), shiftTable.getBranchNum(), shiftTable.getShiftTableBizday(), LibConfig.S_CARD_CONSUME, LibConfig.activePosMachine.getPos_machine_sequence());
            String uuid = UUIDUtils.getUUID32();
            ticketSendDetail.setTicket_send_fid(uuid);
            ticketSendDetail.setTicket_send_detail_num(i + 1);
            ticketSendDetail.setTicket_send_detail_print_num(couponsKG.getKeyGBString());
            if (marketActionDetail.getMarket_action_detail_auto_value()) {
                int minValue = (int) marketActionDetail.getMarket_action_detail_value_min();
                int maxValue = (int) marketActionDetail.getMarket_action_detail_value_max();
                int lastValue = (int) (Math.random() * (maxValue - minValue + 1)) + minValue;
                ticketSendDetail.setTicket_send_detail_value(lastValue);
            } else {
                ticketSendDetail.setTicket_send_detail_value((float) marketActionDetail.getMarket_action_detail_value());
            }
            ticketSendDetail.setTicket_send_detail_date(nowDateString);
            ticketSendDetail.setTicket_send_detail_type(marketActionDetail.getMarket_action_detail_type_name());
            ticketSendDetail.setTicket_send_detail_state_code(1);
            ticketSendDetail.setTicket_send_detail_state_name("启用");
            ticketSendDetail.setTicket_send_detail_sync_flag(false);
            ticketSendDetail.setTicket_send_detail_uuid(UUIDUtils.getUUID32());
            ticketSendDetail.setTicket_send_bar_code(couponsKG.getKeyGBString());
            if (marketActionDetail.isMarket_action_detail_self_branch()) {
                ticketSendDetail.setTicket_send_detail_branch("<?xml version=\"1.0\" encoding=\"GBK\"?> <AppliedBranchArray><AppliedBranch><AppliedBranchNum>" + LibConfig.activeBranch.getBranch_num() + "</AppliedBranchNum><AppliedBranchName>" + LibConfig.activeBranch.getBranch_name() + "</AppliedBranchName></AppliedBranch></AppliedBranchArray>");
            } else {
                ticketSendDetail.setTicket_send_detail_branch(marketActionDetail.getMarket_action_detail_branch());
            }

            ticketSendDetail.setAction_id(marketActionDetail.getAction_id());
            if (RetailPosManager.isOpenCrm()) {
                ticketSendDetail.setTicket_send_detail_phone(vipUserInfo == null ? null : vipUserInfo.getCard_phone());
                ticketSendDetail.setTicket_send_detail_card_num((vipUserInfo == null || TextUtils.isEmpty(vipUserInfo.getCard_user_num())) ? 0 : Integer.parseInt(vipUserInfo.getCard_user_num()));
            } else {
                ticketSendDetail.setTicket_send_detail_card_num(vipUserInfo == null ? 0 : Integer.parseInt(vipUserInfo.getCard_user_num()));
            }
            ticketSendDetail.setTicket_send_detail_order_money(paymentmoney);
            ticketSendDetail.setTicket_send_detail_send_time(nowDateString);
            ticketSendDetail.setTicket_send_detail_user("");
            ticketSendDetail.setTicket_send_detail_send_branch(shiftTable.getBranchNum());
            ticketSendDetail.setTicket_send_detail_pre_order_no(orderNo);

            if ("指定日期".equals(marketActionDetail.getMarket_action_detail_valid_type())) {
                if (TextUtils.isEmpty(marketActionDetail.getMarket_action_detail_effective_date())) {
                    ticketSendDetail.setTicket_send_detail_valid_start(TimeUtil.getTodayZeroString());
                } else {
                    Date date = TimeUtil.initDateByDay();
                    if (TimeUtil.isaboveNowDate(date, marketActionDetail.getMarket_action_detail_effective_date())) {
                        ticketSendDetail.setTicket_send_detail_valid_start(marketActionDetail.getMarket_action_detail_effective_date());
                    } else {
                        ticketSendDetail.setTicket_send_detail_valid_start(TimeUtil.getInstance().dateToString(date, TimeUtil.FORMAT_ONE));
                    }
                }
                ticketSendDetail.setTicket_send_detail_valid_date(marketActionDetail.getMarket_action_detail_date());
            } else if ("指定周期".equals(marketActionDetail.getMarket_action_detail_valid_type())) {
                String startDete = TimeUtil.getInstance().getLaterDate(TimeUtil.getTodayZeroString(), marketActionDetail.getMarket_action_detail_start());
                String endDete = TimeUtil.getInstance().getLaterDate(startDete, marketActionDetail.getMarket_action_detail_day() - 1);
                ticketSendDetail.setTicket_send_detail_valid_start(startDete);
                ticketSendDetail.setTicket_send_detail_valid_date(endDete);
            } else {
                if (TextUtils.isEmpty(marketActionDetail.getMarket_action_detail_effective_date())) {
                    ticketSendDetail.setTicket_send_detail_valid_start(TimeUtil.getTodayZeroString());
                } else {
                    Date date = TimeUtil.initDateByDay();
                    if (TimeUtil.isaboveNowDate(date, marketActionDetail.getMarket_action_detail_effective_date())) {
                        ticketSendDetail.setTicket_send_detail_valid_start(marketActionDetail.getMarket_action_detail_effective_date());
                    } else {
                        ticketSendDetail.setTicket_send_detail_valid_start(TimeUtil.getInstance().dateToString(date, TimeUtil.FORMAT_ONE));
                    }
                }
                if (marketActionDetail.getMarket_action_detail_day() > 0) {
                    String laterDate = TimeUtil.getInstance().getLaterDate(ticketSendDetail.getTicket_send_detail_valid_start(), marketActionDetail.getMarket_action_detail_day());
                    ticketSendDetail.setTicket_send_detail_valid_date(laterDate);
                } else {
                    ticketSendDetail.setTicket_send_detail_valid_date(marketActionDetail.getMarket_action_detail_date());
                }
            }


            ticketSendDetail.setSystem_book_code(shiftTable.getSystemBookCode());
            //新添加
            ticketSendDetail.setBranch_num(shiftTable.getBranchNum());
            ticketSendDetail.setShiftTableNum(shiftTable.getShiftTableNum());

            boolean isAlready = false;

            for (CouponsXmlModel couponsXmlModel : LibConfig.sCouponsXmlModels) {
                if (couponsXmlModel.getCouponsName().equals(ticketSendDetail.getTicket_send_detail_type())) {
                    ticketSendDetail.setTicket_send_detail_type_code(couponsXmlModel.getTicketCode());
                    ticketSendDetail.setTicket_send_detail_memo(couponsXmlModel.getCouponsMemo());
                    isAlready = true;
                    break;
                }
            }
            if (isAlready) {
                ticketSendDetail.setCoupon_action_detail_id(!TextUtils.isEmpty(marketActionDetail.getCoupon_action_detail_id()) ? Long.parseLong(marketActionDetail.getCoupon_action_detail_id()) : null);
                ticketSendDetailList.add(ticketSendDetail);
            }
        }

        return ticketSendDetailList;
    }

    /**
     * 储值兑换券
     */
    public static TicketSendDetail createStrange2TicketModel(ShiftTable shiftTable, VipUserInfo vipUserInfo, String orderNo,
                                                             float paymentmoney) {
        String nowDateString = TimeUtil.getInstance().getNowDateString();
        TicketSendDetail ticketSendDetail = new TicketSendDetail();
        KeyGeneratorBizday couponsKG = KeyGeneratorBizdayImpl.getInstance().createCouponsKG(shiftTable.getSystemBookCode(), shiftTable.getBranchNum(), shiftTable.getShiftTableBizday(), LibConfig.S_CARD_CONSUME, LibConfig.activePosMachine.getPos_machine_sequence());
        String uuid = UUIDUtils.getUUID32();
        ticketSendDetail.setTicket_send_fid(uuid);
        ticketSendDetail.setTicket_send_detail_num(0);
        ticketSendDetail.setTicket_send_detail_print_num(couponsKG.getKeyGBString());
        ticketSendDetail.setTicket_send_detail_value(paymentmoney);

        ticketSendDetail.setTicket_send_detail_date(nowDateString);
        for (CouponsXmlModel couponsXmlModel : LibConfig.sCouponsXmlModels) {
            if (couponsXmlModel.getCouponsType().equals("会员卡储值券") && !couponsXmlModel.isOnline()) {
                ticketSendDetail.setTicket_send_detail_type(couponsXmlModel.getCouponsName());
                ticketSendDetail.setCoupon_action_detail_id(couponsXmlModel.getCouponActionDetailId());
                break;
            }
        }

        ticketSendDetail.setTicket_send_detail_state_code(1);
        ticketSendDetail.setTicket_send_detail_state_name("启用");
        ticketSendDetail.setTicket_send_detail_memo("");
        ticketSendDetail.setTicket_send_detail_sync_flag(false);
        ticketSendDetail.setTicket_send_detail_uuid(UUIDUtils.getUUID32());
        ticketSendDetail.setTicket_send_bar_code(couponsKG.getKeyGBString());

        ticketSendDetail.setTicket_send_detail_branch("<?xml version=\"1.0\" encoding=\"GBK\"?> <AppliedBranchArray><AppliedBranch><AppliedBranchNum>" + LibConfig.activeBranch.getBranch_num() + "</AppliedBranchNum><AppliedBranchName>" + LibConfig.activeBranch.getBranch_name() + "</AppliedBranchName></AppliedBranch></AppliedBranchArray>");

        ticketSendDetail.setAction_id(ticketSendDetail.getAction_id());
        ticketSendDetail.setTicket_send_detail_card_num(vipUserInfo == null ? 0 : Integer.parseInt(vipUserInfo.getCard_user_num()));
        ticketSendDetail.setTicket_send_detail_order_money(paymentmoney);
        ticketSendDetail.setTicket_send_detail_send_time(nowDateString);
        ticketSendDetail.setTicket_send_detail_user("");
        ticketSendDetail.setTicket_send_detail_send_branch(shiftTable.getBranchNum());
        ticketSendDetail.setTicket_send_detail_pre_order_no(orderNo);
        ticketSendDetail.setTicket_send_detail_valid_start(TimeUtil.getTodayZeroString());
        ticketSendDetail.setTicket_send_detail_valid_date(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), 30) + " 00:00:00");
        ticketSendDetail.setSystem_book_code(shiftTable.getSystemBookCode());
        //新添加
        ticketSendDetail.setBranch_num(shiftTable.getBranchNum());
        ticketSendDetail.setShiftTableNum(shiftTable.getShiftTableNum());

        for (CouponsXmlModel couponsXmlModel : LibConfig.sCouponsXmlModels) {
            if (couponsXmlModel.getCouponsName().equals(ticketSendDetail.getTicket_send_detail_type())) {
                ticketSendDetail.setTicket_send_detail_type_code(couponsXmlModel.getTicketCode());
                break;
            }
        }


        return ticketSendDetail;
    }


    private static boolean currentTimeContain(MarketAction marketAction) {

        String startTime = marketAction.getAction_time_from();
        String endTime = marketAction.getAction_time_to();
        if (TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime)) return true;

        String nowTime = TimeUtil.getInstance().stampToDate(System.currentTimeMillis());

        String format = "HH:mm:ss";
        try {
            String nowString = nowTime.substring(nowTime.length() - 8, nowTime.length());
            String startString = startTime.substring(startTime.length() - 8, startTime.length());
            String endString = endTime.substring(endTime.length() - 8, endTime.length());

            Date nowDate = new SimpleDateFormat(format).parse(nowString);
            Date starDate = new SimpleDateFormat(format).parse(startString);
            Date endDate = new SimpleDateFormat(format).parse(endString);
            if (isEffectiveTime(nowDate, starDate, endDate)) {
                return true;
            } else {
                return false;
            }
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author jqlin
     */
    private static boolean isEffectiveTime(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


    //消费赠劵
    public static List<TicketSendDetail> sendNewCouponsByPosOrder(PosOrder posOrder, ShiftTable
            shiftTable, List<MarketAction> marketActions, VipUserInfo vipUserInfo) {
        List<TicketSendDetail> ticketSendModels = new ArrayList<>();
        try {
            //营销活动为空
            if (marketActions == null || marketActions.size() == 0) return new ArrayList<>();
            //营销活动活动金额来排序
            Collections.sort(marketActions, new Comparator<MarketAction>() {
                @Override
                public int compare(MarketAction marketAction, MarketAction marketAction1) {
                    if (marketAction.getActionMoney() > marketAction1.getActionMoney()) {
                        return -1;
                    } else if (marketAction.getActionMoney() < marketAction1.getActionMoney()) {
                        return -0;
                    }
                    return 0;
                }
            });
            PosOrder clonePosOrder = RetailPosManager.getInstance().copyPosOrder(posOrder);
//            PosOrder clonePosOrder = (PosOrder) posOrder.clone();
            List<PosOrderDetail> posOrderDetails = clonePosOrder.getPosOrderDetails();
            List<Payment> payments = clonePosOrder.getPayments();
            //遍历营销活动
            for (MarketAction marketAction : marketActions) {
                String action_type = marketAction.getAction_type();
                if (!LibConfig.MARKET_ACTION_TYPE_CONSUME.equals(action_type))
                    continue;  //活动类型不匹配
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                Date zero = calendar.getTime();
                if (marketAction.getOnly_card_use() != null && marketAction.getOnly_card_use() && vipUserInfo == null)
                    continue;

                if (marketAction.getOnly_use_once() && vipUserInfo != null) {
                    if (LibConfig.sVipEnjoyMarketAction.size() > 0) {
                        String actionId = LibConfig.sVipEnjoyMarketAction.get(vipUserInfo.getCard_user_num());
                        if (actionId != null && actionId.equals(marketAction.getAction_id())) {
                            continue;
                        }
                    }
                }
                if (marketAction.getOnly_use_once() != null && marketAction.getOnly_use_once()) {
                    if (vipUserInfo == null) continue;
                    if (LibConfig.sVipEnjoyMarketAction.get(vipUserInfo.getCard_user_num()) != null)
                        continue;
                }
                //活动时间范围不匹配
                if (!TimeUtil.isActionEffectiveDate(zero, marketAction.getAction_date_from(), marketAction.getAction_date_to()))
                    continue;

                if (!currentTimeContain(marketAction))
                    continue;

                MarketActionScopeBean marketActionScopeBean = marketAction.getPos_action_param();
                if (marketActionScopeBean == null) continue; //活动条件不能为空
                //判断卡类型
                if (marketActionScopeBean.getCard_user_type_codes() != null && marketActionScopeBean.getCard_user_type_codes().size() > 0) {
                    if (vipUserInfo == null || !marketActionScopeBean.getCard_user_type_codes().contains(vipUserInfo.getCard_user_type_code())) {
                        continue;
                    }
                }
                //判断例外日期
                if (!TextUtils.isEmpty(marketActionScopeBean.getExcept_date()) && TimeUtil.isContainTodayDate(marketActionScopeBean.getExcept_date())) {
                    continue;
                }
                float limitmoney = 0;

                //商品范围支付钱
                float rangDetailPayMoney = clonePosOrder.getOrderPaymentMoney();
                for (PosOrderDetail posOrderDetail : posOrderDetails) {
                    if (posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_COUPON))
                        continue;

                    if ("指定商品".equals(marketActionScopeBean.getAction_type())) {
                        if (marketActionScopeBean.getItem_nums() != null && marketActionScopeBean.getItem_nums().size() > 0) {
                            if (!marketActionScopeBean.getItem_nums().contains(posOrderDetail.getItemNum())) {
                                //指定商品
                                rangDetailPayMoney -= posOrderDetail.getOrderDetailPaymentMoney();
                            }
                        }
                    } else if ("例外商品".equals(marketActionScopeBean.getAction_type())) {
                        if (marketActionScopeBean.getItem_type_names() != null && marketActionScopeBean.getItem_type_names().contains(posOrderDetail.getPosItem().getItem_category())) {
                            if (marketActionScopeBean.getOther_item_nums() != null && marketActionScopeBean.getOther_item_nums().contains(posOrderDetail.getItemNum())) {
                                rangDetailPayMoney -= posOrderDetail.getOrderDetailPaymentMoney();
                            }
                        } else {
                            rangDetailPayMoney -= posOrderDetail.getOrderDetailPaymentMoney();
                        }
                    } else if ("全部商品".equals(marketActionScopeBean.getAction_type())) {

                    }
                }
                //支付范围
                float rangPaymentPayMoney = clonePosOrder.getOrderPaymentMoney();
                if (marketActionScopeBean.getPayment_types() != null && marketActionScopeBean.getPayment_types().size() > 0) {
                    for (Payment payment : payments) {
                        if (!marketActionScopeBean.getPayment_types().contains(payment.getPaymentPayBy())) {
                            String paymentBy = payment.getPaymentPayBy();
                            if (marketActionScopeBean.getPayment_types().contains(LibConfig.C_PAYMENT_TYPE_THIRD_PARTY_ONLINE)) {
                                if (paymentBy.equals(LibConfig.C_PAYMENT_TYPE_ALIPAY_NAME) || paymentBy.equals(LibConfig.C_PAYMENT_TYPE_WECHATPAY_NAME) ||
                                        paymentBy.equals(LibConfig.C_PAYMENT_TYPE_CLOUDFLASHPAY_NAME) || paymentBy.equals(LibConfig.C_PAYMENT_TYPE_MOBILEPET_NAME) ||
                                        paymentBy.equals(LibConfig.C_PAYMENT_TYPE_YIPAY_NAME)) {
                                } else {
                                    rangPaymentPayMoney -= payment.getPaymentMoney();
                                }
                            } else {
                                rangPaymentPayMoney -= payment.getPaymentMoney();
                            }
                        }
                    }
                }
                if (rangPaymentPayMoney > rangDetailPayMoney) {
                    //用商品总和
                    limitmoney = rangDetailPayMoney;
                } else {
                    limitmoney = rangPaymentPayMoney;
                }

                List<MarketActionDetail> market_action_details = marketAction.getMarket_action_details();
                Collections.sort(market_action_details, new Comparator<MarketActionDetail>() {
                    @Override
                    public int compare(MarketActionDetail marketActionDetail, MarketActionDetail marketActionDetail1) {
                        if (marketActionDetail.getMarket_action_detail_grade() > marketActionDetail1.getMarket_action_detail_grade()) {
                            return -1;
                        } else if (marketActionDetail.getMarket_action_detail_grade() < marketActionDetail1.getMarket_action_detail_grade()) {
                            return 1;
                        }
                        return 0;
                    }
                });

                boolean isAction_increase = marketActionScopeBean.isAction_increase();

                double isGradeDetail = 0;
                MarketActionDetail marketActionDetail;
                for (int i = 1; i <= market_action_details.size(); i++) {
                    marketActionDetail = market_action_details.get(i - 1);
                    int market_action_detail_amount = marketActionDetail.getMarket_action_detail_amount();
                    marketActionDetail.setMaket_action_send_count(market_action_detail_amount);
                    //阶梯价为0必送
                    if (marketActionDetail.getMarket_action_detail_grade() == 0) {
                        List<TicketSendDetail> ticketBeans = createTicketModel(marketActionDetail, shiftTable, vipUserInfo, clonePosOrder.getOrderNo(), clonePosOrder.getOrderPaymentMoney());
                        if (ticketBeans != null && ticketBeans.size() > 0) {
                            ticketSendModels.addAll(ticketBeans);
                        }
                    } else {
                        if (marketActionDetail.getMarket_action_detail_grade() != 0 && marketActionDetail.getMarket_action_detail_grade() < isGradeDetail)
                            continue;//阶梯价不为零赠送过了就不送了
                        if (marketActionDetail.getMarket_action_detail_grade() > limitmoney)
                            continue;
                        if (isAction_increase) {
                            int totalCount = (int) (limitmoney / marketActionDetail.getMarket_action_detail_grade());
                            if (marketActionScopeBean.getMost_count() != 0 && marketActionScopeBean.getMost_count() <= totalCount) {
                                totalCount = marketActionScopeBean.getMost_count();
                            }
                            marketActionDetail.setMaket_action_send_count(totalCount * marketActionDetail.getMarket_action_detail_amount());
                            List<TicketSendDetail> ticketBeans = createTicketModel(marketActionDetail, shiftTable, vipUserInfo, clonePosOrder.getOrderNo(), clonePosOrder.getOrderPaymentMoney());
                            if (ticketBeans != null && ticketBeans.size() > 0) {
                                isGradeDetail = marketActionDetail.getMarket_action_detail_grade();
                                ticketSendModels.addAll(ticketBeans);
                            }
                        } else {
                            List<TicketSendDetail> ticketBeans = createTicketModel(marketActionDetail, shiftTable, vipUserInfo, clonePosOrder.getOrderNo(), clonePosOrder.getOrderPaymentMoney());
                            if (ticketBeans != null && ticketBeans.size() > 0) {
                                isGradeDetail = marketActionDetail.getMarket_action_detail_grade();
                                ticketSendModels.addAll(ticketBeans);
                            }
                        }

                    }
                }
                if (ticketSendModels.size() > 0) {
                    if (marketAction.getOnly_use_once() != null && marketAction.getOnly_use_once() && vipUserInfo != null) {
                        LibConfig.sVipEnjoyMarketAction.put(vipUserInfo.getCard_user_num(), marketAction.getAction_id());
                    }
                    return ticketSendModels;
                }
            }
            return ticketSendModels;
        } catch (Exception e) {
            return ticketSendModels;
        }
    }


}
