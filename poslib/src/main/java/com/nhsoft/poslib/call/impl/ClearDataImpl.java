package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.call.callback.ClearDataCallback;
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
        OrderImpl.getInstance().deleteOrderByNDayData(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));
        ClientPointImpl.deleteClient(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));
        VipSendCardSuccessImpl.getInstance().deleteOverDateData(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));
        PayStyleImpl.getInstance().deleteCardDeposit(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));
        PayStyleImpl.getInstance().deleteRelatCard(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));
        PayStyleImpl.getInstance().deleteReplaceCard(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));
        ShiftTableImpl.getInstance().deleteShiftTable(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));
        PosCarryLogImpl.deleteLogData(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));
        VipIcInitImpl.getInstance().deleteOverDateBean(TimeUtil.getInstance().getNDaysDate(TimeUtil.getInstance().getNowDateString(), days));

    }
}
