package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.Employee;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.service.greendao.EmployeeDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2018/11/19 5:34 PM
 * 此类用于：
 */
public class EmployeeImpl {

    private static EmployeeImpl instance;
    public static EmployeeImpl getInstance() {
        if (instance == null) {
            instance = new EmployeeImpl();
        }
        return instance;
    }


    public boolean saveEmployeeList(final List<Employee> employeeList){
        final EmployeeDao employeeDao = DaoManager.getInstance().getDaoSession().getEmployeeDao();
        employeeDao.deleteAll();
        if(employeeList.size() == 0)return true;
        boolean isSuccess = MatterUtils.doMatter(employeeDao, new Runnable() {
            @Override
            public void run() {
                employeeDao.insertOrReplaceInTx(employeeList);
            }
        });
        return isSuccess;
    }

    public static List<Employee> getAllEmployee(){
        EmployeeDao employeeDao = DaoManager.getInstance().getDaoSession().getEmployeeDao();
        return employeeDao.queryBuilder().where(EmployeeDao.Properties.Employee_kind.eq("销售员"),
                EmployeeDao.Properties.Employee_actived.eq(true),EmployeeDao.Properties.Branch_num.eq(LibConfig.activeLoginBean.getBranch_num())).build().list();
    }

    public static List<String> getAllEmployeeName(){
        EmployeeDao employeeDao = DaoManager.getInstance().getDaoSession().getEmployeeDao();
        List<Employee> salemanList = employeeDao.queryBuilder().where(EmployeeDao.Properties.Employee_kind.eq("销售员"),
                EmployeeDao.Properties.Employee_actived.eq(true), EmployeeDao.Properties.Branch_num.eq(LibConfig.activeLoginBean.getBranch_num())).build().list();
        List<String> salemanNameList = new ArrayList<>();

        for (Employee employee : salemanList){
            salemanNameList.add(employee.getEmployee_name());
        }

        return salemanNameList;
    }
}
