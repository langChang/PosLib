package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.PosMachine;
import com.nhsoft.poslib.service.greendao.DaoSession;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class RegisterPosMachineService {

    private DaoSession mDaoSession = DaoManager.getInstance().getDaoSession();

    public boolean insert(final PosMachine posMachine) {
        return MatterUtils.doMatter(mDaoSession.getLoginDao(), new Runnable() {
            @Override
            public void run() {
                mDaoSession.getPosMachineDao()
                        .insertOrReplaceInTx(posMachine);
            }
        });
    }

    public boolean delete(PosMachine posMachine) {
        return false;
    }

    public boolean update(PosMachine posMachine) {
        return false;
    }

    public List<PosMachine> queryAll() {
        return  mDaoSession.getPosMachineDao().loadAll();
    }

    public PosMachine queryById(long id) {
        return null;
    }

    public PosMachine queryByName(String name) {
        return null;
    }

    public List<PosMachine> queryByObj(String where, String... params) {
        return mDaoSession.getPosMachineDao()
                .queryRaw(where, params);
    }
}
