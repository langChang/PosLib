package com.nhsoft.poslib.entity.nongmao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 农贸
 * 商户
 */
@Entity
public class Merchant {

    /**
     * branch_num : 0
     * merchant_address : string
     * merchant_card_no : string
     * merchant_category : string
     * merchant_code : string
     * merchant_del_tag : true
     * merchant_license_no : string
     * merchant_linkman : string
     * merchant_memo : string
     * merchant_name : string
     * merchant_num : 0
     * merchant_phone : string
     * merchant_status : 0
     * merchant_tax_no : string
     * system_book_code : string
     */
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;
    private int branch_num;
    private String  merchant_address;
    private String  merchant_card_no;
    private String  merchant_category;
    private String  merchant_code;
    private boolean merchant_del_tag;
    private String  merchant_license_no;
    private String  merchant_linkman;
    private String  merchant_memo;
    private String  merchant_name;
    private int     merchant_num;
    private String  merchant_phone;
    private int     merchant_status;
    private String  merchant_tax_no;
    private String  system_book_code;

    @Generated(hash = 1671053638)
    public Merchant(Long id, int branch_num, String merchant_address,
            String merchant_card_no, String merchant_category, String merchant_code,
            boolean merchant_del_tag, String merchant_license_no,
            String merchant_linkman, String merchant_memo, String merchant_name,
            int merchant_num, String merchant_phone, int merchant_status,
            String merchant_tax_no, String system_book_code) {
        this.id = id;
        this.branch_num = branch_num;
        this.merchant_address = merchant_address;
        this.merchant_card_no = merchant_card_no;
        this.merchant_category = merchant_category;
        this.merchant_code = merchant_code;
        this.merchant_del_tag = merchant_del_tag;
        this.merchant_license_no = merchant_license_no;
        this.merchant_linkman = merchant_linkman;
        this.merchant_memo = merchant_memo;
        this.merchant_name = merchant_name;
        this.merchant_num = merchant_num;
        this.merchant_phone = merchant_phone;
        this.merchant_status = merchant_status;
        this.merchant_tax_no = merchant_tax_no;
        this.system_book_code = system_book_code;
    }

    @Generated(hash = 91174753)
    public Merchant() {
    }

    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public String getMerchant_address() {
        return merchant_address;
    }

    public void setMerchant_address(String merchant_address) {
        this.merchant_address = merchant_address;
    }

    public String getMerchant_card_no() {
        return merchant_card_no;
    }

    public void setMerchant_card_no(String merchant_card_no) {
        this.merchant_card_no = merchant_card_no;
    }

    public String getMerchant_category() {
        return merchant_category;
    }

    public void setMerchant_category(String merchant_category) {
        this.merchant_category = merchant_category;
    }

    public String getMerchant_code() {
        return merchant_code;
    }

    public void setMerchant_code(String merchant_code) {
        this.merchant_code = merchant_code;
    }

    public boolean isMerchant_del_tag() {
        return merchant_del_tag;
    }

    public void setMerchant_del_tag(boolean merchant_del_tag) {
        this.merchant_del_tag = merchant_del_tag;
    }

    public String getMerchant_license_no() {
        return merchant_license_no;
    }

    public void setMerchant_license_no(String merchant_license_no) {
        this.merchant_license_no = merchant_license_no;
    }

    public String getMerchant_linkman() {
        return merchant_linkman;
    }

    public void setMerchant_linkman(String merchant_linkman) {
        this.merchant_linkman = merchant_linkman;
    }

    public String getMerchant_memo() {
        return merchant_memo;
    }

    public void setMerchant_memo(String merchant_memo) {
        this.merchant_memo = merchant_memo;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public int getMerchant_num() {
        return merchant_num;
    }

    public void setMerchant_num(int merchant_num) {
        this.merchant_num = merchant_num;
    }

    public String getMerchant_phone() {
        return merchant_phone;
    }

    public void setMerchant_phone(String merchant_phone) {
        this.merchant_phone = merchant_phone;
    }

    public int getMerchant_status() {
        return merchant_status;
    }

    public void setMerchant_status(int merchant_status) {
        this.merchant_status = merchant_status;
    }

    public String getMerchant_tax_no() {
        return merchant_tax_no;
    }

    public void setMerchant_tax_no(String merchant_tax_no) {
        this.merchant_tax_no = merchant_tax_no;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getMerchant_del_tag() {
        return this.merchant_del_tag;
    }
}
