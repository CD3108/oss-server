require([ "jcl", "gesture" ], function($) {
	var sendMsg = function(val) {
		var value = $("#msg").html();
		value = value + val;
		$("#msg").html(value);
	}
	var clearMsg = function() {
		$("#msg").html("");
	}
	
	$("#gestureBlock").bind("swipe", function(e) {
		clearMsg();
		sendMsg("你刚才滑动了一下;");
	});
	
	$("#gestureBlock").bind("swipeLeft", function(e) {
		sendMsg("你刚才向左滑动了;");
	});
	$("#gestureBlock").bind("swipeRight", function(e) {
		sendMsg("你刚才向右滑动了;");
	});
	$("#gestureBlock").bind("swipeUp", function(e) {
		sendMsg("你刚才向上滑动了;");
	});
	$("#gestureBlock").bind("swipeDown", function(e) {
		sendMsg("你刚才向下滑动了;");
	});
	
	$("#gestureBlock").bind("tap", function(e) {
		clearMsg();
		sendMsg("你刚才单击了;");
	});
	$("#gestureBlock").bind("doubleTap", function(e) {
		clearMsg();
		sendMsg("你刚才双击了;");
	});
	$("#gestureBlock").bind("longTap", function(e) {
		clearMsg();
		sendMsg("你刚才长按了;");
	});
});