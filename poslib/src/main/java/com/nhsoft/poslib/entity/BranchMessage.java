package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.nhsoft.poslib.service.greendao.DaoSession;
import com.nhsoft.poslib.service.greendao.BranchsBeanDao;
import com.nhsoft.poslib.service.greendao.BranchMessageDao;
@Entity
public class BranchMessage {

    /**
     * pos_screen_context : 是19年的最后一天
     ‘(?ì _ í?)bilibil ?
     * branchs : [{"branch_num":1,"branch_name":"分店一","branch_matrix_price_actived":false},{"branch_num":2,"branch_name":"分店二","branch_matrix_price_actived":false},{"branch_num":9,"branch_name":"分店三","branch_matrix_price_actived":false}]
     */

    @Id
    private Long id;
    private String pos_screen_context;//滚动信息
    @ToMany(referencedJoinProperty = "branchMessageId")
    private List<BranchsBean> branchs;//应用门店列表
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 937955880)
    private transient BranchMessageDao myDao;
    @Generated(hash = 1245374408)
    public BranchMessage(Long id, String pos_screen_context) {
        this.id = id;
        this.pos_screen_context = pos_screen_context;
    }
    @Generated(hash = 847185566)
    public BranchMessage() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPos_screen_context() {
        return this.pos_screen_context;
    }
    public void setPos_screen_context(String pos_screen_context) {
        this.pos_screen_context = pos_screen_context;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1435285828)
    public List<BranchsBean> getBranchs() {
        if (branchs == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BranchsBeanDao targetDao = daoSession.getBranchsBeanDao();
            List<BranchsBean> branchsNew = targetDao._queryBranchMessage_Branchs(id);
            synchronized (this) {
                if (branchs == null) {
                    branchs = branchsNew;
                }
            }
        }
        return branchs;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1381247363)
    public synchronized void resetBranchs() {
        branchs = null;
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
    @Generated(hash = 1103910301)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBranchMessageDao() : null;
    }

}
