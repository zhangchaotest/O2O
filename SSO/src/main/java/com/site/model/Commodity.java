package com.site.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)

public class Commodity {
	String commodityCode;
	String commodityName;
	String commodityId;
	String innerCommodityId;
	Integer isShelf;
	Integer disabled;
	
	@Autowired
	ChannelCommodityStore channelCommodityStore;
	
	public String getInnerCommodityId() {
		return innerCommodityId;
	}
	public void setInnerCommodityId(String innerCommodityId) {
		this.innerCommodityId = innerCommodityId;
	}
	public Integer getIsShelf() {
		return isShelf;
	}
	public void setIsShelf(Integer isShelf) {
		this.isShelf = isShelf;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	public String getCommodityCode() {
		return commodityCode;
	}
	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	
	public ChannelCommodityStore getChannelCommodityStore() {
		return channelCommodityStore;
	}
	public void setChannelCommodityStore(ChannelCommodityStore channelCommodityStore) {
		this.channelCommodityStore = channelCommodityStore;
	}
	@Override
	public String toString() {
		return "Commodity [commodityCode=" + commodityCode + ", commodityName=" + commodityName + ", commodityId="
				+ commodityId + ", innerCommodityId=" + innerCommodityId + ", isShelf=" + isShelf + ", disabled="
				+ disabled + "]";
	}
	
	
}
