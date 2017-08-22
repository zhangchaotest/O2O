package com.site.exception.impl;

public class SystemException extends com.site.exception.SystemException {

	private static final long serialVersionUID = 1L;
	private int errorCode;
	private String errorMessage;

	public SystemException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
		setErrorMessage(errorMessage);
		setCode(errorCode);
	}

	public SystemException(IExceptionCode errorCode) {
		super(ExceptionHelper.getMessage(errorCode));
		this.errorCode = ExceptionHelper.getCode(errorCode);
		this.errorMessage = this.getMessage();
	}

	public SystemException(IExceptionCode errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = ExceptionHelper.getCode(errorCode);
		this.errorMessage = ExceptionHelper.getMessage(errorCode);
	}

	public SystemException(IExceptionCode errorCode, String message) {
		super(message);
		this.errorCode = ExceptionHelper.getCode(errorCode);
		this.errorMessage = ExceptionHelper.getMessage(errorCode);
	}

	public SystemException(IExceptionCode errorCode, Throwable cause) {
		super(ExceptionHelper.getMessage(errorCode), cause);
		this.errorCode = ExceptionHelper.getCode(errorCode);
		this.errorMessage = this.getMessage();
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
