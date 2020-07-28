package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.PosCarryLog;
import com.nhsoft.poslib.entity.PosMachine;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.entity.shift.ShiftTable;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.service.greendao.PosCarryLogDao;
import com.nhsoft.poslib.utils.TimeUtil;

import java.util.List;

/**
 * Created by Iverson on 2019/4/11 5:40 PM
 * 此类用于：用于抓取异常收银操作日志
 */
public class PosCarryLogService {

    private static String NAME_BY_OPEN_DRAW = "打开钱箱";
    private static String NAME_BY_COLLECTION_ORDER = "挂单";
    private static String NAME_BY_DELETE_ORDER = "整单取消";
    private static String NAME_BY_DELETE_GOODS = "删除";
    private static String NAME_BY_QUIT_GOODS_TIMES = "退货";
    private static String NAME_BY_CHANGE_PRICE = "修改价格";
    private static String NAME_BY_MGR_DISCOUNT = "经理折扣";
    private static String NAME_BY_PRESENT_GOODS = "赠送";
    public static final String RETAIL_POS_LOG_CHANGE_AMOUNT = "更改数量";

    /**
     * 打开钱箱
     */
    public static void tryOpenDraw(){
        ShiftTable shiftTable = LibConfig.activeShiftTable;
        PosMachine posMachine = LibConfig.activePosMachine;
        if(shiftTable == null || posMachine == null)return;
        PosCarryLogDao posCarryLogDao = DaoManager.getInstance().getDaoSession().getPosCarryLogDao();
        PosCarryLog posCarryLog = new PosCarryLog();
        posCarryLog.setRetail_pos_log_type(NAME_BY_OPEN_DRAW);
        posCarryLog.setIsUpload(false);
        posCarryLog.setBranch_num(shiftTable.getBranch_num());
        posCarryLog.setSystem_book_code(shiftTable.getSystem_book_code());
        posCarryLog.setRetail_pos_log_shift_num(shiftTable.getShift_table_num());
        String nowDateString = TimeUtil.getInstance().getNowDateString();
        posCarryLog.setRetail_pos_log_time(nowDateString);
        posCarryLog.setRetail_pos_log_operator(shiftTable.getShift_table_user_name());
        posCarryLog.setRetail_pos_log_bizday(shiftTable.getShift_table_bizday());
        posCarryLog.setRetail_pos_log_machine(posMachine.getPos_machine_terminal_id());
        posCarryLog.setMerchant_num(shiftTable.getMerchant_num());
        posCarryLog.setStall_num(shiftTable.getStall_num());

        posCarryLogDao.insertInTx(posCarryLog);
    }


    /**
     * 挂单
     */
    public static void tryCollectionOrder(PosOrder posOrder){
        ShiftTable shiftTable = LibConfig.activeShiftTable;
        PosMachine posMachine = LibConfig.activePosMachine;
        if(shiftTable == null || posMachine == null)return;
        PosCarryLogDao posCarryLogDao = DaoManager.getInstance().getDaoSession().getPosCarryLogDao();
        PosCarryLog posCarryLog = new PosCarryLog();
        posCarryLog.setRetail_pos_log_type(NAME_BY_COLLECTION_ORDER);
        posCarryLog.setIsUpload(false);
        posCarryLog.setBranch_num(shiftTable.getBranch_num());
        posCarryLog.setSystem_book_code(shiftTable.getSystem_book_code());
        posCarryLog.setRetail_pos_log_shift_num(shiftTable.getShift_table_num());
        String nowDateString = TimeUtil.getInstance().getNowDateString();
        posCarryLog.setRetail_pos_log_time(nowDateString);
        posCarryLog.setRetail_pos_log_operator(shiftTable.getShift_table_user_name());
        posCarryLog.setRetail_pos_log_bizday(shiftTable.getShift_table_bizday());
        posCarryLog.setRetail_pos_log_machine(posMachine.getPos_machine_terminal_id());
        posCarryLog.setMerchant_num(shiftTable.getMerchant_num());
        posCarryLog.setStall_num(shiftTable.getStall_num());
        posCarryLog.setRetail_pos_log_order_no(posOrder.getOrderNo());
        posCarryLog.setRetail_pos_log_money(posOrder.getOrderPaymentMoney());
        StringBuilder builder = new StringBuilder("");
        for (PosOrderDetail posOrderDetail : posOrder.getPosOrderDetails()){
            if(LibConfig.C_ORDER_DETAIL_TYPE_ITEM.equals(posOrderDetail.getOrderDetailType())){
                builder.append(posOrderDetail.getOrderDetailItem()+",");
            }
        }
        String totalItemStr = builder.toString();
        posCarryLog.setRetail_pos_log_item_name(totalItemStr.length() >= 245 ? totalItemStr.substring(245) :  totalItemStr);

        posCarryLogDao.insertInTx(posCarryLog);
    }

