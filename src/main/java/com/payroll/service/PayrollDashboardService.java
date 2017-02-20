package com.payroll.service;
import java.util.Date;
import java.util.List;

import com.ptg.payroll.domain.Payroll;
import com.ptg.payroll.domain.PayrollList;
import com.ptg.payroll.domain.PayrollUserSecurity;
import com.ptg.payroll.domain.SpecialPayroll;
import com.ptg.payroll.domain.payroll.PayGroup;
import com.ptg.payroll.domain.payroll.SpecialPayrollInfo;
import com.ptg.payroll.entity.ps.pe.dashboard.PayrollDeadline;



public interface PayrollDashboardService {

	public List<Payroll> getPayrollGroups(String company, Date startDate, Date endDate);
	
	public List<PayrollList> getPayrollGroupsList(String company, String personId, Date startDate, Date endDate);
	
	public SpecialPayroll getSpecialPayroll(PayGroup payGroup);
	
	public void createOrUpdateSpecialPayroll(SpecialPayroll specialPayroll, String employeeId);
	
	public void deleteSpecialPayroll(PayGroup payGroup, PayrollUserSecurity payrollUserSecurity, String employeeId);
		
	public void updatePayrollCheckDate(PayGroup payGroup, Date checkDate);
	
	public PayrollDeadline getPayrollDeadline(String company);
	
	public SpecialPayrollInfo getSpecialPayrollPageInfo(String companyId);
	public SpecialPayrollInfo getSpecialPayrollPageInfo(String companyId,String emplid);

	public void updateIsRecallable(List<Payroll> payrolls);
	
}