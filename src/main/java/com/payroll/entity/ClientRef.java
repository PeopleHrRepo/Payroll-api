package com.payroll.entity;

import java.util.List;

public class ClientRef extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2917677196128122394L;
	private String clientId;
	private String clientname;
	private String country;
	private String address1;
	private String address2;
	private String location;
	private String companyRefId;
	private List<PayGoupRef> payGoupRefs;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCompanyRefId() {
		return companyRefId;
	}
	public void setCompanyRefId(String companyRefId) {
		this.companyRefId = companyRefId;
	}
	public List<PayGoupRef> getPayGoupRefs() {
		return payGoupRefs;
	}
	public void setPayGoupRefs(List<PayGoupRef> payGoupRefs) {
		this.payGoupRefs = payGoupRefs;
	}
	
}
