package com.nhsoft.poslib.utils;

import java.util.UUID;

/**
 * Created by Iverson on 2019/1/15 8:43 AM
 * 此类用于：
 */
public class UUIDUtils {

    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
