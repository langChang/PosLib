package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.nhsoft.poslib.entity.BookResource;
import com.nhsoft.poslib.model.BranchXmlModel;
import com.nhsoft.poslib.model.CouponsXmlModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2019/1/15 2:20 PM
 * 此类用于：
 */
public class CouponsParseUtil {

    public static List<CouponsXmlModel> getCouponsList(BookResource couponsXml) {
//        if(LoginService.getInstance().isNongMao()){
//            return new ArrayList<>();
//        }
        List<CouponsXmlModel> couponsList = new ArrayList<>();
        String xml2json = XmlParser.xml2jsonString(couponsXml.getBookResourceParam());
        try {
            if (TextUtils.isEmpty(xml2json)) return new ArrayList<>();

            JSONObject jsonObject = new JSONObject(xml2json);
            JSONObject couponsListObj = jsonObject.getJSONObject("消费券列表");

            if (couponsListObj == null) return new ArrayList<>();
            JSONArray couponslistArray = couponsListObj.optJSONArray("消费券");

            if (couponslistArray != null) {
                for (int i = 0; i < couponslistArray.length(); i++) {
                    CouponsXmlModel couponsXmlModel = new CouponsXmlModel();
                    JSONObject couponsObject = (JSONObject) couponslistArray.get(i);



                    couponsXmlModel.setCouponsName(couponsObject.getString("消费券名称"));

                    if (isHaveJsonKey("消费券代码", couponsObject, 3)) {
                        couponsXmlModel.setTicketCode(couponsObject.getString("消费券代码"));
                    } else {
                        couponsXmlModel.setTicketCode(couponsXmlModel.getCouponsName());
                    }

                    if (isHaveJsonKey("是否线上券", couponsObject, 3)) {
                        couponsXmlModel.setOnline(!couponsObject.getString("是否线上券").equals("0"));
                    } else {
                        couponsXmlModel.setOnline(false);
                    }

                    if (isHaveJsonKey("券中心活动明细ID", couponsObject, 5)) {
                        couponsXmlModel.setCouponActionDetailId(couponsObject.getLong("券中心活动明细ID"));
                    } else {
                        couponsXmlModel.setCouponActionDetailId(null);
                    }

                    couponsXmlModel.setCouponsIsAct(couponsObject.getString("是否启用"));
                    couponsXmlModel.setCouponsValue((float) couponsObject.getDouble("消费券面值"));
                    if (isHaveJsonKey("积分兑券比例", couponsObject, 2)) {
                        couponsXmlModel.setCouponsPointBit((float) couponsObject.getDouble("积分兑券比例"));
                    }
                    if (isHaveJsonKey("积分兑券默认有效天数", couponsObject, 1)) {
                        couponsXmlModel.setCouponsPointDelayday(couponsObject.getInt("积分兑券默认有效天数"));
                    }

//                    couponsXmlModel.setCouponsType(couponsObject.getString("消费券应用分类"));
                    if(isHaveJsonKey("消费券应用分类",couponsObject,3)){
                        couponsXmlModel.setCouponsType(couponsObject.getString("消费券应用分类"));
                    }
//                    couponsXmlModel.setCouponsAddPage(couponsObject.getInt("消费券超额张数累加"));

                    if(isHaveJsonKey("消费券超额张数累加",couponsObject,1)){
                        couponsXmlModel.setCouponsAddPage(couponsObject.getInt("消费券超额张数累加"));
                    }

                    if(isHaveJsonKey("促销特价商品不支持抵扣",couponsObject,3)){
                        couponsXmlModel.setCouponsSupportPromotion(couponsObject.getString("促销特价商品不支持抵扣"));
                    }


                    if (isHaveJsonKey("促销商品不计入最低消费金额", couponsObject, 1)) {
                        couponsXmlModel.setMoneyExceptPromotionItems(couponsObject.getInt("促销商品不计入最低消费金额"));
                    }
                    if (isHaveJsonKey("消费券数量限制", couponsObject, 1)) {
                        couponsXmlModel.setCouponsMaxNumber(couponsObject.getInt("消费券数量限制"));
                    }

                    if (isHaveJsonKey("消费券单独使用", couponsObject, 3)) {
                        couponsXmlModel.setCouponsIsAlone(couponsObject.getString("消费券单独使用"));
                    }
                    if (isHaveJsonKey("消费券最低消费金额", couponsObject, 2)) {
                        couponsXmlModel.setCouponsLimitMoney((float) couponsObject.getDouble("消费券最低消费金额"));
                    }
                    if (isHaveJsonKey("支付方式", couponsObject, 3)) {
                        couponsXmlModel.setCouponsSupportPayStyle(couponsObject.getString("支付方式"));
                    }

                    if (isHaveJsonKey("商品类别列表", couponsObject, 3)) {
                        String catetoryList = couponsObject.getString("商品类别列表");
                        if (!TextUtils.isEmpty(catetoryList)) {
                            List<CouponsXmlModel.CatetoryData> catetoryDataList = new ArrayList<>();
                            JSONObject catetoryListJsonObject = new JSONObject(catetoryList);


                            CouponsXmlModel.CatetoryData catetoryData = null;
                            if (isHaveJsonKey("商品类别", catetoryListJsonObject, 4)) {
                                JSONArray catetoryListArray = catetoryListJsonObject.getJSONArray("商品类别");
                                for (int j = 0; j < catetoryListArray.length(); j++) {
                                    catetoryData = new CouponsXmlModel.CatetoryData();
                                    List<CouponsXmlModel.GoodsData> goodsDataList = new ArrayList<>();
                                    String catetoryJson = catetoryListArray.optString(j);
                                    JSONObject catetoryJsonObject = new JSONObject(catetoryJson);
                                    catetoryData.setCatetoryName(catetoryJsonObject.getString("商品类别名称"));
                                    catetoryData.setCatetoryNumber(catetoryJsonObject.getString("商品类别编码"));
                                    catetoryData.setCatetoryDiacount((float) catetoryJsonObject.getDouble("折扣率"));

                                    if (isHaveJsonKey("商品列表", catetoryJsonObject, 4)) {
                                        JSONArray goodsListObject = catetoryJsonObject.getJSONArray("商品列表");
                                        for (int k = 0; k < goodsListObject.length(); k++) {
                                            String catetoryJsonObjectString = goodsListObject.optString(k);
                                            if (!TextUtils.isEmpty(catetoryJsonObjectString)) {
                                                CouponsXmlModel.GoodsData goodsData = new Gson().fromJson(catetoryJsonObjectString, CouponsXmlModel.GoodsData.class);
                                                goodsDataList.add(goodsData);
                                            }
                                        }
                                    } else if (isHaveJsonKey("商品列表", catetoryJsonObject, 3)) {
                                        if (!TextUtils.isEmpty(catetoryJsonObject.getString("商品列表"))) {
                                            String goodsListJson = catetoryJsonObject.getString("商品列表");

                                            if (!TextUtils.isEmpty(goodsListJson)) {
                                                catetoryJsonObject = new JSONObject(goodsListJson);
                                                if (isHaveJsonKey("商品", catetoryJsonObject, 4)) {
                                                    JSONArray goodsListArray = catetoryJsonObject.getJSONArray("商品");
                                                    for (int m = 0; m < goodsListArray.length(); m++) {
                                                        String goodBeanJson = goodsListArray.optString(m);
                                                        if (!TextUtils.isEmpty(goodBeanJson)) {
                                                            CouponsXmlModel.GoodsData goodsData = new Gson().fromJson(goodBeanJson, CouponsXmlModel.GoodsData.class);
                                                            goodsDataList.add(goodsData);
                                                        }
                                                    }
                                                } else if (isHaveJsonKey("商品", catetoryJsonObject, 3)) {
                                                    String goodsObjectJson = catetoryJsonObject.getString("商品");
                                                    if (!TextUtils.isEmpty(goodsObjectJson)) {
                                                        CouponsXmlModel.GoodsData goodsData = new Gson().fromJson(goodsObjectJson, CouponsXmlModel.GoodsData.class);
                                                        goodsDataList.add(goodsData);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    catetoryData.setGoodsList(goodsDataList);
                                    catetoryDataList.add(catetoryData);
                                }

                            } else if (isHaveJsonKey("商品类别", catetoryListJsonObject, 3)) {
                                catetoryData = new CouponsXmlModel.CatetoryData();
                                JSONObject catetoryJsonObject = new JSONObject(catetoryListJsonObject.getString("商品类别"));
                                catetoryData.setCatetoryName(catetoryJsonObject.getString("商品类别名称"));
                                catetoryData.setCatetoryNumber(catetoryJsonObject.getString("商品类别编码"));
                                catetoryData.setCatetoryDiacount((float) catetoryJsonObject.getDouble("折扣率"));

                                List<CouponsXmlModel.GoodsData> goodsDataList = new ArrayList<>();
                                if (isHaveJsonKey("商品列表", catetoryJsonObject, 4)) {
                                    JSONArray goodsListObject = catetoryJsonObject.getJSONArray("商品列表");
                                    for (int k = 0; k < goodsListObject.length(); k++) {
                                        String catetoryJsonObjectString = goodsListObject.optString(k);
                                        if (!TextUtils.isEmpty(catetoryJsonObjectString)) {
                                            CouponsXmlModel.GoodsData goodsData = new Gson().fromJson(catetoryJsonObjectString, CouponsXmlModel.GoodsData.class);
                                            goodsDataList.add(goodsData);
                                        }
                                    }
                                } else if (isHaveJsonKey("商品列表", catetoryJsonObject, 3)) {
                                    if (!TextUtils.isEmpty(catetoryJsonObject.getString("商品列表"))) {
                                        String goodsListJson = catetoryJsonObject.getString("商品列表");

                                        if (!TextUtils.isEmpty(goodsListJson)) {
                                            catetoryJsonObject = new JSONObject(goodsListJson);
                                            if (isHaveJsonKey("商品", catetoryJsonObject, 4)) {
                                                JSONArray goodsListArray = catetoryJsonObject.getJSONArray("商品");
                                                for (int m = 0; m < goodsListArray.length(); m++) {
                                                    String goodBeanJson = goodsListArray.optString(m);
                                                    if (!TextUtils.isEmpty(goodBeanJson)) {
                                                        CouponsXmlModel.GoodsData goodsData = new Gson().fromJson(goodBeanJson, CouponsXmlModel.GoodsData.class);
                                                        goodsDataList.add(goodsData);
                                                    }
                                                }
                                            } else if (isHaveJsonKey("商品", catetoryJsonObject, 3)) {
                                                String goodsObjectJson = catetoryJsonObject.getString("商品");
                                                if (!TextUtils.isEmpty(goodsObjectJson)) {
                                                    CouponsXmlModel.GoodsData goodsData = new Gson().fromJson(goodsObjectJson, CouponsXmlModel.GoodsData.class);
                                                    goodsDataList.add(goodsData);
                                                }
                                            }
                                        }
                                    }
                                }
                                catetoryData.setGoodsList(goodsDataList);
                                catetoryDataList.add(catetoryData);
                            }
                            couponsXmlModel.setmCatetoryDataList(catetoryDataList);
                        }
                    }


                    if (isHaveJsonKey("全部商品", couponsObject, 1)) {
                        int isAll = couponsObject.getInt("全部商品");
                        couponsXmlModel.setAll(isAll != 0);
                    }

                    if (isHaveJsonKey("类别代码列表", couponsObject, 3)) {
                        String cateCodeList = couponsObject.getString("类别代码列表");
                        couponsXmlModel.setCategoryCodeList(cateCodeList);
                    }


                    if (isHaveJsonKey("商品列表", couponsObject, 3)) {
                        String goodsCodeList = couponsObject.getString("商品列表");
                        couponsXmlModel.setGoodsCodeList(goodsCodeList);
                    }

//                    if (!couponsXmlModel.isAll() && couponsXmlModel.getmCatetoryDataList() == null && couponsXmlModel.getGoodsCodeList() == null)


                    if (isHaveJsonKey("应用分店列表", couponsObject, 3)) {
                        String branchList = couponsObject.getString("应用分店列表");
                        JSONObject branchJsonObject = new JSONObject(branchList);
                        if (!TextUtils.isEmpty(branchList)) {
                            List<BranchXmlModel> branchDataList = new ArrayList<>();
                            if (isHaveJsonKey("AppliedBranch", branchJsonObject, 4)) {
                                JSONArray appliedBranchList = branchJsonObject.getJSONArray("AppliedBranch");
                                for (int j = 0; j < appliedBranchList.length(); j++) {
                                    String catetoryJson = appliedBranchList.optString(j);
                                    BranchXmlModel branchData = new Gson().fromJson(catetoryJson, BranchXmlModel.class);
                                    branchDataList.add(branchData);
                                }
                            } else if (isHaveJsonKey("AppliedBranch", branchJsonObject, 3)) {
                                String catetoryJson = branchJsonObject.getString("AppliedBranch");
                                BranchXmlModel branchData = new Gson().fromJson(catetoryJson, BranchXmlModel.class);
                                branchDataList.add(branchData);
                            }
                            couponsXmlModel.setmBranchList(branchDataList);
                        }
                    }

                    couponsList.add(couponsXmlModel);

                }
            } else {

                if(isHaveJsonKey("消费券",couponsListObj,3)){
                    CouponsXmlModel couponsXmlModel = new CouponsXmlModel();
                    JSONObject couponsObject = (JSONObject) couponsListObj.get("消费券");

                    couponsXmlModel.setCouponsName(couponsObject.getString("消费券名称"));
                    if (isHaveJsonKey("消费券代码", couponsObject, 3)) {
                        couponsXmlModel.setTicketCode(couponsObject.getString("消费券代码"));
                    } else {
                        couponsXmlModel.setTicketCode(couponsXmlModel.getCouponsName());
                    }

                    if (isHaveJsonKey("是否线上券", couponsObject, 3)) {
                        couponsXmlModel.setOnline(!couponsObject.getString("是否线上券").equals("0"));
                    } else {
                        couponsXmlModel.setOnline(false);
                    }


                    if (isHaveJsonKey("券中心活动明细ID", couponsObject, 5)) {
                        couponsXmlModel.setCouponActionDetailId(couponsObject.getLong("券中心活动明细ID"));
                    } else {
                        couponsXmlModel.setCouponActionDetailId(null);
                    }

                    couponsXmlModel.setCouponsIsAct(couponsObject.getString("是否启用"));
                    couponsXmlModel.setCouponsValue((float) couponsObject.getDouble("消费券面值"));
                    if (isHaveJsonKey("积分兑券比例", couponsObject, 2)) {
                        couponsXmlModel.setCouponsPointBit((float) couponsObject.getDouble("积分兑券比例"));
                    }
                    if (isHaveJsonKey("积分兑券默认有效天数", couponsObject, 1)) {
                        couponsXmlModel.setCouponsPointDelayday(couponsObject.getInt("积分兑券默认有效天数"));
                    }

                    couponsXmlModel.setCouponsType(couponsObject.getString("消费券应用分类"));
                    couponsXmlModel.setCouponsAddPage(couponsObject.getInt("消费券超额张数累加"));

                    couponsXmlModel.setCouponsSupportPromotion(couponsObject.getString("促销特价商品不支持抵扣"));

                    if (isHaveJsonKey("促销商品不计入最低消费金额", couponsObject, 1)) {
                        couponsXmlModel.setMoneyExceptPromotionItems(couponsObject.getInt("促销商品不计入最低消费金额"));
                    }
                    if (isHaveJsonKey("消费券数量限制", couponsObject, 1)) {
                        couponsXmlModel.setCouponsMaxNumber(couponsObject.getInt("消费券数量限制"));
                    }

                    if (isHaveJsonKey("消费券单独使用", couponsObject, 3)) {
                        couponsXmlModel.setCouponsIsAlone(couponsObject.getString("消费券单独使用"));
                    }
                    if (isHaveJsonKey("消费券最低消费金额", couponsObject, 2)) {
                        couponsXmlModel.setCouponsLimitMoney((float) couponsObject.getDouble("消费券最低消费金额"));
                    }
                    if (isHaveJsonKey("支付方式", couponsObject, 3)) {
                        couponsXmlModel.setCouponsSupportPayStyle(couponsObject.getString("支付方式"));
                    }

                    if (isHaveJsonKey("商品类别列表", couponsObject, 3)) {
                        String catetoryList = couponsObject.getString("商品类别列表");
                        if (!TextUtils.isEmpty(catetoryList)) {
                            List<CouponsXmlModel.CatetoryData> catetoryDataList = new ArrayList<>();
                            JSONObject catetoryListJsonObject = new JSONObject(catetoryList);


                            CouponsXmlModel.CatetoryData catetoryData = null;
                            if (isHaveJsonKey("商品类别", catetoryListJsonObject, 4)) {
                                JSONArray catetoryListArray = catetoryListJsonObject.getJSONArray("商品类别");
                                for (int j = 0; j < catetoryListArray.length(); j++) {
                                    catetoryData = new CouponsXmlModel.CatetoryData();
                                    List<CouponsXmlModel.GoodsData> goodsDataList = new ArrayList<>();
                                    String catetoryJson = catetoryListArray.optString(j);
                                    JSONObject catetoryJsonObject = new JSONObject(catetoryJson);
                                    catetoryData.setCatetoryName(catetoryJsonObject.getString("商品类别名称"));
                                    catetoryData.setCatetoryNumber(catetoryJsonObject.getString("商品类别编码"));
                                    catetoryData.setCatetoryDiacount((float) catetoryJsonObject.getDouble("折扣率"));

                                    if (isHaveJsonKey("商品列表", catetoryJsonObject, 4)) {
                                        JSONArray goodsListObject = catetoryJsonObject.getJSONArray("商品列表");
                                        for (int k = 0; k < goodsListObject.length(); k++) {
                                            String catetoryJsonObjectString = goodsListObject.optString(k);
                                            if (!TextUtils.isEmpty(catetoryJsonObjectString)) {
                                                CouponsXmlModel.GoodsData goodsData = new Gson().fromJson(catetoryJsonObjectString, CouponsXmlModel.GoodsData.class);
                                                goodsDataList.add(goodsData);
                                            }
                                        }
                                    } else if (isHaveJsonKey("商品列表", catetoryJsonObject, 3)) {
                                        if (!TextUtils.isEmpty(catetoryJsonObject.getString("商品列表"))) {
                                            String goodsListJson = catetoryJsonObject.getString("商品列表");

                                            if (!TextUtils.isEmpty(goodsListJson)) {
                                                catetoryJsonObject = new JSONObject(goodsListJson);
                                                if (isHaveJsonKey("商品", catetoryJsonObject, 4)) {
                                                    JSONArray goodsListArray = catetoryJsonObject.getJSONArray("商品");
                                                    for (int m = 0; m < goodsListArray.length(); m++) {
                                                        String goodBeanJson = goodsListArray.optString(m);
                                                        if (!TextUtils.isEmpty(goodBeanJson)) {
                                                            CouponsXmlModel.GoodsData goodsData = new Gson().fromJson(goodBeanJson, CouponsXmlModel.GoodsData.class);
                                                            goodsDataList.add(goodsData);
                                                        }
                                                    }
                                                } else if (isHaveJsonKey("商品", catetoryJsonObject, 3)) {
                                                    String goodsObjectJson = catetoryJsonObject.getString("商品");
                                                    if (!TextUtils.isEmpty(goodsObjectJson)) {
                                                        CouponsXmlModel.GoodsData goodsData = new Gson().fromJson(goodsObjectJson, CouponsXmlModel.GoodsData.class);
                                                        goodsDataList.add(goodsData);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    catetoryData.setGoodsList(goodsDataList);
                                    catetoryDataList.add(catetoryData);
                                }

                            } else if (isHaveJsonKey("商品类别", catetoryListJsonObject, 3)) {
                                catetoryData = new CouponsXmlModel.CatetoryData();
                                JSONObject catetoryJsonObject = new JSONObject(catetoryListJsonObject.getString("商品类别"));
                                catetoryData.setCatetoryName(catetoryJsonObject.getString("商品类别名称"));
                                catetoryData.setCatetoryNumber(catetoryJsonObject.getString("商品类别编码"));
                                catetoryData.setCatetoryDiacount((float) catetoryJsonObject.getDouble("折扣率"));

                                List<CouponsXmlModel.GoodsData> goodsDataList = new ArrayList<>();
                                if (isHaveJsonKey("商品列表", catetoryJsonObject, 4)) {
                                    JSONArray goodsListObject = catetoryJsonObject.getJSONArray("商品列表");
                                    for (int k = 0; k < goodsListObject.length(); k++) {
                                        String catetoryJsonObjectString = goodsListObject.optString(k);
                                        if (!TextUtils.isEmpty(catetoryJsonObjectString)) {
                                            CouponsXmlModel.GoodsData goodsData = new Gson().fromJson(catetoryJsonObjectString, CouponsXmlModel.GoodsData.class);
                                            goodsDataList.add(goodsData);
                                        }
                                    }
                                } else if (isHaveJsonKey("商品列表", catetoryJsonObject, 3)) {
                                    if (!TextUtils.isEmpty(catetoryJsonObject.getString("商品列表"))) {
                                        String goodsListJson = catetoryJsonObject.getString("商品列表");

                                        if (!TextUtils.isEmpty(goodsListJson)) {
                                            catetoryJsonObject = new JSONObject(goodsListJson);
                                            if (isHaveJsonKey("商品", catetoryJsonObject, 4)) {
                                                JSONArray goodsListArray = catetoryJsonObject.getJSONArray("商品");
                                                for (int m = 0; m < goodsListArray.length(); m++) {
                                                    String goodBeanJson = goodsListArray.optString(m);
                                                    if (!TextUtils.isEmpty(goodBeanJson)) {
                                                        CouponsXmlModel.GoodsData goodsData = new Gson().fromJson(goodBeanJson, CouponsXmlModel.GoodsData.class);
                                                        goodsDataList.add(goodsData);
                                                    }
                                                }
                                            } else if (isHaveJsonKey("商品", catetoryJsonObject, 3)) {
                                                String goodsObjectJson = catetoryJsonObject.getString("商品");
                                                if (!TextUtils.isEmpty(goodsObjectJson)) {
                                                    CouponsXmlModel.GoodsData goodsData = new Gson().fromJson(goodsObjectJson, CouponsXmlModel.GoodsData.class);
                                                    goodsDataList.add(goodsData);
                                                }
                                            }
                                        }
                                    }
                                }
                                catetoryData.setGoodsList(goodsDataList);
                                catetoryDataList.add(catetoryData);
                            }
                            couponsXmlModel.setmCatetoryDataList(catetoryDataList);
                        }
                    }


                    if (isHaveJsonKey("全部商品", couponsObject, 1)) {
                        int isAll = couponsObject.getInt("全部商品");
                        couponsXmlModel.setAll(isAll != 0);
                    }

                    if (isHaveJsonKey("类别代码列表", couponsObject, 3)) {
                        String cateCodeList = couponsObject.getString("类别代码列表");
                        couponsXmlModel.setCategoryCodeList(cateCodeList);
                    }


                    if (isHaveJsonKey("商品列表", couponsObject, 3)) {
                        String goodsCodeList = couponsObject.getString("商品列表");
                        couponsXmlModel.setGoodsCodeList(goodsCodeList);
                    }

//                    if (!couponsXmlModel.isAll() && couponsXmlModel.getmCatetoryDataList() == null && couponsXmlModel.getGoodsCodeList() == null)


                    if (isHaveJsonKey("应用分店列表", couponsObject, 3)) {
                        String branchList = couponsObject.getString("应用分店列表");
                        JSONObject branchJsonObject = new JSONObject(branchList);
                        if (!TextUtils.isEmpty(branchList)) {
                            List<BranchXmlModel> branchDataList = new ArrayList<>();
                            if (isHaveJsonKey("AppliedBranch", branchJsonObject, 4)) {
                                JSONArray appliedBranchList = branchJsonObject.getJSONArray("AppliedBranch");
                                for (int j = 0; j < appliedBranchList.length(); j++) {
                                    String catetoryJson = appliedBranchList.optString(j);
                                    BranchXmlModel branchData = new Gson().fromJson(catetoryJson, BranchXmlModel.class);
                                    branchDataList.add(branchData);
                                }
                            } else if (isHaveJsonKey("AppliedBranch", branchJsonObject, 3)) {
                                String catetoryJson = branchJsonObject.getString("AppliedBranch");
                                BranchXmlModel branchData = new Gson().fromJson(catetoryJson, BranchXmlModel.class);
                                branchDataList.add(branchData);
                            }
                            couponsXmlModel.setmBranchList(branchDataList);
                        }
                    }

                    couponsList.add(couponsXmlModel);
                }
            }


            return couponsList;
//                Log.e("xml2json", "" + couponsList.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return couponsList;
    }

    public static boolean isHaveJsonKey(String key, JSONObject jsonObject, int type) {
        try {
            if (type == 1) {
                jsonObject.getInt(key);
            } else if (type == 2) {
                jsonObject.getDouble(key);
            } else if (type == 3) {
                if (TextUtils.isEmpty(jsonObject.getString(key))) {
                    return false;
                }
                return true;
            } else if (type == 4) {
                jsonObject.getJSONArray(key);
            }else if (type == 5) {
                jsonObject.getLong(key);
            }
        } catch (JSONException e) {
            return false;
        }
        return true;
    }
}
