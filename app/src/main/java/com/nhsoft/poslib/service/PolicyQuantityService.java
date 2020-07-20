package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.PolicyQuantity;
import com.nhsoft.poslib.entity.PolicyQuantityDetail;
import com.nhsoft.poslib.service.greendao.PolicyQuantityDao;
import com.nhsoft.poslib.service.greendao.PolicyQuantityDetailDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.ArrayList;
import java.util.List;

public class PolicyQuantityService {
    private static PolicyQuantityService instance;

    public static PolicyQuantityService getInstance(){

        if (instance==null){
            instance=new PolicyQuantityService();
        }
        return instance;
    }

    public boolean saveBean(final List<PolicyQuantity> policyDiscountList){
        final PolicyQuantityDao policyQuentityDao= DaoManager.getInstance().getDaoSession().getPolicyQuantityDao();
        policyQuentityDao.deleteAll();
        final PolicyQuantityDetailDao policyQuentityDetailDao =DaoManager.getInstance().getDaoSession().getPolicyQuantityDetailDao();
        policyQuentityDetailDao.deleteAll();
        return MatterUtils.doMatter(policyQuentityDao, new Runnable() {
            @Override
            public void run() {
                for (PolicyQuantity policyQuantity : policyDiscountList){
                    policyQuentityDao.insertOrReplaceInTx(policyQuantity);
                    policyQuentityDetailDao.insertOrReplaceInTx(policyQuantity.getPolicy_promotion_quantity_details());
                }
            }
        });
    }


    public static List<PolicyQuantity> getNewestPolciyQuantity() {
        final PolicyQuantityDao policyQuantityDao= DaoManager.getInstance().getDaoSession().getPolicyQuantityDao();
        final PolicyQuantityDetailDao policyQuantityDetailDao =DaoManager.getInstance().getDaoSession().getPolicyQuantityDetailDao();

        List<PolicyQuantity> policyQuantities = policyQuantityDao.queryBuilder().
                orderDesc(PolicyQuantityDao.Properties.Promotion_quantity_audit_time).build().list();

        if (policyQuantities != null) {
            for (PolicyQuantity policyQuantity : policyQuantities) {
                List<PolicyQuantityDetail> list = policyQuantityDetailDao.queryBuilder().
                        where(PolicyQuantityDetailDao.Properties.Promotion_quantity_no.eq(policyQuantity.getPromotion_quantity_no())).build().list();
                policyQuantity.setPolicy_quantity_details(list);
            }
            return policyQuantities;
        } else {
            return new ArrayList<>();
        }
    }

}
