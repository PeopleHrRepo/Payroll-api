package com.ptg.payroll.domain.payroll;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class PayGroup {
    
    private Long id = new Long(0);
    private String company;
    private String payGroup;
    private Date payBeginDate;
    private Date payEndDate = new Date();
    private String offCycle;
    private int payrollNumber;
    private String payFrequency;
    // status of the invoice, is it in Preview, Finalized etc.
    private String status;
    private Date reportDate;
    private int previewPayrollNumber;
    private Date checkDate;
    
    private String jobCosting;
    private String certifiedPayroll;
    
    @JsonIgnore
    private String isParent="1";
    private String departmentId;
    private String location;
    
    private String payFrequencyDesc;
    

    /* Auto-Generated Code below. Please don't hand edit them. -- Starts */
    public PayGroup() {

    }

    public PayGroup(String company, String payGroup, Date payBeginDate, Date payEndDate, String offCycle, int payrollNumber,
            String payFrequency, String status) {
        this.company = company;
        this.payGroup = payGroup;
        this.payBeginDate = payBeginDate;
        this.payEndDate = payEndDate;
        this.offCycle = offCycle;
        this.payrollNumber = payrollNumber;
        this.payFrequency = payFrequency;
        this.status = status;
    }

    public PayGroup(String company, String payGroup, Date payBeginDate, Date payEndDate, String offCycle, int payrollNumber) {
        this.company = company;
        this.payGroup = payGroup;
        this.payBeginDate = payBeginDate;
        this.payEndDate = payEndDate;
        this.offCycle = offCycle;
        this.payrollNumber = payrollNumber;
    }

    public PayGroup(String company, String payGroup, Date payEndDate, String offCycle, int payrollNumber) {
        this.company = company;
        this.payGroup = payGroup;
        this.payEndDate = payEndDate;
        this.offCycle = offCycle;
        this.payrollNumber = payrollNumber;
    }
    public PayGroup(String company, String payGroup, Date payEndDate, String offCycle, int payrollNumber, String payFrequency) {
        this.company = company;
        this.payGroup = payGroup;
        this.payEndDate = payEndDate;
        this.offCycle = offCycle;
        this.payrollNumber = payrollNumber;
        this.payFrequency = payFrequency;
    }
    
    public PayGroup(String company, String payGroup) {
        this.company = company;
        this.payGroup = payGroup;
    }
    
    /**
	 * @return the isParent
	 */
	public String getIsParent() {
		return isParent;
	}
	/**
	 * @param isParent the isParent to set
	 */
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPayGroup() {
		return payGroup;
	}

	public void setPayGroup(String payGroup) {
		this.payGroup = payGroup;
	}

	public Date getPayBeginDate() {
		return payBeginDate;
	}

	public void setPayBeginDate(Date payBeginDate) {
		this.payBeginDate = payBeginDate;
	}

	public Date getPayEndDate() {
		return payEndDate;
	}

	public void setPayEndDate(Date payEndDate) {
		this.payEndDate = payEndDate;
	}

	public String getOffCycle() {
		return offCycle;
	}

	public void setOffCycle(String offCycle) {
		this.offCycle = offCycle;
	}

	public int getPayrollNumber() {
		return payrollNumber;
	}

	public void setPayrollNumber(int payrollNumber) {
		this.payrollNumber = payrollNumber;
	}

	public String getPayFrequency() {
		return payFrequency;
	}

	public void setPayFrequency(String payFrequency) {
		this.payFrequency = payFrequency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public int getPreviewPayrollNumber() {
		return previewPayrollNumber;
	}

	public void setPreviewPayrollNumber(int previewPayrollNumber) {
		this.previewPayrollNumber = previewPayrollNumber;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPayFrequencyDesc() {
		return payFrequencyDesc;
	}

	public void setPayFrequencyDesc(String payFrequencyDesc) {
		this.payFrequencyDesc = payFrequencyDesc;
	}
	

	public String getJobCosting() {
		return jobCosting;
	}

	public void setJobCosting(String jobCosting) {
		this.jobCosting = jobCosting;
	}

	public String getCertifiedPayroll() {
		return certifiedPayroll;
	}

	public void setCertifiedPayroll(String certifiedPayroll) {
		this.certifiedPayroll = certifiedPayroll;
	}	

    /* All auto generated code -- Ends */
}
