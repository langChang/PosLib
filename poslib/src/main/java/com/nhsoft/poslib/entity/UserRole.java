package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Iverson on 2018/11/16 10:39 AM
 * 此类用于：
 */

@Entity
public class UserRole {

    /**
     * system_role_num : 444400001
     * app_user_num : 444400014
     * system_book_code : 4444
     */

    @Id(autoincrement = true)
    private Long id;
    private Long system_role_num;
    private Long    app_user_num;
    private String system_book_code;
    @Generated(hash = 1712539164)
    public UserRole(Long id, Long system_role_num, Long app_user_num,
            String system_book_code) {
        this.id = id;
        this.system_role_num = system_role_num;
        this.app_user_num = app_user_num;
        this.system_book_code = system_book_code;
    }
    @Generated(hash = 552541888)
    public UserRole() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getSystem_role_num() {
        return this.system_role_num;
    }
    public void setSystem_role_num(Long system_role_num) {
        this.system_role_num = system_role_num;
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

}
