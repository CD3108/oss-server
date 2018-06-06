require(["domReady!","mobile","util"],function(doc,Mobile){
	new iScroll("content",{tap:true,scrollbars: true,useTransform:false});//增加页面滑动效果
	$("#testSwitch").tap(function() {
		var dropmenu = WmWebUI.select("myDropmenu");
		var that = this;
		if (dropmenu.invisible()) {
			dropmenu.show();
			$(that).html("关闭菜单");
		} else {
			dropmenu.hidden();
			$(that).html("打开菜单");
		}
	});
	
	$("#testSetLabel").tap(function(){
		var dropmenu = WmWebUI.select("myDropmenu");
		var that = this;
		if(dropmenu.getLabel()){
			dropmenu.setLabel(null);
			$(that).html("设置文本");
		}else{
			dropmenu.setLabel("自定义下拉菜单");
			$(that).html("清除文本");
		}
	});
	
	$("#testSetOpenAction").tap(function(){
		var dropmenu = WmWebUI.select("myDropmenu");
		var that = this;
		if(dropmenu.openAction){
			dropmenu.setOpenAction(null);
			$(that).html("设置打开事件");
		}else{
			dropmenu.setOpenAction(function(){
				alert("打开事件");
			});
			$(that).html("清除打开事件");
		}
	});
	
	$("#testSetCloseAction").tap(function(){
		var dropmenu = WmWebUI.select("myDropmenu");
		var that = this;
		if(dropmenu.closeAction){
			dropmenu.setCloseAction(null);
			$(that).html("设置关闭事件");
		}else{
			dropmenu.setCloseAction(function(){
				alert("关闭事件");
			});
			$(that).html("清除关闭事件");
		}
	});
	
	$("#testSetItemAction").tap(function() {
		var dropmenu = WmWebUI.select("myDropmenu");
		var items = dropmenu.getDropmenuItems();
		var that = this;
		if(items[0]&&items[0].action){
			for(var i=0;i<items.length;i++){
				items[i].setAction(null);
			}
			$(that).html("设置菜单项事件");
		}else{
			for(var i=0;i<items.length;i++){
				items[i].setAction(new Function("alert('第"+(i+1)+"个菜单项点击事件');"));
			}
			$(that).html("清空菜单项事件");
		}
	});
	/*清除所有菜单项*/
	$("#testRemoveAll").tap(function() {
		var dropmenu = WmWebUI.select("myDropmenu");
		dropmenu.removeAll();
		alert("清除成功");
	});
	/*添加菜单项*/
	$("#testPush").tap(function() {
		var dropmenu = WmWebUI.select("myDropmenu");
		dropmenu.push("下拉菜单项"+(dropmenu.getDropmenuItems().length+1));
		alert("添加成功")
	});
	/*删除菜单项*/
	$("#testRemove").tap(function() {
		var dropmenu = WmWebUI.select("myDropmenu");
		var item = dropmenu.getDropmenuItem(dropmenu.getDropmenuItems().length-1);
		item.remove();
		alert("删除成功");
	});
	/*更新菜单项*/
	$("#testHtml").tap(function(){
		var dropmenu=WmWebUI.select("myDropmenu");
		var item = dropmenu.getDropmenuItem(dropmenu.getDropmenuItems().length-1);//获取最后一个菜单项
		item.html("新的下拉菜单项");
		alert("更新成功");
	});
});