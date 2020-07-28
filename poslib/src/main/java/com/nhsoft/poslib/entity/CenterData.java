package com.nhsoft.poslib.entity;

/**
 * Created by Iverson on 2019-05-09 16:02
 * 此类用于：
 */
public class CenterData {
    private int    branch_num;
    private String init_date_time; //yyyy-MM-dd HH:mm:ss  时间这种格式
    private String print_num;
    private int    card_code;
    private Long client_id;
    private String userName;
    private String userPhone;
    private String userBirth;
    private int    userSex;
    private String userIdType;
    private String userIdNum;
    private String card_send_time;
    private String card_status;
    private String eff_time;
    private long card_type;
    private long card_data_type;
    private String physic_print_num;

    public String getPhysic_print_num() {
        return physic_print_num;
    }

    public void setPhysic_print_num(String physic_print_num) {
        this.physic_print_num = physic_print_num;
    }

    public long getCard_data_type() {
        return card_data_type;
    }

    public void setCard_data_type(long card_data_type) {
        this.card_data_type = card_data_type;
    }

    private float  card_total_deposit;
    private float  card_user_balance;
    private float  card_user_point;
    private float  card_total_consume;

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }

    private String system_book_code;



    public float getCard_total_deposit() {
        return card_total_deposit;
    }

    public void setCard_total_deposit(float card_total_deposit) {
        this.card_total_deposit = card_total_deposit;
    }

    public float getCard_user_balance() {
        return card_user_balance;
    }

    public void setCard_user_balance(float card_user_balance) {
        this.card_user_balance = card_user_balance;
    }

    public float getCard_user_point() {
        return card_user_point;
    }

    public void setCard_user_point(float card_user_point) {
        this.card_user_point = card_user_point;
    }

    public float getCard_total_consume() {
        return card_total_consume;
    }

    public void setCard_total_consume(float card_total_consume) {
        this.card_total_consume = card_total_consume;
    }

    public long getCard_type() {
        return card_type;
    }

    public void setCard_type(long card_type) {
        this.card_type = card_type;
    }

    public String getEff_time() {
        return eff_time;
    }

    public void setEff_time(String eff_time) {
        this.eff_time = eff_time;
    }




    public String getCard_status() {
        return card_status;
    }

    public void setCard_status(String card_status) {
        this.card_status = card_status;
    }



    public String getCard_send_time() {
        return card_send_time;
    }

    public void setCard_send_time(String card_send_time) {
        this.card_send_time = card_send_time;
    }



    public String getUserIdType() {
        return userIdType;
    }

    public void setUserIdType(String userIdType) {
        this.userIdType = userIdType;
    }

    public String getUserIdNum() {
        return userIdNum;
    }

    public void setUserIdNum(String userIdNum) {
        this.userIdNum = userIdNum;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }



    public int getBranch_num() {
        return branch_num;
    }

    public void setBranch_num(int branch_num) {
        this.branch_num = branch_num;
    }


    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }


    public String getInit_date_time() {
        return init_date_time;
    }

    public void setInit_date_time(String init_date_time) {
        this.init_date_time = init_date_time;
    }

    public String getPrint_num() {
        return print_num;
    }

    public void setPrint_num(String print_num) {
        this.print_num = print_num;
    }

    public int getCard_code() {
        return card_code;
    }

    public void setCard_code(int card_code) {
        this.card_code = card_code;
    }
}
