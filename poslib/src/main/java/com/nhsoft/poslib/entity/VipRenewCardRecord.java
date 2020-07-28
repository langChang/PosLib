package com.nhsoft.poslib.entity;

public class VipRenewCardRecord {


    /**
     * branch_num : Integer||门店编号
     * shift_table_num : Integer||班次号
     * shift_table_bizday : String||交班日期(yyyyMMdd)
     * card_user_num : String||卡主键
     * relat_card_new_deadline : String||续卡到最新日期（yyyy-MM-dd HH:mm:ss）
     * relat_card_operator : String||续卡操作人
     * relat_card_payment_type : Integer||续卡支付方式编码
     * relat_card_payment_type_name : String||续卡支付方式名称
     * relat_card_bank_name : String||续卡银行
     * relat_card_billref : String||参考单据号
     * relat_card_money : BigDecimal||续卡金额
     * relat_card_memo : String||续卡备注
     * account_bank_num : Integer||续卡电子银行
     * relat_card_machine : String||续卡设备号
     * system_book_code : String||账套号
     */

    private String branch_num;
    private String shift_table_num;
    private String shift_table_bizday;
    private String card_user_num;
    private String relat_card_new_deadline;
    private String relat_card_operator;
    private String relat_card_payment_type;
    private String relat_card_payment_type_name;
    private String relat_card_bank_name;
    private String relat_card_billref;
    private String relat_card_money;
    private String relat_card_memo;
    private String account_bank_num;
    private String relat_card_machine;
    private String system_book_code;

    public String getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(String branch_num) {
        this.branch_num = branch_num;
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

    public String getCard_user_num() {
        return card_user_num;
    }

    public void setCard_user_num(String card_user_num) {
        this.card_user_num = card_user_num;
    }

    public String getRelat_card_new_deadline() {
        return relat_card_new_deadline;
    }

    public void setRelat_card_new_deadline(String relat_card_new_deadline) {
        this.relat_card_new_deadline = relat_card_new_deadline;
    }

    public String getRelat_card_operator() {
        return relat_card_operator;
    }

    public void setRelat_card_operator(String relat_card_operator) {
        this.relat_card_operator = relat_card_operator;
    }

    public String getRelat_card_payment_type() {
        return relat_card_payment_type;
    }

    public void setRelat_card_payment_type(String relat_card_payment_type) {
        this.relat_card_payment_type = relat_card_payment_type;
    }

    public String getRelat_card_payment_type_name() {
        return relat_card_payment_type_name;
    }

    public void setRelat_card_payment_type_name(String relat_card_payment_type_name) {
        this.relat_card_payment_type_name = relat_card_payment_type_name;
    }

    public String getRelat_card_bank_name() {
        return relat_card_bank_name;
    }

    public void setRelat_card_bank_name(String relat_card_bank_name) {
        this.relat_card_bank_name = relat_card_bank_name;
    }

    public String getRelat_card_billref() {
        return relat_card_billref;
    }

    public void setRelat_card_billref(String relat_card_billref) {
        this.relat_card_billref = relat_card_billref;
    }

    public String getRelat_card_money() {
        return relat_card_money;
    }

    public void setRelat_card_money(String relat_card_money) {
        this.relat_card_money = relat_card_money;
    }

    public String getRelat_card_memo() {
        return relat_card_memo;
    }

    public void setRelat_card_memo(String relat_card_memo) {
        this.relat_card_memo = relat_card_memo;
    }

    public String getAccount_bank_num() {
        return account_bank_num;
    }

    public void setAccount_bank_num(String account_bank_num) {
        this.account_bank_num = account_bank_num;
    }

    public String getRelat_card_machine() {
        return relat_card_machine;
    }

    public void setRelat_card_machine(String relat_card_machine) {
        this.relat_card_machine = relat_card_machine;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }
}
