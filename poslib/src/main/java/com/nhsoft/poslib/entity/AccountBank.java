package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class AccountBank {

    /**
     * 获取所有现金银行清单
     *
     * account_bank_num : 444400005
     * account_bank_name : 台衡PC秤演示门店非现金银行
     * account_bank_balance : 0
     * account_bank_cash_flag : false  是否是现金银行
     * account_bank_no : 365892555522220
     * account_bank_branch_num : 1
     * account_bank_enabled : true
     * account_bank_bank : 农业银行
     * account_bank_bank_user : 台衡PC秤
     * account_bank_opened : true
     */

    @Id
    @Property(nameInDb = "ACCOUNT_BANK_NUM")
    private Long account_bank_num;
    private String account_bank_name;
    private double account_bank_balance;
    private boolean account_bank_cash_flag;
    private String account_bank_no;
    private int account_bank_branch_num;
    private boolean account_bank_enabled;
    private String account_bank_bank;
    private String account_bank_bank_user;
    private boolean account_bank_opened;
    @Generated(hash = 599887562)
    public AccountBank(Long account_bank_num, String account_bank_name,
            double account_bank_balance, boolean account_bank_cash_flag,
            String account_bank_no, int account_bank_branch_num,
            boolean account_bank_enabled, String account_bank_bank,
            String account_bank_bank_user, boolean account_bank_opened) {
        this.account_bank_num = account_bank_num;
        this.account_bank_name = account_bank_name;
        this.account_bank_balance = account_bank_balance;
        this.account_bank_cash_flag = account_bank_cash_flag;
        this.account_bank_no = account_bank_no;
        this.account_bank_branch_num = account_bank_branch_num;
        this.account_bank_enabled = account_bank_enabled;
        this.account_bank_bank = account_bank_bank;
        this.account_bank_bank_user = account_bank_bank_user;
        this.account_bank_opened = account_bank_opened;
    }
    @Generated(hash = 1063244636)
    public AccountBank() {
    }
    public Long getAccount_bank_num() {
        return this.account_bank_num;
    }
    public void setAccount_bank_num(Long account_bank_num) {
        this.account_bank_num = account_bank_num;
    }
    public String getAccount_bank_name() {
        return this.account_bank_name;
    }
    public void setAccount_bank_name(String account_bank_name) {
        this.account_bank_name = account_bank_name;
    }
    public double getAccount_bank_balance() {
        return this.account_bank_balance;
    }
    public void setAccount_bank_balance(double account_bank_balance) {
        this.account_bank_balance = account_bank_balance;
    }
    public boolean getAccount_bank_cash_flag() {
        return this.account_bank_cash_flag;
    }
    public void setAccount_bank_cash_flag(boolean account_bank_cash_flag) {
        this.account_bank_cash_flag = account_bank_cash_flag;
    }
    public String getAccount_bank_no() {
        return this.account_bank_no;
    }
    public void setAccount_bank_no(String account_bank_no) {
        this.account_bank_no = account_bank_no;
    }
    public int getAccount_bank_branch_num() {
        return this.account_bank_branch_num;
    }
    public void setAccount_bank_branch_num(int account_bank_branch_num) {
        this.account_bank_branch_num = account_bank_branch_num;
    }
    public boolean getAccount_bank_enabled() {
        return this.account_bank_enabled;
    }
    public void setAccount_bank_enabled(boolean account_bank_enabled) {
        this.account_bank_enabled = account_bank_enabled;
    }
    public String getAccount_bank_bank() {
        return this.account_bank_bank;
    }
    public void setAccount_bank_bank(String account_bank_bank) {
        this.account_bank_bank = account_bank_bank;
    }
    public String getAccount_bank_bank_user() {
        return this.account_bank_bank_user;
    }
    public void setAccount_bank_bank_user(String account_bank_bank_user) {
        this.account_bank_bank_user = account_bank_bank_user;
    }
    public boolean getAccount_bank_opened() {
        return this.account_bank_opened;
    }
    public void setAccount_bank_opened(boolean account_bank_opened) {
        this.account_bank_opened = account_bank_opened;
    }


}
