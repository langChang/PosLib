package com.nhsoft.poslib.entity;

import com.nhsoft.poslib.service.greendao.PolicyPromotionDao;
import com.nhsoft.poslib.service.greendao.PolicyPromotionDetailDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;
import com.nhsoft.poslib.service.greendao.DaoSession;

/**
 * Created by Iverson on 2018/11/16 2:26 PM
 * 此类用于：
 */
@Entity
public class PolicyPromotion {
    /**
     * policy_promotion_no : String||促销特价单据号
     * policy_promotion_date_from : String||促销特价开始日期
     * policy_promotion_date_to : String||促销特价结束日期
     * policy_promotion_time_from : String||促销特价开始时间
     * policy_promotion_time_to : String||促销特价结束时间
     * policy_promotion_applied_branch : String||促销特价应用门店
     * policy_promotion_mon_actived : false
     * policy_promotion_tues_actived : false
     * policy_promotion_wed_actived : false
     * policy_promotion_thurs_actived : false
     * policy_promotion_friday_actived : false
     * policy_promotion_sat_actived : false
     * policy_promotion_sun_actived : false
     * policy_promotion_state_code : 1
     * policy_promotion_state_name : String||状态名称
     * policy_promotion_create_time : String||促销特价创建时间
     * policy_promotion_creator : String||促销特价创建人
     * policy_promotion_audit_time : String||促销特价审核时间
     * policy_promotion_auditor : String||促销特价审核人
     * policy_promotion_memo : String||促销特价备注
     * policy_promotion_cancel_time : String||促销特价注销时间
     * policy_promotion_cancel_operator : String||促销特价作废人
     * branch_num : 99
     * policy_promotion_card_only : true
     * policy_promotion_type : 1
     * policy_promotion_all : true
     * policy_promotion_discount : 0.1
     * policy_promotion_card_type : String
     * policy_promotion_repeat_type : String||促销特价重复类型
     * policy_promotion_repeat_end : String||促销特价结束时间
     * policy_promotion_except_item : String
     * policy_promotion_category : String||促销特价特价类型
     * policy_promotion_item_category : String||促销特价商品类别
     * policy_promotion_disable_pay_discount : true
     * policy_promotion_card_once : true
     * policy_promotion_last_edit_time : String||促销特价最后修改时间
     * policy_promotion_last_editor : String||促销特价最后修改人
     */
    @Id
    @Property(nameInDb = "POLICY_PROMOTION_NO")
    private String policy_promotion_no;
    private String  policy_promotion_date_from;
    private String  policy_promotion_date_to;
    private String  policy_promotion_time_from;
    private String  policy_promotion_time_to;
    private String  policy_promotion_applied_branch;
    private boolean policy_promotion_mon_actived;
    private boolean policy_promotion_tues_actived;
    private boolean policy_promotion_wed_actived;
    private boolean policy_promotion_thurs_actived;
    private boolean policy_promotion_friday_actived;
    private boolean policy_promotion_sat_actived;
    private boolean policy_promotion_sun_actived;
    private int     policy_promotion_state_code;
    private String  policy_promotion_state_name;
    private String  policy_promotion_create_time;
    private String  policy_promotion_creator;
    private String  policy_promotion_audit_time;
    private String  policy_promotion_auditor;
    private String  policy_promotion_memo;
    private String  policy_promotion_cancel_time;
    private String  policy_promotion_cancel_operator;
    private int     branch_num;
    private boolean policy_promotion_card_only;
    private int     policy_promotion_type;
    private boolean policy_promotion_all;
    private double  policy_promotion_discount;
    private String  policy_promotion_card_type;
    private String  policy_promotion_repeat_type;
    private String  policy_promotion_repeat_end;
    private String  policy_promotion_except_item;
    private String  policy_promotion_category;
    private String  policy_promotion_item_category;
    private boolean policy_promotion_disable_pay_discount;
    private boolean policy_promotion_card_once;
    private String  policy_promotion_last_edit_time;
    private String  policy_promotion_last_editor;
    private Integer policy_promotion_total_limit; //促销总份额数
    private String policy_promotion_level_ids;


