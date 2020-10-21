package com.nhsoft.poslib.model;


import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ls on 2019/01/12
 * 此类用于：会员卡总的配置
 */

public class VipCardConfig implements Serializable {


    /**
     * 最低控制金额 : 0
     * 默认续卡费用 : 1
     * 是否可更改续卡费用 : 0
     * 默认换卡费用 : 1
     * 是否可更改换卡费用 : 0
     * 兑换积分 : 10
     * 换取储值 : 1
     * 强制银行卡输入 : 0
     * 强制省份证号码输入 : 0
     * 不允许修改存款金额 : 0
     * 不允许手工输入卡号 : 0
     * 存款金额基数 : 1
     * 是否可更改积分兑换值 : -1
     * 发卡强制用户信息输入 : 0
     * 初始化卡时内外码一致提示 : 0
     * 启用短信提示 : 0
     * 存款短信提示格式 : 尊敬的客户：您的会员卡(尾号[PrintNum])于[DepositDate]完成一笔存款，金额[DepositMoney]元。
     * 消费短信提示格式 : 尊敬的客户：您的会员卡(尾号[PrintNum])于[ConsumeDate]完成一笔消费，金额[ConsumeMoney]元。
     * 单笔存款限额 : 0
     * 终端存款限额 : 50
     * 启用会员卡消费抽奖 : -1
     * 幸运顾客中奖频率 : 3
     * 幸运顾客中奖最高金额 : 0
     * 幸运顾客中奖最低金额 : 0
     * 卡内余额限额 : 0
     * 抽奖启用时间 : 20170205T00:00:00
     * 会员卡信息不完整提醒 : -1
     * ConsumeChechRemoteMinMoney : 0
     * ReplaceControlLossDay : 0
     * 消费微信提醒 : -1
     * 存款微信提醒 : -1
     * 卡消费启用密码验证 : -1
     * CardDepositWarningMoney : 0
     * EnableCardChange : -1
     * CardChangeType : 小于1元
     * CardOnlineDepositMemo : 尊敬的客户尊敬的客户尊敬的客户尊敬的客户尊敬的客户尊敬的客户尊敬的客户尊敬的客户尊敬的客户尊敬的客户
     * RegisterInfoCardBalance : 200.00000000000000000
     * InputPhoneNeedSmsCheck : 0
     * POSNotReadCardPoint : 0
     * ICConsumePointNoCard : 0
     * DownlaodCardUser : 0
     * ReplaceCardNeedSmsCheck : 0
     * ChangePhoneNeedSmsCheck : 0
     * EnableCardAfterReg : -1
     * ChangePWNeedSmsCheck : 0
     * DeliverTimeNeedDeposit : 0
     * DisableCardOnlineEnroll : 0
     * RevokeNeedNoMoney : 0
     * EnableCardOnlineDeposit : -1
     * ConsumeNotAllowPhone : 0
     * DelieveNotAllowChangeCardType : -1
     * AliMemberDepositEnrollShop : 0
     * UnLossCardNeedSmsCheck : -1 //前台POS会员卡解挂启用短信验证
     * EnableCardTypeSettlementDiscount : -1
     * BanPrintPhoneQueryCard : 0
     */

