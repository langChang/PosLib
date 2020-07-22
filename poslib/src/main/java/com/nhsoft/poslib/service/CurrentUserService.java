package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.CurrentUser;
import com.nhsoft.poslib.service.greendao.CurrentUserDao;
import com.nhsoft.poslib.utils.MatterUtils;

public class CurrentUserService {

    private static CurrentUserService instance;

    public static CurrentUserService getInstance(){
        if (instance==null){
            instance=new CurrentUserService();
        }
        return instance;
    }

    public void deleteBean(){
        final CurrentUserDao currentUserDao= DaoManager.getInstance().getDaoSession().getCurrentUserDao();
        int size = currentUserDao.loadAll().size();
        if (size>10){
            currentUserDao.deleteAll();
        }

    }

    public boolean insertBean(final CurrentUser currentUser){
        final CurrentUserDao currentUserDao= DaoManager.getInstance().getDaoSession().getCurrentUserDao();

        return MatterUtils.doMatter(currentUserDao, new Runnable() {
            @Override
            public void run() {
                currentUserDao.insertOrReplaceInTx(currentUser);
            }
        });
    }


}
