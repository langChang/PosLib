package com.nhsoft.poslib.service;

import android.database.Cursor;
import android.text.TextUtils;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.Branch;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.shift.ShiftTable;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.service.greendao.BranchDao;
import com.nhsoft.poslib.service.greendao.DaoSession;
import com.nhsoft.poslib.service.greendao.PosOrderDao;
import com.nhsoft.poslib.service.greendao.ShiftTableDao;
import com.nhsoft.poslib.utils.EvtLog;
import com.nhsoft.poslib.utils.MatterUtils;
import com.nhsoft.poslib.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class ShiftTableService {
    private static ShiftTableService instance;

    public static ShiftTableService getInstance() {
        if (instance == null) {
            instance = new ShiftTableService();
        }
        return instance;
    }

    /*
     * 1、查找上个营业日未交班的班次
     * （根据账套号，终端id，分店编号）
     * （1、时间戳比当前营业日小 2、流水为true 3、交班为false）
     * */
    private DaoSession mDaoSession = DaoManager.getInstance().getDaoSession();
    ShiftTableDao mShiftTableDao = mDaoSession.getShiftTableDao();

    public List<ShiftTable> getUnClosedShiftTable(String systemBookCode, int branchNum) {


        return mShiftTableDao.queryBuilder()
                .where(
                        ShiftTableDao.Properties.System_book_code.eq(systemBookCode)//根据账套号
                        , ShiftTableDao.Properties.Branch_num.eq(branchNum)//branchNum
                        , ShiftTableDao.Properties.Shift_table_bizday.lt(getCurrentBizday(systemBookCode, branchNum))//结班时间
//                        , ShiftTableDao.Properties.Shift_table_need_carry.eq(true)//流水
                        , ShiftTableDao.Properties.Shift_table_closed.eq(false)//交班为false
                )
//                .orderAsc(ShiftTableDao.Properties.BranchNum)
                .list();

    }

    /**
     * 2、查找当前营业日当前班次是否未交班
     * *      * （1、时间戳为当前营业日 2、用户为当前用户 3、交班为false）
     *
     * @param systemBookCode
     * @param shiftTableBizday
     * @param appUserNum
     * @return
     */
    public ShiftTable getCurrentClosedShiftTable(String systemBookCode, int branchNum, String shiftTableBizday,
                                                 long appUserNum) {
        return mShiftTableDao.queryBuilder().where(
                ShiftTableDao.Properties.System_book_code.eq(systemBookCode)//根据账套号
                , ShiftTableDao.Properties.Branch_num.eq(branchNum)//
                , ShiftTableDao.Properties.Shift_table_bizday.eq(shiftTableBizday)//
                , ShiftTableDao.Properties.Shift_table_user_num.eq(appUserNum)
                , ShiftTableDao.Properties.Shift_table_closed.eq(false)
        ).unique();
    }

    /**
     * 3、强制交班
     * * （1、更新交班状态为true 2、更新交班时间 3、更新班次最后修改时间）
     *
     * @param shiftTable
     * @return
     */
    public ShiftTable closeShiftTable(ShiftTable shiftTable) {
        shiftTable.setShiftTableClosed(true);
        shiftTable.setShiftTableEnd(TimeUtil.getInstance().getNowDate());
        shiftTable.setShiftTableLastEditTime(TimeUtil.getInstance().getNowDate());
        if (insert(shiftTable)) {
            return shiftTable;
        } else {
            return null;
        }

    }

    /**
     * 4、创建新的班次
     * * （1、开班日期 2、收银员编号 3、收银员代码 4、班次的收银员名称 5、终端标识
     * * 6、账套号 7、分店编号 8、班次号 9、班次最后修改时间 10、营业日 yyyymmdd）
     *
     * @param shiftTable
     * @return
     */
    public ShiftTable createShiftTable(ShiftTable shiftTable) {
        shiftTable.setShiftTableNeedCarry(false);//流水
        shiftTable.setShiftTableSynchronized(false);//上传
        shiftTable.setShiftTableClosed(false);//交班
        shiftTable.setShiftTableUploadTimes(0);
        shiftTable.setShiftTableLastEditTime(TimeUtil.getInstance().getNowDate());
        insert(shiftTable);
        return shiftTable;
    }

    //插入
    public boolean insert(final ShiftTable shiftTable) {
        return MatterUtils.doMatter(mDaoSession.getShiftTableDao(), new Runnable() {
            @Override
            public void run() {
                mDaoSession.getShiftTableDao()
                        .insertOrReplaceInTx(shiftTable);
            }
        });
    }

    public List<ShiftTable> loadAll() {
        final ShiftTableDao shiftTableDao = DaoManager.getInstance().getDaoSession().getShiftTableDao();
        return shiftTableDao.loadAll();
    }


    //如果有流水，改变标记
    public boolean changeStateShiftTable(final ShiftTable shiftTable) {
        final ShiftTableDao shiftTableDao = DaoManager.getInstance().getDaoSession().getShiftTableDao();
        return MatterUtils.doMatter(shiftTableDao, new Runnable() {
            @Override
            public void run() {
                shiftTable.setShiftTableNeedCarry(true);
                shiftTableDao.insertOrReplaceInTx(shiftTable);
            }
        });
    }

    /**
     * 获取班次号
     *
     * @param systemBookCode
     * @param branchNum
     * @param posMachineSequence
     * @return
     */

    public int getShiftTableNum(String systemBookCode, int branchNum, int posMachineSequence) {
        int min = posMachineSequence * 1000;
        int max = min + 1000;
        int pos_machine_sequence = 0;
        ShiftTable shi = mShiftTableDao.queryBuilder().where(
                ShiftTableDao.Properties.System_book_code.eq(systemBookCode)//根据账套号
                , ShiftTableDao.Properties.Branch_num.eq(branchNum)//
                , ShiftTableDao.Properties.Shift_table_num.between(min, max)
        ).orderDesc(ShiftTableDao.Properties.Shift_table_num).limit(1).offset(0).unique();
        if (shi == null) {
            pos_machine_sequence = min + 1;
        } else {
            pos_machine_sequence = shi.getShiftTableNum() + 1;
        }

        return (pos_machine_sequence);
    }

    /**
     * 获取当前营业日
     *
     * @param systemBookCode
     * @param branchNum
     * @return
     */

    public String getCurrentBizday(String systemBookCode, int branchNum) {
        String strCurrentBizDay = null;
        BranchDao branchDao = mDaoSession.getBranchDao();
        Branch branch = branchDao.queryBuilder()
                .where(
                        BranchDao.Properties.System_book_code.eq(systemBookCode)
                        , BranchDao.Properties.Branch_num.eq(branchNum)
                )
                .limit(1)
                .offset(0).unique();
        try {
            String branch_close_time = branch.getBranch_close_time();
            long dateToStamp=0;
            if (TextUtils.isEmpty(branch_close_time)){
                strCurrentBizDay = TimeUtil.getInstance().getCurrDateSelfType(TimeUtil.LONG_DATE_FORMAT);
            }else {
                 dateToStamp = TimeUtil.getInstance().dateToStamp(branch_close_time);
            }

            long time = TimeUtil.getInstance().getNowDate().getTime();
            if (LibConfig.saleParamsBean.getEnableBranchCloseTime()==null|| LibConfig.saleParamsBean.getEnableBranchCloseTime()) {
                if (dateToStamp < time) {
                    strCurrentBizDay = TimeUtil.getInstance().getCurrDateSelfType(TimeUtil.LONG_DATE_FORMAT);
                } else {
                    strCurrentBizDay = TimeUtil.getInstance().getYesterdayDate();
                }
            } else {
                strCurrentBizDay = TimeUtil.getInstance().getCurrDateSelfType(TimeUtil.LONG_DATE_FORMAT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strCurrentBizDay;
    }


    /**
     * 获取该营业日下所有班次
     *
     * @param systemBookCode
     * @param branchNum
     * @param bizDay
     * @return
     */
    public List<ShiftTable> getListShiftList(String systemBookCode, int branchNum, String bizDay) {
        ShiftTableDao shiftTableDao = DaoManager.getInstance().getDaoSession().getShiftTableDao();
        return shiftTableDao.queryBuilder().where(ShiftTableDao.Properties.System_book_code.eq(systemBookCode),
                ShiftTableDao.Properties.Branch_num.eq(branchNum),
                ShiftTableDao.Properties.Shift_table_bizday.eq(bizDay)).list();
    }

    /**
     * 获取所有的营业日
     *
     * @param systemBookCode
     * @param branchNum
     * @return
     */
    public List<String> getListBizDayList(String systemBookCode, int branchNum) {
        ShiftTableDao shiftTableDao = DaoManager.getInstance().getDaoSession().getShiftTableDao();
        List<String> stringList = new ArrayList<>();
        try {
            String sql = "select SHIFT_TABLE_BIZDAY from SHIFT_TABLE where SYSTEM_BOOK_CODE = ? " +
                    "and BRANCH_NUM= ?  group by SHIFT_TABLE_BIZDAY order by SHIFT_TABLE_BIZDAY desc";
            String[] strings = {systemBookCode, String.valueOf(branchNum)};
            Cursor cursor = shiftTableDao.getDatabase().rawQuery(sql, strings);
            while (cursor.moveToNext()) {
                String bizDay = cursor.getString(0);
                stringList.add(bizDay);
            }
//                for (int i=0;i<cursor.getCount())
        } catch (Exception e) {
            EvtLog.d("getListBizDayList=:" + e.toString());
        }

        return stringList;
    }


    /**
     * 删除过期历史班次
     *
     * @param date
     */
    public void deleteShiftTable(String date) {
//        date=date+" 00:00:00";

        ShiftTableDao shiftTableDao = DaoManager.getInstance().getDaoSession().getShiftTableDao();
        List<ShiftTable> shiftTableList = shiftTableDao.queryBuilder().list();

        for (ShiftTable shiftTable : shiftTableList) {
            try {
                String string = shiftTable.getShiftTableBizday().substring(0, 4) + "-" +
                        shiftTable.getShiftTableBizday().substring(4, 6) + "-" + shiftTable.getShiftTableBizday().substring(6, 8) + " 00:00:00";
                long l1 = TimeUtil.getInstance().dateToStamp(string);
                long l2 = TimeUtil.getInstance().dateToStamp(date + " 00:00:00");
                if (l1 < l2) {
                    shiftTableDao.delete(shiftTable);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<ShiftTable> selectShiftTable(final String date) {
        ShiftTableDao shiftTableDao = DaoManager.getInstance().getDaoSession().getShiftTableDao();
        List<ShiftTable> shiftTableList = shiftTableDao.queryBuilder().where(ShiftTableDao.Properties.Shift_table_bizday.ge(date)).list();
        return shiftTableList;
    }


    public void modifyOrderUpLoadFlag(ShiftTable shiftTable) {
        PosOrderDao posOrderDao = DaoManager.getInstance().getDaoSession().getPosOrderDao();
        List<PosOrder> list = posOrderDao.queryBuilder().where(
                PosOrderDao.Properties.BranchNum.eq(shiftTable.getBranch_num()),
                PosOrderDao.Properties.ShiftTableNum.eq(shiftTable.getShiftTableNum()),
                PosOrderDao.Properties.ShiftTableBizday.eq(shiftTable.getShiftTableBizday()),
                PosOrderDao.Properties.SystemBookCode.eq(shiftTable.getSystem_book_code())
        ).list();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                PosOrder posOrder = list.get(i);
                posOrder.setOrderUploadState(false);
                posOrderDao.insertOrReplaceInTx(posOrder);
            }
        }

    }


}
