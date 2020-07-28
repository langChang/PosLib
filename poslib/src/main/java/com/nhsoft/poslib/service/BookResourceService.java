package com.nhsoft.poslib.service;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.nhsoft.poslib.dao.UserDao;
import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.BookResource;
import com.nhsoft.poslib.entity.OtherPayment;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.model.VipCardTypeBean;
import com.nhsoft.poslib.service.greendao.BookResourceDao;
import com.nhsoft.poslib.utils.MatterUtils;
import com.nhsoft.poslib.utils.XmlParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2018/11/19 5:38 PM
 * 此类用于：
 */
public class BookResourceService {

    private static BookResourceService instance;

    public static BookResourceService getInstance() {
        if (instance == null) {
            instance = new BookResourceService();
        }
        return instance;
    }

    public static boolean saveBookResource(final List<BookResource> dataLis) {
        final BookResourceDao mBookResourceDao = DaoManager.getInstance().getDaoSession().getBookResourceDao();
        mBookResourceDao.deleteAll();
        if (dataLis.size() == 0) return true;
        boolean isSuccess = MatterUtils.doMatter(mBookResourceDao, new Runnable() {
            @Override
            public void run() {
                for (BookResource bookResource : dataLis) {
                    if (!LibConfig.S_LOCAL_COUPONS_STYLE.equals(bookResource.getBookResourceName())) {
                        mBookResourceDao.insertOrReplaceInTx(bookResource);
                    } else {
//                        Log.e("bookresouce",bookResource.getBookResourceParam());
                        UserDao.saveCouponsXml(bookResource);
                    }

                }

            }
        });
        return isSuccess;
    }

