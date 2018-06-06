package com.ai.server.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.ailk.common.data.IData;
import com.ailk.common.data.impl.DataMap;


public class HttpClientUtils {
	
	public static String Get(String url,String param){
		String result ="";
		url += param;
		
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse res = client.execute(get);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				if (entity != null) {
					result = new String(EntityUtils.toString(entity, HTTP.UTF_8));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
			
		} finally{
			//关闭连接 ,释放资源
			client.getConnectionManager().shutdown();
		}

		
		return result;
	}

	public static String Post(String url, String body) {
		String result = "";
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		HttpEntity entity = null;
		HttpResponse response = null;
		try {
			List<NameValuePair> param = new ArrayList <NameValuePair>();
			param.add(new BasicNameValuePair("param", body));
			
			
//			HttpEntity uefEntity = new StringEntity(body, "UTF-8"); 
			HttpEntity uefEntity = new UrlEncodedFormEntity(param, "UTF-8");
			httpPost.setEntity(uefEntity);
			httpPost.setHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
			response = client.execute(httpPost);
			entity = response.getEntity();
			if (entity != null) {
				result = new String(EntityUtils.toString(entity, "UTF-8"));
			}

		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			httpPost.abort();
			try {
				if (entity != null) {
					if (entity.isStreaming()) {
						InputStream instream = entity.getContent();
						if (instream != null) {
							instream.close();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public static String Post1(String url, IData param2) {
		String result = "";
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		HttpEntity entity = null;
		HttpResponse response = null;
		try {
			List<NameValuePair> param = new ArrayList <NameValuePair>();
			String names[] = param2.getNames();
			for(int i=0;i<names.length;i++){
				param.add(new BasicNameValuePair(names[i], param2.getString(names[i])));
			}
			
			HttpEntity uefEntity = new UrlEncodedFormEntity(param, "UTF-8");
			httpPost.setEntity(uefEntity);
			httpPost.setHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
			response = client.execute(httpPost);
			entity = response.getEntity();
			if (entity != null) {
				result = new String(EntityUtils.toString(entity, "UTF-8"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			httpPost.abort();
			try {
				if (entity != null) {
					if (entity.isStreaming()) {
						InputStream instream = entity.getContent();
						if (instream != null) {
							instream.close();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public static void main(String args[]){
		String url = "http://218.26.133.7:18080/eom/mt/MTCon/login.json";
		IData param = new DataMap();
		param.put("staffId", "10010");
		param.put("password", "ailkailk");
		param.put("loginName", "10010");
		param.put("bindImei", "20160608094223476");
		param.put("loginTerminal", "1");
		param.put("channelId", "4803218338919005347");
		param.put("channelStartDate", "20160708101546");
		param.put("channelEndDate", "20501231235959");
		param.put("pushTermimal", "1");
		System.out.println(Post1(url,param));
	
	}
	

	
	
	
	
}
