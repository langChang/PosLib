package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

@Entity
public class CardDepositFailed {
    /**
     * 卡储值失败bean
     */

    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;

    private String deposit_fid;
    private String system_book_code;
    private Integer branch_num;
    private Integer shift_table_num;
    private String shift_table_bizday;
    private Integer deposit_cust_num;
    private Integer deposit_card_type;
    private String deposit_printed_num;
    private Integer deposit_category;
    private String deposit_type;
    private double deposit_balance;
    private double deposit_cash;
    private double deposit_money;
    private double deposit_point;
    private double deposit_invoice;
    private String deposit_date;
    private String deposit_operator;
    private Integer deposit_count;
    private Integer deposit_payment_type;
    private String deposit_payment_type_name;
    private String deposit_bank_name;
    private String deposit_billref;
    private String deposit_memo;
    private String deposit_start_date;
    private String deposit_deadline;
    private String deposit_physical_num;
    private String deposit_cust_name;
    private String deposit_branch_name;
    private Integer deposit_balance_detail_num;
    private Integer account_bank_num;
    private String deposit_machine;
    private Boolean deposit_settlement_flag;
    private Boolean deposit_repealed;
    private Boolean deposit_notice_flag;
    private String deposit_open_id;
    private String client_fid;
    private String deposit_anti_fid;
    private String deposit_seller;
    private String deposit_synch_date;
    private String deposit_source;

    private String card_print_num;
    private String card_phone;
    private String card_type;

    @Transient
    private boolean isStrangeOk = false;

    @Transient
    private boolean isOnlineOk = false;



    public boolean isOnlineOk() {
        return isOnlineOk;
    }

    public void setOnlineOk(boolean onlineOk) {
        isOnlineOk = onlineOk;
    }

    public boolean isStrangeOk() {
        return isStrangeOk;
    }

    public void setStrangeOk(boolean strangeOk) {
        isStrangeOk = strangeOk;
    }



    //临时属性
    private String card_user_num;//额外添加

    private String deposit_crypt;
    private Boolean from_pos = false;
    private String re_send_fid;
    @Generated(hash = 189081047)
    public CardDepositFailed(Long id, String deposit_fid, String system_book_code,
            Integer branch_num, Integer shift_table_num, String shift_table_bizday,
            Integer deposit_cust_num, Integer deposit_card_type,
            String deposit_printed_num, Integer deposit_category,
            String deposit_type, double deposit_balance, double deposit_cash,
            double deposit_money, double deposit_point, double deposit_invoice,
            String deposit_date, String deposit_operator, Integer deposit_count,
            Integer deposit_payment_type, String deposit_payment_type_name,
            String deposit_bank_name, String deposit_billref, String deposit_memo,
            String deposit_start_date, String deposit_deadline,
            String deposit_physical_num, String deposit_cust_name,
            String deposit_branch_name, Integer deposit_balance_detail_num,
            Integer account_bank_num, String deposit_machine,
            Boolean deposit_settlement_flag, Boolean deposit_repealed,
            Boolean deposit_notice_flag, String deposit_open_id, String client_fid,
            String deposit_anti_fid, String deposit_seller,
            String deposit_synch_date, String deposit_source, String card_print_num,
            String card_phone, String card_type, String card_user_num,
            String deposit_crypt, Boolean from_pos, String re_send_fid) {
        this.id = id;
        this.deposit_fid = deposit_fid;
        this.system_book_code = system_book_code;
        this.branch_num = branch_num;
        this.shift_table_num = shift_table_num;
        this.shift_table_bizday = shift_table_bizday;
        this.deposit_cust_num = deposit_cust_num;
        this.deposit_card_type = deposit_card_type;
        this.deposit_printed_num = deposit_printed_num;
        this.deposit_category = deposit_category;
        this.deposit_type = deposit_type;
        this.deposit_balance = deposit_balance;
        this.deposit_cash = deposit_cash;
        this.deposit_money = deposit_money;
        this.deposit_point = deposit_point;
        this.deposit_invoice = deposit_invoice;
        this.deposit_date = deposit_date;
        this.deposit_operator = deposit_operator;
        this.deposit_count = deposit_count;
        this.deposit_payment_type = deposit_payment_type;
        this.deposit_payment_type_name = deposit_payment_type_name;
        this.deposit_bank_name = deposit_bank_name;
        this.deposit_billref = deposit_billref;
        this.deposit_memo = deposit_memo;
        this.deposit_start_date = deposit_start_date;
        this.deposit_deadline = deposit_deadline;
        this.deposit_physical_num = deposit_physical_num;
        this.deposit_cust_name = deposit_cust_name;
        this.deposit_branch_name = deposit_branch_name;
        this.deposit_balance_detail_num = deposit_balance_detail_num;
        this.account_bank_num = account_bank_num;
        this.deposit_machine = deposit_machine;
        this.deposit_settlement_flag = deposit_settlement_flag;
        this.deposit_repealed = deposit_repealed;
        this.deposit_notice_flag = deposit_notice_flag;
        this.deposit_open_id = deposit_open_id;
        this.client_fid = client_fid;
        this.deposit_anti_fid = deposit_anti_fid;
        this.deposit_seller = deposit_seller;
        this.deposit_synch_date = deposit_synch_date;
        this.deposit_source = deposit_source;
        this.card_print_num = card_print_num;
        this.card_phone = card_phone;
        this.card_type = card_type;
        this.card_user_num = card_user_num;
        this.deposit_crypt = deposit_crypt;
        this.from_pos = from_pos;
        this.re_send_fid = re_send_fid;
    }

