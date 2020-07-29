package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.PolicyMoney;
import com.nhsoft.poslib.entity.PolicyMoneyDetail;
import com.nhsoft.poslib.service.greendao.PolicyMoneyDao;
import com.nhsoft.poslib.service.greendao.PolicyMoneyDetailDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.ArrayList;
import java.util.List;

public class PolicyMoneyImpl {
    private static PolicyMoneyImpl instance;

    public static PolicyMoneyImpl getInstance(){

        if (instance==null){
            instance=new PolicyMoneyImpl();
        }
        return instance;
    }

    public boolean saveBean(final List<PolicyMoney> policyMoneyList){
        final PolicyMoneyDao policyMoneyDao = DaoManager.getInstance().getDaoSession().getPolicyMoneyDao();
        policyMoneyDao.deleteAll();
        final PolicyMoneyDetailDao policyMoneyDetailDao =DaoManager.getInstance().getDaoSession().getPolicyMoneyDetailDao();
        policyMoneyDetailDao.deleteAll();
        return MatterUtils.doMatter(policyMoneyDao, new Runnable() {
            @Override
            public void run() {
                for (PolicyMoney policyMoney : policyMoneyList){
                    policyMoneyDao.insertOrReplaceInTx(policyMoney);
                    policyMoneyDetailDao.insertOrReplaceInTx(policyMoney.getPolicy_promotion_money_details());
                }
            }
        });
    }


    public static List<PolicyMoney> getNewestPolciyMoney() {
        final PolicyMoneyDao policyMoneyDao= DaoManager.getInstance().getDaoSession().getPolicyMoneyDao();
        final PolicyMoneyDetailDao policyMoneyDetailDao =DaoManager.getInstance().getDaoSession().getPolicyMoneyDetailDao();

        List<PolicyMoney> policyMonies = policyMoneyDao.queryBuilder().
                orderDesc(PolicyMoneyDao.Properties.Promotion_money_audit_time).build().list();

        if (policyMonies != null) {
            for (PolicyMoney policyMoney : policyMonies) {
                List<PolicyMoneyDetail> list = policyMoneyDetailDao.queryBuilder().
                        where(PolicyMoneyDetailDao.Properties.Promotion_money_no.eq(policyMoney.getPromotion_money_no())).build().list();
                policyMoney.setPolicy_promotion_money_details(list);
            }
            return policyMonies;
        } else {
            return new ArrayList<>();
        }
    }

}
