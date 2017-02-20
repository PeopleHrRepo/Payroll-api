package com.ptg.payroll.hrp.model;
public class HRPSessionSignon {
	private String tSessionId;
	private String personId;
	private String companyId;
	
	public String gettSessionId() {
		return tSessionId;
	}
	public void settSessionId(String tSessionId) {
		this.tSessionId = tSessionId;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}