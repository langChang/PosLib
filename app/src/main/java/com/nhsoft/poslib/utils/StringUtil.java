package com.nhsoft.poslib.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.nhsoft.poslib.dao.SettingDao;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Iverson on 2018/11/22 12:04 PM
 * 此类用于：
 */
public class StringUtil {

    private static final int    MOBIL_F_TAG        = 3;
    private static final int    MOBIL_L_TAG        = 7;
    private static final int    MOBIL_P_TAG_PREFIX = 6;
    private static final int    MOBIL_N_TAG_PREFIX = 10;
    private static final String TAG                = "StringUtil";


    public static String isWeightGoods(String item_unit) {
        if ("公斤".equals(item_unit)) {
            return "Kg";
        }
        return item_unit;
    }


    /**
     * 获取任意字母字符
     * @param num
     * @return
     */
    public static String getRandStr(int num){
        String strs = "ABCDEFGHIJKLMNPQRSTUVWXYZ";
        StringBuffer buff = new StringBuffer();
        for(int i=1;i<=num;i++){
            char str = strs.charAt((int)(Math.random() * 25));
            buff.append(str);

        }
        return buff.toString();

    }

    /**
     * 判断字符串是否为null或者空字符串
     *
     * @param input 输入的字符串
     * @return 如果为null或者空字符串，返回true；否则返回false
     */
    public static boolean isNullOrEmpty(String input) {
        return TextUtils.isEmpty(input) || 0 == input.trim().length();
    }

    public static boolean equals(@NonNull String fristCheck, @NonNull String secondCheck) {
        return TextUtils.equals(fristCheck, secondCheck);
    }



