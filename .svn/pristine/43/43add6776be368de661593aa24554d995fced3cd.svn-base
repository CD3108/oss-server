package com.ai.server.core.session;

import com.ai.server.core.context.IpuContextData;
import com.ai.server.util.Constant;
import com.ailk.common.data.IData;
import com.ailk.mobile.frame.context.IContextData;
import com.ailk.mobile.frame.session.impl.AbstractSessionManager;
import com.ailk.mobile.util.MobileUtility;

public class IpuSessionManager extends AbstractSessionManager {

	/**
	 * 自定义的校验逻辑
	 */
	@Override
	public void customVerify(String sessionId, IData param, IContextData contextData) throws Exception {
		String account = param.getString(Constant.Session.STAFF_ID, "");
		String sessionAccount = (String) ((IpuContextData) contextData).get("STAFF_ID");
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Session Verify account : " + account);
		}

		if (!account.equals("") && !account.equals(sessionAccount)) {
			if (log.isDebugEnabled())
				log.debug("Session [" + sessionId + "|" + account + "] " + "Not Matched Cache Session [" + sessionId
						+ "|" + sessionAccount + "]");
			MobileUtility.error("非法操作，请重新登录！");
		}
	}

	@Override
	public String createSession(IContextData contextData) {
		destorySession();
		return super.createSession(contextData);
	}
}
