package com.payroll.dao;
import java.util.List;

import com.payroll.entity.PayrollEarningCode;
import com.payroll.entity.pk.PayrollStagingRecordKey;
import com.payroll.entity.ps.payrollentry.EmployeeDOM;
import com.payroll.entity.ps.payrollentry.PayrollEntryValidator;
import com.ptg.payroll.domain.Department;
import com.ptg.payroll.domain.EmployeeDepartmentSplit;
import com.ptg.payroll.domain.EmployeeLeaveBalance;
import com.ptg.payroll.domain.payroll.PayGroup;
import com.ptg.payroll.entity.ps.pe.dashboard.PayrollDeadline;


public interface PayrollDataAccessDao {

    public List<EmployeeDOM> getOnCycleEmployees(PayGroup payGroup);

    public List<EmployeeDOM> getOnCycleAllEmployees(PayGroup payGroup);

    public List<EmployeeDOM> getTerminatedEmployees(PayGroup payGroup);

    public List<EmployeeDOM> getOffCycleEmployees(PayGroup payGroup);

    public List<EmployeeDOM> getPayrollEmployees(PayGroup payGroup);

    public List<EmployeeDOM> getPayrollTerminatedEmployees(PayGroup payGroup);

    public List<Department> getCompanyDepartments(PayGroup payGroup);

    public List<EmployeeDepartmentSplit> getEmployeeDepartments(PayGroup payGroup, String emplId);

    public List<PayrollEntryValidator> getPayrollEntryValidators(PayGroup payGroup);

    public List<EmployeeLeaveBalance> getPayrollEmployeesLeaveAccrual(PayGroup payGroup);

    public List<PayrollEarningCode> getPayrollEarningCodes(PayGroup payGroup);

    public void insertPayrollEarningCodes(PayGroup payGroup, List<PayrollEarningCode> payrollEaningCodes);

    public boolean checkifPayrollEntryByEmployee(Long id, String employeeId);

    public PayrollDeadline getPayrollBlackoutTime(PayGroup payGroup);
    
    public List<String> getReportedEarnCodesForPayroll(PayGroup payGroup);
    
    public void deleteReportedEarnCodeRecords (String deletedEarnCodes,PayGroup payGroup );

	public List<EmployeeDOM> getOnCycleEmployeesWithDeptLoc(PayGroup payGroup);
	
	public boolean checkPayrollBlackout(PayGroup payGroup);

	public List<PayrollStagingRecordKey> getUniqueEmployeeForPaygroup(PayGroup payGroup);

}