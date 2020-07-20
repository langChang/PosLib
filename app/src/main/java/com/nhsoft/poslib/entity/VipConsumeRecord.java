package com.nhsoft.poslib.entity;

public class VipConsumeRecord {


    /**
     * 消费记录
     *
     * branch_num : Integer||门店编号
     * shift_table_num : Integer||班次号
     * shift_table_bizday : String||交班日期(yyyyMMdd)
     * card_user_num : String||卡主键
     * consume_point : BigDecimal||消费可获得积分
     * consume_discount : BigDecimal||消费折扣
     * consume_round : BigDecimal||消费舍出
     * consume_money : BigDecimal||实际消费金额
     * consume_operator : String||消费操作人
     * consume_bill_num : String||参考单据号
     * consume_memo : String||备注
     * consume_machine : String||消费设备号
     * consume_order_no : String||外部关联卡号
     * system_book_code : String||账套号
     * consume_category : Integer||消费分类(查询参数)
     * consume_type : String|| 消费类型(查询参数)
     * consume_balance : BigDecimal|| 消费前余额(查询参数)
     * consume_invoice : BigDecimal|| 发票金额(查询参数)
     * consume_date : String||消费时间(查询参数)
     * consume_count : Integer|| 消费次数(查询参数)
     */

    private String branch_num;
    private String shift_table_num;
    private String shift_table_bizday;
    private String card_user_num;
    private String consume_point;
    private String consume_discount;
    private String consume_round;
    private String consume_money;
    private String consume_operator;
    private String consume_bill_num;
    private String consume_memo;
    private String consume_machine;
    private String consume_order_no;
    private String system_book_code;
    private String consume_category;
    private String consume_type;
    private String consume_balance;
    private String consume_invoice;
    private String consume_date;
    private String consume_count;
    private String merchant_num;
    private String stall_num;

    public String getStall_num() {
        return stall_num;
    }

    public void setStall_num(String stall_num) {
        this.stall_num = stall_num;
    }

    public String getMerchant_num() {
        return merchant_num;
    }

    public void setMerchant_num(String merchant_num) {
        this.merchant_num = merchant_num;
    }

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

    public String getConsume_point() {
        return consume_point;
    }

    public void setConsume_point(String consume_point) {
        this.consume_point = consume_point;
    }

    public String getConsume_discount() {
        return consume_discount;
    }

    public void setConsume_discount(String consume_discount) {
        this.consume_discount = consume_discount;
    }

    public String getConsume_round() {
        return consume_round;
    }

    public void setConsume_round(String consume_round) {
        this.consume_round = consume_round;
    }

    public String getConsume_money() {
        return consume_money;
    }

    public void setConsume_money(String consume_money) {
        this.consume_money = consume_money;
    }

    public String getConsume_operator() {
        return consume_operator;
    }

    public void setConsume_operator(String consume_operator) {
        this.consume_operator = consume_operator;
    }

    public String getConsume_bill_num() {
        return consume_bill_num;
    }

    public void setConsume_bill_num(String consume_bill_num) {
        this.consume_bill_num = consume_bill_num;
    }

    public String getConsume_memo() {
        return consume_memo;
    }

    public void setConsume_memo(String consume_memo) {
        this.consume_memo = consume_memo;
    }

    public String getConsume_machine() {
        return consume_machine;
    }

    public void setConsume_machine(String consume_machine) {
        this.consume_machine = consume_machine;
    }

    public String getConsume_order_no() {
        return consume_order_no;
    }

    public void setConsume_order_no(String consume_order_no) {
        this.consume_order_no = consume_order_no;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public String getConsume_category() {
        return consume_category;
    }

    public void setConsume_category(String consume_category) {
        this.consume_category = consume_category;
    }

    public String getConsume_type() {
        return consume_type;
    }

    public void setConsume_type(String consume_type) {
        this.consume_type = consume_type;
    }

    public String getConsume_balance() {
        return consume_balance;
    }

    public void setConsume_balance(String consume_balance) {
        this.consume_balance = consume_balance;
    }

    public String getConsume_invoice() {
        return consume_invoice;
    }

    public void setConsume_invoice(String consume_invoice) {
        this.consume_invoice = consume_invoice;
    }

    public String getConsume_date() {
        return consume_date;
    }

    public void setConsume_date(String consume_date) {
        this.consume_date = consume_date;
    }

    public String getConsume_count() {
        return consume_count;
    }

    public void setConsume_count(String consume_count) {
        this.consume_count = consume_count;
    }
}
