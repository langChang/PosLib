package com.nhsoft.poslib.model;

import java.util.List;

/**
 * 临时接收crm数据
 * @Author ADMIN
 * @time 2020-04-02 9:32 
 */

public class VipUserInfoCrm {

    private VipUserInfo card_user;
    //全渠道
    private String      default_card;//ama 表明卡号
    private String      customer_id;//crm 卡主建
    private String      sex;//性别
    private String      point_multiple;//积分倍数
    private String      point;//积分
    private String      phone;//手机号
    private String      openid;//微信openid
    private String      name;//会员名
    private String      level_deadline;//等级有效期(yyyy-MM-dd HH:MM:SS)
    private String      level;//等级
    private String      certificate_type;//证件类型
    private String      certificate_id;//证件编号
    private String      create_time;//注册日期(yyyy-MM-dd HH:MM:SS)
    private String discount_without_coupon;


    public String getDiscount_without_coupon() {
        return discount_without_coupon;
    }

    public void setDiscount_without_coupon(String discount_without_coupon) {
        this.discount_without_coupon = discount_without_coupon;
    }



    private String birth;//会员生日(yyyy-MM-dd HH:MM:SS)

    private List<VipUserInfo> card_users;

    public List<VipUserInfo> getCard_users() {
        return card_users;
    }

    public void setCard_users(List<VipUserInfo> card_users) {
        this.card_users = card_users;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public VipUserInfo getCard_user() {
        return card_user;
    }

    public void setCard_user(VipUserInfo card_user) {
        this.card_user = card_user;
    }

    public String getDefault_card() {
        return default_card;
    }

    public void setDefault_card(String default_card) {
        this.default_card = default_card;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPoint_multiple() {
        return point_multiple;
    }

    public void setPoint_multiple(String point_multiple) {
        this.point_multiple = point_multiple;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel_deadline() {
        return level_deadline;
    }

    public void setLevel_deadline(String level_deadline) {
        this.level_deadline = level_deadline;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCertificate_type() {
        return certificate_type;
    }

    public void setCertificate_type(String certificate_type) {
        this.certificate_type = certificate_type;
    }

    public String getCertificate_id() {
        return certificate_id;
    }

    public void setCertificate_id(String certificate_id) {
        this.certificate_id = certificate_id;
    }
}
