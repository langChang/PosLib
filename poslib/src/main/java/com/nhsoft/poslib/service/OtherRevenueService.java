package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.OtherRevenue;
import com.nhsoft.poslib.entity.order.Payment;
import com.nhsoft.poslib.service.greendao.OtherRevenueDao;
import com.nhsoft.poslib.service.greendao.PaymentDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class OtherRevenueService {

    private static OtherRevenueService instance;

    public static OtherRevenueService getInstance(){
        if (instance==null){
            instance=new OtherRevenueService();
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

    public int getNum(String systemBookCode,int branchNum,int shiftTableNum,String bizDay){
        final PaymentDao paymentDao= DaoManager.getInstance().getDaoSession().getPaymentDao();
        return  paymentDao.queryBuilder().where(
                PaymentDao.Properties.BranchNum.eq(branchNum),
                PaymentDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PaymentDao.Properties.SystemBookCode.eq(systemBookCode),
                PaymentDao.Properties.PaymentNo.like("%"+"OS"+"%"),
                PaymentDao.Properties.ShiftTableBizday.eq(bizDay)).list().size();
    }

    public Float getMoney(String systemBookCode,int branchNum,int shiftTableNum,String bizDay){
        float money=0;
        final PaymentDao paymentDao= DaoManager.getInstance().getDaoSession().getPaymentDao();
        List<Payment> list = paymentDao.queryBuilder().where(
                PaymentDao.Properties.BranchNum.eq(branchNum),
                PaymentDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PaymentDao.Properties.SystemBookCode.eq(systemBookCode),
                PaymentDao.Properties.PaymentNo.like("%"+"OS"+"%"),
                PaymentDao.Properties.ShiftTableBizday.eq(bizDay)).list();
        for (Payment mPayment:list ) {
            money=money+mPayment.getPaymentMoney();
        }
        return money;
    }

}
