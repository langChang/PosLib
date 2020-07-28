package com.nhsoft.poslib.service;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.CustomerRegister;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.service.greendao.CustomerRegisterDao;
import com.nhsoft.poslib.utils.MatterUtils;
import com.nhsoft.poslib.utils.TimeUtil;

import java.util.List;

/**
 * CRM 开卡会员
 */
public class CustomerRegisterSerivce {

    private static CustomerRegisterSerivce instance;

    public static CustomerRegisterSerivce getInstance() {

        if (instance == null) {
            instance = new CustomerRegisterSerivce();
        }
        return instance;
    }

    public boolean insertBean(float money,String category_id,String type,String payTypeName,String phone,String bill_no,String levelName) {
        final CustomerRegister customerRegister = setData(money,category_id,type,payTypeName,phone,bill_no,levelName);
        final CustomerRegisterDao customerRegisterDao = DaoManager.getInstance().getDaoSession().getCustomerRegisterDao();
        return MatterUtils.doMatter(customerRegisterDao, new Runnable() {
            @Override
            public void run() {
                customerRegisterDao.insertOrReplaceInTx(customerRegister);
            }
        });
    }
    public boolean insertBean(final CustomerRegister customerRegister) {
        final CustomerRegisterDao customerRegisterDao = DaoManager.getInstance().getDaoSession().getCustomerRegisterDao();
        return MatterUtils.doMatter(customerRegisterDao, new Runnable() {
            @Override
            public void run() {
                customerRegisterDao.insertOrReplaceInTx(customerRegister);
            }
        });
    }

    private CustomerRegister setData(float money,String category_id,String type,String payTypeName,String phone,String bill_no,String levelName){
        CustomerRegister customerRegister = new CustomerRegister();
        customerRegister.setBranch_num(LibConfig.activeLoginBean.getBranch_num());
        customerRegister.setShift_table_bizday(LibConfig.activeShiftTable.getShift_table_bizday());
        customerRegister.setShift_table_num(LibConfig.activeShiftTable.getShift_table_num());
        customerRegister.setSystem_book_code(LibConfig.activeLoginBean.getSystem_book_code());
        customerRegister.setVip_card_user_category(category_id);
        customerRegister.setVip_card_user_date(TimeUtil.getNowDateString(TimeUtil.FORMAT_ONE));
        customerRegister.setVip_card_user_memo("");
        customerRegister.setVip_card_user_type(type);
        customerRegister.setVip_card_user_payment(payTypeName);
        customerRegister.setVip_card_user_valid_date(TimeUtil.getNowDateString(TimeUtil.FORMAT_ONE));
        customerRegister.setVip_card_user_operator(LibConfig.activeShiftTable.getShiftTableUserName());
        customerRegister.setVip_card_user_phone(phone);
        customerRegister.setVip_card_user_ref_bill("");
        customerRegister.setVip_card_user_log_fid(bill_no);
        customerRegister.setVip_card_user_level_name(levelName);
        customerRegister.setVip_card_user_money(money);
        return customerRegister;
    }

