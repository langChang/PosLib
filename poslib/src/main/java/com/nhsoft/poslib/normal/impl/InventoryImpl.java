package com.nhsoft.poslib.normal.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.Inventory;
import com.nhsoft.poslib.normal.callback.InventoryCallBack;
import com.nhsoft.poslib.service.greendao.InventoryDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Iverson on 2020/7/8 10:56 AM
 * 此类用于：
 */
public class InventoryImpl implements InventoryCallBack {

    @Override
    public Map<Long,Float> saveGoodsInventoryList(final List<Inventory> list) {
        final HashMap<Long, Float> integerFloatHashMap = new HashMap<>();
        final InventoryDao inventoryDao = DaoManager.getInstance().getDaoSession().getInventoryDao();
        inventoryDao.deleteAll();
        if(list == null || list.isEmpty()){
            return integerFloatHashMap;
        }
        MatterUtils.doMatter(inventoryDao, new Runnable() {
            @Override
            public void run() {
                for (Inventory inventory : list){
                    integerFloatHashMap.put(inventory.getItem_num(),inventory.getInventory_amount());
                }
                inventoryDao.insertOrReplaceInTx(list);
            }
        });
        return integerFloatHashMap;
    }



}
