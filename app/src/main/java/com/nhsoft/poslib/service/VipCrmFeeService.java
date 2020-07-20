package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.VipCrmFee;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.service.greendao.VipCrmFeeDao;
import com.nhsoft.poslib.utils.MatterUtils;
import com.nhsoft.poslib.utils.TimeUtil;

public class VipCrmFeeService {

    private static  VipCrmFeeService instance;

    public static VipCrmFeeService getInstance(){
        if (instance==null){
            instance=new VipCrmFeeService();
        }
        return instance;
    }

    public boolean insertBean(String phone,String orderType,float money,String payTypeName){
        final VipCrmFee vipCrmFee = setData(phone, orderType, money, payTypeName);
        final VipCrmFeeDao vipCrmFeeDao = DaoManager.getInstance().getDaoSession().getVipCrmFeeDao();
        return MatterUtils.doMatter(vipCrmFeeDao, new Runnable() {
            @Override
            public void run() {
                vipCrmFeeDao.insertOrReplaceInTx(vipCrmFee);
            }
        });
    }

    public VipCrmFee setData(String phone,String orderType,float money,String payTypeName){
        VipCrmFee vipCrmFee = new VipCrmFee();

        vipCrmFee.setBranchNum(LibConfig.activeShiftTable.getBranchNum());
        vipCrmFee.setDate(TimeUtil.getNowDateString(TimeUtil.FORMAT_ONE));
        vipCrmFee.setMacAddress(LibConfig.activeShiftTable.getShiftTableTerminalId());

        vipCrmFee.setOperator(LibConfig.activeShiftTable.getShiftTableUserName());
        vipCrmFee.setVipPhone(phone);
        vipCrmFee.setType(orderType);
        vipCrmFee.setPayMoney(money);

        vipCrmFee.setMemo("");
        vipCrmFee.setPayTypeName(payTypeName);
        vipCrmFee.setShiftTableNum(LibConfig.activeShiftTable.getShiftTableNum()+"");
        vipCrmFee.setSystemBookCode(LibConfig.activeShiftTable.getSystemBookCode());
        return vipCrmFee;
    }

}
