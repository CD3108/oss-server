require(["domReady!","wadeMobile","mobile","util"],function(doc,WadeMobile,Mobile){
	if(WadeMobile.isApp()){
		/*进入界面先销毁链接,防止离开此界面时没有断开*/
		WadeMobile.unregisterForPush();
		/*接受推送信息后的处理方法*/
		window.receiveMessage = function(msg){
			if(WadeMobile.isIOS()){
				WadeMobile.alert(msg);
			}else if(WadeMobile.isAndroid()){
				WadeMobile.showNotification(msg);
				alert(msg);
			}
		};
		WadeMobile.setCallbackForPush("receiveMessage");
	}
	
	$("#connect").tap(function(){
		if($("#connect").html()=="连接"){
			WadeMobile.getSysInfo(function(info){
				$("#deviceId").html(info);
				$("#devicePart").css("display","block"); 
				WadeMobile.registerForPush(info,function(msg){
					WadeMobile.tip(msg);
				},function(msg){
					WadeMobile.tip(msg);
				});
			},"IMEI");
			$("#connect").attr("class", "e_button-red");
			$("#connect").html("断开");
		}else{
			WadeMobile.unregisterForPush(function(){
				WadeMobile.tip("注销成功");
			});
			$("#connect").attr("class", "e_button-ok");
			$("#connect").html("连接");
			
			$("#devicePart").css("display","none"); 
		}
	});
});