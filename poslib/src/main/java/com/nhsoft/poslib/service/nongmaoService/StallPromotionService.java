package com.nhsoft.poslib.service.nongmaoService;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.nongmao.Stall;
import com.nhsoft.poslib.entity.nongmao.StallPromotion;
import com.nhsoft.poslib.entity.nongmao.StallPromotionDetail;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.service.greendao.StallPromotionDao;
import com.nhsoft.poslib.service.greendao.StallPromotionDetailDao;
import com.nhsoft.poslib.utils.EvtLog;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.ArrayList;
import java.util.List;

public class StallPromotionService {

    private static StallPromotionService instance;

    public static StallPromotionService getInstance() {
        if (instance == null) {
            instance = new StallPromotionService();
        }
        return instance;
    }


    public boolean insertBeanList(final List<StallPromotion> stallPromotionList ) {
        final StallPromotionDao merchantDao = DaoManager.getInstance().getDaoSession().getStallPromotionDao();
        final StallPromotionDetailDao stallDiscountDetailDao = DaoManager.getInstance().getDaoSession().getStallPromotionDetailDao();

        merchantDao.deleteAll();
        stallDiscountDetailDao.deleteAll();
        if (stallPromotionList == null) return true;

        return MatterUtils.doMatter(merchantDao, new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < stallPromotionList.size(); i++) {
                            StallPromotion stallPromotion = stallPromotionList.get(i);
                            if(stallPromotion.getStalls() != null && stallPromotion.getStalls().size() >0 ){
                                stallPromotion.setStallList(new Gson().toJson(stallPromotion.getStalls()));
                            }
                            merchantDao.insertOrReplaceInTx(stallPromotion);
                            stallDiscountDetailDao.insertOrReplaceInTx(stallPromotion.getDetails());
                        }
                    }
                }
        );
    }

    public  List<StallPromotion> getNewestStallPolciyPromotion() {
        StallPromotionDao policyPromotionDao = DaoManager.getInstance().getDaoSession().getStallPromotionDao();
        StallPromotionDetailDao policyPromotionDetailDao = DaoManager.getInstance().getDaoSession().getStallPromotionDetailDao();
        List<StallPromotion> policyPromotions = policyPromotionDao.queryBuilder().
                orderDesc(StallPromotionDao.Properties.Policy_promotion_audit_time).build().list();

        if (policyPromotions != null) {
            for (StallPromotion policyPromotion : policyPromotions) {
                List<StallPromotionDetail> list = policyPromotionDetailDao.queryBuilder().
                        where(StallPromotionDetailDao.Properties.Policy_promotion_no.eq(policyPromotion.getPolicy_promotion_no())).build().list();

                if(!TextUtils.isEmpty(policyPromotion.getStallList())){
                    try {
                        policyPromotion.setStalls((List<Stall>) new Gson().fromJson(policyPromotion.getStallList(), new TypeToken<ArrayList<Stall>>() {
                        }.getType()));
                    }catch (Exception e){
                        EvtLog.d("http:=error StallPromotionService--->getNewestStallPolciyPromotion"+e.toString());
                    }
                }
                policyPromotion.setStallPromotionDetails(list);

                if(policyPromotion.getPolicy_promotion_card_once()) LibConfig.allVipOnceStallPolicyPromotionList.add(policyPromotion);

            }
            return policyPromotions;
        } else {
            return new ArrayList<>();
        }
    }

}