    @Generated(hash = 1900191829)
    public CardDepositFailed() {
    }

    public String getCard_print_num() {
        return card_print_num;
    }

    public void setCard_print_num(String card_print_num) {
        this.card_print_num = card_print_num;
    }

    public String getCard_phone() {
        return card_phone;
    }

    public void setCard_phone(String card_phone) {
        this.card_phone = card_phone;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDeposit_fid() {
        return this.deposit_fid;
    }
    public void setDeposit_fid(String deposit_fid) {
        this.deposit_fid = deposit_fid;
    }
    public String getSystem_book_code() {
        return this.system_book_code;
    }
    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }
    public Integer getBranch_num() {
        return this.branch_num;
    }
    public void setBranch_num(Integer branch_num) {
        this.branch_num = branch_num;
    }
    public Integer getShift_table_num() {
        return this.shift_table_num;
    }
    public void setShift_table_num(Integer shift_table_num) {
        this.shift_table_num = shift_table_num;
    }
    public String getShift_table_bizday() {
        return this.shift_table_bizday;
    }
    public void setShift_table_bizday(String shift_table_bizday) {
        this.shift_table_bizday = shift_table_bizday;
    }
    public Integer getDeposit_cust_num() {
        return this.deposit_cust_num;
    }
    public void setDeposit_cust_num(Integer deposit_cust_num) {
        this.deposit_cust_num = deposit_cust_num;
    }
    public Integer getDeposit_card_type() {
        return this.deposit_card_type;
    }
    public void setDeposit_card_type(Integer deposit_card_type) {
        this.deposit_card_type = deposit_card_type;
    }
    public String getDeposit_printed_num() {
        return this.deposit_printed_num;
    }
    public void setDeposit_printed_num(String deposit_printed_num) {
        this.deposit_printed_num = deposit_printed_num;
    }
    public Integer getDeposit_category() {
        return this.deposit_category;
    }
    public void setDeposit_category(Integer deposit_category) {
        this.deposit_category = deposit_category;
    }
    public String getDeposit_type() {
        return this.deposit_type;
    }
    public void setDeposit_type(String deposit_type) {
        this.deposit_type = deposit_type;
    }
    public double getDeposit_balance() {
        return this.deposit_balance;
    }
    public void setDeposit_balance(double deposit_balance) {
        this.deposit_balance = deposit_balance;
    }
    public double getDeposit_cash() {
        return this.deposit_cash;
    }
    public void setDeposit_cash(double deposit_cash) {
        this.deposit_cash = deposit_cash;
    }
    public double getDeposit_money() {
        return this.deposit_money;
    }
    public void setDeposit_money(double deposit_money) {
        this.deposit_money = deposit_money;
    }
    public double getDeposit_point() {
        return this.deposit_point;
    }
    public void setDeposit_point(double deposit_point) {
        this.deposit_point = deposit_point;
    }
    public double getDeposit_invoice() {
        return this.deposit_invoice;
    }
    public void setDeposit_invoice(double deposit_invoice) {
        this.deposit_invoice = deposit_invoice;
    }
    public String getDeposit_date() {
        return this.deposit_date;
    }
    public void setDeposit_date(String deposit_date) {
        this.deposit_date = deposit_date;
    }
    public String getDeposit_operator() {
        return this.deposit_operator;
    }
    public void setDeposit_operator(String deposit_operator) {
        this.deposit_operator = deposit_operator;
    }
    public Integer getDeposit_count() {
        return this.deposit_count;
    }
    public void setDeposit_count(Integer deposit_count) {
        this.deposit_count = deposit_count;
    }
    public Integer getDeposit_payment_type() {
        return this.deposit_payment_type;
    }
    public void setDeposit_payment_type(Integer deposit_payment_type) {
        this.deposit_payment_type = deposit_payment_type;
    }
    public String getDeposit_payment_type_name() {
        return this.deposit_payment_type_name;
    }
    public void setDeposit_payment_type_name(String deposit_payment_type_name) {
        this.deposit_payment_type_name = deposit_payment_type_name;
    }
    public String getDeposit_bank_name() {
        return this.deposit_bank_name;
    }
    public void setDeposit_bank_name(String deposit_bank_name) {
        this.deposit_bank_name = deposit_bank_name;
    }
    public String getDeposit_billref() {
        return this.deposit_billref;
    }
    public void setDeposit_billref(String deposit_billref) {
        this.deposit_billref = deposit_billref;
    }
    public String getDeposit_memo() {
        return this.deposit_memo;
    }
    public void setDeposit_memo(String deposit_memo) {
        this.deposit_memo = deposit_memo;
    }
    public String getDeposit_start_date() {
        return this.deposit_start_date;
    }
    public void setDeposit_start_date(String deposit_start_date) {
        this.deposit_start_date = deposit_start_date;
    }
    public String getDeposit_deadline() {
        return this.deposit_deadline;
    }
    public void setDeposit_deadline(String deposit_deadline) {
        this.deposit_deadline = deposit_deadline;
    }
    public String getDeposit_physical_num() {
        return this.deposit_physical_num;
    }
    public void setDeposit_physical_num(String deposit_physical_num) {
        this.deposit_physical_num = deposit_physical_num;
    }
    public String getDeposit_cust_name() {
        return this.deposit_cust_name;
    }
    public void setDeposit_cust_name(String deposit_cust_name) {
        this.deposit_cust_name = deposit_cust_name;
    }
    public String getDeposit_branch_name() {
        return this.deposit_branch_name;
    }
    public void setDeposit_branch_name(String deposit_branch_name) {
        this.deposit_branch_name = deposit_branch_name;
    }
    public Integer getDeposit_balance_detail_num() {
        return this.deposit_balance_detail_num;
    }
    public void setDeposit_balance_detail_num(Integer deposit_balance_detail_num) {
        this.deposit_balance_detail_num = deposit_balance_detail_num;
    }
    public Integer getAccount_bank_num() {
        return this.account_bank_num;
    }
    public void setAccount_bank_num(Integer account_bank_num) {
        this.account_bank_num = account_bank_num;
    }
    public String getDeposit_machine() {
        return this.deposit_machine;
    }
    public void setDeposit_machine(String deposit_machine) {
        this.deposit_machine = deposit_machine;
    }
    public Boolean getDeposit_settlement_flag() {
        return this.deposit_settlement_flag;
    }
    public void setDeposit_settlement_flag(Boolean deposit_settlement_flag) {
        this.deposit_settlement_flag = deposit_settlement_flag;
    }
    public Boolean getDeposit_repealed() {
        return this.deposit_repealed;
    }
    public void setDeposit_repealed(Boolean deposit_repealed) {
        this.deposit_repealed = deposit_repealed;
    }
    public Boolean getDeposit_notice_flag() {
        return this.deposit_notice_flag;
    }
    public void setDeposit_notice_flag(Boolean deposit_notice_flag) {
        this.deposit_notice_flag = deposit_notice_flag;
    }
    public String getDeposit_open_id() {
        return this.deposit_open_id;
    }
    public void setDeposit_open_id(String deposit_open_id) {
        this.deposit_open_id = deposit_open_id;
    }
    public String getClient_fid() {
        return this.client_fid;
    }
    public void setClient_fid(String client_fid) {
        this.client_fid = client_fid;
    }
    public String getDeposit_anti_fid() {
        return this.deposit_anti_fid;
    }
    public void setDeposit_anti_fid(String deposit_anti_fid) {
        this.deposit_anti_fid = deposit_anti_fid;
    }
    public String getDeposit_seller() {
        return this.deposit_seller;
    }
    public void setDeposit_seller(String deposit_seller) {
        this.deposit_seller = deposit_seller;
    }
    public String getDeposit_synch_date() {
        return this.deposit_synch_date;
    }
    public void setDeposit_synch_date(String deposit_synch_date) {
        this.deposit_synch_date = deposit_synch_date;
    }
    public String getDeposit_source() {
        return this.deposit_source;
    }
    public void setDeposit_source(String deposit_source) {
        this.deposit_source = deposit_source;
    }
    public String getCard_user_num() {
        return this.card_user_num;
    }
    public void setCard_user_num(String card_user_num) {
        this.card_user_num = card_user_num;
    }
    public String getDeposit_crypt() {
        return this.deposit_crypt;
    }
    public void setDeposit_crypt(String deposit_crypt) {
        this.deposit_crypt = deposit_crypt;
    }
    public Boolean getFrom_pos() {
        return this.from_pos;
    }
    public void setFrom_pos(Boolean from_pos) {
        this.from_pos = from_pos;
    }
    public String getRe_send_fid() {
        return this.re_send_fid;
    }
    public void setRe_send_fid(String re_send_fid) {
        this.re_send_fid = re_send_fid;
    }



}
