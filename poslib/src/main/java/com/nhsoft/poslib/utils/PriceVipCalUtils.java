package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.call.impl.BookResourceImpl;
import com.nhsoft.poslib.call.impl.PosItemImpl;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.PosItemGrade;
import com.nhsoft.poslib.entity.VipCrmAmaLevel;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.model.VipCardConfig;
import com.nhsoft.poslib.model.VipCardTypeBean;
import com.nhsoft.poslib.model.VipUserInfo;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Iverson on 2019/1/4 9:28 AM
 * 此类用于：用于计算商品促销价和会员价
 */
public class PriceVipCalUtils {




    /**
     * 获取会员价
     *
     * @param posOrderDetail
     * @param vipUserInfo
     * @return
     */
    public static Float getVipMemberPrice(PosOrderDetail posOrderDetail, VipUserInfo vipUserInfo) {
        if (!PosItemImpl.getInstance().goodsCanDiscount(posOrderDetail.getItemNum(), posOrderDetail.getItemGradeNum()))
            return Float.MAX_VALUE;

        if (!RetailPosManager.getInstance().isEnablePayDiscount()) return Float.MAX_VALUE;

        //消费折扣
        if (RetailPosManager.isOpenCrm()) {
            return getCrmVipPrice(posOrderDetail, vipUserInfo);
        }

        if (LibConfig.saleParamsBean.isEnableCardPayDiscount()) {
            return getCouponsVipPrice(vipUserInfo,posOrderDetail);
        } else {
            return getPayVipPrice(vipUserInfo,posOrderDetail,Float.MAX_VALUE);
        }
    }


    /**
     * 没有开启全渠道的消费折扣
     *
     * @return
     */
    private static float getCouponsVipPrice(VipUserInfo vipUserInfo, PosOrderDetail posOrderDetail) {
        VipCardTypeBean vipCardTypeBean  = BookResourceImpl.getInstance().getVipCardTypeBeanList
                (LibConfig.activeShiftTable.getSystemBookCode(), LibConfig.S_LOCAL_VIP_TYPE, vipUserInfo.getCard_user_type_name());

        float couponsDiscount = Float.MAX_VALUE;
        if (vipUserInfo != null && vipCardTypeBean != null) {
            if (!TextUtils.isEmpty(vipCardTypeBean.getDiscountPriceLevel()) && !TextUtils.isEmpty(vipCardTypeBean.getDiscountRate())) {
                try {
                    int level = Integer.parseInt(vipCardTypeBean.getDiscountPriceLevel());
                    PosItem posItem = posOrderDetail.getPosItem();
                    PosItemGrade posItemGrade = posOrderDetail.getPosItemGrade();
                    couponsDiscount = getFinalLevelPrice(posItem, posItemGrade, level) * Float.parseFloat(vipCardTypeBean.getDiscountRate());
                    if (couponsDiscount != 0) return couponsDiscount;
                } catch (NumberFormatException e) {

                }
            }
        }
        return  Float.MAX_VALUE;
    }

    /**
     * 开启全渠道消费折扣
     * @param posOrderDetail
     * @param vipUserInfo
     * @return
     */
    public static float getCrmVipPrice(PosOrderDetail posOrderDetail, VipUserInfo vipUserInfo) {
        float finalPrice = Float.MAX_VALUE;
        float birthPrice = Float.MAX_VALUE;
        float gradePrice = Float.MAX_VALUE;
        PosItem posItem = posOrderDetail.getPosItem();
        PosItemGrade posItemGrade = posOrderDetail.getPosItemGrade();

        if (vipUserInfo != null) {


            VipCardConfig vipConfig = RetailPosManager.getInstance().getVipConfig(LibConfig.SYSTEM_BOOK);
            if(vipConfig == null)return Float.MAX_VALUE;

            boolean isEnablePayDiscount = vipConfig.isEnableCardPayDiscount();//是否开启卡支付折扣参数
            boolean isCustomerDiscountType = vipConfig.isCustomerDiscountType();//是否身份等级
            //没有开启卡支付折扣并且是卡类型折扣
            if(!isEnablePayDiscount && !isCustomerDiscountType){

                if (checkBirthDay(vipUserInfo.getCard_user_birth_date())) {
                    try {
                        float birthBit = 0;
                        if (!TextUtils.isEmpty(vipUserInfo.getCard_user_type_birth_discount())) {
                            birthBit = Float.parseFloat(vipUserInfo.getCard_user_type_birth_discount());
                        }
                        if (birthBit > 0 && birthBit <= 1) {
                            birthPrice = posOrderDetail.getOrderDetailStdPrice() * birthBit;
                        }
                    } catch (NumberFormatException e) {

                    }
                }
                float cardCouponsPrice = getCouponsVipPrice(vipUserInfo, posOrderDetail);
                return cardCouponsPrice > birthPrice ? birthPrice : cardCouponsPrice;
            }

            VipCrmAmaLevel vipLevel = RetailPosManager.getVipLevel(vipUserInfo.getLevel());
            if (vipLevel == null) return finalPrice;

            float levelPrice = getFinalLevelPrice(posItem, posItemGrade, vipLevel.getPrice_level());
            if ("阶梯价".equals(vipLevel.getDiscount_type())) {
                gradePrice = levelPrice;
            } else if ("折扣价".equals(vipLevel.getDiscount_type())) {
                if (checkBirthDay(vipUserInfo.getBirth())) {
                    try {
                        float birthBit = vipLevel.getBirth_discount();
                        if (birthBit > 0 && birthBit <= 1) {
                            birthPrice = levelPrice * birthBit;
                        } else {
                            float price_discount = vipLevel.getPrice_discount();
                            if (price_discount > 0 && price_discount <= 1) {
                                gradePrice = levelPrice * price_discount;
                            }
                        }
                    } catch (Exception e) {

                    }
                } else {
                    float price_discount = vipLevel.getPrice_discount();
                    if (price_discount > 0 && price_discount <= 1) {
                        gradePrice = levelPrice * price_discount;
                    }
                }

            }

            finalPrice = birthPrice > gradePrice ? gradePrice : birthPrice;
        }

        return finalPrice;
    }





