package com.ptg.payroll.domain.payroll;
public class PayrollEmployeeError {
	private String employeeId;
    private String departmentId;
    private String earningCode;
    private String type;
    private String message;
    
    public PayrollEmployeeError(){
    	
    }
	
	public PayrollEmployeeError(String employeeId, String departmentId, String earningCode, String type, String message){
		this.employeeId = employeeId;
		this.departmentId = departmentId;
		this.earningCode = earningCode;
		this.type = type;
		this.message = message;
	}
    
    
    /* IDE generated getters & setters */
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getEarningCode() {
		return earningCode;
	}
	public void setEarningCode(String earningCode) {
		this.earningCode = earningCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
   
    
    
}
