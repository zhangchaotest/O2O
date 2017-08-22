package com.site.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.net.URLCodec;
import org.springframework.stereotype.Service;
import com.site.common.CustomContextHolder;
import com.site.common.HttpCommon;
import com.site.json.JsonConvertUtils;
import com.site.mapper.ChannelCommodityStoreMapper;
import com.site.mapper.CommodityMapper;
import com.site.mapper.OrderMapper;
import com.site.model.ChannelCommodityStore;
import com.site.model.Commodity;
import com.site.model.Order;
import com.site.model.Test;
import com.site.model.User;
import com.site.model.OrderReturn;
import com.site.service.CommodityService;
import com.site.service.OrderService;
import ch.qos.logback.classic.Logger;
import net.sf.json.JSONObject;
import com.site.common.ZHttpClient;
import com.site.dto.CommodityDto;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Resource
	private CommodityMapper commoditymapper;
	@Resource
	private ChannelCommodityStoreMapper channelcommodityStoremapper;
	@Resource
	private OrderMapper orderMapper;
	@Override
	public Object SubmitOrder(String param) {
		OrderReturn orderReturn = new OrderReturn();
		JSONObject jo=  new JSONObject();
		Map<String,Object > storeCommodity= JsonConvertUtils.toMap(param);
		String StoreCode=(String) storeCommodity.get("StoreCode");
		List<String> list = new ArrayList<String>();
		List<JSONObject> comfo = new ArrayList<JSONObject>();
		Commodity commodity = new Commodity();
		Commodity commodityout = new Commodity();
		ChannelCommodityStore channelcommodityStore = new ChannelCommodityStore();
		CommodityService commodityService = new CommodityServiceImpl();
		list=(List<String>) storeCommodity.get("CommodityCodes");
		List<String> uncommodity = new ArrayList<String>();
		List<String> usecommodity = new ArrayList<String>();
		for(int a=0;a<list.size();a++){
			String commodityCode=list.get(a).toString();
			CustomContextHolder.setCustomerType("db_2");
			try{
				commodity=commoditymapper.getCommodityId(commodityCode);
				if(commodity.toString()!=null&&commodity.toString()!=""){
					usecommodity.add(commodityCode);
				}
				
			}catch(Exception e){
				uncommodity.add(commodityCode);
			}
		}
		if(uncommodity.toString()!="[]"){
			 orderReturn.setErrorMessage("存在店铺商品："+uncommodity+"不在cms商品表里");
			 orderReturn.setState("false");
			 return orderReturn;
		}else{
			CustomContextHolder.setCustomerType("db_1");
			for(int i=0;i<usecommodity.size();i++){
				try{
					//获取saleprice
					channelcommodityStore = channelcommodityStoremapper.getCommodityPrice(usecommodity.get(i), StoreCode);
						//放入json对象中				
						jo.put("commodityid", commodity.getCommodityId());
						jo.put("count", 2);
						jo.put("pic", "http://img11.yiguoimg.com/e/items/2017/170803/9288710991291139_300.jpg");
						double price=2*channelcommodityStore.getSalePrice();
						jo.put("price", price);
						comfo.add(jo);
					}catch(Exception e){
					uncommodity.add(usecommodity.get(i));
				}
			}
		}
			if(uncommodity.toString()!="[]"){
				orderReturn.setErrorMessage("存在店铺商品："+uncommodity+"不在该店铺中");
				orderReturn.setState("false");
				return orderReturn;
			}
			else{
				String querystr="{\"Uinfo\": {\"Uid\": \"df36ddd2-4426-4ee4-8207-3ad148dc2ae2\",\"Uname\": \"王倩雯\",\n" +
			            "        \"nickName\": \"\",\n" +
			            "        \"Phone\": \"18221779134\",\n" +
			            "        \"Role\": \"1\"\n" +
			            "    },\n" +
			            "    \"StoreId\": \""+ StoreCode+"\",\n" +
			            "    \"Address\": {\n" +
			            "        \"AddressId\": \"49cdd70b-f254-4182-a6ab-eee357c8ac7c\",\n" +
			            "        \"AddressType\": \"2\"\n" +
			            "    },\n" +
			            "    \"CpInfo\": {\n" +
			            "        \"CouponCode\": \"\"\n" +
			            "    },\n" +
			            "    \"Freight\": 0,\n" +
			            "    \"PkgAmount\": 0,\n" +
			            "    \"RpAmount\": 0,\n" +
			            "    \"RedPackets\": [],\n" +
			            "    \"ComInfo\":"+ comfo+ ",\n" +
			            "    \"GiftInfo\": []\n" +
			            "}";

				ZHttpClient zhttpClient = new ZHttpClient();
				zhttpClient.addHeaderMap("x-token", "5xm54sM1WZy9R4+6XkiZim4lECxHWFp5TAjE3iSTp3JHKxG9XlYGxtQLsZCSNdMXqgMU6cTCTMFS4AWtC96825CSdTWoltviELiOc8izVh8eRwp/NN5lpnV8OGD6J6UL");
				String data=zhttpClient.postJsonHttpClient("http://dingapi.dev.yiguo.com/api/Order/SubmitOrder", querystr);
				JSONObject fromObject= new JSONObject();
				JSONObject orderObject= new JSONObject();
		        fromObject = JSONObject.fromObject(data); 
		        String oid = fromObject.getJSONObject("data").get("oid").toString();
		        if(oid!="null"){
		        	String SerialNumber = getSerialNumber(oid);	
		        	String queryOrder="{\"ChannelName\":\"钉钉\",\"SerialNumber\":\""+SerialNumber+"\",\"OrderStatus\":2,\"SellerCode\":null,\"StoreCode\":null,\"PayStatus\":0,\"RefundReason\":null,\"FailReason\":null}";
		        	URLCodec uRLCodec = new URLCodec();
		        	try {
						queryOrder = uRLCodec.encode(queryOrder, CharEncoding.UTF_8);
						String result=zhttpClient.getFormHttpClient("http://172.17.7.208:10111/YGPS.O2O.Server.Order.UpdateStatus?request="+queryOrder);
			        	System.out.println("result:"+result);
			        	orderObject = JSONObject.fromObject(result);         	
			        	orderReturn.setResultCode(orderObject.get("ResultCode").toString());
			        	orderReturn.setState(orderObject.get("State").toString());
			        	orderReturn.setSuccess(orderObject.getJSONObject("ResultObj").get("Success").toString());
			        	orderReturn.setMessage(SerialNumber);
			        	return orderReturn;
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		        orderReturn.setErrorMessage("oid为"+oid+",下单失败");
		        orderReturn.setState("false");
		        return orderReturn ;
		        }
		}

	@Override
	public  String getSerialNumber(String oid) {
		Order orderDetail = new Order();
		CustomContextHolder.setCustomerType("db_1");
		orderDetail = orderMapper.getSerialNumber(oid);
		String serialNumber = orderDetail.getSerialNumber(); 
		// TODO Auto-generated method stub
		return serialNumber;
	}
	public List<String> verifyCommodity(Object obj){
		List<String>usecommodity= new ArrayList<String>();
		if(obj.toString()!=null&&obj.toString()!=""){
			usecommodity.add(obj.toString());
			return usecommodity;
		}
		return null;
	}
	
}
