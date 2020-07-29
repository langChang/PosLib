package com.nhsoft.poslib.call.impl;

import android.database.Cursor;
import android.text.TextUtils;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.ManagementTemplate;
import com.nhsoft.poslib.entity.ManagementTemplateDetail;
import com.nhsoft.poslib.service.greendao.DaoSession;
import com.nhsoft.poslib.service.greendao.ManagementTemplateDao;
import com.nhsoft.poslib.service.greendao.ManagementTemplateDetailDao;
import com.nhsoft.poslib.utils.MatterUtils;
import com.nhsoft.poslib.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2018/11/19 6:12 PM
 * 此类用于：
 */
public class ManagementTemplateImpl {

    public static boolean saveManagementTemplate(final ManagementTemplate result) {

        final ManagementTemplateDao managementTemplateDao = DaoManager.getInstance().getDaoSession().getManagementTemplateDao();
        final ManagementTemplateDetailDao managementTemplateDetailDao = DaoManager.getInstance().getDaoSession().getManagementTemplateDetailDao();
        managementTemplateDetailDao.deleteAll();
        managementTemplateDao.deleteAll();
        if (result == null)  return true;
        return MatterUtils.doMatter(managementTemplateDao, new Runnable() {
            @Override
            public void run() {

                    managementTemplateDao.insertOrReplaceInTx(result);
                    if( result.getManagement_template_details() != null){
                        List<ManagementTemplateDetail> management_template_details = result.getManagement_template_details();
                        if (management_template_details != null && management_template_details.size() > 0) {
                            managementTemplateDetailDao.insertOrReplaceInTx(management_template_details);
                        }
                    }
            }
        });
    }



    public static String getMangementTMaxDate() {
        String item_last_edit_time = null;
        try {
            DaoSession session = DaoManager.getInstance().getDaoSession();
            String strSql = "select *  from managemen_template  order by MANAGEMENT_TEMPLATE_LAST_EDIT_TIME desc limit 1";
            Cursor c = session.getDatabase().rawQuery(strSql, null);
            if (c.moveToFirst()) {
                item_last_edit_time = c.getString(c.getColumnIndex("MANAGEMENT_TEMPLATE_LAST_EDIT_TIME"));
            }
            c.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (TextUtils.isEmpty(item_last_edit_time)) {
            item_last_edit_time = TimeUtil.getInstance().stampToDate(1);
        }
        return item_last_edit_time;
    }

    public static List<ManagementTemplateDetail> getTemplateDetails(Long management_template_num){
        if(management_template_num == null || management_template_num.longValue() == 0){
            return new ArrayList<>();
        }else {
            ManagementTemplateDetailDao managementTemplateDetailDao = DaoManager.getInstance().getDaoSession().getManagementTemplateDetailDao();
            return managementTemplateDetailDao.queryBuilder().where(ManagementTemplateDetailDao.Properties.Management_template_num.eq(management_template_num)).build().list();
        }
    }
}
