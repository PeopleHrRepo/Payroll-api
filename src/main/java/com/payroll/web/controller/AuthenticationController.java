package com.payroll.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.payroll.constant.NavigationURIConstants;
import com.payroll.entity.SignIn;
import com.ptg.payroll.domain.ReturnResponse;


@RequestMapping(value = NavigationURIConstants.SECURITY)
public interface AuthenticationController {

	@RequestMapping(value = NavigationURIConstants.LOGIN, method = RequestMethod.POST)
	ReturnResponse doSignIn(HttpServletRequest request,HttpServletResponse response,SignIn signIn) throws Exception;
	
	@RequestMapping(value = NavigationURIConstants.LOGOUT, method = RequestMethod.POST)
	ReturnResponse dologOut(HttpServletRequest request, HttpServletResponse response,SignIn signIn);
	
}
