package com.ptg.payroll.hrp.service;

import com.ptg.payroll.hrp.model.HRPUserCompanies;

public interface GetUserCompanies {

	public abstract HRPUserCompanies execute(String personId);

}