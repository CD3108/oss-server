package com.ai.server.core.bean;

import com.ai.server.core.context.IpuContextData;
import com.ailk.mobile.frame.bean.AbstractBean;
import com.ailk.mobile.frame.context.IContext;

public class BaseBean extends AbstractBean{
	@Override
	protected IpuContextData getContextData() throws Exception {
		IContext<?> context = getContext();
		return (IpuContextData)context.getContextData();
	}
}
