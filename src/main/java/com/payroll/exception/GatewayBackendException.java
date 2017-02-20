package com.payroll.exception;
public class GatewayBackendException extends RuntimeException {

	private static final long serialVersionUID = 2476322297626082671L;

	public GatewayBackendException() {
		
	}

	public GatewayBackendException(String message) {
		super(message);
		
	}

	public GatewayBackendException(Throwable cause) {
		super(cause);
		
	}

	public GatewayBackendException(String message, Throwable cause) {
		super(message, cause);
		
	}

}
