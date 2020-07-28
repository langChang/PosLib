package com.nhsoft.poslib.entity;

public class VipExchangingCredits {


    /**
     * 兑分记录
     *
     * branch_num : Integer||门店编号
     * shift_table_num : Integer||班次号
     * shift_table_bizday : String||交班日期(yyyyMMdd)
     * card_user_num : String||卡主键
     * consume_point_point : BigDecimal||消费积分数
     * consume_point_operator : String||操作人
     * consume_point_memo : String||备注
     * consume_point_ref_bill_no : String||参考单据号
     * item_num : Integer||商品编码
     * storehouse_num : Integer||仓库编码
     * amount : BigDecimal||商品数量
     * item_name : String||商品名称
     * consume_point_date //兑换日期(查询参数)
     * consume_point_balance//原始积分(查询参数)
     */

    private String branch_num;
    private String shift_table_num;
    private String shift_table_bizday;
    private String card_user_num;
    private String consume_point_point;
    private String consume_point_operator;
    private String consume_point_memo;
    private String consume_point_ref_bill_no;
    private String item_num;
    private String storehouse_num;
    private String amount;
    private String item_name;
    private String consume_point_date;
    private String consume_point_balance;
    private String customer_id;

    public String getConsume_point_date() {
        return consume_point_date;
    }

    public void setConsume_point_date(String consume_point_date) {
        this.consume_point_date = consume_point_date;
    }

    public String getConsume_point_balance() {
        return consume_point_balance;
    }

    public void setConsume_point_balance(String consume_point_balance) {
        this.consume_point_balance = consume_point_balance;
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

    public String getConsume_point_point() {
        return consume_point_point;
    }

    public void setConsume_point_point(String consume_point_point) {
        this.consume_point_point = consume_point_point;
    }

    public String getConsume_point_operator() {
        return consume_point_operator;
    }

    public void setConsume_point_operator(String consume_point_operator) {
        this.consume_point_operator = consume_point_operator;
    }

    public String getConsume_point_memo() {
        return consume_point_memo;
    }

    public void setConsume_point_memo(String consume_point_memo) {
        this.consume_point_memo = consume_point_memo;
    }

    public String getConsume_point_ref_bill_no() {
        return consume_point_ref_bill_no;
    }

    public void setConsume_point_ref_bill_no(String consume_point_ref_bill_no) {
        this.consume_point_ref_bill_no = consume_point_ref_bill_no;
    }

    public String getItem_num() {
        return item_num;
    }

    public void setItem_num(String item_num) {
        this.item_num = item_num;
    }

    public String getStorehouse_num() {
        return storehouse_num;
    }

    public void setStorehouse_num(String storehouse_num) {
        this.storehouse_num = storehouse_num;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}