    /**
     * private BigDecimal lowestMoney;// 最低控制金额
     * 	private BigDecimal relateCardFee;// 默认续卡费用
     * 	private Boolean changeRelateCardFee;// 是否可更改续卡费用
     * 	private BigDecimal changeCardFeeMoney;// 默认换卡费用
     * 	private Boolean isChangeCardFee;// 是否可更改换卡费用
     * 	private BigDecimal exchangePoint;// 兑换积分
     * 	private BigDecimal exchangeMoney;// 换取储值
     * 	private Boolean forceBank;// 强制银行卡输入
     * 	private Boolean forceID;// 强制省份证号码输入
     * 	private Boolean noEditMoney;// 不允许修改存款金额
     * 	private Boolean noInputByHand;// 不允许手工输入卡号
     * 	private BigDecimal baseMoney;// 存款金额基数
     * 	private Boolean isChangeExchangeMoney;// 是否可更改积分兑换值
     * 	private Boolean forceCustomeInfo;// 发卡强制用户信息输入
     * 	private Boolean initPwdEqual;// 初始化卡时内外码一致提示
     * 	private Boolean isSupportSms;// 启用短信提示
     * 	private String storeSmsFormate;// 存款短信提示格式
     * 	private String shopSmsFormate;// 消费短信提示格式
     * 	private BigDecimal maxSingleStore;// 单笔存款限额
     * 	private BigDecimal maxTerminal;// 终端存款限额
     * 	private Boolean isCardShopPresent;// 启用会员卡消费抽奖
     * 	private BigDecimal luckyCustomerRate;// 幸运顾客中奖频率
     * 	private BigDecimal luckyCustomerMaxMoney;// 幸运顾客中奖最高金额
     * 	private BigDecimal luckyCustomerMinMoney;// 幸运顾客中奖最低金额
     * 	private BigDecimal maxCardMoney;// 卡内余额限额
     * 	private Date prestentDate;// 抽奖启用时间
     * 	private Boolean isCardInfoBugNotice;// 会员卡信息不完整提醒
     * 	private BigDecimal ConsumeChechRemoteMinMoney;// ConsumeChechRemoteMinMoney
     * 	private Integer ReplaceControlLossDay;// 换卡挂失天数
     * 	private Boolean consumeNeedPassword;// 卡消费启用密码验证
     * 	private Boolean inputPhoneNeedSmsCheck;// 不带会员卡提供手机号码消费启用短信验证(仅限磁条卡)
     * 	private Boolean posNotReadCardPoint;//前台POS结账不读取积分余额
     * 	private Boolean isDepositWx;// 存款微信提醒
     * 	private Boolean isConsumeWx;// 消费微信提醒
     * 	private BigDecimal cardDepositWarningMoney;
     * 	private Boolean ICConsumePointNoCard;
     * 	private Boolean downlaodCardUser; //下载会员积分/折扣卡到本地
     * 	private Boolean replaceCardNeedSmsCheck;//前台换卡启用短信验证
     * 	private Boolean changePhoneNeedSmsCheck;//前台修改手机号码启用短信验证
     * 	private Boolean enableCardAfterReg; //电子卡注册后直接激活
     * 	private Boolean changePWNeedSmsCheck;//会员卡修改密码需要短信验证
     * 	private Boolean deliverTimeNeedDeposit;//发卡时间按首次存款时间记录
     * 	private Boolean enableCardChange;// 是否启用零钱包
     * 	private String cardChangeType;// 零钱包类型
     * 	private Boolean disableCardOnlineEnroll; //关闭电子卡注册
     * 	private Boolean revokeNeedNoMoney;  //不允许门店回收有余额储值卡；仅限制非配送中心门店
     * 	private Boolean enableCardOnlineDeposit;//电子卡启用在线充值
     * 	private String cardOnlineDepositMemo;//电子卡启用在线充值公告
     * 	private Boolean consumeNotAllowPhone;
     * 	private Boolean delieveNotAllowChangeCardType; 不允许改变卡类型，手机号进入默认电子卡
     * 	private Boolean aliMemberDepositEnrollShop; //支付宝版微会员卡存款记入发卡门店
     * 	private Boolean unLossCardNeedSmsCheck;//会员卡解挂短信验证
     * 	private Boolean enableCardTypeSettlementDiscount;//是否启用卡类型结算折扣率
     * 	private Boolean banPrintPhoneQueryCard;//不允许手工输入手机号查询会员卡
     * 	private BigDecimal registerInfoCardBalance;//卡内余额超过 XX 必须登记会员信息
     * 	private Boolean usePointNeedPsw;//使用积分需要密码验证
     */
    @SerializedName("最低控制金额")
    private String lowestCtrlMoney;
    @SerializedName("默认续卡费用")
    private String defaultContinueCardFee;
    @SerializedName("是否可更改续卡费用")
    private String isCanChangeContinueFee;
    @SerializedName("默认换卡费用")
    private String defaultChangeCardFee;
    @SerializedName("是否可更改换卡费用")
    private String isCanChangeReplaceCardFee;
    @SerializedName("强制银行卡输入")
    private String forceBankCardInput;
    @SerializedName("强制省份证号码输入")
    private String forceProvinceNumberInput;
    @SerializedName("不允许手工输入卡号")
    private String cannotInputCardNumberByHand;
    @SerializedName("存款金额基数")
    private String strangeBaseNumber;
    @SerializedName("是否可更改积分兑换值")
    private String isCanchangeIntegralToMoneyRate;
    @SerializedName("发卡强制用户信息输入")
    private String sendCardForceUserInputMessage;
    @SerializedName("初始化卡时内外码一致提示")
    private String initCardHintPasswrodBoth;
    @SerializedName("存款短信提示格式")
    private String strangeMoneyHintWithMessage;
    @SerializedName("消费短信提示格式")
    private String consumeUseMessageHint;
    @SerializedName("单笔存款限额")
    private String signalStrangeMoneyLimit;
    @SerializedName("终端存款限额")
    private String terminalStrangeMoneyLimit;
    @SerializedName("启用会员卡消费抽奖")
    private String useVipCardToConsumeLuck;
    @SerializedName("幸运顾客中奖频率")
    private String lucklyConsumerRate;
    @SerializedName("幸运顾客中奖最高金额")
    private String lucklyConsumerHightestMoney;
    @SerializedName("幸运顾客中奖最低金额")
    private String lucklyConsumeLowestMoney;
    @SerializedName("卡内余额限额")
    private String cardReaminMoneyLimit;
    @SerializedName("抽奖启用时间")
    private String luckTime;
    @SerializedName("会员卡信息不完整提醒")
    private String vipCardMessageNotComoleteHint;
    private String ConsumeChechRemoteMinMoney;
    private String ReplaceControlLossDay;
    @SerializedName("消费微信提醒")
    private String consumeWeChatHint;
    @SerializedName("卡消费启用密码验证")
    private String cardConsumeusePasswordCheck;
    private String CardDepositWarningMoney;
    private String EnableCardChange;
    private String CardChangeType;
    private String CardOnlineDepositMemo;
    private String RegisterInfoCardBalance;
    private String InputPhoneNeedSmsCheck;
    private String POSNotReadCardPoint;
    private String ICConsumePointNoCard;
    private String DownlaodCardUser;
    private String ReplaceCardNeedSmsCheck;
    private String ChangePhoneNeedSmsCheck;
    private String EnableCardAfterReg;
    private String ChangePWNeedSmsCheck;
    private String DeliverTimeNeedDeposit;
    private String DisableCardOnlineEnroll;
    private String RevokeNeedNoMoney;
    private String EnableCardOnlineDeposit;
    private String ConsumeNotAllowPhone;
    private String DelieveNotAllowChangeCardType;
    private String AliMemberDepositEnrollShop;
    private String UnLossCardNeedSmsCheck;
    private String EnableCardTypeSettlementDiscount;
    private String EnableCardPayDiscount; //是否启用卡的支付折扣
    private String CardConsumeDiscountType; //消费折扣类型。

