package com.ptg.payroll.db.sp.payroll.payrollentry;
import java.util.Date;

import com.payroll.exception.GatewayBackendException;

/**
 * @author ashrivastava
 *
 */
public interface IInitializePayrollGroupNew {
	/**
	 * Initialize the new payroll group for payroll entry.
	 * 
	 * @param company
	 * @param payGroup
	 * @param payBeginDate
	 * @param payEndDate
	 * @param oprId
	 * @param key
	 * @throws GatewayBackendException
	 */
	public abstract void execute(String company, String payGroup, Date payBeginDate, Date payEndDate, String oprId , String key)
			throws GatewayBackendException;

}