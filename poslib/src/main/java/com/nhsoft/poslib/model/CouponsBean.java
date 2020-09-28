package com.nhsoft.poslib.model;

import java.util.List;

/**
 * Created by Iverson on 2019/1/7 3:25 PM
 * 此类用于：
 */
public class CouponsBean implements Cloneable {


    /**
     * ticket_send_detail_uuid : String||消费券标志 1
     * action_id : String||营销活动标志
     * ticket_send_detail_print_num : String||消费券券号 1
     * ticket_send_detail_value : BigDecimal||消费券面值 1
     * ticket_send_discount : BigDecimal||消费券折扣 1
     * ticket_send_detail_valid_date : String||消费券有效截止日期1
     * ticket_send_detail_type : String||消费券类型名称1
     * ticket_send_bar_code : String||消费券条码 1
     * ticket_send_detail_memo : String||消费券备注
     * ticket_category : String||消费券应用分类 1
     * ticket_send_detail_valid_start : String||消费券可用起始时间 1
     * ticket_send_detail_send_branch_name : String||消费券发放门店名称 1
     * ticket_limit_money : BigDecimal||最低消费金额（前台控制）1
     * ticket_limit_amount_loop : Boolean||超额张数累加（前台控制）1
     * ticket_used_self : Boolean||不允许和其它消费券同时使用（前台控制） 1
     * except_promotion_items : Boolean||促销特价商品不支持抵扣（前台控制）
     * ticket_limit_amount : Integer||使用限制张数（前台控制）
     */

    private String ticket_send_detail_uuid;
    private String action_id;
    private String ticket_send_detail_print_num;
    private float ticket_send_detail_value;
    private float ticket_send_discount;
    private String ticket_send_detail_valid_date;
    private String ticket_send_detail_type;
    private String ticket_send_bar_code;
    private String ticket_send_detail_memo;
    private String ticket_category;
    private String ticket_send_detail_valid_start;
    private String ticket_send_detail_send_branch_name;
    private float ticket_limit_money;
    private boolean ticket_limit_amount_loop;
    private boolean ticket_used_self;
    private float residueMoney;
    private boolean except_promotion_items;
    private int     ticket_limit_amount;
    private boolean isDestruction = false;
    private float ticket_max_use_money;
    private float coupons_discount_amount; //抵扣数量
    private int total_calculate_limit_amount; //计算出来的最大抵扣张数


    public float getCoupons_discount_amount() {
        return coupons_discount_amount;
    }

    public void setCoupons_discount_amount(float coupons_discount_amount) {
        this.coupons_discount_amount = coupons_discount_amount;
    }

    public float getTicket_max_use_money() {
        return ticket_max_use_money;
    }

    public void setTicket_max_use_money(float ticket_max_use_money) {
        this.ticket_max_use_money = ticket_max_use_money;
    }



    public boolean isDestruction() {
        return isDestruction;
    }

    public void setDestruction(boolean destruction) {
        isDestruction = destruction;
    }



    public int getTicket_send_state_code() {
        return ticket_send_state_code;
    }

    public void setTicket_send_state_code(int ticket_send_state_code) {
        this.ticket_send_state_code = ticket_send_state_code;
    }

    private int                  ticket_send_state_code;
    private String               ticket_send_detail_branch;
    private List<String>         supportPaystyleList;
    private List<BranchXmlModel> branchXmlModels;


    public List<String> getSupportPaystyleList() {
        return supportPaystyleList;
    }

    public void setSupportPaystyleList(List<String> supportPaystyleList) {
        this.supportPaystyleList = supportPaystyleList;
    }

    public String getTicket_send_detail_uuid() {
        return ticket_send_detail_uuid;
    }

    public void setTicket_send_detail_uuid(String ticket_send_detail_uuid) {
        this.ticket_send_detail_uuid = ticket_send_detail_uuid;
    }

    public String getAction_id() {
        return action_id;
    }

    public void setAction_id(String action_id) {
        this.action_id = action_id;
    }

    public String getTicket_send_detail_print_num() {
        return ticket_send_detail_print_num;
    }

    public void setTicket_send_detail_print_num(String ticket_send_detail_print_num) {
        this.ticket_send_detail_print_num = ticket_send_detail_print_num;
    }

