package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.entity.CardTypeParam;
import com.nhsoft.poslib.entity.ClientPoint;
import com.nhsoft.poslib.entity.ItemCategory;
import com.nhsoft.poslib.entity.PointPolicy;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.VipCrmAmaLevel;
import com.nhsoft.poslib.model.VipUserInfo;
import com.nhsoft.poslib.entity.order.Payment;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.model.PointRuleXmlModel;
import com.nhsoft.poslib.call.impl.BookResourceImpl;
import com.nhsoft.poslib.call.impl.ItemCategoryImpl;
import com.nhsoft.poslib.call.impl.PointPolicyImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Iverson on 2019/1/14 6:35 PM
 * 此类用于：积分计算
 */
public class PointCalUtils {

    private static PointCalUtils instance;

    public static PointCalUtils getInstance() {
        if (instance == null) {
            instance = new PointCalUtils();
        }
        return instance;
    }

    /**
     * @param posOrder
     * @param vipUserInfo
     * @param storeMoney
     * @param uploadType  积分上传类型 参照 Constant
     * @return
     */
    public ClientPoint calcuatePoint(PosOrder posOrder, VipUserInfo vipUserInfo, float storeMoney, String uploadType) {
       return  calcuatePoint(posOrder, vipUserInfo, storeMoney, uploadType,true);
    }


    public ClientPoint calcuatePoint(PosOrder posOrder, VipUserInfo vipUserInfo, float storeMoney, String uploadType,boolean isSave) {
        if(vipUserInfo == null)return null;

        ClientPoint clientPoint = null;
        if (RetailPosManager.isOpenCrm()) {
            VipCrmAmaLevel vipLevel = RetailPosManager.getVipLevel(vipUserInfo.getLevel());
            if(vipLevel != null && vipLevel.point_rule != null){
                clientPoint = CrmPointCalUtils.getInstance().calcuatePoint(vipLevel, posOrder, vipUserInfo, storeMoney, uploadType);
                if (posOrder != null && clientPoint != null) {
                    clientPoint.setClient_point_ref_bill_no(posOrder.getOrderNo());
                }
                if(vipUserInfo != null && clientPoint != null){
                    clientPoint.setCustomer_id(vipUserInfo.getCustomer_id());
                }
            }
            return clientPoint;
        }

        CardTypeParam cardType = RetailPosManager.getCardType(vipUserInfo.getCard_user_type_code());

        //如果卡有自己的规则，就不用全局的
        if (cardType != null && cardType.getPoint_rule() != null) {
            LibConfig.activeVipCardTypeBean = BookResourceImpl.getInstance().getVipCardTypeBeanList
                    (LibConfig.activeShiftTable.getSystemBookCode(), LibConfig.S_LOCAL_VIP_TYPE, cardType.getType_name());
            clientPoint = CardPointCalUtils.getInstance().calcuatePoint(cardType, posOrder, vipUserInfo, storeMoney, uploadType);
        } else {
            LibConfig.activeVipCardTypeBean = BookResourceImpl.getInstance().getVipCardTypeBeanList
                    (LibConfig.activeShiftTable.getSystemBookCode(), LibConfig.S_LOCAL_VIP_TYPE, vipUserInfo.getCard_user_type_name());

            float birthPow = (LibConfig.saleParamsBean.isPointBirthdayActive() && isBirthDay(new Date(), vipUserInfo.getCard_user_birth_date())) ? LibConfig.saleParamsBean.getPointBirthdayProp() : 1;
            int forPoint = (int) (getForPoint(posOrder, vipUserInfo, storeMoney, birthPow));
            if (forPoint == 0) return null;
            if(isSave){
                clientPoint = ClientPointUtils.saveClientPoint(LibConfig.activeShiftTable, LibConfig.activeBranch, forPoint, vipUserInfo.getCard_user_num(), vipUserInfo.getCard_user_cust_name(),posOrder == null ? "" : posOrder.getOrderNo(), uploadType);
            }else {
                clientPoint = ClientPointUtils.DepositClientPoint(LibConfig.activeShiftTable, LibConfig.activeBranch, forPoint, vipUserInfo.getCard_user_num(), vipUserInfo.getCard_user_cust_name(), uploadType);
            }

            if (clientPoint != null) {
                vipUserInfo.setCard_point("" + (int) ((Float.parseFloat(TextUtils.isEmpty(vipUserInfo.getCard_point()) ? "0" : vipUserInfo.getCard_point()) + clientPoint.getClient_point_balance())));
            }
        }

        if (posOrder != null && clientPoint != null) {
            clientPoint.setClient_point_ref_bill_no(posOrder.getOrderNo());
        }

        if(vipUserInfo != null && clientPoint != null){
            clientPoint.setCustomer_id(vipUserInfo.getCustomer_id());
        }
        return clientPoint;
    }



