/**
 * Program Name: DataAccessException 
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
 * Associated Defects Raised : 
 *
 */ 

package com.payroll.exception;

public class DataAccessException extends PtgPayrollBaseException {

  /**
   * Generated UID
   */
  private static final long serialVersionUID = 7369552997038420811L;

  public DataAccessException(String message) {
    super(message);
  }

  public DataAccessException(String message, Throwable throwable) {
    super(message, throwable);
  }


}