    private String BanPrintPhoneQueryCard;
    @SerializedName("不允许修改存款金额")
    private String noEditMoney;
    @SerializedName("存款微信提醒")
    private String isDepositWx;
    @SerializedName("启用短信提示")
    private String isSupportSms;
    @SerializedName("兑换积分")
    private float exchangePoint;// 兑换积分
    @SerializedName("换取储值")
    private float exchangeMoney;// 换取储值
    private String UsePointNeedPsw;//使用积分需要密码验证
    private String FirstDeliverCardNoPay;//第一次发放实体卡不收取换卡费用
    private String EnableCardSmsCheck;

    private String CardInputUsePrint;
    private String CardInputUsePhone;
    private String CardSettleUsePrint;
    private String CardSettleUsePhone;


    public boolean isEnableCardSmsCheck() {
        return TextUtils.isEmpty(EnableCardSmsCheck) ? false : (!"0".equals(EnableCardSmsCheck));
    }

    public void setEnableCardSmsCheck(String enableCardSmsCheck) {
        this.EnableCardSmsCheck = enableCardSmsCheck;
    }

    private String PosReadCardHideInfo;//前台读卡不显示卡信息(余额) 0没勾显示 -1勾了不显示

    public boolean getPosReadCardHideInfo() {
        return PosReadCardHideInfo==null?false:(PosReadCardHideInfo.equals("0")?false:true);
    }

