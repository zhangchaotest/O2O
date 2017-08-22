package com.site.service;

import com.site.model.ChannelCommodityStore;

public interface CommodityService {
	public ChannelCommodityStore getCommodity(String commodityCode,String storeCode);
}