    /**
     * 根据fid 查找对应的bean
     * @param fid
     * @return
     */
    public CustomerRegister getCustomerRegisterByFid(String fid) {
        final CustomerRegisterDao customerRegisterDao = DaoManager.getInstance().getDaoSession().getCustomerRegisterDao();
        List<CustomerRegister> list = customerRegisterDao.queryBuilder().where(CustomerRegisterDao.Properties.Vip_card_user_log_fid.eq(fid)).list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据fid 更改对应的bean的上传状态
     * @param fid
     */
    public void changeState(String fid){
        final CustomerRegisterDao customerRegisterDao = DaoManager.getInstance().getDaoSession().getCustomerRegisterDao();
        List<CustomerRegister> list = customerRegisterDao.queryBuilder().where(CustomerRegisterDao.Properties.Vip_card_user_log_fid.eq(fid)).list();
        if (list != null && list.size() > 0) {
            CustomerRegister customerRegister = list.get(0);
            customerRegister.setVip_card_user_state(true);
            insertBean(customerRegister);
        }
    }

    /**
     * 获取当前班次的 付费会员开卡次数
     *
     * @param systemBookCode
     * @param shiftTableNum
     * @param branchNum
     * @return
     */
    public int getNumShiftTableNum(String systemBookCode, String shiftTableNum, String branchNum) {
        int num = 0;
        final CustomerRegisterDao customerRegisterDao = DaoManager.getInstance().getDaoSession().getCustomerRegisterDao();
        List<CustomerRegister> list = customerRegisterDao.queryBuilder().where(
                CustomerRegisterDao.Properties.System_book_code.eq(systemBookCode),
                CustomerRegisterDao.Properties.Shift_table_num.eq(shiftTableNum),
                CustomerRegisterDao.Properties.Branch_num.eq(branchNum)
        ).list();

        if (list != null && list.size() > 0) {
            num = list.size();
        }

        return num;
    }

    /**
     * 获取当前营业日的 付费会员开卡次数
     *
     * @param systemBookCode
     * @param shiftTableBizday
     * @param branchNum
     * @return
     */
    public int getNumShiftTableBizday(String systemBookCode, String shiftTableBizday, String branchNum) {
        int num = 0;
        final CustomerRegisterDao customerRegisterDao = DaoManager.getInstance().getDaoSession().getCustomerRegisterDao();
        List<CustomerRegister> list = customerRegisterDao.queryBuilder().where(
                CustomerRegisterDao.Properties.System_book_code.eq(systemBookCode),
                CustomerRegisterDao.Properties.Shift_table_bizday.eq(shiftTableBizday),
                CustomerRegisterDao.Properties.Branch_num.eq(branchNum)
        ).list();

        if (list != null && list.size() > 0) {
            num = list.size();
        }

        return num;
    }

    /**
     * 获取当前班次的 付费会员开卡金额
     *
     * @param systemBookCode
     * @param shiftTableNum
     * @param branchNum
     * @return
     */
    public float getMoneyShiftTableNum(String systemBookCode, String shiftTableNum, String branchNum) {
        float money = 0;
        final CustomerRegisterDao customerRegisterDao = DaoManager.getInstance().getDaoSession().getCustomerRegisterDao();
        List<CustomerRegister> list = customerRegisterDao.queryBuilder().where(
                CustomerRegisterDao.Properties.System_book_code.eq(systemBookCode),
                CustomerRegisterDao.Properties.Shift_table_num.eq(shiftTableNum),
                CustomerRegisterDao.Properties.Branch_num.eq(branchNum)
        ).list();

        if (list != null && list.size() > 0) {
            for (CustomerRegister customerRegister : list) {
                money = money + customerRegister.getVip_card_user_money();
            }
        }

        return money;
    }

    /**
     * 获取当前营业日的 付费会员开卡金额
     *
     * @param systemBookCode
     * @param shiftTableBizday
     * @param branchNum
     * @return
     */
    public float getMoneyShiftTableBizday(String systemBookCode, String shiftTableBizday, String branchNum) {
        float money = 0;
        final CustomerRegisterDao customerRegisterDao = DaoManager.getInstance().getDaoSession().getCustomerRegisterDao();
        List<CustomerRegister> list = customerRegisterDao.queryBuilder().where(
                CustomerRegisterDao.Properties.System_book_code.eq(systemBookCode),
                CustomerRegisterDao.Properties.Shift_table_bizday.eq(shiftTableBizday),
                CustomerRegisterDao.Properties.Branch_num.eq(branchNum)
        ).list();

        if (list != null && list.size() > 0) {
            for (CustomerRegister customerRegister : list) {
                money = money + customerRegister.getVip_card_user_money();
            }
        }

        return money;
    }


    public List<CustomerRegister> getBeanList(String systemBookCode, int shiftTableNum, int branchNum){
        final CustomerRegisterDao customerRegisterDao = DaoManager.getInstance().getDaoSession().getCustomerRegisterDao();
        List<CustomerRegister> list = customerRegisterDao.queryBuilder().where(
                CustomerRegisterDao.Properties.System_book_code.eq(systemBookCode),
                CustomerRegisterDao.Properties.Shift_table_num.eq(shiftTableNum),
                CustomerRegisterDao.Properties.Branch_num.eq(branchNum),
                CustomerRegisterDao.Properties.Vip_card_user_state.eq(false)
        ).build().list();
        return list;
    }

}