    public void setPosReadCardHideInfo(String posReadCardHideInfo) {
        PosReadCardHideInfo = posReadCardHideInfo;
    }

    public String getFirstDeliverCardNoPay() {
        return FirstDeliverCardNoPay;
    }

    public void setFirstDeliverCardNoPay(String firstDeliverCardNoPay) {
        FirstDeliverCardNoPay = firstDeliverCardNoPay;
    }

    public String getUsePointNeedPsw() {
        return UsePointNeedPsw;
    }

    public void setUsePointNeedPsw(String usePointNeedPsw) {
        UsePointNeedPsw = usePointNeedPsw;
    }

    public float getExchangePoint() {
        return exchangePoint;
    }

    public void setExchangePoint(float exchangePoint) {
        this.exchangePoint = exchangePoint;
    }

    public float getExchangeMoney() {
        return exchangeMoney;
    }

    public void setExchangeMoney(float exchangeMoney) {
        this.exchangeMoney = exchangeMoney;
    }

    public String getNoEditMoney() {
        return noEditMoney;
    }

    public void setNoEditMoney(String noEditMoney) {
        this.noEditMoney = noEditMoney;
    }

    public String getIsDepositWx() {
        return isDepositWx;
    }

    public void setIsDepositWx(String isDepositWx) {
        this.isDepositWx = isDepositWx;
    }

    public String getIsSupportSms() {
        return isSupportSms;
    }

    public void setIsSupportSms(String isSupportSms) {
        this.isSupportSms = isSupportSms;
    }

    public String getLowestCtrlMoney() {
        return lowestCtrlMoney;
    }

    public void setLowestCtrlMoney(String lowestCtrlMoney) {
        this.lowestCtrlMoney = lowestCtrlMoney;
    }

    public String getDefaultContinueCardFee() {
        return TextUtils.isEmpty(defaultContinueCardFee)?"0" : defaultContinueCardFee ;

    }

    public void setDefaultContinueCardFee(String defaultContinueCardFee) {
        this.defaultContinueCardFee = defaultContinueCardFee;
    }

    public String getIsCanChangeContinueFee() {
        return isCanChangeContinueFee;
    }

    public void setIsCanChangeContinueFee(String isCanChangeContinueFee) {
        this.isCanChangeContinueFee = isCanChangeContinueFee;
    }

    public String getDefaultChangeCardFee() {
        return TextUtils.isEmpty(defaultChangeCardFee)?"0" : defaultChangeCardFee ;
    }

    public void setDefaultChangeCardFee(String defaultChangeCardFee) {
        this.defaultChangeCardFee = defaultChangeCardFee;
    }

    public String getIsCanChangeReplaceCardFee() {
        return isCanChangeReplaceCardFee;
    }

    public void setIsCanChangeReplaceCardFee(String isCanChangeReplaceCardFee) {
        this.isCanChangeReplaceCardFee = isCanChangeReplaceCardFee;
    }
    public String getForceBankCardInput() {
        return forceBankCardInput == null ? "0" : forceBankCardInput;
    }

    public void setForceBankCardInput(String forceBankCardInput) {
        this.forceBankCardInput = forceBankCardInput;
    }

    public String getForceProvinceNumberInput() {
        return forceProvinceNumberInput;
    }

    public void setForceProvinceNumberInput(String forceProvinceNumberInput) {
        this.forceProvinceNumberInput = forceProvinceNumberInput;
    }

    public String getCannotInputCardNumberByHand() {
        return cannotInputCardNumberByHand;
    }

    public void setCannotInputCardNumberByHand(String cannotInputCardNumberByHand) {
        this.cannotInputCardNumberByHand = cannotInputCardNumberByHand;
    }

    public String getStrangeBaseNumber() {
        return strangeBaseNumber;
    }

    public void setStrangeBaseNumber(String strangeBaseNumber) {
        this.strangeBaseNumber = strangeBaseNumber;
    }

    public String getIsCanchangeIntegralToMoneyRate() {
        return isCanchangeIntegralToMoneyRate;
    }

    public void setIsCanchangeIntegralToMoneyRate(String isCanchangeIntegralToMoneyRate) {
        this.isCanchangeIntegralToMoneyRate = isCanchangeIntegralToMoneyRate;
    }

