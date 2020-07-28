package com.nhsoft.poslib.service;

import android.text.TextUtils;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.PrintOrderUsing;
import com.nhsoft.poslib.service.greendao.PrintOrderUsingDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class PrintOrderUsingService {

    private static PrintOrderUsingService instance;

    public static PrintOrderUsingService getInstance(){

        if (instance==null){
            instance=new PrintOrderUsingService();
        }
        return instance;
    }

    public boolean insertPrintOrderUsing(final PrintOrderUsing printOrderUsing){
        final PrintOrderUsingDao printOrderUsingDao= DaoManager.getInstance().getDaoSession().getPrintOrderUsingDao();
        return MatterUtils.doMatter(printOrderUsingDao, new Runnable() {
            @Override
            public void run() {
                printOrderUsingDao.insertOrReplaceInTx(printOrderUsing);
            }
        });
    }

    public String getPrintOrderUsing(String name){
        String asdfasf="";
        if (TextUtils.isEmpty(name)){
            return asdfasf;
        }
        final PrintOrderUsingDao printOrderUsingDao= DaoManager.getInstance().getDaoSession().getPrintOrderUsingDao();
        List<PrintOrderUsing> list = printOrderUsingDao.queryBuilder().where(PrintOrderUsingDao.Properties.Id.eq(name)).list();
        if (list!=null&list.size()>0){
            asdfasf=list.get(0).getPrint_using_name();
        }
        return asdfasf;
    }

    public int getPrintOrderUs(String name){
        int mVersionCode=0;
        if (TextUtils.isEmpty(name)){
            return mVersionCode;
        }
        final PrintOrderUsingDao printOrderUsingDao= DaoManager.getInstance().getDaoSession().getPrintOrderUsingDao();
        List<PrintOrderUsing> list = printOrderUsingDao.queryBuilder().where(PrintOrderUsingDao.Properties.Print_using_name.eq(name)).list();
        if (list!=null&list.size()>0){
            mVersionCode=list.get(0).getVersion_code();
        }
        return mVersionCode;
    }
    public PrintOrderUsing getPrintOrderUsBean(String name){
        PrintOrderUsing mVersionCode=null;
        if (TextUtils.isEmpty(name)){
            return mVersionCode;
        }
        final PrintOrderUsingDao printOrderUsingDao= DaoManager.getInstance().getDaoSession().getPrintOrderUsingDao();
        List<PrintOrderUsing> list = printOrderUsingDao.queryBuilder().where(PrintOrderUsingDao.Properties.Print_using_name.eq(name)).list();
        if (list!=null&list.size()>0){
            mVersionCode=list.get(0);
        }
        return mVersionCode;
    }

}
