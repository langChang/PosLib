package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.FmPosOrder;
import com.nhsoft.poslib.entity.order.Payment;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.entity.order.PosOrderKitDetail;
import com.nhsoft.poslib.entity.shift.ShiftTable;
import com.nhsoft.poslib.service.greendao.FmPaymentDao;
import com.nhsoft.poslib.service.greendao.FmPosOrderDao;
import com.nhsoft.poslib.service.greendao.FmPosOrderDetailDao;
import com.nhsoft.poslib.service.greendao.PaymentDao;
import com.nhsoft.poslib.service.greendao.PosOrderDao;
import com.nhsoft.poslib.service.greendao.PosOrderDetailDao;
import com.nhsoft.poslib.service.greendao.PosOrderKitDetailDao;
import com.nhsoft.poslib.service.greendao.ShiftTableDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

public class DataSynchronousService {
    /**
     * 数据同步service
     */

    private static DataSynchronousService instance;
    public static DataSynchronousService getInstance(){
        if (instance==null){
            instance=new DataSynchronousService();
        }
        return instance;
    }



    /**
     * 获取本地没有上传的所有的班次(有流水，未上传，)
     * @param systemBookCode
     * @param branchNum
     * @return
     */
    public List<ShiftTable> getNotUploadList(String systemBookCode, int branchNum){
        List<ShiftTable> shiftTables=null;
        ShiftTableDao shiftTableDao=DaoManager.getInstance().getDaoSession().getShiftTableDao();
        shiftTables=shiftTableDao.queryBuilder().where(
                ShiftTableDao.Properties.System_book_code.eq(systemBookCode),//账套号
                ShiftTableDao.Properties.Branch_num.eq(branchNum)//店
                ,ShiftTableDao.Properties.Shift_table_synchronized.eq(false)//上传
                ,ShiftTableDao.Properties.Shift_table_need_carry.eq(true)//流水
                ,ShiftTableDao.Properties.Shift_table_upload_times.lt(3)//上传次数

        ).list();
        return shiftTables;
    }