    /**
     * 撤单
     */
    public static void tryDeleteOrder(PosOrder posOrder){
        ShiftTable shiftTable = LibConfig.activeShiftTable;
        PosMachine posMachine = LibConfig.activePosMachine;
        if(shiftTable == null || posMachine == null)return;
        PosCarryLogDao posCarryLogDao = DaoManager.getInstance().getDaoSession().getPosCarryLogDao();
        PosCarryLog posCarryLog = new PosCarryLog();
        posCarryLog.setRetail_pos_log_type(NAME_BY_DELETE_ORDER);
        posCarryLog.setIsUpload(false);
        posCarryLog.setBranch_num(shiftTable.getBranch_num());
        posCarryLog.setSystem_book_code(shiftTable.getSystem_book_code());
        posCarryLog.setRetail_pos_log_shift_num(shiftTable.getShift_table_num());
        String nowDateString = TimeUtil.getInstance().getNowDateString();
        posCarryLog.setRetail_pos_log_time(nowDateString);
        posCarryLog.setRetail_pos_log_operator(shiftTable.getShift_table_user_name());
        posCarryLog.setRetail_pos_log_bizday(shiftTable.getShift_table_bizday());
        posCarryLog.setRetail_pos_log_machine(posMachine.getPos_machine_terminal_id());
        posCarryLog.setMerchant_num(shiftTable.getMerchant_num());
        posCarryLog.setStall_num(shiftTable.getStall_num());
        posCarryLog.setRetail_pos_log_order_no(posOrder.getOrderNo());
        posCarryLog.setRetail_pos_log_money(posOrder.getOrderPaymentMoney());
        StringBuilder builder = new StringBuilder("");

        for (PosOrderDetail posOrderDetail : posOrder.getPosOrderDetails()){
            if(LibConfig.C_ORDER_DETAIL_TYPE_ITEM.equals(posOrderDetail.getOrderDetailType())){
                builder.append(posOrderDetail.getOrderDetailItem()+",");
            }
        }

        String totalItemStr = builder.toString();
        posCarryLog.setRetail_pos_log_item_name(totalItemStr.length() >= 245 ? totalItemStr.substring(245) :  totalItemStr);
        posCarryLogDao.insertInTx(posCarryLog);
    }


    /**
     * 退货
     */
    public static void tryQuitOrder(PosOrder posOrder){
        ShiftTable shiftTable = LibConfig.activeShiftTable;
        PosMachine posMachine = LibConfig.activePosMachine;
        if(shiftTable == null || posMachine == null)return;
        PosCarryLogDao posCarryLogDao = DaoManager.getInstance().getDaoSession().getPosCarryLogDao();
        PosCarryLog posCarryLog = new PosCarryLog();
        posCarryLog.setRetail_pos_log_type(NAME_BY_QUIT_GOODS_TIMES);
        posCarryLog.setIsUpload(false);
        posCarryLog.setBranch_num(shiftTable.getBranch_num());
        posCarryLog.setSystem_book_code(shiftTable.getSystem_book_code());
        posCarryLog.setRetail_pos_log_shift_num(shiftTable.getShift_table_num());
        String nowDateString = TimeUtil.getInstance().getNowDateString();
        posCarryLog.setRetail_pos_log_time(nowDateString);
        posCarryLog.setRetail_pos_log_operator(shiftTable.getShift_table_user_name());
        posCarryLog.setRetail_pos_log_bizday(shiftTable.getShift_table_bizday());
        posCarryLog.setRetail_pos_log_machine(posMachine.getPos_machine_terminal_id());
        posCarryLog.setMerchant_num(shiftTable.getMerchant_num());
        posCarryLog.setStall_num(shiftTable.getStall_num());
        posCarryLog.setRetail_pos_log_order_no(posOrder.getOrderNo());
        posCarryLog.setRetail_pos_log_money(posOrder.getOrderPaymentMoney());
        StringBuilder builder = new StringBuilder("");

        for (PosOrderDetail posOrderDetail : posOrder.getPosOrderDetails()){
            if(LibConfig.C_ORDER_DETAIL_TYPE_ITEM.equals(posOrderDetail.getOrderDetailType())){
                builder.append(posOrderDetail.getOrderDetailItem()+",");
            }
        }
        String totalItemStr = builder.toString();
        posCarryLog.setRetail_pos_log_item_name(totalItemStr.length() >= 245 ? totalItemStr.substring(245) :  totalItemStr);

        posCarryLogDao.insertInTx(posCarryLog);
    }


