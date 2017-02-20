package com.ptg.payroll.db.sp.payroll.payrollentry;

import com.payroll.exception.GatewayBackendException;
import com.ptg.payroll.domain.payroll.PayGroup;

/**
 * @author slekkala
 *
 */
public interface IUpdateSpecialPayrollPPData {
	
	
	/**
	 * Update special payroll prior pay period data (if prior pay period is expired, drop data)
	 * 
	 * @param payGroup
	 * @throws GatewayBackendException
	 */
	public abstract void execute(PayGroup payGroup) throws GatewayBackendException;

}