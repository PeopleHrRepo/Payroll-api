package com.ptg.payroll.domain.payroll;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.payroll.entity.PayrollEarningRecord;
import com.ptg.payroll.domain.Department;

public class DepartmentEarning {
    @JsonUnwrapped
    private Department department;
    private BigDecimal splitPercent;
    private int position; // position of the department among multiple departments.
    private List<Hours> hours = new ArrayList<Hours>();
    private List<Hours> otherHours = new ArrayList<Hours>();
    private List<Amount> otherEarnings = new ArrayList<Amount>();
    private List<UnitPay> unitsPay = new ArrayList<UnitPay>();
    private List<OverridePayRate> overridePayRates = new ArrayList<OverridePayRate>();
    
    
    
    /* Auto-Generated Code below. Please don't hand edit them. -- Starts */
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public BigDecimal getSplitPercent() {
        return splitPercent;
    }

    public void setSplitPercent(BigDecimal splitPercent) {
        this.splitPercent = splitPercent;
    }

    public List<Hours> getHours() {
        return hours;
    }

    public void setHours(List<Hours> hours) {
        this.hours = hours;
    }

    public List<Hours> getOtherHours() {
        return otherHours;
    }

    public void setOtherHours(List<Hours> otherHours) {
        this.otherHours = otherHours;
    }

    public List<UnitPay> getUnitsPay() {
        return unitsPay;
    }

    public void setUnitsPay(List<UnitPay> unitsPay) {
        this.unitsPay = unitsPay;
    }

    public List<Amount> getOtherEarnings() {
        return otherEarnings;
    }

    public void setOtherEarnings(List<Amount> otherEarnings) {
        this.otherEarnings = otherEarnings;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    
	public List<OverridePayRate> getOverridePayRates() {
		return overridePayRates;
	}

	public void setOverridePayRates(List<OverridePayRate> overridePayRates) {
		this.overridePayRates = overridePayRates;
	}

	public DepartmentEarning() {
    	
    }
    
    public DepartmentEarning(PayrollEarningRecord earningRecord) {
    	Department dept = new Department();
    	dept.setDeptId(earningRecord.getDeptId());
    	dept.setLocation(earningRecord.getLocation());
    	dept.setJobCode(earningRecord.getJobCode());
    	this.setDepartment(dept);
    }
    /* All auto generated code -- Ends */
}
