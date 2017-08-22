package com.site.exception.impl;

public class ServiceException extends com.site.exception.ServiceException {
	
	private static final long serialVersionUID = 1L;
	private int errorCode;
	private String errorMessage;

	public ServiceException(String errorCode, String errorMessage) {
	    super(errorCode, errorMessage);
	    setErrorMessage(errorMessage);
	    setCode(errorCode);
	  }

	  public ServiceException(IExceptionCode errorCode) {
	    super(ExceptionHelper.getMessage(errorCode));
	    this.errorCode = ExceptionHelper.getCode(errorCode);
	    this.errorMessage = this.getMessage();
	  }

	  public ServiceException(IExceptionCode errorCode, String message, Throwable cause) {
	    super(message, cause);
	    this.errorCode = ExceptionHelper.getCode(errorCode);
	    this.errorMessage = ExceptionHelper.getMessage(errorCode);
	  }

	  public ServiceException(IExceptionCode errorCode, String message) {
	    super(message);
	    this.errorCode = ExceptionHelper.getCode(errorCode);
	    this.errorMessage = ExceptionHelper.getMessage(errorCode);
	  }

	  public ServiceException(IExceptionCode errorCode, Throwable cause) {
	    super(ExceptionHelper.getMessage(errorCode), cause);
	    this.errorCode = ExceptionHelper.getCode(errorCode);
	    this.errorMessage = this.getMessage();
	  }

	  public ServiceException(String msg) {
	    super(msg);
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
