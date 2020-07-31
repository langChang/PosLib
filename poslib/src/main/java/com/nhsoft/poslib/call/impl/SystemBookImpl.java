package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.SystemBook;
import com.nhsoft.poslib.service.greendao.SystemBookDao;
import com.nhsoft.poslib.utils.MatterUtils;

/**
 * Created by Iverson on 2018/11/19 5:34 PM
 * 此类用于：
 */
public class SystemBookImpl {


    private static SystemBookImpl instance;
    public static SystemBookImpl getInstance(){
        if (instance==null){
            instance=new SystemBookImpl();
        }
        return instance;
    }


    public boolean saveSystemBook(final SystemBook dataLis){
        final SystemBookDao systemBookDao = DaoManager.getInstance().getDaoSession().getSystemBookDao();
        systemBookDao.deleteAll();
        if(dataLis == null)return false;
        boolean isSuccess = MatterUtils.doMatter(systemBookDao, new Runnable() {
            @Override
            public void run() {
                systemBookDao.insertOrReplaceInTx(dataLis);
            }
        });
        return isSuccess;
    }

    /**
     * 获取该账套号的bean
     * @param systemBookCode
     * @return
     */
    public static SystemBook getBean(String systemBookCode){
        final SystemBookDao systemBookDao = DaoManager.getInstance().getDaoSession().getSystemBookDao();
        return systemBookDao.queryBuilder().where(SystemBookDao.Properties.System_book_code.eq(systemBookCode)).unique();
    }
}
