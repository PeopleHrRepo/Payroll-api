package com.payroll.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "PS_T2_PE_PYRL_E_VW")
@NamedNativeQueries({
    @NamedNativeQuery(
            name="getPayrollEmployeeRecordsView",
            query =  "SELECT * from PS_T2_PE_PYRL_E_VW "
            		+ " WHERE COMPANY = :company AND PAYGROUP = :payGroup "
            		+ " AND PAY_END_DT = :payEndDate "
            		+ " AND OFF_CYCLE = :offCycle "
            		+ " AND T2_HRP_PAYROLL_NUM = :payrollNumber "
            		+ " ORDER BY LAST_NAME, END_DT",
            resultClass = PayrollEmployeeRecordVW.class
    ),
   
   @NamedNativeQuery(
            name="getPayrollEmployeeRecordsViewForEmployee",
            query =  "SELECT * from PS_T2_PE_PYRL_E_VW "
                    		+ " WHERE COMPANY = :company AND PAYGROUP = :payGroup "
                    		+ " AND PAY_END_DT = :payEndDate "
                    		+ " AND OFF_CYCLE = :offCycle "
                    		+ " AND T2_HRP_PAYROLL_NUM = :payrollNumber "
                    		+ " AND EMPLID = :emplid "
                    		+ " ORDER BY LAST_NAME, END_DT",
            resultClass = PayrollEmployeeRecordVW.class
    )
})
public class PayrollEmployeeRecordVW implements Serializable {

    private static final long serialVersionUID = 1L;

    /* Primary Key Definition Starts */
    @Id
    @Column(nullable = false, name = "COMPANY")
    private String company;

    @Id
    @Column(nullable = false, name = "PAYGROUP")
    private String payrollGroup;

    @Id
    @Column(nullable = false, name = "PAY_END_DT")
    @Temporal(TemporalType.DATE)
    private Date payEndDate;

    @Id
    @Column(nullable = false, name = "OFF_CYCLE")
    private String offCycle = "N";

    @Id
    @Column(nullable = false, name = "T2_HRP_PAYROLL_NUM")
    private Integer payrollNum;

    @Id
    @Column(nullable = false, name = "EMPLID")
    private String emplId = " ";

    @Id
    @Column(nullable = false, name = "EMPL_RCD")
    private Integer emplRecord;

    @Id
    @Column(nullable = false, name = "START_DT")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Id
    @Column(nullable = false, name = "END_DT")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @Id
    @Column(nullable = false, name = "T2_PE_LINE_NBR")
    private Integer lineNumber = 0;
    
    @Id
    @Column(nullable = false, name = "ERNCD")
    private String jobEarnCode = " ";

    @Id
    @Column(nullable = false, name = "DEPTID")
    private String deptId = " ";

    @Id
    @Column(nullable = false, name = "JOBCODE")
    private String jobCode = " ";

    @Id
    @Column(nullable = false, name = "LOCATION")
    private String location = " ";

    /** Primary key ends **/
    @Column(nullable = false, name = "GROSSUP")
    private String grossup = "N";
    
    @Column(nullable = false, name = "DESCR")
    private String departmentDesc;

    @Column(nullable = false, name = "DESCR1")
    private String locationDesc;

