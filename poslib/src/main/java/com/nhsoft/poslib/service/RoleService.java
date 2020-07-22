package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.RolePrivilegeNew;
import com.nhsoft.poslib.entity.SystemRole;
import com.nhsoft.poslib.service.greendao.RolePrivilegeNewDao;
import com.nhsoft.poslib.service.greendao.SystemRoleDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

/**
 * Created by Iverson on 2018/11/19 5:34 PM
 * 此类用于：
 */
public class RoleService {
    public static boolean saveRole(final List<SystemRole> dataLis){
        final SystemRoleDao roleDao = DaoManager.getInstance().getDaoSession().getSystemRoleDao();
        final RolePrivilegeNewDao rolePrivilegeNewDao=DaoManager.getInstance().getDaoSession().getRolePrivilegeNewDao();
        roleDao.deleteAll();
        rolePrivilegeNewDao.deleteAll();
        if(dataLis.size() == 0)return true;
        boolean isSuccess = MatterUtils.doMatter(roleDao, new Runnable() {
            @Override
            public void run() {
                for (SystemRole systemRole:dataLis){
                    roleDao.insertOrReplaceInTx(systemRole);
                    for (RolePrivilegeNew rolePrivilegeNew:systemRole.getRole_privilege_news()){
                        rolePrivilegeNewDao.insertOrReplaceInTx(rolePrivilegeNew);
                    }
                }
            }
        });
        return isSuccess;
    }
}
