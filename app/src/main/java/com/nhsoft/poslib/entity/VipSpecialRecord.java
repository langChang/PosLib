package com.nhsoft.poslib.entity;

public class VipSpecialRecord {


    /**
     * card_user_num : Integer||卡主键
     * system_book_code : String||账套号
     * branch_num : Integer||门店编号
     * card_user_log_type : String||卡操作状态
     * card_user_log_operator : String||操作人
     * card_user_log_time : Date||操作时间
     * card_user_log_branch_name : String||操作门店名
     * card_user_log_memo : String||备注
     * card_user_log_context : String||详情
     * shift_table_num : Integer||班次号
     * shift_table_bizday : String||交班日期(yyyyMMdd)
     * card_user_revoke_money : BigDecimal||回收金额
     */

    private String card_user_num;
    private String system_book_code;
    private String branch_num;
    private String card_user_log_type;
    private String card_user_log_operator;
    private String card_user_log_time;
    private String card_user_log_branch_name;
    private String card_user_log_memo;
    private String card_user_log_context;
    private String shift_table_num;
    private String shift_table_bizday;
    private String card_user_revoke_money;

    public String getCard_user_num() {
        return card_user_num;
    }

    public void setCard_user_num(String card_user_num) {
        this.card_user_num = card_user_num;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public String getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(String branch_num) {
        this.branch_num = branch_num;
    }

    public String getCard_user_log_type() {
        return card_user_log_type;
    }

    public void setCard_user_log_type(String card_user_log_type) {
        this.card_user_log_type = card_user_log_type;
    }

    public String getCard_user_log_operator() {
        return card_user_log_operator;
    }

    public void setCard_user_log_operator(String card_user_log_operator) {
        this.card_user_log_operator = card_user_log_operator;
    }

    public String getCard_user_log_time() {
        return card_user_log_time;
    }

    public void setCard_user_log_time(String card_user_log_time) {
        this.card_user_log_time = card_user_log_time;
    }

    public String getCard_user_log_branch_name() {
        return card_user_log_branch_name;
    }

    public void setCard_user_log_branch_name(String card_user_log_branch_name) {
        this.card_user_log_branch_name = card_user_log_branch_name;
    }

    public String getCard_user_log_memo() {
        return card_user_log_memo;
    }

    public void setCard_user_log_memo(String card_user_log_memo) {
        this.card_user_log_memo = card_user_log_memo;
    }

    public String getCard_user_log_context() {
        return card_user_log_context;
    }

    public void setCard_user_log_context(String card_user_log_context) {
        this.card_user_log_context = card_user_log_context;
    }

    public String getShift_table_num() {
        return shift_table_num;
    }

    public void setShift_table_num(String shift_table_num) {
        this.shift_table_num = shift_table_num;
    }

    public String getShift_table_bizday() {
        return shift_table_bizday;
    }

    public void setShift_table_bizday(String shift_table_bizday) {
        this.shift_table_bizday = shift_table_bizday;
    }

    public String getCard_user_revoke_money() {
        return card_user_revoke_money;
    }

    public void setCard_user_revoke_money(String card_user_revoke_money) {
        this.card_user_revoke_money = card_user_revoke_money;
    }
}
