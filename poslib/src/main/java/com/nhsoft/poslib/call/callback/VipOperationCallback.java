package com.nhsoft.poslib.call.callback;

import com.nhsoft.poslib.model.VipUserInfo;

/**
 * Created by Iverson on 2020/8/20 7:03 PM
 * 此类用于：
 */
public interface VipOperationCallback {

    void refreshCurrentVipInfo(VipUserInfo vipUserInfo);

}
