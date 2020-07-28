package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import com.nhsoft.poslib.service.greendao.PointOrderDetialDao;
import com.nhsoft.poslib.service.greendao.PointOrderDao;
import com.nhsoft.poslib.service.greendao.DaoSession;

@Entity
public class PointOrder {

    /**
     * "point_order_end": "string",
     * "point_order_fid": "string",
     * "point_order_memo": "string",
     * "point_order_name": "string",
     * "point_order_start": "string",
     * "point_state_code": 0,
     * "point_state_name": "string",
     * "system_book_code": "string"
     * "branch_num": 0,
     * "point_order_applied_branch": "string",
     */
    @Id
    @Property(nameInDb = "point_order_fid")
    private String point_order_fid;
    private String system_book_code;
    private int branch_num;
    private String point_order_name;
    private String point_order_start;
    private String point_order_end;
    private String point_order_applied_branch;
    private String point_order_creat_or;
    private String point_order_createTime;
    private String point_order_audit_or;
    private String point_order_audit_time;
    private String point_order_cancel_operator;
    private String poin_order_cancel_time;
    private String point_state_name;
    private int point_state_code;
    private String point_order_memo;
    @ToMany(referencedJoinProperty = "point_order_fid")
    private List<PointOrderDetial>point_order_details;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 32282207)
    private transient PointOrderDao myDao;
    @Generated(hash = 898910604)
    public PointOrder(String point_order_fid, String system_book_code,
            int branch_num, String point_order_name, String point_order_start,
            String point_order_end, String point_order_applied_branch,
            String point_order_creat_or, String point_order_createTime,
            String point_order_audit_or, String point_order_audit_time,
            String point_order_cancel_operator, String poin_order_cancel_time,
            String point_state_name, int point_state_code,
            String point_order_memo) {
        this.point_order_fid = point_order_fid;
        this.system_book_code = system_book_code;
        this.branch_num = branch_num;
        this.point_order_name = point_order_name;
        this.point_order_start = point_order_start;
        this.point_order_end = point_order_end;
        this.point_order_applied_branch = point_order_applied_branch;
        this.point_order_creat_or = point_order_creat_or;
        this.point_order_createTime = point_order_createTime;
        this.point_order_audit_or = point_order_audit_or;
        this.point_order_audit_time = point_order_audit_time;
        this.point_order_cancel_operator = point_order_cancel_operator;
        this.poin_order_cancel_time = poin_order_cancel_time;
        this.point_state_name = point_state_name;
        this.point_state_code = point_state_code;
        this.point_order_memo = point_order_memo;
    }
    @Generated(hash = 585796335)
    public PointOrder() {
    }
    public String getPoint_order_fid() {
        return this.point_order_fid;
    }
    public void setPoint_order_fid(String point_order_fid) {
        this.point_order_fid = point_order_fid;
    }
    public String getSystem_book_code() {
        return this.system_book_code;
    }
    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }
    public int getBranch_num() {
        return this.branch_num;
    }
    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }
    public String getPoint_order_name() {
        return this.point_order_name;
    }
    public void setPoint_order_name(String point_order_name) {
        this.point_order_name = point_order_name;
    }
    public String getPoint_order_start() {
        return this.point_order_start;
    }
    public void setPoint_order_start(String point_order_start) {
        this.point_order_start = point_order_start;
    }
    public String getPoint_order_end() {
        return this.point_order_end;
    }
    public void setPoint_order_end(String point_order_end) {
        this.point_order_end = point_order_end;
    }
    public String getPoint_order_applied_branch() {
        return this.point_order_applied_branch;
    }
    public void setPoint_order_applied_branch(String point_order_applied_branch) {
        this.point_order_applied_branch = point_order_applied_branch;
    }
    public String getPoint_order_creat_or() {
        return this.point_order_creat_or;
    }
    public void setPoint_order_creat_or(String point_order_creat_or) {
        this.point_order_creat_or = point_order_creat_or;
    }
    public String getPoint_order_createTime() {
        return this.point_order_createTime;
    }
    public void setPoint_order_createTime(String point_order_createTime) {
        this.point_order_createTime = point_order_createTime;
    }
    public String getPoint_order_audit_or() {
        return this.point_order_audit_or;
    }
    public void setPoint_order_audit_or(String point_order_audit_or) {
        this.point_order_audit_or = point_order_audit_or;
    }
    public String getPoint_order_audit_time() {
        return this.point_order_audit_time;
    }
    public void setPoint_order_audit_time(String point_order_audit_time) {
        this.point_order_audit_time = point_order_audit_time;
    }
    public String getPoint_order_cancel_operator() {
        return this.point_order_cancel_operator;
    }
    public void setPoint_order_cancel_operator(String point_order_cancel_operator) {
        this.point_order_cancel_operator = point_order_cancel_operator;
    }
    public String getPoin_order_cancel_time() {
        return this.poin_order_cancel_time;
    }
    public void setPoin_order_cancel_time(String poin_order_cancel_time) {
        this.poin_order_cancel_time = poin_order_cancel_time;
    }
    public String getPoint_state_name() {
        return this.point_state_name;
    }
    public void setPoint_state_name(String point_state_name) {
        this.point_state_name = point_state_name;
    }
    public int getPoint_state_code() {
        return this.point_state_code;
    }
    public void setPoint_state_code(int point_state_code) {
        this.point_state_code = point_state_code;
    }
    public String getPoint_order_memo() {
        return this.point_order_memo;
    }
    public void setPoint_order_memo(String point_order_memo) {
        this.point_order_memo = point_order_memo;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1207362753)
    public List<PointOrderDetial> getPoint_order_details() {
        if (point_order_details == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PointOrderDetialDao targetDao = daoSession.getPointOrderDetialDao();
            List<PointOrderDetial> point_order_detailsNew = targetDao
                    ._queryPointOrder_Point_order_details(point_order_fid);
            synchronized (this) {
                if (point_order_details == null) {
                    point_order_details = point_order_detailsNew;
                }
            }
        }
        return point_order_details;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1446050523)
    public synchronized void resetPoint_order_details() {
        point_order_details = null;
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
    @Generated(hash = 1162985686)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPointOrderDao() : null;
    }



}
