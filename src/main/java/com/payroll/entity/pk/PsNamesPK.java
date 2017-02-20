package com.payroll.entity.pk;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Embeddable
public class PsNamesPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="EMPLID", nullable=false, length=11)
	private String emplId;
	
	@Column(name="NAME_TYPE", nullable=false, length=2)
	private String nameType;
	
	@Temporal(TemporalType.DATE)
	@Column(name="EFFDT")
	private Date effectiveDate;

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getEmplId() {
		return emplId;
	}

	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}

	public String getNameType() {
		return nameType;
	}

	public void setNameType(String nameType) {
		this.nameType = nameType;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	

}
