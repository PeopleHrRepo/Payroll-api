package com.payroll.utils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.ptg.payroll.domain.SessionUser;
import com.ptg.payroll.hrp.model.HRPHeaderConstants;



public class PayrollControllerUtils {
	
	public static final String USER_COMPANIES = "sessionUser";
	public static String getCurrentOperatorId(HttpSession session) {
	        return getUserCompanyFromSession(session).getEmployeeId();
	    }
	    
	 public   static String getCurrentPersonId(HttpSession session) {
	        return getUserCompanyFromSession(session).getPersonId();
	    }
	
	 public static SessionUser getUserCompanyFromSession(HttpSession session) {
	    	SessionUser ucs = (SessionUser) session.getAttribute(USER_COMPANIES);
	        return ucs;
	    }
	    
	public static String getHeaderCurrentOperatorId(HttpServletRequest request) {
        return request.getHeader(HRPHeaderConstants.EMPLOYEEID);
    }
    
   public static String getHeaderCurrentPersonId(HttpServletRequest request) {
        return request.getHeader(HRPHeaderConstants.PERSONID);
    }
   
   public static String getHeaderCompanyId(HttpServletRequest request) {
       return request.getHeader(HRPHeaderConstants.COMPANYID);
   }
   
	
}
