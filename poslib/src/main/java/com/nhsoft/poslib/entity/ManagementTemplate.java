package com.nhsoft.poslib.entity;

import com.nhsoft.poslib.service.greendao.ManagementTemplateDao;
import com.nhsoft.poslib.service.greendao.ManagementTemplateDetailDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import com.nhsoft.poslib.service.greendao.DaoSession;

/**
 * Created by Iverson on 2018/11/17 11:15 AM
 * 此类用于：
 */
@Entity
public class ManagementTemplate {
    /**
     * managemen_template_num : 0
     * system_book_code : 4444
     * management_template_name : 下载测试
     * management_template_last_edit_time : 2018-10-30 19:17:48
     * management_template_details : [{"item_num":210100009,"management_template_num":417300009},{"item_num":444400352,"management_template_num":417300009},{"item_num":444400353,"management_template_num":417300009},{"item_num":444400358,"management_template_num":417300009},{"item_num":444400360,"management_template_num":417300009},{"item_num":444400362,"management_template_num":417300009},{"item_num":444400364,"management_template_num":417300009},{"item_num":444400365,"management_template_num":417300009},{"item_num":444400367,"management_template_num":417300009},{"item_num":444400368,"management_template_num":417300009},{"item_num":444400369,"management_template_num":417300009},{"item_num":444400370,"management_template_num":417300009},{"item_num":444400371,"management_template_num":417300009},{"item_num":444400372,"management_template_num":417300009},{"item_num":444400373,"management_template_num":417300009},{"item_num":444400374,"management_template_num":417300009},{"item_num":444400375,"management_template_num":417300009},{"item_num":444400376,"management_template_num":417300009},{"item_num":444400377,"management_template_num":417300009},{"item_num":444400378,"management_template_num":417300009},{"item_num":444400391,"management_template_num":417300009},{"item_num":444400410,"management_template_num":417300009},{"item_num":444400411,"management_template_num":417300009},{"item_num":444400412,"management_template_num":417300009},{"item_num":444400430,"management_template_num":417300009},{"item_num":444400457,"management_template_num":417300009},{"item_num":444400505,"management_template_num":417300009},{"item_num":444400506,"management_template_num":417300009},{"item_num":444400512,"management_template_num":417300009},{"item_num":444400513,"management_template_num":417300009},{"item_num":444400514,"management_template_num":417300009},{"item_num":444400532,"management_template_num":417300009},{"item_num":444400546,"management_template_num":417300009},{"item_num":444400548,"management_template_num":417300009},{"item_num":444400554,"management_template_num":417300009},{"item_num":444400555,"management_template_num":417300009},{"item_num":444400557,"management_template_num":417300009},{"item_num":444400559,"management_template_num":417300009},{"item_num":444400560,"management_template_num":417300009},{"item_num":444400561,"management_template_num":417300009},{"item_num":444400562,"management_template_num":417300009},{"item_num":444400563,"management_template_num":417300009},{"item_num":444400571,"management_template_num":417300009},{"item_num":444400578,"management_template_num":417300009},{"item_num":444400775,"management_template_num":417300009},{"item_num":444400995,"management_template_num":417300009},{"item_num":444400996,"management_template_num":417300009},{"item_num":444400997,"management_template_num":417300009},{"item_num":444401001,"management_template_num":417300009},{"item_num":444401029,"management_template_num":417300009},{"item_num":444401033,"management_template_num":417300009},{"item_num":444401034,"management_template_num":417300009},{"item_num":444401073,"management_template_num":417300009},{"item_num":444401075,"management_template_num":417300009},{"item_num":444401077,"management_template_num":417300009},{"item_num":444401078,"management_template_num":417300009},{"item_num":444401079,"management_template_num":417300009},{"item_num":444401080,"management_template_num":417300009},{"item_num":444401084,"management_template_num":417300009},{"item_num":444401085,"management_template_num":417300009},{"item_num":444401086,"management_template_num":417300009},{"item_num":444401087,"management_template_num":417300009},{"item_num":444401088,"management_template_num":417300009},{"item_num":444401089,"management_template_num":417300009},{"item_num":444401090,"management_template_num":417300009},{"item_num":444401091,"management_template_num":417300009},{"item_num":444401092,"management_template_num":417300009},{"item_num":444401093,"management_template_num":417300009},{"item_num":444401094,"management_template_num":417300009},{"item_num":444401095,"management_template_num":417300009},{"item_num":444401102,"management_template_num":417300009},{"item_num":444401103,"management_template_num":417300009},{"item_num":444401104,"management_template_num":417300009},{"item_num":444401105,"management_template_num":417300009},{"item_num":444401110,"management_template_num":417300009},{"item_num":444401111,"management_template_num":417300009},{"item_num":444401142,"management_template_num":417300009},{"item_num":444401453,"management_template_num":417300009},{"item_num":444401454,"management_template_num":417300009},{"item_num":444401455,"management_template_num":417300009},{"item_num":444401456,"management_template_num":417300009},{"item_num":444401457,"management_template_num":417300009},{"item_num":444401458,"management_template_num":417300009},{"item_num":444401459,"management_template_num":417300009},{"item_num":444401466,"management_template_num":417300009},{"item_num":444401467,"management_template_num":417300009},{"item_num":444401468,"management_template_num":417300009},{"item_num":444401478,"management_template_num":417300009},{"item_num":444401479,"management_template_num":417300009},{"item_num":444401480,"management_template_num":417300009},{"item_num":444401481,"management_template_num":417300009},{"item_num":444401492,"management_template_num":417300009},{"item_num":444401501,"management_template_num":417300009},{"item_num":444401502,"management_template_num":417300009},{"item_num":444401503,"management_template_num":417300009},{"item_num":444401504,"management_template_num":417300009},{"item_num":444499994,"management_template_num":417300009}]
     */

