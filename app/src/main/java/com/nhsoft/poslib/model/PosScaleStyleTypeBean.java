package com.nhsoft.poslib.model;

/**
 * Created by Iverson on 2018/11/29 12:09 PM
 * 此类用于：读取出来的支付方式
 */
public class PosScaleStyleTypeBean implements Cloneable {
    private String paymentTypeCode;
    private String paymentTypeName;
    private String paymentTypeMemo;
    private String posPaymentType;
    private String paymentNeedCheck;
    private String posOrderPaymentType;
    private String posCardPaymentType;
    private boolean posPaymentEqualSource;
    private String posPayBranchs;
    private boolean isNoCanUse = true;//是否限制使用
    private boolean isAvailable = true;  //是否可用
    private boolean isCurrent = false;
    private boolean isGloableVip = false;//是否全局会员
    private boolean enableEjectCashBox;//是否开启钱箱


    public boolean isEnableEjectCashBox() {
        return enableEjectCashBox;
    }

    public void setEnableEjectCashBox(boolean enableEjectCashBox) {
        this.enableEjectCashBox = enableEjectCashBox;
    }




    public boolean isGloableVip() {
        return isGloableVip;
    }

    public void setGloableVip(boolean gloableVip) {
        isGloableVip = gloableVip;
    }




    public boolean isNoCanUse() {
        return isNoCanUse;
    }

    public void setNoCanUse(boolean noCanUse) {
        isNoCanUse = noCanUse;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(String paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    public String getPaymentTypeMemo() {
        return paymentTypeMemo;
    }

    public void setPaymentTypeMemo(String paymentTypeMemo) {
        this.paymentTypeMemo = paymentTypeMemo;
    }

    public String getPosPaymentType() {
        return posPaymentType;
    }

    public void setPosPaymentType(String posPaymentType) {
        this.posPaymentType = posPaymentType;
    }

    public String getPaymentNeedCheck() {
        return paymentNeedCheck;
    }

    public void setPaymentNeedCheck(String paymentNeedCheck) {
        this.paymentNeedCheck = paymentNeedCheck;
    }

    public String getPosOrderPaymentType() {
        return posOrderPaymentType;
    }

    public void setPosOrderPaymentType(String posOrderPaymentType) {
        this.posOrderPaymentType = posOrderPaymentType;
    }

    public String getPosCardPaymentType() {
        return posCardPaymentType;
    }

    public void setPosCardPaymentType(String posCardPaymentType) {
        this.posCardPaymentType = posCardPaymentType;
    }

    public boolean getPosPaymentEqualSource() {
        return posPaymentEqualSource;
    }

    public void setPosPaymentEqualSource(boolean posPaymentEqualSource) {
        this.posPaymentEqualSource = posPaymentEqualSource;
    }




    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public String getPosPayBranchs() {
        return posPayBranchs;
    }

    public void setPosPayBranchs(String posPayBranchs) {
        this.posPayBranchs = posPayBranchs;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

}
