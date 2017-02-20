package com.ptg.payroll.db.sp.payroll.payrollentry;

import com.payroll.exception.GatewayBackendException;
import com.ptg.payroll.domain.payroll.PayGroup;

public interface DeleteSpecialPayrollSpService {
	
	/**
	 * Execute the delete special payroll stored procedure
	 * 
	 * @param payGroup
	 * @throws GatewayBackendException
	 */
	public abstract void execute(PayGroup payGroup, String action)
			throws GatewayBackendException;
}