    /**
     * 转换保留小数位 字符串
     *
     * @param i      小数位数
     * @param numStr 数字字符串
     * @return String
     */
    public static String getDecimalFormat(int i, String numStr) {
        try {
            if (numStr != null && !"".equals(numStr)) {
                BigDecimal bd = new BigDecimal(numStr);
                bd = bd.setScale(i, BigDecimal.ROUND_HALF_UP);
                return bd.toString();
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 转换保留小数位
     *
     * @param i   小数位数
     * @param num 要转换的数字
     * @return double 返回类型
     */
    public static double decimalFormat(int i, Double num) {
        String temp = getDecimalFormat(i, num.toString());
        return Double.valueOf(temp);
    }

    /**
     * 检查是否是字符串
     *
     * @param input 被检查字符串
     * @return 是否是数字组成的字符串，包括0开头;<br>
     * 如果为空或者空字符串，返回true；比如："011"返回true，"a11"返回false
     */
    public static boolean isNumberString(String input) {
        if (!isNullOrEmpty(input)) {
            if (input.matches("[0-9]+")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param str 带检测的字符串
     * @return String 去掉多余的.与0
     */
    public static String filterZeroAndDot(String str) {
        if (!isNullOrEmpty(str) && str.indexOf(".") > 0) {
            // 去掉多余的0
            str = str.replaceAll("0+?$", "");
            // 如最后一位是.则去掉
            str = str.replaceAll("[.]$", "");
        }
        return str;
    }

    public static String getDecimalFormatString(double input, int digits) {
        BigDecimal bd = new BigDecimal(input);
        bd = bd.setScale(digits, BigDecimal.ROUND_HALF_UP);

        return filterZeroAndDot(bd.toString());
    }

    public static boolean isMobileNumber(String mobiles) {
        Pattern p = Pattern.compile("^(13|14|15|16|17|18|19)\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isEmail(String email) {
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }

    /**
     * 验证中文名字
     */
    public static boolean isValidName(String name) {
        Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5\\·]{2,10}$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    /**
     * 隐藏字符串中间部分 用*代替
     *
     * @param number 加密字符串
     * @param start  起始位置
     * @param end    结束位置
     * @return 加密后的字符串
     */
    public static String hideMiddleNumber(String number, int start, int end) {
        if (isNullOrEmpty(number)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if (i >= start && i <= number.length() - end - 1) {
                sb.append('*');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 验证身份证
     */
    public static boolean isValidIdCard(String idCard) {
        Pattern pattern = Pattern.compile("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$");
        Matcher matcher = pattern.matcher(idCard);
        return matcher.matches();
    }


    public static String getNickName(String phone) {
        return "" + phone.substring(0, 3) + "****" + phone.substring(8);
    }

    //做个不为空的判读
    public static String trimString(String content) {
        return content == null ? "" : content;
    }

    //做个不为空的判读
    public static String decodeString(String content) {
        return TextUtils.isEmpty(content) ? "0" : content;
    }


    public static String getPrintString(String str1, String str2) {
        int printLength = SettingDao.getPrintLength();
        byte[] chars = new byte[0];
        try {
            chars = (str1 + str2).getBytes("gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (chars.length < printLength) {
            StringBuilder blankStr = new StringBuilder();
            for (int i = printLength - chars.length; i > 0; i--) {
                blankStr.append(" ");
            }
            return str1 + blankStr.toString() + str2;
        } else {
            return str1 + str2;
        }
    }

    public static String getHalfPrintString(String str1, String str2) {
        int printLength = SettingDao.getPrintLength();
        byte[] chars = new byte[0];
        try {
            chars = (str1 + str2).getBytes("gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (chars.length < printLength/2) {
            StringBuilder blankStr = new StringBuilder();
            for (int i = printLength/2 - chars.length; i > 1; i--) {
                blankStr.append(" ");
            }
            return str1 + blankStr.toString() + str2;
        } else {
            return str1 + str2;
        }
    }

    public static String getPrintLine(){
        int printLength = SettingDao.getPrintLength();
        String lineString = new String();

        for (int i = 0; i < printLength; i++){
            lineString = lineString + "-";
        }
        return lineString;
    }

    public static String PrintSignFirstLine(){
        int printLength = SettingDao.getPrintLength();
        String lineString = new String();

        for (int i = 0; i < printLength; i++){
            lineString = lineString + "*";
        }
        return lineString;
    }

    public static String PrintSignSecondLine(){
        int printLength = SettingDao.getPrintLength();
        String lineString = new String();

        for (int i = 0; i < printLength; i++){
            lineString = lineString + "=";
        }
        return lineString;
    }

    public static String getPrintStar(){
        int printLength = SettingDao.getPrintLength();
        String lineString = new String();

        for (int i = 0; i < printLength; i++){
            lineString = lineString + "*";
        }
        return lineString;
    }

    public static String getPrintQRString() {
        try {
            StringBuilder blankStr = new StringBuilder();
            int printLength = (SettingDao.getPrintLength() - 16) / 2;
            for (int i = 0; i < printLength; i++) {
                blankStr.append(" ");
            }
            return blankStr.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getPrintString(String str) {
        int printLength = SettingDao.getPrintLength();
        byte[] chars = new byte[0];
        try {
            chars = str.getBytes("gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (chars.length < printLength) {
            StringBuilder blankStr = new StringBuilder();
            int halfValue = (printLength - chars.length) / 2;
            for (int i = 0; i < halfValue; i++) {
                blankStr.append(" ");
            }
            return blankStr.toString() + str;
        } else {
            return str;
        }
    }


    public static String getPrintBarcodeString(int printLength ,String str) {
        byte[] chars = new byte[0];
        try {
            chars = str.getBytes("gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (chars.length < printLength) {
            StringBuilder blankStr = new StringBuilder();
            int halfValue = (printLength - chars.length) / 2;
            for (int i = 0; i < halfValue; i++) {
                blankStr.append(" ");
            }
            String text=blankStr.toString() + str;

            for (int i=blankStr.toString().length()+chars.length;i<printLength;i++){
                text=text+" ";
            }
//            Log.e("blankStr",blankStr.toString().length()+"");
            return text;
        } else {
            return str.substring(0,printLength/2);
        }
    }




    public static String getNomalXmlString(String xml) {
        xml = xml.replace("消费券列表", "CouponsList").replace("商品名称", "GoodsName").replace("消费券名称", "CouponsName")
                .replace("是否启用", "isAble")
                .replace("消费券面值", "CouponsValue").replace("商品类别列表", "CatetoryList")
                .replace("商品类别名称", "CatetoryName")
                .replace("商品类别编码", "CatetoryCode").replace("折扣率", "DiscountBit")
                .replace("商品列表", "GoodsList").replace("支付方式", "PayStyle")
                .replace("商品编号", "GoodsNumber")
                .replace("商品代码", "GoodsCode").replace("消费券数量限制", "MaxNumber")
                .replace("消费券最低消费金额", "LimtFreeMoney").replace("应用分店列表", "BranchList")
                .replace("积分兑券比例", "PointBit").replace("积分兑券默认有效天数", "PointDelayDay")
                .replace("促销特价商品不支持抵扣", "SupportPomotion").replace("消费券应用分类", "CouponsType")
                .replace("消费券超额张数累加", "AddNumber").replace("消费券单独使用", "IsAloneUse")
                .replace("消费券", "Coupons")
                .replace("商品类别", "CatetoryType").replace("商品", "GoodsItem");
        return xml;
    }


    public static String getCouponsKey(){
        return "";
    }


    /**
     * 在线支付转换成最后名字
     * @param currentPayName  当前服务器返回的支付名称
     * @return
     */
    public static String getFinalPayName (String currentPayName){
        if ("微信条码支付".equals(currentPayName) || "微信二维码支付".equals(currentPayName)) {
            return "微信支付" ;
        } else if ("支付宝条码支付".equals(currentPayName) || "支付宝二维码支付".equals(currentPayName)) {
            return "支付宝";
        } else if ("移动和包条码支付".equals(currentPayName)) {
           return  "移动和包支付";
        } else if ("云闪付条码支付".equals(currentPayName)) {
            return "云闪付";
        } else {
            return currentPayName;
        }
    }

    /**
     * 截取前20个字符
     * @param text
     * @return
     */
    public static String getText(String text){
        StringBuffer s=new StringBuffer();
        if (text.getBytes().length>20){
            int length=text.length();
            int index=0;
            for (int i=0;i<length;i++){
                if((text.charAt(i) >= 0x4e00)&&(text.charAt(i) <= 0x9fbb)) {
                    index+=2;
                }else {
                    index+=1;
                }
                if (index>20){
                    return s.toString();
                }else {
                    s.append(text.charAt(i));
                }
            }
        }
        return text;
    }
}
