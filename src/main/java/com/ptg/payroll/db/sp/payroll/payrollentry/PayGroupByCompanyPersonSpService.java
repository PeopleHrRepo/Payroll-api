package com.ptg.payroll.db.sp.payroll.payrollentry;
import java.util.List;

import com.payroll.entity.TimesheetPayrollGroup;


public interface PayGroupByCompanyPersonSpService {
	public abstract List<TimesheetPayrollGroup> execute(String company, String personId);

}