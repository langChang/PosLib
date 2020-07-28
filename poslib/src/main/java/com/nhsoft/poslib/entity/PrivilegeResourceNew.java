package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class PrivilegeResourceNew {

    /**
     * privilege_resource_key : Integer||权限编码
     * privilege_resource_name : String||权限名称
     * privilege_resource_category : String||权限类型
     * privilege_resource_query : Boolean||权限是否可查询
     * privilege_resource_edit : Boolean||权限是否可编辑
     * privilege_resource_delete : Boolean||权限是否可删除
     * privilege_resource_audit : Boolean||权限是否可审核
     * privilege_resource_invalid : Boolean||权限是否可注销
     * privilege_resource_re_audit : Boolean||权限是否可反审核
     * privilege_resource_print : Boolean||权限是否可打印
     * privilege_resource_export : Boolean||权限是否可导出
     * privilege_resource_parent_key : Integer||权限父编码
     */

    @Id
    @Property(nameInDb = "PRIVILEGE_RESOURCE_KEY")
    private Long privilege_resource_key;
    private String privilege_resource_name;
    private String privilege_resource_category;
    private boolean privilege_resource_query;
    private boolean privilege_resource_edit;
    private boolean privilege_resource_delete;
    private boolean privilege_resource_audit;
    private boolean privilege_resource_invalid;
    private boolean privilege_resource_re_audit;
    private boolean privilege_resource_print;
    private boolean privilege_resource_export;
    @Generated(hash = 463377616)
    public PrivilegeResourceNew(Long privilege_resource_key,
            String privilege_resource_name, String privilege_resource_category,
            boolean privilege_resource_query, boolean privilege_resource_edit,
            boolean privilege_resource_delete, boolean privilege_resource_audit,
            boolean privilege_resource_invalid, boolean privilege_resource_re_audit,
            boolean privilege_resource_print, boolean privilege_resource_export) {
        this.privilege_resource_key = privilege_resource_key;
        this.privilege_resource_name = privilege_resource_name;
        this.privilege_resource_category = privilege_resource_category;
        this.privilege_resource_query = privilege_resource_query;
        this.privilege_resource_edit = privilege_resource_edit;
        this.privilege_resource_delete = privilege_resource_delete;
        this.privilege_resource_audit = privilege_resource_audit;
        this.privilege_resource_invalid = privilege_resource_invalid;
        this.privilege_resource_re_audit = privilege_resource_re_audit;
        this.privilege_resource_print = privilege_resource_print;
        this.privilege_resource_export = privilege_resource_export;
    }
    @Generated(hash = 826487845)
    public PrivilegeResourceNew() {
    }
    public Long getPrivilege_resource_key() {
        return this.privilege_resource_key;
    }
    public void setPrivilege_resource_key(Long privilege_resource_key) {
        this.privilege_resource_key = privilege_resource_key;
    }
    public String getPrivilege_resource_name() {
        return this.privilege_resource_name;
    }
    public void setPrivilege_resource_name(String privilege_resource_name) {
        this.privilege_resource_name = privilege_resource_name;
    }
    public String getPrivilege_resource_category() {
        return this.privilege_resource_category;
    }
    public void setPrivilege_resource_category(String privilege_resource_category) {
        this.privilege_resource_category = privilege_resource_category;
    }
    public boolean getPrivilege_resource_query() {
        return this.privilege_resource_query;
    }
    public void setPrivilege_resource_query(boolean privilege_resource_query) {
        this.privilege_resource_query = privilege_resource_query;
    }
    public boolean getPrivilege_resource_edit() {
        return this.privilege_resource_edit;
    }
    public void setPrivilege_resource_edit(boolean privilege_resource_edit) {
        this.privilege_resource_edit = privilege_resource_edit;
    }
    public boolean getPrivilege_resource_delete() {
        return this.privilege_resource_delete;
    }
    public void setPrivilege_resource_delete(boolean privilege_resource_delete) {
        this.privilege_resource_delete = privilege_resource_delete;
    }
    public boolean getPrivilege_resource_audit() {
        return this.privilege_resource_audit;
    }
    public void setPrivilege_resource_audit(boolean privilege_resource_audit) {
        this.privilege_resource_audit = privilege_resource_audit;
    }
    public boolean getPrivilege_resource_invalid() {
        return this.privilege_resource_invalid;
    }
    public void setPrivilege_resource_invalid(boolean privilege_resource_invalid) {
        this.privilege_resource_invalid = privilege_resource_invalid;
    }
    public boolean getPrivilege_resource_re_audit() {
        return this.privilege_resource_re_audit;
    }
    public void setPrivilege_resource_re_audit(
            boolean privilege_resource_re_audit) {
        this.privilege_resource_re_audit = privilege_resource_re_audit;
    }
    public boolean getPrivilege_resource_print() {
        return this.privilege_resource_print;
    }
    public void setPrivilege_resource_print(boolean privilege_resource_print) {
        this.privilege_resource_print = privilege_resource_print;
    }
    public boolean getPrivilege_resource_export() {
        return this.privilege_resource_export;
    }
    public void setPrivilege_resource_export(boolean privilege_resource_export) {
        this.privilege_resource_export = privilege_resource_export;
    }


}
