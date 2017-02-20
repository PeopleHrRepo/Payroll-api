package com.ptg.payroll.domain.payroll;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.payroll.entity.PayrollHolidanBean;
import com.payroll.entity.TimesheetPayrollGroup;


public class SpecialPayrollInfo {
	private Integer checkStartDay;
	private Integer directDepositStartDay;
	private BigDecimal checkReportLeadDate;
	private BigDecimal adviceReportLeadDate;
	private String reportTime;
	private Date clientTodaysDate;
	private List<TimesheetPayrollGroup> payGroups = new ArrayList<TimesheetPayrollGroup>();
	private List<PayrollHolidanBean> holidays = new ArrayList<PayrollHolidanBean>();
	private String companyAddress;
		
	public Integer getCheckStartDay() {
		return checkStartDay;
	}
	public void setCheckStartDay(Integer checkStartDay) {
		this.checkStartDay = checkStartDay;
	}
	public Integer getDirectDepositStartDay() {
		return directDepositStartDay;
	}
	public void setDirectDepositStartDay(Integer directDepositStartDay) {
		this.directDepositStartDay = directDepositStartDay;
	}
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	public List<TimesheetPayrollGroup> getPayGroups() {
		return payGroups;
	}
	public void setPayGroups(List<TimesheetPayrollGroup> payGroups) {
		this.payGroups = payGroups;
	}
	public List<PayrollHolidanBean> getHolidays() {
		return holidays;
	}
	public void setHolidays(List<PayrollHolidanBean> holidays) {
		this.holidays = holidays;
	}
	public Date getClientTodaysDate() {
		return clientTodaysDate;
	}
	public void setClientTodaysDate(Date clientTodaysDate) {
		this.clientTodaysDate = clientTodaysDate;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public BigDecimal getAdviceReportLeadDate() {
		return adviceReportLeadDate;
	}
	public void setAdviceReportLeadDate(BigDecimal adviceReportLeadDate) {
		this.adviceReportLeadDate = adviceReportLeadDate;
	}
	public BigDecimal getCheckReportLeadDate() {
		return checkReportLeadDate;
	}
	public void setCheckReportLeadDate(BigDecimal checkReportLeadDate) {
		this.checkReportLeadDate = checkReportLeadDate;
	}
	
}