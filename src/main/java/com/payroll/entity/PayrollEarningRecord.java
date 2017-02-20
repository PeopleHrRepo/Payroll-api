package com.payroll.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.ws.rs.DefaultValue;

@Entity
@Table(name = "PS_T2_PE_PYRL_RPTD", uniqueConstraints = @UniqueConstraint(columnNames = { "COMPANY", "PAYGROUP", "PAY_END_DT", "OFF_CYCLE",
        "T2_HRP_PAYROLL_NUM", "EMPLID", "EMPL_RCD", "DEPTID", "JOBCODE", "LOCATION", "ERNCD", "T2_DUR", "SEQNUM", "SEPCHK" }))
@NamedNativeQueries({
    @NamedNativeQuery(
            name="getPayrollEmployeeEarningRecords",
            query="SELECT * FROM PS_T2_PE_PYRL_RPTD "+
                    "WHERE COMPANY =  :company "+ 
                    "AND PAYGROUP = :payGroup "+
                    "AND PAY_END_DT = :payEndDate "+
                    "AND OFF_CYCLE = :offCycle "+
                    "AND T2_HRP_PAYROLL_NUM = :payrollNumber ",
            resultClass = PayrollEarningRecord.class
    ),
    @NamedNativeQuery(
            name="getPayrollEmployeeEarningRecordsForEmployee",
            query="SELECT * FROM PS_T2_PE_PYRL_RPTD "+
                    "WHERE COMPANY =  :company "+ 
            		"AND EMPLID = :emplid "+
                    "AND PAYGROUP = :payGroup "+
                    "AND PAY_END_DT = :payEndDate "+
                    "AND OFF_CYCLE = :offCycle "+
                    "AND T2_HRP_PAYROLL_NUM = :payrollNumber ",
            resultClass = PayrollEarningRecord.class
    )
    
})
public class PayrollEarningRecord implements Serializable {

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
    private Integer emplRcd = 0;

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
    @Column(nullable = false, name = "ERNCD")
    private String earnCode;

    @Id
    @Column(nullable = false, name = "T2_DUR")
    @Temporal(TemporalType.DATE)
    private Date reportedDate;

    @Id
    @Column(nullable = false, name = "SEQNUM")
    private Integer addLineNum;

    @Id
    @Column(nullable = false, name = "SEPCHK")
    private Integer sepChk;

    /* Primary Key Definition ends */

    @Column(nullable = false, name = "GROSSUP")
    private String grossup = "N";

    @Column(nullable = false, name = "HOURS_EARNED")
    private BigDecimal hours = new BigDecimal(0);

    @Column(nullable = false, name = "T2_PE_AMOUNT")
    private BigDecimal amount = new BigDecimal(0);

    @Column(nullable = false, name = "T2_PE_UNITS")
    private BigDecimal units = new BigDecimal(0);

    @Column(nullable = false, name = "T2_PE_OVR_UNIT_RT")
    private BigDecimal overrideUnitRate = new BigDecimal(0);

    @Column(nullable = false, name = "DISABLE_DIR_DEP")
    private String disableDirectDeposit;

    @Id
    @Column(nullable = false, name = "EARNS_BEGIN_DT")
    @Temporal(TemporalType.DATE)
    private Date earnBeginDate;

    @Id
    @Column(nullable = false, name = "EARNS_END_DT")
    @Temporal(TemporalType.DATE)
    private Date earnEndDate;

    @Column(name = "DEDCD")
    private String dedCode;

    @Column(name = "DED_CLASS")
    private String dedClass;

    @Column(name = "T2_PE_SOURCE")
    private String source;
	
    @Column(name = "T2_PE_SUP_ERNCD")
    private String forceSepChk = "N";
    
    @Column(name = "T2_PE_401_ERCD_FLG")
    private String excludeFrom401K = "N";
	
    @Column(nullable = false, name="ADDL_SEQ")     
	private Integer addSeq=0;
    
    @Column(nullable = false, name = "T2_PE_RECR_JED_FLG")
    private String recrJed = "N";
    
    @Column(nullable = false, name = "T2_PE_OVR_HRLY_RT")
    private BigDecimal overrideHourlyRate = new BigDecimal(0);
    
    @Column(nullable = false, name = "T2_OVERRIDE_AMOUNT")
    private BigDecimal overrideAmount = new BigDecimal(0);

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

    public Integer getEmplRcd() {
        return emplRcd;
    }

