package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.MeasureUnit;
import com.nhsoft.poslib.entity.MeasureUnitItem;
import com.nhsoft.poslib.service.greendao.MeasureUnitDao;
import com.nhsoft.poslib.service.greendao.MeasureUnitItemDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class MeasureUnitService {
    private static MeasureUnitService instance;

    public static MeasureUnitService getInstance() {
        if (instance == null) {
            instance = new MeasureUnitService();
        }
        return instance;
    }

    public boolean saveMeasureUnit(final List<MeasureUnit> list) {
        final MeasureUnitDao measureUnitDao = DaoManager.getInstance().getDaoSession().getMeasureUnitDao();
        final MeasureUnitItemDao measureUnitItemDao = DaoManager.getInstance().getDaoSession().getMeasureUnitItemDao();

        return MatterUtils.doMatter(measureUnitDao, new Runnable() {
            @Override
            public void run() {
                measureUnitDao.deleteAll();
                measureUnitItemDao.deleteAll();
                measureUnitDao.insertOrReplaceInTx(list);
                for (int i = 0; i < list.size(); i++) {
                    MeasureUnit measureUnit = list.get(i);
                    List<MeasureUnitItem> mMeasureUnitItems = measureUnit.getItem_units();
                    if (mMeasureUnitItems != null && mMeasureUnitItems.size() > 0) {
                        for (MeasureUnitItem item : mMeasureUnitItems) {
                            item.setGroup_name(measureUnit.getGroup_name());
                            measureUnitItemDao.insertOrReplaceInTx(item);
                        }

                    }
                }
            }
        });
//        return false;
    }


    /**
     * 根据 MeasureUnit 的 groupName
     * 获取相应的 List<MeasureUnitItem>
     *
     * @param groupName
     * @return
     */
    public List<MeasureUnitItem> getMeasureUnitList(String groupName) {
        final MeasureUnitItemDao measureUnitItemDao = DaoManager.getInstance().getDaoSession().getMeasureUnitItemDao();
        List<MeasureUnitItem> measureUnitItems = measureUnitItemDao.queryBuilder()
                .where(MeasureUnitItemDao.Properties.Group_name.eq(groupName)).list();
        return measureUnitItems;
    }


}
