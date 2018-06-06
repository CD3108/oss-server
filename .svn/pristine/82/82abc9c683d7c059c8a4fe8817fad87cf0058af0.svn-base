require(["domReady!","mobile","wadeMobile","util"], function(doc,Mobile,WadeMobile) {
	var isSdcard=$("#isSdcard").val();
	var fileName=$("#fileName").val();
	
	// 读文件
	WadeMobile.readFile(function(str){
		$("#content").val(str);
	},fileName,"file",isSdcard,true);
	
	// 写文件
	$("#save").tap(function(){
		var content=$("#content").val();
		WadeMobile.writeFile(content,fileName,"file",isSdcard);
		Mobile.openTemplate("File");
	});
});