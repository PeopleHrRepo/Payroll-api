package com.payroll.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.payroll.entity.pk.PayrollPayPK;

@Entity
@Table(name="PS_T2_PE_PAY_TBL")
@NamedNativeQueries({
	@NamedNativeQuery(
			name="deletePayrollPayForPayGroup",
			query="delete from PS_T2_PE_PAY_TBL p "+
					"WHERE p.COMPANY = :company "+						
					"AND p.PAYGROUP = :payGroup "+
					"AND p.PAY_END_DT = :payEndDt ",
			resultClass=PayrollPay.class)
})
public class PayrollPay  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public PayrollPay(){}
	
	
	@EmbeddedId
	private PayrollPayPK id;
	
	@Column(name="EARNS_BEGIN_DT")
	@Temporal(TemporalType.DATE)
	private Date earnsBeginDt;
	
	@Temporal(TemporalType.DATE)
	@Column(name="EARNS_END_DT")
	private Date earnsEndDt;
	
	@Column(name="HOURS_EARNED")
	private BigDecimal hoursEarned = new BigDecimal(0);
	
	@Column(name="DEDCD")
	private String dedCd;
	
	@Column(name="DED_CLASS")
	private String dedClass;
	
	@Column(name="T2_PE_AMOUNT")
	private BigDecimal amount = new BigDecimal(0);
	
	@Column(name="T2_OVERRIDE_AMOUNT")
	private BigDecimal overrideAmount = new BigDecimal(0);
	
	@Column(name="T2_PE_OVR_HRLY_RT")
	private BigDecimal overrideHourlyRate = new BigDecimal(0);
	
	@Column(name="T2_PE_OVR_UNIT_RT")
	private BigDecimal overrideUnitRate = new BigDecimal(0);
	
	@Column(name="T2_PE_UNITS")
	private BigDecimal units = new BigDecimal(0);
		
	@Column(name="T2_PE_SOURCE")
	private String source;
	
	@Column(name="DISABLE_DIR_DEP") // Y / N
    private String disableDirectDeposit="N";
	
    @Column(nullable = false, name="ADDL_SEQ")     
	private Integer addSeq=0;

	public PayrollPayPK getId() {
		return id;
	}

	public void setId(PayrollPayPK id) {
		this.id = id;
	}

	public BigDecimal getHoursEarned() {
		return hoursEarned;
	}

	public void setHoursEarned(BigDecimal hoursEarned) {
		this.hoursEarned = hoursEarned;
	}

	public Date getEarnsBeginDt() {
		return earnsBeginDt;
	}

	public void setEarnsBeginDt(Date earnsBeginDt) {
		this.earnsBeginDt = earnsBeginDt;
	}

	public Date getEarnsEndDt() {
		return earnsEndDt;
	}

	public void setEarnsEndDt(Date earnsEndDt) {
		this.earnsEndDt = earnsEndDt;
	}

	public String getDedCd() {
		return dedCd;
	}

	public void setDedCd(String dedCd) {
		this.dedCd = dedCd;
	}

	public String getDedClass() {
		return dedClass;
	}

	public void setDedClass(String dedClass) {
		this.dedClass = dedClass;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getOverrideAmount() {
		return overrideAmount;
	}

	public void setOverrideAmount(BigDecimal overrideAmount) {
		this.overrideAmount = overrideAmount;
	}
	
	public BigDecimal getOverrideHourlyRate() {
		return overrideHourlyRate;
	}

	public void setOverrideHourlyRate(BigDecimal overrideHourlyRate) {
		this.overrideHourlyRate = overrideHourlyRate;
	}

	public BigDecimal getOverrideUnitRate() {
		return overrideUnitRate;
	}

	public void setOverrideUnitRate(BigDecimal overrideUnitRate) {
		this.overrideUnitRate = overrideUnitRate;
	}

	public BigDecimal getUnits() {
		return units;
	}

	public void setUnits(BigDecimal units) {
		this.units = units;
	}
	

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public Integer getAddSeq() {
		return addSeq;
	}

	public void setAddSeq(Integer addSeq) {
		this.addSeq = addSeq;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	
}
