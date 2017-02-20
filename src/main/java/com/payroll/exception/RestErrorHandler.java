/**
 * Program Name: RestErrorHandler 
 *                                                                 
 * Program Description / functionality: This is the Exception handling class for my company service   
 *                            
 * Modules Impacted: My Company  
 *                                                                    
 *                                                                                                         
 * Developer    Created             /Modified Date       Purpose
 * *******************************************************************************
 * Naresh     06/01/2017
 *
 * 
 * * Associated Defects Raised :               
 *
 */

package com.payroll.exception;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.payroll.security.filter.ConfigService;
import com.payroll.utils.CommonUtils;
import com.ptg.payroll.domain.ReturnResponse;
import com.ptg.payroll.domain.ValidationError;

@ControllerAdvice
public class RestErrorHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(RestErrorHandler.class);
  private MessageSource messageSource;

  @Autowired
  public RestErrorHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ReturnResponse processValidationError(MethodArgumentNotValidException ex) {
    BindingResult result = ex.getBindingResult();
    List<FieldError> fieldErrors = result.getFieldErrors();
    ValidationError validationError = processFieldErrors(fieldErrors);
    return CommonUtils.getHttpStatusResponse(ConfigService.getProperty("ERR-MYCOMP-60008"), HttpStatus.BAD_REQUEST, validationError,
        "ERR-MYCOMP-60008");
  }

  @ExceptionHandler(CommmonValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ReturnResponse processCommonValidationError(CommmonValidationException ex) {
    ValidationError validationError = new ValidationError();
    Map<String, String> errorMap = ex.getErorMap();
    for (Map.Entry<String, String> entry : errorMap.entrySet()) {
      validationError.addFieldError(entry.getKey(), entry.getValue());
    }

    return CommonUtils.getHttpStatusResponse(ConfigService.getProperty("ERR-MYCOMP-60001"), HttpStatus.BAD_REQUEST, validationError,
        "ERR-MYCOMP-60001");

  }

  private ValidationError processFieldErrors(List<FieldError> fieldErrors) {
    ValidationError validationError = new ValidationError();

    for (FieldError fieldError : fieldErrors) {
      validationError.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
    }
    validationError.setStatus(HttpStatus.BAD_REQUEST.value());
    return validationError;
  }

  @ExceptionHandler(PtgPayrollParseException.class)
  @ResponseBody
  ReturnResponse trinetParseException(PtgPayrollParseException tpe) {
    return error(tpe, HttpStatus.BAD_REQUEST, tpe.getMessage());
  }

  @ExceptionHandler(NavigationServiceException.class)
  @ResponseBody
  ReturnResponse navigationServiceException(NavigationServiceException bse) {
    return error(bse, HttpStatus.BAD_REQUEST, bse.getMessage());
  }

  @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
  @ResponseBody
  public ReturnResponse resolveException(HttpMessageNotReadableException e) {
    return error(e, HttpStatus.BAD_REQUEST,  "ERR-MYCOMP-60006");
  }

  @ExceptionHandler(UnsupportedOperationException.class)
  @ResponseBody
  ReturnResponse unsupportedOperationExceptionHandler(UnsupportedOperationException e) {
    return error(e, HttpStatus.NOT_IMPLEMENTED, "ERR-MYCOMP-60011");
  }

  @ExceptionHandler(NotImplementedException.class)
  @ResponseBody
  ReturnResponse notImplementedExceptionHandler(NotImplementedException e) {
    return error(e, HttpStatus.NOT_IMPLEMENTED,  "ERR-MYCOMP-60014");
  }
  
  
  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseBody
  ReturnResponse missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
    return error(e, HttpStatus.NOT_IMPLEMENTED,  "ERR-MYCOMP-60014");
  }
  @ExceptionHandler(TypeMismatchException.class)
  @ResponseBody
  ReturnResponse TypeMismatchExceptionHandler(TypeMismatchException e) {
    return CommonUtils.getHttpStatusResponse(ConfigService.getProperty("ERR-MYCOMP-60036"), HttpStatus.BAD_REQUEST, null,
        "ERR-MYCOMP-60036");
  }
  @ExceptionHandler(Exception.class)
  @ResponseBody
  ReturnResponse exception(Exception e) {
    return error(e, HttpStatus.NOT_FOUND,  "ERR-MYCOMP-60015");
  }

  @SuppressWarnings("unused")
  private String resolveLocalizedErrorMessage(FieldError fieldError) {
    Locale currentLocale = LocaleContextHolder.getLocale();
    String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

    if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
      String[] fieldErrorCodes = fieldError.getCodes();
      if (fieldErrorCodes.length > 0)
        localizedErrorMessage = fieldErrorCodes[0];
    }

    return localizedErrorMessage;
  }

  private <E extends Exception> ReturnResponse error(E e, HttpStatus httpStatus, String errorCode) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    LOGGER.error(e.getMessage());
    e.printStackTrace();
    return CommonUtils.getHttpStatusResponse(ConfigService.getProperty(errorCode), httpStatus, null, errorCode);
  }

}
