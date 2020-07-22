package com.nhsoft.poslib.entity;

public class VipLossRecord {


    /**
     * 挂失记录
     *
     * card_user_num : String||卡主键
     * card_loss_operator : String||操作人
     * card_loss_branch_name : String||操作店铺
     * card_loss_memo : String||备注
     * system_book_code : String||账套号
     */

    private String card_user_num;
    private String card_loss_operator;
    private String card_loss_branch_name;
    private String card_loss_memo;
    private String system_book_code;
    private String card_loss_operate_name;//操作名称
    private String card_loss_operate_time;//操作时间

    public String getCard_loss_operate_name() {
        return card_loss_operate_name;
    }

    public void setCard_loss_operate_name(String card_loss_operate_name) {
        this.card_loss_operate_name = card_loss_operate_name;
    }

    public String getCard_loss_operate_time() {
        return card_loss_operate_time;
    }

    public void setCard_loss_operate_time(String card_loss_operate_time) {
        this.card_loss_operate_time = card_loss_operate_time;
    }

    public String getCard_user_num() {
        return card_user_num;
    }

    public void setCard_user_num(String card_user_num) {
        this.card_user_num = card_user_num;
    }

    public String getCard_loss_operator() {
        return card_loss_operator;
    }

    public void setCard_loss_operator(String card_loss_operator) {
        this.card_loss_operator = card_loss_operator;
    }

    public String getCard_loss_branch_name() {
        return card_loss_branch_name;
    }

    public void setCard_loss_branch_name(String card_loss_branch_name) {
        this.card_loss_branch_name = card_loss_branch_name;
    }

    public String getCard_loss_memo() {
        return card_loss_memo;
    }

    public void setCard_loss_memo(String card_loss_memo) {
        this.card_loss_memo = card_loss_memo;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }
}
