package com.nhsoft.poslib.service;

import android.text.TextUtils;

import com.google.gson.Gson;

import com.nhsoft.poslib.entity.BookResource;
import com.nhsoft.poslib.entity.CustomerRegister;
import com.nhsoft.poslib.entity.VipUserInfo;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.model.VipCardConfig;
import com.nhsoft.poslib.utils.EvtLog;
import com.nhsoft.poslib.utils.TimeUtil;
import com.nhsoft.poslib.utils.XmlParser;

import org.json.JSONException;
import org.json.JSONObject;

public class VipSendCardService {

    private static VipSendCardService instance;

    public static VipSendCardService getInstance() {
        if (instance == null) {
            instance = new VipSendCardService();
        }
        return instance;
    }


    /**
     * 生成发卡请求
     *
     * @param strPhone
     * @param strBirthday
     * @param strCardType
     * @param strCardNum
     * @param card_user_storage
     * @return
     */
    public String getStringSendCardQuest(String strPhone,String username, String strBirthday, String strCardType, String strCardNum, String card_user_storage, String customer_id,String open_id,String seller) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("card_user_num", strCardNum);
            jsonObject.put("user_phone", strPhone);
            jsonObject.put("user_name", username);
            jsonObject.put("card_type", strCardType);
            jsonObject.put("open_id", open_id);
            jsonObject.put("seller", seller == null ? "" : seller);
            if (!TextUtils.isEmpty(customer_id)) {
                jsonObject.put("customer_id", customer_id);
            }

            jsonObject.put("card_user_storage", card_user_storage);
            if (TextUtils.isEmpty(strBirthday)) {
                strBirthday = TimeUtil.getInstance().dateToString(TimeUtil.getInstance().getNowDate(), TimeUtil.FORMAT_THREE);
            }
            if (LibConfig.sVipCardParams != null) {
                String gettime = LibConfig.sVipCardParams.getDeliverTimeNeedDeposit();
                EvtLog.d("getTime:=" + gettime);
            }

            if (LibConfig.sVipCardParams != null && LibConfig.sVipCardParams.getDeliverTimeNeedDeposit().equals("-1")) {
                jsonObject.put("card_user_date", "");
            } else {
                jsonObject.put("card_user_date", TimeUtil.getNowDateString(TimeUtil.FORMAT_ONE));
            }
            jsonObject.put("birthday", strBirthday + " 00:00:00");

            jsonObject.put("enroll_shop", LibConfig.activeBranch.getBranch_num());
            jsonObject.put("operator", LibConfig.activeAppUser.getApp_user_name());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 产生CRM会员请求json
     * 保存到本地
     *
     * @param bill_no
     * @param phone
     * @param category_id
     * @param received_amount
     * @return
     */
    public String CreateRequestSendCardCRMByRegiest(String bill_no, String phone, long category_id,
                                           String received_amount, String payTypeName,
                                           String vip_card_user_name,
                                           String birthday,
                                           String vip_card_user_level,String user_name,String user_sex) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("bill_no", bill_no);
            jsonObject.put("branch_num", LibConfig.activeBranch.getBranch_num());
            jsonObject.put("branch_name", LibConfig.activeBranch.getBranch_name());
            jsonObject.put("category_id", category_id);//	nteger <int64> 购买类型Id

            jsonObject.put("mac_address", LibConfig.activePosMachine.getPos_machine_terminal_id());
            jsonObject.put("phone", phone);
            if (!TextUtils.isEmpty(birthday)){
                if (birthday.length()<=10){
                    birthday=birthday+" 00:00:00";
                }
                jsonObject.put("birthday", birthday);
            }

            jsonObject.put("received_amount", received_amount);
            jsonObject.put("name", user_name);
            jsonObject.put("sex", user_sex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CustomerRegister customerRegister = new CustomerRegister();
        customerRegister.setBranch_num(LibConfig.activeLoginBean.getBranch_num());
        customerRegister.setBranch_num(LibConfig.activeLoginBean.getBranch_num());
        customerRegister.setShift_table_bizday(LibConfig.activeShiftTable.getShift_table_bizday());
        customerRegister.setShift_table_num(LibConfig.activeShiftTable.getShift_table_num());
        customerRegister.setSystem_book_code(LibConfig.activeLoginBean.getSystem_book_code());
        customerRegister.setVip_card_user_category(category_id + "");
        customerRegister.setVip_card_user_date(TimeUtil.getNowDateString(TimeUtil.FORMAT_ONE));
        customerRegister.setVip_card_user_memo("");
        customerRegister.setVip_card_user_type("发卡");
        customerRegister.setVip_card_user_payment(payTypeName);
        customerRegister.setVip_card_user_valid_date(TimeUtil.getNowDateString(TimeUtil.FORMAT_ONE));
        customerRegister.setVip_card_user_operator(LibConfig.activeShiftTable.getShiftTableUserName());
        customerRegister.setVip_card_user_phone(phone);
//        customerRegister.setVip_card_user_print_num(print_num);
        customerRegister.setVip_card_user_ref_bill("");
        customerRegister.setVip_card_user_log_fid(bill_no);
        customerRegister.setVip_card_user_level(vip_card_user_name);
        customerRegister.setVip_card_user_level_name(vip_card_user_level);

        CustomerRegisterSerivce.getInstance().insertBean(customerRegister);

        return jsonObject.toString();
    }