    public BookResource getBookPosSale(String systemCode, String payScale) {
        if (!LibConfig.S_LOCAL_COUPONS_STYLE.equals(payScale)) {
            final BookResourceDao mBookResourceDao = DaoManager.getInstance().getDaoSession().getBookResourceDao();
            List<BookResource> list = mBookResourceDao.queryBuilder().where(BookResourceDao.Properties.BookResourceName.eq(payScale), BookResourceDao.Properties.SystemBookCode.eq(systemCode)).list();
            if (list.size() > 0) {
                return list.get(0);
            }

            if (LibConfig.S_PAY_SCALE_STYLE.equals(payScale)) {
                BookResource bookResource = new BookResource();
                bookResource.setBookResourceName(LibConfig.S_PAY_SCALE_STYLE);
                bookResource.setBookResourceParam("<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                        "<PosPaymentTypeList><PosPaymentType><PaymentTypeCode>001</PaymentTypeCode><PaymentTypeName>现金</PaymentTypeName><PaymentTypeMemo></PaymentTypeMemo><POSPaymentType>1</POSPaymentType><PaymentNeedCheck>0</PaymentNeedCheck><POSOrderPaymentType>1</POSOrderPaymentType><POSCardPaymentType>1</POSCardPaymentType><POSPaymentEqualSource>0</POSPaymentEqualSource><EnableEjectCashBox>1</EnableEjectCashBox></PosPaymentType><PosPaymentType><PaymentTypeCode>002</PaymentTypeCode><PaymentTypeName>银联卡</PaymentTypeName><PaymentTypeMemo></PaymentTypeMemo><POSPaymentType>1</POSPaymentType><PaymentNeedCheck>1</PaymentNeedCheck><POSOrderPaymentType>1</POSOrderPaymentType><POSCardPaymentType>1</POSCardPaymentType><POSPaymentEqualSource>0</POSPaymentEqualSource><EnableEjectCashBox>0</EnableEjectCashBox></PosPaymentType><PosPaymentType><PaymentTypeCode>003</PaymentTypeCode><PaymentTypeName>积分消费</PaymentTypeName><PaymentTypeMemo></PaymentTypeMemo><POSPaymentType>1</POSPaymentType><PaymentNeedCheck>1</PaymentNeedCheck><POSOrderPaymentType>1</POSOrderPaymentType><POSCardPaymentType>1</POSCardPaymentType><POSPaymentEqualSource>0</POSPaymentEqualSource><EnableEjectCashBox>0</EnableEjectCashBox></PosPaymentType><PosPaymentType><PaymentTypeCode>004</PaymentTypeCode><PaymentTypeName>储值卡</PaymentTypeName><PaymentTypeMemo></PaymentTypeMemo><POSPaymentType>1</POSPaymentType><PaymentNeedCheck>1</PaymentNeedCheck><POSOrderPaymentType>1</POSOrderPaymentType><POSCardPaymentType>1</POSCardPaymentType><POSPaymentEqualSource>1</POSPaymentEqualSource><EnableEjectCashBox>0</EnableEjectCashBox></PosPaymentType><PosPaymentType><PaymentTypeCode>005</PaymentTypeCode><PaymentTypeName>签单</PaymentTypeName><PaymentTypeMemo></PaymentTypeMemo><POSPaymentType>1</POSPaymentType><PaymentNeedCheck>1</PaymentNeedCheck><POSOrderPaymentType>1</POSOrderPaymentType><POSCardPaymentType>1</POSCardPaymentType><POSPaymentEqualSource>0</POSPaymentEqualSource><EnableEjectCashBox>0</EnableEjectCashBox></PosPaymentType><PosPaymentType><PaymentTypeCode>006</PaymentTypeCode><PaymentTypeName>零钱包</PaymentTypeName><PaymentTypeMemo></PaymentTypeMemo><POSPaymentType>1</POSPaymentType><PaymentNeedCheck>1</PaymentNeedCheck><POSOrderPaymentType>1</POSOrderPaymentType><POSCardPaymentType>1</POSCardPaymentType><POSPaymentEqualSource>0</POSPaymentEqualSource><EnableEjectCashBox>0</EnableEjectCashBox></PosPaymentType><PosPaymentType><PaymentTypeCode>007</PaymentTypeCode><PaymentTypeName>支付宝</PaymentTypeName><PaymentTypeMemo></PaymentTypeMemo><POSPaymentType>1</POSPaymentType><PaymentNeedCheck>1</PaymentNeedCheck><POSOrderPaymentType>1</POSOrderPaymentType><POSCardPaymentType>1</POSCardPaymentType><POSPaymentEqualSource>0</POSPaymentEqualSource><EnableEjectCashBox>0</EnableEjectCashBox></PosPaymentType><PosPaymentType><PaymentTypeCode>008</PaymentTypeCode><PaymentTypeName>微信支付</PaymentTypeName><PaymentTypeMemo></PaymentTypeMemo><POSPaymentType>1</POSPaymentType><PaymentNeedCheck>1</PaymentNeedCheck><POSOrderPaymentType>1</POSOrderPaymentType><POSCardPaymentType>1</POSCardPaymentType><POSPaymentEqualSource>0</POSPaymentEqualSource><EnableEjectCashBox>0</EnableEjectCashBox></PosPaymentType><PosPaymentType><PaymentTypeCode>009</PaymentTypeCode><PaymentTypeName>云闪付</PaymentTypeName><PaymentTypeMemo></PaymentTypeMemo><POSPaymentType>1</POSPaymentType><PaymentNeedCheck>1</PaymentNeedCheck><POSOrderPaymentType>1</POSOrderPaymentType><POSCardPaymentType>1</POSCardPaymentType><POSPaymentEqualSource>0</POSPaymentEqualSource><EnableEjectCashBox>0</EnableEjectCashBox></PosPaymentType><PosPaymentType><PaymentTypeCode>0010</PaymentTypeCode><PaymentTypeName>移动和包支付</PaymentTypeName><PaymentTypeMemo></PaymentTypeMemo><POSPaymentType>1</POSPaymentType><PaymentNeedCheck>1</PaymentNeedCheck><POSOrderPaymentType>1</POSOrderPaymentType><POSCardPaymentType>1</POSCardPaymentType><POSPaymentEqualSource>0</POSPaymentEqualSource><EnableEjectCashBox>0</EnableEjectCashBox></PosPaymentType><PosPaymentType><PaymentTypeCode>0011</PaymentTypeCode><PaymentTypeName>翼支付</PaymentTypeName><PaymentTypeMemo></PaymentTypeMemo><POSPaymentType>1</POSPaymentType><PaymentNeedCheck>1</PaymentNeedCheck><POSOrderPaymentType>1</POSOrderPaymentType><POSCardPaymentType>1</POSCardPaymentType><POSPaymentEqualSource>0</POSPaymentEqualSource><EnableEjectCashBox>0</EnableEjectCashBox></PosPaymentType></PosPaymentTypeList>");
                return bookResource;
            }
            return null;
        } else {
            String couponsXml = UserDao.getCouponsXml();
            if (TextUtils.isEmpty(couponsXml)) {
                return null;
            } else {
                return new Gson().fromJson(couponsXml, BookResource.class);
            }
        }

    }

    /**
     * 获取消费卡 列表
     *
     * @param systemCode
     * @param payScale
     * @param branch     发卡门店
     * @return
     */
    public List<VipCardTypeBean> getVipCardTypeBeanList(String systemCode, String payScale, int branch) {
        BookResource bookPosCardType = getBookPosSale(systemCode, payScale);
        List<VipCardTypeBean> list = new ArrayList<>();
        Gson gson = new Gson();
        String s = XmlParser.xml2json(bookPosCardType.getBookResourceParam());
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject object = jsonObject.optJSONObject("消费卡类型列表");
            JSONObject jsonObject1 = object.optJSONObject(payScale);
            if (jsonObject1 != null) {
                VipCardTypeBean vipCardTypeBean123 = gson.fromJson(jsonObject1.toString(), VipCardTypeBean.class);
                list.add(vipCardTypeBean123);
                return list;
            }
            JSONArray jsonArray = object.optJSONArray(payScale);
            for (int i = 0; i < jsonArray.length(); i++) {
                VipCardTypeBean vipCardTypeBean = gson.fromJson(jsonArray.optString(i), VipCardTypeBean.class);
                if (!vipCardTypeBean.getIsEnabled().equals("-1")) continue;//“会员卡类型-启用”参数控制
                if (vipCardTypeBean.getOnlyBranchUse().equals("-1")) {
                    if (LibConfig.activeLoginBean.getBranch_num() == branch) {
                        list.add(vipCardTypeBean);
                    }
                } else {
                    list.add(vipCardTypeBean);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解析bookresource表中其他收支
     *
     * @param systemCode
     * @param payScale
     * @return
     */
    public List<OtherPayment> getOtherPaymentList(String systemCode, String payScale) {
        BookResource bookPosCardType = getBookPosSale(systemCode, payScale);
        if (bookPosCardType == null) {
            return null;
        }
        List<OtherPayment> list = new ArrayList<>();
        Gson gson = new Gson();
        String s = XmlParser.xml2json(bookPosCardType.getBookResourceParam());
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(s);
            JSONObject object = jsonObject.optJSONObject("FeeItemList");
            JSONArray jsonArray = object.optJSONArray("FeeItem");
            for (int i = 0; i < jsonArray.length(); i++) {
                OtherPayment mOtherPayment = gson.fromJson(jsonArray.optString(i), OtherPayment.class);
                list.add(mOtherPayment);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 电子卡是否启用 true 是
     *
     * @param systemCode
     * @param payScale
     * @return
     */
    public boolean getVipCardTypeBeanIsEnable(String systemCode, String payScale) {
        BookResource bookPosCardType = getBookPosSale(systemCode, payScale);
        Gson gson = new Gson();
        String s = XmlParser.xml2json(bookPosCardType.getBookResourceParam());
        if (TextUtils.isEmpty(s)) {
            return false;
        }
        boolean isenable = true;
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject object = jsonObject.optJSONObject("消费卡类型列表");
            JSONArray jsonArray = object.optJSONArray(payScale);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    VipCardTypeBean vipCardTypeBean = gson.fromJson(jsonArray.optString(i), VipCardTypeBean.class);
//                if (!vipCardTypeBean.getIsEnabled().equals("-1"))continue;//“会员卡类型-启用”参数控制
                    if (vipCardTypeBean.getName().equals("电子卡") && !vipCardTypeBean.getIsEnabled().equals("-1")) {
                        isenable = false;
                    }

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return isenable;
    }


    /**
     * 获取指定会员卡类型的参数bean
     *
     * @param systemCode
     * @param payScale
     * @param cardTypeName
     * @return
     */
    public VipCardTypeBean getVipCardTypeBeanList(String systemCode, String payScale, String cardTypeName) {
        BookResource bookPosCardType = getBookPosSale(systemCode, payScale);
        VipCardTypeBean cardTypeBean = null;
        Gson gson = new Gson();
        String s = XmlParser.xml2json(bookPosCardType.getBookResourceParam());
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject object = jsonObject.optJSONObject("消费卡类型列表");
            JSONObject jsonObject1 = object.optJSONObject(payScale);
            if (jsonObject1 != null) {
                VipCardTypeBean vipCardTypeBean123 = gson.fromJson(jsonObject1.toString(), VipCardTypeBean.class);
                if (vipCardTypeBean123 != null && vipCardTypeBean123.getName().equals(cardTypeName)) {
                    return vipCardTypeBean123;
                } else if (vipCardTypeBean123 != null && vipCardTypeBean123.getNum().equals(cardTypeName)) {
                    return vipCardTypeBean123;
                } else {
                    return null;
                }

            }
            JSONArray jsonArray = object.optJSONArray(payScale);
            for (int i = 0; i < jsonArray.length(); i++) {
                VipCardTypeBean vipCardTypeBean = gson.fromJson(jsonArray.optString(i), VipCardTypeBean.class);
                if (vipCardTypeBean.getName().equals(cardTypeName)) {
                    cardTypeBean = vipCardTypeBean;
                    return vipCardTypeBean;
                } else if (vipCardTypeBean.getNum().equals(cardTypeName)) {
                    cardTypeBean = vipCardTypeBean;
                    return vipCardTypeBean;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cardTypeBean;
    }

    /**
     * 判断是否 支持CRM
     */
    public boolean isOpenCrm() {
        String BookResourceName = "ENABLE_CARD_MIGRATE_CRM";
        final BookResourceDao mBookResourceDao = DaoManager.getInstance().getDaoSession().getBookResourceDao();
        List<BookResource> list = mBookResourceDao.queryBuilder().where(BookResourceDao.Properties.BookResourceName.eq(BookResourceName)).list();
        if (list != null && list.size() > 0) {
            String bookResourceParam = list.get(0).getBookResourceParam();
            if (bookResourceParam.equals("true")) {
                return true;
            } else {
                return false;
            }

        }
        return false;
    }
}
