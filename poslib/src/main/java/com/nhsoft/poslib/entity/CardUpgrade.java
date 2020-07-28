package com.nhsoft.poslib.entity;

public class CardUpgrade {


    /**
     * 会员卡升级
     *
     * card_upgrade_new_card_type : 0
     * card_upgrade_num : 0
     * card_upgrade_operate_time : string
     * card_upgrade_ori_card_type : 0
     * card_upgrade_status : 0
     * card_user_num : 0
     */

    private int card_upgrade_new_card_type;
    private int card_upgrade_num;
    private String card_upgrade_operate_time;
    private int card_upgrade_ori_card_type;
    private int card_upgrade_status;
    private int card_user_num;

    public int getCard_upgrade_new_card_type() {
        return card_upgrade_new_card_type;
    }

    public void setCard_upgrade_new_card_type(int card_upgrade_new_card_type) {
        this.card_upgrade_new_card_type = card_upgrade_new_card_type;
    }

    public int getCard_upgrade_num() {
        return card_upgrade_num;
    }

    public void setCard_upgrade_num(int card_upgrade_num) {
        this.card_upgrade_num = card_upgrade_num;
    }

    public String getCard_upgrade_operate_time() {
        return card_upgrade_operate_time;
    }

    public void setCard_upgrade_operate_time(String card_upgrade_operate_time) {
        this.card_upgrade_operate_time = card_upgrade_operate_time;
    }

    public int getCard_upgrade_ori_card_type() {
        return card_upgrade_ori_card_type;
    }

    public void setCard_upgrade_ori_card_type(int card_upgrade_ori_card_type) {
        this.card_upgrade_ori_card_type = card_upgrade_ori_card_type;
    }

    public int getCard_upgrade_status() {
        return card_upgrade_status;
    }

    public void setCard_upgrade_status(int card_upgrade_status) {
        this.card_upgrade_status = card_upgrade_status;
    }

    public int getCard_user_num() {
        return card_user_num;
    }

    public void setCard_user_num(int card_user_num) {
        this.card_user_num = card_user_num;
    }
}
