package com.nhsoft.poslib.entity;

import java.util.List;

public class LeaveMessage {


    /**
     *
     * 系统留言
     *
     * app_user_num : 0
     * message_board_content : string
     * message_board_create_time : string
     * message_board_id : string
     * message_board_sender : string
     * message_board_subject : string
     * system_book_code : string
     */

    private int app_user_num;
    private String message_board_content;
    private String message_board_create_time;
    private String message_board_id;
    private String message_board_sender;
    private String message_board_subject;
    private String system_book_code;
    private List<LeaveMessageDetail>message_board_details;

    public List<LeaveMessageDetail> getMessage_board_details() {
        return message_board_details;
    }

    public void setMessage_board_details(List<LeaveMessageDetail> message_board_details) {
        this.message_board_details = message_board_details;
    }

    public int getApp_user_num() {
        return app_user_num;
    }

    public void setApp_user_num(int app_user_num) {
        this.app_user_num = app_user_num;
    }

    public String getMessage_board_content() {
        return message_board_content;
    }

    public void setMessage_board_content(String message_board_content) {
        this.message_board_content = message_board_content;
    }

    public String getMessage_board_create_time() {
        return message_board_create_time;
    }

    public void setMessage_board_create_time(String message_board_create_time) {
        this.message_board_create_time = message_board_create_time;
    }

    public String getMessage_board_id() {
        return message_board_id;
    }

    public void setMessage_board_id(String message_board_id) {
        this.message_board_id = message_board_id;
    }

    public String getMessage_board_sender() {
        return message_board_sender;
    }

    public void setMessage_board_sender(String message_board_sender) {
        this.message_board_sender = message_board_sender;
    }

    public String getMessage_board_subject() {
        return message_board_subject;
    }

    public void setMessage_board_subject(String message_board_subject) {
        this.message_board_subject = message_board_subject;
    }

    public String getSystem_book_code() {
        return system_book_code;
    }

    public void setSystem_book_code(String system_book_code) {
        this.system_book_code = system_book_code;
    }
}
