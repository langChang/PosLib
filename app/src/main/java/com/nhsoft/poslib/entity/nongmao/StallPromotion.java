package com.nhsoft.poslib.entity.nongmao;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Transient;

import com.nhsoft.poslib.service.greendao.StallPromotionDetailDao;
import com.nhsoft.poslib.service.greendao.StallPromotionDao;
import com.nhsoft.poslib.service.greendao.DaoSession;

/**
 * 农贸
 * nhsoft.amazon.fm.stall.promotion
 * 档口促销
 */
@Entity
public class StallPromotion {

    /**
     * branch_num : 0
     * details : [{"item_grade_num":0,"item_num":0,"policy_promotion_detail_bill_limit":0,"policy_promotion_detail_memo":"string","policy_promotion_detail_num":0,"policy_promotion_detail_rate":0,"policy_promotion_detail_special_price":0,"policy_promotion_detail_std_price":0,"policy_promotion_no":"string"}]
     * merchant_num : 0
     * policy_promotion_audit_time : string
     * policy_promotion_auditor : string
     * policy_promotion_cancel_operator : string
     * policy_promotion_cancel_time : string
     * policy_promotion_card_once : true
     * policy_promotion_card_only : true
     * policy_promotion_card_type : string
     * policy_promotion_category : string
     * policy_promotion_create_time : string
     * policy_promotion_creator : string
     * policy_promotion_date_from : string
     * policy_promotion_date_to : string
     * policy_promotion_discount : 0
     * policy_promotion_except_item : string
     * policy_promotion_memo : string
     * policy_promotion_no : string
     * policy_promotion_rate : 0
     * policy_promotion_time_from : string
     * policy_promotion_time_to : string
     * stall_num : 0
     * stall_promotion_all : true
     * state_code : 0
     * state_name : string
     * system_book_code : string
     */
//    @Id(autoincrement = true)
    @Id
    private           String                     policy_promotion_no;
    private           int                        branch_num;
    private           int                        merchant_num;
    private           String                     policy_promotion_audit_time;
    private           String                     policy_promotion_auditor;
    private           String                     policy_promotion_cancel_operator;
    private           String                     policy_promotion_cancel_time;
    private           boolean                    policy_promotion_card_once;
    private           boolean                    policy_promotion_card_only;
    private           String                     policy_promotion_card_type;
    private           String                     policy_promotion_category;
    private           String                     policy_promotion_create_time;
    private           String                     policy_promotion_creator;
    private           String                     policy_promotion_date_from;
    private           String                     policy_promotion_date_to;
    private           float                      policy_promotion_discount;
    private           String                     policy_promotion_except_item;
    private           String                     policy_promotion_memo;
    private           float                      policy_promotion_rate;
    private           String                     policy_promotion_time_from;
    private           String                     policy_promotion_time_to;
    private           int                        stall_num;
    private           boolean                    stall_promotion_all;
    private           int                        state_code;
    private           String                     state_name;
    private           String                     system_book_code;
    @ToMany(referencedJoinProperty = "policy_promotion_no")
    private           List<StallPromotionDetail> details;

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

    public void setStallList(String stallList) {
        this.stallList = stallList;
    }



    public List<StallPromotionDetail> getStallPromotionDetails(){
        return details;
    }

