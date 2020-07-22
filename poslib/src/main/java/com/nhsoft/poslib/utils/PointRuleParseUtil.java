package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.nhsoft.poslib.entity.BookResource;
import com.nhsoft.poslib.model.PointRuleXmlModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2019/1/15 2:25 PM
 * 此类用于：
 */
public class PointRuleParseUtil {

    public static List<PointRuleXmlModel> getPointRuleList(BookResource pointRuleXml) {
        List<PointRuleXmlModel> pointRuleXmlModels = new ArrayList<>();
        String xml2json = XmlParser.xml2json(pointRuleXml.getBookResourceParam());

        try {
            JSONObject jsonObject = new JSONObject(xml2json);
            JSONObject pointRuleRootObject = jsonObject.getJSONObject("积分规则表");
            JSONArray pointRuleArray = pointRuleRootObject.optJSONArray("积分规则");
            for (int i=0; i < pointRuleArray.length(); i++){
                PointRuleXmlModel pointRuleXmlModel = new PointRuleXmlModel();
                JSONObject pointRuleObj = (JSONObject) pointRuleArray.get(i);
                pointRuleXmlModel.setPointRuleName(pointRuleObj.getString("规则名称"));
                pointRuleXmlModel.setCounponsMoney((float) pointRuleObj.getDouble("消费金额"));
                pointRuleXmlModel.setPointValue((float) pointRuleObj.getDouble("积分值"));
                pointRuleXmlModel.setIsAlable(pointRuleObj.getInt("启用"));
                pointRuleXmlModel.setOtherSetting(pointRuleObj.getString("附加设置"));
                List<PointRuleXmlModel.PointRuleDetail> pointRuleDetails = new ArrayList<>();
                if(CouponsParseUtil.isHaveJsonKey("积分类别明细",pointRuleObj,3)){
                    String pointCatetoryJson = pointRuleObj.getString("积分类别明细");
                    if(!TextUtils.isEmpty(pointCatetoryJson)){
                        JSONObject pointCatetoryObj = new JSONObject(pointCatetoryJson);
                        if(CouponsParseUtil.isHaveJsonKey("类别明细",pointCatetoryObj,4)){
                            JSONArray catetoryArray = pointCatetoryObj.getJSONArray("类别明细");
                            for (int j=0; j<catetoryArray.length(); j++){
                                String catetoryDetailJson = catetoryArray.optString(j);
                                PointRuleXmlModel.PointRuleDetail pointRuleDetail = new Gson().fromJson(catetoryDetailJson, PointRuleXmlModel.PointRuleDetail.class);
                                pointRuleDetails.add(pointRuleDetail);
                            }
                        }else if(CouponsParseUtil.isHaveJsonKey("类别明细",pointCatetoryObj,3)){
                            String singlecatetoryDetailJson = pointCatetoryObj.getString("类别明细");
                            PointRuleXmlModel.PointRuleDetail pointRuleDetail = new Gson().fromJson(singlecatetoryDetailJson, PointRuleXmlModel.PointRuleDetail.class);
                            pointRuleDetails.add(pointRuleDetail);
                        }
                    }
                    pointRuleXmlModel.setPointRuleDetails(pointRuleDetails);
                }

                pointRuleXmlModels.add(pointRuleXmlModel);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return pointRuleXmlModels;
    }
}
