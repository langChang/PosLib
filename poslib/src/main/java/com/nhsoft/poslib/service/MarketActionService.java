package com.nhsoft.poslib.service;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.MarketAction;
import com.nhsoft.poslib.entity.MarketActionDetail;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.model.BranchXmlModel;
import com.nhsoft.poslib.model.MarketActionScopeBean;
import com.nhsoft.poslib.service.greendao.MarketActionDao;
import com.nhsoft.poslib.service.greendao.MarketActionDetailDao;
import com.nhsoft.poslib.utils.MatterUtils;
import com.nhsoft.poslib.utils.XmlParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2018/11/15 11:29 AM
 * 此类用于：用于操作商品的服务操作
 */
public class MarketActionService {
    /**
     * 保存PosItem数据
     * PosItem 集合
     *
     * @param result
     * @return
     */
    public static boolean saveMarketAction(final List<MarketAction> result) {
        final MarketActionDao marketActionDao = DaoManager.getInstance().getDaoSession().getMarketActionDao();
        final MarketActionDetailDao marketActionDetailDao = DaoManager.getInstance().getDaoSession().getMarketActionDetailDao();

        marketActionDetailDao.deleteAll();
        marketActionDao.deleteAll();

        if (result.size() == 0) return true;

        return MatterUtils.doMatter(marketActionDao, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < result.size(); i++) {
                    MarketAction marketAction = result.get(i);
                    if(marketAction.getPos_action_param() != null){
                        marketAction.setAction_param(new Gson().toJson(marketAction.getPos_action_param()));
                    }

                    marketActionDao.insertOrReplaceInTx(marketAction);
                    List<MarketActionDetail> market_action_details = marketAction.getMarket_action_details();
                    if (market_action_details != null && market_action_details.size() > 0) {
                        marketActionDetailDao.insertOrReplaceInTx(market_action_details);
                    }
                }
            }
        });
    }


    /**
     * 根据action_id来获取促销
     * @param actionId
     * @return
     */
    public MarketAction getOnceMarketActionByActionId(String actionId){
        if(actionId == null || TextUtils.isEmpty(actionId))return null;
        final MarketActionDao marketActionDao = DaoManager.getInstance().getDaoSession().getMarketActionDao();
        List<MarketAction> list = marketActionDao.queryBuilder().where(MarketActionDao.Properties.Action_id.eq(actionId),MarketActionDao.Properties.Only_use_once.eq(true)).build().list();
        if(list == null || list.isEmpty()){
            return null;
        }
        return list.get(0);
    }


    /**
     * 获取所有的活动
     *
     * @return
     */
    public static List<MarketAction> getAllMarketAction(int branch) {
        List<MarketAction> marketActions = new ArrayList<>();
        final MarketActionDao marketActionDao = DaoManager.getInstance().getDaoSession().getMarketActionDao();
        final MarketActionDetailDao marketActionDetailDao = DaoManager.getInstance().getDaoSession().getMarketActionDetailDao();

        List<MarketAction> loadMarketActions = marketActionDao.queryBuilder().orderDesc(MarketActionDao.Properties.Action_audit_time).build().list();
//        List<MarketAction> loadMarketActions = marketActionDao.loadAll();
        if (loadMarketActions != null && loadMarketActions.size() > 0) {
            for (MarketAction marketAction : loadMarketActions) {
                LibConfig.allOnceMarketAction.clear();
                if(marketAction.getOnly_use_once()!= null && marketAction.getOnly_use_once()){

                    LibConfig.allOnceMarketAction.add(marketAction);
                }
                if(!TextUtils.isEmpty(marketAction.getAction_param())){
                    MarketActionScopeBean marketActionScopeBean = new Gson().fromJson(marketAction.getAction_param(), MarketActionScopeBean.class);
                    marketAction.setActionMoney(marketActionScopeBean.getAction_money());
                }
                List<MarketActionDetail> marketActionDetails = marketActionDetailDao.queryBuilder().where(MarketActionDetailDao.Properties.Action_id.eq(marketAction.getAction_id())).build().list();
                for (MarketActionDetail marketActionDetail : marketActionDetails) {
                    List<BranchXmlModel> branchXmlModels = new ArrayList<>();
                    String xml2json = XmlParser.xml2json(marketActionDetail.getMarket_action_detail_branch());
                    try {
                        JSONObject jsonObjectRoot = new JSONObject(xml2json);
                        JSONObject brachListObject = new JSONObject(jsonObjectRoot.getString("AppliedBranchArray"));
                        if (isHaveJsonKey("AppliedBranch", brachListObject, 4)) {
                            String appliedBranch = brachListObject.getString("AppliedBranch");
                            List<BranchXmlModel> branchXmlModelmore = new Gson().fromJson(appliedBranch, new TypeToken<List<BranchXmlModel>>() {
                            }.getType());
                            branchXmlModels.addAll(branchXmlModelmore);
                        } else if (isHaveJsonKey("AppliedBranch", brachListObject, 3)) {
                            BranchXmlModel branchXmlModel = new Gson().fromJson(brachListObject.getString("AppliedBranch"), BranchXmlModel.class);
                            branchXmlModels.add(branchXmlModel);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    marketActionDetail.setBranchXmlModels(branchXmlModels);
                }
                marketAction.setMarket_action_details(marketActionDetails);
            }
            marketActions.addAll(loadMarketActions);
        }
        return marketActions;
    }

    private static boolean isHaveJsonKey(String key, JSONObject jsonObject, int type) {
        try {
            if (type == 1) {
                jsonObject.getInt(key);
            } else if (type == 2) {
                jsonObject.getDouble(key);
            } else if (type == 3) {
                jsonObject.getString(key);
            } else if (type == 4) {
                jsonObject.getJSONArray(key);
            }
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

}
