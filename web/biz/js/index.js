require(["common","mobile","util"], function(Common,Mobile) {

	$("#plugin-menu li,#ui-menu li").each(function(index, item) {
		if (item.getAttribute("action") != null) {
			$(item).tap(function() {
				var param = new Wade.DataMap();
				/*特殊符号测试*/
				param.put("flag",";/?:@&=+$,#()[]!#%*'");
				param.put("url","http://www.163.com?action=1&aaaa=2");
				param.put("chinese","中文测试");
				Mobile.openPage(item.getAttribute("action"),param);
			});
		} else if(item.getAttribute("action") == null) {
			$(item).tap(function() {
				var data = new Wade.DataMap();
				data.put("packageName", item.getAttribute("packageName"));
				data.put("className", item.getAttribute("className"));
				data.put("downloadUrl", item.getAttribute("downloadUrl"));
				Mobile.openNative(data);
			});
		} else {
			item.onclick = function() {
				alert("敬请期待……");
			};
		}
	});
});
