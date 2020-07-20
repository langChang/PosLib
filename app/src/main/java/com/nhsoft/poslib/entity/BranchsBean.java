package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class BranchsBean {
    @Id
    private Long id;
    private int branch_num;//门店编号
    private String branch_name;//门店名称
    private boolean branch_matrix_price_actived;
    private Long branchMessageId;//对应BranchMessage 的主键
    @Generated(hash = 346775694)
    public BranchsBean(Long id, int branch_num, String branch_name,
            boolean branch_matrix_price_actived, Long branchMessageId) {
        this.id = id;
        this.branch_num = branch_num;
        this.branch_name = branch_name;
        this.branch_matrix_price_actived = branch_matrix_price_actived;
        this.branchMessageId = branchMessageId;
    }
    @Generated(hash = 2081510601)
    public BranchsBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getBranch_num() {
        return this.branch_num;
    }
    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }
    public String getBranch_name() {
        return this.branch_name;
    }
    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }
    public boolean getBranch_matrix_price_actived() {
        return this.branch_matrix_price_actived;
    }
    public void setBranch_matrix_price_actived(
            boolean branch_matrix_price_actived) {
        this.branch_matrix_price_actived = branch_matrix_price_actived;
    }
    public Long getBranchMessageId() {
        return this.branchMessageId;
    }
    public void setBranchMessageId(Long branchMessageId) {
        this.branchMessageId = branchMessageId;
    }
}
