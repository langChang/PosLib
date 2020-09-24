package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class OtherRevenue {


    /**
     * 其他收支
     *
     * account_bank_num : 0
     * branch_num : 0
     * client_fid : string
     * inner_branch_num : 0
     * other_inout_audit_man : string
     * other_inout_audit_time : string
     * other_inout_bill_no : string
     * other_inout_bizday : string
     * other_inout_create_time : string
     * other_inout_creator : string
     * other_inout_date : string
     * other_inout_kind : string
     * other_inout_machine : string
     * other_inout_memo : string
     * other_inout_money : 0
     * other_inout_operator : string
     * other_inout_payment_type : string
     * other_inout_ref_bill : string
     * other_inout_shift_table_num : 0
     * supplier_num : 0
     * system_book_code : string
     */

    @Id(autoincrement = true)
    private Long id;
    private int account_bank_num;
    private int branch_num;
    private String client_fid;
    private int inner_branch_num;
    private String other_inout_audit_man;
    private String other_inout_audit_time;
    private String other_inout_bill_no;
    private String other_inout_bizday;
    private String other_inout_create_time;
    private String other_inout_creator;
    private String other_inout_date;
    private String other_inout_kind;
    private String other_inout_machine;
    private String other_inout_memo;
    private Float other_inout_money;
    private String other_inout_operator;
    private String other_inout_payment_type;
    private String other_inout_ref_bill;
    private int other_inout_shift_table_num;
    private int supplier_num;
    private String system_book_code;
    private String payBarCode;


    @Generated(hash = 795196672)
    public OtherRevenue(Long id, int account_bank_num, int branch_num,
            String client_fid, int inner_branch_num, String other_inout_audit_man,
            String other_inout_audit_time, String other_inout_bill_no,
            String other_inout_bizday, String other_inout_create_time,
            String other_inout_creator, String other_inout_date,
            String other_inout_kind, String other_inout_machine,
            String other_inout_memo, Float other_inout_money,
            String other_inout_operator, String other_inout_payment_type,
            String other_inout_ref_bill, int other_inout_shift_table_num,
            int supplier_num, String system_book_code, String payBarCode) {
        this.id = id;
        this.account_bank_num = account_bank_num;
        this.branch_num = branch_num;
        this.client_fid = client_fid;
        this.inner_branch_num = inner_branch_num;
        this.other_inout_audit_man = other_inout_audit_man;
        this.other_inout_audit_time = other_inout_audit_time;
        this.other_inout_bill_no = other_inout_bill_no;
        this.other_inout_bizday = other_inout_bizday;
        this.other_inout_create_time = other_inout_create_time;
        this.other_inout_creator = other_inout_creator;
        this.other_inout_date = other_inout_date;
        this.other_inout_kind = other_inout_kind;
        this.other_inout_machine = other_inout_machine;
        this.other_inout_memo = other_inout_memo;
        this.other_inout_money = other_inout_money;
        this.other_inout_operator = other_inout_operator;
        this.other_inout_payment_type = other_inout_payment_type;
        this.other_inout_ref_bill = other_inout_ref_bill;
        this.other_inout_shift_table_num = other_inout_shift_table_num;
        this.supplier_num = supplier_num;
        this.system_book_code = system_book_code;
        this.payBarCode = payBarCode;
    }

    @Generated(hash = 1464478784)
    public OtherRevenue() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAccount_bank_num() {
        return account_bank_num;
    }

    public void setAccount_bank_num(int account_bank_num) {
        this.account_bank_num = account_bank_num;
    }

    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public String getClient_fid() {
        return client_fid;
    }

    public void setClient_fid(String client_fid) {
        this.client_fid = client_fid;
    }

    public int getInner_branch_num() {
        return inner_branch_num;
    }

    public void setInner_branch_num(int inner_branch_num) {
        this.inner_branch_num = inner_branch_num;
    }

    public String getOther_inout_audit_man() {
        return other_inout_audit_man;
    }

    public void setOther_inout_audit_man(String other_inout_audit_man) {
        this.other_inout_audit_man = other_inout_audit_man;
    }

    public String getOther_inout_audit_time() {
        return other_inout_audit_time;
    }

    public void setOther_inout_audit_time(String other_inout_audit_time) {
        this.other_inout_audit_time = other_inout_audit_time;
    }

    public String getOther_inout_bill_no() {
        return other_inout_bill_no;
    }

    public void setOther_inout_bill_no(String other_inout_bill_no) {
        this.other_inout_bill_no = other_inout_bill_no;
    }

    public String getOther_inout_bizday() {
        return other_inout_bizday;
    }

    public void setOther_inout_bizday(String other_inout_bizday) {
        this.other_inout_bizday = other_inout_bizday;
    }

    public String getOther_inout_create_time() {
        return other_inout_create_time;
    }

    public void setOther_inout_create_time(String other_inout_create_time) {
        this.other_inout_create_time = other_inout_create_time;
    }

    public String getOther_inout_creator() {
        return other_inout_creator;
    }

    public void setOther_inout_creator(String other_inout_creator) {
        this.other_inout_creator = other_inout_creator;
    }

    public String getOther_inout_date() {
        return other_inout_date;
    }

    public void setOther_inout_date(String other_inout_date) {
        this.other_inout_date = other_inout_date;
    }

    public String getOther_inout_kind() {
        return other_inout_kind;
    }

    public void setOther_inout_kind(String other_inout_kind) {
        this.other_inout_kind = other_inout_kind;
    }

    public String getOther_inout_machine() {
        return other_inout_machine;
    }

    public void setOther_inout_machine(String other_inout_machine) {
        this.other_inout_machine = other_inout_machine;
    }

    public String getOther_inout_memo() {
        return other_inout_memo;
    }

    public void setOther_inout_memo(String other_inout_memo) {
        this.other_inout_memo = other_inout_memo;
    }
    

    public String getOther_inout_operator() {
        return other_inout_operator;
    }

    public void setOther_inout_operator(String other_inout_operator) {
        this.other_inout_operator = other_inout_operator;
    }

    public String getOther_inout_payment_type() {
        return other_inout_payment_type;
    }

    public void setOther_inout_payment_type(String other_inout_payment_type) {
        this.other_inout_payment_type = other_inout_payment_type;
    }

    public String getOther_inout_ref_bill() {
        return other_inout_ref_bill;
    }

    public void setOther_inout_ref_bill(String other_inout_ref_bill) {
        this.other_inout_ref_bill = other_inout_ref_bill;
    }

    public int getOther_inout_shift_table_num() {
        return other_inout_shift_table_num;
    }

    public void setOther_inout_shift_table_num(int other_inout_shift_table_num) {
        this.other_inout_shift_table_num = other_inout_shift_table_num;
    }

    public int getSupplier_num() {
        return supplier_num;
    }

    public void setSupplier_num(int supplier_num) {
        this.supplier_num = supplier_num;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public Float getOther_inout_money() {
        return this.other_inout_money;
    }

    public void setOther_inout_money(Float other_inout_money) {
        this.other_inout_money = other_inout_money;
    }

    public String getPayBarCode() {
        return this.payBarCode;
    }

    public void setPayBarCode(String payBarCode) {
        this.payBarCode = payBarCode;
    }
}
