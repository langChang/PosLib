package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 前台营业参数
 * 应用卡脱敏
 */
@Entity
public class DeskOperatingParameters {

    /**
     * hide_center_four_phone_num
     *
     * 用户手机号中间4位以*代替
     *
     * hide_center_three_card_num
     *
     * 用户卡号中间3位以*代替（仅卡号大于5位有效）
     *
     * hide_fame
     *
     * 用户姓名只显示姓，名以*代替
     */
    @Id
    private String id="ID";
    private boolean can_sale_zero_price_item;
    private boolean can_change_amount_for_weight_item;
    private boolean integer_only_for_normal_item;

    private boolean enable_cash_round;
    private boolean enable_weigh_item_round;
    private boolean enable_card_pay_discount;

    private boolean disable_cancel_deposit_with_coupon;
    private boolean hide_center_four_phone_num;

    private boolean hide_center_three_card_num;
    private boolean hide_fame;

    private String prefix_of_weight_item;
    private int precision_of_weight;
    private int precision_of_money;

    private int length_of_weight;
    private int choose_bar_type;
    private String round_type;
    private String round_to;

    @Generated(hash = 887740460)
    public DeskOperatingParameters(String id, boolean can_sale_zero_price_item,
            boolean can_change_amount_for_weight_item, boolean integer_only_for_normal_item,
            boolean enable_cash_round, boolean enable_weigh_item_round,
            boolean enable_card_pay_discount, boolean disable_cancel_deposit_with_coupon,
            boolean hide_center_four_phone_num, boolean hide_center_three_card_num,
            boolean hide_fame, String prefix_of_weight_item, int precision_of_weight,
            int precision_of_money, int length_of_weight, int choose_bar_type,
            String round_type, String round_to) {
        this.id = id;
        this.can_sale_zero_price_item = can_sale_zero_price_item;
        this.can_change_amount_for_weight_item = can_change_amount_for_weight_item;
        this.integer_only_for_normal_item = integer_only_for_normal_item;
        this.enable_cash_round = enable_cash_round;
        this.enable_weigh_item_round = enable_weigh_item_round;
        this.enable_card_pay_discount = enable_card_pay_discount;
        this.disable_cancel_deposit_with_coupon = disable_cancel_deposit_with_coupon;
        this.hide_center_four_phone_num = hide_center_four_phone_num;
        this.hide_center_three_card_num = hide_center_three_card_num;
        this.hide_fame = hide_fame;
        this.prefix_of_weight_item = prefix_of_weight_item;
        this.precision_of_weight = precision_of_weight;
        this.precision_of_money = precision_of_money;
        this.length_of_weight = length_of_weight;
        this.choose_bar_type = choose_bar_type;
        this.round_type = round_type;
        this.round_to = round_to;
    }
    @Generated(hash = 132209127)
    public DeskOperatingParameters() {
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public boolean getCan_sale_zero_price_item() {
        return this.can_sale_zero_price_item;
    }
    public void setCan_sale_zero_price_item(boolean can_sale_zero_price_item) {
        this.can_sale_zero_price_item = can_sale_zero_price_item;
    }
    public boolean getCan_change_amount_for_weight_item() {
        return this.can_change_amount_for_weight_item;
    }
    public void setCan_change_amount_for_weight_item(
            boolean can_change_amount_for_weight_item) {
        this.can_change_amount_for_weight_item = can_change_amount_for_weight_item;
    }
    public boolean getInteger_only_for_normal_item() {
        return this.integer_only_for_normal_item;
    }
    public void setInteger_only_for_normal_item(
            boolean integer_only_for_normal_item) {
        this.integer_only_for_normal_item = integer_only_for_normal_item;
    }
    public boolean getEnable_cash_round() {
        return this.enable_cash_round;
    }
    public void setEnable_cash_round(boolean enable_cash_round) {
        this.enable_cash_round = enable_cash_round;
    }
    public boolean getEnable_weigh_item_round() {
        return this.enable_weigh_item_round;
    }
    public void setEnable_weigh_item_round(boolean enable_weigh_item_round) {
        this.enable_weigh_item_round = enable_weigh_item_round;
    }
    public boolean getEnable_card_pay_discount() {
        return this.enable_card_pay_discount;
    }
    public void setEnable_card_pay_discount(boolean enable_card_pay_discount) {
        this.enable_card_pay_discount = enable_card_pay_discount;
    }
    public boolean getDisable_cancel_deposit_with_coupon() {
        return this.disable_cancel_deposit_with_coupon;
    }
    public void setDisable_cancel_deposit_with_coupon(
            boolean disable_cancel_deposit_with_coupon) {
        this.disable_cancel_deposit_with_coupon = disable_cancel_deposit_with_coupon;
    }
    public boolean getHide_center_four_phone_num() {
        return this.hide_center_four_phone_num;
    }
    public void setHide_center_four_phone_num(boolean hide_center_four_phone_num) {
        this.hide_center_four_phone_num = hide_center_four_phone_num;
    }
    public boolean getHide_center_three_card_num() {
        return this.hide_center_three_card_num;
    }
    public void setHide_center_three_card_num(boolean hide_center_three_card_num) {
        this.hide_center_three_card_num = hide_center_three_card_num;
    }
    public boolean getHide_fame() {
        return this.hide_fame;
    }
    public void setHide_fame(boolean hide_fame) {
        this.hide_fame = hide_fame;
    }
    public String getPrefix_of_weight_item() {
        return this.prefix_of_weight_item;
    }
    public void setPrefix_of_weight_item(String prefix_of_weight_item) {
        this.prefix_of_weight_item = prefix_of_weight_item;
    }
    public int getPrecision_of_weight() {
        return this.precision_of_weight;
    }
    public void setPrecision_of_weight(int precision_of_weight) {
        this.precision_of_weight = precision_of_weight;
    }
    public int getPrecision_of_money() {
        return this.precision_of_money;
    }
    public void setPrecision_of_money(int precision_of_money) {
        this.precision_of_money = precision_of_money;
    }
    public int getLength_of_weight() {
        return this.length_of_weight;
    }
    public void setLength_of_weight(int length_of_weight) {
        this.length_of_weight = length_of_weight;
    }
    public int getChoose_bar_type() {
        return this.choose_bar_type;
    }
    public void setChoose_bar_type(int choose_bar_type) {
        this.choose_bar_type = choose_bar_type;
    }
    public String getRound_type() {
        return this.round_type;
    }
    public void setRound_type(String round_type) {
        this.round_type = round_type;
    }
    public String getRound_to() {
        return this.round_to;
    }
    public void setRound_to(String round_to) {
        this.round_to = round_to;
    }
}
