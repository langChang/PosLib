package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.FmPayment;
import com.nhsoft.poslib.entity.FmPosOrder;
import com.nhsoft.poslib.entity.FmPosOrderDetail;
import com.nhsoft.poslib.service.greendao.FmPaymentDao;
import com.nhsoft.poslib.service.greendao.FmPosOrderDao;
import com.nhsoft.poslib.service.greendao.FmPosOrderDetailDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

/**
 * Created by Iverson on 2019-09-25 17:05
 * 此类用于：
 */
public class FmOrderService {

    private static FmOrderService instance;

    public static FmOrderService getInstance() {
        if (instance == null) {
            instance = new FmOrderService();
        }
        return instance;
    }


    /**
     * 订单生成存入订单
     */
    public boolean doPayment(final FmPosOrder posOrder) {
        final FmPosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getFmPosOrderDao();
        final FmPaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getFmPaymentDao();
        final FmPosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getFmPosOrderDetailDao();

        return MatterUtils.doMatter(posOrderDao, new Runnable() {
            @Override
            public void run() {
                posOrderDao.deleteByKey(posOrder.getOrderNo());
                posOrderDao.insertOrReplaceInTx(posOrder);
                posOrderDao.detachAll();
                deletePayment(posOrder.getOrderNo(), paymentDao);
                List<FmPayment> payments = posOrder.getPayments();
                if (payments != null && payments.size() > 0) {
                    if (LoginService.getInstance().isNongMao()){
                        for (int i = 0; i <payments.size() ; i++) {
                            payments.get(i).setOrderNo(posOrder.getOrderNo());
                            payments.get(i).setSystemBookCode(posOrder.getSystemBookCode());
                            payments.get(i).setBranchNum(posOrder.getBranchNum());
                            payments.get(i).setShiftTableBizday(posOrder.getShiftTableBizday());
                            payments.get(i).setShiftTableNum(posOrder.getShiftTableNum());
                            paymentDao.insertOrReplaceInTx(payments.get(i));
                            paymentDao.detachAll();
                        }
                    }else {
                        paymentDao.insertOrReplaceInTx(payments);
                        paymentDao.detachAll();
                    }


                }
                deletePosOrderDetail(posOrder.getOrderNo(), posOrderDetailDao);
                List<FmPosOrderDetail> posOrderDetails = posOrder.getPosOrderDetails();
                for (FmPosOrderDetail posOrderDetail : posOrderDetails) {

                    if (LoginService.getInstance().isNongMao()) {
                        if (posOrderDetail.getSystemBookCode() == null) {
                            posOrderDetail.setSystemBookCode(posOrder.getSystemBookCode());
                        }
                        if (posOrderDetail.getBranchNum() == 0) {
                            posOrderDetail.setBranchNum(posOrder.getBranchNum());
                        }
                    }

                    posOrderDetailDao.insertOrReplaceInTx(posOrderDetail);
                    posOrderDetailDao.detachAll();
                }
            }
        });
    }



    /**
     * 实时上传成功后将posorder订单上传状态修改掉
     *
     * @param orderNum 订单的编号
     */
    public void changeFmOrderUploadStatus(String orderNum) {
        FmPosOrderDao mPosOrderDao = DaoManager.getInstance().getDaoSession().getFmPosOrderDao();
        FmPosOrder loadOrder = mPosOrderDao.load(orderNum);
        if (loadOrder != null) {
            loadOrder.setOrderUploadState(true);
            mPosOrderDao.insertOrReplace(loadOrder);
        }

    }


    /**
     * 先删除之前存入的支付payment
     *
     * @param orderNo
     * @param paymentDao
     * @return
     */
    public boolean deletePayment(final String orderNo, final FmPaymentDao paymentDao) {
        return MatterUtils.doMatter(paymentDao, new Runnable() {
            @Override
            public void run() {
                List<FmPayment> payments = paymentDao._queryFmPosOrder_Payments(orderNo);
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
    public boolean deletePosOrderDetail(final String orderNo, final FmPosOrderDetailDao posOrderDetailDao) {
        return MatterUtils.doMatter(posOrderDetailDao, new Runnable() {
            @Override
            public void run() {
                List<FmPosOrderDetail> posOrderDetails = posOrderDetailDao._queryFmPosOrder_PosOrderDetails(orderNo);
                posOrderDetailDao.deleteInTx(posOrderDetails);
            }
        });
    }

}
