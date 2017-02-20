package com.payroll.utils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.payroll.entity.EarnCode;
import com.ptg.payroll.domain.LeaveAccrualBean;
import com.ptg.payroll.domain.payroll.EmployeeLeaveAccrual;


public class PayrollDataAdapter {

    /**
     * Convert LeaveAccrualBean list to EmployeeLeaveAccrual
     * 
     * @param leaveAccruals
     * @return
     */
    public static List<EmployeeLeaveAccrual> getEmployeesLeaveAccrualList(List<LeaveAccrualBean> leaveAccruals) {

        Map<String, EmployeeLeaveAccrual> employees = new HashMap<String, EmployeeLeaveAccrual>();

        if (leaveAccruals != null) {
            for (LeaveAccrualBean leaveAccrual : leaveAccruals) {
                // If exist add new leave accrual
                if (employees.containsKey(leaveAccrual.getEmplId())) {
                    EmployeeLeaveAccrual employee = employees.get(leaveAccrual.getEmplId());
                    employee.addLeaveAccrual(leaveAccrual.getErnCd(), leaveAccrual.getBalanceHours());
                }
                // If not exists create new employee leave accrual
                else {
                    EmployeeLeaveAccrual employee = new EmployeeLeaveAccrual(leaveAccrual);
                    employees.put(leaveAccrual.getEmplId(), employee);
                }
            }
        }
        return new ArrayList<EmployeeLeaveAccrual>(employees.values());
    }

    public static Map<String, EarnCode> getEarnCodeMapFromEarnCodeList(List<EarnCode> earningCodeList,
            boolean isLeaveAccrualSystemClient) {

        Map<String, EarnCode> earningCodeMap = new HashMap<String, EarnCode>();
        if (earningCodeList != null) {
            for (EarnCode companyErnCode : earningCodeList) {
                if (!isLeaveAccrualSystemClient) {
                    companyErnCode.setIsLoadLeaveEarnCd("N");
                }
                earningCodeMap.put(companyErnCode.getCode(), companyErnCode);
            }
        }
        return earningCodeMap;
    }
    
    public static Date convertDateToNewTimeZone(Date date, TimeZone toTimeZone) {

        Calendar calendar = Calendar.getInstance();
        TimeZone fromTimeZone = calendar.getTimeZone();

        calendar.setTimeZone(fromTimeZone);
        calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);
        if (fromTimeZone.inDaylightTime(calendar.getTime())) {
            calendar.add(Calendar.MILLISECOND, calendar.getTimeZone().getDSTSavings() * -1);
        }

        calendar.add(Calendar.MILLISECOND, toTimeZone.getRawOffset());
        if (toTimeZone.inDaylightTime(calendar.getTime())) {
            calendar.add(Calendar.MILLISECOND, toTimeZone.getDSTSavings());
        }
        return calendar.getTime();
    }

}