    public Integer getPolicy_promotion_total_limit() {
        return policy_promotion_total_limit;
    }

    public void setPolicy_promotion_total_limit(Integer policy_promotion_total_limit) {
        this.policy_promotion_total_limit = policy_promotion_total_limit;
    }



    @ToMany(referencedJoinProperty = "policy_promotion_no")
    private List<PolicyPromotionDetail> policy_promotion_details;
    @Transient
    private PolicyPromotionDetail policyPromotionDetail;


    public PolicyPromotionDetail getPolicyPromotionDetail() {
        return policyPromotionDetail;
    }

    public void setPolicyPromotionDetail(PolicyPromotionDetail policyPromotionDetail) {
        this.policyPromotionDetail = policyPromotionDetail;
    }


    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1017260140)
    private transient PolicyPromotionDao myDao;
    @Generated(hash = 1291637421)
    public PolicyPromotion(String policy_promotion_no, String policy_promotion_date_from,
            String policy_promotion_date_to, String policy_promotion_time_from, String policy_promotion_time_to,
            String policy_promotion_applied_branch, boolean policy_promotion_mon_actived,
            boolean policy_promotion_tues_actived, boolean policy_promotion_wed_actived,
            boolean policy_promotion_thurs_actived, boolean policy_promotion_friday_actived,
            boolean policy_promotion_sat_actived, boolean policy_promotion_sun_actived,
            int policy_promotion_state_code, String policy_promotion_state_name,
            String policy_promotion_create_time, String policy_promotion_creator,
            String policy_promotion_audit_time, String policy_promotion_auditor, String policy_promotion_memo,
            String policy_promotion_cancel_time, String policy_promotion_cancel_operator, int branch_num,
            boolean policy_promotion_card_only, int policy_promotion_type, boolean policy_promotion_all,
            double policy_promotion_discount, String policy_promotion_card_type,
            String policy_promotion_repeat_type, String policy_promotion_repeat_end,
            String policy_promotion_except_item, String policy_promotion_category,
            String policy_promotion_item_category, boolean policy_promotion_disable_pay_discount,
            boolean policy_promotion_card_once, String policy_promotion_last_edit_time,
            String policy_promotion_last_editor, Integer policy_promotion_total_limit,
            String policy_promotion_level_ids) {
        this.policy_promotion_no = policy_promotion_no;
        this.policy_promotion_date_from = policy_promotion_date_from;
        this.policy_promotion_date_to = policy_promotion_date_to;
        this.policy_promotion_time_from = policy_promotion_time_from;
        this.policy_promotion_time_to = policy_promotion_time_to;
        this.policy_promotion_applied_branch = policy_promotion_applied_branch;
        this.policy_promotion_mon_actived = policy_promotion_mon_actived;
        this.policy_promotion_tues_actived = policy_promotion_tues_actived;
        this.policy_promotion_wed_actived = policy_promotion_wed_actived;
        this.policy_promotion_thurs_actived = policy_promotion_thurs_actived;
        this.policy_promotion_friday_actived = policy_promotion_friday_actived;
        this.policy_promotion_sat_actived = policy_promotion_sat_actived;
        this.policy_promotion_sun_actived = policy_promotion_sun_actived;
        this.policy_promotion_state_code = policy_promotion_state_code;
        this.policy_promotion_state_name = policy_promotion_state_name;
        this.policy_promotion_create_time = policy_promotion_create_time;
        this.policy_promotion_creator = policy_promotion_creator;
        this.policy_promotion_audit_time = policy_promotion_audit_time;
        this.policy_promotion_auditor = policy_promotion_auditor;
        this.policy_promotion_memo = policy_promotion_memo;
        this.policy_promotion_cancel_time = policy_promotion_cancel_time;
        this.policy_promotion_cancel_operator = policy_promotion_cancel_operator;
        this.branch_num = branch_num;
        this.policy_promotion_card_only = policy_promotion_card_only;
        this.policy_promotion_type = policy_promotion_type;
        this.policy_promotion_all = policy_promotion_all;
        this.policy_promotion_discount = policy_promotion_discount;
        this.policy_promotion_card_type = policy_promotion_card_type;
        this.policy_promotion_repeat_type = policy_promotion_repeat_type;
        this.policy_promotion_repeat_end = policy_promotion_repeat_end;
        this.policy_promotion_except_item = policy_promotion_except_item;
        this.policy_promotion_category = policy_promotion_category;
        this.policy_promotion_item_category = policy_promotion_item_category;
        this.policy_promotion_disable_pay_discount = policy_promotion_disable_pay_discount;
        this.policy_promotion_card_once = policy_promotion_card_once;
        this.policy_promotion_last_edit_time = policy_promotion_last_edit_time;
        this.policy_promotion_last_editor = policy_promotion_last_editor;
        this.policy_promotion_total_limit = policy_promotion_total_limit;
        this.policy_promotion_level_ids = policy_promotion_level_ids;
    }

    @Generated(hash = 880149592)
    public PolicyPromotion() {
    }
    public void setPolicy_promotion_details(List<PolicyPromotionDetail> policy_promotion_details){
        this.policy_promotion_details = policy_promotion_details;
    }

    public String getPolicy_promotion_no() {
        return policy_promotion_no;
    }

    public void setPolicy_promotion_no(String policy_promotion_no) {
        this.policy_promotion_no = policy_promotion_no;
    }

    public String getPolicy_promotion_date_from() {
        return policy_promotion_date_from;
    }

    public void setPolicy_promotion_date_from(String policy_promotion_date_from) {
        this.policy_promotion_date_from = policy_promotion_date_from;
    }

    public String getPolicy_promotion_date_to() {
        return policy_promotion_date_to;
    }

    public void setPolicy_promotion_date_to(String policy_promotion_date_to) {
        this.policy_promotion_date_to = policy_promotion_date_to;
    }

    public String getPolicy_promotion_time_from() {
        return policy_promotion_time_from;
    }

    public void setPolicy_promotion_time_from(String policy_promotion_time_from) {
        this.policy_promotion_time_from = policy_promotion_time_from;
    }

    public String getPolicy_promotion_time_to() {
        return policy_promotion_time_to;
    }

    public void setPolicy_promotion_time_to(String policy_promotion_time_to) {
        this.policy_promotion_time_to = policy_promotion_time_to;
    }

    public String getPolicy_promotion_applied_branch() {
        return policy_promotion_applied_branch;
    }

    public void setPolicy_promotion_applied_branch(String policy_promotion_applied_branch) {
        this.policy_promotion_applied_branch = policy_promotion_applied_branch;
    }

    public boolean isPolicy_promotion_mon_actived() {
        return policy_promotion_mon_actived;
    }

    public void setPolicy_promotion_mon_actived(boolean policy_promotion_mon_actived) {
        this.policy_promotion_mon_actived = policy_promotion_mon_actived;
    }

    public boolean isPolicy_promotion_tues_actived() {
        return policy_promotion_tues_actived;
    }

    public void setPolicy_promotion_tues_actived(boolean policy_promotion_tues_actived) {
        this.policy_promotion_tues_actived = policy_promotion_tues_actived;
    }

    public boolean isPolicy_promotion_wed_actived() {
        return policy_promotion_wed_actived;
    }

    public void setPolicy_promotion_wed_actived(boolean policy_promotion_wed_actived) {
        this.policy_promotion_wed_actived = policy_promotion_wed_actived;
    }

    public boolean isPolicy_promotion_thurs_actived() {
        return policy_promotion_thurs_actived;
    }

    public void setPolicy_promotion_thurs_actived(boolean policy_promotion_thurs_actived) {
        this.policy_promotion_thurs_actived = policy_promotion_thurs_actived;
    }

    public boolean isPolicy_promotion_friday_actived() {
        return policy_promotion_friday_actived;
    }

    public void setPolicy_promotion_friday_actived(boolean policy_promotion_friday_actived) {
        this.policy_promotion_friday_actived = policy_promotion_friday_actived;
    }

    public boolean isPolicy_promotion_sat_actived() {
        return policy_promotion_sat_actived;
    }

    public void setPolicy_promotion_sat_actived(boolean policy_promotion_sat_actived) {
        this.policy_promotion_sat_actived = policy_promotion_sat_actived;
    }

    public boolean isPolicy_promotion_sun_actived() {
        return policy_promotion_sun_actived;
    }

    public void setPolicy_promotion_sun_actived(boolean policy_promotion_sun_actived) {
        this.policy_promotion_sun_actived = policy_promotion_sun_actived;
    }

    public int getPolicy_promotion_state_code() {
        return policy_promotion_state_code;
    }

    public void setPolicy_promotion_state_code(int policy_promotion_state_code) {
        this.policy_promotion_state_code = policy_promotion_state_code;
    }

    public String getPolicy_promotion_state_name() {
        return policy_promotion_state_name;
    }

    public void setPolicy_promotion_state_name(String policy_promotion_state_name) {
        this.policy_promotion_state_name = policy_promotion_state_name;
    }

    public String getPolicy_promotion_create_time() {
        return policy_promotion_create_time;
    }

    public void setPolicy_promotion_create_time(String policy_promotion_create_time) {
        this.policy_promotion_create_time = policy_promotion_create_time;
    }

    public String getPolicy_promotion_creator() {
        return policy_promotion_creator;
    }

    public void setPolicy_promotion_creator(String policy_promotion_creator) {
        this.policy_promotion_creator = policy_promotion_creator;
    }

    public String getPolicy_promotion_audit_time() {
        return policy_promotion_audit_time;
    }

    public void setPolicy_promotion_audit_time(String policy_promotion_audit_time) {
        this.policy_promotion_audit_time = policy_promotion_audit_time;
    }

    public String getPolicy_promotion_auditor() {
        return policy_promotion_auditor;
    }

    public void setPolicy_promotion_auditor(String policy_promotion_auditor) {
        this.policy_promotion_auditor = policy_promotion_auditor;
    }

    public String getPolicy_promotion_memo() {
        return policy_promotion_memo;
    }

    public void setPolicy_promotion_memo(String policy_promotion_memo) {
        this.policy_promotion_memo = policy_promotion_memo;
    }

    public String getPolicy_promotion_cancel_time() {
        return policy_promotion_cancel_time;
    }

    public void setPolicy_promotion_cancel_time(String policy_promotion_cancel_time) {
        this.policy_promotion_cancel_time = policy_promotion_cancel_time;
    }

    public String getPolicy_promotion_cancel_operator() {
        return policy_promotion_cancel_operator;
    }

    public void setPolicy_promotion_cancel_operator(String policy_promotion_cancel_operator) {
        this.policy_promotion_cancel_operator = policy_promotion_cancel_operator;
    }

    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public boolean isPolicy_promotion_card_only() {
        return policy_promotion_card_only;
    }

    public void setPolicy_promotion_card_only(boolean policy_promotion_card_only) {
        this.policy_promotion_card_only = policy_promotion_card_only;
    }

    public int getPolicy_promotion_type() {
        return policy_promotion_type;
    }

    public void setPolicy_promotion_type(int policy_promotion_type) {
        this.policy_promotion_type = policy_promotion_type;
    }

    public boolean isPolicy_promotion_all() {
        return policy_promotion_all;
    }

    public void setPolicy_promotion_all(boolean policy_promotion_all) {
        this.policy_promotion_all = policy_promotion_all;
    }

    public double getPolicy_promotion_discount() {
        return policy_promotion_discount;
    }

    public void setPolicy_promotion_discount(double policy_promotion_discount) {
        this.policy_promotion_discount = policy_promotion_discount;
    }

    public String getPolicy_promotion_card_type() {
        return policy_promotion_card_type;
    }

    public void setPolicy_promotion_card_type(String policy_promotion_card_type) {
        this.policy_promotion_card_type = policy_promotion_card_type;
    }

    public String getPolicy_promotion_repeat_type() {
        return policy_promotion_repeat_type;
    }

    public void setPolicy_promotion_repeat_type(String policy_promotion_repeat_type) {
        this.policy_promotion_repeat_type = policy_promotion_repeat_type;
    }

    public String getPolicy_promotion_repeat_end() {
        return policy_promotion_repeat_end;
    }

    public void setPolicy_promotion_repeat_end(String policy_promotion_repeat_end) {
        this.policy_promotion_repeat_end = policy_promotion_repeat_end;
    }

    public String getPolicy_promotion_except_item() {
        return policy_promotion_except_item;
    }

    public void setPolicy_promotion_except_item(String policy_promotion_except_item) {
        this.policy_promotion_except_item = policy_promotion_except_item;
    }

    public String getPolicy_promotion_category() {
        return policy_promotion_category;
    }

    public void setPolicy_promotion_category(String policy_promotion_category) {
        this.policy_promotion_category = policy_promotion_category;
    }

    public String getPolicy_promotion_item_category() {
        return policy_promotion_item_category;
    }

    public void setPolicy_promotion_item_category(String policy_promotion_item_category) {
        this.policy_promotion_item_category = policy_promotion_item_category;
    }

    public boolean isPolicy_promotion_disable_pay_discount() {
        return policy_promotion_disable_pay_discount;
    }

    public void setPolicy_promotion_disable_pay_discount(boolean policy_promotion_disable_pay_discount) {
        this.policy_promotion_disable_pay_discount = policy_promotion_disable_pay_discount;
    }

    public boolean isPolicy_promotion_card_once() {
        return policy_promotion_card_once;
    }

    public void setPolicy_promotion_card_once(boolean policy_promotion_card_once) {
        this.policy_promotion_card_once = policy_promotion_card_once;
    }

    public String getPolicy_promotion_last_edit_time() {
        return policy_promotion_last_edit_time;
    }

    public void setPolicy_promotion_last_edit_time(String policy_promotion_last_edit_time) {
        this.policy_promotion_last_edit_time = policy_promotion_last_edit_time;
    }

    public String getPolicy_promotion_last_editor() {
        return policy_promotion_last_editor;
    }

    public void setPolicy_promotion_last_editor(String policy_promotion_last_editor) {
        this.policy_promotion_last_editor = policy_promotion_last_editor;
    }


    public boolean getPolicy_promotion_mon_actived() {
        return this.policy_promotion_mon_actived;
    }


    public boolean getPolicy_promotion_tues_actived() {
        return this.policy_promotion_tues_actived;
    }


    public boolean getPolicy_promotion_wed_actived() {
        return this.policy_promotion_wed_actived;
    }


    public boolean getPolicy_promotion_thurs_actived() {
        return this.policy_promotion_thurs_actived;
    }


    public boolean getPolicy_promotion_friday_actived() {
        return this.policy_promotion_friday_actived;
    }


    public boolean getPolicy_promotion_sat_actived() {
        return this.policy_promotion_sat_actived;
    }


    public boolean getPolicy_promotion_sun_actived() {
        return this.policy_promotion_sun_actived;
    }


    public boolean getPolicy_promotion_card_only() {
        return this.policy_promotion_card_only;
    }


    public boolean getPolicy_promotion_disable_pay_discount() {
        return this.policy_promotion_disable_pay_discount;
    }


    public boolean getPolicy_promotion_card_once() {
        return this.policy_promotion_card_once;
    }


    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 248067514)
    public List<PolicyPromotionDetail> getPolicy_promotion_details() {
        if (policy_promotion_details == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PolicyPromotionDetailDao targetDao = daoSession.getPolicyPromotionDetailDao();
            List<PolicyPromotionDetail> policy_promotion_detailsNew = targetDao
                    ._queryPolicyPromotion_Policy_promotion_details(policy_promotion_no);
            synchronized (this) {
                if (policy_promotion_details == null) {
                    policy_promotion_details = policy_promotion_detailsNew;
                }
            }
        }
        return policy_promotion_details;
    }


    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1865475432)
    public synchronized void resetPolicy_promotion_details() {
        policy_promotion_details = null;
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


    public boolean getPolicy_promotion_all() {
        return this.policy_promotion_all;
    }

    public String getPolicy_promotion_level_ids() {
        return this.policy_promotion_level_ids;
    }

    public void setPolicy_promotion_level_ids(String policy_promotion_level_ids) {
        this.policy_promotion_level_ids = policy_promotion_level_ids;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 99308927)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPolicyPromotionDao() : null;
    }

}
