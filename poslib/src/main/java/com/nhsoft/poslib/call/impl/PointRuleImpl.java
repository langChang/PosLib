package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.PointRule;
import com.nhsoft.poslib.service.greendao.PointRuleDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

/**
 * Created by Iverson on 2020/4/8 1:55 PM
 * 此类用于：
 */
public class PointRuleImpl {
    private static PointRuleImpl instance;

    public static PointRuleImpl getInstance() {
        if (instance == null) {
            instance = new PointRuleImpl();
        }
        return instance;
    }


    public void clearPointRule(){
        final PointRuleDao pointRuleDao = DaoManager.getInstance().getDaoSession().getPointRuleDao();
        pointRuleDao.deleteAll();
    }

    public boolean savePointRule(final PointRule pointRule) {
        final PointRuleDao pointRuleDao = DaoManager.getInstance().getDaoSession().getPointRuleDao();
        if (pointRule == null) {
            return true;
        }
        boolean isSuccess = MatterUtils.doMatter(pointRuleDao, new Runnable() {
            @Override
            public void run() {
                pointRuleDao.insertOrReplace(pointRule);
            }
        });
        return isSuccess;
    }

    public PointRule getPointRule(Long rule_id){
        final PointRuleDao pointRuleDao = DaoManager.getInstance().getDaoSession().getPointRuleDao();
        List<PointRule> ruleList = pointRuleDao.queryBuilder().where(PointRuleDao.Properties.Rule_id.eq(rule_id)).build().list();
        if(ruleList == null || ruleList.isEmpty()){
            return  null;
        }
        return ruleList.get(0);
    }

}
