/**
 * Program Name: PersonServiceException 
 *                                                                 
 * Program Description / functionality: This is the Exception handling class for my Person service   
 *                            
 * Modules Impacted: My Person   
 *                                                                    
 *                                                                                                         
 * Developer    Created             /Modified Date       Purpose
 *******************************************************************************
 * Naresh     06/01/2017
 * Naresh     06/01/2017           Implemented code changes as per review comments
 * 
 * Associated Defects Raised : 
 *
 */

package com.payroll.exception;

public class ProfileServiceException extends PtgPayrollBaseException {

    private static final long serialVersionUID = 1L;

    public ProfileServiceException(String message, String errorCode) {
        super(message, errorCode);

    }

    public ProfileServiceException(String errorCode) {
        super.errorCode = errorCode;

    }

    public ProfileServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
