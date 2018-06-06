require(["mobile","common","hammer","handlebars","util"],function(Mobile,Common,Hammer,Handlebars){	
	// 初始化详情页面
	initInfoDetail();
	
	// 编辑
	$("#edit").tap(function(){
		edit();
	});
	
	// 保存
	$("#save").tap(function() {
		update()
	});
	
	// 详情
	function initInfoDetail() {
		// 显示详情模板
		var jsonData = $("#jsonData").data("json");
		var html = Handlebars.compile($("#T_infoDetail").val())(jsonData);
		$("#content").empty();
		$("#content").append($(html));
		var iscroll = new iScroll("content");
		iscroll.refresh();
		
		// 编辑状态更改为详情状态
		$("#editTitle").hide();
		$("#save").hide();
		$("#detailTitle").show();
		$("#edit").show();
		
		// 编辑
		$(".edit-btn").tap(function(){
			edit();
		});
		// 返回
		$(".back-btn").tap(function(){
			Mobile.back();
		});
	}
	
	// 编辑
	function edit() {
		// 显示编辑模板
		var jsonData = $("#jsonData").data("json");
		var html = Handlebars.compile($("#T_infoEdit").val())(jsonData);
		$("#content").empty();
		$("#content").append($(html));
		var iscroll = new iScroll("content");
		iscroll.refresh();
		
		// 详情状态更改为编辑状态
		$("#detailTitle").hide();
		$("#edit").hide();
		$("#editTitle").show();
		$("#save").show();
		
		// 保存
		$(".save-btn").tap(function() {
			update()
		});
		// 返回
		$(".back-btn").tap(function(){
			Mobile.back();
		});
	}
	
	// 保存
	function update() {
		var oldData = new Wade.DataMap($("#jsonData").data("json"));
		// 更新json数据
		var param = oldData;
		param.put("STATE","SSSSS");
		$("#jsonData").data("json",param.toString());
		
		// 保存
		Mobile.loadingStart("正在保存,请稍等……","等待");
		Common.callSvc("Demo.update",param,function(data){
			Mobile.loadingStop();
			
			initInfoDetail();

		});	
	}
});