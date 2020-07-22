package com.nhsoft.poslib.service.nongmaoService;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.nongmao.CategoryFind;
import com.nhsoft.poslib.service.greendao.CategoryFindDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class CategoryFindService {

    private static CategoryFindService instance;

    public static CategoryFindService getInstance() {
        if (instance == null) {
            instance = new CategoryFindService();
        }
        return instance;
    }

    public boolean insertBeanList(final List<CategoryFind> categoryFindList) {
        final CategoryFindDao categoryFindDao = DaoManager.getInstance().getDaoSession().getCategoryFindDao();
        categoryFindDao.deleteAll();
        if (categoryFindList == null) {
            return true;
        }

        return MatterUtils.doMatter(categoryFindDao, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < categoryFindList.size(); i++) {
                    categoryFindDao.insertOrReplaceInTx(categoryFindList.get(i));
                }
            }
        });

    }

}
