/**
 * Program Name: ExceptionConstants
 * 
 * Program Description / functionality:This interface is to declare constant values required in the application
 * 
 * Modules Impacted: My Company
 * 
 *  * Developer    Created             /Modified Date       Purpose
  *******************************************************************************
 * Astha       06/02/2017                          
 * 
 * * Associated Defects Raised : 
 *
 */

package com.payroll.constant;


public enum ExceptionConstants {

  ERR_ID_NOT_FOUND("ERR-MYCOMP-60002"), 
  ERR_INVALID_DATE("ERR-MYCOMP-60004"), 
  ERR_DATABASE_DOWN("ERR-MYCOMP-60010"),
  ERR_GETORGCHART("ERR-ORGCHART-60020"),
  ERR_UPDATEPASSWORD("ERR-EO-10021"),
  ERR_INSERTPATIENTDETAILS("ERR-EO-10022"),
  ERR_GETMYSUPPORTSTATS("ERR-MYSUPPORT-60030"),
  ERR_GETACTIONSUNASSIGNED("ERR-ACTIONASSIGNED-60040"),
  ERR_GETACTIONSASSIGNEDTOSOMEONE("ERR-ACTIONSASSIGNEDTOSOMEONE-60041"),
  ERR_GETWORKINBOXAPPROVE("ERR-ORGCHART-60025"),
  ERR_GETACTIONSASSIGNEDTOYOU("ERR-ACTIONSASSIGNEDTOYOU-60042");
 
  private String value;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  private ExceptionConstants(String value) {
    this.value = value;
  }
};
