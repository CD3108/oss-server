require(["common","mobile","util"], function(Common,Mobile) {
	
	$("#callIf").tap(function(){
		alert("callIf");
		var param = new Wade.DataMap();
		param.put("hello","world");
		param.put("what","day");
		param.put("lll","测试一下中文");
		Common.callIf("getJson",param,function(data){
			alert(data);
		})
	});
	$("#openPageIf").tap(function(){
		alert("openPageIf");
		var param = new Wade.DataMap();
		param.put("hello","world");
		param.put("what","day");
		param.put("lll","测试一下中文");
		Common.openPageIf("test2","getJson2",param,function(data){
			alert(data);
		})
	});
	
	$("#yubaPush").tap(function(){
		var param = new Wade.DataMap();
		/*特殊符号测试*/
		param.put("flag",";/?:@&=+$,#()[]!#%*'");
		param.put("url","http://www.163.com?action=1&aaaa=2");
		param.put("chinese","中文测试");
		Mobile.openTemplate("YunbaPush", param);
	});
	$("#openUrl").tap(function(){
		
		Mobile.openUrl("https://www.baidu.com/");
	});
	
	
	
	
});