    /**
     * 是否生日
     *
     * @param nowTime
     * @param birthTime
     * @return
     */
    public boolean isBirthDay(Date nowTime, String birthTime) {
        if (TextUtils.isEmpty(birthTime)) return false;
        Date birthDate = TimeUtil.getInstance().getPriceDateByString(birthTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        int birthmonth = calendar.get(Calendar.MONTH);   //获取月份，0表示1月份
        int birthday = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数

        calendar.setTime(nowTime);
        int nowmonth = calendar.get(Calendar.MONTH);   //获取月份，0表示1月份
        int nowday = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数

        return birthmonth == nowmonth && birthday == nowday;
    }

    /**
     * @param posOrder
     * @param vipUserInfo
     * @param storeMoney
     * @return
     */
    private float getForPoint(PosOrder posOrder, VipUserInfo vipUserInfo, float storeMoney, float birthPow) {
        if (vipUserInfo == null) return 0;
        List<PointRuleXmlModel> allPointRuleList = LibConfig.allPointRuleList;
        if (allPointRuleList == null || allPointRuleList.size() == 0) {
            return 0;
        }
        for (PointRuleXmlModel pointRuleXmlModel : allPointRuleList) {
            if (pointRuleXmlModel.getIsAlable() != 0) {
                if (pointRuleXmlModel.getPointRuleName().equals(LibConfig.PRESENT_BY_GOODS_POINT) || LibConfig.PRESENT_BY_GOODS_POINT_SIMPLE.equals(pointRuleXmlModel.getPointRuleName())) {
                    if (posOrder == null) return 0;
                    //按照单品积分计算
                    return calSimpleGoodsPoint(posOrder, vipUserInfo, birthPow);
                } else if (pointRuleXmlModel.getPointRuleName().equals(LibConfig.PRESENT_BY_COUPONS_MONEY)) {
                    if (posOrder == null) return 0;
                    //按照消费金额计算
                    return calCouponsPoint(posOrder, vipUserInfo, pointRuleXmlModel, birthPow);
                } else if (pointRuleXmlModel.getPointRuleName().equals(LibConfig.PRESENT_BY_CARD_STORE)) {
                    float totalPoint = 0;
                    if (storeMoney != 0) {
                        //按照会员卡储值消费计算
                        totalPoint = calCardStorePoint(storeMoney, vipUserInfo, birthPow);
                    } else if (posOrder != null) {
                        storeMoney = 0;
                        for (Payment payment : posOrder.getPayments()) {
                            if (payment.getPaymentPayBy().equals(LibConfig.C_PAYMENT_TYPE_PETCARD_NAME)) {
                                storeMoney += payment.getPaymentMoney();
                            }
                        }
                        //按照卡类型消费金额计算
                        totalPoint = calCardCouponsPoint(storeMoney, vipUserInfo, birthPow);
                    }
                    return totalPoint;
                } else if (pointRuleXmlModel.getPointRuleName().equals(LibConfig.PRESENT_BY_CARD_CONSUME)) {
                    if (posOrder == null) return 0;
                    storeMoney = 0;
                    for (Payment payment : posOrder.getPayments()) {
                        if (payment.getPaymentPayBy().equals(LibConfig.C_PAYMENT_TYPE_PETCARD_NAME)) {
                            storeMoney += payment.getPaymentMoney();
                        }
                    }
                    //按照卡类型消费金额计算
                    return calCardCouponsPoint(storeMoney, vipUserInfo, birthPow);
                }
            }
        }

        return 0;
    }

    /**
     * 计算单品积分
     *
     * @param posOrder
     * @param birthPow
     * @return
     */
    private float calSimpleGoodsPoint(PosOrder posOrder, VipUserInfo vipUserInfo, float birthPow) {
        float totalPoint = 0;
        if (posOrder != null) {
            List<PosOrderDetail> posOrderDetails = posOrder.getPosOrderDetails();
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (!posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_SALE_NAME) && !posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_RETURN_NAME))
                    continue;
                if (LibConfig.saleParamsBean.isPointSpecialItemActive() && !TextUtils.isEmpty(posOrderDetail.getOrderDetailPolicyFid()))
                    continue;
                PosItem posItem = posOrderDetail.getPosItem();
                if (posItem == null) continue;

                if (posItem.getItem_point_actived()) {
                    float point = posItem.getItem_point_value() * posOrderDetail.getOrderDetailAmount();

                    float policyPow = getPointPolicyValue(vipUserInfo, LibConfig.PRESENT_BY_COUPONS_MONEY, posItem.getItem_num());
                    float totalPow = 0;
                    if (policyPow != 1) {
                        totalPow += policyPow;
                    }
                    if (birthPow != 1) {
                        totalPow += birthPow;
                    }
                    point = point * (totalPow == 0 ? 1 : totalPow);
                    totalPoint += point;
                }
            }
        }
        return totalPoint;
    }


    /**
     * 消费积分
     *
     * @return
     */
    private float calCouponsPoint(PosOrder posOrder, VipUserInfo vipUserInfo, PointRuleXmlModel currentModel, float birthPow) {
        float pointPay = 0f;
        for (Payment payment : posOrder.getPayments()) {
            if (LibConfig.C_PAYMENT_TYPE_INTEGRAL_NAME.equals(payment.getPaymentPayBy())) {
                pointPay = payment.getPaymentMoney();
            }
        }
        float p = (posOrder.getOrderPaymentMoney() - posOrder.getOrderMgrDiscountMoney() - pointPay) / (posOrder.getOrderPaymentMoney());//每件商品的系统
        float totalPoint = 0.0f;
        if (posOrder != null) {
            List<PosOrderDetail> posOrderDetails = posOrder.getPosOrderDetails();
            if (currentModel == null) return 0;
            List<PointRuleXmlModel.PointRuleDetail> pointRuleDetails = currentModel.getPointRuleDetails();
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (LibConfig.saleParamsBean.isPointSpecialItemActive() && !TextUtils.isEmpty(posOrderDetail.getOrderDetailPolicyFid()))
                    continue;
                if (!posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_SALE_NAME) && !posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_RETURN_NAME))
                    continue;
                PosItem posItem = posOrderDetail.getPosItem();
                if (posItem == null) continue;
                if (!posItem.getItem_point_actived()) continue;

                ItemCategory loadItemCategory = ItemCategoryImpl.findCategoryCode(posOrderDetail.getPosItem().getItem_category_code());
                if (loadItemCategory != null) {
                    if (pointRuleDetails == null || pointRuleDetails.size() == 0) {
                        float point = 0;
                        if (currentModel.getCounponsMoney() != 0) {
                            point = (posOrderDetail.getResidueMoney()) * p * currentModel.getPointValue() / currentModel.getCounponsMoney();
                            float policyPow = getPointPolicyValue(vipUserInfo, LibConfig.PRESENT_BY_COUPONS_MONEY, posItem.getItem_num());
                            float totalPow = 0;
                            if (policyPow != 1) {
                                totalPow += policyPow;
                            }
                            if (birthPow != 1) {
                                totalPow += birthPow;
                            }
                            point = point * (totalPow == 0 ? 1 : totalPow);
                        }
                        totalPoint = totalPoint + point;
                        continue;
                    } else {
                        String cateGroyName = "";

                        PointRuleXmlModel.PointRuleDetail currentPointRuleDetail = null;
                        boolean isInCatetory = false;
                        while (loadItemCategory != null){
                            cateGroyName = loadItemCategory.getCategory_name();
                            for (PointRuleXmlModel.PointRuleDetail pointRuleDetail : pointRuleDetails) {
                                currentPointRuleDetail = pointRuleDetail;
                                if (pointRuleDetail.getCatetoryName().equals(cateGroyName)) {
                                    isInCatetory = true;
                                    break;
                                }
                            }

                            if(isInCatetory){
                                break;
                            }

                            loadItemCategory = ItemCategoryImpl.findParentCategoryCode(loadItemCategory.getParent_category_code());
                        }


                        if (isInCatetory) {
                            if (currentPointRuleDetail.getCouponsMoneyDetail() == 0 || currentPointRuleDetail.getPointValueDetail() == 0) {
                                float point = 0;
                                if (currentModel.getCounponsMoney() != 0) {
                                    point = (posOrderDetail.getResidueMoney()) * p * currentModel.getPointValue() / currentModel.getCounponsMoney();
                                    float policyPow = getPointPolicyValue(vipUserInfo, LibConfig.PRESENT_BY_COUPONS_MONEY, posItem.getItem_num());
                                    float totalPow = 0;
                                    if (policyPow != 1) {
                                        totalPow += policyPow;
                                    }
                                    if (birthPow != 1) {
                                        totalPow += birthPow;
                                    }
                                    point = point * (totalPow == 0 ? 1 : totalPow);
                                }
                                totalPoint = totalPoint + point;
                            } else {
                                float point = (posOrderDetail.getResidueMoney()) * p * currentPointRuleDetail.getPointValueDetail() / currentPointRuleDetail.getCouponsMoneyDetail();
                                float policyPow = getPointPolicyValue(vipUserInfo, LibConfig.PRESENT_BY_COUPONS_MONEY, posItem.getItem_num());
                                float totalPow = 0;
                                if (policyPow != 1) {
                                    totalPow += policyPow;
                                }
                                if (birthPow != 1) {
                                    totalPow += birthPow;
                                }
                                point = point * (totalPow == 0 ? 1 : totalPow);

                                totalPoint = totalPoint + point;
                            }
                        } else {
                            float point = 0;
                            if (currentModel.getCounponsMoney() != 0) {
                                point = (posOrderDetail.getResidueMoney()) * p * currentModel.getPointValue() / currentModel.getCounponsMoney();
                                float policyPow = getPointPolicyValue(vipUserInfo, LibConfig.PRESENT_BY_COUPONS_MONEY, posItem.getItem_num());
                                float totalPow = 0;
                                if (policyPow != 1) {
                                    totalPow += policyPow;
                                }
                                if (birthPow != 1) {
                                    totalPow += birthPow;
                                }
                                point = point * (totalPow == 0 ? 1 : totalPow);
                            }
                            totalPoint = totalPoint + point;
                        }
                    }
                }
            }
        }
        return totalPoint;
    }

    /**
     * 卡储值积分
     *
     * @param storeMoney (不含存款金额)
     * @param birthPow
     * @return
     */
    private float calCardStorePoint(float storeMoney, VipUserInfo vipUserInfo, float birthPow) {
        if (LibConfig.activeVipCardTypeBean != null) {
            if (TextUtils.isEmpty(LibConfig.activeVipCardTypeBean.getStrangeToFenRate())
                    || Float.parseFloat(LibConfig.activeVipCardTypeBean.getStrangeToFenRate()) == 0) {
                return 0;
            }
            float point = (storeMoney / Float.parseFloat(LibConfig.activeVipCardTypeBean.getStrangeToFenRate()));

            float policyPow = getPointPolicyValue(vipUserInfo, LibConfig.PRESENT_BY_CARD_CONSUME, 0);
            float totalPow = 0;

            if (policyPow != 1) {
                totalPow += policyPow;
            }
            if (birthPow != 1) {
                totalPow += birthPow;
            }
            return point * (totalPow == 0 ? 1 : totalPow);
        }
        return 0;
    }

    /**
     * 卡消费积分
     *
     * @param storeMoney 存入钱 (不含存款金额)
     * @return
     */
    private float calCardCouponsPoint(float storeMoney, VipUserInfo vipUserInfo, float birthPow) {
        if (LibConfig.activeVipCardTypeBean != null) {
            if (TextUtils.isEmpty(LibConfig.activeVipCardTypeBean.getConsumeToFenRate()) || Float.parseFloat(LibConfig.activeVipCardTypeBean.getConsumeToFenRate()) == 0) {
                return 0;
            }
            float point = (storeMoney / Float.parseFloat(LibConfig.activeVipCardTypeBean.getConsumeToFenRate()));
            float policyPow = getPointPolicyValue(vipUserInfo,LibConfig.PRESENT_BY_CARD_CONSUME, 0);
            float totalPow = 0;

            if (policyPow != 1) {
                totalPow += policyPow;
            }
            if (birthPow != 1) {
                totalPow += birthPow;
            }
            return point * (totalPow == 0 ? 1 : totalPow);
        }
        return 0;
    }


    /**
     * 获取积分政策倍数
     *
     *
     * @param vipUserInfo
     * @param type
     * @return
     */
    private float getPointPolicyValue(VipUserInfo vipUserInfo, String type, long item_num) {

        float policyBit = 1;
        List<PointPolicy> allPointPolicyList = LibConfig.allPointPolicyList;
        if (allPointPolicyList == null || allPointPolicyList.size() == 0) return policyBit;
        for (PointPolicy pointPolicy : allPointPolicyList) {
            if (!isSelectDay(pointPolicy)) continue;
            if (!currentTimeContain(pointPolicy)) continue;
            if (!currentDateContain(pointPolicy)) continue;
            if(!TextUtils.isEmpty(pointPolicy.getPoint_policy_level_ids()) && !pointPolicy.getPoint_policy_level_ids().contains(vipUserInfo.getLevel())){
                continue;
            }
            if (item_num != 0) {
                if (!pointPolicy.getPoint_policy_all_item() && !PointPolicyImpl.isInPointPolicy(pointPolicy.getPoint_policy_id(), item_num))
                    continue;
            }
            if (LibConfig.PRESENT_BY_CARD_STORE.equals(type) || LibConfig.PRESENT_BY_CARD_CONSUME.equals(type)) {
                if (LibConfig.POINT_POLICY_TYPE_CONSUME.equals(pointPolicy.getPoint_policy_type())) {
                    policyBit = (float) pointPolicy.getPoint_policy_multiple();
                    break;
                }
            } else if (LibConfig.PRESENT_BY_GOODS_POINT.equals(type) || LibConfig.PRESENT_BY_GOODS_POINT_SIMPLE.equals(type) || LibConfig.PRESENT_BY_COUPONS_MONEY.equals(type)) {
                if (LibConfig.POINT_POLICY_TYPE_GOODS.equals(pointPolicy.getPoint_policy_type())) {
                    policyBit = (float) pointPolicy.getPoint_policy_multiple();
                    break;
                }
            }
        }
        return policyBit;
    }

    private static boolean isSelectDay(PointPolicy pointPolicy) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return pointPolicy.getPoint_policy_sun_actived();
            case 2:
                return pointPolicy.getPoint_policy_mon_actived();
            case 3:
                return pointPolicy.getPoint_policy_tues_actived();
            case 4:
                return pointPolicy.getPoint_policy_wed_actived();
            case 5:
                return pointPolicy.getPoint_policy_thurs_actived();
            case 6:
                return pointPolicy.getPoint_policy_friday_actived();
            case 7:
                return pointPolicy.getPoint_policy_sat_actived();
            default:
                return false;
        }
    }

    private static boolean currentTimeContain(PointPolicy pointPolicy) {

        String startTime = pointPolicy.getPoint_policy_time_from();
        String endTime = pointPolicy.getPoint_policy_time_to();
        String nowTime = TimeUtil.getInstance().stampToDate(System.currentTimeMillis());

        String format = "HH:mm:ss";
        try {
            String nowString = nowTime.substring(nowTime.length() - 8);
            String startString = startTime.substring(startTime.length() - 8);
            String endString = endTime.substring(endTime.length() - 8);

            Date nowDate = new SimpleDateFormat(format).parse(nowString);
            Date starDate = new SimpleDateFormat(format).parse(startString);
            Date endDate = new SimpleDateFormat(format).parse(endString);
            return isEffectiveDate(nowDate, starDate, endDate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean currentDateContain(PointPolicy pointPolicy) {

        String startTime = pointPolicy.getPoint_policy_date_from();
        String endTime = pointPolicy.getPoint_policy_date_to();
        String nowTime = TimeUtil.getInstance().stampToDate(System.currentTimeMillis());

        String format = "yyyy-MM-dd";
        try {
            String nowString = nowTime.substring(0, nowTime.length() - 8);
            String startString = startTime.substring(0, startTime.length() - 8);
            String endString = endTime.substring(0, endTime.length() - 8);

            Date nowDate = new SimpleDateFormat(format).parse(nowString);
            Date starDate = new SimpleDateFormat(format).parse(startString);
            Date endDate = new SimpleDateFormat(format).parse(endString);
            int replayType = 0;
            if (!TextUtils.isEmpty(pointPolicy.getPoint_policy_repeat_type()) && "每月".equals(pointPolicy.getPoint_policy_repeat_type())) {
                replayType = 1;
            } else if (!TextUtils.isEmpty(pointPolicy.getPoint_policy_repeat_type()) && "每年".equals(pointPolicy.getPoint_policy_repeat_type())) {
                replayType = 2;
            }

            return isEffectiveDate(nowDate, starDate, endDate, replayType);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime    当前时间
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param replayType 0 代表不重复  1 代表每个月重复 2 代表每年重复
     * @returnre
     */
    private static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime, int replayType) {
        Calendar nowdate = Calendar.getInstance();
        nowdate.setTime(nowTime);
        int nowMonth = nowdate.get(Calendar.MONTH);
        int nowDay = nowdate.get(Calendar.DAY_OF_MONTH);

        Calendar begindate = Calendar.getInstance();
        begindate.setTime(startTime);
        int beginMonth = begindate.get(Calendar.MONTH);
        int beginDay = begindate.get(Calendar.DAY_OF_MONTH);

        Calendar enddate = Calendar.getInstance();
        enddate.setTime(endTime);
        int endMonth = enddate.get(Calendar.MONTH);
        int endDay = enddate.get(Calendar.DAY_OF_MONTH);

        int isSpanMonth = endMonth - beginMonth;//是否跨月

        //每个月重复
        if (replayType == 1) {
            if (isSpanMonth == 1) {
                if (endDay >= beginDay) return true; //大过一个月

                int monthHavaDays = getMonthHavaDays(enddate.get(Calendar.YEAR), enddate.get(Calendar.MONTH));
                int beginmonthHavaDays = getMonthHavaDays(begindate.get(Calendar.YEAR), begindate.get(Calendar.MONTH));
                //开始日期是最后一天
                if (beginmonthHavaDays == beginDay) {
                    int nowHavadays = getMonthHavaDays(nowdate.get(Calendar.YEAR), nowdate.get(Calendar.MONTH));
                    if (nowDay == nowHavadays) return true; //今天也是最后一天
                    if (monthHavaDays == endDay) return true;//最后一天也是最后一天
                    //今天小于等于结束日期
                    return nowDay <= endDay;
                } else {
                    if (nowDay >= beginDay) return true;
                    if (monthHavaDays == endDay) return true;//最后一天也是最后一天
                    return nowDay <= endDay;
                }

            } else if (isSpanMonth > 1) {
                return true;
            } else {
                int monthHavaDays = getMonthHavaDays(enddate.get(Calendar.YEAR), enddate.get(Calendar.MONTH));
                if (monthHavaDays == endDay) {
                    //代表是月末最后一天
                    return nowDay >= beginDay;
                } else {
                    return nowDay >= beginDay || nowDay <= endDay;
                }
            }
        } else if (replayType == 2) {
            if (nowMonth < beginMonth || nowMonth > endMonth) return false;
            if (nowMonth > beginMonth && nowMonth < endMonth) return true;
            if (isSpanMonth >= 1) {
                int beginmonthHavaDays = getMonthHavaDays(begindate.get(Calendar.YEAR), begindate.get(Calendar.MONTH));
                int nowHavadays = getMonthHavaDays(nowdate.get(Calendar.YEAR), nowdate.get(Calendar.MONTH));
                int monthHavaDays = getMonthHavaDays(enddate.get(Calendar.YEAR), enddate.get(Calendar.MONTH));

                if (nowMonth == beginMonth) {
                    if (beginmonthHavaDays == beginDay) {
                        //今天也是最后一天
                        return nowDay == nowHavadays;
                    } else {
                        return nowDay >= beginDay;
                    }
                } else if (nowMonth == endMonth) {
                    if (monthHavaDays != endDay) {
                        return nowDay <= endDay;
                    } else {
                        if (nowDay == nowHavadays) return true; //今天也是最后一天
                    }
                }

                if (endDay >= beginDay) return true; //大过一个月
                //开始日期是最后一天
                if (beginmonthHavaDays == beginDay) {
                    if (nowDay == nowHavadays) return true; //今天也是最后一天
                    if (monthHavaDays == endDay) return true;//最后一天也是最后一天
                    //今天小于等于结束日期
                    return nowDay <= endDay;
                } else {
                    if (nowDay >= beginDay) return true;
                    if (monthHavaDays == endDay) return true;//最后一天也是最后一天
                    return nowDay <= endDay;
                }
            } else {
                int monthHavaDays = getMonthHavaDays(enddate.get(Calendar.YEAR), enddate.get(Calendar.MONTH));
                if (monthHavaDays == endDay) {
                    //代表是月末最后一天
                    return nowDay >= beginDay;
                } else {
                    return nowDay >= beginDay || nowDay <= endDay;
                }
            }

        } else {
            if (nowTime.getTime() == startTime.getTime()
                    || nowTime.getTime() == endTime.getTime()) {
                return true;
            }

            return nowdate.after(begindate) && nowdate.before(enddate);
        }
    }

    private static int getMonthHavaDays(int year, int month) {
        Calendar instance = Calendar.getInstance();
        instance.set(year, month, 0);
        return instance.get(Calendar.DAY_OF_MONTH);
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
    private static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
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

        return date.after(begin) && date.before(end);
    }
}
