package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.PolicyDiscount;
import com.nhsoft.poslib.entity.PolicyDiscountDetail;
import com.nhsoft.poslib.service.greendao.PolicyDiscountDao;
import com.nhsoft.poslib.service.greendao.PolicyDiscountDetailDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.ArrayList;
import java.util.List;

public class PolicyDiscountImpl {


    private static PolicyDiscountImpl instance;
    public static PolicyDiscountImpl getInstance(){
        if (instance==null){
            instance=new PolicyDiscountImpl();
        }
        return instance;
    }


    public boolean savePolicyDiscountList(final List<PolicyDiscount> policyDiscountList){
        final PolicyDiscountDao policyDiscountDao= DaoManager.getInstance().getDaoSession().getPolicyDiscountDao();
        policyDiscountDao.deleteAll();
        final PolicyDiscountDetailDao policyDiscountDetailDao=DaoManager.getInstance().getDaoSession().getPolicyDiscountDetailDao();
        policyDiscountDetailDao.deleteAll();
        return MatterUtils.doMatter(policyDiscountDao, new Runnable() {
            @Override
            public void run() {
                for (PolicyDiscount policyDiscount:policyDiscountList){
                    policyDiscountDao.insertOrReplaceInTx(policyDiscount);
                    policyDiscountDetailDao.insertOrReplaceInTx(policyDiscount.getPolicy_discount_details());
                }
            }
        });
    }


    public static List<PolicyDiscount> getNewestPolciyDiscount() {
        final PolicyDiscountDao policyDiscountDao= DaoManager.getInstance().getDaoSession().getPolicyDiscountDao();
        final PolicyDiscountDetailDao policyDiscountDetailDao = DaoManager.getInstance().getDaoSession().getPolicyDiscountDetailDao();
        List<PolicyDiscount> policyDiscounts = policyDiscountDao.queryBuilder().
                orderDesc(PolicyDiscountDao.Properties.Policy_discount_audit_time).build().list();
//                orderDesc(PolicyDiscountDao.Properties.Policy_discount_bill_money,PolicyDiscountDao.Properties.Policy_discount_audit_time).build().list();

        if (policyDiscounts != null) {
            for (PolicyDiscount policyDiscount : policyDiscounts) {
                List<PolicyDiscountDetail> list = policyDiscountDetailDao.queryBuilder().
                        where(PolicyDiscountDetailDao.Properties.Policy_discount_no.eq(policyDiscount.getPolicy_discount_no())).build().list();
                policyDiscount.setPolicyDiscountDetalils(list);
            }
            return policyDiscounts;
        } else {
            return new ArrayList<>();
        }
    }

}
