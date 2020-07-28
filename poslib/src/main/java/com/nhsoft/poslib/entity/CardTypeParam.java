package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Iverson on 2020/4/8 11:57 AM
 * 此类用于：
 */
@Entity
public class CardTypeParam {

    /**
     * birth_discount : 0
     * card_min_balance : 0
     * card_pay_discount : 0
     * card_pay_discount_level : 0
     * card_user_type_image_id : string
     * card_user_type_image_path : string
     * deposit_basic : 0
     * disable_pay_discount : true
     * discount_without_coupon : true
     * point_rule : {"birthday_point_value":0,"discount_item_no_point":true,"enable_birthday_point":true,"rule_create_time":"string","rule_creator":"string","rule_id":0,"rule_money":0,"rule_name":"string","rule_type":"string","rule_value":0}
     * settlement_discount : 0
     * type_code : string
     * type_consume_rate : 0
     * type_default_days : 0
     * type_default_permanent : true
     * type_deposit_rate : 0
     * type_discount : 0
     * type_name : string
     * type_price_level : 0
     * type_printed_color : string
     * type_support_deposit : true
     * type_support_online_loss : true
     * type_support_point : true
     * type_valid : true
     * use_in_enroll_shop : true
     */

    @Id(autoincrement = true)
    private Long      id;
    private float     birth_discount;
    private float     card_min_balance;
    private float     card_pay_discount;
    private float     card_pay_discount_level;
    private String    card_user_type_image_id;
    private String    card_user_type_image_path;
    private float     deposit_basic;
    private boolean   disable_pay_discount;
    private boolean   discount_without_coupon;
    @Transient
    private PointRule point_rule;
    private float     settlement_discount;
    private String    type_code;
    private float     type_consume_rate;
    private float     type_default_days;
    private boolean   type_default_permanent;
    private float     type_deposit_rate;
    private float     type_discount;
    private String    type_name;
    private int       type_price_level;
    private String    type_printed_color;
    private boolean   type_support_deposit;
    private boolean   type_support_online_loss;
    private boolean   type_support_point;
    private boolean   type_valid;
    private boolean   use_in_enroll_shop;
    private Long      rule_id;


    @Generated(hash = 254780054)
    public CardTypeParam(Long id, float birth_discount, float card_min_balance, float card_pay_discount, float card_pay_discount_level, String card_user_type_image_id, String card_user_type_image_path, float deposit_basic,
                         boolean disable_pay_discount, boolean discount_without_coupon, float settlement_discount, String type_code, float type_consume_rate, float type_default_days, boolean type_default_permanent, float type_deposit_rate,
                         float type_discount, String type_name, int type_price_level, String type_printed_color, boolean type_support_deposit, boolean type_support_online_loss, boolean type_support_point, boolean type_valid, boolean use_in_enroll_shop,
                         Long rule_id) {
        this.id = id;
        this.birth_discount = birth_discount;
        this.card_min_balance = card_min_balance;
        this.card_pay_discount = card_pay_discount;
        this.card_pay_discount_level = card_pay_discount_level;
        this.card_user_type_image_id = card_user_type_image_id;
        this.card_user_type_image_path = card_user_type_image_path;
        this.deposit_basic = deposit_basic;
        this.disable_pay_discount = disable_pay_discount;
        this.discount_without_coupon = discount_without_coupon;
        this.settlement_discount = settlement_discount;
        this.type_code = type_code;
        this.type_consume_rate = type_consume_rate;
        this.type_default_days = type_default_days;
        this.type_default_permanent = type_default_permanent;
        this.type_deposit_rate = type_deposit_rate;
        this.type_discount = type_discount;
        this.type_name = type_name;
        this.type_price_level = type_price_level;
        this.type_printed_color = type_printed_color;
        this.type_support_deposit = type_support_deposit;
        this.type_support_online_loss = type_support_online_loss;
        this.type_support_point = type_support_point;
        this.type_valid = type_valid;
        this.use_in_enroll_shop = use_in_enroll_shop;
        this.rule_id = rule_id;
    }

    @Generated(hash = 1636734344)
    public CardTypeParam() {
    }


    public PointRule getPoint_rule() {
        return point_rule;
    }

