package com.nhsoft.poslib.service;

import android.text.TextUtils;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.BranchResource;
import com.nhsoft.poslib.service.greendao.BranchResourceDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class BranchResourceService {
    private static BranchResourceService instance;
    public static BranchResourceService getInstance(){
        if (instance==null){
            instance=new BranchResourceService();
        }
        return instance;
    }

    /**
     * 保存数据到数据库
     * @param branchResourceList
     * @return
     */
    public boolean save(final List<BranchResource> branchResourceList){
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
}