package com.ptg.payroll.domain;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.ptg.payroll.domain.payroll.EarningCode;
import com.ptg.payroll.domain.payroll.PayrollEmployee;

public class SpecialPayroll {
	@JsonUnwrapped
	private Payroll payroll;
	@JsonUnwrapped
	private List<PayrollEmployee> employees = new ArrayList<PayrollEmployee>();
	@JsonUnwrapped
	private List<EarningCode> earningCodes = new ArrayList<EarningCode>();;
	
	
	
	 /* Auto-Generated Code below. Please don't hand edit them. -- Starts */
	public SpecialPayroll(){
		
	}

	public Payroll getPayroll() {
		return payroll;
	}

	public void setPayroll(Payroll payroll) {
		this.payroll = payroll;
	}
	
	public List<PayrollEmployee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<PayrollEmployee> employees) {
		this.employees = employees;
	}

	public List<EarningCode> getEarningCodes() {
		return earningCodes;
	}

	public void setEarningCodes(List<EarningCode> earningCodes) {
		this.earningCodes = earningCodes;
	}
	
	/* All auto generated code -- Ends */

	

}
