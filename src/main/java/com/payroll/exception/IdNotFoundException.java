/**
 * Program Name: IdNotFoundException
 * 
 * Program Description / functionality: This is the Exception handling class for my company service
 * 
 * Modules Impacted: My Company
 * 
 *  * Developer    Created             /Modified Date       Purpose
 * *******************************************************************************
 * Naresh     06/01/2017            
 * 
 *   
 * Associated Defects Raised :
 * 
 */

package com.payroll.exception;

public class IdNotFoundException extends PtgPayrollBaseException {

  private static final long serialVersionUID = 1L;

  public IdNotFoundException(String errorCode) {
    this.errorCode = errorCode;
  }

  public IdNotFoundException(String message, String errorCode) {
    super(message, errorCode);

  }

  public IdNotFoundException(String message, Throwable throwable) {
    super(message, throwable);
  }



}
