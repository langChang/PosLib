package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class RelatCard {
    /**
     * 续卡
     */
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;
    
    private String relat_card_fid;
    private String system_book_code;
    private Integer branch_num;
    private Integer shift_table_num;
    private String shift_table_bizday;
    private Integer relat_card_cust_num;
    private String relat_card_printed_num;
    private String relat_card_physical_num;
    private String relat_card_old_deadline;
    private String relat_card_new_deadline;
    private String relat_card_operator;
    private Integer relat_card_payment_type;
    private String relat_card_payment_type_name;
    private String relat_card_bank_name;
    private String relat_card_billref;
    private Double relat_card_money;
    private String relat_card_memo;
    private String relat_card_operate_time;
    private String relat_card_branch_name;
    private Integer relat_card_card_cype;
    private String relat_card_cust_name;
    private Integer account_bank_num;
    private String relat_card_machine;
    @Generated(hash = 1015534085)
    public RelatCard(Long id, String relat_card_fid, String system_book_code,
            Integer branch_num, Integer shift_table_num, String shift_table_bizday,
            Integer relat_card_cust_num, String relat_card_printed_num,
            String relat_card_physical_num, String relat_card_old_deadline,
            String relat_card_new_deadline, String relat_card_operator,
            Integer relat_card_payment_type, String relat_card_payment_type_name,
            String relat_card_bank_name, String relat_card_billref,
            Double relat_card_money, String relat_card_memo,
            String relat_card_operate_time, String relat_card_branch_name,
            Integer relat_card_card_cype, String relat_card_cust_name,
            Integer account_bank_num, String relat_card_machine) {
        this.id = id;
        this.relat_card_fid = relat_card_fid;
        this.system_book_code = system_book_code;
        this.branch_num = branch_num;
        this.shift_table_num = shift_table_num;
        this.shift_table_bizday = shift_table_bizday;
        this.relat_card_cust_num = relat_card_cust_num;
        this.relat_card_printed_num = relat_card_printed_num;
        this.relat_card_physical_num = relat_card_physical_num;
        this.relat_card_old_deadline = relat_card_old_deadline;
        this.relat_card_new_deadline = relat_card_new_deadline;
        this.relat_card_operator = relat_card_operator;
        this.relat_card_payment_type = relat_card_payment_type;
        this.relat_card_payment_type_name = relat_card_payment_type_name;
        this.relat_card_bank_name = relat_card_bank_name;
        this.relat_card_billref = relat_card_billref;
        this.relat_card_money = relat_card_money;
        this.relat_card_memo = relat_card_memo;
        this.relat_card_operate_time = relat_card_operate_time;
        this.relat_card_branch_name = relat_card_branch_name;
        this.relat_card_card_cype = relat_card_card_cype;
        this.relat_card_cust_name = relat_card_cust_name;
        this.account_bank_num = account_bank_num;
        this.relat_card_machine = relat_card_machine;
    }
    @Generated(hash = 2011763331)
    public RelatCard() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRelat_card_fid() {
        return this.relat_card_fid;
    }
    public void setRelat_card_fid(String relat_card_fid) {
        this.relat_card_fid = relat_card_fid;
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
    public Integer getShift_table_num() {
        return this.shift_table_num;
    }
    public void setShift_table_num(Integer shift_table_num) {
        this.shift_table_num = shift_table_num;
    }
    public String getShift_table_bizday() {
        return this.shift_table_bizday;
    }
    public void setShift_table_bizday(String shift_table_bizday) {
        this.shift_table_bizday = shift_table_bizday;
    }
    public Integer getRelat_card_cust_num() {
        return this.relat_card_cust_num;
    }
    public void setRelat_card_cust_num(Integer relat_card_cust_num) {
        this.relat_card_cust_num = relat_card_cust_num;
    }
    public String getRelat_card_printed_num() {
        return this.relat_card_printed_num;
    }
    public void setRelat_card_printed_num(String relat_card_printed_num) {
        this.relat_card_printed_num = relat_card_printed_num;
    }
    public String getRelat_card_physical_num() {
        return this.relat_card_physical_num;
    }
    public void setRelat_card_physical_num(String relat_card_physical_num) {
        this.relat_card_physical_num = relat_card_physical_num;
    }
    public String getRelat_card_old_deadline() {
        return this.relat_card_old_deadline;
    }
    public void setRelat_card_old_deadline(String relat_card_old_deadline) {
        this.relat_card_old_deadline = relat_card_old_deadline;
    }
    public String getRelat_card_new_deadline() {
        return this.relat_card_new_deadline;
    }
    public void setRelat_card_new_deadline(String relat_card_new_deadline) {
        this.relat_card_new_deadline = relat_card_new_deadline;
    }
    public String getRelat_card_operator() {
        return this.relat_card_operator;
    }
    public void setRelat_card_operator(String relat_card_operator) {
        this.relat_card_operator = relat_card_operator;
    }
    public Integer getRelat_card_payment_type() {
        return this.relat_card_payment_type;
    }
    public void setRelat_card_payment_type(Integer relat_card_payment_type) {
        this.relat_card_payment_type = relat_card_payment_type;
    }
    public String getRelat_card_payment_type_name() {
        return this.relat_card_payment_type_name;
    }
    public void setRelat_card_payment_type_name(
            String relat_card_payment_type_name) {
        this.relat_card_payment_type_name = relat_card_payment_type_name;
    }
    public String getRelat_card_bank_name() {
        return this.relat_card_bank_name;
    }
    public void setRelat_card_bank_name(String relat_card_bank_name) {
        this.relat_card_bank_name = relat_card_bank_name;
    }
    public String getRelat_card_billref() {
        return this.relat_card_billref;
    }
    public void setRelat_card_billref(String relat_card_billref) {
        this.relat_card_billref = relat_card_billref;
    }
    public Double getRelat_card_money() {
        return this.relat_card_money;
    }
    public void setRelat_card_money(Double relat_card_money) {
        this.relat_card_money = relat_card_money;
    }
    public String getRelat_card_memo() {
        return this.relat_card_memo;
    }
    public void setRelat_card_memo(String relat_card_memo) {
        this.relat_card_memo = relat_card_memo;
    }
    public String getRelat_card_operate_time() {
        return this.relat_card_operate_time;
    }
    public void setRelat_card_operate_time(String relat_card_operate_time) {
        this.relat_card_operate_time = relat_card_operate_time;
    }
    public String getRelat_card_branch_name() {
        return this.relat_card_branch_name;
    }
    public void setRelat_card_branch_name(String relat_card_branch_name) {
        this.relat_card_branch_name = relat_card_branch_name;
    }
    public Integer getRelat_card_card_cype() {
        return this.relat_card_card_cype;
    }
    public void setRelat_card_card_cype(Integer relat_card_card_cype) {
        this.relat_card_card_cype = relat_card_card_cype;
    }
    public String getRelat_card_cust_name() {
        return this.relat_card_cust_name;
    }
    public void setRelat_card_cust_name(String relat_card_cust_name) {
        this.relat_card_cust_name = relat_card_cust_name;
    }
    public Integer getAccount_bank_num() {
        return this.account_bank_num;
    }
    public void setAccount_bank_num(Integer account_bank_num) {
        this.account_bank_num = account_bank_num;
    }
    public String getRelat_card_machine() {
        return this.relat_card_machine;
    }
    public void setRelat_card_machine(String relat_card_machine) {
        this.relat_card_machine = relat_card_machine;
    }
 
}
