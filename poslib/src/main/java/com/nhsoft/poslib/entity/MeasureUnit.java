package com.nhsoft.poslib.entity;

import com.nhsoft.poslib.service.greendao.MeasureUnitDao;
import com.nhsoft.poslib.service.greendao.MeasureUnitItemDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;
import java.util.List;
import com.nhsoft.poslib.service.greendao.DaoSession;

@Entity
public class MeasureUnit {

    @Id
    @Property(nameInDb = "GROUP_NAME")
    private String group_name;
    @ToMany(referencedJoinProperty = "group_name")
    private List<MeasureUnitItem>item_units=new ArrayList<MeasureUnitItem>(0);


    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 894292046)
    private transient MeasureUnitDao myDao;
    @Generated(hash = 597085323)
    public MeasureUnit(String group_name) {
        this.group_name = group_name;
    }
    @Generated(hash = 1838431005)
    public MeasureUnit() {
    }
    public String getGroup_name() {
        return this.group_name;
    }
    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 918245471)
    public List<MeasureUnitItem> getItem_units() {
        if (item_units == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MeasureUnitItemDao targetDao = daoSession.getMeasureUnitItemDao();
            List<MeasureUnitItem> item_unitsNew = targetDao
                    ._queryMeasureUnit_Item_units(group_name);
            synchronized (this) {
                if (item_units == null) {
                    item_units = item_unitsNew;
                }
            }
        }
        return item_units;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 208491279)
    public synchronized void resetItem_units() {
        item_units = null;
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
    @Generated(hash = 1037843502)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMeasureUnitDao() : null;
    }

}
