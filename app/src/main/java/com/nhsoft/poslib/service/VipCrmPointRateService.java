package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.VipCrmPoint;
import com.nhsoft.poslib.entity.VipCrmPointRate;
import com.nhsoft.poslib.service.greendao.VipCrmPointRateDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

/**
 * @Author ADMIN
 * @time 2020-04-21 18:17
 */

public class VipCrmPointRateService {

    private static VipCrmPointRateService instance;

    public static VipCrmPointRateService getInstance() {

        if (instance == null) {
            instance = new VipCrmPointRateService();
        }
        return instance;
    }

    public boolean insertBean(final VipCrmPoint vipCrmPoint) {
        final VipCrmPointRateDao vipCrmPointRateDao = DaoManager.getInstance().getDaoSession().getVipCrmPointRateDao();
        vipCrmPointRateDao.deleteAll();
        if (vipCrmPoint == null || vipCrmPoint.getPoint_rule() == null) return true;
        return MatterUtils.doMatter(vipCrmPointRateDao, new Runnable() {
            @Override
            public void run() {
                vipCrmPointRateDao.insertOrReplaceInTx(vipCrmPoint.getPoint_rule());
            }
        });
    }

    public VipCrmPointRate getBean(){
        final VipCrmPointRateDao vipCrmPointRateDao = DaoManager.getInstance().getDaoSession().getVipCrmPointRateDao();
        List<VipCrmPointRate> vipCrmPointRates = vipCrmPointRateDao.loadAll();
        if (vipCrmPointRates==null || vipCrmPointRates.size()==0){
            return null;
        }else {
            return vipCrmPointRates.get(0);
        }
    }

}
