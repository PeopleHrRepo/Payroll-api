package com.ptg.payroll.domain.payroll;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.ptg.payroll.domain.Employee;
import com.ptg.payroll.domain.EmployeeDetails;


public class PayrollEmployee {
    @JsonUnwrapped
    private Employee employee;
    @JsonUnwrapped
    private EmployeeDetails employeeDetails;
    
    
    
    
    
    
    /* Auto-Generated Code below. Please don't hand edit them. -- Starts */
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public EmployeeDetails getEmployeeDetails() {
		return employeeDetails;
	}
	public void setEmployeeDetails(EmployeeDetails employeeDetails) {
		this.employeeDetails = employeeDetails;
	}
    /* All auto generated code -- Ends */

}
