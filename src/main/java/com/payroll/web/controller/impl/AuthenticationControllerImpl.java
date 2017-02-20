package com.payroll.web.controller.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payroll.constant.NavigationConstants;
import com.payroll.entity.SignIn;
import com.payroll.utils.CommonUtils;
import com.payroll.utils.PayrollControllerUtils;
import com.payroll.utils.SecurityUtilities;
import com.payroll.web.controller.AuthenticationController;
import com.ptg.payroll.domain.ReturnResponse;
import com.ptg.payroll.domain.SessionUser;

@RestController
public class AuthenticationControllerImpl 
	implements AuthenticationController{
		private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationControllerImpl.class);

		
		@Override
		public ReturnResponse doSignIn(HttpServletRequest request, HttpServletResponse response,@RequestBody SignIn signIn) throws Exception {
			LOGGER.debug("doSignIn method begin");
			boolean login=false;
			SessionUser sessionUser=null;
			if(signIn!=null){
				
				if(signIn.getEmployeeId()=="" && signIn.getEmployeeId().length()>0){
					return CommonUtils.getHttpStatusResponse(NavigationConstants.SUCCESS, HttpStatus.UNAUTHORIZED,"Invalid employeeId", null);
				}
				else if(signIn.getPassword()=="" && signIn.getPassword().length()>0){
					return CommonUtils.getHttpStatusResponse(NavigationConstants.SUCCESS, HttpStatus.UNAUTHORIZED,"Invalid Password", null);
				}
				else{
					sessionUser=isValidUser(signIn);
					if(sessionUser!=null){
						login=true;
					}
				}
			}
			
			
			if (login) {
					HttpSession httpSession=request.getSession();
					String token=httpSession.getId();
					sessionUser.setUserToken(SecurityUtilities.encrypt(token));
					httpSession.setAttribute(PayrollControllerUtils.USER_COMPANIES, sessionUser);
					writeCookie(sessionUser, response,token);
				return CommonUtils.getHttpStatusResponse(NavigationConstants.SUCCESS, HttpStatus.OK,sessionUser,null);
			} else
				return CommonUtils.getHttpStatusResponse(NavigationConstants.NO_RECORDS, HttpStatus.UNAUTHORIZED,null, "UNAUTHORIZED");
		}
		
		@Override
		public ReturnResponse dologOut(HttpServletRequest request, HttpServletResponse response,@RequestBody SignIn signIn) {
			boolean login=false;
			LOGGER.debug("dologOut method begin");
			if(signIn.getEmployeeId().equalsIgnoreCase(PayrollControllerUtils.getCurrentPersonId(request.getSession()))){
				login=true;
			}
			if (login) {
					HttpSession httpSession=request.getSession();
					httpSession.removeAttribute(PayrollControllerUtils.USER_COMPANIES);
					httpSession.invalidate();
					eraseCookie(request, response);

				return CommonUtils.getHttpStatusResponse(NavigationConstants.SUCCESS, HttpStatus.OK,"Log out", null);
			} else
				return CommonUtils.getHttpStatusResponse(NavigationConstants.FAILURE, HttpStatus.UNAUTHORIZED,null, null);
		}
		private void eraseCookie(HttpServletRequest request, HttpServletResponse response) {
			LOGGER.debug("eraseCookie method begin");
		    Cookie[] cookies = request.getCookies();
		    if (cookies != null)
		        for (int i = 0; i < cookies.length; i++) {
		            cookies[i].setValue("");
		            cookies[i].setPath("/");
		            cookies[i].setMaxAge(0);
		            response.addCookie(cookies[i]);
		        }
		}
		private void writeCookie(SessionUser sessionUser, HttpServletResponse response,String token) throws Exception {
			LOGGER.debug("writeCookie method begin");
			response.addCookie(new Cookie("countryId", sessionUser.getCompanyId()));
			response.addCookie(new Cookie("personId",sessionUser.getPersonId()));
			response.addCookie(new Cookie("user_token",SecurityUtilities.encrypt(token)));
			
		}
		
		private SessionUser isValidUser(SignIn signIn) {
			SessionUser isValidUser=null;
			
			if(signIn.getEmployeeId().equalsIgnoreCase("00001166721") && signIn.getPassword().equalsIgnoreCase("1234")){
				isValidUser=new SessionUser();
				isValidUser.setPersonId("00001166721");
				isValidUser.setEmployeeId("00001166721");
				isValidUser.setCompanyId("7NU");
			}
				
			else if(signIn.getEmployeeId().equalsIgnoreCase("00000003174") && signIn.getPassword().equalsIgnoreCase("1234"))
			{
				isValidUser=new SessionUser();
				isValidUser.setPersonId("00000003174");
				isValidUser.setEmployeeId("00000003174");
				isValidUser.setCompanyId("001");

				
			}
			
			
			return isValidUser;
		}
}
