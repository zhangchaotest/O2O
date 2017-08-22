package com.site.mapper;

import com.site.model.ChannelCommodityStore;

public interface ChannelCommodityStoreMapper {
	
	public ChannelCommodityStore getCommodityPrice(String commodityCode, String storeCode); 
}
