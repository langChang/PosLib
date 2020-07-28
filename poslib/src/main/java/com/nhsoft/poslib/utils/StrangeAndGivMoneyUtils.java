package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.nhsoft.poslib.entity.PolicyPromotion;
import com.nhsoft.poslib.entity.VipStrangeGive;
import com.nhsoft.poslib.model.VipUserInfo;
import com.nhsoft.poslib.entity.order.PosOrderDetail;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Iverson on 2019/1/4 9:28 AM
 * 此类用于：用于计算商品促销价和会员价
 */
public class StrangeAndGivMoneyUtils {

    private static StrangeAndGivMoneyUtils instance;
    public static StrangeAndGivMoneyUtils getInstance(){
        if (instance==null){
            instance=new StrangeAndGivMoneyUtils();
        }
        return instance;
    }
    public boolean getInActive( VipStrangeGive policyPromotion){
        // 0 代表不重复  1 代表每个月重复 2 代表每年重复
        int type=0;
        if (policyPromotion.getRepeatActivity().equals("0")){

        }else if (policyPromotion.getRepeatActivity().equals("1")){
            if (policyPromotion.getCycle().equals("每月")){
                type=1;
            }else if (policyPromotion.getCycle().equals("每年")){
                type=2;
            }
        }
        return currentDateContain(policyPromotion,type); //具体日期  currentTimeContain(policyPromotion)&&
    }



    public  Float getVipMemberPrice(PosOrderDetail posOrderDetail, VipUserInfo vipUserInfo) {
        Float birthPrice = Float.MAX_VALUE;
        Float vipPrice = Float.MAX_VALUE;
        Float gradePrice = Float.MAX_VALUE;
        if (!TextUtils.isEmpty(vipUserInfo.getCard_user_birth_date())) {
            Date birthDate = TimeUtil.getInstance().getPriceDateByString(vipUserInfo.getCard_user_birth_date());
            Date nowDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(birthDate);
            int birthmonth = calendar.get(Calendar.MONTH);   //获取月份，0表示1月份
            int birthday = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数

            calendar.setTime(nowDate);
            int nowmonth = calendar.get(Calendar.MONTH);   //获取月份，0表示1月份
            int nowday = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数

            if (birthmonth == nowmonth && birthday == nowday) {
                try {
                    float birthBit = Float.parseFloat(vipUserInfo.getCard_user_type_birth_discount());
                    if (birthBit > 0 && birthBit < 1) {
                        birthPrice = posOrderDetail.getOrderDetailStdPrice() * birthBit;
                    }
                } catch (NumberFormatException e) {

                }
            }
            try {
                float vipBit = Float.parseFloat(vipUserInfo.getCard_user_type_discount());
                if (vipBit > 0 && vipBit < 1) {
                    vipPrice = posOrderDetail.getOrderDetailStdPrice() * vipBit;
                }
            } catch (NumberFormatException e) {

            }

            try {
                int level = Integer.parseInt(vipUserInfo.getCard_user_type_price_level());
                if (level == 2) {
                    gradePrice = posOrderDetail.getPosItem().getBranch_level2_price();
                } else if (level == 3) {
                    gradePrice = posOrderDetail.getPosItem().getBranch_level3_price();
                } else if (level == 4) {
                    gradePrice = posOrderDetail.getPosItem().getBranch_level4_price();
                } else if (level == 0) {
                    gradePrice = posOrderDetail.getOrderDetailStdPrice();
                }
                if (gradePrice == 0) {
                    gradePrice = Float.MAX_VALUE;
                }
            } catch (NumberFormatException e) {

            }
            Float minValue = (birthPrice < vipPrice) ? birthPrice : vipPrice;
            minValue = (minValue < gradePrice) ? minValue : gradePrice;
            return minValue;
        }
        return Float.MAX_VALUE;
    }


    private  boolean isSelectDay(PolicyPromotion policyPromotion) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return policyPromotion.getPolicy_promotion_sun_actived();
            case 2:
                return policyPromotion.getPolicy_promotion_mon_actived();
            case 3:
                return policyPromotion.getPolicy_promotion_tues_actived();
            case 4:
                return policyPromotion.getPolicy_promotion_wed_actived();
            case 5:
                return policyPromotion.getPolicy_promotion_thurs_actived();
            case 6:
                return policyPromotion.getPolicy_promotion_friday_actived();
            case 7:
                return policyPromotion.getPolicy_promotion_sat_actived();
            default:
                return false;
        }
    }

    private  boolean currentTimeContain(VipStrangeGive policyPromotion) {

        String startTime = policyPromotion.getStratDate();
        String endTime = policyPromotion.getEndDate();
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

    public   boolean currentDateContain(VipStrangeGive policyPromotion,int replayType) {

        String startTime = policyPromotion.getStratDate();
        String endTime = policyPromotion.getEndDate();
        String nowTime = TimeUtil.getInstance().stampToDate(System.currentTimeMillis());
        if(TextUtils.isEmpty(startTime)){
            return true;
        }

        String format = "yyyy-MM-dd";
        try {
            String nowString = nowTime.substring(0, nowTime.length() - 8);
            String startString = startTime.substring(0,4)+"-"+startTime.substring(4,6)+"-"+startTime.substring(6,8);
            String endString =endTime.substring(0,4)+"-"+endTime.substring(4,6)+"-"+endTime.substring(6,8);

            Date nowDate = new SimpleDateFormat(format).parse(nowString);
            Date starDate = new SimpleDateFormat(format).parse(startString);
            Date endDate = new SimpleDateFormat(format).parse(endString);
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
                }else {
                    if(nowDay >= beginDay)return true;
                    if (monthHavaDays == endDay) return true;//最后一天也是最后一天
                    if(nowDay <= endDay)return true;
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
            if(nowMonth < beginMonth || nowMonth > endMonth)return false;
            if(nowMonth > beginMonth && nowMonth < endMonth)return true;
            if(isSpanMonth >= 1){
                int beginmonthHavaDays = getMonthHavaDays(begindate.get(Calendar.YEAR), begindate.get(Calendar.MONTH));
                int nowHavadays = getMonthHavaDays(nowdate.get(Calendar.YEAR), nowdate.get(Calendar.MONTH));
                int monthHavaDays = getMonthHavaDays(enddate.get(Calendar.YEAR), enddate.get(Calendar.MONTH));

                if(nowMonth == beginMonth){
                    if(beginmonthHavaDays == beginDay){
                        if (nowDay == nowHavadays) return true; //今天也是最后一天
                        return false;
                    }else {
                        if(nowDay >= beginDay)return true;
                        return false;
                    }
                }else if(nowMonth == endMonth){
                    if(monthHavaDays != endDay){
                       if(nowDay <= endDay)return true;
                       return false;
                    }else {
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
                }else {
                    if(nowDay >= beginDay)return true;
                    if (monthHavaDays == endDay) return true;//最后一天也是最后一天
                    if(nowDay <= endDay)return true;
                    return false;
                }
            }else {
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

            if (startTime.getTime()<nowTime.getTime()&&endTime.getTime()>nowTime.getTime()) {
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