    public String getSendCardForceUserInputMessage() {
        return sendCardForceUserInputMessage;
    }

    public void setSendCardForceUserInputMessage(String sendCardForceUserInputMessage) {
        this.sendCardForceUserInputMessage = sendCardForceUserInputMessage;
    }

    public String getInitCardHintPasswrodBoth() {
        return initCardHintPasswrodBoth;
    }

    public void setInitCardHintPasswrodBoth(String initCardHintPasswrodBoth) {
        this.initCardHintPasswrodBoth = initCardHintPasswrodBoth;
    }

    public String getStrangeMoneyHintWithMessage() {
        return strangeMoneyHintWithMessage;
    }

    public void setStrangeMoneyHintWithMessage(String strangeMoneyHintWithMessage) {
        this.strangeMoneyHintWithMessage = strangeMoneyHintWithMessage;
    }

    public String getConsumeUseMessageHint() {
        return consumeUseMessageHint;
    }

    public void setConsumeUseMessageHint(String consumeUseMessageHint) {
        this.consumeUseMessageHint = consumeUseMessageHint;
    }

    public String getSignalStrangeMoneyLimit() {
        return signalStrangeMoneyLimit;
    }

    public void setSignalStrangeMoneyLimit(String signalStrangeMoneyLimit) {
        this.signalStrangeMoneyLimit = signalStrangeMoneyLimit;
    }

    public String getTerminalStrangeMoneyLimit() {
        return terminalStrangeMoneyLimit;
    }

    public void setTerminalStrangeMoneyLimit(String terminalStrangeMoneyLimit) {
        this.terminalStrangeMoneyLimit = terminalStrangeMoneyLimit;
    }

    public String getUseVipCardToConsumeLuck() {
        return useVipCardToConsumeLuck;
    }

    public void setUseVipCardToConsumeLuck(String useVipCardToConsumeLuck) {
        this.useVipCardToConsumeLuck = useVipCardToConsumeLuck;
    }

    public String getLucklyConsumerRate() {
        return lucklyConsumerRate;
    }

    public void setLucklyConsumerRate(String lucklyConsumerRate) {
        this.lucklyConsumerRate = lucklyConsumerRate;
    }

    public String getLucklyConsumerHightestMoney() {
        return lucklyConsumerHightestMoney;
    }

    public void setLucklyConsumerHightestMoney(String lucklyConsumerHightestMoney) {
        this.lucklyConsumerHightestMoney = lucklyConsumerHightestMoney;
    }

    public String getLucklyConsumeLowestMoney() {
        return lucklyConsumeLowestMoney;
    }

    public void setLucklyConsumeLowestMoney(String lucklyConsumeLowestMoney) {
        this.lucklyConsumeLowestMoney = lucklyConsumeLowestMoney;
    }

    public String getCardReaminMoneyLimit() {
        if(TextUtils.isEmpty(cardReaminMoneyLimit)){
            cardReaminMoneyLimit = "0";
        }
        if(!TextUtils.isEmpty(cardReaminMoneyLimit)){
            if(Float.parseFloat(cardReaminMoneyLimit) == 0){
                return "99999999";
            }
        }
        return cardReaminMoneyLimit;
    }

    public void setCardReaminMoneyLimit(String cardReaminMoneyLimit) {
        this.cardReaminMoneyLimit = cardReaminMoneyLimit;
    }

    public String getLuckTime() {
        return luckTime;
    }

    public void setLuckTime(String luckTime) {
        this.luckTime = luckTime;
    }

    public String getVipCardMessageNotComoleteHint() {
        return vipCardMessageNotComoleteHint;
    }

    public void setVipCardMessageNotComoleteHint(String vipCardMessageNotComoleteHint) {
        this.vipCardMessageNotComoleteHint = vipCardMessageNotComoleteHint;
    }

    public String getConsumeWeChatHint() {
        return consumeWeChatHint;
    }

    public void setConsumeWeChatHint(String consumeWeChatHint) {
        this.consumeWeChatHint = consumeWeChatHint;
    }

    public String getCardConsumeusePasswordCheck() {
        return cardConsumeusePasswordCheck;
    }

