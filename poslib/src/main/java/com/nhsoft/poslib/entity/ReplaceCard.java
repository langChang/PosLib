package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

@Entity
public class ReplaceCard {

    /**
     * 换卡
     */

    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;//本地设置主键

    private String replace_card_fid;
    private String system_book_code;
    private Integer branch_num;
    private Integer replace_card_cust_num;
    private String replace_card_old_printed_num;
    private String replace_card_old_physical_num;
    private String replace_card_old_deadline;
    private String replace_card_new_printed_num;
    private String replace_card_new_physical_num;
    private String replace_card_new_deadline;
    private String replace_card_operator;
    private Integer replace_card_payment_type;
    private String replace_card_payment_type_name;
    private String replace_card_billref;
    private String repalce_card_bank_name;
    private double replace_card_money;
    private String replace_card_memo;
    private String replace_card_operate_time;
    private Integer replace_card_card_type;
    private String replace_card_branch_name;
    private String repalce_card_cust_name;
    private Integer account_bank_num;
    private String shift_table_bizday;
    private Integer shift_table_num;
    private String replace_card_machine;

    //临时属性
    @Transient
    private Integer replace_init_card_num;

    @Generated(hash = 316618464)
    public ReplaceCard(Long id, String replace_card_fid, String system_book_code,
            Integer branch_num, Integer replace_card_cust_num,
            String replace_card_old_printed_num,
            String replace_card_old_physical_num, String replace_card_old_deadline,
            String replace_card_new_printed_num,
            String replace_card_new_physical_num, String replace_card_new_deadline,
            String replace_card_operator, Integer replace_card_payment_type,
            String replace_card_payment_type_name, String replace_card_billref,
            String repalce_card_bank_name, double replace_card_money,
            String replace_card_memo, String replace_card_operate_time,
            Integer replace_card_card_type, String replace_card_branch_name,
            String repalce_card_cust_name, Integer account_bank_num,
            String shift_table_bizday, Integer shift_table_num,
            String replace_card_machine) {
        this.id = id;
        this.replace_card_fid = replace_card_fid;
        this.system_book_code = system_book_code;
        this.branch_num = branch_num;
        this.replace_card_cust_num = replace_card_cust_num;
        this.replace_card_old_printed_num = replace_card_old_printed_num;
        this.replace_card_old_physical_num = replace_card_old_physical_num;
        this.replace_card_old_deadline = replace_card_old_deadline;
        this.replace_card_new_printed_num = replace_card_new_printed_num;
        this.replace_card_new_physical_num = replace_card_new_physical_num;
        this.replace_card_new_deadline = replace_card_new_deadline;
        this.replace_card_operator = replace_card_operator;
        this.replace_card_payment_type = replace_card_payment_type;
        this.replace_card_payment_type_name = replace_card_payment_type_name;
        this.replace_card_billref = replace_card_billref;
        this.repalce_card_bank_name = repalce_card_bank_name;
        this.replace_card_money = replace_card_money;
        this.replace_card_memo = replace_card_memo;
        this.replace_card_operate_time = replace_card_operate_time;
        this.replace_card_card_type = replace_card_card_type;
        this.replace_card_branch_name = replace_card_branch_name;
        this.repalce_card_cust_name = repalce_card_cust_name;
        this.account_bank_num = account_bank_num;
        this.shift_table_bizday = shift_table_bizday;
        this.shift_table_num = shift_table_num;
        this.replace_card_machine = replace_card_machine;
    }

    @Generated(hash = 534692660)
    public ReplaceCard() {
    }

    public Integer getReplace_init_card_num() {
        return replace_init_card_num;
    }

