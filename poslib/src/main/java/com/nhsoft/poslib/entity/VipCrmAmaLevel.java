package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * ama对应的会员名称
 * @Author ADMIN
 * @time 2020-04-07 10:04
 */

@Entity
public class VipCrmAmaLevel {

    /**
     * id : 208500000086
     * discount_type : 阶梯价
     * discount_without_coupon : false
     * price_level : 2
     * ruleid
     * price_discount : 0
     * point_rule : {"rule_id":208501000010,"rule_name":"卡存款积分","rule_creator":"管理员","rule_create_time":"2019-11-26 14:12:40","rule_type":"卡储值积分","rule_money":50,"rule_value":1,"discount_item_no_point":false}
     * rank : 0
     * level_name : 注册会员
     * need_pay : false
     * upgrade_by_growth : true
     * growth_value : 0
     */

    @Id
    private long              id;
    private String            discount_type;
    private boolean           discount_without_coupon;
    private int               price_level;
    private float             price_discount;
    private float             birth_discount;
//    @ToOne(joinProperty = "id")
    @Transient
    public  VipLevelPointRule point_rule;
    private int               rank;
    private String            level_name;
    private boolean           need_pay;
    private boolean           upgrade_by_growth;
    private int               growth_value;
    private String payment_types;






    @Generated(hash = 2006340928)
    public VipCrmAmaLevel(long id, String discount_type, boolean discount_without_coupon, int price_level, float price_discount, float birth_discount, int rank, String level_name, boolean need_pay,
            boolean upgrade_by_growth, int growth_value, String payment_types) {
        this.id = id;
        this.discount_type = discount_type;
        this.discount_without_coupon = discount_without_coupon;
        this.price_level = price_level;
        this.price_discount = price_discount;
        this.birth_discount = birth_discount;
        this.rank = rank;
        this.level_name = level_name;
        this.need_pay = need_pay;
        this.upgrade_by_growth = upgrade_by_growth;
        this.growth_value = growth_value;
        this.payment_types = payment_types;
    }

    @Generated(hash = 1505268889)
    public VipCrmAmaLevel() {
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getBirth_discount() {
        return birth_discount;
    }

    public void setBirth_discount(float birth_discount) {
        this.birth_discount = birth_discount;
    }



    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }

    public boolean isDiscount_without_coupon() {
        return discount_without_coupon;
    }

    public void setDiscount_without_coupon(boolean discount_without_coupon) {
        this.discount_without_coupon = discount_without_coupon;
    }

    public int getPrice_level() {
        return price_level;
    }

    public void setPrice_level(int price_level) {
        this.price_level = price_level;
    }

    public float getPrice_discount() {
        return price_discount;
    }

    public void setPrice_discount(float price_discount) {
        this.price_discount = price_discount;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public boolean isNeed_pay() {
        return need_pay;
    }

    public void setNeed_pay(boolean need_pay) {
        this.need_pay = need_pay;
    }

    public boolean isUpgrade_by_growth() {
        return upgrade_by_growth;
    }

    public void setUpgrade_by_growth(boolean upgrade_by_growth) {
        this.upgrade_by_growth = upgrade_by_growth;
    }

    public int getGrowth_value() {
        return growth_value;
    }

    public void setGrowth_value(int growth_value) {
        this.growth_value = growth_value;
    }

    public boolean getDiscount_without_coupon() {
        return this.discount_without_coupon;
    }

    public boolean getNeed_pay() {
        return this.need_pay;
    }

    public boolean getUpgrade_by_growth() {
        return this.upgrade_by_growth;
    }

    public String getPayment_types() {
        return this.payment_types;
    }

    public void setPayment_types(String payment_types) {
        this.payment_types = payment_types;
    }
}
