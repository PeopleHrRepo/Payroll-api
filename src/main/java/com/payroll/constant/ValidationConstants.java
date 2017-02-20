/**
 * Program Name: ValidationConstants 
 *                                                                 
 * Program Description / functionality: This is constants interface for Validation Constants
 *                            
 * Modules Impacted: Manage Validation Constants
 *                                                                    
 * Tables affected:                                                                      
 *                                                                                                         
 * Developer    Created             /Modified Date       Purpose
  *******************************************************************************
 * Astha       25/12/2016                               
 * 
 * * Associated Defects Raised : 
 *
 */

package com.payroll.constant;

public interface ValidationConstants {

	public static final String PERSON_EMPID_NOT_NULL = "Employee Id should not be null";

	public static final String PERSON_EMPID_SIZE = "Employee Id size should be minimum 4 characters and maximum can be 11 characters and it should be start with emp";

	public static final String PERSON_EMAIL_ACCESS_TYPE_NOT_NULL = "Person Email access type should not be null";

	public static final String PERSON_EMAIL_ACCESS_TYPE_SIZE = "Person Email access type size should be minimum 1 and maximum 4 character";

	public static final String PERSON_EMAIL_NOT_NULL = "Person Email should not be null";

	public static final String PERSON_EMAIL_VALIDATION = "Enter valid Email ID";

	public static final String PERSON_EMAIL_FLAG_NOT_NULL = "Person Email flag value should not be null";

	public static final String PERSON_COMPANY_NOT_NULL = "Company Name field is required";

	public static final String PERSON_COMPANY_SIZE = "Company Name size can be min 1 and max 3 char";

	public static final String PERSON_NATIONAL_ID_SIZE = "National ID size can be max 20 char";

	public static final String PERSON_FORMOF_ADDRESS_NOT_NULL = "Form of Address field is required";

	public static final String PERSON_FIRST_NAME_NOT_NULL = "First Name field is required";

	public static final String PERSON_LAST_NAME_NOT_NULL = "Last Name field is required";

	public static final String PERSON_FIRST_NAME_SIZE = "First Name size can be max 30 char";

	public static final String PERSON_LAST_NAME_SIZE = "Last Name size can be max 30 char";

	public static final String PERSON_MIDDLE_NAME_SIZE = "Middle Name size can be max 30 char";

	public static final String PERSON_COUNTRY_ID_NOT_NULL = "Country field is required";

	public static final String PERSON_COUNTRY_ID_SIZE = "Country Size can be of max 3 char";

	public static final String PERSON_ADDRESS1_NOT_NULL = "Address1 Field is required";

	public static final String PERSON_ADDRESS2_NOT_NULL = "Address1 Field is required";

	public static final String PERSON_ADDRESS1_SIZE = "Address1 size can be of max 55 char";

	public static final String PERSON_ADDRESS2_SIZE = "Address2 size can be of max 55 char";
	
	public static final String PERSON_CITY_NOT_NULL = "City Field is required";

	public static final String PERSON_CITY_SIZE = "City size can be of max 30 char";
	
	public static final String PERSON_COUNTRY_NOT_NULL = "Country Field is required";

	public static final String PERSON_COUNTRY_SIZE = "Country size can be of max 3 char";
	
	public static final String PERSON_STATE_NOT_NULL = "State Field is required";

	public static final String PERSON_STATE_SIZE = "State size can be of max 6 char";
	
	public static final String PERSON_POSTAL_NOT_NULL = "Postal Code Field is required";

	public static final String PERSON_POSTAL_SIZE = "Postal Code size can be of max 12 char";

	public static final String PERSON_HOME_PHONE_SIZE = "Home Phone size can be of max 24 char";
	
	public static final String PERSON_HOME_EMAIL_NOT_NULL = "Home Email Field is required";

	public static final String PERSON_HOME_EMAIL_SIZE = "Home Email size can be of max 70 char";
	
	public static final String PERSON_GENDER_ID_NOT_NULL = "Gender Field is required";

	public static final String PERSON_GENDER_ID_SIZE = "Gender size can be of max 4 char";
	
	public static final String PERSON_ENTRY_REASON_NOT_NULL = "Entry Reason Field is required";

	public static final String PERSON_ENTRY_REASON_SIZE = "Entry Reason size can be of max 3 char";
	
	public static final String PERSON_EMP_TYPE_NOT_NULL = "Employee type Field is required";

	public static final String PERSON_EMP_TYPE_SIZE = "Employee type size should be of 1 char";

