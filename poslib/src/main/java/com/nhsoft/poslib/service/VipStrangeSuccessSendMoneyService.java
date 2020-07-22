package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.VipStrangeSuccessSendMoney;
import com.nhsoft.poslib.service.greendao.VipStrangeSuccessSendMoneyDao;
import com.nhsoft.poslib.utils.MatterUtils;

public class VipStrangeSuccessSendMoneyService {

    private static VipStrangeSuccessSendMoneyService instance;
    public  static VipStrangeSuccessSendMoneyService getInstance(){
        if (instance==null){
            instance=new VipStrangeSuccessSendMoneyService();
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
