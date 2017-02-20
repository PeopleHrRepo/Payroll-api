package com.payroll.web.controller.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.payroll.constant.NavigationConstants;
import com.payroll.entity.EarnCode;
import com.payroll.service.PayrollService;
import com.payroll.utils.CommonUtils;
import com.payroll.utils.PayrollControllerUtils;
import com.payroll.web.controller.PayrollController;
import com.ptg.payroll.domain.PayrollUserCompany;
import com.ptg.payroll.domain.ReturnResponse;
import com.ptg.payroll.domain.payroll.User;



@RestController
public class PayrollControllerImpl implements PayrollController{
	private static final Logger LOGGER = LoggerFactory.getLogger(PayrollControllerImpl.class);
	@Autowired
    private PayrollService payrollService;
	
		public ReturnResponse getEarningCodes(HttpServletRequest request, HttpServletResponse response, @PathVariable String company) {

			LOGGER.info("getEarningCodes - Start");
			
			LOGGER.info("getEarningCodes - Calling service to get EarningCodes for the company");
			
			List<EarnCode> earningCodeList = payrollService.getEarningCodesByCompany(company);
			boolean isLeaveAccrualSystemClient = payrollService.getLeaveAccrualSystemClientStatus(company);
			if(!isLeaveAccrualSystemClient){
				for (EarnCode companyErnCode : earningCodeList) {
					companyErnCode.setIsLoadLeaveEarnCd("N");
				}
			}
			//request.getSession().setAttribute("companyEarningCodes", earningCodeList);
			LOGGER.info("getEarningCodes - End");
			 if (CommonUtils.isResult(earningCodeList)) {
					return CommonUtils.getHttpStatusResponse(NavigationConstants.SUCCESS, HttpStatus.OK,  earningCodeList, null);
				} else
					return CommonUtils.getHttpStatusResponse(NavigationConstants.NO_RECORDS, HttpStatus.PRECONDITION_FAILED,
							null, null);
	        
	    }
		/**
		  * Get the list of comapanies on as per user roles
		  * 
		  * @param request
		  * @param response
		  * @return
		  */
		 @Override
		 public ReturnResponse getUserCompanies(HttpServletRequest request) {

		  LOGGER.debug("getUserCompanies : Start");

		  List<PayrollUserCompany> companiesList = null;
		  String personId=PayrollControllerUtils.getCurrentPersonId(request.getSession());
		  if(personId!=null)
		  companiesList = payrollService.getUserCompanies(personId);

		  LOGGER.debug("getUserCompanies :" + companiesList.toString());
		  LOGGER.debug("getUserCompanies : End");
		  if (CommonUtils.isResult(companiesList))
		   return CommonUtils.getHttpStatusResponse(NavigationConstants.SUCCESS, HttpStatus.OK, companiesList, null);
		   else
		   return CommonUtils.getHttpStatusResponse(NavigationConstants.NO_RECORDS, HttpStatus.PRECONDITION_FAILED,null, null);
		  

		 }
		
		 public ReturnResponse  getUserSecurity(HttpServletRequest request, HttpServletResponse response, @PathVariable String company) {
			 LOGGER.info("GET - getUserSecurity");

		        String personId = PayrollControllerUtils.getCurrentPersonId(request.getSession());
		        String employeeId = PayrollControllerUtils.getCurrentOperatorId(request.getSession());

		        User user = payrollService.getUserRolePermissions(company, personId, employeeId);

		        if (user!=null) {
					return CommonUtils.getHttpStatusResponse(NavigationConstants.SUCCESS, HttpStatus.OK,  user, null);
				} else
					return CommonUtils.getHttpStatusResponse(NavigationConstants.NO_RECORDS, HttpStatus.PRECONDITION_FAILED,
							null, null);
		    }
		 @Override
		 public ReturnResponse getUserDeptLocationAccess(HttpServletRequest request, @PathVariable String company) {
		  LOGGER.debug("getUserDeptLocationAccess : Starts");
		  LOGGER.debug(" company : - "+company+"\n personId : "+PayrollControllerUtils.getCurrentPersonId(request.getSession()));
		  String result = payrollService.getUserDeptLocationAccess(company, PayrollControllerUtils.getCurrentPersonId(request.getSession()));
		  LOGGER.debug("getUserDeptLocationAccess : ends");
		  if (result != null) {
			   return CommonUtils.getHttpStatusResponse(NavigationConstants.SUCCESS, HttpStatus.OK, result, null);
			  } else {
			   return CommonUtils.getHttpStatusResponse(NavigationConstants.NO_RECORDS, HttpStatus.PRECONDITION_FAILED,
			     null, null);
			  }
			 }
}
