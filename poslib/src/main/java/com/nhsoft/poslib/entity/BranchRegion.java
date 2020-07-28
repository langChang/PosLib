package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class BranchRegion {

    /**
     * branch_region_num : 444400001
     * branch_region_code : 1
     * branch_region_name : 浙江
     */

    @Id
    @Property(nameInDb = "BRANCH_REGION_NUM")
    private Long branch_region_num;
    private String branch_region_code;
    private String branch_region_name;
    private int branch_num;


    @Generated(hash = 330357113)
    public BranchRegion(Long branch_region_num, String branch_region_code,
            String branch_region_name, int branch_num) {
        this.branch_region_num = branch_region_num;
        this.branch_region_code = branch_region_code;
        this.branch_region_name = branch_region_name;
        this.branch_num = branch_num;
    }

    @Generated(hash = 1428694473)
    public BranchRegion() {
    }


    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public Long getBranch_region_num() {
        return this.branch_region_num;
    }
    public void setBranch_region_num(Long branch_region_num) {
        this.branch_region_num = branch_region_num;
    }
    public String getBranch_region_code() {
        return this.branch_region_code;
    }
    public void setBranch_region_code(String branch_region_code) {
        this.branch_region_code = branch_region_code;
    }
    public String getBranch_region_name() {
        return this.branch_region_name;
    }
    public void setBranch_region_name(String branch_region_name) {
        this.branch_region_name = branch_region_name;
    }


}
