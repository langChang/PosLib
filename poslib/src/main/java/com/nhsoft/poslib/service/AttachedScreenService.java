package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.AttachedScreen;
import com.nhsoft.poslib.service.greendao.AttachedScreenDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.ArrayList;
import java.util.List;

public class AttachedScreenService {
    private static AttachedScreenService instance;
    public static AttachedScreenService getInstance(){
        if (instance==null){
            instance=new AttachedScreenService();
        }
        return instance;
    }

    public boolean saveAttachedScreen(final List<AttachedScreen>attachedScreenList){
        final AttachedScreenDao attachedScreenDao=DaoManager.getInstance().getDaoSession().getAttachedScreenDao();
        attachedScreenDao.deleteAll();
        if(attachedScreenList == null || attachedScreenList.size() == 0)return true;
        return  MatterUtils.doMatter(attachedScreenDao, new Runnable() {
            @Override
            public void run() {
                for (AttachedScreen attachedScreen:attachedScreenList){
                    attachedScreenDao.insertOrReplaceInTx(attachedScreen);
                }

            }
        });
    }

    //获取附屏url集合
    public List<String> getAttachedScreed(){
        List<String> list =new ArrayList<>();
        final AttachedScreenDao attachedScreenDao=DaoManager.getInstance().getDaoSession().getAttachedScreenDao();
         List<AttachedScreen> attachedScreenList=attachedScreenDao.queryBuilder().list();
         if (attachedScreenList!=null){
             for (AttachedScreen attachedScreen:attachedScreenList){
                 list.add(attachedScreen.getSystem_image_url());
             }

         }
        return list;
    }}
