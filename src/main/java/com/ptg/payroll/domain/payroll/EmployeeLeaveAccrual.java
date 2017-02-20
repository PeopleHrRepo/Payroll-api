package com.ptg.payroll.domain.payroll;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.ptg.payroll.domain.LeaveAccrualBean;

public class EmployeeLeaveAccrual implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String company;
	private String emplId;
	private Integer emplRcd;
	List<Leave> leaveAccruals;
	
	public EmployeeLeaveAccrual(){
		
	}
	
	public EmployeeLeaveAccrual(LeaveAccrualBean bean){
		this.company = bean.getCompany();
		this.emplId = bean.getEmplId();
		this.emplRcd = bean.getEmplRcd();
		
		this.addLeaveAccrual(bean.getErnCd(), bean.getBalanceHours());
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
	
	public List<Leave> getLeaveAccruals() {
		return leaveAccruals;
	}

	public void setLeaveAccruals(List<Leave> leaveAccruals) {
		this.leaveAccruals = leaveAccruals;
	}
	
	public void addLeaveAccrual(String ernCd, Double balanceHours){
		if(this.leaveAccruals == null){
			this.leaveAccruals = new ArrayList<Leave>();
		}
		this.leaveAccruals.add(new Leave(ernCd, balanceHours));
	}

	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	}
}
