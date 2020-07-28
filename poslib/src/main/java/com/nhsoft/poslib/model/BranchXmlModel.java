package com.nhsoft.poslib.model;

import java.io.Serializable;

/**
 * Created by Iverson on 2019/1/12 11:46 AM
 * 此类用于：
 */
public class BranchXmlModel implements Serializable {
    private int    AppliedBranchNum;
    private String AppliedBranchName;

    public int getAppliedBranchNum() {
        return AppliedBranchNum;
    }

    public void setAppliedBranchNum(int appliedBranchNum) {
        AppliedBranchNum = appliedBranchNum;
    }

    public String getAppliedBranchName() {
        return AppliedBranchName;
    }

    public void setAppliedBranchName(String appliedBranchName) {
        AppliedBranchName = appliedBranchName;
    }



}
