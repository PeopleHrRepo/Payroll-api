package com.ptg.payroll.domain;
public class EmployeeLeaveBalance {

    private String employeeId;
    private Integer employeeRecord;
    private String earnCode;
    private Double balanceHours;

    /** Generated getters & setters */

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

    public String getEarnCode() {
        return earnCode;
    }

    public void setEarnCode(String earnCode) {
        this.earnCode = earnCode;
    }

    public Double getBalanceHours() {
        return balanceHours;
    }

    public void setBalanceHours(Double balanceHours) {
        this.balanceHours = balanceHours;
    }
}
