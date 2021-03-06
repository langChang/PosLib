package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.DeskOperatingParameters;
import com.nhsoft.poslib.service.greendao.DeskOperatingParametersDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class DeskOperatingParametersImpl {

    private static DeskOperatingParametersImpl instance;

    public static DeskOperatingParametersImpl getInstance(){
        if (instance==null){
            instance=new DeskOperatingParametersImpl();
        }
        return instance;
    }


    public boolean insertDeskOperatingParameters(final DeskOperatingParameters deskOperatingParameters){
        final DeskOperatingParametersDao deskOperatingParametersDao= DaoManager.getInstance().getDaoSession().getDeskOperatingParametersDao();
        return MatterUtils.doMatter(deskOperatingParametersDao, new Runnable() {
            @Override
            public void run() {
                deskOperatingParametersDao.insertOrReplaceInTx(deskOperatingParameters);
            }
        });
    }

    public DeskOperatingParameters getDeskOperatingParameters(){
        final DeskOperatingParametersDao deskOperatingParametersDao= DaoManager.getInstance().getDaoSession().getDeskOperatingParametersDao();
        List<DeskOperatingParameters> deskOperatingParameters = deskOperatingParametersDao.loadAll();
        if (deskOperatingParameters!=null&&deskOperatingParameters.size()>0){
            return deskOperatingParameters.get(0);
        }else {
            return new DeskOperatingParameters();
        }
    }
}
