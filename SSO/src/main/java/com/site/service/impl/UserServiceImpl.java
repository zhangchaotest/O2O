package com.site.service.impl;

import org.springframework.stereotype.Service;

import com.site.common.ZHttpClient;
import com.site.model.User;
import com.site.service.UserService;

import net.sf.json.JSONObject;

@Service("userService")
public class UserServiceImpl implements UserService{
	public static final String token="";
	@Override
	public User getUser(String userId) {
		ZHttpClient zhttpClient = new ZHttpClient();
		String querystr = "{\"UserId\":\"DF36DDD2-4426-4EE4-8207-3AD148DC2AE2\",\"Channel\":\"ding7d9431596d3ad5d135c2f4657eb6378f\"}";
		String data = zhttpClient.postJsonHttpClient("http://172.17.7.239:122/api/Authentication/login", querystr);
		JSONObject fromObject= new JSONObject();
        fromObject = JSONObject.fromObject(data);   
        String token = (String) fromObject.getJSONObject("data").get("token");
        System.out.println("token-----"+token);
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String args[]){
		UserService userService= new UserServiceImpl();
		userService.getUser("");

	}
	
}
