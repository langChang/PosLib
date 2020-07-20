package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.VipCRMLevel;
import com.nhsoft.poslib.entity.VipCRMLevelDetail;
import com.nhsoft.poslib.entity.VipCrmAmaLevel;
import com.nhsoft.poslib.service.greendao.VipCRMLevelDao;
import com.nhsoft.poslib.service.greendao.VipCRMLevelDetailDao;
import com.nhsoft.poslib.service.greendao.VipCrmAmaLevelDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.ArrayList;
import java.util.List;

public class VipCrmLevelService {

    private static VipCrmLevelService instance;

    public static VipCrmLevelService getInstance() {
        if (instance == null) {
            instance = new VipCrmLevelService();
        }
        return instance;
    }

    public boolean insertBean(final List<VipCRMLevel> vipCrmLevelList) {

        final VipCRMLevelDao vipCRMLevelDao = DaoManager.getInstance().getDaoSession().getVipCRMLevelDao();
        final VipCRMLevelDetailDao vipCRMLevelDetailDao = DaoManager.getInstance().getDaoSession().getVipCRMLevelDetailDao();
        vipCRMLevelDao.deleteAll();
        vipCRMLevelDetailDao.deleteAll();
        if (vipCrmLevelList == null || vipCrmLevelList.size() == 0) {
            return true;
        }
        return MatterUtils.doMatter(vipCRMLevelDao, new Runnable() {
            @Override
            public void run() {
                for (VipCRMLevel vipCRMLevel : vipCrmLevelList) {
                    vipCRMLevelDao.insertOrReplaceInTx(vipCRMLevel);
                    List<VipCRMLevelDetail> categoriesSelf = vipCRMLevel.getCategoriesSelf();
                    if (categoriesSelf != null && categoriesSelf.size() > 0) {
                        for (VipCRMLevelDetail vipCRMLevelDetail : categoriesSelf) {
                            vipCRMLevelDetail.setVipCRMLevelId(vipCRMLevel.getIdAuto());
                            vipCRMLevelDetailDao.insertOrReplaceInTx(vipCRMLevelDetail);
                        }

                    }

                }
            }
        });
    }

    /**
     * 获取全部 会员等级
     *
     * @return
     */
    public List<VipCRMLevel> getVipCrmLevelList() {
        final VipCRMLevelDao vipCRMLevelDao = DaoManager.getInstance().getDaoSession().getVipCRMLevelDao();
        final VipCRMLevelDetailDao vipCRMLevelDetailDao = DaoManager.getInstance().getDaoSession().getVipCRMLevelDetailDao();
        List<VipCRMLevel> lists = new ArrayList<>();
        List<VipCRMLevel> list = vipCRMLevelDao.queryBuilder().list();
        if (list != null && list.size() > 0) {
            for (VipCRMLevel vipCRMLevel : list) {
                List<VipCRMLevelDetail> vipCRMLevelDetails = vipCRMLevelDetailDao.queryBuilder().where
                        (VipCRMLevelDetailDao.Properties.VipCRMLevelId.eq(vipCRMLevel.getIdAuto())).list();
                vipCRMLevel.setCategories(vipCRMLevelDetails);
                lists.add(vipCRMLevel);
            }
        }
        return list;
    }

    /**
     * 获取付费会员等级
     *
     * @return
     */
    public List<VipCRMLevel> getVipCrmLevelFeeList() {
        List<VipCRMLevel> vipCRMLevelList = new ArrayList<>();
        List<VipCRMLevel> list = getVipCrmLevelList();
        List<VipCrmAmaLevel> unVipCrmAmaLevelList = VipCrmAmaLevelService.getInstance().getVipCrmAmaLevelList();
        if (unVipCrmAmaLevelList != null && unVipCrmAmaLevelList.size() > 0) {
            for (VipCrmAmaLevel vipCrmAmaLevel : unVipCrmAmaLevelList) {
                for (VipCRMLevel vipCRMLevel : list) {
                    if (vipCrmAmaLevel.getLevel_name().equals(vipCRMLevel.getName())) {
                        vipCRMLevelList.add(vipCRMLevel);
                    }
                }
            }
        } else {
            return list;
        }
        for (VipCRMLevel vipCRMLevel : list) {
            if (vipCRMLevel.getRank()==0){
                vipCRMLevelList.add(vipCRMLevel);
                break;
            }
        }

        return vipCRMLevelList;
    }

    /**
     * 续费 不包含v0
     * 获取付费会员等级
     *
     * @return
     */
    public List<VipCRMLevel> getVipCrmLevelListForUpdate() {
        List<VipCRMLevel> vipCRMLevelList = new ArrayList<>();
        List<VipCRMLevel> list = getVipCrmLevelList();
        List<VipCrmAmaLevel> unVipCrmAmaLevelList = VipCrmAmaLevelService.getInstance().getVipCrmAmaLevelList();
        if (unVipCrmAmaLevelList != null && unVipCrmAmaLevelList.size() > 0) {
            for (VipCrmAmaLevel vipCrmAmaLevel : unVipCrmAmaLevelList) {
                for (VipCRMLevel vipCRMLevel : list) {
                    if (vipCrmAmaLevel.getLevel_name().equals(vipCRMLevel.getName())) {
                        vipCRMLevelList.add(vipCRMLevel);
                    }
                }
            }
        } else {
            return list;
        }

        return vipCRMLevelList;
    }


}
