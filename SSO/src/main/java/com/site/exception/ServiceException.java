package com.site.exception;

@SuppressWarnings("serial")
public class ServiceException extends Exception {
	
	private String code;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String code, String message) {
		this(message);
		setCode(code);
	}

	public ServiceException(String code, String message, Throwable cause) {
		super(message, cause);
		setCode(code);
	}

	public ServiceException(String code, Throwable cause) {
		super(cause);
		setCode(code);
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
