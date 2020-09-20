package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.PolicyQuantity;
import com.nhsoft.poslib.entity.PolicyQuantityDetail;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.service.greendao.PolicyQuantityDao;
import com.nhsoft.poslib.service.greendao.PolicyQuantityDetailDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.ArrayList;
import java.util.List;

public class PolicyQuantityImpl {
    private static PolicyQuantityImpl instance;

    public static PolicyQuantityImpl getInstance(){
        if (instance==null){
            instance=new PolicyQuantityImpl();
        }
        return instance;
    }

    public boolean savePolicyQuantityList(final List<PolicyQuantity> policyDiscountList){
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

                if(policyQuantity.getPromotion_quantity_discount() != null && list != null){
                    for (PolicyQuantityDetail policyQuantityDetail: list){
                        PosItem posItem = RetailPosManager.getInstance().getPosItemByItemNum(policyQuantityDetail.getItem_num());
                        policyQuantityDetail.setPromotion_quantity_detail_special_price(RetailPosManager.getInstance().getItemRegularPrice(posItem,null) * policyQuantity.getPromotion_quantity_discount());
                    }
                }
            }


            return policyQuantities;
        } else {
            return new ArrayList<>();
        }
    }

}
