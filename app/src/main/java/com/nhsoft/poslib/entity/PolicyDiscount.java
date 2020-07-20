package com.nhsoft.poslib.entity;

import com.nhsoft.poslib.service.greendao.PolicyDiscountDao;
import com.nhsoft.poslib.service.greendao.PolicyDiscountDetailDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import com.nhsoft.poslib.service.greendao.DaoSession;

/**
 * Created by Iverson on 2019/4/16 2:30 PM
 * 此类用于：
 */
@Entity
public class PolicyDiscount {

    @Id
    private String policy_discount_no;
    private String system_book_code;
    private String policy_discount_date_from;
    private String policy_discount_date_to;
    private String policy_discount_time_from;
    private String policy_discount_time_to;
    private String policy_discount_applied_branch;
    private boolean policy_discount_mon_actived;
    private boolean policy_discount_tues_actived;
    private boolean policy_discount_wed_actived;
    private boolean policy_discount_thurs_actived;
    private boolean policy_discount_friday_actived;
    private boolean policy_discount_sat_actived;
    private boolean policy_discount_sun_actived;
    private int policy_discount_state_code;
    private String policy_discount_state_name;
    private String policy_discount_create_time;
    private String policy_discount_creator;
    private String policy_discount_audit_time;
    private String policy_discount_auditor;
    private String policy_discount_memo;
    private String policy_discount_cancel_time;
    private String policy_discount_cancel_operator;
    private int branch_num;
    private float policy_discount_bill_money;
    private float policy_discount_discount;
    private boolean policy_discount_card_only;
    private String policy_discount_card_type;
    private String policy_discount_repeat_type;
    private String policy_discount_repeat_end;
    private float policy_discount_discount_money;
    private float policy_discount_total_discount;
    private String policy_discount_assigned_item;
    private String policy_discount_assigned_type;
    private String policy_discount_assigned_category;
    private String policy_discount_department;
    private String policy_discount_last_edit_time;
    private String policy_discount_last_editor;
    private String policy_discount_type;
    private String policy_discount_level_ids;



    public String getPolicy_discount_type() {
        return policy_discount_type;
    }

    public void setPolicy_discount_type(String policy_discount_type) {
        this.policy_discount_type = policy_discount_type;
    }



    @ToMany(referencedJoinProperty = "policy_discount_no")
    private List<PolicyDiscountDetail>policy_discount_details;

