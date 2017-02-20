package com.payroll.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.payroll.constant.NavigationURIConstants;
import com.ptg.payroll.domain.ReturnResponse;


@RequestMapping(value = NavigationURIConstants.SECURITY)
public interface EmployeeBatchController {

	@RequestMapping(value = NavigationURIConstants.GET_EMPLOYEE_COMPANY, method = RequestMethod.GET)
	ReturnResponse getEmployeeCompanies();
	
	@RequestMapping(value = NavigationURIConstants.GET_EMPLOYEE_NAME, method = RequestMethod.GET)
	 ReturnResponse getEmployeeNames(HttpServletRequest request);
	
	
	
}
