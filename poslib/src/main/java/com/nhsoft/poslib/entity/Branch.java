package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Iverson on 2018/11/12 2:06 PM
 * 此类用于：
 */
@Entity
public class Branch {


    /**
     * 获取所有门店
     *
     * branch_num : 1
     * branch_region_num : 444400002
     * branch_code : 1
     * branch_name : 台衡PC秤演示门店
     * branch_actived : true
     * branch_addr : 杭州市
     * branch_phone : 18815286522
     * branch_rdc : false
     * branch_type : 加盟
     * branch_matrix_price_actived : true
     * branch_credit_limit : 0.0
     * branch_card_balance_limit : 0.0
     * branch_expiration : 2018-12-31T03:24:28.000+0000
     * branch_support_ic : true
     * branch_support_id : false
     * branch_product : 喜临门蔬果（熟食）食品专卖店管理系统(V2015)
     * branch_kit_enabled : false
     * branch_status  1 闭店
     */

    @Id
    @Property(nameInDb = "BRANCH_NUM")
    private Long branch_num;
    private String system_book_code;
    private int     branch_region_num;
    private String  branch_code;
    private String  branch_name;
    private Boolean branch_actived;
    private String  branch_addr;
    private String  branch_phone;
    private boolean branch_rdc;
    private String  branch_type;
    private boolean branch_matrix_price_actived;
    private double  branch_credit_limit;
    private double  branch_card_balance_limit;
    private String  branch_expiration;
    private boolean branch_support_ic;
    private boolean branch_support_id;
    private String  branch_product;
    private boolean branch_kit_enabled;
    private Long management_template_num;
    private String branch_close_time;
    private int branch_status;
    private String branch_memo;//商户自定义文案


    @Generated(hash = 841082453)
    public Branch(Long branch_num, String system_book_code, int branch_region_num,
            String branch_code, String branch_name, Boolean branch_actived,
            String branch_addr, String branch_phone, boolean branch_rdc,
            String branch_type, boolean branch_matrix_price_actived,
            double branch_credit_limit, double branch_card_balance_limit,
            String branch_expiration, boolean branch_support_ic,
            boolean branch_support_id, String branch_product,
            boolean branch_kit_enabled, Long management_template_num,
            String branch_close_time, int branch_status, String branch_memo) {
        this.branch_num = branch_num;
        this.system_book_code = system_book_code;
        this.branch_region_num = branch_region_num;
        this.branch_code = branch_code;
        this.branch_name = branch_name;
        this.branch_actived = branch_actived;
        this.branch_addr = branch_addr;
        this.branch_phone = branch_phone;
        this.branch_rdc = branch_rdc;
        this.branch_type = branch_type;
        this.branch_matrix_price_actived = branch_matrix_price_actived;
        this.branch_credit_limit = branch_credit_limit;
        this.branch_card_balance_limit = branch_card_balance_limit;
        this.branch_expiration = branch_expiration;
        this.branch_support_ic = branch_support_ic;
        this.branch_support_id = branch_support_id;
        this.branch_product = branch_product;
        this.branch_kit_enabled = branch_kit_enabled;
        this.management_template_num = management_template_num;
        this.branch_close_time = branch_close_time;
        this.branch_status = branch_status;
        this.branch_memo = branch_memo;
    }

    @Generated(hash = 956821948)
    public Branch() {
    }


    public int getBranch_status() {
        return branch_status;
    }

    public void setBranch_status(int branch_status) {
        this.branch_status = branch_status;
    }

    public Long getBranch_num() {
        return this.branch_num;
    }
    public void setBranch_num(Long branch_num) {
        this.branch_num = branch_num;
    }
    public String getSystem_book_code() {
        return this.system_book_code;
    }
    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }
    public int getBranch_region_num() {
        return this.branch_region_num;
    }
    public void setBranch_region_num(int branch_region_num) {
        this.branch_region_num = branch_region_num;
    }
    public String getBranch_code() {
        return this.branch_code;
    }
    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }
    public String getBranch_name() {
        return this.branch_name;
    }
    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }
    public Boolean getBranch_actived() {
        return this.branch_actived;
    }
    public void setBranch_actived(Boolean branch_actived) {
        this.branch_actived = branch_actived;
    }
    public String getBranch_addr() {
        return this.branch_addr;
    }
    public void setBranch_addr(String branch_addr) {
        this.branch_addr = branch_addr;
    }
    public String getBranch_phone() {
        return this.branch_phone;
    }
    public void setBranch_phone(String branch_phone) {
        this.branch_phone = branch_phone;
    }
    public boolean getBranch_rdc() {
        return this.branch_rdc;
    }
    public void setBranch_rdc(boolean branch_rdc) {
        this.branch_rdc = branch_rdc;
    }
    public String getBranch_type() {
        return this.branch_type;
    }
    public void setBranch_type(String branch_type) {
        this.branch_type = branch_type;
    }
    public boolean getBranch_matrix_price_actived() {
        return this.branch_matrix_price_actived;
    }
    public void setBranch_matrix_price_actived(
            boolean branch_matrix_price_actived) {
        this.branch_matrix_price_actived = branch_matrix_price_actived;
    }
    public double getBranch_credit_limit() {
        return this.branch_credit_limit;
    }
    public void setBranch_credit_limit(double branch_credit_limit) {
        this.branch_credit_limit = branch_credit_limit;
    }
    public double getBranch_card_balance_limit() {
        return this.branch_card_balance_limit;
    }
    public void setBranch_card_balance_limit(double branch_card_balance_limit) {
        this.branch_card_balance_limit = branch_card_balance_limit;
    }
    public String getBranch_expiration() {
        return this.branch_expiration;
    }
    public void setBranch_expiration(String branch_expiration) {
        this.branch_expiration = branch_expiration;
    }
    public boolean getBranch_support_ic() {
        return this.branch_support_ic;
    }
    public void setBranch_support_ic(boolean branch_support_ic) {
        this.branch_support_ic = branch_support_ic;
    }
    public boolean getBranch_support_id() {
        return this.branch_support_id;
    }
    public void setBranch_support_id(boolean branch_support_id) {
        this.branch_support_id = branch_support_id;
    }
    public String getBranch_product() {
        return this.branch_product;
    }
    public void setBranch_product(String branch_product) {
        this.branch_product = branch_product;
    }
    public boolean getBranch_kit_enabled() {
        return this.branch_kit_enabled;
    }
    public void setBranch_kit_enabled(boolean branch_kit_enabled) {
        this.branch_kit_enabled = branch_kit_enabled;
    }
    public Long getManagement_template_num() {
        return this.management_template_num;
    }
    public void setManagement_template_num(Long management_template_num) {
        this.management_template_num = management_template_num;
    }
    public String getBranch_close_time() {
        return this.branch_close_time;
    }
    public void setBranch_close_time(String branch_close_time) {
        this.branch_close_time = branch_close_time;
    }

    public String getBranch_memo() {
        return this.branch_memo;
    }

    public void setBranch_memo(String branch_memo) {
        this.branch_memo = branch_memo;
    }



}
