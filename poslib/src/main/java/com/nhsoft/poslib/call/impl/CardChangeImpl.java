package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.CardChange;
import com.nhsoft.poslib.service.greendao.CardChangeDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2018/11/15 11:29 AM
 */
public class CardChangeImpl {

    private static CardChangeImpl instance;
    public static CardChangeImpl getInstance(){
        if (instance==null){
            instance=new CardChangeImpl();
        }
        return instance;
    }

    public void updateCardChangeStatus(final List<CardChange> cardChanges){
        CardChangeDao cardChangeDao =  DaoManager.getInstance().getDaoSession().getCardChangeDao();
        for (CardChange cardChange : cardChanges){
            CardChange loadCardChange = cardChangeDao.load(cardChange.getCard_change_fid());
            if(loadCardChange != null){
                loadCardChange.setCard_change_synch_flag(true);
                cardChangeDao.insertOrReplace(loadCardChange);
            }
        }
    }

    /**
     * 保存零钱包数据
     * @param cardChanges
     */
    public void saveCardChangeData(List<CardChange> cardChanges){
        CardChangeDao cardChangeDao =  DaoManager.getInstance().getDaoSession().getCardChangeDao();
        cardChangeDao.insertOrReplaceInTx(cardChanges);
    }

    /**
     * 获取本班次零钱包未上传的数据
     * @param shiftTabeNum
     * @return
     */
    public  List<CardChange>  getAllNoUploadData(int shiftTabeNum){
        CardChangeDao cardChangeDao =  DaoManager.getInstance().getDaoSession().getCardChangeDao();
        List<CardChange> cardChanges = cardChangeDao.queryBuilder().where(CardChangeDao.Properties.Shift_table_num.eq(shiftTabeNum),CardChangeDao.Properties.Card_change_synch_flag.eq(false)).build().list();
        if(cardChanges != null){
            return cardChanges;
        }else{
            return new ArrayList<>();
        }
    }
}
