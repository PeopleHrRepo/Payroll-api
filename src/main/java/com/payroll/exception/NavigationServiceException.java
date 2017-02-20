/**
 * Program Name: NavigationServiceException
 * 
 * Program Description / functionality: This is the Exception handling class for my company service
 * 
 * Modules Impacted: My Company
 * 
 * Developer    Created             /Modified Date       Purpose
 * *******************************************************************************
 * Naresh     06/01/2017               
 * 
 * Associated Defects Raised :
 * 
 */

package com.payroll.exception;

public class NavigationServiceException extends PtgPayrollBaseException {

  private static final long serialVersionUID = 1L;

  public NavigationServiceException(String message, String errorCode) {
    super(message, errorCode);

  }

  public NavigationServiceException(String errorCode) {
    super.errorCode = errorCode;

  }

  public NavigationServiceException(String message, Throwable throwable) {
    super(message, throwable);
  }

}
