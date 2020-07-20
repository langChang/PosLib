package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.AmountPay;
import com.nhsoft.poslib.service.greendao.AmountPayDao;
import com.nhsoft.poslib.utils.MatterUtils;


public class AmountPayService {
    private static AmountPayService instance;

    public static AmountPayService getInstance(){
        if (instance==null){
            instance=new AmountPayService();
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
