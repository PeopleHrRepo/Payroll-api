package com.payroll.exception;
import java.util.ArrayList;
import java.util.List;

public class ApplicationException extends Exception {
	private static final long serialVersionUID = 1L;
	List<ApplicationError> errors = new ArrayList<ApplicationError>();
	
	public List<ApplicationError> getErrors() {
		return errors;
	}
	public void setErrors(List<ApplicationError> errors) {
		this.errors = errors;
	}
	public ApplicationException(List<ApplicationError> errors) {
		super("Application Error occured:");
		this.errors = errors;
	} 

	public ApplicationException() {
		super("Application Error occured:");
	}
	
	public ApplicationException(String errorMessage) {
		super(errorMessage);
	}


	public void addApplicationError(ApplicationError appError){
		errors.add(appError);		
	}
}