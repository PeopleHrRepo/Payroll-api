/**
 * Program Name: NavigationValidator
 * 
 * Program Description / functionality: This is the Exception handling class for my company service   
 * 
 * Modules Impacted: My Company
 * 
 * Developer    Created             /Modified Date       Purpose
  *******************************************************************************
 * Naresh     06/01/2017 
 * 
 * Associated Defects Raised :
 */

package com.payroll.exception;

import org.apache.commons.lang3.StringUtils;

import com.payroll.security.filter.ConfigService;

public class NavigationValidator {

  static String onlyDigits = "[0-9]{7}([0-9]{4})?$";
  static String alphaNumericCompany = "[A-Z0-9]{3}";
 
  public static void validateEmployeeId(String empId) throws CommmonValidationException {

    if (empId == null || !empId.matches(onlyDigits)) {

      throw new CommmonValidationException("employeeId", ConfigService.getProperty("ERR-HELP-60002"));
    }

  }

 
  
  public static void validatePositionId(String positionId) throws CommmonValidationException {

    if (StringUtils.isBlank(positionId) || !positionId.matches(onlyDigits)) {
      throw new CommmonValidationException("positionId", ConfigService.getProperty("ERR-HELP-60033"));
    }

  }

  public static void validateCompanyId(String company) throws CommmonValidationException {

    if (StringUtils.isBlank(company) || !company.matches(alphaNumericCompany)) {
      throw new CommmonValidationException("company", ConfigService.getProperty("ERR-HELP-60035"));
    }

  }
  
}
