package com.nhsoft.poslib.entity;

public class VipModifyRecord {


    /**
     * 根据会员查询修改记录（返回最近营业日的20条）
     *
     * card_user_register_cust_num : Integer||卡主键
     * system_book_code : String||账套号
     * branch_num : Integer||门店编号
     * card_user_register_content : String||卡操作记录xml格式
     * card_user_register_date : String||卡操作时间
     * shift_table_num : Integer||班次号
     * shift_table_bizday : String||交班日期(yyyyMMdd)
     * card_user_register_type : String||卡操作状态
     * card_user_register_memo : String||备注
     * card_user_register_operator : String||操作人
     */

    private String card_user_register_cust_num;
    private String system_book_code;
    private String branch_num;
    private String card_user_register_content;
    private String card_user_register_date;
    private String shift_table_num;
    private String shift_table_bizday;
    private String card_user_register_type;
    private String card_user_register_memo;
    private String card_user_register_operator;

    public String getCard_user_register_cust_num() {
        return card_user_register_cust_num;
    }

    public void setCard_user_register_cust_num(String card_user_register_cust_num) {
        this.card_user_register_cust_num = card_user_register_cust_num;
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

    public String getCard_user_register_content() {
        return card_user_register_content;
    }

    public void setCard_user_register_content(String card_user_register_content) {
        this.card_user_register_content = card_user_register_content;
    }

    public String getCard_user_register_date() {
        return card_user_register_date;
    }

    public void setCard_user_register_date(String card_user_register_date) {
        this.card_user_register_date = card_user_register_date;
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

    public String getCard_user_register_type() {
        return card_user_register_type;
    }

    public void setCard_user_register_type(String card_user_register_type) {
        this.card_user_register_type = card_user_register_type;
    }

    public String getCard_user_register_memo() {
        return card_user_register_memo;
    }

    public void setCard_user_register_memo(String card_user_register_memo) {
        this.card_user_register_memo = card_user_register_memo;
    }

    public String getCard_user_register_operator() {
        return card_user_register_operator;
    }

    public void setCard_user_register_operator(String card_user_register_operator) {
        this.card_user_register_operator = card_user_register_operator;
    }
}
