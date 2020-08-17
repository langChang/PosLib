package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.call.impl.PosItemImpl;
import com.nhsoft.poslib.entity.PolicyQuantity;
import com.nhsoft.poslib.entity.PolicyQuantityDetail;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.model.VipCardConfig;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Iverson on 2019/1/4 9:28 AM
 * 此类用于：用于计算商品促销价和会员价
 */
public class PriceQuantityCalUtils {

    public static PolicyQuantity getPolicyQuantityPrice(List<PosOrderDetail> posOrderDetails, List<PolicyQuantity> policyQuantities) {

        if (policyQuantities == null || policyQuantities.size() == 0) return null;

        for (PolicyQuantity policyQuantity : policyQuantities) {

            if (!isSelectDay(policyQuantity)) continue; //判断星期几包含了没有
            if (!currentTimeContain(policyQuantity)) continue;  //具体时间
            if (!currentDateContain(policyQuantity)) continue;//具体日期

            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (!posOrderDetail.getPosItem().getItem_weight_flag() && "偶数特价".equals(policyQuantity.getPromotion_quantity_type()) && isContainGoods(policyQuantity, posOrderDetail) != null) {
                    posOrderDetail.setOrderDetailPolicyPromotionQuantityFlagTwo(true);
                }
            }

            if (policyQuantity.getPromotion_quantity_card_only()) {
                if (LibConfig.activeVipMember == null) continue;
                VipCardConfig vipConfig = RetailPosManager.getInstance().getVipConfig(LibConfig.SYSTEM_BOOK);
                if (RetailPosManager.isOpenCrm() && vipConfig != null) {
                    boolean isEnablePayDiscount = vipConfig.isEnableCardPayDiscount();//是否开启卡支付折扣参数
                    boolean isCustomerDiscountType = vipConfig.isCustomerDiscountType();//是否身份等级
                    if (!isEnablePayDiscount) {
                        if (isCustomerDiscountType) {
                            if (!TextUtils.isEmpty(policyQuantity.getPromotion_quantity_level_ids())) {
                                continue;
                            }

                            if (!RetailPosManager.checkCrmLevelInPolicy(LibConfig.activeVipMember, policyQuantity.getPromotion_quantity_level_ids())) {
                                continue;
                            }
                        }

                        if (!isCustomerDiscountType) {
                            if (TextUtils.isEmpty(policyQuantity.getPromotion_quantity_card_type())) {
                                continue;
                            }
                            if (("<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                                    "<消费卡类型列表/>").equals(policyQuantity.getPromotion_quantity_card_type())) {
                                continue;
                            }

                            if (!TextUtils.isEmpty(policyQuantity.getPromotion_quantity_card_type())) {
                                if (!policyQuantity.getPromotion_quantity_card_type().contains(">" + LibConfig.activeVipMember.getCard_user_type_name() + "<"))
                                    continue;
                            }
                        }

                    }
                } else {
                    if (!RetailPosManager.checkCrmLevelInPolicy(LibConfig.activeVipMember, policyQuantity.getPromotion_quantity_level_ids())) {
                        continue;
                    }
                    if (!TextUtils.isEmpty(policyQuantity.getPromotion_quantity_card_type())) {
                        if (("<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                                "<消费卡类型列表/>").equals(policyQuantity.getPromotion_quantity_card_type())) {
                        } else {
                            if (!policyQuantity.getPromotion_quantity_card_type().contains(">" + LibConfig.activeVipMember.getCard_user_type_name() + "<"))
                                continue;
                        }
                    }
                }


//
////                if(RetailPosManager.isOpenCrm()){
//                    if(!RetailPosManager.checkCrmLevelInPolicy(LibConfig.activeVipMember,policyQuantity.getPromotion_quantity_level_ids())){
//                        continue;
//                    }
////                }else {
//                    if (!TextUtils.isEmpty(policyQuantity.getPromotion_quantity_card_type())) {
//                        if (("<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
//                                "<消费卡类型列表/>").equals(policyQuantity.getPromotion_quantity_card_type())) {
//                        } else {
//                            if (!policyQuantity.getPromotion_quantity_card_type().contains(">" + LibConfig.activeVipMember.getCard_user_type_name() + "<"))
//                                continue;
//                        }
//                    }
//                }

            }

            Map<String, List<PosOrderDetail>> stringListMap = new HashMap<>();

            for (int j = 0; j < posOrderDetails.size(); j++) {

                PosOrderDetail posOrderDetail = posOrderDetails.get(j);

                posOrderDetail.setOrderDetailPolicyPromotionQuantityFlagTwo(false);
                if (!PosItemImpl.getInstance().goodsCanDiscount(posOrderDetail.getItemNum(), posOrderDetail.getItemGradeNum()))
                    continue;

                if (!TextUtils.isEmpty(posOrderDetail.getOrderDetailPolicyFid()) && !posOrderDetail.getOrderDetailPolicyPromotionQuantityFlag())
                    continue;

                if (posOrderDetail.getPosItem() == null) continue;

                if (posOrderDetail.getOrderDetailMemo() != null && posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_CHANGE_TAG))
                    continue;

                if (posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_PRESENT_NAME))
                    continue;

                if (posOrderDetail.getPosItem().getItem_weight_flag() && "偶数特价".equals(policyQuantity.getPromotion_quantity_type()))
                    continue;

                policyQuantity.setPolicyQuantityDetail(null);

                PolicyQuantityDetail containGoods = isContainGoods(policyQuantity, posOrderDetail);
                if (null == containGoods) continue;
                if (containGoods.getPromotion_quantity_detail_special_price() > posOrderDetail.getOrderDetailPrice()) {
                    if (policyQuantity.getPromotion_quantity_no().equals(posOrderDetail.getOrderDetailPolicyFid())) {
                        posOrderDetail.setOrderDetailPolicyPromotionQuantityFlag(false);
                        posOrderDetail.setOrderDetailPolicyFid(null);
                        continue;
                    }
                }


                posOrderDetail.setPolicyQuantityDetail(containGoods);

                if ("单品超量特价".equals(policyQuantity.getPromotion_quantity_type())) {
                    List<PosOrderDetail> posOrderDetailList = stringListMap.get(posOrderDetail.getItemNum() + "");
                    if (posOrderDetailList == null) {
                        posOrderDetailList = new ArrayList<>();
                        posOrderDetailList.add(posOrderDetail);
                        stringListMap.put(posOrderDetail.getItemNum() + "", posOrderDetailList);
                    } else {
                        posOrderDetailList.add(posOrderDetail);
                    }

                    float totalAmount = getTotalAmount(posOrderDetailList);
                    if (totalAmount >= containGoods.getPromotion_quantity_detail_min_amount()) {
                        for (PosOrderDetail posOrderDetail1 : posOrderDetailList) {
                            if (checkPolicyPriceUse(containGoods.getPromotion_quantity_detail_special_price(), posOrderDetail1)) {
                                if (containGoods.getPromotion_quantity_detail_special_price() > posOrderDetail1.getPosItem().getItem_min_price()) {
                                    posOrderDetail1.setOrderDetailPrice(containGoods.getPromotion_quantity_detail_special_price());
                                } else {
                                    posOrderDetail1.setOrderDetailPrice(posOrderDetail1.getPosItem().getItem_min_price());
                                }
                                posOrderDetail1.setOrderDetailPolicyPromotionQuantityFlag(true);
                                posOrderDetail1.setOrderDetailPolicyFid(containGoods.getPromotion_quantity_no());
                                TagUtils.removeVipTag(posOrderDetail1);
                                PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail1);
                            }
                        }
                    } else {
                        for (PosOrderDetail posOrderDetail1 : posOrderDetailList) {
                            posOrderDetail1.setOrderDetailPolicyPromotionQuantityFlag(false);
                            if (containGoods.getPromotion_quantity_no().equals(posOrderDetail1.getOrderDetailPolicyFid())) {
                                posOrderDetail1.setOrderDetailPolicyFid("");
                            }
                            PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail1);
                        }
                    }
                } else if ("偶数特价".equals(policyQuantity.getPromotion_quantity_type())) {
                    List<PosOrderDetail> posOrderDetailList = stringListMap.get(posOrderDetail.getItemNum() + "");
                    if (posOrderDetailList == null) {
                        posOrderDetailList = new ArrayList<>();
                        posOrderDetailList.add(posOrderDetail);
                        stringListMap.put(posOrderDetail.getItemNum() + "", posOrderDetailList);
                    } else {
                        posOrderDetailList.add(posOrderDetail);
                    }

                    for (int i = 0; i < posOrderDetailList.size(); i++) {
                        PosOrderDetail posOrderDetail1 = posOrderDetailList.get(i);
                        posOrderDetail1.setOrderDetailPolicyPromotionQuantityFlagTwo(true);
                        if (i % 2 == 0) {
                            posOrderDetail1.setOrderDetailPolicyPromotionQuantityFlag(false);
                            if (containGoods.getPromotion_quantity_no().equals(posOrderDetail1.getOrderDetailPolicyFid())) {
                                posOrderDetail1.setOrderDetailPolicyFid("");
                            }
                            PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail1);
                        } else {

                            if (checkPolicyPriceUse(containGoods.getPromotion_quantity_detail_special_price(), posOrderDetail1)) {
                                if (containGoods.getPromotion_quantity_detail_special_price() > posOrderDetail1.getPosItem().getItem_min_price()) {
                                    posOrderDetail1.setOrderDetailPrice(containGoods.getPromotion_quantity_detail_special_price());
                                } else {
                                    posOrderDetail1.setOrderDetailPrice(posOrderDetail1.getPosItem().getItem_min_price());
                                }
                                posOrderDetail1.setOrderDetailPolicyPromotionQuantityFlag(true);
                                posOrderDetail1.setOrderDetailPolicyFid(containGoods.getPromotion_quantity_no());
                                TagUtils.removeVipTag(posOrderDetail1);
                                PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail1);
                            }

                        }
                    }
                } else if ("范围超量特价".equals(policyQuantity.getPromotion_quantity_type())) {
                    Float promotion_quantity_min_amount = policyQuantity.getPromotion_quantity_min_amount();
                    if (promotion_quantity_min_amount == null) break;
                    PosOrderDetail clonePosOrderDeatil = null;


                    List<PosOrderDetail> posOrderDetailList = stringListMap.get("---");
                    if (posOrderDetailList == null) {
                        posOrderDetailList = new ArrayList<>();
                        posOrderDetailList.add(posOrderDetail);
                        stringListMap.put("---", posOrderDetailList);
                    } else {
                        posOrderDetailList.add(posOrderDetail);
                    }

                    float totalAmount = getTotalAmount(posOrderDetailList);
                    if (promotion_quantity_min_amount != 0 && totalAmount < promotion_quantity_min_amount) {
                        for (PosOrderDetail posOrderDetail1 : posOrderDetailList) {
                            posOrderDetail1.setOrderDetailPolicyPromotionQuantityFlag(false);
                            if (containGoods.getPromotion_quantity_no().equals(posOrderDetail1.getOrderDetailPolicyFid())) {
                                posOrderDetail1.setOrderDetailPolicyFid("");
                            }
                            PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail1);
                        }
                        continue;
                    }


                    if (policyQuantity.getPromotion_quantity_append() != null && policyQuantity.getPromotion_quantity_append()) {
                        if (promotion_quantity_min_amount == 0) {
                            promotion_quantity_min_amount = Float.MAX_VALUE;
                        } else {
                            float pow = ((int) (totalAmount / promotion_quantity_min_amount)) * promotion_quantity_min_amount;
                            if (pow > promotion_quantity_min_amount) {
                                promotion_quantity_min_amount = ((int) (totalAmount / promotion_quantity_min_amount)) * promotion_quantity_min_amount;
                            }
                        }

                        float current_quantity_amount = 0;
                        for (PosOrderDetail posOrderDetail1 : posOrderDetailList) {
                            PolicyQuantityDetail policyQuantityDetail = posOrderDetail1.getPolicyQuantityDetail();
                            if (policyQuantityDetail == null) continue;


                            if (promotion_quantity_min_amount > current_quantity_amount) {
                                if (posOrderDetail1.getOrderDetailAmount() + current_quantity_amount > promotion_quantity_min_amount) {
                                    if (promotion_quantity_min_amount - current_quantity_amount - posOrderDetail1.getOrderDetailAmount() < 0) {


                                        clonePosOrderDeatil = RetailPosManager.getInstance().copyPosOrderDetail(posOrderDetail1);
                                        if (!checkPolicyPriceUse(policyQuantityDetail.getPromotion_quantity_detail_special_price(), clonePosOrderDeatil)
                                                && !TextUtils.isEmpty(clonePosOrderDeatil.getOrderDetailMemo())
                                                && clonePosOrderDeatil.getOrderDetailMemo().contains(LibConfig.GOODS_VIP_TAG)) {
                                            clonePosOrderDeatil = null;
                                            continue;
                                        }
                                        clonePosOrderDeatil.setOrderDetailAmount(current_quantity_amount + posOrderDetail1.getOrderDetailAmount() - promotion_quantity_min_amount);
                                        clonePosOrderDeatil.setOrderDetailPolicyPromotionQuantityFlag(false);
                                        if (containGoods.getPromotion_quantity_no().equals(clonePosOrderDeatil.getOrderDetailPolicyFid())) {
                                            clonePosOrderDeatil.setOrderDetailPolicyFid("");
                                        }
                                        PosOrderCalcUtil.calcPosOrderDetail(clonePosOrderDeatil);


                                        posOrderDetail1.setOrderDetailAmount(promotion_quantity_min_amount - current_quantity_amount);
                                        current_quantity_amount += posOrderDetail1.getOrderDetailAmount();

                                        if (checkPolicyPriceUse(policyQuantityDetail.getPromotion_quantity_detail_special_price(), posOrderDetail1)) {
                                            if (policyQuantityDetail.getPromotion_quantity_detail_special_price() > posOrderDetail1.getPosItem().getItem_min_price()) {
                                                posOrderDetail1.setOrderDetailPrice(policyQuantityDetail.getPromotion_quantity_detail_special_price());
                                            } else {
                                                posOrderDetail1.setOrderDetailPrice(posOrderDetail1.getPosItem().getItem_min_price());
                                            }
                                            posOrderDetail1.setOrderDetailPolicyPromotionQuantityFlag(true);
                                            posOrderDetail1.setOrderDetailPolicyFid(policyQuantityDetail.getPromotion_quantity_no());
                                            TagUtils.removeVipTag(posOrderDetail1);
                                            PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail1);
                                        }


                                    } else {
                                        if (checkPolicyPriceUse(policyQuantityDetail.getPromotion_quantity_detail_special_price(), posOrderDetail1)) {
                                            current_quantity_amount += posOrderDetail1.getOrderDetailAmount();
                                            if (policyQuantityDetail.getPromotion_quantity_detail_special_price() > posOrderDetail1.getPosItem().getItem_min_price()) {
                                                posOrderDetail1.setOrderDetailPrice(policyQuantityDetail.getPromotion_quantity_detail_special_price());
                                            } else {
                                                posOrderDetail1.setOrderDetailPrice(posOrderDetail1.getPosItem().getItem_min_price());
                                            }
                                            posOrderDetail1.setOrderDetailPolicyPromotionQuantityFlag(true);
                                            posOrderDetail1.setOrderDetailPolicyFid(policyQuantityDetail.getPromotion_quantity_no());
                                            TagUtils.removeVipTag(posOrderDetail1);
                                            PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail1);
                                        }


                                    }

                                } else {
                                    if (checkPolicyPriceUse(policyQuantityDetail.getPromotion_quantity_detail_special_price(), posOrderDetail1)) {
                                        current_quantity_amount += posOrderDetail1.getOrderDetailAmount();
                                        if (policyQuantityDetail.getPromotion_quantity_detail_special_price() > posOrderDetail1.getPosItem().getItem_min_price()) {
                                            posOrderDetail1.setOrderDetailPrice(policyQuantityDetail.getPromotion_quantity_detail_special_price());
                                        } else {
                                            posOrderDetail1.setOrderDetailPrice(posOrderDetail1.getPosItem().getItem_min_price());
                                        }
                                        posOrderDetail1.setOrderDetailPolicyPromotionQuantityFlag(true);
                                        posOrderDetail1.setOrderDetailPolicyFid(policyQuantityDetail.getPromotion_quantity_no());
                                        TagUtils.removeVipTag(posOrderDetail1);
                                        PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail1);
                                    }
                                }
                            } else {
                                posOrderDetail1.setOrderDetailPolicyPromotionQuantityFlag(false);
                                if (containGoods.getPromotion_quantity_no().equals(posOrderDetail1.getOrderDetailPolicyFid())) {
                                    posOrderDetail1.setOrderDetailPolicyFid("");
                                }
                                PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail1);
                            }


                        }

                    } else {
                        if (totalAmount >= promotion_quantity_min_amount) {
                            for (PosOrderDetail posOrderDetail1 : posOrderDetailList) {
                                PolicyQuantityDetail policyQuantityDetail = posOrderDetail1.getPolicyQuantityDetail();
                                if (policyQuantityDetail == null) continue;

                                if (checkPolicyPriceUse(policyQuantityDetail.getPromotion_quantity_detail_special_price(), posOrderDetail1)) {
                                    if (policyQuantityDetail.getPromotion_quantity_detail_special_price() > posOrderDetail1.getPosItem().getItem_min_price()) {
                                        posOrderDetail1.setOrderDetailPrice(policyQuantityDetail.getPromotion_quantity_detail_special_price());
                                    } else {
                                        posOrderDetail1.setOrderDetailPrice(posOrderDetail1.getPosItem().getItem_min_price());
                                    }
                                    posOrderDetail1.setOrderDetailPolicyPromotionQuantityFlag(true);
                                    posOrderDetail1.setOrderDetailPolicyFid(policyQuantityDetail.getPromotion_quantity_no());
                                    TagUtils.removeVipTag(posOrderDetail1);
                                    PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail1);
                                }

                            }
                        } else {
                            for (PosOrderDetail posOrderDetail1 : posOrderDetailList) {
                                posOrderDetail1.setOrderDetailPolicyPromotionQuantityFlag(false);
                                if (containGoods.getPromotion_quantity_no().equals(posOrderDetail1.getOrderDetailPolicyFid())) {
                                    posOrderDetail1.setOrderDetailPolicyFid("");
                                }
                                PosOrderCalcUtil.calcPosOrderDetail(posOrderDetail1);
                            }
                        }
                    }


                    if (clonePosOrderDeatil != null) {
                        posOrderDetails.add(clonePosOrderDeatil);
                    }

                }
            }

        }
        return null;
    }

    private static float getTotalAmount(List<PosOrderDetail> posOrderDetails) {
        float totalAmount = 0;
        for (PosOrderDetail posOrderDetail : posOrderDetails) {
            totalAmount += posOrderDetail.getOrderDetailAmount();
        }

        return totalAmount;
    }

    /**
     * 判断是否在促销商品范围内
     *
     * @param policyQuantity
     * @param posOrderDetail
     * @return
     */
    private static PolicyQuantityDetail isContainGoods(PolicyQuantity policyQuantity, PosOrderDetail posOrderDetail) {
        List<PolicyQuantityDetail> policy_promotion_quantity_details = policyQuantity.getPolicy_promotion_quantity_details();
        if (policy_promotion_quantity_details != null && policy_promotion_quantity_details.size() > 0) {
            for (PolicyQuantityDetail policyQuantityDetail : policy_promotion_quantity_details) {
                if (posOrderDetail.getItemNum() == policyQuantityDetail.getItem_num()) {
                    return policyQuantityDetail;
                }
            }
        }
        return null;
    }

    /**
     * 比比促销价和商品售价
     *
     * @param promotion_quantity_detail_special_price
     * @param posOrderDetail
     * @return
     */
    private static boolean checkPolicyPriceUse(float promotion_quantity_detail_special_price, PosOrderDetail posOrderDetail) {
        if (promotion_quantity_detail_special_price <= posOrderDetail.getOrderDetailPrice()) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isSelectDay(PolicyQuantity policyQuantity) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return policyQuantity.getPromotion_quantity_sun_actived();
            case 2:
                return policyQuantity.getPromotion_quantity_mon_actived();
            case 3:
                return policyQuantity.getPromotion_quantity_tues_actived();
            case 4:
                return policyQuantity.getPromotion_quantity_wed_actived();
            case 5:
                return policyQuantity.getPromotion_quantity_thurs_actived();
            case 6:
                return policyQuantity.getPromotion_quantity_friday_actived();
            case 7:
                return policyQuantity.getPromotion_quantity_sat_actived();
            default:
                return false;
        }
    }

    private static boolean currentTimeContain(PolicyQuantity policyQuantity) {

        String startTime = policyQuantity.getPromotion_quantity_time_from();
        String endTime = policyQuantity.getPromotion_quantity_time_to();
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

    private static boolean currentDateContain(PolicyQuantity policyQuantity) {

        String startTime = policyQuantity.getPromotion_quantity_date_from();
        String endTime = policyQuantity.getPromotion_quantity_date_to();
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
