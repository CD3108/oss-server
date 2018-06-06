require(["domReady!","common","util","mobile"], function(doc,Common,util,Mobile) {
	new iScroll("content");
	
	$("#TowerList li").each(function(index, item) {
		if (item.getAttribute("action") != null) {
			$(item).tap(function() {
				var param = new Wade.DataMap();
				/*特殊符号测试*/
				Mobile.openPage(item.getAttribute("action"),param);
			});
		} else {
			item.onclick = function() {
				alert("敬请期待……");
			};
		}
	});
});