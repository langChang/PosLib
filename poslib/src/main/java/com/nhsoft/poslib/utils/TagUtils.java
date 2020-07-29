package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.call.impl.PosCarryLogImpl;

/**
 * Created by Iverson on 2019/3/5 4:25 PM
 * 此类用于：
 */
public class TagUtils {

    /**
     * 删除会员标记
     * @param posOrderDetail
     */
    public static void removeVipTag(PosOrderDetail posOrderDetail){
        String orderDetailMemo = posOrderDetail.getOrderDetailMemo();
        if(!TextUtils.isEmpty(orderDetailMemo)){
            orderDetailMemo = orderDetailMemo.replace(LibConfig.GOODS_VIP_TAG, "");
            posOrderDetail.setOrderDetailMemo(orderDetailMemo);
        }
    }

    /**
     * 添加会员标记
     * @param posOrderDetail
     */
    public static void addVipTag(PosOrderDetail posOrderDetail){
        String orderDetailMemo = posOrderDetail.getOrderDetailMemo();
        if(!TextUtils.isEmpty(orderDetailMemo)){
            if(orderDetailMemo.contains(LibConfig.GOODS_VIP_TAG))return;
            posOrderDetail.setOrderDetailMemo(new StringBuilder().append(orderDetailMemo).append(LibConfig.GOODS_VIP_TAG).toString());
        }else {
            posOrderDetail.setOrderDetailMemo(new StringBuilder().append(LibConfig.GOODS_VIP_TAG).toString());
        }
    }


    /**
     * 删除手改标记
     * @param posOrderDetail
     */
    public static void removeChangeTag(PosOrderDetail posOrderDetail){
        String orderDetailMemo = posOrderDetail.getOrderDetailMemo();
        if(!TextUtils.isEmpty(orderDetailMemo)){
            orderDetailMemo = orderDetailMemo.replace(LibConfig.GOODS_CHANGE_TAG,"");
            posOrderDetail.setOrderDetailMemo(orderDetailMemo);
        }
    }

    /**
     * 添加手改标记
     * @param posOrderDetail
     */
    public static void addChangeTag(PosOrderDetail posOrderDetail){
        String orderDetailMemo = posOrderDetail.getOrderDetailMemo();
        if(!TextUtils.isEmpty(orderDetailMemo)){
            posOrderDetail.setOrderDetailMemo(new StringBuilder().append(orderDetailMemo).append(LibConfig.GOODS_CHANGE_TAG).toString());
        }else {
            if(orderDetailMemo.contains(LibConfig.GOODS_CHANGE_TAG))return;
            posOrderDetail.setOrderDetailMemo(new StringBuilder().append(LibConfig.GOODS_CHANGE_TAG).toString());
        }
        PosCarryLogImpl.tryChangeGoodsPrice(posOrderDetail);
    }


}
