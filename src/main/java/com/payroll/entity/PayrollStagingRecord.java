package com.payroll.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.payroll.entity.pk.PayrollStagingRecordKey;
import com.ptg.payroll.domain.ErrorMessage;

@Entity
@Table(name = "PS_T2_PE_UPLD_STG", uniqueConstraints = @UniqueConstraint(columnNames = { "COMPANY", "PAYGROUP", "PAY_END_DT", "OFF_CYCLE",
        "T2_HRP_PAYROLL_NUM", "EMPLID", "LOCATION", "DEPTID", "JOBCODE", "T2_DUR", "ERNCD", "T2_PE_PROFILE_ID", "T2_PE_SOURCE", "T2_PE_IS_VALID" , "SEQNUM"}))




@NamedNativeQueries({ 
		@NamedNativeQuery(
							name = "SQL.payroll.dataimport.findStagingRecords",
							query = "SELECT * FROM PS_T2_PE_UPLD_STG STG WHERE STG.COMPANY = :company AND STG.PAYGROUP = :payGroup AND STG.PAY_END_DT  = :payEndDate AND STG.OFF_CYCLE "
										+ " = :offCycle AND STG.T2_HRP_PAYROLL_NUM = :payrollNumber AND STG.T2_PE_PROFILE_ID   = :profileId AND STG.T2_PE_SOURCE = :source",
							resultClass = PayrollStagingRecord.class),
		@NamedNativeQuery(
							name = "SQL.payroll.dataimport.findInvalidStagingRecords",
							query = "SELECT * FROM PS_T2_PE_UPLD_STG STG WHERE STG.COMPANY = :company AND STG.PAYGROUP = :payGroup AND STG.PAY_END_DT  = :payEndDate AND STG.OFF_CYCLE "
									+ " = :offCycle AND STG.T2_HRP_PAYROLL_NUM = :payrollNumber AND STG.T2_PE_PROFILE_ID   = :profileId AND STG.T2_PE_SOURCE = :source"
									+ " AND T2_PE_IS_VALID='N'",
							resultClass = PayrollStagingRecord.class),
})
@JsonIgnoreProperties(ignoreUnknown = true, value = { "new" })
public class PayrollStagingRecord implements Serializable {

    private static final long serialVersionUID = -4502610804537618417L;

    @EmbeddedId
    private PayrollStagingRecordKey recordKey;

    @Column(nullable = false, name = "EARNS_BEGIN_DT")
    @Temporal(TemporalType.DATE)
    private Date earningBeginDate;

    @Column(nullable = false, name = "EARNS_END_DT")
    @Temporal(TemporalType.DATE)
    private Date earningEndDate;

    @Column(nullable = false, name = "HOURS_EARNED")
    private BigDecimal hours = new BigDecimal(0);

    @Column(nullable = false, name = "T2_PE_AMOUNT")
    private BigDecimal dollarAmount = new BigDecimal(0);

    @Column(nullable = false, name = "T2_OVERRIDE_AMOUNT")
    private BigDecimal overrideAmount = new BigDecimal(0);

    @Column(nullable = false, name = "T2_PE_UNITS")
    private BigDecimal units = new BigDecimal(0);

    @Column(nullable = false, name = "T2_PE_OVR_UNIT_RT")
    private BigDecimal overrideUnitRate = new BigDecimal(0);

    @Column(nullable = false, name = "T2_PE_OVR_HRLY_RT")
    private BigDecimal overrideHourlyRate = new BigDecimal(0); 

    @Column(nullable = false, name = "T2_PE_IS_VALID")
    private String isValid = "N";

    @Column(nullable = true, name = "T2_PE_ERROR_DESCR")
    private String errorMessage;
    
    @Column(nullable = false, name = "SEPCHK")
    private int checkNumber= 0;

    @Column(nullable=false, name="ALTER_EMPLID")
    private String alternateEmployeeId = " ";
    
    @Transient
    private Integer virtualId; // Primarily to use as unique key for the records.

    @Transient
    private List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();

    @Transient
    private String employeeName; // LastName, FirstName

    /* Auto generated code, please don't modify */
    public PayrollStagingRecordKey getRecordKey() {
        return recordKey;
    }

    public void setRecordKey(PayrollStagingRecordKey recordKey) {
        this.recordKey = recordKey;
    }

    public Date getEarningBeginDate() {
        return earningBeginDate;
    }

    public void setEarningBeginDate(Date earningBeginDate) {
        this.earningBeginDate = earningBeginDate;
    }

    public Date getEarningEndDate() {
        return earningEndDate;
    }

    public void setEarningEndDate(Date earningEndDate) {
        this.earningEndDate = earningEndDate;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public BigDecimal getDollarAmount() {
        return dollarAmount;
    }

    public void setDollarAmount(BigDecimal dollarAmount) {
        this.dollarAmount = dollarAmount;
    }

    public BigDecimal getOverrideAmount() {
        return overrideAmount;
    }

    public void setOverrideAmount(BigDecimal overrideAmount) {
        this.overrideAmount = overrideAmount;
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

    public BigDecimal getOverrideHourlyRate() {
        return overrideHourlyRate;
    }

    public void setOverrideHourlyRate(BigDecimal overrideHourlyRate) {
        this.overrideHourlyRate = overrideHourlyRate;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public Integer getVirtualId() {
        return virtualId;
    }

    public void setVirtualId(Integer virtualId) {
        this.virtualId = virtualId;
    }

    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<ErrorMessage> errorMessages) {
        this.errorMessages = errorMessages;
    }
    
    public int getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(int checkNumber) {
		this.checkNumber = checkNumber;
	}

	public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

	public String getAlternateEmployeeId() {
        return alternateEmployeeId;
    }

    public void setAlternateEmployeeId(String alternateEmployeeId) {
        this.alternateEmployeeId = alternateEmployeeId;
    }

    @Override
	public String toString() {
		return "PayrollStagingRecord [recordKey=" + recordKey
				+ ", earningBeginDate=" + earningBeginDate
				+ ", earningEndDate=" + earningEndDate + ", hours=" + hours
				+ ", dollarAmount=" + dollarAmount + ", overrideAmount="
				+ overrideAmount + ", units=" + units + ", overrideUnitRate=" + overrideUnitRate
				+ ", isValid="
				+ isValid + ", errorMessage=" + errorMessage + ", virtualId="
				+ virtualId + ", errorMessages=" + errorMessages
				+ ", employeeName=" + employeeName + "]";
	}
    
    

}