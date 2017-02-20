/**
 * Program Name: HomeDaoException 
 *                                                                 
 * Program Description / functionality: This is the Exception handling class for my Person service   
 *                            
 * Modules Impacted: My Person   
 *                                                                    
 *                                                                                                         
 * Developer    Created             /Modified Date       Purpose
  *******************************************************************************
 * Naresh     06/01/2017
 * Naresh     06/01/2017          Implemented code changes as per review comments
 * 
 * Associated Defects Raised : 
 *
 */
package com.payroll.exception;

public class HomeDaoException extends RuntimeException {

  private static final long serialVersionUID = -9012898912300932547L;

  public HomeDaoException(String message) {
    super(message);
  }

  public HomeDaoException(String message, Throwable throwable) {
    super(message, throwable);
  }


}
