/**
 * Program Name: ValidationError
 * 
 * Program Description / functionality: This is the domain class for my company service
 * 
 * Modules Impacted: My Company
 * 
 * Tables affected:
 *
 * Developer Created /Modified Date Purpose
 * ******************************************************************************* Bhargava
 * 17/08/2015
 * 
 * 
 * Associated Defects Raised :
 * 
 */

package com.ptg.payroll.domain;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class ValidationError {
  private List<FieldError> fieldErrors = new ArrayList<FieldError>();

  private int status;

  public ValidationError() {

  }

  public void addFieldError(String path, String message) {
    FieldError error = new FieldError(path, message);
    fieldErrors.add(error);
  }

  public int getStatus() {
    return status;
  }

  public List<FieldError> getFieldErrors() {
    return fieldErrors;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public void setFieldErrors(List<FieldError> fieldErrors) {
    this.fieldErrors = fieldErrors;
  }

}
