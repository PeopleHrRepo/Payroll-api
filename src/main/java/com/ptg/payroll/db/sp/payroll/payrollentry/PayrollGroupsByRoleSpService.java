package com.ptg.payroll.db.sp.payroll.payrollentry;
import java.util.Date;
import java.util.List;

import com.ptg.payroll.domain.PayrollByRole;

/**
 * @author rlakshmiraj
 *
 */
public interface PayrollGroupsByRoleSpService {

	List<PayrollByRole> execute(String company, String personId, Date startDate, Date endDate);
	
}