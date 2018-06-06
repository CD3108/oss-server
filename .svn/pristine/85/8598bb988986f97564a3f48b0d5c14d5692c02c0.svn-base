package com.ai.server.core.context;

import com.ai.server.util.Constant;
import com.ailk.mobile.frame.context.impl.DefaultContextData;

@SuppressWarnings("serial")
public class IpuContextData extends DefaultContextData{

	public IpuContextData() {
		
	}

	public IpuContextData(String account) {
		getData().put(Constant.Context.USER_NAME, account);
	}
	
	public String getUserName() {
		return contextData.getString(Constant.Context.USER_NAME, "");
	}

	public void setUserName(String account) {
		put(Constant.Context.USER_NAME, account);
	}
	
	public String getVerifyCode()
    {
        return getData().getString("VERIFY_CODE");
    }

    public void setVerifyCode(String verifyCode)
    {
        put("VERIFY_CODE", verifyCode);
    }
}
