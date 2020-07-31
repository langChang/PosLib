package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.ClientPoint;
import com.nhsoft.poslib.service.greendao.ClientPointDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

/**
 * Created by Iverson on 2019/1/16 4:27 PM
 * 此类用于：
 */
public class ClientPointImpl {

    private static ClientPointImpl instance;

    public static ClientPointImpl getInstance() {
        if (instance == null) {
            instance = new ClientPointImpl();
        }
        return instance;
    }

    public static boolean saveClient(final ClientPoint clientPoint) {
        final ClientPointDao clientPointDao = DaoManager.getInstance().getDaoSession().getClientPointDao();
        if (clientPoint == null) return true;
        boolean isSuccess = MatterUtils.doMatter(clientPointDao, new Runnable() {
            @Override
            public void run() {
                clientPointDao.insertOrReplaceInTx(clientPoint);
            }
        });
        return isSuccess;
    }


    /**
     *
     * @param clientPoint
     * @return
     */
    public static boolean updateClientPoint(final ClientPoint clientPoint) {
        final ClientPointDao clientPointDao = DaoManager.getInstance().getDaoSession().getClientPointDao();
        if (clientPoint == null) return true;
        boolean isSuccess = MatterUtils.doMatter(clientPointDao, new Runnable() {
            @Override
            public void run() {
                clientPointDao.insertOrReplaceInTx(clientPoint);
            }
        });
        return isSuccess;
    }


    /**
     *
     * @param date
     */
    public static void deleteClient(final String date) {
        final ClientPointDao clientPointDao = DaoManager.getInstance().getDaoSession().getClientPointDao();
        List<ClientPoint> list = clientPointDao.queryBuilder().where(ClientPointDao.Properties.Client_point_last_edit_time.le(date)).build().list();
        for (ClientPoint clientPoint : list) {
            clientPointDao.delete(clientPoint);
        }
    }


    public void updateClientPointStatus(final ClientPoint clientPoint) {
        final ClientPointDao clientPointDao = DaoManager.getInstance().getDaoSession().getClientPointDao();
        if (clientPoint == null) return;
        MatterUtils.doMatter(clientPointDao, new Runnable() {
            @Override
            public void run() {
                clientPointDao.insertOrReplaceInTx(clientPoint);
            }
        });
    }

    public List<ClientPoint> getClientPointList(String systemBookCode, int branchNum, boolean clientPointSync) {
        final ClientPointDao clientPointDao = DaoManager.getInstance().getDaoSession().getClientPointDao();
        return clientPointDao.queryBuilder().where(
                ClientPointDao.Properties.System_book_code.eq(systemBookCode),
//                ClientPointDao.Properties.Shift_table_num.eq(shiftTableNum),
                ClientPointDao.Properties.Client_point_sync.eq(clientPointSync),
                ClientPointDao.Properties.Branch_num.eq(branchNum)
        ).list();
    }
}
