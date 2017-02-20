package com.payroll.service.impl;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payroll.dao.PsNamesSecurityDao;
import com.payroll.entity.PersonSecurity;
import com.payroll.entity.ps.NamedQueries.NewHireQueryEnum;
import com.payroll.entity.ps.NamedQueries.QueryEnum;
import com.payroll.exception.GatewayAuthenticationException;
import com.payroll.service.HRPTSessionSignonService;
import com.payroll.utils.PayrollControllerUtils;
import com.ptg.payroll.hrp.model.BackendConfig;
import com.ptg.payroll.hrp.model.HRPSessionSignon;
import com.ptg.payroll.hrp.model.HRPUserCompanies;
import com.ptg.payroll.hrp.service.UserCompanyService;
import com.ptg.payroll.model.SignonResponse;
import com.ptg.payroll.model.UserCompanies;


@Service
public class HRPTSessionSignonServiceImpl implements HRPTSessionSignonService {
	@Autowired
	BackendConfig backendConfig;

	@Autowired
	UserCompanyService userCompanyService;
	@Autowired
	private ConversionService conversionService;
	@Autowired
	private PsNamesSecurityDao psNamesSecurityDao;

	@Autowired
	private SessionFactory  hrp_sessionFactory;
	
	

	public void setHrp_sessionFactory(SessionFactory hrp_sessionFactory) {
		this.hrp_sessionFactory = hrp_sessionFactory;
	}
	public void setBackendConfig(BackendConfig backendConfig) {
		this.backendConfig = backendConfig;
	}

	public void setUserCompanyService(UserCompanyService userCompanyService) {
		this.userCompanyService = userCompanyService;
	}

	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	


	public void setPsNamesSecurityDao(PsNamesSecurityDao psNamesSecurityDao) {
		this.psNamesSecurityDao = psNamesSecurityDao;
	}




	private static final Logger log = LoggerFactory.getLogger(HRPTSessionSignonServiceImpl.class);

	@Override
	public SignonResponse process(HttpServletRequest request) throws GatewayAuthenticationException {
		//String tsessionId = (String)session.getAttribute(HRPHeaderConstants.SESSION_VALUE);
		String personId  = PayrollControllerUtils.getCurrentPersonId(request.getSession());
		String companyId = PayrollControllerUtils.getUserCompanyFromSession(request.getSession()).getCompanyId();
		
		List<PersonSecurity> personSecurityRecords = psNamesSecurityDao.findPersonSecurityByPersonIdTsessionId(personId, null);
		
	 	if (personSecurityRecords == null || personSecurityRecords.size() != 1){	 		
	 		//session.invalidate();
	 		throw new GatewayAuthenticationException("Couldn't verify tsession in HRP");
	 	}
	 	    // call the userscompanies service
	 	
	 	    HRPUserCompanies hrpCompanies = userCompanyService.process(personId);
			// now we should create the response object
			SignonResponse signonResponse = new SignonResponse();
			// now convert the HRPCompanies to UserCompanies
			UserCompanies uc = conversionService.convert(hrpCompanies, UserCompanies.class);
			//set the current company to the one got in seeker
			uc.setCurrentCompanyId(companyId);
			// now we can set the companies in signonResponse
			signonResponse.setCompanies(uc);

			// in controller we need to put the gateway session
			return signonResponse;

		

	}
	
	@Override
	public boolean validateHrpSession(HttpServletRequest request) {

		//String tsessionId = (String)session.getAttribute(HRPHeaderConstants.SESSION_VALUE);
		String personId  = PayrollControllerUtils.getCurrentPersonId(request.getSession());
		
		boolean isHrpSessionValaid = false;

		List<PersonSecurity> personSecurityRecords = psNamesSecurityDao.findPersonSecurityByPersonIdTsessionId(personId, null);
		
	 	if (personSecurityRecords == null || personSecurityRecords.size() != 1){	 		
	 		log.info("Couldn't verify tsession in HRP");
	 		isHrpSessionValaid = false;
	 		//session.invalidate();
	 	}else {
			isHrpSessionValaid = true;
		}
		
		log.info("HrpSession validation status = "+isHrpSessionValaid);
		return isHrpSessionValaid;

	}
	
	@Override	
	public String getEmployeeName(String emplid) {
		String emplName = null;
		List<String> resultList = psNamesSecurityDao.getEmployeeName(emplid);
		if (resultList != null && resultList.size() > 0) {
			emplName = (String) resultList.get(0);
		}

		return emplName;
	}
	
	@Override
	public String getPersonIdByUserId(String employeeId){
        String personId = "";
        try {
            Query query = hrp_sessionFactory.getCurrentSession().getNamedQuery(NewHireQueryEnum.NEW_HIRE_SELECT_PERSONID_BY_EMPLID.getKey());
            query.setParameter("employeeId", employeeId);
            personId = (String) query.uniqueResult();
        }catch(Exception e){
        	e.printStackTrace();
        }    
        return personId;
    }
	
	@Override
	public BigDecimal getNextAuditKey(){		
		BigDecimal auditKey = null;		
		if (hrp_sessionFactory.getCurrentSession()==null)
					return null;
		try{	
			Query query = hrp_sessionFactory.getCurrentSession().getNamedQuery(QueryEnum.NEWHIRE_HRP_AUDIT_KEY_NEXTVAL.getKey());
			auditKey = (BigDecimal) query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
		}					
		return auditKey;			    
	}
	
	@Override
	@Transactional(value="hrpTransactionManager")
    public void updatePersonSecurity(PersonSecurity pers, HRPSessionSignon signon) {
		List<PersonSecurity> personSecurityRecords = psNamesSecurityDao.findPersonSecurityByPersonIdTsessionId(signon.getPersonId(), signon.gettSessionId());
	 	if (personSecurityRecords == null || personSecurityRecords.size() != 1){
	        pers.setId(signon.getPersonId());
	        pers.setEffDt(new Date());
	        pers.setEndDt(getEndDate());
	        pers.setFailCount(0);
	        pers.setTsessionId(signon.gettSessionId());
	        pers.setLoginTimeStamp(new Date());
	        pers.setSessionActive("Y");
	        pers.setSetByAdmin("N");
	        pers.setUserLevel(0);
	        pers.setUpdateKey(getNextAuditKey());
	        pers.setAuditKey(getNextAuditKey());
	        
	        hrp_sessionFactory.getCurrentSession().merge(pers);
	 	}
    }
	
	private static Date getEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2099, Calendar.DECEMBER, 31);
        return calendar.getTime();
    }
	
	@Override
	public UserCompanies getUserCompanies(HttpServletRequest request) throws GatewayAuthenticationException {
		// call the userscompanies service
		String personId  = PayrollControllerUtils.getCurrentPersonId(request.getSession());
		String companyId = PayrollControllerUtils.getUserCompanyFromSession(request.getSession()).getCompanyId();
 	    HRPUserCompanies hrpCompanies = userCompanyService.process(personId);
		// now convert the HRPCompanies to UserCompanies
		UserCompanies uc = conversionService.convert(hrpCompanies, UserCompanies.class);
		//set the current company to the one got in seeker
		uc.setCurrentCompanyId(companyId);
		return uc;
	}

}