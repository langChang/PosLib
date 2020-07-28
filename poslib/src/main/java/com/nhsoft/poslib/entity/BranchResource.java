package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class BranchResource {

    /**
     * 获取所有分店资源属性
     *
     * systemBookCode : String
     * branchNum : Integer
     * branchResourceName : String
     * branchResourceParam : String
     */

    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;
    private String systemBookCode;
    private String branchNum;
    private String branchResourceName;
    private String branchResourceParam;

    @Generated(hash = 1628664842)
    public BranchResource(Long id, String systemBookCode, String branchNum,
            String branchResourceName, String branchResourceParam) {
        this.id = id;
        this.systemBookCode = systemBookCode;
        this.branchNum = branchNum;
        this.branchResourceName = branchResourceName;
        this.branchResourceParam = branchResourceParam;
    }

    @Generated(hash = 343302091)
    public BranchResource() {
    }

    public String getSystemBookCode() {
        return systemBookCode;
    }

    public void setSystemBookCode(String systemBookCode) {
        this.systemBookCode = systemBookCode;
    }

    public String getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(String branchNum) {
        this.branchNum = branchNum;
    }

    public String getBranchResourceName() {
        return branchResourceName;
    }

    public void setBranchResourceName(String branchResourceName) {
        this.branchResourceName = branchResourceName;
    }

    public String getBranchResourceParam() {
        return branchResourceParam;
    }

    public void setBranchResourceParam(String branchResourceParam) {
        this.branchResourceParam = branchResourceParam;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
