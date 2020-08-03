package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.AccountBank;
import com.nhsoft.poslib.service.greendao.AccountBankDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

/**
 * Created by Iverson on 2018/11/19 5:34 PM
 * 此类用于：
 */
public class AccountBankImpl {


    private static AccountBankImpl instance;

    public static AccountBankImpl getInstance() {
        if (instance == null) {
            instance = new AccountBankImpl();
        }
        return instance;
    }

    /**
     * 保存bean
     * @param dataLis
     * @return
     */
    public  boolean saveAccountBankList(final List<AccountBank> dataLis) {
        final AccountBankDao mAccountBankDao = DaoManager.getInstance().getDaoSession().getAccountBankDao();
        mAccountBankDao.deleteAll();
        if (dataLis.size() == 0) return true;
        boolean isSuccess = MatterUtils.doMatter(mAccountBankDao, new Runnable() {
            @Override
            public void run() {
                mAccountBankDao.insertOrReplaceInTx(dataLis);
            }
        });
        return isSuccess;
    }

    /**
     * 获取本门店非现金银行
     * @param branchNum
     * @return
     */
    public  List<AccountBank> getAccountBankList(int branchNum) {
        final AccountBankDao mAccountBankDao = DaoManager.getInstance().getDaoSession().getAccountBankDao();
        return mAccountBankDao.queryBuilder().where(
                AccountBankDao.Properties.Account_bank_cash_flag.eq(false),
                AccountBankDao.Properties.Account_bank_enabled.eq(true),
                AccountBankDao.Properties.Account_bank_branch_num.eq(branchNum)
        ).list();
    }

    /**
     * 获取现金银行
     * @return
     */
    public AccountBank getAccountBankNum(){
        final AccountBankDao mAccountBankDao = DaoManager.getInstance().getDaoSession().getAccountBankDao();
        List<AccountBank> list = mAccountBankDao.queryBuilder().where(
                AccountBankDao.Properties.Account_bank_cash_flag.eq(true),
                AccountBankDao.Properties.Account_bank_enabled.eq(true)
        ).list();
        if (list==null||list.size()==0){
            return new AccountBank();
        }else  {
            return list.get(0);
        }
    }



}
