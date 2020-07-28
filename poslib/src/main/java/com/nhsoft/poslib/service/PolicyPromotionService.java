package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.PolicyPromotion;
import com.nhsoft.poslib.entity.PolicyPromotionDetail;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.service.greendao.PolicyPromotionDao;
import com.nhsoft.poslib.service.greendao.PolicyPromotionDetailDao;
import com.nhsoft.poslib.utils.MatterUtils;
import com.nhsoft.poslib.utils.PricePromotionCalUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2018/11/15 11:29 AM
 */
public class PolicyPromotionService {

    public static boolean savePolicyPromotion(final List<PolicyPromotion> result) {


        final PolicyPromotionDao policyPromotionDao = DaoManager.getInstance().getDaoSession().getPolicyPromotionDao();
        final PolicyPromotionDetailDao policyPromotionDetailDao = DaoManager.getInstance().getDaoSession().getPolicyPromotionDetailDao();
        policyPromotionDetailDao.deleteAll();
        policyPromotionDao.deleteAll();
        if (result.size() == 0) return true;

        return MatterUtils.doMatter(policyPromotionDao, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < result.size(); i++) {
                    PolicyPromotion pointPolicy = result.get(i);
                    policyPromotionDao.insertOrReplaceInTx(pointPolicy);
                    List<PolicyPromotionDetail> policy_promotion_details = pointPolicy.getPolicy_promotion_details();
                    if (policy_promotion_details != null && policy_promotion_details.size() > 0) {
                        policyPromotionDetailDao.insertOrReplaceInTx(policy_promotion_details);
                    }
                }
            }
        });
    }

    public static List<PolicyPromotion> getNewestPolciyPromotion() {
        PolicyPromotionDao policyPromotionDao = DaoManager.getInstance().getDaoSession().getPolicyPromotionDao();
        PolicyPromotionDetailDao policyPromotionDetailDao = DaoManager.getInstance().getDaoSession().getPolicyPromotionDetailDao();
        List<PolicyPromotion> policyPromotions = policyPromotionDao.queryBuilder().
                orderDesc(PolicyPromotionDao.Properties.Policy_promotion_audit_time).build().list();
        if (policyPromotions != null) {
            LibConfig.allVipOncePolicyPromotionList.clear();
            LibConfig.allVipCardPolicyPromotionList.clear();
            LibConfig.allPromotionLimit.clear();

            for (PolicyPromotion policyPromotion : policyPromotions) {
                if(policyPromotion.getPolicy_promotion_total_limit() != null && policyPromotion.getPolicy_promotion_total_limit() > 0){
                    LibConfig.allPromotionLimit.add(policyPromotion.getPolicy_promotion_no());
                }
                List<PolicyPromotionDetail> list = policyPromotionDetailDao.queryBuilder().
                        where(PolicyPromotionDetailDao.Properties.Policy_promotion_no.eq(policyPromotion.getPolicy_promotion_no())).build().list();
                policyPromotion.setPolicy_promotion_details(list);
                if (policyPromotion.getPolicy_promotion_card_once())
                    LibConfig.allVipOncePolicyPromotionList.add(policyPromotion);
                if (policyPromotion.getPolicy_promotion_disable_pay_discount())
                    LibConfig.allVipCardPolicyPromotionList.add(policyPromotion);
            }
            return policyPromotions;
        } else {
            return new ArrayList<>();
        }
    }


    public static boolean isEnablePayDiscount() {

        if (LibConfig.allVipCardPolicyPromotionList != null && LibConfig.allVipCardPolicyPromotionList.size() > 0) {
            for (PolicyPromotion policyPromotion : LibConfig.allVipCardPolicyPromotionList) {
                if (!PricePromotionCalUtils.currentDateContain(policyPromotion)) continue;//具体日期
                if (!PricePromotionCalUtils.currentTimeContain(policyPromotion)) continue;  //具体时间
                if (!PricePromotionCalUtils.isSelectDay(policyPromotion)) continue; //判断星期几包含了没有
                return false;
            }
        }

        return true;
    }

}
