package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.ShiftTablePayment;
import com.nhsoft.poslib.service.greendao.ShiftTablePaymentDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class ShiftTablePaymentService {

    private static ShiftTablePaymentService instance;

    public static ShiftTablePaymentService getInstance(){
        if (instance==null){
            instance=new ShiftTablePaymentService();
        }
        return instance;
    }

    public boolean saveBean(final ShiftTablePayment shiftTablePayment){
        final ShiftTablePaymentDao shiftTablePaymentDao = DaoManager.getInstance().getDaoSession().getShiftTablePaymentDao();
        return MatterUtils.doMatter(shiftTablePaymentDao, new Runnable() {
            @Override
            public void run() {
                shiftTablePaymentDao.insertOrReplaceInTx(shiftTablePayment);
            }
        });
    }

    public boolean saveBean(final List<ShiftTablePayment> shiftTablePaymentList){
        final ShiftTablePaymentDao shiftTablePaymentDao = DaoManager.getInstance().getDaoSession().getShiftTablePaymentDao();
        return MatterUtils.doMatter(shiftTablePaymentDao, new Runnable() {
            @Override
            public void run() {
                for (ShiftTablePayment shiftTablePayment : shiftTablePaymentList) {
                    shiftTablePayment.setShiftTablePaymentSyncFlag(true);
                    shiftTablePaymentDao.insertOrReplaceInTx(shiftTablePayment);
                }

            }
        });
    }

    public List<ShiftTablePayment> getListBean(String shiftTableNum){
        final ShiftTablePaymentDao shiftTablePaymentDao = DaoManager.getInstance().getDaoSession().getShiftTablePaymentDao();
        return shiftTablePaymentDao.queryBuilder().where
                (ShiftTablePaymentDao.Properties.Shift_table_num.eq(shiftTableNum)
                , ShiftTablePaymentDao.Properties.ShiftTablePaymentSyncFlag.eq(false))
                .list();
    }

}
