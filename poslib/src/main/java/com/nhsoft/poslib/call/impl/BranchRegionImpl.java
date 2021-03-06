package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.BranchRegion;
import com.nhsoft.poslib.service.greendao.BranchRegionDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

/**
 * Created by Iverson on 2018/11/19 5:34 PM
 * 此类用于：
 */
public class BranchRegionImpl {

    private static BranchRegionImpl instance;
    public static BranchRegionImpl getInstance() {
        if (instance == null) {
            instance = new BranchRegionImpl();
        }
        return instance;
    }



    public boolean saveBranchRegionList(final List<BranchRegion> branchRegionList) {
        final BranchRegionDao mBranchRegionDao = DaoManager.getInstance().getDaoSession().getBranchRegionDao();
        mBranchRegionDao.deleteAll();
        if (branchRegionList.size() == 0) return true;
        boolean isSuccess = MatterUtils.doMatter(mBranchRegionDao, new Runnable() {
            @Override
            public void run() {
                mBranchRegionDao.insertOrReplaceInTx(branchRegionList);
            }
        });

        return isSuccess;
    }
}


