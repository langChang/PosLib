package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.Branch;
import com.nhsoft.poslib.service.greendao.BranchDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

/**
 * Created by Iverson on 2018/11/19 5:06 PM
 * 此类用于：
 */
public class BranchService{
    private static BranchService instance;
    public static BranchService getInstance(){
        if (instance==null){
            instance=new BranchService();
        }
        return instance;
    }

    public static boolean saveBranch(final List<Branch> dataLis){
        final BranchDao branchItemDao = DaoManager.getInstance().getDaoSession().getBranchDao();
        if(dataLis.size() == 0)return true;
        branchItemDao.deleteAll();
        boolean isSuccess = MatterUtils.doMatter(branchItemDao, new Runnable() {
            @Override
            public void run() {
                branchItemDao.insertOrReplaceInTx(dataLis);
            }
        });
        return isSuccess;
    }

    public static  List<Branch> queryAll(){
        final BranchDao branchItemDao = DaoManager.getInstance().getDaoSession().getBranchDao();
        return branchItemDao.loadAll();
    }

    /**
     *
     * @param systemBookCode
     * @param branchNum
     * @return
     */
    public Branch getBranch(String systemBookCode, int branchNum){
        BranchDao branchDao = DaoManager.getInstance().getDaoSession().getBranchDao();
        Branch branch=branchDao.queryBuilder()
                .where(
                        BranchDao.Properties.System_book_code.eq(systemBookCode)
                        ,BranchDao.Properties.Branch_num.eq(branchNum)
                ).unique();

        return branch;
    }
}
