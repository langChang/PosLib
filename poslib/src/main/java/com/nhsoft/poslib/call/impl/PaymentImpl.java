package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.order.Payment;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.service.greendao.PaymentDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class PaymentImpl {
    private static PaymentImpl instance;
    public static PaymentImpl getInstance(){
        if (instance==null){
            instance=new PaymentImpl();
        }
        return instance;
    }

    public boolean insertBean(final Payment payment){
        final PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        return MatterUtils.doMatter(paymentDao, new Runnable() {
            @Override
            public void run() {
                paymentDao.insertOrReplaceInTx(payment);
            }
        });
    }


    public boolean change20200920PaymentStatus(){

        final PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        final List<Payment> list = paymentDao.queryBuilder().where(PaymentDao.Properties.ShiftTableBizday.eq("20200920"), PaymentDao.Properties.SystemBookCode.eq("62282"),
                PaymentDao.Properties.PaymentPayBy.eq(LibConfig.C_PAYMENT_TYPE_PETCARD_NAME)).build().list();
        if(list != null){
            for (Payment payment : list){
                payment.setPaymentBalance(0);
            }
        }
        return MatterUtils.doMatter(paymentDao, new Runnable() {
            @Override
            public void run() {
                if(list != null && list.size() > 0){
                    paymentDao.insertOrReplaceInTx(list);
                }

            }
        });
    }
}
