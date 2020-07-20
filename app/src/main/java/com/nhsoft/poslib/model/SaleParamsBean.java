package com.nhsoft.poslib.model;

import com.nhsoft.poslib.libconfig.LibConfig;

/**
 * Created by Iverson on 2018/12/15 12:04 PM
 * 此类用于：前台参数bean
 */
public class SaleParamsBean {

    private boolean canSaleZeroPriceItem; //前台允许销售单价位0的商品
    private boolean canChangeAmountForWeightItem;//称重商品不允许修改数量
    private boolean integerOnlyForNormalItem; //非称重商品不允许输入小数
    private String  roundType = LibConfig.S_ROUND_HALF; //舍零方式
    private String  roundTo = LibConfig.S_PRECISION_FEN ; //精确到 角 分
    private boolean enableWeighItemRound;
    private String prefixOfBarCode;
    private boolean ModifyPointActive;
    private String  ModifyPointRange;
    private boolean PointPolicyActive;
    private boolean PointBirthdayActive;
    private float   PointBirthdayProp;
    private boolean PointSpecialItemActive;
    private boolean enableUserAuthorization;//前台销售启用特殊权限临时授权
    private boolean returnNeedOrderNo;//退货必须要原单号
    private boolean canReturnPolicyItem;//是否可以退促销商品
    private boolean operatorAsDefaultSaleman;//默认销售员为前台收银员
    private boolean needSaleman;//
    private boolean EnableCashRound;//
    private boolean shiftEndNeedInputActualCash;//收款交班时需输入本班次收款现金
    private boolean ChangePriceItemShowDialog;//议价商品录入直接跳出议价窗体

    private String prefixOfWeightItem;//开始识别号
    private String precisionOfWeight;//条码重量精度
    private String precisionOfMoney;//条码金额精度
    private String lengthOfWeight;//重量长度
    private String  lengthOfMoney;//重量长度
    private boolean chooseWeightOutBar;//条码设置
    private int chooseBarType;//条码设置
    private boolean cancelItemNeedNote;//前台退货需要强制填写说明
    private boolean consumeNeedPassword;//卡消费启用密码验证
    private boolean cashPaymentNeedDiscount;
    private boolean posPrintExtraOrderNo;//外部流水号
    private boolean enableCardOrPosClient;//会员不与客户同用
    private boolean printMergePosItem; //打印是否合并
    private boolean hangOrderNeedSaleman; //挂单需要销售员
    private boolean allowTicketSelect;// 消费劵批量选择
    private Boolean enableBranchCloseTime;//营业日截止时间
    private boolean enableCardPayDiscount; //是否启用卡支付折扣
    private boolean disable_cancel_deposit_with_coupon ; //不允许赠券已核销的存款单退款
    private boolean showDiscount;//展示会员优惠金额 XML节点 ：ShowDiscount
    private int showDiscountCardType; //展示会员优惠卡类型 XML节点 ：ShowDiscountCardType
    private long showDiscountLevel;//展示会员优惠会员等级 XML节点 ：ShowDiscountLevel
    private int showDiscountType; //0:支付折扣/价格级别 1:消费折扣/价格级别 XML节点 ：ShowDiscountType
    private boolean enableDiscountShare; //是否启用消费券分摊
    private boolean firstDeliverCardNoPay;//第一次换卡是否收取费用


    public boolean isFirstDeliverCardNoPay() {
        return firstDeliverCardNoPay;
    }

    public void setFirstDeliverCardNoPay(boolean firstDeliverCardNoPay) {
        this.firstDeliverCardNoPay = firstDeliverCardNoPay;
    }

    public boolean isEnableDiscountShare() {
        return enableDiscountShare;
    }

    public void setEnableDiscountShare(boolean enableDiscountShare) {
        this.enableDiscountShare = enableDiscountShare;
    }

    public boolean isDisable_cancel_deposit_with_coupon() {
        return disable_cancel_deposit_with_coupon;
    }

    public void setDisable_cancel_deposit_with_coupon(boolean disable_cancel_deposit_with_coupon) {
        this.disable_cancel_deposit_with_coupon = disable_cancel_deposit_with_coupon;
    }

    public boolean isEnableCardPayDiscount() {
        return enableCardPayDiscount;
    }

    public void setEnableCardPayDiscount(boolean enableCardPayDiscount) {
        this.enableCardPayDiscount = enableCardPayDiscount;
    }

    public Boolean getEnableBranchCloseTime() {
        return enableBranchCloseTime;
    }

