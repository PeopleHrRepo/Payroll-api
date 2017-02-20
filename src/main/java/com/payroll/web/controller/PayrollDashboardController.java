package com.payroll.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.payroll.constant.NavigationURIConstants;
import com.ptg.payroll.domain.ReturnResponse;

@RequestMapping(value = NavigationURIConstants.SECURITY)
public interface PayrollDashboardController {

	@RequestMapping(value = NavigationURIConstants.GET_EARNING_CDOES, method = RequestMethod.GET)
	ReturnResponse getEarningCodes(HttpServletRequest request, HttpServletResponse response,String company); 
		 
	 @RequestMapping(value = NavigationURIConstants.GET_PAYROLL_DEADLINES, method = RequestMethod.GET)
	 ReturnResponse getPayrollDeadline(String company);
	 
	 
	 @RequestMapping(value = NavigationURIConstants.GET_PAYROLL_PAYGROUP_LIST_BY_COMPANY, method = RequestMethod.GET)
	 ReturnResponse getPayrollGroupsList(HttpServletRequest request,String company,Date startDate,Date endDate);
	 
	 @RequestMapping(value =NavigationURIConstants.GET_PAYGROUPS_DATAENTRY, method = RequestMethod.GET)
	  ReturnResponse getPayrollGroups(HttpServletRequest request,String company,Date startDate, Date endDate);

}
