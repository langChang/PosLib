package com.nhsoft.poslib.entity;

import com.nhsoft.poslib.model.BasePolicyBean;
import com.nhsoft.poslib.service.greendao.DaoSession;
import com.nhsoft.poslib.service.greendao.PolicyMoneyDao;
import com.nhsoft.poslib.service.greendao.PolicyMoneyDetailDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * Created by Iverson on 2019-05-15 15:28
 * 此类用于：
 */

@Entity
public class PolicyMoney  extends BasePolicyBean {

    /**
     * promotion_money_no : 4344990000005
     * system_book_code : 4344
     * promotion_money_date_from : 2019-05-15 00:00:00
     * promotion_money_date_to : 2019-05-22 00:00:00
     * promotion_money_time_from : 2019-05-15 00:00:00
     * promotion_money_time_to : 2019-05-15 23:59:59
     * promotion_money_bill_money : 80
     * promotion_money_applied_branch : <?xml version="1.0" encoding="GBK"?>
     <AppliedBranchArray/>
     * promotion_money_mon_actived : true
     * promotion_money_tues_actived : true
     * promotion_money_wed_actived : true
     * promotion_money_thurs_actived : true
     * promotion_money_friday_actived : true
     * promotion_money_sat_actived : true
     * promotion_money_sun_actived : true
     * promotion_money_creator : 管理员
     * promotion_money_create_time : 2019-05-15 15:27:53
     * promotion_money_auditor : 管理员
     * promotion_money_audit_time : 2019-05-15 15:27:54
     * promotion_money_items : <?xml version="1.0" encoding="GBK"?>
     <商品列表><商品><商品编号>434400119</商品编号><商品名称>增量下载测试222</商品名称><商品代码>02015</商品代码><商品库存计量单位>斤</商品库存计量单位></商品><商品><商品编号>434400165</商品编号><商品名称>笔记本</商品名称><商品代码>02016</商品代码><商品库存计量单位>斤</商品库存计量单位></商品><商品><商品编号>434400796</商品编号><商品名称>餐盒</商品名称><商品代码>02017</商品代码><商品库存计量单位>只</商品库存计量单位><商品规格>cejl</商品规格></商品><商品><商品编号>434400797</商品编号><商品名称>原料红枣</商品名称><商品代码>02018</商品代码><商品库存计量单位>只</商品库存计量单位><商品规格>cejl</商品规格></商品><商品><商品编号>434400798</商品编号><商品名称>成分红枣</商品名称><商品代码>02019</商品代码><商品库存计量单位>只</商品库存计量单位><商品规格>cejl</商品规格></商品><商品><商品编号>434400802</商品编号><商品名称>不二家（荔枝）</商品名称><商品代码>02020</商品代码><商品库存计量单位>只</商品库存计量单位><商品规格>cejl</商品规格></商品></商品列表>
     * branch_num : 99
     * promotion_item_count : 5
     * promotion_money_card_only : true
     * promotion_money_card_type : <?xml version="1.0" encoding="GBK"?>
     <消费卡类型列表><消费卡类型 编号="99"><名称>电子卡</名称></消费卡类型><消费卡类型 编号="100"><名称>会员卡</名称></消费卡类型><消费卡类型 编号="101"><名称>折扣卡</名称></消费卡类型><消费卡类型 编号="102"><名称>超级vip</名称></消费卡类型><消费卡类型 编号="103"><名称>测试vip</名称></消费卡类型><消费卡类型 编号="104"><名称>至尊卡</名称></消费卡类型><消费卡类型 编号="105"><名称>周年纪念卡</名称></消费卡类型></消费卡类型列表>
     * promotion_money_append : true
     * promotion_money_assigned_type : 指定商品
     * promotion_money_last_edit_time : 2019-05-15 15:27:53
     * promotion_money_last_editor : 管理员
     */

