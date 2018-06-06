require(["mobile","common","hammer","util"],function(Mobile,Common,Hammer){	
	var iscroll = new iScroll("content");
	
	// 保存
	$("#save,.save-btn").tap(function() {
		var param = new Wade.DataMap();
		Mobile.loadingStart("正在保存,请稍等……","等待");
		Common.callSvc("Demo.save",param,function(data){
			Mobile.loadingStop();
			
			Mobile.tip("新增成功");
			Common.openPage("SceneBean_Demo");
		});	
	});
	
	// 返回
	$(".back-btn").tap(function(){
		Mobile.back();
	});
	
	
});