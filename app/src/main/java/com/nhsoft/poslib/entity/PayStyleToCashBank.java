package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 支付方式 和 现金银行 对应
 */
@Entity
public class PayStyleToCashBank {
    @Id
    @Property(nameInDb = "PAYSTYLECODE")
    private String payStyleCode;//支付方式编码
    private String payStyleName;//支付方式名字
    private String accountBankCode;//现金银行 编码
    private String accountBankName;//现金银行 名字
    @Generated(hash = 1011434929)
    public PayStyleToCashBank(String payStyleCode, String payStyleName,
            String accountBankCode, String accountBankName) {
        this.payStyleCode = payStyleCode;
        this.payStyleName = payStyleName;
        this.accountBankCode = accountBankCode;
        this.accountBankName = accountBankName;
    }
    @Generated(hash = 1205089739)
    public PayStyleToCashBank() {
    }
    public String getPayStyleCode() {
        return this.payStyleCode;
    }
    public void setPayStyleCode(String payStyleCode) {
        this.payStyleCode = payStyleCode;
    }
    public String getPayStyleName() {
        return this.payStyleName;
    }
    public void setPayStyleName(String payStyleName) {
        this.payStyleName = payStyleName;
    }
    public String getAccountBankCode() {
        return this.accountBankCode;
    }
    public void setAccountBankCode(String accountBankCode) {
        this.accountBankCode = accountBankCode;
    }
    public String getAccountBankName() {
        return this.accountBankName;
    }
    public void setAccountBankName(String accountBankName) {
        this.accountBankName = accountBankName;
    }

}
