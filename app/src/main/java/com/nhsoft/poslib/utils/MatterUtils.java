package com.nhsoft.poslib.utils;

import android.util.Log;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by Iverson on 2018/11/15 10:12 AM
 * 此类用于：数据库事务操作的内容
 */
public class MatterUtils {

    /**
     * @param dao 需要操作事物dao文件
     * @param runnable 事务要做的事情
     * @return false : 代表数据库操作失败 true : 代表数据库操作成功
     */
    public static boolean doMatter(AbstractDao dao,Runnable runnable){
        String daoName = dao.getClass().getSimpleName();
        EvtLog.e("matter process","matter start: "+daoName);
        try {
            dao.getSession().runInTx(runnable);
        }catch (Exception e){
            Log.e("matter process","matter failure: "+daoName);
            e.printStackTrace();
            return false;
        }
        EvtLog.e("matter process","matter success: "+daoName);
        return true;
    }

}
