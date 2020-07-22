package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.AppUser;
import com.nhsoft.poslib.entity.Branch;
import com.nhsoft.poslib.entity.UserRole;
import com.nhsoft.poslib.service.greendao.AppUserDao;
import com.nhsoft.poslib.service.greendao.UserRoleDao;
import com.nhsoft.poslib.utils.AesUtil;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

/**
 * Created by Iverson on 2018/11/15 11:29 AM
 */
public class AppUserService {

    private static AppUserService instance;
    public static AppUserService getInstance(){
        if (instance==null){
            instance=new AppUserService();
        }
        return instance;
    }
    public static boolean saveAppUser(final List<AppUser> result) {
        final AppUserDao appUserDao = DaoManager.getInstance().getDaoSession().getAppUserDao();
        final UserRoleDao userRoleDao = DaoManager.getInstance().getDaoSession().getUserRoleDao();
        userRoleDao.deleteAll();
        appUserDao.deleteAll();
        if(result.size() == 0)return true;
        return MatterUtils.doMatter(appUserDao, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < result.size(); i++) {
                    AppUser appUser = result.get(i);
                    appUserDao.insertOrReplaceInTx(appUser);
                    List<UserRole> user_role_list = appUser.getUser_role_list();
                    if (user_role_list != null && user_role_list.size() > 0) {
                        userRoleDao.insertOrReplaceInTx(user_role_list);
                    }
                }
            }
        });
    }

    public static  List<AppUser> queryAll(){
        final AppUserDao appUserDao = DaoManager.getInstance().getDaoSession().getAppUserDao();
        return appUserDao.loadAll();
    }



    /**
     * 本地登录
     * @param systemBookCode
     * @param branchNum
     * @param appUserCode
//     * @param appUserPw
     * @return
     */
    public AppUser login(String systemBookCode, int branchNum, String appUserCode){
        Branch branch = BranchService.getInstance().getBranch(systemBookCode, branchNum);
        if(branch != null){
            AppUserDao appUserDao = DaoManager.getInstance().getDaoSession().getAppUserDao();
            return appUserDao.queryBuilder().where(
                    AppUserDao.Properties.System_book_code.eq(systemBookCode)
                    ,AppUserDao.Properties.App_user_code.eq(appUserCode)

            ).unique();
        }
        return null;
    }

    public AppUser getBean(String systemBookCode,int branchNum,String app_user_code){
        AppUserDao appUserDao = DaoManager.getInstance().getDaoSession().getAppUserDao();
        return appUserDao.queryBuilder().where(
                AppUserDao.Properties.System_book_code.eq(systemBookCode)
                ,AppUserDao.Properties.App_user_code.eq(app_user_code)

        ).unique();
    }

    public void deleteAllBean(){
        AppUserDao appUserDao = DaoManager.getInstance().getDaoSession().getAppUserDao();
        appUserDao.deleteAll();
    }

    public boolean updatePassword(final Long app_user_num, final String password){
        final AppUserDao appUserDao = DaoManager.getInstance().getDaoSession().getAppUserDao();
        return MatterUtils.doMatter(appUserDao, new Runnable() {
            @Override
            public void run() {
                AppUser appUser=appUserDao
                        .queryBuilder()
                        .where(AppUserDao.Properties.App_user_num.eq(app_user_num))
                        .unique();
                String strPswd = AesUtil.encryptStr(password, appUser.getApp_user_pw_key());
                appUser.setApp_user_password(strPswd);
                appUserDao.insertOrReplaceInTx(appUser);
            }
        });
    }
}