    /**
     * 获取支付折扣
     *
     * @param posOrderDetail
     * @param vipUserInfo
     * @return
     */
    public static Float getVipMemberPriceBySettle(PosOrderDetail posOrderDetail, VipUserInfo vipUserInfo) {
        if (!PosItemImpl.getInstance().goodsCanDiscount(posOrderDetail.getItemNum(), posOrderDetail.getItemGradeNum()))
            return Float.MAX_VALUE;
        if (!RetailPosManager.getInstance().isEnablePayDiscount()) return Float.MAX_VALUE;

        float vipMemberPrice = getVipMemberPrice(posOrderDetail, vipUserInfo);
        if (RetailPosManager.isOpenCrm()) {
            return getCrmPayVipPrice(vipUserInfo, posOrderDetail, vipMemberPrice);
        } else {
            return getPayVipPrice(vipUserInfo, posOrderDetail, vipMemberPrice);
        }
    }


    /**
     * 没有开启全渠道的支付折扣
     *
     * @return
     */
    private static float getPayVipPrice(VipUserInfo vipUserInfo, PosOrderDetail posOrderDetail, float vipMemberPrice) {
        float birthPrice = Float.MAX_VALUE;
        float vipPrice = Float.MAX_VALUE;
        float gradePrice = Float.MAX_VALUE;
        try {
            int level = (int) Float.parseFloat(vipUserInfo.getCard_user_type_price_level());
            PosItem posItem = posOrderDetail.getPosItem();
            PosItemGrade posItemGrade = posOrderDetail.getPosItemGrade();
            gradePrice = getFinalLevelPrice(posItem, posItemGrade, level);
        } catch (NumberFormatException e) {

        }


        if (checkBirthDay(vipUserInfo.getCard_user_birth_date())) {
            try {
                float birthBit = 0;
                if (!TextUtils.isEmpty(vipUserInfo.getCard_user_type_birth_discount())) {
                    birthBit = Float.parseFloat(vipUserInfo.getCard_user_type_birth_discount());
                }
                if (birthBit > 0 && birthBit <= 1) {
                    birthPrice = posOrderDetail.getOrderDetailStdPrice() * birthBit;
                }
            } catch (NumberFormatException e) {

            }
        }

        try {
            float vipBit = 0;
            if (!TextUtils.isEmpty(vipUserInfo.getCard_user_type_discount())) {
                vipBit = Float.parseFloat(vipUserInfo.getCard_user_type_discount());
            }

            if (vipBit > 0 && vipBit <= 1) {
                vipPrice = posOrderDetail.getOrderDetailStdPrice() * vipBit;
            }
        } catch (NumberFormatException e) {

        }
        if(vipMemberPrice < vipPrice){
            vipPrice = vipMemberPrice;
        }

        Float minValue = (birthPrice < vipPrice) ? birthPrice : vipPrice;
        minValue = (minValue < gradePrice) ? minValue : gradePrice;
        return minValue;
    }


    /**
     * 开启全渠道的支付折扣
     *
     * @return
     */
    private static float getCrmPayVipPrice(VipUserInfo vipUserInfo, PosOrderDetail posOrderDetail, float vipMemberPrice) {
        float birthPrice = Float.MAX_VALUE;
        float vipPrice = Float.MAX_VALUE;
        float gradePrice = Float.MAX_VALUE;

        try {
            int level = (int) Float.parseFloat(vipUserInfo.getCard_user_type_price_level());
            PosItem posItem = posOrderDetail.getPosItem();
            PosItemGrade posItemGrade = posOrderDetail.getPosItemGrade();
            gradePrice = getFinalLevelPrice(posItem, posItemGrade, level);
        } catch (NumberFormatException e) {

        }

        if (checkBirthDay(vipUserInfo.getCard_user_birth_date())) {
            try {
                float birthBit = 0;
                if (!TextUtils.isEmpty(vipUserInfo.getCard_user_type_birth_discount())) {
                    birthBit = Float.parseFloat(vipUserInfo.getCard_user_type_birth_discount());
                }
                if (birthBit > 0 && birthBit <= 1) {
                    birthPrice = gradePrice * birthBit;
                }
            } catch (NumberFormatException e) {

            }
        }

        try {
            float vipBit = 0;
            if (!TextUtils.isEmpty(vipUserInfo.getCard_user_type_discount())) {
                vipBit = Float.parseFloat(vipUserInfo.getCard_user_type_discount());
            }

            if (vipBit > 0 && vipBit <= 1) {
                vipPrice = gradePrice * vipBit;
            }
            if (vipMemberPrice < vipPrice) {
                vipPrice = vipMemberPrice;
            }
        } catch (NumberFormatException e) {

        }

        Float minValue = (birthPrice < vipPrice) ? birthPrice : vipPrice;
        minValue = (minValue < gradePrice) ? minValue : gradePrice;
        return minValue;
    }





