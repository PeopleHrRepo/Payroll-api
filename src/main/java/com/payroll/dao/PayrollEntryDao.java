package com.payroll.dao;

import java.util.List;

import com.payroll.entity.ps.ClientOption;

public interface PayrollEntryDao {
	public ClientOption getClientOption(String company);
	public boolean is401KAmbroseClient(String company);
	List<String> getActiveEarnCodeReported(String company);
}