    /**
     * 产生CRM会员请求json
     * 保存到本地
     *
     * @param bill_no
     * @param phone
     * @param category_id
     * @param received_amount
     * @return
     */
    public String CreateRequestSendCardCRM(String bill_no, String phone, long category_id,
                                           String received_amount, String payTypeName,
                                           String vip_card_user_name,
                                           String birthday,
                                           String vip_card_user_level) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("bill_no", bill_no);
            jsonObject.put("branch_num", LibConfig.activeBranch.getBranch_num());
            jsonObject.put("branch_name", LibConfig.activeBranch.getBranch_name());
            jsonObject.put("category_id", category_id);//	nteger <int64> 购买类型Id

            jsonObject.put("mac_address", LibConfig.activePosMachine.getPos_machine_terminal_id());
            jsonObject.put("phone", phone);
            if (!TextUtils.isEmpty(birthday)){
                if (birthday.length()<=10){
                    birthday=birthday+" 00:00:00";
                }
                jsonObject.put("birthday", birthday);
            }

            jsonObject.put("received_amount", received_amount);
            jsonObject.put("name", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CustomerRegister customerRegister = new CustomerRegister();
        customerRegister.setBranch_num(LibConfig.activeLoginBean.getBranch_num());
        customerRegister.setBranch_num(LibConfig.activeLoginBean.getBranch_num());
        customerRegister.setShift_table_bizday(LibConfig.activeShiftTable.getShift_table_bizday());
        customerRegister.setShift_table_num(LibConfig.activeShiftTable.getShift_table_num());
        customerRegister.setSystem_book_code(LibConfig.activeLoginBean.getSystem_book_code());
        customerRegister.setVip_card_user_category(category_id + "");
        customerRegister.setVip_card_user_date(TimeUtil.getNowDateString(TimeUtil.FORMAT_ONE));
        customerRegister.setVip_card_user_memo("");
        customerRegister.setVip_card_user_type("发卡");
        customerRegister.setVip_card_user_payment(payTypeName);
        customerRegister.setVip_card_user_valid_date(TimeUtil.getNowDateString(TimeUtil.FORMAT_ONE));
        customerRegister.setVip_card_user_operator(LibConfig.activeShiftTable.getShiftTableUserName());
        customerRegister.setVip_card_user_phone(phone);
//        customerRegister.setVip_card_user_print_num(print_num);
        customerRegister.setVip_card_user_ref_bill("");
        customerRegister.setVip_card_user_log_fid(bill_no);
        customerRegister.setVip_card_user_level(vip_card_user_name);
        customerRegister.setVip_card_user_level_name(vip_card_user_level);

        CustomerRegisterSerivce.getInstance().insertBean(customerRegister);

        return jsonObject.toString();
    }
    /**
     * 更新产生CRM会员 保存本地的数据
     *
     * @param vipUserInfo
     * @param strRemain
     */
    public void UpdateBean(CustomerRegister customerRegister, VipUserInfo vipUserInfo, String strRemain) {
        customerRegister.setVip_card_user_date(vipUserInfo.getCard_user_date());
        customerRegister.setVip_card_user_name(vipUserInfo.getCard_user_cust_name());
        if (TextUtils.isEmpty(strRemain)) {
            strRemain = "0";
        }
        customerRegister.setVip_card_user_money(Float.parseFloat(strRemain));
        CustomerRegisterSerivce.getInstance().insertBean(customerRegister);

    }


    public VipCardConfig getVipCardTypeBean(String systemBookCode) {
        VipCardConfig vipCardConfigBean = null;
        BookResource bookPosCardType = BookResourceService.getInstance().getBookPosSale(systemBookCode, LibConfig.S_LOCAL_VIP_STYPE);
        if (bookPosCardType != null) {
            Gson gson = new Gson();
            String s = XmlParser.xml2json(bookPosCardType.getBookResourceParam());
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONObject object = jsonObject.optJSONObject("消费卡参数");
                vipCardConfigBean = gson.fromJson(object.toString(), VipCardConfig.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (vipCardConfigBean ==null){
            vipCardConfigBean =new VipCardConfig();
            vipCardConfigBean.setCardReaminMoneyLimit("1000");//getCardReaminMoneyLimit
            vipCardConfigBean.setLowestCtrlMoney("0");//lowestCtrlMoney
            vipCardConfigBean.setDefaultContinueCardFee("0");
            vipCardConfigBean.setDefaultChangeCardFee("0");
            vipCardConfigBean.setCardConsumeusePasswordCheck("0");
            vipCardConfigBean.setInputPhoneNeedSmsCheck("0");
            vipCardConfigBean.setReplaceCardNeedSmsCheck("0");
            vipCardConfigBean.setChangePhoneNeedSmsCheck("0");
            vipCardConfigBean.setChangePWNeedSmsCheck("0");
            vipCardConfigBean.setDeliverTimeNeedDeposit("0");
            vipCardConfigBean.setEnableCardChange("0");
            vipCardConfigBean.setRevokeNeedNoMoney("0");
            vipCardConfigBean.setConsumeNotAllowPhone("0");
            vipCardConfigBean.setDelieveNotAllowChangeCardType("0");
            vipCardConfigBean.setUnLossCardNeedSmsCheck("0");
            vipCardConfigBean.setBanPrintPhoneQueryCard("0");
            vipCardConfigBean.setUsePointNeedPsw("0");
            vipCardConfigBean.setIsSupportSms("0");
            vipCardConfigBean.setReplaceControlLossDay("0");
            vipCardConfigBean.setCannotInputCardNumberByHand("0");
            vipCardConfigBean.setStrangeBaseNumber("0");
            vipCardConfigBean.setSignalStrangeMoneyLimit("0");
            vipCardConfigBean.setNoEditMoney("0");
            vipCardConfigBean.setForceBankCardInput("0");
            vipCardConfigBean.setPosReadCardHideInfo("0");
        }

        return vipCardConfigBean;
    }


}
