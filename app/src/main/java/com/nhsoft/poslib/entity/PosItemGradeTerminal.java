package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import com.nhsoft.poslib.service.greendao.PosItemGradeDao;
import com.nhsoft.poslib.service.greendao.PosItemGradeTerminalDao;
import com.nhsoft.poslib.service.greendao.DaoSession;

@Entity
public class PosItemGradeTerminal {
    @Id(autoincrement = true)
    private Long id;

    private Long item_num;

    private int item_grade_num;

    private Long posItemGradeId;

    @ToOne(joinProperty = "posItemGradeId")
    private PosItemGrade posItemGrade;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1498159096)
    private transient PosItemGradeTerminalDao myDao;

    @Generated(hash = 737265870)
    public PosItemGradeTerminal(Long id, Long item_num, int item_grade_num,
            Long posItemGradeId) {
        this.id = id;
        this.item_num = item_num;
        this.item_grade_num = item_grade_num;
        this.posItemGradeId = posItemGradeId;
    }

    @Generated(hash = 2091404772)
    public PosItemGradeTerminal() {
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

    public int getItem_grade_num() {
        return this.item_grade_num;
    }

    public void setItem_grade_num(int item_grade_num) {
        this.item_grade_num = item_grade_num;
    }

    public Long getPosItemGradeId() {
        return this.posItemGradeId;
    }

    public void setPosItemGradeId(Long posItemGradeId) {
        this.posItemGradeId = posItemGradeId;
    }

    @Generated(hash = 115195644)
    private transient Long posItemGrade__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 551186047)
    public PosItemGrade getPosItemGrade() {
        Long __key = this.posItemGradeId;
        if (posItemGrade__resolvedKey == null
                || !posItemGrade__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PosItemGradeDao targetDao = daoSession.getPosItemGradeDao();
            PosItemGrade posItemGradeNew = targetDao.load(__key);
            synchronized (this) {
                posItemGrade = posItemGradeNew;
                posItemGrade__resolvedKey = __key;
            }
        }
        return posItemGrade;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1597728472)
    public void setPosItemGrade(PosItemGrade posItemGrade) {
        synchronized (this) {
            this.posItemGrade = posItemGrade;
            posItemGradeId = posItemGrade == null ? null : posItemGrade.getId();
            posItemGrade__resolvedKey = posItemGradeId;
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
    @Generated(hash = 1628377258)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPosItemGradeTerminalDao() : null;
    }

   

}
