package com.site.model;

import java.util.List;
import java.util.Map;

public class OrderReturn {
	private String State;
	private String ResultCode;
	private String ErrorMessage;
	private String Success;
	private String Message;
	
	public OrderReturn(){
		this.State = null;
		this.ResultCode = null;
		this.ErrorMessage = null;
		this.Success = null;
		this.Message = null;
	}
	public String getSuccess() {
		return Success;
	}

	public void setSuccess(String success) {
		Success = success;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getResultCode() {
		return ResultCode;
	}
	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}

	
}




	
