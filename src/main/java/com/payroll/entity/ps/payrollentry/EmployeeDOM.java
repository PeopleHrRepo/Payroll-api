package com.payroll.entity.ps.payrollentry;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/*@Entity
@Table(name="PS_T2_PE_EMPLOYEES")
@NamedNativeQueries({
	@NamedNativeQuery(
			name="getAllPayrollEmployees",
			query="SELECT * FROM PS_T2_PE_EMPLOYEES "+
				    "WHERE COMPANY =  :company "+ 
				    "AND PAYGROUP = :payGroup "+ 
				    "AND PAY_END_DT = :payEndDate "+
				    "AND OFF_CYCLE = :offCycle "+
				    "AND T2_HRP_PAYROLL_NUM = :payrollNumber "+
				    "ORDER BY LAST_NAME ",
			resultClass = PayrollEmployee.class
	),
	@NamedNativeQuery(
			name="getAvailablePayrollEmployees",
			query="SELECT * FROM PS_T2_PE_EMPLOYEES "+
				    "WHERE COMPANY =  :company "+ 
				    "AND PAYGROUP = :payGroup "+ 
				    "AND PAY_END_DT = :payEndDate "+
				    "AND OFF_CYCLE = :offCycle "+
				    "AND T2_HRP_PAYROLL_NUM = :payrollNumber "+
				    "AND EMPLID NOT IN (SELECT DISTINCT EMPLID "+
				    						"FROM PS_T2_PE_RPTD_TBL  "+
                                            "WHERE COMPANY =  :company "+
                                            "AND PAYGROUP = :payGroup "+
                                            "AND PAY_END_DT = :payEndDate "+
                                            "AND OFF_CYCLE = :offCycle "+
                                            "AND T2_HRP_PAYROLL_NUM = :payrollNumber) "+
				    "ORDER BY LAST_NAME ",
			resultClass = PayrollEmployee.class
	)
})*/
public class EmployeeDOM{
	
	private String employeeId;	
	private BigDecimal employeeRecord;
	private String departmentId;
	private String jobCode;
	private String location;
	private String departmentDesc;
	private String locationDesc;
	private String lastName;
	private String firstName;
	private String middleName;
	private String emplStatus;
	private String emplStatusDescr;
	private String regOrTemp;
	private String fullOrPartTime;
	private String employeeType;
	private String employeeClass;
	private Date hireDt;
	private Date terminationDt;
	private Date lastWorkDt;
	private String usWorkEligibilty;
	
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public BigDecimal getEmployeeRecord() {
		return employeeRecord;
	}

	public void setEmployeeRecord(BigDecimal employeeRecord) {
		this.employeeRecord = employeeRecord;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
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

	public String getDepartmentDesc() {
		return departmentDesc;
	}

	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}

	public String getLocationDesc() {
		return locationDesc;
	}

	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getEmplStatus() {
		return emplStatus;
	}

	public void setEmplStatus(String emplStatus) {
		this.emplStatus = emplStatus;
	}

	public String getEmplStatusDescr() {
		return emplStatusDescr;
	}

	public void setEmplStatusDescr(String emplStatusDescr) {
		this.emplStatusDescr = emplStatusDescr;
	}

	public String getRegOrTemp() {
		return regOrTemp;
	}

	public void setRegOrTemp(String regOrTemp) {
		this.regOrTemp = regOrTemp;
	}

	public String getFullOrPartTime() {
		return fullOrPartTime;
	}

	public void setFullOrPartTime(String fullOrPartTime) {
		this.fullOrPartTime = fullOrPartTime;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getEmployeeClass() {
		return employeeClass;
	}

	public void setEmployeeClass(String employeeClass) {
		this.employeeClass = employeeClass;
	}

	public Date getHireDt() {
		return hireDt;
	}

	public void setHireDt(Date hireDt) {
		this.hireDt = hireDt;
	}

	public Date getTerminationDt() {
		return terminationDt;
	}

	public void setTerminationDt(Date terminationDt) {
		this.terminationDt = terminationDt;
	}

	public Date getLastWorkDt() {
		return lastWorkDt;
	}

	public void setLastWorkDt(Date lastWorkDt) {
		this.lastWorkDt = lastWorkDt;
	}

	public String getUsWorkEligibilty() {
		return usWorkEligibilty;
	}

	public void setUsWorkEligibilty(String usWorkEligibilty) {
		this.usWorkEligibilty = usWorkEligibilty;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
