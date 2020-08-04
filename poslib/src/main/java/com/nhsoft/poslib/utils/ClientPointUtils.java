package com.nhsoft.poslib.utils;

import android.text.TextUtils;

import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.call.impl.ClientPointImpl;
import com.nhsoft.poslib.entity.Branch;
import com.nhsoft.poslib.entity.ClientPoint;
import com.nhsoft.poslib.entity.shift.ShiftTable;

/**
 * Created by Iverson on 2019/1/16 4:30 PM
 * 此类用于：保存积分
 */
public class ClientPointUtils {

    public static ClientPoint saveClientPoint(ShiftTable shiftTable, Branch branch, int pointValue,String cardUserNum,String cardUserCustName,String order_no, String uploadType){
        ClientPoint clientPoint = new ClientPoint();
        String nowDateString = TimeUtil.getInstance().getNowDateString();
        clientPoint.setClient_point_fid(order_no);
        clientPoint.setSystem_book_code(shiftTable.getSystemBookCode());
        clientPoint.setBranch_num(shiftTable.getBranchNum());
        if(!TextUtils.isEmpty(cardUserNum)){
            clientPoint.setCard_user_num(Integer.parseInt(cardUserNum));
        }

        clientPoint.setClient_point_branch_name(branch.getBranch_name());
        clientPoint.setClient_point_cust_name(cardUserCustName);
        clientPoint.setClient_point_date(nowDateString);
        clientPoint.setClient_point_balance(pointValue);
        clientPoint.setClient_point_operate_type(uploadType);
        clientPoint.setClient_point_operator(shiftTable.getShiftTableUserName());
        clientPoint.setClient_point_memo("");
        clientPoint.setClient_point_uuid(UUIDUtils.getUUID32());
        clientPoint.setClient_point_sync(false);
        clientPoint.setClient_point_del_flag(false);
        clientPoint.setShift_table_bizday(shiftTable.getShiftTableBizday());
        clientPoint.setShift_table_num(shiftTable.getShiftTableNum());
        clientPoint.setClient_point_last_edit_time(nowDateString);
        if(ClientPointImpl.saveClient(clientPoint)){
            return clientPoint;
        }else {
            return null;
        }
    }

    public static void updateClientPoint(ClientPoint clientPoint){
        RetailPosManager.getInstance().updateClientPointStatus(clientPoint);
    }

    public static ClientPoint DepositClientPoint(ShiftTable shiftTable, Branch branch, int pointValue,String cardUserNum,String cardUserCustName, String uploadType){
        ClientPoint clientPoint = new ClientPoint();
        String nowDateString = TimeUtil.getInstance().getNowDateString();
        clientPoint.setClient_point_fid(UUIDUtils.getUUID32());
        clientPoint.setSystem_book_code(shiftTable.getSystemBookCode());
        clientPoint.setBranch_num(shiftTable.getBranchNum());
        clientPoint.setCard_user_num(Integer.parseInt(cardUserNum));
        clientPoint.setClient_point_branch_name(branch.getBranch_name());
        clientPoint.setClient_point_cust_name(cardUserCustName);
        clientPoint.setClient_point_date(nowDateString);
        clientPoint.setClient_point_balance(pointValue);
        clientPoint.setClient_point_operate_type(uploadType);
        clientPoint.setClient_point_operator(shiftTable.getShiftTableUserName());
        clientPoint.setClient_point_memo("");
        clientPoint.setClient_point_uuid(UUIDUtils.getUUID32());
        clientPoint.setClient_point_sync(false);
        clientPoint.setClient_point_del_flag(false);
        clientPoint.setShift_table_bizday(shiftTable.getShiftTableBizday());
        clientPoint.setShift_table_num(shiftTable.getShiftTableNum());
        clientPoint.setClient_point_last_edit_time(nowDateString);
//        if(ClientPointService.saveClient(clientPoint)){
            return clientPoint;
//        }else {
//            return null;
//        }
    }
}
