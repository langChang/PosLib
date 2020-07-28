package com.nhsoft.poslib.entity.shift;


public class ShiftTableTemporary {
    /**
     * 临时bean 不存表
     */

    private int merchant_num;
    private int stall_num;
    private String shift_table_biz_date;//开班日期 yyyy-mm-dd
    private String shift_table_start;//接班时间
    private String shift_table_end;//交班时间
    private int shift_table_user_num;//收银员编号
    private String shift_table_user_code;//收银员代码
    private String shift_table_user_name;//班次的收银员名称
    private boolean shift_table_closed;//是否已经交班
    private boolean shift_table_synchronized;//是否已经上传
    private boolean shift_table_need_carry;//是否有流水
    private boolean shift_table_carried;//
    private String shift_table_terminal_id;//终端标识
    private boolean shift_table_dpc_synchronized;
    private float shift_table_actual_money;
    private float shift_table_actual_bank_money;
    private int shift_table_status;//默认0
    private String shift_table_memo;//
    private String shift_table_last_edit_time;//班次最后修改时间
    private String system_book_code;//账套号
    private int branch_num;//分店编号
    private int shift_table_num;//班次号 规则待补充
    private String shift_table_bizday;//营业日 yyyymmdd
    private int shift_table_upload_times;//该班次上传次数，每个班次最多上传三次，三次都检验失败，不管该班次 默认为0

    public int getMerchant_num() {
        return merchant_num;
    }

    public void setMerchant_num(int merchant_num) {
        this.merchant_num = merchant_num;
    }

    public int getStall_num() {
        return stall_num;
    }

    public void setStall_num(int stall_num) {
        this.stall_num = stall_num;
    }

    public String getShift_table_biz_date() {
        return shift_table_biz_date;
    }

    public void setShift_table_biz_date(String shift_table_biz_date) {
        this.shift_table_biz_date = shift_table_biz_date;
    }

    public String getShift_table_start() {
        return shift_table_start;
    }

    public void setShift_table_start(String shift_table_start) {
        this.shift_table_start = shift_table_start;
    }

    public String getShift_table_end() {
        return shift_table_end;
    }

    public void setShift_table_end(String shift_table_end) {
        this.shift_table_end = shift_table_end;
    }

    public int getShift_table_user_num() {
        return shift_table_user_num;
    }

    public void setShift_table_user_num(int shift_table_user_num) {
        this.shift_table_user_num = shift_table_user_num;
    }

    public String getShift_table_user_code() {
        return shift_table_user_code;
    }

    public void setShift_table_user_code(String shift_table_user_code) {
        this.shift_table_user_code = shift_table_user_code;
    }

    public String getShift_table_user_name() {
        return shift_table_user_name;
    }

    public void setShift_table_user_name(String shift_table_user_name) {
        this.shift_table_user_name = shift_table_user_name;
    }

    public boolean isShift_table_closed() {
        return shift_table_closed;
    }

    public void setShift_table_closed(boolean shift_table_closed) {
        this.shift_table_closed = shift_table_closed;
    }

    public boolean isShift_table_synchronized() {
        return shift_table_synchronized;
    }

    public void setShift_table_synchronized(boolean shift_table_synchronized) {
        this.shift_table_synchronized = shift_table_synchronized;
    }

    public boolean isShift_table_need_carry() {
        return shift_table_need_carry;
    }

    public void setShift_table_need_carry(boolean shift_table_need_carry) {
        this.shift_table_need_carry = shift_table_need_carry;
    }

    public boolean isShift_table_carried() {
        return shift_table_carried;
    }

    public void setShift_table_carried(boolean shift_table_carried) {
        this.shift_table_carried = shift_table_carried;
    }

    public String getShift_table_terminal_id() {
        return shift_table_terminal_id;
    }

    public void setShift_table_terminal_id(String shift_table_terminal_id) {
        this.shift_table_terminal_id = shift_table_terminal_id;
    }

    public boolean isShift_table_dpc_synchronized() {
        return shift_table_dpc_synchronized;
    }

    public void setShift_table_dpc_synchronized(boolean shift_table_dpc_synchronized) {
        this.shift_table_dpc_synchronized = shift_table_dpc_synchronized;
    }

    public float getShift_table_actual_money() {
        return shift_table_actual_money;
    }

    public void setShift_table_actual_money(float shift_table_actual_money) {
        this.shift_table_actual_money = shift_table_actual_money;
    }

    public float getShift_table_actual_bank_money() {
        return shift_table_actual_bank_money;
    }

    public void setShift_table_actual_bank_money(float shift_table_actual_bank_money) {
        this.shift_table_actual_bank_money = shift_table_actual_bank_money;
    }

    public int getShift_table_status() {
        return shift_table_status;
    }

    public void setShift_table_status(int shift_table_status) {
        this.shift_table_status = shift_table_status;
    }

    public String getShift_table_memo() {
        return shift_table_memo;
    }

    public void setShift_table_memo(String shift_table_memo) {
        this.shift_table_memo = shift_table_memo;
    }

    public String getShift_table_last_edit_time() {
        return shift_table_last_edit_time;
    }

    public void setShift_table_last_edit_time(String shift_table_last_edit_time) {
        this.shift_table_last_edit_time = shift_table_last_edit_time;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }

    public int getShift_table_num() {
        return shift_table_num;
    }

    public void setShift_table_num(int shift_table_num) {
        this.shift_table_num = shift_table_num;
    }

    public String getShift_table_bizday() {
        return shift_table_bizday;
    }

    public void setShift_table_bizday(String shift_table_bizday) {
        this.shift_table_bizday = shift_table_bizday;
    }

    public int getShift_table_upload_times() {
        return shift_table_upload_times;
    }

    public void setShift_table_upload_times(int shift_table_upload_times) {
        this.shift_table_upload_times = shift_table_upload_times;
    }
}
