package com.ai.server.bean;

import java.awt.image.BufferedImage;

import com.ai.server.core.bean.BaseBean;
import com.ai.server.core.context.IpuContextData;
import com.ai.server.core.session.IpuSessionManager;
import com.ai.server.dao.LoginDao;
import com.ai.server.util.Constant;
import com.ai.server.util.ImageVerify;
import com.ailk.common.data.IData;
import com.ailk.common.data.IDataset;
import com.ailk.common.data.impl.DataMap;
import com.ailk.mobile.util.MobileUtility;

public class LoginBean extends BaseBean{
	
	public IData login(IData param) throws Exception{
		IData result = new DataMap();
		
		// 校验验证码是否输入正确
		if (!checkLoginVerifyCode(param)) {
			MobileUtility.error("登陆失败，验证码输入错误!");
			return result;
		}
		/*
		 * 通过传入的账号密码做登陆校验
		 */
		LoginDao dao = new LoginDao("jszx");
		IData userInfo = dao.getUserInfo(param);
		
		if(userInfo != null){
			IpuContextData IpuContextData = new IpuContextData("");
			IpuContextData.put(Constant.Session.USER_NAME, userInfo.getString("NAME"));
			IpuContextData.put(Constant.Session.STAFF_ID, userInfo.getString("CODE"));
			IpuContextData.put(Constant.Session.USER_ID, userInfo.getString("ID"));
			String sessionId = IpuSessionManager.getInstance().createSession(IpuContextData);
			result.put(Constant.Session.SESSION_ID, sessionId);
			result.put(Constant.Session.USER_NAME, userInfo.getString("NAME"));
			result.put(Constant.Session.STAFF_ID, userInfo.getString("CODE"));
			result.put(Constant.Session.USER_ID, userInfo.getString("ID"));
			String userId = userInfo.getString("ID");
			IDataset menus = dao.getUserMenus(userId);
			result.put("menus", menus);
		}else{
			MobileUtility.error("用户名或密码错误，请重新登录!");
		}
		return result;
	}
	
	
	
	/**
	 * 初始化验证码
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public IData initVerifyCode(IData param) {
		IData result = new DataMap();

		try {
			// 获取验证码
			String verifyCode = ImageVerify.getVerifyCode(4, 2);
			BufferedImage image = ImageVerify.getImageVerify(verifyCode);
			String imageVerifyCode = ImageVerify.getImageBase64(image);

			// 创建session
			IpuContextData contextData = new IpuContextData();
			String sessionId = IpuSessionManager.getInstance()
					.createSession(contextData);
			contextData.setVerifyCode(verifyCode.toUpperCase());

			result.put("VERIFY_IMG", imageVerifyCode);
			result.put("SESSION_ID", sessionId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 
	 * @Title: refreshVerifyCode
	 * @Description: 刷新验证码
	 * @param prama
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public IData refreshVerifyCode(IData prama) throws Exception {
		// 获取验证码
		String verifyCode = ImageVerify.getVerifyCode(4, 2);
		BufferedImage image = ImageVerify.getImageVerify(verifyCode);
		String imageVerifyCode = ImageVerify.getImageBase64(image);

		if (getContextData() == null) {
			// 创建session
			IpuContextData contextData = new IpuContextData();
			IpuSessionManager.getInstance().createSession(contextData);
		}

		getContextData().setVerifyCode(verifyCode);

		IData result = new DataMap();
		result.put("VERIFY_IMG", imageVerifyCode);
		return result;
	}
	
	/**
	 * 
	 * @Title: checkLoginVerifyCode
	 * @Description: 校验登陆的验证码
	 * @param param
	 * @return true:验证码正确；false:验证码输入错误
	 * @throws
	 */
	private boolean checkLoginVerifyCode(IData param) {
		try {
			String verifyCode = getContextData().getVerifyCode();
			String clientVerifyCode = param.getString("VERIFY_CODE");
			if (clientVerifyCode == null || verifyCode == null
					|| !verifyCode.equalsIgnoreCase(clientVerifyCode)) {
				return false;
			}

			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
