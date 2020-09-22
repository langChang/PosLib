package com.nhsoft.poslib.entity;

import com.nhsoft.poslib.service.greendao.DaoSession;
import com.nhsoft.poslib.service.greendao.PolicyQuantityDao;
import com.nhsoft.poslib.service.greendao.PolicyQuantityDetailDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * Created by Iverson on 2018/11/16 2:26 PM
 * 此类用于：
 */
@Entity
public class PolicyQuantity {
    /**
     * promotion_quantity_no : 4344990000013
     * branch_num : 99
     * promotion_quantity_date_from : 2019-05-13 00:00:00
     * promotion_quantity_date_to : 2019-05-20 00:00:00
     * promotion_quantity_time_from : 2019-05-13 00:00:00
     * promotion_quantity_time_to : 2019-05-13 23:59:59
     * promotion_quantity_applied_branch : <?xml version="1.0" encoding="GBK"?>
     <AppliedBranchArray><AppliedBranch><AppliedBranchNum>99</AppliedBranchNum><AppliedBranchName>管理中心</AppliedBranchName></AppliedBranch></AppliedBranchArray>
     * promotion_quantity_mon_actived : true
     * promotion_quantity_tues_actived : true
     * promotion_quantity_wed_actived : true
     * promotion_quantity_thurs_actived : true
     * promotion_quantity_friday_actived : true
     * promotion_quantity_sat_actived : true
     * promotion_quantity_sun_actived : true
     * state : {"stateCode":3,"stateName":"制单|审核"}
     * promotion_quantity_create_time : 2019-05-13 14:42:32
     * promotion_quantity_creator : 管理员
     * promotion_quantity_audit_time : 2019-05-13 15:11:07
     * promotion_quantity_auditor : 管理员
     * promotion_quantity_card_only : false
     * promotion_quantity_card_type : <?xml version="1.0" encoding="GBK"?>
     <消费卡类型列表/>
     * promotion_quantity_last_edit_time : 2019-05-13 14:42:32
     * promotion_quantity_last_editor : 管理员
     */
    
    @Id
    private String promotion_quantity_no;
    private int     branch_num;
    private String  promotion_quantity_date_from;
    private String  promotion_quantity_date_to;
    private String  promotion_quantity_time_from;
    private String  promotion_quantity_time_to;
    private String  promotion_quantity_applied_branch;
    private boolean promotion_quantity_mon_actived;
    private boolean promotion_quantity_tues_actived;
    private boolean promotion_quantity_wed_actived;
    private boolean promotion_quantity_thurs_actived;
    private boolean promotion_quantity_friday_actived;
    private boolean promotion_quantity_sat_actived;
    private boolean promotion_quantity_sun_actived;
    private String  promotion_quantity_create_time;
    private String  promotion_quantity_creator;
    private String  promotion_quantity_audit_time;
    private String  promotion_quantity_auditor;
    private boolean promotion_quantity_card_only;
    private String  promotion_quantity_card_type;
    private String  promotion_quantity_last_edit_time;
    private String  promotion_quantity_last_editor;
    private String  promotion_quantity_type;
    private Float promotion_quantity_min_amount; //商品起始量
    private Boolean promotion_quantity_append;
    private String promotion_quantity_level_ids;
    private Float promotion_quantity_discount;

    public Float getPromotion_quantity_min_amount() {
        return promotion_quantity_min_amount;
    }

    public void setPromotion_quantity_min_amount(float promotion_quantity_min_amount) {
        this.promotion_quantity_min_amount = promotion_quantity_min_amount;
    }



