package com.nhsoft.poslib.entity.nongmao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Transient;

import com.nhsoft.poslib.service.greendao.StallDiscountDetailDao;
import com.nhsoft.poslib.service.greendao.StallDiscountDao;
import com.nhsoft.poslib.service.greendao.DaoSession;

/**
 * 农贸
 * stall.discount
 * 档口超额折扣
 */
@Entity
public class StallDiscount {


    /**
     * branch_num : 0
     * policy_discount_audit_time : string
     * policy_discount_auditor : string
     * policy_discount_bill_money : 0
     * policy_discount_cancel_operator : string
     * policy_discount_cancel_time : string
     * policy_discount_card_only : true
     * policy_discount_card_type : string
     * policy_discount_create_time : string
     * policy_discount_creator : string
     * policy_discount_date_from : string
     * policy_discount_date_to : string
     * policy_discount_discount : 0
     * policy_discount_discount_money : 0
     * policy_discount_friday_actived : true
     * policy_discount_last_edit_time : string
     * policy_discount_memo : string
     * policy_discount_mon_actived : true
     * policy_discount_no : string
     * policy_discount_sat_actived : true
     * policy_discount_sun_actived : true
     * policy_discount_thurs_actived : true
     * policy_discount_time_from : string
     * policy_discount_time_to : string
     * policy_discount_total_discount : 0
     * policy_discount_tues_actived : true
     * policy_discount_wed_actived : true
     * stall_discount_details : []
     * state_code : 0
     * state_name : string
     * system_book_code : string
     */
    @Id
    private String  policy_discount_no;
    private int branch_num;
    private String  policy_discount_audit_time;
    private String  policy_discount_auditor;
    private int     policy_discount_bill_money;
    private String  policy_discount_cancel_operator;
    private String  policy_discount_cancel_time;
    private boolean policy_discount_card_only;
    private String  policy_discount_card_type;
    private String  policy_discount_create_time;
    private String  policy_discount_creator;
    private String  policy_discount_date_from;
    private String  policy_discount_date_to;
    private int     policy_discount_discount;
    private int     policy_discount_discount_money;
    private boolean policy_discount_friday_actived;
    private String  policy_discount_last_edit_time;
    private String  policy_discount_memo;
    private boolean policy_discount_mon_actived;

    private boolean policy_discount_sat_actived;
    private boolean policy_discount_sun_actived;
    private boolean policy_discount_thurs_actived;
    private String  policy_discount_time_from;
    private String  policy_discount_time_to;
    private int     policy_discount_total_discount;
    private boolean policy_discount_tues_actived;
    private boolean policy_discount_wed_actived;
    private int     state_code;
    private String  state_name;
    private String  system_book_code;
    private String stallList;

    @Transient
    private List<Stall> stalls;
    public String getStallList() {
        return stallList;
    }

    public List<Stall> getStalls() {
        return stalls;
    }

    public void setStalls(List<Stall> stalls) {
        this.stalls = stalls;
    }



