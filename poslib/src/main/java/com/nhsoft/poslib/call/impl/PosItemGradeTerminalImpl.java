package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.PosItemGrade;
import com.nhsoft.poslib.entity.PosItemGradeTerminal;
import com.nhsoft.poslib.service.greendao.PosItemGradeTerminalDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.ArrayList;
import java.util.List;

public class PosItemGradeTerminalImpl {
    private static PosItemGradeTerminalImpl instance;

    public static PosItemGradeTerminalImpl getInstance() {
        if (instance == null) {
            instance = new PosItemGradeTerminalImpl();
        }
        return instance;
    }
    public boolean savePosItemGradeTerminal(final List<PosItemGradeTerminal> dataLis){
        final PosItemGradeTerminalDao posItemGradeTerminalDao = DaoManager.getInstance().getDaoSession().getPosItemGradeTerminalDao();
        posItemGradeTerminalDao.deleteAll();
        if(dataLis.size() == 0)return true;
        boolean isSuccess = MatterUtils.doMatter(posItemGradeTerminalDao, new Runnable() {
            @Override
            public void run() {
                posItemGradeTerminalDao.insertOrReplaceInTx(dataLis);
            }
        });
        return isSuccess;
    }


    public  List<PosItemGradeTerminal> getPosItemGradeTerminal(){
        return DaoManager.getInstance().getDaoSession().getPosItemGradeTerminalDao().queryBuilder().list();
    }

    /**
     * 获取单个分级商品下的终端设置的商品
     * @param item_num
     * @return
     */
    public  List<PosItemGradeTerminal> getPosItemTerminalByCode(Long item_num){
        return DaoManager.getInstance().getDaoSession().getPosItemGradeTerminalDao().queryBuilder()
                .where(PosItemGradeTerminalDao.Properties.Item_num.eq(item_num))
                .list();
    }


    /**
     * 获取全部终端设置的PosItemGrade
     * @return
     */
    public  List<PosItemGrade> getAllPosItemGrade(){
        List<PosItemGradeTerminal> posItemTerminalList=DaoManager.getInstance().getDaoSession().getPosItemGradeTerminalDao().queryBuilder()
                .list();
        List<PosItemGrade> posItemGradeList=new ArrayList<>();
        for(PosItemGradeTerminal posItemGradeTerminal:posItemTerminalList){
            PosItemGrade posItemGrade= PosItemImpl.getInstance().getPosItemGradeByItemGradeNum(posItemGradeTerminal.getItem_grade_num(),posItemGradeTerminal.getItem_num());
            if (posItemGrade!=null){
                if (!posItemGrade.getItem_grade_sale_cease_flag())
                    posItemGradeList.add(posItemGrade);
            }
//            if (posItemGradeTerminal.getPosItemGrade()==null){
//                posItemGradeList.add(PosItemService.getInstance().getPosItemGradeByKey(posItemGradeTerminal.getItem_grade_num()));
//            }else {
//                posItemGradeList.add(posItemGradeTerminal.getPosItemGrade());
//            }
        }
        DaoManager.getInstance().getDaoSession().getPosItemGradeTerminalDao().detachAll();
        return posItemGradeList;
    }
}
