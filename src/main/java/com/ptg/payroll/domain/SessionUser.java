package com.ptg.payroll.domain;

public class SessionUser {

private String employeeId;
private String personId;
private String companyId;

private String userToken;

public String getUserToken() {
	return userToken;
}
public void setUserToken(String userToken) {
	this.userToken = userToken;
}
public SessionUser() {
}
public SessionUser(String employeeId, String personId, String companyId) {
	super();
	this.employeeId = employeeId;
	this.personId = personId;
	this.companyId = companyId;
}

public String getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(String employeeId) {
	this.employeeId = employeeId;
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