    public void setPoint_rule(PointRule point_rule) {
        this.point_rule = point_rule;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getBirth_discount() {
        return this.birth_discount;
    }

    public void setBirth_discount(float birth_discount) {
        this.birth_discount = birth_discount;
    }

    public float getCard_min_balance() {
        return this.card_min_balance;
    }

    public void setCard_min_balance(float card_min_balance) {
        this.card_min_balance = card_min_balance;
    }

    public float getCard_pay_discount() {
        return this.card_pay_discount;
    }

    public void setCard_pay_discount(float card_pay_discount) {
        this.card_pay_discount = card_pay_discount;
    }

    public float getCard_pay_discount_level() {
        return this.card_pay_discount_level;
    }

    public void setCard_pay_discount_level(float card_pay_discount_level) {
        this.card_pay_discount_level = card_pay_discount_level;
    }

    public String getCard_user_type_image_id() {
        return this.card_user_type_image_id;
    }

    public void setCard_user_type_image_id(String card_user_type_image_id) {
        this.card_user_type_image_id = card_user_type_image_id;
    }

    public String getCard_user_type_image_path() {
        return this.card_user_type_image_path;
    }

    public void setCard_user_type_image_path(String card_user_type_image_path) {
        this.card_user_type_image_path = card_user_type_image_path;
    }

    public float getDeposit_basic() {
        return this.deposit_basic;
    }

    public void setDeposit_basic(float deposit_basic) {
        this.deposit_basic = deposit_basic;
    }

    public boolean getDisable_pay_discount() {
        return this.disable_pay_discount;
    }

    public void setDisable_pay_discount(boolean disable_pay_discount) {
        this.disable_pay_discount = disable_pay_discount;
    }

    public boolean getDiscount_without_coupon() {
        return this.discount_without_coupon;
    }

    public void setDiscount_without_coupon(boolean discount_without_coupon) {
        this.discount_without_coupon = discount_without_coupon;
    }

    public float getSettlement_discount() {
        return this.settlement_discount;
    }

    public void setSettlement_discount(float settlement_discount) {
        this.settlement_discount = settlement_discount;
    }

    public String getType_code() {
        return this.type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public float getType_consume_rate() {
        return this.type_consume_rate;
    }

    public void setType_consume_rate(float type_consume_rate) {
        this.type_consume_rate = type_consume_rate;
    }

    public float getType_default_days() {
        return this.type_default_days;
    }

    public void setType_default_days(float type_default_days) {
        this.type_default_days = type_default_days;
    }

    public boolean getType_default_permanent() {
        return this.type_default_permanent;
    }

    public void setType_default_permanent(boolean type_default_permanent) {
        this.type_default_permanent = type_default_permanent;
    }

    public float getType_deposit_rate() {
        return this.type_deposit_rate;
    }

    public void setType_deposit_rate(float type_deposit_rate) {
        this.type_deposit_rate = type_deposit_rate;
    }

    public float getType_discount() {
        return this.type_discount;
    }

    public void setType_discount(float type_discount) {
        this.type_discount = type_discount;
    }

    public String getType_name() {
        return this.type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public int getType_price_level() {
        return this.type_price_level;
    }

    public void setType_price_level(int type_price_level) {
        this.type_price_level = type_price_level;
    }

    public String getType_printed_color() {
        return this.type_printed_color;
    }

    public void setType_printed_color(String type_printed_color) {
        this.type_printed_color = type_printed_color;
    }

    public boolean getType_support_deposit() {
        return this.type_support_deposit;
    }

    public void setType_support_deposit(boolean type_support_deposit) {
        this.type_support_deposit = type_support_deposit;
    }

    public boolean getType_support_online_loss() {
        return this.type_support_online_loss;
    }

    public void setType_support_online_loss(boolean type_support_online_loss) {
        this.type_support_online_loss = type_support_online_loss;
    }

    public boolean getType_support_point() {
        return this.type_support_point;
    }

    public void setType_support_point(boolean type_support_point) {
        this.type_support_point = type_support_point;
    }

    public boolean getType_valid() {
        return this.type_valid;
    }

    public void setType_valid(boolean type_valid) {
        this.type_valid = type_valid;
    }

    public boolean getUse_in_enroll_shop() {
        return this.use_in_enroll_shop;
    }

    public void setUse_in_enroll_shop(boolean use_in_enroll_shop) {
        this.use_in_enroll_shop = use_in_enroll_shop;
    }

    public Long getRule_id() {
        return this.rule_id;
    }

    public void setRule_id(Long rule_id) {
        this.rule_id = rule_id;
    }


}
