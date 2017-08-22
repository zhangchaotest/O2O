package com.site.dto;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.site.common.BeanUtils;
import com.site.mapper.CommodityMapper;
import com.site.model.Commodity;



public class CommodityDto {
	
	String commodityCode;
	String commodityName;
	String commodityId;
	String innerCommodityId;
	Integer isShelf;
	Integer disabled;
		
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
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
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

}
