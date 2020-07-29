package com.nhsoft.poslib.call.callback;

import com.nhsoft.poslib.entity.Inventory;

import java.util.List;
import java.util.Map;

/**
 * Created by Iverson on 2020/7/8 10:55 AM
 * 此类用于：
 */
public interface InventoryCallBack {

    Map<Long,Float> saveGoodsInventoryList(List<Inventory> list);

}
