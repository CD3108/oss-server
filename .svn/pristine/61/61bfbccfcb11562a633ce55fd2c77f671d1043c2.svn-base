require(["common","wadeMobile","jcl","util"], function(Common, WadeMobile,$) {
	
//	$('#alias').val("Dave");//设置默认用户别名
//	$('#msgForSend').val("今天天气真不错！");//设置内容
	
	$("#register").tap(function() {
		//按钮
		var registerBtn = $('#register');
		
		if(registerBtn.attr('connected') != 'true'){
			var alias = $('#alias').val();
			if (escape(alias).indexOf( "%u" ) >= 0){
				alert("昵称不能含有中文，请重新输入！");
				return;
			}
			//订阅主题/频道
			WadeMobile.registerForPushWithYunba(alias, function(obj){
				if(obj != 'SUC'){
					alert('频道订阅失败！');
				}
				registerBtn.css('background-color', '#ADFF2F');
				registerBtn.attr('connected', 'true');
			});
		}else{
			//退订主题/频道
			WadeMobile.unregisterForPushWithYunba(function(obj){
				if(obj != 'SUC'){
					alert('频道退订失败！')
				}
				registerBtn.css('background-color', '#F7F7F7');
				registerBtn.attr('connected', 'false');
			});
		}
	});
	
	
	$("#send").tap(function() {
		//获取参数
		var array = function(info){
			var index = info.lastIndexOf("@");
			var alias = "ALL", msg = info;
			if(index != -1){
				index += info.substr(index).indexOf(' ');
				msg = info.substr(index).trim();
				
				var aliasInfo = info.substring(0, index).trim();
				var aliasList = alias.split(/\s+/);
				alias = aliasInfo.replace(new RegExp(/(@)/g), '').split(/\s+/)[0];
			}
			
			var result = new Array();
			result.push(alias);//发送用户
			result.push(msg);//发送内容
			return result;
			
		}($('#msgForSend').val());
		
		//发送消息
		WadeMobile.sendTextWithYunba(array[0], array[1], function(obj){
			alert((obj != 'SUC') + '发送结果：' + obj);
			if(obj != 'SUC'){
				alert('消息发送失败！');
			}
		});
	});
	
	window.receiveMessage = function(rec_msg){
		//接收格式为: {"MSG":"内容", "ALIAS":"昵称"}
		var recData = new Wade.DataMap(rec_msg);
		var recAlias = recData.get("ALIAS"), recMsg = recData.get("MSG");
		
		var content = '<br/><span ';
		//如果是自己发言，标注颜色
		if(recAlias == $("#alias").val()){
			content += 'style="color:#ADFF2F"';
			recAlias = '我';
		}
		content += '>' + recAlias + ": " + recMsg + '</span>';
		
		$('#msgForReceive').prepend($(content)); 
	}
	//设置回调函数
	WadeMobile.setCallbackForPushWithYunba("receiveMessage");
});