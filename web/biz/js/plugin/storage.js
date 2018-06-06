require(["domReady!","mobile","wmPopup","util"], function(doc,Mobile,wmPopup) {
	var popup = new wmPopup("editPopup","confirm","cancel","add2","add1");
	var iscroll = new iScroll("content");
	var Flag0={
			"key1":false,
			"key2":false,
			"key3":false,
			"key4":false,
			"key5":false
	}
	var Flag1={
			"key1":false,
			"key2":false,
			"key3":false,
			"key4":false,
			"key5":false
	}
	//
	Mobile.getMemoryCache(function(json){
		var allValue=$.parseJSON(json);
		$.each(allValue,function(key,value){
			if(value!=""){
				confirmE(0, Flag0, key, value, true);
				Flag1[key]=true;
			}
		});
	},["key1","key2","key3","key4","key5"]);
	
	//将所有已有的离线值，加入对应栏目中。
	Mobile.getOfflineCache(function(json){
		var allValue=$.parseJSON(json);
		$.each(allValue,function(key,value){
			if(value!=""){
				confirmE(1, Flag1, key, value, true);
				Flag1[key]=true;
			}
		});
	},["key1","key2","key3","key4","key5"]);
	
	var type = -1;
	var emptyFlag=[true,true];
	$("#close").tap(function(){
		Mobile.closeApp();
	});
	$("#add1").tap(function() {
		$("#contentVaule").val("");
		type = 0;
	});
	$("#add2").tap(function() {
		$("#contentVaule").val("");
		type = 1;
	});
	$("#confirm").tap(function() {
		var Flag=eval("Flag"+type);
		var k=selectKey(Flag, type);
		confirmE(type,Flag,k);
	});
	function selectKey(obj,fg){
		for(k in obj){
			if(!obj[k]){
				obj[k]=true;
				return k;
			}
		}
		return -1;
	}
	function confirmE(fg,Flag,k,value,hasV){
		if(k==-1){
			alert("请删除部分数据后添加");
			return;
		}
		var kk=k+fg;
		var v = value == null || value == "" ? $("#contentVaule").val() : value;
		if(!hasV){
			switch (fg) {
				case 0:
					Mobile.setMemoryCache(k, v);
					break;
				case 1:
					Mobile.setOfflineCache(k, v);
					break;
			}
		}
		if(emptyFlag[fg]){
			$("#empty"+fg).hide();
			$("#list"+fg).show();
			emptyFlag[fg]=false;
		}
		var str="<li id=\""+kk+"li"+"\"><div class=\"content\"><div class=\"main\" id=\""+kk+"\" ><div class=\"title\" >" + v + "</div></div><div class=\"fn\"><span class=\"e_ico-delete\" id=\""+kk+"del"+"\" ></span></div></div></li>"
		$("#list"+fg+" ul").append(str);
		var main=$("#"+kk);
		var del=$("#"+kk+"del");
		switch (fg) {
			case 0:
				main.tap(function(){
					Mobile.getMemoryCache(alert,k,"没有记录，我是默认值");
				});	
				break;
			case 1:
				main.tap(function(){
					Mobile.getOfflineCache(alert,k,"没有记录，我是默认值");
				});	
				break;
		}
		del.tap(function(){
			switch (fg) {
				case 0:
					Mobile.removeMemoryCache(k);
					break;
				case 1:
					Mobile.removeOfflineCache(k);
					break;
			}
			$("#"+kk+"li").remove();
			if($("#list"+fg+" ul li").size()==0){
				$("#list"+fg).hide();
				$("#empty"+fg).show();
				emptyFlag[fg]=true;
			}
			Flag[k]=false;
			iscroll.refresh();
		});
		iscroll.refresh();
	}
});