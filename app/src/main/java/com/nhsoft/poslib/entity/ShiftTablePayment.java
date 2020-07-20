package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ShiftTablePayment {

    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;
    private String system_book_code;
    private int branch_num;
    private int shift_table_num;
    private String shift_table_bizday;
    private int shift_table_payment_num;
    private String shift_table_payment_type;
    private float shift_table_payment_money;
    private boolean shiftTablePaymentSyncFlag;

    private String shift_table_payment_bank_name;
    private String shift_table_payment_time;
    private String shift_table_payment_bank_bill_no;

    private float shift_table_payment_ori_money;

    @Generated(hash = 1249400082)
    public ShiftTablePayment(Long id, String system_book_code, int branch_num,
            int shift_table_num, String shift_table_bizday,
            int shift_table_payment_num, String shift_table_payment_type,
            float shift_table_payment_money, boolean shiftTablePaymentSyncFlag,
            String shift_table_payment_bank_name, String shift_table_payment_time,
            String shift_table_payment_bank_bill_no,
            float shift_table_payment_ori_money) {
        this.id = id;
        this.system_book_code = system_book_code;
        this.branch_num = branch_num;
        this.shift_table_num = shift_table_num;
        this.shift_table_bizday = shift_table_bizday;
        this.shift_table_payment_num = shift_table_payment_num;
        this.shift_table_payment_type = shift_table_payment_type;
        this.shift_table_payment_money = shift_table_payment_money;
        this.shiftTablePaymentSyncFlag = shiftTablePaymentSyncFlag;
        this.shift_table_payment_bank_name = shift_table_payment_bank_name;
        this.shift_table_payment_time = shift_table_payment_time;
        this.shift_table_payment_bank_bill_no = shift_table_payment_bank_bill_no;
        this.shift_table_payment_ori_money = shift_table_payment_ori_money;
    }

    @Generated(hash = 172316936)
    public ShiftTablePayment() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystem_book_code() {
        return this.system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public int getBranch_num() {
        return this.branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public int getShift_table_num() {
        return this.shift_table_num;
    }

    public void setShift_table_num(int shift_table_num) {
        this.shift_table_num = shift_table_num;
    }

    public String getShift_table_bizday() {
        return this.shift_table_bizday;
    }

    public void setShift_table_bizday(String shift_table_bizday) {
        this.shift_table_bizday = shift_table_bizday;
    }

    public int getShift_table_payment_num() {
        return this.shift_table_payment_num;
    }

    public void setShift_table_payment_num(int shift_table_payment_num) {
        this.shift_table_payment_num = shift_table_payment_num;
    }

    public String getShift_table_payment_type() {
        return this.shift_table_payment_type;
    }

    public void setShift_table_payment_type(String shift_table_payment_type) {
        this.shift_table_payment_type = shift_table_payment_type;
    }

    public float getShift_table_payment_money() {
        return this.shift_table_payment_money;
    }

    public void setShift_table_payment_money(float shift_table_payment_money) {
        this.shift_table_payment_money = shift_table_payment_money;
    }

    public boolean getShiftTablePaymentSyncFlag() {
        return this.shiftTablePaymentSyncFlag;
    }

    public void setShiftTablePaymentSyncFlag(boolean shiftTablePaymentSyncFlag) {
        this.shiftTablePaymentSyncFlag = shiftTablePaymentSyncFlag;
    }

    public String getShift_table_payment_bank_name() {
        return this.shift_table_payment_bank_name;
    }

    public void setShift_table_payment_bank_name(
            String shift_table_payment_bank_name) {
        this.shift_table_payment_bank_name = shift_table_payment_bank_name;
    }

    public String getShift_table_payment_time() {
        return this.shift_table_payment_time;
    }

    public void setShift_table_payment_time(String shift_table_payment_time) {
        this.shift_table_payment_time = shift_table_payment_time;
    }

    public String getShift_table_payment_bank_bill_no() {
        return this.shift_table_payment_bank_bill_no;
    }

    public void setShift_table_payment_bank_bill_no(
            String shift_table_payment_bank_bill_no) {
        this.shift_table_payment_bank_bill_no = shift_table_payment_bank_bill_no;
    }

    public float getShift_table_payment_ori_money() {
        return this.shift_table_payment_ori_money;
    }

    public void setShift_table_payment_ori_money(
            float shift_table_payment_ori_money) {
        this.shift_table_payment_ori_money = shift_table_payment_ori_money;
    }



   

}
