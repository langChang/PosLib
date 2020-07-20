package com.nhsoft.poslib.service.nongmaoService;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.nongmao.StallMatrix;
import com.nhsoft.poslib.service.greendao.StallMatrixDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class StallMatrixService {

    private  static StallMatrixService instance;
    public static StallMatrixService getInstance(){
        if (instance==null){
            instance=new StallMatrixService();
        }
        return instance;
    }


    public boolean insertBeanList(final List<StallMatrix> stallMatrixList) {
        final StallMatrixDao merchantDao = DaoManager.getInstance().getDaoSession().getStallMatrixDao();
        merchantDao.deleteAll();
        if (stallMatrixList == null) return true;

        return MatterUtils.doMatter(merchantDao, new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < stallMatrixList.size(); i++) {
                            merchantDao.insertOrReplaceInTx(stallMatrixList.get(i));
                        }
                    }
                }
        );

    }

    public StallMatrix getPriceByItemNum(long itemNum,int stallNum,int merchatNum){
        final StallMatrixDao merchantDao = DaoManager.getInstance().getDaoSession().getStallMatrixDao();
        List<StallMatrix> list = merchantDao.queryBuilder().where(StallMatrixDao.Properties.Item_num.eq(itemNum), StallMatrixDao.Properties.Stall_num.eq(stallNum), StallMatrixDao.Properties.Merchant_num.eq(merchatNum))
                .build().list();

        if(list == null || list.size() == 0){
            return null;
        }else {
            return list.get(0);
        }
    }

}
