/**
 * Program Name: PtgpayrollParseException 
 *                                                                 
 * Program Description / functionality: This is the Exception handling class for my company service   
 *                            
 * Modules Impacted: My Company
 *                                                                    
 *                                                                                                         
 * Developer    Created             /Modified Date       Purpose
  *******************************************************************************
 * Naresh     06/01/2017
 *
 * 
 * Associated Defects Raised : 
 *
 */ 

package com.payroll.exception;

public class PtgPayrollParseException extends PtgPayrollCheckedBaseException {

  private static final long serialVersionUID = 1L;

  public PtgPayrollParseException(String errorCode) {
    this.errorCode = errorCode;
  }

  public PtgPayrollParseException(String message, String errorCode) {
    super(message, errorCode);

  }



}
