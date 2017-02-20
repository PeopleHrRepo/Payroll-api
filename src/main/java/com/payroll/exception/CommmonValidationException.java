/**
 * Program Name: CommmonValidationException 
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

import java.util.HashMap;
import java.util.Map;

public class CommmonValidationException extends PtgPayrollBaseException {

  private static final long serialVersionUID = 1L;

  Map<String, String> erorMap = new HashMap<String, String>();
  String fieldName;
  String message;

  public CommmonValidationException(String fieldName, String message) {
    erorMap.put(fieldName, message);

  }

  public CommmonValidationException(Map<String, String> errorMap) {
    this.erorMap = errorMap;
  }

  public Map<String, String> getErorMap() {
    return erorMap;
  }

  public void setErorMap(Map<String, String> erorMap) {
    this.erorMap = erorMap;
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