    @ToMany(referencedJoinProperty = "promotion_quantity_no")
    private List<PolicyQuantityDetail>  policy_promotion_quantity_details;
    @Transient
    private PolicyQuantityDetail policyQuantityDetail;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 2093691929)
    private transient PolicyQuantityDao myDao;

    @Generated(hash = 1676829405)
    public PolicyQuantity(String promotion_quantity_no, int branch_num, String promotion_quantity_date_from, String promotion_quantity_date_to,
            String promotion_quantity_time_from, String promotion_quantity_time_to, String promotion_quantity_applied_branch,
            boolean promotion_quantity_mon_actived, boolean promotion_quantity_tues_actived, boolean promotion_quantity_wed_actived,
            boolean promotion_quantity_thurs_actived, boolean promotion_quantity_friday_actived, boolean promotion_quantity_sat_actived,
            boolean promotion_quantity_sun_actived, String promotion_quantity_create_time, String promotion_quantity_creator, String promotion_quantity_audit_time,
            String promotion_quantity_auditor, boolean promotion_quantity_card_only, String promotion_quantity_card_type, String promotion_quantity_last_edit_time,
            String promotion_quantity_last_editor, String promotion_quantity_type, Float promotion_quantity_min_amount, Boolean promotion_quantity_append,
            String promotion_quantity_level_ids, Float promotion_quantity_discount) {
        this.promotion_quantity_no = promotion_quantity_no;
        this.branch_num = branch_num;
        this.promotion_quantity_date_from = promotion_quantity_date_from;
        this.promotion_quantity_date_to = promotion_quantity_date_to;
        this.promotion_quantity_time_from = promotion_quantity_time_from;
        this.promotion_quantity_time_to = promotion_quantity_time_to;
        this.promotion_quantity_applied_branch = promotion_quantity_applied_branch;
        this.promotion_quantity_mon_actived = promotion_quantity_mon_actived;
        this.promotion_quantity_tues_actived = promotion_quantity_tues_actived;
        this.promotion_quantity_wed_actived = promotion_quantity_wed_actived;
        this.promotion_quantity_thurs_actived = promotion_quantity_thurs_actived;
        this.promotion_quantity_friday_actived = promotion_quantity_friday_actived;
        this.promotion_quantity_sat_actived = promotion_quantity_sat_actived;
        this.promotion_quantity_sun_actived = promotion_quantity_sun_actived;
        this.promotion_quantity_create_time = promotion_quantity_create_time;
        this.promotion_quantity_creator = promotion_quantity_creator;
        this.promotion_quantity_audit_time = promotion_quantity_audit_time;
        this.promotion_quantity_auditor = promotion_quantity_auditor;
        this.promotion_quantity_card_only = promotion_quantity_card_only;
        this.promotion_quantity_card_type = promotion_quantity_card_type;
        this.promotion_quantity_last_edit_time = promotion_quantity_last_edit_time;
        this.promotion_quantity_last_editor = promotion_quantity_last_editor;
        this.promotion_quantity_type = promotion_quantity_type;
        this.promotion_quantity_min_amount = promotion_quantity_min_amount;
        this.promotion_quantity_append = promotion_quantity_append;
        this.promotion_quantity_level_ids = promotion_quantity_level_ids;
        this.promotion_quantity_discount = promotion_quantity_discount;
    }

    @Generated(hash = 1880178371)
    public PolicyQuantity() {
    }

    public PolicyQuantityDetail getPolicyQuantityDetail() {
        return policyQuantityDetail;
    }

    public void setPolicyQuantityDetail(PolicyQuantityDetail policyQuantityDetail) {
        this.policyQuantityDetail = policyQuantityDetail;
    }





    public void setPolicy_quantity_details(List<PolicyQuantityDetail> policyQuantityDetails){
        this.policy_promotion_quantity_details = policyQuantityDetails;
    }

    public String getPromotion_quantity_no() {
        return promotion_quantity_no;
    }

    public void setPromotion_quantity_no(String promotion_quantity_no) {
        this.promotion_quantity_no = promotion_quantity_no;
    }

    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public String getPromotion_quantity_date_from() {
        return promotion_quantity_date_from;
    }

    public void setPromotion_quantity_date_from(String promotion_quantity_date_from) {
        this.promotion_quantity_date_from = promotion_quantity_date_from;
    }

    public String getPromotion_quantity_date_to() {
        return promotion_quantity_date_to;
    }

    public void setPromotion_quantity_date_to(String promotion_quantity_date_to) {
        this.promotion_quantity_date_to = promotion_quantity_date_to;
    }

    public String getPromotion_quantity_time_from() {
        return promotion_quantity_time_from;
    }

    public void setPromotion_quantity_time_from(String promotion_quantity_time_from) {
        this.promotion_quantity_time_from = promotion_quantity_time_from;
    }

    public String getPromotion_quantity_time_to() {
        return promotion_quantity_time_to;
    }

    public void setPromotion_quantity_time_to(String promotion_quantity_time_to) {
        this.promotion_quantity_time_to = promotion_quantity_time_to;
    }

    public String getPromotion_quantity_applied_branch() {
        return promotion_quantity_applied_branch;
    }

    public void setPromotion_quantity_applied_branch(String promotion_quantity_applied_branch) {
        this.promotion_quantity_applied_branch = promotion_quantity_applied_branch;
    }

    public boolean isPromotion_quantity_mon_actived() {
        return promotion_quantity_mon_actived;
    }

    public void setPromotion_quantity_mon_actived(boolean promotion_quantity_mon_actived) {
        this.promotion_quantity_mon_actived = promotion_quantity_mon_actived;
    }

    public boolean isPromotion_quantity_tues_actived() {
        return promotion_quantity_tues_actived;
    }

    public void setPromotion_quantity_tues_actived(boolean promotion_quantity_tues_actived) {
        this.promotion_quantity_tues_actived = promotion_quantity_tues_actived;
    }

    public boolean isPromotion_quantity_wed_actived() {
        return promotion_quantity_wed_actived;
    }

    public void setPromotion_quantity_wed_actived(boolean promotion_quantity_wed_actived) {
        this.promotion_quantity_wed_actived = promotion_quantity_wed_actived;
    }

    public boolean isPromotion_quantity_thurs_actived() {
        return promotion_quantity_thurs_actived;
    }

    public void setPromotion_quantity_thurs_actived(boolean promotion_quantity_thurs_actived) {
        this.promotion_quantity_thurs_actived = promotion_quantity_thurs_actived;
    }

    public boolean isPromotion_quantity_friday_actived() {
        return promotion_quantity_friday_actived;
    }

    public void setPromotion_quantity_friday_actived(boolean promotion_quantity_friday_actived) {
        this.promotion_quantity_friday_actived = promotion_quantity_friday_actived;
    }

    public boolean isPromotion_quantity_sat_actived() {
        return promotion_quantity_sat_actived;
    }

    public void setPromotion_quantity_sat_actived(boolean promotion_quantity_sat_actived) {
        this.promotion_quantity_sat_actived = promotion_quantity_sat_actived;
    }

    public boolean isPromotion_quantity_sun_actived() {
        return promotion_quantity_sun_actived;
    }

    public void setPromotion_quantity_sun_actived(boolean promotion_quantity_sun_actived) {
        this.promotion_quantity_sun_actived = promotion_quantity_sun_actived;
    }

    public String getPromotion_quantity_create_time() {
        return promotion_quantity_create_time;
    }

    public void setPromotion_quantity_create_time(String promotion_quantity_create_time) {
        this.promotion_quantity_create_time = promotion_quantity_create_time;
    }

    public String getPromotion_quantity_creator() {
        return promotion_quantity_creator;
    }

    public void setPromotion_quantity_creator(String promotion_quantity_creator) {
        this.promotion_quantity_creator = promotion_quantity_creator;
    }

    public String getPromotion_quantity_audit_time() {
        return promotion_quantity_audit_time;
    }

    public void setPromotion_quantity_audit_time(String promotion_quantity_audit_time) {
        this.promotion_quantity_audit_time = promotion_quantity_audit_time;
    }

    public String getPromotion_quantity_auditor() {
        return promotion_quantity_auditor;
    }

    public void setPromotion_quantity_auditor(String promotion_quantity_auditor) {
        this.promotion_quantity_auditor = promotion_quantity_auditor;
    }

    public boolean isPromotion_quantity_card_only() {
        return promotion_quantity_card_only;
    }

    public void setPromotion_quantity_card_only(boolean promotion_quantity_card_only) {
        this.promotion_quantity_card_only = promotion_quantity_card_only;
    }

    public String getPromotion_quantity_card_type() {
        return promotion_quantity_card_type;
    }

    public void setPromotion_quantity_card_type(String promotion_quantity_card_type) {
        this.promotion_quantity_card_type = promotion_quantity_card_type;
    }

    public String getPromotion_quantity_last_edit_time() {
        return promotion_quantity_last_edit_time;
    }

    public void setPromotion_quantity_last_edit_time(String promotion_quantity_last_edit_time) {
        this.promotion_quantity_last_edit_time = promotion_quantity_last_edit_time;
    }

    public String getPromotion_quantity_last_editor() {
        return promotion_quantity_last_editor;
    }

    public void setPromotion_quantity_last_editor(String promotion_quantity_last_editor) {
        this.promotion_quantity_last_editor = promotion_quantity_last_editor;
    }

    public String getPromotion_quantity_type() {
        return promotion_quantity_type;
    }

    public void setPromotion_quantity_type(String promotion_quantity_type) {
        this.promotion_quantity_type = promotion_quantity_type;
    }

    public boolean getPromotion_quantity_mon_actived() {
        return this.promotion_quantity_mon_actived;
    }

    public boolean getPromotion_quantity_tues_actived() {
        return this.promotion_quantity_tues_actived;
    }

    public boolean getPromotion_quantity_wed_actived() {
        return this.promotion_quantity_wed_actived;
    }

    public boolean getPromotion_quantity_thurs_actived() {
        return this.promotion_quantity_thurs_actived;
    }

    public boolean getPromotion_quantity_friday_actived() {
        return this.promotion_quantity_friday_actived;
    }

    public boolean getPromotion_quantity_sat_actived() {
        return this.promotion_quantity_sat_actived;
    }

    public boolean getPromotion_quantity_sun_actived() {
        return this.promotion_quantity_sun_actived;
    }

    public boolean getPromotion_quantity_card_only() {
        return this.promotion_quantity_card_only;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2118770358)
    public List<PolicyQuantityDetail> getPolicy_promotion_quantity_details() {
        if (policy_promotion_quantity_details == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PolicyQuantityDetailDao targetDao = daoSession.getPolicyQuantityDetailDao();
            List<PolicyQuantityDetail> policy_promotion_quantity_detailsNew = targetDao
                    ._queryPolicyQuantity_Policy_promotion_quantity_details(promotion_quantity_no);
            synchronized (this) {
                if (policy_promotion_quantity_details == null) {
                    policy_promotion_quantity_details = policy_promotion_quantity_detailsNew;
                }
            }
        }
        return policy_promotion_quantity_details;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 144521181)
    public synchronized void resetPolicy_promotion_quantity_details() {
        policy_promotion_quantity_details = null;
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

    public void setPromotion_quantity_min_amount(Float promotion_quantity_min_amount) {
        this.promotion_quantity_min_amount = promotion_quantity_min_amount;
    }

    public Boolean getPromotion_quantity_append() {
        return this.promotion_quantity_append;
    }

    public void setPromotion_quantity_append(Boolean promotion_quantity_append) {
        this.promotion_quantity_append = promotion_quantity_append;
    }

    public String getPromotion_quantity_level_ids() {
        return this.promotion_quantity_level_ids;
    }

    public void setPromotion_quantity_level_ids(String promotion_quantity_level_ids) {
        this.promotion_quantity_level_ids = promotion_quantity_level_ids;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1899654716)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPolicyQuantityDao() : null;
    }

    public Float getPromotion_quantity_discount() {
        return this.promotion_quantity_discount;
    }

    public void setPromotion_quantity_discount(Float promotion_quantity_discount) {
        this.promotion_quantity_discount = promotion_quantity_discount;
    }


   


}
