package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.model.ClientParamsBean;
import com.nhsoft.poslib.model.ItemSequenceBean;
import com.nhsoft.poslib.model.NewVersionBean;
import com.nhsoft.poslib.model.PosScaleStyleTypeBean;
import com.nhsoft.poslib.model.SaleParamsBean;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Iverson on 2018/11/29 12:01 PM
 * 此类用于：
 */
public class XmlUtil {


    public static ClientParamsBean getClientParams(String content){
        ClientParamsBean clientParamsBean = new ClientParamsBean();
        clientParamsBean.setCreditControlPW("");
        clientParamsBean.setCreditControlType("0");
        if(TextUtils.isEmpty(content)){
            return clientParamsBean;
        }
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            //②从Dom工厂中获得dom解析器
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            //③把要解析的xml文件读入Dom解析器
//            content = URLDecoder.decode(content, "UTF-8");
            Document doc = dbBuilder.parse(new ByteArrayInputStream(content.getBytes("GBK")));
            NodeList nList = doc.getElementsByTagName("PosClientParam");
            for (int i = 0; i < nList.getLength(); i++) {
                //先从Person元素开始解析
                Element personElement = (Element) nList.item(i);

                NodeList childNoList = personElement.getChildNodes();
                for (int j = 0; j < childNoList.getLength(); j++) {
                    Node childNode = childNoList.item(j);
                    //判断子note类型是否为元素Note
                    if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNode;
                        String nodeValue;
                        if (childElement.getFirstChild() == null) {
                            nodeValue = "";
                        } else {
                            nodeValue = String.valueOf(childElement.getFirstChild().getNodeValue()) == null ? "" : String.valueOf(childElement.getFirstChild().getNodeValue());
                        }
                        if ("CreditControlType".equals(childElement.getNodeName())) {
                            clientParamsBean.setCreditControlType(nodeValue);
                        } else if ("CreditControlPW".equals(childElement.getNodeName())) {
                            clientParamsBean.setCreditControlPW(nodeValue);
                        }
                    }
                }

            }
        }catch (Exception e){

        }
        return clientParamsBean;
    }


    public static ArrayList<PosScaleStyleTypeBean> getPosScaleStyle(String content) {
        ArrayList<PosScaleStyleTypeBean> typeBeanArrayList = new ArrayList<PosScaleStyleTypeBean>();
        try {
            //①获得DOM解析器的工厂示例:
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            //②从Dom工厂中获得dom解析器
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            //③把要解析的xml文件读入Dom解析器
//            content = URLDecoder.decode(content, "UTF-8");
            Document doc = dbBuilder.parse(new ByteArrayInputStream(content.getBytes("GBK")));
            System.out.println("处理该文档的DomImplemention对象=" + doc.getImplementation());
            //④得到文档中名称为person的元素的结点列表
            NodeList nList = doc.getElementsByTagName("PosPaymentType");
            //⑤遍历该集合,显示集合中的元素以及子元素的名字
            for (int i = 0; i < nList.getLength(); i++) {
                //先从Person元素开始解析
                Element personElement = (Element) nList.item(i);
                PosScaleStyleTypeBean p = new PosScaleStyleTypeBean();
                //获取person下的name和age的Note集合
                NodeList childNoList = personElement.getChildNodes();
                for (int j = 0; j < childNoList.getLength(); j++) {
                    Node childNode = childNoList.item(j);
                    //判断子note类型是否为元素Note
                    if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNode;
                        String nodeValue;
                        if (childElement.getFirstChild() == null) {
                            nodeValue = "";
                        } else {
                            nodeValue = String.valueOf(childElement.getFirstChild().getNodeValue()) == null ? "" : String.valueOf(childElement.getFirstChild().getNodeValue());
                        }
                        if ("PaymentTypeCode".equals(childElement.getNodeName())) {
                            p.setPaymentTypeCode(nodeValue);
                        } else if ("PaymentTypeName".equals(childElement.getNodeName())) {
                            p.setPaymentTypeName(nodeValue);
                        } else if ("PaymentTypeMemo".equals(childElement.getNodeName())) {
                            p.setPaymentTypeMemo(nodeValue);
                        } else if ("POSPaymentType".equals(childElement.getNodeName())) {
                            p.setPosPaymentType(nodeValue);
                        } else if ("PaymentNeedCheck".equals(childElement.getNodeName())) {
                            p.setPaymentNeedCheck(nodeValue);
                        } else if ("POSOrderPaymentType".equals(childElement.getNodeName())) {
                            p.setPosOrderPaymentType(nodeValue);
                        } else if ("POSCardPaymentType".equals(childElement.getNodeName())) {
                            p.setPosCardPaymentType(nodeValue);
                        } else if ("POSPaymentEqualSource".equals(childElement.getNodeName())) {
                            if("0".equals(nodeValue)){
                                p.setPosPaymentEqualSource(false);
                            }else {
                                p.setPosPaymentEqualSource(true);
                            }
                        } else if("AppliedBranchs".equals(childElement.getNodeName())){
                            p.setPosPayBranchs(personElement == null ? "" : personElement.getTextContent());
                        }else if("EnableEjectCashBox".equals(childElement.getNodeName())){
                            if("0".equals(nodeValue)){
                                p.setEnableEjectCashBox(false);
                            }else {
                                p.setEnableEjectCashBox(true);
                            }
                        }
                    }
                }
                if ("1".equals(p.getPosPaymentType())) {
                    if(!LibConfig.C_PAYMENT_TYPE_INTEGRAL_NAME.equals(p.getPaymentTypeName()) || !LibConfig.C_PAYMENT_TYPE_SIGNBILL_NAME.equals(p.getPaymentTypeName())
                            || !LibConfig.C_PAYMENT_TYPE_COINPURSE_NAME.equals(p.getPaymentTypeName())){
                        if(TextUtils.isEmpty(p.getPosPayBranchs()) || (p.getPosPayBranchs().contains(LibConfig.activeLoginBean.getBranch_num() + LibConfig.activeLoginBean.getBranch_name()))){
                            typeBeanArrayList.add(p);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        LibConfig.openDrawPayment.clear();
        for (PosScaleStyleTypeBean scaleStyleTypeBean : typeBeanArrayList){
            if(scaleStyleTypeBean.isEnableEjectCashBox()){
                LibConfig.openDrawPayment.add(scaleStyleTypeBean.getPaymentTypeName());
            }
        }
        return typeBeanArrayList;
    }

    public static ArrayList<ItemSequenceBean> getItemSequence(String content) {
        ArrayList<ItemSequenceBean> typeBeanArrayList = new ArrayList<>();
        try {
            //①获得DOM解析器的工厂示例:
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            //②从Dom工厂中获得dom解析器
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            //③把要解析的xml文件读入Dom解析器
//            content = URLDecoder.decode(content, "UTF-8");
            Document doc = dbBuilder.parse(new ByteArrayInputStream(content.getBytes("GBK")));
            System.out.println("处理该文档的DomImplemention对象=" + doc.getImplementation());
            //④得到文档中名称为person的元素的结点列表
            NodeList nList = doc.getElementsByTagName("PosItem");
            //⑤遍历该集合,显示集合中的元素以及子元素的名字
            for (int i = 0; i < nList.getLength(); i++) {
                //先从Person元素开始解析
                Element personElement = (Element) nList.item(i);
                ItemSequenceBean p = new ItemSequenceBean();
                //获取person下的name和age的Note集合
                NodeList childNoList = personElement.getChildNodes();
                for (int j = 0; j < childNoList.getLength(); j++) {
                    Node childNode = childNoList.item(j);
                    //判断子note类型是否为元素Note
                    if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNode;
                        long nodeValue;
                        if (childElement.getFirstChild() == null) {
                            nodeValue = 1;
                        } else {
                            nodeValue = TextUtils.isEmpty(childElement.getFirstChild().getNodeValue()) ? 1 : Long.valueOf(childElement.getFirstChild().getNodeValue());
                        }
                        if ("ItemNum".equals(childElement.getNodeName())) {
                            p.setItemNum(nodeValue);
                        } else if ("ItemSequence".equals(childElement.getNodeName())) {
                            p.setItemSequence(nodeValue);
                        }
                    }
                }
                typeBeanArrayList.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return typeBeanArrayList;
    }


    public static NewVersionBean getNewVersion(String versionXml) {

        NewVersionBean newVersionBean = new NewVersionBean();
        try {
            //①获得DOM解析器的工厂示例:
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            //②从Dom工厂中获得dom解析器
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            //③把要解析的xml文件读入Dom解析器
//            content = URLDecoder.decode(content, "UTF-8");
            Document doc = dbBuilder.parse(new ByteArrayInputStream(versionXml.getBytes("UTF-8")));
            System.out.println("处理该文档的DomImplemention对象=" + doc.getImplementation());
            //④得到文档中名称为person的元素的结点列表
            NodeList nList = doc.getElementsByTagName("info");
            //⑤遍历该集合,显示集合中的元素以及子元素的名字
            for (int i = 0; i < nList.getLength(); i++) {
                //先从Person元素开始解析
                Element personElement = (Element) nList.item(i);
                //获取person下的name和age的Note集合
                NodeList childNoList = personElement.getChildNodes();
                for (int j = 0; j < childNoList.getLength(); j++) {
                    Node childNode = childNoList.item(j);
                    //判断子note类型是否为元素Note
                    if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNode;
                        String nodeValue;
                        if (childElement.getFirstChild() == null) {
                            nodeValue = "";
                        } else {
                            nodeValue = String.valueOf(childElement.getFirstChild().getNodeValue()) == null ? "" : String.valueOf(childElement.getFirstChild().getNodeValue());
                        }
                        if ("version".equals(childElement.getNodeName())) {
                            newVersionBean.setVersion(nodeValue);
                        } else if ("versionCode".equals(childElement.getNodeName())) {
                            newVersionBean.setVersionCode(nodeValue);
                        } else if ("url".equals(childElement.getNodeName())) {
                            newVersionBean.setUrl(nodeValue);
                        } else if ("description".equals(childElement.getNodeName())) {
                            newVersionBean.setDescription(nodeValue);
                        } else if ("url_server".equals(childElement.getNodeName())) {
                            newVersionBean.setUrl_server(nodeValue);
                        } else if ("level".equals(childElement.getNodeName())) {
                            newVersionBean.setLevel(nodeValue);
                        } else if ("account".equals(childElement.getNodeName())) {
                            newVersionBean.setAccount(nodeValue);
                        } else if ("min_version".equals(childElement.getNodeName())) {
                            newVersionBean.setMinVersion(nodeValue);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newVersionBean;
    }


    /**
     * 前台参数xml解析
     * @param content
     * @return
     */
    public static SaleParamsBean getPosSaleParams(String content) {
        SaleParamsBean p = new SaleParamsBean();
        try {
            //①获得DOM解析器的工厂示例:
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            //②从Dom工厂中获得dom解析器
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            //③把要解析的xml文件读入Dom解析器
//            content = URLDecoder.decode(content, "UTF-8");
            Document doc = dbBuilder.parse(new ByteArrayInputStream(content.getBytes("GBK")));
            System.out.println("处理该文档的DomImplemention对象=" + doc.getImplementation());
            //④得到文档中名称为person的元素的结点列表
            NodeList nList = doc.getElementsByTagName("PosSaleParam");
            //⑤遍历该集合,显示集合中的元素以及子元素的名字
            for (int i = 0; i < nList.getLength(); i++) {
                //先从Person元素开始解析
                Element personElement = (Element) nList.item(i);

                //获取person下的name和age的Note集合
                NodeList childNoList = personElement.getChildNodes();
                for (int j = 0; j < childNoList.getLength(); j++) {
                    Node childNode = childNoList.item(j);
                    //判断子note类型是否为元素Note
                    if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNode;
                        String nodeValue;
                        if (childElement.getFirstChild() == null) {
                            nodeValue = "";
                        } else {
                            nodeValue = String.valueOf(childElement.getFirstChild().getNodeValue()) == null ? "" : String.valueOf(childElement.getFirstChild().getNodeValue());
                        }
                        if ("CanSaleZeroPriceItem".equals(childElement.getNodeName())) {
                            if ("1".equals(nodeValue)) {
                                p.setCanSaleZeroPriceItem(true);
                            } else {
                                p.setCanSaleZeroPriceItem(false);
                            }
                        } else if ("CanChangeAmountForWeightItem".equals(childElement.getNodeName())) {
                            if ("0".equals(nodeValue)) {
                                p.setCanChangeAmountForWeightItem(false);
                            } else {
                                p.setCanChangeAmountForWeightItem(true);
                            }
                        } else if ("IntegerOnlyForNormalItem".equals(childElement.getNodeName())) {
                            if ("1".equals(nodeValue)) {
                                p.setIntegerOnlyForNormalItem(true);
                            } else {
                                p.setIntegerOnlyForNormalItem(false);
                            }
                        } else if ("RoundType".equals(childElement.getNodeName())) {
                            p.setRoundType(nodeValue);
                        } else if ("RoundTo".equals(childElement.getNodeName())) {
                            p.setRoundTo(nodeValue);
                        } else if ("PrefixOfBarCode".equals(childElement.getNodeName())) {
                            p.setPrefixOfBarCode(nodeValue);
                        } else if ("EnableWeighItemRound".equals(childElement.getNodeName())) {
                            if ("1".equals(nodeValue)) {
                                p.setEnableWeighItemRound(true);
                            } else {
                                p.setEnableWeighItemRound(false);
                            }
                        } else if ("PointSpecialItemActive".equals(childElement.getNodeName())) {
                            if ("0".equals(nodeValue)) {
                                p.setPointSpecialItemActive(false);
                            } else {
                                p.setPointSpecialItemActive(true);
                            }
                        } else if ("PointBirthdayActive".equals(childElement.getNodeName())) {
                            if ("0".equals(nodeValue)) {
                                p.setPointBirthdayActive(false);
                            } else {
                                p.setPointBirthdayActive(true);
                            }
                        } else if ("PointBirthdayProp".equals(childElement.getNodeName())) {
                            p.setPointBirthdayProp(Float.parseFloat(nodeValue));
                            if(p.getPointBirthdayProp() == 0)p.setPointBirthdayProp(1);
                        }else if("PointPolicyActive".equals(childElement.getNodeName())){
                            if("0".equals(nodeValue)){
                                p.setPointPolicyActive(false);
                            }else {
                                p.setPointPolicyActive(true);
                            }
                        }else if("EnableUserAuthorization".equals(childElement.getNodeName())){
                            if("0".equals(nodeValue)){
                                p.setEnableUserAuthorization(false);
                            }else {
                                p.setEnableUserAuthorization(true);
                            }
                        }else if("ReturnNeedOrderNo".equals(childElement.getNodeName())){
                            if("0".equals(nodeValue)){
                                p.setReturnNeedOrderNo(false);
                            }else {
                                p.setReturnNeedOrderNo(true);
                            }
                        }else if("NeedSaleman".equals(childElement.getNodeName())){
                            if("0".equals(nodeValue)){
                                p.setNeedSaleman(false);
                            }else {
                                p.setNeedSaleman(true);
                            }
                        }else if("OperatorAsDefaultSaleman".equals(childElement.getNodeName())){
                            if("0".equals(nodeValue)){
                                p.setOperatorAsDefaultSaleman(false);
                            }else {
                                p.setOperatorAsDefaultSaleman(true);
                            }
                        }else if("CanReturnPolicyItem".equals(childElement.getNodeName())){
                            if("0".equals(nodeValue)){
                                p.setCanReturnPolicyItem(false);
                            }else {
                                p.setCanReturnPolicyItem(true);
                            }
                        }else if("EnableCashRound".equals(childElement.getNodeName())){
                            if("0".equals(nodeValue)){
                                p.setEnableCashRound(false);
                            }else {
                                p.setEnableCashRound(true);
                            }
                        }else if ("ShiftEndNeedInputActualCash".equals(childElement.getNodeName())){
                            if("0".equals(nodeValue)){
                                p.setShiftEndNeedInputActualCash(false);
                            }else {
                                p.setShiftEndNeedInputActualCash(true);
                            }
                        }else if ("ChangePriceItemShowDialog".equals(childElement.getNodeName())){
                            if("0".equals(nodeValue)){
                                p.setChangePriceItemShowDialog(false);
                            }else {
                                p.setChangePriceItemShowDialog(true);
                            }
                        }else if ("CancelItemNeedNote".equals(childElement.getNodeName())){
                            if("0".equals(nodeValue)){
                                p.setCancelItemNeedNote(false);
                            }else {
                                p.setCancelItemNeedNote(true);
                            }
                        }else if ("CashPaymentNeedDiscount".equals(childElement.getNodeName())){
                            if("0".equals(nodeValue)){
                                p.setCashPaymentNeedDiscount(false);
                            }else {
                                p.setCashPaymentNeedDiscount(true);
                            }
                        }else if ("ChooseWeightOutBar".equals(childElement.getNodeName())){
                            if("0".equals(nodeValue)){
                                p.setChooseWeightOutBar(false);
                            }else {
                                p.setChooseWeightOutBar(true);
                            }
                        }else if ("ChooseBarType".equals(childElement.getNodeName())){
                                p.setChooseBarType(Integer.parseInt(nodeValue));
                        }else if ("PrefixOfWeightItem".equals(childElement.getNodeName())){
                            p.setPrefixOfWeightItem(nodeValue);
                        }else if ("PrecisionOfWeight".equals(childElement.getNodeName())){
                            p.setPrecisionOfWeight(nodeValue);
                        }else if ("PrecisionOfMoney".equals(childElement.getNodeName())){
                            p.setPrecisionOfMoney(nodeValue);
                        }else if ("LengthOfWeight".equals(childElement.getNodeName())){
                            p.setLengthOfWeight(nodeValue);
                        }else if ("LengthOfMoney".equals(childElement.getNodeName())){
                            p.setLengthOfMoney(nodeValue);
                        }else if ("POSPrintExtraOrderNo".equals(childElement.getNodeName())){
                            if("0".equals(nodeValue)){
                                p.setPosPrintExtraOrderNo(false);
                            }else {
                                p.setPosPrintExtraOrderNo(true);
                            }
                        }else if ("EnableCardOrPosClient".equals(childElement.getNodeName())){
                            if("0".equals(nodeValue)){
                                p.setEnableCardOrPosClient(false);
                            }else {
                                p.setEnableCardOrPosClient(true);
                            }
                        }else if("PrintMergePosItem".equals(childElement.getNodeName())){
                            if("0".equals(nodeValue)){
                                p.setPrintMergePosItem(false);
                            }else {
                                p.setPrintMergePosItem(true);
                            }
                        }else if ("HangOrderNeedSaleman".equals(childElement.getNodeName())){
                            if ("0".equals(nodeValue)) {
                                p.setHangOrderNeedSaleman(false);
                            } else {
                                p.setHangOrderNeedSaleman(true);
                            }
                        }else if ("AllowTicketSelect".equals(childElement.getNodeName())){
                            if ("0".equals(nodeValue)) {
                                p.setAllowTicketSelect(false);
                            } else {
                                p.setAllowTicketSelect(true);
                            }
                        }else if ("EnableBranchCloseTime".equals(childElement.getNodeName())){
                            if ("0".equals(nodeValue)) {
                                p.setEnableBranchCloseTime(false);
                            } else {
                                p.setEnableBranchCloseTime(true);
                            }
                        }else if ("EnableCardPayDiscount".equals(childElement.getNodeName())){
                            if ("0".equals(nodeValue)) {
                                p.setEnableCardPayDiscount(false);
                            } else {
                                p.setEnableCardPayDiscount(true);
                            }
                        }else if ("DisableCancelDepositWithCoupon".equals(childElement.getNodeName())){//disable_cancel_deposit_with_coupon
                            if ("0".equals(nodeValue)) {
                                p.setDisable_cancel_deposit_with_coupon(false);
                            } else {
                                p.setDisable_cancel_deposit_with_coupon(true);
                            }
                        }else if ("ShowDiscount".equals(childElement.getNodeName())){
                            if ("0".equals(nodeValue)) {
                                p.setShowDiscount(false);
                            } else {
                                p.setShowDiscount(true);
                            }
                        }else if ("ShowDiscountCardType".equals(childElement.getNodeName())){
                            p.setShowDiscountCardType(Integer.valueOf(nodeValue));
                        }else if ("ShowDiscountLevel".equals(childElement.getNodeName())){
                            p.setShowDiscountLevel(Long.valueOf(nodeValue));
                        }else if ("ShowDiscountType".equals(childElement.getNodeName())){
                            p.setShowDiscountType(Integer.valueOf(nodeValue));
                        }else if ("EnableDiscountShare".equals(childElement.getNodeName())){
                            if ("0".equals(nodeValue)) {
                                p.setEnableDiscountShare(false);
                            } else {
                                p.setEnableDiscountShare(true);
                            }
                        }else if ("FirstDeliverCardNoPay".equals(childElement.getNodeName())){
                            if ("0".equals(nodeValue)) {
                                p.setFirstDeliverCardNoPay(false);
                            } else {
                                p.setFirstDeliverCardNoPay(true);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

}
