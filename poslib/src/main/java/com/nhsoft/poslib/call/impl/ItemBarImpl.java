package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.ItemBar;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.service.greendao.DaoSession;
import com.nhsoft.poslib.service.greendao.ItemBarDao;
import com.nhsoft.poslib.utils.NumberUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2019-07-23 14:16
 * 此类用于：
 */
public class ItemBarImpl {


    private static ItemBarImpl instance;
    public static ItemBarImpl getInstance(){
        if (instance==null){
            instance=new ItemBarImpl();
        }
        return instance;
    }

    private DaoSession mDaoSession = DaoManager.getInstance().getDaoSession();


    public List<ItemBar> getAllItemBarGoods(){
        List<ItemBar> itemBars = new ArrayList<>();

        ItemBarDao itemBarDao = mDaoSession.getItemBarDao();
        List<ItemBar> itemBars1 = itemBarDao.loadAll();
        for (ItemBar itemBar : itemBars1){
            if(itemBar.getItem_bar_code() != null && itemBar.getItem_bar_code().startsWith(LibConfig.saleParamsBean.getPrefixOfWeightItem()) && itemBar.getItem_bar_code().length() == 7&& NumberUtil.isNumeric(itemBar.getItem_bar_code())){
                List<PosItem> posItemByItemBarCode = PosItemImpl.getInstance().getPosItemByItemBarCode(LibConfig.activeShiftTable.getBranchNum(), itemBar.getItem_bar_code());
                if(null != posItemByItemBarCode && posItemByItemBarCode.size() > 0){
                    itemBar.setPosItem(posItemByItemBarCode.get(0));
                    itemBars.add(itemBar);
                    itemBar.setItem_valid_period(posItemByItemBarCode.get(0).getItem_valid_period());
                }
            }
        }
        return itemBars;
    }

    public ItemBar getItemBar(Long item_num){
        List<ItemBar> list = mDaoSession.getItemBarDao().queryBuilder()
                .where(ItemBarDao.Properties.Item_num.eq(item_num))
                .list();

        if(list != null && list.size() > 0){
            return list.get(0);
        }

        return null;
    }
}
