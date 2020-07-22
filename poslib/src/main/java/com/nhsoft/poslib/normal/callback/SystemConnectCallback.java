package com.nhsoft.poslib.normal.callback;

/**
 * Created by Iverson on 2019-11-28 15:31
 * 此类用于：
 */
public interface SystemConnectCallback {

    void showErrorInfo(String errorInfo);

    void showWarnInfo(String warnInfo);

    void openDraw();
}
