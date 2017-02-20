package com.ptg.payroll.db.sp.payroll.payrollentry;
import java.util.Date;
import java.util.List;

import com.ptg.payroll.domain.Payroll;


public interface PayrollGroupsSpService {

	public abstract List<Payroll> execute(String companyId, Date startDate, Date endDate);

}