    public void setCardConsumeusePasswordCheck(String cardConsumeusePasswordCheck) {
        this.cardConsumeusePasswordCheck = cardConsumeusePasswordCheck;
    }

    public String getConsumeChechRemoteMinMoney() {
        return ConsumeChechRemoteMinMoney;
    }

    public void setConsumeChechRemoteMinMoney(String ConsumeChechRemoteMinMoney) {
        this.ConsumeChechRemoteMinMoney = ConsumeChechRemoteMinMoney;
    }

    public String getReplaceControlLossDay() {
        return ReplaceControlLossDay;
    }

    public void setReplaceControlLossDay(String ReplaceControlLossDay) {
        this.ReplaceControlLossDay = ReplaceControlLossDay;
    }

    public String getCardDepositWarningMoney() {
        return CardDepositWarningMoney;
    }

    public void setCardDepositWarningMoney(String CardDepositWarningMoney) {
        this.CardDepositWarningMoney = CardDepositWarningMoney;
    }

    public String getEnableCardChange() {
        return EnableCardChange == null ? "0" : EnableCardChange;
    }

    public void setEnableCardChange(String EnableCardChange) {
        this.EnableCardChange = EnableCardChange;
    }

    public String getCardChangeType() {
        return CardChangeType;
    }

    public void setCardChangeType(String CardChangeType) {
        this.CardChangeType = CardChangeType;
    }

    public String getCardOnlineDepositMemo() {
        return CardOnlineDepositMemo;
    }

    public void setCardOnlineDepositMemo(String CardOnlineDepositMemo) {
        this.CardOnlineDepositMemo = CardOnlineDepositMemo;
    }

    public String getRegisterInfoCardBalance() {
        return RegisterInfoCardBalance;
    }

    public void setRegisterInfoCardBalance(String RegisterInfoCardBalance) {
        this.RegisterInfoCardBalance = RegisterInfoCardBalance;
    }

    public String getInputPhoneNeedSmsCheck() {
        return InputPhoneNeedSmsCheck;
    }

    public void setInputPhoneNeedSmsCheck(String InputPhoneNeedSmsCheck) {
        this.InputPhoneNeedSmsCheck = InputPhoneNeedSmsCheck;
    }

    public String getPOSNotReadCardPoint() {
        return POSNotReadCardPoint;
    }

    public void setPOSNotReadCardPoint(String POSNotReadCardPoint) {
        this.POSNotReadCardPoint = POSNotReadCardPoint;
    }

    public String getICConsumePointNoCard() {
        return ICConsumePointNoCard;
    }

    public void setICConsumePointNoCard(String ICConsumePointNoCard) {
        this.ICConsumePointNoCard = ICConsumePointNoCard;
    }

    public String getDownlaodCardUser() {
        return DownlaodCardUser;
    }

    public void setDownlaodCardUser(String DownlaodCardUser) {
        this.DownlaodCardUser = DownlaodCardUser;
    }

    public String getReplaceCardNeedSmsCheck() {
        return ReplaceCardNeedSmsCheck;
    }

    public void setReplaceCardNeedSmsCheck(String ReplaceCardNeedSmsCheck) {
        this.ReplaceCardNeedSmsCheck = ReplaceCardNeedSmsCheck;
    }

    public String getChangePhoneNeedSmsCheck() {
        return ChangePhoneNeedSmsCheck;
    }

    public void setChangePhoneNeedSmsCheck(String ChangePhoneNeedSmsCheck) {
        this.ChangePhoneNeedSmsCheck = ChangePhoneNeedSmsCheck;
    }

    public String getEnableCardAfterReg() {
        return EnableCardAfterReg;
    }

    public void setEnableCardAfterReg(String EnableCardAfterReg) {
        this.EnableCardAfterReg = EnableCardAfterReg;
    }

    public String getChangePWNeedSmsCheck() {
        return ChangePWNeedSmsCheck;
    }

    public void setChangePWNeedSmsCheck(String ChangePWNeedSmsCheck) {
        this.ChangePWNeedSmsCheck = ChangePWNeedSmsCheck;
    }

    public String getDeliverTimeNeedDeposit() {
        return DeliverTimeNeedDeposit;
    }

    public void setDeliverTimeNeedDeposit(String DeliverTimeNeedDeposit) {
        this.DeliverTimeNeedDeposit = DeliverTimeNeedDeposit;
    }

