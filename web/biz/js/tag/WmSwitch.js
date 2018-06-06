require(["domReady!","mobile","util"],function(doc,Mobile){
	new iScroll("content",{tap:true,scrollbars: true});
	/*开关打开和关闭*/
	$("#testSwitch").tap( function(){
		var mySwitch = WmWebUI.select("mySwitch");
		if(!mySwitch.disabled){
			if(mySwitch.getValue()){
				$(this).html("打开开关");
				mySwitch.setValue(false);
			}else{
				$(this).html("关闭开关");
				mySwitch.setValue(true);
			}
		}else{
			Mobile.tip("开关不可用！");
		}
	});
	/*开关禁用和取消*/
	$("#testSetDisabled").tap(function() {
		var mySwitch = WmWebUI.select("mySwitch");
		if (!mySwitch.disabled) {
			$(this).html("取消禁用");
			mySwitch.setDisabled(true);
		} else {
			$(this).html("禁用开关");
			mySwitch.setDisabled(false);
		}
	});
	/*开关文本设置*/
	$("#testSetLable").tap(function(){
		var mySwitch = WmWebUI.select("mySwitch");
		if(mySwitch.getLabel()=="开|关"){
			$(this).html("还原文本");
			mySwitch.setLabel("on|off");
		}else{
			$(this).html("设置文本");
			mySwitch.setLabel("开|关");
		}
	});
	/*开关事件设置*/
	$("#testSetAction").tap(function(){
		var mySwitch = WmWebUI.select("mySwitch");
		if(mySwitch.onAction){
			$(this).html("设置事件");
			mySwitch.setOnAction(null);
			mySwitch.setOffAction(null);
		}else{
			$(this).html("清除事件");
			mySwitch.setOnAction(function(){
				Mobile.tip("开关打开事件");
			});
			mySwitch.setOffAction(function(){
				Mobile.tip("开关关闭事件");
			});
		}
	});
});