    @Column(nullable = false, name = "T2_PE_OVR_CMP_RT")
    private BigDecimal overrideCompRate = new BigDecimal(0);

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "EMPL_STATUS")
    private String emplStatus;

    @Column(name = "T2_EMPL_STS_DESCR")
    private String emplStatusDescr;

    @Column(name = "REG_TEMP")
    private String regOrTemp;

    @Column(name = "FULL_PART_TIME")
    private String fullOrPartTime;

    @Column(name = "EMPL_TYPE")
    private String emplType;

    @Column(nullable = false, name = "DIST_PCT")
    private BigDecimal splitPercent = new BigDecimal(0);

    @Column(name = "STD_HOURS")
    private Double stdHours;

    @Column(name = "STD_HRS_FREQUENCY")
    private String stdHrsFrequency = " ";

    @Column(name = "HIRE_DT")
    @Temporal(TemporalType.DATE)
    private Date hireDt;

    @Temporal(TemporalType.DATE)
    @Column(name = "TERMINATION_DT")
    private Date terminationDate;

    @Column(nullable = false, name = "COMPRATE")
    private BigDecimal compRate = new BigDecimal(0);

    @Column(name = "US_WORK_ELIGIBILTY")
    private String usWorkEligibilty;

    @Column(name = "PAY_FREQUENCY")
    private String payFrequency;

    // Y / N
    @Column(name = "DISABLE_DIR_DEP")
    private String disableDirectDeposit;

    @Column(name = "T2_PE_SOURCE")
    private String source;

    @Column(name = "T2_HOME_DEPT_FLG")
    private String homeDept;

    @Column(name = "ACTION")
    private String midPayAction;
    
    @Column(name = "ACTION_DESCR")
    private String midPayActionDesc;
    
    @Transient
    private String changedByProc="N";
    
    @Temporal(TemporalType.DATE)
    @Column(name = "JOB_EFFDT")
    private Date actionEffDate;
    
    @Column(nullable = false, name = "T2_PE_JED_FLG")
    private String jed = "N";
    
    @Column(nullable = false, name = "T2_PE_PP_HRS")
    private BigDecimal payPeriodHours = new BigDecimal(0);
    
    @Column(name = "EMPL_CLASS")
    private String emplClass;
    
    /* Generated Getter & Setter */
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPayrollGroup() {
        return payrollGroup;
    }

    public void setPayrollGroup(String payrollGroup) {
        this.payrollGroup = payrollGroup;
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

    public Integer getPayrollNum() {
        return payrollNum;
    }

    public void setPayrollNum(Integer payrollNum) {
        this.payrollNum = payrollNum;
    }

    public String getEmplId() {
        return emplId;
    }

    public void setEmplId(String emplId) {
        this.emplId = emplId;
    }

    public Integer getEmplRecord() {
        return emplRecord;
    }

    public void setEmplRecord(Integer emplRecord) {
        this.emplRecord = emplRecord;
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

    public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getJobEarnCode() {
		return jobEarnCode;
	}

	public void setJobEarnCode(String jobEarnCode) {
		this.jobEarnCode = jobEarnCode;
	}

	public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
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

    public String getGrossup() {
		return grossup;
	}

	public void setGrossup(String grossup) {
		this.grossup = grossup;
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

    public BigDecimal getOverrideCompRate() {
        return overrideCompRate;
    }

    public void setOverrideCompRate(BigDecimal overrideCompRate) {
        this.overrideCompRate = overrideCompRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmplType() {
        return emplType;
    }

    public void setEmplType(String emplType) {
        this.emplType = emplType;
    }

    public BigDecimal getSplitPercent() {
        return splitPercent;
    }

    public void setSplitPercent(BigDecimal splitPercent) {
        this.splitPercent = splitPercent;
    }

    public Double getStdHours() {
        return stdHours;
    }

    public void setStdHours(Double stdHours) {
        this.stdHours = stdHours;
    }

    public String getStdHrsFrequency() {
        return stdHrsFrequency;
    }

    public void setStdHrsFrequency(String stdHrsFrequency) {
        this.stdHrsFrequency = stdHrsFrequency;
    }

    public Date getHireDt() {
        return hireDt;
    }

    public void setHireDt(Date hireDt) {
        this.hireDt = hireDt;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public BigDecimal getCompRate() {
        return compRate;
    }

    public void setCompRate(BigDecimal compRate) {
        this.compRate = compRate;
    }

    public String getUsWorkEligibilty() {
        return usWorkEligibilty;
    }

    public void setUsWorkEligibilty(String usWorkEligibilty) {
        this.usWorkEligibilty = usWorkEligibilty;
    }

    public String getPayFrequency() {
        return payFrequency;
    }

    public void setPayFrequency(String payFrequency) {
        this.payFrequency = payFrequency;
    }

    public String getDisableDirectDeposit() {
        return disableDirectDeposit;
    }

    public void setDisableDirectDeposit(String disableDirectDeposit) {
        this.disableDirectDeposit = disableDirectDeposit;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getHomeDept() {
        return homeDept;
    }

    public void setHomeDept(String homeDept) {
        this.homeDept = homeDept;
    }
   
	public String getChangedByProc() {
		return changedByProc;
	}

	public void setChangedByProc(String changedByProc) {
		this.changedByProc = changedByProc;
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

	public String getJed() {
		return jed;
	}

	public void setJed(String jed) {
		this.jed = jed;
	}

	public BigDecimal getPayPeriodHours() {
		return payPeriodHours;
	}

	public void setPayPeriodHours(BigDecimal payPeriodHours) {
		this.payPeriodHours = payPeriodHours;
	}

	public String getEmplClass() {
		return emplClass;
	}

	public void setEmplClass(String emplClass) {
		this.emplClass = emplClass;
	}

    
	
}