package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.BranchGroup;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.service.greendao.BranchGroupDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

/**
 * Created by Iverson on 2018/11/19 5:34 PM
 * 此类用于：
 */
public class BranchGroupImpl {

    public static boolean saveBranchGroup(final List<BranchGroup> dataLis){
        final BranchGroupDao mBranchGroupDao = DaoManager.getInstance().getDaoSession().getBranchGroupDao();
        mBranchGroupDao.deleteAll();
        if(dataLis.size() == 0)return true;
        boolean isSuccess = MatterUtils.doMatter(mBranchGroupDao, new Runnable() {
            @Override
            public void run() {
                mBranchGroupDao.insertOrReplaceInTx(dataLis);
            }
        });
        return isSuccess;
    }
    public static void initMyBranchGroup(int branch_num){
        final BranchGroupDao mBranchGroupDao = DaoManager.getInstance().getDaoSession().getBranchGroupDao();
        List<BranchGroup> branchGroups = mBranchGroupDao.loadAll();
        String Branch_group_name="";
        LibConfig.branchGroupList.clear();
        for (int i=0;i<branchGroups.size();i++){
            BranchGroup branchGroup=branchGroups.get(i);
            if (branchGroup.getBranch_num()==branch_num){
//                Branch_group_name=branchGroup.getBranch_group_type();
                LibConfig.branchGroupList.add(branchGroup);
            }
        }
//        List<BranchGroup> list = mBranchGroupDao.queryBuilder().where(BranchGroupDao.Properties.Branch_group_type.eq(Branch_group_name)).list();

    }
}