    public void setPolicy_discount_details(List<PolicyDiscountDetail> policy_discount_details) {
        this.policy_discount_details = policy_discount_details;
    }

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1437830612)
    private transient PolicyDiscountDao myDao;



    @Generated(hash = 201557001)
    public PolicyDiscount(String policy_discount_no, String system_book_code,
            String policy_discount_date_from, String policy_discount_date_to,
            String policy_discount_time_from, String policy_discount_time_to,
            String policy_discount_applied_branch, boolean policy_discount_mon_actived,
            boolean policy_discount_tues_actived, boolean policy_discount_wed_actived,
            boolean policy_discount_thurs_actived, boolean policy_discount_friday_actived,
            boolean policy_discount_sat_actived, boolean policy_discount_sun_actived,
            int policy_discount_state_code, String policy_discount_state_name,
            String policy_discount_create_time, String policy_discount_creator,
            String policy_discount_audit_time, String policy_discount_auditor,
            String policy_discount_memo, String policy_discount_cancel_time,
            String policy_discount_cancel_operator, int branch_num, float policy_discount_bill_money,
            float policy_discount_discount, boolean policy_discount_card_only,
            String policy_discount_card_type, String policy_discount_repeat_type,
            String policy_discount_repeat_end, float policy_discount_discount_money,
            float policy_discount_total_discount, String policy_discount_assigned_item,
            String policy_discount_assigned_type, String policy_discount_assigned_category,
            String policy_discount_department, String policy_discount_last_edit_time,
            String policy_discount_last_editor, String policy_discount_type,
            String policy_discount_level_ids) {
        this.policy_discount_no = policy_discount_no;
        this.system_book_code = system_book_code;
        this.policy_discount_date_from = policy_discount_date_from;
        this.policy_discount_date_to = policy_discount_date_to;
        this.policy_discount_time_from = policy_discount_time_from;
        this.policy_discount_time_to = policy_discount_time_to;
        this.policy_discount_applied_branch = policy_discount_applied_branch;
        this.policy_discount_mon_actived = policy_discount_mon_actived;
        this.policy_discount_tues_actived = policy_discount_tues_actived;
        this.policy_discount_wed_actived = policy_discount_wed_actived;
        this.policy_discount_thurs_actived = policy_discount_thurs_actived;
        this.policy_discount_friday_actived = policy_discount_friday_actived;
        this.policy_discount_sat_actived = policy_discount_sat_actived;
        this.policy_discount_sun_actived = policy_discount_sun_actived;
        this.policy_discount_state_code = policy_discount_state_code;
        this.policy_discount_state_name = policy_discount_state_name;
        this.policy_discount_create_time = policy_discount_create_time;
        this.policy_discount_creator = policy_discount_creator;
        this.policy_discount_audit_time = policy_discount_audit_time;
        this.policy_discount_auditor = policy_discount_auditor;
        this.policy_discount_memo = policy_discount_memo;
        this.policy_discount_cancel_time = policy_discount_cancel_time;
        this.policy_discount_cancel_operator = policy_discount_cancel_operator;
        this.branch_num = branch_num;
        this.policy_discount_bill_money = policy_discount_bill_money;
        this.policy_discount_discount = policy_discount_discount;
        this.policy_discount_card_only = policy_discount_card_only;
        this.policy_discount_card_type = policy_discount_card_type;
        this.policy_discount_repeat_type = policy_discount_repeat_type;
        this.policy_discount_repeat_end = policy_discount_repeat_end;
        this.policy_discount_discount_money = policy_discount_discount_money;
        this.policy_discount_total_discount = policy_discount_total_discount;
        this.policy_discount_assigned_item = policy_discount_assigned_item;
        this.policy_discount_assigned_type = policy_discount_assigned_type;
        this.policy_discount_assigned_category = policy_discount_assigned_category;
        this.policy_discount_department = policy_discount_department;
        this.policy_discount_last_edit_time = policy_discount_last_edit_time;
        this.policy_discount_last_editor = policy_discount_last_editor;
        this.policy_discount_type = policy_discount_type;
        this.policy_discount_level_ids = policy_discount_level_ids;
    }

    @Generated(hash = 443859017)
    public PolicyDiscount() {
    }

    public void setPolicyDiscountDetalils(List<PolicyDiscountDetail> policy_discount_details){
        this.policy_discount_details = policy_discount_details;
    }



    public String getPolicy_discount_no() {
        return this.policy_discount_no;
    }
    public void setPolicy_discount_no(String policy_discount_no) {
        this.policy_discount_no = policy_discount_no;
    }
    public String getSystem_book_code() {
        return this.system_book_code;
    }
    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }
    public String getPolicy_discount_date_from() {
        return this.policy_discount_date_from;
    }
    public void setPolicy_discount_date_from(String policy_discount_date_from) {
        this.policy_discount_date_from = policy_discount_date_from;
    }
    public String getPolicy_discount_date_to() {
        return this.policy_discount_date_to;
    }
    public void setPolicy_discount_date_to(String policy_discount_date_to) {
        this.policy_discount_date_to = policy_discount_date_to;
    }
    public String getPolicy_discount_time_from() {
        return this.policy_discount_time_from;
    }
    public void setPolicy_discount_time_from(String policy_discount_time_from) {
        this.policy_discount_time_from = policy_discount_time_from;
    }
    public String getPolicy_discount_time_to() {
        return this.policy_discount_time_to;
    }
    public void setPolicy_discount_time_to(String policy_discount_time_to) {
        this.policy_discount_time_to = policy_discount_time_to;
    }
    public String getPolicy_discount_applied_branch() {
        return this.policy_discount_applied_branch;
    }
    public void setPolicy_discount_applied_branch(
            String policy_discount_applied_branch) {
        this.policy_discount_applied_branch = policy_discount_applied_branch;
    }
    public boolean getPolicy_discount_mon_actived() {
        return this.policy_discount_mon_actived;
    }
    public void setPolicy_discount_mon_actived(
            boolean policy_discount_mon_actived) {
        this.policy_discount_mon_actived = policy_discount_mon_actived;
    }
    public boolean getPolicy_discount_tues_actived() {
        return this.policy_discount_tues_actived;
    }
    public void setPolicy_discount_tues_actived(
            boolean policy_discount_tues_actived) {
        this.policy_discount_tues_actived = policy_discount_tues_actived;
    }
    public boolean getPolicy_discount_wed_actived() {
        return this.policy_discount_wed_actived;
    }
    public void setPolicy_discount_wed_actived(
            boolean policy_discount_wed_actived) {
        this.policy_discount_wed_actived = policy_discount_wed_actived;
    }
    public boolean getPolicy_discount_thurs_actived() {
        return this.policy_discount_thurs_actived;
    }
    public void setPolicy_discount_thurs_actived(
            boolean policy_discount_thurs_actived) {
        this.policy_discount_thurs_actived = policy_discount_thurs_actived;
    }
    public boolean getPolicy_discount_friday_actived() {
        return this.policy_discount_friday_actived;
    }
    public void setPolicy_discount_friday_actived(
            boolean policy_discount_friday_actived) {
        this.policy_discount_friday_actived = policy_discount_friday_actived;
    }
    public boolean getPolicy_discount_sat_actived() {
        return this.policy_discount_sat_actived;
    }
    public void setPolicy_discount_sat_actived(
            boolean policy_discount_sat_actived) {
        this.policy_discount_sat_actived = policy_discount_sat_actived;
    }
    public boolean getPolicy_discount_sun_actived() {
        return this.policy_discount_sun_actived;
    }
    public void setPolicy_discount_sun_actived(
            boolean policy_discount_sun_actived) {
        this.policy_discount_sun_actived = policy_discount_sun_actived;
    }
    public int getPolicy_discount_state_code() {
        return this.policy_discount_state_code;
    }
    public void setPolicy_discount_state_code(int policy_discount_state_code) {
        this.policy_discount_state_code = policy_discount_state_code;
    }
    public String getPolicy_discount_state_name() {
        return this.policy_discount_state_name;
    }
    public void setPolicy_discount_state_name(String policy_discount_state_name) {
        this.policy_discount_state_name = policy_discount_state_name;
    }
    public String getPolicy_discount_create_time() {
        return this.policy_discount_create_time;
    }
    public void setPolicy_discount_create_time(String policy_discount_create_time) {
        this.policy_discount_create_time = policy_discount_create_time;
    }
    public String getPolicy_discount_creator() {
        return this.policy_discount_creator;
    }
    public void setPolicy_discount_creator(String policy_discount_creator) {
        this.policy_discount_creator = policy_discount_creator;
    }
    public String getPolicy_discount_audit_time() {
        return this.policy_discount_audit_time;
    }
    public void setPolicy_discount_audit_time(String policy_discount_audit_time) {
        this.policy_discount_audit_time = policy_discount_audit_time;
    }
    public String getPolicy_discount_auditor() {
        return this.policy_discount_auditor;
    }
    public void setPolicy_discount_auditor(String policy_discount_auditor) {
        this.policy_discount_auditor = policy_discount_auditor;
    }
    public String getPolicy_discount_memo() {
        return this.policy_discount_memo;
    }
    public void setPolicy_discount_memo(String policy_discount_memo) {
        this.policy_discount_memo = policy_discount_memo;
    }
    public String getPolicy_discount_cancel_time() {
        return this.policy_discount_cancel_time;
    }
    public void setPolicy_discount_cancel_time(String policy_discount_cancel_time) {
        this.policy_discount_cancel_time = policy_discount_cancel_time;
    }
    public String getPolicy_discount_cancel_operator() {
        return this.policy_discount_cancel_operator;
    }
    public void setPolicy_discount_cancel_operator(
            String policy_discount_cancel_operator) {
        this.policy_discount_cancel_operator = policy_discount_cancel_operator;
    }
    public int getBranch_num() {
        return this.branch_num;
    }
    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }
    public float getPolicy_discount_bill_money() {
        return this.policy_discount_bill_money;
    }
    public void setPolicy_discount_bill_money(float policy_discount_bill_money) {
        this.policy_discount_bill_money = policy_discount_bill_money;
    }
    public float getPolicy_discount_discount() {
        return this.policy_discount_discount;
    }
    public void setPolicy_discount_discount(float policy_discount_discount) {
        this.policy_discount_discount = policy_discount_discount;
    }
    public boolean getPolicy_discount_card_only() {
        return this.policy_discount_card_only;
    }
    public void setPolicy_discount_card_only(boolean policy_discount_card_only) {
        this.policy_discount_card_only = policy_discount_card_only;
    }
    public String getPolicy_discount_card_type() {
        return this.policy_discount_card_type;
    }
    public void setPolicy_discount_card_type(String policy_discount_card_type) {
        this.policy_discount_card_type = policy_discount_card_type;
    }
    public String getPolicy_discount_repeat_type() {
        return this.policy_discount_repeat_type;
    }
    public void setPolicy_discount_repeat_type(String policy_discount_repeat_type) {
        this.policy_discount_repeat_type = policy_discount_repeat_type;
    }
    public String getPolicy_discount_repeat_end() {
        return this.policy_discount_repeat_end;
    }
    public void setPolicy_discount_repeat_end(String policy_discount_repeat_end) {
        this.policy_discount_repeat_end = policy_discount_repeat_end;
    }
    public float getPolicy_discount_discount_money() {
        return this.policy_discount_discount_money;
    }
    public void setPolicy_discount_discount_money(
            float policy_discount_discount_money) {
        this.policy_discount_discount_money = policy_discount_discount_money;
    }
    public float getPolicy_discount_total_discount() {
        return this.policy_discount_total_discount;
    }
    public void setPolicy_discount_total_discount(
            float policy_discount_total_discount) {
        this.policy_discount_total_discount = policy_discount_total_discount;
    }
    public String getPolicy_discount_assigned_item() {
        return this.policy_discount_assigned_item;
    }
    public void setPolicy_discount_assigned_item(
            String policy_discount_assigned_item) {
        this.policy_discount_assigned_item = policy_discount_assigned_item;
    }
    public String getPolicy_discount_assigned_type() {
        return this.policy_discount_assigned_type;
    }
    public void setPolicy_discount_assigned_type(
            String policy_discount_assigned_type) {
        this.policy_discount_assigned_type = policy_discount_assigned_type;
    }
    public String getPolicy_discount_assigned_category() {
        return this.policy_discount_assigned_category;
    }
    public void setPolicy_discount_assigned_category(
            String policy_discount_assigned_category) {
        this.policy_discount_assigned_category = policy_discount_assigned_category;
    }
    public String getPolicy_discount_department() {
        return this.policy_discount_department;
    }
    public void setPolicy_discount_department(String policy_discount_department) {
        this.policy_discount_department = policy_discount_department;
    }
    public String getPolicy_discount_last_edit_time() {
        return this.policy_discount_last_edit_time;
    }
    public void setPolicy_discount_last_edit_time(
            String policy_discount_last_edit_time) {
        this.policy_discount_last_edit_time = policy_discount_last_edit_time;
    }
    public String getPolicy_discount_last_editor() {
        return this.policy_discount_last_editor;
    }
    public void setPolicy_discount_last_editor(String policy_discount_last_editor) {
        this.policy_discount_last_editor = policy_discount_last_editor;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1558278077)
    public synchronized void resetPolicy_discount_details() {
        policy_discount_details = null;
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
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1242465847)
    public List<PolicyDiscountDetail> getPolicy_discount_details() {
        if (policy_discount_details == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PolicyDiscountDetailDao targetDao = daoSession.getPolicyDiscountDetailDao();
            List<PolicyDiscountDetail> policy_discount_detailsNew = targetDao
                    ._queryPolicyDiscount_Policy_discount_details(policy_discount_no);
            synchronized (this) {
                if (policy_discount_details == null) {
                    policy_discount_details = policy_discount_detailsNew;
                }
            }
        }
        return policy_discount_details;
    }

    public String getPolicy_discount_level_ids() {
        return this.policy_discount_level_ids;
    }

    public void setPolicy_discount_level_ids(String policy_discount_level_ids) {
        this.policy_discount_level_ids = policy_discount_level_ids;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1448785235)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPolicyDiscountDao() : null;
    }


    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */


}
