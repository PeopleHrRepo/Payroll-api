package com.ptg.payroll.domain.payroll;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.payroll.entity.PayrollEarningRecord;
import com.ptg.payroll.domain.Employee;

public class EmployeePayLineItem {
    @JsonUnwrapped
    private Employee employee;
    private boolean isPrimary;
    private Date startDate;
    private Date endDate;
    private int position; // Position# if multiple employee pay line items appear.
    private int lineNumber;
    private boolean hasErrors;
    
    private List<LeaveBalance> leaveBalances = new ArrayList<LeaveBalance>();
    private List<CheckDetail> checkDetails = new ArrayList<CheckDetail>();
    private List<DepartmentEarning> departmentEarnings = new ArrayList<DepartmentEarning>();
    private List<PayrollEmployeeError> errors = new ArrayList<PayrollEmployeeError>();
    private List<RecurringEarningDistribution> earningDistributionDetails = new ArrayList<RecurringEarningDistribution>();
    
    
    public EmployeePayLineItem() {
    	
    }
    
    public EmployeePayLineItem(PayrollEarningRecord earningRecord) {
    	Employee employee = new Employee();
    	employee.setEmployeeId(earningRecord.getEmplId());
    	employee.setEmployeeRecord(earningRecord.getEmplRcd());
    	this.employee = employee;
    	this.startDate = earningRecord.getReportedDate();
    	this.endDate = earningRecord.getReportedDate();
    	this.lineNumber = earningRecord.getSepChk();
    }

    /* Auto-Generated Code below. Please don't hand edit them. -- Starts */
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<DepartmentEarning> getDepartmentEarnings() {
        return departmentEarnings;
    }

    public void setDepartmentEarnings(List<DepartmentEarning> deparmentEarnings) {
        this.departmentEarnings = deparmentEarnings;
    }

    public List<CheckDetail> getCheckDetails() {
        return checkDetails;
    }

    public void setCheckDetails(List<CheckDetail> checkDetails) {
        this.checkDetails = checkDetails;
    }

    public List<LeaveBalance> getLeaveBalances() {
        return leaveBalances;
    }

    public void setLeaveBalances(List<LeaveBalance> leaveBalances) {
        this.leaveBalances = leaveBalances;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public boolean isHasErrors() {
		return hasErrors;
	}

	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}

	public List<PayrollEmployeeError> getErrors() {
		return errors;
	}

	public void setErrors(List<PayrollEmployeeError> errors) {
		this.errors = errors;
	}

	public List<RecurringEarningDistribution> getEarningDistributionDetails() {
		return earningDistributionDetails;
	}

	public void setEarningDistributionDetails(List<RecurringEarningDistribution> earningDistributionDetails) {
		this.earningDistributionDetails = earningDistributionDetails;
	}

    /* All auto generated code -- Ends */
}
