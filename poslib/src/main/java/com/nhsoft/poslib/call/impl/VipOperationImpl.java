package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.call.callback.VipOperationCallback;
import com.nhsoft.poslib.model.VipUserInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Iverson on 2020/8/20 7:15 PM
 * 此类用于：
 */
public class VipOperationImpl implements VipOperationCallback {

    private static VipOperationImpl instance;

    public static VipOperationImpl getInstance(){
        if (instance==null){
            instance=new VipOperationImpl();
        }
        return instance;
    }


    @Override
    public void refreshCurrentVipInfo(VipUserInfo vipUserInfo) {

    }


    /**
     * 获取挂失卡的json
     * @param card_user_num
     * @param card_loss_operator
     * @param card_loss_branch_name
     * @return
     */
    public String getLossCardJson(String card_user_num,String card_loss_operator,String card_loss_branch_name){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("card_user_num", card_user_num);
            jsonObject.put("card_loss_operator", card_loss_operator);
            jsonObject.put("card_loss_branch_name", card_loss_branch_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}
