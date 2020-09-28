package com.nhsoft.poslib.call.impl;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.BranchResource;
import com.nhsoft.poslib.model.CardBalaceConfig;
import com.nhsoft.poslib.service.greendao.BranchResourceDao;
import com.nhsoft.poslib.utils.MatterUtils;
import com.nhsoft.poslib.utils.XmlParser;

import java.util.List;

public class BranchResourceImpl {
    private static BranchResourceImpl instance;
    public static BranchResourceImpl getInstance(){
        if (instance==null){
            instance=new BranchResourceImpl();
        }
        return instance;
    }

    /**
     * 保存数据到数据库
     * @param branchResourceList
     * @return
     */
    public boolean saveBranchResourceList(final List<BranchResource> branchResourceList){
        final BranchResourceDao resourceDao=DaoManager.getInstance().getDaoSession().getBranchResourceDao();
        resourceDao.deleteAll();
        return  MatterUtils.doMatter(resourceDao, new Runnable() {
            @Override
            public void run() {
                resourceDao.insertOrReplaceInTx(branchResourceList);
            }
        });
    }

    public String getItemSequenceString(String systemBook,int branchNum){
        final BranchResourceDao resourceDao=DaoManager.getInstance().getDaoSession().getBranchResourceDao();
        List<BranchResource> branchResources = resourceDao.queryRaw("where system_book_code = ? and branch_num=?", systemBook, String.valueOf(branchNum));
        if(branchResources != null && branchResources.size() > 0){
            if(TextUtils.isEmpty(branchResources.get(0).getBranchResourceParam())){
                return "";
            }
            return branchResources.get(0).getBranchResourceParam();
        }else {
            return "";
        }
    }

    public CardBalaceConfig getCardBalaceConfig(int branch_num){
        final BranchResourceDao resourceDao = DaoManager.getInstance().getDaoSession().getBranchResourceDao();
        BranchResource branchResource = resourceDao.queryBuilder().where(BranchResourceDao.Properties.BranchResourceName.eq("分店消费卡参数"),
                BranchResourceDao.Properties.BranchNum.eq(branch_num)).unique();
        if(branchResource != null){
           try {
               String xml2json = XmlParser.xml2json(branchResource.getBranchResourceParam());
               return new Gson().fromJson(xml2json, CardBalaceConfig.class);
           }catch (Exception e){

           }
        }
        return null;
    }
}
