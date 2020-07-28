package com.nhsoft.poslib.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import com.nhsoft.poslib.service.greendao.PrivilegeResourceNewDao;
import com.nhsoft.poslib.service.greendao.RolePrivilegeNewDao;
import com.nhsoft.poslib.service.greendao.DaoSession;

@Entity()
public class RolePrivilegeNew {

    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;

    private Long system_role_num;
    private Long privilege_resource_key;
    private String system_book_code;
    private boolean role_query;
    private boolean role_edit;
    private boolean role_delete;
    private boolean role_audit;
    private boolean role_invalid;
    private boolean role_change_price;
    private boolean role_print;
    private boolean role_export;
    private boolean role_re_audit  ;

    @ToOne(joinProperty = "privilege_resource_key")
    private PrivilegeResourceNew privilege_resource_new;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 38476890)
    private transient RolePrivilegeNewDao myDao;

    @Generated(hash = 1484488709)
    public RolePrivilegeNew(Long id, Long system_role_num,
            Long privilege_resource_key, String system_book_code,
            boolean role_query, boolean role_edit, boolean role_delete,
            boolean role_audit, boolean role_invalid, boolean role_change_price,
            boolean role_print, boolean role_export, boolean role_re_audit) {
        this.id = id;
        this.system_role_num = system_role_num;
        this.privilege_resource_key = privilege_resource_key;
        this.system_book_code = system_book_code;
        this.role_query = role_query;
        this.role_edit = role_edit;
        this.role_delete = role_delete;
        this.role_audit = role_audit;
        this.role_invalid = role_invalid;
        this.role_change_price = role_change_price;
        this.role_print = role_print;
        this.role_export = role_export;
        this.role_re_audit = role_re_audit;
    }

    @Generated(hash = 753034756)
    public RolePrivilegeNew() {
    }

    @Generated(hash = 970737244)
    private transient Long privilege_resource_new__resolvedKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSystem_role_num() {
        return system_role_num;
    }

    public void setSystem_role_num(Long system_role_num) {
        this.system_role_num = system_role_num;
    }

    public Long getPrivilege_resource_key() {
        return privilege_resource_key;
    }

    public void setPrivilege_resource_key(Long privilege_resource_key) {
        this.privilege_resource_key = privilege_resource_key;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public boolean isRole_query() {
        return role_query;
    }

    public void setRole_query(boolean role_query) {
        this.role_query = role_query;
    }

    public boolean isRole_edit() {
        return role_edit;
    }

    public void setRole_edit(boolean role_edit) {
        this.role_edit = role_edit;
    }

    public boolean isRole_delete() {
        return role_delete;
    }

    public void setRole_delete(boolean role_delete) {
        this.role_delete = role_delete;
    }

    public boolean isRole_audit() {
        return role_audit;
    }

    public void setRole_audit(boolean role_audit) {
        this.role_audit = role_audit;
    }

    public boolean isRole_invalid() {
        return role_invalid;
    }

    public void setRole_invalid(boolean role_invalid) {
        this.role_invalid = role_invalid;
    }

    public boolean isRole_change_price() {
        return role_change_price;
    }

    public void setRole_change_price(boolean role_change_price) {
        this.role_change_price = role_change_price;
    }

    public boolean isRole_print() {
        return role_print;
    }

    public void setRole_print(boolean role_print) {
        this.role_print = role_print;
    }

    public boolean isRole_export() {
        return role_export;
    }

    public void setRole_export(boolean role_export) {
        this.role_export = role_export;
    }

    public boolean isRole_re_audit() {
        return role_re_audit;
    }

    public void setRole_re_audit(boolean role_re_audit) {
        this.role_re_audit = role_re_audit;
    }

    public boolean getRole_query() {
        return this.role_query;
    }

    public boolean getRole_edit() {
        return this.role_edit;
    }

    public boolean getRole_delete() {
        return this.role_delete;
    }

    public boolean getRole_audit() {
        return this.role_audit;
    }

    public boolean getRole_invalid() {
        return this.role_invalid;
    }

    public boolean getRole_change_price() {
        return this.role_change_price;
    }

    public boolean getRole_print() {
        return this.role_print;
    }

    public boolean getRole_export() {
        return this.role_export;
    }

    public boolean getRole_re_audit() {
        return this.role_re_audit;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 833646551)
    public PrivilegeResourceNew getPrivilege_resource_new() {
        Long __key = this.privilege_resource_key;
        if (privilege_resource_new__resolvedKey == null
                || !privilege_resource_new__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PrivilegeResourceNewDao targetDao = daoSession
                    .getPrivilegeResourceNewDao();
            PrivilegeResourceNew privilege_resource_newNew = targetDao.load(__key);
            synchronized (this) {
                privilege_resource_new = privilege_resource_newNew;
                privilege_resource_new__resolvedKey = __key;
            }
        }
        return privilege_resource_new;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1666302917)
    public void setPrivilege_resource_new(
            PrivilegeResourceNew privilege_resource_new) {
        synchronized (this) {
            this.privilege_resource_new = privilege_resource_new;
            privilege_resource_key = privilege_resource_new == null ? null
                    : privilege_resource_new.getPrivilege_resource_key();
            privilege_resource_new__resolvedKey = privilege_resource_key;
        }
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
    @Generated(hash = 971444004)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRolePrivilegeNewDao() : null;
    }



}
