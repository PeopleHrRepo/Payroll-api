package com.payroll.exception;
public class ApplicationError {
	String errorCode;
	String errorMessage;
	String fieldName;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public ApplicationError(){
		super();
	}
	public ApplicationError(String errorCode, String errorMessage, String fieldName) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.fieldName = fieldName;
	}

}
