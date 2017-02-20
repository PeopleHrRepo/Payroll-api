package com.ptg.payroll.domain;


import com.payroll.entity.BaseEntity;



public class PayrollUserCompany extends BaseEntity{
	
	
	private static final long serialVersionUID = -7761488880939091401L;
	
	private String orgId;
    private String orgDesc;
    private boolean isCurrentCompany;
    private String isTriNetLeaveSystemCompany ;
    private String country;
    private String isSortApprovalConfigured;
    private String hasHistoricData;
    private String brand;
    private String vertical;
    
	public String getIsTriNetLeaveSystemCompany() {
		return isTriNetLeaveSystemCompany;
	}
	public void setIsTriNetLeaveSystemCompany(String isTriNetLeaveSystemCompany) {
		this.isTriNetLeaveSystemCompany = isTriNetLeaveSystemCompany;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgDesc() {
		return orgDesc;
	}
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}
	
	
	public boolean isCurrentCompany() {
		return isCurrentCompany;
	}
	
	public void setIsCurrentCompany(boolean isCurrentCompany) {
		this.isCurrentCompany = isCurrentCompany;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getIsSortApprovalConfigured() {
		return isSortApprovalConfigured;
	}
	
	public void setIsSortApprovalConfigured(String isSortApprovalConfigured) {
		this.isSortApprovalConfigured = isSortApprovalConfigured;
	}
	@Override
	public String toString() {
		return "PayrollUserCompany [orgId=" + orgId + ", orgDesc=" + orgDesc
				+ ", isCurrentCompany=" + isCurrentCompany
				+ ", isTriNetLeaveSystemCompany=" + isTriNetLeaveSystemCompany
				+ ", country=" + country + ", isSortApprovalConfigured="
				+ isSortApprovalConfigured + "]";
	}
	public String getHasHistoricData() {
		return hasHistoricData;
	}
	public void setHasHistoricData(String hasHistoricData) {
		this.hasHistoricData = hasHistoricData;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getVertical() {
		return vertical;
	}
	public void setVertical(String vertical) {
		this.vertical = vertical;
	}
	
	
    
}