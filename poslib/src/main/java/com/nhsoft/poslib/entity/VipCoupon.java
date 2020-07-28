package com.nhsoft.poslib.entity;

public class VipCoupon {


    /**
     * 优惠券详情
     *
     * ticket_send_detail_uuid : String||消费券标志
     * action_id : String||营销活动标志
     * ticket_send_detail_print_num : String||消费券券号
     * ticket_send_detail_value : BigDecimal||消费券面值
     * ticket_send_discount : BigDecimal||消费券折扣
     * ticket_send_detail_valid_date : String||消费券有效截止日期
     * ticket_send_detail_type : String||消费券类型名称
     * ticket_send_bar_code : String||消费券条码
     * ticket_send_detail_memo : String||消费券备注
     * ticket_category : String||消费券应用分类
     * ticket_send_detail_valid_start : String||消费券可用起始时间
     * ticket_send_detail_send_branch_name : String||消费券发放门店名称
     * ticket_limit_money : BigDecimal||最低消费金额（前台控制）
     * ticket_limit_amount_loop : Boolean||超额张数累加（前台控制）
     * ticket_used_self : Boolean||不允许和其它消费券同时使用（前台控制）
     * except_promotion_items : Boolean||促销特价商品不支持抵扣（前台控制）
     * ticket_limit_amount : Integer||使用限制张数（前台控制）
     */

    private String ticket_send_detail_uuid;
    private String action_id;
    private String ticket_send_detail_print_num;
    private String ticket_send_detail_value;
    private String ticket_send_discount;
    private String ticket_send_detail_valid_date;
    private String ticket_send_detail_type;
    private String ticket_send_bar_code;
    private String ticket_send_detail_memo;
    private String ticket_category;
    private String ticket_send_detail_valid_start;
    private String ticket_send_detail_send_branch_name;
    private String ticket_limit_money;
    private String ticket_limit_amount_loop;
    private String ticket_used_self;
    private String except_promotion_items;
    private String ticket_limit_amount;

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

    public String getTicket_send_detail_value() {
        return ticket_send_detail_value;
    }

    public void setTicket_send_detail_value(String ticket_send_detail_value) {
        this.ticket_send_detail_value = ticket_send_detail_value;
    }

    public String getTicket_send_discount() {
        return ticket_send_discount;
    }

    public void setTicket_send_discount(String ticket_send_discount) {
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

    public String getTicket_limit_money() {
        return ticket_limit_money;
    }

    public void setTicket_limit_money(String ticket_limit_money) {
        this.ticket_limit_money = ticket_limit_money;
    }

    public String getTicket_limit_amount_loop() {
        return ticket_limit_amount_loop;
    }

    public void setTicket_limit_amount_loop(String ticket_limit_amount_loop) {
        this.ticket_limit_amount_loop = ticket_limit_amount_loop;
    }

    public String getTicket_used_self() {
        return ticket_used_self;
    }

    public void setTicket_used_self(String ticket_used_self) {
        this.ticket_used_self = ticket_used_self;
    }

    public String getExcept_promotion_items() {
        return except_promotion_items;
    }

    public void setExcept_promotion_items(String except_promotion_items) {
        this.except_promotion_items = except_promotion_items;
    }

    public String getTicket_limit_amount() {
        return ticket_limit_amount;
    }

    public void setTicket_limit_amount(String ticket_limit_amount) {
        this.ticket_limit_amount = ticket_limit_amount;
    }
}
