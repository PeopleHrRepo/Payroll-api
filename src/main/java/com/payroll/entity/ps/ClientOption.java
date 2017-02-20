package com.payroll.entity.ps;
import java.math.BigDecimal;

public class ClientOption{
	
	private static final String PEO_ID_PASSPORT = "PAS";
	private static final String PEO_ID_SOI = "SOI";
	private static final String PEO_ID_AMBROSE = "AMB";
	
	private String company;
	private String peoId;
	private String t2ReviewCustomer;
	private String timeZone;
	private String webDeadline;
	private String offCycleWebDeadline;
	private String timeZoneStr;
	private BigDecimal checkReportLeadDate;
	private BigDecimal adviceReportLeadDate;
	
	public ClientOption(){
		
	}
	
	public ClientOption(String company, String peoId){
		this.company = company;
		this.peoId = peoId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getT2ReviewCustomer() {
		return t2ReviewCustomer;
	}

	public void setT2ReviewCustomer(String t2ReviewCustomer) {
		this.t2ReviewCustomer = t2ReviewCustomer;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getWebDeadline() {
		return webDeadline;
	}

	public void setOffCycleWebDeadline(String offCycleWebDeadline) {
		this.offCycleWebDeadline = offCycleWebDeadline;
	}

	public String getOffCycleWebDeadline() {
		return offCycleWebDeadline;
	}

	public void setWebDeadline(String webDeadline) {
		this.webDeadline = webDeadline;
	}

	public String getTimeZoneStr() {
		return timeZoneStr;
	}

	public void setTimeZoneStr(String timeZoneStr) {
		this.timeZoneStr = timeZoneStr;
	}

	public BigDecimal getCheckReportLeadDate() {
		return checkReportLeadDate;
	}

	public void setCheckReportLeadDate(BigDecimal checkReportLeadDate) {
		this.checkReportLeadDate = checkReportLeadDate;
	}

	public BigDecimal getAdviceReportLeadDate() {
		return adviceReportLeadDate;
	}

	public void setAdviceReportLeadDate(BigDecimal adviceReportLeadDate) {
		this.adviceReportLeadDate = adviceReportLeadDate;
	}

	public String getPeoId() {
		return peoId;
	}

	public void setPeoId(String peoId) {
		this.peoId = peoId;
	}
	
	public boolean isAmbroseClient() {
		return PEO_ID_AMBROSE.equals(this.getPeoId());
	}
	
	public boolean isPassportClient() {
		return PEO_ID_PASSPORT.equals(this.getPeoId());
	}
	
	public boolean isSoiClient() {
		return PEO_ID_SOI.equals(this.getPeoId());
	}
}