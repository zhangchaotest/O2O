package com.site.enums;

public enum HttpEnum {
	
	GET("get"),POST("post"),PUT("put"),PATCH("patch"),DELETE("delete");
	
	private String status;
	
	private HttpEnum(String status) {
		this.setStatus(status);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
