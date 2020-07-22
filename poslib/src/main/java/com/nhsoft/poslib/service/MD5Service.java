package com.nhsoft.poslib.service;


import androidx.annotation.NonNull;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.TableMd5;
import com.nhsoft.poslib.service.greendao.TableMd5Dao;
import com.nhsoft.poslib.utils.MatterUtils;
import com.nhsoft.poslib.utils.Md5;

import org.greenrobot.greendao.query.Query;

/**
 * Created by Iverson on 2018/11/19 4:24 PM
 * 此类用于：用于比较数据是否更改
 */
public class MD5Service {
    private static MD5Service instance;
    private TableMd5Dao tableMd5Dao;
    private TableMd5 tableMd5;
    private long mKey;
    private String mDataInfo;

    public static MD5Service getInstance(){
        if (instance==null){
            instance=new MD5Service();
        }
        return instance;
    }

    /**
     *
     * @param key
     * @param dataInfo
     * @return
     */
    public boolean isNewData(long key, @NonNull String dataInfo) {
        mKey=key;
        mDataInfo=dataInfo;
        tableMd5Dao = DaoManager.getInstance().getDaoSession().getTableMd5Dao();

        String oldValue;
        Query query = tableMd5Dao.queryBuilder().where(TableMd5Dao.Properties.StrKey.eq(key)).build();
        tableMd5 = (TableMd5) query.unique();//获取MD5实体类
        if (tableMd5 != null) {
            oldValue = tableMd5.getStrValue();
        } else {
            oldValue = "";
        }
        String newValue = Md5.newMd5(dataInfo);
        if (Md5.isQuear(oldValue, newValue)) {
            return true;
        } else {
            return false;
        }
    }

    public  boolean updateMD5(){
        String newValue = Md5.newMd5(mDataInfo);
        tableMd5 = new TableMd5();
        tableMd5.setStrKey(mKey);
        tableMd5.setStrValue(newValue);
        return MatterUtils.doMatter(tableMd5Dao, new Runnable() {
            @Override
            public void run() {
                tableMd5Dao.insertOrReplaceInTx(tableMd5);
            }
        });
//        long updateRow = tableMd5Dao.insertOrReplace(tableMd5);
//        if (updateRow == 1) {
//            return true;
//        } else {
//            return false;
//        }
    }
    public static void deleteMD5(String s){
        TableMd5Dao tableMd5Dao=DaoManager.getInstance().getDaoSession().getTableMd5Dao();
        TableMd5 md5=tableMd5Dao.queryBuilder().where(TableMd5Dao.Properties.StrKey.eq(s)).unique();
        if(md5 != null){
            tableMd5Dao.delete(md5);
        }
    }
}
