package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.VipStrangeSuccessSendMoney;
import com.nhsoft.poslib.service.greendao.VipStrangeSuccessSendMoneyDao;
import com.nhsoft.poslib.utils.MatterUtils;

public class VipStrangeSuccessSendMoneyImpl {

    private static VipStrangeSuccessSendMoneyImpl instance;
    public  static VipStrangeSuccessSendMoneyImpl getInstance(){
        if (instance==null){
            instance=new VipStrangeSuccessSendMoneyImpl();
        }
        return instance;
    }

    public boolean insertVipStrangeSuccessSendMoney(final VipStrangeSuccessSendMoney vipStrangeSuccessSendMoney){
        final VipStrangeSuccessSendMoneyDao vipStrangeSuccessSendMoneyDao=DaoManager.getInstance().getDaoSession().getVipStrangeSuccessSendMoneyDao();
        return MatterUtils.doMatter(vipStrangeSuccessSendMoneyDao, new Runnable() {
            @Override
            public void run() {
                vipStrangeSuccessSendMoneyDao.insertOrReplaceInTx(vipStrangeSuccessSendMoney);
            }
        });
    }
}
