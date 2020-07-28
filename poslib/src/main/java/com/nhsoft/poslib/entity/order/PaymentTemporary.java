package com.nhsoft.poslib.entity.order;

public class PaymentTemporary {
    /**
     *   "payment_no": "Q207699192120001",
     *                 "payment_pay_by": "现金",
     *                 "payment_money": 63.0000,
     *                 "payment_buyer_money": 0.0000,
     *                 "payment_receipt_money": 0.0000,
     *                 "payment_time": "2019-07-31 11:59:20",
     *                 "payment_round": 0.0000,
     *                 "payment_receive": 63.0000,
     *                 "payment_change": 0.0000,
     *                 "payment_paid": 63.0000,
     *                 "payment_balance": 0.0000,
     *                 "payment_machine": "00-0D-4C-D0-85-38*A0-19-B2-D1-2F-65",
     *                 "payment_memo": "",
     *                 "payment_card_balance": 0.0000,
     *                 "payment_consume_count": 0,
     *                 "payment_date": "2019-07-31 11:59:20",
     *                 "account_bank_num": 207600001,
     *                 "payment_point": 0.0000,
     *                 "payment_online_un_discount": 0.0000
     */
    private String payment_no;
    private String payment_pay_by;
    private String payment_time;
    private String payment_machine;
    private String payment_memo;
    private String payment_date;
    private float payment_money;
    private float payment_buyer_money;
    private float payment_receipt_money;
    private float payment_round;
    private float payment_receive;
    private float payment_change;
    private float payment_paid;
    private float payment_balance;
    private float payment_card_balance;
    private float payment_online_un_discount;
    private float payment_point;
    private int payment_consume_count;
    private int account_bank_num;
    private long payment_cust_num;
}
