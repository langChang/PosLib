package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Iverson on 2019/1/16 3:56 PM
 * 此类用于：
 */
@Entity
public class ClientPoint {

    @Id
    private String client_point_fid;
    private String system_book_code;
    private int branch_num;
    private int card_user_num;
    private String client_point_branch_name;
    private String client_point_cust_name;
    private String client_point_source;
    private String client_point_date;
    private float client_point_balance;
    private String client_point_operator;
    private String client_point_ref_bill_no;
    private String client_point_memo;
    private String client_point_operate_type;
    private String client_point_uuid;
    private boolean client_point_del_flag;
    private boolean client_point_sync;
    private String shift_table_bizday;
    private int shift_table_num;
    private String client_point_rule_id;
    private String client_point_last_edit_time;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    private String customer_id;

    @Generated(hash = 855855871)
    public ClientPoint(String client_point_fid, String system_book_code,
            int branch_num, int card_user_num, String client_point_branch_name,
            String client_point_cust_name, String client_point_source,
            String client_point_date, float client_point_balance,
            String client_point_operator, String client_point_ref_bill_no,
            String client_point_memo, String client_point_operate_type,
            String client_point_uuid, boolean client_point_del_flag,
            boolean client_point_sync, String shift_table_bizday,
            int shift_table_num, String client_point_rule_id,
            String client_point_last_edit_time, String customer_id) {
        this.client_point_fid = client_point_fid;
        this.system_book_code = system_book_code;
        this.branch_num = branch_num;
        this.card_user_num = card_user_num;
        this.client_point_branch_name = client_point_branch_name;
        this.client_point_cust_name = client_point_cust_name;
        this.client_point_source = client_point_source;
        this.client_point_date = client_point_date;
        this.client_point_balance = client_point_balance;
        this.client_point_operator = client_point_operator;
        this.client_point_ref_bill_no = client_point_ref_bill_no;
        this.client_point_memo = client_point_memo;
        this.client_point_operate_type = client_point_operate_type;
        this.client_point_uuid = client_point_uuid;
        this.client_point_del_flag = client_point_del_flag;
        this.client_point_sync = client_point_sync;
        this.shift_table_bizday = shift_table_bizday;
        this.shift_table_num = shift_table_num;
        this.client_point_rule_id = client_point_rule_id;
        this.client_point_last_edit_time = client_point_last_edit_time;
        this.customer_id = customer_id;
    }

    @Generated(hash = 1755165776)
    public ClientPoint() {
    }

   
    public String getClient_point_fid() {
        return this.client_point_fid;
    }
    public void setClient_point_fid(String client_point_fid) {
        this.client_point_fid = client_point_fid;
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
    public int getCard_user_num() {
        return this.card_user_num;
    }
    public void setCard_user_num(int card_user_num) {
        this.card_user_num = card_user_num;
    }
    public String getClient_point_branch_name() {
        return this.client_point_branch_name;
    }
    public void setClient_point_branch_name(String client_point_branch_name) {
        this.client_point_branch_name = client_point_branch_name;
    }
    public String getClient_point_cust_name() {
        return this.client_point_cust_name;
    }
    public void setClient_point_cust_name(String client_point_cust_name) {
        this.client_point_cust_name = client_point_cust_name;
    }
    public String getClient_point_source() {
        return this.client_point_source;
    }
    public void setClient_point_source(String client_point_source) {
        this.client_point_source = client_point_source;
    }
    public String getClient_point_date() {
        return this.client_point_date;
    }
    public void setClient_point_date(String client_point_date) {
        this.client_point_date = client_point_date;
    }
    public float getClient_point_balance() {
        return this.client_point_balance;
    }
    public void setClient_point_balance(float client_point_balance) {
        this.client_point_balance = client_point_balance;
    }
    public String getClient_point_operator() {
        return this.client_point_operator;
    }
    public void setClient_point_operator(String client_point_operator) {
        this.client_point_operator = client_point_operator;
    }
    public String getClient_point_ref_bill_no() {
        return this.client_point_ref_bill_no;
    }
    public void setClient_point_ref_bill_no(String client_point_ref_bill_no) {
        this.client_point_ref_bill_no = client_point_ref_bill_no;
    }
    public String getClient_point_memo() {
        return this.client_point_memo;
    }
    public void setClient_point_memo(String client_point_memo) {
        this.client_point_memo = client_point_memo;
    }
    public String getClient_point_operate_type() {
        return this.client_point_operate_type;
    }
    public void setClient_point_operate_type(String client_point_operate_type) {
        this.client_point_operate_type = client_point_operate_type;
    }
    public String getClient_point_uuid() {
        return this.client_point_uuid;
    }
    public void setClient_point_uuid(String client_point_uuid) {
        this.client_point_uuid = client_point_uuid;
    }
    public boolean getClient_point_del_flag() {
        return this.client_point_del_flag;
    }
    public void setClient_point_del_flag(boolean client_point_del_flag) {
        this.client_point_del_flag = client_point_del_flag;
    }
    public boolean getClient_point_sync() {
        return this.client_point_sync;
    }
    public void setClient_point_sync(boolean client_point_sync) {
        this.client_point_sync = client_point_sync;
    }
    public String getShift_table_bizday() {
        return this.shift_table_bizday;
    }
    public void setShift_table_bizday(String shift_table_bizday) {
        this.shift_table_bizday = shift_table_bizday;
    }
    public int getShift_table_num() {
        return this.shift_table_num;
    }
    public void setShift_table_num(int shift_table_num) {
        this.shift_table_num = shift_table_num;
    }
    public String getClient_point_rule_id() {
        return this.client_point_rule_id;
    }
    public void setClient_point_rule_id(String client_point_rule_id) {
        this.client_point_rule_id = client_point_rule_id;
    }
    public String getClient_point_last_edit_time() {
        return this.client_point_last_edit_time;
    }
    public void setClient_point_last_edit_time(String client_point_last_edit_time) {
        this.client_point_last_edit_time = client_point_last_edit_time;
    }

    
   
}
