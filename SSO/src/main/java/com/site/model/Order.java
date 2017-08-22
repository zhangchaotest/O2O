package com.site.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//@JsonIgnoreProperties(ignoreUnknown = true)

public class Order {
	private ArrayList<Commodity> commodityCodes;
	private String storeCode;
	private String serialNumber;
	private String orderId;
	public ArrayList<Commodity> getCommodityCodes() {
		return commodityCodes;
	}
	public void setCommodityCodes(ArrayList<Commodity> commodityCodes) {
		this.commodityCodes = commodityCodes;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	 
}
