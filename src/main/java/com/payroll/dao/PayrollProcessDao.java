package com.payroll.dao;

import java.util.List;

import com.payroll.entity.PaygroupReviewer;
import com.payroll.entity.PayrollPay;
import com.ptg.payroll.domain.PayrollGroup;
import com.ptg.payroll.domain.payroll.PayGroup;


public interface PayrollProcessDao {

    boolean checkForMidPayEvent(PayGroup payGroup);
    
    boolean checkPayrollEngineStatus();

    void reInitializePayroll(PayGroup payGroup, String userEmployeeId);

     void createPayrollPay(PayGroup payGroup, List<PayrollPay> employees);

     String getPayrollEngineNextStep(PayGroup payGroup);

     void updatePayrollStatus(PayGroup payGroup, String userId, String status);

     PayrollGroup getPayrollEngineStatus(PayGroup payGroup);

     void updatePayrollDataEntryOption(PayGroup payGroup, String dataEntryOption, String operatorId);

     List<PaygroupReviewer> getPaygroupReviewers(PayGroup payGroup);
    
     void updateOperatorId(PayGroup payGroup, String operatorId);
    
     boolean verifySpecialPayrollCreatedUsr(PayGroup payGroup, String operatorId);
    
     boolean verifySpecialPayrollUpdatedUsrs(PayGroup payGroup, String operatorId);
    
     boolean retroPayCheck(PayGroup payGroup);
    
     boolean checkPayrollFLSA(PayGroup payGroup);
    
     boolean checkPayrollTip(PayGroup payGroup);
}