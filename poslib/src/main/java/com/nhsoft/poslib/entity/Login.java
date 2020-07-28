package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Iverson on 2018/11/12 2:04 PM
 * 此类用于：
 */
@Entity
public class Login {

    @Id(autoincrement = true)
    private Long id;
    /**
     * user_id : 444400043
     * user_code : 001
     * user_name : 商超1
     * system_book_code : 4444
     * system_book_name : 喜临门蔬果（熟食）专卖演示帐套
     * branch_num : 99
     * branch_name : 管理中心
     */
    private String system_book_code;
    private String system_book_name;
    private int    branch_num;
    private String branch_name;
    private String user_psw;
    private String branch_product;//门店产品类型
    private Integer merchant_num;//商户号
    private String branch_module;
    private String branch_id;


    @Generated(hash = 1010487010)
    public Login(Long id, String system_book_code, String system_book_name,
            int branch_num, String branch_name, String user_psw,
            String branch_product, Integer merchant_num, String branch_module,
            String branch_id) {
        this.id = id;
        this.system_book_code = system_book_code;
        this.system_book_name = system_book_name;
        this.branch_num = branch_num;
        this.branch_name = branch_name;
        this.user_psw = user_psw;
        this.branch_product = branch_product;
        this.merchant_num = merchant_num;
        this.branch_module = branch_module;
        this.branch_id = branch_id;
    }

    @Generated(hash = 1827378950)
    public Login() {
    }

    public String getBranch_module() {
        return branch_module;
    }

    public void setBranch_module(String branch_module) {
        this.branch_module = branch_module;
    }

    public Integer getMerchant_num() {
        return merchant_num;
    }

    public void setMerchant_num(Integer merchant_num) {
        this.merchant_num = merchant_num;
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
    public String getSystem_book_name() {
        return this.system_book_name;
    }
    public void setSystem_book_name(String system_book_name) {
        this.system_book_name = system_book_name;
    }
    public int getBranch_num() {
        return this.branch_num;
    }
    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }
    public String getBranch_name() {
        return this.branch_name;
    }
    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }
    public String getUser_psw() {
        return this.user_psw;
    }
    public void setUser_psw(String user_psw) {
        this.user_psw = user_psw;
    }
    public String getBranch_product() {
        return this.branch_product;
    }
    public void setBranch_product(String branch_product) {
        this.branch_product = branch_product;
    }

    public String getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id;
    }
}
