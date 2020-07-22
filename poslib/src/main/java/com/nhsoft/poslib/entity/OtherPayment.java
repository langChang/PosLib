package com.nhsoft.poslib.entity;

public class OtherPayment {


    /**
     * 从bookresource 表解析 得到其他收支
     *
     * FeeItemCode : 001
     * FeeItemName : 运费
     * FeeItemInOutFlag : -1
     */

    private String FeeItemCode;
    private String FeeItemName;
    private String FeeItemInOutFlag;

    public String getFeeItemCode() {
        return FeeItemCode;
    }

    public void setFeeItemCode(String FeeItemCode) {
        this.FeeItemCode = FeeItemCode;
    }

    public String getFeeItemName() {
        return FeeItemName;
    }

    public void setFeeItemName(String FeeItemName) {
        this.FeeItemName = FeeItemName;
    }

    public String getFeeItemInOutFlag() {
        return FeeItemInOutFlag;
    }

    public void setFeeItemInOutFlag(String FeeItemInOutFlag) {
        this.FeeItemInOutFlag = FeeItemInOutFlag;
    }
}
