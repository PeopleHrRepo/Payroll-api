package com.ptg.payroll.model;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonUnwrapped;



/**
 * handles Signon response
 * we send the token and also the companies this user is associated with
 * 
 * @author Neeraj
 *
 */
@XmlRootElement(name="signonResponse")
public class SignonResponse {
	/**
	 * Authorization token to be used for subsequent requests
	 */
	private String userToken;
	/**
	 * Companies the authenticated user is related to
	 */
	
	private UserCompanies companies;

	public SignonResponse() {}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	
	@JsonUnwrapped
	public UserCompanies getCompanies() {
		return companies;
	}

	public void setCompanies(UserCompanies companies) {
		this.companies = companies;
	}
	
	
	
	

}