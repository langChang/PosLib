package com.nhsoft.poslib.utils;




import com.nhsoft.poslib.libconfig.LibConfig ;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * Created by Iverson on 2018/11/22 3:16 PM
 * 此类用于：
 */
public class NumberUtil {

    public static double getNewDouble(double value) {
        BigDecimal b = new BigDecimal(value);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    public static float getNewFloat(float value) {
        float format = (float) (Math.round(value * 100)) / 100;


//        BigDecimal b = new BigDecimal(value);
//        value = b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
//        value = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
//        DecimalFormat df = new DecimalFormat("#.00");
//        String format = df.format(value);
//        return Float.parseFloat(format);
        return Float.parseFloat("" + format);
//
    }

    public static float getShortNewFloat(float value) {
        int format = Math.round(value * 10) ;
        float finalValue = format/10.0f;
        return Float.parseFloat("" + finalValue);
//
    }

    public static float getNewLongFloat(float value) {
        float format = (float) (Math.round(value * 1000)) / 1000;


//        BigDecimal b = new BigDecimal(value);
//        value = b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
//        value = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
//        DecimalFormat df = new DecimalFormat("#.00");
//        String format = df.format(value);
//        return Float.parseFloat(format);
        return Float.parseFloat("" + format);
//
    }

    public static String getNewFloatString(float value) {
        try {
            BigDecimal obj = new BigDecimal(value);
            DecimalFormat df = new DecimalFormat("#.00");
            if (obj.compareTo(BigDecimal.ZERO) == 0) {
                return "0.00";
            } else if (obj.compareTo(BigDecimal.ZERO) > 0 && obj.compareTo(new BigDecimal(1)) < 0) {
                return "0" + df.format(obj);
            } else if (obj.compareTo(BigDecimal.ZERO) < 0 && obj.compareTo(new BigDecimal(-1)) > 0) {
                return "-0" + df.format(obj).replace("-", "");
            } else {
                return df.format(obj);
            }
        } catch (Exception e) {
            return "";
        }
    }

    public static String getNewShortFloatString(float value) {
        try {
            int format = Math.round(value * 10) ;
            float finalValue = format/10.0f;
            return finalValue+"0";
        } catch (Exception e) {
            return "";
        }
    }

    public static String getNewFloatLongString(Double value) {
        try {
            BigDecimal obj = new BigDecimal(value);
            DecimalFormat df = new DecimalFormat("#.000");
            if (obj.compareTo(BigDecimal.ZERO) == 0) {
                return "0.000";
            } else if (obj.compareTo(BigDecimal.ZERO) > 0 && obj.compareTo(new BigDecimal(1)) < 0) {
                return "0" + df.format(obj);
            } else if (obj.compareTo(BigDecimal.ZERO) < 0 && obj.compareTo(new BigDecimal(-1)) > 0) {
                return "-0" + df.format(obj).replace("-", "");
            } else {
                return df.format(obj);
            }
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * 金额取整方法
     *
     * @param value
     * @param roundType
     * @param precision
     * @return
     */
    public static float roundMoney(float value, String roundType, String precision) {
        float fuhao = value/Math.abs(value);

        float finalValue = 0;
        value = NumberUtil.getNewLongFloat(Math.abs(value));
        BigDecimal b = new BigDecimal(value+0.0001);
        BigDecimal nagate = BigDecimal.ONE;
        if (b.compareTo(BigDecimal.ZERO) < 0) {
            b = b.abs();
            nagate = BigDecimal.ONE.negate();
        }


        int newScale;
        switch (precision) {
            case LibConfig.S_PRECISION_YUAN:
                newScale = 0;
                break;
            case LibConfig.S_PRECISION_JIAO:
                newScale = 1;
                break;
            case LibConfig.S_PRECISION_FEN:
                newScale = 2;
                break;
            default:
                newScale = 2;
        }

        switch (roundType) {
            case LibConfig.S_ROUND_UP:
                b = new BigDecimal(value - 0.0001);;
                BigDecimal multiply = nagate.multiply(b.setScale(newScale, BigDecimal.ROUND_UP));
                float floatValue = multiply.floatValue();
                finalValue = NumberUtil.getNewLongFloat(floatValue);
                break;
            case LibConfig.S_ROUND_DOWN:
//                finalValue = NumberUtil.getNewLongFloat(nagate.multiply(b.setScale(newScale, BigDecimal.ROUND_DOWN)).floatValue());
                if(newScale == 0){
                    finalValue = ((int)value)/1.0f;
                }else if(newScale == 1){
                    finalValue = ((int)(value * 10))/10.0f;
                }else if(newScale == 2){
                    finalValue = ((int)(value * 100))/100.0f;
                }
                break;
            case LibConfig.S_ROUND_HALF:
                finalValue = NumberUtil.getNewLongFloat(nagate.multiply(b.setScale(newScale, BigDecimal.ROUND_HALF_UP)).floatValue());
                break;
            case LibConfig.S_ROUND_HALF_FIVE:
                int intb = b.intValue();
                BigDecimal remain = b.subtract(BigDecimal.valueOf(intb));
                if (remain.compareTo(BigDecimal.valueOf(0.25)) < 0) {
                    finalValue = nagate.multiply(BigDecimal.valueOf(intb).add(BigDecimal.ZERO)).floatValue();
                } else if (remain.compareTo(BigDecimal.valueOf(0.75)) >= 0) {
                    finalValue = nagate.multiply(BigDecimal.valueOf(intb).add(BigDecimal.ONE)).floatValue();
                }else {
                    finalValue = nagate.multiply(BigDecimal.valueOf(intb).add(BigDecimal.valueOf(0.5))).floatValue();
                }
                break;
            default:
                finalValue = NumberUtil.getNewLongFloat(nagate.multiply(b.setScale(newScale, BigDecimal.ROUND_HALF_UP)).floatValue());
        }

        return NumberUtil.getNewFloat(finalValue * fuhao);

    }


    /**
     * 金额取整方法
     *
     * @param value
     * @param roundType
     * @param precision
     * @return
     */
    public static float roundZeroMoney(float value, String roundType, String precision) {
        float finalValue = 0;
        value = NumberUtil.getNewLongFloat(value);
        BigDecimal b = new BigDecimal(value+0.0001);
        BigDecimal nagate = BigDecimal.ONE;
        if (b.compareTo(BigDecimal.ZERO) < 0) {
            b = b.abs();
            nagate = BigDecimal.ONE.negate();
        }


        int newScale;
        switch (precision) {
            case LibConfig.S_PRECISION_YUAN:
                newScale = 0;
                break;
            case LibConfig.S_PRECISION_JIAO:
                newScale = 1;
                break;
            case LibConfig.S_PRECISION_FEN:
                newScale = 2;
                break;
            default:
                newScale = 2;
        }

        switch (roundType) {
            case LibConfig.S_ROUND_UP:
                finalValue = nagate.multiply(b.setScale(newScale, BigDecimal.ROUND_UP)).floatValue();
                break;
            case LibConfig.S_ROUND_DOWN:
//                finalValue = nagate.multiply(b.setScale(newScale, BigDecimal.ROUND_DOWN)).floatValue();

                if(newScale == 0){
                    finalValue = ((int)value)/1.0f;
                }else if(newScale == 1){
                    finalValue = ((int)(value * 10))/10.0f;
                }else if(newScale == 2){
                    finalValue = ((int)(value * 100))/100.0f;
                }
                break;
            case LibConfig.S_ROUND_HALF:
                finalValue = nagate.multiply(b.setScale(newScale, BigDecimal.ROUND_HALF_UP)).floatValue();
                break;
            case LibConfig.S_ROUND_HALF_FIVE:
                int intb = b.intValue();
                BigDecimal remain = b.subtract(BigDecimal.valueOf(intb));
                if (remain.compareTo(BigDecimal.valueOf(0.5)) < 0) {
                    finalValue = nagate.multiply(BigDecimal.valueOf(intb).add(BigDecimal.ZERO)).floatValue();
                } else {
                    finalValue = nagate.multiply(BigDecimal.valueOf(intb).add(BigDecimal.valueOf(0.5))).floatValue();
                }
                break;
            default:
                finalValue = nagate.multiply(b.setScale(newScale, BigDecimal.ROUND_HALF_UP)).floatValue();
        }

        return NumberUtil.getNewFloat(finalValue);

    }


    //判断整数（int）
    public static boolean isInteger(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    //判断浮点数（double和float）
    public static boolean isDouble(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }
    public static boolean isNumeric(String str)
    {for (int i = 0; i < str.length(); i++){if (!Character.isDigit(str.charAt(i))){return false;}}return true;}

}
