package com.nhsoft.poslib.call.callback;

import com.nhsoft.poslib.entity.shift.ShiftTable;

/**
 * Created by Iverson on 2020/7/29 9:31 AM
 * 此类用于：关于班次的接口
 */
public interface ShiftTableCallback {


    /**
     * 获取班次信息
     * @param shiftTable 班次
     * @return 班次的json内容
     */
    String getShiftTableToString(ShiftTable shiftTable);
}
