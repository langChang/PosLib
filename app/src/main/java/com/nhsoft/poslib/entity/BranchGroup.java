package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class BranchGroup {

    /**
     * 获取门店分组
     *
     * branch_group_id : 44440000002
     * branch_num : 1
     * branch_group_type : 储值卡共享分组
     * branch_group_name : 默认共享分组
     */

    @Id
    @Property(nameInDb = "BRANCH_GROUP_ID")
    private String branch_group_id;
    private int branch_num;
    private String branch_group_type;
    private String branch_group_name;
    @Generated(hash = 656325273)
    public BranchGroup(String branch_group_id, int branch_num,
            String branch_group_type, String branch_group_name) {
        this.branch_group_id = branch_group_id;
        this.branch_num = branch_num;
        this.branch_group_type = branch_group_type;
        this.branch_group_name = branch_group_name;
    }
    @Generated(hash = 916113890)
    public BranchGroup() {
    }
    public String getBranch_group_id() {
        return this.branch_group_id;
    }
    public void setBranch_group_id(String branch_group_id) {
        this.branch_group_id = branch_group_id;
    }
    public int getBranch_num() {
        return this.branch_num;
    }
    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }
    public String getBranch_group_type() {
        return this.branch_group_type;
    }
    public void setBranch_group_type(String branch_group_type) {
        this.branch_group_type = branch_group_type;
    }
    public String getBranch_group_name() {
        return this.branch_group_name == null ? "" : branch_group_name;
    }
    public void setBranch_group_name(String branch_group_name) {
        this.branch_group_name = branch_group_name;
    }

    
}
