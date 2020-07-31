package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.AppUser;
import com.nhsoft.poslib.entity.PrivilegeResourceNew;
import com.nhsoft.poslib.entity.SystemRole;
import com.nhsoft.poslib.entity.RolePrivilegeNew;
import com.nhsoft.poslib.service.greendao.AppUserDao;
import com.nhsoft.poslib.service.greendao.PrivilegeResourceNewDao;
import com.nhsoft.poslib.service.greendao.SystemRoleDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2018/11/19 5:34 PM
 * 此类用于：权限资源
 */
public class PrivilegeResourceNewImpl {

    private static PrivilegeResourceNewImpl instance;

    public static PrivilegeResourceNewImpl getInstance() {
        if (instance == null) {
            instance = new PrivilegeResourceNewImpl();
        }
        return instance;
    }


    public boolean savePrivilegeList(final List<PrivilegeResourceNew> dataLis) {
        final PrivilegeResourceNewDao mPrivilegeDao = DaoManager.getInstance().getDaoSession().getPrivilegeResourceNewDao();
        mPrivilegeDao.deleteAll();
        if (dataLis.size() == 0) return true;
        boolean isSuccess = MatterUtils.doMatter(mPrivilegeDao, new Runnable() {
            @Override
            public void run() {
                mPrivilegeDao.insertOrReplaceInTx(dataLis);
            }
        });
        return isSuccess;
    }

    /**
     * 判断用户AA对cc操作是否拥有XX权限
     *
     * @param appUser       AA （张三、李四）
     * @param privilageName cc（发卡、退货）
     * @param operatorName  XX（查询、编辑）
     * @return
     */
    public boolean getPrivilage(AppUser appUser, String privilageName, String operatorName, List<SystemRole> roleList) {
        boolean roleNew = false;
        SystemRole systemRole = getRole(appUser, roleList);
        /*
        * APPUser--(appUserNum)-->SystemRole--(systemRoleNum)-->RolePrivilegeNew--(privilege_resource_key)-->PrivilegeResourceNew-->getName(发卡。。。)
        * 拿到RolePrivilegeNew的List 对其遍历
         第一步：根据用户缓存中查找对应的角色 SystemRoles放置缓存  返回SystemRole
         第二步：
         */
        if (systemRole == null) {
            return false;
        }
        List<RolePrivilegeNew> rolePrivilegeNews = systemRole.getRole_privilege_news();
        for (RolePrivilegeNew rolePrivilegeNew : rolePrivilegeNews) {
            if (rolePrivilegeNew.getPrivilege_resource_new()!=null&&rolePrivilegeNew.getPrivilege_resource_new().getPrivilege_resource_name().equals(privilageName)) {
                if (operatorName.equals(LibConfig.ACTION_SELECT)) {
                    return rolePrivilegeNew.getRole_query();
                } else if (operatorName.equals(LibConfig.ACTION_EDIT)) {
                    return rolePrivilegeNew.getRole_edit();
                } else if (operatorName.equals(LibConfig.ACTION_DELETE)) {
                    return rolePrivilegeNew.getRole_delete();
                } else if (operatorName.equals(LibConfig.ACTION_INVALID)) {
                    return rolePrivilegeNew.getRole_invalid();
                } else if (operatorName.equals(LibConfig.ACTION_AUDIT)) {
                    return rolePrivilegeNew.getRole_audit();
                } else if (operatorName.equals(LibConfig.ACTION_PRINT)) {
                    return rolePrivilegeNew.getRole_print();
                } else if (operatorName.equals(LibConfig.ACTION_EXPORT)) {
                    return rolePrivilegeNew.getRole_export();
                } else if (operatorName.equals(LibConfig.ACTION_RE_EXPORT)) {
                    return rolePrivilegeNew.getRole_re_audit();
//                    return rolePrivilegeNew.getPrivilege_resource_re_audit();
                }

            }
        }

        return roleNew;
    }


    /**
     * 返还APPUser所属角色
     *
     * @param appUser
     * @param roleList
     * @return
     */
    public SystemRole getRole(AppUser appUser, List<SystemRole> roleList) {

        if (appUser.getUser_role_list() == null || appUser.getUser_role_list().size() == 0) {
            return null;
        }
        long systemRoleNum = appUser.getUser_role_list().get(0).getSystem_role_num();
        SystemRole systemRole = null;
        loop1:
        for (SystemRole role : roleList) {
            if (role.getSystem_role_num().equals(systemRoleNum)) {
                systemRole = role;
                break loop1;
            }
        }
        return systemRole;
    }

    /**
     * 获取全部SystemRole放到全局变量
     *
     * @return
     */
    public List<SystemRole> getSystemRoleList() {
        SystemRoleDao roleDao = DaoManager.getInstance().getDaoSession().getSystemRoleDao();
        List<SystemRole> list = roleDao.queryBuilder().list();
        return list == null ? new ArrayList<SystemRole>() : list;
    }


    /***
     * 返还 对operatorName（查询、删除）等动作有 privilegeName（发卡，撤单）权限的 所有角色 对用的APPUser
     *
     * @param appUserList
     * @param privilegeName
     * @param operatorName
     * @param roleList
     * @return
     */
    public List<AppUser> getHavePermissions(List<AppUser> appUserList, String privilegeName, String operatorName, List<SystemRole> roleList) {

        List<AppUser> userList = new ArrayList<>();
        for (AppUser appUser : appUserList) {
            if (getPrivilage(appUser, privilegeName, operatorName, roleList)) {
                userList.add(appUser);
            }
        }

        return userList;
    }

    //把本地所有用户加载到内存
    public List<AppUser> getAppUserList() {
        AppUserDao appUserDao = DaoManager.getInstance().getDaoSession().getAppUserDao();
        return appUserDao.queryBuilder().list();
    }

}
