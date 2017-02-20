package com.ptg.payroll.domain;
public class EmployeeDepartmentSplit {

    private String companyId;
    private String payGroup;
    private Boolean offCycle;
    private String deptId;
    private String employeeId;
    private double splitPercentage;
    private String earnCode;

    /* ******** All auto generated code, Don't hand edit them - Starts ******** */

    public EmployeeDepartmentSplit(String companyId, String payGroup, Boolean offCycle, String deptId, String employeeId, double splitPercentage,
            String earnCode) {
        super();
        this.companyId = companyId;
        this.payGroup = payGroup;
        this.offCycle = offCycle;
        this.deptId = deptId;
        this.employeeId = employeeId;
        this.splitPercentage = splitPercentage;
        this.earnCode = earnCode;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public Boolean getOffCycle() {
        return offCycle;
    }

    public void setOffCycle(Boolean offCycle) {
        this.offCycle = offCycle;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public double getSplitPercentage() {
        return splitPercentage;
    }

    public void setSplitPercentage(double splitPercentage) {
        this.splitPercentage = splitPercentage;
    }

    public String getEarnCode() {
        return earnCode;
    }

    public void setEarnCode(String earnCode) {
        this.earnCode = earnCode;
    }

    /* ******** All auto generated code, Don't hand edit them - Starts ******** */

}