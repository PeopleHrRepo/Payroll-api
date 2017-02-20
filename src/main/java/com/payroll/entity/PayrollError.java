package com.payroll.entity;
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
import com.payroll.entity.pk.PayrollErrorKey;

@Entity
@Table(name = "PS_T2_PE_PYRL_ERRS", uniqueConstraints = @UniqueConstraint(columnNames = { 
		"COMPANY", "PAYGROUP", "PAY_END_DT", "OFF_CYCLE", "T2_HRP_PAYROLL_NUM", "EMPLID", "DEPTID", "ERNCD", "T2_PE_ERROR_CODE" }))
@JsonIgnoreProperties(ignoreUnknown = true, value = { "new" })
@NamedNativeQueries({
    @NamedNativeQuery(
            name="getPayrollErrors",
            query="SELECT * FROM PS_T2_PE_PYRL_ERRS "+
            "WHERE company = :company  "+
            "and PAYGROUP = :payGroup  "+
            "AND PAY_END_DT = :payEndDate "+
            "AND OFF_CYCLE = :offCycle  "+
            "AND T2_HRP_PAYROLL_NUM = :payrollNumber ",
            resultClass = PayrollError.class
    )
})
public class PayrollError {
	
	private static final long serialVersionUID = -4502610804537618417L;
	
    @EmbeddedId
    private PayrollErrorKey key;

    @Column(nullable = false, name = "ERRORMSG")
    private String message;
    
    @Column(nullable = false, name = "LASTUPDDTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate = new Date();

    @Column(nullable = true, name = "LASTUPDOPRID")
    private String operatorId;
    
    @Column(nullable=false, name="T2_REVIEWED")
    private String reviewed = "N";


    /* Auto generated code, please don't modify */

	public PayrollErrorKey getKey() {
		return key;
	}

	public void setKey(PayrollErrorKey key) {
		this.key = key;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReviewed() {
		return reviewed;
	}

	public void setReviewed(String reviewed) {
		this.reviewed = reviewed;
	}

	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
