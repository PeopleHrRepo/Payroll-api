package com.payroll.web.controller.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import com.payroll.constant.NavigationConstants;
import com.payroll.service.EmployeeBatchService;
import com.payroll.utils.CommonUtils;
import com.payroll.utils.PayrollControllerUtils;
import com.payroll.web.controller.EmployeeBatchController;
import com.ptg.payroll.domain.NameValueBean;
import com.ptg.payroll.domain.ReturnResponse;

@RestController
public class EmployeeBatchControllerImpl implements EmployeeBatchController  {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeBatchControllerImpl.class);
	@Autowired
	private EmployeeBatchService employeeBatchService;
	
	
	@Override
	public ReturnResponse getEmployeeCompanies() {
		try {
			LOGGER.info("getEmployeeCompanies - Start");
			List<NameValueBean> result = employeeBatchService.searchBatchCompanies();
			LOGGER.info("getEmployeeCompanies - end");
			  if (CommonUtils.isResult(result)) {
					return CommonUtils.getHttpStatusResponse(NavigationConstants.SUCCESS, HttpStatus.OK, result, null);
				} else
					return CommonUtils.getHttpStatusResponse(NavigationConstants.NO_RECORDS, HttpStatus.PRECONDITION_FAILED,
							null, null);
		} catch (Exception ex) {
			LOGGER.error("Failed in getEmployeeCompanies", ex);
			return CommonUtils.getHttpStatusResponse(NavigationConstants.HIBERNATE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(), null);
		}
	}
	@Override
	 public ReturnResponse getEmployeeNames(HttpServletRequest request) {
		
	  try {
	  String name =  employeeBatchService.getEmployeeName(PayrollControllerUtils.getCurrentPersonId(request.getSession())) ;
	   if (name != null) {
	    return CommonUtils.getHttpStatusResponse(NavigationConstants.SUCCESS, HttpStatus.OK, name, null);
	   } else
	    return CommonUtils.getHttpStatusResponse(NavigationConstants.NO_RECORDS, HttpStatus.PRECONDITION_FAILED,
	      null, null);
	 } catch (Exception ex) {
	  LOGGER.error("Failed in getEmployeeNames", ex);
	  return CommonUtils.getHttpStatusResponse(NavigationConstants.HIBERNATE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(), null);
	 }
	 }
	
}
