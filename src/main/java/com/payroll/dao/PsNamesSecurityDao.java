package com.payroll.dao;

import java.util.List;

import com.payroll.entity.PersonSecurity;
import com.payroll.entity.PsNames;

public interface PsNamesSecurityDao {
	PsNames findByIdEmplIdAndMaxEffectiveDate(String emplid);
	List<String> getEmployeeName(String emplid);
	List<Object[]> getSepCheckMessageForCompany(String company,String msgType);
	List<PersonSecurity> findPersonSecurityByPersonIdTsessionId(String personId,String tsessionId);
}
