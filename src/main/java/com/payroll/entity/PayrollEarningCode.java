package com.payroll.entity;

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
import javax.persistence.UniqueConstraint;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.payroll.entity.pk.PayrollEarningCodeKey;



@Entity
@Table(name = "PS_T2_PE_PYRL_ERCD", uniqueConstraints = @UniqueConstraint(columnNames = { 
		"COMPANY", "PAYGROUP", "PAY_END_DT", "OFF_CYCLE", "T2_HRP_PAYROLL_NUM", "ERNCD" }))
@JsonIgnoreProperties(ignoreUnknown = true, value = { "new" })
@NamedNativeQueries({
    @NamedNativeQuery(
            name="getPayrollEarningCodes",
            query="SELECT * FROM PS_T2_PE_PYRL_ERCD "+
            "WHERE company = :company  "+
            "and PAYGROUP = :payGroup  "+
            "AND PAY_END_DT = :payEndDate "+
            "AND OFF_CYCLE = :offCycle  "+
            "AND T2_HRP_PAYROLL_NUM = :payrollNumber "+ 
            "AND T2_PE_STATUS_CD != 'D'",
            resultClass = PayrollEarningCode.class
    )
})
public class PayrollEarningCode {
	
	private static final long serialVersionUID = -4502610804537618417L;

    @EmbeddedId
    private PayrollEarningCodeKey key;

    @Column(nullable = false, name = "SEQNO")
    private BigDecimal seqNumber = new BigDecimal(0);

    @Column(nullable = false, name = "T2_PE_STATUS_CD")
    private String status = "N";
    
    @Column(nullable = false, name = "LASTUPDDTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate = new Date();

    @Column(nullable = true, name = "LASTUPDOPRID")
    private String operatorId;


    /* Auto generated code, please don't modify */

	public PayrollEarningCodeKey getKey() {
		return key;
	}

	public void setKey(PayrollEarningCodeKey key) {
		this.key = key;
	}

	public BigDecimal getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(BigDecimal seqNumber) {
		this.seqNumber = seqNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
