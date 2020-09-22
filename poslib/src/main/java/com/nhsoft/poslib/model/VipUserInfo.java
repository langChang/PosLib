package com.nhsoft.poslib.model;


import android.text.TextUtils;

import java.util.List;

public class VipUserInfo implements Cloneable{

    /**
     * card_user_num : String||卡Id
     * card_print_num : String||表面卡号
     * card_balance : BigDecimal||卡余额
     * card_point : BigDecimal||卡积分值
     * card_phone : String||卡关联手机号
     * card_user_birth_date : String||卡生日(yyyy-MM-dd HH:MM:SS)
     * card_user_type : String||卡介质
     * card_user_cust_name : String||卡持有者姓名
     * card_user_type_name : String||卡类型名称
     * card_user_deadline : String||卡有效期
     * card_user_type_code : String||卡类型编号
     * card_user_type_price_level : Integer||卡类型价格级别
     * card_user_type_discount : BigDecimal||卡类型折扣
     * card_user_type_birth_discount : BigDecimal||卡类型生日折扣
     * card_state_name : :"初始化"
     * "card_user_address":"",
     * "card_user_cust_sex":"",
     * "card_user_id_card_type":"",
     * "card_user_id_card_num":"",
     *"card_user_date": "string",//发卡日期
     * card_user_enroll_shop  发卡门店
     * card_user_locked boolean 是否锁定
     * open_id 对应微信openId
     */


    private String card_user_address;
    private String card_user_cust_sex;
    private String card_user_id_card_type;
    private String card_user_id_card_num;
    private String card_user_num;
    private String card_print_num;
    private String card_balance;
    private String card_point;
    private String card_phone;
    private String card_user_birth_date;
    private String card_user_type;
    private String card_user_cust_name;
    private String card_user_type_name;
    private String card_user_deadline;
    private String card_user_type_code;
    private String card_user_type_price_level;
    private String card_user_type_discount;
    private String card_user_type_birth_discount;
    private String card_state_name;
    private String card_user_date;

    private float card_balance_money;
    private float card_balance_total_consume;
    private float card_balance_total_deposit;
    private String card_user_balance_count;
    private String card_user_consume_count;
    private float card_user_total_cash;
    private float card_user_total_invoice;
    private String card_user_deposit_count;
    private String card_user_invoice_count;
    private boolean discount_without_coupon;
    private int card_user_enroll_shop;
    private boolean card_user_locked;
    private float limitCardMoney = 0;
    private String open_id;
    private boolean isQuitCard;
    private String card_user_password;// 会员卡密码
    private boolean card_user_change_enabled;//零钱包是否开启
    private String card_type;


    private int ServerCardType = 0; // 0 : 默认为ama卡  1： 为水星礼品卡
    private boolean cardMedium;//卡介质 是否是IC卡（需要读卡写卡）

    private int mPasswordChecked = -1;//是否经过密码验证   如果通过验证重新赋值为1
    private int mMegChecked = -1;//是否经过短信验证码验证  如果通过验证重新赋值为1
    private boolean isICCard;//是否IC卡录入
    private String physic_print_num;

    public boolean isBindCardInput = false;

    private boolean isGlobalVip;//是否全局会员

    //全渠道
    private String  default_card;//ama 表面卡号
    private String  customer_id;//crm 卡主建
    private String  sex;//性别
    private String  point_multiple;//积分倍数
    private String  point;//积分
    private String  phone;//手机号
    private String  openid;//微信openid
    private String  name;//会员名
    private String  level_deadline;//等级有效期(yyyy-MM-dd HH:MM:SS)
    private String  level;//等级
    private String  certificate_type;//证件类型
    private String  certificate_id;//证件编号
    private String create_time;//注册日期(yyyy-MM-dd HH:MM:SS)
    private String birth;//会员生日(yyyy-MM-dd HH:MM:SS)
    /**
     * id : GIFT_CARD
     *
     * book_code : 2072
     * number : 18583132878704093232
     * balance : 1000.0
     * logo_url : http://nhsoft-pintuan.oss-cn-shanghai.aliyuncs.com/public/system/mercury/asset2s/attachments/000/001/483/original/800*800.jpg
     * brand_name : 蜂巢7号
     * title : 3232
     */

    private String id;
    private String book_code;
    private String number;
    private String balance;
    private String logo_url;
    private String brand_name;
    private String title;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getPhysic_print_num() {
        return physic_print_num;
    }

