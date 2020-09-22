package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.StoreHouse;
import com.nhsoft.poslib.service.greendao.StoreHouseDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

/**
 * Created by Iverson on 2018/11/19 5:34 PM
 * 此类用于：
 */
public class StoreHouseImpl {

    private static StoreHouseImpl instance;
    public static StoreHouseImpl getInstance(){
        if (instance==null){
            instance=new StoreHouseImpl();
        }
        return instance;
    }

    public boolean saveStoreHouseList(final List<StoreHouse> dataLis){
        final StoreHouseDao storeHouseDao = DaoManager.getInstance().getDaoSession().getStoreHouseDao();
        storeHouseDao.deleteAll();
        if(dataLis.size() == 0)return true;
        boolean isSuccess = MatterUtils.doMatter(storeHouseDao, new Runnable() {
            @Override
            public void run() {
                storeHouseDao.insertOrReplaceInTx(dataLis);
            }
        });
        return isSuccess;
    }

    public List<StoreHouse> getAllStoreHouse(){
        final StoreHouseDao storeHouseDao = DaoManager.getInstance().getDaoSession().getStoreHouseDao();
        return storeHouseDao.loadAll();
    }

    public StoreHouse getCurrentStoreHouse(int branchNum){
        for (StoreHouse storeHouse:getAllStoreHouse()){
            if (storeHouse.getBranch_num()==branchNum){
                return storeHouse;
            }
        }
        return null;
    }

}
