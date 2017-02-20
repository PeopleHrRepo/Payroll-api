package com.payroll.exception;
public class GatewayAuthenticationException extends RuntimeException {
	private static final long serialVersionUID = 8248786721802748521L;

	public GatewayAuthenticationException() {
		super();
	}

	public GatewayAuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	public GatewayAuthenticationException(String message) {
		super(message);
	}

	public GatewayAuthenticationException(Throwable cause) {
		super(cause);
	}
	

}