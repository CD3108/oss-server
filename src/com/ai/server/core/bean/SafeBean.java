package com.ai.server.core.bean;

import com.ailk.common.data.IData;
import com.ailk.common.data.impl.DataMap;

public class SafeBean {
	
	public IData getResKey(IData param) throws Exception{
		IData key = new DataMap();
		key.put("KEY", "12345678");
		return key;
	}
}
