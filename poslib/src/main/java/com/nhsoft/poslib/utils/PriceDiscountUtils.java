package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.entity.PolicyDiscount;
import com.nhsoft.poslib.entity.PolicyDiscountDetail;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.call.impl.PosItemImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Iverson on 2019/1/4 9:28 AM
 * 此类用于：用于计算商品促销价和会员价
 */
public class PriceDiscountUtils {

    public static List<PolicyDiscount> getPolicyDiscountPrice(PosOrderDetail posOrderDetail, List<PolicyDiscount> policyDiscounts) {
        if (!PosItemImpl.getInstance().goodsCanDiscount(posOrderDetail.getItemNum(), posOrderDetail.getItemGradeNum()))
            return null;
        if(posOrderDetail.getOrderDetailPolicyPromotionFlag() || posOrderDetail.getOrderDetailPolicyDiscountFlag() || !TextUtils.isEmpty(posOrderDetail.getOrderDetailPolicyFid())){
            return null;
        }
        List<PolicyDiscount> policyDiscountList = posOrderDetail.getPolicyDiscounts();
        if(policyDiscountList == null){
            policyDiscountList = new ArrayList<>();
        }


        if(posOrderDetail.getOrderDetailMemo() != null && posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_CHANGE_TAG))return null;
        out: for (PolicyDiscount policyDiscount : policyDiscounts) {
            if (!isSelectDay(policyDiscount)) continue; //判断星期几包含了没有
            if (!currentTimeContain(policyDiscount)) continue;  //具体时间
            if (!currentDateContain(policyDiscount)) continue;//具体日期

            if (policyDiscount.getPolicy_discount_card_only()) {
                if (LibConfig.activeVipMember == null) continue;

//                if(RetailPosManager.isOpenCrm()){
                   if(!RetailPosManager.checkCrmLevelInPolicy(LibConfig.activeVipMember,policyDiscount.getPolicy_discount_level_ids())){
                       continue;
                   }
//                }else {
                    if (!TextUtils.isEmpty(policyDiscount.getPolicy_discount_card_type())) {
                        if (("<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                                "<消费卡类型列表/>").equals(policyDiscount.getPolicy_discount_card_type())) {
                        } else {
                            if (!policyDiscount.getPolicy_discount_card_type().contains(">" + LibConfig.activeVipMember.getCard_user_type_name() + "<"))
                                continue;
                        }
                    }
//                }

            }

            if("指定商品".equals(policyDiscount.getPolicy_discount_assigned_type())){
                List<PolicyDiscountDetail> policy_promotion_details = policyDiscount.getPolicy_discount_details();
                for (PolicyDiscountDetail policyDiscountDetail : policy_promotion_details){
                    if(posOrderDetail.getItemNum() == policyDiscountDetail.getItem_num()){
                        policyDiscountList.add(policyDiscount);
                        continue out;
                    }
                }
            }else if("指定类别".equals(policyDiscount.getPolicy_discount_assigned_type())){
                if(!TextUtils.isEmpty(policyDiscount.getPolicy_discount_assigned_category())){
                    String item_category_code = posOrderDetail.getPosItem().getItem_category_code();
                    String[] splitCategory = policyDiscount.getPolicy_discount_assigned_category().split(",");
                    boolean inUsable = false;
                    for (String string : splitCategory) {
                        if (item_category_code.equals(string)) {
                            inUsable = true;
                            break;
                        }
                    }
                    if (!inUsable) continue;
                    List<PolicyDiscountDetail> policy_promotion_details = policyDiscount.getPolicy_discount_details();
                    for (PolicyDiscountDetail policyDiscountDetail : policy_promotion_details){
                        if(posOrderDetail.getItemNum() == policyDiscountDetail.getItem_num()){
                            inUsable = false;
                            break;
                        }
                    }
                    if(inUsable){
                        policyDiscountList.add(policyDiscount);
                        continue out;
                    }
                }

            }else if("全场".equals(policyDiscount.getPolicy_discount_assigned_type())){
                boolean inUsable = true;
                List<PolicyDiscountDetail> policy_promotion_details = policyDiscount.getPolicy_discount_details();
                for (PolicyDiscountDetail policyDiscountDetail : policy_promotion_details){
                    if(posOrderDetail.getItemNum() == policyDiscountDetail.getItem_num()){
                        inUsable = false;
                        break;
                    }
                }

                if(inUsable){
                    policyDiscountList.add(policyDiscount);
                    continue out;
                }
            }
        }
        return policyDiscountList;
    }

