package com.ptg.payroll.domain.payroll;
import java.util.List;

public class PayrollEmployeeEarningsEntity {
	
	private PayrollEmployeeEarnings payrollEmployeeEarnings;
	
	private List<EarningCode> earningCodes;
	
	public PayrollEmployeeEarnings getPayrollEmployeeEarnings() {
		return payrollEmployeeEarnings;
	}

	public void setPayrollEmployeeEarnings(PayrollEmployeeEarnings payrollEmployeeEarnings) {
		this.payrollEmployeeEarnings = payrollEmployeeEarnings;
	}

	public List<EarningCode> getEarningCodes() {
		return earningCodes;
	}

	public void setEarningCodes(List<EarningCode> earningCodes) {
		this.earningCodes = earningCodes;
	}
}
