package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.entity.PolicyPresent;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.model.VipCardConfig;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Iverson on 2019/1/4 9:28 AM
 * 此类用于：用于计算商品促销价和会员价
 */
public class PricePolicyPresentUseUtils {

    public static PolicyPresent getPolicyPresentUse(List<PosOrderDetail> posOrderDetails, PolicyPresent policyPresent) {

        if (!isSelectDay(policyPresent)) return null; //判断星期几包含了没有
        if (!currentTimeContain(policyPresent)) return null; //具体时间
        if (!currentDateContain(policyPresent)) return null;//具体日期

        if (policyPresent.getPolicy_present_card_only()) {
            if (LibConfig.activeVipMember == null) return null;


            VipCardConfig vipConfig = RetailPosManager.getInstance().getVipConfig(LibConfig.SYSTEM_BOOK);
            if (RetailPosManager.isOpenCrm() && vipConfig != null) {
                boolean isEnablePayDiscount = vipConfig.isEnableCardPayDiscount();//是否开启卡支付折扣参数
                boolean isCustomerDiscountType = vipConfig.isCustomerDiscountType();//是否身份等级
                if (!isEnablePayDiscount) {
                    if (isCustomerDiscountType) {
                        if (!TextUtils.isEmpty(policyPresent.getPolicy_present_level_ids())) {
                            return null;
                        }

                        if (!RetailPosManager.checkCrmLevelInPolicy(LibConfig.activeVipMember, policyPresent.getPolicy_present_level_ids())) {
                            return null;
                        }
                    }

                    if (!isCustomerDiscountType) {
                        if (TextUtils.isEmpty(policyPresent.getPolicy_present_card_type())) {
                            return null;
                        }
                        if (("<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                                "<消费卡类型列表/>").equals(policyPresent.getPolicy_present_level_ids())) {
                            return null;
                        }

                        if (!TextUtils.isEmpty(policyPresent.getPolicy_present_card_type())) {
                            if (!policyPresent.getPolicy_present_card_type().contains(">" + LibConfig.activeVipMember.getCard_user_type_name() + "<"))
                                return null;
                        }
                    }
                }else {
                    if (!RetailPosManager.checkCrmLevelInPolicy(LibConfig.activeVipMember, policyPresent.getPolicy_present_level_ids())) {
                        return null;
                    }
                    if (!TextUtils.isEmpty(policyPresent.getPolicy_present_card_type())) {
                        if (("<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                                "<消费卡类型列表/>").equals(policyPresent.getPolicy_present_card_type())) {
                        } else {
                            if (!policyPresent.getPolicy_present_card_type().contains(">" + LibConfig.activeVipMember.getCard_user_type_name() + "<"))
                                return null;
                        }
                    }
                }
            } else {
                if (!RetailPosManager.checkCrmLevelInPolicy(LibConfig.activeVipMember, policyPresent.getPolicy_present_level_ids())) {
                    return null;
                }
                if (!TextUtils.isEmpty(policyPresent.getPolicy_present_card_type())) {
                    if (("<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                            "<消费卡类型列表/>").equals(policyPresent.getPolicy_present_card_type())) {
                    } else {
                        if (!policyPresent.getPolicy_present_card_type().contains(">" + LibConfig.activeVipMember.getCard_user_type_name() + "<"))
                            return null;
                    }
                }
            }


//                if(RetailPosManager.isOpenCrm()){
//                    if(!RetailPosManager.checkCrmLevelInPolicy(LibConfig.activeVipMember,policyPresent.getPolicy_present_level_ids())){
//                        return null;
//                    }
////                }else {
//                    if (!TextUtils.isEmpty(policyPresent.getPolicy_present_card_type())) {
//                        if (("<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
//                                "<消费卡类型列表/>").equals(policyPresent.getPolicy_present_card_type())) {
//                        } else {
//                            if (!policyPresent.getPolicy_present_card_type().contains(">" + LibConfig.activeVipMember.getCard_user_type_name() + "<"))
//                                return null;
//                        }
//                    }
//                }

        }

        float goodsUseTotalCount = 0;

        for (PosOrderDetail posOrderDetail : posOrderDetails) {
            if (posOrderDetail.getItemNum() == policyPresent.getItem_num()) {
                if (!posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_PRESENT_NAME)) {
                    goodsUseTotalCount += posOrderDetail.getOrderDetailAmount();
                }
            }
        }

        if (goodsUseTotalCount >= policyPresent.getPolicy_present_sale_amount()) {
            policyPresent.setMultiple((int) (goodsUseTotalCount / policyPresent.getPolicy_present_sale_amount()));
            return policyPresent;
        }


        return null;
    }


    private static boolean isSelectDay(PolicyPresent policyPresent) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return policyPresent.getPolicy_present_sun_actived();
            case 2:
                return policyPresent.getPolicy_present_mon_actived();
            case 3:
                return policyPresent.getPolicy_present_tues_actived();
            case 4:
                return policyPresent.getPolicy_present_wed_actived();
            case 5:
                return policyPresent.getPolicy_present_thurs_actived();
            case 6:
                return policyPresent.getPolicy_present_friday_actived();
            case 7:
                return policyPresent.getPolicy_present_sat_actived();
            default:
                return false;
        }
    }

    private static boolean currentTimeContain(PolicyPresent policyPresent) {

        String startTime = policyPresent.getPolicy_present_time_from();
        String endTime = policyPresent.getPolicy_present_time_to();
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

    private static boolean currentDateContain(PolicyPresent policyPresent) {

        String startTime = policyPresent.getPolicy_present_date_from();
        String endTime = policyPresent.getPolicy_present_date_to();
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
