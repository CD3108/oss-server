
require(["mobile","util"], function(Mobile) {
	
	// 开始游戏
	$("#startGame").tap(function(){
		
		// 页面跳转需要渲染的数据
		var param = new Wade.DataMap();
		param.put("TemplateRedirectData","页面跳转（直接数据渲染）传过来的数据")
		Mobile.openTemplate("SceneBean_dataRequest",param);
	});
	
});