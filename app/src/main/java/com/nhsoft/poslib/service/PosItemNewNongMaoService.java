package com.nhsoft.poslib.service;


import com.google.gson.Gson;
import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.new_nong_mao.PosItemNewNongMao;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.model.NmGoodsGradeBean;
import com.nhsoft.poslib.service.greendao.PosItemNewNongMaoDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PosItemNewNongMaoService {

    private static PosItemNewNongMaoService instance;

    public static PosItemNewNongMaoService getInstance() {

        if (instance == null) {
            instance = new PosItemNewNongMaoService();
        }
        return instance;

    }

    public boolean insertBeanList(final List<PosItemNewNongMao> posItemNewNongMaoList) {
        final PosItemNewNongMaoDao posItemNewNongMaoDao = DaoManager.getInstance().getDaoSession().getPosItemNewNongMaoDao();
        posItemNewNongMaoDao.deleteAll();
        return MatterUtils.doMatter(posItemNewNongMaoDao, new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < posItemNewNongMaoList.size(); i++) {
                            PosItemNewNongMao posItem = posItemNewNongMaoList.get(i);

                            if (posItem.getPos_Images() != null && posItem.getPos_Images().size() > 0) {
                                posItem.setPos_images_json(new Gson().toJson(posItem.getPos_Images()));
                            }
                            posItemNewNongMaoDao.insertOrReplaceInTx(posItem);

                        }
                    }
                });
    }


        public List<NmGoodsGradeBean> getAllGoodsGradeList () {
            List<NmGoodsGradeBean> goodsGradeBeans = new ArrayList<>();
            Map<String, List<PosItemNewNongMao>> stringListMap = new HashMap<>();
             List<PosItemNewNongMao> nmPosItems = DaoManager.getInstance().getDaoSession().getPosItemNewNongMaoDao().queryBuilder().where(PosItemNewNongMaoDao.Properties.Item_del_tag.eq(false)).build().list();
            LibConfig.activityNewDisplayShowGoods.clear();
            LibConfig.activityNewDisplayShowGoods.addAll(nmPosItems);
            if (nmPosItems == null || nmPosItems.size() == 0)
                return new ArrayList<>();

            for (PosItemNewNongMao posItem : nmPosItems) {
                List<PosItemNewNongMao> posItems = stringListMap.get(posItem.getItem_category());
                if (posItems == null) {
                    posItems = new ArrayList<>();
                    posItems.add(posItem);
                    stringListMap.put(posItem.getItem_category(), posItems);
                } else {
                    posItems.add(posItem);
                }
            }
            if (stringListMap.size() > 0) {
                for (Map.Entry<String, List<PosItemNewNongMao>> entry : stringListMap.entrySet()) {
                    NmGoodsGradeBean nmGoodsGradeBean = new NmGoodsGradeBean();
                    nmGoodsGradeBean.setItemCodeName(entry.getKey());
                    nmGoodsGradeBean.setItemCode(entry.getKey());
                    nmGoodsGradeBean.setPosItems(entry.getValue());
                    goodsGradeBeans.add(nmGoodsGradeBean);
                }
            }
            return goodsGradeBeans;
        }

    }
