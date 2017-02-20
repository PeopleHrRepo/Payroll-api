package com.payroll.entity;

public class SignIn extends BaseEntity {
	private static final long serialVersionUID = 1022968807807360443L;
private String employeeId;
private String password;

public String getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(String employeeId) {
	this.employeeId = employeeId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}


public SignIn() {
}

public SignIn(String employeeId, String password) {
	super();
	this.employeeId = employeeId;
	this.password = password;

}



	
	
}
