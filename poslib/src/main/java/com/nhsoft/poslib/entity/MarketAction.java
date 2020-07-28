package com.nhsoft.poslib.entity;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.nhsoft.poslib.model.MarketActionScopeBean;
import com.nhsoft.poslib.service.greendao.DaoSession;
import com.nhsoft.poslib.service.greendao.MarketActionDao;
import com.nhsoft.poslib.service.greendao.MarketActionDetailDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * Created by Iverson on 2018/11/16 10:44 AM
 * 此类用于：
 */
@Entity
public class MarketAction {

    /**
     * action_id : MA4444990000166
     * branch_num : 99
     * action_name : pos红酥手
     * action_create_time : 2018-11-14T06:09:06.540+0000
     * action_creator : 管理员
     * action_audit_time : 2018-11-14T06:08:54.740+0000
     * action_auditor : 管理员
     * action_type : POS消费赠券
     * action_sms_template : [称呼]您好，为感谢您的支持，现赠送您[优惠券类型]，券编号为[券编号]，请在[有效期]前使用此券，期待您的光临！回复TD退订
     * action_date_from : 2018-11-13T16:00:00.000+0000
     * action_date_to : 2018-11-15T16:00:00.000+0000
     * market_action_details : [{"action_id":"MA4444990000166","market_action_detail_num":1,"market_action_detail_amount":1,"market_action_detail_type_name":"5yuan","market_action_detail_branch":"<?xml version=\"1.0\" encoding=\"GBK\"?>\n<AppliedBranchArray><AppliedBranch><AppliedBranchNum>0<\/AppliedBranchNum><AppliedBranchName>所有门店<\/AppliedBranchName><\/AppliedBranch><\/AppliedBranchArray>","market_action_detail_value":5,"market_action_detail_day":2,"market_action_detail_grade":50}]
     */

    @Id
    @Property(nameInDb = "ACTION_ID")
    private String action_id;
    private int                           branch_num;
    private String                        action_name;
    private String                        action_create_time;
    private String                        action_creator;
    private String                        action_audit_time;
    private String                        action_auditor;
    private String                        action_type;
    private String                        action_sms_template;
    private String                        action_date_from;
    private String                        action_date_to;
    private String action_param;
    private String action_time_from;
    private String action_time_to;

    public Boolean getOnly_card_use() {
        return only_card_use;
    }

    public void setOnly_card_use(Boolean only_card_use) {
        this.only_card_use = only_card_use;
    }

    public Boolean getOnly_use_once() {
        return only_use_once == null ? false : only_use_once;
    }

    public void setOnly_use_once(Boolean only_use_once) {
        this.only_use_once = only_use_once;
    }

    private Boolean only_card_use;
    private Boolean only_use_once;

    @Transient
    private MarketActionScopeBean pos_action_param;
    @Transient
    float actionMoney = 0;




    public void setPos_action_param(MarketActionScopeBean pos_action_param) {
        this.pos_action_param = pos_action_param;
        if(pos_action_param != null){
            setAction_param(new Gson().toJson(pos_action_param));
        }
    }

    public MarketActionScopeBean getPos_action_param() {
        if(!TextUtils.isEmpty(action_param)){
            pos_action_param = new Gson().fromJson(action_param,MarketActionScopeBean.class);
        }
        return pos_action_param;
    }

    @ToMany(referencedJoinProperty = "action_id")
    private List<MarketActionDetail> market_action_details;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 626563972)
    private transient MarketActionDao myDao;

    @Generated(hash = 1386104761)
    public MarketAction(String action_id, int branch_num, String action_name, String action_create_time, String action_creator, String action_audit_time, String action_auditor, String action_type, String action_sms_template, String action_date_from, String action_date_to, String action_param, String action_time_from, String action_time_to, Boolean only_card_use, Boolean only_use_once) {
        this.action_id = action_id;
        this.branch_num = branch_num;
        this.action_name = action_name;
        this.action_create_time = action_create_time;
        this.action_creator = action_creator;
        this.action_audit_time = action_audit_time;
        this.action_auditor = action_auditor;
        this.action_type = action_type;
        this.action_sms_template = action_sms_template;
        this.action_date_from = action_date_from;
        this.action_date_to = action_date_to;
        this.action_param = action_param;
        this.action_time_from = action_time_from;
        this.action_time_to = action_time_to;
        this.only_card_use = only_card_use;
        this.only_use_once = only_use_once;
    }

    @Generated(hash = 362253464)
    public MarketAction() {
    }


    public String getAction_id() {
        return this.action_id;
    }

    public void setAction_id(String action_id) {
        this.action_id = action_id;
    }

    public int getBranch_num() {
        return this.branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public String getAction_name() {
        return this.action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public String getAction_create_time() {
        return this.action_create_time;
    }

    public void setAction_create_time(String action_create_time) {
        this.action_create_time = action_create_time;
    }

    public String getAction_creator() {
        return this.action_creator;
    }

    public void setAction_creator(String action_creator) {
        this.action_creator = action_creator;
    }

    public String getAction_audit_time() {
        return this.action_audit_time;
    }

    public void setAction_audit_time(String action_audit_time) {
        this.action_audit_time = action_audit_time;
    }

    public String getAction_auditor() {
        return this.action_auditor;
    }

    public void setAction_auditor(String action_auditor) {
        this.action_auditor = action_auditor;
    }

    public String getAction_type() {
        return this.action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public String getAction_sms_template() {
        return this.action_sms_template;
    }

    public void setAction_sms_template(String action_sms_template) {
        this.action_sms_template = action_sms_template;
    }

    public String getAction_date_from() {
        return this.action_date_from;
    }

    public void setAction_date_from(String action_date_from) {
        this.action_date_from = action_date_from;
    }

    public String getAction_date_to() {
        return this.action_date_to;
    }

    public void setAction_date_to(String action_date_to) {
        this.action_date_to = action_date_to;
    }


    public String getAction_param() {
        return action_param;
    }

    public void setAction_param(String action_param) {
        this.action_param = action_param;
    }

    public void setMarket_action_details(List<MarketActionDetail> marketActionDetails) {
        this.market_action_details = marketActionDetails;
    }
    public float getActionMoney() {
        return actionMoney;
    }

    public void setActionMoney(float actionMoney) {
        this.actionMoney = actionMoney;
    }

    public String getAction_time_from() {
        return action_time_from;
    }

    public void setAction_time_from(String action_time_from) {
        this.action_time_from = action_time_from;
    }

    public String getAction_time_to() {
        return action_time_to;
    }

    public void setAction_time_to(String action_time_to) {
        this.action_time_to = action_time_to;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1756862266)
    public List<MarketActionDetail> getMarket_action_details() {
        if (market_action_details == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MarketActionDetailDao targetDao = daoSession.getMarketActionDetailDao();
            List<MarketActionDetail> market_action_detailsNew = targetDao._queryMarketAction_Market_action_details(action_id);
            synchronized (this) {
                if (market_action_details == null) {
                    market_action_details = market_action_detailsNew;
                }
            }
        }
        return market_action_details;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 875664375)
    public synchronized void resetMarket_action_details() {
        market_action_details = null;
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
    @Generated(hash = 940456300)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMarketActionDao() : null;
    }
}
