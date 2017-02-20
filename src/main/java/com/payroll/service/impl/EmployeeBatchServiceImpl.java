package com.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.payroll.dao.EmployeeBatchDao;
import com.payroll.service.EmployeeBatchService;
import com.ptg.payroll.domain.NameValueBean;

public class EmployeeBatchServiceImpl implements EmployeeBatchService{

	@Autowired
	private EmployeeBatchDao employeeBatchDao;
	
	public EmployeeBatchDao getEmployeeBatchDao() {
		return employeeBatchDao;
	}
	
	public void setEmployeeBatchDao(EmployeeBatchDao employeeBatchDao) {
		this.employeeBatchDao = employeeBatchDao;
	}

	@Override
	public List<NameValueBean> searchBatchCompanies() {
		return employeeBatchDao.searchBatchCompanies();
	}
	@Override
	 public String getEmployeeName(String empId) {
	  return employeeBatchDao.getEmployeeName(empId);
	 }
}
