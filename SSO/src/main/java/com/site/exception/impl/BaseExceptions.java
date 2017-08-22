package com.site.exception.impl;

public enum BaseExceptions implements IExceptionCode {
	
	@Desc("未知异常")
	@Code(-1)
	UNKNOWN_EXCEPTION,

	
	@Desc("JSON转换出错!")
	@Code(4001)
	JsonTransError
	
}
