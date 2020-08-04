package com.nhsoft.poslib.dao;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.nhsoft.poslib.model.SystemBookProxy;
import com.nhsoft.poslib.utils.SharedPreferenceUtil;

/**
 * Created by Iverson on 2018/12/14 1:42 PM
 * 此类用于：
 */
public class SettingDao {

    /*********************用户设置*******************/
    public static final String USER_SETTING = "user_setting";

    public static final String SYSTEM_BOOK = "mac_address";

    public static final String MAC_ADDRESS = "mac_address";
    public static final String MAC_ADDRESS_1 = "mac_address_1";

    public static final String USER_DEV_PATH = "devicePath";
    public static final String CHANGE_GOODS_LIST= "change_goods_lisst";
    public static final String USER_PRINT_LENGTH = "user_print_length";
    public static final String USER_EQUIPMENT_MODEL = "user_equipment_model";
    public static final String PAY_URL_PATH = "pay_url_path";
    public static final String USER_PRINT_TYPE = "user_print_type";//打印方式
    public static final String USER_PRINT_MODE = "user_print_mode";//打印机型号
    public static final String USER_WEIGHT_MODE = "user_weight_mode";//称型号
    public static final String USER_WEIGHT_TYPE = "user_weight_type";//称连接方式
    public static final String USER_EQUIPMENT_BARCODE_MODEL = "user_equipment_barcode_model";//需要下发的条码秤选中的条码秤机型
    public static final String USER_EQUIPMENT_BARCODE_IP_ADRRESS = "user_equipment_barcode_ip_address";//需要下发的条码秤选中的条码秤机型的Ip地址

//
//    //保存串口
    public static void saveDevicePort(String devicePath){
        SharedPreferenceUtil.saveValue(USER_SETTING, USER_DEV_PATH,devicePath);
    }
//
    public static String getDevicePort(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SETTING,USER_DEV_PATH);
    }


    //
//    修改商品属性的list
    public static void saveChangeGoodsList(String goodsList){
        SharedPreferenceUtil.saveValue(USER_SETTING, CHANGE_GOODS_LIST,goodsList);
    }
    //
    public static String getChangeGoodsList(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SETTING,CHANGE_GOODS_LIST);
    }


    //    //保存打印长度
    public static void savePrintLength(int length){
        SharedPreferenceUtil.saveValue(USER_SETTING, USER_PRINT_LENGTH,length);
    }
    //
    public static int getPrintLength(){
        return SharedPreferenceUtil.getIntegerValueByKey(USER_SETTING,USER_PRINT_LENGTH);
    }



    //    //保存打印长度
    public static void saveMacAddress(String mac){
        SharedPreferenceUtil.saveValue(USER_SETTING, MAC_ADDRESS,mac);
    }
    //
    public static String getMacAddress(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SETTING,MAC_ADDRESS);
    }


    //    //保存打印长度
    public static void saveMacAddress1(String mac1){
        SharedPreferenceUtil.saveValue(USER_SETTING, MAC_ADDRESS_1,mac1);
    }
    //
    public static String getMacAddress1(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SETTING,MAC_ADDRESS_1);
    }


    //    //保存设备类型
    public static void saveEquipmentModel(String equipment){
        SharedPreferenceUtil.saveValue(USER_SETTING, USER_EQUIPMENT_MODEL,equipment);
    }
    //
    public static String getEquipmentModel(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SETTING,USER_EQUIPMENT_MODEL);
    }

    //    //保存需下发条码秤设备类型
    public static void saveEquipmentBarcodeModel(String equipment){
        SharedPreferenceUtil.saveValue(USER_SETTING, USER_EQUIPMENT_BARCODE_MODEL,equipment);
    }
    //获取需下发条码秤设备类型
    public static String getEquipmentBarcodeModel(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SETTING,USER_EQUIPMENT_BARCODE_MODEL);
    }

    //    //保存需下发条码秤设备IP地址
    public static void saveEquipmentBarcodeIpAddress(String ip){
        SharedPreferenceUtil.saveValue(USER_SETTING, USER_EQUIPMENT_BARCODE_IP_ADRRESS,ip);
    }
    //获取需下发条码秤设备IP地址
    public static String getEquipmentBarcodeIpAddress(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SETTING,USER_EQUIPMENT_BARCODE_IP_ADRRESS);
    }

    //保存打印方式类型
    public static void savePrintType(String type){
        SharedPreferenceUtil.saveValue(USER_SETTING, USER_PRINT_TYPE,type);
    }
    //
    public static String getPrintType(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SETTING,USER_PRINT_TYPE);
    }

    //保存称重方式类型
    public static void saveWeightType(String type){
        SharedPreferenceUtil.saveValue(USER_SETTING, USER_WEIGHT_TYPE,type);
    }
    //
    public static String getWeightType(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SETTING,USER_WEIGHT_TYPE);
    }

    //保存打印机型号
    public static void savePrintMode(String type){
        SharedPreferenceUtil.saveValue(USER_SETTING, USER_PRINT_MODE,type);
    }
    //
    public static String getPrintMode(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SETTING,USER_PRINT_MODE);
    }

    //保存称型号
    public static void saveWeightMode(String type){
        SharedPreferenceUtil.saveValue(USER_SETTING, USER_WEIGHT_MODE,type);
    }
    //
    public static String getWeightMode(){
        return SharedPreferenceUtil.getStringValueByKey(USER_SETTING,USER_WEIGHT_MODE);
    }


    //获取保存支付地址
    public static void saveSystemBookProxy(SystemBookProxy systemBookProxy){
        if(systemBookProxy == null) return;
        SharedPreferenceUtil.saveValue(USER_SETTING, PAY_URL_PATH,new Gson().toJson(systemBookProxy));
    }

    public static SystemBookProxy getSystemBookProxy(){
        String userInfo = SharedPreferenceUtil.getStringValueByKey(USER_SETTING,PAY_URL_PATH);
        if(TextUtils.isEmpty(userInfo)){
            return null;
        }
        return new Gson().fromJson(userInfo,SystemBookProxy.class);
    }

}
