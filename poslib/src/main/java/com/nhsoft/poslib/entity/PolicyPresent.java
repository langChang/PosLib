package com.nhsoft.poslib.entity;

import com.nhsoft.poslib.model.BasePolicyBean;
import com.nhsoft.poslib.service.greendao.PolicyPresentDao;
import com.nhsoft.poslib.service.greendao.PolicyPresentDetailDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;
import com.nhsoft.poslib.service.greendao.DaoSession;

/**
 * Created by Iverson on 2019-05-15 15:38
 * 此类用于：
 */
@Entity
public class PolicyPresent extends BasePolicyBean {

    /**
     * policy_present_no : 4344990000003
     * item_num : 434400009
     * system_book_code : 4344
     * policy_present_date_from : 2019-05-15 00:00:00
     * policy_present_date_to : 2019-05-22 00:00:00
     * policy_present_time_from : 2019-05-15 00:00:00
     * policy_present_time_to : 2019-05-15 23:59:59
     * policy_present_applied_branch : <?xml version="1.0" encoding="GBK"?>
     <AppliedBranchArray/>
     * policy_present_mon_actived : true
     * policy_present_tues_actived : true
     * policy_present_wed_actived : true
     * policy_present_thurs_actived : true
     * policy_present_friday_actived : true
     * policy_present_sat_actived : true
     * policy_present_sun_actived : true
     * policy_present_creator : 管理员
     * policy_present_create_time : 2019-05-15 15:37:42
     * policy_present_auditor : 管理员
     * policy_present_audit_time : 2019-05-15 15:37:44
     * policy_present_sale_amount : 5
     * branch_num : 99
     * policy_present_item_count : 4
     * policy_present_card_only : true
     * policy_present_card_type : <?xml version="1.0" encoding="GBK"?>
     <消费卡类型列表><消费卡类型 编号="99"><名称>电子卡</名称></消费卡类型><消费卡类型 编号="100"><名称>会员卡</名称></消费卡类型><消费卡类型 编号="101"><名称>折扣卡</名称></消费卡类型><消费卡类型 编号="102"><名称>超级vip</名称></消费卡类型><消费卡类型 编号="103"><名称>测试vip</名称></消费卡类型><消费卡类型 编号="104"><名称>至尊卡</名称></消费卡类型><消费卡类型 编号="105"><名称>周年纪念卡</名称></消费卡类型></消费卡类型列表>
     * policy_present_last_edit_time : 2019-05-15 15:37:42
     * policy_present_last_editor : 管理员
     */

    @Id
    private String policy_present_no;
    private int     item_num;
    private String  system_book_code;
    private String  policy_present_date_from;
    private String  policy_present_date_to;
    private String  policy_present_time_from;
    private String  policy_present_time_to;
    private String  policy_present_applied_branch;
    private boolean policy_present_mon_actived;
    private boolean policy_present_tues_actived;
    private boolean policy_present_wed_actived;
    private boolean policy_present_thurs_actived;
    private boolean policy_present_friday_actived;
    private boolean policy_present_sat_actived;
    private boolean policy_present_sun_actived;
    private String  policy_present_creator;
    private String  policy_present_create_time;
    private String  policy_present_auditor;
    private String  policy_present_audit_time;
    private float   policy_present_sale_amount;
    private int     branch_num;
    private float   policy_present_item_count;
    private boolean policy_present_card_only;
    private String  policy_present_card_type;
    private String  policy_present_last_edit_time;
    private String  policy_present_last_editor;
    private String policy_present_level_ids;


    @Transient
    private int multiple;

    public int getMultiple() {
        return multiple;
    }

    public void setMultiple(int multiple) {
        this.multiple = multiple;
    }

