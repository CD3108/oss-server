package com.ai.server.core.bean;

import com.ai.server.util.HttpClientUtils;
import com.ailk.common.data.IData;
import com.ailk.common.data.impl.DataMap;
import com.ailk.mobile.config.InterfaceConfig;
import com.ailk.mobile.frame.session.ISessionManager;
import com.ailk.mobile.frame.session.impl.AbstractSessionManager;
import com.ailk.mobile.util.MobileUtility;

public class InterfaceBean extends BaseBean {

	/**
	 * 调用第三方通用接口
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public IData commonInterface(IData param) throws Exception{
	   IData result = new DataMap();//
	   String action = param.getString("action","");	
	   String postUrl = InterfaceConfig.getUrl(action);

	   String ret = HttpClientUtils.Post(postUrl, param.toString());
	   result = new DataMap(ret);
       return result;
	}

}
