package com.ptg.payroll.db.sp.payroll.payrollentry;

import com.payroll.exception.GatewayBackendException;
import com.ptg.payroll.domain.SpecialPayroll;

public interface InsertSpecialPayrollSpService {

	/**
	 * Excute the insert/update of special payroll by stored procedure.
	 * @param specialPayroll
	 * @throws GatewayBackendException
	 */
	SpecialPayroll execute(SpecialPayroll specialPayroll)	throws GatewayBackendException;

}