    @ToMany(referencedJoinProperty = "policy_present_no")
    private List<PolicyPresentDetail> policy_present_details;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1552722138)
    private transient PolicyPresentDao myDao;

    @Generated(hash = 2097011813)
    public PolicyPresent(String policy_present_no, int item_num, String system_book_code, String policy_present_date_from, String policy_present_date_to, String policy_present_time_from, String policy_present_time_to, String policy_present_applied_branch,
            boolean policy_present_mon_actived, boolean policy_present_tues_actived, boolean policy_present_wed_actived, boolean policy_present_thurs_actived, boolean policy_present_friday_actived, boolean policy_present_sat_actived, boolean policy_present_sun_actived,
            String policy_present_creator, String policy_present_create_time, String policy_present_auditor, String policy_present_audit_time, float policy_present_sale_amount, int branch_num, float policy_present_item_count, boolean policy_present_card_only,
            String policy_present_card_type, String policy_present_last_edit_time, String policy_present_last_editor, String policy_present_level_ids) {
        this.policy_present_no = policy_present_no;
        this.item_num = item_num;
        this.system_book_code = system_book_code;
        this.policy_present_date_from = policy_present_date_from;
        this.policy_present_date_to = policy_present_date_to;
        this.policy_present_time_from = policy_present_time_from;
        this.policy_present_time_to = policy_present_time_to;
        this.policy_present_applied_branch = policy_present_applied_branch;
        this.policy_present_mon_actived = policy_present_mon_actived;
        this.policy_present_tues_actived = policy_present_tues_actived;
        this.policy_present_wed_actived = policy_present_wed_actived;
        this.policy_present_thurs_actived = policy_present_thurs_actived;
        this.policy_present_friday_actived = policy_present_friday_actived;
        this.policy_present_sat_actived = policy_present_sat_actived;
        this.policy_present_sun_actived = policy_present_sun_actived;
        this.policy_present_creator = policy_present_creator;
        this.policy_present_create_time = policy_present_create_time;
        this.policy_present_auditor = policy_present_auditor;
        this.policy_present_audit_time = policy_present_audit_time;
        this.policy_present_sale_amount = policy_present_sale_amount;
        this.branch_num = branch_num;
        this.policy_present_item_count = policy_present_item_count;
        this.policy_present_card_only = policy_present_card_only;
        this.policy_present_card_type = policy_present_card_type;
        this.policy_present_last_edit_time = policy_present_last_edit_time;
        this.policy_present_last_editor = policy_present_last_editor;
        this.policy_present_level_ids = policy_present_level_ids;
    }

    @Generated(hash = 1641139763)
    public PolicyPresent() {
    }

    public void setPolicy_present_details(List<PolicyPresentDetail> policy_present_details) {
        this.policy_present_details = policy_present_details;
    }

    public String getPolicy_present_no() {
        return policy_present_no;
    }

    public void setPolicy_present_no(String policy_present_no) {
        this.policy_present_no = policy_present_no;
    }

    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public String getPolicy_present_date_from() {
        return policy_present_date_from;
    }

    public void setPolicy_present_date_from(String policy_present_date_from) {
        this.policy_present_date_from = policy_present_date_from;
    }

    public String getPolicy_present_date_to() {
        return policy_present_date_to;
    }

    public void setPolicy_present_date_to(String policy_present_date_to) {
        this.policy_present_date_to = policy_present_date_to;
    }

    public String getPolicy_present_time_from() {
        return policy_present_time_from;
    }

    public void setPolicy_present_time_from(String policy_present_time_from) {
        this.policy_present_time_from = policy_present_time_from;
    }

    public String getPolicy_present_time_to() {
        return policy_present_time_to;
    }

    public void setPolicy_present_time_to(String policy_present_time_to) {
        this.policy_present_time_to = policy_present_time_to;
    }

    public String getPolicy_present_applied_branch() {
        return policy_present_applied_branch;
    }

    public void setPolicy_present_applied_branch(String policy_present_applied_branch) {
        this.policy_present_applied_branch = policy_present_applied_branch;
    }

    public boolean isPolicy_present_mon_actived() {
        return policy_present_mon_actived;
    }

    public void setPolicy_present_mon_actived(boolean policy_present_mon_actived) {
        this.policy_present_mon_actived = policy_present_mon_actived;
    }

    public boolean isPolicy_present_tues_actived() {
        return policy_present_tues_actived;
    }

    public void setPolicy_present_tues_actived(boolean policy_present_tues_actived) {
        this.policy_present_tues_actived = policy_present_tues_actived;
    }

    public boolean isPolicy_present_wed_actived() {
        return policy_present_wed_actived;
    }

    public void setPolicy_present_wed_actived(boolean policy_present_wed_actived) {
        this.policy_present_wed_actived = policy_present_wed_actived;
    }

    public boolean isPolicy_present_thurs_actived() {
        return policy_present_thurs_actived;
    }

    public void setPolicy_present_thurs_actived(boolean policy_present_thurs_actived) {
        this.policy_present_thurs_actived = policy_present_thurs_actived;
    }

    public boolean isPolicy_present_friday_actived() {
        return policy_present_friday_actived;
    }

    public void setPolicy_present_friday_actived(boolean policy_present_friday_actived) {
        this.policy_present_friday_actived = policy_present_friday_actived;
    }

    public boolean isPolicy_present_sat_actived() {
        return policy_present_sat_actived;
    }

    public void setPolicy_present_sat_actived(boolean policy_present_sat_actived) {
        this.policy_present_sat_actived = policy_present_sat_actived;
    }

    public boolean isPolicy_present_sun_actived() {
        return policy_present_sun_actived;
    }

    public void setPolicy_present_sun_actived(boolean policy_present_sun_actived) {
        this.policy_present_sun_actived = policy_present_sun_actived;
    }

    public String getPolicy_present_creator() {
        return policy_present_creator;
    }

    public void setPolicy_present_creator(String policy_present_creator) {
        this.policy_present_creator = policy_present_creator;
    }

    public String getPolicy_present_create_time() {
        return policy_present_create_time;
    }

    public void setPolicy_present_create_time(String policy_present_create_time) {
        this.policy_present_create_time = policy_present_create_time;
    }

    public String getPolicy_present_auditor() {
        return policy_present_auditor;
    }

    public void setPolicy_present_auditor(String policy_present_auditor) {
        this.policy_present_auditor = policy_present_auditor;
    }

    public String getPolicy_present_audit_time() {
        return policy_present_audit_time;
    }

    public void setPolicy_present_audit_time(String policy_present_audit_time) {
        this.policy_present_audit_time = policy_present_audit_time;
    }

    public float getPolicy_present_sale_amount() {
        return policy_present_sale_amount;
    }

    public void setPolicy_present_sale_amount(float policy_present_sale_amount) {
        this.policy_present_sale_amount = policy_present_sale_amount;
    }

    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public float getPolicy_present_item_count() {
        return policy_present_item_count;
    }

    public void setPolicy_present_item_count(float policy_present_item_count) {
        this.policy_present_item_count = policy_present_item_count;
    }

    public boolean isPolicy_present_card_only() {
        return policy_present_card_only;
    }

    public void setPolicy_present_card_only(boolean policy_present_card_only) {
        this.policy_present_card_only = policy_present_card_only;
    }

    public String getPolicy_present_card_type() {
        return policy_present_card_type;
    }

    public void setPolicy_present_card_type(String policy_present_card_type) {
        this.policy_present_card_type = policy_present_card_type;
    }

    public String getPolicy_present_last_edit_time() {
        return policy_present_last_edit_time;
    }

    public void setPolicy_present_last_edit_time(String policy_present_last_edit_time) {
        this.policy_present_last_edit_time = policy_present_last_edit_time;
    }

    public String getPolicy_present_last_editor() {
        return policy_present_last_editor;
    }

    public void setPolicy_present_last_editor(String policy_present_last_editor) {
        this.policy_present_last_editor = policy_present_last_editor;
    }

    public boolean getPolicy_present_mon_actived() {
        return this.policy_present_mon_actived;
    }

    public boolean getPolicy_present_tues_actived() {
        return this.policy_present_tues_actived;
    }

    public boolean getPolicy_present_wed_actived() {
        return this.policy_present_wed_actived;
    }

    public boolean getPolicy_present_thurs_actived() {
        return this.policy_present_thurs_actived;
    }

    public boolean getPolicy_present_friday_actived() {
        return this.policy_present_friday_actived;
    }

    public boolean getPolicy_present_sat_actived() {
        return this.policy_present_sat_actived;
    }

    public boolean getPolicy_present_sun_actived() {
        return this.policy_present_sun_actived;
    }

    public boolean getPolicy_present_card_only() {
        return this.policy_present_card_only;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 272404369)
    public List<PolicyPresentDetail> getPolicy_present_details() {
        if (policy_present_details == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PolicyPresentDetailDao targetDao = daoSession.getPolicyPresentDetailDao();
            List<PolicyPresentDetail> policy_present_detailsNew = targetDao._queryPolicyPresent_Policy_present_details(policy_present_no);
            synchronized (this) {
                if (policy_present_details == null) {
                    policy_present_details = policy_present_detailsNew;
                }
            }
        }
        return policy_present_details;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1695899464)
    public synchronized void resetPolicy_present_details() {
        policy_present_details = null;
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

    public String getPolicy_present_level_ids() {
        return this.policy_present_level_ids;
    }

    public void setPolicy_present_level_ids(String policy_present_level_ids) {
        this.policy_present_level_ids = policy_present_level_ids;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1585796371)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPolicyPresentDao() : null;
    }
}
