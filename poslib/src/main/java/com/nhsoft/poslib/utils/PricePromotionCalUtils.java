package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.call.impl.PosItemImpl;
import com.nhsoft.poslib.entity.PolicyPromotion;
import com.nhsoft.poslib.entity.PolicyPromotionDetail;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.model.PromotionResponsBean;
import com.nhsoft.poslib.model.VipCardConfig;
import com.nhsoft.poslib.model.VipUserInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Iverson on 2019/1/4 9:28 AM
 * 此类用于：用于计算商品促销价和会员价
 */
public class PricePromotionCalUtils {

    /**
     * @param posOrderDetail
     * @param policyPromotionList
     * @param isSettle            是否是支付折扣计算
     * @return
     */
    public static PolicyPromotion getPolicypromotionPrice(PosOrderDetail posOrderDetail, List<PolicyPromotion> policyPromotionList, boolean isSettle) {
        if (!PosItemImpl.getInstance().goodsCanDiscount(posOrderDetail.getItemNum(), posOrderDetail.getItemGradeNum()))
            return null;

        for (PolicyPromotion policyPromotion : policyPromotionList) {
            //会员只能使用一次
            if (policyPromotion.getPolicy_promotion_card_once()) {
                if (LibConfig.activeVipMember != null || LibConfig.discountVipMember != null) {
                    VipUserInfo vipUserInfo = null;
                    if (LibConfig.activeVipMember != null) {
                        vipUserInfo = LibConfig.activeVipMember;
                    } else if (LibConfig.discountVipMember != null) {
                        vipUserInfo = LibConfig.discountVipMember;
                    }
                    if (LibConfig.sVipEnjoyPromotion.size() > 0) {
                        String promotionId = "";
                        if (!TextUtils.isEmpty(vipUserInfo.getCustomer_id())) {
                            VipCardConfig vipConfig = RetailPosManager.getInstance().getVipConfig(LibConfig.SYSTEM_BOOK);
                            if (RetailPosManager.isOpenCrm() && vipConfig != null) {
                                boolean isEnablePayDiscount = vipConfig.isEnableCardPayDiscount();//是否开启卡支付折扣参数
                                boolean isCustomerDiscountType = vipConfig.isCustomerDiscountType();//是否身份等级
                                if(!isEnablePayDiscount && !isCustomerDiscountType && !TextUtils.isEmpty(vipUserInfo.getCard_user_num())){
                                    promotionId = LibConfig.sVipEnjoyPromotion.get(vipUserInfo.getCard_user_num());
                                }else {
                                    promotionId = LibConfig.sVipEnjoyPromotion.get(vipUserInfo.getCustomer_id());
                                }
                            }else {
                                promotionId = LibConfig.sVipEnjoyPromotion.get(vipUserInfo.getCustomer_id());
                            }
                        } else {
                            promotionId = LibConfig.sVipEnjoyPromotion.get(vipUserInfo.getCard_user_num());
                        }
                        if (promotionId != null && promotionId.equals(policyPromotion.getPolicy_promotion_no())) {
                            continue;
                        }
                    }
                } else {
                    continue;
                }

            }

            if (LibConfig.allPromotionLimit != null && LibConfig.allPromotionFoundLimit != null && LibConfig.allPromotionLimit.contains(policyPromotion.getPolicy_promotion_no())) {
                if (!NetUtil.getInstance().isNetworkConnected(RetailPosManager.sContext)) continue;
                boolean isUse = true;
                for (PromotionResponsBean foundBean : LibConfig.allPromotionFoundLimit) {
                    if (policyPromotion.getPolicy_promotion_no().equals(foundBean.getPolicy_fid())) {
                        if (policyPromotion.getPolicy_promotion_total_limit() != null && policyPromotion.getPolicy_promotion_total_limit() - foundBean.getPolicy_sale_amount() <= 0) {
                            isUse = false;
                            break;
                        }
                    }
                }
                if (!isUse) continue;
            }
            if (!isSelectDay(policyPromotion)) continue; //判断星期几包含了没有
            if (!currentTimeContain(policyPromotion)) continue;  //具体时间
            if (!currentDateContain(policyPromotion)) continue;//具体日期

            if (policyPromotion.getPolicy_promotion_card_only()) {
                if (LibConfig.activeVipMember == null && LibConfig.discountVipMember == null)
                    continue;

                VipCardConfig vipConfig = RetailPosManager.getInstance().getVipConfig(LibConfig.SYSTEM_BOOK);
                if (RetailPosManager.isOpenCrm() && vipConfig != null) {
                    boolean isEnablePayDiscount = vipConfig.isEnableCardPayDiscount();//是否开启卡支付折扣参数
                    boolean isCustomerDiscountType = vipConfig.isCustomerDiscountType();//是否身份等级
                    if (!isEnablePayDiscount) {
                        if (isCustomerDiscountType) {
                            if (!TextUtils.isEmpty(policyPromotion.getPolicy_promotion_level_ids())) {
                                continue;
                            }

                            if (!RetailPosManager.checkCrmLevelInPolicy(LibConfig.activeVipMember, policyPromotion.getPolicy_promotion_level_ids())) {
                                continue;
                            }
                        }

                        if (!isCustomerDiscountType) {
                            if (TextUtils.isEmpty(policyPromotion.getPolicy_promotion_card_type())) {
                                continue;
                            }
                            if (("<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                                    "<消费卡类型列表/>").equals(policyPromotion.getPolicy_promotion_level_ids())) {
                                continue;
                            }

                            if (!TextUtils.isEmpty(policyPromotion.getPolicy_promotion_card_type())) {
                                if (!policyPromotion.getPolicy_promotion_card_type().contains(">" + LibConfig.activeVipMember.getCard_user_type_name() + "<"))
                                    continue;
                            }
                        }
                    }else {
                        if (!TextUtils.isEmpty(policyPromotion.getPolicy_promotion_card_type())) {
                            if(!isSettle)continue;
                            if (("<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                                    "<消费卡类型列表/>").equals(policyPromotion.getPolicy_promotion_card_type())) {
                            } else {
                                if (!policyPromotion.getPolicy_promotion_card_type().contains(">" + LibConfig.activeVipMember.getCard_user_type_name() + "<"))
                                    continue;
                            }
                        }
                        if (!RetailPosManager.checkCrmLevelInPolicy(LibConfig.activeVipMember, policyPromotion.getPolicy_promotion_level_ids())) {
                            continue;
                        }
                    }
                } else {
                    if (!RetailPosManager.checkCrmLevelInPolicy(LibConfig.activeVipMember, policyPromotion.getPolicy_promotion_level_ids())) {
                        continue;
                    }
                    if (!TextUtils.isEmpty(policyPromotion.getPolicy_promotion_card_type())) {
                        if (("<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                                "<消费卡类型列表/>").equals(policyPromotion.getPolicy_promotion_card_type())) {
                        } else {
                            if (!policyPromotion.getPolicy_promotion_card_type().contains(">" + LibConfig.activeVipMember.getCard_user_type_name() + "<"))
                                continue;
                        }
                    }
                }


//                if (RetailPosManager.isOpenCrm()) {
//                    if (!RetailPosManager.checkCrmLevelInPolicy(LibConfig.activeVipMember == null ? LibConfig.discountVipMember : LibConfig.activeVipMember, policyPromotion.getPolicy_promotion_level_ids())) {
//                        continue;
//                    }
//
//                    if (!TextUtils.isEmpty(policyPromotion.getPolicy_promotion_card_type())) {
//                        if (("<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
//                                "<消费卡类型列表/>").equals(policyPromotion.getPolicy_promotion_card_type())) {
//                        } else {
//
//                            VipCardConfig vipConfig = RetailPosManager.getInstance().getVipConfig(LibConfig.SYSTEM_BOOK);
//                            boolean isEnablePayDiscount = vipConfig.isEnableCardPayDiscount();//是否开启卡支付折扣参数
//                            boolean isCustomerDiscountType = vipConfig.isCustomerDiscountType();//是否身份等级
//                            if(vipConfig != null && isEnablePayDiscount  && !isCustomerDiscountType && !isSettle) {
//                                continue;
//                            } else {
//                                if(LibConfig.activeVipMember != null){
//                                    if (!policyPromotion.getPolicy_promotion_card_type().contains(">" + LibConfig.activeVipMember.getCard_user_type_name() + "<"))
//                                        continue;
//                                }else if(LibConfig.discountVipMember != null){
//                                    if (!policyPromotion.getPolicy_promotion_card_type().contains(">" + LibConfig.discountVipMember.getCard_user_type_name() + "<"))
//                                        continue;
//                                }
//                            }
//                        }
//                    }
//
//                } else {
//                    if (!TextUtils.isEmpty(policyPromotion.getPolicy_promotion_card_type())) {
//                        if (("<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
//                                "<消费卡类型列表/>").equals(policyPromotion.getPolicy_promotion_card_type())) {
//                        } else {
//                            if (!policyPromotion.getPolicy_promotion_card_type().contains(">" + LibConfig.activeVipMember.getCard_user_type_name() + "<"))
//                                continue;
//                        }
//                    }
//                }

            }
            List<PolicyPromotionDetail> policy_promotion_details = policyPromotion.getPolicy_promotion_details();
            //全场折扣
            if (policyPromotion.isPolicy_promotion_all()) {
                if (!TextUtils.isEmpty(policyPromotion.getPolicy_promotion_except_item()) && policyPromotion.getPolicy_promotion_except_item().contains("Key=\"" + posOrderDetail.getItemNum())) {
                    continue;
                }
                return policyPromotion;
            } else if (!TextUtils.isEmpty(policyPromotion.getPolicy_promotion_item_category())) {

                if (!TextUtils.isEmpty(policyPromotion.getPolicy_promotion_except_item()) && policyPromotion.getPolicy_promotion_except_item().contains("Key=\"" + posOrderDetail.getItemNum())) {
                    continue;
                }
                //类别折扣
                if (posOrderDetail.getPosItem() == null) return null;
                String item_category_code = posOrderDetail.getPosItem().getItem_category_code();
                String[] splitCategory = policyPromotion.getPolicy_promotion_item_category().split(",");
                boolean inUsable = false;
                for (String string : splitCategory) {
                    if (item_category_code.equals(string)) {
                        inUsable = true;
                        break;
                    }
                }
                if (!inUsable) continue;
                return policyPromotion;
            } else {
                if (policy_promotion_details != null && policy_promotion_details.size() > 0) {
                    for (PolicyPromotionDetail promotionDetail : policy_promotion_details) {
                        if (posOrderDetail.getItemNum() == promotionDetail.getItem_num() && posOrderDetail.getItemGradeNum() == promotionDetail.getItem_grade_num()) {
                            if (policyPromotion.getPolicy_promotion_discount() > 0 && policyPromotion.getPolicy_promotion_discount() < 1) {
                                promotionDetail.setPolicy_promotion_detail_special_price(posOrderDetail.getOrderDetailStdPrice() * policyPromotion.getPolicy_promotion_discount());
                            }
                            policyPromotion.setPolicyPromotionDetail(promotionDetail);
                            return policyPromotion;
                        }
                    }
                }
            }
        }
        return null;
    }


    public static boolean isSelectDay(PolicyPromotion policyPromotion) {
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

    public static boolean currentTimeContain(PolicyPromotion policyPromotion) {

        String startTime = policyPromotion.getPolicy_promotion_time_from();
        String endTime = policyPromotion.getPolicy_promotion_time_to();
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

    public static boolean currentDateContain(PolicyPromotion policyPromotion) {

        String startTime = policyPromotion.getPolicy_promotion_date_from();
        String endTime = policyPromotion.getPolicy_promotion_date_to();
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
            if (!TextUtils.isEmpty(policyPromotion.getPolicy_promotion_repeat_type()) && "每月".equals(policyPromotion.getPolicy_promotion_repeat_type())) {
                replayType = 1;
            } else if (!TextUtils.isEmpty(policyPromotion.getPolicy_promotion_repeat_type()) && "每年".equals(policyPromotion.getPolicy_promotion_repeat_type())) {
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
