package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.Aggregation;
import com.nhsoft.poslib.service.greendao.AggregationDao;
import com.nhsoft.poslib.utils.MatterUtils;

public class AggregationService {
    private static AggregationService instance;
    public static AggregationService getInstance(){
        if (instance==null){
            instance=new AggregationService();
        }
        return instance;
    }

    public boolean save(final Aggregation aggregation){
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
