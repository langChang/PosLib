package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.nhsoft.poslib.entity.BookResource;
import com.nhsoft.poslib.entity.PointOrderDetial;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.service.PosItemService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2019/1/15 2:20 PM
 * 此类用于：
 */
public class PointGoodsParseUtil {

    public static List<PointOrderDetial> getPointList(BookResource pointsXml) {
//        if(LoginService.getInstance().isNongMao()){
//            return new ArrayList<>();
//        }

//        pointsXml.setBookResourceParam("<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
//                "<存款金额赠送列表><积分兑换物 物品编号=\"001\"><物品名称>新疆大红枣</物品名称><积分要求>1</积分要求><对应商品编号>434400011</对应商品编号><对应商品名称>新疆大红枣</对应商品名称></积分兑换物><积分兑换物 物品编号=\"004\"><物品名称>火箭</物品名称><积分要求>2</积分要求></积分兑换物><积分兑换物 物品编号=\"006\"><物品名称>蜂蜜</物品名称><积分要求>8</积分要求><对应商品编号>434400034</对应商品编号><对应商品名称>蜂蜜</对应商品名称></积分兑换物><积分兑换物 物品编号=\"007\"><物品名称>兔子年糕</物品名称><积分要求>3</积分要求><对应商品编号>434400789</对应商品编号><对应商品名称>兔子年糕</对应商品名称></积分兑换物><积分兑换物 物品编号=\"008\"><物品名称>梅花糕</物品名称><积分要求>3</积分要求><对应商品编号>434400790</对应商品编号><对应商品名称>梅花糕</对应商品名称></积分兑换物><积分兑换物 物品编号=\"009\"><物品名称>奥尔良</物品名称><积分要求>1</积分要求><对应商品编号>434400793</对应商品编号><对应商品名称>奥尔良</对应商品名称></积分兑换物><积分兑换物 物品编号=\"010\"><物品名称>大菠萝</物品名称><积分要求>5</积分要求><对应商品编号>434400791</对应商品编号><对应商品名称>大菠萝</对应商品名称></积分兑换物><积分兑换物 物品编号=\"011\"><物品名称>使用积分兑换我</物品名称><积分要求>3</积分要求><对应商品编号>434403208</对应商品编号><对应商品名称>使用积分兑换我</对应商品名称></积分兑换物><积分兑换物 物品编号=\"012\"><物品名称>特级脆蜜枣</物品名称><积分要求>2</积分要求><对应商品编号>434400071</对应商品编号><对应商品名称>特级脆蜜枣</对应商品名称></积分兑换物><积分兑换物 物品编号=\"013\"><物品名称>辅助商品03</物品名称><积分要求>12</积分要求><对应商品编号>434400039</对应商品编号><对应商品名称>辅助商品03</对应商品名称></积分兑换物><积分兑换物 物品编号=\"014\"><物品名称>芒果加权二号</物品名称><积分要求>1</积分要求><对应商品编号>434400020</对应商品编号><对应商品名称>芒果加权二号</对应商品名称></积分兑换物><积分兑换物 物品编号=\"015\"><物品名称>12</物品名称><积分要求>21</积分要求></积分兑换物><积分兑换物 物品编号=\"016\"><物品名称>北大荒稻花香25Kg真空包装拿货</物品名称><积分要求>2</积分要求><对应商品编号>434400111</对应商品编号><对应商品名称>北大荒稻花香25Kg真空包装拿货</对应商品名称></积分兑换物><积分兑换物 物品编号=\"017\"><物品名称>黄香蕉</物品名称><积分要求>5</积分要求><对应商品编号>434403482</对应商品编号><对应商品名称>黄香蕉</对应商品名称></积分兑换物><积分兑换物 物品编号=\"018\"><物品名称>梅子酒（柚子味）</物品名称><积分要求>10</积分要求><对应商品编号>434403625</对应商品编号><对应商品名称>梅子酒（柚子味）</对应商品名称></积分兑换物><积分兑换物 物品编号=\"019\"><物品名称>lipton冷泡茶</物品名称><积分要求>10</积分要求><对应商品编号>434403585</对应商品编号><对应商品名称>lipton冷泡茶</对应商品名称></积分兑换物><积分兑换物 物品编号=\"020\"><物品名称>樱桃</物品名称><积分要求>1</积分要求><对应商品编号>434403189</对应商品编号><对应商品名称>樱桃</对应商品名称></积分兑换物><积分兑换物 物品编号=\"021\"><物品名称>鸭脖</物品名称><积分要求>2</积分要求><对应商品编号>434403540</对应商品编号><对应商品名称>鸭脖</对应商品名称></积分兑换物><积分兑换物 物品编号=\"024\"><物品名称>绿芭蕉</物品名称><积分要求>10</积分要求><对应商品编号>434403195</对应商品编号><对应商品名称>绿芭蕉</对应商品名称></积分兑换物><积分兑换物 物品编号=\"025\"><物品名称>荧光笔</物品名称><积分要求>20</积分要求><对应商品编号>434403594</对应商品编号><对应商品名称>荧光笔</对应商品名称></积分兑换物><积分兑换物 物品编号=\"026\"><物品名称>炸鸡</物品名称><积分要求>30</积分要求><对应商品编号>434403192</对应商品编号><对应商品名称>炸鸡</对应商品名称></积分兑换物><积分兑换物 物品编号=\"027\"><物品名称>问问群二群</物品名称><积分要求>2</积分要求><对应商品编号>434403382</对应商品编号><对应商品名称>问问群二群</对应商品名称></积分兑换物><积分兑换物 物品编号=\"028\"><物品名称>绿叶草莓六号</物品名称><积分要求>4</积分要求><对应商品编号>434403556</对应商品编号><对应商品名称>绿叶草莓六号</对应商品名称></积分兑换物><积分兑换物 物品编号=\"029\"><物品名称>惠州原生</物品名称><积分要求>3</积分要求><对应商品编号>434403562</对应商品编号><对应商品名称>惠州原生</对应商品名称></积分兑换物><积分兑换物 物品编号=\"030\"><物品名称>绿叶丹东草莓</物品名称><积分要求>3</积分要求><对应商品编号>434403041</对应商品编号><对应商品名称>绿叶丹东草莓</对应商品名称></积分兑换物><积分兑换物 物品编号=\"031\"><物品名称>雪梨</物品名称><积分要求>10</积分要求><对应商品编号>434403582</对应商品编号><对应商品名称>雪梨</对应商品名称></积分兑换物><积分兑换物 物品编号=\"032\"><物品名称>鲜奶一号</物品名称><积分要求>23</积分要求><对应商品编号>434400081</对应商品编号><对应商品名称>鲜奶一号</对应商品名称></积分兑换物><积分兑换物 物品编号=\"033\"><物品名称>果味月饼</物品名称><积分要求>30</积分要求><对应商品编号>434403627</对应商品编号><对应商品名称>果味月饼</对应商品名称></积分兑换物><积分兑换物 物品编号=\"034\"><物品名称>不二家（荔枝）</物品名称><积分要求>22</积分要求><对应商品编号>434400802</对应商品编号><对应商品名称>不二家（荔枝）</对应商品名称></积分兑换物><积分兑换物 物品编号=\"035\"><物品名称>芬达330ml</物品名称><积分要求>23</积分要求><对应商品编号>434403593</对应商品编号><对应商品名称>芬达330ml</对应商品名称></积分兑换物><积分兑换物 物品编号=\"036\"><物品名称>奉化牛头</物品名称><积分要求>100</积分要求><对应商品编号>434403177</对应商品编号><对应商品名称>奉化牛头</对应商品名称></积分兑换物><积分兑换物 物品编号=\"037\"><物品名称>550ml纯悦包装饮用水</物品名称><积分要求>23</积分要求><对应商品编号>434403566</对应商品编号><对应商品名称>550ml纯悦包装饮用水</对应商品名称></积分兑换物><积分兑换物 物品编号=\"038\"><物品名称>不二家组合装</物品名称><积分要求>120</积分要求><对应商品编号>434400804</对应商品编号><对应商品名称>不二家组合装</对应商品名称></积分兑换物><积分兑换物 物品编号=\"039\"><物品名称>明前茶</物品名称><积分要求>88</积分要求><对应商品编号>434403591</对应商品编号><对应商品名称>明前茶</对应商品名称></积分兑换物><积分兑换物 物品编号=\"040\"><物品名称>苏绣</物品名称><积分要求>23</积分要求><对应商品编号>434400118</对应商品编号><对应商品名称>苏绣</对应商品名称></积分兑换物><积分兑换物 物品编号=\"041\"><物品名称>农夫山泉</物品名称><积分要求>23</积分要求><对应商品编号>434403120</对应商品编号><对应商品名称>农夫山泉</对应商品名称></积分兑换物><积分兑换物 物品编号=\"042\"><物品名称>五花肉</物品名称><积分要求>1</积分要求><对应商品编号>434404504</对应商品编号><对应商品名称>五花肉</对应商品名称></积分兑换物></存款金额赠送列表>");

        if(pointsXml == null){
            return new ArrayList<>();
        }
        List<PointOrderDetial> pointList = new ArrayList<>();

        String xml2json = XmlParser.xml2jsonString(pointsXml.getBookResourceParam());
        try {
            if (TextUtils.isEmpty(xml2json)) return new ArrayList<>();

            JSONObject baseJsonObject = new JSONObject(xml2json);
            JSONObject jsonObject = baseJsonObject.getJSONObject("存款金额赠送列表");

//            JSONObject pointGoodsListObj = jsonObject.getJSONObject("积分兑换物");

            if (jsonObject == null) return new ArrayList<>();
            JSONArray pointlistArray = jsonObject.optJSONArray("积分兑换物");

            if (pointlistArray != null) {
                for (int i = 0; i < pointlistArray.length(); i++) {
                    PointOrderDetial pointOrderDetial = new PointOrderDetial();
                    JSONObject pointObject = (JSONObject) pointlistArray.get(i);
                    pointOrderDetial.setPoint_order_detail_item_name(pointObject.getString("物品名称"));

                    if (isHaveJsonKey("对应商品编号", pointObject, 3)) {
                        pointOrderDetial.setItem_num(Integer.parseInt(pointObject.getString("对应商品编号")));
                        PosItem posItemByKey = PosItemService.getInstance().getPosItemByKey(pointOrderDetial.getItem_num());
                        if(posItemByKey != null){
                            pointOrderDetial.setPoint_order_detail_item_unit(posItemByKey.getItem_unit());
                        }else {
                            pointOrderDetial.setPoint_order_detail_item_unit("");
                        }

                    }else {
                        pointOrderDetial.setPoint_order_detail_item_unit("");
                    }

                    if (isHaveJsonKey("积分要求", pointObject, 3)) {
                        pointOrderDetial.setPoint_order_detail_point(Float.parseFloat(pointObject.getString("积分要求")));
                    }

                    pointOrderDetial.setPoint_order_detail_amount(1);
                    pointList.add(pointOrderDetial);
                }
            } else {

                if(isHaveJsonKey("积分兑换物",jsonObject,3)){
                    PointOrderDetial pointOrderDetial = new PointOrderDetial();
                    JSONObject pointObject = jsonObject.getJSONObject("积分兑换物");
                    pointOrderDetial.setPoint_order_detail_item_name(pointObject.getString("物品名称"));

                    if (isHaveJsonKey("对应商品编号", pointObject, 3)) {
                        pointOrderDetial.setItem_num(Integer.parseInt(pointObject.getString("对应商品编号")));
                        PosItem posItemByKey = PosItemService.getInstance().getPosItemByKey(pointOrderDetial.getItem_num());
                        pointOrderDetial.setPoint_order_detail_item_unit(posItemByKey.getItem_unit());
                    }else {
                        pointOrderDetial.setPoint_order_detail_item_unit("个");
                    }

                    if (isHaveJsonKey("积分要求", pointObject, 3)) {
                        pointOrderDetial.setPoint_order_detail_point(Float.parseFloat(pointObject.getString("积分要求")));
                    }

                    pointOrderDetial.setPoint_order_detail_amount(1);

                    pointList.add(pointOrderDetial);
                }
            }

            return pointList;
//                Log.e("xml2json", "" + pointList.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pointList;
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
