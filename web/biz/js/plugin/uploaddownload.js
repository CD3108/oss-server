require(["domReady!","wadeMobile", "util"], function(doc,WadeMobile) {
	var iscroll = new iScroll("content");
	var picPath = "picture/my.png";
	
	/**
	 * 选择图片 
	 */
	$("#getPicture").tap(function() {
		WadeMobile.getPicture(function(filePath){
			$("#uploadFilePath").html(filePath);
		});
	});

	/**
	 * 上传 
	 */
	$("#upload").tap(function() {
		var uploadFile = $("#uploadFilePath").html();
		if(!uploadFile){
			alert("请先选择上传文件!");
			return;
		}
		/*单文件上传*/
		var params = Wade.DataMap();
		params.put("FILE_PATH",picPath);
		
		WadeMobile.loadingStart("正在上传...");
		WadeMobile.uploadWithServlet(uploadFile, "UploadDownloadBean.upload", params.toString(), function(result){
			WadeMobile.loadingStop();
			
			var data = new Wade.DataMap(result);
			$("#remoteFilePath").html(data.get("FILE_PATH"));
		});
		/*多文件上传*/
		//有待补充
	});
	
	/**
	 * 下载
	 * 常用相对路径,如果只有android,可使用绝对路径
	 */
	$("#download").tap(function() {
		var params = new Wade.DataMap();
		params.put("FILE_PATH", picPath); //下载文件的路径
		
		WadeMobile.loadingStart("正在下载...");
		WadeMobile.downloadWithServlet(picPath, "UploadDownloadBean.download", params.toString(), function(savePath){
			WadeMobile.loadingStop();
			
//			alert("下载成功:"+savePath);
			$("#downloadFilePath").html(savePath);
			
			var width = $("#downloadPicture").width();
			var height = $("#downloadPicture").height();
			$("#downloadPicture").removeClass("e_imagePlaceHolder");
			$("#downloadPicture").html("<img height='" + height + "' width='" + width + "' src='" + savePath + "'/>");
			iscroll.refresh();
		}, function(error){
			alert("下载错误:"+error);
		});
	});
});
