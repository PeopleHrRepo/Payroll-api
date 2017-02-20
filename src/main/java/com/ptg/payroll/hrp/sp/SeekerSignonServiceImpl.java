package com.ptg.payroll.hrp.sp;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.payroll.constant.PtgGatewayConstants;
import com.payroll.exception.GatewayAuthenticationException;
import com.payroll.exception.GatewayBackendException;
import com.ptg.payroll.hrp.model.BackendConfig;
import com.ptg.payroll.hrp.model.HRPSignonData;
import com.ptg.payroll.hrp.model.HRPSignonResponse;
import com.ptg.payroll.model.Signon;




/**
 * Service to handle signon request to HRP
 * 
 * @author sbhalodia
 * 
 */
@Service
public class SeekerSignonServiceImpl {
	private static final Logger log = LoggerFactory.getLogger(SeekerSignonServiceImpl.class);
	@Autowired
	private BackendConfig backendConfig;

	
	public void setBackendConfig(BackendConfig backendConfig) {
		this.backendConfig = backendConfig;
	}

	public HRPSignonResponse process(String userid, String password, int signType) {
		String hrpurl = backendConfig.getSeekerSignInUrl();
		if (password == null) {
			password = "";
		}
		if (userid.equalsIgnoreCase("12345678901")){
			log.error("12345678901 userid failed:");
			throw new GatewayAuthenticationException("Could not signon in HRP");
		}
		RestTemplate restTemplate = new RestTemplate();
		Document signxml = createHRPSignXML(userid, password, signType);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Content-Type", "text/xml; charset=UTF-8");
		requestHeaders.add("Accept", "text/xml, application/xml");
		HttpEntity requestEntity = new HttpEntity(signxml.getRootElement().asXML(), requestHeaders);
		String respTemp = restTemplate.postForObject(hrpurl, requestEntity, String.class);
		// hrp seeker bug
		respTemp = respTemp.trim();
		log.debug("response from HRP:" + respTemp);
		HRPSignonResponse result = new HRPSignonResponse();
		HRPSignonData data = new HRPSignonData();
		try {
			Document doc = DocumentHelper.parseText(respTemp);
			String response_status = doc.selectSingleNode("SignonResponse/data/response_status").getStringValue();			
			if ("success".equals(response_status)) {
				data.setUserid(doc.selectSingleNode("SignonResponse/data/userid").getStringValue());
				data.setUser_company(doc.selectSingleNode("SignonResponse/data/user_company").getStringValue());
				data.setUser_position(doc.selectSingleNode("SignonResponse/data/user_position").getStringValue());
				data.setTsessionid(doc.selectSingleNode("SignonResponse/data/tsessionid").getStringValue());
				data.setLogon_timestamp(doc.selectSingleNode("SignonResponse/data/logon_timestamp").getStringValue());
				result.setData(data);
			} else {
				log.error("Response from HRP:" + respTemp);
				throw new GatewayBackendException("Could not signon in HRP");
			}

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			log.error("Exception when trying to conver the response from HRP: " + respTemp, e);
			throw new GatewayBackendException("Could not signon in HRP");
		}
		// HRPSignonResponse result =
		// restTemplate.getForObject("http://localhost:8080/fxmobile/resources/hrpSignonResponse.xml",
		// HRPSignonResponse.class);
		log.debug("HRPSignonResponse: in service:"+result);
		return result;
	}

	private Document createHRPSignXML(String userid, String password, int signType) {
		// create request document
		Document document = DocumentHelper.createDocument();
		document.setName("SignonRequest");
		Element root = document.addElement("SignonRequest");
		Namespace sch = new Namespace("sch", PtgGatewayConstants.DEFAULT_MAIN_NAMESPACE);
		root.add(sch);
		// add server info
		Element header = root.addElement("header");
		// content_form == default - XML, range[XML,JSON]
		header.addElement("activity").addText("General");
		header.addElement("function").addText("API_Signon");
		Element data = root.addElement("data");
		data.addElement("pers_key").addText(userid);
		data.addElement("pers_key_type").addText(String.valueOf(signType));
		if (password == null) {
			data.addElement("pin");
		} else {
			data.addElement("pin").addText(password);
		}
		String reqStr = root.asXML();
		System.out.println("Request:" + reqStr);
		return document;
	}

	public HRPSignonResponse process(Signon signon) {
		return process(signon.getUsername(), signon.getPassword(), 2);
	}
}