    @ToMany(referencedJoinProperty = "policy_discount_no")
    private List<StallDiscountDetail> stall_discount_details;
    public void setStall_discount_details(List<StallDiscountDetail> stall_discount_details) {
        this.stall_discount_details = stall_discount_details;
    }

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1979845351)
    private transient StallDiscountDao myDao;


    @Generated(hash = 561091868)
    public StallDiscount(String policy_discount_no, int branch_num, String policy_discount_audit_time,
            String policy_discount_auditor, int policy_discount_bill_money,
            String policy_discount_cancel_operator, String policy_discount_cancel_time,
            boolean policy_discount_card_only, String policy_discount_card_type,
            String policy_discount_create_time, String policy_discount_creator,
            String policy_discount_date_from, String policy_discount_date_to,
            int policy_discount_discount, int policy_discount_discount_money,
            boolean policy_discount_friday_actived, String policy_discount_last_edit_time,
            String policy_discount_memo, boolean policy_discount_mon_actived,
            boolean policy_discount_sat_actived, boolean policy_discount_sun_actived,
            boolean policy_discount_thurs_actived, String policy_discount_time_from,
            String policy_discount_time_to, int policy_discount_total_discount,
            boolean policy_discount_tues_actived, boolean policy_discount_wed_actived, int state_code,
            String state_name, String system_book_code, String stallList) {
        this.policy_discount_no = policy_discount_no;
        this.branch_num = branch_num;
        this.policy_discount_audit_time = policy_discount_audit_time;
        this.policy_discount_auditor = policy_discount_auditor;
        this.policy_discount_bill_money = policy_discount_bill_money;
        this.policy_discount_cancel_operator = policy_discount_cancel_operator;
        this.policy_discount_cancel_time = policy_discount_cancel_time;
        this.policy_discount_card_only = policy_discount_card_only;
        this.policy_discount_card_type = policy_discount_card_type;
        this.policy_discount_create_time = policy_discount_create_time;
        this.policy_discount_creator = policy_discount_creator;
        this.policy_discount_date_from = policy_discount_date_from;
        this.policy_discount_date_to = policy_discount_date_to;
        this.policy_discount_discount = policy_discount_discount;
        this.policy_discount_discount_money = policy_discount_discount_money;
        this.policy_discount_friday_actived = policy_discount_friday_actived;
        this.policy_discount_last_edit_time = policy_discount_last_edit_time;
        this.policy_discount_memo = policy_discount_memo;
        this.policy_discount_mon_actived = policy_discount_mon_actived;
        this.policy_discount_sat_actived = policy_discount_sat_actived;
        this.policy_discount_sun_actived = policy_discount_sun_actived;
        this.policy_discount_thurs_actived = policy_discount_thurs_actived;
        this.policy_discount_time_from = policy_discount_time_from;
        this.policy_discount_time_to = policy_discount_time_to;
        this.policy_discount_total_discount = policy_discount_total_discount;
        this.policy_discount_tues_actived = policy_discount_tues_actived;
        this.policy_discount_wed_actived = policy_discount_wed_actived;
        this.state_code = state_code;
        this.state_name = state_name;
        this.system_book_code = system_book_code;
        this.stallList = stallList;
    }

    @Generated(hash = 29938442)
    public StallDiscount() {
    }
    /** Used for active entity operations. */

    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public String getPolicy_discount_audit_time() {
        return policy_discount_audit_time;
    }

    public void setPolicy_discount_audit_time(String policy_discount_audit_time) {
        this.policy_discount_audit_time = policy_discount_audit_time;
    }

    public String getPolicy_discount_auditor() {
        return policy_discount_auditor;
    }

    public void setPolicy_discount_auditor(String policy_discount_auditor) {
        this.policy_discount_auditor = policy_discount_auditor;
    }

    public int getPolicy_discount_bill_money() {
        return policy_discount_bill_money;
    }

    public void setPolicy_discount_bill_money(int policy_discount_bill_money) {
        this.policy_discount_bill_money = policy_discount_bill_money;
    }

    public String getPolicy_discount_cancel_operator() {
        return policy_discount_cancel_operator;
    }

    public void setPolicy_discount_cancel_operator(String policy_discount_cancel_operator) {
        this.policy_discount_cancel_operator = policy_discount_cancel_operator;
    }

    public String getPolicy_discount_cancel_time() {
        return policy_discount_cancel_time;
    }

    public void setPolicy_discount_cancel_time(String policy_discount_cancel_time) {
        this.policy_discount_cancel_time = policy_discount_cancel_time;
    }

    public boolean isPolicy_discount_card_only() {
        return policy_discount_card_only;
    }

    public void setPolicy_discount_card_only(boolean policy_discount_card_only) {
        this.policy_discount_card_only = policy_discount_card_only;
    }

    public String getPolicy_discount_card_type() {
        return policy_discount_card_type;
    }

    public void setPolicy_discount_card_type(String policy_discount_card_type) {
        this.policy_discount_card_type = policy_discount_card_type;
    }

    public String getPolicy_discount_create_time() {
        return policy_discount_create_time;
    }

    public void setPolicy_discount_create_time(String policy_discount_create_time) {
        this.policy_discount_create_time = policy_discount_create_time;
    }

    public String getPolicy_discount_creator() {
        return policy_discount_creator;
    }

    public void setPolicy_discount_creator(String policy_discount_creator) {
        this.policy_discount_creator = policy_discount_creator;
    }

    public String getPolicy_discount_date_from() {
        return policy_discount_date_from;
    }

    public void setPolicy_discount_date_from(String policy_discount_date_from) {
        this.policy_discount_date_from = policy_discount_date_from;
    }

    public String getPolicy_discount_date_to() {
        return policy_discount_date_to;
    }

    public void setPolicy_discount_date_to(String policy_discount_date_to) {
        this.policy_discount_date_to = policy_discount_date_to;
    }

    public int getPolicy_discount_discount() {
        return policy_discount_discount;
    }

    public void setPolicy_discount_discount(int policy_discount_discount) {
        this.policy_discount_discount = policy_discount_discount;
    }

    public int getPolicy_discount_discount_money() {
        return policy_discount_discount_money;
    }

    public void setPolicy_discount_discount_money(int policy_discount_discount_money) {
        this.policy_discount_discount_money = policy_discount_discount_money;
    }

    public boolean isPolicy_discount_friday_actived() {
        return policy_discount_friday_actived;
    }

    public void setPolicy_discount_friday_actived(boolean policy_discount_friday_actived) {
        this.policy_discount_friday_actived = policy_discount_friday_actived;
    }

    public String getPolicy_discount_last_edit_time() {
        return policy_discount_last_edit_time;
    }

    public void setPolicy_discount_last_edit_time(String policy_discount_last_edit_time) {
        this.policy_discount_last_edit_time = policy_discount_last_edit_time;
    }

    public String getPolicy_discount_memo() {
        return policy_discount_memo;
    }

    public void setPolicy_discount_memo(String policy_discount_memo) {
        this.policy_discount_memo = policy_discount_memo;
    }

    public boolean isPolicy_discount_mon_actived() {
        return policy_discount_mon_actived;
    }

    public void setPolicy_discount_mon_actived(boolean policy_discount_mon_actived) {
        this.policy_discount_mon_actived = policy_discount_mon_actived;
    }

    public String getPolicy_discount_no() {
        return policy_discount_no;
    }

    public void setPolicy_discount_no(String policy_discount_no) {
        this.policy_discount_no = policy_discount_no;
    }

    public boolean isPolicy_discount_sat_actived() {
        return policy_discount_sat_actived;
    }

    public void setPolicy_discount_sat_actived(boolean policy_discount_sat_actived) {
        this.policy_discount_sat_actived = policy_discount_sat_actived;
    }

    public boolean isPolicy_discount_sun_actived() {
        return policy_discount_sun_actived;
    }

    public void setPolicy_discount_sun_actived(boolean policy_discount_sun_actived) {
        this.policy_discount_sun_actived = policy_discount_sun_actived;
    }

    public boolean isPolicy_discount_thurs_actived() {
        return policy_discount_thurs_actived;
    }

    public void setPolicy_discount_thurs_actived(boolean policy_discount_thurs_actived) {
        this.policy_discount_thurs_actived = policy_discount_thurs_actived;
    }

    public String getPolicy_discount_time_from() {
        return policy_discount_time_from;
    }

    public void setPolicy_discount_time_from(String policy_discount_time_from) {
        this.policy_discount_time_from = policy_discount_time_from;
    }

    public String getPolicy_discount_time_to() {
        return policy_discount_time_to;
    }

    public void setPolicy_discount_time_to(String policy_discount_time_to) {
        this.policy_discount_time_to = policy_discount_time_to;
    }

    public int getPolicy_discount_total_discount() {
        return policy_discount_total_discount;
    }

    public void setPolicy_discount_total_discount(int policy_discount_total_discount) {
        this.policy_discount_total_discount = policy_discount_total_discount;
    }

    public boolean isPolicy_discount_tues_actived() {
        return policy_discount_tues_actived;
    }

    public void setPolicy_discount_tues_actived(boolean policy_discount_tues_actived) {
        this.policy_discount_tues_actived = policy_discount_tues_actived;
    }

    public boolean isPolicy_discount_wed_actived() {
        return policy_discount_wed_actived;
    }

    public void setPolicy_discount_wed_actived(boolean policy_discount_wed_actived) {
        this.policy_discount_wed_actived = policy_discount_wed_actived;
    }

    public int getState_code() {
        return state_code;
    }

    public void setState_code(int state_code) {
        this.state_code = state_code;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public boolean getPolicy_discount_card_only() {
        return this.policy_discount_card_only;
    }

    public boolean getPolicy_discount_friday_actived() {
        return this.policy_discount_friday_actived;
    }

    public boolean getPolicy_discount_mon_actived() {
        return this.policy_discount_mon_actived;
    }

    public boolean getPolicy_discount_sat_actived() {
        return this.policy_discount_sat_actived;
    }

    public boolean getPolicy_discount_sun_actived() {
        return this.policy_discount_sun_actived;
    }

    public boolean getPolicy_discount_thurs_actived() {
        return this.policy_discount_thurs_actived;
    }

    public boolean getPolicy_discount_tues_actived() {
        return this.policy_discount_tues_actived;
    }

    public boolean getPolicy_discount_wed_actived() {
        return this.policy_discount_wed_actived;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 111805772)
    public List<StallDiscountDetail> getStall_discount_details() {
        if (stall_discount_details == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StallDiscountDetailDao targetDao = daoSession.getStallDiscountDetailDao();
            List<StallDiscountDetail> stall_discount_detailsNew = targetDao
                    ._queryStallDiscount_Stall_discount_details(policy_discount_no);
            synchronized (this) {
                if (stall_discount_details == null) {
                    stall_discount_details = stall_discount_detailsNew;
                }
            }
        }
        return stall_discount_details;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1812992548)
    public synchronized void resetStall_discount_details() {
        stall_discount_details = null;
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

    public void setStallList(String stallList) {
        this.stallList = stallList;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 104824588)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStallDiscountDao() : null;
    }




}
