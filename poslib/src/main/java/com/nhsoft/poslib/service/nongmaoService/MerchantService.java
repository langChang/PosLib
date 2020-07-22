package com.nhsoft.poslib.service.nongmaoService;

import android.text.TextUtils;


import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.nongmao.Merchant;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.service.greendao.MerchantDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class MerchantService {

    private static MerchantService instance;

    public static MerchantService getInstance() {
        if (instance == null) {
            instance = new MerchantService();
        }
        return instance;
    }

    public boolean insertBeanList(final List<Merchant> merchantList) {
        final MerchantDao merchantDao = DaoManager.getInstance().getDaoSession().getMerchantDao();
        if (merchantList == null) return true;
        merchantDao.deleteAll();
        return MatterUtils.doMatter(merchantDao, new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < merchantList.size(); i++) {
                            merchantDao.insertOrReplaceInTx(merchantList.get(i));
                        }
                    }
                }
        );

    }

    public Merchant getBeanById(int merchant_num) {
        final MerchantDao merchantDao = DaoManager.getInstance().getDaoSession().getMerchantDao();
        List<Merchant> build = merchantDao.queryBuilder().where(MerchantDao.Properties.Merchant_num.eq(merchant_num)).limit(1).offset(0).list();
        if (build == null || build.size() == 0) {
            return null;
        } else {
            return build.get(0);
        }
    }

    public String getMerchantNameString() {
        List<Merchant> merchantList = DaoManager.getInstance().getDaoSession().getMerchantDao().queryBuilder()
                .where(MerchantDao.Properties.Merchant_num.eq(LibConfig.activeAppUser.getMerchant_num()))
                .list();
        if(merchantList.size() == 0)return "";
        return merchantList.get(0).getMerchant_name();
    }



    public Merchant getMerchant() {
        List<Merchant> merchantList = DaoManager.getInstance().getDaoSession().getMerchantDao().queryBuilder()
                .where(MerchantDao.Properties.Merchant_num.eq(LibConfig.activeAppUser.getMerchant_num()))
                .list();
        if (merchantList.size()>0){
            return merchantList.get(0);
        }
        return null;
    }

    public String getNameByNum(String merchant_num) {

        if (TextUtils.isEmpty(merchant_num)){
            return "";
        }
        List<Merchant> merchantList = DaoManager.getInstance().getDaoSession().getMerchantDao().queryBuilder()
                .where(MerchantDao.Properties.Merchant_num.eq(merchant_num)).list();
        if (merchantList.size() > 0) {
            if (TextUtils.isEmpty(merchantList.get(0).getMerchant_name())) {
                return "";
            } else {
                return merchantList.get(0).getMerchant_name();
            }

        } else {
            return "";
        }

    }

}
