package com.nhsoft.poslib.model;

/**
 * Created by Iverson on 2019-05-07 17:45
 * 此类用于：
 */
public class ClientUserBean {

    /**
     * client_fid : 004020990000008
     * branch_num : 99
     * client_name : Jellycal
     * client_code : cl007
     * client_pinyin : JELLYCAL
     * client_birth : 2019-07-23 13:43:11
     * client_phone : 18012340001
     * client_mobile :
     * client_type : 德意志
     * client_usual_discount : 1.0
     * client_price_level : 1
     * client_credit_limit : 200.0
     * client_credit_enable : true
     * client_check_sign : true
     * client_password : 123
     * client_settlement_model : 业务发生门店结算
     * client_credit_remind_type : 2
     */

    private String client_fid;
    private int     branch_num;
    private String  client_name;
    private String  client_code;
    private String  client_pinyin;
    private String  client_birth;
    private String  client_phone;
    private String  client_mobile;
    private String  client_type;
    private float  client_usual_discount;
    private int     client_price_level;
    private float  client_credit_limit;
    private boolean client_credit_enable;
    private boolean client_check_sign;
    private String  client_password;
    private String  client_settlement_model;
    private Integer     client_credit_remind_type;
    private float useMoney;

    public String getClient_fid() {
        return client_fid;
    }

    public void setClient_fid(String client_fid) {
        this.client_fid = client_fid;
    }

    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public String getClient_pinyin() {
        return client_pinyin;
    }

    public void setClient_pinyin(String client_pinyin) {
        this.client_pinyin = client_pinyin;
    }

    public String getClient_birth() {
        return client_birth;
    }

    public void setClient_birth(String client_birth) {
        this.client_birth = client_birth;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public String getClient_mobile() {
        return client_mobile;
    }

    public void setClient_mobile(String client_mobile) {
        this.client_mobile = client_mobile;
    }

    public String getClient_type() {
        return client_type;
    }

    public void setClient_type(String client_type) {
        this.client_type = client_type;
    }

    public float getClient_usual_discount() {
        return client_usual_discount;
    }

    public void setClient_usual_discount(float client_usual_discount) {
        this.client_usual_discount = client_usual_discount;
    }

    public int getClient_price_level() {
        return client_price_level;
    }

    public void setClient_price_level(int client_price_level) {
        this.client_price_level = client_price_level;
    }

    public float getClient_credit_limit() {
        return client_credit_limit;
    }

    public void setClient_credit_limit(float client_credit_limit) {
        this.client_credit_limit = client_credit_limit;
    }

    public boolean isClient_credit_enable() {
        return client_credit_enable;
    }

    public void setClient_credit_enable(boolean client_credit_enable) {
        this.client_credit_enable = client_credit_enable;
    }

    public boolean isClient_check_sign() {
        return client_check_sign;
    }

    public void setClient_check_sign(boolean client_check_sign) {
        this.client_check_sign = client_check_sign;
    }

    public String getClient_password() {
        return client_password;
    }

    public void setClient_password(String client_password) {
        this.client_password = client_password;
    }

    public String getClient_settlement_model() {
        return client_settlement_model;
    }

    public void setClient_settlement_model(String client_settlement_model) {
        this.client_settlement_model = client_settlement_model;
    }

    public Integer getClient_credit_remind_type() {
        return client_credit_remind_type;
    }

    public void setClient_credit_remind_type(Integer client_credit_remind_type) {
        this.client_credit_remind_type = client_credit_remind_type;
    }

    public float getUseMoney() {
        return useMoney;
    }

    public void setUseMoney(float useMoney) {
        this.useMoney = useMoney;
    }
}
