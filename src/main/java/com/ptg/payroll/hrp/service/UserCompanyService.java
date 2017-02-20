package com.ptg.payroll.hrp.service;

import com.ptg.payroll.hrp.model.HRPSignonResponse;
import com.ptg.payroll.hrp.model.HRPUserCompanies;

public interface UserCompanyService {

	HRPUserCompanies process(String personId);

	HRPUserCompanies process(HRPSignonResponse hrpResponse);

}