	public static final String PERSON_NATIONAL_ID_NOT_NULL = "National ID field is required";

	
	public static final String PERSON_FORMOF_ADDRESS_SIZE = "Form of Address size can be max 4 char";
	
	public static final String PERSON_MIDDLE_NAME_NOT_NULL = "Middle Name field is required";
	
	public static final String PERSON_EMP_REQUEST_TEMP_NOT_NULL = "Employee Reg/Temp indicator Field is required";
	public static final String PERSON_EMP_REQUEST_TEMP_SIZE = "Employee Reg/Temp size should be of 1 char";
	public static final String PERSON_EMP_CLASS_NOT_NULL = "Employee class Field is required";
	public static final String PERSON_EMP_CLASS_SIZE = "Employee class size can be max of 3 char";
	public static final String PERSON_EMP_ANNUAL_WAGES_NOT_NULL = "Employee Annual Wages Field is required";
	public static final String PERSON_BENIFIT_CLASS_NOT_NULL = "Employee benifit class Field is required";
	public static final String PERSON_BENIFIT_CLASS_SIZE = "Employee benifit class size can be max of 2 char";
	public static final String PERSON_STANDARD_HOURS_NOT_NULL = "Employee Standard Hours/Week Field is required";
	public static final String PERSON_BUSINESS_TITLE_NOT_NULL = "Employee Business Title Field is required";
	public static final String PERSON_BUSINESS_TITLE_SIZE = "Employee Business Title size can be max of 5 char";
	public static final String PERSON_JOB_CODE_NOT_NULL = "Employee Job Code Field is required";
	public static final String PERSON_JOB_CODE_SIZE = "Employee Job Code size can be max of 6 char";
	public static final String PERSON_FLSA_STATUS_NOT_NULL = "Employee FLSA Status Field is required";
	public static final String PERSON_FLSA_STATUS_SIZE = "Employee FLSA Status size should be of 1 char";
	public static final String PERSON_SUPERVISOR_NOT_NULL = "Employee Supervisor Field is required";
	public static final String PERSON_SUPERVISOR_SIZE = "Employee Supervisor size can be of max 11 char";
	public static final String PERSON_COMPENSATION_BASIC_NOT_NULL = "Employee Compensation Basis Field is required";
	public static final String PERSON_COMPENSATION_BASIC_SIZE = "Employee Compensation Basis size can be of max 5 char";
	public static final String PERSON_COMPENSATION_RATE_NOT_NULL = "Employee Compensation Rate Field is required";
	public static final String PERSON_DEPARTMENT_NOT_NULL = "Employee Department Field is required";
	public static final String PERSON_DEPARTMENT_SIZE = "Employee Department size can be of max 10 char";
	public static final String PERSON_WORK_LOCATION_NOT_NULL = "Employee Work Location Field is required";
	public static final String PERSON_WORK_LOCATION_SIZE = "Employee Work Location size can be of max 10 char";
	public static final String PERSON_PAY_GROUPS_NOT_NULL = "Employee Pay Groups Field is required";
	public static final String PERSON_PAY_GROUPS_SIZE = "Employee Pay Groups size can be of max 3 char";
	public static final String PERSON_MILITARY_STATUS_NOT_NULL = "Military Status Field is required";
	public static final String PERSON_MILITARY_STATUS_SIZE = "Military Status size should be of 1 char";
	public static final String PERSON_ALTERNATE_EMPID_NOT_NULL = "Alternate EmpId Field is required";
	public static final String PERSON_ALTERNATE_EMPID_SIZE = "Alternate EmpId size can be of max 11 char";
	public static final String PERSON_WORK_EMAIL_NOT_NULL = "Work Email Field is required";
	public static final String PERSON_WORK_EMAIL_SIZE = "Work Email size can be of max 70 char";
	public static final String PERSON_ENTRY_DATE_NOT_NULL = "Entry Date Field is required";
	public static final String PERSON_NUMBER_INVALID = "Field should contain only numbers";
	public static final String PERSON_ALPHABETS_INVALID = "Field should contain only alphabets";
	public static final String PERSON_DATE_INVALID = "Invalid Date Format. Hint-YYYY-MM-DD";
	public static final String PERSON_NUMBER_OR_DECIMAL_NUMBER_INVALID = "Field can be number or decimal number";
	public static final String EMPLOYEE_OPTION_PREFERENCE_SIZE = "Employee option preference size can be of max 30 char";
}
