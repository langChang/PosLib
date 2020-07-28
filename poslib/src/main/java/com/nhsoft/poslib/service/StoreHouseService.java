package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.StoreHouse;
import com.nhsoft.poslib.service.greendao.StoreHouseDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

/**
 * Created by Iverson on 2018/11/19 5:34 PM
 * 此类用于：
 */
public class StoreHouseService {
    private static StoreHouseService instance;
    public static StoreHouseService getInstance(){
        if (instance==null){
            instance=new StoreHouseService();
        }
        return instance;
    }

    public static boolean saveStoreHouse(final List<StoreHouse> dataLis){
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

    public static List<StoreHouse> getAllStoreHouse(){
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
