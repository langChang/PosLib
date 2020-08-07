package com.nhsoft.poslib.utils;

import android.text.TextUtils;
import android.util.Log;

import com.nhsoft.poslib.R;
import com.nhsoft.poslib.RetailPosManager;

/**
 * Created by Iverson on 2018/12/10 6:01 PM
 * 此类用于：
 */
public class OrderPayNoUtils {


    public static String getNewOrderPayNo(String orderPayNo) {
        if (TextUtils.isEmpty(orderPayNo)) {
            return "A";
        } else {

            return getCharLast(orderPayNo);
//            if (orderPayNo.charAt(orderPayNo.length() - 1) >= 'A' && orderPayNo.charAt(orderPayNo.length() - 1) <= 'Z') {
//                // Complete this case
//                String strinPre = orderPayNo.substring(0, orderPayNo.length() - 1);
//                char charValue = orderPayNo.charAt(orderPayNo.length() - 1);
//                return strinPre + ((char) ((int) charValue + 1));
//            }
        }
    }


    //获取字母前缀
    public static String getCharLast(String payNo) {
        String[] charArray = RetailPosManager.sContext.getResources().getStringArray(R.array.C_CHARCATE);
        String charArray1 = "ABCDEFGHIJLKMNPQRSTUVWXYZ";
        int posMachineSequence = 0 ;
        for (int i =0; i < payNo.length(); i++){
            posMachineSequence += Math.pow(25,payNo.length() - i - 1) * (charArray1.indexOf(payNo.charAt(i)) + 1);
        }
        Log.e("loginSecod",""+posMachineSequence);
        StringBuilder charBuilder = new StringBuilder();
        if (posMachineSequence < 25) {
            charBuilder.append(String.valueOf(charArray[posMachineSequence]));
        } else if (posMachineSequence < 25 * 25 * 25) {
            int firstChar = posMachineSequence / (25);
            if (firstChar > 25) {
                int secondChar = firstChar % 25;
                firstChar = firstChar / 25;
                if (secondChar == 0) {
                    secondChar = 25;
                    firstChar--;
                }
                charBuilder.append(charArray[firstChar - 1]).append(charArray[secondChar - 1]);
            } else {
                charBuilder.append(charArray[firstChar - 1]);
            }
            int lastChar = posMachineSequence % 25;
            charBuilder.append(charArray[lastChar]);
        }
        return charBuilder.toString();
    }

}
