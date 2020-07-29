package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.order.Payment;
import com.nhsoft.poslib.service.greendao.PaymentDao;
import com.nhsoft.poslib.utils.MatterUtils;

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
}
