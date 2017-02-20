package com.payroll.dao;

import java.util.List;

import com.payroll.entity.EarnCode;
import com.payroll.entity.PayrollHolidanBean;
import com.payroll.entity.TimesheetPayrollGroup;
import com.ptg.payroll.domain.PayrollGroup;
import com.ptg.payroll.domain.PayrollUserCompany;
import com.ptg.payroll.domain.SortApprovalConfiguration;
import com.ptg.payroll.domain.payroll.PayGroup;
import com.ptg.payroll.entity.ps.pe.dashboard.PayrollDeadline;
import com.ptg.payroll.model.UserPermission;






public interface PayrollDao {
	List<EarnCode> getEarningCodes(String company);
	List<EarnCode> getEarningCodesByCompany(String company);
	boolean getLeaveAccrualSystemClientStatus(String company);
	PayGroup findPayrollInstance(Long id);
	List<EarnCode> getEarningCodesByPayGroup(String company, String payGroup);
	String getUserRole(String company, String personId);
	List<UserPermission> getUserPermissions(String company, String personId);
	String getUserDeptLocationAccess(String company, String personId, String userRole);
	List<PayrollUserCompany> getUserCompanies(String personId);
	PayrollDeadline getPayrollDeadline(String company);
	SortApprovalConfiguration getCompanyEffectiveCurrentSortApprovalOrder(String company);
	PayrollGroup getPayrollGroup(PayGroup payGroup);
	List<PayrollHolidanBean> getHolidays(String company);
	List<TimesheetPayrollGroup> getOnCyclePayrolls(String company);
	 String getCompanyAddress(String company);
	
}