    /**
     * 查看会员价优惠
     *
     * @param posOrderDetail
     * @param vipUserInfo
     * @return
     */
    public static Float getVipMemberPriceByCheck(PosOrderDetail posOrderDetail, VipUserInfo vipUserInfo) {
        if (!PosItemImpl.getInstance().goodsCanDiscount(posOrderDetail.getItemNum(), posOrderDetail.getItemGradeNum()))
            return Float.MAX_VALUE;
        if (!RetailPosManager.getInstance().isEnablePayDiscount()) return Float.MAX_VALUE;
        if (RetailPosManager.isOpenCrm()) {
            return getCrmPayVipPrice(vipUserInfo, posOrderDetail, Float.MAX_VALUE);
        } else {
            return getPayVipPrice(vipUserInfo, posOrderDetail, Float.MAX_VALUE);
        }

    }

    private static float getFinalLevelPrice(PosItem posItem, PosItemGrade posItemGrade, int level) {
        float vipPrice = 0;
        if (LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived()) {
            if (level == 2) {
                vipPrice = posItemGrade == null ? posItem.getBranch_level2_price() : posItemGrade.getBranch_grade_level2_price();
            } else if (level == 3) {
                vipPrice = posItemGrade == null ? posItem.getBranch_level3_price() : posItemGrade.getBranch_grade_level3_price();
            } else if (level == 4) {
                vipPrice = posItemGrade == null ? posItem.getBranch_level4_price() : posItemGrade.getBranch_grade_level4_price();
            } else if (level == 1) {
                vipPrice = posItemGrade == null ? posItem.getBranch_regular_price() : posItemGrade.getBranch_grade_regular_price();
            }

            if (vipPrice == 0) {
                vipPrice = posItem.getBranch_regular_price();
            }

            if (vipPrice == 0) {
                if (level == 2) {
                    vipPrice = posItemGrade == null ? posItem.getItem_level2_price() : posItemGrade.getItem_grade_level2_price();
                } else if (level == 3) {
                    vipPrice = posItemGrade == null ? posItem.getItem_level3_price() : posItemGrade.getItem_grade_level3_price();
                } else if (level == 4) {
                    vipPrice = posItemGrade == null ? posItem.getItem_level4_price() : posItemGrade.getItem_grade_level4_price();
                } else if (level == 1) {
                    vipPrice = posItemGrade == null ? posItem.getItem_regular_price() : posItemGrade.getItem_grade_regular_price();
                }

                if (vipPrice == 0) {
                    vipPrice = posItem.getItem_regular_price();
                }

                if (vipPrice == 0) return Float.MAX_VALUE;
            }
        } else {
//            if (level == 2) {
//                vipPrice = posItem.getItem_level2_price();
//            } else if (level == 3) {
//                vipPrice = posItem.getItem_level3_price();
//            } else if (level == 4) {
//                vipPrice = posItem.getItem_level4_price();
//            }

            if (level == 2) {
                vipPrice = posItemGrade == null ? posItem.getItem_level2_price() : posItemGrade.getItem_grade_level2_price();
            } else if (level == 3) {
                vipPrice = posItemGrade == null ? posItem.getItem_level3_price() : posItemGrade.getItem_grade_level3_price();
            } else if (level == 4) {
                vipPrice = posItemGrade == null ? posItem.getItem_level4_price() : posItemGrade.getItem_grade_level4_price();
            }

//            if (vipPrice == 0) {
////                vipPrice = posItem.getItem_regular_price();
////            }
            if (vipPrice == 0) {
                vipPrice = posItemGrade == null ? posItem.getItem_regular_price() : posItemGrade.getItem_grade_regular_price();
            }

            if (vipPrice == 0) return Float.MAX_VALUE;
        }

        return vipPrice;
    }


    public static boolean checkBirthDay(String birthDay) {
        if (!TextUtils.isEmpty(birthDay)) {
            Date birthDate = TimeUtil.getInstance().getPriceDateByString(birthDay);
            Date nowDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(birthDate);
            int birthmonth = calendar.get(Calendar.MONTH);   //获取月份，0表示1月份
            int birthday = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数

            calendar.setTime(nowDate);
            int nowmonth = calendar.get(Calendar.MONTH);   //获取月份，0表示1月份
            int nowday = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数

            if (birthmonth == nowmonth && birthday == nowday) {
                return true;
            }
        }
        return false;
    }
}
