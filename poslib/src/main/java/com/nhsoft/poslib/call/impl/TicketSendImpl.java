package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.TicketSendDetail;
import com.nhsoft.poslib.service.greendao.TicketSendDetailDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.List;

/**
 * Created by Iverson on 2019/1/14 5:05 PM
 * 此类用于：
 */
public class TicketSendImpl {
    private static TicketSendImpl instance;

    public static TicketSendImpl getInstance() {
        if (instance == null) {
            instance = new TicketSendImpl();
        }
        return instance;
    }

    public boolean saveTicketSendList(final List<TicketSendDetail> ticketSendList) {
        final TicketSendDetailDao ticketSendDetailDao = DaoManager.getInstance().getDaoSession().getTicketSendDetailDao();

        return MatterUtils.doMatter(ticketSendDetailDao, new Runnable() {
            @Override
            public void run() {
                    for (int i=0; i< ticketSendList.size(); i++){
                        ticketSendDetailDao.insertOrReplace(ticketSendList.get(i));
                    }

            }
        });
    }

    public void updateTicketStatus(List<TicketSendDetail> ticketSendDetails){
        final TicketSendDetailDao ticketSendDetailDao = DaoManager.getInstance().getDaoSession().getTicketSendDetailDao();
        for (TicketSendDetail ticketSendDetail: ticketSendDetails){
            TicketSendDetail loadTicketDetail = ticketSendDetailDao.load(ticketSendDetail.getTicket_send_fid());
            loadTicketDetail.setTicket_send_detail_sync_flag(true);
            ticketSendDetailDao.insertOrReplace(loadTicketDetail);
        }
    }


    public void updateTicketsStatus(List<TicketSendDetail> ticketSendDetails){
        final TicketSendDetailDao ticketSendDetailDao = DaoManager.getInstance().getDaoSession().getTicketSendDetailDao();
        for (TicketSendDetail ticketSendDetail: ticketSendDetails){
            ticketSendDetail.setTicket_send_detail_sync_flag(true);
            ticketSendDetailDao.insertOrReplaceInTx(ticketSendDetail);
        }
    }

    /**
     * 保存bean
     * @param ticketSendDetails
     */
    public void saveTicketsStatus(List<TicketSendDetail> ticketSendDetails){
        final TicketSendDetailDao ticketSendDetailDao = DaoManager.getInstance().getDaoSession().getTicketSendDetailDao();
        for (TicketSendDetail ticketSendDetail: ticketSendDetails){
            ticketSendDetailDao.insertOrReplaceInTx(ticketSendDetail);
        }
    }

//    public boolean deleteTicketDetail(final String ticket_send_fid, final TicketSendDetailDao ticketSendDetailDao) {
//        return MatterUtils.doMatter(ticketSendDetailDao, new Runnable() {
//            @Override
//            public void run() {
//                List<TicketSendDetail> ticketSendDetails = ticketSendDetailDao._queryTicketSend_Ticket_detail_list(ticket_send_fid);
//                ticketSendDetailDao.deleteInTx(ticketSendDetails);
//            }
//        });
//    }

    /**
     * 获取该班次下所有未上传的赠券
     * @param systemBookCode
     * @param branchNum
//     * @param shiftTableNum
     * @param ticketSendDetailSyncFlag
     * @return
     */
    public List<TicketSendDetail> getTicketSendDetailList(String systemBookCode,int branchNum,boolean ticketSendDetailSyncFlag){
        TicketSendDetailDao ticketSendDetailDao=DaoManager.getInstance().getDaoSession().getTicketSendDetailDao();
        return ticketSendDetailDao.queryBuilder().where(
                TicketSendDetailDao.Properties.System_book_code.eq(systemBookCode),
                TicketSendDetailDao.Properties.Ticket_send_detail_sync_flag.eq(ticketSendDetailSyncFlag),
                TicketSendDetailDao.Properties.Branch_num.eq(branchNum)
//                TicketSendDetailDao.Properties.ShiftTableNum.eq(shiftTableNum)
        ).list();
    }
}
