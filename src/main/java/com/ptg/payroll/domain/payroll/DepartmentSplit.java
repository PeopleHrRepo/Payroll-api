package com.ptg.payroll.domain.payroll;
import java.math.BigDecimal;

public class DepartmentSplit {

    private String employeeId;
    private Integer employeeRecord = 0;
    private String departmentId;
    private String departmentDescription;
    private BigDecimal splitPercent;


    /* IDE Generated Getters / Setters */

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getEmployeeRecord() {
        return employeeRecord;
    }

    public void setEmployeeRecord(Integer employeeRecord) {
        this.employeeRecord = employeeRecord;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public BigDecimal getSplitPercent() {
        return splitPercent;
    }

    public void setSplitPercent(BigDecimal splitPercent) {
        this.splitPercent = splitPercent;
    }

	public String getDepartmentDescription() {
		return departmentDescription;
	}

	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}

}
