package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Iverson on 2019-06-10 10:37
 * 此类用于：
 */
@Entity
public class CardChange {

    @Id
    private String card_change_fid;
    private int card_user_num;
    private String system_book_code;
    private int branch_num;
    private int shift_table_num;
    private String shift_table_bizday;
    private String card_change_date;
    private String card_change_type;
    private float card_change_money;
    private float card_change_balance;
    private String card_change_memo;
    private String card_change_operator;
    private String card_change_machine;
    private boolean card_change_synch_flag;
    @Generated(hash = 773687375)
    public CardChange(String card_change_fid, int card_user_num,
            String system_book_code, int branch_num, int shift_table_num,
            String shift_table_bizday, String card_change_date,
            String card_change_type, float card_change_money,
            float card_change_balance, String card_change_memo,
            String card_change_operator, String card_change_machine,
            boolean card_change_synch_flag) {
        this.card_change_fid = card_change_fid;
        this.card_user_num = card_user_num;
        this.system_book_code = system_book_code;
        this.branch_num = branch_num;
        this.shift_table_num = shift_table_num;
        this.shift_table_bizday = shift_table_bizday;
        this.card_change_date = card_change_date;
        this.card_change_type = card_change_type;
        this.card_change_money = card_change_money;
        this.card_change_balance = card_change_balance;
        this.card_change_memo = card_change_memo;
        this.card_change_operator = card_change_operator;
        this.card_change_machine = card_change_machine;
        this.card_change_synch_flag = card_change_synch_flag;
    }
    @Generated(hash = 791324979)
    public CardChange() {
    }
    public String getCard_change_fid() {
        return this.card_change_fid;
    }
    public void setCard_change_fid(String card_change_fid) {
        this.card_change_fid = card_change_fid;
    }
    public int getCard_user_num() {
        return this.card_user_num;
    }
    public void setCard_user_num(int card_user_num) {
        this.card_user_num = card_user_num;
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
    public String getCard_change_date() {
        return this.card_change_date;
    }
    public void setCard_change_date(String card_change_date) {
        this.card_change_date = card_change_date;
    }
    public String getCard_change_type() {
        return this.card_change_type;
    }
    public void setCard_change_type(String card_change_type) {
        this.card_change_type = card_change_type;
    }
    public float getCard_change_money() {
        return this.card_change_money;
    }
    public void setCard_change_money(float card_change_money) {
        this.card_change_money = card_change_money;
    }
    public float getCard_change_balance() {
        return this.card_change_balance;
    }
    public void setCard_change_balance(float card_change_balance) {
        this.card_change_balance = card_change_balance;
    }
    public String getCard_change_memo() {
        return this.card_change_memo;
    }
    public void setCard_change_memo(String card_change_memo) {
        this.card_change_memo = card_change_memo;
    }
    public String getCard_change_operator() {
        return this.card_change_operator;
    }
    public void setCard_change_operator(String card_change_operator) {
        this.card_change_operator = card_change_operator;
    }
    public String getCard_change_machine() {
        return this.card_change_machine;
    }
    public void setCard_change_machine(String card_change_machine) {
        this.card_change_machine = card_change_machine;
    }
    public boolean getCard_change_synch_flag() {
        return this.card_change_synch_flag;
    }
    public void setCard_change_synch_flag(boolean card_change_synch_flag) {
        this.card_change_synch_flag = card_change_synch_flag;
    }

}