    @Id
    private String promotion_money_no;
    private String  system_book_code;
    private String  promotion_money_date_from;
    private String  promotion_money_date_to;
    private String  promotion_money_time_from;
    private String  promotion_money_time_to;
    private float   promotion_money_bill_money;
    private String  promotion_money_applied_branch;
    private boolean promotion_money_mon_actived;
    private boolean promotion_money_tues_actived;
    private boolean promotion_money_wed_actived;
    private boolean promotion_money_thurs_actived;
    private boolean promotion_money_friday_actived;
    private boolean promotion_money_sat_actived;
    private boolean promotion_money_sun_actived;
    private String  promotion_money_creator;
    private String  promotion_money_create_time;
    private String  promotion_money_auditor;
    private String  promotion_money_audit_time;
    private String  promotion_money_items;
    private int     branch_num;
    private int     promotion_item_count;
    private boolean promotion_money_card_only;
    private String  promotion_money_card_type;
    private boolean promotion_money_append;
    private String  promotion_money_assigned_type;
    private String promotion_money_level_ids;
    private Integer promotion_money_price_type;
    private Float promotion_money_discount;


    @Transient
    private int multiple;

    public int getMultiple() {
        return multiple;
    }

    public void setMultiple(int multiple) {
        this.multiple = multiple;
    }

