package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.nhsoft.poslib.BuildConfig;
import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Iverson on 2019-10-14 10:09
 * 此类用于：事件统计 转化
 */
public class EventStatisticalUtil {

    //会员录入界面
    public static String QUICK_CREATE_NEW_VIP                 = "quick_create_new_vip";//快速新建会员点击次数
    public static String QUICK_CREATE_NEW_VIP_COMPLETE        = "quicK_create_new_vip_complete";//快速新建会员完成次数
    public static String INPUT_VIP_TYPE_BY_PRINTNUM           = "input_vip_type_by_printnum";//通过表面卡号录入会员次数
    public static String INPUT_VIP_TYPE_BY_PHONE              = "input_vip_type_by_phone";//通过手机号录入会员次数
    public static String INPUT_VIP_TYPE_BY_PAYCODE            = "input_vip_type_by_paycode";//通过付款码录入会员次数
    public static String INPUT_VIP_CANCEL                     = "input_vip_cancel";//取消会员录入次数
    //修改商品属性界面
    public static String CLICK_CHANGE_GOODS_TIMES             = "click_change_goods_times";//修改商品点击次数
    public static String CHANGE_GOODS_BY_ACCOUNT              = "change_goods_by_account";//修改商品的数量次数
    public static String CHANGE_GOODS_BY_SINGLE_PRICE         = "change_goods_by_single_price";//修改商品的单价次数
    public static String CHANGE_GOODS_BY_SUBMIT               = "change_goods_by_submit";//修改商品的小计次数
    public static String CHANGE_GOODS_BY_DISCOUNT             = "change_goods_by_discount";//修改商品的小计折扣次数
    //左滑商品次数
    //TODO 左滑次数
    public static String GOODS_TO_PRESENT                     = "goods_to_present";//已选商品赠送次数
    public static String GOODS_TO_DELETE                      = "goods_to_delete";//已选商品删除次数
    //撤单次数
    public static String POSORDER_TO_CANCEL                   = "posorder_to_cancel";//撤单次数
    //更多操作
    public static String OPERATION_BY_CHANGE_CASH_MODE        = "operation_by_change_cash_mode";//切换收银模式次数
    public static String OPERATION_BY_INPUT_CLIENT            = "operation_by_input_client";//录入客户次数
    public static String OPERATION_BY_OPEN_DRAW               = "operation_by_open_draw";//主动打开钱箱次数
    public static String OPERATION_BY_TO_OMS_ORDER            = "operation_by_to_oms_order";//查询OMS订单的次数
    public static String OPERATION_BY_REPLAY_PRINT_ORDER      = "operation_by_replay_print_order";//补打上一单次数
    //结算按钮
    public static String SETTLEMENT_BY_NORMAL                 = "settlement_by_normal";//正常结算按钮点击次数
    public static String SETTLEMENT_BY_VIPDISCOUNT            = "settlement_by_vipdiscount";//支付折扣结算按钮点击次数
    //结算界面-消费劵对话框
    public static String CLICK_COUPONS_TIMES                  = "click_coupons_times";//消费券点击次数
    public static String COUPONS_INPUT_BY_SCAN                = "coupons_input_by_scan";//扫描优惠券请求次数
    public static String COUPONS_INPUT_CANCEL                 = "coupons_input_cancel";//取消录入优惠券次数
    public static String COUPONS_ADD_TIMES                    = "coupons_add_times";//添加优惠券次数
    //结算界面 - 清空消费券
    public static String COUPONS_CLEAR_INPUT                  = "coupons_clear_input";//清除已经录入优惠券次数
    //结算界面 - 订单优惠
    public static String CLICK_ORDER_DISCOUNT_TIMES           = "click_order_discount_times";//订单优惠点击次数
    public static String POSORDER_DISCOUNT_BY_MONEY           = "posorder_discount_by_money";//订单现金折扣确认次数
    public static String POSORDER_DISCOUNT_BY_DISCOUNT        = "posorder_discount_by_discount";//订单打折折扣确认次数
    //结算界面 - 清空订单优惠
    public static String POSORDER_DISCOUNT_CLEAR              = "posorder_discount_clear";//清空订单优惠次数
    //结算界面 - 抹零功能
    public static String MOLING_TO_JIAO                       = "moling_to_jiao";//抹零到角次数
    public static String MOLING_TO_FIVE_JIAO                  = "moling_to_five_jiao";//抹零到5角次数
    public static String MOLING_TO_YUAN                       = "moling_to_yuan";//抹零到元次数
    //结算界面 - 删除支付方式
    public static String PAY_STYLE_DELETE                     = "pay_style_delete";//删除已经录入支付方式次数
    //结算界面 - 结算界面取消
    public static String SETTLEMENT_CANCEL                    = "settlement_cancel";//结算页面取消次数
    //会员界面 - 积分兑换点击次数
    public static String INTEGRAL_EXCHANGE_TIMES              = "integral_exchange_times";//积分兑换点击次数
    //会员界面 - 积分转储值
    public static String INTEGRAL_EXCHANGE_MONEY_TIMES        = "integral_exchange_money_times";//积分转储值次数
    //会员界面 - 储值有单退货点击次数
    public static String STORE_RETURN_ORDER_CLICK_TIMES       = "store_return_order_click_times"; //储值有单退货点击次数
    //会员界面 - 储值有单退货确认
    public static String STORE_RETURN_ORDER_CONFIRM_TIMES     = "store_return_order_confirm_times";// 储值有单退货确认次数
    //会员界面 - 储值有单退货取消
    public static String STORE_RETURN_ORDER_CANCEL_TIMES      = "store_return_order_cancel_times";//储值有单退货取消次数
    //会员界面 - 反储值次数
    public static String VIP_REVERSE_STORE_TIMES              = "vip_reverse_store_times";//反储值次数
    //订单界面 - 反结账点击次数
    public static String ORDER_REVERSE_SETTLEMENT_TIMES       = "order_reverse_settlement_times";//反结账点击次数
    //订单界面 - 退货点击次数
    public static String ORDER_RETURN_SETTLEMENT_TIMES        = "order_return_settlement_times";//退货点击次数
    //订单界面 - 根据筛选条件订单查询确认次数
    public static String ORDER_QUERY_BY_FILTER_CONFIRM_TIMES  = "order_query_by_filter_confirm_times";
    //订单界面 - 根据筛选条件订单查询取消次数
    public static String ORDER_QUERY_BY_FILTER_CANCAL_TIMES = "order_query_by_filter_cancel_times";
    //订单界面 - 根据精确订单号查询次数
    public static String ORDER_QUERY_BY_ACCURATE_TIMES    = "order_query_by_accurate_times";
    //有单退货界面 - 确认退货的次数
    public static String ORDER_RETURN_CONFIRM_TIMES         = "order_return_confirm_times";
    //有单退货界面 - 退货的原因点击次数
    public static String ORDER_RETURN_REASON_TIMES          = "order_return_reason_times";
    //系统界面 - 进入单边账的界面次数
    public static String ENTER_TO_SINGLE_ORDER_TIMES        = "enter_to_single_order_times";
    //系统界面 - 点击检查单边账的次数
    public static String CLICK_CHECK_SINGLE_ORDER_TIMES     = "click_check_single_order_times";
    //    系统界面 - 打印设置点击次数
    public static String CLICK_PRINT_SETTING_TIMES          = "click_print_setting_times";
    //    系统界面 - 下载模版点击次数
    public static String CLICK_PRINT_MODEL_DOWNLOAD_TIMES   = "click_print_model_download_times";
    //    系统界面 - 应用模版点击次数
    public static String CLICK_USE_PRINT_MODEL_TIMES        = "click_use_print_model_times";
    //    系统界面 - 交班审核点击次数
    public static String CLICK_INCLUDING_AN_AUDIT_TIMES     = "click_including_an_audit_times";
    //    系统界面 - 终端商品设置点击次数
    public static String CLICK_TERMINAL_GOODS_SETTING_TIMES = "click_terminal_goods_setting_times";
    //    系统界面 - 商品调价点击次数
    public static String CLICK_CHANGE_GOODS_PRICE_TIMES     = "click_change_goods_price_times";
    //    系统界面 - 生成调价单调价点击次数
    public static String CLICK_CREATE_CHANGE_PRICE_BILL_TIMES = "click_create_change_price_bill_times";
    //    登录界面 - 登录界面设置点击次数
    public static String CLICK_LOGIN_SETTING_TIMES            = "click_login_setting_times";
    //    登录设置界面 - 设置零售收银模式次数
    public static String CLICK_SETTING_RESALE_MODE_TIMES      = "click_setting_resale_mode_times";
    //    登录设置界面 - 设置简易无图收银模式次数
    public static String CLICK_SETTING_SINGLE_MODE_TIMES      = "click_setting_single_mode_times";
    //    登录设置界面 - 设置简易有图收银模式次数
    public static String CLICK_SETTING_FIGURE_MODE_TIMES      = "click_setting_figure_mode_times";
    //    登录设置界面 - 设置条码秤收银模式次数
    public static String CLICK_SETTING_BARCODE_MODE_TIMES     = "click_setting_barcode_mode_times";
    //统计在用的门店信息
    public static String STATISTICAL_BRANCH     = "statistical_branch";


    /**
     * @param event 埋点事件名
     */
    public static void onEventStacal(String event) {
        MobclickAgent.onEvent(RetailPosManager.sContext, event);
    }


    public static void onEventStacalBranchName(String bookName,String branchName,String book_code){
            Map<String, String> brach = new HashMap<String, String>();
            brach.put("book_code", ""+bookName+"_"+book_code+"_"+(TextUtils.isEmpty(branchName)? bookName : branchName)+"_"+LibConfig.activePosMachine.getPos_machine_name());
//            brach.put("brach_name", branchName);
            brach.put("version_name", BuildConfig.VERSION_NAME);
//            brach.put("pos_name",""+ LibConfig.activePosMachine.getPos_machine_name());
            MobclickAgent.onEvent(RetailPosManager.sContext, STATISTICAL_BRANCH, brach);
    }
}
