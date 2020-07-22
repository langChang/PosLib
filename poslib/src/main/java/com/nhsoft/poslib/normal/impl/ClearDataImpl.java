package com.nhsoft.poslib.normal.impl;

import com.nhsoft.poslib.normal.callback.ClearDataCallback;
import com.nhsoft.poslib.service.ClientPointService;
import com.nhsoft.poslib.service.OrderService;
import com.nhsoft.poslib.service.PayStyleService;
import com.nhsoft.poslib.service.PosCarryLogService;
import com.nhsoft.poslib.service.ShiftTableService;
import com.nhsoft.poslib.service.VipIcInitService;
import com.nhsoft.poslib.service.VipSendCardSuccessService;
import com.nhsoft.poslib.utils.TimeUtil;

/**
 * Created by Iverson on 2019-11-27 15:38
 * 此类用于：
 */
public  class ClearDataImpl implements ClearDataCallback {

    @Override
    public void clearData(int days) {
        days *= -1;
        //每天删除前35
        OrderService.getInstance().deleteOrderByNDayData(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));
        ClientPointService.deleteClient(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));
        VipSendCardSuccessService.getInstance().deleteOverDateData(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));
        PayStyleService.getInstance().deleteCardDeposit(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));
        PayStyleService.getInstance().deleteRelatCard(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));
        PayStyleService.getInstance().deleteReplaceCard(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));
        ShiftTableService.getInstance().deleteShiftTable(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));
        PosCarryLogService.deleteLogData(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));
        VipIcInitService.getInstance().deleteOverDateBean(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));

    }
}
