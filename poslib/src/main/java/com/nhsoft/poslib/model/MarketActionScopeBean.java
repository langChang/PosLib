package com.nhsoft.poslib.model;

import java.util.List;

/**
 * Created by Iverson on 2019/3/6 5:36 PM
 * 此类用于：
 */
public class MarketActionScopeBean {

    /**
     * action_money : 20
     * action_increase : true
     * branch_nums : [1]
     * payment_types : ["现金","消费券","签单","积分消费","储值卡","零钱包"]
     * action_type : 例外商品
     * most_count : 3
     * item_nums : [434401367,434401376,434401377,434401378,434401417,434401418]
     * item_types : ["201","202"]
     * item_type_names : 叶菜类,根茎类
     * other_item_nums : [434400509,434410231,434410232,434410242]
     * action_sms_type : 0
     * card_user_nums : []
     * card_user_type_codes : ["1","101","100","99"]
     */

    private float action_money;
    private boolean       action_increase;
    private String        action_type;
    private int           most_count;
    private String        item_type_names;
    private String        except_date;
    private int           action_sms_type;
    private boolean       first_action;
    private List<Integer> branch_nums;
    private List<String>  payment_types;
    private List<Integer> item_nums;
    private List<String>  item_types;
    private List<Integer> other_item_nums;
    private List<String>  card_user_type_codes;
    private List<VipLevelOffline> vip_level_offlines;
    private boolean only_join_in_once;

    public boolean isOnly_join_in_once() {
        return only_join_in_once;
    }

    public void setOnly_join_in_once(boolean only_join_in_once) {
        this.only_join_in_once = only_join_in_once;
    }

    public List<VipLevelOffline> getVip_level_offlines() {
        return vip_level_offlines;
    }

    public void setVip_level_offlines(List<VipLevelOffline> vip_level_offlines) {
        this.vip_level_offlines = vip_level_offlines;
    }



    public float getAction_money() {
        return action_money;
    }

    public void setAction_money(float action_money) {
        this.action_money = action_money;
    }

    public boolean isAction_increase() {
        return action_increase;
    }

    public void setAction_increase(boolean action_increase) {
        this.action_increase = action_increase;
    }

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public int getMost_count() {
        return most_count;
    }

    public void setMost_count(int most_count) {
        this.most_count = most_count;
    }

    public String getItem_type_names() {
        return item_type_names;
    }

    public void setItem_type_names(String item_type_names) {
        this.item_type_names = item_type_names;
    }

    public int getAction_sms_type() {
        return action_sms_type;
    }

    public void setAction_sms_type(int action_sms_type) {
        this.action_sms_type = action_sms_type;
    }

    public List<Integer> getBranch_nums() {
        return branch_nums;
    }

    public void setBranch_nums(List<Integer> branch_nums) {
        this.branch_nums = branch_nums;
    }

    public List<String> getPayment_types() {
        return payment_types;
    }

    public void setPayment_types(List<String> payment_types) {
        this.payment_types = payment_types;
    }

    public List<Integer> getItem_nums() {
        return item_nums;
    }

    public void setItem_nums(List<Integer> item_nums) {
        this.item_nums = item_nums;
    }

    public List<String> getItem_types() {
        return item_types;
    }

    public void setItem_types(List<String> item_types) {
        this.item_types = item_types;
    }

    public List<Integer> getOther_item_nums() {
        return other_item_nums;
    }

    public void setOther_item_nums(List<Integer> other_item_nums) {
        this.other_item_nums = other_item_nums;
    }

    public List<String> getCard_user_type_codes() {
        return card_user_type_codes;
    }

    public void setCard_user_type_codes(List<String> card_user_type_codes) {
        this.card_user_type_codes = card_user_type_codes;
    }
    public boolean isFirst_action() {
        return first_action;
    }

    public void setFirst_action(boolean first_action) {
        this.first_action = first_action;
    }


    public String getExcept_date() {
        return except_date;
    }

    public void setExcept_date(String except_dates) {
        this.except_date = except_dates;
    }

}