    private           String                  promotion_money_assigned_category;
    private           String                  promotion_money_last_edit_time;
    private           String                  promotion_money_last_editor;
    @ToMany(referencedJoinProperty = "promotion_money_no")
    private           List<PolicyMoneyDetail> policy_promotion_money_details;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 2132523669)
    private transient PolicyMoneyDao myDao;

    @Generated(hash = 533458529)
    public PolicyMoney(String promotion_money_no, String system_book_code, String promotion_money_date_from, String promotion_money_date_to, String promotion_money_time_from, String promotion_money_time_to, float promotion_money_bill_money, String promotion_money_applied_branch, boolean promotion_money_mon_actived, boolean promotion_money_tues_actived, boolean promotion_money_wed_actived, boolean promotion_money_thurs_actived, boolean promotion_money_friday_actived, boolean promotion_money_sat_actived, boolean promotion_money_sun_actived, String promotion_money_creator, String promotion_money_create_time,
            String promotion_money_auditor, String promotion_money_audit_time, String promotion_money_items, int branch_num, int promotion_item_count, boolean promotion_money_card_only, String promotion_money_card_type, boolean promotion_money_append, String promotion_money_assigned_type, String promotion_money_level_ids, Integer promotion_money_price_type, Float promotion_money_discount, String promotion_money_assigned_category, String promotion_money_last_edit_time, String promotion_money_last_editor) {
        this.promotion_money_no = promotion_money_no;
        this.system_book_code = system_book_code;
        this.promotion_money_date_from = promotion_money_date_from;
        this.promotion_money_date_to = promotion_money_date_to;
        this.promotion_money_time_from = promotion_money_time_from;
        this.promotion_money_time_to = promotion_money_time_to;
        this.promotion_money_bill_money = promotion_money_bill_money;
        this.promotion_money_applied_branch = promotion_money_applied_branch;
        this.promotion_money_mon_actived = promotion_money_mon_actived;
        this.promotion_money_tues_actived = promotion_money_tues_actived;
        this.promotion_money_wed_actived = promotion_money_wed_actived;
        this.promotion_money_thurs_actived = promotion_money_thurs_actived;
        this.promotion_money_friday_actived = promotion_money_friday_actived;
        this.promotion_money_sat_actived = promotion_money_sat_actived;
        this.promotion_money_sun_actived = promotion_money_sun_actived;
        this.promotion_money_creator = promotion_money_creator;
        this.promotion_money_create_time = promotion_money_create_time;
        this.promotion_money_auditor = promotion_money_auditor;
        this.promotion_money_audit_time = promotion_money_audit_time;
        this.promotion_money_items = promotion_money_items;
        this.branch_num = branch_num;
        this.promotion_item_count = promotion_item_count;
        this.promotion_money_card_only = promotion_money_card_only;
        this.promotion_money_card_type = promotion_money_card_type;
        this.promotion_money_append = promotion_money_append;
        this.promotion_money_assigned_type = promotion_money_assigned_type;
        this.promotion_money_level_ids = promotion_money_level_ids;
        this.promotion_money_price_type = promotion_money_price_type;
        this.promotion_money_discount = promotion_money_discount;
        this.promotion_money_assigned_category = promotion_money_assigned_category;
        this.promotion_money_last_edit_time = promotion_money_last_edit_time;
        this.promotion_money_last_editor = promotion_money_last_editor;
    }

    @Generated(hash = 1601496171)
    public PolicyMoney() {
    }
    
    public void setPolicy_promotion_money_details(List<PolicyMoneyDetail> policy_promotion_money_details) {
        this.policy_promotion_money_details = policy_promotion_money_details;
    }


    public String getPromotion_money_no() {
        return promotion_money_no;
    }

    public void setPromotion_money_no(String promotion_money_no) {
        this.promotion_money_no = promotion_money_no;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public String getPromotion_money_date_from() {
        return promotion_money_date_from;
    }

    public void setPromotion_money_date_from(String promotion_money_date_from) {
        this.promotion_money_date_from = promotion_money_date_from;
    }

    public String getPromotion_money_date_to() {
        return promotion_money_date_to;
    }

    public void setPromotion_money_date_to(String promotion_money_date_to) {
        this.promotion_money_date_to = promotion_money_date_to;
    }

    public String getPromotion_money_time_from() {
        return promotion_money_time_from;
    }

    public void setPromotion_money_time_from(String promotion_money_time_from) {
        this.promotion_money_time_from = promotion_money_time_from;
    }

    public String getPromotion_money_time_to() {
        return promotion_money_time_to;
    }

    public void setPromotion_money_time_to(String promotion_money_time_to) {
        this.promotion_money_time_to = promotion_money_time_to;
    }

    public float getPromotion_money_bill_money() {
        return promotion_money_bill_money;
    }

    public void setPromotion_money_bill_money(float promotion_money_bill_money) {
        this.promotion_money_bill_money = promotion_money_bill_money;
    }

    public String getPromotion_money_applied_branch() {
        return promotion_money_applied_branch;
    }

    public void setPromotion_money_applied_branch(String promotion_money_applied_branch) {
        this.promotion_money_applied_branch = promotion_money_applied_branch;
    }

    public boolean isPromotion_money_mon_actived() {
        return promotion_money_mon_actived;
    }

    public void setPromotion_money_mon_actived(boolean promotion_money_mon_actived) {
        this.promotion_money_mon_actived = promotion_money_mon_actived;
    }

    public boolean isPromotion_money_tues_actived() {
        return promotion_money_tues_actived;
    }

    public void setPromotion_money_tues_actived(boolean promotion_money_tues_actived) {
        this.promotion_money_tues_actived = promotion_money_tues_actived;
    }

    public boolean isPromotion_money_wed_actived() {
        return promotion_money_wed_actived;
    }

    public void setPromotion_money_wed_actived(boolean promotion_money_wed_actived) {
        this.promotion_money_wed_actived = promotion_money_wed_actived;
    }

    public boolean isPromotion_money_thurs_actived() {
        return promotion_money_thurs_actived;
    }

    public void setPromotion_money_thurs_actived(boolean promotion_money_thurs_actived) {
        this.promotion_money_thurs_actived = promotion_money_thurs_actived;
    }

    public boolean isPromotion_money_friday_actived() {
        return promotion_money_friday_actived;
    }

    public void setPromotion_money_friday_actived(boolean promotion_money_friday_actived) {
        this.promotion_money_friday_actived = promotion_money_friday_actived;
    }

    public boolean isPromotion_money_sat_actived() {
        return promotion_money_sat_actived;
    }

    public void setPromotion_money_sat_actived(boolean promotion_money_sat_actived) {
        this.promotion_money_sat_actived = promotion_money_sat_actived;
    }

    public boolean isPromotion_money_sun_actived() {
        return promotion_money_sun_actived;
    }

    public void setPromotion_money_sun_actived(boolean promotion_money_sun_actived) {
        this.promotion_money_sun_actived = promotion_money_sun_actived;
    }

    public String getPromotion_money_creator() {
        return promotion_money_creator;
    }

    public void setPromotion_money_creator(String promotion_money_creator) {
        this.promotion_money_creator = promotion_money_creator;
    }

    public String getPromotion_money_create_time() {
        return promotion_money_create_time;
    }

    public void setPromotion_money_create_time(String promotion_money_create_time) {
        this.promotion_money_create_time = promotion_money_create_time;
    }


    public String getPromotion_money_assigned_category() {
        return promotion_money_assigned_category;
    }

    public void setPromotion_money_assigned_category(String promotion_money_assigned_category) {
        this.promotion_money_assigned_category = promotion_money_assigned_category;
    }

    public String getPromotion_money_auditor() {
        return promotion_money_auditor;
    }

    public void setPromotion_money_auditor(String promotion_money_auditor) {
        this.promotion_money_auditor = promotion_money_auditor;
    }

    public String getPromotion_money_audit_time() {
        return promotion_money_audit_time;
    }

    public void setPromotion_money_audit_time(String promotion_money_audit_time) {
        this.promotion_money_audit_time = promotion_money_audit_time;
    }

    public String getPromotion_money_items() {
        return promotion_money_items;
    }

    public void setPromotion_money_items(String promotion_money_items) {
        this.promotion_money_items = promotion_money_items;
    }

    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public int getPromotion_item_count() {
        return promotion_item_count;
    }

    public void setPromotion_item_count(int promotion_item_count) {
        this.promotion_item_count = promotion_item_count;
    }

    public boolean isPromotion_money_card_only() {
        return promotion_money_card_only;
    }

    public void setPromotion_money_card_only(boolean promotion_money_card_only) {
        this.promotion_money_card_only = promotion_money_card_only;
    }

    public String getPromotion_money_card_type() {
        return promotion_money_card_type;
    }

    public void setPromotion_money_card_type(String promotion_money_card_type) {
        this.promotion_money_card_type = promotion_money_card_type;
    }

    public boolean isPromotion_money_append() {
        return promotion_money_append;
    }

    public void setPromotion_money_append(boolean promotion_money_append) {
        this.promotion_money_append = promotion_money_append;
    }

    public String getPromotion_money_assigned_type() {
        return promotion_money_assigned_type;
    }

    public void setPromotion_money_assigned_type(String promotion_money_assigned_type) {
        this.promotion_money_assigned_type = promotion_money_assigned_type;
    }

    public String getPromotion_money_last_edit_time() {
        return promotion_money_last_edit_time;
    }

    public void setPromotion_money_last_edit_time(String promotion_money_last_edit_time) {
        this.promotion_money_last_edit_time = promotion_money_last_edit_time;
    }

    public String getPromotion_money_last_editor() {
        return promotion_money_last_editor;
    }

    public void setPromotion_money_last_editor(String promotion_money_last_editor) {
        this.promotion_money_last_editor = promotion_money_last_editor;
    }


    public boolean getPromotion_money_mon_actived() {
        return this.promotion_money_mon_actived;
    }


    public boolean getPromotion_money_tues_actived() {
        return this.promotion_money_tues_actived;
    }


    public boolean getPromotion_money_wed_actived() {
        return this.promotion_money_wed_actived;
    }


    public boolean getPromotion_money_thurs_actived() {
        return this.promotion_money_thurs_actived;
    }


    public boolean getPromotion_money_friday_actived() {
        return this.promotion_money_friday_actived;
    }


    public boolean getPromotion_money_sat_actived() {
        return this.promotion_money_sat_actived;
    }


    public boolean getPromotion_money_sun_actived() {
        return this.promotion_money_sun_actived;
    }


    public boolean getPromotion_money_card_only() {
        return this.promotion_money_card_only;
    }


    public boolean getPromotion_money_append() {
        return this.promotion_money_append;
    }


    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1433624623)
    public List<PolicyMoneyDetail> getPolicy_promotion_money_details() {
        if (policy_promotion_money_details == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PolicyMoneyDetailDao targetDao = daoSession.getPolicyMoneyDetailDao();
            List<PolicyMoneyDetail> policy_promotion_money_detailsNew = targetDao._queryPolicyMoney_Policy_promotion_money_details(promotion_money_no);
            synchronized (this) {
                if (policy_promotion_money_details == null) {
                    policy_promotion_money_details = policy_promotion_money_detailsNew;
                }
            }
        }
        return policy_promotion_money_details;
    }


    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 238632843)
    public synchronized void resetPolicy_promotion_money_details() {
        policy_promotion_money_details = null;
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


    public String getPromotion_money_level_ids() {
        return this.promotion_money_level_ids;
    }

    public void setPromotion_money_level_ids(String promotion_money_level_ids) {
        this.promotion_money_level_ids = promotion_money_level_ids;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 583038740)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPolicyMoneyDao() : null;
    }

    public Integer getPromotion_money_price_type() {
        return this.promotion_money_price_type;
    }

    public void setPromotion_money_price_type(Integer promotion_money_price_type) {
        this.promotion_money_price_type = promotion_money_price_type;
    }

    public Float getPromotion_money_discount() {
        return this.promotion_money_discount;
    }

    public void setPromotion_money_discount(Float promotion_money_discount) {
        this.promotion_money_discount = promotion_money_discount;
    }
}
