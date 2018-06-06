package com.ai.server.util;

import java.net.MalformedURLException;
import javax.servlet.http.HttpServletRequest;

public final class ApplicationPath {
	//定义应用主机上文件路径，应用请求路径
	public static String appFilePath, appRequestPath;
	
	/******************************************************************
	 * 获取应用主机上的文件路径(/x/xx/xxx)
	 * @return
	 * @throws MalformedURLException
	 */
	public static String getFilePath(HttpServletRequest request) throws MalformedURLException{
		if(request == null)
			throw new RuntimeException("请求对象为空，无法获取应用文件路径！");
		
		if(appFilePath == null){
			String ServerInfo = request.getSession().getServletContext().getServerInfo();
			String serverType = ServerInfo.contains("Tomcat")?"T":"W";
			
			if(serverType.equals("T")){
				appFilePath = request.getSession().getServletContext().getRealPath("/");
				
			}else{
				appFilePath = request.getSession().getServletContext().getResource("/").getPath();
			}
		}
		return appFilePath;
	}
	/*****************************************************************
	 * 获取应用的WEB访问路径(http://x.xx.xxx:aaa/application)
	 * @return
	 * @throws MalformedURLException
	 */
	public static String getRequestPath(HttpServletRequest request) throws MalformedURLException{
		if(request == null)
			throw new RuntimeException("请求对象为空，无法获取应用WEB请求路径！");
		
		if(appRequestPath == null){
			appRequestPath = request.getScheme()
					+ "://" 
					+ request.getServerName()
					+ ":" 
					+ request.getServerPort()
					+ request.getContextPath()
					+ "/";

		}
		return appRequestPath;
	}
	
	/**私有化构造器*/
	private ApplicationPath(){}
}