    public float getTicket_send_detail_value() {
        return ticket_send_detail_value;
    }

    public void setTicket_send_detail_value(float ticket_send_detail_value) {
        this.ticket_send_detail_value = ticket_send_detail_value;
        this.residueMoney = ticket_send_detail_value;
    }

    public float getTicket_send_discount() {
        return ticket_send_discount;
    }

    public void setTicket_send_discount(float ticket_send_discount) {
        this.ticket_send_discount = ticket_send_discount;
    }

    public String getTicket_send_detail_valid_date() {
        return ticket_send_detail_valid_date;
    }

    public void setTicket_send_detail_valid_date(String ticket_send_detail_valid_date) {
        this.ticket_send_detail_valid_date = ticket_send_detail_valid_date;
    }

    public String getTicket_send_detail_type() {
        return ticket_send_detail_type;
    }

    public void setTicket_send_detail_type(String ticket_send_detail_type) {
        this.ticket_send_detail_type = ticket_send_detail_type;
    }

    public String getTicket_send_bar_code() {
        return ticket_send_bar_code;
    }

    public void setTicket_send_bar_code(String ticket_send_bar_code) {
        this.ticket_send_bar_code = ticket_send_bar_code;
    }

    public String getTicket_send_detail_memo() {
        return ticket_send_detail_memo;
    }

    public void setTicket_send_detail_memo(String ticket_send_detail_memo) {
        this.ticket_send_detail_memo = ticket_send_detail_memo;
    }

    public String getTicket_category() {
        return ticket_category;
    }

    public void setTicket_category(String ticket_category) {
        this.ticket_category = ticket_category;
    }

    public String getTicket_send_detail_valid_start() {
        return ticket_send_detail_valid_start;
    }

    public void setTicket_send_detail_valid_start(String ticket_send_detail_valid_start) {
        this.ticket_send_detail_valid_start = ticket_send_detail_valid_start;
    }

    public String getTicket_send_detail_send_branch_name() {
        return ticket_send_detail_send_branch_name;
    }

    public void setTicket_send_detail_send_branch_name(String ticket_send_detail_send_branch_name) {
        this.ticket_send_detail_send_branch_name = ticket_send_detail_send_branch_name;
    }

    public float getTicket_limit_money() {
        return ticket_limit_money;
    }

    public void setTicket_limit_money(float ticket_limit_money) {
        this.ticket_limit_money = ticket_limit_money;
    }

    public boolean isTicket_limit_amount_loop() {
        return ticket_limit_amount_loop;
    }

    public void setTicket_limit_amount_loop(boolean ticket_limit_amount_loop) {
        this.ticket_limit_amount_loop = ticket_limit_amount_loop;
    }

    public boolean isTicket_used_self() {
        return ticket_used_self;
    }

    public void setTicket_used_self(boolean ticket_used_self) {
        this.ticket_used_self = ticket_used_self;
    }

    public boolean isExcept_promotion_items() {
        return except_promotion_items;
    }

    public void setExcept_promotion_items(boolean except_promotion_items) {
        this.except_promotion_items = except_promotion_items;
    }

    public int getTicket_limit_amount() {
        return ticket_limit_amount;
    }

    public void setTicket_limit_amount(int ticket_limit_amount) {
        this.ticket_limit_amount = ticket_limit_amount;
    }


    public String getTicket_send_detail_branch() {
        return ticket_send_detail_branch;
    }

    public void setTicket_send_detail_branch(String ticket_send_detail_branch) {
        this.ticket_send_detail_branch = ticket_send_detail_branch;
    }

    public float getResidueMoney() {
        return residueMoney;
    }

    public void setResidueMoney(float residueMoney) {
        this.residueMoney = residueMoney;
    }

    public List<BranchXmlModel> getBranchXmlModels() {
        return branchXmlModels;
    }

    public void setBranchXmlModels(List<BranchXmlModel> branchXmlModels) {
        this.branchXmlModels = branchXmlModels;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public int getTotal_calculate_limit_amount() {
        return total_calculate_limit_amount;
    }

    public void setTotal_calculate_limit_amount(int total_calculate_limit_amount) {
        this.total_calculate_limit_amount = total_calculate_limit_amount;
    }
}
