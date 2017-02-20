package com.payroll.service;

import java.util.List;

import com.ptg.payroll.domain.NameValueBean;

public interface EmployeeBatchService {
	List<NameValueBean>searchBatchCompanies();
	String getEmployeeName(String empId);
}
