/**
 *  Program Name: MyCompanyURIConstants 
 *                                                                 
 * Program Description / functionality: This is constant interface for my company controller 
 *       which contains all URI link for services.       
 *                        
 * Modules Impacted: My Company
 *                                                                    
 *                                                                                                         
 * Developer    Created             /Modified Date       Purpose
 *******************************************************************************
 * *******     17/12/2016 
 * 
 * * Associated Defects Raised : 
 *
 */

package com.payroll.constant;

public interface NavigationURIConstants {

	public static final String AUTH_SECURITY = "/v1/service";
	public static final String SECURITY = "/v1/api";
	public static final String GET_WELCOME_PAGE = "/welcome";
	public static final String GET_LANDING_PAGE = "/getPage";
	public static final String GET_LANDING_PAGE_STATE = "/getPageState/{countryId}";
	public static final String GET_LANDING_PAGE_CITY = "/getPageCity/{stateId}";
	public static final String GET_IMAGE= "/getImages/{imagePath:.+}";
	public static final String GET_IMAGES= "/getImages";
	public static final String GET_INDEX= "/getIndex";
	public static final String GET_SCHEDULE= "/getSchedule/{scheduleType}";
	public static final String GET_PDF= "/getPdf";
	public static final String LOGIN= "/login";
	public static final String LOGOUT= "/logout/{userName}";
	public static final String GET_SIGN_IN= "/signIn/{userName}/{password}";
	public static final String SIGN_IN_REFRESH= "/refreshUser/{userName}";
	public static final String GET_SIGNED= "/getSigned";
	
	//=-------
	public static final String GET_USER_DEPT_LOCATION_ACCESS="/payroll/userdeptlocationaccess/{company}";
	public static final String GET_EMPLOYEE_COMPANY="/employee/company";
	
	//PayRoll DashBoard Serivce Url
	public static final String GET_EARNING_CDOES= "/payroll/earningcodes/{company}";
	public static final String GET_TIMESHEETPAYROLL_EARNING_CDOES= "/timesheetPayroll/payroll/earningcodes/{company}";
	public static final String GET_EMPLOYEE_DATA_ENTRY= "/payroll/dataentry/{payGroupid}";
	public static final String GET_EMPLOYEE_NAME="/employeeName";
	public static final String GET_USER_COMPANIES="/timesheetPayroll/userCompanies";
	 public static final String GET_PAYROLL_DEADLINES="/payroll/payrollDeadline/company/{company}";
	 public static final String GET_PAYROLL_PAYGROUP_LIST_BY_COMPANY="/payroll/paygroupslist/{company}/{startDate}/{endDate}";
	 public static final String GET_PAYGROUPS_DATAENTRY = "/payroll/paygroups/{company}/{startDate}/{endDate}";
	
	//PayRoll Security
	public static final String GET_USER_SECURITY= "/employee/security/{company}";
}
