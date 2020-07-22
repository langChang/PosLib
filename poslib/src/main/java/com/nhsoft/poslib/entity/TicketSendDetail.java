package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Iverson on 2019/1/14 12:12 PM
 * 此类用于：
 */

@Entity
public class TicketSendDetail {

    @Id
    private String  ticket_send_fid;
    private int     ticket_send_detail_num;
    private String  ticket_send_detail_print_num;
    private float   ticket_send_detail_value;
    private String  ticket_send_detail_date;
    private String  ticket_send_detail_valid_date;
    private String  ticket_send_detail_type;
    private int     ticket_send_detail_state_code;
    private String  ticket_send_detail_state_name;
    private String  ticket_send_detail_memo;
    private boolean ticket_send_detail_sync_flag;
    private String  ticket_send_detail_uuid;
    private String  ticket_send_bar_code;
    private String  ticket_send_detail_branch;
    private int     ticket_send_detail_branch_num;
    private String  ticket_send_detail_time;
    private String ticket_send_detail_user;
    private String action_id;
    private int ticket_send_detail_card_num;
    private String ticket_send_detail_order_no;
    private float ticket_send_detail_order_money;
    private String ticket_send_detail_send_time;
    private int ticket_send_detail_send_branch;
    private String ticket_send_detail_valid_start;
    private String ticket_send_detail_pre_order_no;
    private String ticket_send_detail_source;
    private String ticket_send_detail_use_source;
    private String ticket_send_detail_password;
    private String ticket_send_detail_open_id;
    private String ticket_send_detail_ref_bill;
    private String system_book_code;
    private String ticket_send_detail_phone;

    private int branch_num;
    private int shiftTableNum;

    private String ticket_send_detail_type_code;
    private Long coupon_action_detail_id;


    @Generated(hash = 323069201)
    public TicketSendDetail(String ticket_send_fid, int ticket_send_detail_num,
            String ticket_send_detail_print_num, float ticket_send_detail_value,
            String ticket_send_detail_date, String ticket_send_detail_valid_date,
            String ticket_send_detail_type, int ticket_send_detail_state_code,
            String ticket_send_detail_state_name, String ticket_send_detail_memo,
            boolean ticket_send_detail_sync_flag, String ticket_send_detail_uuid,
            String ticket_send_bar_code, String ticket_send_detail_branch,
            int ticket_send_detail_branch_num, String ticket_send_detail_time,
            String ticket_send_detail_user, String action_id, int ticket_send_detail_card_num,
            String ticket_send_detail_order_no, float ticket_send_detail_order_money,
            String ticket_send_detail_send_time, int ticket_send_detail_send_branch,
            String ticket_send_detail_valid_start, String ticket_send_detail_pre_order_no,
            String ticket_send_detail_source, String ticket_send_detail_use_source,
            String ticket_send_detail_password, String ticket_send_detail_open_id,
            String ticket_send_detail_ref_bill, String system_book_code,
            String ticket_send_detail_phone, int branch_num, int shiftTableNum,
            String ticket_send_detail_type_code, Long coupon_action_detail_id) {
        this.ticket_send_fid = ticket_send_fid;
        this.ticket_send_detail_num = ticket_send_detail_num;
        this.ticket_send_detail_print_num = ticket_send_detail_print_num;
        this.ticket_send_detail_value = ticket_send_detail_value;
        this.ticket_send_detail_date = ticket_send_detail_date;
        this.ticket_send_detail_valid_date = ticket_send_detail_valid_date;
        this.ticket_send_detail_type = ticket_send_detail_type;
        this.ticket_send_detail_state_code = ticket_send_detail_state_code;
        this.ticket_send_detail_state_name = ticket_send_detail_state_name;
        this.ticket_send_detail_memo = ticket_send_detail_memo;
        this.ticket_send_detail_sync_flag = ticket_send_detail_sync_flag;
        this.ticket_send_detail_uuid = ticket_send_detail_uuid;
        this.ticket_send_bar_code = ticket_send_bar_code;
        this.ticket_send_detail_branch = ticket_send_detail_branch;
        this.ticket_send_detail_branch_num = ticket_send_detail_branch_num;
        this.ticket_send_detail_time = ticket_send_detail_time;
        this.ticket_send_detail_user = ticket_send_detail_user;
        this.action_id = action_id;
        this.ticket_send_detail_card_num = ticket_send_detail_card_num;
        this.ticket_send_detail_order_no = ticket_send_detail_order_no;
        this.ticket_send_detail_order_money = ticket_send_detail_order_money;
        this.ticket_send_detail_send_time = ticket_send_detail_send_time;
        this.ticket_send_detail_send_branch = ticket_send_detail_send_branch;
        this.ticket_send_detail_valid_start = ticket_send_detail_valid_start;
        this.ticket_send_detail_pre_order_no = ticket_send_detail_pre_order_no;
        this.ticket_send_detail_source = ticket_send_detail_source;
        this.ticket_send_detail_use_source = ticket_send_detail_use_source;
        this.ticket_send_detail_password = ticket_send_detail_password;
        this.ticket_send_detail_open_id = ticket_send_detail_open_id;
        this.ticket_send_detail_ref_bill = ticket_send_detail_ref_bill;
        this.system_book_code = system_book_code;
        this.ticket_send_detail_phone = ticket_send_detail_phone;
        this.branch_num = branch_num;
        this.shiftTableNum = shiftTableNum;
        this.ticket_send_detail_type_code = ticket_send_detail_type_code;
        this.coupon_action_detail_id = coupon_action_detail_id;
    }