    /**
     * 经理折扣
     */
    public static void tryOrderMgr(PosOrder posOrder){
        ShiftTable shiftTable = LibConfig.activeShiftTable;
        PosMachine posMachine = LibConfig.activePosMachine;
        if(shiftTable == null || posMachine == null)return;
        PosCarryLogDao posCarryLogDao = DaoManager.getInstance().getDaoSession().getPosCarryLogDao();
        PosCarryLog posCarryLog = new PosCarryLog();
        posCarryLog.setRetail_pos_log_type(NAME_BY_MGR_DISCOUNT);
        posCarryLog.setIsUpload(false);
        posCarryLog.setBranch_num(shiftTable.getBranch_num());
        posCarryLog.setSystem_book_code(shiftTable.getSystem_book_code());
        posCarryLog.setRetail_pos_log_shift_num(shiftTable.getShift_table_num());
        String nowDateString = TimeUtil.getInstance().getNowDateString();
        posCarryLog.setRetail_pos_log_time(nowDateString);
        posCarryLog.setRetail_pos_log_operator(shiftTable.getShift_table_user_name());
        posCarryLog.setRetail_pos_log_bizday(shiftTable.getShift_table_bizday());
        posCarryLog.setRetail_pos_log_machine(posMachine.getPos_machine_terminal_id());
        posCarryLog.setMerchant_num(shiftTable.getMerchant_num());
        posCarryLog.setStall_num(shiftTable.getStall_num());
        posCarryLog.setRetail_pos_log_order_no(posOrder.getOrderNo());
        posCarryLog.setRetail_pos_log_money(posOrder.getOrderMgrDiscountMoney());
        StringBuilder builder = new StringBuilder("");

        for (PosOrderDetail posOrderDetail : posOrder.getPosOrderDetails()){
            if(LibConfig.C_ORDER_DETAIL_TYPE_ITEM.equals(posOrderDetail.getOrderDetailType())){
                builder.append(posOrderDetail.getOrderDetailItem()+",");
            }
        }

        String totalItemStr = builder.toString();
        posCarryLog.setRetail_pos_log_item_name(totalItemStr.length() >= 245 ? totalItemStr.substring(245) :  totalItemStr);
        posCarryLogDao.insertInTx(posCarryLog);
    }


    /**
     * 删除商品
     */
    public static void tryDeleteGoods(PosOrderDetail posOrderDetail){
        ShiftTable shiftTable = LibConfig.activeShiftTable;
        PosMachine posMachine = LibConfig.activePosMachine;
        if(shiftTable == null || posMachine == null)return;
        PosCarryLogDao posCarryLogDao = DaoManager.getInstance().getDaoSession().getPosCarryLogDao();
        PosCarryLog posCarryLog = new PosCarryLog();
        posCarryLog.setRetail_pos_log_item_num(posOrderDetail.getItemNum());
        posCarryLog.setRetail_pos_log_type(NAME_BY_DELETE_GOODS);
        posCarryLog.setIsUpload(false);
        posCarryLog.setBranch_num(shiftTable.getBranch_num());
        posCarryLog.setSystem_book_code(shiftTable.getSystem_book_code());
        posCarryLog.setRetail_pos_log_shift_num(shiftTable.getShift_table_num());
        String nowDateString = TimeUtil.getInstance().getNowDateString();
        posCarryLog.setRetail_pos_log_time(nowDateString);
        posCarryLog.setRetail_pos_log_operator(shiftTable.getShift_table_user_name());
        posCarryLog.setRetail_pos_log_bizday(shiftTable.getShift_table_bizday());
        posCarryLog.setRetail_pos_log_machine(posMachine.getPos_machine_terminal_id());
        posCarryLog.setMerchant_num(shiftTable.getMerchant_num());
        posCarryLog.setStall_num(shiftTable.getStall_num());
        posCarryLog.setRetail_pos_log_order_no(posOrderDetail.getOrderNo());
        posCarryLog.setRetail_pos_log_money(posOrderDetail.getOrderDetailPaymentMoney());
        posCarryLog.setRetail_pos_log_item_name(posOrderDetail.getOrderDetailItem());
        posCarryLog.setRetail_pos_log_amount(posOrderDetail.getOrderDetailAmount());
        posCarryLog.setRetail_pos_log_price(posOrderDetail.getOrderDetailPrice());
        posCarryLog.setRetail_pos_log_std_price(posOrderDetail.getOrderDetailStdPrice());

        posCarryLogDao.insertInTx(posCarryLog);
    }


