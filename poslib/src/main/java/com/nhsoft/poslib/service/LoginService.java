package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.Login;
import com.nhsoft.poslib.service.greendao.DaoSession;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

/**
 * Created by Iverson on 2018/11/17 9:55 AM
 * 此类用于：
 */
public class LoginService {

    private static LoginService instance;
    public static LoginService getInstance(){
        if (instance==null){
            instance=new LoginService();
        }
        return instance;
    }

    private DaoSession mDaoSession = DaoManager.getInstance().getDaoSession();

    public boolean insert(final Login login) {
        mDaoSession.getLoginDao().deleteAll();
        return MatterUtils.doMatter(mDaoSession.getLoginDao(), new Runnable() {
            @Override
            public void run() {
                mDaoSession.getLoginDao()
                        .insertOrReplaceInTx(login);
            }
        });
    }


    public List<Login> queryAll() {
        return  mDaoSession.getLoginDao().loadAll();
    }

    public Login queryById(long id) {
        Login mLogin= mDaoSession.getLoginDao().queryBuilder().unique();
        return null;
    }
    public static Login getLogin(){
        Login mLogin= DaoManager.getInstance().getDaoSession().getLoginDao().queryBuilder().limit(1).offset(0).unique();
        return mLogin;
    }

    public Login queryByName(String name) {
        return null;
    }

    public List<Login> queryByObj(String where, String... params) {
        return mDaoSession.getLoginDao()
                .queryRaw(where, params);
    }

}
