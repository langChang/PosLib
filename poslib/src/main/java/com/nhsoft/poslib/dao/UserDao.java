package com.nhsoft.poslib.dao;

import com.google.gson.Gson;
import com.nhsoft.poslib.entity.BookResource;
import com.nhsoft.poslib.utils.EventStatisticalUtil;
import com.nhsoft.poslib.utils.SharedPreferenceUtil;

/**
 * Created by Iverson on 2018/5/28 上午10:51
 * 此类用于：
 */

public class UserDao {

    /**************************关于Sp存储的信息 *************************/
    public static final String USER_SP                          = "user_sp";
    public static final String USER_TOKEN                       = "user_token";
    public static final String COUPONS_XML                       = "coupons_xml";
    public static final String LOGIN_DATE                       = "login_date";//用户上次登录时间
    public static final String LOGIN_USER_NAME                  = "login_user_name";//登录用户账号
    public static final String LOGIN_USER_PASD                  = "login_user_pasd";//登录用户密码（明文）
    public static final String USING_USER_NAME                  = "using_user_name";//登录用户账号(用于账号密码回显)
    public static final String USING_USER_PASD                  = "using_user_pasd";//登录用户密码（用于账号密码回显 明文）
    public static final String EFFECT_DATE                      = "effect_date";//有效时间  授权日期
    public static final String TERMINAL_CHECK_NUM               = "TERMINAL_CHECK_NUM";//终端数校验日期
    public static final String TERMINAL_CHECK_NUM_RESOULT       = "TERMINAL_CHECK_NUM_RESOULT";//终端数校验J结果
    public static final String LIMIT_LOW_VERSION                = "limit_low_version";
    public static final String PRINT_STYLE_ORDER                = "print_style_order";
    public static final String PRINT_STYLE_ORDER_USING_NAME     = "print_style_order_using_name";
    public static final String PRINT_PATH_ORDER                 = "print_path_order";
    public static final String USE_UNIT_STRING                  = "use_unit_string";//使用公斤或市斤
    public static final String CASHIER_MODE                     = "cashier_mode";//收银模式
    public static final String PRINT_NUM                        = "print_num";//打印份数
    public static final String SHOW_LINE                        = "show_line";//打印份数
    public static final String OPEN_WEIGHT                       = "open_weight";//是否开启连续称重功能
    public static final String CONNECT_KEYBOARD                 = "connect_keyboard";//是否连接外接键盘
    public static final String DOWNLOAD_APK_URL                 = "download_apk_url";//打印份数
    public static final String BAISHA_DELECT_BOTTOM_MENU        = "detele_bottom_menu";

    public static final String SYSTEM_BOOK                       = "system_book";//商品按钮适配类型
    public static final String GOODS_TYPE                       = "good_type";//商品按钮适配类型
    public static final String ROW_GOODS_NUM                    = "row_goods_num";//每行商品数
    public static final String LINE_GOODS_NUM                   = "line_goods_num";//每列商品数
    public static final String GOODS_NAME_SIZE                  = "goods_name_size";//商品名字字体大小
    public static final String GOOD_PRICE_SIZE                  = "goods_price_size";//商品单价字体大小
    public static final String ROW_MARGIN                       = "row_margin";//行间距
    public static final String LINE_MARGIN                      = "line_margin";//列间距
    public static final String OMS_TURN                      = "OMS_TURN";//消息提醒开关 0关  1开
    public static final String OMS_MESSAGE_TIMES                      = "OMS_MESSAGE_TIMES";//消息提醒次数 int




    public static void setSystemBook(String systemBook){
        SharedPreferenceUtil.saveValue(USER_SP,SYSTEM_BOOK,systemBook);
    }

