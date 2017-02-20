package com.payroll.service;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import com.payroll.entity.PersonSecurity;
import com.payroll.exception.GatewayAuthenticationException;
import com.ptg.payroll.hrp.model.HRPSessionSignon;
import com.ptg.payroll.model.SignonResponse;
import com.ptg.payroll.model.UserCompanies;



@Service
public interface HRPTSessionSignonService  {
	public SignonResponse process(HttpServletRequest request) throws GatewayAuthenticationException;
	public boolean validateHrpSession(HttpServletRequest request);
	public  String getEmployeeName (String emplid);	
	public BigDecimal getNextAuditKey();
	public void updatePersonSecurity(PersonSecurity pers, HRPSessionSignon signon);
	public String getPersonIdByUserId(String employeeId);
	public UserCompanies getUserCompanies(HttpServletRequest request) throws GatewayAuthenticationException;
}