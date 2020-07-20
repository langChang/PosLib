package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;

import com.nhsoft.poslib.service.greendao.RolePrivilegeNewDao;
import com.nhsoft.poslib.service.greendao.SystemRoleDao;
import com.nhsoft.poslib.service.greendao.DaoSession;

@Entity
public class SystemRole {


    /**
     * 获取所有角色资源
     *
     * system_role_num : 444400018
     * system_role_name : 批发
     * system_role_category : WEB用户角色
     */
    @Id
    @Property(nameInDb = "SYSTEM_ROLE_NUM")
    private Long system_role_num;
    private String system_role_name;
    private String system_role_category;
    @ToMany(referencedJoinProperty = "system_role_num")
    private List<RolePrivilegeNew>role_privilege_news;




    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1833450898)
    private transient SystemRoleDao myDao;
    @Generated(hash = 774622381)
    public SystemRole(Long system_role_num, String system_role_name,
            String system_role_category) {
        this.system_role_num = system_role_num;
        this.system_role_name = system_role_name;
        this.system_role_category = system_role_category;
    }
    @Generated(hash = 63380534)
    public SystemRole() {
    }
    public Long getSystem_role_num() {
        return this.system_role_num;
    }
    public void setSystem_role_num(Long system_role_num) {
        this.system_role_num = system_role_num;
    }
    public String getSystem_role_name() {
        return this.system_role_name;
    }
    public void setSystem_role_name(String system_role_name) {
        this.system_role_name = system_role_name;
    }
    public String getSystem_role_category() {
        return this.system_role_category;
    }
    public void setSystem_role_category(String system_role_category) {
        this.system_role_category = system_role_category;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1012452805)
    public List<RolePrivilegeNew> getRole_privilege_news() {
        if (role_privilege_news == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RolePrivilegeNewDao targetDao = daoSession.getRolePrivilegeNewDao();
            List<RolePrivilegeNew> role_privilege_newsNew = targetDao
                    ._querySystemRole_Role_privilege_news(system_role_num);
            synchronized (this) {
                if (role_privilege_news == null) {
                    role_privilege_news = role_privilege_newsNew;
                }
            }
        }
        return role_privilege_news;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 93870740)
    public synchronized void resetRole_privilege_news() {
        role_privilege_news = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1539928941)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSystemRoleDao() : null;
    }
    

}
