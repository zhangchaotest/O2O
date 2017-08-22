package com.site.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		
		String sourceType = CustomContextHolder.getCustomerType();  
		// TODO Auto-generated method stub
		return sourceType;
	}

}