    /**
     * 修改商品单价
     */
    public static void tryChangeGoodsPrice(PosOrderDetail posOrderDetail){
        ShiftTable shiftTable = LibConfig.activeShiftTable;
        PosMachine posMachine = LibConfig.activePosMachine;
        if(shiftTable == null || posMachine == null)return;
        PosCarryLogDao posCarryLogDao = DaoManager.getInstance().getDaoSession().getPosCarryLogDao();
        PosCarryLog posCarryLog = new PosCarryLog();
        posCarryLog.setRetail_pos_log_type(NAME_BY_CHANGE_PRICE);
        posCarryLog.setIsUpload(false);
        posCarryLog.setBranch_num(shiftTable.getBranch_num());
        posCarryLog.setSystem_book_code(shiftTable.getSystem_book_code());
        posCarryLog.setRetail_pos_log_shift_num(shiftTable.getShift_table_num());
        String nowDateString = TimeUtil.getInstance().getNowDateString();
        posCarryLog.setRetail_pos_log_time(nowDateString);
        posCarryLog.setRetail_pos_log_operator(shiftTable.getShift_table_user_name());
        posCarryLog.setRetail_pos_log_bizday(shiftTable.getShift_table_bizday());
        posCarryLog.setRetail_pos_log_machine(posMachine.getPos_machine_terminal_id());
        posCarryLog.setMerchant_num(shiftTable.getMerchant_num());
        posCarryLog.setStall_num(shiftTable.getStall_num());
        posCarryLog.setRetail_pos_log_order_no(posOrderDetail.getOrderNo());
        posCarryLog.setRetail_pos_log_money(posOrderDetail.getOrderDetailPaymentMoney());
        posCarryLog.setRetail_pos_log_item_name(posOrderDetail.getOrderDetailItem());
        posCarryLog.setRetail_pos_log_amount(posOrderDetail.getOrderDetailAmount());
        posCarryLog.setRetail_pos_log_price(posOrderDetail.getOrderDetailPrice());
        posCarryLog.setRetail_pos_log_std_price(posOrderDetail.getOrderDetailStdPrice());

        posCarryLogDao.insertInTx(posCarryLog);
    }

    /**
     * 修改商品数量
     */
    public static void tryChangeGoodsAmount(PosOrderDetail posOrderDetail){
        ShiftTable shiftTable = LibConfig.activeShiftTable;
        PosMachine posMachine = LibConfig.activePosMachine;
        if(shiftTable == null || posMachine == null)return;
        PosCarryLogDao posCarryLogDao = DaoManager.getInstance().getDaoSession().getPosCarryLogDao();
        PosCarryLog posCarryLog = new PosCarryLog();
        posCarryLog.setRetail_pos_log_type(RETAIL_POS_LOG_CHANGE_AMOUNT);
        posCarryLog.setIsUpload(false);
        posCarryLog.setBranch_num(shiftTable.getBranch_num());
        posCarryLog.setSystem_book_code(shiftTable.getSystem_book_code());
        posCarryLog.setRetail_pos_log_shift_num(shiftTable.getShift_table_num());
        String nowDateString = TimeUtil.getInstance().getNowDateString();
        posCarryLog.setRetail_pos_log_time(nowDateString);
        posCarryLog.setRetail_pos_log_operator(shiftTable.getShift_table_user_name());
        posCarryLog.setRetail_pos_log_bizday(shiftTable.getShift_table_bizday());
        posCarryLog.setRetail_pos_log_machine(posMachine.getPos_machine_terminal_id());
        posCarryLog.setMerchant_num(shiftTable.getMerchant_num());
        posCarryLog.setStall_num(shiftTable.getStall_num());
        posCarryLog.setRetail_pos_log_order_no(posOrderDetail.getOrderNo());
        posCarryLog.setRetail_pos_log_money(posOrderDetail.getOrderDetailPaymentMoney());
        posCarryLog.setRetail_pos_log_item_name(posOrderDetail.getOrderDetailItem());
        posCarryLog.setRetail_pos_log_amount(posOrderDetail.getOrderDetailAmount());
        posCarryLog.setRetail_pos_log_price(posOrderDetail.getOrderDetailPrice());
        posCarryLog.setRetail_pos_log_std_price(posOrderDetail.getOrderDetailStdPrice());

        posCarryLogDao.insertInTx(posCarryLog);
    }

