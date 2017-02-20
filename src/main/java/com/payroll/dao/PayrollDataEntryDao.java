package com.payroll.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.payroll.entity.PayrollEarningRecord;
import com.payroll.entity.PayrollEmployeeJobCodeRecord;
import com.payroll.entity.PayrollEmployeeRecord;
import com.payroll.entity.PayrollEmployeeRecordBackend;
import com.payroll.entity.PayrollEmployeeRecordVW;
import com.payroll.entity.PayrollError;
import com.ptg.payroll.domain.Department;
import com.ptg.payroll.domain.payroll.DepartmentSplit;
import com.ptg.payroll.domain.payroll.PayGroup;

public interface PayrollDataEntryDao {

    public List<PayrollEmployeeRecordVW> getPayrollEmployeesView(PayGroup payGroup);
    
    public List<PayrollEmployeeRecordVW> getPayrollEmployeesViewForEmployee(PayGroup payGroup, String employeeId);

    public List<PayrollEmployeeJobCodeRecord> getPayrollEmployeesJobCodeViewForEmployee(PayGroup payGroup, String employeeId);

    public List<PayrollEarningRecord> getPayrollEmployeeEarnings(PayGroup payGroup);
    
    public List<PayrollEarningRecord> getPayrollEmployeeEarningsForEmployee(PayGroup payGroup, String employeeId);

    public Map<String, String> getPayrollEmployeesPaymentMethod(PayGroup payGroup);
    
    public Map<String, String> getPayrollEmployeesPaymentMethodForEmployee(PayGroup payGroup, String employeeId);
    
    public List<PayrollEarningRecord> resetPayrollForEmployee(PayGroup payGroup, String employeeId);

    public void deleteEmployeeEarnings(PayGroup payGroup, String employeeId, Date periodStartDate, Date periodEndDate, Integer checkNumber);

    public void insertPayrollEmployeeLineItems(PayGroup payGroup, List<PayrollEmployeeRecord> employeeRecords, List<PayrollEarningRecord> earningRecords);

    public List<DepartmentSplit> getDepartmentSplits(PayGroup payGroup);
    
    public List<DepartmentSplit> getDepartmentSplitsForEmployee(PayGroup payGroup, String employeeId);

    public List<Department> getCompanyDepartments(PayGroup payGroup);

    public String getDefaultEarnCode(PayGroup payGroup);

    public List<PayrollEmployeeRecord> getPayrollEmployeeRecords(PayGroup payGroup);

	public List<PayrollEmployeeRecordBackend> getPayrollEmployeesChangedByBackend(PayGroup payGroup);
	
	public void insertPayrollErrors(List<PayrollError> payrollErrors);

	public void updateAdditionalPay(PayGroup payGroup);
}