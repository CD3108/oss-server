package com.ai.server.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.marker.weixin.DefaultSession;
import org.marker.weixin.HandleMessageAdapter;
import org.marker.weixin.MySecurity;
import org.marker.weixin.msg.Msg4Event;
import org.marker.weixin.msg.Msg4Image;
import org.marker.weixin.msg.Msg4Link;
import org.marker.weixin.msg.Msg4Location;
import org.marker.weixin.msg.Msg4Text;
import org.marker.weixin.msg.Msg4Video;
import org.marker.weixin.msg.Msg4Voice;

import com.ai.server.util.PageData;

public class WeiXinServlet extends HttpServlet {
	public  Log LOG = LogFactory.getLog("WeiXinServlet");

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {

		String signature = req.getParameter("signature"); // 微信加密签名
		String timestamp = req.getParameter("timestamp"); // 时间戳
		String nonce = req.getParameter("nonce"); // 随机数
		String echostr = req.getParameter("echostr"); // 字符串

		if (null != signature && null != timestamp && null != nonce
				&& null != echostr) {/* 接口验证 */
			List<String> list = new ArrayList<String>(3) {
				private static final long serialVersionUID = 2621444383666420433L;

				public String toString() { // 重写toString方法，得到三个参数的拼接字符串
					return this.get(0) + this.get(1) + this.get(2);
				}
			};
			list.add("jnsyjweixin"); // 读取Token(令牌)
			list.add(timestamp);
			list.add(nonce);
			Collections.sort(list); // 排序
			String tmpStr = new MySecurity().encode(list.toString(),
					MySecurity.SHA_1); // SHA-1加密
			PrintWriter out = null;
			try {
				out = resp.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (signature.equals(tmpStr)) {
				out.write(echostr); // 请求验证成功，返回随机码
			} else {
				out.write("");
			}

		} else {/* 消息处理 */
			try {
				sendMsg(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 处理微信服务器发过来的各种消息，包括：文本、图片、地理位置、音乐等等
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void sendMsg(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		InputStream is = request.getInputStream();
		OutputStream os = response.getOutputStream();

		final DefaultSession session = DefaultSession.newInstance();
		session.addOnHandleMessageListener(new HandleMessageAdapter() {

			/**
			 * 事件
			 */
			@Override
			public void onEventMsg(Msg4Event msg) {
				/**
				 * msg.getEvent() unsubscribe：取消关注 ; subscribe：关注
				 */
				LOG.info("------------getEvent-------:"+msg.getEvent());
				LOG.info("------------getEventKey--------:"+msg.getEventKey());
				if ("subscribe".equals(msg.getEvent())) {
					returnMSg(msg, null, "关注");
				}else if("CLICK".equals(msg.getEvent())){
					returnMSg(msg, null, msg.getEventKey());
				}
			}

			/**
			 * 收到的文本消息
			 */
			@Override
			public void onTextMsg(Msg4Text msg) {
				//returnMSg(null, msg, msg.getContent().trim());
			}

			@Override
			public void onImageMsg(Msg4Image msg) {
				// TODO Auto-generated method stub
				super.onImageMsg(msg);
			}

			@Override
			public void onLocationMsg(Msg4Location msg) {
				// TODO Auto-generated method stub
				super.onLocationMsg(msg);
			}

			@Override
			public void onLinkMsg(Msg4Link msg) {
				// TODO Auto-generated method stub
				super.onLinkMsg(msg);
			}

			@Override
			public void onVideoMsg(Msg4Video msg) {
				// TODO Auto-generated method stub
				super.onVideoMsg(msg);
			}

			@Override
			public void onVoiceMsg(Msg4Voice msg) {
				// TODO Auto-generated method stub
				super.onVoiceMsg(msg);
			}

			@Override
			public void onErrorMsg(int errorCode) {
				// TODO Auto-generated method stub
				super.onErrorMsg(errorCode);
			}

			/**
			 * 返回消息
			 * 
			 * @param emsg
			 * @param tmsg
			 * @param getmsg
			 */
			public void returnMSg(Msg4Event emsg, Msg4Text tmsg, String getmsg) {
				PageData msgpd;
				PageData pd = new PageData();
				String toUserName, fromUserName, createTime;
				if (null == emsg) {
					toUserName = tmsg.getToUserName();
					fromUserName = tmsg.getFromUserName();
					createTime = tmsg.getCreateTime();
				} else {
					toUserName = emsg.getToUserName();
					fromUserName = emsg.getFromUserName();
					createTime = emsg.getCreateTime();
				}
				pd.put("KEYWORD", getmsg);
				try {
					if(getmsg.equals("关注")){
						Msg4Text rmsg = new Msg4Text();
						rmsg.setFromUserName(toUserName);
						rmsg.setToUserName(fromUserName);
						// rmsg.setFuncFlag("0");
						rmsg.setContent("你好！欢迎关注!"); // 回复文字消息
						session.callback(rmsg);
					}else if(getmsg.equals("QUESTION")){
						String content = "<a href='URL'>点击参加->集宁区食品药品监督管理局开展的有奖问卷调查活动</a>";
//						String url = "http://zafir1228.nat123.net/weixin/Weixin/question.html?open_id="+fromUserName;
						String url = "http://syj.nmhuixin.com/weixin/Weixin/question.html?open_id="+fromUserName;
						content = content.replace("URL", url);
						Msg4Text rmsg = new Msg4Text();
						rmsg.setFromUserName(toUserName);
						rmsg.setToUserName(fromUserName);
						rmsg.setContent(content); // 回复文字消息
						session.callback(rmsg);
					}
					
						
				} catch (Exception e1) {
				}
			}

		});

		/* 必须调用这两个方法 如果不调用close方法，将会出现响应数据串到其它Servlet中。 */
		session.process(is, os); // 处理微信消息
		session.close(); // 关闭Session
	}

}
