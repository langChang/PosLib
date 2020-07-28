package com.nhsoft.poslib.utils;

import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.model.PosOrderState;

/**
 * Created by Iverson on 2018/11/27 3:49 PM
 * 此类用于：
 */
public class PosOrderStateUtil {

    public static PosOrderState getOrderState(int code) {
        PosOrderState posOrderState = new PosOrderState();
        switch (code) {
            case LibConfig.S_ORDER_INIT:
                posOrderState.ORDER_STATE_CODE = LibConfig.S_ORDER_INIT;
                posOrderState.ORDER_STATE_NAME = LibConfig.S_ORDER_INIT_NAME;
                break;

            case LibConfig.S_ORDER_ONORDER:
                posOrderState.ORDER_STATE_CODE = LibConfig.S_ORDER_ONORDER;
                posOrderState.ORDER_STATE_NAME = LibConfig.S_ORDER_ONORDER_NAME;
                break;

            case LibConfig.S_ORDER_COMPLETE:
                posOrderState.ORDER_STATE_CODE = LibConfig.S_ORDER_COMPLETE;
                posOrderState.ORDER_STATE_NAME = LibConfig.S_ORDER_COMPLETE_NAME;
                break;
            case LibConfig.S_ORDER_CANCEL:
                posOrderState.ORDER_STATE_CODE = LibConfig.S_ORDER_CANCEL;
                posOrderState.ORDER_STATE_NAME = LibConfig.S_ORDER_CANCEL_NAME;
                break;
        }
        return posOrderState;
    }


    /**
     * 将单据置为异常
     * @param posOrder
     */
    public static void setPosOrderByCancel(PosOrder posOrder){
        posOrder.setOrderStateCode(LibConfig.S_ORDER_INIT+LibConfig.S_ORDER_CANCEL);
        posOrder.setOrderStateName(LibConfig.S_ORDER_INIT_NAME+"|"+LibConfig.S_ORDER_CANCEL_NAME);
    }

    /**
     * 将单据置为异常
     * @param posOrder
     */
    public static void setPosOrderByComplete(PosOrder posOrder){
        posOrder.setOrderStateCode(LibConfig.S_ORDER_INIT+LibConfig.S_ORDER_COMPLETE);
        posOrder.setOrderStateName(LibConfig.S_ORDER_INIT_NAME+"|"+LibConfig.S_ORDER_COMPLETE_NAME);
    }
}
