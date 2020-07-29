package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.PolicyPresent;
import com.nhsoft.poslib.entity.PolicyPresentDetail;
import com.nhsoft.poslib.service.greendao.PolicyPresentDao;
import com.nhsoft.poslib.service.greendao.PolicyPresentDetailDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.ArrayList;
import java.util.List;

public class PolicyPresentImpl {
    private static PolicyPresentImpl instance;

    public static PolicyPresentImpl getInstance(){

        if (instance==null){
            instance=new PolicyPresentImpl();
        }
        return instance;
    }

    public boolean saveBean(final List<PolicyPresent> policyPresentList){
        final PolicyPresentDao policyPresentDao  = DaoManager.getInstance().getDaoSession().getPolicyPresentDao();
        policyPresentDao.deleteAll();
        final PolicyPresentDetailDao policyPresentDetailDao =DaoManager.getInstance().getDaoSession().getPolicyPresentDetailDao();
        policyPresentDetailDao.deleteAll();
        return MatterUtils.doMatter(policyPresentDao, new Runnable() {
            @Override
            public void run() {
                for (PolicyPresent policyPresent : policyPresentList){
                    policyPresentDao.insertOrReplaceInTx(policyPresent);
                    policyPresentDetailDao.insertOrReplaceInTx(policyPresent.getPolicy_present_details());
                }
            }
        });
    }


    public static List<PolicyPresent> getNewestPolciyPresent() {
        final PolicyPresentDao policyPresentDao= DaoManager.getInstance().getDaoSession().getPolicyPresentDao();
        final PolicyPresentDetailDao policyPresentDetailDao =DaoManager.getInstance().getDaoSession().getPolicyPresentDetailDao();

        List<PolicyPresent> policyPresents = policyPresentDao.queryBuilder().
                orderDesc(PolicyPresentDao.Properties.Policy_present_audit_time).build().list();

        if (policyPresents != null) {
            for (PolicyPresent policyPresent : policyPresents) {
                List<PolicyPresentDetail> list = policyPresentDetailDao.queryBuilder().
                        where(PolicyPresentDetailDao.Properties.Policy_present_no.eq(policyPresent.getPolicy_present_no())).build().list();
                policyPresent.setPolicy_present_details(list);
            }
            return policyPresents;
        } else {
            return new ArrayList<>();
        }
    }

}
