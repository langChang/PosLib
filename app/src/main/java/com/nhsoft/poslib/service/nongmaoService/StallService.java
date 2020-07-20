package com.nhsoft.poslib.service.nongmaoService;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.nongmao.Stall;
import com.nhsoft.poslib.service.greendao.StallDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class StallService {

    private static StallService instance;

    public static StallService getInstance() {
        if (instance == null) {
            instance = new StallService();
        }
        return instance;
    }


    public boolean insertBeanList(final List<Stall> stalls) {
        final StallDao merchantDao = DaoManager.getInstance().getDaoSession().getStallDao();
        if (stalls == null) return true;
        merchantDao.deleteAll();
        return MatterUtils.doMatter(merchantDao, new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < stalls.size(); i++) {
                            merchantDao.insertOrReplaceInTx(stalls.get(i));
                        }
                    }
                }
        );

    }

    public List<Stall> getAllList() {
        final StallDao merchantDao = DaoManager.getInstance().getDaoSession().getStallDao();
        return merchantDao.loadAll();
    }

    public String getStallName(int stall_num) {
        String stallName = "";
        List<Stall> stallList = DaoManager.getInstance().getDaoSession().getStallDao().queryBuilder()
                .where(StallDao.Properties.Stall_num.eq(stall_num))
                .list();
        if (stallList.size() != 0) {
            stallName = stallList.get(0).getStall_name();
        }
        return stallName;
    }

    public Stall getStall(int stall_num) {
        List<Stall> stallList = DaoManager.getInstance().getDaoSession().getStallDao().queryBuilder()
                .where(StallDao.Properties.Stall_num.eq(stall_num))
                .list();
        if (stallList.size() > 0) {
            return stallList.get(0);
        }
        return null;
    }

}
