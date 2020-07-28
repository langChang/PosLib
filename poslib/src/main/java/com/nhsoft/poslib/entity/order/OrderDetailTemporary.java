package com.nhsoft.poslib.entity.order;

public class OrderDetailTemporary {
    /**
     *
     "order_no": "Q207699192120002",
     "order_detail_num": 1,
     "item_num": 207600002,
     "order_detail_type": "商品项目",
     "order_detail_item": "和田大红枣",
     "order_detail_item_department": "干果类",
     "order_detail_std_price": 20.0000,
     "order_detail_price": 20.0000,
     "order_detail_amount": 1.0000,
     "order_detail_discount": 0.0000,
     "order_detail_payment_money": 20.0000,
     "order_detail_state_code": 1,
     "order_detail_state_name": "销售",
     "order_detail_commission": 0.0000,
     "order_detail_policy_promotion_flag": false,

     "order_detail_cost": 0.0000,
     "order_detail_ticket_uuid": "",
     "item_grade_num": 0,
     "order_detail_has_kit": false,
     "order_detail_policy_fid": "",
     "order_detail_lot_number": "",
     "order_detail_memo": "",
     "pos_order_kit_details": []
     */


    private String order_no;
    private int order_detail_num;
    private int item_num;
    private int order_detail_state_code;
    private String order_detail_type;
    private String order_detail_item;
    private String order_detail_item_department;
    private String order_detail_state_name;
    private float order_detail_std_price;
    private float order_detail_price;
    private float order_detail_amount;
    private float order_detail_discount;
    private float order_detail_payment_money;
    private float order_detail_commission;
    private boolean order_detail_policy_promotion_flag;

    private float order_detail_cost;
    private String order_detail_ticket_uuid;
    private String order_detail_policy_fid;
    private String order_detail_lot_number;
    private String order_detail_memo;
    private int item_grade_num;
    private String order_detail_online_unit;
    private boolean order_detail_has_kit;
    private float order_detail_share_discount;
    private String order_pay_no;



//    private
}
