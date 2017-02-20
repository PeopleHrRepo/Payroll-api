package com.payroll.service.impl;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.payroll.service.SignonService;
import com.ptg.payroll.hrp.model.HRPHeaderConstants;
import com.ptg.payroll.hrp.model.HRPSignonResponse;
import com.ptg.payroll.hrp.model.HRPUserCompanies;
import com.ptg.payroll.hrp.service.UserCompanyService;
import com.ptg.payroll.hrp.sp.SeekerSignonServiceImpl;
import com.ptg.payroll.model.SignonResponse;
import com.ptg.payroll.model.UserCompanies;



/**
 * Service to handle signon request to HRP
 * We make two calls to hrp
 * 1. to seeker to sigon
 * 2. to asp to get companies associated with the user
 * 
 * @author sbhalodia
 * 
 */
@Service
public class HRPSignonServiceImpl implements SignonService{
	private static final Logger log = LoggerFactory.getLogger(HRPSignonServiceImpl.class);
	@Autowired 
	private SeekerSignonServiceImpl seekerSignonService;
	@Autowired
	private UserCompanyService userCompanyService;
	@Autowired
	private ConversionService conversionService;
	
	
	

	public void setSeekerSignonService(SeekerSignonServiceImpl seekerSignonService) {
		this.seekerSignonService = seekerSignonService;
	}

	public void setUserCompanyService(UserCompanyService userCompanyService) {
		this.userCompanyService = userCompanyService;
	}
	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}
	public SignonResponse process(HttpSession session) {
		//get the signon parameters
		String userId = ((String)session.getAttribute(HRPHeaderConstants.USERID_VALUE));
		String password = ((String)session.getAttribute(HRPHeaderConstants.USERPASSWORD));
		
		// now call the seeker signon
		HRPSignonResponse hrpSignonResponse = seekerSignonService.process(userId, password, HRPHeaderConstants.SIGNON_TYPE);
		log.debug("completed seeker signon in HRP");
		
		//make call to get usercompanies
		HRPUserCompanies hrpCompanies = userCompanyService.process(hrpSignonResponse);
		log.debug("completed companies request in HRP");
		
		//now that we have made our calls need preserve tsessionId and logon_timestamp
		session.setAttribute(HRPHeaderConstants.SESSION_VALUE, hrpSignonResponse.getData().getTsessionid());
		session.setAttribute(HRPHeaderConstants.LOGON_TIMESTAMP_VALUE, hrpSignonResponse.getData().getLogon_timestamp());

		// now we should create the response object
		SignonResponse signonResponse = new SignonResponse();
		// now convert the HRPCompanies to UserCompanies
		UserCompanies uc = conversionService.convert(hrpCompanies, UserCompanies.class);
		//set the current company to the one got in seeker
		uc.setCurrentCompanyId(hrpSignonResponse.getData().getUser_company());
		// now we can set the companies in signonResponse
		signonResponse.setCompanies(uc);

		// in controller we need to put the gateway session
		return signonResponse;
	}

	
}