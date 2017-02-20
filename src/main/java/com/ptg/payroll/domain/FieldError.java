/**
 * Program Name: FiledError
 * 
 * Program Description / functionality: This is the domain class for my company service
 * 
 * Modules Impacted: My Company
 * 
 * Tables affected:
 * 
 * Developer Created Modified Date Purpose
 * ******************************************************************************* Bhargava
 * 17/08/2015
 * 
 * 
 * Associated Defects Raised :
 * 
 */

package com.ptg.payroll.domain;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class FieldError {

  private String field;
  private String message;

  public FieldError(String field, String message) {
    this.field = field;
    this.message = message;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
