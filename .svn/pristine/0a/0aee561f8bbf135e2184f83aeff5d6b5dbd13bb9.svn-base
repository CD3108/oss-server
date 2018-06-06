require(["domReady!","wmWebUI","jcl"],function(doc,WmWebUI,$){
	/*testAction按钮*/
	$("#testAction").tap(function(){
		var items = WmWebUI.select("myTab").getTabItems();
		if(items[0]&&items[0].action){
			for(var i=0;i<items.length;i++){
				items[i].setAction(null);
			}
			$(this).html("绑定选中事件");
		}else{
			for(var i=0;i<items.length;i++){
				items[i].setAction(new Function("alert('选中第"+(i+1)+"个标签菜单项');"));
			}
			$(this).html("取消选中事件");
		}
	});
	/*testActive按钮*/
	$("#testActive").tap(function(){
		//通过序号获取对象
		var wmTabItem = WmWebUI.select("myTab").getTabItem(1);
		wmTabItem.active();
	});
	/*testAnimation按钮*/
	$("#testAnimation").tap(function(){
		var myTab = WmWebUI.select("myTab");
		if(myTab.isAnimation){
			$(this).html("开启组件动画");
			myTab.setAnimation(false);
		}else{
			$(this).html("关闭组件动画");
			myTab.setAnimation(true);
		}
	});
	
	/*testAdd按钮*/
	$("#testAdd").tap(function(){
		var myTab = WmWebUI.select("myTab");
		myTab.add("新的标签","<b>新的标签<b/>")
	});
	
	/*testAdd按钮*/
	$("#testRemove").tap(function(){
		var myTab = WmWebUI.select("myTab");
		var lastTabItem = myTab.getTabItem(myTab.length()-1);
		lastTabItem.remove();
	});
});
