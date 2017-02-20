/**
 * Program Name: ReturnResponse
 * 
 * Program Description / functionality: This is the domain class for my company service
 * 
 * Modules Impacted: My Company
 * 
 * 
 * * Developer Created /Modified Date Purpose
 *******************************************************************************
 * Bhargava 17/08/2015
 * 
 * 
 * Associated Defects Raised :
 * 
 */

package com.ptg.payroll.domain;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class ReturnResponse {

  private String errorCode;
  private Object data;
  private String statusCode;
  private String statusMessage;

  @JsonProperty(value = "_statusCode")
  public String getStatusCode() {
    return statusCode;
  }

  public String getErrorCode() {
    return errorCode;
  }

  @JsonProperty(value = "_statusMessage")
  public String getStatusMessage() {
    return statusMessage;
  }

  public Object getData() {
    return data;
  }

  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }

  public void setData(Object data) {
    this.data = data;
  }

}
