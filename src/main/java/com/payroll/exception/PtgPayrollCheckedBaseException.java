/**
 * Program Name: PtgpayrollCheckedBaseException 
 *                                                                 
 * Program Description / functionality: This is the Exception handling class for my company service   
 *                            
 * Modules Impacted: My Company
 *                                                                    
 * Developer    Created             /Modified Date       Purpose
 * *******************************************************************************
 * Naresh     06/01/2017           
 * 
 *   
 * Associated Defects Raised : 
 *                                                                                                         
 */

package com.payroll.exception;

import com.payroll.constant.ExceptionConstants;


public class PtgPayrollCheckedBaseException extends Exception {

  /**
   * Generated UID
   */
  private static final long serialVersionUID = 6631843575648275651L;

  protected String errorCode;

  protected Object[] messageParams;

  protected ExceptionConstants exceptionConstants;

  public PtgPayrollCheckedBaseException() {
    super();
  }

  public PtgPayrollCheckedBaseException(String msg) {
    super(msg);
  }

  public PtgPayrollCheckedBaseException(String msg, String errorCode) {
    super(msg);
    this.errorCode = errorCode;

  }

  public PtgPayrollCheckedBaseException(Throwable e) {
    super(e);
  }

  public PtgPayrollCheckedBaseException(String msg, Throwable e) {
    super(msg, e);
  }



  public static String getStack(Throwable e) {
    StackTraceElement[] st = Thread.currentThread().getStackTrace();
    return st[4].getClassName() + "." + st[4].getMethodName() + "():" + st[4].getLineNumber() + " ->" + e.getMessage();
  }

  public static String getStack() {
    StackTraceElement[] st = Thread.currentThread().getStackTrace();
    return st[4].getClassName() + "." + st[4].getMethodName() + "():" + st[4].getLineNumber() + " -> ";
  }


  public String getErrorCode() {
    return this.errorCode;
  }


  /**
   * @return Returns the messageParams.
   */
  public Object[] getMessageParams() {
    return messageParams;
  }

  public ExceptionConstants getExceptionConstants() {
    return exceptionConstants;
  }

}
