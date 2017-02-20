package com.ptg.payroll.domain.payroll;
import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Leave implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String ernCd;
	private Double balanceHours;
	
	public Leave(){
		
	}
	
	public Leave(String ernCd, Double balanceHours){
	
		this.ernCd = ernCd;
		this.balanceHours= balanceHours;
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
