package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.CardTypeParam;
import com.nhsoft.poslib.entity.PointRule;
import com.nhsoft.poslib.service.greendao.CardTypeParamDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

/**
 * Created by Iverson on 2020/4/8 1:55 PM
 * 此类用于：
 */
public class CardTypeParamService {
    private static CardTypeParamService instance;

    public static CardTypeParamService getInstance() {
        if (instance == null) {
            instance = new CardTypeParamService();
        }
        return instance;
    }



    public boolean saveCardTypeParamList(final List<CardTypeParam> dataLis) {
        final CardTypeParamDao cardTypeParamDao = DaoManager.getInstance().getDaoSession().getCardTypeParamDao();
        cardTypeParamDao.deleteAll();
        PointRuleService.getInstance().clearPointRule();
        if(dataLis == null){
            return true;
        }
        if (dataLis.size() == 0) return true;
        boolean isSuccess = MatterUtils.doMatter(cardTypeParamDao, new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<dataLis.size();i++){
                    CardTypeParam cardTypeParam = dataLis.get(i);
                    PointRule point_rule = cardTypeParam.getPoint_rule();
                    if(point_rule != null){
                        cardTypeParam.setRule_id(point_rule.getRule_id());
                    }
                    cardTypeParamDao.insertOrReplace(dataLis.get(i));
                    PointRuleService.getInstance().savePointRule(point_rule);
                }
            }
        });
        return isSuccess;
    }

    public CardTypeParam getCardType(String cardTypeCode) {
        final CardTypeParamDao cardTypeParamDao = DaoManager.getInstance().getDaoSession().getCardTypeParamDao();
        List<CardTypeParam> list = cardTypeParamDao.queryBuilder().where(CardTypeParamDao.Properties.Type_code.eq(cardTypeCode)).build().list();
        if(list == null || list.isEmpty()){
            return null;
        }
        CardTypeParam cardTypeParam = list.get(0);
        if(cardTypeParam.getRule_id() != null && cardTypeParam.getRule_id() != 0){
            PointRule pointRule = PointRuleService.getInstance().getPointRule(cardTypeParam.getRule_id());
            cardTypeParam.setPoint_rule(pointRule);
        }
        return cardTypeParam;
    }

}
