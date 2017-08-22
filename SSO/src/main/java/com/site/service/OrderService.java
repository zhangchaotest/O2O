package com.site.service;

import net.sf.json.JSONObject;

public interface OrderService {
	public Object SubmitOrder(String param);
	public String getSerialNumber(String oid);
}
