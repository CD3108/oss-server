
require(["mobile","util"], function(Mobile) {
	
	// 测测你的人品按钮
	$("#testCharacter").tap(function(){
		
		var param = new Wade.DataMap();
		param.put("data", $("#J_username").val());
		
		// 数据请求
		Mobile.dataRequest("SceneBean.dataRequestScene", param, function(result){
			// 获取返回的数据
			var obj = new Wade.DataMap(result);
			var retName = obj.get("retName");
			var retMsg = obj.get("retMsg");
			
			$("#retName").text(retName);
			$("#retMsg").text(retMsg);
			$("#mainTestCharacterContent").hide();
			$("#retTestCharacterContent").show();
		});
	});
	
	// 再测一次按钮
	$("#testCharacterAgain").tap(function(){
		$("#retName").text("");
		$("#retMsg").text("");
		$("#J_username").val("");
		$("#retTestCharacterContent").hide();
		$("#mainTestCharacterContent").show();
	});
	
});