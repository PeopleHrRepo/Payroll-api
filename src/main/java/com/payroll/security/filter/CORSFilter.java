/**
 * Program Name: CORSFilter
 * 
 * Program Description / functionality: Common filter service
 * 
 * Modules Impacted: My Company
 *
 * Developer    Created             /Modified Date       Purpose
  *******************************************************************************
 * Neeraj S   16/02/2017            20/02/2017           
 * 
 * 
 * Associated Defects Raised :
 * 
 */
package com.payroll.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import com.google.gson.Gson;
import com.payroll.constant.NavigationURIConstants;
import com.payroll.utils.FilterUtil;
import com.payroll.utils.PayrollControllerUtils;
import com.payroll.utils.SecurityUtilities;
import com.ptg.payroll.domain.SessionUser;
import com.ptg.payroll.domain.ReturnResponse;


public class CORSFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(CORSFilter.class);
	private static final UrlPathHelper urlPathHelper = new UrlPathHelper();

	/**
	 *
	 * Purpose: This method is to implement cross origin resource sharing
	 * 
	 * @param Http
	 *            request
	 * @param Http
	 *            response
	 * @param filterChain
	 * 
	 * @see : Data
	 */
	//Uncomment this at time of deployment
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		boolean authSerive = false;
		LOGGER.debug("Filter begin");
		response.addHeader("Access-Control-Allow-Origin", "*");

		ReturnResponse returnResponse = new ReturnResponse();
		returnResponse.setErrorCode("401");
		returnResponse.setStatusCode(HttpStatus.UNAUTHORIZED + "");
		returnResponse.setData(null);
		returnResponse.setStatusMessage("Unauthorized : Full authentication is required to access this resource");
		if (FilterUtil.isHeaderEqual(request, FilterUtil.PTGTOKEN)&& FilterUtil.isHeaderValue(request, FilterUtil.PTGTOKEN)) {
			response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			response.addHeader("Access-Control-Allow-Headers", "Content-Type");
			response.addHeader("Access-Control-Max-Age", "1");
			String requestUri = urlPathHelper.getRequestUri(request);
			if (FilterUtil.getHeaderValue(request, FilterUtil.PTGTOKEN).equalsIgnoreCase(FilterUtil.PTGTOKEN_LOGIN) && requestUri.contains(NavigationURIConstants.LOGIN)) {
				authSerive = true;	
			} else if (FilterUtil.isHeaderEqual(request, FilterUtil.USERTOKEN)) {
				if (FilterUtil.isHeaderValue(request, FilterUtil.USERTOKEN)) {
					HttpSession httpSession = request.getSession();
					String userToken=FilterUtil.getHeaderValue(request, FilterUtil.USERTOKEN);
					try {
						userToken = SecurityUtilities.decrypt(userToken);
					} catch (Exception e) {
						e.getMessage();
					}
					
					
					String sessionId = httpSession.getId();
					if (userToken.equals(sessionId)) {
						SessionUser sessionUser = (SessionUser) httpSession.getAttribute(PayrollControllerUtils.USER_COMPANIES);
						if (sessionUser != null && sessionUser.getCompanyId().length() > 0
								&& sessionUser.getPersonId().length() > 0 && sessionUser.getEmployeeId().length() > 0)
							authSerive = true;
						else
							returnResponse.setStatusMessage("Invalid token :" + userToken);
					} else
						returnResponse.setStatusMessage("Unable to Identify usertoken :" + FilterUtil.getHeaderValue(request, FilterUtil.USERTOKEN));
				} 
			}
		}

		LOGGER.debug("Filter end");
		if (authSerive)
			filterChain.doFilter(request, response);
		else {
			response.setContentType(MediaType.APPLICATION_JSON);
			response.getWriter().print(new Gson().toJson(returnResponse));
		}

	}
	//Uncomment this at time of development
	/*
	 @Override
	  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	      throws ServletException, IOException {
	    response.addHeader("Access-Control-Allow-Origin", "*");
	    if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
	      LOGGER.debug("Sending Header....");
	      response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
	      response.addHeader("Access-Control-Allow-Headers", "Content-Type");
	      response.addHeader("Access-Control-Max-Age", "1");
	    }
	    filterChain.doFilter(request, response);
	  }
	  */
}
