package com.nhsoft.poslib.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import com.nhsoft.poslib.service.greendao.VipCRMLevelDetailDao;
import com.nhsoft.poslib.service.greendao.VipCRMLevelDao;
import com.nhsoft.poslib.service.greendao.DaoSession;


@Entity
public class VipCRMLevel implements Cloneable{

    @Id(autoincrement = true)
    private Long idAuto;
    private String name;
    private int rank;
    @ToMany(referencedJoinProperty = "vipCRMLevelId")
    private List<VipCRMLevelDetail> categories ;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 742492290)
    private transient VipCRMLevelDao myDao;



    @Generated(hash = 635655210)
    public VipCRMLevel(Long idAuto, String name, int rank) {
        this.idAuto = idAuto;
        this.name = name;
        this.rank = rank;
    }

    @Generated(hash = 2007893045)
    public VipCRMLevel() {
    }

    public List<VipCRMLevelDetail> getCategoriesSelf(){
        return categories;
    }

    public void setCategories(List<VipCRMLevelDetail> categories) {
        this.categories = categories;
    }

    public Long getIdAuto() {
        return this.idAuto;
    }
    public void setIdAuto(Long idAuto) {
        this.idAuto = idAuto;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getRank() {
        return this.rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1901542495)
    public List<VipCRMLevelDetail> getCategories() {
        if (categories == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            VipCRMLevelDetailDao targetDao = daoSession.getVipCRMLevelDetailDao();
            List<VipCRMLevelDetail> categoriesNew = targetDao
                    ._queryVipCRMLevel_Categories(idAuto);
            synchronized (this) {
                if (categories == null) {
                    categories = categoriesNew;
                }
            }
        }
        return categories;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1494004962)
    public synchronized void resetCategories() {
        categories = null;
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
    @Generated(hash = 1327257219)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getVipCRMLevelDao() : null;
    }
}
