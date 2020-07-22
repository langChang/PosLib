package com.nhsoft.poslib.entity;

import com.nhsoft.poslib.service.greendao.PointPolicyDao;
import com.nhsoft.poslib.service.greendao.PointPolicyDetailDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import com.nhsoft.poslib.service.greendao.DaoSession;

/**
 * Created by Iverson on 2018/11/16 10:54 AM
 * 此类用于：
 */
@Entity
public class PointPolicy {

    /**
     * point_policy_id : 44440000002
     * system_book_code : 4444
     * point_policy_name : 门店双倍积分
     * point_policy_date_from : 2018-11-05T03:59:41.563+0000
     * point_policy_date_to : 2018-11-29T16:00:00.000+0000
     * point_policy_time_from : 2018-11-15T16:00:49.833+0000
     * point_policy_time_to : 2018-11-16T15:59:49.833+0000
     * point_policy_mon_actived : true
     * point_policy_tues_actived : true
     * point_policy_wed_actived : true
     * point_policy_thurs_actived : true
     * point_policy_friday_actived : true
     * point_policy_sat_actived : true
     * point_policy_sun_actived : true
     * point_policy_multiple : 2.0
     * point_policy_creator : 管理员
     * point_policy_status : true
     * point_policy_all_item : false
     * point_policy_type : 商品积分促销
     * point_policy_applied_branch : <?xml version="1.0" encoding="GBK"?>
     * <AppliedBranchArray><AppliedBranch><AppliedBranchNum>1</AppliedBranchNum><AppliedBranchName>台衡PC秤演示门店</AppliedBranchName></AppliedBranch><AppliedBranch><AppliedBranchNum>2</AppliedBranchNum><AppliedBranchName>2店</AppliedBranchName></AppliedBranch><AppliedBranch><AppliedBranchNum>5</AppliedBranchNum><AppliedBranchName>5店</AppliedBranchName></AppliedBranch><AppliedBranch><AppliedBranchNum>6</AppliedBranchNum><AppliedBranchName>6店</AppliedBranchName></AppliedBranch><AppliedBranch><AppliedBranchNum>7</AppliedBranchNum><AppliedBranchName>天猫演示门店</AppliedBranchName></AppliedBranch><AppliedBranch><AppliedBranchNum>99</AppliedBranchNum><AppliedBranchName>管理中心</AppliedBranchName></AppliedBranch><AppliedBranch><AppliedBranchNum>101</AppliedBranchNum><AppliedBranchName>99</AppliedBranchName></AppliedBranch></AppliedBranchArray>
     * point_policy_repeat_type :
     * point_policy_details : [{"point_policy_id":"44440000002","item_num":444400358},{"point_policy_id":"44440000002","item_num":444401501},{"point_policy_id":"44440000002","item_num":444401503},{"point_policy_id":"44440000002","item_num":444401527}]
     */
    @Id
    @Property(nameInDb = "POINT_POLICY_ID")
    private           String                  point_policy_id;
    private           String                  system_book_code;
    private           String                  point_policy_name;
    private           String                  point_policy_date_from;
    private           String                  point_policy_date_to;
    private           String                  point_policy_time_from;
    private           String                  point_policy_time_to;
    private           boolean                 point_policy_mon_actived;
    private           boolean                 point_policy_tues_actived;
    private           boolean                 point_policy_wed_actived;
    private           boolean                 point_policy_thurs_actived;
    private           boolean                 point_policy_friday_actived;
    private           boolean                 point_policy_sat_actived;
    private           boolean                 point_policy_sun_actived;
    private           double                  point_policy_multiple;
    private           String                  point_policy_creator;
    private           boolean                 point_policy_status;
    private           boolean                 point_policy_all_item;
    private           String                  point_policy_type;
    private           String                  point_policy_applied_branch;
    private           String                  point_policy_repeat_type;
    private           String                  point_policy_level_ids;
    @ToMany(referencedJoinProperty = "point_policy_id")
    private           List<PointPolicyDetail> point_policy_details;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession              daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 578393282)
    private transient PointPolicyDao          myDao;

    @Generated(hash = 453165853)
    public PointPolicy(String point_policy_id, String system_book_code, String point_policy_name, String point_policy_date_from, String point_policy_date_to, String point_policy_time_from, String point_policy_time_to, boolean point_policy_mon_actived, boolean point_policy_tues_actived, boolean point_policy_wed_actived, boolean point_policy_thurs_actived, boolean point_policy_friday_actived, boolean point_policy_sat_actived, boolean point_policy_sun_actived, double point_policy_multiple, String point_policy_creator, boolean point_policy_status, boolean point_policy_all_item, String point_policy_type, String point_policy_applied_branch, String point_policy_repeat_type, String point_policy_level_ids) {
        this.point_policy_id = point_policy_id;
        this.system_book_code = system_book_code;
        this.point_policy_name = point_policy_name;
        this.point_policy_date_from = point_policy_date_from;
        this.point_policy_date_to = point_policy_date_to;
        this.point_policy_time_from = point_policy_time_from;
        this.point_policy_time_to = point_policy_time_to;
        this.point_policy_mon_actived = point_policy_mon_actived;
        this.point_policy_tues_actived = point_policy_tues_actived;
        this.point_policy_wed_actived = point_policy_wed_actived;
        this.point_policy_thurs_actived = point_policy_thurs_actived;
        this.point_policy_friday_actived = point_policy_friday_actived;
        this.point_policy_sat_actived = point_policy_sat_actived;
        this.point_policy_sun_actived = point_policy_sun_actived;
        this.point_policy_multiple = point_policy_multiple;
        this.point_policy_creator = point_policy_creator;
        this.point_policy_status = point_policy_status;
        this.point_policy_all_item = point_policy_all_item;
        this.point_policy_type = point_policy_type;
        this.point_policy_applied_branch = point_policy_applied_branch;
        this.point_policy_repeat_type = point_policy_repeat_type;
        this.point_policy_level_ids = point_policy_level_ids;
    }

    @Generated(hash = 195974621)
    public PointPolicy() {
    }

    public void setPoint_policy_details(List<PointPolicyDetail> point_policy_details) {
        this.point_policy_details = point_policy_details;
    }

    public String getPoint_policy_id() {
        return this.point_policy_id;
    }

    public void setPoint_policy_id(String point_policy_id) {
        this.point_policy_id = point_policy_id;
    }

    public String getSystem_book_code() {
        return this.system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public String getPoint_policy_name() {
        return this.point_policy_name;
    }

    public void setPoint_policy_name(String point_policy_name) {
        this.point_policy_name = point_policy_name;
    }

    public String getPoint_policy_date_from() {
        return this.point_policy_date_from;
    }

    public void setPoint_policy_date_from(String point_policy_date_from) {
        this.point_policy_date_from = point_policy_date_from;
    }

    public String getPoint_policy_date_to() {
        return this.point_policy_date_to;
    }

    public void setPoint_policy_date_to(String point_policy_date_to) {
        this.point_policy_date_to = point_policy_date_to;
    }

    public String getPoint_policy_time_from() {
        return this.point_policy_time_from;
    }

    public void setPoint_policy_time_from(String point_policy_time_from) {
        this.point_policy_time_from = point_policy_time_from;
    }

    public String getPoint_policy_time_to() {
        return this.point_policy_time_to;
    }

    public void setPoint_policy_time_to(String point_policy_time_to) {
        this.point_policy_time_to = point_policy_time_to;
    }

    public boolean getPoint_policy_mon_actived() {
        return this.point_policy_mon_actived;
    }

    public void setPoint_policy_mon_actived(boolean point_policy_mon_actived) {
        this.point_policy_mon_actived = point_policy_mon_actived;
    }

    public boolean getPoint_policy_tues_actived() {
        return this.point_policy_tues_actived;
    }

    public void setPoint_policy_tues_actived(boolean point_policy_tues_actived) {
        this.point_policy_tues_actived = point_policy_tues_actived;
    }

    public boolean getPoint_policy_wed_actived() {
        return this.point_policy_wed_actived;
    }

    public void setPoint_policy_wed_actived(boolean point_policy_wed_actived) {
        this.point_policy_wed_actived = point_policy_wed_actived;
    }

    public boolean getPoint_policy_thurs_actived() {
        return this.point_policy_thurs_actived;
    }

    public void setPoint_policy_thurs_actived(boolean point_policy_thurs_actived) {
        this.point_policy_thurs_actived = point_policy_thurs_actived;
    }

    public boolean getPoint_policy_friday_actived() {
        return this.point_policy_friday_actived;
    }

    public void setPoint_policy_friday_actived(boolean point_policy_friday_actived) {
        this.point_policy_friday_actived = point_policy_friday_actived;
    }

    public boolean getPoint_policy_sat_actived() {
        return this.point_policy_sat_actived;
    }

    public void setPoint_policy_sat_actived(boolean point_policy_sat_actived) {
        this.point_policy_sat_actived = point_policy_sat_actived;
    }

    public boolean getPoint_policy_sun_actived() {
        return this.point_policy_sun_actived;
    }

    public void setPoint_policy_sun_actived(boolean point_policy_sun_actived) {
        this.point_policy_sun_actived = point_policy_sun_actived;
    }

    public double getPoint_policy_multiple() {
        return this.point_policy_multiple;
    }

    public void setPoint_policy_multiple(double point_policy_multiple) {
        this.point_policy_multiple = point_policy_multiple;
    }

    public String getPoint_policy_creator() {
        return this.point_policy_creator;
    }

    public void setPoint_policy_creator(String point_policy_creator) {
        this.point_policy_creator = point_policy_creator;
    }

    public boolean getPoint_policy_status() {
        return this.point_policy_status;
    }

    public void setPoint_policy_status(boolean point_policy_status) {
        this.point_policy_status = point_policy_status;
    }

    public boolean getPoint_policy_all_item() {
        return this.point_policy_all_item;
    }

    public void setPoint_policy_all_item(boolean point_policy_all_item) {
        this.point_policy_all_item = point_policy_all_item;
    }

    public String getPoint_policy_type() {
        return this.point_policy_type;
    }

    public void setPoint_policy_type(String point_policy_type) {
        this.point_policy_type = point_policy_type;
    }

    public String getPoint_policy_applied_branch() {
        return this.point_policy_applied_branch;
    }

    public void setPoint_policy_applied_branch(String point_policy_applied_branch) {
        this.point_policy_applied_branch = point_policy_applied_branch;
    }

    public String getPoint_policy_repeat_type() {
        return this.point_policy_repeat_type;
    }

    public void setPoint_policy_repeat_type(String point_policy_repeat_type) {
        this.point_policy_repeat_type = point_policy_repeat_type;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 637483663)
    public List<PointPolicyDetail> getPoint_policy_details() {
        if (point_policy_details == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PointPolicyDetailDao targetDao = daoSession.getPointPolicyDetailDao();
            List<PointPolicyDetail> point_policy_detailsNew = targetDao._queryPointPolicy_Point_policy_details(point_policy_id);
            synchronized (this) {
                if (point_policy_details == null) {
                    point_policy_details = point_policy_detailsNew;
                }
            }
        }
        return point_policy_details;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 641735510)
    public synchronized void resetPoint_policy_details() {
        point_policy_details = null;
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

    public String getPoint_policy_level_ids() {
        return this.point_policy_level_ids;
    }

    public void setPoint_policy_level_ids(String point_policy_level_ids) {
        this.point_policy_level_ids = point_policy_level_ids;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 718509366)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPointPolicyDao() : null;
    }


}
