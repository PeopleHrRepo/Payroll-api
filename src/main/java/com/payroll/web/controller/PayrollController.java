package com.payroll.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.payroll.constant.NavigationURIConstants;
import com.ptg.payroll.domain.ReturnResponse;


@RequestMapping(value = NavigationURIConstants.SECURITY)
public interface PayrollController {
	@RequestMapping(value = NavigationURIConstants.GET_TIMESHEETPAYROLL_EARNING_CDOES, method = RequestMethod.GET)
	ReturnResponse getEarningCodes(HttpServletRequest request, HttpServletResponse response,String company);
	
	 @RequestMapping(value = NavigationURIConstants.GET_USER_SECURITY, method = RequestMethod.GET)
	 ReturnResponse getUserSecurity(HttpServletRequest request, HttpServletResponse response,String company);
	 
	 @RequestMapping(value = NavigationURIConstants.GET_USER_COMPANIES, method = RequestMethod.GET)
	 ReturnResponse getUserCompanies(HttpServletRequest request);
	 
	 @RequestMapping(value = NavigationURIConstants.GET_USER_DEPT_LOCATION_ACCESS, method = RequestMethod.GET)
	 ReturnResponse getUserDeptLocationAccess(HttpServletRequest request,String company);
}
