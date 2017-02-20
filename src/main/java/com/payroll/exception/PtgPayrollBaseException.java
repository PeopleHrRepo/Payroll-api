package com.payroll.exception;

import com.payroll.constant.ExceptionConstants;

public class PtgPayrollBaseException extends RuntimeException {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -3868232127218537194L;

	protected String errorCode;

	protected Object[] messageParams;

	protected ExceptionConstants exceptionConstants;

	public PtgPayrollBaseException() {
		super();
	}

	public PtgPayrollBaseException(String msg) {
		super(msg);
	}

	public PtgPayrollBaseException(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;

	}

	public PtgPayrollBaseException(Throwable e) {
		super(e);
	}

	public PtgPayrollBaseException(String msg, Throwable e) {
		super(msg, e);
		this.errorCode = e.getMessage();
	}

	public static String getStack(Throwable e) {
		StackTraceElement[] st = Thread.currentThread().getStackTrace();
		return st[4].getClassName() + "." + st[4].getMethodName() + "():" + st[4].getLineNumber() + " ->"
				+ e.getMessage();
	}

	public static String getStack() {
		StackTraceElement[] st = Thread.currentThread().getStackTrace();
		return st[4].getClassName() + "." + st[4].getMethodName() + "():" + st[4].getLineNumber() + " -> ";
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	/**
	 * @return Returns the messageParams.
	 */
	public Object[] getMessageParams() {
		return messageParams;
	}

	public ExceptionConstants getExceptionConstants() {
		return exceptionConstants;
	}

}