    @Generated(hash = 938100872)
    public TicketSendDetail() {
    }
    

    public int getShiftTableNum() {
        return shiftTableNum;
    }

    public void setShiftTableNum(int shiftTableNum) {
        this.shiftTableNum = shiftTableNum;
    }

    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }



  

    public String getTicket_send_fid() {
        return this.ticket_send_fid;
    }
    public void setTicket_send_fid(String ticket_send_fid) {
        this.ticket_send_fid = ticket_send_fid;
    }
    public int getTicket_send_detail_num() {
        return this.ticket_send_detail_num;
    }
    public void setTicket_send_detail_num(int ticket_send_detail_num) {
        this.ticket_send_detail_num = ticket_send_detail_num;
    }
    public String getTicket_send_detail_print_num() {
        return this.ticket_send_detail_print_num;
    }
    public void setTicket_send_detail_print_num(
            String ticket_send_detail_print_num) {
        this.ticket_send_detail_print_num = ticket_send_detail_print_num;
    }
    public float getTicket_send_detail_value() {
        return this.ticket_send_detail_value;
    }
    public void setTicket_send_detail_value(float ticket_send_detail_value) {
        this.ticket_send_detail_value = ticket_send_detail_value;
    }
    public String getTicket_send_detail_date() {
        return this.ticket_send_detail_date;
    }
    public void setTicket_send_detail_date(String ticket_send_detail_date) {
        this.ticket_send_detail_date = ticket_send_detail_date;
    }
    public String getTicket_send_detail_valid_date() {
        return this.ticket_send_detail_valid_date;
    }
    public void setTicket_send_detail_valid_date(
            String ticket_send_detail_valid_date) {
        this.ticket_send_detail_valid_date = ticket_send_detail_valid_date;
    }
    public String getTicket_send_detail_type() {
        return this.ticket_send_detail_type;
    }
    public void setTicket_send_detail_type(String ticket_send_detail_type) {
        this.ticket_send_detail_type = ticket_send_detail_type;
    }
    public int getTicket_send_detail_state_code() {
        return this.ticket_send_detail_state_code;
    }
    public void setTicket_send_detail_state_code(
            int ticket_send_detail_state_code) {
        this.ticket_send_detail_state_code = ticket_send_detail_state_code;
    }
    public String getTicket_send_detail_state_name() {
        return this.ticket_send_detail_state_name;
    }
    public void setTicket_send_detail_state_name(
            String ticket_send_detail_state_name) {
        this.ticket_send_detail_state_name = ticket_send_detail_state_name;
    }
    public String getTicket_send_detail_memo() {
        return this.ticket_send_detail_memo;
    }
    public void setTicket_send_detail_memo(String ticket_send_detail_memo) {
        this.ticket_send_detail_memo = ticket_send_detail_memo;
    }
    public boolean isTicket_send_detail_sync_flag() {
        return this.ticket_send_detail_sync_flag;
    }

    public boolean setTicket_send_detail_sync_flag(boolean ticket_send_detail_sync_flag) {
        return this.ticket_send_detail_sync_flag = ticket_send_detail_sync_flag;
    }

    public String getTicket_send_detail_uuid() {
        return this.ticket_send_detail_uuid;
    }
    public void setTicket_send_detail_uuid(String ticket_send_detail_uuid) {
        this.ticket_send_detail_uuid = ticket_send_detail_uuid;
    }
    public String getTicket_send_bar_code() {
        return this.ticket_send_bar_code;
    }
    public void setTicket_send_bar_code(String ticket_send_bar_code) {
        this.ticket_send_bar_code = ticket_send_bar_code;
    }
    public String getTicket_send_detail_branch() {
        return this.ticket_send_detail_branch;
    }
    public void setTicket_send_detail_branch(String ticket_send_detail_branch) {
        this.ticket_send_detail_branch = ticket_send_detail_branch;
    }
    public int getTicket_send_detail_branch_num() {
        return this.ticket_send_detail_branch_num;
    }
    public void setTicket_send_detail_branch_num(
            int ticket_send_detail_branch_num) {
        this.ticket_send_detail_branch_num = ticket_send_detail_branch_num;
    }
    public String getTicket_send_detail_user() {
        return this.ticket_send_detail_user;
    }
    public void setTicket_send_detail_user(String ticket_send_detail_user) {
        this.ticket_send_detail_user = ticket_send_detail_user;
    }
    public String getAction_id() {
        return this.action_id;
    }
    public void setAction_id(String action_id) {
        this.action_id = action_id;
    }
    public int getTicket_send_detail_card_num() {
        return this.ticket_send_detail_card_num;
    }
    public void setTicket_send_detail_card_num(int ticket_send_detail_card_num) {
        this.ticket_send_detail_card_num = ticket_send_detail_card_num;
    }
    public String getTicket_send_detail_order_no() {
        return this.ticket_send_detail_order_no;
    }
    public void setTicket_send_detail_order_no(String ticket_send_detail_order_no) {
        this.ticket_send_detail_order_no = ticket_send_detail_order_no;
    }
    public float getTicket_send_detail_order_money() {
        return this.ticket_send_detail_order_money;
    }
    public void setTicket_send_detail_order_money(
            float ticket_send_detail_order_money) {
        this.ticket_send_detail_order_money = ticket_send_detail_order_money;
    }
    public String getTicket_send_detail_send_time() {
        return this.ticket_send_detail_send_time;
    }
    public void setTicket_send_detail_send_time(
            String ticket_send_detail_send_time) {
        this.ticket_send_detail_send_time = ticket_send_detail_send_time;
    }
    public int getTicket_send_detail_send_branch() {
        return this.ticket_send_detail_send_branch;
    }
    public void setTicket_send_detail_send_branch(
            int ticket_send_detail_send_branch) {
        this.ticket_send_detail_send_branch = ticket_send_detail_send_branch;
    }
    public String getTicket_send_detail_valid_start() {
        return this.ticket_send_detail_valid_start;
    }
    public void setTicket_send_detail_valid_start(
            String ticket_send_detail_valid_start) {
        this.ticket_send_detail_valid_start = ticket_send_detail_valid_start;
    }
    public String getTicket_send_detail_pre_order_no() {
        return this.ticket_send_detail_pre_order_no;
    }
    public void setTicket_send_detail_pre_order_no(
            String ticket_send_detail_pre_order_no) {
        this.ticket_send_detail_pre_order_no = ticket_send_detail_pre_order_no;
    }
    public String getTicket_send_detail_source() {
        return this.ticket_send_detail_source;
    }
    public void setTicket_send_detail_source(String ticket_send_detail_source) {
        this.ticket_send_detail_source = ticket_send_detail_source;
    }
    public String getTicket_send_detail_use_source() {
        return this.ticket_send_detail_use_source;
    }
    public void setTicket_send_detail_use_source(
            String ticket_send_detail_use_source) {
        this.ticket_send_detail_use_source = ticket_send_detail_use_source;
    }
    public String getTicket_send_detail_password() {
        return this.ticket_send_detail_password;
    }
    public void setTicket_send_detail_password(String ticket_send_detail_password) {
        this.ticket_send_detail_password = ticket_send_detail_password;
    }
    public String getTicket_send_detail_open_id() {
        return this.ticket_send_detail_open_id;
    }
    public void setTicket_send_detail_open_id(String ticket_send_detail_open_id) {
        this.ticket_send_detail_open_id = ticket_send_detail_open_id;
    }
    public String getTicket_send_detail_ref_bill() {
        return this.ticket_send_detail_ref_bill;
    }
    public void setTicket_send_detail_ref_bill(String ticket_send_detail_ref_bill) {
        this.ticket_send_detail_ref_bill = ticket_send_detail_ref_bill;
    }
    public String getSystem_book_code() {
        return this.system_book_code;
    }
    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }
    public String getTicket_send_detail_time() {
        return this.ticket_send_detail_time;
    }
    public void setTicket_send_detail_time(String ticket_send_detail_time) {
        this.ticket_send_detail_time = ticket_send_detail_time;
    }

    public boolean getTicket_send_detail_sync_flag() {
        return this.ticket_send_detail_sync_flag;
    }

    public String getTicket_send_detail_type_code() {
        return this.ticket_send_detail_type_code;
    }

    public void setTicket_send_detail_type_code(String ticket_send_detail_type_code) {
        this.ticket_send_detail_type_code = ticket_send_detail_type_code;
    }

    public Long getCoupon_action_detail_id() {
        return this.coupon_action_detail_id;
    }

    public void setCoupon_action_detail_id(Long coupon_action_detail_id) {
        this.coupon_action_detail_id = coupon_action_detail_id;
    }

    public String getTicket_send_detail_phone() {
        return this.ticket_send_detail_phone;
    }

    public void setTicket_send_detail_phone(String ticket_send_detail_phone) {
        this.ticket_send_detail_phone = ticket_send_detail_phone;
    }

}
