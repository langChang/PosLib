package com.nhsoft.poslib.call.impl;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.PosMachine;
import com.nhsoft.poslib.service.greendao.DaoSession;
import com.nhsoft.poslib.service.greendao.PosMachineDao;
import com.nhsoft.poslib.utils.MacUtil;
import com.nhsoft.poslib.utils.MatterUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Iverson on 2020/7/29 9:35 AM
 * 此类用于：关于注册机器的接口的实现
 */
public class PosMachineImpl {

    private static PosMachineImpl instance;
    public static PosMachineImpl getInstance(){
        if (instance==null){
            instance=new PosMachineImpl();
        }
        return instance;
    }


    public boolean savePosMachineList(final List<PosMachine> posMachineList){
        final PosMachineDao posMachineDao = DaoManager.getInstance().getDaoSession().getPosMachineDao();
        posMachineDao.deleteAll();
        if(posMachineList.size() == 0)return true;
        boolean isSuccess = MatterUtils.doMatter(posMachineDao, new Runnable() {
            @Override
            public void run() {
                posMachineDao.insertOrReplaceInTx(posMachineList);
            }
        });
        return isSuccess;
    }

    /**
     * 根据终端标识查找对应的POS机
     * @param systemBookCode
     * @param branchNum
     * @param terminalId
     * @return
     */
    public PosMachine getPosMachine(String systemBookCode, int branchNum, String terminalId){
        if (TextUtils.isEmpty(terminalId)){
            return null;
        }
        PosMachineDao posMachineDao = DaoManager.getInstance().getDaoSession().getPosMachineDao();
        PosMachine posMachine = null;
//        return posMachineDao.queryBuilder()
//                .where(
//                 PosMachineDao.Properties.Pos_machine_terminal_id.eq(terminalId),
//                 PosMachineDao.Properties.Branch_num.eq(branchNum)
//                ).unique();
        List<PosMachine> posMachines = posMachineDao.queryBuilder()
                .where(
                 PosMachineDao.Properties.Branch_num.eq(branchNum)
                ).list();
        for (int i = 0; i <posMachines.size() ; i++) {
            if (posMachines.get(i).getPos_machine_terminal_id().toUpperCase().contains(terminalId.toUpperCase())){
                posMachineDao.detachAll();
                return posMachines.get(i);
            }
        }
        posMachineDao.detachAll();
        return posMachine;
    }



    @SuppressLint({"NewApi", "MissingPermission"})
    public String getSerialNumber() {
        String serial = "";
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {//9.0+
                serial = Build.getSerial();
            } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {//8.0+
                serial = Build.SERIAL;
            } else {//8.0-
                Class<?> c = Class.forName("android.os.SystemProperties");
                Method get = c.getMethod("get", String.class);
                serial = (String) get.invoke(c, "ro.serialno");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("e", "读取设备序列号异常：" + e.toString());
        }
        return serial;
    }


    /**
     * 获取mac地址
     * @return
     */
    public String getMacAddress(){
        if(!TextUtils.isEmpty(MacUtil.mMacAddress))return MacUtil.mMacAddress;
        MacUtil.mMacAddress = MacUtil.getMacAddress();
        return MacUtil.mMacAddress;
    }


    /**
     * 根据终端标识查找对应的POS机
     * @param branchNum
     * @param terminalId
     * @return
     */
    public PosMachine getPosMachine(int branchNum, String terminalId){
        if (TextUtils.isEmpty(terminalId)){
            return null;
        }
        PosMachineDao posMachineDao = DaoManager.getInstance().getDaoSession().getPosMachineDao();
        PosMachine posMachine = null;
        List<PosMachine> posMachines = posMachineDao.queryBuilder()
                .where(
                        PosMachineDao.Properties.Branch_num.eq(branchNum)
                ).list();
        for (int i = 0; i <posMachines.size() ; i++) {
            if (posMachines.get(i).getPos_machine_terminal_id().toUpperCase().contains(terminalId.toUpperCase())){
                posMachineDao.detachAll();
                return posMachines.get(i);
            }
        }
        posMachineDao.detachAll();
        return posMachine;
    }


    public boolean savePosMachine(final PosMachine posMachine) {
        final DaoSession mDaoSession = DaoManager.getInstance().getDaoSession();
        return MatterUtils.doMatter(mDaoSession.getLoginDao(), new Runnable() {
            @Override
            public void run() {
                mDaoSession.getPosMachineDao()
                        .insertOrReplaceInTx(posMachine);
            }
        });
    }
}