    public void setEnableBranchCloseTime(Boolean enableBranchCloseTime) {
        this.enableBranchCloseTime = enableBranchCloseTime;
    }

    public boolean isAllowTicketSelect() {
        return allowTicketSelect;
    }

    public void setAllowTicketSelect(boolean allowTicketSelect) {
        this.allowTicketSelect = allowTicketSelect;
    }

    public boolean isHangOrderNeedSaleman() {
        return hangOrderNeedSaleman;
    }

    public void setHangOrderNeedSaleman(boolean hangOrderNeedSaleman) {
        this.hangOrderNeedSaleman = hangOrderNeedSaleman;
    }



    public boolean isPrintMergePosItem() {
        return printMergePosItem;
    }

    public void setPrintMergePosItem(boolean printMergePosItem) {
        this.printMergePosItem = printMergePosItem;
    }
    public boolean isEnableCardOrPosClient() {
        return enableCardOrPosClient;
    }

    public void setEnableCardOrPosClient(boolean enableCardOrPosClient) {
        this.enableCardOrPosClient = enableCardOrPosClient;
    }

    public boolean isPosPrintExtraOrderNo() {
        return posPrintExtraOrderNo;
    }

    public void setPosPrintExtraOrderNo(boolean posPrintExtraOrderNo) {
        this.posPrintExtraOrderNo = posPrintExtraOrderNo;
    }



    public boolean isCashPaymentNeedDiscount() {
        return cashPaymentNeedDiscount;
    }

    public void setCashPaymentNeedDiscount(boolean cashPaymentNeedDiscount) {
        this.cashPaymentNeedDiscount = cashPaymentNeedDiscount;
    }

    public boolean isConsumeNeedPassword() {
        return consumeNeedPassword;
    }

    public void setConsumeNeedPassword(boolean consumeNeedPassword) {
        this.consumeNeedPassword = consumeNeedPassword;
    }

    public boolean isCancelItemNeedNote() {
        return cancelItemNeedNote;
    }

    public void setCancelItemNeedNote(boolean cancelItemNeedNote) {
        this.cancelItemNeedNote = cancelItemNeedNote;
    }


    public String getPrefixOfWeightItem() {
        return prefixOfWeightItem == null ? "" : prefixOfWeightItem;
    }

    public void setPrefixOfWeightItem(String prefixOfWeightItem) {
        this.prefixOfWeightItem = prefixOfWeightItem;
    }

    public String getPrecisionOfWeight() {
        return precisionOfWeight;
    }

    public void setPrecisionOfWeight(String precisionOfWeight) {
        this.precisionOfWeight = precisionOfWeight;
    }

    public String getPrecisionOfMoney() {
        return precisionOfMoney;
    }

    public void setPrecisionOfMoney(String precisionOfMoney) {
        this.precisionOfMoney = precisionOfMoney;
    }


    public String getLengthOfMoney() {
        return lengthOfMoney;
    }

    public void setLengthOfMoney(String lengthOfMoney) {
        this.lengthOfMoney = lengthOfMoney;
    }

    public String getLengthOfWeight() {
        return lengthOfWeight;
    }

    public void setLengthOfWeight(String lengthOfWeight) {
        this.lengthOfWeight = lengthOfWeight;
    }

    public boolean isChooseWeightOutBar() {
        return chooseWeightOutBar;
    }

    public void setChooseWeightOutBar(boolean chooseWeightOutBar) {
        this.chooseWeightOutBar = chooseWeightOutBar;
    }

    public boolean isChangePriceItemShowDialog() {
        return ChangePriceItemShowDialog;
    }

    public void setChangePriceItemShowDialog(boolean changePriceItemShowDialog) {
        this.ChangePriceItemShowDialog = changePriceItemShowDialog;
    }

    public int getChooseBarType() {
        return chooseBarType;
    }

    public void setChooseBarType(int chooseBarType) {
        this.chooseBarType = chooseBarType;
    }


    public boolean isShiftEndNeedInputActualCash() {
        return shiftEndNeedInputActualCash;
    }

    public void setShiftEndNeedInputActualCash(boolean shiftEndNeedInputActualCash) {
        this.shiftEndNeedInputActualCash = shiftEndNeedInputActualCash;
    }

    public boolean isEnableCashRound() {
        return EnableCashRound;
    }

    public void setEnableCashRound(boolean enableCashRound) {
        EnableCashRound = enableCashRound;
    }

    public boolean isOperatorAsDefaultSaleman() {
        return operatorAsDefaultSaleman;
    }

    public void setOperatorAsDefaultSaleman(boolean operatorAsDefaultSaleman) {
        this.operatorAsDefaultSaleman = operatorAsDefaultSaleman;
    }

