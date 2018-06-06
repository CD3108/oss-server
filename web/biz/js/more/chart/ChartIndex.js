require(["domReady!","mobile","util"],function(doc, Mobile) {
	new iScroll("content",{scrollbars:true});
	
	var echartsMenus =$("#EchartsMenus").children("li");
	$.each(echartsMenus,function(index,item){
		var action = $(item).children("div").children("div").eq(0).html();
		$(item).tap(function(){
			Mobile.openPage(action);
		});
	});
	var highchartsMenus =$("#HighchartsMenus").children("li");
	$.each(highchartsMenus,function(index,item){
		var action = $(item).children("div").children("div").eq(0).html();
		$(item).tap(function(){
			Mobile.openPage(action);
		});
	});
	var ichartsMenus =$("#ichartsMenus").children("li");
	$.each(ichartsMenus,function(index,item){
		var action = $(item).children("div").children("div").eq(0).html();
		$(item).tap(function(){
			Mobile.openPage(action);
		});
	});
	
});