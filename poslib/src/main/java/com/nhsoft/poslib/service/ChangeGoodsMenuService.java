package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.ChangeGoodsMenu;
import com.nhsoft.poslib.service.greendao.BottomMenuDao;
import com.nhsoft.poslib.service.greendao.ChangeGoodsMenuDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class ChangeGoodsMenuService {
    private static ChangeGoodsMenuService instance;

    public static ChangeGoodsMenuService getInstance() {
        if (instance == null) {
            instance = new ChangeGoodsMenuService();
        }
        return instance;
    }

    public static boolean saveChangeGoodsMenu(final List<ChangeGoodsMenu> dataLis) {
        final ChangeGoodsMenuDao changeGoodsMenuDao = DaoManager.getInstance().getDaoSession().getChangeGoodsMenuDao();
        changeGoodsMenuDao.deleteAll();
        if (dataLis.size() == 0) return true;
        boolean isSuccess = MatterUtils.doMatter(changeGoodsMenuDao, new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<dataLis.size();i++){
                    dataLis.get(i).setMenu_id((long) (i+1));
                }
                changeGoodsMenuDao.insertInTx(dataLis);
            }
        });
        changeGoodsMenuDao.detachAll();
        return isSuccess;
    }


    public List<ChangeGoodsMenu> getAllChangeGoodsMenus(){
        final ChangeGoodsMenuDao changeGoodsMenuDao = DaoManager.getInstance().getDaoSession().getChangeGoodsMenuDao();
        List<ChangeGoodsMenu> changeGoodsMenus = changeGoodsMenuDao.loadAll();
        changeGoodsMenuDao.detachAll();
        return changeGoodsMenus;
    }

    public void removeAllChangeGoodsMenu(){
        final BottomMenuDao mBottomMenuDao = DaoManager.getInstance().getDaoSession().getBottomMenuDao();
        mBottomMenuDao.deleteAll();
        mBottomMenuDao.detachAll();
    }
}
