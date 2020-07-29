package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.BookResource;
import com.nhsoft.poslib.entity.PointOrder;
import com.nhsoft.poslib.entity.PointOrderDetial;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.service.greendao.PointOrderDao;
import com.nhsoft.poslib.service.greendao.PointOrderDetialDao;
import com.nhsoft.poslib.utils.MatterUtils;
import com.nhsoft.poslib.utils.PointGoodsParseUtil;

import java.util.ArrayList;
import java.util.List;

public class PointOrderImpl {

    private static PointOrderImpl instance;

    public static PointOrderImpl getInstance(){
        if (instance==null){
            instance=new PointOrderImpl();
        }
        return instance;
    }

    public boolean insertPointOrder(final List<PointOrder> pointOrderList){
        final PointOrderDao pointOrderDao= DaoManager.getInstance().getDaoSession().getPointOrderDao();
        final PointOrderDetialDao pointOrderDetialDao=DaoManager.getInstance().getDaoSession().getPointOrderDetialDao();
        pointOrderDao.deleteAll();
        pointOrderDetialDao.deleteAll();
        if (pointOrderList==null)return true;
        return MatterUtils.doMatter(pointOrderDao, new Runnable() {
            @Override
            public void run() {
                for (PointOrder pointOrder:pointOrderList){
                    pointOrderDao.insertOrReplace(pointOrder);
                    if (pointOrder.getPoint_order_details()!=null&&pointOrder.getPoint_order_details().size()>0){
                        for (PointOrderDetial pointOrderDetial:pointOrder.getPoint_order_details()){
                            pointOrderDetialDao.insertOrReplace(pointOrderDetial);
                        }
                    }
                }


            }
        });
    }

    public List<PointOrderDetial> getPointOrderDetails(){
        final PointOrderDao pointOrderDao= DaoManager.getInstance().getDaoSession().getPointOrderDao();
        final PointOrderDetialDao pointOrderDetialDao=DaoManager.getInstance().getDaoSession().getPointOrderDetialDao();
        List<PointOrderDetial>pointOrderDetialList=new ArrayList<>();
        List<PointOrder>pointOrderList;
        pointOrderList=pointOrderDao.queryBuilder().list();
        for (PointOrder pointOrder:pointOrderList){
            List<PointOrderDetial> list = pointOrderDetialDao.queryBuilder().where(PointOrderDetialDao.Properties.Point_order_fid.eq(pointOrder.getPoint_order_fid())).list();
            if(list != null && list.size() > 0){
                pointOrderDetialList.addAll(list);
            }
        }
        pointOrderDao.detachAll();
        pointOrderDetialDao.detachAll();
        return pointOrderDetialList;
    }


    /**
     * 获取积分兑换物
     * @return
     */
    public List<PointOrderDetial> getPointDetails(){
        BookResource bookPosSale = BookResourceImpl.getInstance().getBookPosSale(LibConfig.activeShiftTable.getSystemBookCode(), LibConfig.S_LOCAL_POINT_GOODS);
        List<PointOrderDetial> pointList = PointGoodsParseUtil.getPointList(bookPosSale);
        if(pointList == null)pointList = new ArrayList<>();
        return pointList;
    }
}
