package com.ai.server.bean.demo;

import java.awt.image.BufferedImage;
import java.util.Random;

import com.ai.server.core.bean.BaseBean;
import com.ai.server.core.context.IpuContextData;
import com.ai.server.core.session.IpuSessionManager;
import com.ai.server.util.ImageVerify;
import com.ailk.common.data.IData;
import com.ailk.common.data.impl.DataMap;
import com.ailk.mobile.util.MobileUtility;

public class SceneBean extends BaseBean {

	/**
	 * 数据请求场景
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public IData dataRequestScene(IData param) throws Exception {
		IData result = new DataMap();

		// 获取从前台传过来的数据
		String data = param.getString("data");

		// 获取人品--随机数
		Random random = new Random();
		int testCharacter = random.nextInt(100);

		StringBuffer retMsg = new StringBuffer();
		if (testCharacter > 0 && testCharacter < 20) {
			retMsg.append("是我不好...不应该和你谈人品问题的...");
		} else if (testCharacter >= 20 && testCharacter < 40) {
			retMsg.append("您的人品之低下实在让人惊讶啊...");
		} else if (testCharacter >= 40 && testCharacter < 60) {
			retMsg.append("您的人品太差了..稍不小心就会去干坏事了吧？");
		} else if (testCharacter >= 60 && testCharacter < 80) {
			retMsg.append("您的人品勉勉强强...要自己好自为之...");
		} else if (testCharacter >= 80 && testCharacter <= 100) {
			retMsg.append("您的人品太好了...你就是当代活雷锋啊...");
		}

		result.put("retName", data);
		result.put("retMsg", retMsg);
		return result;

	}

	/**
	 * 页面跳转(调用数据接口)场景
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public IData openPageScene(IData param) throws Exception {
		IData result = new DataMap();

		// 获取从前台传过来的数据
		String data = param.getString("data", "");

		// 处理前台传过来的数据
		if ("1".equals(data.trim())) {
			result.put("retMsg", "太棒了，回答正确");
		} else {
			result.put("retMsg", "哎呀，回答错误了，答案：1个，因为再吃的时候就不是空着肚子了");
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
	 * @author 王玉娟
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
	 * 登陆
	 * 
	 * @param param
	 * @return
	 */
	public IData login(IData param) {
		IData result = new DataMap();

		// 校验验证码是否输入正确
		if (!checkLoginVerifyCode(param)) {
			MobileUtility.error("登陆失败，验证码输入错误!");
			return result;
		}

		// 将用户信息保存至session
		try {
			getContextData().put("ACCOUNT",param.getString("USER_NAME"));
			return getContextData().getData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}

	}

	/**
	 * 
	 * @Title: checkLoginVerifyCode
	 * @Description: 校验登陆的验证码
	 * @author 王玉娟
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
	
	public IData getPersonDetailMore(IData param) {
		IData result = new DataMap();
		int personId = param.getInt("id");
		if (personId <= 0) {
			throw new RuntimeException("获取参数失败！");
		}
		if (personId == 1) {
			result.put("name", "小胖");
			result.put("age", 20);
			result.put("gender", "男");
			result.put("dept", "移动部门");
		} else if (personId == 2) {
			result.put("name", "张三");
			result.put("age", 22);
			result.put("gender", "男");
			result.put("dept", "联通部门");
		} else if (personId == 3) {
			result.put("name", "李四");
			result.put("age", 24);
			result.put("gender", "女");
			result.put("dept", "财务部门");
		}
		return result;
	}
}
