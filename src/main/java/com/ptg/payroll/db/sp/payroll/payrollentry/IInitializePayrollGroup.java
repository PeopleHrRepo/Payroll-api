package com.ptg.payroll.db.sp.payroll.payrollentry;
import java.util.Date;

import com.payroll.exception.GatewayBackendException;


/**
 * @author slekkala
 *
 */
public interface IInitializePayrollGroup {
	/**
	 * Initialize the new payroll group for payroll entry.
	 * 
	 * @param company
	 * @param payGroup
	 * @param payBeginDate
	 * @param payEndDate
	 * @throws GatewayBackendException
	 */
	public abstract void execute(String company, String payGroup, Date payBeginDate, Date payEndDate, String oprId)
			throws GatewayBackendException;

}