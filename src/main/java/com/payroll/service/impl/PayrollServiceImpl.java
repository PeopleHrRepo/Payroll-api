package com.payroll.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.payroll.dao.PayrollDao;
import com.payroll.dao.PayrollEntryDao;
import com.payroll.entity.EarnCode;
import com.payroll.entity.ps.ClientOption;
import com.payroll.mapper.PayrollDashboardMapper;
import com.payroll.mapper.PayrollMapper;
import com.payroll.service.HRPTSessionSignonService;
import com.payroll.service.PayrollService;
import com.ptg.payroll.domain.PayrollUserCompany;
import com.ptg.payroll.domain.payroll.PayGroup;
import com.ptg.payroll.entity.ps.pe.dashboard.PayrollDeadline;





public class PayrollServiceImpl implements PayrollService{
	private static final Logger LOGGER = LoggerFactory.getLogger(PayrollServiceImpl.class);
	@Autowired
	private PayrollDao payrollDao;
	@Autowired
    private PayrollEntryDao payrollEntryDao;
	
	@Autowired 
	private HRPTSessionSignonService hrptSessionSignonService;
	
	public void setPayrollDao(PayrollDao payrollDao) {
		this.payrollDao = payrollDao;
	}


	public void setPayrollEntryDao(PayrollEntryDao payrollEntryDao) {
		this.payrollEntryDao = payrollEntryDao;
	}


	


	public void setHrptSessionSignonService(HRPTSessionSignonService hrptSessionSignonService) {
		this.hrptSessionSignonService = hrptSessionSignonService;
	}


	@Override
	public List<EarnCode> getEarningCodes(String company) {
		List<EarnCode> earningCodeList = payrollDao.getEarningCodes(company);
    	this.applyEarningCodeRules(company, earningCodeList);
    	return earningCodeList;
	}

	
	public void applyEarningCodeRules(String company, List<EarnCode> earningCodeList) {
		ClientOption clientOption = payrollEntryDao.getClientOption(company);
		boolean isAmbroseClient = clientOption.isAmbroseClient();
		if(isAmbroseClient){
			boolean is401KClient = payrollEntryDao.is401KAmbroseClient(company);
			if (!is401KClient) {
				PayrollDashboardMapper.disableEarnCode401KFlag(earningCodeList, isAmbroseClient);
			}
		} else {
			PayrollDashboardMapper.disableEarnCode401KFlag(earningCodeList, isAmbroseClient);
		}
	}


	@Override
	public List<EarnCode> getEarningCodesByCompany(String company) {
		
		LOGGER.info("getEarningCodes - START");
		List<String> activeEarnCodes = payrollEntryDao.getActiveEarnCodeReported(company);		
		List<EarnCode>  compnayEarnCodeList = payrollDao.getEarningCodesByCompany(company);
		this.applyEarningCodeRules(company, compnayEarnCodeList);
		
		if(activeEarnCodes != null && activeEarnCodes.size() > 0){
			for (EarnCode companyEarncode : compnayEarnCodeList){
				if(activeEarnCodes.contains(companyEarncode.getCode()))
					companyEarncode.setReported(true);
			}
		}
		
		return compnayEarnCodeList;
	}
	@Override
	public boolean getLeaveAccrualSystemClientStatus(String company) {
		return payrollDao.getLeaveAccrualSystemClientStatus(company);
	}

	@Override
	public PayGroup findPayrollInstance(Long id) {
		return payrollDao.findPayrollInstance(id);
	}

	@Override
	public List<EarnCode> getEarningCodesByPayGroup(String company, String payGroup) {
    	List<EarnCode> earningCodeList = payrollDao.getEarningCodesByPayGroup(company, payGroup);
    	this.applyEarningCodeRules(company, earningCodeList);
    	return earningCodeList;
	}
	@Override
	public com.ptg.payroll.domain.payroll.User getUserRolePermissions(String company, String personId, String employeeId) {
		
		String employeeName =  hrptSessionSignonService.getEmployeeName(employeeId) ;
		String userRole = payrollDao.getUserRole(company, personId);
		List<com.ptg.payroll.model.UserPermission> permissions = payrollDao.getUserPermissions(company, userRole);
		
		com.ptg.payroll.domain.payroll.User user = new com.ptg.payroll.domain.payroll.User(employeeName, employeeId, company);
		PayrollMapper.PayrollSecurityMapper mapper = new PayrollMapper.PayrollSecurityMapper();
		mapper.translate(permissions, user);
		return user;
	}
	@Override
	 public String getUserDeptLocationAccess(String company, String personId) {
	        String userRole = payrollDao.getUserRole(company, personId);
	        return payrollDao.getUserDeptLocationAccess(company, personId, userRole);
	    }
	@Override
	 public List<PayrollUserCompany> getUserCompanies(String  personId) {
	  return payrollDao.getUserCompanies(personId);
	 }

	 @Override
	 public PayrollDeadline getPayrollDeadline(String company) {
	  return payrollDao.getPayrollDeadline(company);
	 }
}
