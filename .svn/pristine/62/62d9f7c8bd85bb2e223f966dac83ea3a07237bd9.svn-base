require(["mobile","common","hammer","handlebars","util"],function(Mobile,Common,Hammer,Handlebars){	
	var iscroll = new iScroll("scroll");
	
	var currentPage = 1;
	var PageSize = 5;
	
	// 页面初始化默认查询
	reload();
	
	// 当点击查询按钮时,重新初始化分页参数
	$("#qryButton").tap(function(){
		reload();
	});
	
	// 初始化重新查询
	function reload() {
		// 清空
		$("#infoList").empty();
		
		var data = new Wade.DataMap();
 		data.put("PageNo", 1);
 		data.put("PageSize",PageSize);
 		
 		doQry(data);
	}
	
	// 重新计算固定滚动区域，并刷新滚动条
	function reCalcTopAndRefresh() {
		$(".scroll-wrap").css("top",$(".query-wrap").height());
		iscroll.refresh();
	}
	
	// 查询条件是否显示更多
	$("#showMore").tap(function(){
		if($("#showMore").text()=="more"){
			$("#showMore").text("less");
			$(".query-wrap .c_list ul li.less-more").show();
		}else{
			$("#showMore").text("more");
			$(".query-wrap .c_list ul li.less-more").hide();
		}
		
		reCalcTopAndRefresh();
	});
	
	// 查询
	function doQry(data){
		Mobile.loadingStart("正在获取,请稍等……","等待");
		Common.callSvc("Demo.getInfoList",data,function(data){
			Mobile.loadingStop();
			
			var json = eval("(" + data+ ")");
			var html = Handlebars.compile($("#T_infoList").val())(json);
			$(".has-more").remove();
			$("#infoList").append($(html));
			reCalcTopAndRefresh();
			
			// 为列表数据绑定事件
			$("#infoList li div.content").each(function(index,ele) {
				var pressLi = new Hammer(ele);
				
				// 长按列表数据，显示删除按钮
		 		pressLi.on("press", function (ev) {
		 			$(".press-wrap").hide();
					var $pressWrap = $(ev.target).parents("li.active").find(".press-wrap");
					$pressWrap.show();
					
					//为删除按钮绑定事件
					var del = new Hammer($pressWrap.get(0));
					del.on("tap", function(ev) {
						// 该条记录详细信息
						var data=$(ev.target).parents("li").data("json");
						
						var data = new Wade.DataMap();
						// id号，请根据实际修改
				 		data.put("id", data.PARTY_ID);
				 		Mobile.loadingStart("正在删除,请稍等……","等待");
						Common.callSvc("Demo.delete",data,function(data){
							Mobile.loadingStop();
							
							$(ev.target).parents("li").remove();
							reCalcTopAndRefresh()
						});	
					});
					
				});	
		 		
		 		// 单击列表数据，显示详情页
		 		pressLi.on("tap", function (ev) {
		 			var jsonData=$(ev.target).parents("li").data("json");
		 			var param = new Wade.DataMap();
		 			param.put("jsonData", JSON.stringify(jsonData));
					Mobile.openTemplate("SceneBean_DemoDetail",param);	
				});	
			});
			
			
			// 点击加载更多
			$(".has-more").tap(function() {
				// 当点击查询按钮时,重新初始化分页参数
				var data = new Wade.DataMap();
		 		data.put("PageNo", ++currentPage);
		 		data.put("PageSize",PageSize);
		 		
		 		doQry(data);
			});
			
		});	
	}
	
	// 新增
	$("#add").tap(function(){
		Common.openPage("SceneBean_DemoAdd");
	});

	
});