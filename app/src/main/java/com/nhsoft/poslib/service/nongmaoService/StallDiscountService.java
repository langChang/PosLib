package com.nhsoft.poslib.service.nongmaoService;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.nongmao.Stall;
import com.nhsoft.poslib.entity.nongmao.StallDiscount;
import com.nhsoft.poslib.entity.nongmao.StallDiscountDetail;
import com.nhsoft.poslib.service.greendao.StallDiscountDao;
import com.nhsoft.poslib.service.greendao.StallDiscountDetailDao;
import com.nhsoft.poslib.utils.EvtLog;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.ArrayList;
import java.util.List;

public class StallDiscountService {

    private static StallDiscountService instance;

    public static StallDiscountService getInstance() {
        if (instance == null) {
            instance = new StallDiscountService();
        }
        return instance;
    }

    public boolean insertBeanList(final List<StallDiscount> mStallDiscountList ) {

        final StallDiscountDao merchantDao = DaoManager.getInstance().getDaoSession().getStallDiscountDao();
        final StallDiscountDetailDao stallDiscountDetailDao = DaoManager.getInstance().getDaoSession().getStallDiscountDetailDao();
        merchantDao.deleteAll();
        stallDiscountDetailDao.deleteAll();
        if (mStallDiscountList == null) return true;

        return MatterUtils.doMatter(merchantDao, new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < mStallDiscountList.size(); i++) {
                            StallDiscount stallDiscount = mStallDiscountList.get(i);
                            if(stallDiscount.getStalls() != null && stallDiscount.getStalls().size() >0 ){
                                stallDiscount.setStallList(new Gson().toJson(stallDiscount.getStalls()));
                            }
                            merchantDao.insertOrReplaceInTx(stallDiscount);
                            stallDiscountDetailDao.insertOrReplaceInTx(stallDiscount.getStall_discount_details());
                        }
                    }
                }
        );
    }

    /**
     * 获取最后
     * @return
     */
    public StallDiscount getStallDiscount(){
        final StallDiscountDao merchantDao = DaoManager.getInstance().getDaoSession().getStallDiscountDao();
        List<StallDiscount> stallDiscounts = merchantDao.loadAll();
        if (stallDiscounts!=null&&stallDiscounts.size()>0){
            return stallDiscounts.get(0);
        }else {
            return null;
        }

    }

    public static List<StallDiscount> getNewestStallPolciyDiscount() {
        final StallDiscountDao policyDiscountDao= DaoManager.getInstance().getDaoSession().getStallDiscountDao();
        final StallDiscountDetailDao policyDiscountDetailDao = DaoManager.getInstance().getDaoSession().getStallDiscountDetailDao();
        List<StallDiscount> policyDiscounts = policyDiscountDao.queryBuilder().
                orderDesc(StallDiscountDao.Properties.Policy_discount_audit_time).build().list();

        if (policyDiscounts != null) {
            for (StallDiscount policyDiscount : policyDiscounts) {
                if(!TextUtils.isEmpty(policyDiscount.getStallList())){
                    try {
                        policyDiscount.setStalls((List<Stall>) new Gson().fromJson(policyDiscount.getStallList(), new TypeToken<ArrayList<Stall>>() {
                        }.getType()));
                    }catch (Exception e){
                        EvtLog.d("http:=error StallDiscountService--->getNewestStallPolciyDiscount"+e.toString());
                    }
                }
                List<StallDiscountDetail> list = policyDiscountDetailDao.queryBuilder().
                        where(StallDiscountDetailDao.Properties.Policy_discount_no.eq(policyDiscount.getPolicy_discount_no())).build().list();
                policyDiscount.setStall_discount_details(list);
            }
            return policyDiscounts;
        } else {
            return new ArrayList<>();
        }
    }


}