    @Id
    @Property(nameInDb = "MANAGEMENT_TEMPLATE_NUM")
    private Long management_template_num;
    private String                              system_book_code;
    private String                              management_template_name;
    private String                              management_template_last_edit_time;
    @ToMany(referencedJoinProperty = "management_template_num")
    private List<ManagementTemplateDetail> management_template_details;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 838448675)
    private transient ManagementTemplateDao myDao;
    @Generated(hash = 11177989)
    public ManagementTemplate(Long management_template_num, String system_book_code, String management_template_name, String management_template_last_edit_time) {
        this.management_template_num = management_template_num;
        this.system_book_code = system_book_code;
        this.management_template_name = management_template_name;
        this.management_template_last_edit_time = management_template_last_edit_time;
    }
    @Generated(hash = 994175748)
    public ManagementTemplate() {
    }
    public Long getManagement_template_num() {
        return this.management_template_num;
    }
    public void setManagement_template_num(Long management_template_num) {
        this.management_template_num = management_template_num;
    }
    public String getSystem_book_code() {
        return this.system_book_code;
    }
    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }
    public String getManagement_template_name() {
        return this.management_template_name;
    }
    public void setManagement_template_name(String management_template_name) {
        this.management_template_name = management_template_name;
    }
    public String getManagement_template_last_edit_time() {
        return this.management_template_last_edit_time;
    }
    public void setManagement_template_last_edit_time(String management_template_last_edit_time) {
        this.management_template_last_edit_time = management_template_last_edit_time;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1347040591)
    public List<ManagementTemplateDetail> getManagement_template_details() {
        if (management_template_details == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ManagementTemplateDetailDao targetDao = daoSession.getManagementTemplateDetailDao();
            List<ManagementTemplateDetail> management_template_detailsNew = targetDao._queryManagementTemplate_Management_template_details(management_template_num);
            synchronized (this) {
                if (management_template_details == null) {
                    management_template_details = management_template_detailsNew;
                }
            }
        }
        return management_template_details;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 752787342)
    public synchronized void resetManagement_template_details() {
        management_template_details = null;
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
    @Generated(hash = 1772393097)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getManagementTemplateDao() : null;
    }

}