    public void setReplace_init_card_num(Integer replace_init_card_num) {
        this.replace_init_card_num = replace_init_card_num;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReplace_card_fid() {
        return this.replace_card_fid;
    }

    public void setReplace_card_fid(String replace_card_fid) {
        this.replace_card_fid = replace_card_fid;
    }

    public String getSystem_book_code() {
        return this.system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public Integer getBranch_num() {
        return this.branch_num;
    }

    public void setBranch_num(Integer branch_num) {
        this.branch_num = branch_num;
    }

    public Integer getReplace_card_cust_num() {
        return this.replace_card_cust_num;
    }

    public void setReplace_card_cust_num(Integer replace_card_cust_num) {
        this.replace_card_cust_num = replace_card_cust_num;
    }

    public String getReplace_card_old_printed_num() {
        return this.replace_card_old_printed_num;
    }

    public void setReplace_card_old_printed_num(
            String replace_card_old_printed_num) {
        this.replace_card_old_printed_num = replace_card_old_printed_num;
    }

    public String getReplace_card_old_physical_num() {
        return this.replace_card_old_physical_num;
    }

    public void setReplace_card_old_physical_num(
            String replace_card_old_physical_num) {
        this.replace_card_old_physical_num = replace_card_old_physical_num;
    }

    public String getReplace_card_old_deadline() {
        return this.replace_card_old_deadline;
    }

    public void setReplace_card_old_deadline(String replace_card_old_deadline) {
        this.replace_card_old_deadline = replace_card_old_deadline;
    }

    public String getReplace_card_new_printed_num() {
        return this.replace_card_new_printed_num;
    }

    public void setReplace_card_new_printed_num(
            String replace_card_new_printed_num) {
        this.replace_card_new_printed_num = replace_card_new_printed_num;
    }

    public String getReplace_card_new_physical_num() {
        return this.replace_card_new_physical_num;
    }

    public void setReplace_card_new_physical_num(
            String replace_card_new_physical_num) {
        this.replace_card_new_physical_num = replace_card_new_physical_num;
    }

    public String getReplace_card_new_deadline() {
        return this.replace_card_new_deadline;
    }

    public void setReplace_card_new_deadline(String replace_card_new_deadline) {
        this.replace_card_new_deadline = replace_card_new_deadline;
    }

    public String getReplace_card_operator() {
        return this.replace_card_operator;
    }

    public void setReplace_card_operator(String replace_card_operator) {
        this.replace_card_operator = replace_card_operator;
    }

    public Integer getReplace_card_payment_type() {
        return this.replace_card_payment_type;
    }

    public void setReplace_card_payment_type(Integer replace_card_payment_type) {
        this.replace_card_payment_type = replace_card_payment_type;
    }

    public String getReplace_card_payment_type_name() {
        return this.replace_card_payment_type_name;
    }

    public void setReplace_card_payment_type_name(
            String replace_card_payment_type_name) {
        this.replace_card_payment_type_name = replace_card_payment_type_name;
    }

    public String getReplace_card_billref() {
        return this.replace_card_billref;
    }

    public void setReplace_card_billref(String replace_card_billref) {
        this.replace_card_billref = replace_card_billref;
    }

    public String getRepalce_card_bank_name() {
        return this.repalce_card_bank_name;
    }

    public void setRepalce_card_bank_name(String repalce_card_bank_name) {
        this.repalce_card_bank_name = repalce_card_bank_name;
    }

    public double getReplace_card_money() {
        return this.replace_card_money;
    }

    public void setReplace_card_money(double replace_card_money) {
        this.replace_card_money = replace_card_money;
    }

    public String getReplace_card_memo() {
        return this.replace_card_memo;
    }

    public void setReplace_card_memo(String replace_card_memo) {
        this.replace_card_memo = replace_card_memo;
    }

    public String getReplace_card_operate_time() {
        return this.replace_card_operate_time;
    }

    public void setReplace_card_operate_time(String replace_card_operate_time) {
        this.replace_card_operate_time = replace_card_operate_time;
    }

    public Integer getReplace_card_card_type() {
        return this.replace_card_card_type;
    }

    public void setReplace_card_card_type(Integer replace_card_card_type) {
        this.replace_card_card_type = replace_card_card_type;
    }

    public String getReplace_card_branch_name() {
        return this.replace_card_branch_name;
    }

    public void setReplace_card_branch_name(String replace_card_branch_name) {
        this.replace_card_branch_name = replace_card_branch_name;
    }

    public String getRepalce_card_cust_name() {
        return this.repalce_card_cust_name;
    }

    public void setRepalce_card_cust_name(String repalce_card_cust_name) {
        this.repalce_card_cust_name = repalce_card_cust_name;
    }

    public Integer getAccount_bank_num() {
        return this.account_bank_num;
    }

    public void setAccount_bank_num(Integer account_bank_num) {
        this.account_bank_num = account_bank_num;
    }

    public String getShift_table_bizday() {
        return this.shift_table_bizday;
    }

    public void setShift_table_bizday(String shift_table_bizday) {
        this.shift_table_bizday = shift_table_bizday;
    }

    public Integer getShift_table_num() {
        return this.shift_table_num;
    }

    public void setShift_table_num(Integer shift_table_num) {
        this.shift_table_num = shift_table_num;
    }

    public String getReplace_card_machine() {
        return this.replace_card_machine;
    }

    public void setReplace_card_machine(String replace_card_machine) {
        this.replace_card_machine = replace_card_machine;
    }
}