    public static String getSystemBook(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SP,SYSTEM_BOOK);
    }



    public static void setGoodsType(String goodsType){
        SharedPreferenceUtil.saveValue(USER_SP,GOODS_TYPE,goodsType);
    }

    public static String getGoodsType(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SP,GOODS_TYPE);
    }

    public static void setRowGoodsNum(String rowGoodsNum){
        SharedPreferenceUtil.saveValue(USER_SP,ROW_GOODS_NUM,rowGoodsNum);
    }

    public static String getRowGoodsNum(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SP,ROW_GOODS_NUM);
    }

    public static void setLineGoodsNum(String lineGoodsNum){
        SharedPreferenceUtil.saveValue(USER_SP,LINE_GOODS_NUM,lineGoodsNum);
    }

    public static String getLineGoodsNum(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SP,LINE_GOODS_NUM);
    }

    public static void setGoodsNameSize(String goodsNameSize){
        SharedPreferenceUtil.saveValue(USER_SP,GOODS_NAME_SIZE,goodsNameSize);
    }

    public static String getGoodsNameSize(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SP,GOODS_NAME_SIZE);
    }

    public static void setGoodPriceSize(String goodPriceSize){
        SharedPreferenceUtil.saveValue(USER_SP,GOOD_PRICE_SIZE,goodPriceSize);
    }

    public static String getGoodPriceSize(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SP,GOOD_PRICE_SIZE);
    }

    public static void setRowMargin(String rowMargin){
        SharedPreferenceUtil.saveValue(USER_SP,ROW_MARGIN,rowMargin);
    }

    public static String getRowMargin(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SP,ROW_MARGIN);
    }

    public static void setLineMargin(String lineMargin){
        SharedPreferenceUtil.saveValue(USER_SP,LINE_MARGIN,lineMargin);
    }

    public static String getLineMargin(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SP,LINE_MARGIN);
    }


    //保存登录token
    public static void saveToken(String token) {
        SharedPreferenceUtil.saveValue(USER_SP, USER_TOKEN, token);
    }

    public static String getToken() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, USER_TOKEN);
    }



    //保存登录token
    public static void saveCouponsXml(BookResource bookResource) {
        SharedPreferenceUtil.saveValue(USER_SP, COUPONS_XML, new Gson().toJson(bookResource));
    }

    public static String getCouponsXml() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, COUPONS_XML);
    }

    //保存登录token
    public static void saveLowVersion(String versionName) {
        SharedPreferenceUtil.saveValue(USER_SP, LIMIT_LOW_VERSION, versionName);
    }

    public static String getLowVersion() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, LIMIT_LOW_VERSION);
    }

    //保存登录token 今天是否远程登陆下载数据
    public static void setLoginDate(String loginDate) {
        SharedPreferenceUtil.saveValue(USER_SP, LOGIN_DATE, loginDate);
    }

    public static String getLoginDate() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, LOGIN_DATE);
    }


    //保存登录userName
    public static void setLoginUserName(String loginDate) {
        SharedPreferenceUtil.saveValue(USER_SP, LOGIN_USER_NAME, loginDate);
    }

    public static String getLoginUserName() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, LOGIN_USER_NAME);
    }

    //保存登录userName
    public static void setUsingUserName(String loginDate) {
        SharedPreferenceUtil.saveValue(USER_SP, USING_USER_NAME, loginDate);
    }

    public static String getUsingUserName() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, USING_USER_NAME);
    }

    //保存oms 消息提醒开关 0关  1开
    public static void setOmsTurn(String loginDate) {
        SharedPreferenceUtil.saveValue(USER_SP, OMS_TURN, loginDate);
    }

    public static String getOmsTurn() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, OMS_TURN);
    }

    //保存oms 消息提醒开关 0  1  3
    public static void setOmsMessageTimes(String loginDate) {
        SharedPreferenceUtil.saveValue(USER_SP, OMS_MESSAGE_TIMES, loginDate);
    }

    public static String getOmsMessageTimes() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, OMS_MESSAGE_TIMES);
    }

    //保存使用的单位 1:代表公斤  2： 市斤
    public static void setUseUnit(String unit) {
        SharedPreferenceUtil.saveValue(USER_SP, USE_UNIT_STRING, unit);
    }

    public static String getUseUnit() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, USE_UNIT_STRING);
    }


    //保存登录UserPasd
    public static void setLoginUserPasd(String loginDate) {
        SharedPreferenceUtil.saveValue(USER_SP, LOGIN_USER_PASD, loginDate);
    }

    public static String getLoginUserPasd() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, LOGIN_USER_PASD);
    }

    //保存登录UserPasd
    public static void setUsingUserPasd(String loginDate) {
        SharedPreferenceUtil.saveValue(USER_SP, USING_USER_PASD, loginDate);
    }

    public static String getUsingUserPasd() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, USING_USER_PASD);
    }

    //保存登录有效时间
    public static void setEffectDate(String effectDate) {
        SharedPreferenceUtil.saveValue(USER_SP, EFFECT_DATE, effectDate);
    }

    public static String getCheckTerminalDate() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, TERMINAL_CHECK_NUM);
    }

    //终端数校验的日期
    public static void setCheckTerminalDate(String date) {
        SharedPreferenceUtil.saveValue(USER_SP, TERMINAL_CHECK_NUM, date);
    }

    //终端数校验的结果
    public static boolean getCheckTerminal() {
        return SharedPreferenceUtil.getBooleanValueByKey(USER_SP, TERMINAL_CHECK_NUM_RESOULT);
    }

    public static void setCheckTerminal(boolean b) {
        SharedPreferenceUtil.saveValue(USER_SP, TERMINAL_CHECK_NUM_RESOULT, b);
    }


    public static String getEffectDate() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, EFFECT_DATE);
    }

    //设置订单小票格式名字
    public static void setStyleOrder(String name){
         SharedPreferenceUtil.saveValue(USER_SP, PRINT_STYLE_ORDER,name);
    }
    //获取订单小票格式名字
    public static String getStyleOrder() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, PRINT_STYLE_ORDER);
    }

    //设置应用订单小票格式名字
    public static void setStyleOrderUsingName(String name){
        SharedPreferenceUtil.saveValue(USER_SP, PRINT_STYLE_ORDER_USING_NAME,name);
    }
    //获取应用订单小票格式名字
    public static String getStyleOrderUsingName() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, PRINT_STYLE_ORDER_USING_NAME);
    }

    //设置订单小票格式路径
    public static void setStyleOrderPath(String path){
         SharedPreferenceUtil.saveValue(USER_SP, PRINT_PATH_ORDER,path);
    }

    //获取订单小票格式路径
    public static String getStyleOrderPath() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, PRINT_PATH_ORDER);
    }


    //获取收银模式
    public static String getCashierMode() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, CASHIER_MODE);
    }

    public static void setCashierMode(String cashierMode) {
        if(!cashierMode.equals(SharedPreferenceUtil.getStringValueByKey(USER_SP, CASHIER_MODE))){
            if(cashierMode.equals("零售模式")){
                EventStatisticalUtil.onEventStacal(EventStatisticalUtil.CLICK_SETTING_RESALE_MODE_TIMES);
            }else if(cashierMode.equals("简易无图模式")){
                EventStatisticalUtil.onEventStacal(EventStatisticalUtil.CLICK_SETTING_SINGLE_MODE_TIMES);
            }else if(cashierMode.equals("简易有图模式")){
                EventStatisticalUtil.onEventStacal(EventStatisticalUtil.CLICK_SETTING_FIGURE_MODE_TIMES);
            }else if(cashierMode.equals("条码模式")){
                EventStatisticalUtil.onEventStacal(EventStatisticalUtil.CLICK_SETTING_BARCODE_MODE_TIMES);
            }
        }

        SharedPreferenceUtil.saveValue(USER_SP, CASHIER_MODE,cashierMode);
    }

    //获取销售单打印份数
    public static String getPrintNum() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, PRINT_NUM);
    }

    public static void setPrintNum(String printNum) {
        SharedPreferenceUtil.saveValue(USER_SP, PRINT_NUM, printNum);
    }

    //获取销售单打印份数
    public static String getShowLine() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, SHOW_LINE);
    }

    public static void setShowLine(String showLine) {
        SharedPreferenceUtil.saveValue(USER_SP, SHOW_LINE, showLine);
    }


    public static String getOpenWeight() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, OPEN_WEIGHT);
    }

    public static void setOpenWeight(String openWeight) {
        SharedPreferenceUtil.saveValue(USER_SP, OPEN_WEIGHT, openWeight);
    }


    //获取销售单打印份数
    public static String getConnectKeyBoard() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, CONNECT_KEYBOARD);
    }

    public static void setConnectKeyBoard(String openWeight) {
        SharedPreferenceUtil.saveValue(USER_SP, CONNECT_KEYBOARD, openWeight);
    }


    //获取销售单打印份数
    public static String getDownLoadApkUrl() {
        return SharedPreferenceUtil.getStringValueByKey(USER_SP, DOWNLOAD_APK_URL);
    }

    public static void setDownloadApkUrl(String showLine) {
        SharedPreferenceUtil.saveValue(USER_SP, DOWNLOAD_APK_URL, showLine);
    }

    public static void setDeteleBottomMenu(boolean isFlag){
        SharedPreferenceUtil.saveValue(USER_SP,BAISHA_DELECT_BOTTOM_MENU,isFlag);
    }

    public static boolean getDeteleBottomMenu(){
        return SharedPreferenceUtil.getBooleanValueByKey(USER_SP,BAISHA_DELECT_BOTTOM_MENU);
    }

}
