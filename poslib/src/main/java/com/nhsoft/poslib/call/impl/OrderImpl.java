package com.nhsoft.poslib.call.impl;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.AmountPay;
import com.nhsoft.poslib.entity.CardChange;
import com.nhsoft.poslib.entity.CardDeposit;
import com.nhsoft.poslib.entity.CustomerRegister;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.PosItemGrade;
import com.nhsoft.poslib.entity.PosItemKit;
import com.nhsoft.poslib.entity.RelatCard;
import com.nhsoft.poslib.entity.ReplaceCard;
import com.nhsoft.poslib.entity.VipStrangeSuccessSendMoney;
import com.nhsoft.poslib.entity.order.Payment;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.entity.order.PosOrderKitDetail;
import com.nhsoft.poslib.entity.shift.PrintShiftTable;
import com.nhsoft.poslib.entity.shift.ShiftTable;
import com.nhsoft.poslib.libconfig.LibConfig;
import com.nhsoft.poslib.model.VipCardConfig;
import com.nhsoft.poslib.model.VipUserInfo;
import com.nhsoft.poslib.service.greendao.CardChangeDao;
import com.nhsoft.poslib.service.greendao.CardDepositDao;
import com.nhsoft.poslib.service.greendao.CustomerRegisterDao;
import com.nhsoft.poslib.service.greendao.PaymentDao;
import com.nhsoft.poslib.service.greendao.PosOrderDao;
import com.nhsoft.poslib.service.greendao.PosOrderDetailDao;
import com.nhsoft.poslib.service.greendao.PosOrderKitDetailDao;
import com.nhsoft.poslib.service.greendao.RelatCardDao;
import com.nhsoft.poslib.service.greendao.ReplaceCardDao;
import com.nhsoft.poslib.service.greendao.VipSendCardDao;
import com.nhsoft.poslib.service.greendao.VipStrangeSuccessSendMoneyDao;
import com.nhsoft.poslib.utils.EvtLog;
import com.nhsoft.poslib.utils.MatterUtils;
import com.nhsoft.poslib.utils.NumberUtil;
import com.nhsoft.poslib.utils.PosOrderStateUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Iverson on 2018/11/23 11:03 AM
 * 此类用于：
 */
public class OrderImpl {

    private static OrderImpl instance;

    public static OrderImpl getInstance() {
        if (instance == null) {
            instance = new OrderImpl();
        }
        return instance;
    }

    /**
     * 订单生成存入订单
     */
    public boolean savePosOrder(final PosOrder posOrder) {
        final PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        final PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        final PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        final PosOrderKitDetailDao posOrderKitDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderKitDetailDao();

        return MatterUtils.doMatter(posOrderDao, new Runnable() {
            @Override
            public void run() {
                posOrderDao.deleteByKey(posOrder.getOrderNo());
                posOrderDao.insertOrReplaceInTx(posOrder);
                posOrderDao.detachAll();
                deletePayment(posOrder.getOrderNo(), paymentDao);
                List<Payment> payments = posOrder.getPayments();
                if (payments != null && payments.size() > 0) {
                    paymentDao.insertOrReplaceInTx(payments);
                    paymentDao.detachAll();
                }
                deletePosOrderDetail(posOrder.getOrderNo(), posOrderDetailDao, posOrderKitDetailDao);
                List<PosOrderDetail> posOrderDetails = posOrder.getPosOrderDetails();
                for (PosOrderDetail posOrderDetail : posOrderDetails) {
                    if (posOrderDetail.getPosOrderKitDetails() != null && posOrderDetail.getPosOrderKitDetails().size() > 0) {
                        posOrderKitDetailDao.insertOrReplaceInTx(posOrderDetail.getPosOrderKitDetails());
                        posOrderKitDetailDao.detachAll();
                    }
                    posOrderDetailDao.insertOrReplaceInTx(posOrderDetail);
                    posOrderDetailDao.detachAll();
                }
            }
        });
    }

    public void updateOrderMemo(PosOrder posOrder) {
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrder loadOrder = mPosOrderDao.load(posOrder.getOrderNo());
        if (loadOrder != null) {
            String orderMemo = loadOrder.getOrderMemo();
            if (orderMemo == null) {
                orderMemo = "";
            }
            loadOrder.setOrderMemo(orderMemo + "由收银员重新确认为支付成功");
            mPosOrderDao.insertOrReplace(loadOrder);
        }

    }

    public boolean insertPosOrderDetail(final List<PosOrderDetail> posOrderDetails) {
        final PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        return MatterUtils.doMatter(posOrderDetailDao, new Runnable() {
            @Override
            public void run() {
                posOrderDetailDao.insertOrReplaceInTx(posOrderDetails);
            }
        });
    }

    public void checkAgainOrderStatus(String orderNo) {
        if (TextUtils.isEmpty(orderNo)) return;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrder posOrder = mPosOrderDao.load(orderNo);
        final PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        final PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();

        List<PosOrderDetail> posOrderDetails = posOrderDetailDao._queryPosOrder_PosOrderDetails(posOrder.getOrderNo());
        List<Payment> payments = paymentDao._queryPosOrder_Payments(posOrder.getOrderNo());
        posOrder.setPosOrderDetails(posOrderDetails);
        posOrder.setPayments(payments);

        boolean isPaymentOk = true;
        for (Payment payment : payments) {
            if (LibConfig.C_PAYMENT_TYPE_ONLINE.equals(payment.getPaymentPayBy())) {
                isPaymentOk = false;
                break;
            } else if (LibConfig.C_PAYMENT_TYPE_PETCARD_NAME.equals(payment.getPaymentPayBy())) {
                if (payment.getPaymentBalance() != 0) {
                    isPaymentOk = false;
                    break;
                }
            }
        }

        List<PosOrderDetail> couponsDetailList = getCouponsDetailList(orderNo);
        Log.e("测试check", "3");
        if (isPaymentOk && (couponsDetailList == null || couponsDetailList.size() == 0)) {
            updatePosOrderStatus(posOrder);
            Log.e("测试check", "4");
        }

        mPosOrderDao.detachAll();
        paymentDao.detachAll();
        posOrderDetailDao.detachAll();
    }

    /**
     * 先删除之前存入的支付payment
     *
     * @param orderNo
     * @param paymentDao
     * @return
     */
    public boolean deletePayment(final String orderNo, final PaymentDao paymentDao) {
        return MatterUtils.doMatter(paymentDao, new Runnable() {
            @Override
            public void run() {
                List<Payment> payments = paymentDao._queryPosOrder_Payments(orderNo);
                paymentDao.deleteInTx(payments);
            }
        });
    }

    /**
     * 先删除之前存入的posOrderDetail
     *
     * @param orderNo
     * @param posOrderDetailDao
     * @return
     */
    public boolean deletePosOrderDetail(final String orderNo, final PosOrderDetailDao posOrderDetailDao, final PosOrderKitDetailDao orderKitDetailDao) {
        return MatterUtils.doMatter(posOrderDetailDao, new Runnable() {
            @Override
            public void run() {
                List<PosOrderDetail> posOrderDetails = posOrderDetailDao._queryPosOrder_PosOrderDetails(orderNo);
                List<PosOrderKitDetail> posOrderKitDetails = orderKitDetailDao.queryBuilder().where(PosOrderKitDetailDao.Properties.OrderNo.eq(orderNo)).build().list();
                posOrderDetailDao.deleteInTx(posOrderDetails);
                orderKitDetailDao.deleteInTx(posOrderKitDetails);
            }
        });
    }

    /**
     * @param systemBookCode
     * @param branchNum
     * @param orderNum
     * @return
     */
    public List<Payment> getPayment(String systemBookCode, int branchNum, String orderNum) {
        PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        try {
            return paymentDao.queryBuilder().where(
                    PaymentDao.Properties.SystemBookCode.eq(systemBookCode)
                    , PaymentDao.Properties.BranchNum.eq(branchNum)
                    , PaymentDao.Properties.OrderNo.eq(orderNum)
            ).list();
        } catch (Exception e) {
            EvtLog.d("getPayment:=" + systemBookCode + " :=" + branchNum + " :=" + orderNum + " :=" + e.toString());
        }
        return null;

    }

    public List<AmountPay> getPayType(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, String orderNo) {
        PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        List<AmountPay> amountPays = new ArrayList<>();

        // 销售单
        try {//and  PAYMENT_MONEY >0
            String sql = "select PAYMENT_PAY_BY,sum(PAYMENT_MONEY) from PAYMENT where SYSTEM_BOOK_CODE = ? " +
                    "and BRANCH_NUM= ?   and  SHIFT_TABLE_NUM=? and SHIFT_TABLE_BIZDAY=?  and ORDER_NO=? group by PAYMENT_PAY_BY";
            String[] strings = {systemBookCode, String.valueOf(branchNum), String.valueOf(shiftTableNum), shiftTableBizday, orderNo};
            Cursor cursor = paymentDao.getDatabase().rawQuery(sql, strings);
            while (cursor.moveToNext()) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(cursor.getString(0));
                amountPay.setAmountMoney(Float.parseFloat(cursor.getString(1)));
                amountPays.add(amountPay);
            }
        } catch (Exception e) {
            EvtLog.d("getListPaymentByPayType:=" + e.toString());
        }
        return amountPays;
    }


    /**
     * @param systemBookCode
     * @param branchNum
     * @return
     */
    public List<Payment> getAllPayment(String systemBookCode, int branchNum) {
        PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        return paymentDao.queryBuilder().where(
                PaymentDao.Properties.SystemBookCode.eq(systemBookCode)
                , PaymentDao.Properties.BranchNum.eq(branchNum)
        ).list();
    }

    /**
     * 获取最新的 n条订单
     *
     * @param systemBookCode
     * @param branchNum
     * @return
     */
    public List<PosOrder> getPosOrderList(String systemBookCode, int branchNum) {
        int page = 10;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> getPosOrderList = mPosOrderDao.queryBuilder()
                .where(PosOrderDao.Properties.SystemBookCode.eq(systemBookCode)//根据账套号
                        , PosOrderDao.Properties.BranchNum.eq(branchNum)
                        , PosOrderDao.Properties.OrderStateCode.eq(5)
                )
                .limit(page)
                .offset(0)
                .orderDesc(PosOrderDao.Properties.OrderTime)
                .list();
        mPosOrderDao.detachAll();
        return getPosOrderList;
    }

    /**
     * 获取该班次号下所有 posOrder
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @param orderUploadState
     * @return
     */
    public List<PosOrder> getPosOrderList(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, boolean orderUploadState){
        PosOrderDao posOrderDao=DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList = posOrderDao.queryBuilder()
                .where(
                        PosOrderDao.Properties.OrderUploadState.eq(orderUploadState),
                        PosOrderDao.Properties.SystemBookCode.eq(systemBookCode)
//                        , PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday)
//                        , PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum)
                        , PosOrderDao.Properties.BranchNum.eq(branchNum)
                        , PosOrderDao.Properties.OrderStateCode.eq(5))

                .orderAsc(PosOrderDao.Properties.OrderNo)
                .list();
        if(posOrderList == null){
            posOrderList = new ArrayList<>();
        }
        return posOrderList;
    }


    /**
     * 获取最新的 n条订单
     *
     * @param systemBookCode
     * @param branchNum
     * @return
     */
    public List<PosOrder> getAbNormalPosOrderList(String systemBookCode, int branchNum, int shift_table_num) {

        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        final PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        final PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();

        List<PosOrder> posOrders = mPosOrderDao.queryBuilder()
                .where(PosOrderDao.Properties.OrderStateCode.eq(LibConfig.S_ORDER_INIT + LibConfig.S_ORDER_CANCEL)
                        , PosOrderDao.Properties.SystemBookCode.eq(systemBookCode)//根据账套号
                        , PosOrderDao.Properties.BranchNum.eq(branchNum)
                        , PosOrderDao.Properties.ShiftTableNum.eq(shift_table_num))
                .orderDesc(PosOrderDao.Properties.OrderTime)
                .list();
        for (PosOrder posOrder : posOrders) {
            List<PosOrderDetail> posOrderDetails = posOrderDetailDao._queryPosOrder_PosOrderDetails(posOrder.getOrderNo());
            List<Payment> payments = paymentDao._queryPosOrder_Payments(posOrder.getOrderNo());
            for (PosOrderDetail detail : posOrderDetails) {
                PosItem posItem = PosItemImpl.getInstance().getPosItemByKey(detail.getItemNum());
                detail.setPosItem(posItem);
            }
            posOrder.setPosOrderDetails(posOrderDetails);
            posOrder.setPayments(payments);

        }
        mPosOrderDao.detachAll();
        paymentDao.detachAll();
        posOrderDetailDao.detachAll();

        return posOrders;
    }


    /**
     * 更新某个支付
     *
     * @param payment
     */
    public void updatePaymentBalanceStatus(Payment payment) {
        final PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        Payment loadPayment = paymentDao.load(payment.getPaymentNo());
        if (loadPayment != null) {
            payment.setPaymentCardBalance(payment.getPaymentBalance());
            paymentDao.insertOrReplace(payment);
        }
    }

    /**
     * 单边账处理完成修改posorder的状态
     *
     * @param posOrder
     */
    public void updatePosOrderStatus(PosOrder posOrder) {
        PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrder load = posOrderDao.load(posOrder.getOrderNo());
        if (load != null) {
            PosOrderStateUtil.setPosOrderByComplete(load);
            posOrderDao.update(load);
        }
    }

    /**
     * 单边账处理完成修改posorder的状态
     *
     * @param posOrder
     */
    public void updatePosOrderPayInfo(PosOrder posOrder) {
        PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrder load = posOrderDao.load(posOrder.getOrderNo());
        if (load != null) {
            load.setOpenId(posOrder.getOpenId());
            load.setCustomerId(posOrder.getCustomerId());
            load.setOrderPrintedNum(posOrder.getOrderPrintedNum());
            load.setAlipayUserId(posOrder.getAlipayUserId());
            posOrderDao.update(load);
        }
    }

    /**
     * 单边账处理完成修改posorder的状态
     *
     * @param posOrder
     */
    public void updatePosOrderPrintNum(PosOrder posOrder) {
        PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrder load = posOrderDao.load(posOrder.getOrderNo());
        if (load != null) {
            load.setOrderPrintedNum(posOrder.getOrderPrintedNum());
            posOrderDao.update(load);
        }
    }

    public void updatePosOrderDetailStatus(PosOrderDetail posOrderDetail) {
        PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        PosOrderDetail load = posOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderDetailTicketUuid.eq(posOrderDetail.getOrderDetailTicketUuid())).build().unique();
        if (load != null) {
            load.setPaymentBalance(0);
            posOrderDetailDao.update(load);
        }
    }

    public boolean isHaveCoupons(PosOrder posOrder) {
        if (posOrder == null || TextUtils.isEmpty(posOrder.getOrderNo())) return false;
        PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        List<PosOrderDetail> load = posOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderDetailType.eq(LibConfig.C_ORDER_DETAIL_TYPE_COUPON),
                PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).build().list();

        if (load != null && load.size() > 0) {
            return true;
        }

        return false;
    }


    /**
     * 根据订单号获取 订单内所有商品信息
     *
     * @param orderNo
     * @param systemBookCode
     * @param branchNum
     * @return
     */
    public List<PosOrderDetail> getPosOrderDetailList(String systemBookCode, int branchNum, String orderNo) {
        PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        List<PosOrderDetail> list = posOrderDetailDao.queryBuilder()
                .where(
                        PosOrderDetailDao.Properties.OrderNo.eq(orderNo)
                        , PosOrderDetailDao.Properties.SystemBookCode.eq(systemBookCode)//根据账套号
                        , PosOrderDetailDao.Properties.BranchNum.eq(branchNum)).list();
        posOrderDetailDao.detachAll();
        List<PosOrderDetail> posOrderDetails = new ArrayList<>();
        for (PosOrderDetail posOrderDetail : list) {
            if (!posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_COUPON)) {
                posOrderDetails.add(posOrderDetail);
            }
        }
        return posOrderDetails;
    }


    public List<PosOrderDetail> getPosOrderDetailList(String orderNo) {
        PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        List<PosOrderDetail> list = posOrderDetailDao.queryBuilder()
                .where(
                        PosOrderDetailDao.Properties.OrderNo.eq(orderNo),
                        PosOrderDetailDao.Properties.OrderDetailType.notEq(LibConfig.C_ORDER_DETAIL_TYPE_COUPON)
                ).list();
//        posOrderDetailDao.detachAll();
//        List<PosOrderDetail> posOrderDetails = new ArrayList<>();
//        for (PosOrderDetail posOrderDetail : list) {
//            if (!posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_COUPON)) {
//                posOrderDetails.add(posOrderDetail);
//            }
//        }
        return list;
    }

    /**
     * 订单内是否使用了 消费券
     *
     * @param orderNo
     * @return
     */
    public boolean isContinesCoupons(String orderNo) {
        PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        List<PosOrderDetail> list = posOrderDetailDao.queryBuilder()
                .where(PosOrderDetailDao.Properties.OrderNo.eq(orderNo)).list();
        for (PosOrderDetail posOrderDetail : list) {
            if (posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_COUPON)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 根据订单号获取 订单内所有商品信息
     *
     * @param orderNo
     * @param systemBookCode
     * @param branchNum
     * @return
     */
    public List<PosOrderDetail> getAllPosOrderDetailList(String systemBookCode, int branchNum, String orderNo) {
        PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        List<PosOrderDetail> list = posOrderDetailDao.queryBuilder()
                .where(
                        PosOrderDetailDao.Properties.OrderNo.eq(orderNo)
                        , PosOrderDetailDao.Properties.SystemBookCode.eq(systemBookCode)//根据账套号
                        , PosOrderDetailDao.Properties.BranchNum.eq(branchNum)).list();
        posOrderDetailDao.detachAll();
        return list;
    }


    /**
     * 根据订单号获取 订单内所有商品信息
     *
     * @param orderNo
     * @param systemBookCode
     * @param branchNum
     * @return
     */
    public List<PosOrderKitDetail> getPosOrderKitDetailList(String systemBookCode, int branchNum, String orderNo) {
        PosOrderKitDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderKitDetailDao();
        return posOrderDetailDao.queryBuilder()
                .where(
                        PosOrderKitDetailDao.Properties.OrderNo.eq(orderNo)
                        , PosOrderKitDetailDao.Properties.SystemBookCode.eq(systemBookCode)//根据账套号
                        , PosOrderKitDetailDao.Properties.BranchNum.eq(branchNum)).list();
    }


    /**
     * 判断该订单号对应的订单是不是 已退货
     *
     * @param Order
     * @return
     */
    public boolean getIsExitedGood(PosOrder Order) {
        if (Order == null) {
            return false;
        }
        if (Order.getOrderPaymentMoney() < 0) {
            return true;
        }
        PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList = posOrderDao.loadAll();
        for (PosOrder posOrder : posOrderList) {
            if (posOrder.getOrderNo().equals(Order.getOrderRefBillno())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断是不是已退货的原单
     *
     * @param Order
     * @return
     */
    public boolean getIsExitSourceOrder(PosOrder Order) {
        if (Order == null) {
            return false;
        }

        PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList = posOrderDao.loadAll();
        for (PosOrder posOrder : posOrderList) {
            if (TextUtils.isEmpty(posOrder.getOrderRefBillno())) continue;
            if (posOrder.getOrderRefBillno().equals(Order.getOrderNo())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断该订单号对应的订单是不是 已退货
     *
     * @param Order
     * @return
     */
    public PosOrder getExitSourceOrder(PosOrder Order) {
        PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList = posOrderDao.loadAll();
        for (PosOrder posOrder : posOrderList) {
            if (TextUtils.isEmpty(posOrder.getOrderRefBillno())) continue;
            if (posOrder.getOrderRefBillno().equals(Order.getOrderNo())) {
                return posOrder;
            }
        }
        return null;
    }


    /**
     * 根据订单号获取查找订单
     *
     * @param systemBookCode
     * @param branchNum
     * @param orderNo
     * @param startTime
     * @param endTime
     * @param orderType
     * @param payType
     * @param appUserName
     * @param discountType
     * @return
     */
    public List<PosOrder> getPosOrder(int page, String systemBookCode, int branchNum, String orderNo, String startTime,
                                      String endTime, String orderType, String payType, String appUserName, String discountType) {
        if ("全部".equals(startTime)) {
            startTime = "";
        }
        if ("全部".equals(orderType)) {
            orderType = "";
        }
        if ("全部".equals(payType)) {
            payType = "";
        }
        if ("全部".equals(appUserName)) {
            appUserName = "";
        }
        if ("全部".equals(discountType)) {
            discountType = "";
        }

        String fromIndex = String.valueOf(page * 10);//跳过xx条
        List<String> strings = null;
        List<PosOrder> posOrder = null;
        if (!TextUtils.isEmpty(orderNo)) {//订单号

            if (!TextUtils.isEmpty(startTime)) {//时间

                if (!TextUtils.isEmpty(orderType)) {//订单类型

                    if (!TextUtils.isEmpty(payType)) {//支付类型

                        if (!TextUtils.isEmpty(appUserName)) {//收银员
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), orderNo, startTime, endTime, payType, appUserName, fromIndex);
                        } else {
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), orderNo, startTime, endTime, payType, fromIndex);
                        }
                    } else {

                        if (!TextUtils.isEmpty(appUserName)) {//收银员
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), orderNo, startTime, endTime, appUserName, fromIndex);
                        } else {
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), orderNo, startTime, endTime, fromIndex);
                        }
                    }

                } else {
//订单类型

                    if (!TextUtils.isEmpty(payType)) {//支付类型

                        if (!TextUtils.isEmpty(appUserName)) {//收银员
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), orderNo, startTime, endTime, payType, appUserName, fromIndex);
                        } else {
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), orderNo, startTime, endTime, payType, fromIndex);
                        }
                    } else {

                        if (!TextUtils.isEmpty(appUserName)) {//收银员
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), orderNo, startTime, endTime, appUserName, fromIndex);
                        } else {
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), orderNo, startTime, endTime, fromIndex);
                        }
                    }
                }

            } else {//时间

                if (!TextUtils.isEmpty(orderType)) {//订单类型

                    if (!TextUtils.isEmpty(payType)) {//支付类型

                        if (!TextUtils.isEmpty(appUserName)) {//收银员

                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), orderNo, payType, appUserName, fromIndex);
                        } else {
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), orderNo, payType, fromIndex);
                        }
                    } else {

                        if (!TextUtils.isEmpty(appUserName)) {//收银员
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), orderNo, appUserName, fromIndex);
                        } else {
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), orderNo, fromIndex);
                        }
                    }

                } else {
//订单类型

                    if (!TextUtils.isEmpty(payType)) {//支付类型

                        if (!TextUtils.isEmpty(appUserName)) {//收银员
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), orderNo, payType, appUserName, fromIndex);
                        } else {
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), orderNo, payType, fromIndex);
                        }
                    } else {

                        if (!TextUtils.isEmpty(appUserName)) {//收银员
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), orderNo, appUserName, fromIndex);
                        } else {
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), orderNo, fromIndex);
                        }
                    }
                }

            }

        } else {
//订单号

            if (!TextUtils.isEmpty(startTime)) {//时间

                if (!TextUtils.isEmpty(orderType)) {//订单类型

                    if (!TextUtils.isEmpty(payType)) {//支付类型

                        if (!TextUtils.isEmpty(appUserName)) {//收银员
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), startTime, endTime, payType, appUserName, fromIndex);
                        } else {
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), startTime, endTime, payType, fromIndex);
                        }
                    } else {

                        if (!TextUtils.isEmpty(appUserName)) {//收银员
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), startTime, endTime, appUserName, fromIndex);
                        } else {
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), startTime, endTime, fromIndex);
                        }
                    }

                } else {
//订单类型
                    if (!TextUtils.isEmpty(payType)) {//支付类型

                        if (!TextUtils.isEmpty(appUserName)) {//收银员
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), startTime, endTime, payType, appUserName, fromIndex);
                        } else {
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), startTime, endTime, payType, fromIndex);
                        }
                    } else {

                        if (!TextUtils.isEmpty(appUserName)) {//收银员
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), startTime, endTime, appUserName, fromIndex);
                        } else {
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), startTime, endTime, fromIndex);
                        }
                    }
                }

            } else {//时间

                if (!TextUtils.isEmpty(orderType)) {//订单类型

                    if (!TextUtils.isEmpty(payType)) {//支付类型

                        if (!TextUtils.isEmpty(appUserName)) {//收银员
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), payType, appUserName, fromIndex);
                        } else {
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), payType, fromIndex);
                        }
                    } else {

                        if (!TextUtils.isEmpty(appUserName)) {//收银员
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), appUserName, fromIndex);
                        } else {
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), fromIndex);
                        }
                    }

                } else {
//订单类型

                    if (!TextUtils.isEmpty(payType)) {//支付类型

                        if (!TextUtils.isEmpty(appUserName)) {//收银员
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), payType, appUserName, fromIndex);
                        } else {
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), payType, fromIndex);
                        }
                    } else {

                        if (!TextUtils.isEmpty(appUserName)) {//收银员
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), appUserName, fromIndex);
                        } else {
                            strings = Arrays.asList(systemBookCode, String.valueOf(branchNum), fromIndex);
                        }
                    }
                }

            }
        }
        PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();

