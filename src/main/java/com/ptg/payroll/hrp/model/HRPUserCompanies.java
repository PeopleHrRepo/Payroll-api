package com.ptg.payroll.hrp.model;
import java.util.ArrayList;
import java.util.List;



public class HRPUserCompanies {
	List<HRPUserCompany> companies = new ArrayList<HRPUserCompany>();

	public List<HRPUserCompany> getCompanies() {
		return companies;
	}

	public void setCompanies(List<HRPUserCompany> companies) {
		this.companies = companies;
	}
	public void addCompany(HRPUserCompany company){
		companies.add(company);
	}

}