    public void setStallPromotionDetails(List<StallPromotionDetail> details1){
        this.details = details1;
    }
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession                 daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1538102404)
    private transient StallPromotionDao          myDao;

    @Transient
    private StallPromotionDetail stallPromotionDetail;

    public StallPromotionDetail getStallPromotionDetail() {
        return stallPromotionDetail;
    }

    public void setStallPromotionDetail(StallPromotionDetail stallPromotionDetail) {
        this.stallPromotionDetail = stallPromotionDetail;
    }



    @Generated(hash = 2089505295)
    public StallPromotion(String policy_promotion_no, int branch_num, int merchant_num, String policy_promotion_audit_time, String policy_promotion_auditor, String policy_promotion_cancel_operator, String policy_promotion_cancel_time, boolean policy_promotion_card_once, boolean policy_promotion_card_only,
            String policy_promotion_card_type, String policy_promotion_category, String policy_promotion_create_time, String policy_promotion_creator, String policy_promotion_date_from, String policy_promotion_date_to, float policy_promotion_discount, String policy_promotion_except_item,
            String policy_promotion_memo, float policy_promotion_rate, String policy_promotion_time_from, String policy_promotion_time_to, int stall_num, boolean stall_promotion_all, int state_code, String state_name, String system_book_code, String stallList) {
        this.policy_promotion_no = policy_promotion_no;
        this.branch_num = branch_num;
        this.merchant_num = merchant_num;
        this.policy_promotion_audit_time = policy_promotion_audit_time;
        this.policy_promotion_auditor = policy_promotion_auditor;
        this.policy_promotion_cancel_operator = policy_promotion_cancel_operator;
        this.policy_promotion_cancel_time = policy_promotion_cancel_time;
        this.policy_promotion_card_once = policy_promotion_card_once;
        this.policy_promotion_card_only = policy_promotion_card_only;
        this.policy_promotion_card_type = policy_promotion_card_type;
        this.policy_promotion_category = policy_promotion_category;
        this.policy_promotion_create_time = policy_promotion_create_time;
        this.policy_promotion_creator = policy_promotion_creator;
        this.policy_promotion_date_from = policy_promotion_date_from;
        this.policy_promotion_date_to = policy_promotion_date_to;
        this.policy_promotion_discount = policy_promotion_discount;
        this.policy_promotion_except_item = policy_promotion_except_item;
        this.policy_promotion_memo = policy_promotion_memo;
        this.policy_promotion_rate = policy_promotion_rate;
        this.policy_promotion_time_from = policy_promotion_time_from;
        this.policy_promotion_time_to = policy_promotion_time_to;
        this.stall_num = stall_num;
        this.stall_promotion_all = stall_promotion_all;
        this.state_code = state_code;
        this.state_name = state_name;
        this.system_book_code = system_book_code;
        this.stallList = stallList;
    }

    @Generated(hash = 417064447)
    public StallPromotion() {
    }

    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public int getMerchant_num() {
        return merchant_num;
    }

    public void setMerchant_num(int merchant_num) {
        this.merchant_num = merchant_num;
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

    public String getPolicy_promotion_cancel_operator() {
        return policy_promotion_cancel_operator;
    }

    public void setPolicy_promotion_cancel_operator(String policy_promotion_cancel_operator) {
        this.policy_promotion_cancel_operator = policy_promotion_cancel_operator;
    }

    public String getPolicy_promotion_cancel_time() {
        return policy_promotion_cancel_time;
    }

    public void setPolicy_promotion_cancel_time(String policy_promotion_cancel_time) {
        this.policy_promotion_cancel_time = policy_promotion_cancel_time;
    }

    public boolean isPolicy_promotion_card_once() {
        return policy_promotion_card_once;
    }

    public void setPolicy_promotion_card_once(boolean policy_promotion_card_once) {
        this.policy_promotion_card_once = policy_promotion_card_once;
    }

    public boolean isPolicy_promotion_card_only() {
        return policy_promotion_card_only;
    }

    public void setPolicy_promotion_card_only(boolean policy_promotion_card_only) {
        this.policy_promotion_card_only = policy_promotion_card_only;
    }

    public String getPolicy_promotion_card_type() {
        return policy_promotion_card_type;
    }

    public void setPolicy_promotion_card_type(String policy_promotion_card_type) {
        this.policy_promotion_card_type = policy_promotion_card_type;
    }

    public String getPolicy_promotion_category() {
        return policy_promotion_category;
    }

    public void setPolicy_promotion_category(String policy_promotion_category) {
        this.policy_promotion_category = policy_promotion_category;
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

    public float getPolicy_promotion_discount() {
        return policy_promotion_discount;
    }

    public void setPolicy_promotion_discount(float policy_promotion_discount) {
        this.policy_promotion_discount = policy_promotion_discount;
    }

    public String getPolicy_promotion_except_item() {
        return policy_promotion_except_item;
    }

    public void setPolicy_promotion_except_item(String policy_promotion_except_item) {
        this.policy_promotion_except_item = policy_promotion_except_item;
    }

    public String getPolicy_promotion_memo() {
        return policy_promotion_memo;
    }

    public void setPolicy_promotion_memo(String policy_promotion_memo) {
        this.policy_promotion_memo = policy_promotion_memo;
    }

    public String getPolicy_promotion_no() {
        return policy_promotion_no;
    }

    public void setPolicy_promotion_no(String policy_promotion_no) {
        this.policy_promotion_no = policy_promotion_no;
    }

    public float getPolicy_promotion_rate() {
        return policy_promotion_rate;
    }

    public void setPolicy_promotion_rate(float policy_promotion_rate) {
        this.policy_promotion_rate = policy_promotion_rate;
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

    public int getStall_num() {
        return stall_num;
    }

    public void setStall_num(int stall_num) {
        this.stall_num = stall_num;
    }

    public boolean isStall_promotion_all() {
        return stall_promotion_all;
    }

    public void setStall_promotion_all(boolean stall_promotion_all) {
        this.stall_promotion_all = stall_promotion_all;
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

    public boolean getPolicy_promotion_card_once() {
        return this.policy_promotion_card_once;
    }

    public boolean getPolicy_promotion_card_only() {
        return this.policy_promotion_card_only;
    }

    public boolean getStall_promotion_all() {
        return this.stall_promotion_all;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 144100487)
    public List<StallPromotionDetail> getDetails() {
        if (details == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StallPromotionDetailDao targetDao = daoSession.getStallPromotionDetailDao();
            List<StallPromotionDetail> detailsNew = targetDao._queryStallPromotion_Details(policy_promotion_no);
            synchronized (this) {
                if (details == null) {
                    details = detailsNew;
                }
            }
        }
        return details;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1372349704)
    public synchronized void resetDetails() {
        details = null;
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
    @Generated(hash = 331200216)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStallPromotionDao() : null;
    }

}
