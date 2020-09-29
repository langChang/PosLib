package com.nhsoft.poslib.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Iverson on 2019/1/9 2:00 PM
 * 此类用于：
 */
//@SerializedName("消费券列表")
public class CouponsXmlModel implements Serializable {



    @SerializedName("消费券代码")
    private String ticketCode;
    @SerializedName("消费券名称")
    private String couponsName;
    @SerializedName("是否启用")
    private String couponsIsAct;
    @SerializedName("消费券面值")
    private float  couponsValue;
    @SerializedName("积分兑券比例")
    private float  couponsPointBit;
    @SerializedName("积分兑券默认有效天数")
    private int    couponsPointDelayday;
    @SerializedName("消费券应用分类")
    private String couponsType;
    @SerializedName("抵用数量")
    private String couponsDiscountAmount;
    @SerializedName("消费券超额张数累加")
    private int    couponsAddPage;
    @SerializedName("消费券备注")
    private String couponsMemo;
    @SerializedName("消费券单独使用")
    private String couponsIsAlone;
    @SerializedName("促销特价商品不支持抵扣")
    private String couponsSupportPromotion;
    private boolean isNewVersion;
    @SerializedName("是否线上券")
    private boolean isOnline;
    @SerializedName("券中心活动明细ID")
    private Long  couponActionDetailId;

    public Long getCouponActionDetailId() {
        return couponActionDetailId;
    }

    public void setCouponActionDetailId(Long couponActionDetailId) {
        this.couponActionDetailId = couponActionDetailId;
    }

    public String getCouponsMemo() {
        return couponsMemo;
    }

    public void setCouponsMemo(String couponsMemo) {
        this.couponsMemo = couponsMemo;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }



    public boolean isNewVersion() {
        return isNewVersion;
    }

    public void setNewVersion(boolean newVersion) {
        isNewVersion = newVersion;
    }

    private boolean isAll;
    private String goodsCodeList;
    private String categoryCodeList;


    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }


    public String getGoodsCodeList() {
        return goodsCodeList;
    }

    public void setGoodsCodeList(String goodsCodeList) {
        this.goodsCodeList = goodsCodeList;
    }

    public String getCategoryCodeList() {
        return categoryCodeList;
    }

    public void setCategoryCodeList(String categoryCodeList) {
        this.categoryCodeList = categoryCodeList;
    }

    public boolean isAll() {
        return isAll;
    }

    public void setAll(boolean all) {
        isAll = all;
    }



    public String getCouponsSupportPayStyle() {
        return couponsSupportPayStyle;
    }

    public void setCouponsSupportPayStyle(String couponsSupportPayStyle) {
        this.couponsSupportPayStyle = couponsSupportPayStyle;
    }

    @SerializedName("支付方式")
    private String couponsSupportPayStyle;
    @SerializedName("消费券数量限制")
    private int    couponsMaxNumber;
    @SerializedName("消费券最低消费金额")
    private float  couponsLimitMoney;

    @SerializedName("促销商品不计入最低消费金额")
    private int moneyExceptPromotionItems;

    public int isMoneyExceptPromotionItems() {
        return moneyExceptPromotionItems;
    }

    public void setMoneyExceptPromotionItems(int moneyExceptPromotionItems) {
        this.moneyExceptPromotionItems = moneyExceptPromotionItems;
    }

    @SerializedName("商品类别")
    private List<CatetoryData> mCatetoryDataList;

    public List<CatetoryData> getmCatetoryDataList() {
        return mCatetoryDataList;
    }

    public void setmCatetoryDataList(List<CatetoryData> mCatetoryDataList) {
        this.mCatetoryDataList = mCatetoryDataList;
    }

    @SerializedName("AppliedBranch")
    private List<BranchXmlModel> mBranchList;

    public List<BranchXmlModel> getmBranchList() {
        return mBranchList;
    }

    public void setmBranchList(List<BranchXmlModel> mBranchList) {
        this.mBranchList = mBranchList;
    }

    public String getCouponsName() {
        return couponsName;
    }

    public void setCouponsName(String couponsName) {
        this.couponsName = couponsName;
    }

    public String getCouponsIsAct() {
        return couponsIsAct;
    }

    public void setCouponsIsAct(String couponsIsAct) {
        this.couponsIsAct = couponsIsAct;
    }

    public float getCouponsValue() {
        return couponsValue;
    }

    public void setCouponsValue(float couponsValue) {
        this.couponsValue = couponsValue;
    }

    public float getCouponsPointBit() {
        return couponsPointBit;
    }

    public void setCouponsPointBit(float couponsPointBit) {
        this.couponsPointBit = couponsPointBit;
    }

    public int getCouponsPointDelayday() {
        return couponsPointDelayday;
    }

    public void setCouponsPointDelayday(int couponsPointDelayday) {
        this.couponsPointDelayday = couponsPointDelayday;
    }

    public String getCouponsType() {
        return couponsType;
    }

    public void setCouponsType(String couponsType) {
        this.couponsType = couponsType;
    }

    public int getCouponsAddPage() {
        return couponsAddPage;
    }

    public void setCouponsAddPage(int couponsAddPae) {
        this.couponsAddPage = couponsAddPage;
    }

    public String getCouponsIsAlone() {
        return couponsIsAlone;
    }

    public void setCouponsIsAlone(String couponsIsAlone) {
        this.couponsIsAlone = couponsIsAlone;
    }

    public String getCouponsSupportPromotion() {
        return couponsSupportPromotion;
    }

    public void setCouponsSupportPromotion(String couponsSupportPromotion) {
        this.couponsSupportPromotion = couponsSupportPromotion;
    }

    public int getCouponsMaxNumber() {
        return couponsMaxNumber;
    }

    public void setCouponsMaxNumber(int couponsMaxNumber) {
        this.couponsMaxNumber = couponsMaxNumber;
    }

    public float getCouponsLimitMoney() {
        return couponsLimitMoney;
    }

    public void setCouponsLimitMoney(float couponsLimitMoney) {
        this.couponsLimitMoney = couponsLimitMoney;
    }




