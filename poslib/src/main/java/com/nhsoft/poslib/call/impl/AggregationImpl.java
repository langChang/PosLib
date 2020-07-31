package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.Aggregation;
import com.nhsoft.poslib.service.greendao.AggregationDao;
import com.nhsoft.poslib.utils.MatterUtils;

public class AggregationImpl {
    private static AggregationImpl instance;
    public static AggregationImpl getInstance(){
        if (instance==null){
            instance=new AggregationImpl();
        }
        return instance;
    }

    public boolean saveAggregation(final Aggregation aggregation){
        final AggregationDao aggregationDao=DaoManager.getInstance().getDaoSession().getAggregationDao();
        aggregationDao.deleteAll();
        return MatterUtils.doMatter(aggregationDao, new Runnable() {
            @Override
            public void run() {
                aggregationDao.insertOrReplaceInTx(aggregation);
            }
        });
    }

    public Aggregation getBean(){
        final AggregationDao aggregationDao=DaoManager.getInstance().getDaoSession().getAggregationDao();
        return aggregationDao.queryBuilder().unique();
    }
}
