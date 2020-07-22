package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class Employee {
    /**
     * 获取所有员工
     *
     * branch_num : 1
     * employee_num : 444499035
     * employee_code : 002
     * employee_name : 李昊威
     * employee_kind : 销售员
     * employee_actived : true
     */

    @Id(autoincrement = true)
    private Long id;
    private String system_book_code;
    private int branch_num;
    private int employee_num;
    private String employee_code;
    private String employee_name;
    private String employee_kind;
    private String employee_linktel;
    private boolean employee_actived;
    @Generated(hash = 2054031541)
    public Employee(Long id, String system_book_code, int branch_num,
            int employee_num, String employee_code, String employee_name,
            String employee_kind, String employee_linktel,
            boolean employee_actived) {
        this.id = id;
        this.system_book_code = system_book_code;
        this.branch_num = branch_num;
        this.employee_num = employee_num;
        this.employee_code = employee_code;
        this.employee_name = employee_name;
        this.employee_kind = employee_kind;
        this.employee_linktel = employee_linktel;
        this.employee_actived = employee_actived;
    }
    @Generated(hash = 202356944)
    public Employee() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSystem_book_code() {
        return this.system_book_code;
    }
    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }
    public int getBranch_num() {
        return this.branch_num;
    }
    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }
    public int getEmployee_num() {
        return this.employee_num;
    }
    public void setEmployee_num(int employee_num) {
        this.employee_num = employee_num;
    }
    public String getEmployee_code() {
        return this.employee_code;
    }
    public void setEmployee_code(String employee_code) {
        this.employee_code = employee_code;
    }
    public String getEmployee_name() {
        return this.employee_name;
    }
    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }
    public String getEmployee_kind() {
        return this.employee_kind;
    }
    public void setEmployee_kind(String employee_kind) {
        this.employee_kind = employee_kind;
    }
    public String getEmployee_linktel() {
        return this.employee_linktel;
    }
    public void setEmployee_linktel(String employee_linktel) {
        this.employee_linktel = employee_linktel;
    }
    public boolean getEmployee_actived() {
        return this.employee_actived;
    }
    public void setEmployee_actived(boolean employee_actived) {
        this.employee_actived = employee_actived;
    }





}
