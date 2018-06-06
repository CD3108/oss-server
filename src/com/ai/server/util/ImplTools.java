package com.ai.server.util;

import com.ailk.mobile.config.MobileConfig;

public class ImplTools {
	
	
	//从osi调用接口
	public static String getDateToOsi(String methodName){
		String url = "";
		try {
			url = MobileConfig.getValue("osiUrl");
		} catch (Exception e) {
			e.printStackTrace();
		}
		url += methodName;
		return url;
	}

}
