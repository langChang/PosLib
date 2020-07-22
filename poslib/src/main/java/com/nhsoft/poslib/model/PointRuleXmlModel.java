package com.nhsoft.poslib.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2019/1/9 2:00 PM
 * 此类用于：
 */
//@SerializedName("消费券列表")
public class PointRuleXmlModel implements Serializable {

    private String                pointRuleName;
    private float                 counponsMoney;
    private float                 pointValue;
    private int                   isAlable;
    private String                otherSetting;
    private List<PointRuleDetail> pointRuleDetails = new ArrayList<>();

    public String getPointRuleName() {
        return pointRuleName;
    }

    public void setPointRuleName(String pointRuleName) {
        this.pointRuleName = pointRuleName;
    }

    public float getCounponsMoney() {
        return counponsMoney;
    }

    public void setCounponsMoney(float counponsMoney) {
        this.counponsMoney = counponsMoney;
    }

    public float getPointValue() {
        return pointValue;
    }

    public void setPointValue(float pointValue) {
        this.pointValue = pointValue;
    }

    public int getIsAlable() {
        return isAlable;
    }

    public void setIsAlable(int isAlable) {
        this.isAlable = isAlable;
    }

    public String getOtherSetting() {
        return otherSetting;
    }

    public void setOtherSetting(String otherSetting) {
        this.otherSetting = otherSetting;
    }

    public List<PointRuleDetail> getPointRuleDetails() {
        return pointRuleDetails;
    }

    public void setPointRuleDetails(List<PointRuleDetail> pointRuleDetails) {
        this.pointRuleDetails = pointRuleDetails;
    }

    public static class PointRuleDetail implements Serializable{
        @SerializedName("类别代码")
        private String catetoryCode;
        @SerializedName("类别名称")
        private String catetoryName;
        @SerializedName("消费金额")
        private float  couponsMoneyDetail;
        @SerializedName("积分值")
        private float  pointValueDetail;

        public String getCatetoryCode() {
            return catetoryCode;
        }

        public void setCatetoryCode(String catetoryCode) {
            this.catetoryCode = catetoryCode;
        }

        public String getCatetoryName() {
            return catetoryName;
        }

        public void setCatetoryName(String catetoryName) {
            this.catetoryName = catetoryName;
        }

        public float getCouponsMoneyDetail() {
            return couponsMoneyDetail;
        }

        public void setCouponsMoneyDetail(float couponsMoneyDetail) {
            this.couponsMoneyDetail = couponsMoneyDetail;
        }

        public float getPointValueDetail() {
            return pointValueDetail;
        }

        public void setPointValueDetail(float pointValueDetail) {
            this.pointValueDetail = pointValueDetail;
        }
    }
}
