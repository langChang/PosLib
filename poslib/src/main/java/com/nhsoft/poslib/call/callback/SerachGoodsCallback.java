package com.nhsoft.poslib.call.callback;

import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.PosItemGrade;
import com.nhsoft.poslib.entity.order.PosOrderDetail;

import java.util.List;

/**
 * Created by Iverson on 2019-11-29 16:16
 * 此类用于：搜索商品回调
 */
public interface SerachGoodsCallback {

    /**
     * 当搜索到已经称好的商品
     * @param posOrderDetail 将商品详细返回给调用者
     */
    void searchOutBarGoods(PosOrderDetail posOrderDetail);

    /**
     * 未搜索到商品或者搜索到商品不可用（原料商品、成分商品）
     * @param info 错误信息
     */
    void searchNoUseGoods(String info);

    /**
     * 搜索到一个商品
     * @param posItem
     * @param posItemGrade
     */
    void serachGoodsOnly(PosItem posItem, PosItemGrade posItemGrade);

    /**
     * 搜索到多个商品
     * @param posItems
     */
    void serachGoodsList(List<PosItem> posItems);

    /**
     * 隐藏输入法
     */
    void hideSoftInput();

    /**
     * 重置搜索框
     */
    void resetText();

}
