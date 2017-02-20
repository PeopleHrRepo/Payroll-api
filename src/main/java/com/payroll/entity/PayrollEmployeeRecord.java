package com.payroll.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "PS_T2_PE_PYRL_EMP", uniqueConstraints = @UniqueConstraint(columnNames = { "COMPANY", "PAYGROUP", "PAY_END_DT", "OFF_CYCLE",
        "T2_HRP_PAYROLL_NUM", "EMPLID", "EMPL_RCD", "DEPTID", "JOBCODE", "LOCATION", "START_DT", "END_DT" }))
@NamedNativeQueries({
    @NamedNativeQuery(
            name="getPayrollEmployeeRecords",
            query="SELECT * FROM PS_T2_PE_PYRL_EMP "+
                    "WHERE COMPANY =  :company "+ 
                    "AND PAYGROUP = :payGroup "+
                    "AND PAY_END_DT = :payEndDate "+
                    "AND OFF_CYCLE = :offCycle "+
                    "AND T2_HRP_PAYROLL_NUM = :payrollNumber "+
                    "ORDER BY LAST_NAME, END_DT ",
            resultClass = PayrollEmployeeRecord.class
    )
})
public class PayrollEmployeeRecord implements Serializable{

	private static final long serialVersionUID = -4502610804537618417L;
	
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
    private Integer emplRecd;

    @Id
    @Column(nullable = false, name = "DEPTID")
    private String deptId = " ";

    @Id
    @Column(nullable = false, name = "JOBCODE")
    private String jobCode = " ";

    @Id
    @Column(nullable = false, name = "LOCATION")
    private String location = " ";

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
    
    @Column(nullable = false, name = "T2_PE_JED_FLG")
    private String jed = "N";
    
    @Column(nullable = false, name = "T2_PE_PP_HRS")
    private BigDecimal payPeriodHours = new BigDecimal(0);

    /* Primary Key Definition ends */
    @Column(nullable = false, name = "GROSSUP")
    private String grossup = "N";

    @Column(nullable = false, name = "DIST_PCT")
    private BigDecimal splitPercent = new BigDecimal(0);

    @Column(name = "EMPL_TYPE")
    private String emplType;

    @Column(nullable = false, name = "STD_HOURS")
    private BigDecimal stdHours = new BigDecimal(0);

    @Column(nullable = false, name = "COMPRATE")
    private BigDecimal compRate = new BigDecimal(0);

    @Column(nullable = false, name = "T2_PE_OVR_CMP_RT")
    private BigDecimal overrideCompRate = new BigDecimal(0);

    @Column(nullable = false, name = "DISABLE_DIR_DEP")
    private String disableDirectDeposit;

    @Column(name = "T2_PE_SOURCE")
    private String source;
    
    @Column(name = "T2_HOME_DEPT_FLG")
    private String homeDept;

    /* IDE Generated getters & setters */

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

    public Integer getEmplRecd() {
        return emplRecd;
    }

    public void setEmplRecd(Integer emplRecd) {
        this.emplRecd = emplRecd;
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

	public String getGrossup() {
		return grossup;
	}

	public void setGrossup(String grossup) {
		this.grossup = grossup;
	}

	public BigDecimal getSplitPercent() {
        return splitPercent;
    }

    public void setSplitPercent(BigDecimal splitPercent) {
        this.splitPercent = splitPercent;
    }

    public String getEmplType() {
        return emplType;
    }

    public void setEmplType(String emplType) {
        this.emplType = emplType;
    }

    public BigDecimal getStdHours() {
        return stdHours;
    }

    public void setStdHours(BigDecimal stdHours) {
        this.stdHours = stdHours;
    }

    public BigDecimal getCompRate() {
        return compRate;
    }

    public void setCompRate(BigDecimal compRate) {
        this.compRate = compRate;
    }

    public BigDecimal getOverrideCompRate() {
        return overrideCompRate;
    }

    public void setOverrideCompRate(BigDecimal overrideCompRate) {
        this.overrideCompRate = overrideCompRate;
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
    
    
}