    /**
     * 赠送商品
     */
    public static void tryPresentGoods(PosOrderDetail posOrderDetail){
        ShiftTable shiftTable = LibConfig.activeShiftTable;
        PosMachine posMachine = LibConfig.activePosMachine;
        if(shiftTable == null || posMachine == null)return;
        PosCarryLogDao posCarryLogDao = DaoManager.getInstance().getDaoSession().getPosCarryLogDao();
        PosCarryLog posCarryLog = new PosCarryLog();
        posCarryLog.setRetail_pos_log_type(NAME_BY_PRESENT_GOODS);
        posCarryLog.setIsUpload(false);
        posCarryLog.setBranch_num(shiftTable.getBranch_num());
        posCarryLog.setSystem_book_code(shiftTable.getSystem_book_code());
        posCarryLog.setRetail_pos_log_shift_num(shiftTable.getShift_table_num());
        String nowDateString = TimeUtil.getInstance().getNowDateString();
        posCarryLog.setRetail_pos_log_time(nowDateString);
        posCarryLog.setRetail_pos_log_operator(shiftTable.getShift_table_user_name());
        posCarryLog.setRetail_pos_log_bizday(shiftTable.getShift_table_bizday());
        posCarryLog.setRetail_pos_log_machine(posMachine.getPos_machine_terminal_id());
        posCarryLog.setMerchant_num(shiftTable.getMerchant_num());
        posCarryLog.setStall_num(shiftTable.getStall_num());
        posCarryLog.setRetail_pos_log_order_no(posOrderDetail.getOrderNo());
        posCarryLog.setRetail_pos_log_money(posOrderDetail.getOrderDetailPaymentMoney());
        posCarryLog.setRetail_pos_log_item_name(posOrderDetail.getOrderDetailItem());
        posCarryLog.setRetail_pos_log_amount(posOrderDetail.getOrderDetailAmount());
        posCarryLog.setRetail_pos_log_price(posOrderDetail.getOrderDetailPrice());
        posCarryLog.setRetail_pos_log_std_price(posOrderDetail.getOrderDetailStdPrice());

        posCarryLogDao.insertInTx(posCarryLog);
    }


    /**
     * 删除多少天前的操作数据
     * @param date
     */
    public static void deleteLogData(String date){
        PosCarryLogDao posCarryLogDao = DaoManager.getInstance().getDaoSession().getPosCarryLogDao();
        List<PosCarryLog> list = posCarryLogDao.queryBuilder().where(PosCarryLogDao.Properties.Retail_pos_log_time.le(date)).build().list();
        for (PosCarryLog posCarryLog : list){
           posCarryLogDao.delete(posCarryLog);
        }
    }

    /**
     * 更新
     * @param posCarryLogList
     */
    public static void upDateBean(List<PosCarryLog> posCarryLogList){
        PosCarryLogDao posCarryLogDao = DaoManager.getInstance().getDaoSession().getPosCarryLogDao();
        for (int i=0;i<posCarryLogList.size();i++){
            PosCarryLog posCarryLog=posCarryLogList.get(i);
            posCarryLog.setIsUpload(true);
            posCarryLogDao.updateInTx(posCarryLog);
        }

    }


    /**
     * 上传
     * @param systemBookCode
     * @param branchNum
     * @param shiftTableNum
     * @param isUpload
     * @return
     */
    public static List<PosCarryLog> getBeanList(String systemBookCode, int branchNum, int shiftTableNum, boolean isUpload){
        PosCarryLogDao posCarryLogDao = DaoManager.getInstance().getDaoSession().getPosCarryLogDao();
        return posCarryLogDao.queryBuilder().where(PosCarryLogDao.Properties.System_book_code.eq(systemBookCode),
                PosCarryLogDao.Properties.Branch_num.eq(branchNum),
                PosCarryLogDao.Properties.IsUpload.eq(isUpload)
                ).list();

//        PosCarryLogDao.Properties.Retail_pos_log_shift_num.eq(shiftTableNum)
    }


}
