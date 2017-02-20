package com.ptg.payroll.domain;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Department {

    private String deptId;
    private String deptDesc;
    private String deptLongDesc;
    private String jobCode;
    private String location;
    private String locationDesc;
    private String source;
    private boolean isHomeDepartment;
    // This should be @ employee level, but due to unclear business requirement we are keeping it here.
    private Double wageOverride;
    private BigDecimal payPeriodHours = new BigDecimal(0);

    public Department clone() {
        Department target = new Department();
        target.setDeptId(this.getDeptId());
        target.setDeptDesc(this.getDeptDesc());
        target.setDeptLongDesc(this.getDeptLongDesc());
        target.setJobCode(this.getJobCode());
        target.setLocation(this.getLocation());
        target.setLocationDesc(this.getLocationDesc());
        target.setWageOverride(this.getWageOverride());
        target.setSource(this.getSource());
        target.setHomeDepartment(this.isHomeDepartment());
        target.setPayPeriodHours(this.getPayPeriodHours());
        return target;
    }

    public Department() {

    }

    public Department(String deptId, String deptDesc, String jobCode, String location, String locationDesc) {
        this.deptId = deptId;
        this.deptDesc = deptDesc;
        this.jobCode = jobCode;
        this.location = location;
        this.locationDesc = locationDesc;
    }

    public Department(String deptId) {
        this.deptId = deptId;
    }

    public Department(String deptId, String deptDesc, String location) {
        this.deptId = deptId;
        this.deptDesc = deptDesc;
        this.location = location;
    }

    /* Auto-Generated Code below. Please don't hand edit them. -- Starts */

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    public String getDeptLongDesc() {
        return deptLongDesc;
    }

    public void setDeptLongDesc(String deptLongDesc) {
        this.deptLongDesc = deptLongDesc;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }

    public boolean isHomeDepartment() {
        return isHomeDepartment;
    }

    public void setHomeDepartment(boolean isHomeDepartment) {
        this.isHomeDepartment = isHomeDepartment;
    }

    public Double getWageOverride() {
        return wageOverride;
    }

    public void setWageOverride(Double wageOverride) {
        this.wageOverride = wageOverride;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

	public BigDecimal getPayPeriodHours() {
		return payPeriodHours;
	}

	public void setPayPeriodHours(BigDecimal payPeriodHours) {
		this.payPeriodHours = payPeriodHours;
	}

    /* All auto generated code -- Ends */
}
