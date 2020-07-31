package com.nhsoft.poslib.call.impl;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.AmountPay;
import com.nhsoft.poslib.entity.BookResource;
import com.nhsoft.poslib.entity.CardDeposit;
import com.nhsoft.poslib.entity.CardDepositFailed;
import com.nhsoft.poslib.entity.PayStyleToCashBank;
import com.nhsoft.poslib.entity.RelatCard;
import com.nhsoft.poslib.entity.ReplaceCard;
import com.nhsoft.poslib.entity.VipCrmAmaLevel;
import com.nhsoft.poslib.entity.VipCrmPointRate;
import com.nhsoft.poslib.entity.VipStrangeGive;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.model.PosScaleStyleTypeBean;
import com.nhsoft.poslib.model.VipCardTypeBean;
import com.nhsoft.poslib.service.greendao.CardDepositDao;
import com.nhsoft.poslib.service.greendao.CardDepositFailedDao;
import com.nhsoft.poslib.service.greendao.RelatCardDao;
import com.nhsoft.poslib.service.greendao.ReplaceCardDao;
import com.nhsoft.poslib.utils.EvtLog;
import com.nhsoft.poslib.utils.MatterUtils;
import com.nhsoft.poslib.utils.StrangeAndGivMoneyUtils;
import com.nhsoft.poslib.utils.XmlParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PayStyleImpl {
    private static PayStyleImpl instance;

    public static PayStyleImpl getInstance() {
        if (instance == null) {
            instance = new PayStyleImpl();
        }
        return instance;
    }

    /**
     * 1、支持卡存款并且支持前台销售，
     * 2、如果 条件一中包括“现金”放在第一位,如果不包含，聚合支付放在第一位
     * 3、支付宝，微信支付，云闪付，移动和包支付，翼支付 合并为聚合支付
     *
     * @param list
     * @return
     */
    public List<PosScaleStyleTypeBean> getPayTypeList(List<PosScaleStyleTypeBean> list) {
        List<PosScaleStyleTypeBean> typeBeanList = new ArrayList<>();

        PosScaleStyleTypeBean posScaleStyleTypeBean = new PosScaleStyleTypeBean();
        PosScaleStyleTypeBean posScaleStyleTypeBean_1 = new PosScaleStyleTypeBean();
        posScaleStyleTypeBean_1.setPaymentTypeName("在线支付");

        boolean contoinCash = false;
        for (PosScaleStyleTypeBean mPosScaleStyleTypeBean : list) {
            EvtLog.e("PayStyleService:=" + mPosScaleStyleTypeBean.getPaymentTypeName() + "  type:=" + mPosScaleStyleTypeBean.getPosCardPaymentType());
            if (mPosScaleStyleTypeBean.getPosCardPaymentType().equals("1")) {// 支持卡存款 &&  支持前台销售

                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("支付宝")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("微信支付")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("云闪付")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("移动和包支付")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("翼支付")) {
                    continue;
                }

                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("储值卡")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("签单")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("零钱包")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("积分消费")) {
                    continue;
                }

                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("现金")) {
                    posScaleStyleTypeBean = mPosScaleStyleTypeBean;
                    contoinCash = true;
                    continue;
                }

                typeBeanList.add(mPosScaleStyleTypeBean);
            }


        }
        if (contoinCash) {
            typeBeanList.add(0, posScaleStyleTypeBean);
            typeBeanList.add(1, posScaleStyleTypeBean_1);
        } else {
            typeBeanList.add(0, posScaleStyleTypeBean_1);
        }

        return typeBeanList;
    }


    /**
     * 现金银行绑定支付方式
     * 1.现金不显示
     * 2.在线支付不显示
     * 3.允许POS前台使用为真
     * 4.支持前台销售为真
     * 5.支持卡存款为真
     * 6.应用门店为当前用户登陆门店
     *
     * @param list
     * @return
     */
    public List<PosScaleStyleTypeBean> getPayType2CashBankList(List<PosScaleStyleTypeBean> list) {
        List<PosScaleStyleTypeBean> typeBeanList = new ArrayList<>();

        PosScaleStyleTypeBean posScaleStyleTypeBean_1 = new PosScaleStyleTypeBean();
        posScaleStyleTypeBean_1.setPaymentTypeName("在线支付");
        posScaleStyleTypeBean_1.setPaymentTypeCode(String.valueOf(Integer.MAX_VALUE));
        for (PosScaleStyleTypeBean mPosScaleStyleTypeBean : list) {
            if (mPosScaleStyleTypeBean.getPosCardPaymentType().equals("1")
                    && mPosScaleStyleTypeBean.getPosOrderPaymentType().equals("1")) {// 支持卡存款 &&  支持前台销售

                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("现金")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("储值卡")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("签单")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("零钱包")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("积分消费")) {
                    continue;
                }

                //在线支付
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("支付宝")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("微信支付")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("云闪付")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("移动和包支付")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("翼支付")) {
                    continue;
                }


                typeBeanList.add(mPosScaleStyleTypeBean);
            }


        }
        typeBeanList.add(0,posScaleStyleTypeBean_1);

        return typeBeanList;
    }

    public List<PosScaleStyleTypeBean> getPayTypeSuccessionList(List<PosScaleStyleTypeBean> list, List<AmountPay> amountPays) {
        if (amountPays == null || list == null) {
            return null;
        }
        List<PosScaleStyleTypeBean> typeBeanList = new ArrayList<>();

        PosScaleStyleTypeBean posScaleStyleTypeBean = new PosScaleStyleTypeBean();
        PosScaleStyleTypeBean posScaleStyleTypeBean_1 = new PosScaleStyleTypeBean();
        posScaleStyleTypeBean_1.setPaymentTypeName("在线支付");
//        typeBeanList.add(0, posScaleStyleTypeBean);


        boolean contoinCash = false;
        for (int i = 0; i < amountPays.size(); i++) {
            if (amountPays.get(i).getName().equals("在线支付")) {
                typeBeanList.add(0, posScaleStyleTypeBean_1);
            }


            for (PosScaleStyleTypeBean mPosScaleStyleTypeBean : list) {
                EvtLog.e("PayStyleService:=" + mPosScaleStyleTypeBean.getPaymentTypeName() + "  type:=" + mPosScaleStyleTypeBean.getPosCardPaymentType());
                if (mPosScaleStyleTypeBean.getPosCardPaymentType().equals("1") &&
                        amountPays.get(i).getName().equals(mPosScaleStyleTypeBean.getPaymentTypeName())) {
                    if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("现金")) {
                        typeBeanList.add(0, mPosScaleStyleTypeBean);
                    } else {
                        typeBeanList.add(mPosScaleStyleTypeBean);
                    }

                }


            }
        }

        return typeBeanList;
    }


    /**
     * 1、支持卡存款并且支持前台销售，
     * 2、如果 条件一中包括“现金”放在第一位,如果不包含，聚合支付放在第一位
     * 3、支付宝，微信支付，云闪付，移动和包支付，翼支付 合并为聚合支付
     *
     * @param list
     * @return
     */
    public List<PosScaleStyleTypeBean> getSettlePayList(List<PosScaleStyleTypeBean> list) {
        List<PosScaleStyleTypeBean> typeBeanList = new ArrayList<>();

        PosScaleStyleTypeBean posScaleStyleTypeBean = new PosScaleStyleTypeBean();
        PosScaleStyleTypeBean posScaleStyleTypeBean_1 = new PosScaleStyleTypeBean();
        posScaleStyleTypeBean_1.setPaymentTypeName("在线支付");
        posScaleStyleTypeBean_1.setPaymentTypeCode(String.valueOf(Integer.MAX_VALUE));

        boolean contoinCash = false;
        for (PosScaleStyleTypeBean mPosScaleStyleTypeBean : list) {
            EvtLog.e("PayStyleService:=" + mPosScaleStyleTypeBean.getPaymentTypeName() + "  type:=" + mPosScaleStyleTypeBean.getPosCardPaymentType());
            if (mPosScaleStyleTypeBean.getPosOrderPaymentType().equals("1")) {//  支持前台销售

                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("支付宝")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("支付宝支付")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("微信支付")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("云闪付")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("移动和包支付")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("翼支付")) {
                    continue;
                }

                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("积分消费")) {
                    if (LibConfig.activeVipMember == null || LibConfig.sVipCardParams.getExchangePoint() == 0 || LibConfig.sVipCardParams.getExchangeMoney() == 0) {
                        continue;
                    }
                }

                if (LibConfig.activeClientUser == null && mPosScaleStyleTypeBean.getPaymentTypeName().equals("签单")) {
                    continue;
                }

                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("现金")) {
                    posScaleStyleTypeBean = mPosScaleStyleTypeBean;
                    contoinCash = true;
                    posScaleStyleTypeBean.setCurrent(true);
                    continue;
                }

                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("零钱包")) {
                    if (LibConfig.activeVipMember == null || !LibConfig.activeVipMember.isCard_user_change_enabled()) {
                        continue;
                    }
                }

                typeBeanList.add(mPosScaleStyleTypeBean);
            }


        }
        if (contoinCash) {
            typeBeanList.add(0, posScaleStyleTypeBean);
            typeBeanList.add(1, posScaleStyleTypeBean_1);
        } else {
            typeBeanList.add(0, posScaleStyleTypeBean_1);
        }

        return typeBeanList;
    }



    /**
     *
     *  零售版
     * 1、支持卡存款并且支持前台销售，
     * 2、如果 条件一中包括“现金”放在第一位,如果不包含，聚合支付放在第一位
     * 3、支付宝，微信支付，云闪付，移动和包支付，翼支付 合并为聚合支付
     *
     * @param list
     * @return
     */
    public List<PosScaleStyleTypeBean> getSettlePaySaleList(List<PosScaleStyleTypeBean> list) {
        List<PosScaleStyleTypeBean> typeBeanList = new ArrayList<>();

        PosScaleStyleTypeBean posScaleStyleTypeBean = new PosScaleStyleTypeBean();
        PosScaleStyleTypeBean posScaleStyleTypeBean_1 = new PosScaleStyleTypeBean();
        posScaleStyleTypeBean_1.setPaymentTypeName("在线支付");
        posScaleStyleTypeBean_1.setPaymentTypeCode(String.valueOf(Integer.MAX_VALUE));

        boolean contoinCash = false;
        boolean isHaveOnlinePay = false;
        VipCrmAmaLevel vipLevel = null;
        if (RetailPosManager.isOpenCrm() && LibConfig.activeVipMember != null ) {
            vipLevel = RetailPosManager.getVipLevel(LibConfig.activeVipMember.getLevel());
            if (vipLevel != null && !TextUtils.isEmpty(vipLevel.getPayment_types()) && vipLevel.getPayment_types().contains("第三方在线支付")) {
                isHaveOnlinePay = true;
            }
        }else {
            isHaveOnlinePay = true;
        }

        for (PosScaleStyleTypeBean mPosScaleStyleTypeBean : list) {
            EvtLog.e("PayStyleService:=" + mPosScaleStyleTypeBean.getPaymentTypeName() + "  type:=" + mPosScaleStyleTypeBean.getPosCardPaymentType());

            if (mPosScaleStyleTypeBean.getPosOrderPaymentType().equals("1")) {//  支持前台销售
                if (vipLevel != null && !TextUtils.isEmpty(vipLevel.getPayment_types()) && !vipLevel.getPayment_types().contains(mPosScaleStyleTypeBean.getPaymentTypeName())) {
                    continue;
                }



                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("支付宝")) {
                    if(!isHaveOnlinePay)isHaveOnlinePay=true;
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("支付宝支付")) {
                    if(!isHaveOnlinePay)isHaveOnlinePay=true;
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("微信支付")) {
                    if(!isHaveOnlinePay)isHaveOnlinePay=true;
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("云闪付")) {
                    if(!isHaveOnlinePay)isHaveOnlinePay=true;
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("移动和包支付")) {
                    if(!isHaveOnlinePay)isHaveOnlinePay=true;
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("翼支付")) {
                    if(!isHaveOnlinePay)isHaveOnlinePay=true;
                    continue;
                }

                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("积分消费")) {

                    if(RetailPosManager.isOpenCrm()){

                        if (LibConfig.activeVipMember == null ) {
                            continue;
                        }

                        if(TextUtils.isEmpty(LibConfig.activeVipMember.getPoint()) || Float.parseFloat(LibConfig.activeVipMember.getPoint()) <=0 ){
                            continue;
                        }


                        VipCrmPointRate bean = VipCrmPointRateImpl.getInstance().getBean();
                        if (bean == null){
                           continue;
                        }

                        if(bean.getPoint_value() == 0 || bean.getMoney_value() == 0){
                            continue;
                        }


                    }else {
                        if (LibConfig.activeVipMember == null || LibConfig.sVipCardParams.getExchangePoint() == 0 || LibConfig.sVipCardParams.getExchangeMoney() == 0) {
                            continue;
                        }
                    }

                }

                if (LibConfig.activeClientUser == null && mPosScaleStyleTypeBean.getPaymentTypeName().equals("签单")) {
                    continue;
                }

                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("现金")) {
                    posScaleStyleTypeBean = mPosScaleStyleTypeBean;
                    contoinCash = true;
                    posScaleStyleTypeBean.setCurrent(true);
                    continue;
                }

                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("零钱包")) {
                    if (LibConfig.activeVipMember == null ) {
                        continue;
                    }
                }

                if(LibConfig.C_PAYMENT_TYPE_PETCARD_NAME.equals(mPosScaleStyleTypeBean.getPaymentTypeName())){

                    if(LibConfig.activeVipMember != null && !LibConfig.saleParamsBean.isEnableCardPayDiscount()){
                        try {
                            final PosScaleStyleTypeBean clonePosBean  = (PosScaleStyleTypeBean) mPosScaleStyleTypeBean.clone();
                            clonePosBean.setGloableVip(true);
                            typeBeanList.add(clonePosBean);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                    mPosScaleStyleTypeBean.setGloableVip(false);
                    typeBeanList.add(mPosScaleStyleTypeBean);
                    continue;
                }

                typeBeanList.add(mPosScaleStyleTypeBean);
            }


        }
        if (contoinCash) {
            typeBeanList.add(0, posScaleStyleTypeBean);
            if(isHaveOnlinePay){
                typeBeanList.add(1, posScaleStyleTypeBean_1);
            }

        } else {
            if(isHaveOnlinePay){
                typeBeanList.add(0, posScaleStyleTypeBean_1);
            }
        }

        return typeBeanList;
    }


    /**
     *
     *  零售版
     * 1、支持卡存款并且支持前台销售，
     * 2、如果 条件一中包括“现金”放在第一位,如果不包含，聚合支付放在第一位
     * 3、支付宝，微信支付，云闪付，移动和包支付，翼支付 合并为聚合支付
     *
     * @param list
     * @return
     */
    public List<PosScaleStyleTypeBean> getSettleCardPayList(List<PosScaleStyleTypeBean> list) {
        List<PosScaleStyleTypeBean> typeBeanList = new ArrayList<>();
        if (RetailPosManager.isOpenCrm() && LibConfig.activeVipMember != null) {
            VipCrmAmaLevel vipLevel = RetailPosManager.getVipLevel(LibConfig.activeVipMember.getLevel());
            if (vipLevel != null && !TextUtils.isEmpty(vipLevel.getPayment_types()) && !vipLevel.getPayment_types().contains(LibConfig.C_PAYMENT_TYPE_PETCARD_NAME)) {
                return new ArrayList<>();
            }
        }
        for (PosScaleStyleTypeBean mPosScaleStyleTypeBean : list) {
            EvtLog.e("PayStyleService:=" + mPosScaleStyleTypeBean.getPaymentTypeName() + "  type:=" + mPosScaleStyleTypeBean.getPosCardPaymentType());
            if (mPosScaleStyleTypeBean.getPosOrderPaymentType().equals("1")) {//  支持前台销售

                if(LibConfig.C_PAYMENT_TYPE_PETCARD_NAME.equals(mPosScaleStyleTypeBean.getPaymentTypeName())){

                    if(LibConfig.activeVipMember != null || LibConfig.discountVipMember != null){
                        try {
                            final PosScaleStyleTypeBean clonePosBean  = (PosScaleStyleTypeBean) mPosScaleStyleTypeBean.clone();
                            clonePosBean.setGloableVip(true);
                            typeBeanList.add(clonePosBean);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }

            }


        }
        return typeBeanList;
    }

    /**
     * 判断是否有第三方支付
     *
     * @param longString
     * @param shortString
     * @return
     */
    public static boolean isContainAggregationPay(String longString, String shortString) {
        if (TextUtils.isEmpty(longString) || TextUtils.isEmpty(shortString)) return false;
        if (longString.equals(LibConfig.C_PAYMENT_TYPE_THIRD_PARTY_ONLINE) || longString.equals(LibConfig.C_PAYMENT_TYPE_ONLINE)) {
            if (shortString.contains("微信")) return true;
            if (shortString.contains("支付宝")) return true;
            if (shortString.contains(LibConfig.C_PAYMENT_TYPE_CLOUDFLASHPAY_NAME)) return true;
            if (shortString.contains(LibConfig.C_PAYMENT_TYPE_MOBILEPET_NAME)) return true;
            if (shortString.contains(LibConfig.C_PAYMENT_TYPE_YIPAY_NAME)) return true;

        }
        return false;
    }


    /**
     * 全部支付方式
     *
     * @param list
     * @return
     */
    public List<PosScaleStyleTypeBean> getPosScaleStyleTypeBeanList(List<PosScaleStyleTypeBean> list) {
        List<PosScaleStyleTypeBean> typeBeanList = new ArrayList<>();
        for (PosScaleStyleTypeBean mPosScaleStyleTypeBean : list) {
            typeBeanList.add(mPosScaleStyleTypeBean);
        }
        return typeBeanList;
    }


    /**
     * 续卡
     * 保存数据
     *
     * @param relatCard
     * @return
     */
    public boolean saveRelatCard(final RelatCard relatCard) {
        final RelatCardDao relatCardDao = DaoManager.getInstance().getDaoSession().getRelatCardDao();
        return MatterUtils.doMatter(relatCardDao, new Runnable() {
            @Override
            public void run() {
                relatCardDao.insertOrReplaceInTx(relatCard);
            }
        });
    }

    /***
     * 续卡
     * 删除过期的历史数据
     * @param date
     */
    public void deleteRelatCard(final String date) {
        final RelatCardDao relatCardDao = DaoManager.getInstance().getDaoSession().getRelatCardDao();
        List<RelatCard> relatCardList = relatCardDao.queryBuilder().where(RelatCardDao.Properties.Shift_table_bizday.le(date)).list();
        for (RelatCard relatCard : relatCardList) {
            relatCardDao.delete(relatCard);
        }
    }

    /**
     * 换卡
     * 保存数据
     *
     * @param replaceCard
     * @return
     */
    public boolean saveReplaceCard(final ReplaceCard replaceCard) {
        final ReplaceCardDao replaceCardDao = DaoManager.getInstance().getDaoSession().getReplaceCardDao();
        return MatterUtils.doMatter(replaceCardDao, new Runnable() {
            @Override
            public void run() {
                replaceCardDao.insertOrReplace(replaceCard);
            }
        });
    }

    /**
     * 换卡
     * 删除过期的历史数据
     *
     * @param date
     */
    public void deleteReplaceCard(final String date) {
        final ReplaceCardDao replaceCardDao = DaoManager.getInstance().getDaoSession().getReplaceCardDao();
        List<ReplaceCard> replaceCardList = replaceCardDao.queryBuilder().where(ReplaceCardDao.Properties.Shift_table_bizday.le(date)).list();
        for (ReplaceCard replaceCard : replaceCardList) {
            replaceCardDao.delete(replaceCard);
        }

    }

    /**
     * 储值成功
     *
     * @param cardDeposit
     * @return
     */
    public boolean saveCardDeposit(final CardDeposit cardDeposit) {
        final CardDepositDao cardDepositDao = DaoManager.getInstance().getDaoSession().getCardDepositDao();
        return MatterUtils.doMatter(cardDepositDao, new Runnable() {
            @Override
            public void run() {
                cardDepositDao.insertOrReplaceInTx(cardDeposit);
            }
        });
    }

    /***
     *
     */
    public void deleteCardDeposit(final String date) {
        final CardDepositDao cardDepositDao = DaoManager.getInstance().getDaoSession().getCardDepositDao();
        List<CardDeposit> cardDepositList = cardDepositDao.queryBuilder().where(CardDepositDao.Properties.Shift_table_bizday.le(date)).list();
        for (CardDeposit cardDeposit : cardDepositList) {
            cardDepositDao.delete(cardDeposit);
        }

    }


    /**
     * 储值失败 保存bean
     *
     * @param cardDepositFailed
     * @return
     */
    public boolean saveCardDeposit(final CardDepositFailed cardDepositFailed) {
        final CardDepositFailedDao cardDepositFailedDao = DaoManager.getInstance().getDaoSession().getCardDepositFailedDao();
        return MatterUtils.doMatter(cardDepositFailedDao, new Runnable() {
            @Override
            public void run() {
                cardDepositFailedDao.insertOrReplaceInTx(cardDepositFailed);
            }
        });
    }

    public boolean deleteCardDepositFailed(final String fid) {
        if (TextUtils.isEmpty(fid)){
            return true;
        }
        final CardDepositFailedDao cardDepositFailedDao = DaoManager.getInstance().getDaoSession().getCardDepositFailedDao();
        final CardDepositFailed cardDepositFailed = cardDepositFailedDao.queryBuilder().where(CardDepositFailedDao.Properties.Deposit_fid.eq(fid)).unique();
        if (cardDepositFailed == null){
            return true;
        }
        return MatterUtils.doMatter(cardDepositFailedDao, new Runnable() {
            @Override
            public void run() {
                cardDepositFailedDao.delete(cardDepositFailed);
            }
        });
    }

    //在
    public boolean changeCardDepositFailed(final String fid) {
        final CardDepositFailedDao cardDepositFailedDao = DaoManager.getInstance().getDaoSession().getCardDepositFailedDao();
        final CardDepositFailed cardDepositFailed = cardDepositFailedDao.queryBuilder().where(CardDepositFailedDao.Properties.Deposit_fid.eq(fid)).unique();
        return MatterUtils.doMatter(cardDepositFailedDao, new Runnable() {
            @Override
            public void run() {
                cardDepositFailed.setDeposit_source("no_cou_flag");
                cardDepositFailedDao.insertOrReplaceInTx(cardDepositFailed);
            }
        });
    }

    /**
     * 获取储值失败的bean的list
     *
     * @param systemBookCode
     * @param branchNum
     * @return
     */
    public List<CardDepositFailed> getCardDepositFailedList(String systemBookCode, int branchNum, int shiftTableNum) {
        CardDepositFailedDao cardDepositFailedDao = DaoManager.getInstance().getDaoSession().getCardDepositFailedDao();
        List<CardDepositFailed> cardDepositFaileds = cardDepositFailedDao.queryBuilder().where(
                CardDepositFailedDao.Properties.System_book_code.eq(systemBookCode),
                CardDepositFailedDao.Properties.Shift_table_num.eq(shiftTableNum),
                CardDepositFailedDao.Properties.Deposit_source.isNull(),
                CardDepositFailedDao.Properties.Branch_num.eq(branchNum)
        ).list();
        return cardDepositFaileds;
    }


    /**
     * 解析xml获取消费卡类型
     *
     * @param systemBookCode
     * @param typeName
     * @param type
     * @return
     */
    public VipCardTypeBean getVipCardTypeBean(String systemBookCode, String typeName, String type) {

        VipCardTypeBean vipCardTypeBean1 = null;
        //消费卡类型列表
        BookResource bookPosCardType = BookResourceImpl.getInstance().getBookPosSale(systemBookCode, typeName);
        if (bookPosCardType != null) {
            Gson gson = new Gson();
            String s = XmlParser.xml2json(bookPosCardType.getBookResourceParam());
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONObject object = jsonObject.optJSONObject("消费卡类型列表");
                JSONArray jsonArray = object.optJSONArray("消费卡类型");
                for (int i = 0; i < jsonArray.length(); i++) {
                    VipCardTypeBean vipCardTypeBean = gson.fromJson(jsonArray.optString(i), VipCardTypeBean.class);
                    if (type.equals(vipCardTypeBean.getName())) {
                        return vipCardTypeBean;
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return vipCardTypeBean1;
    }

    /**
     * 读取xml中 存款金额赠送
     *
     * @param systemBookCode
     * @return
     */
    public List<VipStrangeGive> getStrangeGiving(String systemBookCode) {
        List<VipStrangeGive> vipStrangeGiveList = new ArrayList<>();
        BookResource bookPosCardType = BookResourceImpl.getInstance().getBookPosSale(systemBookCode, "存款金额赠送");
        if (bookPosCardType != null) {
            String s = XmlParser.xml2json(bookPosCardType.getBookResourceParam());
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(s);
                JSONObject object = jsonObject.optJSONObject("存款金额赠送列表");
                if (object==null){
                    return null;
                }
                JSONArray jsonArray;
                jsonArray = object.optJSONArray("存款金额赠送");
                if (jsonArray == null) {
                    JSONObject jo = object.optJSONObject("存款金额赠送");
                    jsonArray = new JSONArray();
                    jsonArray.put(jo);
                }
                if (jsonArray == null) return null;
                for (int i = 0; i < jsonArray.length(); i++) {
                    EvtLog.d("vipStrangeGiveList :=  " + i);

                    JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                    if (jsonObject1.optString("状态").equals("失效")) continue;
                    VipStrangeGive vipStrangeGive = new VipStrangeGive();
                    vipStrangeGive.setNum(jsonObject1.optString("编号"));
                    vipStrangeGive.setUpperLimit(jsonObject1.optString("金额上限"));
                    vipStrangeGive.setLowerLimit(jsonObject1.optString("金额下限"));
                    vipStrangeGive.setGivingRate(jsonObject1.optString("赠送金额"));
                    vipStrangeGive.setRepeatActivity(jsonObject1.optString("重复活动"));
                    vipStrangeGive.setStratDate(jsonObject1.optString("开始日期"));
                    vipStrangeGive.setEndDate(jsonObject1.optString("结束日期"));
                    vipStrangeGive.setCycle(jsonObject1.optString("周期"));
                    vipStrangeGive.setState(jsonObject1.optString("状态"));

                    if (jsonObject1.opt("适用卡类型") != null && !jsonObject1.opt("适用卡类型").equals("")) {
                        List<VipStrangeGive.CardType> typeList = new ArrayList<>();

                        if (jsonObject1.optJSONObject("适用卡类型").optJSONArray("会员卡类型") != null) {
                            for (int j = 0; j < jsonObject1.optJSONObject("适用卡类型").optJSONArray("会员卡类型").length(); j++) {
                                VipStrangeGive.CardType cardType = new VipStrangeGive.CardType();
                                cardType.setCardTypeCode(jsonObject1.optJSONObject("适用卡类型").optJSONArray("会员卡类型").optJSONObject(j).optString("CardTypeName"));
                                cardType.setCardTypeName(jsonObject1.optJSONObject("适用卡类型").optJSONArray("会员卡类型").optJSONObject(j).optInt("CardTypeCode") + "");
                                typeList.add(cardType);
                            }
                        } else if (jsonObject1.optJSONObject("适用卡类型").optJSONObject("会员卡类型") != null) {
                            VipStrangeGive.CardType cardType = new VipStrangeGive.CardType();
                            cardType.setCardTypeCode(jsonObject1.optJSONObject("适用卡类型").optJSONObject("会员卡类型").optString("CardTypeName"));
                            cardType.setCardTypeName(jsonObject1.optJSONObject("适用卡类型").optJSONObject("会员卡类型").optInt("CardTypeCode") + "");
                            typeList.add(cardType);
                        }
                        vipStrangeGive.setApplyCardTypeList(typeList);
                    }

                    if (jsonObject1.opt("适用支付方式") != null && !jsonObject1.opt("适用支付方式").toString().equals("")) {
                        List<VipStrangeGive.PayType> payTypeList = new ArrayList<>();

                        if (jsonObject1.optJSONObject("适用支付方式").optJSONArray("支付方式") != null) {
                            for (int j = 0; j < jsonObject1.optJSONObject("适用支付方式").optJSONArray("支付方式").length(); j++) {
                                VipStrangeGive.PayType payType = new VipStrangeGive.PayType();
                                payType.setPaymentTypeName(jsonObject1.optJSONObject("适用支付方式").optJSONArray("支付方式").optJSONObject(j).optString("PaymentTypeName"));
                                payTypeList.add(payType);
                            }
                        } else if (jsonObject1.optJSONObject("适用支付方式").optJSONObject("支付方式") != null) {
                            VipStrangeGive.PayType payType = new VipStrangeGive.PayType();
                            payType.setPaymentTypeName(jsonObject1.optJSONObject("适用支付方式").optJSONObject("支付方式").optString("PaymentTypeName"));
                            payTypeList.add(payType);
                        }
                        vipStrangeGive.setApplyPayTypeList(payTypeList);
                    }


                    if (jsonObject1.opt("应用门店范围") != null && !jsonObject1.opt("应用门店范围").equals("")) {
                        ArrayList<VipStrangeGive.StoreHouse> storeHouses = new ArrayList<>();
                        if (jsonObject1.optJSONObject("应用门店范围").optJSONArray("应用门店") != null) {
                            for (int j = 0; j < jsonObject1.optJSONObject("应用门店范围").optJSONArray("应用门店").length(); j++) {
                                VipStrangeGive.StoreHouse storeHouse = new VipStrangeGive.StoreHouse();
                                storeHouse.setAppliedBranchName(jsonObject1.optJSONObject("应用门店范围").optJSONArray("应用门店").optJSONObject(j).optString("AppliedBranchName"));
                                storeHouse.setAppliedBranchNum(jsonObject1.optJSONObject("应用门店范围").optJSONArray("应用门店").optJSONObject(j).optInt("AppliedBranchNum") + "");
                                storeHouses.add(storeHouse);
                            }
                        } else if (jsonObject1.optJSONObject("应用门店范围").optJSONObject("应用门店") != null) {
                            VipStrangeGive.StoreHouse storeHouse = new VipStrangeGive.StoreHouse();
                            storeHouse.setAppliedBranchName(jsonObject1.optJSONObject("应用门店范围").optJSONObject("应用门店").optString("AppliedBranchName"));
                            storeHouse.setAppliedBranchNum(jsonObject1.optJSONObject("应用门店范围").optJSONObject("应用门店").optInt("AppliedBranchNum") + "");
                            storeHouses.add(storeHouse);
                        }
                        vipStrangeGive.setApplyStoreHouseList(storeHouses);
                    }

                    vipStrangeGiveList.add(vipStrangeGive);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return vipStrangeGiveList;
    }


    /**
     * 调用xml规则返还 储值赠送金额  made by ls
     *
     * @param systemBookCode
     * @param payMoney
     * @param branchNum
     * @param cardType
     * @param payType
     * @return idea
     * 1、应用卡类型
     * 2、应用支付方式
     * 3、应用门店
     * 4、时间范围
     * 5、储值金额范围
     * 赠金 = 赠率>=1?赠率：赠率*payMoney
     */
    public double strangeGivingMoney(String systemBookCode, String payMoney, String branchNum, String cardType, String payType) {
        double money = 0.00;
        if ("在线支付".equals(payType)) {
            payType = "第三方在线支付";
        }
        List<VipStrangeGive> vipStrangeGiveList = getStrangeGiving(systemBookCode);
        if (vipStrangeGiveList == null || vipStrangeGiveList.size() == 0) return money;
        for (VipStrangeGive vipStrangeGive : vipStrangeGiveList) {
            if ("失效".equals(vipStrangeGive.getState())) continue;

            double upperMoney = Double.parseDouble(vipStrangeGive.getUpperLimit());
            double lowerMoney = Double.parseDouble(vipStrangeGive.getLowerLimit());
            if (Double.parseDouble(payMoney) > upperMoney || Double.parseDouble(payMoney) < lowerMoney)
                continue;
            if (!StrangeAndGivMoneyUtils.getInstance().getInActive(vipStrangeGive)) continue;

            double rate = Double.parseDouble(vipStrangeGive.getGivingRate());

            List<String> payTypeNameList = new ArrayList<>();
            List<String> cardTypeNameList = new ArrayList<>();
            List<String> houseNameList = new ArrayList<>();
            if (vipStrangeGive.getApplyPayTypeList() != null)
                for (VipStrangeGive.PayType typ : vipStrangeGive.getApplyPayTypeList()) {
                    payTypeNameList.add(typ.getPaymentTypeName());
                }
            if (vipStrangeGive.getApplyCardTypeList() != null)
                for (VipStrangeGive.CardType typ : vipStrangeGive.getApplyCardTypeList()) {
                    cardTypeNameList.add(typ.getCardTypeCode());
                }
            if (vipStrangeGive.getApplyStoreHouseList() != null)
                for (VipStrangeGive.StoreHouse typ : vipStrangeGive.getApplyStoreHouseList()) {
                    houseNameList.add(typ.getAppliedBranchName());
                }


            if (cardTypeNameList.size() == 0 && payTypeNameList.size() > 0 && houseNameList.size() > 0) {
                if (payTypeNameList.contains(payType) && houseNameList.contains(branchNum)) {

                    if (rate >= 1) {
                        money = rate;
                    } else {
                        money = rate * Double.parseDouble(payMoney);
                    }
                    return money;
                }
            } else if (cardTypeNameList.size() > 0 && payTypeNameList.size() == 0 && houseNameList.size() > 0) {
                if (cardTypeNameList.contains(cardType) && houseNameList.contains(branchNum)) {

                    if (rate >= 1) {
                        money = rate;
                    } else {
                        money = rate * Double.parseDouble(payMoney);
                    }
                    return money;
                }
            } else if (cardTypeNameList.size() > 0 && payTypeNameList.size() > 0 && houseNameList.size() == 0) {
                if (cardTypeNameList.contains(cardType) && payTypeNameList.contains(payType)) {

                    if (rate >= 1) {
                        money = rate;
                    } else {
                        money = rate * Double.parseDouble(payMoney);
                    }
                    return money;
                }
            } else if (cardTypeNameList.size() == 0 && payTypeNameList.size() == 0 && houseNameList.size() > 0) {
                if (houseNameList.contains(branchNum)) {
                    if (rate >= 1) {
                        money = rate;
                    } else {
                        money = rate * Double.parseDouble(payMoney);
                    }
                    return money;
                }
            } else if (cardTypeNameList.size() > 0 && payTypeNameList.size() == 0 && houseNameList.size() == 0) {
                if (cardTypeNameList.contains(cardType)) {
                    if (rate >= 1) {
                        money = rate;
                    } else {
                        money = rate * Double.parseDouble(payMoney);
                    }
                    return money;
                }
            } else if (cardTypeNameList.size() == 0 && payTypeNameList.size() > 0 && houseNameList.size() == 0) {
                if (payTypeNameList.contains(payType)) {
                    if (rate >= 1) {
                        money = rate;
                    } else {
                        money = rate * Double.parseDouble(payMoney);
                    }
                    return money;
                }
            } else if (cardTypeNameList.size() == 0 && payTypeNameList.size() == 0 && houseNameList.size() == 0) {

                if (rate >= 1) {
                    money = rate;
                } else {
                    money = rate * Double.parseDouble(payMoney);
                }
                return money;

            } else if (cardTypeNameList.size() > 0 && payTypeNameList.size() > 0 && houseNameList.size() > 0) {
                if (cardTypeNameList.contains(cardType) && payTypeNameList.contains(payType) && houseNameList.contains(branchNum)) {

                    if (rate >= 1) {
                        money = rate;
                    } else {
                        money = rate * Double.parseDouble(payMoney);
                    }
                    return money;
                }
            }


        }


        return money;
    }


    /**
     * 获取支付方式的现金银行
     * @return
     */
    public String getPayStyleBankNum(String payby){
        String account_bank_num="";
        if (payby.equals("现金")){
            account_bank_num= AccountBankImpl.getInstance().getBean().getAccount_bank_num()+"";
        }else {
            PayStyleToCashBank beanByName = PayStyleToCashBankImpl.getInstance().getBeanByName(payby);
            if (beanByName==null){
            }else {
                account_bank_num=beanByName.getAccountBankCode() ;
            }
        }
        return account_bank_num;//现金银行编号
    }



    /**
     * 1、支持卡存款并且支持前台销售，
     * 2、如果 条件一中包括“现金”放在第一位,如果不包含，聚合支付放在第一位
     * 3、支付宝，微信支付，云闪付，移动和包支付，翼支付 合并为聚合支付
     *
     * @param list
     * @return
     */
    public List<String> getPayTypeNameList(List<PosScaleStyleTypeBean> list) {
        List<String> typeBeanList = new ArrayList<>();

        PosScaleStyleTypeBean posScaleStyleTypeBean = new PosScaleStyleTypeBean();
        PosScaleStyleTypeBean posScaleStyleTypeBean_1 = new PosScaleStyleTypeBean();
        posScaleStyleTypeBean_1.setPaymentTypeName("在线支付");

        boolean contoinCash = false;
        for (PosScaleStyleTypeBean mPosScaleStyleTypeBean : list) {
//            EvtLog.e("PayStyleService:=" + mPosScaleStyleTypeBean.getPaymentTypeName() + "  type:=" + mPosScaleStyleTypeBean.getPosCardPaymentType());
            if (mPosScaleStyleTypeBean.getPosCardPaymentType().equals("1") && mPosScaleStyleTypeBean.getPosOrderPaymentType().equals("1")) {// 支持卡存款 &&  支持前台销售

                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("支付宝")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("微信支付")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("云闪付")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("移动和包支付")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("翼支付")) {
                    continue;
                }

                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("储值卡")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("签单")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("零钱包")) {
                    continue;
                }
                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("积分消费")) {
                    continue;
                }

                if (mPosScaleStyleTypeBean.getPaymentTypeName().equals("现金")) {
                    posScaleStyleTypeBean = mPosScaleStyleTypeBean;
                    contoinCash = true;
                    continue;
                }

                typeBeanList.add(mPosScaleStyleTypeBean.getPaymentTypeName());
            }


        }
        if (contoinCash) {
            typeBeanList.add(0, posScaleStyleTypeBean.getPaymentTypeName());
            typeBeanList.add(1, posScaleStyleTypeBean_1.getPaymentTypeName());
        } else {
            typeBeanList.add(0, posScaleStyleTypeBean_1.getPaymentTypeName());
        }

        return typeBeanList;
    }


}
