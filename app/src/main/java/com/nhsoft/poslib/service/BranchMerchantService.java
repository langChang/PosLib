package com.nhsoft.poslib.service;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.new_nong_mao.BranchMerchant;
import com.nhsoft.poslib.service.greendao.BranchMerchantDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.ArrayList;
import java.util.List;

public class BranchMerchantService {

    private static BranchMerchantService instance;

    public static BranchMerchantService getInstance() {
        if (instance == null) {
            instance = new BranchMerchantService();
        }

        return instance;
    }

    public boolean insertBean(final List<BranchMerchant> branchMerchant) {
        final BranchMerchantDao branchMerchantDao = DaoManager.getInstance().getDaoSession().getBranchMerchantDao();
        return MatterUtils.doMatter(branchMerchantDao, new Runnable() {
            @Override
            public void run() {
                for (BranchMerchant merchant : branchMerchant) {
                    if (merchant.getBranch_actived()){
                        List<String> image_urls = merchant.getImage_urls();
                        if(image_urls != null && image_urls.size() > 0){
                            merchant.setImge_json(new Gson().toJson(image_urls));
                        }
                        branchMerchantDao.insertOrReplaceInTx(merchant);
                    }
                }

            }
        });
    }

    public BranchMerchant get(String id){
        if (TextUtils.isEmpty(id)){
            return null;
        }
        BranchMerchantDao branchMerchantDao= DaoManager.getInstance().getDaoSession().getBranchMerchantDao();
        List<BranchMerchant> list = branchMerchantDao.queryBuilder().where(BranchMerchantDao.Properties.Branch_id.eq(id)).list();
        if (list!=null&&list.size()>0){
            BranchMerchant branchMerchant = list.get(0);
            if(!TextUtils.isEmpty(branchMerchant.getImge_json())){
                branchMerchant.setImage_urls((List<String>) new Gson().fromJson(branchMerchant.getImge_json(), new TypeToken<ArrayList<String>>() {
                }.getType()));
            }
            return branchMerchant;
        }else {
            return null;
        }
    }

    public List<BranchMerchant> getAllList(){
        BranchMerchantDao branchMerchantDao= DaoManager.getInstance().getDaoSession().getBranchMerchantDao();
        List<BranchMerchant> branchMerchants = new ArrayList<>();
        List<BranchMerchant> loadbranchMerchants = branchMerchantDao.loadAll();
        if(loadbranchMerchants != null && loadbranchMerchants.size() > 0){
            for (BranchMerchant branchMerchant : loadbranchMerchants){
                if(!TextUtils.isEmpty(branchMerchant.getImge_json())){
                    branchMerchant.setImage_urls((List<String>) new Gson().fromJson(branchMerchant.getImge_json(), new TypeToken<ArrayList<String>>() {
                    }.getType()));
                }
                branchMerchants.add(branchMerchant);
            }
        }

        return branchMerchants;
    }

//    public BranchMerchant getBranchMerchant(String branchId){
//        BranchMerchantDao branchMerchantDao= DaoManager.getInstance().getDaoSession().getBranchMerchantDao();
//        return branchMerchantDao.queryBuilder()
//                .where(BranchMerchantDao.Properties.Branch_id.eq(branchId))
//                .unique();
//    }


}
