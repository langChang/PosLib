package com.nhsoft.poslib.utils;

import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.model.PosOrderDetailState;

/**
 * Created by Iverson on 2018/11/27 3:49 PM
 * 此类用于：
 */
public class PosOrderDetailStateUtil {


    public static PosOrderDetailState getOrderState(int code) {
        PosOrderDetailState detailState = new PosOrderDetailState();
        switch (code) {
            case LibConfig.S_ORDER_DETAIL_SALE:
                detailState.POS_ORDER_DETAIL_STATE_CODE = LibConfig.S_ORDER_DETAIL_SALE;
                detailState.POS_ORDER_DETAIL_STATE_NAME = LibConfig.S_ORDER_DETAIL_SALE_NAME;
                break;
            case LibConfig.S_ORDER_DETAIL_PRESENT:
                detailState.POS_ORDER_DETAIL_STATE_CODE = LibConfig.S_ORDER_DETAIL_PRESENT;
                detailState.POS_ORDER_DETAIL_STATE_NAME = LibConfig.S_ORDER_DETAIL_PRESENT_NAME;
                break;

            case LibConfig.S_ORDER_DETAIL_RETURN:
                detailState.POS_ORDER_DETAIL_STATE_CODE = LibConfig.S_ORDER_DETAIL_RETURN;
                detailState.POS_ORDER_DETAIL_STATE_NAME = LibConfig.S_ORDER_DETAIL_RETURN_NAME;
                break;

            case LibConfig.S_ORDER_DETAIL_CANCEL:
                detailState.POS_ORDER_DETAIL_STATE_CODE = LibConfig.S_ORDER_DETAIL_CANCEL;
                detailState.POS_ORDER_DETAIL_STATE_NAME = LibConfig.S_ORDER_DETAIL_CANCEL_NAME;
                break;

            case LibConfig.S_ORDER_DETAIL_ONORDER:
                detailState.POS_ORDER_DETAIL_STATE_CODE = LibConfig.S_ORDER_DETAIL_ONORDER;
                detailState.POS_ORDER_DETAIL_STATE_NAME = LibConfig.S_ORDER_DETAIL_ONORDER_NAME;
                break;
        }
        return detailState;
    }
}
