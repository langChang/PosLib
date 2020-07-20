package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

import com.nhsoft.poslib.service.greendao.PosItemDao;
import com.nhsoft.poslib.service.greendao.PosItemTerminalDao;
import com.nhsoft.poslib.service.greendao.DaoSession;

@Entity
public class PosItemTerminal {
    @Id(autoincrement = true)
    private Long id;

    private Long item_num;

    private String item_category_code;

    @ToOne(joinProperty = "item_num")
    private PosItem posItem;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 792020798)
    private transient PosItemTerminalDao myDao;

    @Generated(hash = 760026241)
    private transient Long posItem__resolvedKey;

    @Generated(hash = 1777629694)
    public PosItemTerminal(Long id, Long item_num, String item_category_code) {
        this.id = id;
        this.item_num = item_num;
        this.item_category_code = item_category_code;
    }

    @Generated(hash = 803772066)
    public PosItemTerminal() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItem_num() {
        return this.item_num;
    }

    public void setItem_num(Long item_num) {
        this.item_num = item_num;
    }

    public String getItem_category_code() {
        return this.item_category_code;
    }

    public void setItem_category_code(String item_category_code) {
        this.item_category_code = item_category_code;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 383731143)
    public PosItem getPosItem() {
        Long __key = this.item_num;
        if (posItem__resolvedKey == null || !posItem__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PosItemDao targetDao = daoSession.getPosItemDao();
            PosItem posItemNew = targetDao.load(__key);
            synchronized (this) {
                posItem = posItemNew;
                posItem__resolvedKey = __key;
            }
        }
        return posItem;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1591562205)
    public void setPosItem(PosItem posItem) {
        synchronized (this) {
            this.posItem = posItem;
            item_num = posItem == null ? null : posItem.getItem_num();
            posItem__resolvedKey = item_num;
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
    @Generated(hash = 833674095)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPosItemTerminalDao() : null;
    }
}
