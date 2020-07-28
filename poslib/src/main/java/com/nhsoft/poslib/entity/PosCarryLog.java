package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Iverson on 2019/4/12 2:19 PM
 * 此类用于：
 */
@Entity
public class PosCarryLog {

    @Id(autoincrement = true)
    private Long id;
    private int branch_num;
    private int merchant_num;
    private float retail_pos_log_amount;
    private String retail_pos_log_authorize;
    private String retail_pos_log_bizday;
    private String retail_pos_log_item_name;
    private long retail_pos_log_item_num;
    private String retail_pos_log_machine;
    private String retail_pos_log_memo;
    private float retail_pos_log_money;
    private String retail_pos_log_operator;
    private String retail_pos_log_order_no;
    private float retail_pos_log_price;
    private int retail_pos_log_shift_num;
    private float retail_pos_log_std_price;
    private String retail_pos_log_time;
    private String retail_pos_log_type;
    private long stall_num;
    private String system_book_code;
    private boolean isUpload;


    @Generated(hash = 1092750441)
    public PosCarryLog(Long id, int branch_num, int merchant_num,
            float retail_pos_log_amount, String retail_pos_log_authorize,
            String retail_pos_log_bizday, String retail_pos_log_item_name,
            long retail_pos_log_item_num, String retail_pos_log_machine,
            String retail_pos_log_memo, float retail_pos_log_money,
            String retail_pos_log_operator, String retail_pos_log_order_no,
            float retail_pos_log_price, int retail_pos_log_shift_num,
            float retail_pos_log_std_price, String retail_pos_log_time,
            String retail_pos_log_type, long stall_num, String system_book_code,
            boolean isUpload) {
        this.id = id;
        this.branch_num = branch_num;
        this.merchant_num = merchant_num;
        this.retail_pos_log_amount = retail_pos_log_amount;
        this.retail_pos_log_authorize = retail_pos_log_authorize;
        this.retail_pos_log_bizday = retail_pos_log_bizday;
        this.retail_pos_log_item_name = retail_pos_log_item_name;
        this.retail_pos_log_item_num = retail_pos_log_item_num;
        this.retail_pos_log_machine = retail_pos_log_machine;
        this.retail_pos_log_memo = retail_pos_log_memo;
        this.retail_pos_log_money = retail_pos_log_money;
        this.retail_pos_log_operator = retail_pos_log_operator;
        this.retail_pos_log_order_no = retail_pos_log_order_no;
        this.retail_pos_log_price = retail_pos_log_price;
        this.retail_pos_log_shift_num = retail_pos_log_shift_num;
        this.retail_pos_log_std_price = retail_pos_log_std_price;
        this.retail_pos_log_time = retail_pos_log_time;
        this.retail_pos_log_type = retail_pos_log_type;
        this.stall_num = stall_num;
        this.system_book_code = system_book_code;
        this.isUpload = isUpload;
    }
    @Generated(hash = 2038234693)
    public PosCarryLog() {
    }


    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getBranch_num() {
        return this.branch_num;
    }
    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }
    public int getMerchant_num() {
        return this.merchant_num;
    }
    public void setMerchant_num(int merchant_num) {
        this.merchant_num = merchant_num;
    }
    public float getRetail_pos_log_amount() {
        return this.retail_pos_log_amount;
    }
    public void setRetail_pos_log_amount(float retail_pos_log_amount) {
        this.retail_pos_log_amount = retail_pos_log_amount;
    }
    public String getRetail_pos_log_authorize() {
        return this.retail_pos_log_authorize;
    }
    public void setRetail_pos_log_authorize(String retail_pos_log_authorize) {
        this.retail_pos_log_authorize = retail_pos_log_authorize;
    }
    public String getRetail_pos_log_bizday() {
        return this.retail_pos_log_bizday;
    }
    public void setRetail_pos_log_bizday(String retail_pos_log_bizday) {
        this.retail_pos_log_bizday = retail_pos_log_bizday;
    }
    public String getRetail_pos_log_item_name() {
        return this.retail_pos_log_item_name;
    }
    public void setRetail_pos_log_item_name(String retail_pos_log_item_name) {
        this.retail_pos_log_item_name = retail_pos_log_item_name;
    }
    public long getRetail_pos_log_item_num() {
        return this.retail_pos_log_item_num;
    }
    public void setRetail_pos_log_item_num(long retail_pos_log_item_num) {
        this.retail_pos_log_item_num = retail_pos_log_item_num;
    }
    public String getRetail_pos_log_machine() {
        return this.retail_pos_log_machine;
    }
    public void setRetail_pos_log_machine(String retail_pos_log_machine) {
        this.retail_pos_log_machine = retail_pos_log_machine;
    }
    public String getRetail_pos_log_memo() {
        return this.retail_pos_log_memo;
    }
    public void setRetail_pos_log_memo(String retail_pos_log_memo) {
        this.retail_pos_log_memo = retail_pos_log_memo;
    }
    public float getRetail_pos_log_money() {
        return this.retail_pos_log_money;
    }
    public void setRetail_pos_log_money(float retail_pos_log_money) {
        this.retail_pos_log_money = retail_pos_log_money;
    }
    public String getRetail_pos_log_operator() {
        return this.retail_pos_log_operator;
    }
    public void setRetail_pos_log_operator(String retail_pos_log_operator) {
        this.retail_pos_log_operator = retail_pos_log_operator;
    }
    public String getRetail_pos_log_order_no() {
        return this.retail_pos_log_order_no;
    }
    public void setRetail_pos_log_order_no(String retail_pos_log_order_no) {
        this.retail_pos_log_order_no = retail_pos_log_order_no;
    }
    public float getRetail_pos_log_price() {
        return this.retail_pos_log_price;
    }
    public void setRetail_pos_log_price(float retail_pos_log_price) {
        this.retail_pos_log_price = retail_pos_log_price;
    }
    public int getRetail_pos_log_shift_num() {
        return this.retail_pos_log_shift_num;
    }
    public void setRetail_pos_log_shift_num(int retail_pos_log_shift_num) {
        this.retail_pos_log_shift_num = retail_pos_log_shift_num;
    }
    public float getRetail_pos_log_std_price() {
        return this.retail_pos_log_std_price;
    }
    public void setRetail_pos_log_std_price(float retail_pos_log_std_price) {
        this.retail_pos_log_std_price = retail_pos_log_std_price;
    }
    public String getRetail_pos_log_time() {
        return this.retail_pos_log_time;
    }
    public void setRetail_pos_log_time(String retail_pos_log_time) {
        this.retail_pos_log_time = retail_pos_log_time;
    }
    public String getRetail_pos_log_type() {
        return this.retail_pos_log_type;
    }
    public void setRetail_pos_log_type(String retail_pos_log_type) {
        this.retail_pos_log_type = retail_pos_log_type;
    }
    public long getStall_num() {
        return this.stall_num;
    }
    public void setStall_num(long stall_num) {
        this.stall_num = stall_num;
    }
    public String getSystem_book_code() {
        return this.system_book_code;
    }
    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public boolean isUpload() {
        return isUpload;
    }

    public void setUpload(boolean upload) {
        isUpload = upload;
    }
    public boolean getIsUpload() {
        return this.isUpload;
    }
    public void setIsUpload(boolean isUpload) {
        this.isUpload = isUpload;
    }


}
