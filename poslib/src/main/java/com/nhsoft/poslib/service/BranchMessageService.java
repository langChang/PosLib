package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.BranchMessage;
import com.nhsoft.poslib.entity.BranchsBean;
import com.nhsoft.poslib.service.greendao.DaoSession;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class BranchMessageService {
    private DaoSession mDaoSession = DaoManager.getInstance().getDaoSession();
    private static BranchMessageService instance;
    public static BranchMessageService getInstance(){
        if (instance==null){
            instance=new BranchMessageService();
        }
        return instance;
    }

    /**
     * 保存
     *
     * @param branchMessage
     * @param branchsBeanList
     * @return
     */
    public boolean insert(BranchMessage branchMessage, List<BranchsBean> branchsBeanList) {
        boolean isSuccess=insertBranchMessage(branchMessage);
        if (isSuccess){
            for (BranchsBean branchsBean:branchsBeanList){
                branchsBean.setBranchMessageId(branchMessage.getId());
            }
        }

        return insertBranchBeanList(branchsBeanList);
    }

    /**
     * 插入辅屏滚动信息
     * @param branchMessage
     * @return
     */
    public boolean insertBranchMessage(final BranchMessage branchMessage) {
        return MatterUtils.doMatter(mDaoSession.getBranchMessageDao(), new Runnable() {
            @Override
            public void run() {
                mDaoSession.getBranchMessageDao().insertOrReplaceInTx(branchMessage);
            }
        });
    }

    /**
     * 插入辅屏滚动信息对应的门店
     * @param branchsBeanList
     * @return
     */
    public boolean insertBranchBeanList(final List<BranchsBean> branchsBeanList) {

        return MatterUtils.doMatter(mDaoSession.getBranchsBeanDao(), new Runnable() {
            @Override
            public void run() {
                mDaoSession.getBranchsBeanDao().insertOrReplaceInTx(branchsBeanList);
            }
        });
    }

    public boolean deleteAllBranchMessage(){
        return MatterUtils.doMatter(mDaoSession.getBranchMessageDao(), new Runnable() {
            @Override
            public void run() {
                mDaoSession.getBranchMessageDao().deleteAll();
            }
        });
    }

    public boolean deleteAllBranchBean(){
        return MatterUtils.doMatter(mDaoSession.getBranchsBeanDao(), new Runnable() {
            @Override
            public void run() {
                mDaoSession.getBranchsBeanDao().deleteAll();
            }
        });
    }

    /**
     * 获取改门店的滚动字幕
     * @param branchNum
     * @return
     */
    public String getBranchMessageString(int branchNum){
        List<BranchMessage> branchMessageList=mDaoSession.getBranchMessageDao().loadAll();
        for (BranchMessage branchMessage:branchMessageList){
            branchMessage.getBranchs();
        }
        for (BranchMessage branchMessage:branchMessageList){
            for (BranchsBean branchsBean:branchMessage.getBranchs()){
                if (branchsBean.getBranch_num()==branchNum){
                    return branchMessage.getPos_screen_context();
                }
                if (branchsBean.getBranch_num()==0){
                    return branchMessage.getPos_screen_context();
                }
            }
        }
        return "浙江乐檬信息技术有限公司&4000808050";//默认
    }
}
