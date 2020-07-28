package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class EmployeeEntity {
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

    private int branch_num;
    private int employee_num;
    private String employee_code;
    private String employee_name;
    private String employee_kind;
    private boolean employee_actived;

    @Generated(hash = 1925380761)
    public EmployeeEntity(int branch_num, int employee_num, String employee_code,
            String employee_name, String employee_kind, boolean employee_actived) {
        this.branch_num = branch_num;
        this.employee_num = employee_num;
        this.employee_code = employee_code;
        this.employee_name = employee_name;
        this.employee_kind = employee_kind;
        this.employee_actived = employee_actived;
    }

    @Generated(hash = 249963266)
    public EmployeeEntity() {
    }

    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public int getEmployee_num() {
        return employee_num;
    }

    public void setEmployee_num(int employee_num) {
        this.employee_num = employee_num;
    }

    public String getEmployee_code() {
        return employee_code;
    }

    public void setEmployee_code(String employee_code) {
        this.employee_code = employee_code;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_kind() {
        return employee_kind;
    }

    public void setEmployee_kind(String employee_kind) {
        this.employee_kind = employee_kind;
    }

    public boolean isEmployee_actived() {
        return employee_actived;
    }

    public void setEmployee_actived(boolean employee_actived) {
        this.employee_actived = employee_actived;
    }

    public boolean getEmployee_actived() {
        return this.employee_actived;
    }
}
