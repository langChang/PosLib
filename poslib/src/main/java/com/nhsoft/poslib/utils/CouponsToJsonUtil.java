package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.model.CouponsBean;
import com.nhsoft.poslib.model.CouponsXmlModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Iverson on 2019/2/25 8:50 AM
 * 此类用于：
 */
public class CouponsToJsonUtil {


    public static String toJsonByHome(List<CouponsBean> beanList,int branchNum,String orderNo,float orderPaymentMoney){

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("branch_num",branchNum);
            jsonObject.put("coupon_user",LibConfig.activeShiftTable.getShiftTableUserName());
            jsonObject.put("order_no",orderNo);
            jsonObject.put("order_payment_money",orderPaymentMoney);
            JSONArray jsonArray = new JSONArray();

            for (CouponsBean couponsBean : beanList){
                JSONObject couponsObject = new JSONObject();
                couponsObject.put("ticket_send_detail_uuid",couponsBean.getTicket_send_detail_uuid());
                couponsObject.put("ticket_send_detail_value",couponsBean.getTicket_send_detail_value());
                couponsObject.put("ticket_send_detail_amount",1);
                couponsObject.put("ticket_send_detail_type",couponsBean.getTicket_send_detail_type());
                for (CouponsXmlModel couponsXmlModel : LibConfig.sCouponsXmlModels) {
                    if (couponsXmlModel.getCouponsName().equals(couponsBean.getTicket_send_detail_type())) {
                        couponsObject.put("ticket_send_detail_type_code",couponsXmlModel.getTicketCode());
                        break;
                    }
                }
                if (!TextUtils.isEmpty(couponsBean.getTicket_send_detail_memo())){
                    couponsObject.put("ticket_send_detail_memo",couponsBean.getTicket_send_detail_memo());
                }
                couponsObject.put("action_id",couponsBean.getAction_id());
                jsonArray.put(couponsObject);
            }

            jsonObject.put("posTicketV2DTOS",jsonArray);
            return jsonObject.toString();
        } catch (JSONException e) {
            return null;
        }
    }


    public static String toJsonByAbnoraml(List<PosOrderDetail> beanList, int branchNum, String orderNo, float orderPaymentMoney){

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("branch_num",branchNum);
            jsonObject.put("coupon_user", LibConfig.activeShiftTable.getShiftTableUserName());
            jsonObject.put("order_no",orderNo);
            jsonObject.put("order_payment_money",orderPaymentMoney);
            JSONArray jsonArray = new JSONArray();

            for (PosOrderDetail posOrderDetail : beanList){
                JSONObject couponsObject = new JSONObject();
                couponsObject.put("ticket_send_detail_uuid",posOrderDetail.getOrderDetailTicketUuid());
                couponsObject.put("ticket_send_detail_value",posOrderDetail.getPaymentBalance());
                couponsObject.put("ticket_send_detail_amount",1);
                couponsObject.put("ticket_send_detail_type",posOrderDetail.getOrderDetailItem());
                couponsObject.put("action_id",posOrderDetail.getOrderDetailTicketActionId());
                jsonArray.put(couponsObject);
            }

            jsonObject.put("posTicketV2DTOS",jsonArray);
            return jsonObject.toString();
        } catch (JSONException e) {
            return null;
        }
    }
}
