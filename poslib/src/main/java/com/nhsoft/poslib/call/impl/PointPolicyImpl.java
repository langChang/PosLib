package com.nhsoft.poslib.call.impl;


import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.PointPolicy;
import com.nhsoft.poslib.entity.PointPolicyDetail;
import com.nhsoft.poslib.service.greendao.PointPolicyDao;
import com.nhsoft.poslib.service.greendao.PointPolicyDetailDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2018/11/15 11:29 AM
 */
public class PointPolicyImpl {

    public static boolean savePointPolicy(final List<PointPolicy> result) {
        final PointPolicyDao pointPolicyDao = DaoManager.getInstance().getDaoSession().getPointPolicyDao();
        final PointPolicyDetailDao pointPolicyDetailDao = DaoManager.getInstance().getDaoSession().getPointPolicyDetailDao();

        pointPolicyDetailDao.deleteAll();
        pointPolicyDao.deleteAll();

        if (result.size() == 0) return true;

        return MatterUtils.doMatter(pointPolicyDao, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < result.size(); i++) {
                    PointPolicy pointPolicy = result.get(i);
                    pointPolicyDao.insertOrReplaceInTx(pointPolicy);
                    List<PointPolicyDetail> point_policy_details = pointPolicy.getPoint_policy_details();
                    if (point_policy_details != null && point_policy_details.size() > 0) {
                        pointPolicyDetailDao.insertOrReplaceInTx(point_policy_details);
                    }
                }
            }
        });
    }


    public static List<PointPolicy> loadAllPointPolicy(String systemBookCode) {
        List<PointPolicy> pointPolicies = new ArrayList<>();
        final PointPolicyDao pointPolicyDao = DaoManager.getInstance().getDaoSession().getPointPolicyDao();
        final PointPolicyDetailDao pointPolicyDetailDao = DaoManager.getInstance().getDaoSession().getPointPolicyDetailDao();
        List<PointPolicy> laodList = pointPolicyDao.queryBuilder().where(PointPolicyDao.Properties.System_book_code.eq(systemBookCode))
                .orderDesc(PointPolicyDao.Properties.Point_policy_date_from).build().list();

        if (laodList != null && laodList.size() > 0) {
            pointPolicies.addAll(laodList);
            for (PointPolicy pointPolicy : pointPolicies) {
                List<PointPolicyDetail> loadPointPolicyDetails = pointPolicyDetailDao.queryBuilder().where(PointPolicyDetailDao.Properties.Point_policy_id.eq(pointPolicy.getPoint_policy_id())).build().list();
                pointPolicy.setPoint_policy_details(loadPointPolicyDetails);
            }
        }
        pointPolicyDao.detachAll();
        pointPolicyDetailDao.detachAll();
        return pointPolicies;
    }

    public static boolean isInPointPolicy(String point_policy_id,long item_num){
        final PointPolicyDetailDao pointPolicyDetailDao = DaoManager.getInstance().getDaoSession().getPointPolicyDetailDao();
        List<PointPolicyDetail> list = pointPolicyDetailDao.queryBuilder().where(PointPolicyDetailDao.Properties.Point_policy_id.eq(point_policy_id),
                PointPolicyDetailDao.Properties.Item_num.eq(item_num)).build().list();
        if(list != null && list.size() > 0){
            return true;
        }else {
            return false;
        }
    }
}