    /**
     * 上传本班次没有上传过的订单
     * @param systemBookCode
     * @param shiftTableNum
     * @param shiftTableBizday
     * @param branchNum
     * @param orderUploadState
     * @return
     */
    public List<PosOrder> getPosOrderListToUpload(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, boolean orderUploadState) {

        PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList = posOrderDao.queryBuilder()
                .where(PosOrderDao.Properties.OrderUploadState.eq(orderUploadState)
                        , PosOrderDao.Properties.SystemBookCode.eq(systemBookCode)
                        , PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday)
                        , PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum)
                        , PosOrderDao.Properties.BranchNum.eq(branchNum))
                .orderAsc(PosOrderDao.Properties.OrderNo)
                .list();

        if (posOrderList.size() > 0){
            PaymentDao paymentDao = DaoManager.getInstance().getDaoSession().getPaymentDao();
            PosOrderKitDetailDao posOrderKitDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderKitDetailDao();
            PosOrderDetailDao posOrderDetailDao = DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();
            List<Payment> paymentList = paymentDao.queryBuilder()
                    .where(PaymentDao.Properties.OrderNo.in(posOrderList))
                    .orderAsc(PaymentDao.Properties.OrderNo)
//                    .orderDesc(PaymentDao.Properties.PaymentNo)

                    .list();

            List<PosOrderDetail> posOrderDetailList = posOrderDetailDao.queryBuilder()
                    .where(PosOrderDetailDao.Properties.OrderNo.in(posOrderList))
                    .orderAsc(PosOrderDetailDao.Properties.OrderNo)
//                    .orderDesc(PosOrderDetailDao.Properties.OrderDetailNum)
                    .list();



            int k=0;
            for(int i = 0; i < posOrderList.size(); i++){
                PosOrder posOrder = posOrderList.get(i);
                for(int j = paymentList.size() -1; j >= 0; j--){
                    Payment payment = paymentList.get(j);
                    if (payment.getOrderNo().equals(posOrder.getOrderNo())){
                        posOrder.getPayments().add(payment);
                        paymentList.remove(j);
                        k=1;
                    }else if (k==1){
                        k=0;
                        break ;
                    }

                }

                for (PosOrderDetail posOrderDetail:posOrderDetailList){
                    if (posOrderDetail.getOrderNo().equals(posOrder.getOrderNo())){
                       posOrder.getPosOrderDetails().add(posOrderDetail);
                        paymentList.remove(posOrderDetail);
                        k=1;
                    }else if (k==1&&!posOrderDetail.getOrderNo().equals(posOrder.getOrderNo())){
                        k=0;
                        break ;
                    }
                }


            }
        }


        return posOrderList;
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
                        , PosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday)
                        , PosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum)
                        , PosOrderDao.Properties.BranchNum.eq(branchNum)
                        , PosOrderDao.Properties.OrderStateCode.eq(5))

                .orderAsc(PosOrderDao.Properties.OrderNo)
                .list();
        return posOrderList;
    }

    /**
     * 新农贸 获取该班次号下所有 posOrder
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param shiftTableBizday
     * @param orderUploadState
     * @return
     */
    public List<FmPosOrder> getFmPosOrderList(String systemBookCode, int branchNum, int shiftTableNum, String shiftTableBizday, boolean orderUploadState){
        FmPosOrderDao fmPosOrderDao=DaoManager.getInstance().getDaoSession().getFmPosOrderDao();
        FmPosOrderDetailDao fmPosOrderDetailDao=DaoManager.getInstance().getDaoSession().getFmPosOrderDetailDao();
        FmPaymentDao fmPaymentDao=DaoManager.getInstance().getDaoSession().getFmPaymentDao();

        List<FmPosOrder> posOrderList = fmPosOrderDao.queryBuilder()
                .where(
                        FmPosOrderDao.Properties.OrderUploadState.eq(orderUploadState),
                        FmPosOrderDao.Properties.SystemBookCode.eq(systemBookCode)
                        , FmPosOrderDao.Properties.ShiftTableBizday.eq(shiftTableBizday)
                        , FmPosOrderDao.Properties.ShiftTableNum.eq(shiftTableNum)
                        , FmPosOrderDao.Properties.BranchNum.eq(branchNum)
                        , FmPosOrderDao.Properties.OrderStateCode.notEq(1))

                .orderAsc(FmPosOrderDao.Properties.OrderNo)
                .list();
        for (FmPosOrder fmPosOrder : posOrderList){
            fmPosOrder.setPosOrderDetails(fmPosOrderDetailDao._queryFmPosOrder_PosOrderDetails(fmPosOrder.getOrderNo()));
            fmPosOrder.setPayments(fmPaymentDao._queryFmPosOrder_Payments(fmPosOrder.getOrderNo()));
        }
        return posOrderList;
    }

    /**
     * 获取该订单下所有PosOrderDetail
     * @param systemBookCode
     * @param branchNum
     * @param orderNo
     * @return
     */
    public List<PosOrderDetail> getPosOrderDetailList(String systemBookCode, int branchNum,String orderNo){
        PosOrderDetailDao mPosOrderDetailDao=DaoManager.getInstance().getDaoSession().getPosOrderDetailDao();

        PosOrderKitDetailDao posOrderKitDetailDao =DaoManager.getInstance().getDaoSession().getPosOrderKitDetailDao();

        List<PosOrderDetail> posOrderDetails = mPosOrderDetailDao.queryBuilder().where(
                PosOrderDetailDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderDetailDao.Properties.BranchNum.eq(branchNum),
                PosOrderDetailDao.Properties.OrderNo.eq(orderNo)
        ).list();

        for (PosOrderDetail posOrderDetail : posOrderDetails){
            List<PosOrderKitDetail> list = posOrderKitDetailDao.queryBuilder().where(PosOrderKitDetailDao.Properties.OrderNo.eq(posOrderDetail.getOrderNo()),
                    PosOrderKitDetailDao.Properties.OrderDetailNum.eq(posOrderDetail.getOrderDetailNum())).list();
            if(list != null && list.size() > 0){
                posOrderDetail.setPosOrderKitDetails(list);
            }
        }
       return posOrderDetails;
    }

    /**
     * 获取该订单下所有PosOrderDetail
     * @param systemBookCode
     * @param branchNum
     * @param orderNo
     * @return
     */
    public List<PosOrderKitDetail> getPosOrderDetailKits(String systemBookCode, int branchNum,String orderNo,int detailNum){
        PosOrderKitDetailDao posOrderKitDetailDao=DaoManager.getInstance().getDaoSession().getPosOrderKitDetailDao();
        return posOrderKitDetailDao.queryBuilder().where(
                PosOrderKitDetailDao.Properties.SystemBookCode.eq(systemBookCode),
                PosOrderKitDetailDao.Properties.BranchNum.eq(branchNum),
                PosOrderKitDetailDao.Properties.OrderNo.eq(orderNo),
                PosOrderKitDetailDao.Properties.OrderDetailNum.eq(detailNum)
        ).list();

    }

    /**
     * 获取该订单下所有Payment
     * @param systemBookCode
     * @param branchNum
     * @param orderNo
     * @return
     */
    public List<Payment> getPaymentList(String systemBookCode, int branchNum,String orderNo){
        PaymentDao mPaymentDao=DaoManager.getInstance().getDaoSession().getPaymentDao();
        return mPaymentDao.queryBuilder().where(
                PaymentDao.Properties.SystemBookCode.eq(systemBookCode),
                PaymentDao.Properties.BranchNum.eq(branchNum),
                PaymentDao.Properties.OrderNo.eq(orderNo)
        ).list();

    }


    /**
     * 上传完成后更改上传标记
     * @param posOrderList
     * @return
     */
    public  boolean setPosOrderListToUpload(final List<PosOrder> posOrderList){
        final PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        return MatterUtils.doMatter(posOrderDao, new Runnable() {
            @Override
            public void run() {
                for (PosOrder posOrder:posOrderList){
                    posOrder.setOrderUploadState(true);
                    posOrderDao.insertOrReplaceInTx(posOrder);
                }
            }
        });
    }

    /**
     * 上传完成后更改上传标记
     * @param posOrderList
     * @return
     */
    public  boolean setFmPosOrderListToUpload(final List<FmPosOrder> posOrderList){
        final FmPosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getFmPosOrderDao();
        return MatterUtils.doMatter(posOrderDao, new Runnable() {
            @Override
            public void run() {
                for (FmPosOrder posOrder:posOrderList){
                    posOrder.setOrderUploadState(true);
                    posOrderDao.insertOrReplaceInTx(posOrder);
                }
            }
        });
    }

    //如果校验失败，打开该班次下的所有流水
    public void openShiftTableOfPosOrder(ShiftTable shiftTable){
        shiftTable.setShiftTableUploadTimes((shiftTable.getShiftTableUploadTimes()+1));
        DaoManager.getInstance().getDaoSession().getShiftTableDao().insertOrReplaceInTx(shiftTable);
        PosOrderDao posOrderDao=DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> posOrderList=getPosOrderList(shiftTable.getSystemBookCode(), shiftTable.getBranchNum(),
                shiftTable.getShiftTableNum(),shiftTable.getShiftTableBizday(),false);
        for (PosOrder posOrder:posOrderList){
            posOrder.setOrderUploadState(false);
            posOrderDao.insertOrReplaceInTx(posOrder);
        }
    }
}
