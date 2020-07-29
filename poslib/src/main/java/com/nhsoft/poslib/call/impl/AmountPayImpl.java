package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.AmountPay;
import com.nhsoft.poslib.service.greendao.AmountPayDao;
import com.nhsoft.poslib.utils.MatterUtils;


public class AmountPayImpl {
    private static AmountPayImpl instance;

    public static AmountPayImpl getInstance(){
        if (instance==null){
            instance=new AmountPayImpl();
        }
        return instance;
    }

    public boolean saveBean(final AmountPay amountPay){
        final AmountPayDao amountPayDao= DaoManager.getInstance().getDaoSession().getAmountPayDao();
        return MatterUtils.doMatter(amountPayDao, new Runnable() {
            @Override
            public void run() {
                amountPayDao.insertOrReplaceInTx(amountPay);
            }
        });
    }

    public void  deleteBean(int days){

    }

}
