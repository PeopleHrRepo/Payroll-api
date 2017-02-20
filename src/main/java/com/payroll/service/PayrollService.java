package com.payroll.service;

import java.util.List;

import com.payroll.entity.EarnCode;
import com.ptg.payroll.domain.PayrollUserCompany;
import com.ptg.payroll.domain.payroll.PayGroup;
import com.ptg.payroll.entity.ps.pe.dashboard.PayrollDeadline;




public interface PayrollService {
	 List<EarnCode> getEarningCodes(String company);
	 List<EarnCode> getEarningCodesByCompany(String company);
	 boolean getLeaveAccrualSystemClientStatus(String company);
	 PayGroup findPayrollInstance(Long id);
	 List<EarnCode> getEarningCodesByPayGroup(String company, String payGroup);
	 com.ptg.payroll.domain.payroll.User getUserRolePermissions(String company, String personId, String employeeId);
	 String getUserDeptLocationAccess(String company, String personId);
	 
	 public List<PayrollUserCompany> getUserCompanies(String personId);

	 public PayrollDeadline getPayrollDeadline(String company);

	   
}
