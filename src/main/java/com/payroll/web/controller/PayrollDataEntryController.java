package com.payroll.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.payroll.constant.NavigationURIConstants;
import com.ptg.payroll.domain.ReturnResponse;

@RequestMapping(value = NavigationURIConstants.SECURITY)
public interface PayrollDataEntryController {

	@RequestMapping(value =  NavigationURIConstants.GET_EMPLOYEE_DATA_ENTRY, method = RequestMethod.GET)
	public ReturnResponse getPayrollEmployeeEarnings(HttpServletRequest request,Long id);
}
