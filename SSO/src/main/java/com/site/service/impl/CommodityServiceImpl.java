package com.site.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.site.mapper.ChannelCommodityStoreMapper;
import com.site.model.ChannelCommodityStore;
import com.site.service.CommodityService;

@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {
	
	@Resource
	private ChannelCommodityStoreMapper channelcommodityStoremapper;

	
	@Override
	public ChannelCommodityStore getCommodity(String commodityCode,String storeCode) {
		ChannelCommodityStore channelcommodityStore = new ChannelCommodityStore();
		try{
			channelcommodityStore = channelcommodityStoremapper.getCommodityPrice(commodityCode, storeCode);
		}catch(Exception e){
			e.printStackTrace();
		}				
		// TODO Auto-generated method stub
		return channelcommodityStore;
	}

}