    private static boolean isSelectDay(PolicyDiscount policyDiscount) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return policyDiscount.getPolicy_discount_sun_actived();
            case 2:
                return policyDiscount.getPolicy_discount_mon_actived();
            case 3:
                return policyDiscount.getPolicy_discount_tues_actived();
            case 4:
                return policyDiscount.getPolicy_discount_wed_actived();
            case 5:
                return policyDiscount.getPolicy_discount_thurs_actived();
            case 6:
                return policyDiscount.getPolicy_discount_friday_actived();
            case 7:
                return policyDiscount.getPolicy_discount_sat_actived();
            default:
                return false;
        }
    }

    private static boolean currentTimeContain(PolicyDiscount policyDiscount) {

        String startTime = policyDiscount.getPolicy_discount_time_from();
        String endTime = policyDiscount.getPolicy_discount_time_to();
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

    private static boolean currentDateContain(PolicyDiscount policyDiscount) {

        String startTime = policyDiscount.getPolicy_discount_date_from();
        String endTime = policyDiscount.getPolicy_discount_date_to();
        String nowTime = TimeUtil.getInstance().stampToDate(System.currentTimeMillis());

        String format = "yyyy-MM-dd";
        try {
            String nowString = nowTime.substring(0, nowTime.length() - 8);
            String startString = startTime.substring(0, startTime.length() - 8);
            String endString = endTime.substring(0, endTime.length() - 8);

            Date nowDate = new SimpleDateFormat(format).parse(nowString);
            Date starDate = new SimpleDateFormat(format).parse(startString);
            Date endDate = new SimpleDateFormat(format).parse(endString);
            int replayType  = 0;
            if(!TextUtils.isEmpty(policyDiscount.getPolicy_discount_repeat_type()) && "每月".equals(policyDiscount.getPolicy_discount_repeat_type())){
                replayType = 1;
            }else if(!TextUtils.isEmpty(policyDiscount.getPolicy_discount_repeat_type()) && "每年".equals(policyDiscount.getPolicy_discount_repeat_type())){
                replayType = 2;
            }
            if (isEffectiveDate(nowDate, starDate, endDate, replayType)) {
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
                    if (nowDay <= endDay) return true;//今天小于等于结束日期
                    return false;
                } else {
                    if (nowDay >= beginDay) return true;
                    if (monthHavaDays == endDay) return true;//最后一天也是最后一天
                    if (nowDay <= endDay) return true;
                    return false;
                }

            } else if (isSpanMonth > 1) {
                return true;
            } else {
                int monthHavaDays = getMonthHavaDays(enddate.get(Calendar.YEAR), enddate.get(Calendar.MONTH));
                if (monthHavaDays == endDay) {
                    //代表是月末最后一天
                    if (nowDay >= beginDay) return true;
                    return false;
                } else {
                    if (nowDay >= beginDay || nowDay <= endDay) return true;
                    return false;
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
                        if (nowDay == nowHavadays) return true; //今天也是最后一天
                        return false;
                    } else {
                        if (nowDay >= beginDay) return true;
                        return false;
                    }
                } else if (nowMonth == endMonth) {
                    if (monthHavaDays != endDay) {
                        if (nowDay <= endDay) return true;
                        return false;
                    } else {
                        if (nowDay == nowHavadays) return true; //今天也是最后一天
                    }
                }

                if (endDay >= beginDay) return true; //大过一个月
                //开始日期是最后一天
                if (beginmonthHavaDays == beginDay) {
                    if (nowDay == nowHavadays) return true; //今天也是最后一天
                    if (monthHavaDays == endDay) return true;//最后一天也是最后一天
                    if (nowDay <= endDay) return true;//今天小于等于结束日期
                    return false;
                } else {
                    if (nowDay >= beginDay) return true;
                    if (monthHavaDays == endDay) return true;//最后一天也是最后一天
                    if (nowDay <= endDay) return true;
                    return false;
                }
            } else {
                int monthHavaDays = getMonthHavaDays(enddate.get(Calendar.YEAR), enddate.get(Calendar.MONTH));
                if (monthHavaDays == endDay) {
                    //代表是月末最后一天
                    if (nowDay >= beginDay) return true;
                    return false;
                } else {
                    if (nowDay >= beginDay || nowDay <= endDay) return true;
                    return false;
                }
            }

        } else {
            if (nowTime.getTime() == startTime.getTime()
                    || nowTime.getTime() == endTime.getTime()) {
                return true;
            }

            if (nowdate.after(begindate) && nowdate.before(enddate)) {
                return true;
            } else {
                return false;
            }
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
}