    public boolean isNeedSaleman() {
        return needSaleman;
    }

    public void setNeedSaleman(boolean needSaleman) {
        this.needSaleman = needSaleman;
    }

    public boolean isEnableUserAuthorization() {
        return enableUserAuthorization;
    }

    public void setEnableUserAuthorization(boolean enableUserAuthorization) {
        this.enableUserAuthorization = enableUserAuthorization;
    }

    public boolean isModifyPointActive() {
        return ModifyPointActive;
    }

    public void setModifyPointActive(boolean modifyPointActive) {
        ModifyPointActive = modifyPointActive;
    }

    public String getModifyPointRange() {
        return ModifyPointRange;
    }

    public void setModifyPointRange(String modifyPointRange) {
        ModifyPointRange = modifyPointRange;
    }

    public boolean isPointPolicyActive() {
        return PointPolicyActive;
    }

    public void setPointPolicyActive(boolean pointPolicyActive) {
        PointPolicyActive = pointPolicyActive;
    }

    public boolean isPointBirthdayActive() {
        return PointBirthdayActive;
    }

    public void setPointBirthdayActive(boolean pointBirthdayActive) {
        PointBirthdayActive = pointBirthdayActive;
    }

    public float getPointBirthdayProp() {
        return PointBirthdayProp;
    }

    public void setPointBirthdayProp(float pointBirthdayProp) {
        PointBirthdayProp = pointBirthdayProp;
    }

    public boolean isPointSpecialItemActive() {
        return PointSpecialItemActive;
    }

    public void setPointSpecialItemActive(boolean pointSpecialItemActive) {
        PointSpecialItemActive = pointSpecialItemActive;
    }
    public boolean isCanSaleZeroPriceItem() {
        return canSaleZeroPriceItem;
    }

    public void setCanSaleZeroPriceItem(boolean canSaleZeroPriceItem) {
        this.canSaleZeroPriceItem = canSaleZeroPriceItem;
    }

    public boolean isCanChangeAmountForWeightItem() {
        return canChangeAmountForWeightItem;
    }

    public void setCanChangeAmountForWeightItem(boolean canChangeAmountForWeightItem) {
        this.canChangeAmountForWeightItem = canChangeAmountForWeightItem;
    }

    public boolean isIntegerOnlyForNormalItem() {
        return integerOnlyForNormalItem;
    }

    public void setIntegerOnlyForNormalItem(boolean integerOnlyForNormalItem) {
        this.integerOnlyForNormalItem = integerOnlyForNormalItem;
    }

    public String getRoundType() {
        return roundType;
    }

    public void setRoundType(String roundType) {
        this.roundType = roundType;
    }

    public String getRoundTo() {
        return roundTo;
    }

    public void setRoundTo(String roundTo) {
        this.roundTo = roundTo;
    }

    public boolean isEnableWeighItemRound() {
        return enableWeighItemRound;
    }

    public void setEnableWeighItemRound(boolean enableWeighItemRound) {
        this.enableWeighItemRound = enableWeighItemRound;
    }


    public String getPrefixOfBarCode() {
        return prefixOfBarCode;
    }

    public void setPrefixOfBarCode(String prefixOfBarCode) {
        this.prefixOfBarCode = prefixOfBarCode;
    }


    public boolean isReturnNeedOrderNo() {
        return returnNeedOrderNo;
    }

    public void setReturnNeedOrderNo(boolean returnNeedOrderNo) {
        this.returnNeedOrderNo = returnNeedOrderNo;
    }


    public boolean isCanReturnPolicyItem() {
        return canReturnPolicyItem;
    }

    public void setCanReturnPolicyItem(boolean canReturnPolicyItem) {
        this.canReturnPolicyItem = canReturnPolicyItem;
    }

    public boolean isShowDiscount() {
        return showDiscount;
    }

    public void setShowDiscount(boolean showDiscount) {
        this.showDiscount = showDiscount;
    }

    public int getShowDiscountCardType() {
        return showDiscountCardType;
    }

    public void setShowDiscountCardType(int showDiscountCardType) {
        this.showDiscountCardType = showDiscountCardType;
    }

    public long getShowDiscountLevel() {
        return showDiscountLevel;
    }

    public void setShowDiscountLevel(long showDiscountLevel) {
        this.showDiscountLevel = showDiscountLevel;
    }

    public int getShowDiscountType() {
        return showDiscountType;
    }

    public void setShowDiscountType(int showDiscountType) {
        this.showDiscountType = showDiscountType;
    }
}
