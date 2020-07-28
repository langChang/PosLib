package com.nhsoft.poslib.entity;

import com.nhsoft.poslib.service.greendao.AppUserDao;
import com.nhsoft.poslib.service.greendao.DaoSession;
import com.nhsoft.poslib.service.greendao.UserRoleDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * Created by Iverson on 2018/11/16 10:38 AM
 * 此类用于：
 */

@Entity
public class AppUser {

    /**
     * app_user_num : 444400014
     * system_book_code : 4444
     * app_user_code : nhsoft.hlw
     * app_user_name : nhsoft.hlw
     * app_user_password : d9c08a90d2b8a2ddd436b2d7252645b6
     * app_user_state_code : 1
     * app_user_phone : 15867226751
     * app_user_email :
     * app_user_pw_key
     * user_role_list : [{"system_role_num":444400001,"app_user_num":444400014,"system_book_code":"4444"}]
     */

    @Id
    @Property(nameInDb = "app_user_num")
    private Long app_user_num;
    private String system_book_code;
    private String app_user_code;
    private String app_user_name;
    private String app_user_password;
    private int app_user_state_code;
    private String app_user_phone;
    private String app_user_email;
    private String app_user_pw_key;
    private boolean touch_pos_user;
    private float user_max_discount;
    private float user_max_discount_rate;
    private Integer user_max_order_number;//挂单单数
    private Integer merchant_num;//农贸 商户号

    public Integer getMerchant_num() {
        return merchant_num;
    }

    public void setMerchant_num(Integer merchant_num) {
        this.merchant_num = merchant_num;
    }


    @ToMany(referencedJoinProperty = "app_user_num")
    private List<UserRole> user_role_list;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1682778229)
    private transient AppUserDao myDao;

    @Generated(hash = 452040870)
    public AppUser(Long app_user_num, String system_book_code, String app_user_code, String app_user_name,
            String app_user_password, int app_user_state_code, String app_user_phone, String app_user_email,
            String app_user_pw_key, boolean touch_pos_user, float user_max_discount, float user_max_discount_rate,
            Integer user_max_order_number, Integer merchant_num) {
        this.app_user_num = app_user_num;
        this.system_book_code = system_book_code;
        this.app_user_code = app_user_code;
        this.app_user_name = app_user_name;
        this.app_user_password = app_user_password;
        this.app_user_state_code = app_user_state_code;
        this.app_user_phone = app_user_phone;
        this.app_user_email = app_user_email;
        this.app_user_pw_key = app_user_pw_key;
        this.touch_pos_user = touch_pos_user;
        this.user_max_discount = user_max_discount;
        this.user_max_discount_rate = user_max_discount_rate;
        this.user_max_order_number = user_max_order_number;
        this.merchant_num = merchant_num;
    }

    @Generated(hash = 70494256)
    public AppUser() {
    }


    public Long getApp_user_num() {
        return this.app_user_num;
    }

    public void setApp_user_num(Long app_user_num) {
        this.app_user_num = app_user_num;
    }

    public String getSystem_book_code() {
        return this.system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public String getApp_user_code() {
        return this.app_user_code;
    }

    public void setApp_user_code(String app_user_code) {
        this.app_user_code = app_user_code;
    }

    public String getApp_user_name() {
        return this.app_user_name;
    }

    public void setApp_user_name(String app_user_name) {
        this.app_user_name = app_user_name;
    }

    public String getApp_user_password() {
        return this.app_user_password;
    }

    public void setApp_user_password(String app_user_password) {
        this.app_user_password = app_user_password;
    }

    public int getApp_user_state_code() {
        return this.app_user_state_code;
    }

    public void setApp_user_state_code(int app_user_state_code) {
        this.app_user_state_code = app_user_state_code;
    }

    public String getApp_user_phone() {
        return this.app_user_phone;
    }

    public void setApp_user_phone(String app_user_phone) {
        this.app_user_phone = app_user_phone;
    }

    public String getApp_user_email() {
        return this.app_user_email;
    }

    public void setApp_user_email(String app_user_email) {
        this.app_user_email = app_user_email;
    }

    public String getApp_user_pw_key() {
        return this.app_user_pw_key;
    }

    public void setApp_user_pw_key(String app_user_pw_key) {
        this.app_user_pw_key = app_user_pw_key;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 897157181)
    public List<UserRole> getUser_role_list() {
        if (user_role_list == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserRoleDao targetDao = daoSession.getUserRoleDao();
            List<UserRole> user_role_listNew = targetDao._queryAppUser_User_role_list(app_user_num);
            synchronized (this) {
                if (user_role_list == null) {
                    user_role_list = user_role_listNew;
                }
            }
        }
        return user_role_list;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1014506424)
    public synchronized void resetUser_role_list() {
        user_role_list = null;
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

    public boolean isTouch_pos_user() {
        return touch_pos_user;
    }

    public void setTouch_pos_user(boolean touch_pos_user) {
        this.touch_pos_user = touch_pos_user;
    }

    public float getUser_max_discount() {
        return user_max_discount;
    }

    public void setUser_max_discount(float user_max_discount) {
        this.user_max_discount = user_max_discount;
    }

    public float getUser_max_discount_rate() {
        return user_max_discount_rate;
    }

    public void setUser_max_discount_rate(float user_max_discount_rate) {
        this.user_max_discount_rate = user_max_discount_rate;
    }

    public boolean getTouch_pos_user() {
        return this.touch_pos_user;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 34658731)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAppUserDao() : null;
    }

    public int getUser_max_order_number() {
        return this.user_max_order_number==null?0:user_max_order_number;
    }

    public void setUser_max_order_number(int user_max_order_number) {
        this.user_max_order_number = user_max_order_number;
    }

    public void setUser_max_order_number(Integer user_max_order_number) {
        this.user_max_order_number = user_max_order_number;
    }


}
