package com.nhsoft.poslib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import com.nhsoft.poslib.service.greendao.FmPaymentDao;
import com.nhsoft.poslib.service.greendao.FmPosOrderDetailDao;
import com.nhsoft.poslib.service.greendao.FmPosOrderDao;
import com.nhsoft.poslib.service.greendao.DaoSession;

/**
 * Created by Iverson on 2019-09-25 11:17
 * 此类用于：
 */
@Entity
public class FmPosOrder implements Cloneable {
    @Id
    private String orderNo;
    private String systemBookCode;

    private int    branchNum;
    private int    merchantNum;
    private long    branchId;
    private int    shiftTableNum;
    private String shiftTableBizday;
    private String orderOperator;
    private String orderOperateTime;
    private float  orderDiscountMoney;
    private float  orderTotalMoney;
    private float  orderPaymentMoney;
    private float  orderRound;
    private float  orderChange;
    private String orderMachine;
    private int    orderStateCode;
    private String orderStateName;
    private String orderMemo;
    private String orderRefBillno;
    private float    orderMgrDiscountMoney;
    private float    orderCouponTotalMoney;
    private int    orderCardUserNum;
    private float  orderPromotionDiscountMoney;
    private int    orderDetailItemCount;
    private String orderPayNo;
    private float orderBalance;
    private Boolean orderUploadState;

    public float getOrderBalance() {
        return orderBalance;
    }

    public void setOrderBalance(float orderBalance) {
        this.orderBalance = orderBalance;
    }

    @ToMany(referencedJoinProperty = "orderNo")
    private List<FmPosOrderDetail> posOrderDetails = new ArrayList<>(0);

    public void setPosOrderDetails(List<FmPosOrderDetail> posOrderDetails) {
        this.posOrderDetails = posOrderDetails;
    }

