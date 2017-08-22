package com.site.exception;

@SuppressWarnings("serial")
public class SystemException extends RuntimeException {
	
    private String code;

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String code, String message) {
        this(message);
        setCode(code);
    }

    public SystemException(String code, String message, Throwable cause) {
        super(message, cause);
        setCode(code);
    }

    public SystemException(String code, Throwable cause) {
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
