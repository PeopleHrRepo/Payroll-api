package com.payroll.web.controller.impl;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import com.payroll.constant.NavigationConstants;
import com.payroll.utils.CommonUtils;
import com.payroll.utils.PayrollControllerUtils;
import com.payroll.web.controller.PayrollDataEntryController;
import com.ptg.payroll.domain.ReturnResponse;
import com.ptg.payroll.domain.payroll.PayGroup;
import com.ptg.payroll.domain.payroll.PayrollEmployeeEarnings;
import com.payroll.service.PayrollDataEntryService;
import com.payroll.service.PayrollService;


@RestController
public class PayrollDataEntryControllerImpl implements PayrollDataEntryController {
	 
	@Autowired
	PayrollDataEntryService payrollDataEntryService;
	
	@Autowired
	PayrollService payrollService;
	
	
	public ReturnResponse getPayrollEmployeeEarnings(HttpServletRequest request,@PathVariable Long id) {

        PayGroup payGroup = getPayrollInstance(id);
        String operatorId = PayrollControllerUtils.getCurrentOperatorId(request.getSession());
        PayrollEmployeeEarnings payrollEmployeeEarnings = payrollDataEntryService.initializeAndGetPayrollEmployees(payGroup, operatorId);
        if (CommonUtils.isResult(payrollEmployeeEarnings.getEmployeePayLineItems())) 
			return CommonUtils.getHttpStatusResponse(NavigationConstants.SUCCESS, HttpStatus.OK, payrollEmployeeEarnings.getEmployeePayLineItems(), null);
		else
			return CommonUtils.getHttpStatusResponse(NavigationConstants.NO_RECORDS, HttpStatus.PRECONDITION_FAILED,
					null, null);

    }
	protected PayGroup getPayrollInstance(Long id) {
        return payrollService.findPayrollInstance(id);
    }
}
