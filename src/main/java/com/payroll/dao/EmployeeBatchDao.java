package com.payroll.dao;

import java.util.List;

import com.ptg.payroll.domain.NameValueBean;

public interface EmployeeBatchDao {
	String getEmployeeName(String empId);
	List<NameValueBean> searchBatchCompanies();

}
