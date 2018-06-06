package com.ai.server.core.bean;

import com.ai.server.util.HttpClientUtils;
import com.ailk.common.data.IData;
import com.ailk.common.data.impl.DataMap;

public class OssLogin {
	
	/*
	 * 登录功能
	 */
	public IData login(IData param) throws Exception{
//		System.out.println("------------请求登录---------------");
//		System.out.println("用户名：" + param.get("username"));
//		System.out.println("密码：" + param.get("passwold"));
		IData r_1 = new DataMap();
		r_1.put("staffId",param.get("username"));
		r_1.put("password",param.get("passwold"));
		r_1.put("loginName",param.get("username"));
		r_1.put("bindImei",param.get("imei"));
		r_1.put("loginTerminal",param.get("loginTerminal"));
		//r_1.put("channelId","4803218338919005347");
		//r_1.put("channelStartDate","20160708101546");
		//r_1.put("channelEndDate","20501231235959");
//		r_1.put("channelStatus","1");
//		r_1.put("pushTermimal","1");
		
		
		String ret = HttpClientUtils.Post1("http://133.128.27.198:18080/eom/mt/MTCon/login.json",r_1);
		if(ret.length() < 3){
			ret = "{}";
		}else {
			ret = ret.substring(1, ret.length() - 1);
		}
		IData result = new DataMap(ret);
		return result;
	}
	
	public static void main(String args[]){
//		String url = "http://218.26.133.7:18080/eom/mt/MTCon/login.json";
		String url = "http://133.128.27.198:18080/eom/mt/MTCon/login.json";
//		String url = "http://localhost:8080/getJson2";
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
//		System.out.println(HttpClientUtils.Post(url,null));
		System.out.println(HttpClientUtils.Post1(url,param));
	
	}
}
