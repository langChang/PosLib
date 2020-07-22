package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.VipSendCard;
import com.nhsoft.poslib.service.greendao.VipSendCardDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class VipSendCardSuccessService {

    private static VipSendCardSuccessService instance;
    public static VipSendCardSuccessService getInstance(){
        if (instance==null){
            instance=new VipSendCardSuccessService();
        }
        return instance;
    }

    public boolean insertVipSendCard(final VipSendCard vipSendCard){
        final VipSendCardDao vipSendCardDao=DaoManager.getInstance().getDaoSession().getVipSendCardDao();
        return MatterUtils.doMatter(vipSendCardDao, new Runnable() {
            @Override
            public void run() {
                vipSendCardDao.insertOrReplace(vipSendCard);
            }
        });
    }

    /**
     * 删除 date天后的历史数据表
     * @param date
     */
    public void deleteOverDateData(final String date){
        final VipSendCardDao vipSendCardDao=DaoManager.getInstance().getDaoSession().getVipSendCardDao();
        List<VipSendCard> vipSendCardList=vipSendCardDao.queryBuilder().where(VipSendCardDao.Properties.ShiftTableBizDay.le(date)).list();
        for (final VipSendCard vipSendCard:vipSendCardList){
            vipSendCardDao.delete(vipSendCard);
        }

    }
}
