package com.nhsoft.poslib.entity.order;

import java.util.List;

public class OrderTemporary {
    /**
     * "order_no": "Q207699192120002",
     *         "system_book_code": "2076",
     *         "storehouse_num": 207600014,
     *         "branch_num": 99,
     *         "shift_table_num": 16002,
     *         "shift_table_bizday": "20190731",
     *         "order_date": "2019-07-31 11:59:18",
     *         "order_sold_by": "",
     *         "order_operator": "002",
     *
     *         "order_operate_time": "2019-07-31 11:59:20",
     *         "order_discount_money": 0.0000,
     *         "order_commission": 0.0000,
     *         "order_total_money": 63.0000,
     *         "order_payment_money": 63.0000,
     *         "order_round": 0.0000,
     *         "order_balance": 0.0000,
     *         "order_total_invoice": 0.0000,
     *
     *         "order_change": 0.0000,
     *         "order_time": "2019-07-31 11:59:20",
     *         "order_machine": "00-0D-4C-D0-85-38*A0-19-B2-D1-2F-65",
     *         "order_payee": "002",
     *         "order_state_code": 5,
     *         "order_state_name": "制单|完成",
     *         "order_memo": "",
     *         "order_point": 0.0000,
     *
     *         "order_gross_profit": 63.0000,
     *         "order_mgr_discount_money": 0.0000,
     *         "order_coupon_total_money": 0.0000,
     *         "order_coupon_payment_money": 0.0000,
     *         "order_card_user_num": 0,
     *         "order_card_type": 0,
     *         "order_post_fee": 0.0000,
     *
     *         "order_promotion_discount_money": 0.0000,
     *         "order_external_no": "Q9919212115918RB",
     *         "order_card_change": 0.0000,
     *         "order_tax_money": 0.0000,
     *         "order_online_discount": 0.0000,
     */



    private String order_no;
    private String system_book_code;
    private int storehouse_num;
    private int branch_num;
    private int shift_table_num;
    private String shift_table_bizday;
    private String order_date;
    private String order_sold_by;
    private String order_operator;
    private String order_operate_time;
    private float order_discount_money;
    private float order_commission;
    private float order_total_money;
    private float order_payment_money;
    private float order_round;
    private float order_balance;
    private float order_total_invoice;
    private float order_change;
    private String order_time;
    private String order_machine;
    private String order_payee;
    private int order_state_code;
    private String order_state_name;
    private String order_memo;
    private float order_point;
    private String order_ref_billno;
    private String order_external_no;
    private float order_gross_profit;
    private float order_mgr_discount_money;
    private float order_coupon_total_money;
    private float order_coupon_payment_money;
    private float order_post_fee;
    private float order_promotion_discount_money;

    private float order_tax_money;
    private float order_online_discount;
    private int merchant_num;
    private int stall_num;
    private String  order_printed_num;
    private String  order_card_user;
    private String  order_card_type_desc;
    private String  order_card_phone;
    private float order_card_change;
    private int order_card_user_num;
    private int order_card_type;
    private String  order_pay_no;


    private List<OrderDetailTemporary> pos_order_details;
    private List<PaymentTemporary> payments;
}
