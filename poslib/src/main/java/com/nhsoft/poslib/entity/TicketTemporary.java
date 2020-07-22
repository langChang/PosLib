package com.nhsoft.poslib.entity;

public class TicketTemporary {

    /**
     * action_id : string
     * except_promotion_items : true
     * payment_types : string
     * ticket_category : string
     * ticket_limit_amount : 0
     * ticket_limit_amount_loop : true
     * ticket_limit_money : 0
     * ticket_max_use_money : 0
     * ticket_send_bar_code : string
     * ticket_send_branch_num : 0
     * ticket_send_detail_branch : string
     * ticket_send_detail_memo : string
     * ticket_send_detail_print_num : string
     * ticket_send_detail_send_branch_name : string
     * ticket_send_detail_type : string
     * ticket_send_detail_uuid : string
     * ticket_send_detail_valid_date : string
     * ticket_send_detail_valid_start : string
     * ticket_send_detail_value : 0
     * ticket_send_discount : 0
     * ticket_send_state_code : 0
     * ticket_used_self : true
     */

    private String action_id;
    private boolean except_promotion_items;
    private String payment_types;
    private String ticket_category;
    private int ticket_limit_amount;
    private boolean ticket_limit_amount_loop;
    private float ticket_limit_money;
    private float ticket_max_use_money;
    private String ticket_send_bar_code;
    private int ticket_send_branch_num;
    private String ticket_send_detail_branch;
    private String ticket_send_detail_memo;
    private String ticket_send_detail_print_num;
    private String ticket_send_detail_send_branch_name;
    private String ticket_send_detail_type;
    private String ticket_send_detail_uuid;
    private String ticket_send_detail_valid_date;
    private String ticket_send_detail_valid_start;
    private float ticket_send_detail_value;
    private float ticket_send_discount;
    private int ticket_send_state_code;
    private boolean ticket_used_self;

    public String getAction_id() {
        return action_id;
    }

    public void setAction_id(String action_id) {
        this.action_id = action_id;
    }

    public boolean isExcept_promotion_items() {
        return except_promotion_items;
    }

    public void setExcept_promotion_items(boolean except_promotion_items) {
        this.except_promotion_items = except_promotion_items;
    }

    public String getPayment_types() {
        return payment_types;
    }

    public void setPayment_types(String payment_types) {
        this.payment_types = payment_types;
    }

    public String getTicket_category() {
        return ticket_category;
    }

    public void setTicket_category(String ticket_category) {
        this.ticket_category = ticket_category;
    }

    public int getTicket_limit_amount() {
        return ticket_limit_amount;
    }

    public void setTicket_limit_amount(int ticket_limit_amount) {
        this.ticket_limit_amount = ticket_limit_amount;
    }

    public boolean isTicket_limit_amount_loop() {
        return ticket_limit_amount_loop;
    }

    public void setTicket_limit_amount_loop(boolean ticket_limit_amount_loop) {
        this.ticket_limit_amount_loop = ticket_limit_amount_loop;
    }

    public float getTicket_limit_money() {
        return ticket_limit_money;
    }

    public void setTicket_limit_money(float ticket_limit_money) {
        this.ticket_limit_money = ticket_limit_money;
    }

    public float getTicket_max_use_money() {
        return ticket_max_use_money;
    }

    public void setTicket_max_use_money(float ticket_max_use_money) {
        this.ticket_max_use_money = ticket_max_use_money;
    }

    public String getTicket_send_bar_code() {
        return ticket_send_bar_code;
    }

    public void setTicket_send_bar_code(String ticket_send_bar_code) {
        this.ticket_send_bar_code = ticket_send_bar_code;
    }

    public int getTicket_send_branch_num() {
        return ticket_send_branch_num;
    }

    public void setTicket_send_branch_num(int ticket_send_branch_num) {
        this.ticket_send_branch_num = ticket_send_branch_num;
    }

    public String getTicket_send_detail_branch() {
        return ticket_send_detail_branch;
    }

    public void setTicket_send_detail_branch(String ticket_send_detail_branch) {
        this.ticket_send_detail_branch = ticket_send_detail_branch;
    }

    public String getTicket_send_detail_memo() {
        return ticket_send_detail_memo;
    }

    public void setTicket_send_detail_memo(String ticket_send_detail_memo) {
        this.ticket_send_detail_memo = ticket_send_detail_memo;
    }

    public String getTicket_send_detail_print_num() {
        return ticket_send_detail_print_num;
    }

    public void setTicket_send_detail_print_num(String ticket_send_detail_print_num) {
        this.ticket_send_detail_print_num = ticket_send_detail_print_num;
    }

    public String getTicket_send_detail_send_branch_name() {
        return ticket_send_detail_send_branch_name;
    }

    public void setTicket_send_detail_send_branch_name(String ticket_send_detail_send_branch_name) {
        this.ticket_send_detail_send_branch_name = ticket_send_detail_send_branch_name;
    }

    public String getTicket_send_detail_type() {
        return ticket_send_detail_type;
    }

    public void setTicket_send_detail_type(String ticket_send_detail_type) {
        this.ticket_send_detail_type = ticket_send_detail_type;
    }

    public String getTicket_send_detail_uuid() {
        return ticket_send_detail_uuid;
    }

    public void setTicket_send_detail_uuid(String ticket_send_detail_uuid) {
        this.ticket_send_detail_uuid = ticket_send_detail_uuid;
    }

    public String getTicket_send_detail_valid_date() {
        return ticket_send_detail_valid_date;
    }

    public void setTicket_send_detail_valid_date(String ticket_send_detail_valid_date) {
        this.ticket_send_detail_valid_date = ticket_send_detail_valid_date;
    }

    public String getTicket_send_detail_valid_start() {
        return ticket_send_detail_valid_start;
    }

    public void setTicket_send_detail_valid_start(String ticket_send_detail_valid_start) {
        this.ticket_send_detail_valid_start = ticket_send_detail_valid_start;
    }

    public float getTicket_send_detail_value() {
        return ticket_send_detail_value;
    }

    public void setTicket_send_detail_value(float ticket_send_detail_value) {
        this.ticket_send_detail_value = ticket_send_detail_value;
    }

    public float getTicket_send_discount() {
        return ticket_send_discount;
    }

    public void setTicket_send_discount(float ticket_send_discount) {
        this.ticket_send_discount = ticket_send_discount;
    }

    public int getTicket_send_state_code() {
        return ticket_send_state_code;
    }

    public void setTicket_send_state_code(int ticket_send_state_code) {
        this.ticket_send_state_code = ticket_send_state_code;
    }

    public boolean isTicket_used_self() {
        return ticket_used_self;
    }

    public void setTicket_used_self(boolean ticket_used_self) {
        this.ticket_used_self = ticket_used_self;
    }
}
