package com.payroll.web.controller.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.payroll.constant.NavigationConstants;
import com.payroll.entity.EarnCode;
import com.payroll.service.PayrollDashboardService;
import com.payroll.service.PayrollService;
import com.payroll.utils.CommonUtils;
import com.payroll.utils.PayrollControllerUtils;
import com.payroll.web.controller.PayrollDashboardController;
import com.ptg.payroll.domain.Payroll;
import com.ptg.payroll.domain.PayrollList;
import com.ptg.payroll.domain.ReturnResponse;
import com.ptg.payroll.entity.ps.pe.dashboard.PayrollDeadline;
import com.ptg.payroll.hrp.model.BackendConfig;




@RestController
public class PayrollDashboardControllerImpl implements PayrollDashboardController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PayrollDashboardControllerImpl.class);
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	 @Autowired
	 private PayrollDashboardService payrollDashboardService;
	
	@Autowired
	private PayrollService payrollService;

	 @Autowired
	 private BackendConfig backendConfig;
	
	public PayrollService getPayrollService() {
		return payrollService;
	}

	public void setPayrollService(PayrollService payrollService) {
		this.payrollService = payrollService;
	}

	
	public void setPayrollDashboardService(PayrollDashboardService payrollDashboardService) {
		this.payrollDashboardService = payrollDashboardService;
	}

	public void setBackendConfig(BackendConfig backendConfig) {
		this.backendConfig = backendConfig;
	}

	@Override
	public ReturnResponse getEarningCodes(HttpServletRequest request, HttpServletResponse response,@PathVariable  String company) {
		
		List<EarnCode> earnCodes = payrollService.getEarningCodes(company);
		if (CommonUtils.isResult(earnCodes)) 
			return CommonUtils.getHttpStatusResponse(NavigationConstants.SUCCESS, HttpStatus.OK, earnCodes, null);
		else
			return CommonUtils.getHttpStatusResponse(NavigationConstants.NO_RECORDS, HttpStatus.PRECONDITION_FAILED,
					null, null);
	}
	
	 
	 /**
	     * Get Payrolls Deadline date and time for company
	     * 
	     * @param response
	     * @param request
	     * @param company
	     * @return
	     */
	 @Override
	 public ReturnResponse getPayrollDeadline(@PathVariable String company) {
	  
	     LOGGER.debug("getPayrollDeadline - Starts ");
	     LOGGER.debug("company"+company);

	        PayrollDeadline payrollDeadline = payrollService.getPayrollDeadline(company);
	        
	        LOGGER.debug("getPayrollDeadline - Ends");

	        if (payrollDeadline != null) {
	   return CommonUtils.getHttpStatusResponse(NavigationConstants.SUCCESS, HttpStatus.OK, payrollDeadline, null);
	  } else{
	   return CommonUtils.getHttpStatusResponse(NavigationConstants.NO_RECORDS, HttpStatus.PRECONDITION_FAILED,
	     null, null);
	  }
	    }
	 /**
	     * Get all On cycle payroll for given date duration and role of logged in person for list view
	     * 
	     * @param request
	     * @param response
	     * @param company
	     * @param startDate
	     * @param endDate
	     * @return
	     */
		 public ReturnResponse getPayrollGroupsList(
				HttpServletRequest request,
				@PathVariable String company,
				@PathVariable @DateTimeFormat(pattern = DATE_FORMAT) Date startDate,
				@PathVariable @DateTimeFormat(pattern = DATE_FORMAT) Date endDate) {

			 String personId=PayrollControllerUtils.getCurrentPersonId(request.getSession());
			List<PayrollList> payrollList = new ArrayList<PayrollList>();

			LOGGER.info("GET - getPayrollGroupsList");
			boolean isValidStartDate = this.validatePayrollStartDate(startDate);

			if (isValidStartDate) {
				payrollList = payrollDashboardService.getPayrollGroupsList(company,	personId, startDate, endDate);
			}
			
			//update is recallable for each parent paygroup
			List<Payroll> parentPaygroups = new ArrayList<Payroll>();
			for (PayrollList paygroup : payrollList) {
				Payroll parentPaygroup = null;
				parentPaygroup = paygroup.getParentPayroll();
				if(null != parentPaygroup){
					parentPaygroups.add(parentPaygroup);
				}
				
			}
			payrollDashboardService.updateIsRecallable(parentPaygroups); 
			if (CommonUtils.isResult(payrollList)) {
				   return CommonUtils.getHttpStatusResponse(NavigationConstants.SUCCESS, HttpStatus.OK, payrollList, null);
				  } else{
				   return CommonUtils.getHttpStatusResponse(NavigationConstants.NO_RECORDS, HttpStatus.PRECONDITION_FAILED,  null, null);
				  }
				
		}
		 @Override
		 public ReturnResponse getPayrollGroups(HttpServletRequest request,@PathVariable String company,@PathVariable @DateTimeFormat Date startDate,@PathVariable @DateTimeFormat Date endDate) {
		  List<Payroll> payrolls = new ArrayList<Payroll>();

		  LOGGER.info("GET - getPayrollGroups");
		  boolean isValidStartDate = this.validatePayrollStartDate(startDate);

		  if (isValidStartDate) {
		   payrolls = payrollDashboardService.getPayrollGroups(company, startDate, endDate);
		  }
		  payrollDashboardService.updateIsRecallable(payrolls);
		  if (CommonUtils.isResult(payrolls)) {
			   return CommonUtils.getHttpStatusResponse(NavigationConstants.SUCCESS, HttpStatus.OK, payrolls, null);
			  } else{
			   return CommonUtils.getHttpStatusResponse(NavigationConstants.NO_RECORDS, HttpStatus.PRECONDITION_FAILED,  null, null);
			  }
			
	}
		 
		private boolean validatePayrollStartDate(Date startDate) {
	        boolean isValid = false;

	        Calendar cal = Calendar.getInstance();
	        LOGGER.info("Back end config propertie getPayrollHistoryMonths : " + backendConfig.getPayrollHistoryMonths());
	        cal.add(Calendar.MONTH, -backendConfig.getPayrollHistoryMonths());
	        Date historyDate = cal.getTime();

	        if (startDate.after(historyDate)) {
	            isValid = true;
	        }

	        return isValid;
	    }
}
