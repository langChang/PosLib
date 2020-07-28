package com.nhsoft.poslib.model;

import com.nhsoft.poslib.entity.AmountPay;

import java.util.List;

/**
 * Created by Iverson on 2020/7/1 10:16 AM
 * 此类用于：
 */
public class ShiftTableTotal {

    public int saleNum;   // 销售单数
    public float saleMoney;//销售金额
    public int exitNum;    //退款单数
    public float exitMoney; //退款金额
    public int couponNum;    //消费券单数
    public float couponMoney; //消费券金额

    public int openCardNum;  //开卡单数
    public float openCardMoney;//开卡金额
    public int  strangeNum;    //存款单数
    public float strangeMoney; //存款金额
    public int   replaceCardNum;//韩卡单数
    public float replaceCardMuney;//换卡金额
    public int   renewCardNum;    //续卡单数
    public float renewCardMoney;  //续卡金额

    public float receiveAndPay;//收支合计

    public float goodAmount;   //商品合计
    public float vipDiscountMoney; //会员
    public float modifyDiscountMoney;//手改
    public float promotionDiscountMoney;//促销
    public float couponDiscountMoney;//优惠券
    public float managerDiscountMoney;//经理折扣
    public float saleAmount;//销售汇总
    public float receiveAmountMoney;//收款总金额
    public float roundAmount;//四舍五入金额
    public float feeNum;//赠送数

    public List<AmountPay> paymentList;
    public String          bizday;//营业日

    public int cardChangeNum;//零钱包单数
    public float cardChangeMoney;//零钱包金额


    public float real_cash_receive; //现金实收
    public float cash_marks;
    public int   cardRrmRegisterNum;//CRM 开卡次数
    public float cardRrmRegisterMoney;//CRM 开卡金额

    public int othersNum;
    public float othersMoney;

}
