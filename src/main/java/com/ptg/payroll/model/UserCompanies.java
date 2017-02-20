package com.ptg.payroll.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserCompanies implements Serializable {
	List<UserCompany> companies = new ArrayList<UserCompany>();
	private String currentCompanyId;
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.trinet.fx.gateway.model.UserCompanies#getCompanies()
	 */
	public List<UserCompany> getCompanies() {
		return companies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.trinet.fx.gateway.model.UserCompanies#setCompanies(java.util.ArrayList
	 * )
	 */
	public void setCompanies(List<UserCompany> companies) {
		this.companies = companies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.trinet.fx.gateway.model.UserCompanies#addCompany(com.trinet.fx.gateway
	 * .model.UserCompanyImpl)
	 */
	public void addCompany(UserCompany uc) {
		companies.add(uc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.trinet.fx.gateway.model.UserCompanies#isCompanyIdValid(java.lang.
	 * String)
	 */
	public boolean isCompanyIdValid(String companyId) {
		if (StringUtils.isBlank(companyId))
			return false;
		for (UserCompany uc : this.companies) {
			if (uc.getUserCompany().equals(companyId)) {
				return true;
			}
		}
		return false;
	}

	public void setCurrentCompanyId(String companyId) {
		if (isCompanyIdValid(companyId)) {
			this.currentCompanyId = companyId;
		} else {
			throw new IllegalArgumentException("Invalid companyId: " + companyId);
		}

	}
	@JsonIgnore
	public UserCompany getCurrentCompany() {
		if (StringUtils.isNotBlank(currentCompanyId)) {
			for (UserCompany uc : this.companies) {
				if (uc.getUserCompany().equals(currentCompanyId)) {
					return uc;
				}
			}
		} else {
			throw new IllegalStateException("Invalid companyId: " + currentCompanyId);
		}
		return null;
	}

	public UserCompany setAndGetCurrentCompany(String companyId) {
		this.setCurrentCompanyId(companyId);
		return this.getCurrentCompany();
	}

	/* (non-Javadoc)
	 * @see com.trinet.fx.gateway.model.UserCompanies#setCurrentCompanyToDefaultCompany()
	 */
	public void setCurrentCompanyToDefaultCompany() {
		// check size of list
		if (companies.size()>=1){
			 this.currentCompanyId = companies.get(0).getUserCompany();
		}else{
			throw new IllegalStateException("UserCompanies must have at leaset one company");
		}
		
		
	}

	public String getCurrentCompanyId() {		
		return this.currentCompanyId;
	}
}
