/**
 * Program Name: DataBaseException 
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
 * * Associated Defects Raised : 
 *
 */ 

package com.payroll.exception;

public class DataBaseException extends PtgPayrollBaseException {


  private static final long serialVersionUID = 1L;


  public DataBaseException(String message) {
    super(message);
  }

  public DataBaseException(String message, Throwable throwable) {
    super(message, throwable);
  }


}
