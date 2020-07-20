package com.nhsoft.poslib.entity;

public class LeaveMessageDetail {


    /**
     * 留言详细
     *
     * app_user_num : 0
     * message_board_detail_num : 0
     * message_board_id : string
     * message_category : string
     * message_read_time : string
     * message_receiver : string
     * message_receiver_branch : string
     * system_book_code : string
     */

    private int app_user_num;
    private int message_board_detail_num;
    private String message_board_id;
    private String message_category;
    private String message_read_time;
    private String message_receiver;
    private String message_receiver_branch;
    private String system_book_code;

    public int getApp_user_num() {
        return app_user_num;
    }

    public void setApp_user_num(int app_user_num) {
        this.app_user_num = app_user_num;
    }

    public int getMessage_board_detail_num() {
        return message_board_detail_num;
    }

    public void setMessage_board_detail_num(int message_board_detail_num) {
        this.message_board_detail_num = message_board_detail_num;
    }

    public String getMessage_board_id() {
        return message_board_id;
    }

    public void setMessage_board_id(String message_board_id) {
        this.message_board_id = message_board_id;
    }

    public String getMessage_category() {
        return message_category;
    }

    public void setMessage_category(String message_category) {
        this.message_category = message_category;
    }

    public String getMessage_read_time() {
        return message_read_time;
    }

    public void setMessage_read_time(String message_read_time) {
        this.message_read_time = message_read_time;
    }

    public String getMessage_receiver() {
        return message_receiver;
    }

    public void setMessage_receiver(String message_receiver) {
        this.message_receiver = message_receiver;
    }

    public String getMessage_receiver_branch() {
        return message_receiver_branch;
    }

    public void setMessage_receiver_branch(String message_receiver_branch) {
        this.message_receiver_branch = message_receiver_branch;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }
}