    @ToMany(referencedJoinProperty = "orderNo")
    private List<FmPayment>        payments        = new ArrayList<FmPayment>(0);
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 749678323)
    private transient FmPosOrderDao myDao;

    @Generated(hash = 7656778)
    public FmPosOrder(String orderNo, String systemBookCode, int branchNum, int merchantNum,
            long branchId, int shiftTableNum, String shiftTableBizday, String orderOperator,
            String orderOperateTime, float orderDiscountMoney, float orderTotalMoney,
            float orderPaymentMoney, float orderRound, float orderChange, String orderMachine,
            int orderStateCode, String orderStateName, String orderMemo, String orderRefBillno,
            float orderMgrDiscountMoney, float orderCouponTotalMoney, int orderCardUserNum,
            float orderPromotionDiscountMoney, int orderDetailItemCount, String orderPayNo,
            float orderBalance, Boolean orderUploadState) {
        this.orderNo = orderNo;
        this.systemBookCode = systemBookCode;
        this.branchNum = branchNum;
        this.merchantNum = merchantNum;
        this.branchId = branchId;
        this.shiftTableNum = shiftTableNum;
        this.shiftTableBizday = shiftTableBizday;
        this.orderOperator = orderOperator;
        this.orderOperateTime = orderOperateTime;
        this.orderDiscountMoney = orderDiscountMoney;
        this.orderTotalMoney = orderTotalMoney;
        this.orderPaymentMoney = orderPaymentMoney;
        this.orderRound = orderRound;
        this.orderChange = orderChange;
        this.orderMachine = orderMachine;
        this.orderStateCode = orderStateCode;
        this.orderStateName = orderStateName;
        this.orderMemo = orderMemo;
        this.orderRefBillno = orderRefBillno;
        this.orderMgrDiscountMoney = orderMgrDiscountMoney;
        this.orderCouponTotalMoney = orderCouponTotalMoney;
        this.orderCardUserNum = orderCardUserNum;
        this.orderPromotionDiscountMoney = orderPromotionDiscountMoney;
        this.orderDetailItemCount = orderDetailItemCount;
        this.orderPayNo = orderPayNo;
        this.orderBalance = orderBalance;
        this.orderUploadState = orderUploadState;
    }

    @Generated(hash = 1616111214)
    public FmPosOrder() {
    }

    public void setPayments(List<FmPayment> payments) {
        this.payments = payments;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSystemBookCode() {
        return this.systemBookCode;
    }

    public void setSystemBookCode(String systemBookCode) {
        this.systemBookCode = systemBookCode;
    }

    public int getBranchNum() {
        return this.branchNum;
    }

    public void setBranchNum(int branchNum) {
        this.branchNum = branchNum;
    }

    public int getMerchantNum() {
        return this.merchantNum;
    }

    public void setMerchantNum(int merchantNum) {
        this.merchantNum = merchantNum;
    }

    public long getBranchId() {
        return this.branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    public int getShiftTableNum() {
        return this.shiftTableNum;
    }

    public void setShiftTableNum(int shiftTableNum) {
        this.shiftTableNum = shiftTableNum;
    }

    public String getShiftTableBizday() {
        return this.shiftTableBizday;
    }

    public void setShiftTableBizday(String shiftTableBizday) {
        this.shiftTableBizday = shiftTableBizday;
    }

    public String getOrderOperator() {
        return this.orderOperator;
    }

    public void setOrderOperator(String orderOperator) {
        this.orderOperator = orderOperator;
    }

    public String getOrderOperateTime() {
        return this.orderOperateTime;
    }

    public void setOrderOperateTime(String orderOperateTime) {
        this.orderOperateTime = orderOperateTime;
    }

    public float getOrderDiscountMoney() {
        return this.orderDiscountMoney;
    }

    public void setOrderDiscountMoney(float orderDiscountMoney) {
        this.orderDiscountMoney = orderDiscountMoney;
    }

    public float getOrderTotalMoney() {
        return this.orderTotalMoney;
    }

    public void setOrderTotalMoney(float orderTotalMoney) {
        this.orderTotalMoney = orderTotalMoney;
    }

    public float getOrderPaymentMoney() {
        return this.orderPaymentMoney;
    }

    public void setOrderPaymentMoney(float orderPaymentMoney) {
        this.orderPaymentMoney = orderPaymentMoney;
    }

    public float getOrderRound() {
        return this.orderRound;
    }

    public void setOrderRound(float orderRound) {
        this.orderRound = orderRound;
    }

    public float getOrderChange() {
        return this.orderChange;
    }

    public void setOrderChange(float orderChange) {
        this.orderChange = orderChange;
    }

    public String getOrderMachine() {
        return this.orderMachine;
    }

    public void setOrderMachine(String orderMachine) {
        this.orderMachine = orderMachine;
    }

    public int getOrderStateCode() {
        return this.orderStateCode;
    }

    public void setOrderStateCode(int orderStateCode) {
        this.orderStateCode = orderStateCode;
    }

    public String getOrderStateName() {
        return this.orderStateName;
    }

    public void setOrderStateName(String orderStateName) {
        this.orderStateName = orderStateName;
    }

    public String getOrderMemo() {
        return this.orderMemo;
    }

    public void setOrderMemo(String orderMemo) {
        this.orderMemo = orderMemo;
    }

    public String getOrderRefBillno() {
        return this.orderRefBillno;
    }

    public void setOrderRefBillno(String orderRefBillno) {
        this.orderRefBillno = orderRefBillno;
    }

    public float getOrderMgrDiscountMoney() {
        return this.orderMgrDiscountMoney;
    }

    public void setOrderMgrDiscountMoney(int orderMgrDiscountMoney) {
        this.orderMgrDiscountMoney = orderMgrDiscountMoney;
    }

    public float getOrderCouponTotalMoney() {
        return this.orderCouponTotalMoney;
    }

    public void setOrderCouponTotalMoney(int orderCouponTotalMoney) {
        this.orderCouponTotalMoney = orderCouponTotalMoney;
    }

    public int getOrderCardUserNum() {
        return this.orderCardUserNum;
    }

    public void setOrderCardUserNum(int orderCardUserNum) {
        this.orderCardUserNum = orderCardUserNum;
    }

    public float getOrderPromotionDiscountMoney() {
        return this.orderPromotionDiscountMoney;
    }

    public void setOrderPromotionDiscountMoney(float orderPromotionDiscountMoney) {
        this.orderPromotionDiscountMoney = orderPromotionDiscountMoney;
    }

    public int getOrderDetailItemCount() {
        return this.orderDetailItemCount;
    }

    public void setOrderDetailItemCount(int orderDetailItemCount) {
        this.orderDetailItemCount = orderDetailItemCount;
    }

    public String getOrderPayNo() {
        return this.orderPayNo;
    }

    public void setOrderPayNo(String orderPayNo) {
        this.orderPayNo = orderPayNo;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 113377453)
    public List<FmPosOrderDetail> getPosOrderDetails() {
        if (posOrderDetails == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FmPosOrderDetailDao targetDao = daoSession.getFmPosOrderDetailDao();
            List<FmPosOrderDetail> posOrderDetailsNew = targetDao
                    ._queryFmPosOrder_PosOrderDetails(orderNo);
            synchronized (this) {
                if (posOrderDetails == null) {
                    posOrderDetails = posOrderDetailsNew;
                }
            }
        }
        return posOrderDetails;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 458317267)
    public synchronized void resetPosOrderDetails() {
        posOrderDetails = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1107876797)
    public List<FmPayment> getPayments() {
        if (payments == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FmPaymentDao targetDao = daoSession.getFmPaymentDao();
            List<FmPayment> paymentsNew = targetDao
                    ._queryFmPosOrder_Payments(orderNo);
            synchronized (this) {
                if (payments == null) {
                    payments = paymentsNew;
                }
            }
        }
        return payments;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1083720232)
    public synchronized void resetPayments() {
        payments = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public Boolean getOrderUploadState() {
        if(this.orderUploadState == null)return false;
        return this.orderUploadState;
    }

    public void setOrderUploadState(Boolean orderUploadState) {
        this.orderUploadState = orderUploadState;
    }

    public void setOrderMgrDiscountMoney(float orderMgrDiscountMoney) {
        this.orderMgrDiscountMoney = orderMgrDiscountMoney;
    }

    public void setOrderCouponTotalMoney(float orderCouponTotalMoney) {
        this.orderCouponTotalMoney = orderCouponTotalMoney;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 143917196)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFmPosOrderDao() : null;
    }
}
