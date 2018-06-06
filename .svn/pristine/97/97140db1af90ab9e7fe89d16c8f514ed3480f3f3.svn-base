require(["domReady!","jcl","wmWebUI"],function(doc,$,WmWebUI){
	WmWebUI.initTags(["myTabbar"],function(MyTabbar){
		//MyTabbar.getTabbarItem(3).active();//初始化选择第四个标签
	});
	/*testAction按钮*/
	$("#testAction").tap(function(){
		var items = WmWebUI.select("myTabbar").getTabbarItems();
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
		//方法1，通过序号获取对象
		/*var wmTabbarItem = WmWebUI.select("myTabbar").getTabbarItem(1);
		wmTabbarItem.active();*/
		//方法2，通过id直接获取对象
		var myTabbarItem = WmWebUI.select("myTabbarItem");
		myTabbarItem.active();
	});
	/*testAnimation按钮*/
	$("#testAnimation").tap(function(){
		var myTabbar = WmWebUI.select("myTabbar");
		if(myTabbar.isAnimation){
			$(this).html("开启组件动画");
			myTabbar.setAnimation(false);
		}else{
			$(this).html("关闭组件动画");
			myTabbar.setAnimation(true);
		}
	});
	
	/*全局事件，提供给属性action使用，方法需要绑定到window对象上*/
	window.action = function(){
		alert("选中标签菜单项2")
	}
});