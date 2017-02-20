package com.payroll.utils;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class FilterUtil {
	public final static String PTGTOKEN="ptg_token";
	public final static String USERTOKEN="user_token";
	public final static String PTGTOKEN_LOGIN="login";
	public final static String PTGTOKEN_ACCESS_URI="access_token";
	

	public static boolean isHeaderValue(HttpServletRequest request,String headerName){
		boolean isHeaderValue=false;
		String headerValue=request.getHeader(headerName);
		if(headerValue!=null)
			isHeaderValue=true;
		return isHeaderValue;
	}
	public static String getHeaderValue(HttpServletRequest request,String headerName){
		return request.getHeader(headerName);
	}
	
	
	public static boolean isHeaderEqual(HttpServletRequest request,String header){
		boolean isEqual=false;
		List<String> headers=new ArrayList<String>();
		Enumeration<String> headerNames = request.getHeaderNames();
	    while(headerNames.hasMoreElements())
	    {
	      headers.add((String)headerNames.nextElement());
	    }
	    
	    if(headers.contains(header)){
	    	isEqual=true;
	    }
		return isEqual;
	}
	
	}