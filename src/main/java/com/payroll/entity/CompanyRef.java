package com.payroll.entity;

import java.util.List;

public class CompanyRef  extends BaseEntity {

	private static final long serialVersionUID = 6075187656363558296L;
	private String companyId;
	private String country;
	private String name;
	private String address1;
	private String address2;
	private String location;
	private List<ClientRef> clientRefs;
	public CompanyRef() {
	}
	
	
	public CompanyRef(String companyId, String country, String name, String address1, String address2,
			String location) {
		super();
		this.companyId = companyId;
		this.country = country;
		this.name = name;
		this.address1 = address1;
		this.address2 = address2;
		this.location = location;
	}

	

	public CompanyRef(String companyId, String country, String name, String address1, String address2, String location,
			List<ClientRef> clientRefs) {
		super();
		this.companyId = companyId;
		this.country = country;
		this.name = name;
		this.address1 = address1;
		this.address2 = address2;
		this.location = location;
		this.clientRefs = clientRefs;
	}


	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public List<ClientRef> getClientRefs() {
		return clientRefs;
	}
	public void setClientRefs(List<ClientRef> clientRefs) {
		this.clientRefs = clientRefs;
	} 
	
	
	

}