//        String sql ="select * from POS_ORDER where SYSTEM_BOOK_CODE = ? and BRANCH_NUM = ?   ";
        String sql = " where SYSTEM_BOOK_CODE = ? and BRANCH_NUM = ?   ";
        if (!TextUtils.isEmpty(orderNo)) {
            sql = sql + " and ORDER_NO  LIKE ?";
        }
        if (!TextUtils.isEmpty(startTime)) {
            sql = sql + " and SHIFT_TABLE_BIZDAY BETWEEN ? AND ?";
        }
        if (!TextUtils.isEmpty(orderType)) {
            if (orderType.equals("退货单")) {
                sql = sql + " and ORDER_PAYMENT_MONEY < 0";
            } else {
                sql = sql + " and ORDER_PAYMENT_MONEY >= 0";
            }

        }
        if (!TextUtils.isEmpty(payType)) {
            sql = sql + " and ORDER_NO IN (SELECT ORDER_NO FROM PAYMENT WHERE PAYMENT_PAY_BY = ? )";
        }
        if (!TextUtils.isEmpty(appUserName)) {
            sql = sql + " and ORDER_OPERATOR = ?";
        }

        if (!TextUtils.isEmpty(discountType)) {
            if (discountType.equals("促销")) {
                sql = sql + " and abs(order_promotion_discount_money) > 0.001";
            } else if (discountType.equals("会员")) {
                sql = sql + " and order_card_user_num > 0";
            } else if (discountType.equals("消费券")) {
                sql = sql + " and abs(order_coupon_total_money) > 0.001";
            } else if (discountType.equals("经理折扣")) {
                sql = sql + " and abs(order_mgr_discount_money) > 0.001";
            }
        }


        sql = sql + " and ORDER_STATE_CODE =5 ORDER BY ORDER_NO DESC limit 10 offset ? ";//跳过?条记录选出10条
        try {
            posOrder = posOrderDao.queryRawCreateListArgs(sql, Collections.<Object>unmodifiableList(strings)).list();
        } catch (Exception e) {
            EvtLog.e("cursor:=" + e);
        }

        return posOrder;
    }

    /**
     * 根据手机号查询订单
     *
     * @param phone
     * @param systemBookCode
     * @param branchNum
     * @return
     */
    public List<PosOrder> getPosOrderList(String systemBookCode, int branchNum, String phone) {

        PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        return posOrderDao.queryBuilder()
                .where(PosOrderDao.Properties.OrderCardPhone.eq(phone)
                        , PosOrderDao.Properties.SystemBookCode.eq(systemBookCode)//根据账套号
                        , PosOrderDao.Properties.BranchNum.eq(branchNum))
                .orderDesc(PosOrderDao.Properties.OrderTime)
                .list();
    }


    /**
     * 获取挂单的所有数据
     *
     * @return
     */
    public List<PosOrder> getInitPosOrder(String systemBookCode, long branchNum, String operatorName) {

        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        final PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        final PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();

        List<PosOrder> posOrders = mPosOrderDao.queryBuilder()
                .where(PosOrderDao.Properties.OrderStateCode.eq(LibConfig.S_ORDER_INIT)
                        , PosOrderDao.Properties.SystemBookCode.eq(systemBookCode)//根据账套号
                        , PosOrderDao.Properties.OrderOperator.eq(operatorName)
                        , PosOrderDao.Properties.BranchNum.eq(branchNum))
                .orderDesc(PosOrderDao.Properties.OrderTime)
                .list();
        for (PosOrder posOrder : posOrders) {
            List<PosOrderDetail> posOrderDetails = posOrderDetailDao._queryPosOrder_PosOrderDetails(posOrder.getOrderNo());
            List<Payment> payments = paymentDao._queryPosOrder_Payments(posOrder.getOrderNo());
            for (PosOrderDetail detail : posOrderDetails) {
                PosItem posItem = PosItemImpl.getInstance().getPosItemByKey(detail.getItemNum());
                if (!TextUtils.isEmpty(detail.getKitAmountStr())) {
                    posItem.setPosItemKits((List<PosItemKit>) new Gson().fromJson(detail.getKitAmountStr(), new TypeToken<ArrayList<PosItemKit>>() {
                    }.getType()));
                }

                detail.setPosItem(posItem);
                if (detail.getItemGradeNum() != 0) {
                    PosItemGrade posItemGrade = PosItemImpl.getInstance().getPosItemGradeByKey(detail.getItemGradeNum(), detail.getItemNum());
                    detail.setPosItemGrade(posItemGrade);
                }
            }
            posOrder.setPosOrderDetails(posOrderDetails);
            posOrder.setPayments(payments);

        }
        mPosOrderDao.detachAll();
        paymentDao.detachAll();
        posOrderDetailDao.detachAll();

        return posOrders;
    }


    /**
     * 获取挂单的所有数据
     *
     * @return
     */
    public PosOrder getInitPosOrderByOrderNo(String orderNo) {
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        final PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        final PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();

        PosOrder posOrder = mPosOrderDao.queryBuilder()
                .where(PosOrderDao.Properties.OrderStateCode.eq(LibConfig.S_ORDER_INIT)
                        , PosOrderDao.Properties.OrderNo.eq(orderNo)).build().unique();

        if (posOrder == null) return null;
        List<PosOrderDetail> posOrderDetails = posOrderDetailDao._queryPosOrder_PosOrderDetails(posOrder.getOrderNo());
        List<Payment> payments = paymentDao._queryPosOrder_Payments(posOrder.getOrderNo());
        posOrder.setPosOrderDetails(posOrderDetails);
        posOrder.setPayments(payments);
        return posOrder;
    }

    /**
     * 删除某个挂单
     *
     * @param posOrder
     * @return
     */
    public boolean deleteOrder(final PosOrder posOrder) {
         RetailPosManager.getInstance().tryDeleteOrder(posOrder);
        final PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        final PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        final PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        final PosOrderKitDetailDao orderKitDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderKitDetailDao();
        return MatterUtils.doMatter(posOrderDao, new Runnable() {
            @Override
            public void run() {
                posOrderDao.deleteByKey(posOrder.getOrderNo());
                deletePayment(posOrder.getOrderNo(), paymentDao);
                deletePosOrderDetail(posOrder.getOrderNo(), posOrderDetailDao, orderKitDetailDao);
            }
        });
    }

