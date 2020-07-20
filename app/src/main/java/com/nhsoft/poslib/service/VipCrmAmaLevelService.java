package com.nhsoft.poslib.service;

import android.text.TextUtils;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.VipCrmAmaLevel;
import com.nhsoft.poslib.entity.VipLevelPointRule;
import com.nhsoft.poslib.service.greendao.VipCrmAmaLevelDao;
import com.nhsoft.poslib.service.greendao.VipLevelPointRuleDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

/**
 *
 * @Author ADMIN
 * @time 2020-04-07 10:19
 */

public class VipCrmAmaLevelService  {

    private static VipCrmAmaLevelService instance;

    public static VipCrmAmaLevelService getInstance(){
        if (instance==null){
            instance=new VipCrmAmaLevelService();
        }
        return instance;
    }


    /**
     * 插入数据
     * @param vipCrmAmaLevelList
     * @return
     */
    public boolean insertBean(final List<VipCrmAmaLevel> vipCrmAmaLevelList){
        final VipCrmAmaLevelDao vipCrmAmaLevelDao = DaoManager.getInstance().getDaoSession().getVipCrmAmaLevelDao();
        final VipLevelPointRuleDao VipLevelPointRuleDao = DaoManager.getInstance().getDaoSession().getVipLevelPointRuleDao();
        vipCrmAmaLevelDao.deleteAll();
        VipLevelPointRuleDao.deleteAll();

        if(vipCrmAmaLevelList == null || vipCrmAmaLevelList.size() == 0)return true;

        return MatterUtils.doMatter(vipCrmAmaLevelDao, new Runnable() {
            @Override
            public void run() {
                for (VipCrmAmaLevel vipCrmAmaLevel : vipCrmAmaLevelList) {
                    vipCrmAmaLevelDao.insertOrReplaceInTx(vipCrmAmaLevel);
                    VipLevelPointRule point_rule = vipCrmAmaLevel.point_rule;
                    if (point_rule!=null){
                        point_rule.setId(vipCrmAmaLevel.getId());
                        VipLevelPointRuleDao.insertOrReplaceInTx(point_rule);
                    }
                }
            }
        });
    }

    /**
     * 根据id查找name
     * @param id
     * @return
     */
    public String getVipCrmAmaLevelNameById(String id){
        if (TextUtils.isEmpty(id))return "";
        VipCrmAmaLevelDao vipCrmAmaLevelDao = DaoManager.getInstance().getDaoSession().getVipCrmAmaLevelDao();
        List<VipCrmAmaLevel> list = vipCrmAmaLevelDao.queryBuilder().where(VipCrmAmaLevelDao.Properties.Id.eq(id)).build().list();
        if (list!=null&&list.size()>0){
            String level_name = list.get(0).getLevel_name();
            return level_name;
        }else {
            return "";
        }
    }

    /**
     * 根据id查询会员等级
     * @param id
     * @return
     */
    public int getVipCrmAmaLevelRankById(String id){
        if (TextUtils.isEmpty(id))return -1;
        VipCrmAmaLevelDao vipCrmAmaLevelDao = DaoManager.getInstance().getDaoSession().getVipCrmAmaLevelDao();
        List<VipCrmAmaLevel> list = vipCrmAmaLevelDao.queryBuilder().where(VipCrmAmaLevelDao.Properties.Id.eq(id)).build().list();
        if (list!=null&&list.size()>0){
            int rank = list.get(0).getRank();
            return rank;
        }else {
            return -1;
        }
    }

    public VipLevelPointRule getVipCrmAmaLevelPointRuleById(String id){
        VipLevelPointRuleDao vipLevelPointRuleDao = DaoManager.getInstance().getDaoSession().getVipLevelPointRuleDao();
        List<VipLevelPointRule> list = vipLevelPointRuleDao.queryBuilder().where(VipLevelPointRuleDao.Properties.Id.eq(id)).build().list();
        if (list!=null&&list.size()>0){

            return  list.get(0);
        }else {
            return null;
        }
    }

    /**
     * 查询付费等级
     * @return
     */
    public List< VipCrmAmaLevel> getVipCrmAmaLevelList(){
        VipCrmAmaLevelDao vipCrmAmaLevelDao = DaoManager.getInstance().getDaoSession().getVipCrmAmaLevelDao();
        List<VipCrmAmaLevel> list = vipCrmAmaLevelDao.queryBuilder().where(VipCrmAmaLevelDao.Properties.Need_pay.eq(true)).build().list();
        return list;
    }

    /**
     * 查询不付费等级
     * @return
     */
    public List< VipCrmAmaLevel> getUnVipCrmAmaLevelList(){
        VipCrmAmaLevelDao vipCrmAmaLevelDao = DaoManager.getInstance().getDaoSession().getVipCrmAmaLevelDao();
        List<VipCrmAmaLevel> list = vipCrmAmaLevelDao.queryBuilder().where(VipCrmAmaLevelDao.Properties.Need_pay.eq(false)).build().list();
        return list;
    }

    /**
     * 查询所有等级
     * @return
     */
    public List< VipCrmAmaLevel> getCrmAmaLevelList(){
        VipCrmAmaLevelDao vipCrmAmaLevelDao = DaoManager.getInstance().getDaoSession().getVipCrmAmaLevelDao();
        List<VipCrmAmaLevel> list = vipCrmAmaLevelDao.queryBuilder().build().list();
        return list;
    }


    /**
     * 根据id查找name
     * @param id
     * @return
     */
    public VipCrmAmaLevel getVipCrmAmaLevelById(String id){
        if (TextUtils.isEmpty(id))return null;
        VipCrmAmaLevelDao vipCrmAmaLevelDao = DaoManager.getInstance().getDaoSession().getVipCrmAmaLevelDao();
        List<VipCrmAmaLevel> list = vipCrmAmaLevelDao.queryBuilder().where(VipCrmAmaLevelDao.Properties.Id.eq(id)).build().list();
        if (list!=null&&list.size()>0){
            VipLevelPointRuleDao vipLevelPointRuleDao = DaoManager.getInstance().getDaoSession().getVipLevelPointRuleDao();
            List<VipLevelPointRule> pointRules = vipLevelPointRuleDao.queryBuilder().where(VipLevelPointRuleDao.Properties.Id.eq(id)).build().list();
            if(pointRules != null && pointRules.size() > 0){
                list.get(0).point_rule = pointRules.get(0);
            }
            return list.get(0);
        }else {
            return null;
        }
    }

}
