package com.ai.server.core.handle;

import com.ailk.mobile.frame.handle.impl.DefaultExceptionHandler;
import com.ailk.mobile.servlet.ServletManager;
import com.ailk.mobile.util.MobileConstant;
import com.ailk.mobile.util.MobileServerException;

public class IpuExceptionHandler extends DefaultExceptionHandler{
	
	@Override
	public void pageError(Exception e, String pageAction, String data)
			throws Exception {
		// TODO Auto-generated method stub
		if(MobileServerException.class.isInstance(e)){
			if(MobileConstant.Result.SESSION_ERROR_CODE.equals(((MobileServerException)e).getCode())){
				ServletManager.openPage("index");	//超时则重定向
				return;
			}
		}
		super.pageError(e, pageAction, data);	//执行父类的逻辑
	}
}
