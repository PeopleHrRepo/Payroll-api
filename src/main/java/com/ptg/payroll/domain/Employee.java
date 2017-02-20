package com.ptg.payroll.domain;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class Employee {
    private String employeeId;
    private Integer employeeRecord;
    private String name;
    private String firstName;
    private String lastName;
    private String middleName;
    private String status;
    private String statusDesc;
    private String employeeType;
    private String regOrTemp;
    private String fullOrPartTime;
    private String paymentMethod; // C, A, CA
    private String disableDirectDeposit; // Y, N
    private Date hireDate;
    private Date terminationDate;
    private String jed; // J, N , O
    private BigDecimal stdHours;
    private Double payRate;
    private BigDecimal overrideCompRate;
    private Double hourlyRate;
    private String usWorkEligibility;
    private String changedByProcessor;
    private String midPayAction;
    private String midPayActionDesc;
    private Date actionEffDate;
    private String emplClass;
    
    /* MER-13102 changes */
    // Record status - Terminated or Active
    private String recordStatus;
    // Effective date for file import validation
    private Date effDate;
    
    private String homeDepartment;

    /* ******** All auto generated code, Don't hand edit them - Starts ******** */

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
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

    public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public Double getPayRate() {
        return payRate;
    }

    public void setPayRate(Double payRate) {
        this.payRate = payRate;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public BigDecimal getOverrideCompRate() {
        return overrideCompRate;
    }

    public void setOverrideCompRate(BigDecimal overrideCompRate) {
        this.overrideCompRate = overrideCompRate;
    }

    public String getUsWorkEligibility() {
        return usWorkEligibility;
    }

    public void setUsWorkEligibility(String usWorkEligibility) {
        this.usWorkEligibility = usWorkEligibility;
    }
    
    public String getDisableDirectDeposit() {
        return disableDirectDeposit;
    }

    public void setDisableDirectDeposit(String disableDirectDeposit) {
        this.disableDirectDeposit = disableDirectDeposit;
    }

    public BigDecimal getStdHours() {
        return stdHours;
    }

    public void setStdHours(BigDecimal stdHours) {
        this.stdHours = stdHours;
    }

    public String getChangedByProcessor() {
		return changedByProcessor;
	}

	public void setChangedByProcessor(String changedByProcessor) {
		this.changedByProcessor = changedByProcessor;
	}
	
	public String getMidPayAction() {
		return midPayAction;
	}

	public void setMidPayAction(String midPayAction) {
		this.midPayAction = midPayAction;
	}

	public String getMidPayActionDesc() {
		return midPayActionDesc;
	}

	public void setMidPayActionDesc(String midPayActionDesc) {
		this.midPayActionDesc = midPayActionDesc;
	}

	public Date getActionEffDate() {
		return actionEffDate;
	}

	public void setActionEffDate(Date actionEffDate) {
		this.actionEffDate = actionEffDate;
	}

	/**
     * @return the recordStatus
     */
    public String getRecordStatus() {
        return recordStatus;
    }

    /**
     * @param recordStatus the recordStatus to set
     */
    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    /**
     * @return the effDate
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * @param effDate the effDate to set
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public String getHomeDepartment() {
		return homeDepartment;
	}

	public void setHomeDepartment(String homeDepartment) {
		this.homeDepartment = homeDepartment;
	}
	
	public String getJed() {
		return jed;
	}

	public void setJed(String jed) {
		this.jed = jed;
	}

	public String getEmplClass() {
		return emplClass;
	}

	public void setEmplClass(String emplClass) {
		this.emplClass = emplClass;
	}

    @Override
	public String toString() {
    	return ReflectionToStringBuilder.toString(this);
    }
    /* ******** All auto generated code, Don't hand edit them - Ends ******** */
	
}
