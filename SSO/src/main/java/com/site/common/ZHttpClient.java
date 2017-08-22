package com.site.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.site.framework.StartCheckChargeListener;

public class ZHttpClient {

	private static final Logger logger = LoggerFactory.getLogger(StartCheckChargeListener.class);

	private Map<String, String> headerMap = new HashMap<String, String>();

	CloseableHttpClient client = HttpClientBuilder.create().build();
	RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(2000)
			.setConnectTimeout(2000).setSocketTimeout(5000).build();

	public void setTimeout(Integer seconds) {
		requestConfig =
				RequestConfig.custom().setConnectionRequestTimeout(2000).setConnectTimeout(2000)
				.setSocketTimeout(seconds*1000).build();
	}

	public String getHttpClient(String url) {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);
		if (headerMap.size() != 0) {
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}
		}
		CloseableHttpResponse response = null;
		try {
			response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
//			int statusCode = response.getStatusLine().getStatusCode();
			String responserMsg = EntityUtils.toString(entity, "utf-8");

			return responserMsg;
		} catch (Exception e) {
			logger.error("ZHttpClient getHttpClient", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("ZHttpClient getHttpClient close CloseableHttpResponse error", e);
				}
			}
			httpGet.releaseConnection();
		}
		return null;
	}
	public String getFormHttpClient(String url) {
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
		httpGet.setConfig(requestConfig);
		if (headerMap.size() != 0) {
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}
		}
		CloseableHttpResponse response = null;
		try {
			response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
//			int statusCode = response.getStatusLine().getStatusCode();
			String responserMsg = EntityUtils.toString(entity, "utf-8");

			return responserMsg;
		} catch (Exception e) {
			logger.error("ZHttpClient getHttpClient", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("ZHttpClient getHttpClient close CloseableHttpResponse error", e);
				}
			}
			httpGet.releaseConnection();
		}
		return null;
	}
	public String postJsonHttpClient(String url, String jsonstr) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
		if (headerMap.size() != 0) {
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
		}
		StringEntity se = new StringEntity(jsonstr, "utf-8");
		httpPost.setEntity(se);
		CloseableHttpResponse response = null;
		try {
			response = client.execute(httpPost);

			HttpEntity entity = response.getEntity();
//			int statusCode = response.getStatusLine().getStatusCode();
			String responserMsg = EntityUtils.toString(entity, "utf-8");			
			return responserMsg;

		} catch (Exception e) {
			logger.error("ZHttpClient postJsonHttpClient", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("ZHttpClient postJsonHttpClient close CloseableHttpResponse error", e);
				}
			}
			httpPost.releaseConnection();
		}
		return null;
	}
	

	public String putJsonHttpClient(String url, String jsonstr) {
		HttpPut httpPut = new HttpPut(url);
		httpPut.setConfig(requestConfig);
		httpPut.addHeader("Content-Type", "application/json;charset=UTF-8");
		if (headerMap.size() != 0) {
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpPut.addHeader(entry.getKey(), entry.getValue());
			}
		}
		StringEntity se = new StringEntity(jsonstr, "utf-8");
		httpPut.setEntity(se);
		CloseableHttpResponse response = null;
		try {
			response = client.execute(httpPut);

			HttpEntity entity = response.getEntity();
//			int statusCode = response.getStatusLine().getStatusCode();
			String responserMsg = EntityUtils.toString(entity, "utf-8");
			return responserMsg;

		} catch (Exception e) {
			logger.error("ZHttpClient putJsonHttpClient", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("ZHttpClient putJsonHttpClient close CloseableHttpResponse error", e);
				}
			}
			httpPut.releaseConnection();
		}
		return null;
	}

	public String patchJsonHttpClient(String url, String jsonstr) {
		HttpPatch httpPatch = new HttpPatch(url);
		httpPatch.setConfig(requestConfig);
		httpPatch.addHeader("Content-Type", "application/json;charset=UTF-8");
		if (headerMap.size() != 0) {
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpPatch.addHeader(entry.getKey(), entry.getValue());
			}
		}
		StringEntity se = new StringEntity(jsonstr, "utf-8");
		httpPatch.setEntity(se);
		CloseableHttpResponse response = null;
		try {
			response = client.execute(httpPatch);
			HttpEntity entity = response.getEntity();
//			int statusCode = response.getStatusLine().getStatusCode();
			String responserMsg = EntityUtils.toString(entity, "utf-8");
			return responserMsg;

		} catch (Exception e) {
			logger.error("ZHttpClient patchJsonHttpClient", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("ZHttpClient patchJsonHttpClient close CloseableHttpResponse error", e);
				}
			}
			httpPatch.releaseConnection();
		}
		return null;
	}

	public String deleteHttpClient(String url) {
		HttpDelete httpDelete = new HttpDelete(url);
		httpDelete.setConfig(requestConfig);
		if (headerMap.size() != 0) {
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpDelete.addHeader(entry.getKey(), entry.getValue());
			}
		}
		CloseableHttpResponse response = null;

		try {
			response = client.execute(httpDelete);
			HttpEntity entity = response.getEntity();
//			int statusCode = response.getStatusLine().getStatusCode();
			String responserMsg = EntityUtils.toString(entity, "utf-8");

			return responserMsg;
		} catch (Exception e) {
			logger.error("ZHttpClient deleteHttpClient", e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("ZHttpClient deleteHttpClient close CloseableHttpResponse error", e);
				}
			}
			httpDelete.releaseConnection();
		}
		return null;
	}


	public void addHeaderMap(String key, String value) {
		headerMap.put(key, value);
	}
}