//    public static class BranchData implements Serializable {
//        private int    AppliedBranchNum;
//        private String AppliedBranchName;
//
//        public int getAppliedBranchNum() {
//            return AppliedBranchNum;
//        }
//
//        public void setAppliedBranchNum(int appliedBranchNum) {
//            AppliedBranchNum = appliedBranchNum;
//        }
//
//        public String getAppliedBranchName() {
//            return AppliedBranchName;
//        }
//
//        public void setAppliedBranchName(String appliedBranchName) {
//            AppliedBranchName = appliedBranchName;
//        }
//    }


    public static class CatetoryData {
        @SerializedName("商品类别名称")
        private String          catetoryName;
        @SerializedName("商品类别编码")
        private String          catetoryNumber;
        @SerializedName("折扣率")
        private float          catetoryDiacount;
        @SerializedName("商品")
        private List<GoodsData> goodsList;

        public String getCatetoryNumber() {
            return catetoryNumber;
        }

        public void setCatetoryNumber(String catetoryNumber) {
            this.catetoryNumber = catetoryNumber;
        }

        public float getCatetoryDiacount() {
            return catetoryDiacount;
        }

        public void setCatetoryDiacount(float catetoryDiacount) {
            this.catetoryDiacount = catetoryDiacount;
        }



        public String getCatetoryName() {
            return catetoryName;
        }

        public void setCatetoryName(String catetoryName) {
            this.catetoryName = catetoryName;
        }


        public List<GoodsData> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsData> goodsList) {
            this.goodsList = goodsList;
        }
    }


    public String getCouponsDiscountAmount() {
        return couponsDiscountAmount;
    }

    public void setCouponsDiscountAmount(String couponsDiscountAmount) {
        this.couponsDiscountAmount = couponsDiscountAmount;
    }



    public static class GoodsData implements Serializable {
        @SerializedName("商品名称")
        private String goodsName;
        @SerializedName("商品编号")
        private long   goodsItemNum;
        @SerializedName("商品代码")
        private String goodsItemBar;

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public long getGoodsItemNum() {
            return goodsItemNum;
        }

        public void setGoodsItemNum(long goodsItemNum) {
            this.goodsItemNum = goodsItemNum;
        }

        public String getGoodsItemBar() {
            return goodsItemBar;
        }

        public void setGoodsItemBar(String goodsItemBar) {
            this.goodsItemBar = goodsItemBar;
        }
    }
}
