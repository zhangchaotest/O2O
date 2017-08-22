package com.site.dto;

public class ChannelCommodityStoreDto {
	
	private String ChannelCode;
	private String ChannelStoreId;
	private String StoreCode;
	private String CommodityCode;
	private Integer isShelf;
	private Integer disabled;
	private double SalePrice;
	private double OriginalPrice;
	private String OutCommodityCode;
	private String innerCommodityId;
	
	
	public String getInnerCommodityId() {
		return innerCommodityId;
	}
	public void setInnerCommodityId(String innerCommodityId) {
		this.innerCommodityId = innerCommodityId;
	}
	public String getChannelStoreId() {
		return ChannelStoreId;
	}
	public void setChannelStoreId(String channelStoreId) {
		ChannelStoreId = channelStoreId;
	}
	public String getChannelCode() {
		return ChannelCode;
	}
	public void setChannelCode(String channelCode) {
		ChannelCode = channelCode;
	}
	public String getStoreCode() {
		return StoreCode;
	}
	public void setStoreCode(String storeCode) {
		StoreCode = storeCode;
	}
	public String getCommodityCode() {
		return CommodityCode;
	}
	public void setCommodityCode(String commodityCode) {
		CommodityCode = commodityCode;
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
	public double getSalePrice() {
		return SalePrice;
	}
	public void setSalePrice(double salePrice) {
		SalePrice = salePrice;
	}
	public double getOriginalPrice() {
		return OriginalPrice;
	}
	public void setOriginalPrice(double originalPrice) {
		OriginalPrice = originalPrice;
	}
	public String getOutCommodityCode() {
		return OutCommodityCode;
	}
	public void setOutCommodityCode(String outCommodityCode) {
		OutCommodityCode = outCommodityCode;
	}

	
}
