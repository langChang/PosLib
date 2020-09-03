package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.OtherRevenue;
import com.nhsoft.poslib.entity.order.Payment;
import com.nhsoft.poslib.service.greendao.OtherRevenueDao;
import com.nhsoft.poslib.service.greendao.PaymentDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class OtherRevenueImpl {

    private static OtherRevenueImpl instance;

    public static OtherRevenueImpl getInstance(){
        if (instance==null){
            instance=new OtherRevenueImpl();
        }
        return instance;
    }

    public boolean insertBean(final OtherRevenue otherRevenue){

        final OtherRevenueDao otherRevenueDao= DaoManager.getInstance().getDaoSession().getOtherRevenueDao();
       return MatterUtils.doMatter(otherRevenueDao, new Runnable() {
            @Override
            public void run() {
                otherRevenueDao.insertOrReplaceInTx(otherRevenue);
            }
        });

    }

    public List<Payment> getShfitOtherPayment(String systemBookCode,int branchNum,int shiftTableNum,String bizDay){
        final PaymentDao paymentDao= DaoManager.getInstance().getDaoSession().getPaymentDao();
        return  paymentDao.queryBuilder().where(
                PaymentDao.Properties.BranchNum.eq(branchNum),
                PaymentDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PaymentDao.Properties.SystemBookCode.eq(systemBookCode),
                PaymentDao.Properties.PaymentNo.like("%"+"OS"+"%"),
                PaymentDao.Properties.ShiftTableBizday.eq(bizDay)).list();
    }

    public List<Payment> getBizdayOtherPayment(String systemBookCode,int branchNum,String bizDay){
        final PaymentDao paymentDao= DaoManager.getInstance().getDaoSession().getPaymentDao();
        return  paymentDao.queryBuilder().where(
                PaymentDao.Properties.BranchNum.eq(branchNum),
                PaymentDao.Properties.SystemBookCode.eq(systemBookCode),
                PaymentDao.Properties.PaymentNo.like("%"+"OS"+"%"),
                PaymentDao.Properties.ShiftTableBizday.eq(bizDay)).list();
    }
}
