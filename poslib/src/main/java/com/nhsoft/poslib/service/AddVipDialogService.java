package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.BranchGroup;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.service.greendao.BranchGroupDao;

import java.util.ArrayList;
import java.util.List;

public class AddVipDialogService {

    private static AddVipDialogService instance;

    public static AddVipDialogService getInstance(){
        if (instance==null){
            instance=new AddVipDialogService();
        }
        return instance;
    }


    /**
     * 是否在同一组 true 是  false 否
     * @param branch_num
     * @param branchGroupList
     * @return
     */
    public boolean isInSameGroup(int branch_num,List<BranchGroup> branchGroupList){
        if(branch_num == LibConfig.activeBranch.getBranch_num()){
            return true;
        }
        final BranchGroupDao mBranchGroupDao = DaoManager.getInstance().getDaoSession().getBranchGroupDao();
        List<BranchGroup> branchGroups = mBranchGroupDao.loadAll();
        List<BranchGroup> branchGroups1 = new ArrayList<>();
        for (int i=0;i<branchGroups.size();i++){
            BranchGroup branchGroup=branchGroups.get(i);
            if ("储值卡共享分组".equals(branchGroup.getBranch_group_type()) && branchGroup.getBranch_num()==branch_num){
                branchGroups1.add(branchGroup);
            }
        }
       loop1: for (int i = 0; i < branchGroups1.size(); i++) {
            for (BranchGroup branchGroup:branchGroupList){
                if ("储值卡共享分组".equals(branchGroup.getBranch_group_type()) && branchGroups1.get(i).getBranch_group_name().equals(branchGroup.getBranch_group_name())){
                    return true;
                }
            }
        }

        return false;

    }
}
