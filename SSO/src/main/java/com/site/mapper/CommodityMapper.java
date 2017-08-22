package com.site.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.site.model.Commodity;

public interface CommodityMapper {
	
	public Commodity getCommodityId(String CommodityCode);
	
}
