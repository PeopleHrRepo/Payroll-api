/**
 * Program Name: CommonConstants 
 *                                                                 
 * Program Description / functionality: This is constants interface for Common Constants
 *                            
 * Modules Impacted: Manage Common Constants
 *                                                                    
 * Tables affected:                                                                      
 *                                                                                                         
 * Developer    Created             /Modified Date       Purpose
  *******************************************************************************
 * Astha       06/02/2017                               
 * 
 * * Associated Defects Raised : 
 *
 */

package com.payroll.constant;

public interface CommonConstants {

	public static final String defaultDate = "2000-01-01";
	public static final String defaultTimestamp = "2000-01-01 00:00:00";
	public static final String NUMBER_REGEX = "^(0|[1-9][0-9]*)$";
	public static final String ALPHABET_REGEX = "^[a-zA-Z]*$";
	public static final String ALPHABET_REGEX_SPACE = "^[a-zA-Z ]*$";
	public static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
	public static final String STANDARD_HOURS_REGEX = "^[0-9]{1,6}([,.][0-9]{1,2})?$";
	public static final String ANNUAL_WAGES_REGEX = "^[0-9]{1,18}([,.][0-9]{1,3})?$";

}
