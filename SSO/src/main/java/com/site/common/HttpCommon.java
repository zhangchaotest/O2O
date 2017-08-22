package com.site.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.site.enums.HttpEnum;

import net.sf.json.JSONObject;

public class HttpCommon {

	private static final String host="http://dingapi.dev.yiguo.com";
	 static String entityString="";
	public static String query_str(String str){
		String strnew=str.replace(",", "&");
		System.out.println("str1:"+strnew);
		return strnew;
	}
	
	public static String query_ptr(String str){
		String strnew=str;
		strnew=strnew.replace("=", ":");
		strnew=strnew.replace("'", "\"");
		System.out.println("str:"+strnew);
		return strnew;
	}
	
	public  String make_url(String host,String method,String url,String querystr,String token) {
		HttpClient httpclient=new DefaultHttpClient();
		JSONObject fromObject=new JSONObject();
		
		if(method.equals(HttpEnum.GET.getStatus())){
			httpGet(host, url, querystr, token, httpclient, fromObject);
		}
		if(method.equals(HttpEnum.POST.getStatus())){
			httpPost(host, url, querystr, token, httpclient,entityString);
		}
		if(method.equals(HttpEnum.PUT.getStatus())){
			httpPut(host, url, querystr, token, httpclient, fromObject);
		}
		return entityString;
	}

	private static void httpPut(String host, String url, String query_str,
			String token, HttpClient httpclient, JSONObject fromObject) {
		HttpPut httppost=new HttpPut(host+url);
		HttpEntity entity=httppost.getEntity();
		String postData=query_ptr(query_str);
		System.out.println("postdata:"+postData);
		httppost.addHeader("x-token",token);
		try {
			StringEntity httpentity=new StringEntity(postData,"UTF-8");
			httpentity.setContentType("application/json");
			httppost.setEntity(httpentity);
			System.out.println("excuting request:"+httppost.getURI());
			HttpResponse res=httpclient.execute(httppost);
			entity=res.getEntity();
			if(entity!=null){
				System.out.println("-------------------------------");
				System.out.println("Response code :"+res.getStatusLine().getStatusCode());
				String entityString = EntityUtils.toString(entity);  
				System.out.println("Response content:"+entityString);
				fromObject = JSONObject.fromObject(entityString);
				System.out.println("-------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void httpPost(String host, String url, String query_str,
			String token, HttpClient httpclient, String entityString) {
		HttpPost httppost=new HttpPost(host+url);
		HttpEntity entity=httppost.getEntity();
		String postData=query_ptr(query_str);
		System.out.println("postdata:"+postData);
		httppost.addHeader("x-token",token);
		try {
			StringEntity httpentity=new StringEntity(postData,"UTF-8");
			httpentity.setContentType("application/json");
			httppost.setEntity(httpentity);
			System.out.println("excuting request:"+httppost.getURI());
			HttpResponse res=httpclient.execute(httppost);
			entity=res.getEntity();
			if(entity!=null){
				System.out.println("-------------------------------");
				System.out.println("Response code :"+res.getStatusLine().getStatusCode());
				entityString = EntityUtils.toString(entity);  
				System.out.println("Response content:"+entityString);
				System.out.println("-------------------------------");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

	private static void httpGet(String host, String url, String querystr, String token, HttpClient httpclient, JSONObject fromObject) {
		HttpGet httpget=new HttpGet(host+url+"?"+querystr);
		System.out.println("url:"+host+url+"?"+querystr);
		httpget.addHeader("x-token",token );
		try {
			HttpResponse res=httpclient.execute(httpget);
			HttpEntity entity=res.getEntity();
			System.out.println("--------------------------------------");  
			// 打印响应状态    
			System.out.println(res.getStatusLine());  
			if(entity!=null){
				fromObject=JSONObject.fromObject(EntityUtils.toString(entity));
				System.out.println("data:"+fromObject.getJSONArray("data"));
				String entityString = EntityUtils.toString(entity);  
				System.out.println("Response content:"+entityString);
				System.out.println("--------------------------------------"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