    public void setEmplRcd(Integer emplRcd) {
        this.emplRcd = emplRcd;
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

    public String getEarnCode() {
        return earnCode;
    }

    public void setEarnCode(String earnCode) {
        this.earnCode = earnCode;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }

    public Integer getAddLineNum() {
        return addLineNum;
    }

    public void setAddLineNum(Integer addLineNum) {
        this.addLineNum = addLineNum;
    }

    public Integer getSepChk() {
        return sepChk;
    }

    public void setSepChk(Integer sepChk) {
        this.sepChk = sepChk;
    }

    public String getGrossup() {
        return grossup;
    }

    public void setGrossup(String grossup) {
        this.grossup = grossup;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getUnits() {
        return units;
    }

    public void setUnits(BigDecimal units) {
        this.units = units;
    }

    public BigDecimal getOverrideUnitRate() {
        return overrideUnitRate;
    }

    public void setOverrideUnitRate(BigDecimal overrideUnitRate) {
        this.overrideUnitRate = overrideUnitRate;
    }

    public String getDisableDirectDeposit() {
        return disableDirectDeposit;
    }

    public void setDisableDirectDeposit(String disableDirectDeposit) {
        this.disableDirectDeposit = disableDirectDeposit;
    }

    public Date getEarnBeginDate() {
        return earnBeginDate;
    }

    public void setEarnBeginDate(Date earnBeginDate) {
        this.earnBeginDate = earnBeginDate;
    }

    public Date getEarnEndDate() {
        return earnEndDate;
    }

    public void setEarnEndDate(Date earnEndDate) {
        this.earnEndDate = earnEndDate;
    }

    public String getDedCode() {
        return dedCode;
    }

    public void setDedCode(String dedCode) {
        this.dedCode = dedCode;
    }

    public String getDedClass() {
        return dedClass;
    }

    public void setDedClass(String dedClass) {
        this.dedClass = dedClass;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

	public String getForceSepChk() {
		return forceSepChk;
	}

	public void setForceSepChk(String forceSepChl) {
		this.forceSepChk = forceSepChl;
	}

	public String getExcludeFrom401K() {
		return excludeFrom401K;
	}

	public void setExcludeFrom401K(String excludeFrom401K) {
		this.excludeFrom401K = excludeFrom401K;
	}

	public Integer getAddSeq() {
		return addSeq;
	}

	public void setAddSeq(Integer addSeq) {
		this.addSeq = addSeq;
	}

	public String getRecrJed() {
		return recrJed;
	}

	public void setRecrJed(String recrJed) {
		this.recrJed = recrJed;
	}

	public BigDecimal getOverrideHourlyRate() {
		return overrideHourlyRate;
	}

	public void setOverrideHourlyRate(BigDecimal overrideHourlyRate) {
		this.overrideHourlyRate = overrideHourlyRate;
	}

	public BigDecimal getOverrideAmount() {
		return overrideAmount;
	}

	public void setOverrideAmount(BigDecimal overrideAmount) {
		this.overrideAmount = overrideAmount;
	}

	public String computeUniquenessForSeqNum() {
		//NOTE : this key uses everything except seqnum - 
		//if this function returns same uniqueDBKey, seqnum should get incremented
		StringBuilder key = new StringBuilder();
		key.append(this.getCompany());
		key.append(this.getPayrollGroup());
		key.append(this.getPayEndDate());
		key.append(this.getOffCycle());
		key.append(this.getPayrollNum());
		key.append(this.getEmplId());
		key.append(this.getEmplRcd());
		key.append(this.getDeptId());
		key.append(this.getJobCode());
		key.append(this.getLocation());
		key.append(this.getEarnCode());
		key.append(this.getReportedDate());
		key.append(this.getSepChk());
		return key.toString();
	}
	
	public String computeUniquenessForSeqNumMinusEarnCode() {
		//NOTE : this key uses everything except seqnum and earn code specifically for Job Costing - 
		//if this function returns same uniqueDBKey, seqnum should get incremented
		StringBuilder key = new StringBuilder();
		key.append(this.getCompany());
		key.append(this.getPayrollGroup());
		key.append(this.getPayEndDate());
		key.append(this.getOffCycle());
		key.append(this.getPayrollNum());
		key.append(this.getEmplId());
		key.append(this.getEmplRcd());
		key.append(this.getDeptId());
		key.append(this.getJobCode());
		key.append(this.getLocation());
		key.append(this.getReportedDate());
		key.append(this.getSepChk());
		return key.toString();
	}	
	

}