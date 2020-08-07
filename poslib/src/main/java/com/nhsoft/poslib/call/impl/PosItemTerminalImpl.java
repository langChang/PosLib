package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.PosItemTerminal;
import com.nhsoft.poslib.service.greendao.PosItemTerminalDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.ArrayList;
import java.util.List;

public class PosItemTerminalImpl {
    private static PosItemTerminalImpl instance;

    public static PosItemTerminalImpl getInstance() {
        if (instance == null) {
            instance = new PosItemTerminalImpl();
        }
        return instance;
    }
    public boolean savePosItemTerminal(final List<PosItemTerminal> dataLis){
        final PosItemTerminalDao posItemTerminalDao = DaoManager.getInstance().getDaoSession().getPosItemTerminalDao();
        posItemTerminalDao.deleteAll();
        if(dataLis.size() == 0)return true;
        boolean isSuccess = MatterUtils.doMatter(posItemTerminalDao, new Runnable() {
            @Override
            public void run() {
                posItemTerminalDao.insertOrReplaceInTx(dataLis);
            }
        });
        return isSuccess;
    }


    public  List<PosItemTerminal> getPosItemTerminal(){
        return DaoManager.getInstance().getDaoSession().getPosItemTerminalDao().queryBuilder().list();
    }

    public void removePosItemTerminal(long itemNum,String category){
        final PosItemTerminalDao posItemTerminalDao = DaoManager.getInstance().getDaoSession().getPosItemTerminalDao();
        PosItemTerminal unique = posItemTerminalDao.queryBuilder().where(PosItemTerminalDao.Properties.Item_num.eq(itemNum),
                PosItemTerminalDao.Properties.Item_category_code.eq(category)).build().unique();
        if(unique != null){
            posItemTerminalDao.delete(unique);
        }
    }

    /**
     * 获取单个类别下的终端设置的商品
     * @param category_code
     * @return
     */
    public  List<PosItemTerminal> getPosItemTerminalByCode(String category_code){
        return DaoManager.getInstance().getDaoSession().getPosItemTerminalDao().queryBuilder()
                .where(PosItemTerminalDao.Properties.Item_category_code.eq(category_code))
                .list();
    }


    /**
     * 获取全部终端设置的postItem
     * @return
     */
    public  List<PosItem> getAllPosItem(){
        List<PosItemTerminal> posItemTerminalList=DaoManager.getInstance().getDaoSession().getPosItemTerminalDao().queryBuilder()
                .list();
        List<PosItem> posItemList=new ArrayList<>();
        for(PosItemTerminal posItemTerminal:posItemTerminalList){
//            PosItem posItem=PosItemService.getInstance().getPosItemByKey(posItemTerminal.getItem_num().longValue());
//            if (posItem!=null){
//                posItemList.add(posItem);
//            }
            if (posItemTerminal.getPosItem()==null){
                posItemList.add(PosItemImpl.getInstance().getPosItemByItemNum(posItemTerminal.getItem_num().longValue()));
            }else {
                posItemList.add(posItemTerminal.getPosItem());
            }
        }
        DaoManager.getInstance().getDaoSession().getPosItemTerminalDao().detachAll();
        return posItemList;
    }
}
