package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.BottomMenu;
import com.nhsoft.poslib.service.greendao.BottomMenuDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class BottomMenuImpl {
    private static BottomMenuImpl instance;

    public static BottomMenuImpl getInstance() {
        if (instance == null) {
            instance = new BottomMenuImpl();
        }
        return instance;
    }

    public static boolean saveBottomMenu(final List<BottomMenu> dataLis) {
        final BottomMenuDao mBottomMenuDao = DaoManager.getInstance().getDaoSession().getBottomMenuDao();
        mBottomMenuDao.deleteAll();
        if (dataLis.size() == 0) return true;
        boolean isSuccess = MatterUtils.doMatter(mBottomMenuDao, new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<dataLis.size();i++){
                    dataLis.get(i).setMenu_id((long) (i+1));
                }
                mBottomMenuDao.insertInTx(dataLis);
            }
        });
        mBottomMenuDao.detachAll();
        return isSuccess;
    }


    public List<BottomMenu> getAllBottomMenus(){
        final BottomMenuDao mBottomMenuDao = DaoManager.getInstance().getDaoSession().getBottomMenuDao();
        List<BottomMenu> bottomMenus = mBottomMenuDao.loadAll();
        mBottomMenuDao.detachAll();
        return bottomMenus;
    }

    public void removeAllBottomMenu(){
        final BottomMenuDao mBottomMenuDao = DaoManager.getInstance().getDaoSession().getBottomMenuDao();
        mBottomMenuDao.deleteAll();
        mBottomMenuDao.detachAll();
    }
}
