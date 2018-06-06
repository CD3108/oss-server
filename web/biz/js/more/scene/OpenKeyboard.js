require(["mobile","util"], function(Mobile) {
	
	// 打开原生UI键盘
	$("#pwd").tap(function() {
		Mobile.openKeyboard("onkeydownpassWord");
	});
	
	// 键盘输入的回调，方法名称与Mobile.openKeyboard("onkeydownpassWord");一致
	window.onkeydownpassWord = function(msg){
		if (msg == '-1') {
			$("#pwd").val($("#pwd").val().substring(0, $("#pwd").val().length - 1));
		}else {
			$("#pwd").val($("#pwd").val() + msg);			
		}
	}
	
	$("#submit").tap(function() {
		if($("#pwd").val() == "1234") {
			$("#main").hide();
			$("#hiddenContent").show();
		} else {
			$("#pwd").val("");
			alert("通关密语输入错误，请重新输入");
		}
	});
});