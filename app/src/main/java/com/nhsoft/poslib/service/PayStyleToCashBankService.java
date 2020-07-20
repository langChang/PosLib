package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.PayStyleToCashBank;
import com.nhsoft.poslib.service.greendao.PayStyleToCashBankDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class PayStyleToCashBankService {

    private static PayStyleToCashBankService instance;

    public static PayStyleToCashBankService getInstance(){
        if (instance==null){
            instance=new PayStyleToCashBankService();
        }
        return instance;
    }

    /**
     * 建立现金银行和支付方式对应关系
     * @param mapList
     * @return
     */
    public boolean saveBean(final List<PayStyleToCashBank> mapList){
        final PayStyleToCashBankDao bankDao= DaoManager.getInstance().getDaoSession().getPayStyleToCashBankDao();

       return MatterUtils.doMatter(bankDao, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mapList.size(); i++) {
                    bankDao.insertOrReplaceInTx(mapList.get(i));
                }

            }
        });
    }

    /**
     * 根据支付方式查找bean
     * @param id
     * @return
     */
    public  PayStyleToCashBank getBean(String id){
        final PayStyleToCashBankDao bankDao= DaoManager.getInstance().getDaoSession().getPayStyleToCashBankDao();
        PayStyleToCashBank unique = bankDao.queryBuilder().where(PayStyleToCashBankDao.Properties.PayStyleCode.eq(id)).unique();
        return unique;
    }


    /**
     * 根据支付方式名称查找bean
     * @param payTypeName
     * @return
     */
    public  PayStyleToCashBank getBeanByName(String payTypeName){
        if (payTypeName.equals("支付宝")||payTypeName.equals("微信支付")||payTypeName.equals("云闪付")
                ||payTypeName.equals("移动和包支付")||payTypeName.equals("翼支付")){
            payTypeName="在线支付";
        }
        final PayStyleToCashBankDao bankDao= DaoManager.getInstance().getDaoSession().getPayStyleToCashBankDao();
        PayStyleToCashBank unique = bankDao.queryBuilder().where(
                PayStyleToCashBankDao.Properties.PayStyleName.eq(payTypeName)
        ).unique();
        return unique;
    }

}
