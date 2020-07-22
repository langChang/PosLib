package com.nhsoft.poslib.service;

import android.text.TextUtils;

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

    /**
     * 判断是否是农贸，true代表 是农贸，false表示不是
     * @return
     */
    public boolean isNongMao(){
        Login mLogin=getLogin();
        if (mLogin.getBranch_product().equals("喜临门农贸市场管理系统(V2018)")){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断是否是新农贸，true代表 是农贸，false表示不是
     * @return
     */
    public boolean isNewNM(){

        //特殊判断不能删除。
        String book_code = getLogin().getSystem_book_code();
        if(book_code != null && (book_code.equals("2093") || book_code.equals("61161") || book_code.equals("61162"))){
            return true;
        }

        if (isNongMao()&& !TextUtils.isEmpty(getLogin().getBranch_module()) && getLogin().getBranch_module().contains("农贸2019")){
            return true;
        }else {
            return false;
        }
    }

}
