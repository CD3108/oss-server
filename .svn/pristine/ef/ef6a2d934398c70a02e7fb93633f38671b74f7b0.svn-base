package com.ai.server.bean.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ai.server.core.bean.BaseBean;
import com.ai.server.util.ApplicationPath;
import com.ailk.common.data.IData;
import com.ailk.common.data.impl.DataMap;
import com.ailk.mobile.servlet.ServletManager;
import com.ailk.mobile.util.MobileUtility;

/**
 * @author huangbo
 * @date 2016-1-9 下午2:32:20 
 * @desc 上传下载使用范例
 */
public class UploadDownloadBean extends BaseBean {
    
	/**
	 * 文件上传
	 */
	@SuppressWarnings("unchecked")
	public IData upload(IData param) throws Exception{
		/*1.判断请求和参数是否合法*/
		String filePathStr = param.getString("FILE_PATH", ""); //通过自定义参数决定文件存储规则
		String[] filePaths = filePathStr.split(",");
		if(filePaths.length == 0){
			MobileUtility.error("请设置文件存储路径!");
		}
		/*1.2.判断请求是否包含文件信息*/
		HttpServletRequest request = ServletManager.getRequest();
		if (!ServletFileUpload.isMultipartContent(request)) {
			MobileUtility.error("没有检测到文件,请重新提交!");
		}
		
		/*2.处理上传文件的业务逻辑*/
		IData result = new DataMap();
		DiskFileItemFactory factory = new DiskFileItemFactory(); //获得磁盘文件条目工厂
        //factory.setSizeThreshold(5*1024*1024); //设置缓存的大小。上传文件超过缓存时,将存储在临时目录中。
        //factory.setRepository(file); //设置临时存储
        ServletFileUpload upload = new ServletFileUpload(factory);
        OutputStream out = null;
        InputStream in = null;
        String filePath = null, savePath = "";		//文件地址
		try {
			/* 多个上传文件 */
			List<FileItem> fileList = upload.parseRequest(request);
			// 多文件上传判断 还有些问题，未调通
//			if(fileList.size() != filePaths.length){
//				MobileUtility.error("提交的文件数量和保存的文件列表数量不一致，请检查参数!");
//			}
			for (int i = 0; i < fileList.size(); i++) {
				FileItem item = fileList.get(i);
				String name = item.getFieldName();// 获取表单的属性名

				/* 处理表单数据 */
				if (item.isFormField()) {
					request.setAttribute(name, item.getString());
				} else {
					String value = item.getName();
					request.setAttribute(name, value); // 保存文件名
					//文件在server上保存地址
					filePath = ApplicationPath.getFilePath(request) + filePaths[i];
					File dir = new File(filePath).getParentFile();
					if(! dir.exists() && ! dir.mkdirs()){
						MobileUtility.error("创建上传文件夹失败!");
					}
					savePath += filePath + ",";
					/* 保存文件 */
					File uploadFile = new File(filePath);
					out = new FileOutputStream(uploadFile);
					in = item.getInputStream();
					int length = 0;
					byte[] buf = new byte[1024];
					while ((length = in.read(buf)) != -1) {
						out.write(buf, 0, length);
					}
					log.debug("上传文件大小为：" + item.getSize());
				}
			}
		} catch (Exception e) {
			MobileUtility.error("上传文件失败!", e);
		} finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		}
		result.put("FILE_PATH", savePath.substring(0, savePath.length() - 1));
		result.put("MEESSAG", "上传文件成功:" + filePath);
		return result;
	}
	
	public InputStream download(IData param) throws Exception {
		String filePath = param.getString("FILE_PATH");
		HttpServletRequest request = ServletManager.getRequest();
		filePath = ApplicationPath.getFilePath(request) + filePath;
		
		File file = new File(filePath);
		if (!file.isFile()) {
			MobileUtility.error("文件不存在:" + filePath);
		}

		return new FileInputStream(file);
	}
}