//    /**
//     * 删除全部挂单
//     *
//     * @param posOrderList
//     * @return
//     */
//    public boolean deleteAllOrder(final List<PosOrder> posOrderList) {
//        PosCarryLogService.tryDeleteOrder(posOrderList);
//        final PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
//        final PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
//        final PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
//        final PosOrderKitDetailDao orderKitDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderKitDetailDao();
//        return MatterUtils.doMatter(posOrderDao, new Runnable() {
//            @Override
//            public void run() {
//                posOrderDao.deleteByKey(posOrder.getOrderNo());
//                deletePayment(posOrder.getOrderNo(), paymentDao);
//                deletePosOrderDetail(posOrder.getOrderNo(), posOrderDetailDao, orderKitDetailDao);
//            }
//        });
//    }


    /**
     * 删除某个挂单
     *
     * @param posOrder
     * @return
     */
    public boolean deleteMatchOrder(final PosOrder posOrder) {
        final PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        final PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        final PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        final PosOrderKitDetailDao orderKitDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderKitDetailDao();
        return MatterUtils.doMatter(posOrderDao, new Runnable() {
            @Override
            public void run() {
                posOrderDao.deleteByKey(posOrder.getOrderNo());
                deletePayment(posOrder.getOrderNo(), paymentDao);
                deletePosOrderDetail(posOrder.getOrderNo(), posOrderDetailDao, orderKitDetailDao);
            }
        });
    }

    public boolean deleteAllOrder() {
        final PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        final PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        final PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        final PosOrderKitDetailDao orderKitDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderKitDetailDao();
        boolean b = MatterUtils.doMatter(posOrderDao, new Runnable() {
            @Override
            public void run() {
                posOrderDao.deleteAll();

            }
        });

        boolean b1 = MatterUtils.doMatter(paymentDao, new Runnable() {
            @Override
            public void run() {
                paymentDao.deleteAll();
            }
        });
        boolean b2 = MatterUtils.doMatter(posOrderDetailDao, new Runnable() {
            @Override
            public void run() {
                posOrderDetailDao.deleteAll();
            }
        });
        boolean b3 = MatterUtils.doMatter(orderKitDetailDao, new Runnable() {
            @Override
            public void run() {
                orderKitDetailDao.deleteAll();
            }
        });
        return b && b1 && b2 && b3;
    }

    /**
     * 删除多少天前的order数据
     *
     * @param date
     */
    public void deleteOrderByNDayData(String date) {
        final PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> list = posOrderDao.queryBuilder().where(PosOrderDao.Properties.OrderOperateTime.le(date)).build().list();
        for (PosOrder posOrder : list) {
            deleteMatchOrder(posOrder);
        }
    }


    //根据posOrder 把该posOrder 下的PosOrderDetail和Payment 设置就去
    public PosOrder getCompletePosOrder(final PosOrder posOrder) {
        List<PosOrderDetail> posOrderDetails = getPosOrderDetailList(posOrder.getSystemBookCode(), posOrder.getBranchNum(), posOrder.getOrderNo());
        List<Payment> paymentList = getPayment(posOrder.getSystemBookCode(), posOrder.getBranchNum(), posOrder.getOrderNo());
        posOrder.setPosOrderDetails(posOrderDetails);
        posOrder.setPayments(paymentList);
        return posOrder;
    }


    public PosOrder getLastComplete(String systemBookCode, int branchNum) {
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrder posOrder = mPosOrderDao.queryBuilder()
                .where(PosOrderDao.Properties.OrderStateCode.eq(LibConfig.S_ORDER_INIT + LibConfig.S_ORDER_COMPLETE)
                        , PosOrderDao.Properties.SystemBookCode.eq(systemBookCode)//根据账套号
                        , PosOrderDao.Properties.BranchNum.eq(branchNum))
                .orderDesc(PosOrderDao.Properties.OrderTime)
                .limit(1)
                .unique();

        if (posOrder == null) return null;
        final PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        final PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        List<PosOrderDetail> posOrderDetails = posOrderDetailDao._queryPosOrder_PosOrderDetails(posOrder.getOrderNo());
        List<Payment> payments = paymentDao._queryPosOrder_Payments(posOrder.getOrderNo());
        posOrder.setPosOrderDetails(posOrderDetails);
        posOrder.setPayments(payments);
        return posOrder;
    }

    /**
     * 获取 本的班次的交易单数
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public int PosOrderNum(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, int orderStateCode) {
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        int amount = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode),
                PosOrderDao.Properties.OrderPaymentMoney.notEq(0)

        ).list().size();
        return amount;
    }

    /**
     * 获取 本的营业日的交易单数
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public int PosOrderNumBizday(String systemBookCode, int branchNum, String shiftTableBizday, int orderStateCode) {
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        int amount = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode),
                PosOrderDao.Properties.OrderPaymentMoney.ge(0),
                PosOrderDao.Properties.OrderRefBillno.isNull()

        ).list().size();
        return amount;
    }


    /**
     * 获取本班次销售金额
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getPosOrderAmount(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, int orderStateCode) {
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        float amount = 0;
        float amountDiscount = 0;
        String string = "select sum(ORDER_PAYMENT_MONEY) from POS_ORDER where SYSTEM_BOOK_CODE = ? " +
                "and BRANCH_NUM= ?   and  SHIFT_TABLE_NUM=? and SHIFT_TABLE_BIZDAY=? and ORDER_STATE_CODE=? and ORDER_REF_BILLNO is  null and ORDER_PAYMENT_MONEY >=0";//
        try {
            String[] strings = {systemBookCode, String.valueOf(branchNum), String.valueOf(shiftTableNum), shiftTableBizday, String.valueOf(orderStateCode)};
            Cursor cursor = mPosOrderDao.getDatabase().rawQuery(string, strings);
            if (cursor.moveToNext()) {
                amount = cursor.getFloat(0);
            }
        } catch (Exception e) {
            Log.d("getPosOrderAmount", "getPosOrderAmount: " + e.toString());
        }

        String string1 = "select sum(ORDER_MGR_DISCOUNT_MONEY) from POS_ORDER where SYSTEM_BOOK_CODE = ? " +
                "and BRANCH_NUM= ?   and  SHIFT_TABLE_NUM=? and SHIFT_TABLE_BIZDAY=? and ORDER_STATE_CODE=? and ORDER_REF_BILLNO is  null and ORDER_PAYMENT_MONEY >=0";//getOrderMgrDiscountMoney
        try {
            String[] strings = {systemBookCode, String.valueOf(branchNum), String.valueOf(shiftTableNum), shiftTableBizday, String.valueOf(orderStateCode)};
            Cursor cursor = mPosOrderDao.getDatabase().rawQuery(string1, strings);
            if (cursor.moveToNext()) {
                amountDiscount = cursor.getFloat(0);
            }
        } catch (Exception e) {
            Log.d("getPosOrderAmount", "ORDER_MGR_DISCOUNT_MONEY: " + e.toString());
        }
        amount = amount - amountDiscount;
        return amount;
    }

    /**
     * 获取本营业日销售金额
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getPosOrderAmountBizday(String systemBookCode, int branchNum, String shiftTableBizday, int orderStateCode) {
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        float amount = 0;
        float amountDiscount = 0;
        String string = "select sum(ORDER_PAYMENT_MONEY) from POS_ORDER where SYSTEM_BOOK_CODE = ? " +
                "and BRANCH_NUM= ?    and SHIFT_TABLE_BIZDAY=? and ORDER_STATE_CODE=? and ORDER_REF_BILLNO is  null and ORDER_PAYMENT_MONEY >=0";//
        try {
            String[] strings = {systemBookCode, String.valueOf(branchNum), shiftTableBizday, String.valueOf(orderStateCode)};
            Cursor cursor = mPosOrderDao.getDatabase().rawQuery(string, strings);
            if (cursor.moveToNext()) {
                amount = cursor.getFloat(0);
            }
        } catch (Exception e) {
            Log.d("getPosOrderAmount", "getPosOrderAmount: " + e.toString());
        }

        String string1 = "select sum(ORDER_MGR_DISCOUNT_MONEY) from POS_ORDER where SYSTEM_BOOK_CODE = ? " +
                "and BRANCH_NUM= ?   and SHIFT_TABLE_BIZDAY=? and ORDER_STATE_CODE=? and ORDER_REF_BILLNO is  null and ORDER_PAYMENT_MONEY >=0";//getOrderMgrDiscountMoney
        try {
            String[] strings = {systemBookCode, String.valueOf(branchNum), shiftTableBizday, String.valueOf(orderStateCode)};
            Cursor cursor = mPosOrderDao.getDatabase().rawQuery(string1, strings);
            if (cursor.moveToNext()) {
                amountDiscount = cursor.getFloat(0);
            }
        } catch (Exception e) {
            Log.d("getPosOrderAmount", "ORDER_MGR_DISCOUNT_MONEY: " + e.toString());
        }
        amount = amount - amountDiscount;
        return amount;
    }


    /**
     * 获取本班次收支合计
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getPosOrderAllAmount(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, int orderStateCode) {
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        float amount = 0;
        amount = amount + getCardFeeNum(systemBookCode, branchNum, shiftTableNum, shiftTableBizday);
        return amount;
    }

    /**
     * 获取本营业日收支合计
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getPosOrderAllAmountBizday(String systemBookCode, int branchNum, String shiftTableBizday, int orderStateCode) {
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        float amount = 0;
        amount = amount + getCardFeeNumBizday(systemBookCode, branchNum, shiftTableBizday);
        return amount;
    }

    /***
     * 获取该班次下 存款、换卡和续卡的费用总和
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @return
     */
    private float getCardFeeNum(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday) {
        float money = 0;
        CardDepositDao cardDepositDao = DaoManager.getInstance().getDaoSession().getCardDepositDao();
        List<CardDeposit> cardDepositList = cardDepositDao.queryBuilder().where(
                CardDepositDao.Properties.System_book_code.eq(systemBookCode),
                CardDepositDao.Properties.Branch_num.eq(branchNum),
                CardDepositDao.Properties.Shift_table_num.eq(shiftTableNum),
                CardDepositDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();
        //+储值
        for (CardDeposit cardDeposit : cardDepositList) {
            money = money + (float) cardDeposit.getDeposit_money();
        }

        //+续卡
        RelatCardDao relatCardDao = DaoManager.getInstance().getDaoSession().getRelatCardDao();
        List<RelatCard> relatCardList = relatCardDao.queryBuilder().where(
                RelatCardDao.Properties.System_book_code.eq(systemBookCode),
                RelatCardDao.Properties.Branch_num.eq(branchNum),
                RelatCardDao.Properties.Shift_table_num.eq(shiftTableNum),
                RelatCardDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        for (RelatCard relatCard : relatCardList) {
            money = money + Float.parseFloat(String.valueOf(relatCard.getRelat_card_money()));
        }
        //+换卡
        ReplaceCardDao replaceCardDao = DaoManager.getInstance().getDaoSession().getReplaceCardDao();
        List<ReplaceCard> replaceCardList = replaceCardDao.queryBuilder().where(
                ReplaceCardDao.Properties.System_book_code.eq(systemBookCode),
                ReplaceCardDao.Properties.Branch_num.eq(branchNum),
                ReplaceCardDao.Properties.Shift_table_num.eq(shiftTableNum),
                ReplaceCardDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        for (ReplaceCard replaceCard : replaceCardList) {
            money = money + (float) replaceCard.getReplace_card_money();

        }
        return money;

    }

    /***
     * 获取该营业日下 存款、换卡和续卡的费用总和
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableBizday
     * @return
     */
    private float getCardFeeNumBizday(String systemBookCode, int branchNum, String shiftTableBizday) {
        float money = 0;
        CardDepositDao cardDepositDao = DaoManager.getInstance().getDaoSession().getCardDepositDao();
        List<CardDeposit> cardDepositList = cardDepositDao.queryBuilder().where(
                CardDepositDao.Properties.System_book_code.eq(systemBookCode),
                CardDepositDao.Properties.Branch_num.eq(branchNum),
                CardDepositDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();
        //+储值
        for (CardDeposit cardDeposit : cardDepositList) {
            money = money + (float) cardDeposit.getDeposit_money();
        }

        //+续卡
        RelatCardDao relatCardDao = DaoManager.getInstance().getDaoSession().getRelatCardDao();
        List<RelatCard> relatCardList = relatCardDao.queryBuilder().where(
                RelatCardDao.Properties.System_book_code.eq(systemBookCode),
                RelatCardDao.Properties.Branch_num.eq(branchNum),
                RelatCardDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        for (RelatCard relatCard : relatCardList) {
            money = money + Float.parseFloat(String.valueOf(relatCard.getRelat_card_money()));
        }
        //+换卡
        ReplaceCardDao replaceCardDao = DaoManager.getInstance().getDaoSession().getReplaceCardDao();
        List<ReplaceCard> replaceCardList = replaceCardDao.queryBuilder().where(
                ReplaceCardDao.Properties.System_book_code.eq(systemBookCode),
                ReplaceCardDao.Properties.Branch_num.eq(branchNum),
                ReplaceCardDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        for (ReplaceCard replaceCard : replaceCardList) {
            money = money + (float) replaceCard.getReplace_card_money();

        }
        return money;

    }


    public float getPosOrderAllAmountAc(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, int orderStateCode) {
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        float amount = 0;
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode)
        ).list();

        for (PosOrder posOrder : posOrderList) {
            List<Payment> paymentList = getPayment(posOrder.getSystemBookCode(), posOrder.getBranchNum(), posOrder.getOrderNo());
            for (Payment payment : paymentList) {
                amount = amount + payment.getPaymentMoney();
            }
//            amount=amount+posOrder.getOrderPaymentMoney()-posOrder.getOrderChange();
        }
        return amount;
    }

    /**
     * 获取现金销售额度
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @return
     */
    public float getPosOrderAllCashAmount(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday) {
        PaymentDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        float amount = 0;
        List<Payment> paymentList = mPosOrderDao.queryBuilder().where(
                PaymentDao.Properties.SystemBookCode.eq(systemBookCode),
                PaymentDao.Properties.BranchNum.eq(branchNum),
                PaymentDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PaymentDao.Properties.ShiftTableBizday.eq(shiftTableBizday)
        ).list();

        for (Payment payment : paymentList) {
            if (payment.getPaymentPayBy().equals("现金"))
                amount = amount + payment.getPaymentMoney();
        }
        return amount;
    }

    /**
     * 获取本班次折扣金额
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getPosOrderDiscountAmount(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, int orderStateCode) {
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        float amount = 0;
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode)
        ).list();

        for (PosOrder posOrder : posOrderList) {//orderMgrDiscountMoney
//            amount=amount+posOrder.getOrderMgrDiscountMoney()+posOrder.getOrderDiscountMoney();
            amount = amount + posOrder.getOrderMgrDiscountMoney();
        }
        return amount;
    }

    /**
     * 获取本营业日折扣金额
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getPosOrderDiscountAmountBizday(String systemBookCode, int branchNum, String shiftTableBizday, int orderStateCode) {
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        float amount = 0;
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode)
        ).list();

        for (PosOrder posOrder : posOrderList) {//orderMgrDiscountMoney
            amount = amount + posOrder.getOrderMgrDiscountMoney();
        }
        return amount;
    }

    /**
     * 获取四舍五入金额
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getPosOrderRoundMoney(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, int orderStateCode) {
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        float amount = 0;
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode)
        ).list();

        for (PosOrder posOrder : posOrderList) {//orderMgrDiscountMoney
            amount = amount + posOrder.getOrderRound();
        }
        return amount;
    }

    /**
     * 获取四舍五入金额
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getPosOrderRoundMoneyBizday(String systemBookCode, int branchNum, String shiftTableBizday, int orderStateCode) {
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        float amount = 0;
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode)
        ).list();

        for (PosOrder posOrder : posOrderList) {//orderMgrDiscountMoney
            amount = amount + posOrder.getOrderRound();
        }
        return amount;
    }

    /**
     * 获取本班次 所有支付类型和相应营业额 + 会员卡（储值、换卡、续卡）支付
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @return
     */
    public List<AmountPay> getListPaymentByPayType(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday) {
        PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        List<AmountPay> amountPays = new ArrayList<>();

        // 销售单
        try {//and  PAYMENT_MONEY >0
            String sql = "select PAYMENT_PAY_BY,sum(PAYMENT_MONEY) from PAYMENT where SYSTEM_BOOK_CODE = ? " +
                    "and BRANCH_NUM= ?   and  SHIFT_TABLE_NUM=? and SHIFT_TABLE_BIZDAY=?   group by PAYMENT_PAY_BY";
            String[] strings = {systemBookCode, String.valueOf(branchNum), String.valueOf(shiftTableNum), shiftTableBizday};
            Cursor cursor = paymentDao.getDatabase().rawQuery(sql, strings);
            while (cursor.moveToNext()) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(cursor.getString(0));
                amountPay.setAmountMoney(Float.parseFloat(cursor.getString(1)));
                amountPays.add(amountPay);
            }
        } catch (Exception e) {
            EvtLog.d("getListPaymentByPayType:=" + e.toString());
        }


        CardDepositDao cardDepositDao = DaoManager.getInstance().getDaoSession().getCardDepositDao();
        List<CardDeposit> cardDepositList = cardDepositDao.queryBuilder().where(
                CardDepositDao.Properties.System_book_code.eq(systemBookCode),
                CardDepositDao.Properties.Branch_num.eq(branchNum),
                CardDepositDao.Properties.Shift_table_num.eq(shiftTableNum),
                CardDepositDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        //+储值
        for (CardDeposit cardDeposit : cardDepositList) {
            boolean iscontinuse = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (cardDeposit.getDeposit_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney((float) cardDeposit.getDeposit_cash() + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }
            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(cardDeposit.getDeposit_payment_type_name());
                amountPay.setAmountMoney((float) cardDeposit.getDeposit_cash());
                amountPays.add(amountPay);
            }
        }

        //+续卡
        RelatCardDao relatCardDao = DaoManager.getInstance().getDaoSession().getRelatCardDao();
        List<RelatCard> relatCardList = relatCardDao.queryBuilder().where(
                RelatCardDao.Properties.System_book_code.eq(systemBookCode),
                RelatCardDao.Properties.Branch_num.eq(branchNum),
                RelatCardDao.Properties.Shift_table_num.eq(shiftTableNum),
                RelatCardDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        for (RelatCard relatCard : relatCardList) {
            boolean iscontinuse = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (relatCard.getRelat_card_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney(Float.parseFloat(String.valueOf(relatCard.getRelat_card_money())) + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }
            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(relatCard.getRelat_card_payment_type_name());
                amountPay.setAmountMoney(Float.parseFloat(String.valueOf(relatCard.getRelat_card_money())));
                amountPays.add(amountPay);
            }

        }
        //+换卡
        ReplaceCardDao replaceCardDao = DaoManager.getInstance().getDaoSession().getReplaceCardDao();
        List<ReplaceCard> replaceCardList = replaceCardDao.queryBuilder().where(
                ReplaceCardDao.Properties.System_book_code.eq(systemBookCode),
                ReplaceCardDao.Properties.Branch_num.eq(branchNum),
                ReplaceCardDao.Properties.Shift_table_num.eq(shiftTableNum),
                ReplaceCardDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        for (ReplaceCard replaceCard : replaceCardList) {

            boolean iscontinuse = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (replaceCard.getReplace_card_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney((float) replaceCard.getReplace_card_money() + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }
            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(replaceCard.getReplace_card_payment_type_name());
                amountPay.setAmountMoney((float) replaceCard.getReplace_card_money());
                amountPays.add(amountPay);
            }

        }

        //+CRM 开卡
        final CustomerRegisterDao customerRegisterDao = DaoManager.getInstance().getDaoSession().getCustomerRegisterDao();
        List<CustomerRegister> customerRegisterList = customerRegisterDao.queryBuilder().where(
                CustomerRegisterDao.Properties.System_book_code.eq(systemBookCode),
                CustomerRegisterDao.Properties.Branch_num.eq(branchNum),
                CustomerRegisterDao.Properties.Shift_table_num.eq(shiftTableNum),
                CustomerRegisterDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        for (CustomerRegister customerRegister : customerRegisterList) {

            boolean iscontinuse = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (customerRegister.getVip_card_user_payment().equals(amountPay.getName())) {
                    amountPay.setAmountMoney(customerRegister.getVip_card_user_money() + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }
            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(customerRegister.getVip_card_user_payment());
                amountPay.setAmountMoney(customerRegister.getVip_card_user_money());
                amountPays.add(amountPay);
            }

        }

        //-制单取消
        PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList = posOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(17)
        ).build().list();

        for (PosOrder posOrder : posOrderList) {
            loop1:
            for (AmountPay amountPay : amountPays) {
                loop2:
                for (Payment payment : getPayment(systemBookCode, branchNum, posOrder.getOrderNo())) {
                    if (payment.getPaymentPayBy().equals(amountPay.getName())) {
                        amountPay.setAmountMoney(amountPay.getAmountMoney() - payment.getPaymentMoney());
                        break loop2;
                    }
                }

            }


        }


        return amountPays;
    }


    /**
     * 获取本班次 所有支付类型和相应营业额 + 会员卡（储值、换卡、续卡）支付
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @return
     */
    public List<AmountPay> getListPaymentByPayTotal(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, ShiftTable shiftTable) {
        PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        List<AmountPay> amountPays = new ArrayList<>();
        List<AmountPay> posPays = new ArrayList<>();
        List<AmountPay> cardPays = new ArrayList<>();

        shiftTable.setOrderPayTypeInfo(posPays);
        shiftTable.setCardPayTypeInfo(cardPays);
        // 销售单
        try {//and  PAYMENT_MONEY >0
            String sql = "select PAYMENT_PAY_BY,sum(PAYMENT_MONEY) from PAYMENT where SYSTEM_BOOK_CODE = ? " +
                    "and BRANCH_NUM= ?   and  SHIFT_TABLE_NUM=? and SHIFT_TABLE_BIZDAY=?   group by PAYMENT_PAY_BY";
            String[] strings = {systemBookCode, String.valueOf(branchNum), String.valueOf(shiftTableNum), shiftTableBizday};
            Cursor cursor = paymentDao.getDatabase().rawQuery(sql, strings);
            while (cursor.moveToNext()) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(cursor.getString(0));
                amountPay.setAmountMoney(Float.parseFloat(cursor.getString(1)));
                amountPays.add(amountPay);
                posPays.add(new Gson().fromJson(new Gson().toJson(amountPay), AmountPay.class));
            }
        } catch (Exception e) {
            EvtLog.d("getListPaymentByPayType:=" + e.toString());
        }


        CardDepositDao cardDepositDao = DaoManager.getInstance().getDaoSession().getCardDepositDao();
        List<CardDeposit> cardDepositList = cardDepositDao.queryBuilder().where(
                CardDepositDao.Properties.System_book_code.eq(systemBookCode),
                CardDepositDao.Properties.Branch_num.eq(branchNum),
                CardDepositDao.Properties.Shift_table_num.eq(shiftTableNum),
                CardDepositDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        //+储值
        for (CardDeposit cardDeposit : cardDepositList) {
            boolean iscontinuse = false;
            boolean iscontinuse2 = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (cardDeposit.getDeposit_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney((float) cardDeposit.getDeposit_cash() + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }


            loop2:
            for (AmountPay amountPay : cardPays) {
                if (cardDeposit.getDeposit_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney((float) cardDeposit.getDeposit_cash() + amountPay.getAmountMoney());
                    iscontinuse2 = true;
                    break loop2;
                }
            }
            if (!iscontinuse2) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(cardDeposit.getDeposit_payment_type_name());
                amountPay.setAmountMoney((float) cardDeposit.getDeposit_cash());
                cardPays.add(new Gson().fromJson(new Gson().toJson(amountPay), AmountPay.class));
            }

            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(cardDeposit.getDeposit_payment_type_name());
                amountPay.setAmountMoney((float) cardDeposit.getDeposit_cash());
                amountPays.add(amountPay);
            }


        }

        //+续卡
        RelatCardDao relatCardDao = DaoManager.getInstance().getDaoSession().getRelatCardDao();
        List<RelatCard> relatCardList = relatCardDao.queryBuilder().where(
                RelatCardDao.Properties.System_book_code.eq(systemBookCode),
                RelatCardDao.Properties.Branch_num.eq(branchNum),
                RelatCardDao.Properties.Shift_table_num.eq(shiftTableNum),
                RelatCardDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        for (RelatCard relatCard : relatCardList) {
            boolean iscontinuse = false;
            boolean iscontinuse2 = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (relatCard.getRelat_card_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney(Float.parseFloat(String.valueOf(relatCard.getRelat_card_money())) + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }


            loop2:
            for (AmountPay amountPay : cardPays) {
                if (relatCard.getRelat_card_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney(Float.parseFloat(String.valueOf(relatCard.getRelat_card_money())) + amountPay.getAmountMoney());
                    iscontinuse2 = true;
                    break loop2;
                }
            }
            if (!iscontinuse2) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(relatCard.getRelat_card_payment_type_name());
                amountPay.setAmountMoney(Float.parseFloat(String.valueOf(relatCard.getRelat_card_money())));
                cardPays.add(new Gson().fromJson(new Gson().toJson(amountPay), AmountPay.class));
            }

            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(relatCard.getRelat_card_payment_type_name());
                amountPay.setAmountMoney(Float.parseFloat(String.valueOf(relatCard.getRelat_card_money())));
                amountPays.add(amountPay);
            }

        }
        //+换卡
        ReplaceCardDao replaceCardDao = DaoManager.getInstance().getDaoSession().getReplaceCardDao();
        List<ReplaceCard> replaceCardList = replaceCardDao.queryBuilder().where(
                ReplaceCardDao.Properties.System_book_code.eq(systemBookCode),
                ReplaceCardDao.Properties.Branch_num.eq(branchNum),
                ReplaceCardDao.Properties.Shift_table_num.eq(shiftTableNum),
                ReplaceCardDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        for (ReplaceCard replaceCard : replaceCardList) {

            boolean iscontinuse = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (replaceCard.getReplace_card_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney((float) replaceCard.getReplace_card_money() + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }
            boolean iscontinuse2 = false;
            loop2:
            for (AmountPay amountPay : cardPays) {
                if (replaceCard.getReplace_card_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney(Float.parseFloat(String.valueOf(replaceCard.getReplace_card_money())) + amountPay.getAmountMoney());
                    iscontinuse2 = true;
                    break loop2;
                }
            }
            if (!iscontinuse2) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(replaceCard.getReplace_card_payment_type_name());
                amountPay.setAmountMoney(Float.parseFloat(String.valueOf(replaceCard.getReplace_card_money())));
                cardPays.add(new Gson().fromJson(new Gson().toJson(amountPay), AmountPay.class));
            }

            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(replaceCard.getReplace_card_payment_type_name());
                amountPay.setAmountMoney((float) replaceCard.getReplace_card_money());
                amountPays.add(amountPay);
            }

        }

        //+CRM 开卡
        final CustomerRegisterDao customerRegisterDao = DaoManager.getInstance().getDaoSession().getCustomerRegisterDao();
        List<CustomerRegister> customerRegisterList = customerRegisterDao.queryBuilder().where(
                CustomerRegisterDao.Properties.System_book_code.eq(systemBookCode),
                CustomerRegisterDao.Properties.Branch_num.eq(branchNum),
                CustomerRegisterDao.Properties.Shift_table_num.eq(shiftTableNum),
                CustomerRegisterDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        for (CustomerRegister customerRegister : customerRegisterList) {

            boolean iscontinuse = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (customerRegister.getVip_card_user_payment().equals(amountPay.getName())) {
                    amountPay.setAmountMoney(customerRegister.getVip_card_user_money() + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }

            boolean iscontinuse2 = false;
            loop2:
            for (AmountPay amountPay : cardPays) {
                if (customerRegister.getVip_card_user_payment().equals(amountPay.getName())) {
                    amountPay.setAmountMoney(customerRegister.getVip_card_user_money() + amountPay.getAmountMoney());
                    iscontinuse2 = true;
                    break loop2;
                }
            }
            if (!iscontinuse2) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(customerRegister.getVip_card_user_payment());
                amountPay.setAmountMoney(customerRegister.getVip_card_user_money());
                cardPays.add(new Gson().fromJson(new Gson().toJson(amountPay), AmountPay.class));
            }

            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(customerRegister.getVip_card_user_payment());
                amountPay.setAmountMoney(customerRegister.getVip_card_user_money());
                amountPays.add(amountPay);
            }

        }

        //-制单取消
        PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList = posOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(17)
        ).build().list();

        for (PosOrder posOrder : posOrderList) {
            loop1:
            for (AmountPay amountPay : amountPays) {
                loop2:
                for (Payment payment : getPayment(systemBookCode, branchNum, posOrder.getOrderNo())) {
                    if (payment.getPaymentPayBy().equals(amountPay.getName())) {
                        amountPay.setAmountMoney(amountPay.getAmountMoney() - payment.getPaymentMoney());
                        break loop2;
                    }
                }
            }

            for (AmountPay amountPay : posPays) {
                loop2:
                for (Payment payment : getPayment(systemBookCode, branchNum, posOrder.getOrderNo())) {
                    if (payment.getPaymentPayBy().equals(amountPay.getName())) {
                        amountPay.setAmountMoney(amountPay.getAmountMoney() - payment.getPaymentMoney());
                        break loop2;
                    }
                }
            }


        }


        return amountPays;
    }


    /**
     * 获取本营业日 所有支付类型和相应营业额 + 会员卡（储值、换卡、续卡）支付
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableBizday
     * @return
     */
    public List<AmountPay> getListPaymentByPayTypeBizday(String systemBookCode, int branchNum, String shiftTableBizday) {
        PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        List<AmountPay> amountPays = new ArrayList<>();

        // 销售单
        try {//and  PAYMENT_MONEY >0
            String sql = "select PAYMENT_PAY_BY,sum(PAYMENT_MONEY) from PAYMENT where SYSTEM_BOOK_CODE = ? " +
                    "and BRANCH_NUM= ?  and SHIFT_TABLE_BIZDAY=?   group by PAYMENT_PAY_BY";
            String[] strings = {systemBookCode, String.valueOf(branchNum), shiftTableBizday};
            Cursor cursor = paymentDao.getDatabase().rawQuery(sql, strings);
            while (cursor.moveToNext()) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(cursor.getString(0));
                amountPay.setAmountMoney(Float.parseFloat(cursor.getString(1)));
                amountPays.add(amountPay);
            }
        } catch (Exception e) {
            EvtLog.d("getListPaymentByPayType:=" + e.toString());
        }


        CardDepositDao cardDepositDao = DaoManager.getInstance().getDaoSession().getCardDepositDao();
        List<CardDeposit> cardDepositList = cardDepositDao.queryBuilder().where(
                CardDepositDao.Properties.System_book_code.eq(systemBookCode),
                CardDepositDao.Properties.Branch_num.eq(branchNum),
                CardDepositDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        //+储值
        for (CardDeposit cardDeposit : cardDepositList) {
            boolean iscontinuse = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (cardDeposit.getDeposit_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney((float) cardDeposit.getDeposit_cash() + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }
            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(cardDeposit.getDeposit_payment_type_name());
                amountPay.setAmountMoney((float) cardDeposit.getDeposit_cash());
                amountPays.add(amountPay);
            }
        }

        //+续卡
        RelatCardDao relatCardDao = DaoManager.getInstance().getDaoSession().getRelatCardDao();
        List<RelatCard> relatCardList = relatCardDao.queryBuilder().where(
                RelatCardDao.Properties.System_book_code.eq(systemBookCode),
                RelatCardDao.Properties.Branch_num.eq(branchNum),
                RelatCardDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        for (RelatCard relatCard : relatCardList) {
            boolean iscontinuse = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (relatCard.getRelat_card_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney(Float.parseFloat(String.valueOf(relatCard.getRelat_card_money())) + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }
            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(relatCard.getRelat_card_payment_type_name());
                amountPay.setAmountMoney(Float.parseFloat(String.valueOf(relatCard.getRelat_card_money())));
                amountPays.add(amountPay);
            }

        }
        //+换卡
        ReplaceCardDao replaceCardDao = DaoManager.getInstance().getDaoSession().getReplaceCardDao();
        List<ReplaceCard> replaceCardList = replaceCardDao.queryBuilder().where(
                ReplaceCardDao.Properties.System_book_code.eq(systemBookCode),
                ReplaceCardDao.Properties.Branch_num.eq(branchNum),
                ReplaceCardDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        for (ReplaceCard replaceCard : replaceCardList) {

            boolean iscontinuse = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (replaceCard.getReplace_card_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney((float) replaceCard.getReplace_card_money() + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }
            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(replaceCard.getReplace_card_payment_type_name());
                amountPay.setAmountMoney((float) replaceCard.getReplace_card_money());
                amountPays.add(amountPay);
            }

        }


        return amountPays;
    }


    public List<AmountPay> getListPaymentByPayTypeBizdayTotal(String systemBookCode, int branchNum, String shiftTableBizday, PrintShiftTable shiftTable) {
        PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        List<AmountPay> amountPays = new ArrayList<>();

        List<AmountPay> posPays = new ArrayList<>();
        List<AmountPay> cardPays = new ArrayList<>();
        shiftTable.posTypeList = posPays;
        shiftTable.cardTypeList = cardPays;
//        shiftTable.setOrderPayTypeInfo(posPays);
//        shiftTable.setCardPayTypeInfo(cardPays);


        // 销售单
        try {//and  PAYMENT_MONEY >0
            String sql = "select PAYMENT_PAY_BY,sum(PAYMENT_MONEY) from PAYMENT where SYSTEM_BOOK_CODE = ? " +
                    "and BRANCH_NUM= ?  and SHIFT_TABLE_BIZDAY=?   group by PAYMENT_PAY_BY";
            String[] strings = {systemBookCode, String.valueOf(branchNum), shiftTableBizday};
            Cursor cursor = paymentDao.getDatabase().rawQuery(sql, strings);
            while (cursor.moveToNext()) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(cursor.getString(0));
                amountPay.setAmountMoney(Float.parseFloat(cursor.getString(1)));
                amountPays.add(amountPay);
                posPays.add(new Gson().fromJson(new Gson().toJson(amountPay), AmountPay.class));
            }
        } catch (Exception e) {
            EvtLog.d("getListPaymentByPayType:=" + e.toString());
        }


        CardDepositDao cardDepositDao = DaoManager.getInstance().getDaoSession().getCardDepositDao();
        List<CardDeposit> cardDepositList = cardDepositDao.queryBuilder().where(
                CardDepositDao.Properties.System_book_code.eq(systemBookCode),
                CardDepositDao.Properties.Branch_num.eq(branchNum),
                CardDepositDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        //+储值
        for (CardDeposit cardDeposit : cardDepositList) {
            boolean iscontinuse = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (cardDeposit.getDeposit_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney((float) cardDeposit.getDeposit_cash() + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }

            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(cardDeposit.getDeposit_payment_type_name());
                amountPay.setAmountMoney((float) cardDeposit.getDeposit_cash());
                amountPays.add(amountPay);
            }

            boolean iscontinuse2 = false;
            loop2:
            for (AmountPay amountPay : cardPays) {
                if (cardDeposit.getDeposit_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney((float) cardDeposit.getDeposit_cash() + amountPay.getAmountMoney());
                    iscontinuse2 = true;
                    break loop2;
                }
            }
            if (!iscontinuse2) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(cardDeposit.getDeposit_payment_type_name());
                amountPay.setAmountMoney((float) cardDeposit.getDeposit_cash());
                cardPays.add(new Gson().fromJson(new Gson().toJson(amountPay), AmountPay.class));
            }

        }

        //+续卡
        RelatCardDao relatCardDao = DaoManager.getInstance().getDaoSession().getRelatCardDao();
        List<RelatCard> relatCardList = relatCardDao.queryBuilder().where(
                RelatCardDao.Properties.System_book_code.eq(systemBookCode),
                RelatCardDao.Properties.Branch_num.eq(branchNum),
                RelatCardDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        for (RelatCard relatCard : relatCardList) {
            boolean iscontinuse = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (relatCard.getRelat_card_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney(Float.parseFloat(String.valueOf(relatCard.getRelat_card_money())) + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }
            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(relatCard.getRelat_card_payment_type_name());
                amountPay.setAmountMoney(Float.parseFloat(String.valueOf(relatCard.getRelat_card_money())));
                amountPays.add(amountPay);
            }

            boolean iscontinuse2 = false;
            loop2:
            for (AmountPay amountPay : cardPays) {
                if (relatCard.getRelat_card_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney(Float.parseFloat(String.valueOf(relatCard.getRelat_card_money())) + amountPay.getAmountMoney());
                    iscontinuse2 = true;
                    break loop2;
                }
            }
            if (!iscontinuse2) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(relatCard.getRelat_card_payment_type_name());
                amountPay.setAmountMoney(Float.parseFloat(String.valueOf(relatCard.getRelat_card_money())));
                cardPays.add(new Gson().fromJson(new Gson().toJson(amountPay), AmountPay.class));
            }
        }
        //+换卡
        ReplaceCardDao replaceCardDao = DaoManager.getInstance().getDaoSession().getReplaceCardDao();
        List<ReplaceCard> replaceCardList = replaceCardDao.queryBuilder().where(
                ReplaceCardDao.Properties.System_book_code.eq(systemBookCode),
                ReplaceCardDao.Properties.Branch_num.eq(branchNum),
                ReplaceCardDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        for (ReplaceCard replaceCard : replaceCardList) {

            boolean iscontinuse = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (replaceCard.getReplace_card_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney((float) replaceCard.getReplace_card_money() + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }
            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(replaceCard.getReplace_card_payment_type_name());
                amountPay.setAmountMoney((float) replaceCard.getReplace_card_money());
                amountPays.add(amountPay);
            }


            boolean iscontinuse2 = false;
            loop2:
            for (AmountPay amountPay : cardPays) {
                if (replaceCard.getReplace_card_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney(Float.parseFloat(String.valueOf(replaceCard.getReplace_card_money())) + amountPay.getAmountMoney());
                    iscontinuse2 = true;
                    break loop2;
                }
            }
            if (!iscontinuse2) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(replaceCard.getReplace_card_payment_type_name());
                amountPay.setAmountMoney(Float.parseFloat(String.valueOf(replaceCard.getReplace_card_money())));
                cardPays.add(new Gson().fromJson(new Gson().toJson(amountPay), AmountPay.class));
            }

        }


        //+CRM 开卡
        final CustomerRegisterDao customerRegisterDao = DaoManager.getInstance().getDaoSession().getCustomerRegisterDao();
        List<CustomerRegister> customerRegisterList = customerRegisterDao.queryBuilder().where(
                CustomerRegisterDao.Properties.System_book_code.eq(systemBookCode),
                CustomerRegisterDao.Properties.Branch_num.eq(branchNum),
                CustomerRegisterDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        for (CustomerRegister customerRegister : customerRegisterList) {

            boolean iscontinuse = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (customerRegister.getVip_card_user_payment().equals(amountPay.getName())) {
                    amountPay.setAmountMoney(customerRegister.getVip_card_user_money() + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }

            boolean iscontinuse2 = false;
            loop2:
            for (AmountPay amountPay : cardPays) {
                if (customerRegister.getVip_card_user_payment().equals(amountPay.getName())) {
                    amountPay.setAmountMoney(customerRegister.getVip_card_user_money() + amountPay.getAmountMoney());
                    iscontinuse2 = true;
                    break loop2;
                }
            }
            if (!iscontinuse2) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(customerRegister.getVip_card_user_payment());
                amountPay.setAmountMoney(customerRegister.getVip_card_user_money());
                cardPays.add(new Gson().fromJson(new Gson().toJson(amountPay), AmountPay.class));
            }

            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(customerRegister.getVip_card_user_payment());
                amountPay.setAmountMoney(customerRegister.getVip_card_user_money());
                amountPays.add(amountPay);
            }

        }


        //-制单取消
        PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList = posOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(17)
        ).build().list();

        for (PosOrder posOrder : posOrderList) {
            loop1:
            for (AmountPay amountPay : amountPays) {
                loop2:
                for (Payment payment : getPayment(systemBookCode, branchNum, posOrder.getOrderNo())) {
                    if (payment.getPaymentPayBy().equals(amountPay.getName())) {
                        amountPay.setAmountMoney(amountPay.getAmountMoney() - payment.getPaymentMoney());
                        break loop2;
                    }
                }
            }

            for (AmountPay amountPay : posPays) {
                loop2:
                for (Payment payment : getPayment(systemBookCode, branchNum, posOrder.getOrderNo())) {
                    if (payment.getPaymentPayBy().equals(amountPay.getName())) {
                        amountPay.setAmountMoney(amountPay.getAmountMoney() - payment.getPaymentMoney());
                        break loop2;
                    }
                }
            }
        }

        return amountPays;
    }


    /**
     * 获取本营业日 所有支付类型和相应营业额 + 会员卡（储值、换卡、续卡）支付
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableBizday
     * @return
     */
    public List<AmountPay> getListPaymentByPayTypeBizdayTotal(String systemBookCode, int branchNum, String shiftTableBizday) {
        PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        List<AmountPay> amountPays = new ArrayList<>();

        // 销售单
        try {//and  PAYMENT_MONEY >0
            String sql = "select PAYMENT_PAY_BY,sum(PAYMENT_MONEY) from PAYMENT where SYSTEM_BOOK_CODE = ? " +
                    "and BRANCH_NUM= ?  and SHIFT_TABLE_BIZDAY=?   group by PAYMENT_PAY_BY";
            String[] strings = {systemBookCode, String.valueOf(branchNum), shiftTableBizday};
            Cursor cursor = paymentDao.getDatabase().rawQuery(sql, strings);
            while (cursor.moveToNext()) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(cursor.getString(0));
                amountPay.setAmountMoney(Float.parseFloat(cursor.getString(1)));
                amountPays.add(amountPay);
            }
        } catch (Exception e) {
            EvtLog.d("getListPaymentByPayType:=" + e.toString());
        }


        CardDepositDao cardDepositDao = DaoManager.getInstance().getDaoSession().getCardDepositDao();
        List<CardDeposit> cardDepositList = cardDepositDao.queryBuilder().where(
                CardDepositDao.Properties.System_book_code.eq(systemBookCode),
                CardDepositDao.Properties.Branch_num.eq(branchNum),
                CardDepositDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        //+储值
        for (CardDeposit cardDeposit : cardDepositList) {
            boolean iscontinuse = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (cardDeposit.getDeposit_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney((float) cardDeposit.getDeposit_cash() + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }
            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(cardDeposit.getDeposit_payment_type_name());
                amountPay.setAmountMoney((float) cardDeposit.getDeposit_cash());
                amountPays.add(amountPay);
            }
        }

        //+续卡
        RelatCardDao relatCardDao = DaoManager.getInstance().getDaoSession().getRelatCardDao();
        List<RelatCard> relatCardList = relatCardDao.queryBuilder().where(
                RelatCardDao.Properties.System_book_code.eq(systemBookCode),
                RelatCardDao.Properties.Branch_num.eq(branchNum),
                RelatCardDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        for (RelatCard relatCard : relatCardList) {
            boolean iscontinuse = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (relatCard.getRelat_card_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney(Float.parseFloat(String.valueOf(relatCard.getRelat_card_money())) + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }
            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(relatCard.getRelat_card_payment_type_name());
                amountPay.setAmountMoney(Float.parseFloat(String.valueOf(relatCard.getRelat_card_money())));
                amountPays.add(amountPay);
            }

        }
        //+换卡
        ReplaceCardDao replaceCardDao = DaoManager.getInstance().getDaoSession().getReplaceCardDao();
        List<ReplaceCard> replaceCardList = replaceCardDao.queryBuilder().where(
                ReplaceCardDao.Properties.System_book_code.eq(systemBookCode),
                ReplaceCardDao.Properties.Branch_num.eq(branchNum),
                ReplaceCardDao.Properties.Shift_table_bizday.eq(shiftTableBizday)
        ).build().list();

        for (ReplaceCard replaceCard : replaceCardList) {

            boolean iscontinuse = false;
            loop1:
            for (AmountPay amountPay : amountPays) {
                if (replaceCard.getReplace_card_payment_type_name().equals(amountPay.getName())) {
                    amountPay.setAmountMoney((float) replaceCard.getReplace_card_money() + amountPay.getAmountMoney());
                    iscontinuse = true;
                    break loop1;
                }
            }
            if (!iscontinuse) {
                AmountPay amountPay = new AmountPay();
                amountPay.setName(replaceCard.getReplace_card_payment_type_name());
                amountPay.setAmountMoney((float) replaceCard.getReplace_card_money());
                amountPays.add(amountPay);
            }

        }


        return amountPays;
    }


    /**
     * 获取收款总金额
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @return
     */
    public float getAmountOfCollection(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday) {
        List<AmountPay> amountPayList = getListPaymentByPayType(systemBookCode, branchNum, shiftTableNum, shiftTableBizday);
        float money = 0;
        for (AmountPay amountPay : amountPayList) {
            money = +amountPay.getAmountMoney();
        }
        return money;
    }

    /**
     * 实时上传成功后将posorder订单上传状态修改掉
     * @param order_num 订单的编号
     */
    public void changeOrderUploadStatus(String order_num) {
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrder loadOrder = mPosOrderDao.load(order_num);
        if (loadOrder != null) {
            loadOrder.setOrderUploadState(true);
            mPosOrderDao.insertOrReplace(loadOrder);
        }
    }


    /**
     * 获取本班次续卡次数
     *
     * @param book_code
     * @param shift_table_num
     * @param branchNum
     * @return
     */
    public int getStrangeCardNum(String book_code, String shift_table_num, String branch_num) {
        RelatCardDao vipConsumeDao = DaoManager.getInstance().getDaoSession().getRelatCardDao();
        return vipConsumeDao.queryBuilder().where(
                RelatCardDao.Properties.System_book_code.eq(book_code),
                RelatCardDao.Properties.Shift_table_num.eq(shift_table_num),
                RelatCardDao.Properties.Branch_num.eq(branch_num)
        ).list().size();
    }


    /**
     * 获取本营业日续卡次数
     *
     * @param systemBookCode
     * @param bizday
     * @param branchNum
     * @return
     */
    public int getStrangeCardNumBizday(String systemBookCode, String bizday, int branchNum) {
        RelatCardDao vipConsumeDao = DaoManager.getInstance().getDaoSession().getRelatCardDao();
        return vipConsumeDao.queryBuilder().where(
                RelatCardDao.Properties.System_book_code.eq(systemBookCode),
                RelatCardDao.Properties.Shift_table_bizday.eq(bizday),
                RelatCardDao.Properties.Branch_num.eq(branchNum)
        ).list().size();
    }


    /**
     * 本班次开卡次数
     *
     * @param systemBookCode
     * @param shiftTableNum
     * @param branchNum
     * @return
     */
    public int getStartCardNum(String systemBookCode, String shiftTableNum, String branchNum) {
        VipSendCardDao vipSendCardDao = DaoManager.getInstance().getDaoSession().getVipSendCardDao();
        return vipSendCardDao.queryBuilder().where(
                VipSendCardDao.Properties.BranchNum.eq(branchNum),
                VipSendCardDao.Properties.ShiftTableNum.eq(shiftTableNum),
                VipSendCardDao.Properties.SystemBookCode.eq(systemBookCode)
        ).list().size();
    }

    /**
     * 本营业日开卡次数
     *
     * @param systemBookCode
     * @param branchNum
     * @return
     */
    public int getStartCardNumBizDay(String systemBookCode, String branchNum, String bizday) {
        VipSendCardDao vipSendCardDao = DaoManager.getInstance().getDaoSession().getVipSendCardDao();
        return vipSendCardDao.queryBuilder().where(
                VipSendCardDao.Properties.BranchNum.eq(branchNum),
                VipSendCardDao.Properties.ShiftTableBizDay.eq(bizday),
                VipSendCardDao.Properties.SystemBookCode.eq(systemBookCode)
        ).list().size();
    }

    public int getStrangeSuccessSendNum(String systemBookCode, String shiftTableNum, String branchNum) {
        VipStrangeSuccessSendMoneyDao vipStrangeSuccessSendMoneyDao = DaoManager.getInstance().getDaoSession().getVipStrangeSuccessSendMoneyDao();
        return vipStrangeSuccessSendMoneyDao.queryBuilder().where(
                VipStrangeSuccessSendMoneyDao.Properties.BranchNum.eq(branchNum),
                VipStrangeSuccessSendMoneyDao.Properties.ShiftTableNum.eq(shiftTableNum),
                VipStrangeSuccessSendMoneyDao.Properties.SystemBookCode.eq(systemBookCode)
        ).list().size();
    }

    public float getStrangeSuccessSendMoney(String systemBookCode, String shiftTableNum, String branchNum) {
        float money = 0;
        VipStrangeSuccessSendMoneyDao vipStrangeSuccessSendMoneyDao = DaoManager.getInstance().getDaoSession().getVipStrangeSuccessSendMoneyDao();
        List<VipStrangeSuccessSendMoney> list = vipStrangeSuccessSendMoneyDao.queryBuilder().where(
                VipStrangeSuccessSendMoneyDao.Properties.BranchNum.eq(branchNum),
                VipStrangeSuccessSendMoneyDao.Properties.ShiftTableNum.eq(shiftTableNum),
                VipStrangeSuccessSendMoneyDao.Properties.SystemBookCode.eq(systemBookCode)
        ).list();
        for (VipStrangeSuccessSendMoney vipStrangeSuccessSendMoney : list) {
            money = money + vipStrangeSuccessSendMoney.getMoney();
        }
        return money;
    }

    public float getStrangeSuccessSendMoneyBizday(String systemBookCode, String branchNum, String bizday) {
        float money = 0;
        CardDepositDao vipStrangeSuccessSendMoneyDao = DaoManager.getInstance().getDaoSession().getCardDepositDao();
        List<CardDeposit> list = vipStrangeSuccessSendMoneyDao.queryBuilder().where(
                CardDepositDao.Properties.Branch_num.eq(branchNum),
                CardDepositDao.Properties.Shift_table_bizday.eq(bizday),
                CardDepositDao.Properties.System_book_code.eq(systemBookCode)
        ).list();
        for (CardDeposit vipStrangeSuccessSendMoney : list) {
            money = money + (float) vipStrangeSuccessSendMoney.getDeposit_money();
        }
        return money;
    }

    /**
     * 获取xx班次下的换卡次数
     *
     * @param systemBookCode
     * @param shiftTableNum
     * @param branchNum
     * @return
     */
    public int getReplaceCardNum(String systemBookCode, String shiftTableNum, String branchNum) {
        ReplaceCardDao replaceCardDao = DaoManager.getInstance().getDaoSession().getReplaceCardDao();
        return replaceCardDao.queryBuilder().where(
                ReplaceCardDao.Properties.System_book_code.eq(systemBookCode),
                ReplaceCardDao.Properties.Shift_table_num.eq(shiftTableNum),
                ReplaceCardDao.Properties.Branch_num.eq(branchNum)
        ).list().size();
    }

    /**
     * 获取xx营业日下的换卡次数
     *
     * @param systemBookCode
     * @param bizday
     * @param branchNum
     * @return
     */
    public int getReplaceCardNumBizday(String systemBookCode, String bizday, int branchNum) {
        ReplaceCardDao replaceCardDao = DaoManager.getInstance().getDaoSession().getReplaceCardDao();
        return replaceCardDao.queryBuilder().where(
                ReplaceCardDao.Properties.System_book_code.eq(systemBookCode),
                ReplaceCardDao.Properties.Shift_table_bizday.eq(bizday),
                ReplaceCardDao.Properties.Branch_num.eq(branchNum)
        ).list().size();
    }

    /**
     * 获取xx班次下的储值次数
     *
     * @param systemBookCode
     * @param shiftTableNum
     * @param branchNum
     * @return
     */
    public int getDepositNum(String systemBookCode, String shiftTableNum, String branchNum) {
        final CardDepositDao cardDepositDao = DaoManager.getInstance().getDaoSession().getCardDepositDao();
        return cardDepositDao.queryBuilder().where(
                CardDepositDao.Properties.System_book_code.eq(systemBookCode),
                CardDepositDao.Properties.Shift_table_num.eq(shiftTableNum),
                CardDepositDao.Properties.Branch_num.eq(branchNum)
        ).list().size();
    }

    /**
     * 获取xx营业日下的储值次数
     *
     * @param systemBookCode
     * @param bizday
     * @param branchNum
     * @return
     */
    public int getDepositNumBizday(String systemBookCode, String bizday, int branchNum) {
        final CardDepositDao cardDepositDao = DaoManager.getInstance().getDaoSession().getCardDepositDao();
        return cardDepositDao.queryBuilder().where(
                CardDepositDao.Properties.System_book_code.eq(systemBookCode),
                CardDepositDao.Properties.Shift_table_bizday.eq(bizday),
                CardDepositDao.Properties.Branch_num.eq(branchNum)
        ).list().size();
    }


    /**
     * 获取xx班次下的储值金额
     *
     * @param systemBookCode
     * @param shiftTableNum
     * @param branchNum
     * @return
     */
    public Double getDepositMoney(String systemBookCode, String shiftTableNum, String branchNum) {
        double money = 0;
        final CardDepositDao cardDepositDao = DaoManager.getInstance().getDaoSession().getCardDepositDao();
        List<CardDeposit> vipConsumeList = cardDepositDao.queryBuilder().where(
                CardDepositDao.Properties.System_book_code.eq(systemBookCode),
                CardDepositDao.Properties.Shift_table_num.eq(shiftTableNum),
                CardDepositDao.Properties.Branch_num.eq(branchNum)
        ).list();
        for (CardDeposit mCardDeposit : vipConsumeList) {
            money = money + mCardDeposit.getDeposit_money();
        }
        return money;
    }

    /**
     * 获取xx营业日下的储值金额
     *
     * @param systemBookCode
     * @param bizday
     * @param branchNum
     * @return
     */
    public Double getDepositMoneyBizday(String systemBookCode, String bizday, int branchNum) {
        double money = 0;
        final CardDepositDao cardDepositDao = DaoManager.getInstance().getDaoSession().getCardDepositDao();
        List<CardDeposit> vipConsumeList = cardDepositDao.queryBuilder().where(
                CardDepositDao.Properties.System_book_code.eq(systemBookCode),
                CardDepositDao.Properties.Shift_table_bizday.eq(bizday),
                CardDepositDao.Properties.Branch_num.eq(branchNum)
        ).list();
        for (CardDeposit mCardDeposit : vipConsumeList) {
            money = money + mCardDeposit.getDeposit_money();
        }
        return money;
    }


    /**
     * 获取xx班次下的续卡金额
     *
     * @param systemBookCode
     * @param shiftTableNum
     * @param branchNum
     * @return
     */
    public Double getRenewCardMoney(String systemBookCode, String shiftTableNum, String branchNum) {
        double money = 0;
        RelatCardDao vipConsumeDao = DaoManager.getInstance().getDaoSession().getRelatCardDao();
        List<RelatCard> vipConsumeList = vipConsumeDao.queryBuilder().where(
                RelatCardDao.Properties.System_book_code.eq(systemBookCode),
                RelatCardDao.Properties.Shift_table_num.eq(shiftTableNum),
                RelatCardDao.Properties.Branch_num.eq(branchNum)
        ).list();
        for (RelatCard vipConsume : vipConsumeList) {
            money = money + vipConsume.getRelat_card_money();
        }
        return money;
    }

    /**
     * 获取xx营业日下的续卡金额
     *
     * @param systemBookCode
     * @param bizday
     * @param branchNum
     * @return
     */
    public Double getRenewCardMoneyBizday(String systemBookCode, String bizday, int branchNum) {
        double money = 0;
        RelatCardDao vipConsumeDao = DaoManager.getInstance().getDaoSession().getRelatCardDao();
        List<RelatCard> vipConsumeList = vipConsumeDao.queryBuilder().where(
                RelatCardDao.Properties.System_book_code.eq(systemBookCode),
                RelatCardDao.Properties.Shift_table_bizday.eq(bizday),
                RelatCardDao.Properties.Branch_num.eq(branchNum)
        ).list();
        for (RelatCard vipConsume : vipConsumeList) {
            money = money + vipConsume.getRelat_card_money();
        }
        return money;
    }

    /**
     * 获取xx班次下的零钱包存入单数
     *
     * @param systemBookCode
     * @param shiftTableNum
     * @param branchNum
     * @return
     */
    public int getChangeNum(String systemBookCode, String shiftTableNum, String branchNum) {
        int num = 0;
        CardChangeDao cardChangeDao = DaoManager.getInstance().getDaoSession().getCardChangeDao();
        num = cardChangeDao.queryBuilder().where(
                CardChangeDao.Properties.Shift_table_num.eq(shiftTableNum),
                CardChangeDao.Properties.Branch_num.eq(branchNum),
                CardChangeDao.Properties.System_book_code.eq(systemBookCode)).build().list().size();
        return num;
    }

    /**
     * 获取xx营业日下的零钱包存入单数
     *
     * @param systemBookCode
     * @param shiftTableBizday
     * @param branchNum
     * @return
     */
    public int getChangeNumBizday(String systemBookCode, String shiftTableBizday, int branchNum) {
        int num = 0;
        CardChangeDao cardChangeDao = DaoManager.getInstance().getDaoSession().getCardChangeDao();
        num = cardChangeDao.queryBuilder().where(
                CardChangeDao.Properties.Shift_table_bizday.eq(shiftTableBizday),
                CardChangeDao.Properties.Branch_num.eq(branchNum),
                CardChangeDao.Properties.System_book_code.eq(systemBookCode)).build().list().size();
        return num;
    }

    /**
     * 获取xx班次下的零钱包存入金额
     *
     * @param systemBookCode
     * @param shiftTableNum
     * @param branchNum
     * @return
     */
    public Double getChangeMoney(String systemBookCode, String shiftTableNum, String branchNum) {
        double money = 0;
        CardChangeDao cardChangeDao = DaoManager.getInstance().getDaoSession().getCardChangeDao();
        List<CardChange> list = cardChangeDao.queryBuilder().where(
                CardChangeDao.Properties.Shift_table_num.eq(shiftTableNum),
                CardChangeDao.Properties.Branch_num.eq(branchNum),
                CardChangeDao.Properties.System_book_code.eq(systemBookCode)).build().list();
        for (CardChange cardChange : list) {
            money = money + cardChange.getCard_change_money();
        }
        return money;
    }

    /**
     * 获取xx班次下的零钱包存入金额
     *
     * @param systemBookCode
     * @param shiftTableBizday
     * @param branchNum
     * @return
     */
    public Double getChangeMoneyBizday(String systemBookCode, String shiftTableBizday, int branchNum) {
        double money = 0;
        CardChangeDao cardChangeDao = DaoManager.getInstance().getDaoSession().getCardChangeDao();
        List<CardChange> list = cardChangeDao.queryBuilder().where(
                CardChangeDao.Properties.Shift_table_bizday.eq(shiftTableBizday),
                CardChangeDao.Properties.Branch_num.eq(branchNum),
                CardChangeDao.Properties.System_book_code.eq(systemBookCode)).build().list();
        for (CardChange cardChange : list) {
            money = money + cardChange.getCard_change_money();
        }
        return money;
    }


    /**
     * 获取xx班次下的换卡金额
     *
     * @param systemBookCode
     * @param shiftTableNum
     * @param branchNum
     * @return
     */
    public Double getReplaceCardMoney(String systemBookCode, String shiftTableNum, String branchNum) {
        double money = 0;
        final ReplaceCardDao replaceCardDao = DaoManager.getInstance().getDaoSession().getReplaceCardDao();
        List<ReplaceCard> replaceCardList = replaceCardDao.queryBuilder().where(
                ReplaceCardDao.Properties.System_book_code.eq(systemBookCode),
                ReplaceCardDao.Properties.Shift_table_num.eq(shiftTableNum),
                ReplaceCardDao.Properties.Branch_num.eq(branchNum)
        ).list();
        for (ReplaceCard replaceCard : replaceCardList) {
            money = money + replaceCard.getReplace_card_money();
        }
        return money;
    }

    /**
     * 获取xx营业日下的换卡金额
     *
     * @param systemBookCode
     * @param bizday
     * @param branchNum
     * @return
     */
    public Double getReplaceCardMoneyBizday(String systemBookCode, String bizday, int branchNum) {
        double money = 0;
        final ReplaceCardDao replaceCardDao = DaoManager.getInstance().getDaoSession().getReplaceCardDao();
        List<ReplaceCard> replaceCardList = replaceCardDao.queryBuilder().where(
                ReplaceCardDao.Properties.System_book_code.eq(systemBookCode),
                ReplaceCardDao.Properties.Shift_table_bizday.eq(bizday),
                ReplaceCardDao.Properties.Branch_num.eq(branchNum)
        ).list();
        for (ReplaceCard replaceCard : replaceCardList) {
            money = money + replaceCard.getReplace_card_money();
        }
        return money;
    }


    /**
     * 获取该班次下消费券数量
     *
     * @param systemBookCode
     * @param shiftTableNum
     * @param branchNum
     * @return
     */
    public int getTicketSendDetailNum(String systemBookCode, String shiftTableNum, String branchNum) {
        int num = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.OrderRefBillno.isNull()
        ).list();
        PosOrderDetailDao mPosOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        for (PosOrder posOrder : posOrderList) {
            List<PosOrderDetail> posOrderDetailList = mPosOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).list();
            for (PosOrderDetail posOrderDetail : posOrderDetailList) {
                if (posOrderDetail.getOrderDetailType().equals("消费券")) {
                    num = num + 1;
                }

            }
        }

        return num;
    }

    public int getTicketSendDetailNumBizday(String systemBookCode, String bizday, int branchNum) {
        int num = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.ShiftTableBizday.eq(bizday),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.OrderRefBillno.isNull()
        ).list();
        PosOrderDetailDao mPosOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        for (PosOrder posOrder : posOrderList) {
            List<PosOrderDetail> posOrderDetailList = mPosOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).list();
            for (PosOrderDetail posOrderDetail : posOrderDetailList) {
                if (posOrderDetail.getOrderDetailType().equals("消费券")) {
                    num = num + 1;
                }

            }
        }

        return num;
    }


    public int getTicketSendDetailNumBizday(String systemBookCode, String branchNum) {
        int num = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.OrderRefBillno.isNull()
        ).list();
        PosOrderDetailDao mPosOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        for (PosOrder posOrder : posOrderList) {
            List<PosOrderDetail> posOrderDetailList = mPosOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).list();
            for (PosOrderDetail posOrderDetail : posOrderDetailList) {
                if (posOrderDetail.getOrderDetailType().equals("消费券")) {
                    num = num + 1;
                }

            }
        }

        return num;
    }

    /**
     * 获取该班次下消费券金额  实际抵用金额
     *
     * @param systemBookCode
     * @param shiftTableNum
     * @param branchNum
     * @return
     */
    public float getTicketSendMoney01(String systemBookCode, String shiftTableNum, String branchNum, int state) {
        float money = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.OrderStateCode.eq(state),
                PosOrderDao.Properties.BranchNum.eq(branchNum)
        ).list();
        for (PosOrder posOrder : posOrderList) {
            money = money + posOrder.getOrderCouponTotalMoney();
        }
        return money;
    }

    public float getTicketSendMoney01Bizday(String systemBookCode, String bizday, int branchNum, int state) {
        float money = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.ShiftTableNum.eq(bizday),
                PosOrderDao.Properties.OrderStateCode.eq(state),
                PosOrderDao.Properties.BranchNum.eq(branchNum)
        ).list();
        for (PosOrder posOrder : posOrderList) {
            money = money + posOrder.getOrderCouponTotalMoney();
        }
        return money;
    }

    /**
     * 获取该班次下消费券金额 面值
     *
     * @param systemBookCode
     * @param shiftTableNum
     * @param branchNum
     * @return
     */
    public float getTicketSendMoney(String systemBookCode, String shiftTableNum, String branchNum, int state) {
        float money = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.OrderStateCode.eq(state),
                PosOrderDao.Properties.BranchNum.eq(branchNum)
        ).list();
        for (PosOrder posOrder : posOrderList) {
            money = money + posOrder.getOrderCouponPaymentMoney();
        }
        return money;
    }

    /**
     * 获取该营业日下消费券金额 面值
     *
     * @param systemBookCode
     * @param bizday
     * @param branchNum
     * @return
     */
    public float getTicketSendMoneyBizDay(String systemBookCode, String bizday, int branchNum, int state) {
        float money = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.ShiftTableBizday.eq(bizday),
                PosOrderDao.Properties.OrderStateCode.eq(state),
                PosOrderDao.Properties.BranchNum.eq(branchNum)
        ).list();
        for (PosOrder posOrder : posOrderList) {
            money = money + posOrder.getOrderCouponPaymentMoney();
        }
        return money;
    }


    /**
     * 获取本班次下退单数
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public int getExitOrderNum(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, int orderStateCode) {
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        int amount = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode),
                PosOrderDao.Properties.OrderRefBillno.isNotNull()
        ).list().size();
        int amount1 = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode),
                PosOrderDao.Properties.OrderPaymentMoney.lt(0),
                PosOrderDao.Properties.OrderRefBillno.isNull()
        ).list().size();
        amount = amount1 + amount;
        return amount;
    }

    /**
     * 获取本营业日下退单数
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public int getExitOrderNumBizday(String systemBookCode, int branchNum, String shiftTableBizday, int orderStateCode) {
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        int amount = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode),
                PosOrderDao.Properties.OrderRefBillno.isNotNull()
        ).list().size();
        int amount1 = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode),
                PosOrderDao.Properties.OrderPaymentMoney.lt(0),
                PosOrderDao.Properties.OrderRefBillno.isNull()
        ).list().size();
        amount = amount1 + amount;
        return amount;
    }

    /**
     * 获取本班次下退单金额
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getExitOrderMoney(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, int orderStateCode) {
        float amount = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode),
                PosOrderDao.Properties.OrderRefBillno.isNotNull()
        ).list();//有单

        List<PosOrder> posOrders = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode),
                PosOrderDao.Properties.OrderPaymentMoney.lt(0),
                PosOrderDao.Properties.OrderRefBillno.isNull()
        ).list();//无单
        posOrderList.addAll(posOrders);
        for (PosOrder posOrder : posOrderList) {
            amount = amount + posOrder.getOrderPaymentMoney() - posOrder.getOrderMgrDiscountMoney();
        }
        return amount;
    }

    /**
     * 获取营业日次下退单金额
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getExitOrderMoneyBizday(String systemBookCode, int branchNum, String shiftTableBizday, int orderStateCode) {
        float amount = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode),
                PosOrderDao.Properties.OrderRefBillno.isNotNull()
        ).list();//有单

        List<PosOrder> posOrders = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode),
                PosOrderDao.Properties.OrderPaymentMoney.lt(0),
                PosOrderDao.Properties.OrderRefBillno.isNull()
        ).list();//无单
        posOrderList.addAll(posOrders);
        for (PosOrder posOrder : posOrderList) {
            amount = amount + posOrder.getOrderPaymentMoney() - posOrder.getOrderMgrDiscountMoney();
        }
        return amount;
    }

    /**
     * 获取本班次会员折扣合计
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getOrderVipDiscountMoney(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, int orderStateCode) {
        float amount = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrderDetailDao mPosOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode)
//                PosOrderDao.Properties.OrderRefBillno.isNull()
        ).list();
        for (PosOrder posOrder : posOrderList) {
            List<PosOrderDetail> posOrderDetails = mPosOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).build().list();
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM)) {
                    if (!posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_PRESENT_NAME) && posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_VIP_TAG)
                            && !posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_CHANGE_TAG)
                            && TextUtils.isEmpty(posOrderDetail.getOrderDetailPolicyFid())) {
                        if (posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_RETURN_NAME)) {
                            amount -= (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                        } else {
                            amount += (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                        }

                    }
                }
            }
        }
        return amount;
    }

    /**
     * 获取本营业日会员折扣合计
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getOrderVipDiscountMoneyBizday(String systemBookCode, int branchNum, String shiftTableBizday, int orderStateCode) {
        float amount = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrderDetailDao mPosOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode)
//                PosOrderDao.Properties.OrderRefBillno.isNull()
        ).list();
        for (PosOrder posOrder : posOrderList) {
            List<PosOrderDetail> posOrderDetails = mPosOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).build().list();
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (!posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_PRESENT_NAME) && posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM)) {
                    if (posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_VIP_TAG) && !posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_CHANGE_TAG)
                            && TextUtils.isEmpty(posOrderDetail.getOrderDetailPolicyFid())) {
                        amount += (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                    }
                }
            }
        }
        return amount;
    }


    /**
     * 获取本班次手改几个总和
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getOrderModifyPriceDiscountMoney(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, int orderStateCode) {
        float amount = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrderDetailDao mPosOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode),
                PosOrderDao.Properties.OrderRefBillno.isNull()
        ).list();//销售单+无单

        List<PosOrder> exitPosOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode),
                PosOrderDao.Properties.OrderRefBillno.isNotNull()
        ).list();//有单退货
        for (PosOrder posOrder : exitPosOrderList) {
            List<PosOrderDetail> posOrderDetails = mPosOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).build().list();
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM) && !posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_PRESENT_NAME)) {
//                    if(!posOrderDetail.getOrderDetailMemo().equals(LibConfig.GOODS_VIP_TAG) && posOrderDetail.getOrderDetailMemo().equals(LibConfig.GOODS_CHANGE_TAG)
//                            && !posOrderDetail.getOrderDetailPolicyPromotionFlag()){
                    if (posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_CHANGE_TAG)) {
                        amount -= (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                    }
                }
            }
        }

        for (PosOrder posOrder : posOrderList) {
            List<PosOrderDetail> posOrderDetails = mPosOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).build().list();
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM) && !posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_PRESENT_NAME)) {
//                    if(!posOrderDetail.getOrderDetailMemo().equals(LibConfig.GOODS_VIP_TAG) && posOrderDetail.getOrderDetailMemo().equals(LibConfig.GOODS_CHANGE_TAG)
//                            && !posOrderDetail.getOrderDetailPolicyPromotionFlag()){
                    if (posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_CHANGE_TAG)) {
                        amount += (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                    }
                } else if (posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_RETURN_NAME) && !posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_PRESENT_NAME)) {
                    amount -= (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                }
            }
        }
        return amount;
    }


    /**
     * 获取本营业日手改几个总和
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getOrderModifyPriceDiscountMoneyBizday(String systemBookCode, int branchNum, String shiftTableBizday, int orderStateCode) {
        float amount = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrderDetailDao mPosOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode),
                PosOrderDao.Properties.OrderRefBillno.isNull()
        ).list();//销售单+无单

        List<PosOrder> exitPosOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode),
                PosOrderDao.Properties.OrderRefBillno.isNotNull()
        ).list();//有单退货
        for (PosOrder posOrder : exitPosOrderList) {
            List<PosOrderDetail> posOrderDetails = mPosOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).build().list();
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM) && !posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_PRESENT_NAME)) {
//                    if(!posOrderDetail.getOrderDetailMemo().equals(LibConfig.GOODS_VIP_TAG) && posOrderDetail.getOrderDetailMemo().equals(LibConfig.GOODS_CHANGE_TAG)
//                            && !posOrderDetail.getOrderDetailPolicyPromotionFlag()){
                    if (posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_CHANGE_TAG)) {
                        amount -= (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                    }
                }
            }
        }

        for (PosOrder posOrder : posOrderList) {
            List<PosOrderDetail> posOrderDetails = mPosOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).build().list();
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM) && !posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_PRESENT_NAME)) {
//                    if(!posOrderDetail.getOrderDetailMemo().equals(LibConfig.GOODS_VIP_TAG) && posOrderDetail.getOrderDetailMemo().equals(LibConfig.GOODS_CHANGE_TAG)
//                            && !posOrderDetail.getOrderDetailPolicyPromotionFlag()){
                    if (posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_CHANGE_TAG)) {
                        amount += (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                    }
                } else if (posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_RETURN_NAME) && !posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_PRESENT_NAME)) {
                    amount -= (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                }
            }
        }
        return amount;
    }

    /**
     * 获取本班次促销几个总和
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public Map<String, Float> getOrderPromotionDiscountMoney(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, int orderStateCode) {

        Map<String, Float> map = new HashMap<>();
        float vipMoney = 0;
        float modifyMoney = 0;
        float promoMoney = 0;
        float ticketMoney = 0;
        float mgrMoney = 0;
        float feeMoney = 0;


        float amount = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrderDetailDao mPosOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode)
//                PosOrderDao.Properties.OrderRefBillno.isNull()
        ).list();

        for (PosOrder posOrder : posOrderList) {
            List<PosOrderDetail> posOrderDetails = mPosOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).build().list();
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM)) {//正常订单
                    if (posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_RETURN_NAME) || posOrderDetail.getOrderDetailAmount() < 0) {//退货单

                        if (posOrderDetail.getOrderDetailAmount() < 0) {//赠送
                            feeMoney += posOrderDetail.getOrderDetailPaymentMoney();
                        } else if (posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_CHANGE_TAG)) {//手改
                            modifyMoney -= (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                        } else if (posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_VIP_TAG)) {//会员
                            vipMoney -= (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                        } else {//促销
                            promoMoney -= (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                        }

                    } else {//销售单
                        if (posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_PRESENT_NAME)) {//赠送
                            feeMoney += posOrderDetail.getOrderDetailPaymentMoney();
                        } else if (posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_CHANGE_TAG)) {//手改
                            modifyMoney += (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                        } else if (posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_VIP_TAG)) {//会员
                            vipMoney += (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                        } else {//促销
                            promoMoney += (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                        }

                    }

                } else {//优惠券
                    ticketMoney += posOrder.getOrderCouponTotalMoney();
                }
            }
            mgrMoney += posOrder.getOrderMgrDiscountMoney();//经理折扣
        }


        map.put(LibConfig.GOODS_VIP_TAG, vipMoney);
        map.put(LibConfig.GOODS_CHANGE_TAG, modifyMoney);
        map.put(LibConfig.GOODS_PROMO_TAG, promoMoney);
        map.put(LibConfig.TICKET_TAG, ticketMoney);
        map.put(LibConfig.MGR_TAG, mgrMoney);
        map.put(LibConfig.S_ORDER_DETAIL_PRESENT_NAME, feeMoney);

        for (PosOrder posOrder : posOrderList) {
            List<PosOrderDetail> posOrderDetails = mPosOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).build().list();
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM)) {
                    if (!posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_VIP_TAG) && !posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_CHANGE_TAG)
                            && posOrderDetail.getOrderDetailAmount() > 0 && !TextUtils.isEmpty(posOrderDetail.getOrderDetailPolicyFid())
                            && !posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_RETURN_NAME)) {
                        amount += (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                    } else if (!posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_VIP_TAG) && !posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_CHANGE_TAG)
                            && posOrderDetail.getOrderDetailAmount() > 0 && !TextUtils.isEmpty(posOrderDetail.getOrderDetailPolicyFid())
                            && posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_RETURN_NAME)) {
                        amount -= (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                    }
                }
            }
        }
        return map;
    }

    /**
     * 获取本班次促销几个总和
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getOrderPromotionDiscountMoneyBizday(String systemBookCode, int branchNum, String shiftTableBizday, int orderStateCode) {
        float amount = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrderDetailDao mPosOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode),
                PosOrderDao.Properties.OrderRefBillno.isNull()
        ).list();
        for (PosOrder posOrder : posOrderList) {
            List<PosOrderDetail> posOrderDetails = mPosOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).build().list();
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM)) {
                    if (!posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_VIP_TAG) && !posOrderDetail.getOrderDetailMemo().contains(LibConfig.GOODS_CHANGE_TAG)
                            && (posOrderDetail.getOrderDetailPolicyPromotionFlag() || posOrderDetail.getOrderDetailPolicyDiscountFlag())) {
                        amount += (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() - posOrderDetail.getOrderDetailPaymentMoney());
                    }
                }
            }
        }
        return amount;
    }

    /**
     * 商品合计：所有商品标价总和
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getOrderNoDiscountMoney(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, int orderStateCode) {
        float amount = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrderDetailDao mPosOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode)
        ).list();

        for (PosOrder posOrder : posOrderList) {
            List<PosOrderDetail> posOrderDetails = mPosOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).build().list();
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM)) {
                    if (posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_PRESENT_NAME)) {
                        amount = amount + posOrderDetail.getOrderDetailPaymentMoney();
                    } else {
                        if (posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_RETURN_NAME)) {
                            amount = amount - (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() + (posOrderDetail.getOrderDetailRound() == null ? 0 : posOrderDetail.getOrderDetailRound()));
                        } else {
                            amount = amount + (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice() + (posOrderDetail.getOrderDetailRound() == null ? 0 : posOrderDetail.getOrderDetailRound()));
                        }
                    }


                }
            }
        }


        return amount;
    }


    /**
     * 商品合计：所有商品标价总和
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getOrderNoDiscountMoneyBizday(String systemBookCode, int branchNum, String shiftTableBizday, int orderStateCode) {
        float amount = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrderDetailDao mPosOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode)
        ).list();

        for (PosOrder posOrder : posOrderList) {
            List<PosOrderDetail> posOrderDetails = mPosOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).build().list();
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM)) {
                    if (posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_PRESENT_NAME)) {
                        if (posOrderDetail.getOrderDetailAmount() < 0) {
                            amount = amount - posOrderDetail.getOrderDetailPaymentMoney();
                        } else {
                            amount = amount + posOrderDetail.getOrderDetailPaymentMoney();
                        }
                    } else {
                        if (posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_RETURN_NAME)) {
                            amount = amount - (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice());
                        } else {
                            amount = amount + (posOrderDetail.getOrderDetailAmount() * posOrderDetail.getOrderDetailStdPrice());
                        }
                    }

                }
            }
        }
        return amount;
    }


    public List<Payment> getPaymentList(String OrderNo) {
        PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
        return paymentDao.queryBuilder().where(PaymentDao.Properties.OrderNo.eq(OrderNo)).list();
    }

    /**
     * 获取订单抵扣的优惠劵
     *
     * @param OrderNo
     * @return
     */
    public List<PosOrderDetail> getCouponsDetailList(String OrderNo) {
        PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();

        return posOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(OrderNo),
                PosOrderDetailDao.Properties.OrderDetailType.eq(LibConfig.C_ORDER_DETAIL_TYPE_COUPON),
                PosOrderDetailDao.Properties.PaymentBalance.notEq(0)).list();
    }

    public int getFeeNum(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, int orderStateCode) {
        int num = 0;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrderDetailDao mPosOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode)
        ).list();


        for (PosOrder posOrder : posOrderList) {
            List<PosOrderDetail> posOrderDetails = mPosOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).build().list();
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (posOrderDetail.getOrderDetailType().equals(LibConfig.S_ORDER_DETAIL_PRESENT_NAME)) {
                    if (posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_RETURN_NAME)) {
                        num = num - 1;
                    } else {
                        num = num + 1;
                    }

                }
            }
        }
        return num;
    }

    public float getFeeMoney(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, int orderStateCode) {
        float num = 0l;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrderDetailDao mPosOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode)
        ).list();


        for (PosOrder posOrder : posOrderList) {
            List<PosOrderDetail> posOrderDetails = mPosOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).build().list();
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_PRESENT_NAME)) {
//                    if (posOrderDetail.getOrderDetailStateName().contains(LibConfig.S_ORDER_DETAIL_RETURN_NAME)) {
                    if (posOrderDetail.getOrderDetailAmount() < 0) {
                        num = num - posOrderDetail.getOrderDetailPaymentMoney();
                    } else {
                        num = num + posOrderDetail.getOrderDetailPaymentMoney();
                    }

//                    }
//                    }else {
//                        num=num+posOrderDetail.getOrderDetailPaymentMoney();
//                    }

                }
            }
        }
        return num;
    }

    /**
     * 赠送
     *
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableBizday
     * @param orderStateCode
     * @return
     */
    public float getFeeMoneyBizday(String systemBookCode, int branchNum, String shiftTableBizday, int orderStateCode) {
        float num = 0l;
        PosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        PosOrderDetailDao mPosOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
        List<PosOrder> posOrderList = mPosOrderDao.queryBuilder().where(
                PosOrderDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDao.Properties.BranchNum.eq(branchNum),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                PosOrderDao.Properties.OrderStateCode.eq(orderStateCode)
        ).list();


        for (PosOrder posOrder : posOrderList) {
            List<PosOrderDetail> posOrderDetails = mPosOrderDetailDao.queryBuilder().where(PosOrderDetailDao.Properties.OrderNo.eq(posOrder.getOrderNo())).build().list();
            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                if (posOrderDetail.getOrderDetailStateName().equals(LibConfig.S_ORDER_DETAIL_PRESENT_NAME)) {
                    if (posOrderDetail.getOrderDetailAmount() < 0) {
                        num = num - posOrderDetail.getOrderDetailPaymentMoney();
                    } else {
                        num = num + posOrderDetail.getOrderDetailPaymentMoney();
                    }
                }
            }
        }
        return num;
    }


    /**
     * 获取订单的
     * @param posOrder
     * @param branchName
     * @param systemBookName
     * @param vipCardConfig
     * @return
     */
    public String getPosOrderToJson(PosOrder posOrder, String branchName, String systemBookName,VipCardConfig vipCardConfig) {
        JSONObject totalInfoObject = new JSONObject();
        JSONObject posOrderObject = new JSONObject();
        JSONObject posOrderDetailObject = new JSONObject();
        JSONObject posOrderKitObject = new JSONObject();
        JSONObject paymentObject = new JSONObject();
        JSONArray posOrderDetailList = new JSONArray();
        JSONArray paymentJsonList = new JSONArray();
        posOrderObject = new JSONObject();
        posOrderDetailList = new JSONArray();
        paymentJsonList = new JSONArray();
        try {
            totalInfoObject.put("branch_name", branchName);
            totalInfoObject.put("book_name", systemBookName);

            posOrderObject.put("order_no", posOrder.getOrderNo());
            posOrderObject.put("order_pay_no", posOrder.getOrderPayNo());
            posOrderObject.put("storehouse_num", posOrder.getStorehouseNum());
            posOrderObject.put("system_book_code", posOrder.getSystemBookCode());
            posOrderObject.put("branch_num", posOrder.getBranchNum());
            posOrderObject.put("shift_table_num", posOrder.getShiftTableNum());
            posOrderObject.put("shift_table_bizday", posOrder.getShiftTableBizday());

            posOrderObject.put("order_date", posOrder.getOrderDate());
            posOrderObject.put("order_sold_by", posOrder.getOrderSoldBy() == null ? "" : posOrder.getOrderSoldBy());

            posOrderObject.put("order_operator", posOrder.getOrderOperator());
            posOrderObject.put("order_operate_time", posOrder.getOrderOperateTime());
            posOrderObject.put("order_printed_num", posOrder.getOrderPrintedNum());
            posOrderObject.put("order_card_user", posOrder.getOrderCardUser());

            posOrderObject.put("order_card_type_desc", posOrder.getOrderCardTypeDesc());
            posOrderObject.put("order_discount_money",  NumberUtil.getNewFloatString(posOrder.getOrderDiscountMoney()));
            posOrderObject.put("order_commission", posOrder.getOrderCommission());

            posOrderObject.put("order_total_money", NumberUtil.getNewFloatString(posOrder.getOrderTotalMoney()));
            posOrderObject.put("order_payment_money", NumberUtil.getNewFloatString(posOrder.getOrderPaymentMoney()));
            posOrderObject.put("order_round", NumberUtil.getNewFloatString(posOrder.getOrderRound()));

            posOrderObject.put("order_balance", posOrder.getOrderBalance());
            posOrderObject.put("order_total_invoice", posOrder.getOrderTotalInvoice());
            posOrderObject.put("order_change",  NumberUtil.getNewFloatString(posOrder.getOrderChange()));
            posOrderObject.put("order_time", posOrder.getOrderTime());
            posOrderObject.put("order_machine", posOrder.getOrderMachine());

            posOrderObject.put("order_payee", posOrder.getOrderPayee());
            posOrderObject.put("order_state_code", posOrder.getOrderStateCode());
            posOrderObject.put("order_state_name", posOrder.getOrderStateName());

            posOrderObject.put("order_memo", posOrder.getOrderMemo());
            posOrderObject.put("order_ref_billno", posOrder.getOrderRefBillno());
            posOrderObject.put("order_point", posOrder.getOrderPoint());
            posOrderObject.put("order_gross_profit", posOrder.getOrderGrossProfit());
            posOrderObject.put("order_mgr_discount_money", NumberUtil.getNewFloatString(posOrder.getOrderMgrDiscountMoney()));

            posOrderObject.put("order_coupon_total_money", NumberUtil.getNewFloatString(posOrder.getOrderCouponTotalMoney()));
            posOrderObject.put("order_coupon_payment_money", NumberUtil.getNewFloatString(posOrder.getOrderCouponPaymentMoney()));
            posOrderObject.put("order_card_user_num", posOrder.getOrderCardUserNum() == 1 ? null : posOrder.getOrderCardUserNum());
            posOrderObject.put("order_card_type", posOrder.getOrderCardType());

            posOrderObject.put("order_source", posOrder.getOrderSource());
            posOrderObject.put("order_post_fee", posOrder.getOrderPostFee());
            posOrderObject.put("order_promotion_discount_money",  NumberUtil.getNewFloatString(posOrder.getOrderPromotionDiscountMoney()));
            posOrderObject.put("order_external_no", posOrder.getOrderExternalNo());
            posOrderObject.put("order_card_change", posOrder.getOrderCardChange());
            posOrderObject.put("order_tax_money", posOrder.getOrderTaxMoney());
            posOrderObject.put("order_online_discount", posOrder.getOrderOnlineDiscount());
            posOrderObject.put("order_detail_item_count", posOrder.getOrderDetailItemCount());

            if (posOrder.getMerchantNum() > 0) {
                posOrderObject.put("merchant_num", posOrder.getMerchantNum());
                posOrderObject.put("stall_num", posOrder.getStallNum());
            }
            posOrderObject.put("order_cost_money", posOrder.getOrderCostMoney());
            posOrderObject.put("order_card_phone", posOrder.getOrderCardPhone());
            posOrderObject.put("client_fid", posOrder.getClientFid());
            posOrderObject.put("alipay_user_id", posOrder.getAlipayUserId());
            posOrderObject.put("open_id", posOrder.getOpenId());
            posOrderObject.put("customer_id", posOrder.getCustomerId());

            List<PosOrderDetail> posOrderDetails = DataSynchronousImpl.getInstance().
                    getPosOrderDetailList(posOrder.getSystemBookCode(), posOrder.getBranchNum(), posOrder.getOrderNo());

            for (PosOrderDetail posOrderDetail : posOrderDetails) {
                posOrderDetailObject = new JSONObject();
                posOrderDetailObject.put("order_no", posOrderDetail.getOrderNo());
                posOrderDetailObject.put("order_detail_num", posOrderDetail.getOrderDetailNum());
                if (!posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_COUPON)) {
                    posOrderDetailObject.put("item_num", posOrderDetail.getItemNum());
                }
                posOrderDetailObject.put("order_detail_type", posOrderDetail.getOrderDetailType());
                posOrderDetailObject.put("order_detail_item", posOrderDetail.getOrderDetailItem());

                posOrderDetailObject.put("order_detail_item_department", posOrderDetail.getOrderDetailItemDepartment());
                posOrderDetailObject.put("order_detail_std_price", posOrderDetail.getOrderDetailStdPrice());
                posOrderDetailObject.put("order_detail_price", NumberUtil.getNewFloatString(posOrderDetail.getOrderDetailPrice()));
                posOrderDetailObject.put("order_detail_amount", NumberUtil.getNewFloatLongString((double) posOrderDetail.getOrderDetailAmount()));
                posOrderDetailObject.put("order_detail_online_qty", NumberUtil.getNewFloatLongString((double) posOrderDetail.getOrderDetailAmount()));
                posOrderDetailObject.put("order_detail_discount", posOrderDetail.getOrderDetailDiscount());

                posOrderDetailObject.put("order_detail_money", NumberUtil.getNewFloatString(posOrderDetail.getOrderDetailMoney()));
                posOrderDetailObject.put("order_detail_payment_money", NumberUtil.getNewFloatString(posOrderDetail.getOrderDetailPaymentMoney()));
                posOrderDetailObject.put("order_detail_state_code", posOrderDetail.getOrderDetailStateCode());
                posOrderDetailObject.put("order_detail_state_name", posOrderDetail.getOrderDetailStateName());
                posOrderDetailObject.put("order_detail_commission", posOrderDetail.getOrderDetailCommission());
                posOrderDetailObject.put("order_detail_policy_promotion_flag", posOrderDetail.getOrderDetailPolicyPromotionFlag());
                posOrderDetailObject.put("order_detail_policy_discount_flag", posOrderDetail.getOrderDetailPolicyDiscountFlag());
                posOrderDetailObject.put("order_detail_policy_promotion_money_flag", posOrderDetail.getOrderDetailPolicyPromotionMoneyFlag());
                posOrderDetailObject.put("order_detail_policy_promotion_quantity_flag", posOrderDetail.getOrderDetailPolicyPromotionQuantityFlag());
                posOrderDetailObject.put("order_detail_policy_present_flag", posOrderDetail.getOrderDetailPolicyPresentFlag());
                posOrderDetailObject.put("order_detail_online_unit", posOrderDetail.getOrderDetailOnlineUnit());

                posOrderDetailObject.put("order_detail_cost", posOrderDetail.getOrderDetailCost());
                posOrderDetailObject.put("order_detail_ticket_uuid", posOrderDetail.getOrderDetailTicketUuid());
                posOrderDetailObject.put("order_detail_bizday", posOrder.getShiftTableBizday());
                posOrderDetailObject.put("item_grade_num", posOrderDetail.getItemGradeNum());
                posOrderDetailObject.put("order_detail_has_kit", posOrderDetail.getOrderDetailHasKit());
                posOrderDetailObject.put("order_detail_policy_fid", posOrderDetail.getOrderDetailPolicyFid());
                posOrderDetailObject.put("order_detail_memo", posOrderDetail.getOrderDetailMemo() == null ? "" : posOrderDetail.getOrderDetailMemo());
                posOrderDetailObject.put("order_detail_lot_number", posOrderDetail.getOrderDetailLotNumber() == null ? "" : posOrderDetail.getOrderDetailLotNumber());
                posOrderDetailObject.put("stall_num", posOrderDetail.getStall_num());
                posOrderDetailObject.put("order_detail_share_discount", posOrderDetail.getOrderDetailShareDiscount());
                if (posOrder.getMerchantNum() > 0 && posOrderDetail.getOrder_detail_merchat_rate() != null) {
                    posOrderDetailObject.put("order_detail_merchant_rate", posOrderDetail.getOrder_detail_merchat_rate());
                }


                List<PosOrderKitDetail> posOrderKitDetailList = posOrderDetail.getPosOrderKitDetails();
                JSONArray posOrderKitList = new JSONArray();
                for (PosOrderKitDetail posOrderKitDetail : posOrderKitDetailList) {
                    posOrderKitObject = new JSONObject();


                    posOrderKitObject.put("order_no", posOrderKitDetail.getOrderNo());
                    posOrderKitObject.put("order_detail_num", posOrderKitDetail.getOrderDetailNum());
                    posOrderKitObject.put("order_kit_detail_num", posOrderKitDetail.getOrderKitDetailNum());
                    posOrderKitObject.put("item_num", posOrderKitDetail.getItemNum());
                    posOrderKitObject.put("order_kit_detail_item_name", posOrderKitDetail.getOrderKitDetailItemName());
                    posOrderKitObject.put("order_kit_detail_department", posOrderKitDetail.getOrderKitDetailDepartment());
                    posOrderKitObject.put("order_kit_detail_std_price", posOrderKitDetail.getOrderKitDetailStdPrice());
                    posOrderKitObject.put("order_kit_detail_price", posOrderKitDetail.getOrderKitDetailPrice());
                    posOrderKitObject.put("order_kit_detail_amount", posOrderKitDetail.getOrderKitDetailAmount());
                    posOrderKitObject.put("order_kit_detail_money", posOrderKitDetail.getOrderKitDetailMoney());
                    posOrderKitObject.put("order_kit_detail_append_money", posOrderKitDetail.getOrderKitDetailAppendMoney());
                    posOrderKitObject.put("order_kit_detail_discount", NumberUtil.getNewFloatString(posOrderKitDetail.getOrderKitDetailDiscount()));
                    posOrderKitObject.put("order_kit_detail_tax", posOrderKitDetail.getOrderKitDetailTax());
                    posOrderKitObject.put("order_kit_detail_payment_money", posOrderKitDetail.getOrderKitDetailPaymentMoney());

                    posOrderKitObject.put("order_kit_detail_book_code", posOrderKitDetail.getOrderKitDetailBookCode());
                    posOrderKitObject.put("order_kit_detail_branch_num", posOrderKitDetail.getOrderKitDetailBranchNum());

                    posOrderKitObject.put("order_kit_detail_order_state", posOrderKitDetail.getOrderKitDetailOrderState());
                    posOrderKitObject.put("order_kit_detail_state_code", posOrderKitDetail.getOrderKitDetailStateCode());
                    posOrderKitObject.put("order_kit_detail_share_discount", posOrderKitDetail.getOrderKitDetailShareDiscount());
                    posOrderKitList.put(posOrderKitObject);
                }
                posOrderDetailObject.put("pos_order_kit_details", posOrderKitList);
                posOrderDetailList.put(posOrderDetailObject);
//**
            }
            boolean needRefresh = false;
            String wechatOpenId = null;
            String wechatPrintNum = null;
            String wechatCustNum = null;
            String consume_money = null;
            String consume_balance = null;
            int  remind_type = 1;

            if ((!TextUtils.isEmpty(posOrder.getOpenId()) || !TextUtils.isEmpty(posOrder.getOrderPrintedNum())) && posOrder.getVipUserInfo() != null) {
                VipUserInfo vipUserInfo = posOrder.getVipUserInfo();
                needRefresh = true;
                wechatOpenId = posOrder.getOpenId();
                wechatPrintNum = posOrder.getOrderPrintedNum();
                wechatCustNum = String.valueOf(posOrder.getOrderCardUserNum());
                consume_money = NumberUtil.getNewFloatString(posOrder.getOrderPaymentMoney()-posOrder.getOrderMgrDiscountMoney());
                consume_balance = TextUtils.isEmpty(vipUserInfo.getCard_balance()) ?
                        "0.00" : NumberUtil.getNewFloatString(Float.parseFloat(vipUserInfo.getCard_balance()));
//                consume_balance = NumberUtil.getNewFloatString(payment.getPaymentCardBalance() - payment.getPaymentMoney());
                totalInfoObject.put("consume_balance",consume_balance);
                totalInfoObject.put("consume_money",consume_money);
            }



            List<Payment> paymentList = DataSynchronousImpl.getInstance().getPaymentList
                    (posOrder.getSystemBookCode(), posOrder.getBranchNum(), posOrder.getOrderNo());
            for (Payment payment : paymentList) {
                paymentObject = new JSONObject();
                paymentObject.put("payment_no", payment.getPaymentNo());
                paymentObject.put("payment_pay_by", payment.getPaymentPayBy());
                paymentObject.put("payment_money", payment.getPaymentMoney());
                paymentObject.put("order_no", payment.getOrderNo());
                paymentObject.put("payment_buyer_money", payment.getPaymentBuyerMoney());
                paymentObject.put("payment_time", payment.getPaymentTime());
                paymentObject.put("payment_receipt_money", payment.getPaymentReceiptMoney());
                paymentObject.put("payment_cust_num", payment.getPaymentCustNum() == 0 ? "" : payment.getPaymentCustNum());
                paymentObject.put("payment_bill_no", payment.getPaymentBillNo());
                paymentObject.put("payment_time", payment.getPaymentTime());

                paymentObject.put("payment_receive", payment.getPaymentReceive());
                paymentObject.put("payment_change", payment.getPaymentChange());
                paymentObject.put("payment_paid", payment.getPaymentPaid());
                paymentObject.put("payment_balance", payment.getPaymentBalance());
                paymentObject.put("payment_machine", payment.getPaymentMachine());
                paymentObject.put("payment_memo", payment.getPaymentMemo());
                paymentObject.put("payment_acct_no", payment.getPaymentAcctNo());
                paymentObject.put("payment_auditor", payment.getPaymentAuditor());
                paymentObject.put("payment_card_balance", payment.getPaymentCardBalance());
                paymentObject.put("payment_consume_count", payment.getPaymentConsumeCount());
                paymentObject.put("payment_date", payment.getPaymentDate());
                paymentObject.put("payment_lastest_date", payment.getPaymentLastestDate());
                if(LibConfig.C_PAYMENT_TYPE_PETCARD_NAME.equals(payment.getPaymentPayBy()) && posOrder.getOrderCardUserNum() == payment.getPaymentCustNum()){
                    remind_type = 0;
                }

                if (payment.getMerchantNum() > 0) {
                    paymentObject.put("merchant_num", payment.getMerchantNum());
                    paymentObject.put("stall_num", payment.getStallNum());
                    paymentObject.put("consume_merchant_flag", false);
                }

                if (payment.getAccountBankNum() == 0) {
                    paymentObject.put("account_bank_num", "");
                } else {
                    paymentObject.put("account_bank_num", payment.getAccountBankNum());
                }
                paymentObject.put("payment_point", payment.getPaymentPoint());
                paymentObject.put("payment_online_un_discount", payment.getPaymentOnlineUnDiscount());
                paymentObject.put("payment_round", payment.getPaymentRound());
                paymentObject.put("client_fid", payment.getClientFid());
                paymentJsonList.put(paymentObject);
            }

            posOrderObject.put("payments", paymentJsonList);
            posOrderObject.put("pos_order_details", posOrderDetailList);
            totalInfoObject.put("upload_iwm", false);
//            VipCardConfig vipCardConfigBean = getVipCardTypeBean(Constant.SYSTEM_BOOK_CODE);
            if (vipCardConfig == null || "0".equals(vipCardConfig.getConsumeWeChatHint())) {
                remind_type = 2;
            }
            totalInfoObject.put("remind_type", remind_type);

            totalInfoObject.put("need_refresh", needRefresh);
            if (needRefresh && !TextUtils.isEmpty(wechatOpenId)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("card_user_open_id", wechatOpenId);
                jsonObject.put("card_user_printed_num", wechatPrintNum);
                jsonObject.put("card_user_num", wechatCustNum);
                totalInfoObject.put("card_user", jsonObject);
            }
            totalInfoObject.put("pos_order", posOrderObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return totalInfoObject.toString();
    }


    /**
     * 获取一组订单的JSON
     * @param posOrderList  订单JSON
     * @return 返回一组订单的JSON
     */
    public String getPosOrderListToJson(List<PosOrder> posOrderList) {
        JSONObject posOrderObject = new JSONObject();
        JSONObject posOrderDetailObject = new JSONObject();
        JSONObject posOrderKitObject = new JSONObject();
        JSONObject paymentObject = new JSONObject();
        JSONArray posOrderListArray = new JSONArray();
        JSONArray posOrderDetailList = new JSONArray();
        JSONArray paymentJsonList = new JSONArray();
        for (PosOrder posOrder : posOrderList) {
            posOrderObject = new JSONObject();
            posOrderDetailList = new JSONArray();
            paymentJsonList = new JSONArray();
            try {
                if (LibConfig.activeLoginBean != null && !TextUtils.isEmpty(LibConfig.activeLoginBean.getBranch_id())) {
                    posOrderObject.put("branch_id", LibConfig.activeLoginBean.getBranch_id());
                }
                posOrderObject.put("order_no", posOrder.getOrderNo());
                posOrderObject.put("storehouse_num", posOrder.getStorehouseNum());
                posOrderObject.put("system_book_code", posOrder.getSystemBookCode());
                posOrderObject.put("branch_num", posOrder.getBranchNum());
                posOrderObject.put("shift_table_num", posOrder.getShiftTableNum());
                posOrderObject.put("shift_table_bizday", posOrder.getShiftTableBizday());

                posOrderObject.put("order_date", posOrder.getOrderDate());
                posOrderObject.put("order_sold_by", posOrder.getOrderSoldBy() == null ? "" : posOrder.getOrderSoldBy());

                posOrderObject.put("order_operator", posOrder.getOrderOperator());
                posOrderObject.put("order_operate_time", posOrder.getOrderOperateTime());
                posOrderObject.put("order_printed_num", posOrder.getOrderPrintedNum());
                posOrderObject.put("order_card_user", posOrder.getOrderCardUser());

                posOrderObject.put("order_card_type_desc", posOrder.getOrderCardTypeDesc());
                posOrderObject.put("order_discount_money", NumberUtil.getNewFloatString(posOrder.getOrderDiscountMoney()));
                posOrderObject.put("order_commission", posOrder.getOrderCommission());

                posOrderObject.put("order_total_money", NumberUtil.getNewFloatString(posOrder.getOrderTotalMoney()));
                posOrderObject.put("order_payment_money", NumberUtil.getNewFloatString(posOrder.getOrderPaymentMoney()));
                posOrderObject.put("order_round", NumberUtil.getNewFloatString(posOrder.getOrderRound()));

                posOrderObject.put("order_balance",  NumberUtil.getNewFloatString(posOrder.getOrderBalance()));
                posOrderObject.put("order_total_invoice", posOrder.getOrderTotalInvoice());
                posOrderObject.put("order_change", posOrder.getOrderChange());
                posOrderObject.put("order_time", posOrder.getOrderTime());
                posOrderObject.put("order_machine", posOrder.getOrderMachine());

                posOrderObject.put("order_payee", posOrder.getOrderPayee());
                posOrderObject.put("order_state_code", posOrder.getOrderStateCode());
                posOrderObject.put("order_state_name", posOrder.getOrderStateName());

                posOrderObject.put("order_memo", posOrder.getOrderMemo());
                posOrderObject.put("order_ref_billno", posOrder.getOrderRefBillno());
                posOrderObject.put("order_point", posOrder.getOrderPoint());
                posOrderObject.put("order_gross_profit", posOrder.getOrderGrossProfit());
                posOrderObject.put("order_mgr_discount_money", NumberUtil.getNewFloatString(posOrder.getOrderMgrDiscountMoney()));

                posOrderObject.put("order_coupon_total_money", NumberUtil.getNewFloatString(posOrder.getOrderCouponTotalMoney()));
                posOrderObject.put("order_coupon_payment_money", NumberUtil.getNewFloatString(posOrder.getOrderCouponPaymentMoney()));
                posOrderObject.put("order_card_user_num", posOrder.getOrderCardUserNum() == 1 ? null : posOrder.getOrderCardUserNum());
                posOrderObject.put("order_card_type", posOrder.getOrderCardType());
                posOrderObject.put("order_external_no", posOrder.getOrderExternalNo());

                posOrderObject.put("order_source", posOrder.getOrderSource());
                posOrderObject.put("order_post_fee", posOrder.getOrderPostFee());
                posOrderObject.put("order_promotion_discount_money",  NumberUtil.getNewFloatString(posOrder.getOrderPromotionDiscountMoney()));
                posOrderObject.put("order_card_change",  NumberUtil.getNewFloatString(posOrder.getOrderCardChange()));
                posOrderObject.put("order_tax_money", posOrder.getOrderTaxMoney());
                posOrderObject.put("order_online_discount", posOrder.getOrderOnlineDiscount());
                posOrderObject.put("order_detail_item_count", posOrder.getOrderDetailItemCount());
                if (posOrder.getMerchantNum() > 0) {
                    posOrderObject.put("merchant_num", posOrder.getMerchantNum());
                    posOrderObject.put("stall_num", posOrder.getStallNum());
                }
                posOrderObject.put("order_cost_money", posOrder.getOrderCostMoney());
                posOrderObject.put("order_card_phone", posOrder.getOrderCardPhone());
                posOrderObject.put("client_fid", posOrder.getClientFid());
                posOrderObject.put("alipay_user_id", posOrder.getAlipayUserId());
                posOrderObject.put("open_id", posOrder.getOpenId());
                posOrderObject.put("customer_id", posOrder.getCustomerId());
                posOrderObject.put("order_pay_no", posOrder.getOrderPayNo());

                List<PosOrderDetail> posOrderDetails = DataSynchronousImpl.getInstance().
                        getPosOrderDetailList(posOrder.getSystemBookCode(), posOrder.getBranchNum(), posOrder.getOrderNo());

                for (PosOrderDetail posOrderDetail : posOrderDetails) {
                    posOrderDetailObject = new JSONObject();
                    posOrderDetailObject.put("order_no", posOrderDetail.getOrderNo());
                    posOrderDetailObject.put("order_detail_num", posOrderDetail.getOrderDetailNum());
                    if (!posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_COUPON)) {
                        posOrderDetailObject.put("item_num", posOrderDetail.getItemNum());
                    }
                    posOrderDetailObject.put("order_detail_type", posOrderDetail.getOrderDetailType());
                    posOrderDetailObject.put("order_detail_item", posOrderDetail.getOrderDetailItem());
                    posOrderDetailObject.put("order_detail_item_department", posOrderDetail.getOrderDetailItemDepartment());
                    posOrderDetailObject.put("order_detail_std_price", posOrderDetail.getOrderDetailStdPrice());
                    posOrderDetailObject.put("order_detail_price", NumberUtil.getNewFloatString(posOrderDetail.getOrderDetailPrice()));
                    posOrderDetailObject.put("order_detail_amount", NumberUtil.getNewFloatLongString((double) posOrderDetail.getOrderDetailAmount()));
                    posOrderDetailObject.put("order_detail_online_qty", NumberUtil.getNewFloatLongString((double) posOrderDetail.getOrderDetailAmount()));
                    posOrderDetailObject.put("order_detail_discount", posOrderDetail.getOrderDetailDiscount());

                    posOrderDetailObject.put("order_detail_money", NumberUtil.getNewFloatString(posOrderDetail.getOrderDetailMoney()));
                    posOrderDetailObject.put("order_detail_payment_money",NumberUtil.getNewFloatString( posOrderDetail.getOrderDetailPaymentMoney()));
                    posOrderDetailObject.put("order_detail_state_code", posOrderDetail.getOrderDetailStateCode());
                    posOrderDetailObject.put("order_detail_state_name", posOrderDetail.getOrderDetailStateName());
                    posOrderDetailObject.put("order_detail_commission", posOrderDetail.getOrderDetailCommission());
                    posOrderDetailObject.put("order_detail_policy_promotion_flag", posOrderDetail.getOrderDetailPolicyPromotionFlag());
                    posOrderDetailObject.put("order_detail_policy_discount_flag", posOrderDetail.getOrderDetailPolicyDiscountFlag());
                    posOrderDetailObject.put("order_detail_policy_promotion_money_flag", posOrderDetail.getOrderDetailPolicyPromotionMoneyFlag());
                    posOrderDetailObject.put("order_detail_policy_promotion_quantity_flag", posOrderDetail.getOrderDetailPolicyPromotionQuantityFlag());
                    posOrderDetailObject.put("order_detail_policy_present_flag", posOrderDetail.getOrderDetailPolicyPresentFlag());

                    posOrderDetailObject.put("order_detail_cost", posOrderDetail.getOrderDetailCost());
                    posOrderDetailObject.put("order_detail_ticket_uuid", posOrderDetail.getOrderDetailTicketUuid());
                    posOrderDetailObject.put("order_detail_bizday", posOrder.getShiftTableBizday());
                    posOrderDetailObject.put("item_grade_num", posOrderDetail.getItemGradeNum());
                    posOrderDetailObject.put("order_detail_has_kit", posOrderDetail.getOrderDetailHasKit());
                    posOrderDetailObject.put("order_detail_policy_fid", posOrderDetail.getOrderDetailPolicyFid());
                    posOrderDetailObject.put("order_detail_memo", posOrderDetail.getOrderDetailMemo() == null ? "" : posOrderDetail.getOrderDetailMemo());
                    posOrderDetailObject.put("order_detail_lot_number", posOrderDetail.getOrderDetailLotNumber() == null ? "" : posOrderDetail.getOrderDetailLotNumber());
                    posOrderDetailObject.put("stall_num", posOrderDetail.getStall_num());
                    posOrderDetailObject.put("order_detail_online_unit", posOrderDetail.getOrderDetailOnlineUnit());

                    if (posOrder.getMerchantNum() > 0 && posOrderDetail.getOrder_detail_merchat_rate() != null) {
                        posOrderDetailObject.put("order_detail_merchant_rate", posOrderDetail.getOrder_detail_merchat_rate());
                    }


                    List<PosOrderKitDetail> posOrderKitDetailList = DataSynchronousImpl.getInstance().getPosOrderDetailKits(posOrder.getSystemBookCode(), posOrder.getBranchNum(), posOrder.getOrderNo(), posOrderDetail.getOrderDetailNum());
                    JSONArray posOrderKitList = new JSONArray();

                    for (PosOrderKitDetail posOrderKitDetail : posOrderKitDetailList) {
                        posOrderKitObject = new JSONObject();
                        posOrderKitObject.put("order_no", posOrderKitDetail.getOrderNo());
                        posOrderKitObject.put("order_detail_num", posOrderKitDetail.getOrderDetailNum());
                        posOrderKitObject.put("order_kit_detail_num", posOrderKitDetail.getOrderKitDetailNum());
                        posOrderKitObject.put("item_num", posOrderKitDetail.getItemNum());
                        posOrderKitObject.put("order_kit_detail_item_name", posOrderKitDetail.getOrderKitDetailItemName());
                        posOrderKitObject.put("order_kit_detail_department", posOrderKitDetail.getOrderKitDetailDepartment());
                        posOrderKitObject.put("order_kit_detail_std_price", posOrderKitDetail.getOrderKitDetailStdPrice());
                        posOrderKitObject.put("order_kit_detail_price", posOrderKitDetail.getOrderKitDetailPrice());
                        posOrderKitObject.put("order_kit_detail_amount", posOrderKitDetail.getOrderKitDetailAmount());
                        posOrderKitObject.put("order_kit_detail_money", posOrderKitDetail.getOrderKitDetailMoney());
                        posOrderKitObject.put("order_kit_detail_discount", NumberUtil.getNewFloatString(posOrderKitDetail.getOrderKitDetailDiscount()));
                        posOrderKitObject.put("order_kit_detail_payment_money", posOrderKitDetail.getOrderKitDetailPaymentMoney());
                        posOrderKitObject.put("order_kit_detail_book_code", posOrderKitDetail.getOrderKitDetailBookCode());
                        posOrderKitObject.put("order_kit_detail_branch_num", posOrderKitDetail.getOrderKitDetailBranchNum());
                        posOrderKitObject.put("order_kit_detail_order_state", posOrderKitDetail.getOrderKitDetailOrderState());
                        posOrderKitObject.put("order_kit_detail_append_money", posOrderKitDetail.getOrderKitDetailAppendMoney());
                        posOrderKitObject.put("order_kit_detail_tax", posOrderKitDetail.getOrderKitDetailTax());
                        posOrderKitObject.put("order_kit_detail_state_code", posOrderKitDetail.getOrderKitDetailStateCode());
                        posOrderKitObject.put("order_kit_detail_share_discount", posOrderKitDetail.getOrderKitDetailShareDiscount());
                        posOrderKitList.put(posOrderKitObject);
                    }
                    posOrderDetailObject.put("pos_order_kit_details", posOrderKitList);
                    posOrderDetailList.put(posOrderDetailObject);
//**/
                }

                List<Payment> paymentList = DataSynchronousImpl.getInstance().getPaymentList
                        (posOrder.getSystemBookCode(), posOrder.getBranchNum(), posOrder.getOrderNo());
                for (Payment payment : paymentList) {
                    paymentObject = new JSONObject();
                    paymentObject.put("payment_no", payment.getPaymentNo());
                    paymentObject.put("order_no", payment.getOrderNo());
                    paymentObject.put("payment_pay_by", payment.getPaymentPayBy());
                    paymentObject.put("payment_money", payment.getPaymentMoney());
                    paymentObject.put("payment_buyer_money", payment.getPaymentBuyerMoney());
                    paymentObject.put("payment_receipt_money", payment.getPaymentReceiptMoney());
                    paymentObject.put("payment_cust_num", payment.getPaymentCustNum() == 0 ? "" : payment.getPaymentCustNum());
                    paymentObject.put("payment_bill_no", payment.getPaymentBillNo());
                    paymentObject.put("payment_time", payment.getPaymentTime());
                    paymentObject.put("payment_receive", payment.getPaymentReceive());
                    paymentObject.put("payment_change", payment.getPaymentChange());
                    paymentObject.put("payment_paid", payment.getPaymentPaid());
                    paymentObject.put("payment_balance", payment.getPaymentBalance());
                    paymentObject.put("payment_machine", payment.getPaymentMachine());
                    paymentObject.put("payment_memo", payment.getPaymentMemo());
                    paymentObject.put("payment_acct_no", payment.getPaymentAcctNo());
                    paymentObject.put("payment_auditor", payment.getPaymentAuditor());
                    paymentObject.put("payment_card_balance", payment.getPaymentCardBalance());
                    paymentObject.put("payment_consume_count", payment.getPaymentConsumeCount());
                    paymentObject.put("payment_date", payment.getPaymentDate());
                    paymentObject.put("payment_lastest_date", payment.getPaymentLastestDate());
                    paymentObject.put("account_bank_num", payment.getAccountBankNum());
                    paymentObject.put("payment_point", payment.getPaymentPoint());
                    paymentObject.put("payment_online_un_discount", payment.getPaymentOnlineUnDiscount());
                    paymentObject.put("payment_round", payment.getPaymentRound());
                    paymentObject.put("client_fid", payment.getClientFid());
                    if (payment.getMerchantNum() > 0) {
                        paymentObject.put("merchant_num", payment.getMerchantNum());
                        paymentObject.put("stall_num", payment.getStallNum());
                        paymentObject.put("consume_merchant_flag", false);
                    }
                    paymentJsonList.put(paymentObject);
                }

                posOrderObject.put("payments", paymentJsonList);
                posOrderObject.put("pos_order_details", posOrderDetailList);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            posOrderListArray.put(posOrderObject);
        }
        return posOrderList.toString();
    }


    public boolean checkPosOrder(PosOrder posOrder){
        if(posOrder == null || posOrder.getPosOrderDetails()  == null || posOrder.getPosOrderDetails().size() == 0){
            return false;
        }
        List<PosOrderDetail> posOrderDetails = posOrder.getPosOrderDetails();
        float orderDiscountMoney = 0;
        float oderTotalMoney = 0;
        int policycount = 0;
        for (PosOrderDetail posOrderDetail : posOrderDetails){
            if(posOrderDetail.getOrderDetailType().equals(LibConfig.C_ORDER_DETAIL_TYPE_ITEM)){
                policycount = 0;
                if(posOrderDetail.getOrderDetailPolicyPromotionFlag())policycount ++;
                if(posOrderDetail.getOrderDetailPolicyPresentFlag())policycount ++;
                if(posOrderDetail.getOrderDetailPolicyPromotionQuantityFlag())policycount ++;
                if(posOrderDetail.getOrderDetailPolicyDiscountFlag())policycount ++;
                if(policycount > 1)return false;

                if(posOrderDetail.getOrderDetailPaymentMoney() != posOrderDetail.getOrderDetailMoney() + posOrderDetail.getOrderDetailAppendMoney()){
                    return false;
                }
                if(LibConfig.C_ORDER_DETAIL_TYPE_ITEM.equals(posOrderDetail.getOrderDetailType())){
                    if(posOrderDetail.getOrderDetailStateCode().equals(LibConfig.S_ORDER_DETAIL_RETURN)){
                        orderDiscountMoney -= posOrderDetail.getOrderDetailDiscount();
                        oderTotalMoney -= posOrderDetail.getOrderDetailPaymentMoney();
                    }else if(posOrderDetail.getOrderDetailStateCode().equals(LibConfig.S_ORDER_DETAIL_SALE)){
                        orderDiscountMoney += posOrderDetail.getOrderDetailDiscount();
                        oderTotalMoney += posOrderDetail.getOrderDetailPaymentMoney();
                    }
                }
            }
        }
        if(orderDiscountMoney != posOrder.getOrderDiscountMoney())return false;
//
        if(oderTotalMoney != posOrder.getOrderPaymentMoney() + posOrder.getOrderMgrDiscountMoney() + posOrder.getOrderRound() + posOrder.getQuickZeroMoney() + posOrder.getOrderCouponTotalMoney())return false;
//
//        if(posOrder.getOrderPaymentMoney() != NumberUtil.roundMoney(oderTotalMoney - posOrder.getOrderCouponTotalMoney(),LibConfig.saleParamsBean.getRoundType(),LibConfig.saleParamsBean.getRoundTo()))return false;
//
//        if(posOrder.getOrderTotalMoney() != posOrder.getOrderPaymentMoney() + posOrder.getOrderCouponTotalMoney()+ posOrder.getOrderRound())return false;

        return true;
    }

}
