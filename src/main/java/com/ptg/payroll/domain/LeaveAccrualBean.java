package com.ptg.payroll.domain;
import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class LeaveAccrualBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String company;
	private String emplId;
	private Integer emplRcd;
	private String ernCd;
	private Double balanceHours;
	
	public LeaveAccrualBean(){
		
	}
	
	public LeaveAccrualBean(String company, String emplId, Integer emplRcd, String ernCd, Double balanceHours){
		this.company = company;
		this.emplId = emplId;
		this.emplRcd = emplRcd;
		this.ernCd = ernCd;
		this.balanceHours= balanceHours;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public String getErnCd() {
		return ernCd;
	}

	public void setErnCd(String ernCd) {
		this.ernCd = ernCd;
	}

	public Double getBalanceHours() {
		return balanceHours;
	}

	public void setBalanceHours(Double balanceHours) {
		this.balanceHours = balanceHours;
	}
		
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	}
}
