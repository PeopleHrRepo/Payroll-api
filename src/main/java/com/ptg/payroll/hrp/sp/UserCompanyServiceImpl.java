package com.ptg.payroll.hrp.sp;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payroll.exception.GatewayBackendException;
import com.ptg.payroll.hrp.model.BackendConfig;
import com.ptg.payroll.hrp.model.HRPSignonData;
import com.ptg.payroll.hrp.model.HRPSignonResponse;
import com.ptg.payroll.hrp.model.HRPUserCompanies;
import com.ptg.payroll.hrp.service.GetUserCompanies;
import com.ptg.payroll.hrp.service.UserCompanyService;




@Service
public class UserCompanyServiceImpl implements UserCompanyService  {
	private static final Logger log = LoggerFactory.getLogger(UserCompanyServiceImpl.class);
	@Autowired
	private HttpClient httpclient;
	@Autowired
	private BackendConfig backendConfig;
	@Autowired
	private GetUserCompanies getUserCompanies;
	
	
	
	public void setHttpclient(HttpClient httpclient) {
		this.httpclient = httpclient;
	}
	public void setBackendConfig(BackendConfig backendConfig) {
		this.backendConfig = backendConfig;
	}
	public void setGetUserCompanies(GetUserCompanies getUserCompanies) {
		this.getUserCompanies = getUserCompanies;
	}
	/* (non-Javadoc)
	 * @see com.ptg.payroll.hrp.service.UserCompanyService#process(java.lang.String)
	 */
	@Override
	public HRPUserCompanies process(String personId){
		return getUserCompanies.execute(personId);
		
	}
	/* (non-Javadoc)
	 * @see com.ptg.payroll.hrp.service.UserCompanyService#process(com.ptg.payroll.hrp.model.HRPSignonResponse)
	 */
	@Override
	public HRPUserCompanies process(HRPSignonResponse hrpResponse){
		String hrpCompaniesUrl = backendConfig.getUserCompaniesUrl();
		HRPUserCompanies returnval = null;
		HRPSignonData hd =  hrpResponse.getData();
		PostMethod post = new PostMethod(hrpCompaniesUrl);
		// add cookie and header
		handleHeaderAndCookies(post, hd);
		try {
			 	httpclient.executeMethod(post);
			 	log.debug("Data from ASP:"+post.getResponseBodyAsString());
			 	returnval =  InputStreamJsonToObject(post.getResponseBodyAsStream());
			 	
		} catch (HttpException e) {
			log.error("HTTP error while connecting to HRP-ASP");
			throw new GatewayBackendException(e.getMessage());			
		} catch (IOException e) {
			log.error("Connection error occured while connecting to HRP-ASP");
			throw new GatewayBackendException(e.getMessage());			
		}
		
		return returnval;
		
		
	}
	private HRPUserCompanies InputStreamJsonToObject(InputStream is){
		ObjectMapper mapper = new ObjectMapper(); 
		HRPUserCompanies mycompanies = null;
		try {
			mycompanies = mapper.readValue(is, HRPUserCompanies.class);
		} catch (JsonParseException e) {
			log.error("Error while parsing response from HRP-ASP");
			throw new GatewayBackendException(e.getMessage());			
		} catch (JsonMappingException e) {
			log.error("Mapping error for HRPMyself");
			throw new GatewayBackendException(e.getMessage());			
		} catch (IOException e) {
			log.error("Connection error occured while connecting to HRP-ASP");
			throw new GatewayBackendException(e.getMessage());			
		}
		return mycompanies;
	}
	
	private void handleHeaderAndCookies(PostMethod post, HRPSignonData sd) {
		post.getParams().setCookiePolicy(CookiePolicy.RFC_2109);
		String userId = sd.getUserid();
		String user_company = sd.getUser_company();
		String user_position = sd.getUser_position();
		String tsessionid = sd.getTsessionid();
		StringBuilder sb = new StringBuilder();
		sb.append("Session=SessionID=").append(tsessionid).append("&");
		sb.append("User_Personid=").append(userId).append("&");
		sb.append("User_Position=").append(user_position).append("&");
		sb.append("User_Empl_Rcd=").append("0").append("&");
		sb.append("User_Company=").append(user_company).append("&");
		sb.append("User_Pf_Client=").append("0000");
		String cookievalue = sb.toString();
		log.debug("Sending cookie: " + cookievalue);
		post.addRequestHeader("COOKIE", cookievalue);
		post.addRequestHeader("TRINET_API", "true");

	}
}