    public void setPhysic_print_num(String physic_print_num) {
        this.physic_print_num = physic_print_num;
    }

    public boolean isICCard() {
        return isICCard;
    }

    public void setICCard(boolean ICCard) {
        isICCard = ICCard;
    }

    public boolean isGlobalVip() {
        return isGlobalVip;
    }

    public void setGlobalVip(boolean globalVip) {
        isGlobalVip = globalVip;
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

    public int getmPasswordChecked() {
        return mPasswordChecked;
    }

    public void setmPasswordChecked(int mPasswordChecked) {
        this.mPasswordChecked = mPasswordChecked;
    }

    public int getmMegChecked() {
        return mMegChecked;
    }

    public void setmMegChecked(int mMegChecked) {
        this.mMegChecked = mMegChecked;
    }

    public boolean isCardMedium() {
        return cardMedium;
    }

    public void setCardMedium(boolean cardMedium) {
        this.cardMedium = cardMedium;
    }

    public int getServerCardType() {
        return ServerCardType;
    }

    public void setServerCardType(int serverCardType) {
        ServerCardType = serverCardType;
    }

    public boolean isCard_user_change_enabled() {
        return card_user_change_enabled;
    }

    public void setCard_user_change_enabled(boolean card_user_change_enabled) {
        this.card_user_change_enabled = card_user_change_enabled;
    }

    public String getCard_user_password() {
        return card_user_password;
    }

    public void setCard_user_password(String card_user_password) {
        this.card_user_password = card_user_password;
    }

    public boolean isQuitCard() {
        return isQuitCard;
    }

    public void setQuitCard(boolean quitCard) {
        isQuitCard = quitCard;
    }
    public float getLimitCardMoney() {
        return limitCardMoney;
    }
    public void setLimitCardMoney(float limitCardMoney) {
        this.limitCardMoney = limitCardMoney;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public boolean isCard_user_locked() {
        return card_user_locked;
    }

    public void setCard_user_locked(boolean card_user_locked) {
        this.card_user_locked = card_user_locked;
    }

    public int getCard_user_enroll_shop() {
        return card_user_enroll_shop;
    }

    public void setCard_user_enroll_shop(int card_user_enroll_shop) {
        this.card_user_enroll_shop = card_user_enroll_shop;
    }

    public String getCard_user_date() {
        return card_user_date;
    }

    public void setCard_user_date(String card_user_date) {
        this.card_user_date = card_user_date;
    }

    public String getCard_user_deposit_count() {
        return card_user_deposit_count;
    }

    public void setCard_user_deposit_count(String card_user_deposit_count) {
        this.card_user_deposit_count = card_user_deposit_count;
    }

    public String getCard_user_invoice_count() {
        return card_user_invoice_count;
    }

    public void setCard_user_invoice_count(String card_user_invoice_count) {
        this.card_user_invoice_count = card_user_invoice_count;
    }

    private List<CouponsBean> card_coupons_list;


    public float getCard_balance_money() {
        return card_balance_money;
    }

    public void setCard_balance_money(float card_balance_money) {
        this.card_balance_money = card_balance_money;
    }

    public float getCard_balance_total_consume() {
        return card_balance_total_consume;
    }

    public void setCard_balance_total_consume(float card_balance_total_consume) {
        this.card_balance_total_consume = card_balance_total_consume;
    }

    public float getCard_balance_total_deposit() {
        return card_balance_total_deposit;
    }

    public void setCard_balance_total_deposit(float card_balance_total_deposit) {
        this.card_balance_total_deposit = card_balance_total_deposit;
    }

    public String getCard_user_balance_count() {
        return card_user_balance_count;
    }

    public void setCard_user_balance_count(String card_user_balance_count) {
        this.card_user_balance_count = card_user_balance_count;
    }

    public String getCard_user_consume_count() {
        return card_user_consume_count;
    }

    public void setCard_user_consume_count(String card_user_consume_count) {
        this.card_user_consume_count = card_user_consume_count;
    }

    public float getCard_user_total_cash() {
        return card_user_total_cash;
    }

    public void setCard_user_total_cash(float card_user_total_cash) {
        this.card_user_total_cash = card_user_total_cash;
    }

    public float getCard_user_total_invoice() {
        return card_user_total_invoice;
    }

    public void setCard_user_total_invoice(float card_user_total_invoice) {
        this.card_user_total_invoice = card_user_total_invoice;
    }

    public String getCard_user_address() {
        return card_user_address;
    }

    public void setCard_user_address(String card_user_address) {
        this.card_user_address = card_user_address;
    }

    public String getCard_user_cust_sex() {
        return card_user_cust_sex;
    }

    public void setCard_user_cust_sex(String card_user_cust_sex) {
        this.card_user_cust_sex = card_user_cust_sex;
    }

    public String getCard_user_id_card_type() {
        return card_user_id_card_type;
    }

    public void setCard_user_id_card_type(String card_user_id_card_type) {
        this.card_user_id_card_type = card_user_id_card_type;
    }

    public String getCard_user_id_card_num() {
        return card_user_id_card_num;
    }

    public void setCard_user_id_card_num(String card_user_id_card_num) {
        this.card_user_id_card_num = card_user_id_card_num;
    }

    public String getCard_state_name() {
        return card_state_name;
    }

    public void setCard_state_name(String card_state_name) {
        this.card_state_name = card_state_name;
    }

    public String getCard_user_num() {
        return card_user_num;
    }

    public void setCard_user_num(String card_user_num) {
        this.card_user_num = card_user_num;
    }

    public String getCard_print_num() {
        return card_print_num;
    }

    public void setCard_print_num(String card_print_num) {
        this.card_print_num = card_print_num;
    }

    public String getCard_balance() {
        return card_balance;
    }

    public void setCard_balance(String card_balance) {
        this.card_balance = card_balance;
    }

    public String getCard_point() {
        return  TextUtils.isEmpty(card_point) ? "0" : card_point;
    }

    public void setCard_point(String card_point) {
        this.card_point = card_point;
    }

    public String getCard_phone() {
        return card_phone;
    }

    public void setCard_phone(String card_phone) {
        this.card_phone = card_phone;
    }

    public String getCard_user_birth_date() {
        return card_user_birth_date;
    }

    public void setCard_user_birth_date(String card_user_birth_date) {
        this.card_user_birth_date = card_user_birth_date;
    }

    public String getCard_user_type() {
        return card_user_type;
    }

    public void setCard_user_type(String card_user_type) {
        this.card_user_type = card_user_type;
    }

    public String getCard_user_cust_name() {
        return card_user_cust_name;
    }

    public void setCard_user_cust_name(String card_user_cust_name) {
        this.card_user_cust_name = card_user_cust_name;
    }

    public String getCard_user_type_name() {
        return card_user_type_name;
    }

    public void setCard_user_type_name(String card_user_type_name) {
        this.card_user_type_name = card_user_type_name;
    }

    public String getCard_user_deadline() {
        return card_user_deadline;
    }

    public void setCard_user_deadline(String card_user_deadline) {
        this.card_user_deadline = card_user_deadline;
    }

    public String getCard_user_type_code() {
        return card_user_type_code;
    }

    public void setCard_user_type_code(String card_user_type_code) {
        this.card_user_type_code = card_user_type_code;
    }

    public String getCard_user_type_price_level() {
        return card_user_type_price_level;
    }

    public void setCard_user_type_price_level(String card_user_type_price_level) {
        this.card_user_type_price_level = card_user_type_price_level;
    }

    public String getCard_user_type_discount() {
        return card_user_type_discount;
    }

    public void setCard_user_type_discount(String card_user_type_discount) {
        this.card_user_type_discount = card_user_type_discount;
    }

    public String getCard_user_type_birth_discount() {
        return card_user_type_birth_discount;
    }

    public void setCard_user_type_birth_discount(String card_user_type_birth_discount) {
        this.card_user_type_birth_discount = card_user_type_birth_discount;
    }

    public List<CouponsBean> getCard_coupons_list() {
        return card_coupons_list;
    }

    public void setCard_coupons_list(List<CouponsBean> card_coupons_list) {
        this.card_coupons_list = card_coupons_list;
    }

    public boolean isDiscount_without_coupon() {
        return discount_without_coupon;
    }

    public void setDiscount_without_coupon(boolean discount_without_coupon) {
        this.discount_without_coupon = discount_without_coupon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBook_code() {
        return book_code;
    }

    public void setBook_code(String book_code) {
        this.book_code = book_code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return level == null? "" : level;
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

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }
}
