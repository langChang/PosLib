package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.SystemImageQrcode;
import com.nhsoft.poslib.service.greendao.SystemImageQrcodeDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class SystemImageQrcodeImpl {

    private static SystemImageQrcodeImpl instance;

    public static SystemImageQrcodeImpl getInstance(){
        if (instance==null){
            instance=new SystemImageQrcodeImpl();
        }
        return instance;
    }

    public boolean savaSystemImageQrcode(final List <SystemImageQrcode> systemImageQrcodeList){
        final SystemImageQrcodeDao systemImageQrcodeDao= DaoManager.getInstance().getDaoSession().getSystemImageQrcodeDao();
        return MatterUtils.doMatter(systemImageQrcodeDao, new Runnable() {
            @Override
            public void run() {
                for (SystemImageQrcode systemImageQrcode:systemImageQrcodeList){
                    systemImageQrcodeDao.insertOrReplaceInTx(systemImageQrcode);
                }

            }
        });
    }

    public SystemImageQrcode getSystemImageQrcode(int type){
        SystemImageQrcode systemImageQrcode=null;
        String stringType;
        if (type==1){
            stringType="QR_Code_Bmp";
        }else {
            stringType="QR_Logo";
        }
        final SystemImageQrcodeDao systemImageQrcodeDao= DaoManager.getInstance().getDaoSession().getSystemImageQrcodeDao();
        if (systemImageQrcodeDao.queryBuilder().where(SystemImageQrcodeDao.Properties.System_image_kind.eq(stringType)).list().size()>0){
            systemImageQrcode=systemImageQrcodeDao.queryBuilder().where(SystemImageQrcodeDao.Properties.System_image_kind.eq(stringType)).list().get(0);
        }
        return systemImageQrcode;
    }
}
