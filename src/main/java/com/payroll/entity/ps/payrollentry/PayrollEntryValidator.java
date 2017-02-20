package com.payroll.entity.ps.payrollentry;
import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class PayrollEntryValidator implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Character validatorType;
	private String ernCd;
	private String ernCdType;
	private String emplType;
	private BigDecimal minValue;
	private BigDecimal maxValue;
	private String message;
	private String severity;
	private boolean isEarningCode;
	
	public PayrollEntryValidator(){
		
	}
	
	public PayrollEntryValidator(Character validatorType, String ernCd, String ernCdType, String emplType, 
			BigDecimal minValue, BigDecimal maxValue, String message, String severity){
		this.validatorType = validatorType;
		this.ernCd = ernCd;
		this.ernCdType = ernCdType;
		this.emplType = emplType;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.message = message;
		this.severity = severity;
		this.isEarningCode = validatorType != null && validatorType.equals('E') ? true : false;
	}

	public Character getValidatorType() {
		return validatorType;
	}

	public void setValidatorType(Character validatorType) {
		this.validatorType = validatorType;
	}

	public String getErnCd() {
		return ernCd;
	}

	public void setErnCd(String ernCd) {
		this.ernCd = ernCd;
	}

	public String getErnCdType() {
		return ernCdType;
	}

	public void setErnCdType(String ernCdType) {
		this.ernCdType = ernCdType;
	}

	public BigDecimal getMinValue() {
		return minValue;
	}

	public void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}

	public BigDecimal getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}
	
	public boolean isEarningCode() {
		return isEarningCode;
	}

	public void setEarningCode(boolean isEarningCode) {
		this.isEarningCode = isEarningCode;
	}

	public String getEmplType() {
		return emplType;
	}

	public void setEmplType(String emplType) {
		this.emplType = emplType;
	}

	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	}
}
