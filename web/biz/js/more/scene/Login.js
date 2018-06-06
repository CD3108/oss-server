require(["wmTabbar","common","mobile","oInput","util"], function(WmTabbar,Common,Mobile,oInput) {

	// 页面初始化，初始化验证码
	Common.callSvc("SceneBean.initVerifyCode", null, function(resultData){
		if(typeof(resultData) == "string" ){
			resultData = new Wade.DataMap(resultData);
		}
		$("#verifyImg").attr("src", "data:image/png;base64, " + resultData.get("VERIFY_IMG"));
		
		// 保存session_id(以后每次请求，都会在Common中自动添加此SessionId,以便通过Session校验)
		Common.put("SESSION_ID", resultData.get("SESSION_ID"));
	});
	
	// 刷新验证码
	$("#verifyImg").tap(function(){
		Common.callSvc("SceneBean.refreshVerifyCode", null, function(resultData){
			if(typeof(resultData) == "string" ){
				resultData = new Wade.DataMap(resultData);
			}
			$("#verifyImg").attr("src", "data:image/png;base64, " + resultData.get("VERIFY_IMG"));
			$("#verify").val("");
		});
	})
	
	// 登录
	$("#sceneLoginBtn").tap(function(){
		var loginData = new Wade.DataMap();
		loginData.put("USER_NAME", $("#username").val());
		loginData.put("USER_PASSWORD", $("#password").val());
		loginData.put("VERIFY_CODE", $("#verify").val());
		
		// 输入的参数校验
		var messages = oInput.check();
		if(messages && messages.getMessage()) {
			alert(messages.getMessage());
			return;
		}
		
		// 将前台输入的参数传至后台校验
		Common.callSvc("SceneBean.login",loginData,function(data){
			console.log("结果[用户登陆]：" + data);
			
			if(typeof(resultData) == "string" ){
				resultData = new Wade.DataMap(resultData);
			}
			
			// 保存用户账号
			Common.put("userAccount", data.get("ACCOUNT"));

			// 登录成功或失败之后的操作
			$("#retMsg").text("亲爱的【"+data.get("ACCOUNT")+"】用户，登陆成功");
			$("#loginForm").hide();
			$("#loginResult").show();
		});
	});
	
});