    public String getDisableCardOnlineEnroll() {
        return DisableCardOnlineEnroll;
    }

    public void setDisableCardOnlineEnroll(String DisableCardOnlineEnroll) {
        this.DisableCardOnlineEnroll = DisableCardOnlineEnroll;
    }

    public String getRevokeNeedNoMoney() {
        return RevokeNeedNoMoney;
    }

    public void setRevokeNeedNoMoney(String RevokeNeedNoMoney) {
        this.RevokeNeedNoMoney = RevokeNeedNoMoney;
    }

    public String getEnableCardOnlineDeposit() {
        return EnableCardOnlineDeposit;
    }

    public void setEnableCardOnlineDeposit(String EnableCardOnlineDeposit) {
        this.EnableCardOnlineDeposit = EnableCardOnlineDeposit;
    }

    public String getConsumeNotAllowPhone() {
        return ConsumeNotAllowPhone;
    }

    public void setConsumeNotAllowPhone(String ConsumeNotAllowPhone) {
        this.ConsumeNotAllowPhone = ConsumeNotAllowPhone;
    }

    public String getDelieveNotAllowChangeCardType() {
        return DelieveNotAllowChangeCardType;
    }

    public void setDelieveNotAllowChangeCardType(String DelieveNotAllowChangeCardType) {
        this.DelieveNotAllowChangeCardType = DelieveNotAllowChangeCardType;
    }

    public String getAliMemberDepositEnrollShop() {
        return AliMemberDepositEnrollShop;
    }

    public void setAliMemberDepositEnrollShop(String AliMemberDepositEnrollShop) {
        this.AliMemberDepositEnrollShop = AliMemberDepositEnrollShop;
    }

    public String getUnLossCardNeedSmsCheck() {
        return UnLossCardNeedSmsCheck;
    }

    public void setUnLossCardNeedSmsCheck(String UnLossCardNeedSmsCheck) {
        this.UnLossCardNeedSmsCheck = UnLossCardNeedSmsCheck;
    }

    public String getEnableCardTypeSettlementDiscount() {
        return EnableCardTypeSettlementDiscount;
    }

    public void setEnableCardTypeSettlementDiscount(String EnableCardTypeSettlementDiscount) {
        this.EnableCardTypeSettlementDiscount = EnableCardTypeSettlementDiscount;
    }

    public String getBanPrintPhoneQueryCard() {
        return BanPrintPhoneQueryCard==null?"0":BanPrintPhoneQueryCard;
    }

    public void setBanPrintPhoneQueryCard(String BanPrintPhoneQueryCard) {
        this.BanPrintPhoneQueryCard = BanPrintPhoneQueryCard;
    }

    public boolean isEnableCardPayDiscount() {
        return EnableCardPayDiscount == null ? true : !(Integer.parseInt(EnableCardPayDiscount) == 0);
    }

    public void setEnableCardPayDiscount(String enableCardPayDiscount) {
        this.EnableCardPayDiscount = enableCardPayDiscount;
    }

    public boolean isCustomerDiscountType() {
        return CardConsumeDiscountType == null ? true : "身份等级".equals(CardConsumeDiscountType);
    }

    public void setCardConsumeDiscountType(String cardConsumeDiscountType) {
        CardConsumeDiscountType = cardConsumeDiscountType;
    }

    public String getCardInputUsePrint() {
        return CardInputUsePrint;
    }

    public void setCardInputUsePrint(String cardInputUsePrint) {
        CardInputUsePrint = cardInputUsePrint;
    }

    public String getCardInputUsePhone() {
        return CardInputUsePhone;
    }

    public void setCardInputUsePhone(String cardInputUsePhone) {
        CardInputUsePhone = cardInputUsePhone;
    }

    public String getCardSettleUsePrint() {
        return CardSettleUsePrint;
    }

    public void setCardSettleUsePrint(String cardSettleUsePrint) {
        CardSettleUsePrint = cardSettleUsePrint;
    }

    public String getCardSettleUsePhone() {
        return CardSettleUsePhone;
    }

    public void setCardSettleUsePhone(String cardSettleUsePhone) {
        CardSettleUsePhone = cardSettleUsePhone;
    }
}
