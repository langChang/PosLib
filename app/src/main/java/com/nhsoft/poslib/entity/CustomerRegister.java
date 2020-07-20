package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CustomerRegister {
    /**
     * branch_num : 0
     * shift_table_bizday : string
     * shift_table_num : 0
     * vip_card_user_category : 0
     * vip_card_user_date : string
     * vip_card_user_level : 0
     * vip_card_user_level_name : string
     * vip_card_user_log_fid : string
     * vip_card_user_memo : string
     * vip_card_user_money : 0
     * vip_card_user_name : string
     * vip_card_user_old_date : string
     * vip_card_user_operator : string
     * vip_card_user_payment : string
     * vip_card_user_phone : string
     * vip_card_user_print_num : string
     * vip_card_user_ref_bill : string
     * vip_card_user_type : string
     * vip_card_user_valid_date : string
     */
    @Id
    private String vip_card_user_log_fid;
    private int branch_num;
    private String shift_table_bizday;
    private int shift_table_num;
    private String vip_card_user_category;//会员类别
    private String vip_card_user_date;//操作时间
    private String vip_card_user_level;
    private String vip_card_user_level_name;
    private String vip_card_user_memo;
    private float vip_card_user_money;//付款金额
    private String vip_card_user_name;
    private String vip_card_user_old_date;
    private String vip_card_user_operator;
    private String vip_card_user_payment; //支付方式
    private String vip_card_user_phone;
    private String vip_card_user_print_num;
    private String vip_card_user_ref_bill;
    private String vip_card_user_type;
    private String vip_card_user_valid_date;
    private boolean vip_card_user_state;//上传状态
    private String system_book_code;//账套号

    @Generated(hash = 2070005725)
    public CustomerRegister(String vip_card_user_log_fid, int branch_num,
            String shift_table_bizday, int shift_table_num,
            String vip_card_user_category, String vip_card_user_date,
            String vip_card_user_level, String vip_card_user_level_name,
            String vip_card_user_memo, float vip_card_user_money,
            String vip_card_user_name, String vip_card_user_old_date,
            String vip_card_user_operator, String vip_card_user_payment,
            String vip_card_user_phone, String vip_card_user_print_num,
            String vip_card_user_ref_bill, String vip_card_user_type,
            String vip_card_user_valid_date, boolean vip_card_user_state,
            String system_book_code) {
        this.vip_card_user_log_fid = vip_card_user_log_fid;
        this.branch_num = branch_num;
        this.shift_table_bizday = shift_table_bizday;
        this.shift_table_num = shift_table_num;
        this.vip_card_user_category = vip_card_user_category;
        this.vip_card_user_date = vip_card_user_date;
        this.vip_card_user_level = vip_card_user_level;
        this.vip_card_user_level_name = vip_card_user_level_name;
        this.vip_card_user_memo = vip_card_user_memo;
        this.vip_card_user_money = vip_card_user_money;
        this.vip_card_user_name = vip_card_user_name;
        this.vip_card_user_old_date = vip_card_user_old_date;
        this.vip_card_user_operator = vip_card_user_operator;
        this.vip_card_user_payment = vip_card_user_payment;
        this.vip_card_user_phone = vip_card_user_phone;
        this.vip_card_user_print_num = vip_card_user_print_num;
        this.vip_card_user_ref_bill = vip_card_user_ref_bill;
        this.vip_card_user_type = vip_card_user_type;
        this.vip_card_user_valid_date = vip_card_user_valid_date;
        this.vip_card_user_state = vip_card_user_state;
        this.system_book_code = system_book_code;
    }

    @Generated(hash = 1481619947)
    public CustomerRegister() {
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public boolean isVip_card_user_state() {
        return vip_card_user_state;
    }

    public void setVip_card_user_state(boolean vip_card_user_state) {
        this.vip_card_user_state = vip_card_user_state;
    }

    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public String getShift_table_bizday() {
        return shift_table_bizday;
    }

    public void setShift_table_bizday(String shift_table_bizday) {
        this.shift_table_bizday = shift_table_bizday;
    }

    public int getShift_table_num() {
        return shift_table_num;
    }

    public void setShift_table_num(int shift_table_num) {
        this.shift_table_num = shift_table_num;
    }

    public String getVip_card_user_category() {
        return vip_card_user_category;
    }

    public void setVip_card_user_category(String vip_card_user_category) {
        this.vip_card_user_category = vip_card_user_category;
    }

    public String getVip_card_user_date() {
        return vip_card_user_date;
    }

    public void setVip_card_user_date(String vip_card_user_date) {
        this.vip_card_user_date = vip_card_user_date;
    }

    public String getVip_card_user_level() {
        return vip_card_user_level;
    }

    public void setVip_card_user_level(String vip_card_user_level) {
        this.vip_card_user_level = vip_card_user_level;
    }

    public String getVip_card_user_level_name() {
        return vip_card_user_level_name;
    }

    public void setVip_card_user_level_name(String vip_card_user_level_name) {
        this.vip_card_user_level_name = vip_card_user_level_name;
    }

    public String getVip_card_user_log_fid() {
        return vip_card_user_log_fid;
    }

    public void setVip_card_user_log_fid(String vip_card_user_log_fid) {
        this.vip_card_user_log_fid = vip_card_user_log_fid;
    }

    public String getVip_card_user_memo() {
        return vip_card_user_memo;
    }

    public void setVip_card_user_memo(String vip_card_user_memo) {
        this.vip_card_user_memo = vip_card_user_memo;
    }

    public float getVip_card_user_money() {
        return vip_card_user_money;
    }

    public void setVip_card_user_money(float vip_card_user_money) {
        this.vip_card_user_money = vip_card_user_money;
    }

    public String getVip_card_user_name() {
        return vip_card_user_name;
    }

    public void setVip_card_user_name(String vip_card_user_name) {
        this.vip_card_user_name = vip_card_user_name;
    }

    public String getVip_card_user_old_date() {
        return vip_card_user_old_date;
    }

    public void setVip_card_user_old_date(String vip_card_user_old_date) {
        this.vip_card_user_old_date = vip_card_user_old_date;
    }

    public String getVip_card_user_operator() {
        return vip_card_user_operator;
    }

    public void setVip_card_user_operator(String vip_card_user_operator) {
        this.vip_card_user_operator = vip_card_user_operator;
    }

    public String getVip_card_user_payment() {
        return vip_card_user_payment;
    }

    public void setVip_card_user_payment(String vip_card_user_payment) {
        this.vip_card_user_payment = vip_card_user_payment;
    }

    public String getVip_card_user_phone() {
        return vip_card_user_phone;
    }

    public void setVip_card_user_phone(String vip_card_user_phone) {
        this.vip_card_user_phone = vip_card_user_phone;
    }

    public String getVip_card_user_print_num() {
        return vip_card_user_print_num;
    }

    public void setVip_card_user_print_num(String vip_card_user_print_num) {
        this.vip_card_user_print_num = vip_card_user_print_num;
    }

    public String getVip_card_user_ref_bill() {
        return vip_card_user_ref_bill;
    }

    public void setVip_card_user_ref_bill(String vip_card_user_ref_bill) {
        this.vip_card_user_ref_bill = vip_card_user_ref_bill;
    }

    public String getVip_card_user_type() {
        return vip_card_user_type;
    }

    public void setVip_card_user_type(String vip_card_user_type) {
        this.vip_card_user_type = vip_card_user_type;
    }

    public String getVip_card_user_valid_date() {
        return vip_card_user_valid_date;
    }

    public void setVip_card_user_valid_date(String vip_card_user_valid_date) {
        this.vip_card_user_valid_date = vip_card_user_valid_date;
    }

    public boolean getVip_card_user_state() {
        return this.vip_card_user_state;
    }

    /**
     * 收费会员开卡
     * VIP_CARD_USER_LOG
     * Columns
     * VIP_CARD_USER_LOG_FID
     * SYSTEM_BOOK_CODE
     * BRANCH_NUM
     * VIP_CARD_USER_MONEY
     * VIP_CARD_USER_NAME
     * VIP_CARD_USER_PHONE
     *
     * VIP_CARD_USER_OPERATOR
     * VIP_CARD_USER_PRINT_NUM
     * VIP_CARD_USER_DATE
     * VIP_CARD_USER_REF_BILL
     * VIP_CARD_USER_CATEGORY
     * VIP_CARD_USER_LEVEL
     * VIP_CARD_USER_TYPE
     * VIP_CARD_USER_MEMO
     *
     * SHIFT_TABLE_BIZDAY
     * SHIFT_TABLE_NUM
     * VIP_CARD_USER_STATE
     * VIP_CARD_USER_PAYMENT
     * VIP_CARD_USER_BIRTHDAY
     * VIP_CARD_USER_OLD_DATE
     * VIP_CARD_USER_VALID_DATE
     * VIP_CARD_USER_LEVEL_NAME
     */
//    @Id
//    private String VIP_CARD_USER_LOG_FID; //主键
//    private String SYSTEM_BOOK_CODE;
//    private int BRANCH_NUM;
//    private float VIP_CARD_USER_MONEY;
//    private String VIP_CARD_USER_NAME;
//    private String VIP_CARD_USER_PHONE;
//
//    private String VIP_CARD_USER_OPERATOR;
//    private String VIP_CARD_USER_PRINT_NUM;
//    private String VIP_CARD_USER_DATE;
//    private String VIP_CARD_USER_REF_BILL;
//    private String VIP_CARD_USER_CATEGORY;
//    private String VIP_CARD_USER_LEVEL;
//    private String VIP_CARD_USER_TYPE;
//    private String VIP_CARD_USER_MEMO;
//
//    private String SHIFT_TABLE_BIZDAY;
//    private int SHIFT_TABLE_NUM;
//    private String VIP_CARD_USER_STATE;
//    private float VIP_CARD_USER_PAYMENT;
//    private String VIP_CARD_USER_BIRTHDAY;
//    private String VIP_CARD_USER_OLD_DATE;
//    private String VIP_CARD_USER_VALID_DATE;
//    private String VIP_CARD_USER_LEVEL_NAME;
//    private String VIP_CARD_PAY_TYPE;//支付方式
//    private String VIP_CARD_PAY_TYPE_CODE;//支付方式代码


}
