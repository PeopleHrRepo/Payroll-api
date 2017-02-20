package com.ptg.payroll.model;
import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class UserCompany implements Serializable{
	@JsonIgnore
    private String personid;
    
	private String userCompany;
    private String companyDesc;
	
    @JsonIgnore
    private String userPositionid;
	
	
    private String userClient;
	
	@JsonIgnore
    private String userEmployeeRecord;
	
	@JsonIgnore
    private String userEmployeeId;
	
    private String roles;
    
    private String hrRoles;
    
    private String companyLiveDate;
    
    private String userCountry;
    
    private String productLine;
    
    private String companyStatus;
    
    private String isAmbroseEE;
    
	public String getUserCountry() {
		return userCountry;
	}
	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}
	/* (non-Javadoc)
	 * @see com.trinet.fx.gateway.model.UserCompany#getPersonid()
	 */
    @XmlTransient
	public String getPersonid() {
		return personid;
	}
	/* (non-Javadoc)
	 * @see com.trinet.fx.gateway.model.UserCompany#setPersonid(java.lang.String)
	 */
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	/* (non-Javadoc)
	 * @see com.trinet.fx.gateway.model.UserCompany#getUserCompany()
	 */
	public String getUserCompany() {
		return userCompany;
	}
	/* (non-Javadoc)
	 * @see com.trinet.fx.gateway.model.UserCompany#setUserCompany(java.lang.String)
	 */
	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}
	/* (non-Javadoc)
	 * @see com.trinet.fx.gateway.model.UserCompany#getCompanyDesc()
	 */
	public String getCompanyDesc() {
		return companyDesc;
	}
	/* (non-Javadoc)
	 * @see com.trinet.fx.gateway.model.UserCompany#setCompanyDesc(java.lang.String)
	 */
	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}
	/* (non-Javadoc)
	 * @see com.trinet.fx.gateway.model.UserCompany#getUserPositionid()
	 */
	@XmlTransient
	public String getUserPositionid() {
		return userPositionid;
	}
	/* (non-Javadoc)
	 * @see com.trinet.fx.gateway.model.UserCompany#setUserPositionid(java.lang.String)
	 */
	public void setUserPositionid(String userPositionid) {
		this.userPositionid = userPositionid;
	}
	/* (non-Javadoc)
	 * @see com.trinet.fx.gateway.model.UserCompany#getUserClient()
	 */
	@XmlTransient
	public String getUserClient() {
		return userClient;
	}
	/* (non-Javadoc)
	 * @see com.trinet.fx.gateway.model.UserCompany#setUserClient(java.lang.String)
	 */
	public void setUserClient(String userClient) {
		this.userClient = userClient;
	}
    
	@XmlTransient
	public String getUserEmployeeRecord() {
		return this.userEmployeeRecord;
	}
	public void setUserEmployeeRecord(String userEmployeeRecord) {
		this.userEmployeeRecord = userEmployeeRecord;
		
	}
	@XmlTransient
	public String getUserEmployeeId() {
		return userEmployeeId;
	}
	public void setUserEmployeeId(String userEmployeeId) {
		this.userEmployeeId = userEmployeeId;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getHrRoles() {
		return hrRoles;
	}
	public void setHrRoles(String hrRoles) {
		this.hrRoles = hrRoles;
	}
	public String getCompanyLiveDate() {
		return companyLiveDate;
	}
	public void setCompanyLiveDate(String companyLiveDate) {
		this.companyLiveDate = companyLiveDate;
	}
	public String getProductLine() {
		return productLine;
	}
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	public String getCompanyStatus() {
		return companyStatus;
	}
	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}
	public String getIsAmbroseEE() {
		return isAmbroseEE;
	}
	public void setIsAmbroseEE(String isAmbroseEE) {
		this.isAmbroseEE = isAmbroseEE;
	}
}
