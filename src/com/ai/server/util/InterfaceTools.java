package com.ai.server.util;

import com.ailk.mobile.config.MobileConfig;

public class InterfaceTools {
	
	
	//从osi调用接口
	public static String getDataToOsi(String methodName){
		String url = "";
		try {
			url = MobileConfig.getValue("osiUrl");
		} catch (Exception e) {
			e.printStackTrace();
		}
		url += methodName;
		return url;
	}
	
	public static String getDataTest(String methodName){
		//osiUrlTest
		String url = "";
		try {
			url = MobileConfig.getValue("osiUrlTest");
		} catch (Exception e) {
			e.printStackTrace();
		}
		url += methodName;
		return url;
	}

}
