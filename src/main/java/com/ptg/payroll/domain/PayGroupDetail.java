package com.ptg.payroll.domain;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.payroll.utils.CustomJsonDateDeserializer;
import com.payroll.utils.CustomJsonDateSerializer;

@JsonInclude(Include.NON_NULL)
public class PayGroupDetail {
    

    private String payGroupDesc;
    private String location;
    private String departmentId;
    private Date checkDate;
    private String blackoutFlag;
    private String payrollName;
    private String brandName;
    private String dataEntryOption;
    private String operatorId;
    private String operatorName;
    private String paymentMethod;
    private Date lastUpdatedDate;
    private String displayRunTime;
    private String displayPayEndDate;
    private boolean hasPayrollError;
    private String errorMessage;
    private String payrollStatusDesc;
    private String currentEngineStep;
    private String companyDesc;
    private String defaultRegEarnCode;
    
    
    
    
    
    /* Auto-Generated Code below. Please don't hand edit them. -- Starts */
    public PayGroupDetail() {

    }
    
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getBlackoutFlag() {
		return blackoutFlag;
	}

	public void setBlackoutFlag(String blackoutFlag) {
		this.blackoutFlag = blackoutFlag;
	}

	public String getPayrollName() {
		return payrollName;
	}

	public void setPayrollName(String payrollName) {
		this.payrollName = payrollName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@JsonSerialize(using = CustomJsonDateSerializer.class)
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	/* All auto generated code -- Ends */

	public String getDisplayRunTime() {
		return displayRunTime;
	}

	public void setDisplayRunTime(String displayRunTime) {
		this.displayRunTime = displayRunTime;
	}

	public String getDisplayPayEndDate() {
		return displayPayEndDate;
	}

	public void setDisplayPayEndDate(String displayPayEndDate) {
		this.displayPayEndDate = displayPayEndDate;
	}

	public String getPayGroupDesc() {
		return payGroupDesc;
	}
	
	public void setPayGroupDesc(String payGroupDesc) {
		this.payGroupDesc = payGroupDesc;
	}

	public String getDataEntryOption() {
		return dataEntryOption;
	}

	public void setDataEntryOption(String dataEntryOption) {
		this.dataEntryOption = dataEntryOption;
	}

	public boolean isHasPayrollError() {
		return hasPayrollError;
	}

	public void setHasPayrollError(boolean hasPayrollError) {
		this.hasPayrollError = hasPayrollError;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getPayrollStatusDesc() {
		return payrollStatusDesc;
	}

	public void setPayrollStatusDesc(String payrollStatusDesc) {
		this.payrollStatusDesc = payrollStatusDesc;
	}

	public String getCurrentEngineStep() {
		return currentEngineStep;
	}

	public void setCurrentEngineStep(String currentEngineStep) {
		this.currentEngineStep = currentEngineStep;
	}

	public String getCompanyDesc() {
		return companyDesc;
	}

	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}

	public String getDefaultRegEarnCode() {
		return defaultRegEarnCode;
	}

	public void setDefaultRegEarnCode(String defaultRegEarnCode) {
		this.defaultRegEarnCode = defaultRegEarnCode;
	}
	
}